import time

from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.action_chains import ActionChains
from selenium.common.exceptions import TimeoutException

from .config import Settings
from .reusable import Reusable


LOGIN_URL = "https://xstation5.xtb.com/#/_/login"
HOME_URL = "https://xstation5.xtb.com/#/real/loggedIn"


class XtbLoginPage:
    login = (By.CSS_SELECTOR, "input[name='xslogin']")
    password = (By.CSS_SELECTOR, "input[name='xspass']")
    login_button = (By.CSS_SELECTOR, "input[type='button']")

    def __init__(self, driver, settings: Settings):
        self.reusable = Reusable(driver, settings.timeout)
        self.settings = settings

    def login_to_account(self) -> None:
        self.reusable.open_url(LOGIN_URL)
        self.reusable.wait_for_page(LOGIN_URL)
        self.reusable.send_keys(self.login, self.settings.login)
        self.reusable.send_keys(self.password, self.settings.password)
        self.reusable.click(self.login_button)
        self.reusable.wait_for_page(HOME_URL)


class XtbHomePage:
    select_account = (By.XPATH, "//xs-combobox[@title='Zmień konto']")
    demo_account = (By.XPATH, "//span[text()='DEMO']")
    real_account = (By.XPATH, "//span[text()='REAL']")
    search = (By.CSS_SELECTOR, "input[ng-model='searchString']")
    sell_button = (By.CSS_SELECTOR, "click-and-trade-button[id='clickAndTradeButtonBid']")
    buy_button = (By.CSS_SELECTOR, "click-and-trade-button[id='clickAndTradeButtonAsk']")
    close_button = (By.CSS_SELECTOR, "span[data-xsot='ordersCloseTradeBtn']")
    apply_button = (By.CSS_SELECTOR, "button.applyBtn")
    interval_button = (By.XPATH, "//*[@id='chartPanelsContainer']/xchart-panel/div/div[2]/xs-combobox-chart-interval/div/div/button")
    open_position = (By.XPATH, "/html/body/div[1]/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[3]/div/div/div[1]/div/div[5]/div/div/div/div[2]/div[1]/div")
    position_type = (By.XPATH, "/html/body/div[1]/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[3]/div/div/div[1]/div/div[5]/div/div/div/div[2]/div[2]")
    bollinger_bands = (By.XPATH, "//div[contains(@class, 'indicator-label-container')]//span[contains(text(), 'Bollinger [20, 2.5]')]/following-sibling::span[@class='indicator-value-label ng-binding']")
    close_price = (By.XPATH, "//div[contains(@class, 'indicator-label-container')]//span[contains(text(), 'SMA [1, 0]')]/following-sibling::span[@class='indicator-value-label ng-binding']")
    margin_ok = (By.XPATH, "//button[contains(text(), 'Ok')]")
    close_alert = (By.CSS_SELECTOR, "div.xs-alert-close-btn")

    def __init__(self, driver, settings: Settings):
        self.driver = driver
        self.reusable = Reusable(driver, settings.timeout)
        self.settings = settings

    def select_account_type(self, account: str) -> None:
        normalized_account = account.strip().upper()
        if normalized_account == "DEMO":
            account_locator = self.demo_account
        elif normalized_account == "REAL":
            account_locator = self.real_account
        else:
            raise ValueError(f"Unsupported account: {account!r}")

        self.reusable.click(self.select_account)
        self.reusable.click(account_locator)

    def select_symbol(self, symbol: str) -> None:
        self.reusable.send_keys(self.search, symbol)
        self.reusable.send_keys(self.search, Keys.ENTER)

    def select_interval(self, interval: str) -> None:
        self.reusable.click(self.interval_button)
        active = self.driver.switch_to.active_element
        ActionChains(self.driver).move_to_element(active).click().perform()
        for _ in range(20):
            active = self.driver.switch_to.active_element
            if active.text.strip().lower() == interval.lower():
                return
            ActionChains(self.driver).send_keys(Keys.ARROW_DOWN).perform()
            time.sleep(0.1)
        raise RuntimeError(f"Interval not found: {interval}")

    def close_optional_dialog(self, locator) -> None:
        try:
            self.reusable.click(locator)
        except TimeoutException:
            return

    def _indicator(self, locator) -> str:
        return self.reusable.text(locator)

    def current_close_price(self) -> float:
        self.select_symbol(self.settings.symbol)
        value = self._indicator(self.close_price).replace(",", ".")
        return float("".join(char for char in value if char.isdigit() or char == "."))

    def bollinger_values(self) -> tuple[float, float, float]:
        values = self._indicator(self.bollinger_bands).split(", ")
        if len(values) != 3:
            raise ValueError(f"Unexpected Bollinger value: {values!r}")
        return tuple(float(value.replace(",", ".")) for value in values)

    def bollinger_trend(self) -> int:
        current = self.current_close_price()
        upper, _, lower = self.bollinger_values()
        return -1 if current < lower else 1 if current > upper else 0

    def position_type_value(self) -> int:
        try:
            self.reusable.wait_for_visibility(self.open_position)
        except TimeoutException:
            return 0
        position_type = self.reusable.text(self.position_type).strip()
        if position_type == "Sell":
            return -1
        if position_type == "Buy":
            return 1
        raise RuntimeError(f"Unexpected open position type: {position_type!r}")

    def open_buy(self) -> None:
        self.select_symbol(self.settings.symbol)
        self.reusable.click(self.buy_button)
        self.reusable.click(self.apply_button)

    def open_sell(self) -> None:
        self.select_symbol(self.settings.symbol)
        self.reusable.click(self.sell_button)
        self.reusable.click(self.apply_button)

    def close_position(self) -> None:
        self.reusable.click(self.close_button)
        self.reusable.click(self.apply_button)

from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as conditions
from selenium.webdriver.support.ui import WebDriverWait


class Reusable:
    def __init__(self, driver, timeout: int):
        self.driver = driver
        self.wait = WebDriverWait(driver, timeout)

    def open_url(self, url: str) -> None:
        self.driver.get(url)

    def wait_for_page(self, expected_url: str) -> None:
        self.wait.until(conditions.url_to_be(expected_url))

    def wait_for_visibility(self, locator: tuple[By, str]):
        return self.wait.until(conditions.visibility_of_element_located(locator))

    def click(self, locator: tuple[By, str]) -> None:
        self.wait_for_visibility(locator).click()

    def send_keys(self, locator: tuple[By, str], value: str) -> None:
        self.wait_for_visibility(locator).send_keys(value)

    def text(self, locator: tuple[By, str]) -> str:
        return self.wait_for_visibility(locator).text

import os

import pytest
from selenium import webdriver

from adi.config import Settings
from adi.pages import XtbHomePage, XtbLoginPage
from adi.strategy import trade_strategy


@pytest.mark.integration
def test_trade_strategy():
    if os.getenv("RUN_XTB_TEST") != "1":
        pytest.skip("Set RUN_XTB_TEST=1 to run the live XTB integration test.")

    settings = Settings.from_environment()
    driver = webdriver.Edge() if settings.browser.lower() == "edge" else webdriver.Chrome()
    try:
        driver.delete_all_cookies()
        driver.maximize_window()
        trade_strategy(XtbLoginPage(driver, settings), XtbHomePage(driver, settings))
    finally:
        driver.quit()

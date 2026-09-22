from .pages import XtbHomePage, XtbLoginPage


def trade_strategy(login_page: XtbLoginPage, home_page: XtbHomePage) -> None:
    login_page.login_to_account()
    home_page.close_optional_dialog(home_page.margin_ok)
    home_page.close_optional_dialog(home_page.close_alert)
    home_page.select_account_type(home_page.settings.account)
    home_page.select_symbol(home_page.settings.symbol)
    home_page.select_interval(home_page.settings.interval)

    trend = home_page.bollinger_trend()
    position = home_page.position_type_value()
    if trend == 1 and position == -1:
        home_page.close_position()
        home_page.open_buy()
    elif trend == -1 and position == 1:
        home_page.close_position()
        home_page.open_sell()
    elif trend == 0 and position == 1:
        home_page.close_position()
    elif trend == 1 and position == 0:
        home_page.open_buy()
    elif trend == -1 and position == 0:
        home_page.open_sell()

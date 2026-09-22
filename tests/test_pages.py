from unittest.mock import Mock

import pytest

from adi.pages import XtbHomePage


def make_home_page(reusable: Mock) -> XtbHomePage:
    page = object.__new__(XtbHomePage)
    page.reusable = reusable
    return page


def test_select_account_type_rejects_unknown_account_before_clicking():
    reusable = Mock()
    page = make_home_page(reusable)

    with pytest.raises(ValueError, match="Unsupported account"):
        page.select_account_type("staging")

    reusable.click.assert_not_called()


def test_position_type_value_rejects_unknown_position_type():
    reusable = Mock()
    reusable.text.return_value = "Pending"
    page = make_home_page(reusable)

    with pytest.raises(RuntimeError, match="Unexpected open position type"):
        page.position_type_value()

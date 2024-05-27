package adi.enums;

public enum ExpectedPageTitles {
    INVESTING_PAGE("Litecoin (LTC) Technical Analysis - Investing.com"),
    XTB_PAGE("xStation 5");

    private final String ExpectedPageTitle;

    ExpectedPageTitles(String title) {
        this.ExpectedPageTitle = title;
    }

    public String getExpectedPageTitle() {
        return ExpectedPageTitle;
    }
}
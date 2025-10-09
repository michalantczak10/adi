package adi.enums;

public enum Urls {
    XTB_LOGIN_PAGE("https://xstation5.xtb.com/#/_/login"),
    XTB_HOME_PAGE("https://xstation5.xtb.com/#/real/loggedIn");

    private final String url;

    Urls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
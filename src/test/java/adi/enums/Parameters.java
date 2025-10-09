package adi.enums;

public enum Parameters {
    TIMEOUT(10);

    private final int parameter;

    Parameters(int parameter) {
        this.parameter = parameter;
    }

    public int getParameter() {
        return parameter;
    }
}
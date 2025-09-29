package adi.enums;

public enum Parameters {
    WAIT_TIME(30);

    private final int parameter;

    Parameters(int parameter) {
        this.parameter = parameter;
    }

    public int getParameter() {
        return parameter;
    }
}
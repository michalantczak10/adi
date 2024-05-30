package adi.enums;

public enum Parameters {
    WAIT_TIME(60);
    private final int Parameter;

    Parameters(int parameter) {
        this.Parameter = parameter;
    }

    public int getParameter() {
        return Parameter;
    }
}
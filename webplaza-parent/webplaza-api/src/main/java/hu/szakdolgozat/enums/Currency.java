package hu.szakdolgozat.enums;

public enum Currency {
    HUF,
    EUR,
    USD;

    @Override
    public String toString() {
        switch (this) {
            case HUF:
                return "HUF";
            case EUR:
                return "EUR";
            case USD:
                return "USD";
            default:
                return "N/A";
        }
    }
}

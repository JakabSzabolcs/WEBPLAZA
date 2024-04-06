package hu.szakdolgozat.enums;

public enum OrderState {

    NEW,
    IN_PROGRESS,
    UNDER_DELIVERY,
    DONE;
    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "Új";
            case IN_PROGRESS:
                return "Folyamatban";
            case UNDER_DELIVERY:
                return "Kiszállítás alatt";
            case DONE:
                return "Kész";
            default:
                return "N/A";
        }
    }
}

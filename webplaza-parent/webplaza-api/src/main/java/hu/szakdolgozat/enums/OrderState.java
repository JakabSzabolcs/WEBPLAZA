package hu.szakdolgozat.enums;

public enum OrderState {

    NEW,
    WAITING,
    IN_PROGRESS,
    DONE,
    CANCELLED;
    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "Új";
            case WAITING:
                return "Feldolgozás alatt"; //feldolgozás alatt értetődő amikor a futár összeszedi a termékeket
            case IN_PROGRESS:
                return "Folyamatban"; //folyamatban értetődő amikor a futár már úton van a vásárlóhoz
            case DONE:
                return "Kész";
            case CANCELLED:
                return "Törölve";
            default:
                return "N/A";
        }
    }
}

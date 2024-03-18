package hu.szakdolgozat.enums;

public enum MeasureUnit {
    KG,
    L,
    DB,
    M;


    @Override
    public String toString() {
        switch (this) {
            case KG:
                return "kg";
            case L:
                return "l";
            case DB:
                return "db";
            case M:
                return "m";
            default:
                return "N/A";
        }
    }
}

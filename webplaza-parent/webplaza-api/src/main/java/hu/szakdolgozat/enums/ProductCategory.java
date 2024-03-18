package hu.szakdolgozat.enums;

public enum ProductCategory {
    FOOD,
    BOOKS,
    CLOTHES,
    ELECTRONICS,
    OTHER;

    @Override
    public String toString() {
        switch (this) {
            case FOOD:
                return "Élelmiszer";
            case BOOKS:
                return "Könyv";
            case CLOTHES:
                return "Ruha";
            case ELECTRONICS:
                return "Elektronika";
            case OTHER:
                return "Egyéb";
            default:
                return "N/A";
        }
    }
}

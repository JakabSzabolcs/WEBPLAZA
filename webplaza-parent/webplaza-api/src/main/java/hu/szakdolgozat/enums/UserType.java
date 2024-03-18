package hu.szakdolgozat.enums;

public enum UserType {
    CUSTOMER,
    COURIER,
    ADMIN,
    SHOP_OWNER;

    @Override
    public String toString() {
        switch (this) {
            case CUSTOMER:
                return "Vásárló";
            case COURIER:
                return "Futár";
            case ADMIN:
                return "Adminisztrátor";
            case SHOP_OWNER:
                return "Bolt tulajdonos";
            default:
                return "N/A";
        }
    }

}

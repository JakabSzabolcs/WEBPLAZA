package hu.szakdolgozat.enums;

import java.util.List;

public enum UserType {
    CUSTOMER("Vásárló"),
    COURIER("Futár"),
    ADMIN("Adminisztrátor"),
    SHOP_OWNER("Bolt tulajdonos");

    UserType(String s) {
    }
    public static List<UserType> getAll() {
        return List.of(UserType.values());
    }
}

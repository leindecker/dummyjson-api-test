package core;

public enum ResourcePathEnum {
    HEALTH_CHECK_RESOURCE("/test"),
    ADD_PRODUCTS_RESOURCE("/products/add"),
    LOGIN_RESOURCE("/auth/login"),
    AUTH_PRODUCTS_RESOURCE("/auth/products"),
    PRODUCTS_RESOURCE("/products"),
    USERS_RESOURCE("/users");

    private final String value;

    ResourcePathEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

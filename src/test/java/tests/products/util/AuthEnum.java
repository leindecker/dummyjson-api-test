package tests.products.util;

public enum AuthEnum {

    USERNAME("emilys"),
    PASSWORD("emilyspass"),
    EXPIRED_TOKEN("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWF"
            + "pbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6Ikpva"
            + "G5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjg"
            + "iLCJpYXQiOjE3Mjk3MTQ5NzcsImV4cCI6MTcyOTcxODU3N30.b2CU6GMZuUiix6XjNkmcF5WnQuA0EUlb9tOS4a_SApw");
    private final String value;

    AuthEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package model;

public class ProductsBuilder {
    private String title;
    private String description;
    private Float price;
    private Integer discountPercentage;
    private Integer rating;
    private Integer stock;
    private String brand;
    private String category;
    private String thumbnail;

    public ProductsBuilder title(String title) {
        this.title = title;

        return this;
    }

    public ProductsBuilder description(String description) {
        this.description = description;

        return this;
    }

    public ProductsBuilder price(Float price) {
        this.price = price;

        return this;
    }

    public ProductsBuilder discountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;

        return this;
    }

    public ProductsBuilder rating(Integer rating) {
        this.rating = rating;

        return this;
    }

    public ProductsBuilder stock(Integer stock) {
        this.stock = stock;

        return this;
    }

    public ProductsBuilder brand(String brand) {
        this.brand = brand;

        return this;
    }

    public ProductsBuilder category(String category) {
        this.category = category;

        return this;
    }

    public ProductsBuilder thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;

        return this;
    }

    public Products build() {
        return new Products(title, description, price, discountPercentage, rating, stock, brand, category, thumbnail);
    }
}

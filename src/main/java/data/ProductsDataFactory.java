package data;

import model.Products;
import model.ProductsBuilder;
import net.datafaker.Faker;

public class ProductsDataFactory {

    private static final Faker FAKER = new Faker();

    public static Products createValidProduct() {
        return newProduct();
    }

    private static Products newProduct() {
        return new ProductsBuilder()
                .title("Sicredi Automacao")
                .description(FAKER.lorem().paragraph(2))
                .price((float) FAKER.number().randomDouble(2, 10, 100))
                .discountPercentage(FAKER.random().nextInt(1, 6))
                .rating(FAKER.random().nextInt(1, 3))
                .stock(FAKER.random().nextInt(1, 25))
                .brand(FAKER.text().text(4))
                .category(FAKER.color().name())
                .thumbnail("https://i.dummyjson.com/data/products/11/thumnail.jpg").build();
    }
}

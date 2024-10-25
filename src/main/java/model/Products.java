package model;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class Products {

    private String title;
    private String description;
    private Float price;
    private Integer discountPercentage;
    private Integer rating;
    private Integer stock;
    private String brand;
    private String category;
    private String thumbnail;
}

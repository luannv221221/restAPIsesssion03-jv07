package com.ra.model.dto.product;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponseDTO {
    private Long id;
    private String productName;
    private Double price;
    private String image;
    private Boolean status;
    private String categoryName;
}

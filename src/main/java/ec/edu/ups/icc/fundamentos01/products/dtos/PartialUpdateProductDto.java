package ec.edu.ups.icc.fundamentos01.products.dtos;

import jakarta.validation.constraints.*;

public class PartialUpdateProductDto {

    @Size(min = 3, max = 150)
    public String name;

    @DecimalMin(value = "0.0", inclusive = true)
    public Double price;

    @Min(0)
    public Integer stock;
}
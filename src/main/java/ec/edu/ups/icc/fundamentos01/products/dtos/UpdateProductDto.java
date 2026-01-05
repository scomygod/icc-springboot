package ec.edu.ups.icc.fundamentos01.products.dtos;

import jakarta.validation.constraints.*;

public class UpdateProductDto {

    @NotBlank
    @Size(min = 3, max = 150)
    public String name;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    public Double price;

    @NotNull
    @Min(0)
    public Integer stock;
}
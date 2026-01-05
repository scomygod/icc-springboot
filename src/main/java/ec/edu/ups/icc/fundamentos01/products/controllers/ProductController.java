package ec.edu.ups.icc.fundamentos01.products.controllers;

import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.PartialUpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.UpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto create(@Valid @RequestBody CreateProductDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ProductResponseDto> findAll() {
        return service.findAll();
    }

    // ✅ ESTE MÉTODO ES EL QUE FALTABA
    @GetMapping("/{id}")
    public ProductResponseDto findOne(@PathVariable int id) {
        return service.findOne(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(
            @PathVariable int id,
            @Valid @RequestBody UpdateProductDto dto
    ) {
        return service.update(id, dto);
    }

    @PatchMapping("/{id}")
    public ProductResponseDto partialUpdate(
            @PathVariable int id,
            @Valid @RequestBody PartialUpdateProductDto dto
    ) {
        return service.partialUpdate(id, dto);
    }
}
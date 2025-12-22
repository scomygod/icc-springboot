package ec.edu.ups.icc.fundamentos01.products.controllers;

import org.springframework.web.bind.annotation.*;

import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.UpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.PartialUpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public Object findAll() {
        return service.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Object findOne(@PathVariable int id) {
        return service.findOne(id);
    }

    // CREATE
    @PostMapping
    public Object create(@RequestBody CreateProductDto dto) {
        return service.create(dto);
    }

    // PUT (UPDATE COMPLETE)
    @PutMapping("/{id}")
    public Object update(
            @PathVariable int id,
            @RequestBody UpdateProductDto dto) {
        return service.update(id, dto);
    }

    // PATCH (UPDATE PARTIAL)
    @PatchMapping("/{id}")
    public Object partialUpdate(
            @PathVariable int id,
            @RequestBody PartialUpdateProductDto dto) {
        return service.partialUpdate(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id) {
        return service.delete(id);
    }
}
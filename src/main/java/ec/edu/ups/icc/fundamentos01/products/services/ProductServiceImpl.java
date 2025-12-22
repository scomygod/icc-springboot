package ec.edu.ups.icc.fundamentos01.products.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ec.edu.ups.icc.fundamentos01.products.dtos.*;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntitie;
import ec.edu.ups.icc.fundamentos01.products.mappers.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

    private List<ProductEntitie> products = new ArrayList<>();
    private int currentId = 1;

    @Override
    public List<ProductResponseDto> findAll() {
        return products.stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @Override
    public Object findOne(int id) {
        ProductResponseDto product = products.stream()
                .filter(p -> p.getId() == id)
                .map(ProductMapper::toResponse)
                .findFirst()
                .orElse(null);

        if (product == null) {
            return new Object() { public String error = "Product not found"; };
        }

        return product;
    }

    @Override
    public ProductResponseDto create(CreateProductDto dto) {
        ProductEntitie product =
                ProductMapper.toEntity(currentId++, dto.name, dto.cant);
        products.add(product);
        return ProductMapper.toResponse(product);
    }

    @Override
    public Object update(int id, UpdateProductDto dto) {
        ProductEntitie product =
                products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

        if (product == null)
            return new Object() { public String error = "Product not found"; };

        product.setName(dto.name);
        product.setCant(dto.cant);

        return ProductMapper.toResponse(product);
    }

    @Override
    public Object partialUpdate(int id, PartialUpdateProductDto dto) {
        ProductEntitie product =
                products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

        if (product == null)
            return new Object() { public String error = "Product not found"; };

        if (dto.name != null)
            product.setName(dto.name);
        if (dto.cant != null)
            product.setCant(dto.cant);

        return ProductMapper.toResponse(product);
    }

    @Override
    public Object delete(int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);

        if (!removed)
            return new Object() { public String error = "Product not found"; };

        return new Object() { public String message = "Deleted successfully"; };
    }
}
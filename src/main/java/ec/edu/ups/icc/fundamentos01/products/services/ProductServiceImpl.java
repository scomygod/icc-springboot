package ec.edu.ups.icc.fundamentos01.products.services;

import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.PartialUpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.UpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.entities.Product;
import ec.edu.ups.icc.fundamentos01.products.entities.ProductEntity;
import ec.edu.ups.icc.fundamentos01.products.mappers.ProductMapper;
import ec.edu.ups.icc.fundamentos01.products.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public ProductResponseDto create(CreateProductDto dto) {
        if (dto.name == null || dto.name.isBlank() || dto.name.length() < 3)
            throw new IllegalArgumentException("Nombre inválido");

        if (repo.findByName(dto.name).isPresent())
            throw new IllegalStateException("El producto ya existe");

        Product product = ProductMapper.fromCreateDto(dto);
        ProductEntity saved = repo.save(ProductMapper.toEntity(product));
        return ProductMapper.toResponse(ProductMapper.fromEntity(saved));
    }

    @Override
    public List<ProductResponseDto> findAll() {
        // Evita lanzar excepción aunque la DB tenga nombres nulos o productos vacíos
        return repo.findAll().stream()
                .map(ProductMapper::fromEntity)
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto update(int id, UpdateProductDto dto) {
        ProductEntity entity = repo.findById((long) id)
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado"));

        Product product = ProductMapper.fromEntity(entity);
        product = ProductMapper.fromUpdateDto(dto, product);

        ProductEntity updated = repo.save(ProductMapper.toEntity(product));
        return ProductMapper.toResponse(ProductMapper.fromEntity(updated));
    }

    @Override
    public ProductResponseDto partialUpdate(int id, PartialUpdateProductDto dto) {
        ProductEntity entity = repo.findById((long) id)
                .orElseThrow(() -> new IllegalStateException("Producto no encontrado"));

        Product product = ProductMapper.fromEntity(entity);
        product = ProductMapper.fromPartialDto(dto, product);

        ProductEntity updated = repo.save(ProductMapper.toEntity(product));
        return ProductMapper.toResponse(ProductMapper.fromEntity(updated));
    }
}
package ec.edu.ups.icc.fundamentos01.products.services;

import ec.edu.ups.icc.fundamentos01.products.dtos.CreateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.PartialUpdateProductDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.ProductResponseDto;
import ec.edu.ups.icc.fundamentos01.products.dtos.UpdateProductDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto create(CreateProductDto dto);

    List<ProductResponseDto> findAll();

    ProductResponseDto findOne(int id);   // ← ESTE FALTABA

    ProductResponseDto update(int id, UpdateProductDto dto);

    ProductResponseDto partialUpdate(int id, PartialUpdateProductDto dto);
}
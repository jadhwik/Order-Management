package co.instio.service;

import co.instio.dto.ProductView;
import co.instio.entity.Products;

import java.util.List;

public interface ProductService {


    List<ProductView> createProducts(List<Products> products);
    List<ProductView> getAllProducts();
    ProductView getById(Long productId);
    ProductView updateProducts(Long productId,Products products);
    void deleteProducts(Long productId);
}

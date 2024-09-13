package co.instio.service;

import co.instio.dto.ProductView;
import co.instio.entity.Products;
import co.instio.enums.CommonErrorCodeEnum;
import co.instio.exceptions.ServiceException;
import co.instio.mapper.CommonMapper;
import co.instio.repo.ProductsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService{

    private final ProductsRepo productsRepo;
    private final CommonMapper commonMapper;

    @Override
    public List<ProductView> createProducts(List<Products> productss){
        List<Products> fetchedProducts=productsRepo.findAll();
        if(fetchedProducts.isEmpty()){
            List<Products> savedProducts = productsRepo.saveAll(productss);
            return savedProducts.stream()
                    .map(commonMapper::toProductView)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<ProductView> getAllProducts(){
        List<Products> fetchedProducts = productsRepo.findAll();
        if(fetchedProducts.isEmpty()){
            log.error("No data found!");
            throw new ServiceException(CommonErrorCodeEnum.NOT_FOUND);
        }
        return fetchedProducts.stream()
                .map(commonMapper::toProductView)
                .collect(Collectors.toList());
    }

    @Override
    public ProductView getById(Long productsId){
        Products fetchedProducts = productsRepo.findById(productsId).orElse(null);

        if(fetchedProducts == null){
            log.error("No data found for id:{}",productsId);
            throw new ServiceException(CommonErrorCodeEnum.NOT_FOUND);
        }
        return commonMapper.toProductView(fetchedProducts);
    }

    @Override
    public ProductView updateProducts(Long productsId , Products products){
        Products fetchedProducts = productsRepo.findById(productsId).orElse(null);
        if(fetchedProducts == null){
            log.error("No data found for updation for id:{}",productsId);
            throw new ServiceException(CommonErrorCodeEnum.NOT_FOUND);
        }
        fetchedProducts.setProductName(products.getProductName());
        fetchedProducts.setCategory(products.getCategory());
        Products savedProducts = productsRepo.save(fetchedProducts);
        return commonMapper.toProductView(savedProducts);
    }

    @Override
    public void deleteProducts(Long productsId){
        Products fetchedProducts = productsRepo.findById(productsId).orElse(null);
        if(fetchedProducts == null){
            log.error("No data found for deletion with id:{}",productsId);
            throw new ServiceException(CommonErrorCodeEnum.BAD_REQUEST);
        }
        productsRepo.deleteById(productsId);
    }
}

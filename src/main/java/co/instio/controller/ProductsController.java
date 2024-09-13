package co.instio.controller;

import co.instio.dto.ResponseModel;
import co.instio.entity.Products;
import co.instio.exceptions.ControllerException;
import co.instio.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productsService;

    @PostMapping
    public ResponseModel<?> createProducts(@Valid @RequestBody List<Products> products, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw  new ControllerException(bindingResult.getFieldError().toString());
        }
        return ResponseModel.of(productsService.createProducts(products));
    }

    @GetMapping("/all")
    public ResponseModel<?> getAllProducts(){
        return ResponseModel.of(productsService.getAllProducts());
    }

    @GetMapping("{id}")
    public ResponseModel<?> getUserById(@PathVariable("id") Long productId){
        return ResponseModel.of(productsService.getById(productId));
    }

    @PutMapping("{id}")
    public ResponseModel<?> updateProducts(@PathVariable Long productId , @RequestBody Products products){
        return ResponseModel.of(productsService.updateProducts(productId,products));
    }

    @DeleteMapping("{id}")
    public ResponseModel<?> deleteProducts(@PathVariable Long productId){
        productsService.deleteProducts(productId);
        return ResponseModel.of(HttpStatus.OK);
    }

}

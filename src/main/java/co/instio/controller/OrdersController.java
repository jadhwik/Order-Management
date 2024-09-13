package co.instio.controller;

import co.instio.dto.ResponseModel;
import co.instio.entity.Orders;
import co.instio.exceptions.ControllerException;
import co.instio.service.OrderService;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @PostMapping
    public ResponseModel<?> createOrders(@Valid @RequestBody List<Orders> orders, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw  new ControllerException(bindingResult.getFieldError().toString());
        }
        return ResponseModel.of(orderService.createOrders(orders));
    }

    @GetMapping("/all")
    public ResponseModel<?> getAllOrders(){
        return ResponseModel.of(orderService.getAllOrders());
    }

    @GetMapping("{id}")
    public ResponseModel<?> getUserById(@PathVariable("id") Long orderId){
        return ResponseModel.of(orderService.getById(orderId));
    }

    @PutMapping("{id}")
    public ResponseModel<?> updateOrders(@PathVariable Long orderId , @RequestBody Orders orders){
        return ResponseModel.of(orderService.updateOrders(orderId,orders));
    }

    @DeleteMapping("{id}")
    public ResponseModel<?> deleteOrders(@PathVariable Long orderId){
        orderService.deleteOrders(orderId);
        return ResponseModel.of(HttpStatus.OK);
    }

}

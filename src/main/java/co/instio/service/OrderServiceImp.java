package co.instio.service;

import co.instio.dto.OrdersView;
import co.instio.entity.Orders;
import co.instio.enums.CommonErrorCodeEnum;
import co.instio.exceptions.IErrorCode;
import co.instio.exceptions.ServiceException;
import co.instio.mapper.CommonMapper;
import co.instio.repo.OrdersRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImp implements OrderService{

    private final OrdersRepo ordersRepo;
    private final CommonMapper commonMapper;

    @Override
    public List<OrdersView> createOrders(List<Orders> users){
        List<Orders> fetchedOrders=ordersRepo.findAll();
        if(fetchedOrders.isEmpty()){
            List<Orders> savedOrders = ordersRepo.saveAll(users);
            return savedOrders.stream()
                    .map(commonMapper::toOrdersView)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<OrdersView> getAllOrders(){
        List<Orders> fetchedOrders = ordersRepo.findAll();
        if(fetchedOrders.isEmpty()){
            log.error("No data found!");
            throw new ServiceException(CommonErrorCodeEnum.NOT_FOUND);
        }
        return fetchedOrders.stream()
                .map(commonMapper::toOrdersView)
                .collect(Collectors.toList());
    }

    @Override
    public OrdersView getById(Long ordersId){
        Orders fetchedOrders = ordersRepo.findById(ordersId).orElse(null);

        if(fetchedOrders == null){
            log.error("No data found for id:{}",ordersId);
            throw new ServiceException(CommonErrorCodeEnum.BAD_REQUEST);
        }
        return commonMapper.toOrdersView(fetchedOrders);
    }

    @Override
    public OrdersView updateOrders(Long ordersId , Orders orders){
        Orders fetchedOrders = ordersRepo.findById(ordersId).orElse(null);
        if(fetchedOrders == null){
            log.error("No data found for updation for id:{}",ordersId);
            throw new ServiceException(CommonErrorCodeEnum.BAD_REQUEST);
        }
        fetchedOrders.setStatus(orders.getStatus());
        fetchedOrders.setQuantity(orders.getQuantity());
        Orders savedOrders = ordersRepo.save(fetchedOrders);
        return commonMapper.toOrdersView(savedOrders);
    }

    @Override
    public void deleteOrders(Long ordersId){
        Orders fetchedOrders = ordersRepo.findById(ordersId).orElse(null);
        if(fetchedOrders == null){
            log.error("No data found for deletion with id:{}",ordersId);
            throw new ServiceException(CommonErrorCodeEnum.BAD_REQUEST);
        }
        ordersRepo.deleteById(ordersId);
    }
}

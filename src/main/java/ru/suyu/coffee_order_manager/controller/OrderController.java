package ru.suyu.coffee_order_manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.suyu.coffee_order_manager.domain.OrderStatus;
import ru.suyu.coffee_order_manager.dto.CreateOrderRequestDto;
import ru.suyu.coffee_order_manager.dto.OrderDto;
import ru.suyu.coffee_order_manager.service.OrderService;

@Controller
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'WAITER')")
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto ,
                                                Authentication authentication){
        String waiterUsername = authentication.getName();
        OrderDto createdOrder = orderService.createOrder(createOrderRequestDto , waiterUsername);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'WAITER')")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatus orderStatus){
        OrderDto orderDto = orderService.updateOrderStatus(id,orderStatus);
        return ResponseEntity.ok(orderDto);
    }


}

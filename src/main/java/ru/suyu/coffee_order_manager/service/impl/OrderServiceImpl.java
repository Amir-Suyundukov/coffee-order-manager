package ru.suyu.coffee_order_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.suyu.coffee_order_manager.domain.*;
import ru.suyu.coffee_order_manager.dto.CreateOrderRequestDto;
import ru.suyu.coffee_order_manager.dto.OrderDto;
import ru.suyu.coffee_order_manager.mapper.OrderMapper;
import ru.suyu.coffee_order_manager.repository.MenuItemRepository;
import ru.suyu.coffee_order_manager.repository.OrderRepository;
import ru.suyu.coffee_order_manager.repository.UserRepository;
import ru.suyu.coffee_order_manager.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDto createOrder(CreateOrderRequestDto createOrderRequestDto, String waiterUsername) {
        User waiter = userRepository.findByUsername(waiterUsername)
                .orElseThrow(() -> new RuntimeException("Waiter not found " + waiterUsername));

        User customer = null;
        if (createOrderRequestDto.getCustomerUsername() != null
                && !createOrderRequestDto.getCustomerUsername().isBlank()){
            customer = userRepository.findByUsername(createOrderRequestDto.getCustomerUsername())
                    .orElseThrow(() -> new RuntimeException("Customer not found " + createOrderRequestDto.getCustomerUsername()));
        }

        Order newOrder = new Order();
        newOrder.setWaiter(waiter);
        newOrder.setCustomer(customer);
        newOrder.setOrderDateTime(LocalDateTime.now());
        newOrder.setOrderStatus(OrderStatus.NEW);

        Set<OrderItem> orderItems = new HashSet<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CreateOrderRequestDto.OrderItemRequestDto itemDto : createOrderRequestDto.getItems()){
            MenuItem menuItem = menuItemRepository.findById(itemDto.getMenuItemId())
                    .orElseThrow(() -> new RuntimeException("MenuItem nor found: " + itemDto.getMenuItemId()));

            OrderItem orderItem = OrderItem.builder()
                    .order(newOrder)
                    .menuItem(menuItem)
                    .quantity(itemDto.getQuantity())
                    .priceAtOrder(menuItem.getPrice())

                    .subtotal(menuItem.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())))
                    .build();

            orderItems.add(orderItem);
            totalAmount = totalAmount.add(orderItem.getSubtotal());
        }

        newOrder.setItems(orderItems);
        newOrder.setTotalAmount(totalAmount);

        Order saved = orderRepository.save(newOrder);

        return orderMapper.toDto(saved);
    }

    @Override
    public OrderDto findById(Long orderId) {
        return null;
    }

    @Override
    public List<OrderDto> findAll() {
        return List.of();
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, OrderStatus newStatus) {
        return null;
    }
}

package ru.suyu.coffee_order_manager.service;

import ru.suyu.coffee_order_manager.domain.OrderStatus;
import ru.suyu.coffee_order_manager.dto.CreateOrderRequestDto;
import ru.suyu.coffee_order_manager.dto.OrderDto;

import java.util.List;

public interface OrderService {

    /**
     * Создает новый заказ.
     * @param createOrderRequestDto DTO с информацией для создания заказа.
     * @param waiterUsername Имя пользователя (официанта), создающего заказ.
     * @return DTO созданного заказа.
     */
    OrderDto createOrder(CreateOrderRequestDto createOrderRequestDto, String waiterUsername);

    /**
     * Находит заказ по ID.
     * @param orderId ID заказа.
     * @return DTO заказа.
     */
    OrderDto findById(Long orderId);

    /**
     * Находит все заказы.
     * @return Список DTO всех заказов.
     */
    List<OrderDto> findAll();

    /**
     * Обновляет статус заказа.
     * @param orderId ID заказа.
     * @param newStatus Новый статус.
     * @return DTO обновленного заказа.
     */
    OrderDto updateOrderStatus(Long orderId, OrderStatus newStatus);

}

package ru.suyu.coffee_order_manager.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NamedEntityGraph
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "order_id" ,nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "menu_item_id")
    private MenuItem menuItem;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal priceAtOrder;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;
}

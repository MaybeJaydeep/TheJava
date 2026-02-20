package com.example.demo.service;

import com.example.demo.Entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderInvoiceService {
    Order createOrder();
    Order addInvoice(Long orderId, int quantity, BigDecimal unitPrice);
    Order removeInvoice(Long orderId, Long invoiceId);
    Order getOrderById(Long orderId);
    List<Order> getAllOrders();
}

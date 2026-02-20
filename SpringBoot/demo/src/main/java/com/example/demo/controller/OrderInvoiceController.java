package com.example.demo.controller;

import com.example.demo.Entity.Order;
import com.example.demo.service.OrderInvoiceService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderInvoiceController {

    private final OrderInvoiceService orderInvoiceService;

    public OrderInvoiceController(OrderInvoiceService orderInvoiceService) {
        this.orderInvoiceService = orderInvoiceService;
    }

    @PostMapping
    public Order createOrder() {
        return orderInvoiceService.createOrder();
    }

    @PostMapping("/{orderId}/invoices")
    public Order addInvoice(
            @PathVariable Long orderId,
            @RequestParam int quantity,
            @RequestParam BigDecimal unitPrice
    ) {
        return orderInvoiceService.addInvoice(orderId, quantity, unitPrice);
    }

    @DeleteMapping("/{orderId}/invoices/{invoiceId}")
    public Order removeInvoice(
            @PathVariable Long orderId,
            @PathVariable Long invoiceId
    ) {
        return orderInvoiceService.removeInvoice(orderId, invoiceId);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderInvoiceService.getOrderById(orderId);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderInvoiceService.getAllOrders();
    }
}

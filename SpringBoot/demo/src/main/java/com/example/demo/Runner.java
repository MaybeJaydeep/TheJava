package com.example.demo;

import com.example.demo.Entity.Invoice;
import com.example.demo.Entity.Order;
import com.example.demo.service.OrderInvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
public class Runner implements CommandLineRunner {

    private final OrderInvoiceService orderInvoiceService;

    public Runner(OrderInvoiceService orderInvoiceService) {
        this.orderInvoiceService = orderInvoiceService;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== ORDER-INVOICE DEMO START ===");

        Order order1 = orderInvoiceService.createOrder();
        Order order2 = orderInvoiceService.createOrder();
        System.out.println("Created orders: " + order1.getId() + ", " + order2.getId());

        orderInvoiceService.addInvoice(order1.getId(), 2, new BigDecimal("99.99"));
        orderInvoiceService.addInvoice(order1.getId(), 1, new BigDecimal("49.50"));

        Order updatedOrder1 = orderInvoiceService.getOrderById(order1.getId());
        System.out.println("Order " + order1.getId() + " invoice count: " + updatedOrder1.getInvoices().size());

        Long firstInvoiceId = updatedOrder1.getInvoices().stream()
                .map(Invoice::getId)
                .findFirst()
                .orElseThrow();
        orderInvoiceService.removeInvoice(order1.getId(), firstInvoiceId);
        System.out.println("Removed invoice " + firstInvoiceId + " from order " + order1.getId());

        runEdgeCase("Add invoice with quantity 0", () ->
                orderInvoiceService.addInvoice(order1.getId(), 0, new BigDecimal("100.00")));

        runEdgeCase("Add invoice with negative price", () ->
                orderInvoiceService.addInvoice(order1.getId(), 1, new BigDecimal("-1.00")));

        runEdgeCase("Add invoice to non-existing order", () ->
                orderInvoiceService.addInvoice(99999L, 1, new BigDecimal("10.00")));

        Long remainingInvoiceId = orderInvoiceService.getOrderById(order1.getId()).getInvoices().stream()
                .map(Invoice::getId)
                .findFirst()
                .orElse(null);

        if (remainingInvoiceId != null) {
            runEdgeCase("Remove invoice from wrong order", () ->
                    orderInvoiceService.removeInvoice(order2.getId(), remainingInvoiceId));
        }

        runEdgeCase("Get non-existing order", () -> orderInvoiceService.getOrderById(88888L));

        List<Order> allOrders = orderInvoiceService.getAllOrders();
        System.out.println("Total orders in DB: " + allOrders.size());
        System.out.println("=== ORDER-INVOICE DEMO END ===");
    }

    private void runEdgeCase(String title, Runnable action) {
        try {
            action.run();
            System.out.println("[EDGE CASE FAILED] " + title + " -> no exception");
        } catch (Exception ex) {
            System.out.println("[EDGE CASE OK] " + title + " -> " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
    }
}

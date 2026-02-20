package com.example.demo.service.orderInvoiceImplement;

import com.example.demo.Entity.Invoice;
import com.example.demo.Entity.Order;
import com.example.demo.Repository.InvoiceRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.service.OrderInvoiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderInvoiceServiceImpl implements OrderInvoiceService {

    private final OrderRepository orderRepository;
    private final InvoiceRepository invoiceRepository;

    public OrderInvoiceServiceImpl(OrderRepository orderRepository, InvoiceRepository invoiceRepository) {
        this.orderRepository = orderRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    @Transactional
    public Order createOrder() {
        Order order = new Order();
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order addInvoice(Long orderId, int quantity, BigDecimal unitPrice) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Unit price must be greater than 0");
        }

        Order order = getManagedOrder(orderId);

        Invoice invoice = new Invoice();
        invoice.setQuantity(quantity);
        invoice.setUnitPrice(unitPrice);

        order.addInvoice(invoice);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order removeInvoice(Long orderId, Long invoiceId) {
        Order order = getManagedOrder(orderId);
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new EntityNotFoundException("Invoice not found: " + invoiceId));

        if (invoice.getOrder() == null || !orderId.equals(invoice.getOrder().getId())) {
            throw new IllegalArgumentException("Invoice does not belong to order " + orderId);
        }

        order.removeInvoice(invoice);
        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Long orderId) {
        Order order = getManagedOrder(orderId);
        order.getInvoices().size();
        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        orders.forEach(order -> order.getInvoices().size());
        return orders;
    }

    private Order getManagedOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + orderId));
    }
}

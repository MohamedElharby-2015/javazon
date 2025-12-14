package com.example.javazon.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @SequenceGenerator(name = "order_item_seq", sequenceName = "ORDER_ITEM_SEQ", allocationSize = 1)
    private int orderItemId;

    private int quantity;
    private double priceAtPurchase;
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public OrderItem() {}

    public int getOrderItemId()
    { return orderItemId; }

    public int getQuantity() {
        return quantity; }

    public double getPriceAtPurchase()
    { return priceAtPurchase; }

    public double getSubtotal()
    { return subtotal; }

    public Product getProduct()
    { return product; }

    public Order getOrder()
    { return order; }


    public void setOrderItemId(int orderItemId)
    { this.orderItemId = orderItemId; }

    public void setQuantity(int quantity)
    { this.quantity = quantity; }

    public void setPriceAtPurchase(double priceAtPurchase)
    { this.priceAtPurchase = priceAtPurchase; }

    public void setSubtotal(double subtotal)
    { this.subtotal = subtotal; }

    public void setProduct(Product product)
    { this.product = product; }

    public void setOrder(Order order)
    { this.order = order; }
}

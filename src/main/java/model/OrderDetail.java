package model;

import java.util.List;

public class OrderDetail {
    private int id;
    private Product product;
    private Orders orders;
    private int quantity;


    public OrderDetail(int id, Product product, Orders orders, int quantity) {
        this.id = id;
        this.product = product;
        this.orders = orders;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

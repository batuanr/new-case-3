package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Orders {
    private  int id;
    private double amount;
    Date order_date;
    Customer customer;
    List<Product> productList;
    boolean status ;

    public Orders() {
    }

    public Orders(int id, double amount, Date order_date, Customer customer, List<Product> productList, boolean status) {
        this.id = id;
        this.amount = amount;
        this.order_date = order_date;
        this.customer = customer;
        this.productList = productList;
        this.status = status;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

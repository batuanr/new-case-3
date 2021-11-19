package model;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private Type type;
    private Style style;
    private double price;
    private List<Size> sizeList;
    private List<Orders> ordersList;
    private String url;

    public Product() {
    }



    public Product(String name, Type type, Style style, double price,String url) {
        this.name = name;
        this.type = type;
        this.style = style;
        this.price = price;
        this.url=url;
    }

    public Product(int id, String name, Type type, Style style, double price,String url) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.style = style;
        this.price = price;
        this.url=url;
    }

    public Product(String name, Type type, Style style, double price, List<Size> sizeList,String url) {
        this.name = name;
        this.type = type;
        this.style = style;
        this.price = price;
        this.sizeList = sizeList;
        this.url=url;
    }

    public Product(int id, String name, Type type, Style style, double price, List<Size> sizeList,String url) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.style = style;
        this.price = price;
        this.sizeList = sizeList;
        this.url=url;
    }

    public Product(int id, String name, Type type, Style style, double price, List<Size> sizeList, List<Orders> ordersList,String url) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.style = style;
        this.price = price;
        this.sizeList = sizeList;
        this.ordersList = ordersList;
        this.url=url;
    }

    public Product(String name, double price, Style style, Type type) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public List<Size> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<Size> sizeList) {
        this.sizeList = sizeList;
    }
}

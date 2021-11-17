package model;

import java.util.List;

public class Size {
    private int id;
    private String size;
    private List<Product> productList;

    public Size() {
    }

    public Size(String size) {
        this.size = size;
    }

    public Size(int id, String size) {
        this.id = id;
        this.size = size;
    }

    public Size(int id, String size, List<Product> productList) {
        this.id = id;
        this.size = size;
        this.productList = productList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

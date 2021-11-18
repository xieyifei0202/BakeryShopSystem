package com.bakeshop.test;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private int storeId;
    private int userId;
    private ArrayList<String> items;
    private ArrayList<Integer> quantities;
    private ArrayList<Integer> prices;
    private int totalAmount;
    private String date;
    private String time;
    private String customerName;
    private String status;
    private int customerNum;

    public int getOrderId() {
        return orderId;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getUserId() {
        return userId;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public ArrayList<Integer> getQuantities() {
        return quantities;
    }

    public ArrayList<Integer> getPrices() {
        return prices;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getStatus() {
        return status;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public void setQuantities(ArrayList<Integer> quantities) {
        this.quantities = quantities;
    }

    public void setPrices(ArrayList<Integer> prices) {
        this.prices = prices;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }
}

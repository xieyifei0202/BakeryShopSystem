package com.bakeshop.test;

public class Item {
    private int storeId;
    private int itemId;
    private String itemName;
    private int itemPrice;
    private String sort;
    private int remainNum;
    private int itemSales;

    public int getStoreId() {
        return storeId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public String getSort() {
        return sort;
    }

    public int getRemainNum() {
        return remainNum;
    }

    public int getItemSales() {
        return itemSales;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
    }

    public void setItemSales(int itemSales) {
        this.itemSales = itemSales;
    }
}

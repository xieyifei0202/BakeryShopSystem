package com.bakeshop.test;

public class Inventory {
    private int storeId;
    private int materialId;
    private String materialName;
    private int materialNum;
    private int addNum;
    private String addDate;

    public int getStoreId() {
        return storeId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public int getMaterialNum() {
        return materialNum;
    }

    public int getAddNum() {
        return addNum;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setMaterialNum(int materialNum) {
        this.materialNum = materialNum;
    }

    public void setAddNum(int addNum) {
        this.addNum = addNum;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }
}

package com.tarpuq.inventory_service.model;

public class StockItem {
    private final String itemCode;
    private int quantity;

    public StockItem(String itemCode, int quantity) {
        this.itemCode = itemCode;
        this.quantity = quantity;
    }

    public String getItemCode() { return itemCode; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

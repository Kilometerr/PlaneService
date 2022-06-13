package planeServiceTraining.web.dto;

import java.math.BigDecimal;

public class ItemDTO {

    public String name;
    public int quantity;
    public BigDecimal grossPrice;
    public BigDecimal taxRate;
    public String type;

    public ItemDTO() {
    }

    public ItemDTO(String name, String type, int quantity, BigDecimal grossPrice, BigDecimal taxRate) {
        this.name = name;
        this.quantity = quantity;
        this.grossPrice = grossPrice;
        this.taxRate = taxRate;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(BigDecimal grossPrice) {
        this.grossPrice = grossPrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


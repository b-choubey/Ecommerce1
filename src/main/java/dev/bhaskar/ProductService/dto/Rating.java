package dev.bhaskar.ProductService.dto;

import lombok.Getter;
import lombok.Setter;

//@Getter
// @Setter
public class Rating {
    private double rate;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }


}

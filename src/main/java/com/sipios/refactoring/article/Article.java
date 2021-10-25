package com.sipios.refactoring.article;

public abstract class Article {
    double seasonalDiscount;
    double price;

    Article(double seasonalDiscount, double price) {
        this.seasonalDiscount = seasonalDiscount;
        this.price = price;
    }

    public double getSeasonalDiscount() {
        return seasonalDiscount;
    }

    public double getPrice() {
        return price;
    }
}

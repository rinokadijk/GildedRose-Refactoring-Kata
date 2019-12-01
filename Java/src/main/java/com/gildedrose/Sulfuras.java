package com.gildedrose;

class Sulfuras extends Good {

    public Sulfuras(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void adjustQuality() {
        return;
    }

    @Override
    public void adjustSellDate() {
        return;
    }
}

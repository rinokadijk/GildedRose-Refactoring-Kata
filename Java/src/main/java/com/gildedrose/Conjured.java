package com.gildedrose;

class Conjured extends Good {

    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void adjustQuality() {
        this.quality-=2;
        if (this.sellIn < 0) {
            this.quality-=2;
        }
        setQualityWithinBounds();
    }

    @Override
    public void adjustSellDate() {
        super.adjustSellDate();
    }
}

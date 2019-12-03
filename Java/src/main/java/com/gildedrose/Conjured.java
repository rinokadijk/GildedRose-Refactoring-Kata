package com.gildedrose;

class Conjured extends Good {

    Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void adjustQuality() {
        this.quality -= 2;
        if (isPastSellDate()) {
            this.quality -= 2;
        }
        setQualityWithinBounds();
    }

}

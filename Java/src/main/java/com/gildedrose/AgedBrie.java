package com.gildedrose;

class AgedBrie extends Good {

    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void adjustQuality() {
        this.quality++;
        if (this.sellIn < 0) {
            this.quality++;
        }
        setQualityWithinBounds();
    }

    @Override
    public void adjustSellDate() {
        super.adjustSellDate();
    }
}

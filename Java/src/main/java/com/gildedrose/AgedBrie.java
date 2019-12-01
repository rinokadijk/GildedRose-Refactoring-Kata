package com.gildedrose;

class AgedBrie extends Good {

    AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void adjustQuality() {
        this.quality++;
        if (isPastSellDate()) {
            this.quality++;
        }
        setQualityWithinBounds();
    }

    @Override
    public void adjustSellDate() {
        super.adjustSellDate();
    }
}

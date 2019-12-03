package com.gildedrose;

class Sulfuras extends Good {

    Sulfuras(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    /**
     * Sulfuras adjustQuality is implemented as a no-op
     */
    @Override
    public void adjustQuality() {
    }

    /**
     * Sulfuras adjustSellDate is implemented as a no-op
     */
    @Override
    public void adjustSellDate() {
    }

    /**
     * Sulfuras checkQualityWithinBounds is implemented as a no-op (quality can be out of bounds)
     */
    @Override
    void checkQualityWithinBounds(Item item) {
    }
}

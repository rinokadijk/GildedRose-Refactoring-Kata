package com.gildedrose;

class BackstagePasses extends Good {

    private static final int BY_THREE_DATE = 5;
    private static final int BY_TWO_DATE = 10;

    BackstagePasses(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void adjustQuality() {
        if (isPastSellDate()) {
            this.quality = 0;
            return;
        }
        this.quality++;
        increaseQualityTenDaysBeforeConcert();
        increaseQualityFiveDaysBeforeConcert();
        setQualityWithinBounds();
    }

    private void increaseQualityFiveDaysBeforeConcert() {
        if (this.sellIn <= BY_THREE_DATE) {
            this.quality++;
        }
    }

    private void increaseQualityTenDaysBeforeConcert() {
        if (this.sellIn <= BY_TWO_DATE) {
            this.quality++;
        }
    }

}

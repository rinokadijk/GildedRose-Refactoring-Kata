package com.gildedrose;

class BackstagePasses extends Good {

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
        if (this.sellIn < 6) {
            this.quality++;
        }
    }

    private void increaseQualityTenDaysBeforeConcert() {
        if (this.sellIn < 11) {
            this.quality++;
        }
    }

    @Override
    public void adjustSellDate() {
        super.adjustSellDate();
    }
}

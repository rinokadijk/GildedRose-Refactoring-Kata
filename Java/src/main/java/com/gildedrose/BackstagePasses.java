package com.gildedrose;

class BackstagePasses extends Good {

    public BackstagePasses(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void adjustQuality() {
        if (this.sellIn <= 0) {
            this.quality = 0;
            return;
        }

        this.quality++;
        if (this.sellIn < 11) {
            this.quality++;
        }
        if (this.sellIn < 6) {
            this.quality++;
        }
        setQualityWithinBounds();
    }

    @Override
    public void adjustSellDate() {
        super.adjustSellDate();
    }
}

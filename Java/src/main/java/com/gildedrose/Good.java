package com.gildedrose;

/**
 * Good checks quality constraints when creating a new Item
 */
class Good extends Item {

    private static final int UPPER_QUALITY_BOUND = 50;
    private static final int LOWER_QUALITY_BOUND = 0;

    Good(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        this.checkQualityWithinBounds(this);
    }

    private void checkQualityWithinBounds(Item item) {
        if (item.quality < LOWER_QUALITY_BOUND || (item.quality > UPPER_QUALITY_BOUND && !"Sulfuras, Hand of Ragnaros".equals(item.name))) {
            throw new IllegalStateException("Name must not be null");
        }
    }

    public void adjustQuality() {
        this.quality--;
        if (isPastSellDate()) {
            this.quality--;
        }
        setQualityWithinBounds();
    }

    boolean isPastSellDate() {
        return this.sellIn <= 0;
    }

    void setQualityWithinBounds() {
        this.quality = Math.min(UPPER_QUALITY_BOUND, this.quality);
        this.quality = Math.max(LOWER_QUALITY_BOUND, this.quality);
    }

    public void adjustSellDate() {
        sellIn--;
    }

    static Good create(Item item) {
        if (item.name == null) {
            throw new IllegalStateException("Name must not be null");
        }
        switch (item.name) {
            case "Sulfuras, Hand of Ragnaros":
                return new Sulfuras(item.name, item.sellIn, item.quality);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePasses(item.name, item.sellIn, item.quality);
            case "Aged Brie":
                return new AgedBrie(item.name, item.sellIn, item.quality);
            case "Conjured Mana Cake":
                return new Conjured(item.name, item.sellIn, item.quality);
            default:
                return new Good(item.name, item.sellIn, item.quality);
        }
    }
}

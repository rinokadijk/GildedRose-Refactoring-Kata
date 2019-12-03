package com.gildedrose;

/**
 * Good checks quality constraints when creating a new Item
 */
class Good extends Item {

    private static final int UPPER_QUALITY_BOUND = 50;
    private static final int LOWER_QUALITY_BOUND = 0;

    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String CONJURED = "Conjured Mana Cake";

    Good(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        this.checkQualityWithinBounds(this);
    }

    void checkQualityWithinBounds(Item item) {
        if (item.quality < LOWER_QUALITY_BOUND || item.quality > UPPER_QUALITY_BOUND) {
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
            case SULFURAS:
                return new Sulfuras(item.name, item.sellIn, item.quality);
            case BACKSTAGE_PASSES:
                return new BackstagePasses(item.name, item.sellIn, item.quality);
            case AGED_BRIE:
                return new AgedBrie(item.name, item.sellIn, item.quality);
            case CONJURED:
                return new Conjured(item.name, item.sellIn, item.quality);
            default:
                return new Good(item.name, item.sellIn, item.quality);
        }
    }
}

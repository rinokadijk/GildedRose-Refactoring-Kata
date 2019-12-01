package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Good[] items;

    public GildedRose(Item[] items) {
        this.items = Arrays.stream(items).map(Good::create).toArray(Good[]::new);
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            items[i].adjustQuality();
            items[i].adjustSellDate();
        }
    }
}
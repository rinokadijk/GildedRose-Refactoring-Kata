package com.gildedrose;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    private static final int ANY_VALUE = 1;
    private static final String ANY_ITEM = "foo";
    private static final Item NEGATIVE_QUALITY_ITEM = new Item(ANY_ITEM, ANY_VALUE, -1);

    @Test
    void assertGoldenMaster() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        TexttestFixture.main(new String[]{});
        assertThat("OMGHAI!\n" +
            "-------- day 0 --------\n" +
            "name, sellIn, quality\n" +
            "+5 Dexterity Vest, 10, 20\n" +
            "Aged Brie, 2, 0\n" +
            "Elixir of the Mongoose, 5, 7\n" +
            "Sulfuras, Hand of Ragnaros, 0, 80\n" +
            "Sulfuras, Hand of Ragnaros, -1, 80\n" +
            "Backstage passes to a TAFKAL80ETC concert, 15, 20\n" +
            "Backstage passes to a TAFKAL80ETC concert, 10, 49\n" +
            "Backstage passes to a TAFKAL80ETC concert, 5, 49\n" +
            "Conjured Mana Cake, 3, 6\n" +
            "\n" +
            "-------- day 1 --------\n" +
            "name, sellIn, quality\n" +
            "+5 Dexterity Vest, 9, 19\n" +
            "Aged Brie, 1, 1\n" +
            "Elixir of the Mongoose, 4, 6\n" +
            "Sulfuras, Hand of Ragnaros, 0, 80\n" +
            "Sulfuras, Hand of Ragnaros, -1, 80\n" +
            "Backstage passes to a TAFKAL80ETC concert, 14, 21\n" +
            "Backstage passes to a TAFKAL80ETC concert, 9, 50\n" +
            "Backstage passes to a TAFKAL80ETC concert, 4, 50\n" +
            "Conjured Mana Cake, 2, 5\n\n", is(baos.toString()));
    }

    @Test
    void whenEndOfDay_ThenSystemLowersQuality() {
        Item[] items = new Item[]{new Item(ANY_ITEM, ANY_VALUE, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void whenEndOfDay_ThenSystemLowersSellIn() {
        Item[] items = new Item[]{new Item(ANY_ITEM, 1, ANY_VALUE)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
    }

    @Test
    void whenSellDatePassed_ThenQualityDegradesTwiceAsFast() {
        Item[] items = new Item[]{new Item(ANY_ITEM, 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void whenQualityIsZeroBeforeSellDate_ThenAtEndOfDayQualityIsStillZero() {
        Item[] items = new Item[]{new Item(ANY_ITEM, 1, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void whenQualityIsZeroAfterSellDate_ThenAtEndOfDayQualityIsStillZero() {
        Item[] items = new Item[]{new Item(ANY_ITEM, 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void whenAgedBrie_ThenAtEndOfDayQualityIncreased() {
        Item[] items = new Item[]{new Item("Aged Brie", ANY_VALUE, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void whenAgedBrie_ThenAtEndOfDayQualityNeverExceedsFifty() {
        Item[] items = new Item[]{new Item("Aged Brie", ANY_VALUE, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void whenAgedBrieAfterSellDate_ThenAtEndOfDayQualityNeverExceedsFifty() {
        Item[] items = new Item[]{new Item("Aged Brie", -1, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    @Disabled
    void whenSulfuras_ThenAtEndOfDayQualityIsSameAsYesterday() {
        Item[] items = new Item[]{new Item("Sulfuras", ANY_VALUE, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }

    @Test
    @Disabled
    void whenSulfuras_ThenAtEndOfDaySellInIsSameAsYesterday() {
        Item[] items = new Item[]{new Item("Sulfuras", Integer.MIN_VALUE, ANY_VALUE)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(Integer.MIN_VALUE, app.items[0].sellIn);
    }

    @Test
    void whenBackstagePassesWithOverTenDaysLeft_ThenAtEndOfDayQualityIncreases() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 11, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }

    @Test
    void whenBackstagePassesWithTenDaysLeft_ThenAtEndOfDayQualityIncreasesByTwo() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 10, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void whenBackstagePassesWithFiveDaysLeft_ThenAtEndOfDayQualityIncreasesByThree() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
    }

    @Test
    void whenBackstagePassesExpired_ThenAtEndOfDayQualityIsZero() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void whenBackstagePasses_ThenAtEndOfDayQualityNeverExceedsFifty() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    @Disabled
    void whenConjured_ThenAtEndOfDayQualityDecreasedByTwo() {
        Item[] items = new Item[]{new Item("Conjured", ANY_VALUE, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    @Disabled
    void whenConjured_ThenAtEndOfDayQualityNeverDropsBelowZero() {
        Item[] items = new Item[]{new Item("Conjured", ANY_VALUE, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    @Disabled
    void whenNotSulfurasAndQualityAboveFifty_ThenIllegalStateExceptionAtSystemStartup() {
        Item[] items = new Item[]{new Item(ANY_ITEM, ANY_VALUE, 51)};
        assertThrows(IllegalStateException.class,
            () -> new GildedRose(items),
            "Expected constructor to throw, but it didn't");
    }

    @Test
    @Disabled
    void whenQualityNegative_ThenIllegalStateExceptionAtSystemStartup() {
        Item[] items = new Item[]{new Item(ANY_ITEM, ANY_VALUE, -1)};
        assertThrows(IllegalStateException.class,
            () -> new GildedRose(items),
            "Expected constructor to throw, but it didn't");
    }

    @Test
    @Disabled
    void whenNameNull_ThenIllegalStateExceptionAtSystemStartup() {
        Item[] items = new Item[]{new Item(null, ANY_VALUE, 0)};
        assertThrows(IllegalStateException.class,
            () -> new GildedRose(items),
            "Expected constructor to throw, but it didn't");
    }

    @Test
    @Disabled
    void whenItemReplacedDuringDay_ThenUseValuesFromSystemStartup() {
        Item[] items = new Item[]{new Item(ANY_ITEM, ANY_VALUE, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        items[0] = NEGATIVE_QUALITY_ITEM;
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }
}

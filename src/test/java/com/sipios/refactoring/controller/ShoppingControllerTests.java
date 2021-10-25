package com.sipios.refactoring.controller;

import com.sipios.refactoring.UnitTest;
import com.sipios.refactoring.article.DressArticle;
import com.sipios.refactoring.article.JacketArticle;
import com.sipios.refactoring.article.TshirtArticle;
import com.sipios.refactoring.customer.PlatiniumCustomer;
import com.sipios.refactoring.customer.PremiumCustomer;
import com.sipios.refactoring.customer.StandardCustomer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class ShoppingControllerTests extends UnitTest {

    @InjectMocks
    private ShoppingController controller;

    @Test
    void should_not_throw() {
        Assertions.assertDoesNotThrow(
            () -> controller.getPrice(new Body(new Item[] {}, new StandardCustomer()))
        );
    }

    /*@Test
    void should_throw() {
        Assertions.assertThrows(
            ResponseStatusException.class,
            () -> controller.getPrice(new Body(new Item[] {}, CustomerType))
        );
    }*/

    @Test
    void should_checkStandardCustomerNoArticle() {
        Assertions.assertEquals("0.0",
            controller.getPrice(new Body(new Item[] {}, new StandardCustomer()))
        );
    }

    @Test
    void should_checkPremiumCustomerNoArticle() {
        Assertions.assertEquals("0.0",
            controller.getPrice(new Body(new Item[] {}, new PremiumCustomer()))
        );
    }

    @Test
    void should_checkPlatiniumCustomerArticle() {
        Assertions.assertEquals("0.0",
            controller.getPrice(new Body(new Item[] {}, new PlatiniumCustomer()))
        );
    }

    @Test
    void should_checkStandardCustomerTeeShirt() {
        Assertions.assertEquals("30.0",
            controller.getPrice(new Body(new Item[] {new Item(new TshirtArticle(), 1)}, new StandardCustomer()))
        );
    }

    @Test
    void should_checkStandardCustomerDress() {
        Assertions.assertEquals("50.0",
            controller.getPrice(new Body(new Item[] {new Item(new DressArticle(), 1)}, new StandardCustomer()))
        );
    }

    @Test
    void should_checkStandardCustomerJacket() {
        Assertions.assertEquals("100.0",
            controller.getPrice(new Body(new Item[] {new Item(new JacketArticle(), 1)}, new StandardCustomer()))
        );
    }

    @Test
    void should_checkStandardCustomerALotOfProducts() {
        Assertions.assertEquals("180.0",
            controller.getPrice(new Body(new Item[] {new Item (new TshirtArticle(), 1), new Item(new DressArticle(), 1), new Item(new JacketArticle(), 1)}, new StandardCustomer()))
        );
    }

    @Test
    void should_checkCalculatePriceStandardCustomerTshirtNotSeasonal() {
        Assertions.assertEquals(30, controller.calculatePrice(new Body(new Item[] {new Item(new TshirtArticle(), 1)}, new StandardCustomer()), false));
    }
    @Test
    void should_checkCalculatePriceStandardCustomerTshirtSeasonal() {
        Assertions.assertEquals(30, controller.calculatePrice(new Body(new Item[] {new Item(new TshirtArticle(), 1)}, new StandardCustomer()), true));
    }
    @Test
    void should_checkCalculatePricePremiumCustomerTshirtNotSeasonal() {
        Assertions.assertEquals(27, controller.calculatePrice(new Body(new Item[] {new Item(new TshirtArticle(), 1)}, new PremiumCustomer()), false));
    }
    @Test
    void should_checkCalculatePricePremiumCustomerTshirtSeasonal() {
        Assertions.assertEquals(27, controller.calculatePrice(new Body(new Item[] {new Item(new TshirtArticle(), 1)}, new PremiumCustomer()), true));
    }
    @Test
    void should_checkCalculatePricePlatiniumCustomerTshirtNotSeasonal() {
        Assertions.assertEquals(15, controller.calculatePrice(new Body(new Item[] {new Item(new TshirtArticle(), 1)}, new PlatiniumCustomer()), false));
    }
    @Test
    void should_checkCalculatePricePlatiniumCustomerTshirtSeasonal() {
        Assertions.assertEquals(15, controller.calculatePrice(new Body(new Item[] {new Item(new TshirtArticle(), 1)}, new PlatiniumCustomer()), true));
    }
    @Test
    void should_checkCalculatePriceStandardCustomerJacketNotSeasonal() {
        Assertions.assertEquals(100, controller.calculatePrice(new Body(new Item[] {new Item(new JacketArticle(), 1)}, new StandardCustomer()), false));
    }
    @Test
    void should_checkCalculatePriceStandardCustomerJacketSeasonal() {
        Assertions.assertEquals(90, controller.calculatePrice(new Body(new Item[] {new Item(new JacketArticle(), 1)}, new StandardCustomer()), true));
    }
    @Test
    void should_checkCalculatePricePremiumCustomerJacketNotSeasonal() {
        Assertions.assertEquals(90, controller.calculatePrice(new Body(new Item[] {new Item(new JacketArticle(), 1)}, new PremiumCustomer()), false));
    }
    @Test
    void should_checkCalculatePricePremiumCustomerJacketSeasonal() {
        Assertions.assertEquals(81, controller.calculatePrice(new Body(new Item[] {new Item(new JacketArticle(), 1)}, new PremiumCustomer()), true));
    }
    @Test
    void should_checkCalculatePricePlatiniumCustomerJacketNotSeasonal() {
        Assertions.assertEquals(50, controller.calculatePrice(new Body(new Item[] {new Item(new JacketArticle(), 1)}, new PlatiniumCustomer()), false));
    }
    @Test
    void should_checkCalculatePricePlatiniumCustomerJacketSeasonal() {
        Assertions.assertEquals(45, controller.calculatePrice(new Body(new Item[] {new Item(new JacketArticle(), 1)}, new PlatiniumCustomer()), true));
    }
    @Test
    void should_checkCalculatePriceStandardCustomerDressNotSeasonal() {
        Assertions.assertEquals(50, controller.calculatePrice(new Body(new Item[] {new Item(new DressArticle(), 1)}, new StandardCustomer()), false));
    }
    @Test
    void should_checkCalculatePriceStandardCustomerDressSeasonal() {
        Assertions.assertEquals(40, controller.calculatePrice(new Body(new Item[] {new Item(new DressArticle(), 1)}, new StandardCustomer()), true));
    }
    @Test
    void should_checkCalculatePricePremiumCustomerDressNotSeasonal() {
        Assertions.assertEquals(45, controller.calculatePrice(new Body(new Item[] {new Item(new DressArticle(), 1)}, new PremiumCustomer()), false));
    }
    @Test
    void should_checkCalculatePricePremiumCustomerDressSeasonal() {
        Assertions.assertEquals(36, controller.calculatePrice(new Body(new Item[] {new Item(new DressArticle(), 1)}, new PremiumCustomer()), true));
    }
    @Test
    void should_checkCalculatePricePlatiniumCustomerDressNotSeasonal() {
        Assertions.assertEquals(25, controller.calculatePrice(new Body(new Item[] {new Item(new DressArticle(), 1)}, new PlatiniumCustomer()), false));
    }
    @Test
    void should_checkCalculatePricePlatiniumCustomerDressSeasonal() {
        Assertions.assertEquals(20, controller.calculatePrice(new Body(new Item[] {new Item(new DressArticle(), 1)}, new PlatiniumCustomer()), true));
    }
}

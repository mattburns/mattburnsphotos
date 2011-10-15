package uk.co.mattburns.sitemaker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PricePlanTest {

    @Test(expected = RuntimeException.class)
    public void test_google_html_for_none() {
        PricePlan.NONE.getGoogleCartHtml();
    }

    @Test
    public void test_google_html_for_portrait() {
        assertEquals(
                "<select class=\"product-attr-custom\">"
                        + "<option value=\"6x4\" googlecart-set-product-price=\"1.00\">6\"x4\" - &pound;1.00</option>"
                        + "<option value=\"6x4m\" googlecart-set-product-price=\"3.00\">6\"x4\" mounted - &pound;3.00</option>"
                        + "<option value=\"9x6\" googlecart-set-product-price=\"5.00\">9\"x6\" - &pound;5.00</option>"
                        + "<option value=\"8x6\" googlecart-set-product-price=\"5.00\">8\"x6\" - &pound;5.00</option>"
                        + "<option value=\"8x6m\" googlecart-set-product-price=\"7.00\" selected=\"selected\">8\"x6\" mounted - &pound;7.00</option>"
                        + "<option value=\"15x10\" googlecart-set-product-price=\"15.00\">15\"x10\" - &pound;15.00</option>"
                        + "<option value=\"8x6f\" googlecart-set-product-price=\"20.00\">8\"x6\" mounted and framed - &pound;20.00</option>"
                        + "<option value=\"30x20\" googlecart-set-product-price=\"40.00\">30\"x20\" - &pound;40.00</option>"
                        + "<option value=\"albumNameTag album on DVD\" googlecart-set-product-price=\"50.00\">albumNameTag album on DVD - &pound;50.00</option>"
                        + "</select><input type=\"hidden\" class=\"product-price\" value=\"7.00\">",

                PricePlan.PORTRAIT_2009.getGoogleCartHtml());
    }
}

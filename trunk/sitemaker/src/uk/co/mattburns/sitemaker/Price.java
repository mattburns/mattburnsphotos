package uk.co.mattburns.sitemaker;

public class Price {

    public static String getPricing(PricePlan plan) {
        switch (plan) {
        case PORTRAIT:
            return getPortraitPrice();
        case SCHOOL:
            return getSchoolPrice();
        default:
            throw new RuntimeException();
        }
    }

    private static String getPortraitPrice() {
        StringBuilder sb = new StringBuilder();

        sb.append("<select class=\"product-attr-custom\">");
        sb.append("<option value=\"6x4\"   googlecart-set-product-price=\"1.00\">6\"x4\" - &pound;1.00</option>");
        sb
                .append("<option value=\"9x6\"   googlecart-set-product-price=\"5.00\" selected=\"selected\">9\"x6\" - &pound;5.00</option>");
        sb.append("<option value=\"15x10\" googlecart-set-product-price=\"15.00\">15\"x10\" - &pound;15.00</option>");
        sb.append("<option value=\"30x20\" googlecart-set-product-price=\"30.00\">30\"x20\" - &pound;30.00</option>");
        sb
                .append("<option value=\"albumNameTag album on DVD\" googlecart-set-product-price=\"50.00\">albumNameTag album on DVD - &pound;50.00</option>");
        sb.append("</select>");
        sb.append("<input type=\"hidden\" class=\"product-price\" value=\"5.00\">");
        return sb.toString();
    }

    private static String getSchoolPrice() {
        StringBuilder sb = new StringBuilder();
        sb.append("<select class=\"product-attr-custom\">");
        sb.append("<option value=\"6x4\"   googlecart-set-product-price=\"1.00\">6\"x4\" - &pound;1.00</option>");
        sb
                .append("<option value=\"6x4m\"   googlecart-set-product-price=\"3.00\">6\"x4\" mounted - &pound;3.00</option>");
        sb.append("<option value=\"8x6\" googlecart-set-product-price=\"5.00\">8\"x6\" - &pound;5.00</option>");
        sb
                .append("<option value=\"8x6m\" googlecart-set-product-price=\"7.00\" selected=\"selected\">8\"x6\" mounted - &pound;7.00</option>");
        sb
                .append("<option value=\"8x6f\" googlecart-set-product-price=\"17.00\">8\"x6\" mounted and framed - &pound;17.00</option>");
        sb.append("</select>");
        sb.append("<input type=\"hidden\" class=\"product-price\" value=\"7.00\">");
        return sb.toString();
    }
}

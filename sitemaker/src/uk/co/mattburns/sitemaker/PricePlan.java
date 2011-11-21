package uk.co.mattburns.sitemaker;

import java.util.ArrayList;
import java.util.List;

public enum PricePlan {
    PORTRAIT_2011, PORTRAIT_2009, SCHOOL, NONE;

    private List<Product> products;
    private Product defaultProduct;

    private final Product sixByFour = new Product("1.00", "6\"x4\"", "6x4");
    private final Product sixByFourMounted = new Product("3.00",
            "6\"x4\" mounted", "6x4m");
    private final Product nineBySix = new Product("5.00", "9\"x6\"", "9x6");
    private final Product eightBySix = new Product("5.00", "8\"x6\"", "8x6");
    private final Product eightBySixMounted = new Product("7.00",
            "8\"x6\" mounted", "8x6m");
    private final Product eightBySixFramed = new Product("20.00",
            "8\"x6\" mounted and framed", "8x6f");
    private final Product eightBySixFramed2011 = new Product("35.00",
            "8\"x6\" mounted and framed", "8x6f");
    private final Product fifteenByTen = new Product("15.00", "15\"x10\"",
            "15x10");
    private final Product fifteenByTen2011 = new Product("25.00", "15\"x10\"",
            "15x10");
    private final Product thirtyByTwenty = new Product("40.00", "30\"x20\"",
            "30x20");
    private final Product thirtyByTwenty2011 = new Product("90.00",
            "30\"x20\"", "30x20");
    private final Product dvd50Quid = new Product("50.00",
            "albumNameTag album on DVD", "albumNameTag album on DVD");
    private final Product dvd100Quid = new Product("100.00",
            "albumNameTag album on DVD", "albumNameTag album on DVD");

    public String getGoogleCartHtml() {
        if (this == NONE) {
            throw new RuntimeException();
        }

        StringBuilder sb = new StringBuilder();

        sb.append("<select class=\"product-attr-custom\">");

        for (Product product : getProducts()) {
            sb.append("<option value=\""
                    + product.getCode()
                    + "\" googlecart-set-product-price=\""
                    + product.getPrice()
                    + "\""
                    + (defaultProduct == product ? " selected=\"selected\""
                            : "") + ">" + product.getName() + " - &pound;"
                    + product.getPrice() + "</option>");
        }

        sb.append("</select>");
        sb.append("<input type=\"hidden\" class=\"product-price\" value=\""
                + getDefaultProduct().getPrice() + "\">");

        return sb.toString();
    }

    public Product getDefaultProduct() {
        initProducts();
        return defaultProduct;
    }

    public List<Product> getProducts() {
        initProducts();
        return products;
    }

    public void initProducts() {
        if (products == null) {
            products = new ArrayList<Product>();

            switch (this) {
            case PORTRAIT_2009:
                products.add(sixByFour);
                products.add(sixByFourMounted);
                products.add(nineBySix);
                products.add(eightBySix);
                products.add(eightBySixMounted);
                products.add(fifteenByTen);
                products.add(eightBySixFramed);
                products.add(thirtyByTwenty);
                products.add(dvd50Quid);
                break;
            case PORTRAIT_2011:
                products.add(sixByFour);
                products.add(sixByFourMounted);
                products.add(nineBySix);
                products.add(eightBySix);
                products.add(eightBySixMounted);
                products.add(fifteenByTen2011);
                products.add(eightBySixFramed2011);
                products.add(thirtyByTwenty2011);
                products.add(dvd100Quid);
                break;
            case SCHOOL:
                products.add(sixByFour);
                products.add(sixByFourMounted);
                products.add(nineBySix);
                products.add(eightBySix);
                products.add(eightBySixMounted);
                products.add(eightBySixFramed);

                break;
            case NONE:
                break;
            default:
                throw new RuntimeException("No product set defined");
            }

            defaultProduct = eightBySixMounted;
        }
    }

    public static class Product {
        private final String price;
        private final String name;
        private final String code;

        private Product(String price, String name, String code) {
            super();
            this.price = price;
            this.name = name;
            this.code = code;
        }

        public String getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }
}

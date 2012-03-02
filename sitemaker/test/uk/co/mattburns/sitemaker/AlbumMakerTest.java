package uk.co.mattburns.sitemaker;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class AlbumMakerTest {

    @Test
    public void generateAlbums() throws IOException {
        new AlbumMaker(
                AlbumMaker.PORTFOLIO,
                AlbumMaker.PORTFOLIO,
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/website_portfolio"),
                PricePlan.NONE);
        new AlbumMaker(
                "ashley2224",
                "Ashley",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010_01_31_DVD_Ashley/edited"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "grice",
                "Grice",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010_01_24_export_2010_01_25"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "grandad",
                "Grandad",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010_01_15_export"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "stleonards",
                "Tony and Claire",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2009/2009_12_28_DVD_tonys_wedding/disc01/edited"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "aidan313",
                "Aidan's Baptism",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010-03-13_export"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "sophie2010",
                "Sophie's Baptism",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010-04-24_sophie_DVD/edited"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "skatecal20103147940776278",
                "Longboard Calendar",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010-05-30_export_gimped"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "skatecalalt20103147940776278",
                "Longboard Calendar alternatives",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010-05-30_alternatives"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "kcps2010",
                "Kings Court Leaving Prom",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010-07-15_export"),
                PricePlan.SCHOOL);
        new AlbumMaker(
                "nos1931",
                "Nosworthy",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010-08-14_export"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "gould1931",
                "Gould",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010-08-21_gould_export"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "wood2225",
                "Wood",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010-08-22_rhian_export"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "hitchings1846",
                "Mr and Mrs Hitchings",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2011/2011_09_04_hitchins_export"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "scannell85",
                "Scannell",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2011/2011_10_15_scannell_export"),
                PricePlan.PORTRAIT_2009);
        new AlbumMaker(
                "parrotandbean",
                "Ashley",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2011/2011_12_15_parrot_export"),
                PricePlan.PORTRAIT_2011);
        new AlbumMaker(
                "jackson2011",
                "Wass",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2011/2011_12_20_jackson_export"),
                PricePlan.PORTRAIT_2011);
        new AlbumMaker(
                "isabel2012",
                "Isabel's Christening",
                new File(
                        "C:/Documents and Settings/matt/My Documents/My Pictures/2012/2012_01_29_isabel_export"),
                PricePlan.PORTRAIT_2011);

    }
}

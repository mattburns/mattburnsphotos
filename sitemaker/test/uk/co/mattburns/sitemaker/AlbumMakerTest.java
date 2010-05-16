package uk.co.mattburns.sitemaker;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class AlbumMakerTest {

    @Test
    public void generateAlbums() throws IOException {
        new AlbumMaker(AlbumMaker.PORTFOLIO, AlbumMaker.PORTFOLIO, new File(
                "C:/Documents and Settings/matt/My Documents/My Pictures/website_portfolio"));
        new AlbumMaker("ashley2224", "Ashley", new File(
                "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010_01_31_DVD_Ashley/edited"));
        new AlbumMaker("grice", "Grice", new File(
                "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010_01_24_export_2010_01_25"));
        new AlbumMaker("grandad", "Grandad", new File(
                "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010_01_15_export"));
        new AlbumMaker("stleonards", "Tony and Claire", new File(
                "C:/Documents and Settings/matt/My Documents/My Pictures/2009/2009_12_28_DVD_tonys_wedding/edited"));
        new AlbumMaker("aidan313", "Aidan's Baptism", new File(
                "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010-03-13_export"));
        new AlbumMaker("sophie2010", "Sophie's Baptism", new File(
                "C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010-04-24_sophie_DVD/edited"));

    }
}

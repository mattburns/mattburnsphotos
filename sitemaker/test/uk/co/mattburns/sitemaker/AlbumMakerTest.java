package uk.co.mattburns.sitemaker;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class AlbumMakerTest {

	@Test
	public void generateAlbums() throws IOException {
		new AlbumMaker("ashley2224", "Ashley", new File("C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010_01_31_export_2010_02_09"));
		new AlbumMaker("grice", "Grice", new File("C:/Documents and Settings/matt/My Documents/My Pictures/2010/2010_01_24_export_2010_01_25"));
		new AlbumMaker("portfolio", "portfolio", new File("C:/Documents and Settings/matt/My Documents/My Pictures/website_portfolio"));
	}
}

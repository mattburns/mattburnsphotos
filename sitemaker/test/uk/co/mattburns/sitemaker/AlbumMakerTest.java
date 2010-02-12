package uk.co.mattburns.sitemaker;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class AlbumMakerTest {

	@Test
	public void generateAlbums() throws IOException {
		
		new AlbumMaker("ashley", new File("C:/Users/Public/Pictures/2010/2010_01_31_export_2010_02_09"));
		new AlbumMaker("grice", new File("C:/Users/Public/Pictures/2010/2010_01_24_export_2010_01_25"));
	}
}

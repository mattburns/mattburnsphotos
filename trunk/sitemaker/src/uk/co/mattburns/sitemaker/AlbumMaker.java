package uk.co.mattburns.sitemaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class AlbumMaker {

	private static final String IMAGEMAGICK = "C:\\Program Files\\ImageMagick-6.5.9-2\\";
	private static final String WATERMARK = "\"C:\\svnrepos\\mattburnsphotos\\images\\watermark.png\"";
	private static final String CONVERT = "\"" + IMAGEMAGICK + "convert\"";
	private static final String COMPOSITE = "\"" + IMAGEMAGICK + "composite\"";
	private static final File GENERATED_DIR = new File("C:/svnrepos/mattburnsphotos/generated");
	private static final File IMAGES_DIR = new File("C:/svnrepos/mattburnsphotos/images");
	private static final File JAVASCRIPT_DIR = new File("C:/svnrepos/mattburnsphotos/javascript");
	private static final File CSS_DIR = new File("C:/svnrepos/mattburnsphotos/css");
	private static final File TEMPLATE_HTML = new File("C:/svnrepos/mattburnsphotos/sitemaker/templates/index.html");

	public static void main(String[] args) throws IOException {

		String albumName = args[0];
		File photoSourceDirectory = new File(args[1]);

		if (!photoSourceDirectory.exists()
				|| !photoSourceDirectory.isDirectory()) {
			throw new RuntimeException("arg 1 must be source photo dir: "
					+ args[1]);
		}

		new AlbumMaker(albumName, photoSourceDirectory);
	}

	public AlbumMaker(String albumName, File photoSourceDirectory) throws IOException {
		GENERATED_DIR.mkdirs();
		copyDirectory(CSS_DIR, new File(GENERATED_DIR, "css"));
		copyDirectory(IMAGES_DIR, new File(GENERATED_DIR, "images"));
		copyDirectory(JAVASCRIPT_DIR, new File(GENERATED_DIR, "javascript"));

		File clientAlbumsDirectory = new File(GENERATED_DIR, "clients");

		File albumDir = new File(clientAlbumsDirectory, albumName);

		List<File> sourcePhotos = new ArrayList<File>();
		for (File photo : photoSourceDirectory.listFiles()) {
			if (photo.getName().endsWith("jpg")) {
				sourcePhotos.add(photo);
			}
		}

		makeThumbs(albumDir, sourcePhotos);
		makeMediumPhotos(albumDir, sourcePhotos);
		makeHtml(albumName, albumDir, sourcePhotos);

		System.out.println("Finished :)");
	}

	private void makeHtml(String albumName, File albumDir, List<File> sourcePhotos) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(TEMPLATE_HTML));
		FileWriter writer = new FileWriter(new File(albumDir, "index.html"));
		
		String line = reader.readLine();
		while (line != null) {
			line = line.replaceAll("albumNameTag", albumName);
			if (line.contains("photo repeat start")) {
				StringBuilder sb = new StringBuilder();
				line = reader.readLine();
				while (!line.contains("photo repeat stop")) {
					sb.append(line + "\n");
					line = reader.readLine();
				}
				line = reader.readLine();
				
				for (File photo : sourcePhotos) {
					writer.write(sb.toString().replaceAll("photoTag", photo.getName()));
				}
			}
			writer.write(line + "\n");
			line = reader.readLine();
		}
		
		File thumbsDir = new File(albumDir, "thumbs");
		thumbsDir.mkdirs();
		reader.close();
		writer.close();
		
	}
	
	private void makeThumbs(File albumDir, List<File> sourcePhotos) {
		File thumbsDir = new File(albumDir, "thumbs");
		thumbsDir.mkdirs();

		for (File photo : sourcePhotos) {
			File destinationFile = new File(thumbsDir, photo.getName());
			if (!destinationFile.exists()) {
				String command = CONVERT
						+ " \""
						+ photo.getAbsolutePath()
						+ "\" -resize 100x100^^ -gravity center -quality 75 -extent 100x100 \""
						+ destinationFile.getAbsolutePath() + "\"";
				runCommand(command);
			}
		}
	}

	private void makeMediumPhotos(File albumDir, List<File> sourcePhotos) {
		File mediumDir = new File(albumDir, "medium");
		mediumDir.mkdirs();

		for (File photo : sourcePhotos) {
			File mediumFile = new File(mediumDir, photo.getName());
			if (!mediumFile.exists()) {
				String command = CONVERT + " \"" + photo.getAbsolutePath()
						+ "\" -resize 600x600 \""
						+ mediumFile.getAbsolutePath() + "\"";
				runCommand(command);
				waterMarkPhoto(mediumFile);
			}
		}
	}

	private void waterMarkPhoto(File photo) {
		File stampedFile = new File(photo.getAbsolutePath() + ".stamped.jpg");
		String command = COMPOSITE
				+ " -gravity south -geometry +0+10 -quality 85 " + WATERMARK + " \""
				+ photo.getAbsolutePath() + "\" \""
				+ stampedFile.getAbsolutePath() + "\"";
		runCommand(command);
		String originalName = photo.getAbsolutePath();
		photo.delete();
		stampedFile.renameTo(new File(originalName));
	}

	private void runCommand(String command) {
		Process child;
		try {
			System.out.println("Running : " + command);
			child = Runtime.getRuntime().exec(command);
			child.waitFor();
		} catch (Exception e) {
			throw new RuntimeException("command failed: " + command, e);
		}
	}
	
    // If targetLocation does not exist, it will be created.
    public void copyDirectory(File sourceLocation , File targetLocation)
    throws IOException {
    	if (sourceLocation.getName().equals(".svn") ){
    		return;
    	}
    	
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            
            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {
            
            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);
            
            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }
}

package uk.co.mattburns.sitemaker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlbumMaker {

	private static final String IMAGEMAGICK = "C:\\Program Files\\ImageMagick-6.5.9-2\\";
	private static final String WATERMARK = "\"C:\\svnrepos\\mattburnsphotos\\images\\watermark.png\"";
	private static final String CONVERT = "\"" + IMAGEMAGICK + "convert\"";
	private static final String COMPOSITE = "\"" + IMAGEMAGICK + "composite\"";
	private static final File GENERATED_DIR = new File("C:/svnrepos/mattburnsphotos/generated");

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {

		String albumName = args[0];
		File photoSourceDirectory = new File(args[1]);

		if (!photoSourceDirectory.exists()
				|| !photoSourceDirectory.isDirectory()) {
			throw new RuntimeException("arg 1 must be source photo dir: "
					+ args[1]);
		}

		File clientAlbumsDirectory = new File(GENERATED_DIR, "clients");
		new AlbumMaker(albumName, photoSourceDirectory, clientAlbumsDirectory);

		System.out.println("Finished :)");
	}

	public AlbumMaker(String albumName, File photoSourceDirectory,
			File clientAlbumsDirectory) {

		File albumDir = new File(clientAlbumsDirectory, albumName);

		List<File> sourcePhotos = new ArrayList<File>();
		for (File photo : photoSourceDirectory.listFiles()) {
			if (photo.getName().endsWith("jpg")) {
				sourcePhotos.add(photo);
			}
		}

		makeThumbs(albumDir, sourcePhotos);
		makeMediumPhotos(albumDir, sourcePhotos);

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
}

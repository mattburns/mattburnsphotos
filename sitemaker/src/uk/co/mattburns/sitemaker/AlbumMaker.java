package uk.co.mattburns.sitemaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class AlbumMaker {

    public static final String PORTFOLIO = "portfolio";
    private static final String IMAGEMAGICK = "C:/Program Files/ImageMagick-6.6.0-Q16/";
    private static final String WATERMARK = "\"C:\\svnrepos\\mattburnsphotos\\images\\watermark.png\"";
    private static final String CONVERT = "\"" + IMAGEMAGICK + "convert\"";
    private static final String COMPOSITE = "\"" + IMAGEMAGICK + "composite\"";
    public static final File GENERATED_DIR = new File(
            "C:/svnrepos/mattburnsphotos/generated");
    private static final File IMAGES_DIR = new File(
            "C:/svnrepos/mattburnsphotos/images");
    private static final File JAVASCRIPT_DIR = new File(
            "C:/svnrepos/mattburnsphotos/javascript");
    private static final File CSS_DIR = new File(
            "C:/svnrepos/mattburnsphotos/css");
    private static final File TEMPLATE_HTML = new File(
            "C:/svnrepos/mattburnsphotos/sitemaker/templates/album.html");
    private static final File MOBILE_TEMPLATE_HTML = new File(
            "C:/svnrepos/mattburnsphotos/sitemaker/templates/mobile.html");
    private static final File HOME_HTML = new File(
            "C:/svnrepos/mattburnsphotos/index.html");
    private static final File CONTACT_HTML = new File(
            "C:/svnrepos/mattburnsphotos/contact.html");
    private static final File CREDITS_HTML = new File(
            "C:/svnrepos/mattburnsphotos/credits.html");
    private static final File CLIENTS_HTML = new File(
            "C:/svnrepos/mattburnsphotos/clients.html");
    private static final File PRICES_HTML = new File(
            "C:/svnrepos/mattburnsphotos/prices.html");

    public static void main(String[] args) throws IOException {

        String albumName = args[0];
        File photoSourceDirectory = new File(args[1]);

        if (!photoSourceDirectory.exists()
                || !photoSourceDirectory.isDirectory()) {
            throw new RuntimeException("arg 1 must be source photo dir: "
                    + args[1]);
        }

        new AlbumMaker(albumName, albumName, photoSourceDirectory,
                PricePlan.PORTRAIT_2009);
    }

    public AlbumMaker() {

    }

    public AlbumMaker(String albumID, String albumName,
            File photoSourceDirectory, PricePlan pricePlan) throws IOException {
        if (!photoSourceDirectory.exists()) {
            throw new IllegalArgumentException("Source directory not found: "
                    + photoSourceDirectory);
        }
        GENERATED_DIR.mkdirs();
        copyFile(CSS_DIR, new File(GENERATED_DIR, "css"));
        copyFile(IMAGES_DIR, new File(GENERATED_DIR, "images"));
        copyFile(JAVASCRIPT_DIR, new File(GENERATED_DIR, "javascript"));
        copyFile(HOME_HTML, GENERATED_DIR);
        copyFile(CONTACT_HTML, GENERATED_DIR);
        copyFile(PRICES_HTML, GENERATED_DIR);
        copyFile(CLIENTS_HTML, GENERATED_DIR);
        copyFile(CREDITS_HTML, GENERATED_DIR);

        File clientAlbumsDirectory = new File(GENERATED_DIR, "c");

        File albumDir = new File(clientAlbumsDirectory, albumID);
        albumDir.mkdirs();

        List<File> sourcePhotos = new ArrayList<File>();
        for (File photo : photoSourceDirectory.listFiles()) {
            if (photo.getName().toLowerCase().endsWith("jpg")
                    || photo.getName().toLowerCase().endsWith("jpeg")) {
                sourcePhotos.add(photo);
            }
        }

        // make album
        File destination = new File(albumDir, "index.html");
        makeHtml(albumID, albumName, albumDir, sourcePhotos, pricePlan,
                TEMPLATE_HTML, destination);

        // make mobile album
        destination = new File(albumDir, "mobile.html");
        makeHtml(albumID, albumName, albumDir, sourcePhotos, pricePlan,
                MOBILE_TEMPLATE_HTML, destination);

        makeThumbs(albumDir, sourcePhotos);
        makePhotos(albumDir, sourcePhotos, PhotoSize.medium, true, true);
        makePhotos(albumDir, sourcePhotos, PhotoSize.large, true, true);

        System.out.println("Finished " + albumName + " album :)");
    }

    private void makeHtml(String albumID, String albumName, File albumDir,
            List<File> sourcePhotos, PricePlan pricePlan, File template,
            File destination) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(template));
        FileWriter writer = new FileWriter(destination);

        String line = reader.readLine();
        while (line != null) {
            if (albumID.equals(PORTFOLIO) && line.contains("album-only start")) {
                while (!line.contains("album-only stop")) {
                    line = reader.readLine();
                }
                line = reader.readLine();
            }
            if (line.contains("price-plan")) {
                line = pricePlan.getGoogleCartHtml();
            }
            line = line.replaceAll("albumIDTag", albumID);
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
                    writer.write(sb.toString().replaceAll("photoTag",
                            photo.getName()));
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
                        + "\" -resize 50x50^^ -gravity center -strip -quality 75 -extent 50x50 \""
                        + destinationFile.getAbsolutePath() + "\"";
                runCommand(command);
            }
        }
    }

    public void makePhotos(File albumDir, List<File> sourcePhotos,
            PhotoSize size, boolean putInSubfolder, boolean addWatermark) {
        if (putInSubfolder) {
            albumDir = new File(albumDir, size.toString());
            albumDir.mkdirs();
        }

        for (File photo : sourcePhotos) {
            File file = new File(albumDir, photo.getName());
            if (!file.exists()) {
                String command = CONVERT + " \"" + photo.getAbsolutePath()
                        + "\" -resize " + size.width + "x" + size.height
                        + " \"" + file.getAbsolutePath() + "\"";
                runCommand(command);
                if (addWatermark) {
                    waterMarkPhoto(file);
                }
            }
        }
    }

    private void waterMarkPhoto(File photo) {
        File stampedFile = new File(photo.getAbsolutePath() + ".stamped.jpg");
        String command = COMPOSITE
                + " -gravity south -geometry +0+10 -strip -quality 85 "
                + WATERMARK + " \"" + photo.getAbsolutePath() + "\" \""
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
    public void copyFile(File sourceLocation, File targetLocation)
            throws IOException {
        if (sourceLocation.getName().equals(".svn")
                || sourceLocation.getName().equals("Thumbs.db")) {
            return;
        }

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i = 0; i < children.length; i++) {
                copyFile(new File(sourceLocation, children[i]), new File(
                        targetLocation, children[i]));
            }
        } else {
            if (targetLocation.isDirectory()) {
                copyFile(sourceLocation, new File(targetLocation,
                        sourceLocation.getName()));
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

    public enum PhotoSize {
        medium(600, 400), large(830, 553);
        public int width;
        public int height;

        private PhotoSize(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}

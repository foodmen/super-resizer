package mts.teta.resizer;

import static marvinplugins.MarvinPluginCollection.*;

import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;

public class ImageProcessor {

    public void processImage(BufferedImage image, ResizerApp app) throws IOException {
        if (app.resize.isResize() || app.quality != 100 || !app.format.equals("JPEG"))
            image = getResizedImage(image, app);
        MarvinImage imageIn = new MarvinImage(image);
        MarvinImage imageOut = imageIn.clone();
        if (app.crop.isCrop()) {
            crop(imageIn, imageOut, app.crop.getX(), app.crop.getY(), app.crop.getWidth(), app.crop.getHeight());
        }
        if (app.blur != 0) {
            imageIn = imageOut;
            gaussianBlur(imageIn, imageOut, app.blur);
        }
        MarvinImageIO.saveImage(imageOut, app.outputFile.getPath());
    }

    private BufferedImage getResizedImage(BufferedImage image, ResizerApp app) throws IOException {
        if (!app.resize.isResize()) {
            app.resize.setWidth(image.getWidth());
            app.resize.setHeight(image.getHeight());
        }
        return (Thumbnails.of(image)
                .size(app.resize.getWidth(), app.resize.getHeight())
                .outputQuality(app.quality / 100)
                .outputFormat(app.format.toLowerCase(Locale.ROOT))
                .asBufferedImage());
    }
}

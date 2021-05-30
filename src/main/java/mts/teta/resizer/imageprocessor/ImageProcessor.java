package mts.teta.resizer.imageprocessor;

import static marvinplugins.MarvinPluginCollection.*;

import marvin.image.MarvinImage;
import mts.teta.resizer.ResizerApp;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.builders.BufferedImageBuilder;
import net.coobird.thumbnailator.resizers.Resizers;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageProcessor {

    public void processImage(BufferedImage image, ResizerApp app) throws IOException, BadAttributesException {
        if (app.resize.isResize())
            image = getResizedImage(image, app);
        if (app.crop.isCrop())
            image = getCropImage(image, app);
        if (app.blur != 0)
            image = getBlurImage(image, app);
        saveImage(image, app);
    }

    private BufferedImage getResizedImage(BufferedImage image, ResizerApp app) throws BadAttributesException {
        if (app.resize.getHeight() < 1 || app.resize.getWidth() < 1)
            throw new BadAttributesException("Please check params!");
        BufferedImage destImage = new BufferedImageBuilder(app.resize.getWidth(), app.resize.getHeight()).build();
        Resizers.BILINEAR.resize(image, destImage);
        return destImage;
    }

    private BufferedImage getCropImage(BufferedImage image, ResizerApp app) throws BadAttributesException {
        if (app.crop.getX() < 0 || app.crop.getY() < 0 || app.crop.getHeight() < 1 || app.crop.getWidth() < 1)
            throw new BadAttributesException("Please check params!");
        MarvinImage imageIn = new MarvinImage(image);
        MarvinImage imageOut = imageIn.clone();
        crop(imageIn, imageOut, app.crop.getX(), app.crop.getY(), app.crop.getWidth(), app.crop.getHeight());
        return imageOut.getBufferedImageNoAlpha();
    }

    private BufferedImage getBlurImage(BufferedImage image, ResizerApp app) throws BadAttributesException {
        if (app.blur < 0)
            throw new BadAttributesException("Please check params!");
        MarvinImage imageIn = new MarvinImage(image);
        MarvinImage imageOut = imageIn.clone();
        gaussianBlur(imageIn, imageOut, app.blur);
        return imageOut.getBufferedImageNoAlpha();
    }

    private void saveImage(BufferedImage image, ResizerApp app) throws IOException, BadAttributesException {
        if (app.quality < 1 || app.quality > 100 || !("JPEG").equals(app.format) && !("PNG").equals(app.format))
            throw new BadAttributesException("Please check params!");
        Thumbnails.of(image)
                .size(image.getWidth(), image.getHeight())
                .outputQuality(app.quality / 100)
                .outputFormat(app.format.toLowerCase())
                .toFile(app.outputFile);
    }
}

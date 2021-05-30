package mts.teta.resizer.imageprocessor;

import static marvinplugins.MarvinPluginCollection.*;

import marvin.image.MarvinImage;
import mts.teta.resizer.ResizerApp;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.builders.BufferedImageBuilder;
import net.coobird.thumbnailator.resizers.Resizers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageProcessor {

    public void processImage(BufferedImage image, ResizerApp app) throws IOException, BadAttributesException {
        if (!app.format.equals("JPEG") && !app.format.equals("PNG"))
            throw new BadAttributesException("Please check params!");
        if (app.quality != 100)
            image = reduceQualityImage(image, app);
        if (app.resize.isResize())
            image = getResizedImage(image, app);
        if (app.crop.isCrop())
            image = getCropImage(image, app);
        if (app.blur != 0)
            image = getBlurImage(image, app);
        ImageIO.write(image, app.format, app.outputFile);
    }

    private BufferedImage reduceQualityImage(BufferedImage image, ResizerApp app) throws IOException, BadAttributesException {
        if (app.quality < 1 || app.quality > 100)
            throw new BadAttributesException("Please check params!");
        return Thumbnails.of(image)
                .outputQuality(app.quality / 100)
                .size(image.getWidth(), image.getHeight())
                .asBufferedImage();
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
}

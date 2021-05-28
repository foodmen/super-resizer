package mts.teta.resizer;

import static marvinplugins.MarvinPluginCollection.*;

import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class ImageProcessor {

    public void processImage(BufferedImage image, ResizerApp app) throws IOException {
        if (app.resize.isResize() || app.quality != 100 || app.format != "JPEG")
            image = getResizedImage(image, app);

       /* MarvinImage imageIn = new MarvinImage(image);
        MarvinImage imageOut = imageIn.clone();
        crop(imageIn, imageOut, app.crop.getX(), app.crop.getY(),
                app.crop.getWidth(), app.crop.getHeight());
        MarvinImageIO.saveImage(imageOut, app.outputFile.getPath());*/
        if (app.crop.isCrop())
            image = getCrop(image, app);
        if (app.blur != 0)
            image = getGaussianBlur(image, app);

/*        MarvinImage imageIn = new MarvinImage(image);
        MarvinImageIO.saveImage(imageIn, app.outputFile.getPath());*/
        ImageIO.write(image, "png", app.outputFile);
    }

    private BufferedImage getCrop(BufferedImage image, ResizerApp app) {
        MarvinImage imageIn = new MarvinImage(image);
        MarvinImage imageOut = imageIn.clone();
        crop(imageIn, imageOut, app.crop.getX(), app.crop.getY(),
                app.crop.getWidth(), app.crop.getHeight());
        //MarvinImageIO.saveImage(imageOut, "3.jpg");
        return imageOut.getBufferedImage();
    }

    private BufferedImage getGaussianBlur(BufferedImage image, ResizerApp app) {
        MarvinImage imageIn = new MarvinImage(image);
        MarvinImage imageOut = imageIn.clone();
        gaussianBlur(imageIn, imageOut, app.blur);
        return imageOut.getBufferedImage();
    }

    private BufferedImage getResizedImage(BufferedImage image, ResizerApp app) throws IOException {
        if (!app.resize.isResize()) {
            app.resize.setWidth(image.getWidth());
            app.resize.setHeight(image.getHeight());
        }
        BufferedImage bufferedImage = Thumbnails.of(image)
                .size(app.resize.getWidth(), app.resize.getHeight())
                .outputQuality(app.quality / 100)
                .outputFormat(app.format.toLowerCase(Locale.ROOT))
                .asBufferedImage();
        return bufferedImage;
    }
}

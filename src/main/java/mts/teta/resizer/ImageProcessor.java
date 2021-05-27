package mts.teta.resizer;

import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import net.coobird.thumbnailator.Thumbnails;
import org.marvinproject.image.blur.gaussianBlur.GaussianBlur;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;

public class ImageProcessor {

    public void processImage(BufferedImage image, ResizerApp app) {
        int width = (app.resize.width == -1 ? image.getWidth() : app.resize.width);
        int height = (app.resize.height  == -1 ? image.getHeight() : app.resize.height);

        try {
            Thumbnails.of(image)
                    .size(width, height)
                    .outputQuality(app.quality / 100)
                    .outputFormat(app.format.toLowerCase(Locale.ROOT))
                    .toFile(app.outputName);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл " + e.getMessage());
        }
    }
}

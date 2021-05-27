package mts.teta.resizer;

import marvin.MarvinDefinitions;
import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import marvin.plugin.MarvinPlugin;
import marvin.util.MarvinJarLoader;
import marvin.util.MarvinPluginLoader;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageProcessor {

    public void processImage(BufferedImage image, ResizerApp app) {
        int width = (app.resize[0] == -1 ? image.getWidth() : app.resize[0]);
        int height = (app.resize[1]  == -1 ? image.getHeight() : app.resize[1]);

        try {
            Thumbnails.of(image)
                    .size(width, height)
                    .outputQuality(app.quality / 100)
                    .outputFormat(app.format)
                    .toFile(app.outputName);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл " + e.getMessage());
        }
    }
}

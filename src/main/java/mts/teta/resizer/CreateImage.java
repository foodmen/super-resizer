package mts.teta.resizer;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Locale;

public class CreateImage {

    int height;
    int width;
    double quality;
    String format;
    BufferedImage image;
    BufferedImage output;

    public CreateImage(File inputName, int height, int width,
                       int qualityValue, String format) {
        this.quality = qualityValue;
        this.format = format.toLowerCase(Locale.ROOT);
        try {
            image = ImageIO.read(inputName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.width = (width == -1 ? image.getWidth() : width);
        this.height = (height == -1 ? image.getHeight() : height);
        //System.out.println(this.width + ". " + this.height);
    }

    public BufferedImage resize (String outputName) {
        try {
            Thumbnails.of(image)
                    .size(width, height)
                    .outputQuality(quality / 100)
                    .outputFormat(format)
                    .toFile(outputName);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл " + e.getMessage());
        }
        return output;
    }


/*
    MarvinImage image = MarvinImageIO.loadImage(“test.jpg”);
    MarvinImagePlugin plugin = MarvinPluginLoader.loadPluginImage("net.sourceforge.marvinproject.color.grayScale.jar");
    plugin.process(a_image, a_image, a_null, MarvinImageMask.NULL_MASK, false);

    private MarvinImagePlugin imagePlugin;
    private MarvinImage image,
            backupImage;

    public void actionPerformed(ActionEvent e){
        image = backupImage.clone();
            imagePlugin = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.color.grayScale.jar");
            imagePlugin.process(image, image);
    }*/
}

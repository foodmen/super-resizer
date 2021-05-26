package mts.teta.resizer;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class CreateImage {

    String inputName;
    String outputName;

    public CreateImage(String inputName, String outputName) {
        this.inputName = inputName;
        this.outputName = outputName;
    }

    public boolean resize (int wight, int height) {
        try {
            Thumbnails.of(inputName)
                    .size(wight, height)
                    .toFile(outputName);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл" + e.getMessage());
        }
        return true;
    }

    public boolean changeQuality (double quality) {
        try {
            Thumbnails.of(inputName)
                    .outputQuality(quality / 100)
                    .toFile(outputName);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл" + e.getMessage());
        }
        return true;
    }

    public boolean outputFormat (String format) {
        try {
            Thumbnails.of(inputName)
                    .outputFormat(format)
                    .toFile(outputName);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл" + e.getMessage());
        }
        return true;
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

package mts.teta.resizer;

import mts.teta.resizer.imageprocessor.BadAttributesException;
import picocli.CommandLine;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ResizerApp extends ConsoleAttributes implements Callable<Integer> {

    public static void main(String... args) {
        int exitCode = runConsole(args);
        System.exit(exitCode);
    }

    protected static int runConsole(String[] args) {
        return new CommandLine(new ResizerApp()).execute(args);
    }

    @Override
    public Integer call() throws BadAttributesException, IOException {
        ImageProcessor imageProcessor = new ImageProcessor();
        parametersValidator();
        imageProcessor.processImage(ImageIO.read(inputFile), this);
        return 0;
    }

    private void parametersValidator() throws BadAttributesException {
        if (resize.isResize() && (resize.getHeight() < 1 || resize.getWidth() < 1))
            throw new BadAttributesException("Please check params!");
        if (quality < 1 || quality > 100)
            throw new BadAttributesException("Please check params!");
        if (crop.isCrop() && (crop.getX() < 0 || crop.getY() < 0 ||
            crop.getHeight() < 1 || crop.getWidth() < 1))
            throw new BadAttributesException("Please check params!");
        if (blur < 0)
            throw new BadAttributesException("Please check params!");
        if (!format.equals("JPEG") && !format.equals("PNG"))
            throw new BadAttributesException("Please check params!");
    }

    public void setInputFile(File file) {
        inputFile = file;
    }

    public void setOutputFile(File file) {
        outputFile= file;
    }

    public void setResizeWidth(Integer reducedPreviewWidth) {
        resize.setWidth(reducedPreviewWidth);
    }

    public void setResizeHeight(Integer reducedPreviewHeight) {
        resize.setHeight(reducedPreviewHeight);
    }

    public void setQuality(int qualityImage) {
        quality = qualityImage;
    }
}

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
        parametrsValidator();
        imageProcessor.processImage(ImageIO.read(inputFile), this);
        return 0;
    }

    private void parametrsValidator() throws BadAttributesException {
        if (quality < 0 || quality > 100)
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

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


/*        System.out.println("reszie - " + resize.width + ", " + resize.height);
        System.out.println("crop - " + crop.width + ", " + crop.height  + ", " + crop.x + ", " + crop.y);
        System.out.println("format - " + format);
        System.out.println("qualityValue - " + quality);
        System.out.println("blurRadius - " + blur);
        System.out.println("inputName - " + inputFile);
        System.out.println("outputName - " + outputName);*/

        ImageProcessor imageProcessor = new ImageProcessor();
        parametrsValidator();
        imageProcessor.processImage(ImageIO.read(inputFile), this);
        //imageProcessor.croping(ImageIO.read(inputFile), this);
        //System.out.println(resize.isResize);
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

/*@CommandLine.Command(name = "resizer", mixinStandardHelpOptions = true, version = "resizer 0.0.1", description = "...")
public class ResizerApp implements Callable<Integer> {
    public static void main(String... args) {

        int exitCode = runConsole(args);
        System.exit(exitCode);
    }

    protected static int runConsole(String[] args) {
        return new CommandLine(new ResizerApp()).execute(args);
    }

    @Override
    public Integer call() throws Exception {
        ImageProc
        return null;
    }
}*/

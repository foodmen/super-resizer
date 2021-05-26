package mts.teta.resizer;

import picocli.CommandLine;
import picocli.CommandLine.Command;

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
    public Integer call() {
        CreateImage image = new CreateImage(inputName, outputName);
        /*if (resize[0] != 0 && resize[1] != 0) {
            image.resize(resize[0], resize[1]);
        }
        else if (qualityValue != 0) {
            image.changeQuality(qualityValue);
        }
        else if (!format.isEmpty()) {
            image.outputFormat(format);
        }*/
        System.out.println("reszie - " + resize[0] + ", " + resize[1]);
        System.out.println("crop - " + crop[0] + ", " + crop[1]  + ", " +  crop[2] + ", " + crop[3]);
        System.out.println("format - " + format);
        System.out.println("qualityValue - " + qualityValue);
        System.out.println("blurRadius - " + blurRadius);
        System.out.println("inputName - " + inputName);
        System.out.println("outputName - " + outputName);
        /*ImageProcessor imageProcessor = new ImageProcessor();
        imageProcessor.processImage(ImageIO.read(inputFile), this);*/
        return 0;
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

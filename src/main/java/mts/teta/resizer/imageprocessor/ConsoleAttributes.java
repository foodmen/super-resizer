package mts.teta.resizer.imageprocessor;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

@CommandLine.Command(name = "convert", version = "convert 0.0.1" +
        "https://gitlab.com/link/", description = "Options Settings:", sortOptions = false,
        header = "Version: convert 0.0.1 https://github.com/foodmen/super-resizer\n" +
        "Available formats: jpeg png", separator = " ", usageHelpWidth = 100, customSynopsis = {
        "convert input-file [options ...] output-file"
}
)
public
class ConsoleAttributes{

    @Option(names = "-h", usageHelp = true, hidden = true)
    boolean help;

    @Parameters(index = "0", description = "The input image", paramLabel = "input-file", hidden = true)
    public File inputFile;

    @Parameters(index = "1", description = "The output image", paramLabel = "output-file", hidden = true)
    public File outputFile;

    @Option(names = "--resize", description = "resize the image",
            paramLabel = "width height", parameterConsumer = Resize.class)
    public Resize resize = new Resize();

    @Option(names = "--quality", description = "JPEG/PNG compression level", paramLabel = "value")
    public double quality = 100;

    @Option(names = "--crop", paramLabel = "width height x y",
            description = "\u001B[31mcut\u001B[0m out one rectangular area of the image", parameterConsumer = Crop.class)
    public Crop crop = new Crop();

    @Option(names = "--blur", description = "reduce image noise detail levels", paramLabel = "{radius}")
    public int blur = 0;

    @Option(names = "--format", description = "the image \u001B[31mformat type\u001B[0m",
            paramLabel = "\"outputFormat\"")
    public String format = "JPEG";
}

// 1.jpg --resize 500 500 --quality 10 --format "PNG" --blur 20 --crop 10 20 300 400 2.jpg
package mts.teta.resizer;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@CommandLine.Command(name = "convert", version = "convert 0.0.1" +
        "https://gitlab.com/link/", description = "Options Settings:", sortOptions = false,
        abbreviateSynopsis = true, header = "Version: resizer 0.0.1 https://github.com/foodmen/super-resizer\n" +
        "Available formats: jpeg png", separator = " ", usageHelpWidth = 100
)
class ConsoleAttributes{

    @Option(names = "-h", usageHelp = true, hidden = true)
    boolean help;

    @Parameters(index = "0", description = "The input image", paramLabel = "input-file")
    String inputName;

    @Parameters(index = "1", description = "The output image", paramLabel = "output-file")
    String outputName;

    @Option(names = "--resize", arity = "2", description = "resize the image")
    int[] resize = {0, 0};

    @Option(names = "--quality", description = "JPEG/PNG compression level", paramLabel = "value")
    int qualityValue = 0;

    @Option(names = "--crop", paramLabel = "width height x y", arity = "4",
            description = "\u001B[31mcut\u001B[0m out one rectangular area of the image")
    int[] crop = {0, 0, 0, 0};

    @Option(names = "--blur", description = "reduce image noise detail levels", paramLabel = "{radius}")
    int blurRadius = 0;

    @Option(names = "--format", description = "the image \u001B[31mformat type\u001B[0m",
            paramLabel = "\"outputFormat\"")
    String format = "";


}
// 1.jpg --resize 10 20 --quality 30 --format PNG --blur 2 --crop 1 2 3 4 2.jpg
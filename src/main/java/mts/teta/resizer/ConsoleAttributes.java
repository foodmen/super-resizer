package mts.teta.resizer;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

class ConsoleAttributes{

    @Parameters(index = "0", description = "The input image")
    String inputName;

    @Parameters(index = "1", description = "The output image")
    String outputName;

    @Option(names = { "--blur" }, description = "reduce image noise detail levels")
    int blurRadius = 0;

    @Option(names = { "--quality" }, description = "JPEG/PNG compression level")
    int qualityValue = 0;

    @Option(names = { "--format" }, description = "the image format type")
    String format = "";

    @Option(names = { "--resize" }, arity = "2", description = "resize the image")
    int[] resize = {0, 0};

    @Option(names = { "--crop" }, arity = "4", description = "cut out one rectangular area of the image")
    int[] crop = {0, 0, 0, 0};

}

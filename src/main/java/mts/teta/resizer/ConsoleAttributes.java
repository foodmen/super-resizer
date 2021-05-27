package mts.teta.resizer;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.awt.*;
import java.io.File;
import java.util.Stack;

@CommandLine.Command(name = "convert", version = "convert 0.0.1" +
        "https://gitlab.com/link/", description = "Options Settings:", sortOptions = false,
        header = "Version: convert 0.0.1 https://github.com/foodmen/super-resizer\n" +
        "Available formats: jpeg png", separator = " ", usageHelpWidth = 100, customSynopsis = {
        "convert input-file [options ...] output-file"
}
)
class ConsoleAttributes{

    @Option(names = "-h", usageHelp = true, hidden = true)
    boolean help;

    @Parameters(index = "0", description = "The input image", paramLabel = "input-file", hidden = true)
    File inputFile;

    @Parameters(index = "1", description = "The output image", paramLabel = "output-file", hidden = true)
    String outputName;

    @Option(names = "--resize", arity = "1..*", description = "resize the image", paramLabel = "width height")
    int[] resize = {-1, -1};

    @Option(names = "--quality", description = "JPEG/PNG compression level", paramLabel = "value")
    double quality = 100;

    @Option(names = "--crop", paramLabel = "width height x y", arity = "1..*",
            description = "\u001B[31mcut\u001B[0m out one rectangular area of the image")
    int[] crop = {0, 0, 0, 0};

    @Option(names = "--blur", description = "reduce image noise detail levels", paramLabel = "{radius}")
    int blurRadius = 0;

    @Option(names = "--format", description = "the image \u001B[31mformat type\u001B[0m",
            paramLabel = "\"outputFormat\"")
    String format = "JPEG";
}

/*@CommandLine.Command(name = "set-position")
class SetPositionCommand {
    @Parameters(parameterConsumer = PointConverter.class)
    private Point position;

    static class PointConverter implements CommandLine.IParameterConsumer {
        public void consumeParameters(Stack<String> args,
                                      CommandLine.Model.ArgSpec argSpec,
                                      CommandLine.Model.CommandSpec commandSpec) {
            if (args.size() < 2) {
                throw new CommandLine.ParameterException(commandSpec.commandLine(),
                        "Missing coordinates for Point. Please specify 2 coordinates.");
            }
            int x = Integer.parseInt(args.pop());
            int y = Integer.parseInt(args.pop());
            argSpec.setValue(new Point(x, y));
        }
    }
}*/

// 1.jpg --resize 100 200 --quality 10 --format PNG --blur 2 --crop 1 2 3 4 2.jpg
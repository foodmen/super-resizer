package mts.teta.resizer;

import picocli.CommandLine;

import java.util.Stack;

public class Crop implements CommandLine.IParameterConsumer {

    int width;
    int height;
    int x;
    int y;

    public Crop() {
    }

    public Crop(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    @Override
    public void consumeParameters(Stack<String> args,
                                  CommandLine.Model.ArgSpec argSpec,
                                  CommandLine.Model.CommandSpec commandSpec) {
        if (args.size() < 2 ) {
            throw new CommandLine.ParameterException(commandSpec.commandLine(),
                    "Missing coordinates for Point. Please specify 2 coordinates."); //todo
        }
        int width = Integer.parseInt(args.pop());
        int height = Integer.parseInt(args.pop());
        int x = Integer.parseInt(args.pop());
        int y = Integer.parseInt(args.pop());
        argSpec.setValue(new Crop(width, height, x, y));
    }
}

package mts.teta.resizer.imageprocessor;

import picocli.CommandLine;

import java.util.Stack;

public class Resize implements CommandLine.IParameterConsumer {

    private int width;
    private int height;

    public boolean isResize() {
        return isResize;
    }

    private boolean isResize;

    public Resize() {
    }

    public Resize(int width, int height) {
        this.width = width;
        this.height = height;
        isResize = true;
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
        argSpec.setValue(new Resize(width, height));
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        isResize = true;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        isResize = true;
        this.height = height;
    }
}

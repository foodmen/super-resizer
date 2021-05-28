package mts.teta.resizer;

import picocli.CommandLine;

import java.util.Stack;

public class Crop implements CommandLine.IParameterConsumer {

    public int width;
    public int height;
    public int x;
    public int y;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public boolean isCrop() {
        return isCrop;
    }

    public void setY(int y) {
        this.y = y;
    }

    private boolean isCrop;

    public Crop() {
    }

    public Crop(int width, int height, int x, int y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        isCrop = true;
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
        argSpec.setValue(new Crop(x, y, width, height));
    }
}

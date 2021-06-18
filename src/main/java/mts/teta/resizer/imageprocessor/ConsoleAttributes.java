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
    private boolean help;

    @Parameters(index = "0", description = "The input image", paramLabel = "input-file", hidden = true)
    protected File inputFile;

    @Parameters(index = "1", description = "The output image", paramLabel = "output-file", hidden = true)
    protected File outputFile;

    @Option(names = "--resize", description = "resize the image",
            paramLabel = "width height", parameterConsumer = Resize.class)
    protected Resize resize = new Resize();

    @Option(names = "--quality", description = "JPEG/PNG compression level", paramLabel = "value")
    protected double quality = 100;

    @Option(names = "--crop", paramLabel = "width height x y",
            description = "\u001B[31mcut\u001B[0m out one rectangular area of the image", parameterConsumer = Crop.class)
    protected Crop crop = new Crop();

    @Option(names = "--blur", description = "reduce image noise detail levels", paramLabel = "{radius}")
    protected int blur = 0;

    @Option(names = "--format", description = "the image \u001B[31mformat type\u001B[0m",
            paramLabel = "\"outputFormat\"")
    protected String format = "JPEG";

    public void setInputFile(File file) {
        inputFile = file;
    }

    public void setOutputFile(File file) {
        outputFile = file;
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

    public void setBlurRadius(Integer book_cover_blur_radius) {
        blur = book_cover_blur_radius;
    }
}
import java.awt.image.BufferedImage;

public class Sprite
{
    protected int width, height;
    protected int[] pixels;



    public Sprite(BufferedImage image)
    {
        width = image.getWidth();
        height = image.getHeight();

        pixels = new int[width*height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    public Sprite() {}


    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int[] getPixels()
    {
        return pixels;
    }
}
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class RenderHandler
{
    private BufferedImage view;
    private int[] pixels;

    RenderHandler(int width, int height)
    {
        //Create a BufferedImage that will represent our view.
        view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


        //Create an array for pixels
        pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();

    }

    //Render our array of pixels to the screen
    void render(Graphics graphics)
    {
        graphics.drawImage(view, 0, 0, view.getWidth(), view.getHeight(), null);
    }

    //Render our image to our array of pixels.
    public void renderImage(BufferedImage image, int xPosition, int yPosition, int xZoom, int yZoom, boolean fixed)
    {
        int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        renderArray(imagePixels, image.getWidth(), image.getHeight(), xPosition, yPosition, xZoom, yZoom, fixed);
    }

    void renderSprite(Sprite sprite, int xPosition, int yPosition, int xZoom, int yZoom, boolean fixed) {
        renderArray(sprite.getPixels(), sprite.getWidth(), sprite.getHeight(), xPosition, yPosition, xZoom, yZoom, fixed);
    }


    private void renderArray(int[] renderPixels, int renderWidth, int renderHeight, int xPosition, int yPosition, int xZoom, int yZoom, boolean fixed)
    {
        for(int y = 0; y < renderHeight; y++)
            for(int x = 0; x < renderWidth; x++)
                for(int yZoomPosition = 0; yZoomPosition < yZoom; yZoomPosition++)
                    for(int xZoomPosition = 0; xZoomPosition < xZoom; xZoomPosition++)
                        setPixel(renderPixels[x + y * renderWidth], (x * xZoom) + xPosition + xZoomPosition, ((y * yZoom) + yPosition + yZoomPosition), fixed);
    }

    private void setPixel(int pixel, int x, int y, boolean fixed)
    {
        int pixelIndex = 0;


        if(x >= 0 && y >= 0)
            pixelIndex = x + y * view.getWidth();


        if(pixels.length > pixelIndex && pixel != DiningPhilosophersGUI.alpha)
            pixels[pixelIndex] = pixel;
    }


    void clear()
    {
        for(int i = 0; i < pixels.length; i++)
            pixels[i] = 0;
    }

}
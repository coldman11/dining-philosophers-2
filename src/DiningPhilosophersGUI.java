import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.lang.Runnable;
import java.lang.Thread;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;


public class DiningPhilosophersGUI extends JFrame implements Runnable
{

    static int alpha = 0xFFFF00DC;
    private Canvas canvas = new Canvas();
    private RenderHandler renderer;
    private ArrayList<TransactionData> actions = new ArrayList<>();
    private int counterActions = 0;

    private static int philosopherCount = 0;
    private static int forkCount = 0;
    private static int knifeCount = 0;
    private static int[] philosophersThink = {0,0,0,0,0};
    private static int[] philosopherForks = {0,0,0,0,0};
    private static int[] philosopherKnives = {0,0,0,0,0};



    private BufferedImage[] images = {
            loadImage("customer.png"),
            loadImage("fork.png"),
            loadImage("knife.png"),
            loadImage("lightbulb.png"),
            loadImage("black.png")};

    private Sprite[] sprites = {
            new Sprite(images[0]),
            new Sprite(images[1]),
            new Sprite(images[2]),
            new Sprite(images[3]),
            new Sprite(images[4])};





    private DiningPhilosophersGUI()
    {
        //Make our program shutdown when we exit out.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set the position and size of our frame.
        setBounds(0,0, 1000, 800);

        //Put our frame in the center of the screen.
        setLocationRelativeTo(null);

        //Add our graphics component
        add(canvas);

        //Make our frame visible.
        setVisible(true);

        //Create our object for buffer strategy.
        canvas.createBufferStrategy(3);

        renderer = new RenderHandler(getWidth(), getHeight());




    }





    private BufferedImage loadImage(String path)
    {
        try
        {
            BufferedImage loadedImage = ImageIO.read(DiningPhilosophersGUI.class.getResource(path));
            BufferedImage formattedImage = new BufferedImage(loadedImage.getWidth(), loadedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            formattedImage.getGraphics().drawImage(loadedImage, 0, 0, null);

            return formattedImage;
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
            return null;
        }
    }




    private void render()
    {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();
        super.paint(graphics);




        //rendering starting graphics
        for(int i = 0; i < philosopherCount; i++)
            renderer.renderSprite(sprites[0],200+100*i, 400, 5, 5, true);

        for(int i = 0; i < forkCount; i++)
            renderer.renderSprite(sprites[1], 300+32*i, 300, 2,2, true);


        for(int i = 0; i < knifeCount; i++)
            renderer.renderSprite(sprites[2], 300+32*i, 200, 2, 2, true);




        //rendering forks for philosophers
        for(int i = 0; i < philosopherForks[0]; i++)
            renderer.renderSprite(sprites[1], 200,368,2,2,true);

        for(int i = 0; i < philosopherForks[1]; i++)
            renderer.renderSprite(sprites[1], 300,368,2,2,true);

        for(int i = 0; i < philosopherForks[2]; i++)
            renderer.renderSprite(sprites[1], 400,368,2,2,true);

        for(int i = 0; i < philosopherForks[3]; i++)
            renderer.renderSprite(sprites[1], 500,368,2,2,true);

        for(int i = 0; i < philosopherForks[4]; i++)
            renderer.renderSprite(sprites[1], 600,368,2,2,true);




        //rendering knives for philosophers
        for(int i = 0; i < philosopherKnives[0]; i++)
            renderer.renderSprite(sprites[2], 200 + 32,368,2,2,true);

        for(int i = 0; i < philosopherKnives[1]; i++)
            renderer.renderSprite(sprites[2], 300 + 32,368,2,2,true);

        for(int i = 0; i < philosopherKnives[2]; i++)
            renderer.renderSprite(sprites[2], 400 + 32,368,2,2,true);

        for(int i = 0; i < philosopherKnives[3]; i++)
            renderer.renderSprite(sprites[2], 500 + 32,368,2,2,true);

        for(int i = 0; i < philosopherKnives[4]; i++)
            renderer.renderSprite(sprites[2], 600 + 32,368,2,2,true);


        //rendering thinking sprites
        for(int i = 0; i < philosophersThink[0]; i++)
            renderer.renderSprite(sprites[3], 200,368,2,2,true);

        for(int i = 0; i < philosophersThink[1]; i++)
            renderer.renderSprite(sprites[3], 300,368,2,2,true);

        for(int i = 0; i < philosophersThink[2]; i++)
            renderer.renderSprite(sprites[3], 400,368,2,2,true);

        for(int i = 0; i < philosophersThink[3]; i++)
            renderer.renderSprite(sprites[3], 500,368,2,2,true);

        for(int i = 0; i < philosophersThink[4]; i++)
            renderer.renderSprite(sprites[3], 600 ,368,2,2,true);




        renderer.render(graphics);
        graphics.dispose();
        bufferStrategy.show();
        renderer.clear();
    }


    public void run()
    {
        long lastTime = System.nanoTime(); //long 2^63
        double nanoSecondConversion = 1000000000.0; //60 frames per second
        double changeInSeconds = 0;

        while(true)
        {
            long now = System.nanoTime();
            changeInSeconds += (now - lastTime) / nanoSecondConversion;
            while(changeInSeconds >= 1) {
                update();
                changeInSeconds--;

            }

            render();


            lastTime = now;
        }

    }

    private void update()
    {
        if(!DiningPhilosophers.transactionData.isEmpty() && counterActions < DiningPhilosophers.transactionData.size())
        {

            for(int i = counterActions; i < counterActions + 1; i++)
            {
                actions.add(DiningPhilosophers.transactionData.get(i));
                TransactionData action = DiningPhilosophers.transactionData.get(i);


                if(action.getAction().equals("PickingFork"))
                {
                    forkCount--;
                    philosopherForks[action.getCounter()]++;
                    philosophersThink[action.getCounter()]--;
                    System.out.println("Philosopher " + action.getCounter() + " is picking up fork " + action.getPickCounter());

                }
                else if(action.getAction().equals("PickingKnife"))
                {
                    knifeCount--;
                    philosopherKnives[action.getCounter()]++;
                    System.out.println("Philosopher " + action.getCounter() + " is picking up knife " + action.getPickCounter());
                }
                else if(action.getAction().equals("PuttingFork"))
                {
                    forkCount++;
                    philosopherForks[action.getCounter()]--;
                    System.out.println("Philosopher " + action.getCounter() + " is putting down fork " + action.getPickCounter());
                }
                else if(action.getAction().equals("PuttingKnife"))
                {
                    knifeCount++;
                    philosopherKnives[action.getCounter()]--;
                    System.out.println("Philosopher " + action.getCounter() + " is putting down knife " + action.getPickCounter());
                }
                else if(action.getAction().equals("Thinking"))
                {
                    philosophersThink[action.getCounter()]++;
                    System.out.println("Philosopher " + action.getCounter() + " is thinking");
                }
                else if(action.getAction().equals("Eating"))
                {
                    System.out.println("Philosopher " + action.getCounter() + " is eating");
                }




            }

            counterActions++;

        }

    }



        public static void main(String[] args) {
            DiningPhilosophersGUI producerConsumerGUI = new DiningPhilosophersGUI();
            Thread producerConsumerGUIThread = new Thread(producerConsumerGUI);
            DiningPhilosophers diningPhilosophers = new DiningPhilosophers(
                    5,
                    5,
                    3
            );

            philosopherCount = diningPhilosophers.getPHILOSOPHER_COUNT();
            forkCount = diningPhilosophers.getFORK_COUNT();
            knifeCount = diningPhilosophers.getKNIFE_COUNT();


            diningPhilosophers.execute();
            producerConsumerGUIThread.start();

        }
    }


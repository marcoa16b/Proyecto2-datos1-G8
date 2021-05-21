package B_Cliente.main;

import B_Cliente.game_handler.Handler;
import B_Cliente.objects.ObjectId;
import B_Cliente.objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static boolean run = false;
    private Thread thread;

    static Handler handler;

    public void init(){
        handler = new Handler();
        handler.addObject(new Player(400, 500, ObjectId.player, this));
        //handler.addObject(new Bullet(2, 2, ObjectId.bullet));
    }

    public static Handler getHandler(){
        return handler;
    }

    public synchronized void start(){
        if(run)
            return;
        run = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountofTicks = 60.0;
        double ns = 1000000000 / amountofTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (run){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            try{
                thread.sleep(30); // control de los fps del juego.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //fps = frames;
                //ticks = updates;
                frames = 0;
                updates = 0;
            }
        }
    }

    private  void tick(){
        handler.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ///////////////////////////////////

        // draw here
        g.setColor(Color.BLACK);
        g.fillRect(0,0, getWidth(), getHeight());

        handler.render(g);

        ///////////////////////////////////
        g.dispose();
        bs.show();


    }
}

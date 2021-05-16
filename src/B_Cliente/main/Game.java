package B_Cliente.main;

import B_Cliente.objects.Hadler;
import B_Cliente.objects.ObjectId;
import B_Cliente.objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Game extends Canvas implements Runnable {

    private static boolean run = false;
    private Thread thread;

    Hadler handler;

    public void init(){
        handler = new Hadler();
        handler.addObject(new Player(400, 500, ObjectId.player, this));
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

    public static void main(String[] args) {
        new Window(800,600, "Space Invaders", new Game());
        final String HOST = "127.0.0.1";
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;

        try {
            Socket sc = new Socket(HOST, PUERTO);

            while (run) {
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                out.writeUTF("Hola servidor ");
                String mensaje = in.readUTF();

                System.out.println(mensaje);
            }
            sc.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

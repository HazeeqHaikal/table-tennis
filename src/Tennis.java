//import java.applet.Applet;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Tennis extends JPanel implements Runnable, KeyListener {
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    HumanPaddle p1;
    AIPaddle p2;
    Ball b1;
    Boolean gameStarted;
    Graphics gfx;
    Image img;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tennis");
        Tennis tennis = new Tennis();
        tennis.init();

        frame.add(tennis);
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addKeyListener(tennis);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void init(){
        this.resize(WIDTH, HEIGHT);
        gameStarted = false;
        this.addKeyListener(this);
        p1 = new HumanPaddle(1);
        b1 = new Ball();
        p2 = new AIPaddle(2, b1);
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        gfx = img.getGraphics();
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g){
        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, WIDTH, HEIGHT);
        if(b1.getX() < -10 || b1.getX() > 710) {
            gfx.setColor(Color.red);
            gfx.drawString("Game Over", 350, 250);
        }else {
            p1.draw(gfx);
            p2.draw(gfx);
            b1.draw(gfx);
        }
        if(!gameStarted) {
            gfx.setColor(Color.white);
            gfx.setFont(new Font("Arial", Font.BOLD, 20));
            gfx.drawString("Tennis", 300, 100);
            gfx.drawString("Press Enter to Begin...", 240, 130);
        }
        g.drawImage(img, 0, 0, this);
    }

    public void update(Graphics g){
        paint(g);
    }

    public void run(){
        for(;;) {
            if(gameStarted) {
                p1.move();
                p2.move();
                b1.move();
                b1.checkPaddleCollision(p1, p2);
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(true);
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setDownAccel(true);
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            p1.setUpAccel(true);
        }   else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            p1.setDownAccel(true);
        }else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameStarted = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(false);
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setDownAccel(false);
        }

    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}

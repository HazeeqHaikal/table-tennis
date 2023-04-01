import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Tennis extends Applet implements Runnable, KeyListener {
    public static void main(String[] args) {
        Frame f = new Frame("Tennis");
        Tennis t = new Tennis();
        t.init();
        f.add(t);
        f.setSize(700, 500);
        f.setVisible(true);
        f.setResizable(false);
//        auto focus on the applet
        f.addKeyListener(t);
//        launch in middle of screen
        f.setLocationRelativeTo(null);

        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

    }
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    HumanPaddle p1;
    Ball b1;
    public void init(){
        this.resize(WIDTH, HEIGHT);
        this.addKeyListener(this);
        p1 = new HumanPaddle(1);
        b1 = new Ball();
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g){
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.white);
        if(b1.getX() < -10 || b1.getX() > 710) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 30));

            g.drawString("Game Over", WIDTH / 2, HEIGHT / 2);
        }else {
            p1.draw(g);
            b1.draw(g);
        }
    }

    public void update(Graphics g){
        paint(g);
    }

    public void run(){
        for(;;){

            p1.move();
            b1.move();
            b1.checkPaddleCollision(p1, p1);
            repaint();
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            repaint();
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
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(false);
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setDownAccel(false);
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            System.out.println("LEFT");
        }   else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            System.out.println("RIGHT");
        }

    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}

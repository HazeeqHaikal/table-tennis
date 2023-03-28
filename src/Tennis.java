import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener {
    public static void main(String[] args) {
        Frame f = new Frame("Tennis");
        Tennis t = new Tennis();
        t.init();
        f.add(t);
        f.setSize(700, 500);
        f.setVisible(true);
    }
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    public void init(){
        this.resize(WIDTH, HEIGHT);
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.white);
        g.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
        g.drawOval(WIDTH/2-150, HEIGHT/2-150, 300, 300);
        g.drawOval(WIDTH/2-100, HEIGHT/2-100, 200, 200);
        g.drawOval(WIDTH/2-50, HEIGHT/2-50, 100, 100);
    }

    public void update(Graphics g){
        paint(g);
    }

    public void run(){
        for(;;){
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            repaint();
        }
    }

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP");
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("DOWN");
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("LEFT");
        }   else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("RIGHT");
        }

    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}

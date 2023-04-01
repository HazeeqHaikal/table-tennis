import java.awt.*;

// use inheritance to extend the Paddle class
public interface Paddle {
    public void draw(Graphics g);
    public void move();
    public int getY();
}

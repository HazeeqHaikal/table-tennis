import java.awt.*;

public class AIPaddle implements Paddle {
    double y, yVel;
    int player, x;
    Ball b1;

    public AIPaddle(int player, Ball b) {
        y = 210; yVel = 0;
        b1 = b;
        if(player == 1)
            x = 20;
        else
            x = 660;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, (int)y, 20, 80);
    }

    public void move() {
        y = b1.getY() - 40;

        if(y < 0)
            y = 0;
        if(y > 380)
            y = 380;
    }

    public int getY() {
        return (int)y;
    }
}
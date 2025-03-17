import java.awt.*;

public class Cowboy {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 50;
    
    private int x, y;
    private Color color;
    private boolean alive;

    public Cowboy(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.alive = true;
    }

    public void draw(Graphics g) {
        if (alive) {
            g.setColor(color);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    // G & S
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) { 
        this.y = y;
    }
    public boolean isAlive(){ 
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
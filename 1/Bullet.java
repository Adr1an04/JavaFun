import java.awt.*;

public class Bullet {
    private int x, y;
    private int size = 10;
    private int speed;
    private boolean active;
    private int direction;

    public Bullet(int speed, int direction) {
        this.speed = speed;
        this.direction = direction;
        this.active = false;
    }

    public void fire(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.active = true;
    }

    public void move() {
        if (active) {
            x += speed * direction;
        }
    }

    public void draw(Graphics g) {
        if (active) {
            g.setColor(Color.BLACK);
            g.fillOval(x, y, size, size);
        }
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active){
        this.active = active;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
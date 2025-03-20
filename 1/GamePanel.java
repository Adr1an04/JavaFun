import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    
    private Cowboy leftCowboy, rightCowboy;
    private Bullet leftBullet, rightBullet;
    
    private int bulletSpeed = 5;
    private int scoreLeft = 0, scoreRight = 0;
    private int currentRound = 1, totalRounds;
    private boolean roundOver = false;
    private Timer timer;
    private int aiCounter = 0;
    
    public GamePanel(int totalRounds) {
        this.totalRounds = totalRounds;
        leftCowboy = new Cowboy(50, 200, Color.BLUE);
        rightCowboy = new Cowboy(300, 200, Color.RED);
        leftBullet = new Bullet(bulletSpeed, 1);
        rightBullet = new Bullet(bulletSpeed, -1);
        
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(30, this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        leftCowboy.draw(g);
        rightCowboy.draw(g);
        leftBullet.draw(g);
        rightBullet.draw(g);
  
        g.setColor(Color.BLUE);
        g.drawString("Left Score: " + scoreLeft, 10, 20);
        g.setColor(Color.RED);
        g.drawString("Right Score: " + scoreRight, 280, 20);
        g.setColor(Color.BLACK);
        g.drawString("Round: " + currentRound + " / " + totalRounds, 160, 20);
        
        // indicator
        g.setColor((leftCowboy.isAlive() && !leftBullet.isActive()) ? Color.GREEN : Color.RED);
        g.fillOval(10, 40, 15, 15);
        g.setColor((rightCowboy.isAlive() && !rightBullet.isActive()) ? Color.GREEN : Color.RED);
        g.fillOval(360, 40, 15, 15);
        
        // controls
        g.setColor(Color.BLACK);
        g.drawString("Controls: A = shoot (Left), R = reset (Left), T = reset (Right)", 10, 380);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!roundOver) {
            if (leftBullet.isActive()) {
                leftBullet.move();
                checkCollision();
            }
            if (rightBullet.isActive()) {
                rightBullet.move();
                checkCollision();
            }
            // Simple AI for right cowboy
            if (rightCowboy.isAlive() && !rightBullet.isActive()) {
                aiCounter++;
                if (aiCounter > 100) {
                    shootRight();
                    aiCounter = 0;
                }
            }
        }
        repaint();
    }
    
    private void checkCollision() {
        if (leftBullet.isActive() && rightCowboy.isAlive() &&
            leftBullet.getX() + 10 >= rightCowboy.getX() &&
            leftBullet.getY() >= rightCowboy.getY() &&
            leftBullet.getY() <= rightCowboy.getY() + Cowboy.HEIGHT) {
            scoreLeft++;
            endRound("Blue");
        }
        if (rightBullet.isActive() && leftCowboy.isAlive() &&
            rightBullet.getX() <= leftCowboy.getX() + Cowboy.WIDTH &&
            rightBullet.getY() >= leftCowboy.getY() &&
            rightBullet.getY() <= leftCowboy.getY() + Cowboy.HEIGHT) {
            scoreRight++;
            endRound("Red");
        }
    }
    
    private void endRound(String winner) {
        roundOver = true;
        JOptionPane.showMessageDialog(this, winner + " cowboy wins Round " + currentRound);
        currentRound++;
        if (currentRound > totalRounds) {
            showGameOver();
        } else {
            resetBoth();
            roundOver = false;
        }
    }
    
    private void showGameOver() {
        String result;
        if (scoreLeft > scoreRight) {
            result = "Blue cowboy wins the game!";
        } else if (scoreRight > scoreLeft) {
            result = "Red cowboy wins the game!";
        } else {
            result = "It's a tie!";
        }
        JOptionPane.showMessageDialog(this, "Game Over!\n" + result +
            "\nLeft: " + scoreLeft + " vs Right: " + scoreRight);
        timer.stop();
    }
    
    private void resetBoth() {
        leftBullet.setActive(false);
        rightBullet.setActive(false);
        leftCowboy.setAlive(true);
        rightCowboy.setAlive(true);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if (!roundOver) {
            if (e.getKeyCode() == KeyEvent.VK_A &&
                leftCowboy.isAlive() && !leftBullet.isActive()) {
                shootLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_R && !leftCowboy.isAlive()) {
            resetLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_T && !rightCowboy.isAlive()) {
            resetRight();
        }
    }
    
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
    
    private void shootLeft() {
        leftBullet.fire(leftCowboy.getX() + Cowboy.WIDTH, leftCowboy.getY() + 20);
    }
    
    private void shootRight() {
        rightBullet.fire(rightCowboy.getX(), rightCowboy.getY() + 20);
    }
    
    private void resetLeft() {
        leftCowboy.setX(50);
        leftCowboy.setY(200);
        leftCowboy.setAlive(true);
    }
    
    private void resetRight() {
        rightCowboy.setX(300);
        rightCowboy.setY(200);
        rightCowboy.setAlive(true);
    }
}
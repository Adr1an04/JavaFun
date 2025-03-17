public class Main {
    public static void main(String[] args) {
        Cowboy cowboy = new Cowboy(10, 10, Color.RED);

        System.out.println("Cowboy's x: " + cowboy.getX());
        System.out.println("Cowboy's y: " + cowboy.getY());
        
        cowboy.setX(20);
        cowboy.setY(30);
        
        System.out.println("Cowboy's new x: " + cowboy.getX());
        System.out.println("Cowboy's new y: " + cowboy.getY());
    }  
} 
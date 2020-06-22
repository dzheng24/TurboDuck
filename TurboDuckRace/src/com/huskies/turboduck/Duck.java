package src.com.huskies.turboduck;

public class Duck {

    String name;
    Color color;
    Point point;

    public Duck() {
        this("Some duck I found in my memory", Color.YELLOW);
    }

    public Duck(String name, Color color) {
        this.name = name;
        this.color = color;
        point = new Point();
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Point getPoint() {
        return point;
    }

    public void move() {
        // TODO
    }
}

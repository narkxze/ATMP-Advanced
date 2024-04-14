package principles.open_closed;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return (22 / 7.0) * radius * radius;
    }
}

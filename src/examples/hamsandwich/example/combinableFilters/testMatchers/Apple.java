package hamsandwich.example.combinableFilters.testMatchers;

public class Apple {

    private final Brand brand;
    private Colour colour;

    public Apple(Brand brand) {
        this.brand = brand;
        this.colour = brand.perfectColour;
    }

    public void ripen() {
        if (colour == Colour.Red) {
            colour = Colour.Green;
        } else if (colour == Colour.Green) {
            colour = Colour.Yellow;
        } else {
            colour = Colour.Brown;
        }
    }

    public Colour colour() {
        return colour;
    }

    public Brand brand() {
        return brand;
    }

}

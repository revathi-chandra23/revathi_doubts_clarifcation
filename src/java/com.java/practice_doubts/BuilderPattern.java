// Car class
class Car {
    private String brand;
    private String model;
    private int year;
    private String color;

    private Car(CarBuilder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.year = builder.year;
        this.color = builder.color;
    }

    public static class CarBuilder {
        private String brand;
        private String model;
        private int year;
        private String color;

        public CarBuilder withBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public CarBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder withYear(int year) {
            this.year = year;
            return this;
        }

        public CarBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                '}';
    }
}

public class BuilderPattern {
    public static void main(String[] args) {
        Car car = new Car.CarBuilder()
                .withBrand("Toyota")
                .withModel("Camry")
                .withYear(2022)
                .withColor("Blue")
                .build();
        System.out.println(car);
    }
}
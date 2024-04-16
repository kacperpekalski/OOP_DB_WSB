package pl.wsb.examples.car;

import java.time.LocalDate;

public class Car {

    // fields //
    private String model;
    private LocalDate productionDate;
    private Color color;
    private String description;

    // constructors //
    public Car(String model, LocalDate productionDate, Color color, String description) {
        this.model = model;
        this.productionDate = productionDate;
        this.color = color;
        this.description = description;
    }

    public Car(String model, LocalDate productionDate, Color color) {
        this.model = model;
        this.productionDate = productionDate;
        this.color = color;
        this.description = null;
    }

    // setters //
    public void setDescription(String description) {
        this.description = description;
    }

    // getters //
    public String getModel() {
        return model;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public Color getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    // other private methods //
    private int getProductionYear() {
        if (this.productionDate == null) {
            return 0;
        }
        return this.productionDate.getYear();
    }

    private String getColorName() {
        if (this.color == null) {
            return "unknown color";
        }
        return this.color.toString().toLowerCase();
    }

    // and public one //
    public String getCarSummary() {
        int productionYear = this.getProductionYear();
        String colorName = this.getColorName();

        return String.format("%d %s in %s", productionYear, this.model, colorName);
    }
}

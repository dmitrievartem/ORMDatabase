package ormdatabase.model;

import javax.persistence.Embeddable;

@Embeddable
public class Shim {
    private Float number;
    private Float diameter;
    private Float thickness;

    public Shim() {
    }

    public Shim(Float number, Float diameter, Float thickness) {
        this.number = number;
        this.diameter = diameter;
        this.thickness = thickness;
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    public Float getDiameter() {
        return diameter;
    }

    public void setDiameter(Float diameter) {
        this.diameter = diameter;
    }

    public Float getThickness() {
        return thickness;
    }

    public void setThickness(Float thickness) {
        this.thickness = thickness;
    }
}

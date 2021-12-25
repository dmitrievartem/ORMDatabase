package ormdatabase.model;

import javax.persistence.Embeddable;

@Embeddable
public class Shim {
    private String number;
    private String diameter;
    private String thickness;

    public Shim() {
    }

    public Shim(String number, String diameter, String thickness) {
        this.number = number;
        this.diameter = diameter;
        this.thickness = thickness;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }
}

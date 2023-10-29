// Convert.java
public class ConvertMiles {
    private double kilometers;
    private double miles;

    // Constructor
    public ConvertMiles(double value) {
        if (value >= 0) {
            this.kilometers = value;
            this.miles = convertToMiles(value);
        } else {
            this.miles = Math.abs(value);
            this.kilometers = convertToKilometers(this.miles);
        }
    }

    //setter for km
    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
        this.miles = convertToMiles(kilometers);
    }
    //setter for miles
    public void setMiles(double miles) {
        this.miles = miles;
        this.kilometers = convertToKilometers(miles);
    }

    // Getter for km
    public double getKilometers() {
        return kilometers;
    }

    // Getter for miles
    public double getMiles() {
        return miles;
    }

    private double convertToMiles(double kilometers) {
        double convertedKM =  kilometers * 0.621371;
        return convertedKM;
    }

    private double convertToKilometers(double miles) {
        double convertedMiles = miles / 0.621371;
        return convertedMiles;
    }
}

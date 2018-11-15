
public class ParkingSlot {
    private String carRegistrationNumber;
    private String colorOfCar;
    private int parkingSlotNumber;
    public ParkingSlot(String registrationNumber,String carColor, int slotNumber){
        this.carRegistrationNumber = registrationNumber;
        this.colorOfCar = carColor;
        this.parkingSlotNumber = slotNumber;
    }

    public String getCarRegistrationNumber(){
        return this.carRegistrationNumber;
    }

    public String getColorOfCar(){
        return this.colorOfCar;
    }

    public int getParkingSlotNumber(){
        return this.parkingSlotNumber;
    }

    private void setCarRegistrationNumber(String registrationNumber){
        this.carRegistrationNumber = registrationNumber;
    }

    private void setColorOfCar(String color){
        this.colorOfCar = color;
    }

    private void setParkingSlotNumber(int slotNumber){
        this.parkingSlotNumber = slotNumber;
    }
}

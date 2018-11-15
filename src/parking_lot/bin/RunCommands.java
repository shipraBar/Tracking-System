
import java.util.ArrayList;

public class RunCommands {
    int countOfParkedCars = 0;
    ParkingSlot[] parking_lot;

    public int getCountOfParkedCars(){
        return countOfParkedCars;
    }

    public ParkingSlot[] getParking_lot(){
        return parking_lot;
    }

    private void setCountOfParkedCars(int count){
        countOfParkedCars = count;
    }

    private void setParking_lot(ParkingSlot[] slotArray){
        parking_lot = slotArray;
    }

    public void executeCommands(ArrayList<String> inputList, ParkingSlot[] executed_parking_lot){
        parking_lot = executed_parking_lot;
        //Iterate inputList
        for(int i=1;i<inputList.size();i++){
            String[] command = inputList.get(i).split(" ");

            if((command[0].equals("park"))&&(command.length==3)){
                if(countOfParkedCars<parking_lot.length){
                    int emptySlot = findNextAvailableSlot();
                    parking_lot[emptySlot] = new ParkingSlot(command[1],command[2],emptySlot+1);
                    countOfParkedCars++;
                    System.out.println("Allocated slot number: " + (emptySlot+1));
                }else
                    System.out.println("Sorry, parking lot is full");
            }else if((command[0].equals("leave"))&&(command.length==2)){
                int slotNumber = Integer.valueOf(command[1]);
                //if slotNumber is more than parking lot length or less than equal to zero
                if((slotNumber>parking_lot.length)||(slotNumber<=0))
                    System.out.println("There is no parking slot with number " + slotNumber);
                else {
                    parking_lot = removeCarEntryFromSlot(slotNumber);
                    System.out.println("Slot number " + command[1] + " is free");
                }
            }else if(command[0].equals("status")){
                printParkingLot();
            }else if((command[0].equals("registration_numbers_for_cars_with_colour"))&&(command.length==2)){
                System.out.println(printParticularCars(command[1],"RegistrationNumber"));
            }else if((command[0].equals("slot_numbers_for_cars_with_colour"))&&(command.length==2)){
                System.out.println(printParticularCars(command[1],"SlotNumber"));
            }else if((command[0].equals("slot_number_for_registration_number"))&&(command.length==2)){
                System.out.println(printSlotNumber(command[1]));
            }else{
                System.out.println("Not a valid command");
            }
        }
    }

    private int findNextAvailableSlot(){
        //iterate through parking_lot
        int i=0;
        for(i=0;i<parking_lot.length;i++){
            ParkingSlot slot = parking_lot[i];
            if(slot.getCarRegistrationNumber().equals(""))
                break;
        }
        return i;
    }

    private ParkingSlot[] removeCarEntryFromSlot(int slotNumber){
        countOfParkedCars--;
        parking_lot[slotNumber-1] = new ParkingSlot("",null,-1);
        return parking_lot;
    }

    private void printParkingLot(){
        System.out.println("Slot No.  " + "Registration No    " + "Colour");
        for(int i=0;i<parking_lot.length;i++) {
            ParkingSlot slot = parking_lot[i];
            if(!slot.getCarRegistrationNumber().equals("")){
                System.out.println(slot.getParkingSlotNumber() + "         " + slot.getCarRegistrationNumber() + "      " + slot.getColorOfCar());
            }
        }
    }

    public String printParticularCars(String colour,String type){
        String listOfCars = "";
        if((colour==null) || (colour.equals(""))) {
            return "Invalid color";
        }
        for(int i=0;i<parking_lot.length;i++) {
            ParkingSlot slot = parking_lot[i];
            if((slot.getColorOfCar()!=null)&&(slot.getColorOfCar().equals(colour))&&(type.equals("RegistrationNumber"))){
                listOfCars = listOfCars + slot.getCarRegistrationNumber() + ", ";
            }else if((slot.getColorOfCar()!=null)&&(slot.getColorOfCar().equals(colour))&&(type.equals("SlotNumber"))){
                listOfCars = listOfCars + String.valueOf(slot.getParkingSlotNumber()) + ", ";
            }
        }
        //remove the comma from the end
        if(!listOfCars.equals("")) {
            listOfCars = listOfCars.substring(0, listOfCars.length() - 2);
                return listOfCars;
        }else{
                return "Not found";
        }
    }

    public String printSlotNumber(String registration){
        boolean foundCar = false;
        String carNumber = "";
        for(int i=0;i<parking_lot.length;i++) {
            ParkingSlot slot = parking_lot[i];
            if(slot.getCarRegistrationNumber().equals(registration)) {
                foundCar = true;
                carNumber = String.valueOf(slot.getParkingSlotNumber());
                break;
            }
        }
        if(!foundCar)
            return "Not found";
        return carNumber;
    }
}

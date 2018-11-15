
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RunCommandsTest {

    private RunCommands commandsTestObj;
    private ParkingSlot[] tempSlotArray;
    ArrayList<String> dummyInputList;

    @Before
    public void setUp() throws Exception {
        commandsTestObj = new RunCommands();
        tempSlotArray = new ParkingSlot[5];
        for(int i=0;i<tempSlotArray.length;i++)
            tempSlotArray[i] = new ParkingSlot("",null,-1);
        dummyInputList = new ArrayList<String>();
        dummyInputList.add("create_parking_lot 5");
        dummyInputList.add("park KA-01-HH-1234 White");
        dummyInputList.add("park KA-01-HH-9991 Black");
        dummyInputList.add("park KA-01-HH-9992 Red");
        dummyInputList.add("park KA-01-HH-9993 White");
    }

    @Test
    public void findNextAvailableSlotTest(){
        /*ParkingSlot[] tempSlotArray = new ParkingSlot[4];
        for(int i=0;i<4;i++)
            tempSlotArray[i] = new ParkingSlot("",null,-1);
        ArrayList<String> dummyInputList = new ArrayList<String>();
        dummyInputList.add("create_parking_lot 4");
        dummyInputList.add("park KA-01-HH-1234 White");
        dummyInputList.add("park KA-01-HH-9999 White");*/
        dummyInputList.add("park KA-01-HH-9994 White");
        commandsTestObj.executeCommands(dummyInputList,tempSlotArray);
        assertEquals(commandsTestObj.getParking_lot()[0].getCarRegistrationNumber(),"KA-01-HH-1234");
        assertEquals(commandsTestObj.getCountOfParkedCars(),5);
    }

    @Test
    public void findNextAvailableSlotTestWithMoreCars(){
        /*ParkingSlot[] tempSlotArray = new ParkingSlot[2];
        for(int i=0;i<2;i++)
            tempSlotArray[i] = new ParkingSlot("",null,-1);
        ArrayList<String> dummyInputList = new ArrayList<String>();
        dummyInputList.add("create_parking_lot 2");
        dummyInputList.add("park KA-01-HH-1234 White");
        dummyInputList.add("park KA-01-HH-9999 White");
        dummyInputList.add("park KA-01-HH-9222 Black");*//**//**/
        dummyInputList.add("park KA-01-HH-9994 White");
        dummyInputList.add("park KA-01-HH-9995 White");
        commandsTestObj.executeCommands(dummyInputList,tempSlotArray);
        //Last car will not be parked in parking_lot,since parking_lot can accomdate two cars only
        assertNotEquals(commandsTestObj.getParking_lot()[0].getCarRegistrationNumber(),"KA-01-HH-9995");
        assertNotEquals(commandsTestObj.getParking_lot()[1].getCarRegistrationNumber(),"KA-01-HH-9995");
    }

    @Test
    public void removeCarEntryFromSlotTest(){
        dummyInputList.add("leave 2");
        commandsTestObj.executeCommands(dummyInputList,tempSlotArray);
        assertEquals(commandsTestObj.getCountOfParkedCars(),3);
        assertEquals(commandsTestObj.getParking_lot()[1].getCarRegistrationNumber(),"");
    }

    @Test
    public void printParticularCarsWithSlotNumberTest(){
        dummyInputList.add("slot_numbers_for_cars_with_colour White");
        commandsTestObj.executeCommands(dummyInputList,tempSlotArray);
        assertEquals(commandsTestObj.printParticularCars("White","SlotNumber"),"1, 4");
    }

    @Test
    public void printParticularCarsWithRegistrationNumberTest(){
        dummyInputList.add("registration_numbers_for_cars_with_colour White");
        commandsTestObj.executeCommands(dummyInputList,tempSlotArray);
        assertEquals(commandsTestObj.printParticularCars("White","RegistrationNumber"),"KA-01-HH-1234, KA-01-HH-9993");
    }

    @Test
    public void printParticularCarsWithNotFoundCarTest(){
        dummyInputList.add("registration_numbers_for_cars_with_colour Blue");
        commandsTestObj.executeCommands(dummyInputList,tempSlotArray);
        assertEquals(commandsTestObj.printParticularCars("Blue","RegistrationNumber"),"Not found");
    }

    @Test
    public void printSlotNumberTest(){
        dummyInputList.add("slot_number_for_registration_number KA-01-HH-9992");
        commandsTestObj.executeCommands(dummyInputList,tempSlotArray);
        assertEquals(commandsTestObj.printSlotNumber("KA-01-HH-9992"),"3");
    }

    @Test
    public void printSlotNumberWhenCarIsNotPresentTest(){
        dummyInputList.add("slot_number_for_registration_number KA-01-HH-9997");
        commandsTestObj.executeCommands(dummyInputList,tempSlotArray);
        assertEquals(commandsTestObj.printSlotNumber("KA-01-HH-9997"),"Not found");
    }




}

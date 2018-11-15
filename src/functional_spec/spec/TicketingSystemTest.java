
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TicketingSystemTest{
    private TicketingSystem system;

    @Before
    public void setUp() throws Exception {
        system = new TicketingSystem();
    }

    //Checking both the readFromConsole function using readInputArguments function
    @Test
    public void testReadInputArgumentsFromConsole(){
        String[] arg = new String[9];
        arg[0] = "create_parking_lot";
        arg[1] =  "6";
        arg[2] = "park";
        arg[3] = "KA-01-HH-1234";
        arg[4] = "White";
        arg[5] = "park";
        arg[6] = "KA-01-HH-9999";
        arg[7] = "White";
        arg[8] = "status";
        system.inputList.clear();
        system.readInputArguments(arg);
        assertEquals(system.inputList.size(),4);
    }

    @Test
    public void testIntializeParkingLot(){
        ArrayList<String> dummyInputList = new ArrayList<String>();
        dummyInputList.add("create_parking_lot 4");
        dummyInputList.add("park KA-01-HH-1234 White");
        dummyInputList.add("park KA-01-HH-9999 White");
        dummyInputList.add("status");
        system.inputList = dummyInputList;
        system.intializeParkingLot();
        assertEquals(system.parking_lot.length,4);
    }
}

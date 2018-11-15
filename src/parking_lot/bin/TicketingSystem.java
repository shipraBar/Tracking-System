
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TicketingSystem {
    public ParkingSlot[] parking_lot;
    public ArrayList<String> inputList = new ArrayList<String>();


    public static void main(String[] args){

        //Input the file given by user
        TicketingSystem system = new TicketingSystem();
        system.readInputArguments(args);
        system.intializeParkingLot();
        RunCommands commandObj = new RunCommands();
        commandObj.executeCommands(system.inputList,system.parking_lot);
    }

    public void readInputArguments(String[] args){
        File inputFile = null;
        inputFile = new File(args[0]);
        if(inputFile.canRead())
            readInputFile(inputFile);
        else
            readFromConsole(args);
    }

    public void intializeParkingLot(){
        int parkingSlotCount = (Integer.valueOf(inputList.get(0).split(" ")[1]));
        parking_lot = new ParkingSlot[parkingSlotCount];
        //fill the array with empty registration number, null color of car and negative parking slot number
        Arrays.fill(parking_lot,new ParkingSlot("",null,-1));
    }

    private void readFromConsole(String[] args){
        String command = "";
        for(int i=0;i<args.length;) {
            if(args[i].equals("park")) {
                command = args[i++];
                //checking if user has input incomplete command
                if(i<args.length)
                    command = command + " " + args[i++];
                if(i<args.length)
                    command = command + " " + args[i++];
                inputList.add(command);
            }else if(args[i].equals("status"))
                inputList.add(args[i++]);
            else {
                command = args[i++];
                //checking if user has input incomplete command
                if((i)<args.length)
                    command = command + " " + args[i++];
                inputList.add(command);
            }
        }
    }

    private void readInputFile(File inputFile){
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(inputFile));
            while ((sCurrentLine = br.readLine()) != null) {
                //print each line
                System.out.println(sCurrentLine);
                inputList.add(sCurrentLine);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}

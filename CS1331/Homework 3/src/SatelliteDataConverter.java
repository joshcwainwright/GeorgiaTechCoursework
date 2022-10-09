import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 * Satellite Data Converter Class.
 * @author Joshua Wainwright
 * @version 1
 */
public class SatelliteDataConverter {
    private static Satellite[] createSatelliteArray(File data) {
        File[] fileArray = data.listFiles();
        Satellite[] satArray = new Satellite[fileArray.length];
        if (satArray.length != 0) {
            try {
                for (int i = 0; i < fileArray.length; i++) {
                    Scanner myScan = new Scanner(-*);
                    String stringData = "";
                    while (myScan.hasNextLine()) {
                        stringData += myScan.nextLine() + "\n";
                    }
                    System.out.println(stringData);
                    satArray[i] = SatelliteTLEParser.parseData(stringData);
                }
            } catch (java.io.FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return satArray;
    }

    /**
     * main method.
     * @param args path to satellite_data directory
     */
    public static void main(String[] args) {
        try {
            String fullPath = args[0];
            File path = new File(fullPath);
            Satellite[] satArray = createSatelliteArray(path);
            File output = new File("satellite_output.csv");
            FileWriter myWriter = new FileWriter(output);
            for (int i = 0; i < satArray.length; i++) {
                myWriter.write(satArray[i].encodeCSV());
            }
            myWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

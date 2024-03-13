package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class DataStorageManager {
    /*
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * i have not handle if the file does NOT exist - it just throws an exception 
     * because i wanna avoid misspellings that will cause ser and deser to target diff files 
     */

    // serialize whole list of any type
    public static <T> void serialize(String filePath, List<T> dataList){
        try {
            File file = new File(filePath);

            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(dataList);
            out.close();
            fileOut.close();
        }
        catch(FileNotFoundException f) {
            System.out.println("File " + filePath + " not found");
            f.printStackTrace();
        }
        catch(IOException i) {
            i.printStackTrace();
        }
    }

    // deserialize whole list of any type
    @SuppressWarnings("unchecked")
    public static <T> List<T> deserialize(String filePath) {
        List<T> dataList = null;
        try {
            File file = new File(filePath);
            
            // case if there is no data yet
            if (file.exists()) {
                // if file is empty return empty array list
                if (file.length() == 0) {
                    dataList = new ArrayList<>();
                    return dataList;
                }
            }

            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            dataList = (List<T>) in.readObject();
            in.close();
            fileIn.close();
        }
        catch(FileNotFoundException f) {
            System.out.println("File " + filePath + " not found");
            f.printStackTrace();
        }
        catch(IOException i) {
            i.printStackTrace();
        }
        catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return dataList;
    }

}

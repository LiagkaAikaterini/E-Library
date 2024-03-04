import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class DataManagement {

    // serialize whole list of any type
    public static <T> void serialize(String file, List<T> objectList){
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(objectList);
            out.close();
            fileOut.close();
        }
        catch(FileNotFoundException f) {
            System.out.println("File " + file + " not found");
            f.printStackTrace();
        }
        catch(IOException i) {
            i.printStackTrace();
        }
    }

    // deserialize whole list of any type
    public static <T> List<T> deserializeAdmins(String file) {
        List<T> dataList = null;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            dataList = (List<T>) in.readObject();
            in.close();
            fileIn.close();
        }
        catch(FileNotFoundException f) {
            System.out.println("File " + file + " not found");
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

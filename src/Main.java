import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static String TRANSFER_FILE = "transfer.txt";
    public static String DATA_FILE = "data.txt";
    public static List<Product> productTransferFile = new ArrayList<>();
    public static List<Product> productDataFile = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("========== CHOOSE ===========");
        System.out.println("A). Check in Data File");
        System.out.println("B). Check in Transfer File");
        System.out.println("C). Check CRUD");
        System.out.println("D). Exit");
        String ops;
        do {
            System.out.print(">>> Choose: ");
            ops = scanner.next().toLowerCase();
            switch (ops) {
                case "a":
                    readFromFile(DATA_FILE, productDataFile);
                    displayProduct(productDataFile);
                    break;
                case "b":
                    checkTransferFile(TRANSFER_FILE);
                    break;
                case "c":{
                    System.out.println("Update Case!");
                    break;
                }
                case "d":
                    checkTransferFile(TRANSFER_FILE);
                    System.out.println("Bye...");
                default:
                    System.out.println("Case Invalided!");
                    break;
            }
        }while(!(ops.equals("d")));
    }
    public static void listOfDataRecord(){
        readFromFile(TRANSFER_FILE, productTransferFile);
        System.out.println("===================================================");
        System.out.println("              ---- Data Tracked ----               ");
        System.out.println("===================================================");
        displayProduct(productTransferFile);
    }
    public static void checkTransferFile(String file){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            if(reader.readLine() != null){
                listOfDataRecord();
                System.out.println("You need to commit!");
                System.out.print("YES/NO: ");
                String ops = scanner.next();
                if (ops.equalsIgnoreCase("y")) {
                    writeToDataFile(DATA_FILE);
                    clearDataInTransferFile(TRANSFER_FILE);
                } else if (ops.equalsIgnoreCase("n")) {
                    clearDataInTransferFile(TRANSFER_FILE);
                } else {
                    System.out.println("Valid case!");
                }
            }else{
                System.out.println("Nothing to commit!");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    public static void writeToDataFile(String file){
        try(BufferedWriter writeToDataFile = new BufferedWriter(new FileWriter(file,true))){
            for (Product data : productTransferFile) {
                writeToDataFile.write(data.getId()+",");
                writeToDataFile.write(data.getProductName()+",");
                writeToDataFile.write(data.getQty()+",");
                writeToDataFile.write(data.getPrice()+",");
                writeToDataFile.write(data.getDate()+",");
                writeToDataFile.write(data.getStatus());
                writeToDataFile.newLine();
            }
        }catch (IOException e){
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    public static void clearDataInTransferFile(String file){
        try(BufferedWriter writeToClear = new BufferedWriter(new FileWriter(file))){
            writeToClear.close();
            System.out.println("File has been cleared successfully.");
        }catch (IOException e){
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    public static void readFromFile(String file, List<Product> LIST_NAME){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Product product = new Product();
                    product.setId(parts[0].trim());
                    product.setProductName(parts[1].trim());
                    product.setQty(Integer.parseInt(parts[2].trim()));
                    product.setPrice(Double.parseDouble(parts[3].trim()));
                    product.setDate(LocalDate.parse(parts[4].trim()));
                    product.setStatus(parts[5].trim());
                    LIST_NAME.add(product);
                } else {
                    System.out.println("Invalid data in file: " + line);
                }
            }
        }catch(IOException e){
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    public static void displayProduct(List<Product> LIST_NAME){
        for (Product product : LIST_NAME) {
            System.out.print(product.getId()+" - ");
            System.out.print(product.getProductName()+" - ");
            System.out.print(product.getQty()+" - ");
            System.out.print(product.getPrice()+" - ");
            System.out.print(product.getDate()+" - ");
            System.out.print(product.getStatus());
            System.out.println();
        }
    }
}

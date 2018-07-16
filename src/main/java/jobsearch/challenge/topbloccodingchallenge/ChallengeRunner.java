/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobsearch.challenge.topbloccodingchallenge;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

/**
 * This class was created for the TopBloc Coding Challenge
 * 
 * @author Tommy
 */
public class ChallengeRunner {
    
    static String URL = "http://34.239.125.159:5000/challenge/";
    static String DATA1 = "src/Data1.xlsx";
    static String DATA2 = "src/Data2.xlsx";
    
    /**
     * This method handles the HTTP Post request
     * 
     * @param payload
     * @throws Exception 
     */
    private static void sendPost(String payload) throws Exception {
        URL obj = new URL(URL);
        HttpsURLConnection con = (HttpsURLConnection)obj.openConnection();
        
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        // Write our json-formatted string to the server
        wr.writeChars(payload);
        // flush and close output stream
        wr.close();
    }
    
    public static void main(String[] args) {
        try {
            // Load the Excel sheets in each document
            FileInputStream data1 = new FileInputStream(
                    new File(DATA1)
            );
            FileInputStream data2 = new FileInputStream(
                    new File(DATA1)
            );
            XSSFWorkbook wb1 = new XSSFWorkbook(data1);
            XSSFWorkbook wb2 = new XSSFWorkbook(data2);
            XSSFSheet sheet1 = wb1.getSheetAt(0);
            XSSFSheet sheet2 = wb2.getSheetAt(0);
            
            // data structures to hold Data1.xlsx lists
            ArrayList<Double> Data1_NumberSetOne = new ArrayList<>();
            ArrayList<Double> Data1_NumberSetTwo = new ArrayList<>();
            ArrayList<String> Data1_WordSetOne = new ArrayList<>();
            
            // sorting cell values into proper lists
            for (int col = 0; col < 3; col++) {
                Iterator<Row> rowIterator = sheet1.iterator();
                // skip the title of the column
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    Cell cell = row.getCell(col);
                    switch (col) {
                        case 0:
                            Data1_NumberSetOne.add(cell.getNumericCellValue());
                            break;
                        case 1:
                            Data1_NumberSetTwo.add(cell.getNumericCellValue());
                            break;
                        case 2:
                            Data1_WordSetOne.add(cell.getStringCellValue());
                            break;
                    }
                }
            }
            
            // More lists for Data2.xlsx values
            ArrayList<Double> Data2_NumberSetOne = new ArrayList<>();
            ArrayList<Double> Data2_NumberSetTwo = new ArrayList<>();
            ArrayList<String> Data2_WordSetOne = new ArrayList<>();
            
            // Sorting values to proper lists
            for (int col = 0; col < 3; col++) {
                Iterator<Row> rowIterator = sheet2.iterator();
                // skip the title of the column
                rowIterator.next();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    Cell cell = row.getCell(col);
                    switch (col) {
                        case 0:
                            Data2_NumberSetOne.add(cell.getNumericCellValue());
                            break;
                        case 1:
                            Data2_NumberSetTwo.add(cell.getNumericCellValue());
                            break;
                        case 2:
                            Data2_WordSetOne.add(cell.getStringCellValue());
                            break;
                    }
                }
            }
            
            // simultaneously calculating outcomes and creating Arrays for each
            // set of results.
            Double[] products = { 
                Data1_NumberSetOne.get(0) * Data2_NumberSetOne.get(0),
                Data1_NumberSetOne.get(1) * Data2_NumberSetOne.get(1),
                Data1_NumberSetOne.get(2) * Data2_NumberSetOne.get(2),
                Data1_NumberSetOne.get(3) * Data2_NumberSetOne.get(3)
            };
            
            Double[] quotients = { 
                Data1_NumberSetTwo.get(0) / Data2_NumberSetTwo.get(0),
                Data1_NumberSetTwo.get(1) / Data2_NumberSetTwo.get(1),
                Data1_NumberSetTwo.get(2) / Data2_NumberSetTwo.get(2),
                Data1_NumberSetTwo.get(3) / Data2_NumberSetTwo.get(3)
            };
            
            String[] phrases = {
                Data1_WordSetOne.get(0) + " " + Data2_WordSetOne.get(0),
                Data1_WordSetOne.get(1) + " " + Data2_WordSetOne.get(1),
                Data1_WordSetOne.get(2) + " " + Data2_WordSetOne.get(2),
                Data1_WordSetOne.get(3) + " " + Data2_WordSetOne.get(3)
            };
            
            // Packaging results in a JSON object
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", "tbarry3@mail.depaul.edu");
            jsonObject.put("numberSetOne", Arrays.toString(products));
            jsonObject.put("numberSetTwo", Arrays.toString(quotients));
            jsonObject.put("wordSetOne", Arrays.toString(phrases));
            
            // This method handles the HTTP Post request
            sendPost(jsonObject.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

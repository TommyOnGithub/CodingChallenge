/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobsearch.challenge.topbloccodingchallenge;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Tommy
 */
public class ChallengeRunner {
    
    public static void main(String[] args) {
        try {
            FileInputStream data1 = new FileInputStream(
                    new File("src/Data1.xlsx")
            );
            FileInputStream data2 = new FileInputStream(
                    new File("src/Data2.xlsx")
            );
            XSSFWorkbook wb1 = new XSSFWorkbook(data1);
            XSSFWorkbook wb2 = new XSSFWorkbook(data2);
            XSSFSheet sheet1 = wb1.getSheetAt(0);
            XSSFSheet sheet2 = wb2.getSheetAt(0);
            
            ArrayList<Double> Data1_NumberSetOne = new ArrayList<Double>();
            ArrayList<Double> Data1_NumberSetTwo = new ArrayList<Double>();
            ArrayList<String> Data1_WordSetOne = new ArrayList<String>();
            
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
            
            ArrayList<Double> Data2_NumberSetOne = new ArrayList<Double>();
            ArrayList<Double> Data2_NumberSetTwo = new ArrayList<Double>();
            ArrayList<String> Data2_WordSetOne = new ArrayList<String>();
            
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
                Data1_WordSetOne.get(3) + " " + Data2_WordSetOne.get(3),
            };
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

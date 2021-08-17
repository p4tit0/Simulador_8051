/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;  
import java.io.FileInputStream;  
import java.util.ArrayList;
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

/**
 *
 * @author space
 */
public class Reader {
    public static void main(String[] args) {
        print2D(readFile("src\\res\\instruction_set_8051.xlsx"));
    }
    
    public static void print2D(Object mat[][])
    {
        for (Object[] row : mat){
            for (Object x : row)
                System.out.print(x + " ");
            System.out.println("");
        }            
    }
    
    public static String[][] readFile(String file_path){  
        try{  
            File file = new File(file_path);   //creating a new file instance  
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file   
            XSSFWorkbook wb = new XSSFWorkbook(fis);   
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
            ArrayList<ArrayList<String>> table = new ArrayList<>();
            while (itr.hasNext()){  
                Row row = itr.next();  
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                ArrayList<String> row_arr = new ArrayList<>();
                while (cellIterator.hasNext()){  
                    Cell cell = cellIterator.next();  
                    switch (cell.getCellType()){  
                        case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
                            row_arr.add(cell.getStringCellValue());  
                            break;  
                        case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type  
                            row_arr.add(Integer.toString((int) cell.getNumericCellValue()));  
                            break;  
                    }  
                }
                table.add(row_arr);
            }
            String[][] table_arr = new String[table.size()][];
            for (int i = 0; i < table.size(); i++) {
                String[] row = table.get(i).toArray(new String[0]);
                table_arr[i] = row;
            }
            return table_arr;
        }  
        catch(Exception e){  
        e.printStackTrace();  
        }
        return null;
    }  
}

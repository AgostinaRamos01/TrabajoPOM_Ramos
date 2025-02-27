package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Propertiesdriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {

    public static ArrayList<String> getTestData(String nombreCP) {
        ArrayList<String> data = new ArrayList<String>();

        FileInputStream file = null;
        try {
            //file = new FileInputStream("C:\\Users\\agostina.ramos\\IdeaProjects\\TrabajoPOD\\src\\test\\resources\\data\\CasosDePruebas.xlsx");
            file = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//data//Pruebas.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        XSSFWorkbook excel = null;
        try {
            excel = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int sheets = excel.getNumberOfSheets();

        for(int i =0; i< sheets;i++){
            if(excel.getSheetName(i).equalsIgnoreCase("CasoPrueba")){
                XSSFSheet hojaExcel = excel.getSheetAt(i);

                Iterator<Row> filas = hojaExcel.iterator();

                Row fila = filas.next();

                Iterator<Cell> celdas = fila.cellIterator();

                int k=0;
                int columna =0;
                while(celdas.hasNext()){
                    Cell celdaSelecciona = celdas.next();
                    if(celdaSelecciona.getStringCellValue().equalsIgnoreCase("TituloCPs")){
                        //Encontre la columna con los nombres de los casos de prueba
                        System.out.println("encontre");
                        columna = k;

                    }
                    k++;
                }

                while(filas.hasNext()){
                    Row r = filas.next();
                    if(r.getCell(columna).getStringCellValue().equalsIgnoreCase(nombreCP)){

                        Iterator<Cell> cv =r.cellIterator();

                        while(cv.hasNext()){
                            Cell cell = cv.next();

                            if(cell.getCellType() == CellType.STRING){
                                //System.out.println(cell.getStringCellValue());
                                data.add(cell.getStringCellValue());
                                System.out.println(cell.getStringCellValue());
                            }else if(cell.getCellType() == CellType.NUMERIC){
                                String valorCelda = NumberToTextConverter.toText(cell.getNumericCellValue());
                                //System.out.println(valorCelda);
                                data.add(valorCelda);
                            }
                        }
                    }

                }

            }
        }
        return data;
    }

}

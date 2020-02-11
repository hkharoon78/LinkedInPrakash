package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	XSSFSheet sheet = null;
	FileInputStream file = null;
	XSSFWorkbook workbook  = null;

	public  ExcelData(String fileName,String sheetName) {
		try {
			file = new FileInputStream(new File(fileName));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {

		}
	}


	public String getData(String colName) {
		String colValue  = "";
		for(int i=0;i<13;i++) {
			String actColName = sheet.getRow(0).getCell(i).toString();
			if(actColName.trim().equalsIgnoreCase(colName.trim())) {
				return sheet.getRow(1).getCell(i).toString();
			}
		}
		return colValue;
	}

	public String getData(int row,int col) {
		String cellValue  = "";
		cellValue = sheet.getRow(row-1).getCell(col-1).toString();
		return cellValue;
	}

	public int getRowCount() {
		return sheet.getLastRowNum()-sheet.getFirstRowNum();
	}

	
	public void setData(int row,String colName,int value){
		try{
		for(int i=0;i<13;i++) {
			String actColName = sheet.getRow(0).getCell(i).toString();
			if(actColName.trim().equalsIgnoreCase(colName)) {
				sheet.getRow(row-1).getCell(i).setCellValue(value);
				break;
			}
		}
		
		file.close();

        FileOutputStream outFile =new FileOutputStream(new File(Common.ExcelPath));
        workbook.write(outFile);
        outFile.close();
		} catch (Exception e) {
				Common.fail("Unable to set the value in excel sheet for Page No Column");
		}
	}
	


}

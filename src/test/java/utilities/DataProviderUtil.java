package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {
	@DataProvider(name="fetchUserNamePassword")
	public Iterator<List<String>> fetchUserNamePassword(){
		String sheetName="LoginDataDrivenTest";
		List<List<String>> users=new ArrayList<List<String>>();
		ExcelUtil excelOp=new ExcelUtil();
		int rows=excelOp.getRowsFromSheet(sheetName);
		int cols=excelOp.getColumnsFromSheet(sheetName);
		for(int i=1;i<=rows;i++){
			List<String> al=new ArrayList<String>();
			for(int j=1;j<cols;j++){
				al.add(excelOp.getExcelData(sheetName,i,j));
			}
			users.add(al);
		}
		return users.iterator();
	}
}

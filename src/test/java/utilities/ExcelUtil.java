package utilities;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtil {
	private Workbook workBook;
	private String path="./testData/input.xlsx";
	private XSSFWorkbook wBook;
	private XSSFSheet sName;
	private XSSFRow row;
	public ExcelUtil(String path) {
		this.path=path;
	}
	public ExcelUtil(){}
	public int getRowCount(String sheetName){
		FileInputStream fis=null;
		int rowNum=0;
		try{
			fis=new FileInputStream(this.path);
			wBook=new XSSFWorkbook(fis);
			sName=wBook.getSheet(sheetName);
			rowNum=sName.getLastRowNum();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(fis!=null) {
					fis.close();
					wBook.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return rowNum;
	}
	public int getColumnCount(String sheetName,int rowNum){
		FileInputStream fis=null;
		int columnNum=0;
		try {
			fis=new FileInputStream(this.path);
			wBook=new XSSFWorkbook(fis);
			sName=wBook.getSheet(sheetName);
			row=sName.getRow(rowNum);
			columnNum=row.getLastCellNum();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(fis!=null) {
					fis.close();
					wBook.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return columnNum;
	}
	public Workbook getWorkBook(){
		FileInputStream fis=null;
		try {
				fis=new FileInputStream("./testData/input.xlsx");
				workBook=WorkbookFactory.create(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return workBook;
	}
	public String getExcelData(String sheetName,int row,int column) {
		try {
			return getWorkBook().getSheet(sheetName).getRow(row).getCell(column).toString();
		}catch(Exception e) {
			return "";
		}
	}
	public int getRowsFromSheet(String sheetName){
		try {
			return getWorkBook().getSheet(sheetName).getLastRowNum();
		}catch(Exception e) {
			return 0;
		}
	}
	public int getColumnsFromSheet(String sheetName){
		try {
			Iterator<Row> rows = getWorkBook().getSheet(sheetName).rowIterator();
			return rows.next().getLastCellNum();
		}catch(Exception e) {
			return 0;
		}
	}
}

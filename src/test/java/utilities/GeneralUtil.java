package utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

import org.testng.Assert;

public class GeneralUtil {

	public static String getProperty(String key) {
		try {
			FileReader fr=new FileReader("./src/test/resources/config.properties");
			Properties p=new Properties();
			p.load(fr);
			return p.getProperty(key);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		return "";
	}
	public static void setProperty(String key,String value) {
		try {
			FileReader fr=new FileReader("./src/test/resources/config.properties");
			Properties p=new Properties();
			p.load(fr);
			p.setProperty(key,value);
			
			FileWriter fw=new FileWriter("./src/test/resources/config.properties");
			p.store(fw,"Gets Updated");
			System.out.println(key+"==="+value);
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}

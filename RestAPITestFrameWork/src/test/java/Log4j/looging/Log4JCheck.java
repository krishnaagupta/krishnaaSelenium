package Log4j.looging;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public  abstract class Log4JCheck {

	public Log4JCheck() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
	Logger.log
	}
	
	public static void loadLog4jProperty() {
	Properties prop=new Properties();
	FileInputStream fileIn= new FileInputStream(System.getProperty("user.dir")+"");
	prop.load(fileIn);
	propertyCon
}
}

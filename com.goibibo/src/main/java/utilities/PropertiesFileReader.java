package utilities;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesFileReader {
private Properties properties;
private Map<String,String> allData;
	
	//Class Constructor open read the data from the input stream of the properties file
	public PropertiesFileReader(InputStream fis) throws Exception
	{
		properties = new Properties();
		properties.load(fis);
		allData = new HashMap<>();
		for (String key : properties.stringPropertyNames())
		{
			allData.put(key, properties.getProperty(key));
		}
		fis.close();
	}
	
	public String getValue(String key)
	{
		if(allData.containsKey(key))
			return allData.get(key);
		
		return null;
	}
	
}

package price.fuel.pf2.common.domain;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EntityMap extends HashMap<String, Object> {

    private static final long serialVersionUID = 6723434363565852261L;
    private static final Logger logger = LoggerFactory.getLogger(EntityMap.class);
    

	public HashMap<String, Object> toHashMap(){
		return this;
	}
    

    @Override
    public Object put(String key, Object value) {
    	
    	logger.info(key, value);
    	
        //return super.put(EntityMap.toCamelCase((String) key), value);
    	Object obj = null;
    	try{
    		obj = super.put((String) key.toLowerCase(), value);
    	}catch(Exception e){
    		logger.debug(e.getMessage());
    		e.printStackTrace();
    	}finally{
    	}
    	return obj;
    }
    
    @Override
    public void putAll(Map map) {
    	
    	try{
    		logger.debug("put all");
    		super.putAll(map);
    	}catch(Exception e){
    		logger.debug(e.getMessage());
    		e.printStackTrace();
    	}
    	
    	
        
    }

	public static String toCamelCase(String var){
		
		char[] arr = var.toCharArray();
		
		for(int i=0 ; i<var.length() ; i++){
			if(i>0 && arr[i-1]=='_')
				arr[i] = Character.toUpperCase(arr[i]);
			else
				arr[i] = Character.toLowerCase(arr[i]);
		}
		
		return String.valueOf(arr).replaceAll("_", "");
	}
	
	public static String toUnderscoreCase(String var){
		
        char[] arr1 = var.toCharArray();
        char[] arr2 = new char[arr1.length*2];

        for(int i=0 ; i<arr1.length * 2 ; i++){
                if(i%2==0) arr2[i] = arr1[i/2];
                else arr2[i] = ' ';
        }

        for(int i=0 ; i<arr2.length ; i++){
	        if(i%2==0 && i>0 && arr2[i]==Character.toUpperCase(arr2[i]) && arr2[i]>='A' && arr2[i]<='Z')
	        {
	                arr2[i-1] = '_';
	                arr2[i] = Character.toLowerCase(arr2[i]);
	        }
        }

		return String.valueOf(arr2).replaceAll(" ", "");
	}


	
	
}

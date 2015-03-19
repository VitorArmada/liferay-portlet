package pt.impresa.liferay.config;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pt.impresa.serialization.JSONSerializer;

public class ImpresaConfiguration {
    
    private Map<String,Object> config = new HashMap<String, Object>();
    
    public void set(String key, Object obj) {
        config.put(key, obj);
    }
    
    public Object get(String key) {
        return config.get(key);
    }
    
    public int getInt(String key, int defaultValue) {
        Integer result = (Integer) get(key);
        if(result == null) {
            result = defaultValue;
        }
        return result;
    }
    
    public boolean getBoolean(String key, boolean defaultValue) {
        Boolean result = (Boolean) get(key);
        if(result == null) {
            result = defaultValue;
        }
        return result;
    }
    
    public String getString(String key) {
        return (String) get(key);
    }
    
    public String getString(String key, String defaultValue) {
        String result = (String) get(key);
        if(result == null || result.isEmpty()) {
            result = defaultValue;
        }
        return result;
    }
    
    public List<String> getList(String key) {
        return getList(key, null);
    }
    
    public List<String> getList(String key, List<String> defaultValue) {
        List<String> result = (List<String>)get(key);
        if(result == null || result.isEmpty() || "".equals(result.get(0))) {
            result = defaultValue;
        }
        return result;
    }
    
    public Map<String, Object> getConfig() {
        return config;
    }
    
    public void setConfig(Map<String,Object> config) {
        this.config = config;
    }
    
    public void parseAndSetString(String key, String value) {
        if(value != null) {
            set(key,value);
        }
    }
    
    public void parseAndSetList(String key, String value) {
        if(value != null) {
            String[] values = value.split(",");
            if(values != null && !"".equals(values[0])) {
                set(key, values);
            }
        }
    }
    
    public void parseAndSetInt(String key, String value) throws NumberFormatException {
        if(value != null && !value.isEmpty()) {
            int valueVal = Integer.parseInt(value);
            set(key, valueVal);
        } 
    }
    
    public void parseAndSetBoolean(String key, String value) {
        if(value != null && !value.isEmpty()) {
            boolean valueVal = Boolean.parseBoolean(value);
            set(key, valueVal);
        } 
    }
    
    public String toJSON() throws IOException {
        return JSONSerializer.toJSON(this);
    }
}

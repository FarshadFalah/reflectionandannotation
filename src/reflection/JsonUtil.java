package reflection;

import java.lang.reflect.Field;

public class JsonUtil {
	 public static <T> String serialize(T object) throws IllegalAccessException {
	        Class<?> clazz = object.getClass();
	        StringBuilder json = new StringBuilder("{");
	        Field[] fields = clazz.getDeclaredFields();
	        for (int i = 0; i < fields.length; i++) {
	            Field field = fields[i];
	            field.setAccessible(true);
	            json.append("\"").append(field.getName()).append("\":\"").append(field.get(object)).append("\"");
	            if (i < fields.length - 1) {
	                json.append(", ");
	            }
	        }
	        json.append("}");
	        return json.toString();
	    }

	    public static <T> T deserialize(String json, Class<T> clazz) throws Exception {
	        T object = clazz.getDeclaredConstructor().newInstance();
	        json = json.replaceAll("[{}]", "");
	        String[] keyValuePairs = json.split(", ");
	        for (String pair : keyValuePairs) {
	            String[] entry = pair.split(":");
	            String key = entry[0].replace("\"", "");
	            String value = entry[1].replace("\"", "");
	            
	            Field field = clazz.getDeclaredField(key);
	            field.setAccessible(true);
	            if (field.getType() == int.class) {
	                field.set(object, Integer.parseInt(value));
	            } else {
	                field.set(object, value);
	            }
	        }
	        return object;
	    }
}

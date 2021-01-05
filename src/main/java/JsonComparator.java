import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class JsonComparator {
    public boolean isEqual(Object json1, Object json2) throws JSONException {
        if (!json1.getClass().equals(json2.getClass())) {
            return false;
        }

        if (json1 instanceof JSONObject) {
            JSONObject object1 = (JSONObject) json1;
            JSONObject object2 = (JSONObject) json2;
            Set<String> keysFromFirstJson = object1.keySet();
            Set<String> keysFromSecondJson = object2.keySet();

            if (!keysFromSecondJson.containsAll(keysFromFirstJson)) {
                return false;
            }

            for(String key : keysFromFirstJson) {
                Object valueFromFirstJson = object1.get(key);
                Object valueFromSecondJson = object2.get(key);

                boolean equal = isEqual(valueFromFirstJson, valueFromSecondJson);
                if (!equal) {
                    return false;
                }
            }
        } else if (json1 instanceof JSONArray) {
            JSONArray arrFromFirstJson = (JSONArray) json1;
            JSONArray arrFromSecondJson = (JSONArray) json2;

            boolean equal = isEqual(arrFromFirstJson.get(0), arrFromSecondJson.get(0));
            if (!equal) {
                return false;
            }
        }

        return true;
    }
}

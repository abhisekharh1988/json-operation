import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonComparatorTest {

    @Test
    public void shouldReturnTrueIfBothInputsAreIdentical() {
        String str1 = "{\"name\": \"Abhisek\", \"age\": 34}";
        String str2 = "{\"name\": \"Abhisek\", \"age\": 34}";

        JSONObject json1 = new JSONObject(str1);
        JSONObject json2 = new JSONObject(str2);

        assertTrue(new JsonComparator().isEqual(json1, json2));
    }

    @Test
    public void shouldReturnTrueIfSecondJsonContainsAllFieldsWithSameStructFromFirstJson() {
        String str1 = "{\"name\": \"Abhisek\", \"age\": 34}";
        String str2 = "{\"name\": \"Abhisek\", \"age\": 34, \"address\": { \"city\": \"Kolkata\"}}";

        JSONObject json1 = new JSONObject(str1);
        JSONObject json2 = new JSONObject(str2);

        assertTrue(new JsonComparator().isEqual(json1, json2));
    }

    @Test
    public void shouldReturnFalseIfSecondJsonDoesNotContainsAllFieldsWithSameStructFromFirstJson() {
        String str1 = "{\"name\": \"Abhisek\", \"age\": 34, \"id\": 1}";
        String str2 = "{\"name\": \"Abhisek\", \"age\": 34, \"address\": { \"city\": \"Kolkata\"}}";

        JSONObject json1 = new JSONObject(str1);
        JSONObject json2 = new JSONObject(str2);

        assertFalse(new JsonComparator().isEqual(json1, json2));
    }

    @Test
    public void shouldReturnFalseIfFieldStructureIsDifferent() {
        String str1 = "{\"name\": {\"first\": \"Abhisek\", \"last\": \"Harh\"}, \"age\": 34, \"id\": 1}";
        String str2 = "{\"name\": \"Abhisek\", \"age\": 34, \"address\": { \"city\": \"Kolkata\"}}";

        JSONObject json1 = new JSONObject(str1);
        JSONObject json2 = new JSONObject(str2);

        assertFalse(new JsonComparator().isEqual(json1, json2));
    }

    @Test
    public void shouldReturnFalseIfNestedJsonStructureIsNotSame() {
        String str1 = "{\"name\": \"Abhisek\", \"age\": 34, \"address\": { \"city\": \"Kolkata\", \"state\": \"WB\"}}";
        String str2 = "{\"name\": \"Abhisek\", \"age\": 34, \"address\": { \"city\": \"Kolkata\"}}";

        JSONObject json1 = new JSONObject(str1);
        JSONObject json2 = new JSONObject(str2);

        assertFalse(new JsonComparator().isEqual(json1, json2));
    }
}
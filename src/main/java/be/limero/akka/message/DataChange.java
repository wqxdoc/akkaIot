package be.limero.akka.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class DataChange {
    private static Logger log = LoggerFactory.getLogger(Message.class);

    public String topic;
    public HashMap<String, Object> map = new HashMap<String, Object>();

    public DataChange(String t, Object data) {
        topic = t;
        map.put("data", data);
    }

    public DataChange(String t, Object... objects) {
        topic = t;
        String key = "NOKEY";
        int counter = 0;
        for (Object object : objects) {
            if (counter % 2 == 0) {
                key = (String) object;
            } else {
                map.put(key, object);
            }
            counter++;
        }
    }

    public String getString() {
        try {
            return (String) map.get("data");
        } catch (Exception e) {
            log.error(" cannot convert topic " + topic + " to string ");
            return "";
        }

    }

    public Double getDouble() {
        try {
            String s = (String) map.get("data");
            return Double.valueOf(s);
        } catch (Exception e) {
            log.error(" cannot convert topic " + topic + " to double ");
            return 0.0;
        }

    }

    void put(String key, Object value) {
        map.put(key, value);
    }

    public boolean isTopic(String key) {
        return topic.compareTo(key) == 0;
    }
}

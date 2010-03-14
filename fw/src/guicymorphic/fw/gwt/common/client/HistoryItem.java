package guicymorphic.fw.gwt.common.client;

import com.google.gwt.user.client.History;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for working with parameters in history.
 *
 * @author Alen Vrecko
 */
public class HistoryItem implements Serializable{

    private String id;
    private Map<String, String> parameters;

    HistoryItem() {} // for serialization support

    public HistoryItem(String id, Map<String,String> parameters) {
        this.id = id;
        this.parameters = parameters;
    }

    public HistoryItem(String id) {
        this.id=id;
        parameters = null;
    }

    @Override
    public String toString() {
        return toString(id, parameters);
    }

    public static String toString(String id, Map<String, String> parameters) {
       if (parameters == null || parameters.size() == 0) {
            return id;
        }

        StringBuffer buffer = new StringBuffer(id).append("?");

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        // we are guaranteed that at least one element is present therefore the string must end with &
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }

    public static HistoryItem parse(String historyToken) {
        int question = historyToken.indexOf('?');
        if (question < 0) {
            return new HistoryItem(historyToken);
        } else {
            String id = historyToken.substring(0, question);
            HashMap<String, String> map = new HashMap<String, String>();
            for (String value : historyToken.substring(question+1).split("&")) {
                int split = value.indexOf("=");
                if (split < 0) {
                    throw new RuntimeException("Malformed History Item.");
                }
                map.put(value.substring(0, split), value.substring(split + 1));
            }
            return new HistoryItem(id,map);
        }
    }


   


    public String getParam(String property) {
        return parameters.get(property);
    }
    public String getId() {
        return id;
    }

    public Map<String, String> getParameters() {
        return new HashMap<String, String>(parameters);
    }

}

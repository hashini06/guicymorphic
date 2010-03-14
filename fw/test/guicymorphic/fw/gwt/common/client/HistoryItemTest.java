package guicymorphic.fw.gwt.common.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.History;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 14, 2010
 * Time: 2:56:13 PM
 * To change this template use File | Settings | File Templates.
 */
// must be JUnit 3 style test for GWT to work
public class HistoryItemTest extends GWTTestCase {

    public void testToString() throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", "14");
        map.put("size", "big");
        assertEquals("test?id=14&size=big", new HistoryItem("test", map).toString());
    }

    public void testParse() throws Exception {
        HistoryItem parsed = HistoryItem.parse("test?id=14&size=big");

        assertEquals("test", parsed.getId());
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", "14");
        map.put("size", "big");
        assertEquals(map, parsed.getParameters());
    }

//    public void testFireHistoryEvent1() throws Exception {
//        final ArrayList<String> events = new ArrayList<String>();
//        History.addValueChangeHandler(new ValueChangeHandler<String>() {
//            public void onValueChange(ValueChangeEvent<String> event) {
//                events.add(event.getValue());
//            }
//        });
//
//        HistoryItem.fireHistoryEvent("hello");
//
//        String fired = events.get(0);
//        assertEquals("hello", fired);
//    }
//
//    public void testFireHistoryEvent2() throws Exception {
//        final ArrayList<String> events = new ArrayList<String>();
//        History.addValueChangeHandler(new ValueChangeHandler<String>() {
//            public void onValueChange(ValueChangeEvent<String> event) {
//                events.add(event.getValue());
//            }
//        });
//
//        HistoryItem.fireHistoryEvent("hello", "a", "1");
//
//        assertEquals("hello?a=1", events.get(0));
//    }
//
//    public void testFireHistoryEvent3() throws Exception {
//        final ArrayList<String> events = new ArrayList<String>();
//        History.addValueChangeHandler(new ValueChangeHandler<String>() {
//            public void onValueChange(ValueChangeEvent<String> event) {
//                events.add(event.getValue());
//            }
//        });
//
//        HistoryItem.fireHistoryEvent("hello", "a", "1", "b", "2");
//
//        assertEquals("hello?a=1&b=2", events.get(0));
//    }
//
//    public void testFireHistoryEvent4() throws Exception {
//        final ArrayList<String> events = new ArrayList<String>();
//        History.addValueChangeHandler(new ValueChangeHandler<String>() {
//            public void onValueChange(ValueChangeEvent<String> event) {
//                events.add(event.getValue());
//            }
//        });
//
//        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
//        map.put("a", "1");
//        map.put("b", "2");
//        map.put("c", "3");
//        HistoryItem.fireHistoryEvent("hello", map);
//
//        assertEquals("hello?a=1&b=2&c=3", events.get(0));
//    }

    public void testEmptyParams() throws Exception {

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("a", "1");
        map.put("b", null);
        map.put("c", "");
        HistoryItem historyItem = new HistoryItem("hello", map);
        assertEquals("hello?a=1&b=null&c=", historyItem.toString());

        map.put("", "123");
        map.put(null, "456");
        historyItem = new HistoryItem("hello", map);

        assertEquals("hello?a=1&b=null&c=&=123&null=456", historyItem.toString());

    }

    @Override
    public String getModuleName() {
        return "guicymorphic.fw.gwt.common.guicymorphicSupportTest";
    }
}

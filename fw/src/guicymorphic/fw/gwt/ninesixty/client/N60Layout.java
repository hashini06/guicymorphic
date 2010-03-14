package guicymorphic.fw.gwt.ninesixty.client;

import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 13, 2010
 * Time: 9:54:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class N60Layout {

    public static FlowPanel createDiv(String style) {
        FlowPanel flowPanel = new FlowPanel();
        flowPanel.setStylePrimaryName(style);
        return flowPanel;
    }

     public static FlowPanel createDiv(String style,String id) {
        FlowPanel flowPanel = new FlowPanel();
        flowPanel.setStylePrimaryName(style);
        flowPanel.getElement().setId(id);
        return flowPanel;
    }

    public static FlowPanel clear() {
        return createDiv("clear");
    }
}

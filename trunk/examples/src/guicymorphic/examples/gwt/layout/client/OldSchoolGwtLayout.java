package guicymorphic.examples.gwt.layout.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 14, 2010
 * Time: 11:17:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class OldSchoolGwtLayout extends Composite {


    public OldSchoolGwtLayout() {

        HorizontalPanel panel = new HorizontalPanel();
        panel.add(LayoutExample.createSampleTable());
        VerticalPanel w = new VerticalPanel();
        w.add(new HTML(LayoutExample.loremIpsum()));
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.add(new HTML(LayoutExample.loremIpsum()));
        w.add(horizontalPanel);
        panel.add(w);
        initWidget(panel);
    }
}

package guicymorphic.examples.gwt.layout.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 13, 2010
 * Time: 10:45:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class GwtLayoutPanelsLayout extends Composite {
    private TabLayoutPanel p;


    public GwtLayoutPanelsLayout() {

        p = new TabLayoutPanel(1.5, Style.Unit.EM);
        p.add(new HTML(LayoutExample.loremIpsum()), "this");
        p.add(new HTML(LayoutExample.loremIpsum()), "that");
        p.add(new HTML(LayoutExample.loremIpsum()), "the other");
        p.addSelectionHandler(new SelectionHandler<Integer>() {
            public void onSelection(SelectionEvent<Integer> integerSelectionEvent) {
                p.onResize();
            }
        });
        initWidget(p);
    }


    public TabLayoutPanel get() {
         return p;
    }
}

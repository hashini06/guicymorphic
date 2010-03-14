package guicymorphic.examples.gwt.layout.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 13, 2010
 * Time: 9:44:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class LayoutExample implements EntryPoint {
    public void onModuleLoad() {

        Button nine60gs = new Button("960gs layout");

        nine60gs.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel panel = RootPanel.get("placeholder");
                panel.clear();
                panel.add(new NineSixtyProgrammaticLayout());
            }
        });

        Button gwtLayout = new Button("GWT LayoutPanels");
        gwtLayout.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get("placeholder").clear();
                GwtLayoutPanelsLayout w = new GwtLayoutPanelsLayout();
                RootPanel.get("placeholder").add(w);


            }
        });

        Button gwtOldLayout = new Button("GWT Old School layout");
        gwtOldLayout.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                RootPanel.get("placeholder").clear();
                OldSchoolGwtLayout w = new OldSchoolGwtLayout();
                RootPanel.get("placeholder").add(w);


            }
        });


        FlowPanel panel = new FlowPanel();
        panel.add(nine60gs);
        panel.add(gwtLayout);
        panel.add(gwtOldLayout);
        RootPanel.get("buttons").add(panel);


    }

    public static String loremIpsum() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In fringilla tempus nisl eu pellentesque. Integer tempor velit id massa suscipit eget auctor arcu rutrum. Aliquam vel neque non dolor semper rhoncus. Aliquam sagittis lorem at est bibendum viverra viverra dolor fermentum. Morbi neque erat, feugiat et molestie sed, faucibus sed diam. Vivamus accumsan justo in orci pulvinar laoreet. Proin suscipit tincidunt sem, eget cursus tortor egestas vel. Praesent ullamcorper purus sit amet ante pellentesque ullamcorper. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas ipsum ligula, sodales sed accumsan in, euismod quis justo. Fusce et augue elit, sed pellentesque nisl. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Phasellus a leo metus. Cras massa tellus, egestas ut euismod sit amet, rutrum at metus. Nulla facilisi. ";
    }

    public static FlexTable createSampleTable() {
        FlexTable flexTable = new FlexTable();
        for (int i = 0; i < 50; i++) {
            flexTable.setText(i, 0, "Entry nr. " + i);
        }
        return flexTable;
    }


}

package guicymorphic.examples.gwt.layout.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import guicymorphic.fw.gwt.ninesixty.client.N60Layout;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 13, 2010
 * Time: 10:41:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class NineSixtyProgrammaticLayout extends Composite{


    public NineSixtyProgrammaticLayout() {
         FlowPanel container = N60Layout.createDiv("container_12", "container");

        // left side there is the table
        FlowPanel toc = N60Layout.createDiv("grid_4", "toc");
        toc.add(LayoutExample.createSampleTable());

        // main form with main article, and 2 bottom articles with left having 2 more
        FlowPanel main = N60Layout.createDiv("grid_8", "main");
        FlowPanel topSubsection = N60Layout.createDiv("grid_8 alpha", "subsectionTop");
        topSubsection.getElement().setInnerText(LayoutExample.loremIpsum());
        main.add(topSubsection);
        topSubsection.add(N60Layout.clear());

        FlowPanel leftSubsection = N60Layout.createDiv("grid_4 alpha", "subsectionLeft");
        main.add(leftSubsection);

        FlowPanel leftTop = N60Layout.createDiv("grid_4 alpha omega", "subsectionLeftTop");
        leftTop.getElement().setInnerText(LayoutExample.loremIpsum());
        leftSubsection.add(leftTop);
        leftSubsection.add(N60Layout.clear());
        FlowPanel leftBottom = N60Layout.createDiv("grid_4 alpha omega", "subsectionLeftBottom");
        leftBottom.getElement().setInnerText(LayoutExample.loremIpsum());
        leftSubsection.add(leftBottom);
        leftSubsection.add(N60Layout.clear());
        FlowPanel rightSubsection = N60Layout.createDiv("grid_4 omega", "subsectionRight");
        rightSubsection.getElement().setInnerText(LayoutExample.loremIpsum());
        main.add(rightSubsection);
        main.add(N60Layout.clear());
        container.add(toc);
        container.add(main);
        container.add(N60Layout.clear());
        RootPanel.get().add(container);

        initWidget(container);
    }




}

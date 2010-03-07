package guicymorphic.webapp.contacts.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import guicymorphic.fw.gwt.data.HasValueClickHandlers;
import guicymorphic.fw.gwt.data.ValueClickHandler;
import guicymorphic.fw.gwt.smartgwt.EventModelWrapper;
import guicymorphic.webapp.contacts.client.presenter.ContactsPresenter;
import guicymorphic.webapp.contacts.shared.dto.ContactDisplayDto;

import java.util.ArrayList;

public class ContactsView extends Composite implements ContactsPresenter.Display {

    private final ToolStripButton add = new ToolStripButton();
    private final ToolStripButton delete = new ToolStripButton();
    private final ToolStrip toolStrip = new ToolStrip();
    private final ListGrid contactsGrid;

    public ContactsView() {
        add.setTitle("Add");
        toolStrip.addButton(add);
        delete.setTitle("Delete");
        toolStrip.addButton(delete);


        contactsGrid = new ListGrid();

        contactsGrid.setWidth(600);
        contactsGrid.setHeight(500);
        contactsGrid.setShowAllRecords(true);

        ListGridField displayName = new ListGridField("displayName", "Name", 300);
        contactsGrid.setFields(displayName);

        contactsGrid.setSelectionType(SelectionStyle.SIMPLE);
        contactsGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);

        contactsGrid.setData(new ListGridRecord[0]);


        VerticalPanel panel = new VerticalPanel();
        panel.add(toolStrip);
        panel.add(contactsGrid);
        initWidget(panel);
    }

    @Override
    public HasClickHandlers getAddButton() {
        return EventModelWrapper.wrap(add);
    }

    @Override
    public HasClickHandlers getDeleteButton() {
        return EventModelWrapper.wrap(delete);
    }

    @Override
    public HasValueClickHandlers<String> getList() {
        return new HasValueClickHandlers<String>() {
            @Override
            public HandlerRegistration addValueClickHandler(final ValueClickHandler<String> stringValueClickHandler) {
                RecordClickHandler clickHandler = new RecordClickHandler() {
                    @Override
                    public void onRecordClick(RecordClickEvent recordClickEvent) {
                        ListGridField field = recordClickEvent.getField();
                        if (field.getName().equals("displayName")) {
                            stringValueClickHandler.onValueClick(((ContactDetailsRecord) recordClickEvent.getRecord()).getId());
                        }
                    }
                };
                final HandlerRegistration registration = contactsGrid.addRecordClickHandler(clickHandler);

                return new HandlerRegistration() {
                    @Override
                    public void removeHandler() {
                        registration.removeHandler();
                    }
                };
            }
        };
    }

    @Override
    public void setData(ArrayList<ContactDisplayDto> data) {
        ContactDetailsRecord[] records = new ContactDetailsRecord[data.size()];
        int i = 0;
        for (ContactDisplayDto details : data) {
            records[i++] = new ContactDetailsRecord(details);
        }

        contactsGrid.setData(records);
    }


    @Override
    public ArrayList<String> getSelectedRows() {
        ListGridRecord[] selectedRecords = contactsGrid.getSelection();

        ArrayList<String> ids = new ArrayList<String>(selectedRecords.length);

        for (ListGridRecord record : selectedRecords) {
            ids.add(((ContactDetailsRecord) record).getId());
        }

        return ids;
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}

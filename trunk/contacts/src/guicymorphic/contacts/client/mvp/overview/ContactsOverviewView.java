package guicymorphic.contacts.client.mvp.overview;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import guicymorphic.contacts.shared.dto.ContactDisplayDto;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 14, 2010
 * Time: 4:06:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContactsOverviewView extends Composite implements ContactsOverviewPresenter.View{

    private final ToolStripButton add = new ToolStripButton();
    private final ToolStripButton delete = new ToolStripButton();
    private final ToolStrip toolStrip = new ToolStrip();
    private final ListGrid contactsGrid;

    private final ContactsOverviewPresenter presenter;

    @Inject
    public ContactsOverviewView(ContactsOverviewPresenter presenter) {
        this.presenter = presenter;
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


    public void setData(ArrayList<ContactDisplayDto> contacts) {
        ContactDetailsRecord[] records = new ContactDetailsRecord[contacts.size()];
        int i = 0;
        for (ContactDisplayDto details : contacts) {
            records[i++] = new ContactDetailsRecord(details);
        }

        contactsGrid.setData(records);
    }

    public void setTableText(String message) {
       // smart gwt has a lot of api to master don't know how to get it to display text in the table
    }


    public void go(HasWidgets container) {
        container.clear();
        container.add(this);
    }

    @Override
    protected void onLoad() {
        bind();
        presenter.bind(this);
    }

    private void bind() {
        delete.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                ListGridRecord[] selection = contactsGrid.getSelection();

                ArrayList<ContactDisplayDto> selected = new ArrayList<ContactDisplayDto>();

                for (ListGridRecord selectedRecord : selection) {
                    selected.add(((ContactDetailsRecord) selectedRecord).getDto());
                }
                presenter.deleteContacts(selected);
            }
        });

        add.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                presenter.addContact();
            }
        });

        contactsGrid.addRecordClickHandler(new RecordClickHandler() {
            public void onRecordClick(RecordClickEvent recordClickEvent) {
                ListGridField field = recordClickEvent.getField();
                if (field.getName().equals("displayName")) {
                    presenter.editSelected(((ContactDetailsRecord) recordClickEvent.getRecord()).getDto());
                }
            }
        });

    }
}

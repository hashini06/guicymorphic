package guicymorphic.contacts.client.mvp.editor;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import guicymorphic.contacts.shared.dto.ContactDto;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 14, 2010
 * Time: 4:06:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditorView extends Composite implements EditorPresenter.View{

     private final IButton saveButton = new IButton("Save");
    private final IButton cancelButton = new IButton("Cancel");
    private final TextItem firstName = new TextItem();
    private final TextItem lastName = new TextItem();
    private final TextItem emailItem = new TextItem();
    private ContactDto contact;
    private final EditorPresenter presenter;


    @Inject
    public EditorView(EditorPresenter presenter) {
        this.presenter = presenter;

        VLayout layout = new VLayout(20);

        final DynamicForm form = new DynamicForm();
        form.setWidth(250);

        firstName.setTitle("First name");
        lastName.setTitle("Last name");
        emailItem.setTitle("Email");


        form.setFields(firstName, lastName, emailItem);


        layout.addMember(form);
        HLayout hLayout = new HLayout(20);
        hLayout.addMember(saveButton);
        hLayout.addMember(cancelButton);
        layout.addMember(hLayout);

        initWidget(layout);
    }


    public void setContact(ContactDto contact) {
        this.contact = contact;
        firstName.setValue(contact.getFirstName());
        lastName.setValue(contact.getLastName());
        emailItem.setValue(contact.getEmailAddress());
    }

    public void go(HasWidgets panel) {
        panel.clear();
        panel.add(this);
    }

    @Override
    protected void onLoad() {
        bind();
        presenter.bind(this);
    }

    public EditorPresenter getPresenter() {
        return presenter;
    }

    private void bind() {
        saveButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                presenter.saveOrEdit(new ContactDto(contact != null? contact.getId() : null,(String)firstName.getValue(),(String)lastName.getValue(),(String)emailItem.getValue()));
            }
        });
        cancelButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                presenter.cancel();
            }
        });
    }
}

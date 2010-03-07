package guicymorphic.webapp.contacts.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import guicymorphic.fw.gwt.smartgwt.EventModelWrapper;
import guicymorphic.webapp.contacts.client.presenter.EditContactPresenter;

public class EditContactView extends Composite implements EditContactPresenter.Display {

    private final IButton saveButton = new IButton("Save");
    private final IButton cancelButton = new IButton("Cancel");
    private final TextItem firstName = new TextItem();
    private final TextItem lastName = new TextItem();
    private final TextItem emailItem = new TextItem();


    public EditContactView() {

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


    public HasValue<String> getFirstName() {
        return EventModelWrapper.wrap(firstName);
    }

    public HasValue<String> getLastName() {
        return EventModelWrapper.wrap(lastName);
    }

    public HasValue<String> getEmailAddress() {
        return EventModelWrapper.wrap(emailItem);
    }

    public HasClickHandlers getSaveButton() {
        return EventModelWrapper.wrap(saveButton);
    }

    public HasClickHandlers getCancelButton() {
        return EventModelWrapper.wrap(cancelButton);
    }

    public Widget asWidget() {
        return this;
    }
}

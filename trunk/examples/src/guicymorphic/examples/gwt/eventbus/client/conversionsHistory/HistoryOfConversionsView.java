package guicymorphic.examples.gwt.eventbus.client.conversionsHistory;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Mar 13, 2010
 * Time: 8:45:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class HistoryOfConversionsView extends Composite implements HistoryOfConversionsPresenter.View{
    private final HistoryOfConversionsPresenter presenter;
    private final FlexTable flexTable =  new FlexTable();


    public HistoryOfConversionsView(HistoryOfConversionsPresenter presenter) {
        this.presenter = presenter;
        flexTable.setText(0, 0, "C");
        flexTable.setText(0,1,"F");
        initWidget(flexTable);
    }

    public void add(String celsius, String fahrenheit) {
        int count = flexTable.getRowCount();
        flexTable.setText(count, 0, celsius);
        flexTable.setText(count,1,fahrenheit);
    }

    @Override
    protected void onLoad() {
        presenter.bind(this);
    }
}

package guicymorphic.fw.gwt.common.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.BaseListenerWrapper;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.impl.HistoryImpl;

import java.util.HashMap;

/**
 * Interface over {@link com.google.gwt.user.client.History} to allow for easier testing and dependency injection.
 *
 * @author Alen Vrecko
 */
public interface BrowserHistory {

  /**
   * Adds a {@link com.google.gwt.event.logical.shared.ValueChangeEvent} handler
   * to be informed of changes to the browser's history stack.
   *
   * @param handler the handler
   * @return the registration used to remove this value change handler
   */
   HandlerRegistration addValueChangeHandler(
      ValueChangeHandler<String> handler);

  /**
   * Programmatic equivalent to the user pressing the browser's 'back' button.
   *
   * Note that this does not work correctly on Safari 2.
   */
   void back();

    /**
   * Fire
   * {@link ValueChangeHandler#onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent)}
   * events with the current history state. This is most often called at the end
   * of an application's
   * {@link com.google.gwt.core.client.EntryPoint#onModuleLoad()} to inform
   * history handlers of the initial application state.
   */
  void fireCurrentHistoryState();

    /**
   * Programmatic equivalent to the user pressing the browser's 'forward'
   * button.
   */
   void forward();

  /**
   * Gets the current history token. The handler will not receive a
   * {@link ValueChangeHandler#onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent)}
   * event for the initial token; requiring that an application request the
   * token explicitly on startup gives it an opportunity to run different
   * initialization code in the presence or absence of an initial token.
   *
   * @return the initial token, or the empty string if none is present.
   */
  String getToken();

    /**
   * Adds a new browser history entry. In hosted mode, the 'back' and 'forward'
   * actions are accessible via the standard Alt-Left and Alt-Right keystrokes.
   * Calling this method will cause
   * {@link ValueChangeHandler#onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent)}
   * to be called as well.
   *
   * @param historyToken the token to associate with the new history item
   */
  void newItem(String historyToken);

    /**
   * Adds a new browser history entry. In hosted mode, the 'back' and 'forward'
   * actions are accessible via the standard Alt-Left and Alt-Right keystrokes.
   * Calling this method will cause
   * {@link ValueChangeHandler#onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent)}
   * to be called as well if and only if issueEvent is true.
   *
   * @param historyToken the token to associate with the new history item
   * @param issueEvent true if a
   *          {@link ValueChangeHandler#onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent)}
   *          event should be issued
   */
  void newItem(String historyToken, boolean issueEvent);

     void newItem(HistoryItem item);

     void newItem(String id, HashMap<String, String> params);

     void newItem(String id,String key,String value);

     void newItem(String id,String key1,String value1,String key2,String value2);


}

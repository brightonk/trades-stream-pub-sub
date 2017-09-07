package org.trades.api;

/**
 *
 * @author Brighton Kukasira <brighton.kukasira@gmail.com>
 */
public interface EventSystem {

  public void subscribe(Class<?> aClass, Subscriber<?> subscriber);

  public void unsubscribe(Class<?> aClass, Subscriber<?> subscriber);

}

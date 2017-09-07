package org.trades.api;

/**
 *
 * @author Brighton Kukasira <brighton.kukasira@gmail.com>
 * @param <T>
 */
public interface Subscriber<T> {

  /**
   * This method is called when a trade message is received from the streaming
   * source.
   *
   * @param message a message received from the stream source
   */
  public void onMessage(T message);
}

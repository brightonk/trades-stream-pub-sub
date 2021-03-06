package org.trades.largest;

import org.trades.api.Subscriber;
import org.trades.model.Trade;

/**
 * This class subscribes to trade stream and stores the largest trade.
 *
 * @author Brighton Kukasira <brighton.kukasira@gmail.com>
 */
public class LargestTradeSubscriber implements Subscriber<Trade> {

  public LargestTradeSubscriber() {
  }

  /**
   * Provides a key used for grouping or retrieving. In future the key may
   * change, in that case it is easier to change the key in one place in this
   * method.
   *
   * @param trade
   * @return
   */
  public String getKey(final Trade trade) {
    return trade.getSymbol();
  }

  /**
   * This method is called when a trade message is received from the streaming
   * source.
   *
   * @param trade a trade just received
   */
  @Override
  public void onMessage(final Trade trade) {
    if (trade == null) {
      return;
    }
    final String key = getKey(trade);
    LargestTradeAccumulator.getInstance(key).accept(trade);
  }

  /**
   * returns the largest trade for the given symbol.
   *
   * @param symbol the symbol used to retrieve the largest trade
   * @return the largest trade
   */
  public Trade getLargestTrade(String symbol) {
    return LargestTradeAccumulator.getInstance(symbol).getLargestTrade();
  }
}

package org.trades.count;

import org.trades.api.Subscriber;
import org.trades.model.Trade;

/**
 *
 * @author Brighton Kukasira <brighton.kukasira@gmail.com>
 */
public class TradeCountSubscriber implements Subscriber<Trade> {

  public TradeCountSubscriber() {
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
    TradeCountAccumulator.getInstance(key).accept(trade);
  }

  /**
   * returns the most recent value for the given symbol and flag.
   *
   * @param symbol the symbol used to retrieve the count
   * @param flag the flag used to retrieve the count
   * @return the count of trades with given flag
   */
  public long getTradeCount(String symbol, String flag) {
    return TradeCountAccumulator.getInstance(symbol).getTradeCount(flag);
  }
}

package org.trades.largest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.trades.model.Trade;

/**
 *
 * @author Brighton Kukasira <brighton.kukasira@gmail.com>
 */
public class LargestTradeAccumulator implements Consumer<Trade> {

  private static final ConcurrentHashMap<String, LargestTradeAccumulator> INSTANCES
          = new ConcurrentHashMap<>(0);

  public static LargestTradeAccumulator getInstance(String key) {
    LargestTradeAccumulator instance;
    synchronized (LargestTradeAccumulator.class) {
      instance = INSTANCES.get(key);
      if (instance == null) {
        instance = new LargestTradeAccumulator();
        INSTANCES.put(key, instance);
      }
    }
    return instance;
  }
  // locks on the symbol level so that it does not lock the whole system.
  private final Object LOCK = new Object();
  private Trade largestTrade;

  private LargestTradeAccumulator() {
  }

  /**
   * returns the largest trade in this accumulator
   *
   * @return the largest trade
   */
  public Trade getLargestTrade() {
    synchronized (LOCK) {
      return largestTrade;
    }
  }

  @Override
  public void accept(Trade trade) {
    synchronized (LOCK) {
      if (largestTrade == null) {
        largestTrade = trade;
        return;
      }
      if (largestTrade.getSize() < trade.getSize()) {
        largestTrade = trade;
      }
    }
  }
}

package org.trades.count;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.trades.model.Trade;

/**
 *
 * @author Brighton Kukasira <brighton.kukasira@gmail.com>
 */
public class TradeCountAccumulator implements Consumer<Trade> {

  private static final ConcurrentHashMap<String, TradeCountAccumulator> INSTANCES
          = new ConcurrentHashMap<>(0);
  private static final String COMMA = ",";

  public static TradeCountAccumulator getInstance(String key) {
    TradeCountAccumulator instance;
    synchronized (TradeCountAccumulator.class) {
      instance = INSTANCES.get(key);
      if (instance == null) {
        instance = new TradeCountAccumulator();
        INSTANCES.put(key, instance);
      }
    }
    return instance;
  }
  // locks on the symbol level so that it does not lock the whole system.
  private final Object LOCK = new Object();
  private final ConcurrentHashMap<String, Long> flags;

  private TradeCountAccumulator() {
    this.flags = new ConcurrentHashMap<>(0);
  }

  /**
   * returns the trade count in this accumulator for the given flag
   *
   * @param flag the given flag
   * @return the trade count
   */
  public long getTradeCount(String flag) {
    if (flag == null) {
      return 0L;
    }
    synchronized (LOCK) {
      Long count = flags.get(flag);
      return count == null ? 0 : count;
    }
  }

  @Override
  public void accept(Trade trade) {
    synchronized (LOCK) {
      for (String flag : trade.getFlags().split(COMMA)) {
        incrementCount(flag);
      }
    }
  }

  private void incrementCount(String flag) {
    Long previousValue = flags.putIfAbsent(flag, 1L);
    if (previousValue != null) {
      flags.putIfAbsent(flag, previousValue + 1);
    }
  }
}

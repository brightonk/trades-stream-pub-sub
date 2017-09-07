package org.trades.average;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.trades.model.Trade;

/**
 *
 * @author Brighton Kukasira <brighton.kukasira@gmail.com>
 */
public class AveragePriceAccumulator implements Consumer<Trade> {

  private static final ConcurrentHashMap<String, AveragePriceAccumulator> INSTANCES
          = new ConcurrentHashMap<>(0);

  /**
   * returns a singleton instance of accumulator for the given key. If non
   * exists, a new accumulator will be created.
   *
   * @param key the key used to retrieve the accumulator
   * @return the accumulator
   */
  public static AveragePriceAccumulator getInstance(final String key) {
    AveragePriceAccumulator instance;
    synchronized (AveragePriceAccumulator.class) {
      instance = INSTANCES.get(key);
      if (instance == null) {
        instance = new AveragePriceAccumulator();
        INSTANCES.put(key, instance);
      }
    }
    return instance;
  }
  // locks on the symbol level so that it does not lock the whole system.
  private final Object LOCK = new Object();
  //internal variables not exposed to the world by any getter or setter
  private long cumulativeSize;
  //internal variables not exposed to the world by any getter or setter
  private double cumulativeValue;
  private double averagePrice;

  private AveragePriceAccumulator() {
  }

  /**
   * returns the average price in this accumulator
   *
   * @return the average trade
   */
  public double getAveragePrice() {
    synchronized (LOCK) {
      return averagePrice;
    }
  }

  @Override
  public void accept(final Trade trade) {
    synchronized (LOCK) {
      cumulativeSize += trade.getSize();
      cumulativeValue += trade.getPrice() * trade.getSize();
      averagePrice = cumulativeValue / cumulativeSize;
    }
  }
}

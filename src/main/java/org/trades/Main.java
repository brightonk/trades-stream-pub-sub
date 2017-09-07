package org.trades;

import java.util.logging.Logger;
import org.trades.api.Autowired;
import org.trades.api.EventSystem;
import org.trades.api.Subscriber;
import org.trades.average.AveragePriceSubscriber;
import org.trades.count.TradeCountSubscriber;
import org.trades.largest.LargestTradeSubscriber;
import org.trades.model.Trade;

/**
 *
 * @author Brighton Kukasira <brighton.kukasira@gmail.com>
 */
public class Main {

  private static final Logger LOG = Logger.getLogger(Main.class.getName());

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Main app = new Main();
    try {
      app.start();
    } finally {
      app.stop();
    }
  }
  private final Subscriber<Trade> averagePrice;
  private final Subscriber<Trade> largestTrade;
  private final Subscriber<Trade> tradeCount;

  /**
   * The source variable provides access to the stream of trades. Logic of the
   * trade source is outside the scope of this project.
   * <pre>
   * Assumption:
   * - the source will be injected and it will stream trades to subscribed
   *   classes.
   * - event system will call the subscriber's onMessage method when a new trade is received.
   * </pre>
   */
  @Autowired
  private EventSystem source;

  public Main() {
    this.tradeCount = new TradeCountSubscriber();
    this.largestTrade = new LargestTradeSubscriber();
    this.averagePrice = new AveragePriceSubscriber();
  }

  private void start() {
    source.subscribe(Trade.class, averagePrice);
    source.subscribe(Trade.class, largestTrade);
    source.subscribe(Trade.class, tradeCount);
  }

  private void stop() {
    source.unsubscribe(Trade.class, averagePrice);
    source.unsubscribe(Trade.class, largestTrade);
    source.unsubscribe(Trade.class, tradeCount);
  }
}

package org.trades.largest;

import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.trades.model.Trade;

/**
 *
 * @author Brighton Kukasira <brighton.kukasira@gmail.com>
 */
public class LargestTradeAccumulatorTest {

  private static final Logger LOGGER = Logger.getLogger(LargestTradeAccumulatorTest.class.getName());

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  private Trade trade1;
  private Trade trade2;
  private Trade trade3;

  public LargestTradeAccumulatorTest() {
  }

  @Before
  public void setUp() {
    trade1 = new Trade(1, "XYZ LN", 200.01, 968, "Z", 1481107485791L);
    trade2 = new Trade(2, "123 LN", 98.60, 746, "X", 1481107485791L);
    trade3 = new Trade(3, "XYZ LN", 200.00, 1000, "A,B", 1481107485790L);
  }

  @After
  public void tearDown() {
    trade1 = null;
    trade2 = null;
    trade3 = null;
  }

  /**
   * Test of getInstance method, of class LargestTradeAccumulator.
   */
  @Test
  public void testGetInstance() {
    LOGGER.info("getInstance");
    String key = trade1.getSymbol();
    LargestTradeAccumulator expResult = LargestTradeAccumulator.getInstance(key);
    LargestTradeAccumulator result = LargestTradeAccumulator.getInstance(key);
    assertEquals(expResult, result);
  }

  /**
   * Test of getLargestTrade method, of class LargestTradeAccumulator.
   */
  @Test
  public void testGetLargestTrade() {
    LOGGER.info("getLargestTrade");
    LargestTradeAccumulator instance
            = LargestTradeAccumulator.getInstance(trade1.getSymbol());
    instance.accept(trade1);
    instance.accept(trade3);
    Trade expResult = trade3;
    Trade result = instance.getLargestTrade();
    assertEquals(expResult, result);
  }

  /**
   * Test of accept method, of class LargestTradeAccumulator.
   */
  @Test
  public void testAccept() {
    LOGGER.info("accept");
    LargestTradeAccumulator instance
            = LargestTradeAccumulator.getInstance(trade1.getSymbol());
    instance.accept(trade1);
    // test that method completes without exception
  }

}

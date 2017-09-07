package org.trades.count;

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
public class TradeCountAccumulatorTest {

  private static final Logger LOGGER = Logger.getLogger(TradeCountAccumulatorTest.class.getName());

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  private Trade trade1;
  private Trade trade2;
  private Trade trade3;
  private Trade trade4;

  public TradeCountAccumulatorTest() {
  }

  @Before
  public void setUp() {
    trade1 = new Trade(1, "XYZ LN", 200.01, 968, "Z", 1481107485791L);
    trade2 = new Trade(2, "123 LN", 98.60, 746, "X", 1481107485791L);
    trade3 = new Trade(3, "XYZ LN", 200.00, 1000, "A,B", 1481107485790L);
    trade4 = new Trade(4, "XYZ LN", 200.01, 967, "", 1481107485790L);
  }

  @After
  public void tearDown() {
    trade1 = null;
    trade2 = null;
    trade3 = null;
    trade4 = null;
  }

  /**
   * Test of getInstance method, of class TradeCountAccumulator.
   */
  @Test
  public void testGetInstance() {
    LOGGER.info("getInstance");
    String key = trade1.getSymbol();
    TradeCountAccumulator expResult = TradeCountAccumulator.getInstance(key);
    TradeCountAccumulator result = TradeCountAccumulator.getInstance(key);
    assertEquals(expResult, result);
  }

  /**
   * Test of getTradeCount method, of class TradeCountAccumulator.
   */
  @Test
  public void testGetTradeCount() {
    LOGGER.info("getTradeCount");
    String key = trade1.getSymbol();
    TradeCountAccumulator instance = TradeCountAccumulator.getInstance(key);
    instance.accept(trade1);
    instance.accept(trade3);
    instance.accept(trade4);

    String existingFlag = trade1.getFlags();
    String nonExistingFlag = trade2.getFlags();
    String nullFlag = null;
    String emptyFlag = "";
    // existing flag
    long result1 = instance.getTradeCount(existingFlag);
    assertEquals(1L, result1);
    // non-existing flag
    long result2 = instance.getTradeCount(nonExistingFlag);
    assertEquals(0L, result2);
    // null flag
    long result3 = instance.getTradeCount(nullFlag);
    assertEquals(0L, result3);
    // empty flag
    long result4 = instance.getTradeCount(emptyFlag);
    assertEquals(1L, result4);
  }

  /**
   * Test of accept method, of class TradeCountAccumulator.
   */
  @Test
  public void testAccept() {
    LOGGER.info("accept");
    String key = trade1.getSymbol();
    TradeCountAccumulator instance = TradeCountAccumulator.getInstance(key);
    instance.accept(trade1);
    // test that method completes without exception
  }
}

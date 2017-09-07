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
public class LargestTradeSubscriberTest {

  private static final Logger LOGGER = Logger.getLogger(LargestTradeSubscriberTest.class.getName());

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  private Trade trade1;
  private Trade trade2;
  private Trade trade3;

  public LargestTradeSubscriberTest() {
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
   * Test of getKey method, of class LargestTradeSubscriber.
   */
  @Test
  public void testGetKey() {
    LOGGER.info("getKey");
    Trade trade = new Trade(1, "XYZ LN", 200.01, 968, "Z", 1481107485791L);
    LargestTradeSubscriber instance = new LargestTradeSubscriber();
    String expResult = "XYZ LN";
    String result = instance.getKey(trade);
    assertEquals(expResult, result);
  }

  /**
   * Test of onMessage method, of class LargestTradeSubscriber.
   */
  @Test
  public void testOnMessage() {
    LOGGER.info("onMessage");
    Trade trade = new Trade(1, "XYZ LN", 200.01, 968, "Z", 1481107485791L);
    LargestTradeSubscriber instance = new LargestTradeSubscriber();
    instance.onMessage(trade);
  }

  /**
   * Test of getLargestTrade method, of class LargestTradeSubscriber.
   */
  @Test
  public void testGetLargestTrade() {
    LOGGER.info("getLargestTrade");
    LargestTradeSubscriber instance = new LargestTradeSubscriber();
    instance.onMessage(trade1);
    instance.onMessage(trade2);
    instance.onMessage(trade3);
    Trade result1 = instance.getLargestTrade("XYZ LN");
    assertEquals(trade3, result1);
    Trade result2 = instance.getLargestTrade("123 LN");
    assertEquals(trade2, result2);
  }
}

package org.trades.average;

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
public class AveragePriceSubscriberTest {

  private static final Logger LOGGER = Logger.getLogger(AveragePriceSubscriberTest.class.getName());

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  private Trade trade1;
  private Trade trade2;
  private Trade trade3;

  public AveragePriceSubscriberTest() {
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
   * Test of getKey method, of class AveragePriceSubscriber.
   */
  @Test
  public void testGetKey() {
    LOGGER.info("getKey");
    AveragePriceSubscriber instance = new AveragePriceSubscriber();
    String expResult = trade1.getSymbol();
    String result = instance.getKey(trade1);
    assertEquals(expResult, result);
  }

  /**
   * Test of onMessage method, of class AveragePriceSubscriber.
   */
  @Test
  public void testOnMessage() {
    LOGGER.info("onMessage");
    AveragePriceSubscriber instance = new AveragePriceSubscriber();
    instance.onMessage(trade1);
    instance.onMessage(trade2);
    instance.onMessage(trade3);
  }

  /**
   * Test of getAveragePrice method, of class AveragePriceSubscriber.
   */
  @Test
  public void testGetAveragePrice() {
    LOGGER.info("getAveragePrice");
    String symbol = trade1.getSymbol();
    AveragePriceSubscriber instance = new AveragePriceSubscriber();
    instance.onMessage(trade1);
    instance.onMessage(trade2);
    instance.onMessage(trade3);
    double expResult = 200.00;
    double result = instance.getAveragePrice(symbol);
    assertEquals(expResult, result, 0.01);
  }

}

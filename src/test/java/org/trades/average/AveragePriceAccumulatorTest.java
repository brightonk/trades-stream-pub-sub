package org.trades.average;

import java.io.IOException;
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
public class AveragePriceAccumulatorTest {

  private static final Logger LOGGER = Logger.getLogger(AveragePriceAccumulatorTest.class.getName());

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  private Trade trade1;
  private Trade trade2;
  private Trade trade3;

  public AveragePriceAccumulatorTest() {
  }

  @Before
  public void setUp() throws IOException {
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
   * Test of getInstance method, of class AveragePriceAccumulator.
   */
  @Test
  public void testGetInstance() {
    LOGGER.info("getInstance");
    String key = trade1.getSymbol();
    AveragePriceAccumulator expResult = AveragePriceAccumulator.getInstance(key);
    AveragePriceAccumulator result = AveragePriceAccumulator.getInstance(key);
    assertEquals(expResult, result);
  }

  /**
   * Test of getAveragePrice method, of class AveragePriceAccumulator.
   */
  @Test
  public void testGetAveragePrice() {
    LOGGER.info("getAveragePrice");
    String key = trade1.getSymbol();
    AveragePriceAccumulator instance = AveragePriceAccumulator.getInstance(key);
    instance.accept(trade1);
    instance.accept(trade3);
    double expResult = 200.00;
    double result = instance.getAveragePrice();
    assertEquals(expResult, result, 0.01);
  }

  /**
   * Test of accept method, of class AveragePriceAccumulator.
   */
  @Test
  public void testAccept() {
    LOGGER.info("accept");
    String key = trade1.getSymbol();
    AveragePriceAccumulator instance = AveragePriceAccumulator.getInstance(key);
    instance.accept(trade1);
    // test that method completes without exception
  }
}

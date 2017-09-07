package org.trades.model;

import java.io.Serializable;

/**
 *
 * @author Brighton Kukasira <brighton.kukasira@gmail.com>
 */
public class Trade implements Serializable {

  private static final long serialVersionUID = 1L;
  private final long id;
  private final long timestamp;
  private final String symbol;
  private final double price;
  private final long size;
  private final String flags;

  public Trade(long id, String symbol, double price, long size, String flags,
          long timestamp) {
    this.id = id;
    this.symbol = symbol;
    this.price = price;
    this.size = size;
    this.flags = flags;
    this.timestamp = timestamp;
  }

  public long getId() {
    return id;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getSymbol() {
    return symbol;
  }

  public double getPrice() {
    return price;
  }

  public long getSize() {
    return size;
  }

  public String getFlags() {
    return flags;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (!(o instanceof Trade)) {
      return false;
    }
    return id == ((Trade) o).getId();
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
    return hash;
  }

  @Override
  public String toString() {
    return "Trade[id:" + id + ", symbol:" + symbol + ", price:" + price
            + ", size:" + size + ", flags:" + flags
            + ", timestamp:" + timestamp + "]";
  }
}

package po.drink;
import po.virtual.*;

public class CoffeeCup {
  private int _temperature;
  private int _quantityOfCoffee;
  private final int _capacity;

  public CoffeeCup(int temperature, int quantity, int cap) {
    _temperature = temperature;
    _capacity = cap;
    if (quantity > cap)
      _quantityOfCoffee = cap;
    else
      _quantityOfCoffee = quantity;
  }

  public int getTemperature() {
    return _temperature;
  }

  public void drink() throws EmptyCupException {
    if (_quantityOfCoffee == 0)
      throw new EmptyCupException();

    _quantityOfCoffee = 0;
  }
}
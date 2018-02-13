package cars;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class RemoteCarBase implements CarBase {
	private LinkedList<RemoteCar> cars = new LinkedList<>();

	@Override
	public Car registerCar(String model, int year, Money price) throws RemoteException {
		RemoteCar car = new RemoteCar(model, year, price);
		cars.add(car);
		return car;
	}

	@Override
	public List<Car> getAllCars() throws RemoteException {
		LinkedList<Car> list = new LinkedList<Car>();
		list.addAll(cars);
		return list;
	}

	@Override
	public void removeCar(Car car) throws RemoteException {
		cars.remove(car);
	}
}

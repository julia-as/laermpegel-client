package laerm.client.app;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import de.htw.vt.Sensor;
import de.htw.vt.SensorObserver;
import org.htw.fiw.FrontendServer;


public class LaermClient extends UnicastRemoteObject implements SensorObserver {

	LaermClient client;
	static Sensor sensor0;
	static Sensor sensor1;
	static Sensor sensor2;
	static Sensor sensor3;
	static FrontendServer frontend;



	public LaermClient() throws RemoteException {}

	public static void main(String[] args)  {

		try {
			LaermClient client = new LaermClient();
			sensor0 = (Sensor)Naming.lookup("rmi://localhost:1099/sensors/0");
			sensor0.register(client);
			System.out.println(sensor0.writeToConsole());

			frontend = (FrontendServer)Naming.lookup("rmi://localhost:9876/FrontendServer");
			Double value = Double.valueOf(sensor0.getValue());
			frontend.receiveSensor(sensor0.getX(), sensor0.getY());
			frontend.receiveValue(value);



//			sensor1 = (Sensor)Naming.lookup("rmi://localhost:1099/sensors/1");
//			sensor0.register(client);
//			System.out.println(sensor1.writeToConsole());
//
//			sensor2 = (Sensor)Naming.lookup("rmi://localhost:1099/sensors/2");
//			sensor0.register(client);
//			System.out.println(sensor2.writeToConsole());
//
//			sensor3 = (Sensor)Naming.lookup("rmi://localhost:1099/sensors/3");
//			sensor0.register(client);
//			System.out.println(sensor3.writeToConsole());

		} 
		catch (Exception e) { 
			e.printStackTrace();
		}
	}

	public void update(Sensor sensor, int value) throws RemoteException {
		this.sensor0.setValue(value);
		System.out.println(this.sensor0.writeToConsole());
		Double dValue = new Double(value);
		frontend.receiveValue(dValue);

	}
}

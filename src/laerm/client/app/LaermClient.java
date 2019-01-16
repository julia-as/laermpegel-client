package laerm.client.app;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Observer;

import de.htw.vt.Sensor;
import de.htw.vt.SensorImpl;
import de.htw.vt.SensorObserver;


public class LaermClient extends UnicastRemoteObject implements SensorObserver {

	LaermClient client;
	static Sensor sensor0;
	static Sensor sensor1;
	static Sensor sensor2;
	static Sensor sensor3;

	public LaermClient() throws RemoteException {}

	public static void main(String[] args)  {

		try {
			LaermClient client = new LaermClient();
			sensor0 = (Sensor)Naming.lookup("rmi://localhost:1099/sensors/0");
			sensor0.register(client);
			System.out.println("client(this): " + client.toString());
			System.out.println(sensor0.writeToConsole());

//			sensor1 = new SensorImpl();
//			Naming.lookup("rmi://localhost:1099/sensors/1");
//			sensor0.register(client);
//			System.out.println(sensor1.toString());
//
//			sensor2 = new SensorImpl();
//			Naming.lookup("rmi://localhost:1099/sensors/2");
//			sensor0.register(client);
//			System.out.println(sensor2.toString());
//
//			sensor3 = new SensorImpl();
//			Naming.lookup("rmi://localhost:1099/sensors/3");
//			sensor0.register(client);
//			System.out.println(sensor3.toString());

		} 
		catch (Exception e) { 
			e.printStackTrace();
		}
	}

	public void update(int value) throws RemoteException {
		this.sensor0.setValue(value);
		System.out.println(this.sensor0.writeToConsole());
	}
}

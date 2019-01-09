package laerm.client.app;

import java.rmi.Naming;
import java.util.Observable;
import java.util.Observer;

import de.htw.vt.SensorImpl;


public class LaermClient implements Observer {

	LaermClient client;

	public LaermClient() {}

	public static void main(String[] args) {

		LaermClient client = new LaermClient();

		try {
			SensorImpl sensor0 = new SensorImpl();
			Naming.lookup("rmi://localhost:1099/sensors/0");
			sensor0.register(client);
			System.out.println(sensor0.getSensor());

			SensorImpl sensor1 = new SensorImpl();
			Naming.lookup("rmi://localhost:1099/sensors/1");
			sensor0.register(client);
			System.out.println(sensor1.getSensor());

			SensorImpl sensor2 = new SensorImpl();
			Naming.lookup("rmi://localhost:1099/sensors/2");
			sensor0.register(client);
			System.out.println(sensor2.getSensor());

			SensorImpl sensor3 = new SensorImpl();
			Naming.lookup("rmi://localhost:1099/sensors/3");
			sensor0.register(client);
			System.out.println(sensor3.getSensor());

		} 
		catch (Exception e) { 
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	//attach with subject to observe
	public void register(SensorImpl sensor){};
}

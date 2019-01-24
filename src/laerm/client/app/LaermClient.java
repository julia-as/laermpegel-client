package laerm.client.app;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import de.htw.vt.Sensor;
import de.htw.vt.SensorObserver;
import org.htw.fiw.FrontendServer;


public class LaermClient extends UnicastRemoteObject implements SensorObserver {

	LaermClient client;
	static Sensor sensor;
	static Sensor sensor0;
	static Sensor sensor1;
	static Sensor sensor2;
	static Sensor sensor3;
	static FrontendServer frontend;
	static int anzahlSensors = 3;
	static Map<Integer, Sensor> sensorMap = new HashMap<>();



	public LaermClient() throws RemoteException {}

	public static void main(String[] args)  {

		try {
			LaermClient client = new LaermClient();

			for (int i=0; i < anzahlSensors; i++){
				String rmiUrl = "rmi://localhost:1099/sensors/" + i;
				sensor = (Sensor) Naming.lookup(rmiUrl);
				sensor.register(client);
				System.out.println(sensor.writeToConsole());
				sensorMap.put(sensor.getId(), sensor);

				frontend = (FrontendServer)Naming.lookup("rmi://localhost:9876/FrontendServer");
				Double value = Double.valueOf(sensor.getValue());
				frontend.receiveSensor(sensor.getId(), sensor.getX(), sensor.getY());
				frontend.receiveValue(value);
			}

//			LaermClient client = new LaermClient();
//			sensor0 = (Sensor)Naming.lookup("rmi://localhost:1099/sensors/0");
//			sensor0.register(client);
//			System.out.println(sensor0.writeToConsole());

//			frontend = (FrontendServer)Naming.lookup("rmi://localhost:9876/FrontendServer");
//			Double value = Double.valueOf(sensor0.getValue());
//			frontend.receiveSensor(sensor0.getX(), sensor0.getY());
//			frontend.receiveValue(value);

		}
		catch (Exception e) { 
			e.printStackTrace();
		}
	}

	public void update(Sensor sensor, int value) throws RemoteException {
		Sensor sensorNew = this.sensorMap.get(sensor.getId());
		sensorNew.setValue(value);
		System.out.println(sensorNew.writeToConsole());
		Double dValue = new Double(value);
		//frontend.receiveValue(dValue);
		frontend.receiveNewValue(sensorNew.getId(), dValue);
	}
}

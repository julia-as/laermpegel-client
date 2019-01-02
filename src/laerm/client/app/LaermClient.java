package laerm.client.app;

import java.rmi.Naming;

import de.htw.vt.SensorModelImpl;


public class LaermClient {

	public static void main(String[] args) {

		try {
			SensorModelImpl sensor0 = new SensorModelImpl();
			Naming.lookup("rmi://localhost:1099/sensors/0");
			System.out.println(sensor0.getSensor());
			SensorModelImpl sensor1 = new SensorModelImpl();
			Naming.lookup("rmi://localhost:1099/sensors/1");
			System.out.println(sensor1.getSensor());
			SensorModelImpl sensor2 = new SensorModelImpl();
			Naming.lookup("rmi://localhost:1099/sensors/2");
			System.out.println(sensor2.getSensor());
			SensorModelImpl sensor3 = new SensorModelImpl();
			Naming.lookup("rmi://localhost:1099/sensors/3");
			System.out.println(sensor3.getSensor());
		} 
		catch (Exception e) { 
			e.printStackTrace();
		}
		
	}

}

package laerm.client.app;

import java.rmi.Naming;

import de.htw.vt.SensorModelImpl;


public class LaermClient {

	public static void main(String[] args) {

		try {
			SensorModelImpl sensor = new SensorModelImpl();
			Naming.lookup("rmi://localhost:1099/sensors/");
			System.out.println(sensor.getSensor());
		} 
		catch (Exception e) { 
			e.printStackTrace();
		}
		
	}

}

package laerm.client.app;

import de.htw.vt.FrontendServer;
import de.htw.vt.Sensor;
import de.htw.vt.SensorObserver;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;




public class LaermClient extends UnicastRemoteObject implements SensorObserver {

    static Sensor sensor;
    static FrontendServer frontend;
    static int anzahlSensors = 3;
    static Map<Integer, Sensor> sensorMap = new HashMap<>();
    static String[] sensorRmiArray;


    public LaermClient() throws RemoteException {
    }

    public static void main(String[] args) {

        try {
            LaermClient client = new LaermClient();
            String rmiUrl = "rmi://localhost:1099/sensors/";
            sensorRmiArray = Naming.list(rmiUrl);

            frontend = (FrontendServer) Naming.lookup("rmi://localhost:9876/FrontendServer");

            for (int i = 0; i < sensorRmiArray.length; i++) {
                System.out.println("sensorArray length: " + sensorRmiArray.length);
                System.out.println("naming list: " + sensorRmiArray[i]);

                sensor = (Sensor) Naming.lookup(sensorRmiArray[i]);
                sensor.register(client);
                System.out.println(sensor.writeToConsole());
                sensorMap.put(sensor.getId(), sensor);

                Double value = Double.valueOf(sensor.getValue());
                frontend.receiveSensor(sensor.getId(), sensor.getX(), sensor.getY());
                frontend.receiveValue(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Sensor sensor, int value) throws RemoteException {
        Sensor sensorNew = this.sensorMap.get(sensor.getId());
        sensorNew.setValue(value);
        System.out.println(sensorNew.writeToConsole());
        Double dValue = new Double(value);
        frontend.receiveNewValue(sensorNew.getId(), dValue);
    }
}

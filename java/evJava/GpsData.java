package evJava;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GpsData {
	public Date date; // 1. Fecha y 2. Hora
	public double latitude; // 3. Latitud
	public double longitude; // 4. Longitud
	public int direction; // 5. Dirección
	public int speed; // 6. Velocidad
	public boolean power; // 7. Energía del GPS
	public int satelliteCount; // 8. Cantidad de satélites
	public int gpsAge; // 9. Edad de señal GPS
	public boolean positionType; // 10. Tipo posición
	public int hdop; // 11. HDOP
	public boolean gprs; // 12. Conexión GPRS
	public int gsmStatus; // 13. Estado GSM
	public int signal; // 14. Nivel de señal
	public long odometer; // 15. Distancia acumulada
	public int inputs; // 16. Estado de entradas

    static GpsData parseGpsData(String gpsData) throws ParseException {
        GpsData gpsData1 = new GpsData();
        //fecha y hora
        String fechaYHora = gpsData.substring(0,12);
        SimpleDateFormat parseador=new SimpleDateFormat("ddMMyyHHmmss");
        gpsData1.date = parseador.parse(fechaYHora);
        //latitude
        String latitude = gpsData.substring(12,20);
        latitude = latitude.substring(0,3) + "." + latitude.substring(3);
        gpsData1.latitude = Double.parseDouble(latitude);
        //longitude
        String longitude = gpsData.substring(20,29);
        longitude = longitude.substring(0,4) + "." + longitude.substring(4);
        gpsData1.longitude = Double.parseDouble(longitude);
        //direction
        String direction = gpsData.substring(29,32);
        gpsData1.direction = Integer.parseInt(direction);
        //speed
        String speed = gpsData.substring(32,35);
        gpsData1.speed = Integer.parseInt(speed);
        //power
        String power = gpsData.substring(35,36);
        gpsData1.power = (power.equals("1"));
        //satelliteCount
        String satelliteCount = gpsData.substring(36,38);
        gpsData1.satelliteCount = Integer.parseInt(satelliteCount);
        //gpsAge
        String gpsAge = gpsData.substring(38,42);
        gpsData1.gpsAge = Integer.parseInt(gpsAge);
        //positionType
        String positionType = gpsData.substring(42,43);
        gpsData1.positionType = (positionType.equals("1"));
        //hdop
        String hdop = gpsData.substring(43,46);
        gpsData1.hdop = Integer.parseInt(hdop);
        //gprs
        String gprs = gpsData.substring(46,47);
        gpsData1.gprs = (gprs.equals("1"));
        //gsmStatus
        String gsmStatus = gpsData.substring(46,47);
        gpsData1.gsmStatus = Integer.parseInt(gsmStatus);
        //signal
        String signal = gpsData.substring(47,49);
        gpsData1.signal = Integer.parseInt(signal);
        //odometer
        String odometer = gpsData.substring(49,59);
        gpsData1.odometer = Integer.parseInt(odometer);
        //inputs
        String inputs = gpsData.substring(59);
        gpsData1.inputs =Integer.parseInt(inputs,16) ;
        return gpsData1;
    }
}

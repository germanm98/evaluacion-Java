package evJava;

import java.text.ParseException;

public class Frame {
	public String messageType; // 0E en el ejemplo
	public GpsData gpsData; // Datos GPS ver clase abajo
	public int eventId; // ID de evento
	public long batteryLevel; // Nivel de batería
	public long timer1; // Temporizador 1
	public long timer2; // Temporizador 2
	public long timer3; // Temporizador 3
	public long initialOdometer; // Metros iniciales
	public String messageId; // ID de mensaje (ID=125433)
	public String logNumber; // Número de log (#LOG:07A4)
	public int checksum; // ej: 4F

    static Frame parseFrame(String frame) throws ParseException {
        String[] dividedFrame = (frame.substring(1,(frame.length()-1))).split(","); //se quitan los delimitadores y se separa la cabecera del resto de la frame
        String messageType = dividedFrame[0].substring(dividedFrame[0].length()-2);
        String[] contentNchecksum = dividedFrame[1].split("\\*");
        String[] dividedContent = contentNchecksum[0].split(";");
        Frame parsedFrame = new Frame();
        parsedFrame.messageType = messageType;
        parsedFrame.gpsData = GpsData.parseGpsData(dividedContent[0]);
        parsedFrame.eventId = Integer.parseInt(dividedContent[1]);
        parsedFrame.batteryLevel = Long.parseLong(dividedContent[2]);
        parsedFrame.timer1 = Long.parseLong(dividedContent[3]);
        parsedFrame.timer2 = Long.parseLong(dividedContent[4]);
        parsedFrame.timer3 = Long.parseLong(dividedContent[5]);
        parsedFrame.initialOdometer = Long.parseLong(dividedContent[6]);
        parsedFrame.messageId = dividedContent[7];
        parsedFrame.logNumber= dividedContent[8];
        parsedFrame.checksum = Integer.parseInt(contentNchecksum[1],16);
        return parsedFrame;
    }

    static boolean isValidFrame(String frame){
        String patternFrame = "^>RUS[a-zA-Z0-9]{2},([0-2][0-9]|3[0-1])(0[1-9]|1[0-2])" +
                "(\\d{2})([0-1][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])(\\+|-\\d{7})(\\+|-\\d{8})\\d{6}(1|0)\\d{6}(1|0)" +
                "\\d{3}(1|0)\\d{13}[0-9a-fA-F]+;\\d{2};\\d{8};\\d{10};\\d{7};\\d{7};\\d{10};(ID=\\d+);" +
                "(#LOG:[a-zA-z0-9]+);\\*[0-9a-fA-F]{1,2}<$";
        return frame.matches(patternFrame);
    }
}

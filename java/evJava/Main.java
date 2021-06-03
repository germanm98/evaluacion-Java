package evJava;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;  

public class Main {
	public static void main(String[] args) throws Exception {
        String frame = ">RUS0E,260421180138-3298670-068855420000001110000100711200000000000F8;03;" +
                "04191439;0000000480;0000060;0000660;0000010000;ID=125433;#LOG:07A4;*4F<"; // Example
        if(Frame.isValidFrame(frame)){
			Frame parsedFrame = null;
            parsedFrame = Frame.parseFrame(frame);
            try {
            	String Jsonframe = objectToJsonString(parsedFrame);
                if(sendPostRequest(Jsonframe,"https://bosprgvc44.execute-api.us-east-1.amazonaws.com/default/trax")) {
                    System.out.println("Datos enviados exitosamente");
                } else {
                	 System.out.println("Error al enviar petición");
                }
            }  
            catch (IOException e) {  
                e.printStackTrace();  
            }  
        } else 
        	System.out.println("La trama es inválida");
        
    }
	
	public static String objectToJsonString(Object o) throws JsonProcessingException {
		ObjectMapper Obj = new ObjectMapper();
		String jsonString = Obj.writeValueAsString(o);  
		return jsonString;
	}
	
	public static boolean sendPostRequest(String json, String url) throws Exception {
		try {
			StringEntity entity = new StringEntity(json,ContentType.APPLICATION_FORM_URLENCODED);
	        HttpClient httpClient = HttpClientBuilder.create().build();
	        HttpPost request = new HttpPost(url);
	        request.setEntity(entity);
	        HttpResponse response = httpClient.execute(request);
	        System.out.println(response.getStatusLine().getStatusCode());
	        return response.getStatusLine().getStatusCode() == 200;

		} catch (Exception ex) {
			ex.getMessage();
		}
		return false;
	}
}

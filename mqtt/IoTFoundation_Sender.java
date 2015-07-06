package mqtt;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONArray;
import org.json.JSONObject;

public class IoTFoundation_Sender implements MqttCallback{
	public static void main(String[] args) throws MqttException, UnsupportedEncodingException{		
		new IoTFoundation_Sender().sendMessage();
	}
	
	public void sendMessage() throws UnsupportedEncodingException, MqttSecurityException, MqttException{
		JSONObject jsonObject = new JSONObject();		
        jsonObject.put("id", "iPhone0");
        jsonObject.put("ts", new Date().getTime());
        jsonObject.put("ax", Math.random()*40.0);
        jsonObject.put("ay",Math.random()*40.0);
        jsonObject.put("az", Math.random()*40.0);
        jsonObject.put("oa", Math.random()*40.0);
        jsonObject.put("ob",Math.random()*40.0);
        jsonObject.put("og", Math.random()*40.0);        
        JSONObject p1 = new JSONObject();
		p1.put("dn", "ds0001");
		p1.put("ts", new Date().getTime()+"");
		p1.put("longitude", "3.0");
		p1.put("latitude","3.0");
		p1.put("speed", "200.0");
		p1.put("azimuth", "3.0");
		p1.put("linkid", "200");		
		jsonObject.put("d", p1);
		System.out.println(jsonObject.toString());
		MqttMessage mess = new MqttMessage(jsonObject.toString().getBytes("utf-8"));	
		
		
		MqttClient mqttClient = new MqttClient("tcp://q8d4yd.messaging.internetofthings.ibmcloud.com:1883", "d:q8d4yd:tify-simu:tify-device-01234", new MemoryPersistence());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(true);
		options.setConnectionTimeout(0);
		options.setKeepAliveInterval(1000);
		options.setUserName("use-token-auth");
		options.setPassword("eZ3Ta)?qTCQ&!FPVm9".toCharArray());
		mqttClient.setCallback(this);
		mqttClient.connect(options);		
		mqttClient.publish("iot-2/evt/event_tify_001/fmt/tify", mess);		
		mqttClient.disconnect();	
		
	}

	@Override
	public void connectionLost(Throwable arg0) {
		System.out.println("disconnected");		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		System.out.println("message sent");		
	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {		
	}
}

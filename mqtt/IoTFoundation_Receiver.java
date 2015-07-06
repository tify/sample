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


public class IoTFoundation_Receiver implements MqttCallback{
	public static void main(String[] args) throws MqttException, UnsupportedEncodingException{			

		new IoTFoundation_Receiver().getEvent();
	}
	
	public void getEvent() throws MqttSecurityException, MqttException{
		MqttClient mqttClient = new MqttClient("tcp://q8d4yd.messaging.internetofthings.ibmcloud.com:1883", "a:q8d4yd:tify-001", new MemoryPersistence());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(true);
		options.setConnectionTimeout(0);
		options.setKeepAliveInterval(1000);
		options.setUserName("a-q8d4yd-xnmfx24x0c");
		options.setPassword("SLhS58q4?wPnej!+0(".toCharArray());
		mqttClient.connect(options);
		mqttClient.setCallback(this);
		mqttClient.subscribe("iot-2/type/tify-simu/id/tify-device-01234/evt/event_tify_001/fmt/tify");
		//mqttClient.disconnect();		
	}

	@Override
	public void connectionLost(Throwable arg0) {
		System.out.println("disconnected");			
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {		
	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		System.out.println("message received");
		System.out.println(arg1.toString()); 
		System.out.println("----------------");
	}
}

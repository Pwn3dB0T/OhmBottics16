import lejos.nxt.*;


public class DatenUS extends Thread {
	
	UltrasonicSensor USS = new UltrasonicSensor(SensorPort.S4);
	
	
	public void run (){
		
		while(true){
			System.out.println("UltraSonic: " + USS.getDistance());
		}
	}

}

import lejos.nxt.*;
public class WerteTS {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		TouchSensor TS = new TouchSensor (SensorPort.S3);
		
			while(true){
			if (TS.isPressed()){
				
				System.out.println("TS pressed");
			}
			}
			
		
			
	}

}

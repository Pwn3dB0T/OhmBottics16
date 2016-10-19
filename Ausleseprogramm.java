import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.*;

public class Ausleseprogramm {
	
	public static void main(String[] args) {
		
		
	System.out.println("WerteAuslese");
	System.out.println("-----------------");
	System.out.println("Left  : SB-Werte");
	System.out.println("Right : US-Werte");
	System.out.println("Enter : Info");
	System.out.println("Escape: Exit");
		
		
			while(true){
				
		
			
			if(Button.LEFT.isDown()){
				LCD.clear();
				Sensorboard	sb = new Sensorboard (SensorPort.S1);
				Thread dsb = new DatenSB();
				
				sb.setBrightness(255, 255);
				dsb.start();
				
				switch(Button.waitForAnyPress()){
				case Button.ID_ESCAPE:
					break;
					
				}
				
			}
				
			if(Button.RIGHT.isDown()){
				LCD.clear();
				Thread dus = new DatenUS();
				
				dus.start();
				
				switch(Button.waitForAnyPress()){
				case Button.ID_ESCAPE:
					break;
				}
			}
			
			
			if(Button.ENTER.isDown()){
				LCD.clear();
				System.out.println("Info");
				System.out.println("----------");
				System.out.println("v.0.1.1");
				System.out.println("Free Pages: " + NXT.getUserPages());
				System.out.println("Battery: " + Battery.getVoltage() + "v");
			}
			
			
			if(Button.ESCAPE.isDown()){
				break;
			}
				
			
		}	
	}
}

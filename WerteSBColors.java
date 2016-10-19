import lejos.nxt.*;
import lejos.util.Delay;
import lejos.util.Stopwatch;


public class WerteSBColors {
	
	
	Sensorboard SB = new Sensorboard (SensorPort.S1);
	Stopwatch sw = new Stopwatch();
	
	public void run (){
		while (true){
			LCD.drawString("FR: ", 1, 0);
			LCD.drawString("                   ", 0, 1);
			LCD.drawString("    ", 1, 2);
			LCD.drawInt(sw.elapsed(), 1, 2);
			sw.reset();
			
			LCD.drawString("    ", 1, 3);
			LCD.drawInt(sw.elapsed(), 1, 3);
			
			LCD.drawString("                 ", 0, 5);
			LCD.drawString("                 ", 0, 6);
			LCD.drawString("                 ", 0, 7);
			
			LCD.drawString("0:", 0, 5);
			LCD.drawInt((SB.getGreen()[0] + SB.getRed()[0])/2, 2, 5); //Links
			LCD.drawString("1:", 9, 5);
			LCD.drawInt((SB.getGreen()[1] + SB.getRed()[1])/2, 11, 5);
			LCD.drawString("2:", 0, 6);
			LCD.drawInt((SB.getGreen()[2] + SB.getRed()[2])/2, 2, 6);
			LCD.drawString("3:", 9, 6);
			LCD.drawInt((SB.getGreen()[3] + SB.getRed()[3])/2, 11, 6);
			LCD.drawString("4:", 0, 7);
			LCD.drawInt((SB.getGreen()[4] + SB.getRed()[4])/2, 2, 7);
			System.out.println("Refresh...");
			System.out.println("RX Data...");
			Delay.msDelay(400);
			LCD.clear();
			
		}
	}
}

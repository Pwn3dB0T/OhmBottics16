import lejos.nxt.*;
import lejos.util.Delay;
import lejos.util.Stopwatch;

public class WerteSB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sensorboard SB = new Sensorboard (SensorPort.S1);
		Stopwatch sw = new Stopwatch();

		SB.setBrightness(130, 130);
		SB.setSensorGain(16);
		SB.setTiming(1);
		while (true) {
			
			
			LCD.drawString("DeltaTime: " + (sw.elapsed()-70) + " ms", 1, 2);
			LCD.drawString("    ", 1, 3);
			sw.reset();
			LCD.drawString("                 ", 0, 5);
			LCD.drawString("                 ", 0, 6);
			LCD.drawString("                 ", 0, 7);
			
			LCD.drawString("0:", 0, 5);
			LCD.drawInt(SB.getClear()[0], 2, 5); //Links
			LCD.drawString("1:", 9, 5);
			LCD.drawInt(SB.getClear()[1], 11, 5);
			LCD.drawString("2:", 0, 6);
			LCD.drawInt(SB.getClear()[2], 2, 6);
			LCD.drawString("3:", 9, 6);
			LCD.drawInt(SB.getClear()[3], 11, 6);
			LCD.drawString("4:", 0, 7);
			LCD.drawInt(SB.getClear()[4], 2, 7);
		
			Delay.msDelay(70);
			LCD.clear();
		}
	}

}

import lejos.nxt.*;
import lejos.util.Delay;


public class DatenSB extends Thread {
	
	
	Sensorboard	sb = new Sensorboard (SensorPort.S1);

	
	public void run (){
		while (true){
			int SB_Clear[] = sb.getClear();
			int SB_Green[] = sb.getGreen();
			int SB_Blue[] = sb.getBlue();
			LCD.clear();
			System.out.println(" ");
			System.out.println("Left    : " + SB_Clear[0]);
			System.out.println("LeftMid : " + SB_Clear[1]);
			System.out.println("Middle  : " + SB_Clear[2]);
			System.out.println("RightMid: " + SB_Clear[3]);
			System.out.println("Right   : " + SB_Clear[4]);
			Delay.msDelay(700);
			LCD.clear();
			System.out.println("Refresh...");
			System.out.println("RX Data...");
			Delay.msDelay(400);
			LCD.clear();
			
		}
	}
}

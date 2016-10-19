
/** 
 *  Some Informations, first!
 * 
 *  Java Class to WIN at the German Open RoboCup 2017 Qualification in Hannover and for the German Cup.
 *  Created by Julius Gros (julius.electronics@icloud.com) and Nico Kreuzer.
 *  The EV3 Version was written by Julius Gros.
 *  License: Only with a approval, uttered by Julius Gros AND/OR Nico Kreuzer. (That is why, we use a copyright.)
 *  
 *  This Version (probably the 3rd) is only compatible with the NXT 2.0 (not for the NXT 1.0) Robot, created by Nico and Julius.
 *  For the 1st Version (NXT 1.0), reffer to Michi, Joshi and Julius.
 *  For the 2rd Version (NXT 2.0), reffer to Julius and Nico.
 *  For the EV3 Version BETA (v.0.2.7.EV3), reffer to Julius.
 *  For the EV3 Version (v.1.2.7.EV3), reffer to Julius Electronics
 *  
 *  This code is self-documenting (or I'm just too lazy to write comments)
 * 
 *  Special Thanks to Max for the SensorBoard Access-Code to access the SensorBoard.
 *	Special Thanks, too, to Morty for the great help.
 *	Also Special Thanks to Nico for to being a wounderful partner.
 *  Very, very special Thanks to the leJOS-Team for crating a wounderful JAVA-Codeing-API. (NOT for the EV3-API)
 *  Special Thanks to LEGO for creating the NXT.
 * 
 *  In the bottom, there are the "important" (:-) Informations.
 *  
 *  A few lines originate from Michi and Joshi (our old programm). 
 *  
 *  The EV3 Version (v.0.2.7.EV3 BETA, System Requirements: EV3 with leJOS, 0.9.0 beta), I am coding in the moment. 
 *  (Probably not, because the RoboCup is in a few weeks and the NXT Version is not finish...)
 *  
 *  SYSTEM REQUIREMENTS: 	LEGO NXT with leJOS NXJ 0.9.1
 *  						2 Motors
 *  						1 SensorBoard (Thanks to Max)
 *  						2 TouchSensors
 *  						1 UltrasonicSensor
 *  						and a few more things.
 *  
 *  
 *  (C) 2016 Julius Gros and Nico Kreuzer.
 *  
 *  
 *  v.3.4.0.1.NXT (of course for the NXT under leJOS 0.9.1) (Last Change: 24.09.2016 10:50:00)
 *  
 *  
 *  CHANGELOG:
 *  
 *  v.3.2.9.2.NXT (Last Change: 09.01.2016 15:50:00)
 *  v.3.3.0.0.NXT - v.3.3.9.9 (Last Change: RoboCup Qualification 2016)
 *  v.3.4.0.1.NXT (Last Change: 24.09.2016 10:50:00)
 *  
 *	
 *  
 */

/*WICHTIGE_INFOS________________________________________________________________________________________________________________________________

		 BELEGUNGS PORTS an NXT:
		 
		 SP.1: SensorBoard
		 SP.2: -/-
		 SP.3: TouchSensor
		 SP.4: UltraSonic
		 
______________________________________________________________________________________________________________________________________________
		 
		 AUSLESEMETHODEN:
		 
		 LightSensor an SensorMux: sm.readValue(Port);
		 LightSensor an NXT:       LightSensor.readValue();
		 SensorBoard:			   sb.getClear()
		
______________________________________________________________________________________________________________________________________________		
		
*/

// Now, lets code: :-)

import lejos.nxt.*; 
import lejos.util.Stopwatch;

public class Hauptprogramm {

//_______________________________________________________________________________________________________________________________________________
	
	private static Sensorboard SB; // SensorPort: 1
	private static UltrasonicSensor USS; // SensorPort: 4
	private static TouchSensor TS; //SensorPort: 3
	
	@SuppressWarnings("unused")
	private static Thread dsb;
	
	static Stopwatch sw = new Stopwatch();
	
	private static SuperMotor m1; //Links
	private static SuperMotor m2; //Rechts
	private static SuperMotor m3; //Greifer
	
//_______________________________________________________________________________________________________________________________________________
	
	private static int Drall_AWPRechts_Rechts = 0;
	private static int Drall_AWPRechts_Links = 0;
	@SuppressWarnings("unused")
	private static int Drall_Left_Links = 0; // Drall nach LinksKurve LINKS
	@SuppressWarnings("unused")
	private static int Drall_Left_Rechts = 0; // Drall nach LinksKurve nach RECHTS
	@SuppressWarnings("unused")
	private static int Drall_Right_Links = 0; //Drall nach RechtsKurve nach LINKS
	@SuppressWarnings("unused")
	private static int Drall_Right_Rechts = 0; //Drall nach RechtsKurve nach RECHTS
	
	
	private static int r = 20; //Urspruenglich 0			//Alle int r veraendern (Setze Drall RECHTS ueberall)
	private static int l = 0; //Urspruenglich 0				//Alle int l veraendern (Setze Drall LINKS ueberall)
	private static int MotorSpeed = 300;
	
	private static int SB_Gain=16;
	
//_______________________________________________________________________________________________________________________________________________
	
	private static int HelligkeitSBSide = 170; //Helligkeit der LED SensorBoard an den Seiten
	private static int HelligkeitSBCenter = 170; //Helligkeit der LED SensorBoard in der Mitte (Mittlere 3 LEDs)
	
//_______________________________________________________________________________________________________________________________________________
	
	//Config Int Farben:
	private static int black = 400; //mit 1 Nope Distanz:
	@SuppressWarnings("unused")
	private static int white = 10000; //Urspruenglich 40.000 //mit 1 Nope Distanz: //UNUSED
	@SuppressWarnings("unused")
	private static int alu = 1000; //mit 1 Nope Distanz\\

	
//_______________________________________________________________________________________________________________________________________________

//!! Funktionen	
/*	
	// Funktion Vorwaerts mit Speed-Param
	private static void DriveForward(int Speed) {
		m1.forward();
		m2.forward();
		m1.setSpeed(Speed);
		m2.setSpeed(Speed);
	}
	
	// Funktion Vorwaerts ohne Speed-Param
	private static void DriveForward() {
		m1.forward();
		m2.forward();
		m1.setSpeed(MotorSpeed);
		m2.setSpeed(MotorSpeed);
	}

	// Funktion Vorwaerts mit Drehwinkel
	private static void Forward(int Drehwinkel) {
		m1.rotate(Drehwinkel, true);
		m2.rotate(Drehwinkel);
	}
	
	//Funktion Forwaerts mit Param Left u. Right getrennt
	private static void DriveForwardAus (int Left, int Right ) {
		m1.forward();
		m2.forward();
		m1.setSpeed(Left);
		m2.setSpeed(Right);
	}
	
	// Funktion Rueckwaerts mit Drehwinkel
	private static void Back(int Drehwinkel) {
		m1.rotate(-Drehwinkel, true);
		m2.rotate(-Drehwinkel);
	}

	// Funktion Rechts mit Drehwinkel
	private static void Right(int Drehwinkel) {
		m1.rotate(Drehwinkel, true);
		m2.rotate(-Drehwinkel);
	}

	// Funktion Links mit Drehwinkel
	private static void Left(int Drehwinkel) {
		m1.rotate(-Drehwinkel, true);
		m2.rotate(Drehwinkel);
	}

	//Funktion Links mit Drehwinkel = 500
	private static void Left() {
		m1.rotate(-500, true);
		m2.rotate(500);
	}

	// Funktion Rechts mit Drehwinkel = 500
	private static void Right() {
		m1.rotate(500, true);
		m2.rotate(-500);
	}
	
	// Funktion Umdrehen mit Drehwinkel = 1000
	private static void Turn() {
		m1.rotate(-1000, true);
		m2.rotate(1000);
	}
	
	//Funktion Umdrehen mit Drehwinkel
	private static void Turn(int Drehwinkel) {
		m1.rotate(-Drehwinkel, true);
		m2.rotate(Drehwinkel);
	}
*/	
	private static void m1Speed(int Speed){
		m1.setSpeed(Speed);
	}
	
	private static void m2Speed(int Speed){
		m2.setSpeed(Speed);
	}

	
	@SuppressWarnings("unused")
	private static void RefreshSB(int Zahl){
		LCD.drawString("0:", 0, 5);
		LCD.drawInt((SB.getClear()[0]), 2, 5); //Links
		LCD.drawString("1:", 9, 5);
		LCD.drawInt((SB.getClear()[1]), 11, 5);
		LCD.drawString("2:", 0, 6);
		LCD.drawInt((SB.getClear()[2]), 2, 6);
		LCD.drawString("3:", 9, 6);
		LCD.drawInt((SB.getClear()[3]), 11, 6);
		LCD.drawString("4:", 0, 7);
		LCD.drawInt((SB.getClear()[4]), 2, 7);
	
	}
//_______________________________________________________________________________________________________________________________________________
	
//!! Programme	
	
	//Funktion Evakuierungsprogramm
	public static void Evakprogramm() {
			
			m1.rotate(100,true);
			m1Speed(90);
			m2.rotate(100);
			m2Speed(90);
			m1.rotate(-360,true);
			m2.rotate(360);
			m3.rotate(90);
			m1.rotate(100,true);
			m2.rotate(100);
			
			while(true) {
			
			if(USS.getDistance() < 50) {
				
				m1.rotate(-100,true);
				m2.rotate(-100);
				
				m1.rotate(800,true);
				m2.rotate(-800);
			
				m3.rotate(1000);
				
				m1.rotate(-1000,true);
				m2.rotate(-1000);
				
				m1.rotate(100,true);
				m2.rotate(100);
				
				m3.rotate(-1000);
				
					while(TS.isPressed() ) {
						m1.setSpeed(350);
						m2.setSpeed(350);
						
						m1.forward();
						m2.forward();
						
						System.out.println("Object found.");
					}
				
				m1.rotate(-500,true);
				m2.rotate(-500);
				m1.rotate(400,true);
				m2.rotate(-400);
				m1.rotate(100,true);
				m2.rotate(100);
				m1.rotate(-200,true);
				m2.rotate(200);
				m1.rotate(-500,true);
				m2.rotate(-500);
				m1.rotate(100,true);
				m2.rotate(100);
				m3.rotate(500);
				m1.rotate(-80,true);
				m2.rotate(-80);
				m3.rotate(200);
				m3.rotate(-700);
				
			} else {	
				m1.setSpeed(100);
				m2.setSpeed(100);
				
				m1.backward();
				m2.backward();
			}
			//m1.rotate(100,true);
			//m2.rotate(100);
			}	
		}

	//Funktion Ausweichprogramm
	public static void Ausweichprogramm() {
			
			//Int Rotation Rechts
			
			//Int Drall Ausweichprogramm Rechts
			r = Drall_AWPRechts_Rechts;
			l = Drall_AWPRechts_Links;
			@SuppressWarnings("unused")
						
			int Sen_Clear[] = SB.getClear();
			m1.forward();
			m2.forward();
			
			m1.setSpeed(300);
			m2.setSpeed(300);

			System.out.println("Ausweichprogramm Rechts");

			m1.rotate(-100, true);// rueckwaerts fahren
			m2.rotate(-100);// rueckwaerts fahren

			m1.rotate(-400, true); // drehung nach rechts
			m2.rotate(400); // drehen nach rechts

			m1.rotate(550, true); // forward fahren
			m2.rotate(550); // forward fahren
			
			m1.rotate(400, true); // drehen nach links
			m2.rotate(-400); // drehen nach links

			m1.rotate(700, true);// forward fahren
			m2.rotate(700); // forward fahren

			m1.rotate(350, true); // dr-ehen nach links
			m2.rotate(-350); // drechen nach links
			
			m1.rotate(350, true);
			m2.rotate(350);
			
			m2.rotate(275, true);
			m1.rotate(-270);
			
				
				
			//}

	} //end Funktion
	
	//Funktion Fahrprogramm
	public static void Fahrprogramm() {
		LCD.drawString("FR: ", 1, 0);
		LCD.drawString("                   ", 0, 1);
		LCD.drawString("    ", 1, 2);
		LCD.drawInt(sw.elapsed(), 1, 2);
		sw.reset();
		int Sen_Clear[] = SB.getClear();
		LCD.drawString("    ", 1, 3);
		LCD.drawInt(sw.elapsed(), 1, 3);
		
		LCD.drawString("                 ", 0, 5);
		LCD.drawString("                 ", 0, 6);
		LCD.drawString("                 ", 0, 7);
		
		LCD.drawString("0:", 0, 5);
		LCD.drawInt((SB.getClear()[0]), 2, 5); //Links
		LCD.drawString("1:", 9, 5);
		LCD.drawInt((SB.getClear()[1]), 11, 5); //LinksMitte
		LCD.drawString("2:", 0, 6);
		LCD.drawInt((SB.getClear()[2]), 2, 6); //Mitte
		LCD.drawString("3:", 9, 6);
		LCD.drawInt((SB.getClear()[3]), 11, 6); //RechtsMitte
		LCD.drawString("4:", 0, 7);
		LCD.drawInt((SB.getClear()[4]), 2, 7); //Rechts
		
		if (Sen_Clear[0] < black && Sen_Clear[4] < black) {
			
			LCD.drawString("Schwarz", 5,0);
			m2.rotate(100, true);
			m2Speed(MotorSpeed + l);
			m1.rotate(100);
			m1Speed(MotorSpeed + r);
			

			m1.rotate(230, true);
			m1Speed(MotorSpeed + r);
			m2.rotate(-180);
			m2Speed(MotorSpeed + l);
			SB.getClear();
			
		}
		
		
	
/*		else if (Sen_Clear[0] < black && Sen_Clear[1] < black){
			
			LCD.drawString("Links 90  ",5,0);
			m1.rotate(70);
			m2.rotate(70, true);
			m2.rotate(300, true);
			m2Speed(MotorSpeed + Drall_Left_Rechts);
			m1.rotate(-300);
			m1Speed(MotorSpeed + Drall_Left_Links);
		}
		
		else if (Sen_Clear[3] < black && Sen_Clear[4] < black){
			
			LCD.drawString("Rechts 90  ",5,0);
			m1.rotate(70);
			m2.rotate(70, true);
			m1.rotate(300, true);
			m1Speed(MotorSpeed + Drall_Left_Links);
			m2.rotate(-300);
			m2Speed(MotorSpeed + Drall_Left_Rechts);
			
		}
		if (Sen_Clear[1] < black) {
	
			LCD.drawString("Soft Links", 5, 0);
			m2.forward();
			m2Speed(50);
			m1.forward();
			m1Speed(300);
	
		} else if (Sen_Clear[3] < black){
			
			LCD.drawString("Soft Right", 5, 0);
			m1.forward();
			m1Speed(50);
			m2.forward();
			m2Speed(300);
		
		} else*/ if (Sen_Clear[4] < black) {

			LCD.drawString("Links   ",5,0);
			m2Speed(500);
			m2.forward();
			m1Speed(300);
			m1.backward();
			
		} else if (Sen_Clear[0] < black) {

			LCD.drawString("Rechts  ",5,0);
			m1Speed(500);
			m1.forward();
			m2Speed(300);
			m2.backward();
			
		
		} else {

			LCD.drawString("Gradeaus",5,0);

			m1.forward();
			m2.forward();
			m1Speed(MotorSpeed + r);
			m2Speed(MotorSpeed + l);
		} //end else
		LCD.drawString("    ", 1, 3);
		LCD.drawInt(sw.elapsed(), 1, 4);
	} //end Fahrprogramm

	//Funktion Main-Programm
	@SuppressWarnings("unused")
	public static void main(String[] args) {

	
		//Config the Motors and Sensors:
		m1  = new SuperMotor(MotorPort.B); //Motor LINKS
		m2  = new SuperMotor(MotorPort.C); //Motor RECHTS
		m3  = new SuperMotor(MotorPort.A);
		SB  = new Sensorboard(SensorPort.S1);
		TS  = new TouchSensor(SensorPort.S3);
		USS = new UltrasonicSensor(SensorPort.S4);
		dsb = new DatenHPSB();	
	
		
		SB.setBrightness(HelligkeitSBCenter, HelligkeitSBSide);
		SB.setTiming(1);
		SB.setSensorGain(SB_Gain);
		LCD.drawString("Press to start!", 1, 1);
		Button.waitForAnyPress();
		
		while (true) {

			//dsb.start();
			
			int Sen_Clear[] = SB.getClear();
			int Sen_Green[] = SB.getGreen();
			int Sen_Blue[] = SB.getBlue();
			int Sen_Red[] = SB.getRed();
			LCD.drawString("    ", 1, 3);
			/*if (Sen_Clear[0] > Alu && Sen_Clear[1] && Sen_Clear[2] > Alu && Sen_Clear[4] > Alu && Sen_Clear[4] > Alu) {
				LCD.drawString("Evakprogramm!", 1, 1);
				Delay.msDelay(100);
				Evakprogramm();
				
			} else*/ if (TS.isPressed()) {
				LCD.drawString("Ausweichprogramm!", 1, 1);
				System.out.println("");
				Ausweichprogramm();
				
			} else {
				
				Fahrprogramm();
			
				
			} //end else
			
		} //end while

	} //end main

} //end class

// (C) 2016, (R) Julius Electronics (Julius Gros) and Nico Kreuzer
// (C), (R) The LEGO Company
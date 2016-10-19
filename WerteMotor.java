import lejos.nxt.*;

public class WerteMotor{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LightSensor LLS = new LightSensor(SensorPort.S2);
		LightSensor RLS = new LightSensor(SensorPort.S3);
		
		SuperMotor m1 = new SuperMotor(MotorPort.B);
		SuperMotor m2 = new SuperMotor(MotorPort.C);

		int r = 0;
		int l = 0;

		System.out.println("Press to Start");
		Button.waitForAnyPress();

		while (true) {

			System.out.println(m1.getPower());
			System.out.println(m2.getPower());

			if (LLS.readValue() < 31) {

				m2.forward();
				m2.setSpeed(500);
				m1.backward();
				m1.setSpeed(600);

				l = 125;
				r = 0;
			} else if (RLS.readValue() < 31) {

				m1.forward();
				m1.setSpeed(500);
				m2.backward();
				m2.setSpeed(600);

				r = 125;
				l = 0;
			} else {

				m1.forward();
				m2.forward();
				m1.setSpeed(225 + r);
				m2.setSpeed(225 + l);
			}
		}
	}

}
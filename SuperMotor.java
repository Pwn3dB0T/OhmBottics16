import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TachoMotorPort;


public class SuperMotor extends NXTRegulatedMotor {

	public SuperMotor(TachoMotorPort port) {
		super(port);
	}
	
	public int getPower(){
		return reg.power;
	}

}

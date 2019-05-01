package club.chawks.robojam.hardware;

import com.pi4j.gpio.extension.pca.PCA9685Pin;

public class RoboJamOpMode extends OpMode {

	private CRServo m_servoPort;
	private CRServo m_servoStarboard;
	private boolean m_bInitialized;
	private ServoController m_scParent;

	CRServo getServoDude() {
		CRServo servoReturn = new CRServo(m_scParent, PCA9685Pin.PWM_08);
		return servoReturn;
	}

	public void init() {
		m_bInitialized = true;

		
	}

	public void init(HardwareMap hardwareMap) {

		// TODO Auto-generated method stub
		this.m_servoPort = getServoDude();
		this.m_servoStarboard = getServoDude();
		m_servoPort.setDirection(CRServo.Direction.FORWARD);
		m_servoPort.setPower(.5);
		m_servoStarboard.setDirection(CRServo.Direction.FORWARD);
		m_servoStarboard.setPower(0.5);
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub

	}

}

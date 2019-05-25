package club.chawks.robojam.hardware;

import com.pi4j.gpio.extension.pca.PCA9685Pin;

public class RoboJamOpMode extends OpMode {

	private CRServo m_servoLeft;
	private CRServo m_servoRight;
	private boolean m_bInitialized;

	public void init() {
		m_bInitialized = false;
	}

	public void init(HardwareMap hardwareMap) {

		// TODO Auto-generated method stub
		this.m_servoLeft = hardwareMap.crservo.get("leftWheel");
		this.m_servoRight = hardwareMap.crservo.get("rightWheel");
		m_servoLeft.setDirection(CRServo.Direction.FORWARD);
		m_servoRight.setDirection(CRServo.Direction.FORWARD);
		m_bInitialized = true;
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub
		if(m_bInitialized == false){
			System.out.println("error! Initialize with hardware map");
			return;
		}
		
		//String state = "start";
		//switch state
		
		m_servoLeft.setPower(1);
		
	}

}

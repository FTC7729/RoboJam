package club.chawks.robojam.hardware;

import com.pi4j.gpio.extension.pca.PCA9685Pin;

public class RoboJamOpMode extends OpMode {

	private CRServo m_servoPort;
	private CRServo m_servoStarboard;
	private boolean m_bInitialized;
	private ServoController m_scParent;

	public void init() {
		m_bInitialized = false;

		
	}

	public void init(HardwareMap hardwareMap) {

		// TODO Auto-generated method stub
		this.m_servoPort = hardwareMap.crservo.get("leftWheel");
		this.m_servoStarboard = hardwareMap.crservo.get("rightWheel");
		System.out.println(" ## m_servoPort" + m_servoPort);
		m_servoPort.setDirection(CRServo.Direction.FORWARD);
		m_servoPort.setPower(.5);
		m_servoStarboard.setDirection(CRServo.Direction.FORWARD);
		m_servoStarboard.setPower(0.5);
		m_bInitialized = true;
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub
		if(m_bInitialized == false){
			System.out.println("error! Initialize with hardware map");
			return;
		}
		
		m_servoPort.setPower(1);
		/*for (double i = 0.1; i <= 1.5; i += 0.01)
		{
			m_servoPort.setPower(i);
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

	}

}

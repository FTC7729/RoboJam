package club.chawks.robojam.hardware;


import static java.lang.System.out;

import com.pi4j.gpio.extension.pca.PCA9685Pin;

import club.chawks.robojam.hardware.RoboJamOpMode;
import club.chawks.robojam.hardware.HardwareMap;

public class Main {
	private static ServoController m_scParent;
	public static void main(String[] args) {
		HardwareMap my_hardwaremap = new HardwareMap();
		ServoController my_servoController = new ServoController();
//		CRServo my_servoLeft = new CRServo(my_servoController, PCA9685Pin.PWM_08);
		CRServo my_servoLeft = new CRServo(my_servoController, PCA9685Pin.PWM_08);
//		CRServo my_servoRight = new CRServo();
		my_hardwaremap.put("servocontroller", (HardwareDevice)my_servoController);
		my_hardwaremap.put("leftWheel", (HardwareDevice)my_servoLeft);
//		my_hardwaremap.put("rightWheel",  (HardwareDevice)my_servoRight);
		RoboJamOpMode my_OpMode = new RoboJamOpMode();
		out.println("yes");
		for (double i = 0.1; i <= 1.5; i += 0.01)
		{
			my_servoLeft.setPower(i);
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		my_servoLeft.setPower(0.1);
		my_OpMode.init();
		while(1==1) {
			my_OpMode.loop();
		}
		
	}
}

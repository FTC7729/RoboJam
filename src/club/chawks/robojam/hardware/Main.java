package club.chawks.robojam.hardware;

import static java.lang.System.out;

import com.pi4j.gpio.extension.pca.PCA9685Pin;

import club.chawks.robojam.hardware.RoboJamOpMode;
import club.chawks.robojam.hardware.HardwareMap;

public class Main {
	public static void main(String[] args) {
		HardwareMap my_hardwaremap = new HardwareMap();
		ServoController my_servoController = new ServoController();
//		CRServo my_servoLeft = new CRServo(my_servoController, PCA9685Pin.PWM_08);
		CRServo my_servoLeft = new CRServo();
		CRServo my_servoRight = new CRServo();
		my_hardwaremap.put("servocontroller", (HardwareDevice)my_servoController);
		my_hardwaremap.put("leftWheel", (HardwareDevice)my_servoLeft);
		my_hardwaremap.put("rightWheel",  (HardwareDevice)my_servoRight);
		RoboJamOpMode my_OpMode = new RoboJamOpMode();
		out.println("yes");
		my_OpMode.init();
		while(1==1) {
			my_OpMode.loop();
		}
	}
}

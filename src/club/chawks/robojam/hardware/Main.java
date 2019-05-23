package club.chawks.robojam.hardware;


import static java.lang.System.out;

import com.pi4j.gpio.extension.pca.PCA9685Pin;
import java.util.Iterator;
import club.chawks.robojam.hardware.RoboJamOpMode;
import club.chawks.robojam.hardware.HardwareMap;
import club.chawks.robojam.hardware.HardwareMap.DeviceMapping;

public class Main {
	private static ServoController m_scParent;
	public static void main(String[] args) {
		int iIndex = 0;
		
		//
		// Create a hardware map
		HardwareMap my_hardwaremap = new HardwareMap();
		
		// 
		// Create a servo controller
		ServoController my_servoController = new ServoController();
		my_hardwaremap.put("servocontroller", (HardwareDevice)my_servoController);
		
		//
		// Add servocontroller to allDeviceMappings
		Iterator<DeviceMapping <? extends HardwareDevice>> iterator = my_hardwaremap.allDeviceMappings.iterator();
		int iLoop = 0;
		while (iterator.hasNext()){
			DeviceMapping <? extends HardwareDevice> curDevice = iterator.next();
			if (curDevice.getDeviceTypeClass() == club.chawks.robojam.hardware.ServoController.class) {
				System.out.println(" ## Found servoController");
				DeviceMapping<ServoController> curServoController = (DeviceMapping<ServoController>)curDevice;
 				curServoController.put("servocontroller", my_servoController);
			}
			
			System.out.println(" ## Loop: " + iLoop);
			iLoop++;
		}
			
		// 
		// Create servos
		CRServo my_servoLeft = new CRServo(my_servoController, PCA9685Pin.PWM_09);
		CRServo my_servoRight = new CRServo(my_servoController, PCA9685Pin.PWM_08);
		
		my_hardwaremap.put("leftWheel", (HardwareDevice)my_servoLeft);		
		my_hardwaremap.put("rightWheel",  (HardwareDevice)my_servoRight);
	
		iterator = my_hardwaremap.allDeviceMappings.iterator();
		iLoop = 0;
		while (iterator.hasNext()){
			DeviceMapping <? extends HardwareDevice> curDevice = iterator.next();
			if (curDevice.getDeviceTypeClass() == club.chawks.robojam.hardware.CRServo.class) {
				System.out.println(" ## Found CRServo");
				DeviceMapping<CRServo> curCRServo = (DeviceMapping<CRServo>)curDevice;
 				curCRServo.put("leftWheel", my_servoLeft);
 				curCRServo.put("rightWheel", my_servoRight);
			}
			
			System.out.println(" ## Loop: " + iLoop);
			iLoop++;
		}
		
		// 
		// Create OpMode
		RoboJamOpMode my_OpMode = new RoboJamOpMode();
		out.println(" ## yes");
		
		// 
		// Start the real program baby!
		my_OpMode.init(my_hardwaremap);
		while(1==1) {
			my_OpMode.loop();
		}
		
	}
}

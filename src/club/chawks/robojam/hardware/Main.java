package club.chawks.robojam.hardware;


import static java.lang.System.out;

import com.pi4j.gpio.extension.pca.PCA9685Pin;

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
		// TODO: Add servocontroller to allDeviceMappings
		iIndex = my_hardwaremap.allDeviceMappings.indexOf(club.chawks.robojam.hardware.ServoController.class);
		iIndex = my_hardwaremap.allDeviceMappings.lastIndexOf(club.chawks.robojam.hardware.ServoController.class);
		for (int x = 0;x < my_hardwaremap.allDeviceMappings.size(); x++){
			DeviceMapping<? extends HardwareDevice> curhardwaredev = my_hardwaremap.allDeviceMappings.get(x);
			if (curhardwaredev.getClass().equals(club.chawks.robojam.hardware.ServoController.class)){
				curhardwaredev.put("servocontroller", my_servoController);
			
		}
			
			
		// TODO: try whileloop iterator<String> expiterator = exp.iterator();
			
		//	TODO: while(expiterator.hasNext()){
		// TODO: System.out.println(expiterator.next());   }
		
		// 
		// Create servos
		CRServo my_servoLeft = new CRServo(my_servoController, PCA9685Pin.PWM_09);
		CRServo my_servoRight = new CRServo(my_servoController, PCA9685Pin.PWM_08);
		
		my_hardwaremap.put("leftWheel", (HardwareDevice)my_servoLeft);
		iIndex = my_hardwaremap.allDeviceMappings.indexOf(club.chawks.robojam.hardware.CRServo.class);
		HardwareMap.DeviceMapping<CRServo> my_mapCRServos = (HardwareMap.DeviceMapping<CRServo>)my_hardwaremap.allDeviceMappings.get(iIndex);
		my_mapCRServos.put("leftWheel", my_servoLeft);
		
		my_hardwaremap.put("rightWheel",  (HardwareDevice)my_servoRight);
		my_mapCRServos.put("rightWheel",  my_servoRight);
		
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

package club.chawks.robojam.hardware;

import com.pi4j.gpio.extension.pca.PCA9685Pin;
import java.util.Iterator;
import club.chawks.robojam.hardware.RoboJamOpMode;
import club.chawks.robojam.hardware.HardwareMap;
import club.chawks.robojam.hardware.HardwareMap.DeviceMapping;

public class Main {
	
	private static boolean m_bDebug = true;
	
	private static ServoController createServoController(HardwareMap my_hardwaremap) {
		if ( m_bDebug )
			System.out.println(" ++createServoController");
		// 
		// Create a servo controller
		ServoController my_servoController = new ServoController();
		my_servoController.m_bDebug = m_bDebug;
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
		
		if ( m_bDebug )
			System.out.println(" --createServoController: " + my_servoController);
		
		return my_servoController;
		
	} // createServoController
	
	private static boolean createCRServos(HardwareMap my_hardwaremap, ServoController my_servoController) {
		
		if ( m_bDebug )
			System.out.println(" ++createCRServo");
		
		CRServo my_servoLeft = new CRServo(my_servoController, PCA9685Pin.PWM_09);
		CRServo my_servoRight = new CRServo(my_servoController, PCA9685Pin.PWM_08);
		my_servoLeft.m_bDebug = m_bDebug;
		my_servoRight.m_bDebug = m_bDebug;
		
		my_hardwaremap.put("leftWheel", (HardwareDevice)my_servoLeft);		
		my_hardwaremap.put("rightWheel",  (HardwareDevice)my_servoRight);

		// 
		// Add servios to DeviceMapping
		Iterator<DeviceMapping <? extends HardwareDevice>> iterator = my_hardwaremap.allDeviceMappings.iterator();
		int iLoop = 0;
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
		
		if ( m_bDebug )
			System.out.println(" --createCDServos: " + true);

		return true;
	} // create CRServos
	
	public static HardwareMap loadHardwareMap() {
		HardwareMap returnHardwareMap = null;
		
		if ( m_bDebug )
			System.out.println(" ++LoadHardwareMap");
		
		returnHardwareMap = new HardwareMap();
		
		// 
		// Create a robot controller
		ServoController my_servoController = createServoController(returnHardwareMap);
		if ( my_servoController == null ) {
			System.out.println(" !! Unable to create ServoController");
			System.exit(1);
		}

			
		// 
		// Create servos
		if ( createCRServos(returnHardwareMap, my_servoController) ) {
			System.out.println(" !! Unable to create CRServos");
			System.exit(1);
		}
		
		if (m_bDebug )
			System.out.println(" --LoadHardwareMap");
		
		return returnHardwareMap;
	} // LoadHardwareMap
	
	//
	// main
	public static void main(String[] args) {
		
		//
		// Create a hardware map
		HardwareMap my_hardwaremap = loadHardwareMap();
		if ( my_hardwaremap == null ) {
			System.out.println(" !! Could not load hardwaremap");
			System.exit(1);
		}
		
		// 
		// Create OpMode
		RoboJamOpMode my_OpMode = new RoboJamOpMode();
		
		// 
		// Start the real program baby!
		my_OpMode.init(my_hardwaremap);
		while(1==1) {
			my_OpMode.loop();
		}
		
	}
}

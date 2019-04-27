package club.chawks.robojam.hardware;
import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.io.gpio.GpioController;



class CRServo extends DcMotorSimple
{
	
	private boolean m_bDebug = true;
	private static int PCA9685_ADDRESS = 0x40;
	private GpioController m_gpio;
	private PCA9685GpioProvider m_gpioProvider;
	
	public CRServo () {
		
		//CRServo my_servoLeft = new CRServo(my_servoController, PCA9685Pin.PWM_08);
		
	}
	
    ServoController getController() {
    	ServoController scReturn = new ServoController();
    	
    	return scReturn;
    }
    
    int getPortNumber() {
    	int iReturn = 1;
    	// get the Port Dude
    	return iReturn;
    }
    
} // class CRServo

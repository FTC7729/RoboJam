package club.chawks.robojam.hardware;
import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.Pin;



class CRServo extends DcMotorSimple
{
	
	private boolean m_bDebug = true;
	private static int PCA9685_ADDRESS = 0x40;
	private GpioController m_gpio;
	private PCA9685GpioProvider m_gpioProvider;
	
	private ServoController m_scParent;
	private Pin m_Pin;
	private double m_dPower;
	
	
	/*public CRServo () {
		
		CRServo my_servoLeft = new CRServo(my_servoController, PCA9685Pin.PWM_08);
		 MMMMMMM    _________________________________
	    /___ ___\  | do not construct CRServo please |
	   E| 0   0 |3 /---------------------------------
		| __J__ |   ___________________________________________
		\ \/ / <| I am the comment man, man of the comments |
		 \__|__/    ------------------------------------------- 
		   /|\_E
		 e/ |
		  _/ \_		 
	}*/
	
	public CRServo(ServoController scParent, Pin Pin){
		m_scParent = scParent;
		System.out.println("here is the scParent" + m_scParent);
		m_Pin = Pin;
		System.out.println("here is the pin" + m_Pin);
	}
	
	public void setPower(double power){
		
	/*	 MMMMMMM    ____________________________________
	    /___ ___\  | Math is wrong, but it should work! |
	   E| 0   0 |3 /------------------------------------
		| __J__ |   _____________________________
		\ \n_n/ / <| zero still moves it forward |
		 \__|__/    ----------------------------- 
		   /|\_ E
		 e/ |
		  _/ \_	*/	 
		m_dPower = -500* power +1518;
		
		/*if(m_dPower>2000){
			m_dPower=2000;
		}else if(m_dPower<1000){
			m_dPower=1000;
		}*/
		System.out.println(m_dPower);
		System.out.println("here is the pin again  _  " + m_Pin);
		
		System.out.println("here is the gpioProveider" + m_scParent.m_gpioProvider);
		
		
		m_scParent.m_gpioProvider.setPwm(m_Pin, (int)m_dPower); 
		
		System.out.println("yay, past the power ");
		
		//m_gpioProvider.setPwm(PCA9685Pin.m_Pin, (int)m_dPower);
		// m_gpioProvider.setPwm("PCA....PWM_" + m_Pin, (int)m_dPower);
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

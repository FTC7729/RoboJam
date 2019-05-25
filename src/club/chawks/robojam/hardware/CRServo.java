package club.chawks.robojam.hardware;

import com.pi4j.io.gpio.Pin;

class CRServo extends DcMotorSimple
{
	
	public boolean m_bDebug = true;
	
	private ServoController m_scParent;
	private Pin m_Pin;
	private double m_dPower;
	
	
	/*public CRServo () {
		
		CRServo my_servoLeft = new CRServo(my_servoController, PCA9685Pin.PWM_08);
		 MMMMMMM    _________________________________
	    /___ ___\  | do not construct CRServo please |
	   E| 0   0 |3 /---------------------------------
		| __J__ |    ___________________________________________
		\ \n_n/ /  <| I am the comment man, man of the comments |
		 \__|__/     ------------------------------------------- 
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
		
		if ( m_bDebug )
			System.out.println(" ## yay, past the power ");

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

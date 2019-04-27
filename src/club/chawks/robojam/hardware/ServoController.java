package club.chawks.robojam.hardware;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

class ServoController extends HardwareDevice {
	private boolean m_bDebug = true;
	private int PCA9685_ADDRESS = 0x40;
	private GpioController m_gpio;
	private PCA9685GpioProvider m_gpioProvider; 
	
  public ServoController () {
	  init(PCA9685_ADDRESS);
  }// ServoController
  
  public ServoController (int iAddress) {
	  init(iAddress);
	  
	  try {
			this.m_gpioProvider = this.createProvider();
			
			// provisionPwmOutputs
          m_gpio = GpioFactory.getInstance();
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_00, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_01, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_02, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_03, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_04, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_05, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_06, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_07, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_08, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_09, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_10, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_11, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_12, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_13, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_14, "NA");
          m_gpio.provisionPwmOutputPin(m_gpioProvider, PCA9685Pin.PWM_15, "NA");
	    } catch (IOException exception) {
	        System.out.println(" !! I/O error on I2C bus " + I2CBus.BUS_1 + " occurred");
	    } catch (UnsupportedBusNumberException exception) {
	        System.out.println(" !! Unsupported I2C bus " + I2CBus.BUS_1 + " required");
	    }		 
	  
  }
  
  public boolean init (int iAddress) {
	  
	  return true;
  } 
  
  private PCA9685GpioProvider createProvider() throws UnsupportedBusNumberException, IOException
  {
      if ( m_bDebug ) 
          System.out.println(" ++ createProvider");
      
      	//printServoPos();
          
      // BigDecimal frequency = PCA9685GpioProvider.ANALOG_SERVO_FREQUENCY;
      BigDecimal frequency = new BigDecimal("50.0");
      BigDecimal frequencyCorrectionFactor = new BigDecimal("1.0578");
      
      I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
      
      if ( m_bDebug ) {
          DecimalFormat df = new DecimalFormat("###.0000");
          System.out.println(" ?? Servo Frequency " + df.format(frequency));
          System.out.println(" ?? Servo Correction " + df.format(frequencyCorrectionFactor));
      }
      
      if ( m_bDebug )
          System.out.println(" -- createProvider");
      
      return new PCA9685GpioProvider(bus, PCA9685_ADDRESS, frequency, frequencyCorrectionFactor);
  
  } // createProvider	
  
  /**
   * PWM Status - is pwm enabled?
   */
  enum PwmStatus { ENABLED, DISABLED, MIXED }

  /**
   * Enables all of the servos connected to this controller
   */
  void pwmEnable(){
	  
  }

  /**
   * Disables all of the servos connected to this controller
   */
  void pwmDisable(){
	  
  }

  /**
   * Returns the enablement status of the collective set of servos connected to this controller
   * @return the enablement status of the collective set of servos connected to this controller
   */
  PwmStatus getPwmStatus(){
	  PwmStatus psReturn = PwmStatus.ENABLED;
	  return psReturn;
  }

  /**
   * Set the position of a servo at the given channel
   * @param servo channel of servo
   * @param position from 0.0 to 1.0
   */
  void setServoPosition(int servo, double position){
	  
  }

  /**
   * Get the position of a servo at a given channel
   * @param servo channel of servo
   * @return position, scaled from 0.0 to 1.0
   */
  double getServoPosition(int servo){
	  double dReturn = 0.0;
	  
	  return dReturn;
  }
}
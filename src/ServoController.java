public interface ServoController extends HardwareDevice {

  /**
   * PWM Status - is pwm enabled?
   */
  enum PwmStatus { ENABLED, DISABLED, MIXED }

  /**
   * Enables all of the servos connected to this controller
   */
  void pwmEnable();

  /**
   * Disables all of the servos connected to this controller
   */
  void pwmDisable();

  /**
   * Returns the enablement status of the collective set of servos connected to this controller
   * @return the enablement status of the collective set of servos connected to this controller
   */
  PwmStatus getPwmStatus();

  /**
   * Set the position of a servo at the given channel
   * @param servo channel of servo
   * @param position from 0.0 to 1.0
   */
  void setServoPosition(int servo, double position);

  /**
   * Get the position of a servo at a given channel
   * @param servo channel of servo
   * @return position, scaled from 0.0 to 1.0
   */
  double getServoPosition(int servo);
}
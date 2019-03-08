//import com.qualcomm.robotcore.hardware.ControlSystem;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

///import static com.qualcomm.robotcore.hardware.ControlSystem.MODERN_ROBOTICS;
//import static com.qualcomm.robotcore.hardware.ControlSystem.REV_HUB;

/**
 * {@link DeviceProperties} annotations must accompany annotations like {@link AnalogSensorType} or
 * {@link ServoType}.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DeviceProperties {
    /**
     * Specifies the XML tag to use to represent configured instances of the device as recorded in
     * saved robot configurations. You should choose a tag that is different from all other
     * configuration types.
     */
    String xmlTag();

    /**
     * Specifies the name of the device to be displayed in configuration user interfaces.
     */
    String name();

    /**
     * Specifies a brief phrase that states the type of device.
     */
    String description() default "";

    /**
     * Specifies whether the type is built into the SDK
     */
    boolean builtIn() default false;

    /**
     * Specifies what control systems this device is compatible with
     */
  //  ControlSystem[] compatibleControlSystems() default {MODERN_ROBOTICS, REV_HUB};

    /**
     * Specifies additional XML tags that will map to this device
     */
    String[] xmlTagAliases() default {};
}
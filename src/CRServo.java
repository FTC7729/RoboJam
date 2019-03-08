public interface CRServo extends DcMotorSimple
    {
   
    ServoController getController();

    
    int getPortNumber();
    }

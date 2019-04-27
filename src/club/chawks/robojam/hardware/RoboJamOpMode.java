package club.chawks.robojam.hardware;

public  class RoboJamOpMode extends OpMode{

	private CRServo m_servoPort;
	private CRServo m_servoStarboard;
	private boolean m_bInitialized;
	
	
	CRServo getServoDude() {
		CRServo servoReturn = new CRServo();
		return servoReturn;
	}
	public void init(){
		m_bInitialized = true;	
	
	}
	
	public void init(HardwareMap hardwareMap) {
		// TODO Auto-generated method stub
		this.m_servoPort = getServoDude();
		this.m_servoStarboard = getServoDude();
		
		m_servoPort.setDirection(CRServo.Direction.FORWARD);
		m_servoPort.setPower(0);
		m_servoStarboard.setDirection(CRServo.Direction.FORWARD);
		m_servoStarboard.setPower(0);
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub
		
	}
	
	

}

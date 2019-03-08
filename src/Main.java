import static java.lang.System.out;
public class Main {
	public static void main(String[] args) {
		RoboJamOpMode my_OpMode = new RoboJamOpMode();
		out.println("yes");
		my_OpMode.init();
		while(1==1) {
			my_OpMode.loop();
		}
	}
}

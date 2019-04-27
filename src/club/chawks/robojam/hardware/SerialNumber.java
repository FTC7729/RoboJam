package club.chawks.robojam.hardware;

import java.io.Serializable;

public class SerialNumber implements Serializable {

	protected final String serialNumberString;
	
	protected SerialNumber(String serialNumberString) {
		this.serialNumberString = serialNumberString;
	}

} // class SearialNumber

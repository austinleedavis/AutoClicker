package clicker;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;

public class KeyPresser extends AbstractClickerBehavior {

	private Robot rob;
	private int keyCode;
	private int offset;
	private boolean awaitingOffset = true;
	private long firstClickTry = 0;
	private KeyCodeMap map = new KeyCodeMap();
	private int duration;
	
	public KeyPresser(String keyString, int delay, int offset, int duration, boolean debug, AutoClicker clicker) {
		super(delay,clicker);
		try {
			this.rob = debug?new TextRobot():new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		this.keyCode = map.getCode(keyString);
		this.offset = offset;
		this.duration = duration;
		Application.log.log(Level.INFO,String.format("Using key presser to press %s with %dms delay %d offset and %d duration", keyString, delay, offset, duration));
	}

	@Override
	public String doClickBehavior(long currentTimeMillis) {
		if(awaitingOffset) {
			if(firstClickTry == 0) {
				firstClickTry = currentTimeMillis;
			}
			
			if(currentTimeMillis - firstClickTry > offset) {
				awaitingOffset = false;
			}
			
		}
		
		if(!awaitingOffset && isTimeToExecute(currentTimeMillis)) {
			this.rob.keyPress(this.keyCode);
			this.rob.delay(this.duration);
			this.rob.keyRelease(this.keyCode);
			this.rob.delay(10);
		} 
		
		
		return super.doClickBehavior(currentTimeMillis);
	}
	


}
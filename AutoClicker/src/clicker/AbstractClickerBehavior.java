package clicker;

import java.awt.Robot;

public abstract class AbstractClickerBehavior implements AutoClicker {

	protected AutoClicker clicker;
	
	
	public AbstractClickerBehavior(int delay,AutoClicker clicker) {
		this.clicker = clicker;
		this.delayInMiliseconds = delay;
	}
	
	private int delayInMiliseconds = 1000;
	private long timeOfLastBehaviorExecute = 0;
	
	@Override
	public String doClickBehavior(long currentTimeMillis) {
		return clicker.doClickBehavior(currentTimeMillis);
	}
	
	public int getDelayInMiliseconds() { return this.delayInMiliseconds;}
	public void setDelayInMiliseconds(int delay) { this.delayInMiliseconds = delay;}
	
	boolean isTimeToExecute(long currentTimeMillis) {
		long timePassed = currentTimeMillis - timeOfLastBehaviorExecute;
		if (timePassed > delayInMiliseconds) {
			markTimeOfLastBehaviorExecute(currentTimeMillis);
			return true;
		}
		return false;
	}
	
	private void markTimeOfLastBehaviorExecute(long currentTimeMillis) {
		this.timeOfLastBehaviorExecute = currentTimeMillis;
	}
	

	@Override
	public Robot rob() {
		return this.clicker.rob();
	}
	

}

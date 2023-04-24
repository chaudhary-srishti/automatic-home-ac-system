public class FanController {

	private boolean fanState;

	private int counter;

	private static TempController tempController;

	FanController() {
		this.fanState = true;
	}

	public boolean getFanState() {
		return this.fanState;
	}

	public void setFanState(boolean state) {
		this.fanState = state;
	}

}

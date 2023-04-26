public class FanController {

	private boolean fanState;

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

public class FanController {

	private int fanSpeed;

	private int counter;

	private static TempController tempController;

	public FanController(TempController tempController) {
		this.tempController = tempController;
	}

	public int getSpeed() {

		return this.fanSpeed;
	}

	public void setSpeed(int speed) {
		this.fanSpeed = speed;
	}

}

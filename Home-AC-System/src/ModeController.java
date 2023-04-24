public class ModeController {

	private int mode;

	private int counter;

	private TempController tempController;

	ModeController(TempController tempController) {
		this.tempController = tempController;
	}

	public int getMode() {
		return this.mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
		this.tempController.setMode(mode);
	}

}

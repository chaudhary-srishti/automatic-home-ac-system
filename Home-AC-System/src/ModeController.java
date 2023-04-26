public class ModeController {
	private int mode;

	private TempController tempController;

	ModeController(TempController tempController) {
		this.tempController = tempController;
	}

	public void setMode(int mode) {
		this.mode = mode;
		this.tempController.setMode(mode);
	}
}

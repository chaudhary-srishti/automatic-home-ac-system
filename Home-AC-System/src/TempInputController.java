public class TempInputController {
	private int temp;
	private TempController tempController;

	TempInputController(TempController tempController) {
		this.tempController = tempController;
	}

	public void setTemp(int temp) {
		this.temp = temp;
		tempController.setTemp(temp);
	}
}

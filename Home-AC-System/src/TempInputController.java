public class TempInputController {

	private int temp;

	private int counter;

	private TempController tempController;

	TempInputController(TempController tempController) {
		this.tempController = tempController;
	}

	public int getTemp() {
		return this.temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
		tempController.setTemp(temp);
	}

}

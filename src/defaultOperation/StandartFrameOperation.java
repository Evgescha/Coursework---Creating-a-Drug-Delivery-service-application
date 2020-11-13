package defaultOperation;

import javax.swing.JFrame;
/**
 * класс, содержащий стандартные операции для всех форм
 * @author admin
 *
 */
public class StandartFrameOperation {
	JFrame frame;

	public StandartFrameOperation(JFrame frame) {
		this.frame = frame;
	}

	public void showFrame() {
		frame.setVisible(true);
	}

	public void hideFrame() {
		frame.setVisible(false);
	}
	public boolean isVisible() {
		return frame.isVisible();
	}
	public void switchVisible() {
		frame.setVisible(!isVisible());
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}

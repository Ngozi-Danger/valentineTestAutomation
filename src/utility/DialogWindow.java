package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;


public class DialogWindow implements Runnable {

	String username;
	String password;
	
	public DialogWindow(String UserPassword)
	{
		String[] userPassArray = UserPassword.split(":");
		username = userPassArray[0];
		password = userPassArray[1];
	}
	
@Override
public void run() {
    try {
        entercredentials();
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

public void entercredentials() throws InterruptedException {
    Thread.sleep(5000);
    try {
        enterText(username);
        enterSpecialChar(KeyEvent.VK_TAB);
        enterText(password);
        enterSpecialChar(KeyEvent.VK_ENTER);

    } catch (AWTException e) {

    }
}

private void enterText(String text) throws AWTException {
    Robot robot = new Robot();
    byte[] bytes = text.getBytes();
    int index = 0;
    for (byte b : bytes) {
        int bytecode = b;
        // keycode only handles [A-Z] (which is ASCII decimal [65-90])
        if (bytecode> 96 && bytecode< 123)
            bytecode = bytecode - 32;
        robot.delay(40);
        if(Character.isUpperCase(text.charAt(index))){
        	robot.keyPress (KeyEvent.VK_SHIFT); 
        	robot.keyPress (bytecode); //Your keycode(your letter)
        	robot.keyRelease (KeyEvent.VK_SHIFT); 
        	robot.keyRelease (bytecode);
        }else
        {
	        robot.keyPress(bytecode);
	        robot.keyRelease(bytecode);
        }
        index++;
        
    }
}

private void enterSpecialChar(int s) throws AWTException {
    Robot robot = new Robot();
    robot.delay(40);
    robot.keyPress(s);
    robot.keyRelease(s);
}

}

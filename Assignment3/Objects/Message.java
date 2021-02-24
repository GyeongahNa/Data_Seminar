package Objects;
import java.awt.Color;
import java.awt.Font;
import acm.graphics.*;

public class Message extends GCompound{

	GLabel msg;
	
	/*
	 * Constructor : Message
	 * Input param : (String)text / (double)x / (double)y
	 * Description : Displays text in x, y position. */
	public Message(String text, double x, double y) {
		msg = new GLabel(text);
		msg.setLocation(x, y);
		msg.setColor(Color.WHITE);
		msg.setFont(new Font("Monospaced", Font.PLAIN, 13));
		add(msg);
	}
	
	/*
	 * Method Name : remove
	 * Description : Removes the message.*/
	public void remove() {
		remove(msg);
		msg = null;
	}
}
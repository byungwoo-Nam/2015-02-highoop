import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GEPanel extends JPanel {
	@Override
	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D)g;
		g2D.drawRect(20,20,20,20);
		g2D.drawRect(60,60,20,20);
		g2D.drawRect(40,70,20,20);
		g2D.drawOval(100,100,50,100);
		g2D.setColor(Color.decode("#FF0000"));
		g2D.drawOval(30,30,50,20);
		g2D.drawArc(200, 200, 60, 150, 40, 90);
		g2D.setColor(Color.decode("#0100FF"));
		g2D.fillRect(300,120,100,100);
	}
}

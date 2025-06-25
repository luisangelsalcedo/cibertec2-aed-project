package eparking.views.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import eparking.utils.ThemeStyles;

public class CustomButton extends JButton{

	private static final long serialVersionUID = 1L;
	
	public CustomButton(String text) {
		setText(text);
		setBackground(ThemeStyles.$accent);
		setForeground(ThemeStyles.$white);
		setBorder(new EmptyBorder(5,15,5,15));
		setVisible(true);
		setFocusable(false);		
		setFocusPainted(false);
	    setBorderPainted(false);
	    setContentAreaFilled(true);
	    setOpaque(true);
	    
		if(this.isEnabled()) {
			// hover effect
	        addMouseListener(new MouseAdapter() {
	        	@Override
	    	    public void mousePressed(MouseEvent e) {
	    	        setBackground(ThemeStyles.$accent);
	                setForeground(ThemeStyles.$white);
	    	    }
	        	
	        	@Override
	            public void mouseReleased(MouseEvent e) {
	                setBackground(ThemeStyles.$accentLight);
	                setForeground(ThemeStyles.$white);
	            }
	        	
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                setBackground(ThemeStyles.$accentLight);
	                setForeground(ThemeStyles.$white);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                setBackground(ThemeStyles.$accent);
	                setForeground(ThemeStyles.$white);
	            }
	        });
		}
	}	
}

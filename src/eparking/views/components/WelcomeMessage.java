package eparking.views.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import eparking.utils.ThemeStyles;

public class WelcomeMessage extends JPanel{

	private static final long serialVersionUID = 1L;

	public WelcomeMessage(String title, String text) {		
	    JTextPane tpnTitle = new JTextPane();
	    tpnTitle.setText(title);
	    tpnTitle.setForeground(ThemeStyles.$primaryColor);
	    tpnTitle.setFont(ThemeStyles.xxlFont);
	    tpnTitle.setOpaque(false);
	    tpnTitle.setEditable(false);
	    tpnTitle.setBorder(null);

	    JTextPane tpnContent = new JTextPane();
	    tpnContent.setText(text);
	    tpnContent.setOpaque(false);
	    tpnContent.setEditable(false);
	    tpnContent.setBorder(null);
	    
	    JPanel linePanel2 = new JPanel();
	    linePanel2.setLayout(new GridLayout(1,1));
	    linePanel2.setPreferredSize(new Dimension(50,4));
	    linePanel2.setBackground(ThemeStyles.$primaryColor);
	    
	    JPanel linePanel = new JPanel();
	    linePanel.setLayout(new BorderLayout());
	    linePanel.setPreferredSize(new Dimension(4,4));
	    linePanel.add(linePanel2, BorderLayout.EAST);	    
	    
	    JPanel messagePanel = new JPanel();
	    messagePanel.setOpaque(false);
		messagePanel.setLayout(new BorderLayout(10, 10));		
		messagePanel.add(tpnTitle, BorderLayout.NORTH);
		messagePanel.add(linePanel, BorderLayout.CENTER);
		messagePanel.add(tpnContent, BorderLayout.SOUTH);

	    setVisible(true);
	    setOpaque(false);
	    setLayout(new GridBagLayout());
	    add(messagePanel);
	}
}
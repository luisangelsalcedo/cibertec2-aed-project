package eparking.views.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import eparking.utils.ThemeStyles;

public class ReservationItem extends JPanel{

	private static final long serialVersionUID = 1L;
	private JSeparator separator;
	private String[] content;
	private boolean isHeader;
	
	public ReservationItem(String[] content, boolean isHeader){		 
		this.content = content;
		this.isHeader = isHeader;
		render();
	}
	
	public ReservationItem(String[] content){		
		this(content, false);
	}
	
	private void render () {
		JPanel row = new JPanel();
		row.setOpaque(false);
		if(content.length > 0) {			
			row.setLayout(new GridLayout(1, content.length, 5, 5));
			for(String item : content) {
				
				JLabel lblItem = new JLabel(item);
				if(isHeader) {
					lblItem.setForeground(ThemeStyles.$primaryColor);
					lblItem.setFont(ThemeStyles.hTableFont);
				}
				row.add(lblItem);
			}
		}else {
			row.setLayout(new GridLayout(1, 1, 5, 5));
			row.add(new JLabel("No hay contenido"));
		}
		
		separator = new JSeparator();
		separator.setBorder(BorderFactory.createLineBorder(ThemeStyles.$accent,3));
		
		setLayout(new BorderLayout(5, 5));
		setOpaque(false);
		add(row, BorderLayout.NORTH);
		add(separator, BorderLayout.CENTER);
	}

}

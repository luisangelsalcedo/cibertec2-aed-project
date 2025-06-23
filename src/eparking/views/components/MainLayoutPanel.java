package eparking.views.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainLayoutPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel leftPanelTop;
	private JPanel leftPanelBottom;
	private JPanel rightPanel;
	private Component leftTopComponent = null;
    private Component leftBottomComponent = null;
    private Component rightComponent = null;

	public MainLayoutPanel() {        
        leftPanelTop = new JPanel();
        leftPanelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanelTop.setOpaque(false);
        leftPanelTop.add(getPlaceholder("left-top", leftTopComponent));
        
        leftPanelBottom = new JPanel();
        leftPanelBottom.setLayout(new GridLayout(1,1));
        leftPanelBottom.setOpaque(false);
        leftPanelBottom.add(getPlaceholder("left-bottom", leftBottomComponent));
        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        leftPanel.setOpaque(false);
        leftPanel.add(leftPanelTop);
        leftPanel.add(leftPanelBottom);
        
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(1,1));
        rightPanel.setOpaque(false);
        rightPanel.add(getPlaceholder("right", rightComponent));
        
        JPanel content = new JPanel(new BorderLayout(50, 50) );
        content.setOpaque(false);
        content.add(leftPanel, BorderLayout.WEST);
        content.add(rightPanel, BorderLayout.CENTER);

        setLayout(new GridLayout(1, 1));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setOpaque(false);
        add(content);
        setVisible(true);
	}
	
	private Component getPlaceholder(String name, Component comp) {
        return comp != null ? comp : new JLabel(name);
    }
	
	public void addLeftTop(Component component) {
        leftTopComponent = component;
        leftPanelTop.removeAll(); 
        leftPanelTop.add(getPlaceholder("left-top", leftTopComponent));
        rerender();
    }

    public void addLeftBottom(Component component) {
        leftBottomComponent = component;
        leftPanelBottom.removeAll();
        leftPanelBottom.add(getPlaceholder("left-bottom", leftBottomComponent));
        rerender();
    }

    public void addRight(Component component) {
        rightComponent = component;
        rightPanel.removeAll();
        rightPanel.add(getPlaceholder("right-top", rightComponent));
        rerender();
    }
    
    public void rerender() {
    	revalidate();
        repaint();
    }
}
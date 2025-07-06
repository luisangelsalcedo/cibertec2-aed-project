package eparking.views;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.net.URI;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import eparking.models.GroupMember;
import eparking.utils.RootData;
import eparking.utils.ThemeStyles;
import eparking.views.components.Brand;
import eparking.views.components.CustomButton;

public class AboutUsView extends JPanel {

	private static final long serialVersionUID = 1L;

	public AboutUsView() {				
		// text general description
		String description = "Esta aplicación ha sido desarrollada con fines educativos como parte del curso \"Algoritmos y estructura de datos\", dictado por el docente Marco Diaz Zavala en el Instituto Cibertec.\rEl proyecto tiene como finalidad aplicar conceptos teóricos-prácticos del curso, enfocándose en el diseño de algoritmos eficientes y la correcta utilización de estructuras de datos para resolver problemas comunes de programación.";
		JTextPane textDescription = new JTextPane();
		textDescription.setEditable(false);
		textDescription.setOpaque(false);
		textDescription.setText(description);
		textDescription.setPreferredSize(new Dimension(430, 100));
		
		JLabel lblIntegrantes = new JLabel("Integrantes");
		lblIntegrantes.setForeground(ThemeStyles.$primaryColor);
		lblIntegrantes.setFont(ThemeStyles.lgFont);
		
		JPanel lblIntegrantesPanel = new JPanel();
		lblIntegrantesPanel.setOpaque(false);
		lblIntegrantesPanel.setLayout(new FlowLayout());
		lblIntegrantesPanel.add(lblIntegrantes);
		
		JPanel groupMemberCodePanel = new JPanel();
		groupMemberCodePanel.setLayout(new GridLayout(5,1));
		groupMemberCodePanel.setOpaque(false);
		
		JPanel groupMemberFullNamePanel = new JPanel();
		groupMemberFullNamePanel.setLayout(new GridLayout(5,1));
		groupMemberFullNamePanel.setOpaque(false);
		
		for(GroupMember member:RootData.groupList) {
			groupMemberCodePanel.add(new JLabel(member.getCode()));
			groupMemberFullNamePanel.add(new JLabel(member.getFullName()));
		}
		
		JPanel descriptionPanelContent = new JPanel();
		descriptionPanelContent.setOpaque(false);
		descriptionPanelContent.setBorder(new EmptyBorder(15,15,15,15));
		descriptionPanelContent.setLayout(new BorderLayout(8,8));
		descriptionPanelContent.add(lblIntegrantesPanel, BorderLayout.NORTH);
		descriptionPanelContent.add(groupMemberCodePanel, BorderLayout.WEST);
		descriptionPanelContent.add(groupMemberFullNamePanel, BorderLayout.CENTER);
		
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setOpaque(false);
		descriptionPanel.setLayout(new GridBagLayout());
		descriptionPanel.add(descriptionPanelContent);
		
		// set buttons
		CustomButton showDesignBtn = new CustomButton("Ver diseño");
		showDesignBtn.addActionListener(e->{
			try {
				Desktop.getDesktop().browse(new URI("https://www.figma.com/design/R7jmfXG34h4ximzVEgNyTq/cibertec2-aed-project?node-id=0-1&t=9el9ZPGw6ZwoUqzp-1"));
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
		CustomButton showCodeBtn = new CustomButton("Ver Código");
		showCodeBtn.addActionListener(e->{
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/luisangelsalcedo/cibertec2-aed-project"));
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
		CustomButton showDocumentsBtn = new CustomButton("Ver Informe");
		showDocumentsBtn.addActionListener(e->{
			try {
				Desktop.getDesktop().browse(new URI("https://docs.google.com/document/d/1MOYK_3RIdyp55wnOdbobn4ZKg0vaeFt6MZ7ma0co8Uc/edit?usp=sharing"));
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		});
		
		// set panels
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		topPanel.setOpaque(false);
		topPanel.add(new Brand());		

		JLabel txtVersion = new JLabel();
		txtVersion.setText(RootData.version == null ? "" : "Versión " + RootData.version);
		txtVersion.setForeground(ThemeStyles.$primaryColor);
		JPanel versionPanel = new JPanel();
		versionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		versionPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
		versionPanel.setOpaque(false);
		versionPanel.add(txtVersion);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setOpaque(false);
		centerPanel.add(versionPanel, BorderLayout.NORTH);
		centerPanel.add(textDescription, BorderLayout.CENTER);
		centerPanel.add(descriptionPanel, BorderLayout.SOUTH);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.setOpaque(false);
		bottomPanel.add(showDesignBtn);
		bottomPanel.add(showCodeBtn);
		bottomPanel.add(showDocumentsBtn);
		
		setLayout(new BorderLayout(0, 10));
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
}

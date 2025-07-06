package eparking.utils;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

public class ThemeStyles {
	public static Color $primaryColor;
	public static Color $primaryColorLight;
	public static Color $secondaryColor;
	public static Color $accent;
	public static Color $accentLight;
	public static Color $accentDark;
	public static Color $white;
	public static Color $gray;
	public static Color $dangerColor;
	public static Color $infoColor;
	public static Color $successColor;	
	public static URL favicon;	
	public static Font xsFont;
	public static Font smFont;
	public static Font mdFont;
	public static Font lgFont;
	public static Font xlFont;	
	public static Font xxlFont;	
	public static Font hTableFont;	
	public static DateTimeFormatter dateFormat;
	
	static {
		favicon 		= ThemeStyles.class.getResource(RootData.sourcePath + "favicon.png");
		
		$primaryColor 		= new Color(45, 45, 127);
		$primaryColorLight 	= new Color(115, 115, 169);
		$secondaryColor 	= new Color(0, 0, 0);
		$accent 			= new Color(135, 100, 184);
		$accentLight		= new Color(175, 151, 207);
		$accentDark			= new Color(90, 66, 122);
		$white 				= new Color(255, 255, 255);
		$gray 				= new Color(200, 200, 200);
		$dangerColor 		= new Color(230, 141, 141);
		$infoColor 			= new Color(155, 182, 0);
		$successColor 		= new Color(116, 202, 101);
		
		xsFont = new Font("Tahoma", Font.PLAIN, 8);
		smFont = new Font("Tahoma", Font.PLAIN, 10);
		mdFont = new Font("Tahoma", Font.PLAIN, 12);
		lgFont = new Font("Tahoma", Font.PLAIN, 16);
		xlFont = new Font("Tahoma", Font.PLAIN, 20);
		xxlFont = new Font("Tahoma", Font.BOLD, 22);
		hTableFont = new Font("Tahoma", Font.BOLD, 12);
		
		dateFormat = DateTimeFormatter.ofPattern("HH:mm");
	}
}

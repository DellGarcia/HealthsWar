package br.com.anonymous.frontend;

public class LabelHTML {
	
	public static String html(String txt, String margin) {
		String texto = "<html>"
				+"<div style="
				+ "'text-align: justify;"
				+ "width: 100%;"
				+ "display: flex;"
				+ "flex-flow: wrap;"
				+ "margin: "+margin+"px;"
				+ "flex-direction: row;"
				+ "font-size-adjust: auto;'>"
					+txt+
			"</div>"+
			"</html>";
		
		return texto;
	}
	
	public static String html(String txt) {
		String texto = "<html>"
				+"<div style="
				+ "'text-align: justify;"
				+ "width: 100%;"
				+ "display: flex;"
				+ "flex-flow: wrap;"
				+ "margin: auto;"
				+ "flex-direction: row;"
				+ "font-size-adjust: auto;'>"
					+txt+
			"</div>"+
			"</html>";
		
		return texto;
	}

}

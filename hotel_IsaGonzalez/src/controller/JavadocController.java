package controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class JavadocController {
	@FXML
	private WebView webView;
	private WebEngine engine;

	@FXML
	public void initialize() {}

	public void loadPage() {
		engine = webView.getEngine();
		String path = System.getProperty("user.dir");
		path.replace("\\\\", "/");
		path += "/docs/index.html";
		engine.load("file:///" + path);
	}
}

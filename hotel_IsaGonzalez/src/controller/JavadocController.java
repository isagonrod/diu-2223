package controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class JavadocController {
	@FXML
	private WebView webView;
	private WebEngine engine;

	@FXML
	public void initialize() {
		engine = webView.getEngine();
		loadPage();
	}

	public void loadPage() {
		engine.load("docs/index.html");
	}
}

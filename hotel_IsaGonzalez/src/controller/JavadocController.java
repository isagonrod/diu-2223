package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class JavadocController implements Initializable {
	@FXML
	private WebView webView;
	private WebEngine engine;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		engine = webView.getEngine();
		loadPage();
	}

	// TODO: Para que funcione, hay que añadir --add-modules javafx.web en el VM arguments
	public void loadPage() {
		engine.load("docs/index.html");
	}
}

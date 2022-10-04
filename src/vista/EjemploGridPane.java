package vista;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EjemploGridPane extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            GridPane raiz = new GridPane();
            raiz.setHgap(20);
            raiz.setVgap(20);
            raiz.setPadding(new Insets(20));
            raiz.setAlignment(Pos.CENTER);
            ToggleButton  toogleButtonA,toogleButtonB,toogleButtonC;

            //Inicializamos los botones y agregamos un contenido
            toogleButtonA = new ToggleButton();
            toogleButtonA.setText("Click 1");
            toogleButtonB = new ToggleButton();
            toogleButtonB.setText("Click 2");
            toogleButtonC = new ToggleButton();
            toogleButtonC.setText("Click 3");

            //Establecemos un tamaño mínimo para el ancho y alto de los botones
            toogleButtonA.setMinWidth(130);
            toogleButtonB.setMinWidth(130);
            toogleButtonC.setMinWidth(130);
            toogleButtonA.setMinHeight(40);
            toogleButtonB.setMinHeight(40);
            toogleButtonC.setMinHeight(40);

            //Creamos la primera caja horizontal. Esta debe contener 3 botones no unificados con un grupo toggle
            HBox horizontalBoxTop = new HBox();

            //Le aplicamos espaciado interior para separar la caja de su contenido
            horizontalBoxTop.setPadding(new Insets(5, 5, 5, 5));
            horizontalBoxTop.setSpacing(5);

            //Alineamos el contenido de la caja en una posición TOP_CENTER
            horizontalBoxTop.setAlignment(Pos.TOP_CENTER);
            horizontalBoxTop.getChildren().addAll(toogleButtonA,toogleButtonB,toogleButtonC);
            raiz.add(horizontalBoxTop, 0, 0);
            raiz.setColumnSpan(horizontalBoxTop, 4);

            //Declaramos 3 botones de tipo toggle, los cuales pueden ser agrupados en un toogleGroup
            ToggleButton toogleButton1,toogleButton2,toogleButton3;

            //Declaramos nuestro grupo para botones toggle
            ToggleGroup toggleGroupCenter = new ToggleGroup();

            //Inicializamos los botones y agregamos un contenido
            toogleButton1 = new ToggleButton();
            toogleButton1.setText("Click 4");
            toogleButton2 = new ToggleButton();
            toogleButton2.setText("Click 5");
            toogleButton3 = new ToggleButton();
            toogleButton3.setText("Click 6");

            //Establecemos un tamaño mínimo para el ancho y alto de los botones toggle
            toogleButton1.setMinHeight(30);
            toogleButton2.setMinHeight(30);
            toogleButton3.setMinHeight(30);
            toogleButton1.setMinWidth(130);
            toogleButton2.setMinWidth(130);
            toogleButton3.setMinWidth(130);

            //Adjudicamos un grupo a nuestros botones toggle
            toogleButton1.setToggleGroup(toggleGroupCenter);
            toogleButton2.setToggleGroup(toggleGroupCenter);
            toogleButton3.setToggleGroup(toggleGroupCenter);

            //Creamos la segunda caja horizontal. Esta debe contener 3 botones unificados con un grupo toggle
            HBox horizontalBoxCenter = new HBox();
            //Le aplicamos espaciado interior para separar la caja de su contenido
            horizontalBoxCenter.setPadding(new Insets(-10, 5, 5, 5));
            horizontalBoxCenter.setSpacing(5);
            horizontalBoxCenter.getChildren().addAll(toogleButton1,toogleButton2,toogleButton3);
            raiz.add(horizontalBoxCenter, 0, 1);
            raiz.setColumnSpan(horizontalBoxCenter, 4);

            //Declaramos nuestros botones de tipo radio
            RadioButton radioButtonTop, radioButtonCenter, radioButtonBottom;

            //Declaramos un grupo toggle para dichos botones
            ToggleGroup radioGroup = new ToggleGroup();
            radioButtonTop = new RadioButton("Top");

            //Establecemos una selección por defecto. En este caso la opción por defecto será el botón radio superior
            radioButtonTop.setSelected(true);
            radioButtonCenter = new RadioButton("Center");
            radioButtonBottom = new RadioButton("Bottom");

            //Agrupamos los botones radio a nuestro grupo toggle
            radioButtonTop.setToggleGroup(radioGroup);
            radioButtonCenter.setToggleGroup(radioGroup);
            radioButtonBottom.setToggleGroup(radioGroup);

            //Agregamos los botones
            raiz.add(radioButtonTop, 0, 2);
            raiz.add(radioButtonCenter, 0, 3);
            raiz.add(radioButtonBottom, 0, 4);

            //Declaramos nuestras casillas de verificación
            CheckBox checkBoxTop, checkBoxCenter, checkBoxBottom;
            checkBoxTop = new CheckBox("Top");
            checkBoxCenter = new CheckBox("Center");
            checkBoxBottom = new CheckBox("Bottom");

            //Agregamos las casillas
            raiz.add(checkBoxTop, 1, 2);
            raiz.add(checkBoxCenter, 1, 3);
            raiz.add(checkBoxBottom, 1, 4);

            //Declaramos nuestra caja de elección
            ChoiceBox<String> choiceBox = new ChoiceBox<String>();

            //Agregamos nuestras opciones a nuestra caja
            choiceBox.setItems(FXCollections.observableArrayList("Option 1", "Option 2", "Option 3","Option 4", "Option 5", "Option 6"));

            //Por defecto pondremos la primera opción como opción marcada por defecto
            choiceBox.setValue("Option 1");
            raiz.add(choiceBox, 2, 2);

            //Declaramos nuesta caja combinada. Son iguales que las de elección pero agregan una barra de desplazamiento
            ComboBox<String> comboBox = new ComboBox<String>();

            //Establecemos la cantidad de filas (opciones) visibles. Por ejemplo en 3
            comboBox.setVisibleRowCount(3);

            //Por defecto pondremos la primera opción como opción marcada por defecto
            comboBox.setValue("Option 1");

            //Agregamos nuestras opciones a nuestra caja
            comboBox.setItems(FXCollections.observableArrayList("Option 1", "Option 2", "Option 3","Option 4", "Option 5", "Option 6"));

            raiz.add(comboBox, 3, 2);
            Scene scene = new Scene(raiz,600,400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tarea GripPane <Miguel Ángel Vidal de Blanca>");
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

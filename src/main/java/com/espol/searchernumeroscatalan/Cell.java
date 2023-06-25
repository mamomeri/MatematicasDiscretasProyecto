package com.espol.searchernumeroscatalan;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends StackPane {
    private Label numberLabel;
    private boolean isCatalan;
    private boolean isDerecho;//diferenciar si pertenece al lado derecho o izquierdo
    public Cell(int number,boolean isDerecho) {
        this.isDerecho = isDerecho;
        numberLabel = new Label(String.valueOf(number));
        numberLabel.setStyle("-fx-font-size: 40px;"); // Configurar tamaño de letra

        // Agregar el rectángulo y la etiqueta al StackPane
        getChildren().addAll(numberLabel);

        // Configurar el evento de clic en la etiqueta del número
        numberLabel.setOnMouseClicked(event -> {
            
            // Aquí puedes agregar la lógica adicional que desees realizar al hacer clic en la celda
            if(isDerecho){
                System.out.println("derecha");
            }
            else{
                //lado izquierdo
                System.out.println("izquierda");
                //convertimos el numro de la celda en x, actualizamos el puntaje
                for(int i = 0; i<4;i++){
                    if(Integer.parseInt(numberLabel.getText()) == App.listaNumeros[i]){
                        App.puntosGanables -=1;
                        numberLabel.setText("x");
                        
                    }
                    else{
                    
                    }
                }
                
            }
        });
    }

    public Label getNumberLabel() {
        return numberLabel;
    }

    public boolean isCatalan() {
        return isCatalan;
    }

    public void setCatalan(boolean catalan) {
        isCatalan = catalan;
    }

    // Otros métodos y lógica específica de la celda
    // ...
}

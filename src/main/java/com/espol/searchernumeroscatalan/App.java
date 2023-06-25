package com.espol.searchernumeroscatalan;

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    private GameController gameController;
    public static int[][] mapaNumeros = new int[4][4];
    public static int[] listaNumeros = new int[4];
    public static int puntosGanables = 0; // representa la cantidad de celdas que contienen numeros catalanes

    //label cuenta regresiva
    public Label cuentaRegresiva = new Label("999");
    //label cuenta  puntos
    public Label cuentaPuntos = new Label(String.valueOf(puntosGanables));
    //label mensaje exito o fracaso
    public Label mensaje = new Label("Comienzo!");
    
    
    
    public static void main(String[] args) {
        rellenarlistaNumeros();
        rellenarMapaNumeros();   
        launch(args);
    }
    private static int numeroCatalanCn(int n) {
        int numerador = factorial(2 * n);
        int denominador = factorial(n) * factorial(n);
        return numerador / denominador;
    }

    private static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    private static void rellenarlistaNumeros(){
        //máximo hasta 4
        for (int i = 0; i < 4; i++) {
            listaNumeros[i] = numeroCatalanCn(i);
        }
    }
    private static void rellenarMapaNumeros(){
        Random random = new Random();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                mapaNumeros[row][col] =  random.nextInt(100);
            }
        }
        //incluye aleatoriamente los numeros de catalan asegurando que esten todos
        for (int i = 0; i < 4; i++) {
            int a = random.nextInt(4);
            int b = random.nextInt(4);
            for (int j = 0; j < 4; j++) {
                if(mapaNumeros[a][b] == listaNumeros[j]){
                   a = random.nextInt(4);
                   b = random.nextInt(4);
                }
            }
            mapaNumeros[a][b] = listaNumeros[i];
            puntosGanables +=1;
            System.out.print("Puntos para ganar: ");
            System.out.println(puntosGanables);
        }
    }
    private GridPane createGameBoard() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5); // Espacio horizontal entre las celdas
        gridPane.setVgap(5); // Espacio vertical entre las celdas
        
        
        // Cambiar el color de fondo del GridPane
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREEN, null, null);
        Background background = new Background(backgroundFill);
        gridPane.setBackground(background);

        // Generar el tablero de celdas 4x4
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                
                Cell cell = new Cell(mapaNumeros[row][col],false);
                Label numberLabel = cell.getNumberLabel();

                // Configurar el estilo y los eventos de clic en la celda
                // ...

                // Configurar el tamaño de las celdas
                numberLabel.setMinSize(50, 50); // Establece el tamaño mínimo de la celda
                numberLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Permite que la celda se expanda para llenar el espacio disponible

                // Agregar la etiqueta al GridPane con la configuración de tamaño
                gridPane.add(numberLabel, col, row);
            }
        }

        return gridPane;
    }

    private GridPane createMiniPanel() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5); // Espacio horizontal entre las celdas
        gridPane.setVgap(5); // Espacio vertical entre las celdas
        Random random = new Random();
        
        // Cambiar el color de fondo del GridPane
        BackgroundFill backgroundFill = new BackgroundFill(Color.AZURE, null, null);
        Background background = new Background(backgroundFill);
        gridPane.setBackground(background);

        // Generar el tablero de celdas 4x4
        for (int i = 0; i < 4; i++) {        
                int numeroAleatorio = random.nextInt(100);
                Cell cell = new Cell(listaNumeros[i],true);
                Label numberLabel = cell.getNumberLabel();
                // Configurar el estilo y los eventos de clic en la celda
                // ...
                // Configurar el tamaño de las celdas
                numberLabel.setMinSize(50, 50); // Establece el tamaño mínimo de la celda
                numberLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Permite que la celda se expanda para llenar el espacio disponible
                // Agregar la etiqueta al GridPane con la configuración de tamaño
                gridPane.add(numberLabel, i, 1);    
        }

        return gridPane;
    }

    private void generateGUI(Stage primaryStage) {
        // Crear instancia del controlador
        gameController = new GameController();
        GridPane GameBoard = createGameBoard();
        GridPane MiniPanel =createMiniPanel(); 
        // Crear paneles
        StackPane panelIzquierdo = new StackPane();
        
        StackPane panelDerecho = new StackPane();
       

        
        //label cuenta regresiva
        cuentaRegresiva.setStyle("-fx-font-size: 20px;");
        //label cuenta  puntos
        cuentaPuntos.setStyle("-fx-font-size: 20px;");
        //label mensaje exito o fracaso
        mensaje.setStyle("-fx-font-size: 20px;");

        VBox contenedorDerecho = new VBox(); 
        contenedorDerecho.setPadding(new Insets(30));
        contenedorDerecho.setSpacing(10); 
        contenedorDerecho.getChildren().addAll(MiniPanel, cuentaRegresiva, cuentaPuntos, mensaje);
        panelDerecho.getChildren().add(contenedorDerecho); 
        
        
        VBox contenedorIzquierdo = new VBox(); 
        contenedorIzquierdo.setPadding(new Insets(30));
        contenedorIzquierdo.setSpacing(10); 
        contenedorIzquierdo.getChildren().addAll(GameBoard);
        panelIzquierdo.getChildren().add(contenedorIzquierdo); 
       
        
        
        // Cambiar el color de fondo del panel izquierdo
        BackgroundFill bgFillIzquierdo = new BackgroundFill(Color.BISQUE, null, null);
        Background bgIzquierdo = new Background(bgFillIzquierdo);
        panelIzquierdo.setBackground(bgIzquierdo);
        
        // Cambiar el color de fondo del panel Derecho
        BackgroundFill bgFillDerecho = new BackgroundFill(Color.BURLYWOOD, null, null);
        Background bgDerecho  = new Background(bgFillDerecho );
        panelDerecho.setBackground(bgDerecho);

        // Crear contenedor principal
        SplitPane root = new SplitPane(panelIzquierdo, panelDerecho);

        // Crear escena
        Scene scene = new Scene(root, 800, 600);

        // Establecer título de la ventana
        primaryStage.setTitle("Juego de Números de Catalan");

        // Establecer escena en la ventana
        primaryStage.setScene(scene);

        // Mostrar la ventana
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        generateGUI(primaryStage);
    }
}


module com.espol.searchernumeroscatalan {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.espol.searchernumeroscatalan to javafx.fxml;
    exports com.espol.searchernumeroscatalan;
}

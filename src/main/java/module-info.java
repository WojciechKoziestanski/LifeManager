module io.github.wojciechkoziestanski {
    // Moduły JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Moduły bibliotek
    requires MaterialFX;
    requires java.sql;
    // Moduł Apache PDFBox (w pełni modularny!)
    requires org.apache.pdfbox;

    // Udostępnienie pakietów dla bibliotek (dla refleksji)
    opens io.github.wojciechkoziestanski to javafx.fxml, MaterialFX;
    opens io.github.wojciechkoziestanski.database to MaterialFX, javafx.fxml;
    opens io.github.wojciechkoziestanski.taskplanner to MaterialFX, javafx.fxml;

    // Eksportowanie naszych pakietów
    exports io.github.wojciechkoziestanski;
    exports io.github.wojciechkoziestanski.database;
    exports io.github.wojciechkoziestanski.taskplanner;
}

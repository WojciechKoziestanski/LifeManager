module io.github.wojciechkoziestanski {
    // Te moduły pochodzą z JavaFX (plugin w gradle)
    requires javafx.controls;
    requires javafx.fxml;

    // Ten moduł pochodzi z biblioteki MaterialFX (dependencies w gradle)
    requires MaterialFX;
    requires java.sql;

    // To pozwala bibliotekom "dobrać się" do Twoich klas w trakcie działania programu
    opens io.github.wojciechkoziestanski to javafx.fxml, MaterialFX, com.fasterxml.jackson.databind;

    // To udostępnia Twój kod na zewnątrz
    exports io.github.wojciechkoziestanski;
    exports io.github.wojciechkoziestanski.database;
    opens io.github.wojciechkoziestanski.database to MaterialFX, com.fasterxml.jackson.databind, javafx.fxml;
    exports io.github.wojciechkoziestanski.taskplanner;
    opens io.github.wojciechkoziestanski.taskplanner to MaterialFX, com.fasterxml.jackson.databind, javafx.fxml;
}
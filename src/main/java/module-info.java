module io.github.wojciechkoziestanski {
    // Te moduły pochodzą z JavaFX (plugin w gradle)
    requires javafx.controls;
    requires javafx.fxml;

    // Ten moduł pochodzi z biblioteki MaterialFX (dependencies w gradle)
    requires MaterialFX;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    // To pozwala bibliotekom "dobrać się" do Twoich klas w trakcie działania programu
    opens io.github.wojciechkoziestanski to javafx.fxml, MaterialFX, com.fasterxml.jackson.databind;

    // To udostępnia Twój kod na zewnątrz
    exports io.github.wojciechkoziestanski;
}
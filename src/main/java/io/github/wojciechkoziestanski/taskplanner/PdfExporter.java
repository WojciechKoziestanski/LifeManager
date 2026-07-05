package io.github.wojciechkoziestanski.taskplanner;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PdfExporter {
    public void export(TaskList taskList) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as PDF");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PDF files", "*.pdf")
        );
        fileChooser.setInitialFileName("todolist.pdf");

        java.io.File file = fileChooser.showSaveDialog(new Stage());
        if (file == null) return;

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Dodaj tytuł
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 750);
                String title = "Task List: " + (taskList.getName() != null ? taskList.getName() : "Bez nazwy");
                contentStream.showText(title);
                contentStream.endText();

                // Dodaj zadania
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                float yPosition = 700;
                for (Task task : taskList.getTaskList()) {
                    if (yPosition < 50) {
                        // Zatrzymaj, jeśli nie ma miejsca
                        break;
                    }
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, yPosition);
                    contentStream.showText("• " + task.getName());
                    contentStream.endText();
                    yPosition -= 20;
                }
            }

            document.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

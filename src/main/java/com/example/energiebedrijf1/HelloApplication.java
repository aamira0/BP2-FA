package com.example.energiebedrijf1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    // Variabelen voor tarieven en totaalverbruik
    private double stroomTarief;
    private double gasTarief;
    private double totaalStroomVerbruik = 0;
    private double totaalGasVerbruik = 0;

    // Klasse voor wekelijks verbruik
    public class WeeklyUsage {
        private int weekNumber;
        private double stroomVerbruik;
        private double gasVerbruik;

        // Constructor voor wekelijks verbruik
        public WeeklyUsage(int weekNumber, double stroomVerbruik, double gasVerbruik) {
            this.weekNumber = weekNumber;
            this.stroomVerbruik = stroomVerbruik;
            this.gasVerbruik = gasVerbruik;
        }

        // Getters voor weeknummer, stroomverbruik en gasverbruik
        public int getWeekNumber() {
            return weekNumber;
        }

        public double getStroomVerbruik() {
            return stroomVerbruik;
        }

        public double getGasVerbruik() {
            return gasVerbruik;
        }
    }

    // Lijst om wekelijkse verbruiksgegevens op te slaan
    private List<WeeklyUsage> weeklyUsages = new ArrayList<>();

    // Methode voor het starten van de JavaFX-toepassing
    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();

        // Labels en tekstvelden voor invoer
        Label lblJaarvoorschot = new Label("Jaarlijks Voorschot:");
        Label lblPrijsgas = new Label("Gas Tarief:");
        Label lblPrijsstroom = new Label("Stroom Tarief:");
        Label lblVerbruikStroom = new Label("Wekelijks stroom verbruik (kWh):");
        Label lblVerbruikGas = new Label("Wekelijks gas verbruik (m3):");

        TextField txtJaarvoorschot = new TextField();
        TextField txtPrijsgas = new TextField();
        TextField txtPrijsstroom = new TextField();
        TextField txtVerbruikStroom = new TextField();
        TextField txtVerbruikGas = new TextField();

        // Knoppen voor gegevensopslag en wekelijkse verbruiksopslag
        Button btnSaveYearlyData = new Button("Jaarlijkse Gegevens Opslaan");
        btnSaveYearlyData.setOnAction(event -> saveYearlyData(txtJaarvoorschot.getText(), txtPrijsgas.getText(), txtPrijsstroom.getText()));

        Button btnSaveWeeklyUsage = new Button("Wekelijks Verbruik Opslaan");
        btnSaveWeeklyUsage.setOnAction(event -> {
            try {
                double stroomVerbruik = Double.parseDouble(txtVerbruikStroom.getText());
                double gasVerbruik = Double.parseDouble(txtVerbruikGas.getText());
                String weekNumber = "1"; // Vervang dit door de werkelijke manier om het weeknummer te verkrijgen
                saveWeeklyUsage(String.valueOf(stroomVerbruik), String.valueOf(gasVerbruik), weekNumber);
                addWeeklyUsage(stroomVerbruik, gasVerbruik);
            } catch (NumberFormatException e) {
                System.out.println("Fout: Ongeldige invoer voor verbruik stroom of gas.");
            }
        });

        // Knop voor het tonen van het overzicht
        Button btnShowOverview = new Button("Toon Overzicht");
        btnShowOverview.setOnAction(event -> showOverview());

        // Toevoegen van labels, tekstvelden en knoppen aan het grid
        root.add(lblJaarvoorschot, 0, 0);
        root.add(txtJaarvoorschot, 1, 0);
        root.add(lblPrijsgas, 0, 1);
        root.add(txtPrijsgas, 1, 1);
        root.add(lblPrijsstroom, 0, 2);
        root.add(txtPrijsstroom, 1, 2);
        root.add(btnSaveYearlyData, 1, 3);

        root.add(lblVerbruikStroom, 0, 4);
        root.add(txtVerbruikStroom, 1, 4);
        root.add(lblVerbruikGas, 0, 5);
        root.add(txtVerbruikGas, 1, 5);
        root.add(btnSaveWeeklyUsage, 1, 6);
        root.add(btnShowOverview, 1, 7);

        // Creëren van de JavaFX-scene en tonen van het hoofdvenster
        Scene scene = new Scene(root, 350, 300);
        stage.setTitle("Energiebedrijf - Voorschot en Tarieven");
        stage.setScene(scene);
        stage.show();
    }

    // Methode voor het opslaan van jaarlijkse gegevens
    private void saveYearlyData(String jaarvoorschot, String prijsGas, String prijsStroom) {
        if (!jaarvoorschot.isEmpty() && !prijsGas.isEmpty() && !prijsStroom.isEmpty()) {
            double voorschot = Double.parseDouble(jaarvoorschot);
            gasTarief = Double.parseDouble(prijsGas);
            stroomTarief = Double.parseDouble(prijsStroom);

            // Tonen van opgeslagen jaarlijkse gegevens
            System.out.println("Jaarlijks Voorschot: " + voorschot);
            System.out.println("Gas Tarief: " + gasTarief);
            System.out.println("Stroom Tarief: " + stroomTarief);

            System.out.println("Jaarlijkse gegevens opgeslagen!");
        } else {
            System.out.println("Fout: Jaarlijks voorschot, prijs gas en prijs stroom mogen niet leeg zijn.");
        }
    }

    // Methode voor het opslaan van wekelijks verbruik
    private void saveWeeklyUsage(String verbruikStroom, String verbruikGas, String weekNumber) {
        if (!verbruikStroom.isEmpty() && !verbruikGas.isEmpty()) {
            try {
                double stroomVerbruik = Double.parseDouble(verbruikStroom);
                double gasVerbruik = Double.parseDouble(verbruikGas);

                // Tonen van wekelijks stroom- en gasverbruik
                System.out.println("Wekelijks Stroom Verbruik: " + stroomVerbruik);
                System.out.println("Wekelijks Gas Verbruik: " + gasVerbruik);

                // Toevoegen van wekelijks verbruik aan de lijst
                int weekNumberInt = Integer.parseInt(weekNumber); // converteren van String naar int
                WeeklyUsage weeklyUsage = new WeeklyUsage(weekNumberInt, stroomVerbruik, gasVerbruik);
                weeklyUsages.add(weeklyUsage);

                // Toevoegen van wekelijks verbruik aan het totaal
                addWeeklyUsage(stroomVerbruik, gasVerbruik);

                System.out.println("Wekelijks verbruik opgeslagen!");
            } catch (NumberFormatException e) {
                System.out.println("Fout: Ongeldige invoer voor verbruik stroom of gas.");
            }
        } else {
            System.out.println("Fout: Verbruik stroom en gas mogen niet leeg zijn.");
        }
    }

    // Methode voor het toevoegen van wekelijks verbruik aan het totaal
    private void addWeeklyUsage(double stroomVerbruik, double gasVerbruik) {
        totaalStroomVerbruik += stroomVerbruik;
        totaalGasVerbruik += gasVerbruik;
    }

    // Methode voor het berekenen van wekelijks verbruik
    private double calculateWeeklyUsage(int weekNumber) {
        double totalWeeklyUsage = 0;

        for (WeeklyUsage weeklyUsage : weeklyUsages) {
            if (weeklyUsage.getWeekNumber() == weekNumber) {
                totalWeeklyUsage += weeklyUsage.getStroomVerbruik() + weeklyUsage.getGasVerbruik();
            }
        }

        return totalWeeklyUsage;
    }

    // Methode voor het berekenen van maandelijks verbruik
    private double calculateMonthlyUsage(int monthNumber) {
        double totalMonthlyUsage = 0;

        for (WeeklyUsage weeklyUsage : weeklyUsages) {
            if ((weeklyUsage.getWeekNumber() - 1) / 4 + 1 == monthNumber) {
                totalMonthlyUsage += weeklyUsage.getStroomVerbruik() + weeklyUsage.getGasVerbruik();
            }
        }

        return totalMonthlyUsage;
    }

    // Methode voor het berekenen van jaarlijks verbruik
    private double calculateYearlyUsage() {
        double totalYearlyUsage = 0;

        for (WeeklyUsage weeklyUsage : weeklyUsages) {
            totalYearlyUsage += weeklyUsage.getStroomVerbruik() + weeklyUsage.getGasVerbruik();
        }

        return totalYearlyUsage;
    }

    // Methode voor het tonen van wekelijkse overzichten
    private void showWeeklyOverview(int weekNumber) {
        double weeklyUsage = calculateWeeklyUsage(weekNumber);
        System.out.println("Totaal Verbruik Week " + weekNumber + ": " + weeklyUsage);
    }

    // Methode voor het tonen van maandelijkse overzichten
    private void showMonthlyOverview(int monthNumber) {
        double monthlyUsage = calculateMonthlyUsage(monthNumber);
        System.out.println("Totaal Verbruik Maand " + monthNumber + ": " + monthlyUsage);
    }

    // Methode voor het tonen van jaarlijkse overzichten
    private void showYearlyOverview() {
        double yearlyUsage = calculateYearlyUsage();
        System.out.println("Totaal Verbruik Jaar: " + yearlyUsage);
    }

    // Methode voor het tonen van het volledige overzicht
    private void showOverview() {
        // Tonen van totaal stroom- en gasverbruik
        System.out.println("Totaal Stroom Verbruik: " + totaalStroomVerbruik);
        System.out.println("Totaal Gas Verbruik: " + totaalGasVerbruik);

        // Berekenen en tonen van totale kosten
        double totaleKosten = (totaalStroomVerbruik * stroomTarief) + (totaalGasVerbruik * gasTarief);
        System.out.println("Totale Kosten: " + totaleKosten);

        // Tonen van wekelijkse, maandelijkse en jaarlijkse overzichten
        for (int i = 1; i <= 52; i++) {
            showWeeklyOverview(i);
        }

        for (int i = 1; i <= 12; i++) {
            showMonthlyOverview(i);
        }

        showYearlyOverview();
    }

    // Methode voor het starten van de Java-toepassing
    public static void main(String[] args) {
        launch();
    }
}


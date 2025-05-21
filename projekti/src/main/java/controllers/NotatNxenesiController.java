package controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import models.User;
import repositories.NotatRepository;
import repositories.NxenesitRepository;
import services.UserService;

public class NotatNxenesiController {

    @FXML
    private PieChart PieChart;

    @FXML
    private Button btnRifresko;

    private NxenesitRepository nxenesiRepository = new NxenesitRepository();
    private NotatRepository notaRepository = new NotatRepository();

    private long currentNxenesiId;

    @FXML
    public void initialize() {
        // Merr ID e nxenesit aktual (supozim: UserService ekziston)
        User user = UserService.getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            currentNxenesiId = nxenesiRepository.getNxenesiIdByEmail(email);
        } else {
            currentNxenesiId = 0; // ose ndonjë vlerë default, si -1
        }

        // Ngarko grafikën në fillim
        loadPieChartData();

        // Shto event në butonin Rifresko për rifreskim manual të grafikës
        btnRifresko.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadPieChartData();
            }
        });
    }

    private void loadPieChartData() {
        if (currentNxenesiId <= 0) {
            PieChart.getData().clear();
            return; // Nuk ka nxenes aktiv
        }

        PieChart.getData().clear();

        // Përdor funksionin numriNotavePerNxenesin për nota 1-5
        for (int nota = 1; nota <= 5; nota++) {
            int count = notaRepository.numriNotavePerNxenesin(nota, currentNxenesiId);

            // Shto në PieChart vetëm nëse ka të dhëna
            if (count > 0) {
                PieChart.Data slice = new PieChart.Data("Nota " + nota, count);
                PieChart.getData().add(slice);
            }
        }
    }
}

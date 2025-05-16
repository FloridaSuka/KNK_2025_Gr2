package views;

<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.control.*?>
<?import javafx.scene.image.Image?>
<?import javafx.scene.image.ImageView?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.text.Font?>

<AnchorPane maxHeight="500.0" maxWidth="800.0" prefHeight="600.0" prefWidth="800.0" style="-fx-background-color: #1c2b36;" xmlns="http://javafx.com/javafx/23.0.1" xmlns:fx="http://javafx.com/fxml/1" fx:controller="controllers.NxenesiController">

    <children>
        <!-- Header -->
        <HBox layoutX="20" layoutY="20" spacing="10">
            <ImageView fitHeight="63.0" fitWidth="63.0" pickOnBounds="true" preserveRatio="true">
                <image>
                    <Image url="@../images/ikona.png" />
                </image>
            </ImageView>
            <Label style="-fx-font-size: 24px; -fx-text-fill: white;" text="Menaxhimi i Nxënësve">
                <font>
                    <Font size="27.0" />
                </font>
            </Label>
        </HBox>

        <Separator layoutX="5.0" layoutY="84.0" prefWidth="1150" />

        <!-- Paneli i Majtë: Regjistrimi i Nxënësit -->
        <VBox layoutX="37.0" layoutY="222.0" prefHeight="311.0" prefWidth="336.0" spacing="10" style="-fx-background-color: #2c3e50; -fx-padding: 20px; -fx-border-radius: 10px;">
            <Label style="-fx-font-size: 18px; -fx-text-fill: white;" text="Shto Nxënës të Ri" />
            <TextField fx:id="txtEmri" promptText="Emri" />
            <TextField fx:id="txtMbiemri" promptText="Mbiemri" />
            <TextField fx:id="txtKlasa" promptText="Klasa" />
            <TextField fx:id="txtEmail" promptText="Email" />
            <HBox spacing="10">
                <Button onAction="#shtoNxenes" style="-fx-background-color: #e67e22; -fx-text-fill: white;" text="Shto" />
                <Button onAction="#pastroFushat" style="-fx-background-color: #2ecc71; -fx-text-fill: white;" text="Pastro" />
            </HBox>
        </VBox>

        <!-- Paneli i Djathtë: Lista dhe Opsionet -->
        <VBox layoutX="422.0" layoutY="222.0" prefHeight="311.0" prefWidth="755.0" spacing="10" style="-fx-background-color: #2c3e50; -fx-padding: 20px; -fx-border-radius: 10px;">
            <Label style="-fx-font-size: 18px; -fx-text-fill: white;" text="Lista e Nxënësve" />
            <ListView fx:id="listaNxenesve" prefHeight="200.0" prefWidth="700.0" />
            <HBox spacing="10">
                <Button onAction="#perditesoNxenes" style="-fx-background-color: #3498db; -fx-text-fill: white;" text="Përditëso" />
                <Button onAction="#fshijNxenes" style="-fx-background-color: #e74c3c; -fx-text-fill: white;" text="Fshij" />
                <Button onAction="#eksportoNxenesit" style="-fx-background-color: #9b59b6; -fx-text-fill: white;" text="Eksporto" />
            </HBox>
        </VBox>

        <!-- Kërkimi -->
        <TextField fx:id="txtKerkim" layoutX="13.0" layoutY="104.0" promptText="Kërko Nxënës..." />
        <Button layoutX="205.0" layoutY="104.0" onAction="#kerkoNxenes" text="🔎" />

        <!-- Ora dhe Data -->
        <Label fx:id="lblData" layoutX="1012.0" layoutY="103.0" prefHeight="63.0" prefWidth="107.0" style="-fx-text-fill: white; -fx-font-size: 14px;" text="Data: 00-00-0000" />
        <Label fx:id="lblOra" layoutX="904.0" layoutY="109.0" prefHeight="51.0" prefWidth="81.0" style="-fx-text-fill: white; -fx-font-size: 14px;" text="Ora: 00:00:00" />

        <!-- Gjuha -->
        <MenuButton fx:id="menuLanguage" layoutX="904.0" layoutY="29.0" prefHeight="41.0" prefWidth="119.0" text="Gjuha">
            <items>
                <MenuItem fx:id="menuAL" text="ALB" />
                <MenuItem fx:id="menuEN" text="ENG" />
            </items>
        </MenuButton>
    </children>
</AnchorPane>

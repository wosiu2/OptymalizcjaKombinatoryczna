/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Data.City;
import Data.Coordinates;
import Data.Graph;
import Tools.Exceptions.EmptyStringException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Włosek
 */
public class OpenFromFile {

    private DecimalFormat df = new DecimalFormat("#0.00");

    public int openFromFile(String filePath, Graph g) {
        FileReader fr = null;
        String line = "";
        String[] tempTable = new String[2];
        int i = 0;

        //Otwieranie pliku
        try {
            fr = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Błąd przy otwieraniu pliku\n\n Error:" + e);
            System.exit(1);
        }

        BufferedReader br = new BufferedReader(fr);
        //Odczyt kolejnych lini
        try {
            while ((line = br.readLine()) != null) {

                tempTable = refactorLine(line);
                g.addCity(new City("Miasto" + i, new Coordinates(Double.parseDouble(tempTable[0]), Double.parseDouble(tempTable[1]))));
                i++;
                //System.out.println(tempTable[0]);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Błąd odczytu pliku\n\nError: " + e);
            System.exit(2);
        } catch (EmptyStringException e) {

            JOptionPane.showMessageDialog(null, "Błąd odczytu lini\n\nError: " + e);
            System.exit(4);
        }

        //zamykanie pliku
        try {
            fr.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Błąd przy zamykaniu pliku\n\nError: " + e);
        }
        return 0;

    }

    /**
     *
     * @param line ciag znaków który ma uledz podziałowi
     * @return zwraca tablicę 4 elementową z parametrami do wprowadzenia
     * @throws EmptyStringException
     */
    private String[] refactorLine(String line) throws EmptyStringException {
        String[] tableOfParameters = new String[2];
        int startIndex = 0;
        int stopIndex = 0;
        int parametersCounter = 0;

        if (line == null) {

            throw new EmptyStringException();

        }

        for (int i = 0; i < line.length(); i++) {

            if (line.substring(i, i+1).equals(" ")) {

                tableOfParameters[parametersCounter] = line.substring(startIndex, stopIndex);
                startIndex = i + 1;
                parametersCounter++;
            }

            stopIndex++;

            if (i == line.length() - 1) {
                tableOfParameters[parametersCounter] = line.substring(startIndex, stopIndex);
            }

        }

        return tableOfParameters;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        OpenFromFile o = new OpenFromFile();

        o.openFromFile("D:/Dane.txt", g);

    }

}

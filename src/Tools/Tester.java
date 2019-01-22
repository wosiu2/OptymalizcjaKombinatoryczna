/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import ControlPackage.BB;
import ControlPackage.BruteForce;
import ControlPackage.BruteForceMod;
import ControlPackage.Greed;
import ControlPackage.Little;
import ControlPackage.MSTAproximation;
import Data.Results;
import Data.TSP;

import javax.swing.SwingWorker;

/**
 *
 * @author Włosek
 */
public class Tester extends SwingWorker<Object, Object> {

    /**
     *
     * @param graph
     * @param alg
     * @param path
     */
    public void runTest(double[][] graph, TSP alg, String path) {

        double start, end, time;
        Results res;
        String input;

        SaveToFile save = new SaveToFile();

        start = System.currentTimeMillis();

        res = alg.startAlgorithm(graph);

        end = System.currentTimeMillis();

        time = (end - start);

        input = String.valueOf(graph.length) + " " + String.valueOf(time) + " " + String.valueOf(res.getLengthOfRoad());

        save.stringToFile(path, input, true, true);
        // System.out.println(graph.length);
        // System.out.println(res.getLengthOfRoad());
        //System.out.println(time);

    }

    /**
     * Metoda wykonująca serię testów. Wyniki testów zapisywane są do pliku
     * podanego w ściezce path.
     *
     * @param alg algorytm rozwiązujący problem TSP
     * @param path ścieżka zapisu pliku z wynikami
     * @param start startowa ilość węzłów grafu
     * @param interval liczba o ile ma zostać zwiększona ilość węzłów
     * @param number liczba krotności zwiększania ilości węzłów
     * @param probe ilość prób dla każdej ilości węzłów
     *
     *
     */
    public void runTests(TSP alg, String path, int start, int interval, int number, int probe) {
        CreateWorld w;

        for (int i = 0; i < number; i++) {

            for (int j = 0; j < probe; j++) {
                w = new CreateWorld();

                w.generate(start + i * interval, 400, 400);
                runTest(w.getWorld().getGraphOfCities(), alg, path);

            }

        }

    }

    public void runComTest(int start, int interval, int number, int probe, boolean BF, String pathBF, boolean BB, String pathBB, boolean G, String pathG, boolean MST, String pathMST, boolean LITTLE, String pathLITTLE) {
        CreateWorld w;
        SaveToFile save = new SaveToFile();
        if (BF == true) {
            save.stringToFile(pathBF, "TEST POPRAWNOŚCI", true, true);
        }
        if (BB == true) {

            save.stringToFile(pathBB, "TEST POPRAWNOŚCI", true, true);
        }
        if (G == true) {
            save.stringToFile(pathG, "TEST POPRAWNOŚCI", true, true);
        }
        if (MST == true) {
            save.stringToFile(pathMST, "TEST POPRAWNOŚCI", true, true);
        }
        if (LITTLE == true) {
            save.stringToFile(pathLITTLE, "TEST POPRAWNOŚCI", true, true);
        }

        for (int i = 0; i < number; i++) {

            for (int j = 0; j < probe; j++) {
                w = new CreateWorld();

                w.generate(start + i * interval, 400, 400);
                if (BF == true) {
                    BruteForce brute = new BruteForce();
                    runTest(w.getWorld().getGraphOfCities(), brute, pathBF);
                }
                if (BB == true) {
                    BB bb = new BB();
                    runTest(w.getWorld().getGraphOfCities(), bb, pathBB);
                }
                if (G == true) {
                    Greed gread = new Greed();
                    runTest(w.getWorld().getGraphOfCities(), gread, pathG);
                }
                if (MST == true) {
                    MSTAproximation mst = new MSTAproximation();
                    runTest(w.getWorld().getGraphOfCities(), mst, pathMST);
                }
                if (LITTLE == true) {
                    Little little = new Little();
                    runTest(w.getWorld().getGraphOfCities(), little, pathLITTLE);
                }
            }

        }

    }

    @Override
    protected Object doInBackground() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

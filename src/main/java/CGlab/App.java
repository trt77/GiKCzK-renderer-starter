package CGlab;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    String version = "0.02";

    /*
        W celu uruchomienia aplikacji należy zainstalować gradle
        $gradle -v >>Gradle 8.1
        uruchamiać program przez cmd, w obecnym commicie:
        $gradle run --args="NAZWA_PLIKU SZEROKOŚĆ WYSOKOŚĆ RODZAJ_ALGORYTMU_RYSOWANIA_LINII"
        dotychczas zaimplementowany algorytm: NAIVE

        plik domyślnie zapisywany jest w folderze użytkownika
        w przypadku git add . w Windows
        $git config --global core.autocrlf true
     */
    public static void main(String[] args) {

        String filepath = args[0];
        int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);
        String lineAlgo = args[3];
        Renderer mainRenderer = new Renderer(filepath, width, height, lineAlgo);
        mainRenderer.clear();
        /*
            rysuje punkt...
         */
        // mainRenderer.drawPoint(width/2, height/2);

        /*
            rysuje linię (test NAIVE))...
         */
        //mainRenderer.drawLine(30, 30, 60, 60, Renderer.LineAlgo.valueOf(lineAlgo));

        /*
            rysuje linię (test BRESENHAM, dla każdego z oktantów)...
         */
        mainRenderer.drawLine(200, 200, 300,280 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,280 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,230 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100, 180, Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,130 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,130 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,180 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,230 , Renderer.LineAlgo.valueOf(lineAlgo));
        // kod działa poprawnie dla przykładów w oktancie 7 i 8

        /*
            rysuje linię (test BRESENHAM_INT, dla każdego z oktantów)...
         */
        mainRenderer.drawLine(200, 200, 300,280 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,280 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,230 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100, 180, Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,130 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,130 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,180 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,230 , Renderer.LineAlgo.valueOf(lineAlgo));
        //kod działa poprawnie dla przykładów w oktancie 7, 8, 3, 4


        try {
            mainRenderer.save();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getVersion() {
	return this.version;
    }
}

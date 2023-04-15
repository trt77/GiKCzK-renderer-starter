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

        w przypadku git add . w Windows
        $git config --global core.autocrlf true
     */
    public static void main(String[] args) {

        String filepath = args[0];
        int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);
        String lineAlgo = args[3];
        Renderer mainRenderer = new Renderer(filepath, width, height);
        mainRenderer.clear();
        // mainRenderer.drawPoint(width/2, height/2);

        /*
            rysuje linię używając algorytmu naiwnego...
         */
        mainRenderer.drawLine(30, 30, 60, 60, Renderer.LineAlgo.valueOf(lineAlgo));
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

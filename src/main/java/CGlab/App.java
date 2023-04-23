package CGlab;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    String version = "0.02";

    /*
        W celu uruchomienia aplikacji należy zainstalować gradle
        $gradle -v >>Gradle 8.1
        uruchamiać program przez cmd, w obecnym commicie:
        $gradle run --args="NAZWA_PLIKU SZEROKOŚĆ WYSOKOŚĆ"

        w obecnym commicie: rysowanie trójkąta dzięki współrzędnym barycentrycznym
        plik domyślnie zapisywany jest w folderze USER_HOME użytkownika
        w przypadku git add . w Windows
        $git config --global core.autocrlf true
     */
    public static void main(String[] args) {

        String filepath = args[0];
        int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);
        Renderer mainRenderer = new Renderer(filepath, width, height);
        mainRenderer.clear();


        //---------TESTOWE WYWOLANIA METOD-------


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
        /*mainRenderer.drawLine(200, 200, 300,280 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,280 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,230 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100, 180, Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,130 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,130 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,180 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,230 , Renderer.LineAlgo.valueOf(lineAlgo));*/
        // kod działa poprawnie dla przykładów w oktancie 7 i 8

        /*
            rysuje linię (test BRESENHAM_INT, dla każdego z oktantów)...
         */
        /*mainRenderer.drawLine(200, 200, 300,280 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,280 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,230 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100, 180, Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 100,130 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,130 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,180 , Renderer.LineAlgo.valueOf(lineAlgo));
        mainRenderer.drawLine(200, 200, 300,230 , Renderer.LineAlgo.valueOf(lineAlgo));*/
        //kod działa poprawnie dla przykładów w oktancie 7, 8, 3, 4

        /*
            rysuje trójkąt (z wykorzystaniem punktów A, B, C,
            bez parametru koloryzującego — niezaimplementowany w tym commicie
         */
        /*int x1 = 340;
        int y1 = 40;    //punkt A

        int x2 = 370;
        int y2 = 40;    //punkt B

        int x3 = 355;
        int y3 = 60;   //punkt C

        var vecA = new Vec2f(x1, y1);
        var vecB = new Vec2f(x2, y2);
        var vecC = new Vec2f(x3, y3);

        mainRenderer.drawTriangle(vecA, vecB, vecC);*/

        /*
            rysowanie trójkąta z parametrem koloryzującym
         */

        int x1 = 340;
        int y1 = 40;    //punkt A

        int x2 = 370;
        int y2 = 40;    //punkt B

        int x3 = 355;
        int y3 = 60;   //punkt C

        var vecA = new Vec2f(x1, y1);
        var vecB = new Vec2f(x2, y2);
        var vecC = new Vec2f(x3, y3);

        mainRenderer.drawTriangle(vecA, vecB, vecC, new Vec3f(200,50 , 100));




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

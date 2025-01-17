package CGlab;

// ścieżka

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.lang.Math;

public class Renderer {


    protected int getWidth() {
        return width;
    }

    protected int getHeight() {
        return height;
    }

    public enum LineAlgo { NAIVE, DDA, BRESENHAM, BRESENHAM_INT }

    public BufferedImage render;

    public int height;
    public int width;

    private String filename;
    private String lineAlgo;
    public Renderer(String imageName, int width, int height, String lineAlgo) {
        this.width = width;
        this.height = height;
        render = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.filename = System.getProperty("user.home") + "/" + imageName;
        this.lineAlgo = lineAlgo;
    }

    public Renderer(String imageName, int width, int height) {
        this.width = width;
        this.height = height;
        render = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.filename = System.getProperty("user.home") + "/" + imageName;
    }

    public Renderer(String imageName, int width, int height, Vec3f vec3f) {
        this.width = width;
        this.height = height;
        render = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.filename = System.getProperty("user.home") + "/" + imageName;
    }

    public void drawPoint(int x, int y) {
        int white = 255 | (255 << 8) | (255 << 16) | (255 << 24);
        render.setRGB(x, y, white);
    }

    public void drawLine(int x0, int y0, int x1, int y1, LineAlgo lineAlgo) {
        if(lineAlgo == LineAlgo.NAIVE) drawLineNaive(x0, y0, x1, y1);
        if(lineAlgo == LineAlgo.DDA) drawLineDDA(x0, y0, x1, y1);
        if(lineAlgo == LineAlgo.BRESENHAM) drawLineBresenham(x0, y0, x1, y1);
        if(lineAlgo == LineAlgo.BRESENHAM_INT) drawLineBresenhamInt(x0, y0, x1, y1);
    }

    public void drawLineNaive(int x1, int y1, int x2, int y2) {

        /*

        Współczynnik kierunkowy można obliczyć
        korzystając ze współrzędnych dwóch różnych punktów (x1, y1) i (x2, y2)
        należących do prostej, według następującego wzoru:

        m = (y2 - y1) / (x2 - x1)

         */

        double m = (double) (y2 - y1) / (x2 - x1);

        do {
            drawPoint(x1, y1);
            x1++;
            y1 = (int) Math.round(y1 + m);
            drawPoint(x1, y1);
        } while (x1 != x2 && y1 != y2);

    }

    public void drawLineDDA(int x0, int y0, int x1, int y1) {
        // TODO: zaimplementuj
    }

    public void drawLineBresenham(int x0, int y0, int x1, int y1) {
        int white = 255 | (255 << 8) | (255 << 16) | (255 << 24);

        int dx = x1-x0;
        int dy = y1-y0;
        float derr = Math.abs(dy/(float)(dx));
        float err = 0;

        int y = y0;

        for (int x=x0; x<=x1; x++) {
            render.setRGB(x, y, white);
            err += derr;
            if (err > 0.5) {
                y += (y1 > y0 ? 1 : -1);
                err -= 1.;
            }
        } // Oktanty: 7, 8
    }

    public void drawLineBresenhamInt(int x0, int y0, int x1, int y1) {
        int white = 255 | (255 << 8) | (255 << 16) | (255 << 24);

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        int err = dx - dy;
        int err2;

        while (true) {
            render.setRGB(x0, y0, white);

            if (x0 == x1 && y0 == y1) {
                break;
            }

            err2 = 2 * err;

            if (err2 > -dy) {
                err -= dy;
                x0 += sx;
            }

            if (err2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    public void save() throws IOException {
        File outputfile = new File(filename);
        render = Renderer.verticalFlip(render);
        ImageIO.write(render, "png", outputfile);
    }

    public void clear() {
        for (int x = 0; x < render.getWidth(); x++) {
            for (int y = 0; y < render.getHeight(); y++) {
                int black = 0 | (0 << 8) | (0 << 16) | (255 << 24);
                render.setRGB(x, y, black);
            }
        }
    }

    public static BufferedImage verticalFlip(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage flippedImage = new BufferedImage(w, h, img.getColorModel().getTransparency());
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
        g.dispose();
        return flippedImage;
    }

    /*public Vec3f barycentric(Vec2f A, Vec2f B, Vec2f C, Vec2f P) {
        Vec3f v1 = new Vec3f(B.x - A.x, C.x - A.x, P.x - A.x);

        Vec3f v2 = new Vec3f(B.y - A.y, C.y - A.y, P.y - A.y);

        Vec3f cross = vectorsMultiplied(v1, v2);

        Vec2f uv = new Vec2f(cross.x/cross.z, cross.y/cross.z);

        Vec3f barycentric = new Vec3f(uv.x, uv.y, 1 - uv.x - uv.y);

        return barycentric;
    }*/

    /*public Vec3f barycentric(Vec3i A, Vec3i B, Vec3i C, Vec2f P) {
        Vec3f v1 = new Vec3f(B.x - A.x, C.x - A.x, P.x - A.x);

        Vec3f v2 = new Vec3f(B.y - A.y, C.y - A.y, P.y - A.y);

        Vec3f cross = vectorsMultiplied(v1, v2);

        Vec2f uv = new Vec2f(cross.x / cross.z, cross.y / cross.z);

        Vec3f barycentric = new Vec3f(uv.x, uv.y, 1 - uv.x - uv.y);

        return barycentric;
    }*/

    public Vec3f barycentric(Vec2i A, Vec2i B, Vec2i C, Vec2f P) {
        Vec3f v1 = new Vec3f(B.x - A.x, C.x - A.x, P.x - A.x);

        Vec3f v2 = new Vec3f(B.y - A.y, C.y - A.y, P.y - A.y);

        Vec3f cross = vectorsMultiplied(v1, v2);

        Vec2f uv = new Vec2f(cross.x/cross.z, cross.y/cross.z);

        Vec3f barycentric = new Vec3f(uv.x, uv.y, 1 - uv.x - uv.y);

        return barycentric;
    }

    public Vec3f vectorsMultiplied(Vec3f v1, Vec3f v2) {
        float x = v1.y*v2.z - v1.z*v2.y;
        float y = v1.z*v2.x - v1.x*v2.z;
        float z = v1.x*v2.y - v1.y*v2.x;

        return new Vec3f(x, y, z);
    }

    /*
        rysowanie trójkątów na potrzeby lab 1-3 (bez zaimplementowanego bounding-box)
     */
    /*public void drawTriangle(Vec2f A, Vec2f B, Vec2f C, Vec3f colour) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Vec2f P = new Vec2f(x, y);
                Vec3f barycentric = barycentric(A, B, C, P);

                if (barycentric.x >= 0 && barycentric.y >= 0 && barycentric.z >= 0) {
                    int colr = 255 | ((int)colour.x%255 << 8) | ((int)colour.y%255 << 16) | ((int)colour.z%255 << 24);
                    render.setRGB(x, y, colr);
                }
            }
        }
    }*/


    /*
        rysowanie trójkątów na potrzeby Lab04 (z zaimplementowanym bounding-box), bez testu z-buffora.
     */
    /*public void drawTriangle(Vec2i A, Vec2i B, Vec2i C, int colour) {

        float minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

        if (A.x > maxX) maxX = A.x;
        if (A.x < minX) minX = A.x;
        if (A.y > maxY) maxY = A.y;
        if (A.y < minY) minY = A.y;

        if (B.x > maxX) maxX = B.x;
        if (B.x < minX) minX = B.x;
        if (B.y > maxY) maxY = B.y;
        if (B.y < minY) minY = B.y;

        if (C.x > maxX) maxX = C.x;
        if (C.x < minX) minX = C.x;
        if (C.y > maxY) maxY = C.y;
        if (C.y < minY) minY = C.y;

        for (int x = (int)minX; x < maxX; x++) {
            for (int y = (int)minY; y < maxY; y++) {
                Vec2f P = new Vec2f(x, y);
                Vec3f barycentric = barycentric(A, B, C, P);

                if (barycentric.x >= 0 && barycentric.y >= 0 && barycentric.z >= 0) {
                    render.setRGB(x, y, colour);
                }
            }
        }
    }*/

    /*
        rysowanie trójkątów na potrzeby Lab04 (bez bounding-box), bez testu z-buffora.
     */
    public void drawTriangle(Vec2i A, Vec2i B, Vec2i C, int colour) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Vec2f P = new Vec2f(x, y);
                Vec3f barycentric = barycentric(A, B, C, P);

                if (barycentric.x >= 0 && barycentric.y >= 0 && barycentric.z >= 0) {
                    render.setRGB(x, y, colour);
                }
            }
        }
    }

}

package CGlab;

import java.awt.Color;

public class FlatShadingRenderer {
    private double[] lightDirection;

    public FlatShadingRenderer(double[] lightDirection) {
        this.lightDirection = normalize(lightDirection);
    }

    private double[] normalize(double[] v) {
        double norm = Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
        if (norm == 0) {
            return v;
        }
        return new double[]{v[0] / norm, v[1] / norm, v[2] / norm};
    }

    public Color calculateColor(double[] normal) {
        double intensity = dot(normal, lightDirection);
        intensity = Math.max(0, Math.min(1, intensity));
        return new Color((int) (255 * intensity), (int) (255 * intensity), (int) (255 * intensity));
    }

    private double dot(double[] a, double[] b) {
        return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
    }
}
package view;

import javafx.event.EventHandler;
import javafx.scene.chart.NumberAxis;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class HandleChart {
    private final CreateChart createChart;

    public HandleChart(CreateChart createChart) {
        this.createChart = createChart;
    }

    void handleScrollChart(ScrollEvent event) {
        NumberAxis abscisseLabel = createChart.getMainView().abscisseLabel;
        NumberAxis ordonneLabel = createChart.getMainView().ordonneLabel;
        double minScale = 1;
        double maxScale = 1.2;
        double scale = abscisseLabel.getScale();
        scale = clamp(scale, minScale, maxScale);
        if (event.isAltDown()) {
            scale = scale / 1.5d;
        }
        abscisseLabel.setAutoRanging(false);
        abscisseLabel.setLowerBound(0);
        abscisseLabel.setUpperBound(abscisseLabel.getUpperBound() / scale);
        abscisseLabel.setTickUnit(abscisseLabel.getTickUnit() / scale);
        ordonneLabel.setAutoRanging(false);
        ordonneLabel.setLowerBound(0);
        ordonneLabel.setUpperBound(ordonneLabel.getUpperBound() / scale);
        ordonneLabel.setTickUnit(ordonneLabel.getTickUnit() / scale);
    }

    void mouseOverGlow(NumberAxis n){
        final Effect glow = new DropShadow(10, Color.GOLDENROD);
        n.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                n.setEffect(glow);
            }
        });
        n.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                n.setEffect(null);
            }
        });
    }

    public static double clamp(double value, double min, double max) {

        if (Double.compare(value, min) < 0)
            return min;

        if (Double.compare(value, max) > 0)
            return max;

        return value;
    }
}
package both;


import javafx.scene.paint.Color;

/**
 * Enum des couleurs, chaque point à en fonction de classe une couleur différente
 */
public enum ClassColor {
    RED(Color.RED), BLUE(Color.BLUE), GREEN(Color.GREEN), NULL(Color.BLACK);


    private final Color color;

    ClassColor(final Color color) {
        this.color = color;
    }

    // setosa = red         |
    // versicolor = blue    |           false = red
    // virginica = green    |           true = blue
    // default = black      |

    public Color getColor() {
        return color;
    }
}

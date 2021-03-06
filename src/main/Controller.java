package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import sorting.*;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static javafx.animation.Animation.INDEFINITE;

public class Controller implements Initializable {
    private Timeline timeline;
    private List<Integer> values = new ArrayList<>();
    private final Random random = new Random();
    private Iterator<Change> changes;
    private final Map<String, SortingAlgorithm> sortingAlgorithmMap = new HashMap<>();
    private GraphicsContext graphicsContext;
    private double blockWidth = 0;
    private long time;
    private final DecimalFormat decimalFormat = new DecimalFormat("###,###.##");

    @FXML
    public Canvas canvas;

    @FXML
    public ComboBox<String> sortingAlgorithms;

    @FXML
    public TextField fps;

    @FXML
    public TextField numValues;

    @FXML
    public Label timeLabel;

    public Controller() {
        sortingAlgorithmMap.put("Bogo sort", new BogoSort());
        sortingAlgorithmMap.put("Insertion sort", new InsertionSort());
        sortingAlgorithmMap.put("Quick sort", new QuickSort());
        sortingAlgorithmMap.put("Merge sort", new MergeSort());
        sortingAlgorithmMap.put("Heap sort", new HeapSort());
        sortingAlgorithmMap.put("Selection sort", new SelectionSort());
        sortingAlgorithmMap.put("Shell sort", new ShellSort());
        sortingAlgorithmMap.put("Bubble sort", new BubbleSort());
        sortingAlgorithmMap.put("Cycle sort", new CycleSort());
        sortingAlgorithmMap.put("Cocktail sort", new CocktailSort());
        sortingAlgorithmMap.put("Comb sort", new CombSort());
        sortingAlgorithmMap.put("Gnome sort", new GnomeSort());
        sortingAlgorithmMap.put("Odd-even sort", new OddEvenSort());
    }

    public void start() {
        final SortingAlgorithm sortingAlgorithm = sortingAlgorithmMap.get(sortingAlgorithms.getValue());
        if (timeline != null) {
            timeline.stop();
        }

        fillArray();
        graphicsContext.setFill(Color.BLACK);
        changes = sortingAlgorithm.sort(new ArrayList<>(values)).iterator();

        time = System.currentTimeMillis();

        final String fpsString = fps.getText();
        int iterations = 0;
        if (!fpsString.equals("")) {
            iterations = Integer.parseInt(fpsString);
        }
        if (iterations == 0) {
            iterations = 1;
            fps.setText("1");
        }

        timeline = new Timeline(new KeyFrame(Duration.millis(1000d / iterations), e -> loop()));
        timeline.setCycleCount(INDEFINITE);
        timeline.play();
    }

    private void fillArray() {
        final int height = (int) canvas.getHeight();
        final String iterationString = numValues.getText();
        int elements = 0;
        if (!iterationString.equals("")) {
            elements = Integer.parseInt(iterationString);
        }

        if (elements == 0) {
            elements = 10;
            numValues.setText("10");
        }

        blockWidth = canvas.getWidth() / elements;

        values = IntStream.range(0, elements)
                .map(i -> random.nextInt(height))
                .boxed()
                .collect(Collectors.toList());
    }

    private void loop() {
        try {
            final Change change = changes.next();

            graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            Paint paint;

            switch (change.getChangeType()) {
                case READ:
                    paint = Color.GREEN;
                    break;
                case COMPARE:
                    paint = Color.BLUE;
                    break;
                case WRITE:
                    paint = Color.YELLOW;
                    values.set(change.getNumber1(), change.getValue());
                    break;
                case SWAP:
                    paint = Color.RED;

                    final int temp = values.get(change.getNumber1());
                    values.set(change.getNumber1(), values.get(change.getNumber2()));
                    values.set(change.getNumber2(), temp);
                    break;
                default:
                    paint = Color.BLACK;
            }


            for (int i = 0; i < values.size(); i++) {
                final int value = values.get(i);
                if (i == change.getNumber1() || i == change.getNumber2()) {
                    graphicsContext.setFill(paint);
                } else {
                    graphicsContext.setFill(Color.BLACK);
                }
                graphicsContext.fillRect(i * blockWidth, canvas.getHeight() - value, blockWidth, value);
            }

            timeLabel.setText(decimalFormat.format((System.currentTimeMillis() - time) / 1000d));
        } catch (final NoSuchElementException ex) {
            timeline.stop();
        }
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final List<String> sortedKeys = Arrays.asList(sortingAlgorithmMap.keySet().toArray(new String[0]));
        Collections.sort(sortedKeys);
        sortingAlgorithms.getItems().addAll(sortedKeys);
        sortingAlgorithms.setValue(sortedKeys.get(0));

        graphicsContext = canvas.getGraphicsContext2D();

        numValues.setText("50");
        numValues.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}")) {
                numValues.setText(oldValue);
            }
        });

        fps.setText("10");
        fps.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}")) {
                fps.setText(oldValue);
            }
        });
    }
}

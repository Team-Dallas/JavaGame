package display;

import javax.swing.*;

public class ProgressBar extends JPanel {
    private JProgressBar progressBar;

    public ProgressBar() {
        this.progressBar = new JProgressBar();
        this.progressBar.setString("Loading...");
        this.progressBar.setMinimum(0);
        this.progressBar.setMaximum(100);
        this.progressBar.setValue(0);
        this.progressBar.setStringPainted(true);
        add(this.progressBar);
    }

    public void updateProgress(int value) {
        this.progressBar.setValue(value);
    }
}

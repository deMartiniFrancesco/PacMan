package Grafica;

import Main.Game;
import Main.States;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;

public class MainFrame extends JFrame {

    private Runnable operation;
    private final Game game = Game.istance;

    public MainFrame(String title) throws HeadlessException {
        super(title);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (operation != null) {
                    operation.run();
                }
                dispose();
            }
        });

        draw();

    }

    public void setCloseOperation(Runnable operation) {
        this.operation = operation;
    }


    public void draw() {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException ignored) {
            }
            setCloseOperation(() -> game.actionListener.performAction(States.FINISH));
            setLayout(new BorderLayout());
            pack();
            setVisible(true);
        });
    }


    public void addComponent(Component component) {
        add(component);
        setSize(component.getPreferredSize());
        setLocationRelativeTo(null);
        paint(getGraphics());
    }
}
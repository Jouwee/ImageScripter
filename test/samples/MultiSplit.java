/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samples;

import com.jouwee.proto.Application;
import com.jouwee.proto.gui.JMultiSplit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

/**
 *
 * @author Jouwee
 */
public class MultiSplit {

    public static void main(String[] args) {
        setupLookAndFeel();

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                JFrame frame = new JFrame("Test");
                frame.setSize(600, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JMultiSplit pane = new JMultiSplit(JMultiSplit.HORIZONTAL);

                pane.add(new JButton("1"));
                pane.add(new JPanel());
                pane.add(new JLabel("3"));
                pane.add(new JPanel());
                pane.add(new JButton("1"));

                frame.getContentPane().add(pane);

                frame.setVisible(true);

            }
        });
    }

    /**
     * Sets up the Look and Feel for the application
     */
    private static void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

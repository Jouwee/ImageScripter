/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samples;

import com.jouwee.proto.Application;
import com.jouwee.proto.gui.Pill;
import com.jouwee.proto.gui.PillListModel;
import com.jouwee.proto.gui.PillRenderer;
import com.jouwee.proto.gui.PillSelectionEvent;
import com.jouwee.proto.gui.PillSelectionListener;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

/**
 *
 * @author Nicolas
 */
public class PillTestingFrame extends JFrame {

    public PillTestingFrame() throws HeadlessException {
        super("Pill test frame");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        PillListModel model = new PillListModel();
        final Pill<String> pill = new Pill<>(model);
        pill.addPillSelectionListener(new PillSelectionListener() {

            @Override
            public void valueChanged(PillSelectionEvent e) {
                System.out.println("Selected: " + pill.getModel().getElementAt(e.getIndex()));
            }
        });
        pill.setRenderer(new MyRenderer());
        
        model.add("Test 1");
        model.add("Test 2");
        model.add("Another Test");
        model.add("Yet another");
        model.add("foo");
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pill);
    }
    
    public static void main(String[] args) {
        setupLookAndFeel();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PillTestingFrame frame = new PillTestingFrame();
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
    
    private class MyRenderer implements PillRenderer<String> {

        @Override
        public JComponent getComponent(String value) {
            JComponent ret = new JComponent() {};
            
            ret.setLayout(new BorderLayout());
            JCheckBox check = new JCheckBox();
            check.setFocusable(false);
            check.setOpaque(false);
            ret.add(check, BorderLayout.WEST);
            ret.add(new JLabel(value));
            
            return ret;
        }
        
    }
}

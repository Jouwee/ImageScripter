/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import com.jouwee.proto.Callback;
import com.jouwee.proto.CallbackHeader;
import com.jouwee.proto.ScriptProvider;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JTextArea;

    /**
     * Callback editor
     */
public class CallbackEditor extends JComponent {
        
    /** Fonte default para o editor */
    private static final Font DEFAULT_FONT = new Font("Consolas", Font.PLAIN, 12);
    /** Callback header */
    private final CallbackHeader header;
    /** Callback */
    private final Callback callback;
    /** Actual text editor */
    private final JTextArea textEditor;
        
    /**
     * New callback editor
     *
     * @param header
     * @param callback
     */
    public CallbackEditor(CallbackHeader header, Callback callback) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.header = header;
        this.callback = callback;
        textEditor = new JTextArea();
        textEditor.setFont(DEFAULT_FONT);
        textEditor.setText(callback.getBody());
        JTextArea headerLabel = new JTextArea(ScriptProvider.def().getHeader(header));
        headerLabel.setFont(DEFAULT_FONT);
        headerLabel.setEnabled(false);
        add(headerLabel);
        add(textEditor);
        JTextArea footerLabel = new JTextArea("}");
        footerLabel.setFont(DEFAULT_FONT);
        footerLabel.setEnabled(false);
        add(footerLabel);
    }

    /**
     * Get active callback
     *
     * @return Callback
     */
    public Callback getCallback() {
        return callback;
    }

    /**
     * Returns the edited text
     *
     * @return String
     */
    public String getText() {
        return textEditor.getText();
    }

}

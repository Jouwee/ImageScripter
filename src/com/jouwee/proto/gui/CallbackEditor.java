/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jouwee.proto.gui;

import com.jouwee.proto.Application;
import com.jouwee.proto.Callback;
import com.jouwee.proto.CallbackHeader;
import com.jouwee.proto.ExceptionHandler;
import com.jouwee.proto.ScriptProvider;
import java.awt.Font;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

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
    private final RSyntaxTextArea textEditor;
        
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
        textEditor = new RSyntaxTextArea(20, 60);
        textEditor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        textEditor.setCodeFoldingEnabled(true);
        textEditor.setAntiAliasingEnabled(true);
        textEditor.setText(callback.getBody());
        try {
            Theme theme = Theme.load(Application.class.getResourceAsStream("editorTheme.xml"));
            theme.apply(textEditor);
            RTextScrollPane sp = new RTextScrollPane(textEditor);
            sp.setFoldIndicatorEnabled(true);
            RSyntaxTextArea headerLabel = new RSyntaxTextArea(ScriptProvider.def().getHeader(header));
            headerLabel.setFont(DEFAULT_FONT);
            headerLabel.setEnabled(false);
            headerLabel.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
            theme.apply(headerLabel);
            add(headerLabel);
            add(textEditor);
            RSyntaxTextArea footerLabel = new RSyntaxTextArea("}");
            footerLabel.setFont(DEFAULT_FONT);
            footerLabel.setEnabled(false);
            footerLabel.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
            theme.apply(footerLabel);
            add(footerLabel);
        } catch(IOException e) {
            ExceptionHandler.handle(e);
        }
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

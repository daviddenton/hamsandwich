package org.hamsandwich.filling;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;

public class GenerateMatchersFromClassAction extends EditorAction {

    protected GenerateMatchersFromClassAction() {
        super(new GenerateMatchersFromClassActionHandler());
    }

    public void update(Editor editor, Presentation presentation, DataContext dataContext) {
        System.out.println("In update method");
    }
}

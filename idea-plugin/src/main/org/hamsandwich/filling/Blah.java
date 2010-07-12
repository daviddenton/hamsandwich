package org.hamsandwich.filling;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;

class GenerateMatchersFromClassActionHandler extends EditorActionHandler {

    public void execute(Editor editor, DataContext dataContext) {
        System.out.println("executing action");
    }
}

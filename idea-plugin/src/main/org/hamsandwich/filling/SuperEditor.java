package org.hamsandwich.filling;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiClass;

import static com.intellij.ide.util.EditSourceUtil.getDescriptor;
import static com.intellij.openapi.ui.Messages.showMessageDialog;

public class SuperEditor {
    private final Editor editor;

    public SuperEditor(Editor editor) {
        this.editor = editor;
    }

    public void jumpToClass(PsiClass psiClass) throws CannotPerformAction {
        if (psiClass == null || getDescriptor(psiClass) == null) {
            throw new CannotPerformAction("Can't jump to class " + psiClass);
        }
        getDescriptor(psiClass).navigate(true);
    }

    public PsiClassBuilder newClassBuilder() {
        return new PsiClassBuilder(editor.getProject());
    }

    public void showWarning(String title, String message) {
        showMessageDialog(editor.getProject(), message, title, Messages.getWarningIcon());
    }

    public static class CannotPerformAction extends Exception {
        public CannotPerformAction(String message) {
            super(message);
        }
    }
}

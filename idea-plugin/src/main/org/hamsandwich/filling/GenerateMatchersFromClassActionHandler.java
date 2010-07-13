package org.hamsandwich.filling;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.psi.PsiClass;

import static com.intellij.openapi.application.ApplicationManager.getApplication;

public class GenerateMatchersFromClassActionHandler extends EditorActionHandler {

    public void execute(Editor editor, DataContext dataContext) {
        PsiHelper psiHelper = new PsiHelper(dataContext);
        final PsiClass classToBuildFrom = psiHelper.getCurrentClass();
        final SuperEditor superEditor = new SuperEditor(editor);

        GenerateMatchersClassComputable generateMatchersClassComputable = new GenerateMatchersClassComputable(superEditor, classToBuildFrom);
        if (generateMatchersClassComputable.canGenerateMatcherFor(classToBuildFrom)) {
            try {
                superEditor.jumpToClass(getApplication().runWriteAction(generateMatchersClassComputable));
            } catch (SuperEditor.CannotPerformAction cannotPerformAction) {
                superEditor.showWarning("Warning", "No class generated");
            }
        } else {
            superEditor.showWarning("Warning", "Cannot generate for for an abstract class");
        }
    }
}

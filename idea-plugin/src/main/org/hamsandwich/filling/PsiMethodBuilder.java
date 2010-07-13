package org.hamsandwich.filling;

import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.codeStyle.CodeStyleManager;

public class PsiMethodBuilder<T extends PsiMethodBuilder> {
    private final PsiElementFactory elementFactory;
    private final CodeStyleManager codeStyleManager;

    private String methodText;

    public PsiMethodBuilder(PsiElementFactory elementFactory, CodeStyleManager codeStyleManager) {
        this.elementFactory = elementFactory;
        this.codeStyleManager = codeStyleManager;
    }

    public PsiMethod build() {
        PsiMethod newMethod = elementFactory.createMethodFromText(methodText, null);
        reformat(newMethod);
        return newMethod;
    }

    private void reformat(PsiMethod newMethod) {
        if (codeStyleManager!=null) {
            codeStyleManager.reformat(newMethod);
        }
    }

    public T withMethodText(String methodText) {
        this.methodText = methodText;
        return (T) this;
    }
}

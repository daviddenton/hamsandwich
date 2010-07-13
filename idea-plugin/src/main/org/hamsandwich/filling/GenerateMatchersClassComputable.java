package org.hamsandwich.filling;

import com.intellij.openapi.util.Computable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiModifier;

public class GenerateMatchersClassComputable implements Computable<PsiClass> {
    private final SuperEditor superEditor;
    private final PsiClass classToBuildFrom;

    public GenerateMatchersClassComputable(SuperEditor superEditor, PsiClass classToBuildFrom) {
        this.superEditor = superEditor;
        this.classToBuildFrom = classToBuildFrom;
    }

    public boolean canGenerateMatcherFor(PsiClass classToBuildFrom) {
        return classToBuildFrom != null && classToBuildFrom.getModifierList().getText().contains(PsiModifier.ABSTRACT);
    }

    public PsiClass compute() {
        return superEditor.newClassBuilder()
                .withDirectory(classToBuildFrom.getContainingFile().getContainingDirectory())
                .withName(classToBuildFrom.getName()+"Matcher")
                .build();
    }
}

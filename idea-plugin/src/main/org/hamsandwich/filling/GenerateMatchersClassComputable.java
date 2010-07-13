package org.hamsandwich.filling;

import com.intellij.openapi.util.Computable;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiModifier;
import org.hamsandwich.core.CollectionMatchers;

import static org.hamsandwich.filling.PsiMatchers.noParameters;
import static org.hamsandwich.filling.PsiMatchers.returnsVoid;
import static org.hamsandwich.filling.PsiMatchers.signature;

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
        PsiClassBuilder baseBuilder = superEditor.newClassBuilder()
                .withDirectory(classToBuildFrom.getContainingFile().getContainingDirectory())
                .withName(classToBuildFrom.getName() + "Matcher");

        PsiMethod[] methods = classToBuildFrom.getMethods();

        Iterable<PsiMethod> iterable = CollectionMatchers.filter(methods, signature(noParameters(), returnsVoid()));
        for (PsiMethod psiMethod : iterable) {
            
//            baseBuilder.withMethod(new PsiMethodBuilder().build());
        }
        return baseBuilder.build();
    }

}

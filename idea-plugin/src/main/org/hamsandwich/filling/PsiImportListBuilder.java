package org.hamsandwich.filling;

import com.intellij.psi.PsiImportStatement;
import com.intellij.psi.PsiJavaFile;

import java.util.ArrayList;
import java.util.List;

public class PsiImportListBuilder {
    private final List<PsiImportStatement> importStatements = new ArrayList<PsiImportStatement>();

    public PsiImportListBuilder() {
    }

    public List<PsiImportStatement> build() {
        return importStatements;
    }

    public PsiImportListBuilder withImport(PsiImportStatement newImportStatement) {
        for (PsiImportStatement importStatement : importStatements) {
            if (importStatement.getText().equals(newImportStatement.getText())) {
                return this;
            }
        }
        importStatements.add(newImportStatement);
        return this;
    }

    public PsiImportListBuilder withImportsForFile(PsiJavaFile javaFile) {
        PsiImportStatement[] statements = javaFile.getImportList().getImportStatements();

        for (PsiImportStatement statement : statements) {
            withImport(statement);
        }
        return this;
    }
}

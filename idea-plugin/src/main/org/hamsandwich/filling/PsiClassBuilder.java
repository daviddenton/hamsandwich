package org.hamsandwich.filling;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;

import java.util.ArrayList;
import java.util.List;

import static com.intellij.openapi.ui.Messages.showInfoMessage;
import static com.intellij.psi.JavaPsiFacade.getInstance;
import static com.intellij.psi.search.GlobalSearchScope.allScope;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class PsiClassBuilder {
    private final PsiElementFactory elementFactory;
    private final Project project;

    private final List<String> fields = new ArrayList<String>();
    private final List<PsiMethod> methods = new ArrayList<PsiMethod>();
    private final List<PsiImportStatement> importStatements = new ArrayList<PsiImportStatement>();
    private PsiDirectory directory;

    private String className;
    private String qualifiedClassName;
    private String superClassName;

    public PsiClassBuilder(Project project) {
        this.elementFactory = JavaPsiFacade.getInstance(project).getElementFactory();
        this.project = project;
    }

    public PsiClass build() {
        PsiClass psiClass = JavaDirectoryService.getInstance().createClass(directory, className);

        PsiJavaFile javaFile = (PsiJavaFile) psiClass.getContainingFile();
        PsiImportList importList = javaFile.getImportList();

        for (PsiImportStatement importStatement : importStatements) {
            importList.add(importStatement);
        }

        for (String fieldText : fields) {
            psiClass.add(elementFactory.createFieldFromText(fieldText, psiClass.getOriginalElement()));
        }

        for (PsiMethod method : methods) {
            psiClass.add(method);
        }

        if (isNotBlank(superClassName)) {
            addSuperClass(psiClass);
        }

        return psiClass;
    }

    private void addSuperClass(PsiClass psiClass) {
        PsiClass superClass = getSuperClass();

        if (superClass == null) {
            showInfoMessage("Invalid super class [" + superClassName + "], please check your settings", "Invalid Super Class");
        } else {
            PsiReferenceList extendsList = psiClass.getExtendsList();
            extendsList.add(elementFactory.createKeyword("extends"));
            extendsList.add(elementFactory.createReferenceExpression(superClass));
        }
    }

    private PsiClass getSuperClass() {
        JavaPsiFacade psiFacade = getInstance(project);
        GlobalSearchScope globalSearchScope = allScope(project);
        return psiFacade.findClass(superClassName, globalSearchScope);
    }

    public PsiClassBuilder withName(String className) {
        this.className = className;
        return this;
    }

    public PsiClassBuilder withQualifiedName(String qualifiedClassName) {
        this.qualifiedClassName = qualifiedClassName;
        return this;
    }

    public PsiClassBuilder withDirectory(PsiDirectory directory) {
        this.directory = directory;
        return this;
    }

    public PsiClassBuilder withImports(List<PsiImportStatement> importStatements) {
        if (importStatements != null) {
            this.importStatements.addAll(importStatements);
        }
        return this;
    }

    public PsiClassBuilder withField(String field) {
        fields.add(field);
        return this;
    }

    public PsiClassBuilder withMethod(PsiMethod newMethod) {
        this.methods.add(newMethod);
        return this;
    }

    public PsiClassBuilder withMethods(List<PsiMethod> methods) {
        this.methods.addAll(methods);
        return this;
    }

    public PsiClassBuilder withSuperClass(String qualifiedSuperClassName) {
        this.superClassName = qualifiedSuperClassName;
        return this;
    }

}

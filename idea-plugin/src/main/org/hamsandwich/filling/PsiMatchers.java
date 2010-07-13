package org.hamsandwich.filling;

import com.intellij.psi.HierarchicalMethodSignature;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamsandwich.core.AdaptingMatcher;
import org.hamsandwich.core.CannotAdaptException;
import org.hamsandwich.core.HamSandwichFactory;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PsiMatchers {
    @HamSandwichFactory
    public static Matcher<PsiMethod> signature(Matcher<HierarchicalMethodSignature>... valueMatchers) {
        return new AdaptingMatcher<PsiMethod, HierarchicalMethodSignature>(valueMatchers) {
            @Override
            public HierarchicalMethodSignature get(PsiMethod in) throws CannotAdaptException {
                return in.getHierarchicalMethodSignature();
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<HierarchicalMethodSignature> returnsVoid() {
        return new AdaptingMatcher<HierarchicalMethodSignature, PsiType>(is(equalTo(PsiType.VOID))) {
            @Override
            public PsiType get(HierarchicalMethodSignature in) throws CannotAdaptException {
                return in.getMethod().getReturnType();
            }
        };
    }

    @HamSandwichFactory
    public static Matcher<HierarchicalMethodSignature> noParameters() {
        return new AdaptingMatcher<HierarchicalMethodSignature, PsiType[]>(is(Matchers.<PsiType>emptyArray())) {
            @Override
            public PsiType[] get(HierarchicalMethodSignature in) throws CannotAdaptException {
                return in.getParameterTypes();
            }
        };
    }

}

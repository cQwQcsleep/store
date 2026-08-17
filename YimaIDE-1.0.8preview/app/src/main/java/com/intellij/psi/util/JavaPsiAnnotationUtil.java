package com.intellij.psi.util;

import com.intellij.psi.CommonClassNames;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiEnumConstant;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.PsiImplUtil;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class JavaPsiAnnotationUtil {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "annotation", "com/intellij/psi/util/JavaPsiAnnotationUtil", "getRetentionPolicy"));
    }

    public static RetentionPolicy getRetentionPolicy(PsiClass psiClass) {
        if (psiClass == null) {
            $$$reportNull$$$0(0);
        }
        PsiModifierList modifierList = psiClass.getModifierList();
        if (modifierList != null) {
            PsiAnnotation psiAnnotationFindAnnotation = modifierList.findAnnotation(CommonClassNames.JAVA_LANG_ANNOTATION_RETENTION);
            if (psiAnnotationFindAnnotation == null) {
                return RetentionPolicy.CLASS;
            }
            PsiReference psiReferenceFindAttributeValue = PsiImplUtil.findAttributeValue(psiAnnotationFindAnnotation, (String) null);
            if (psiReferenceFindAttributeValue instanceof PsiReference) {
                PsiEnumConstant psiEnumConstantResolve = psiReferenceFindAttributeValue.resolve();
                if (psiEnumConstantResolve instanceof PsiEnumConstant) {
                    try {
                        return (RetentionPolicy) Enum.valueOf(RetentionPolicy.class, psiEnumConstantResolve.getName());
                    } catch (IllegalArgumentException unused) {
                    }
                }
            }
        }
        return null;
    }
}

package com.intellij.util;

import com.intellij.core.JavaPsiBundle;
import com.intellij.psi.PsiModifierList;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class VisibilityUtil {
    private static final String[] visibilityModifiers = {CompilerOptions.PRIVATE, "packageLocal", CompilerOptions.PROTECTED, CompilerOptions.PUBLIC};

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 3 || i == 4 || i == 6 || i == 7) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 1 || i == 3 || i == 4 || i == 6 || i == 7) ? 3 : 2];
        if (i == 1) {
            objArr[0] = "visibilityModifier";
        } else if (i == 3) {
            objArr[0] = "member";
        } else if (i == 4) {
            objArr[0] = "modifier";
        } else if (i == 6) {
            objArr[0] = "modifierList";
        } else if (i != 7) {
            objArr[0] = "com/intellij/util/VisibilityUtil";
        } else {
            objArr[0] = "newVisibility";
        }
        switch (i) {
            case 1:
            case 3:
            case 4:
            case 6:
            case 7:
                objArr[1] = "com/intellij/util/VisibilityUtil";
                break;
            case 2:
                objArr[1] = "getVisibilityString";
                break;
            case 5:
                objArr[1] = "toPresentableText";
                break;
            default:
                objArr[1] = "getVisibilityModifier";
                break;
        }
        if (i == 1) {
            objArr[2] = "getVisibilityString";
        } else if (i == 3) {
            objArr[2] = "getVisibilityStringToDisplay";
        } else if (i == 4) {
            objArr[2] = "toPresentableText";
        } else if (i == 6 || i == 7) {
            objArr[2] = "setVisibility";
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 3 && i != 4 && i != 6 && i != 7) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    public static String getVisibilityModifier(PsiModifierList psiModifierList) {
        if (psiModifierList == null) {
            return "packageLocal";
        }
        for (String str : visibilityModifiers) {
            if (psiModifierList.hasModifierProperty(str)) {
                if (str == null) {
                    $$$reportNull$$$0(0);
                }
                return str;
            }
        }
        return "packageLocal";
    }

    public static String toPresentableText(String str) {
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        String strVisibilityPresentation = JavaPsiBundle.visibilityPresentation(str);
        if (strVisibilityPresentation == null) {
            $$$reportNull$$$0(5);
        }
        return strVisibilityPresentation;
    }
}

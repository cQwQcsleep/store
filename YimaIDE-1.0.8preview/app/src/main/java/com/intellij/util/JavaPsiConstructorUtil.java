package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.util.JavaPsiConstructorUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class JavaPsiConstructorUtil {
    private static final TokenSet CONSTRUCTOR_CALL_TOKENS = TokenSet.create(new IElementType[]{JavaTokenType.SUPER_KEYWORD, JavaTokenType.THIS_KEYWORD});

    public static class ConstructorVisitorInfo {
        PsiMethod recursivelyCalledConstructor;
        List<PsiMethod> visitedConstructors;

        private ConstructorVisitorInfo() {
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 5 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 5 ? 3 : 2];
        if (i == 3) {
            objArr[0] = "visited";
        } else if (i == 5) {
            objArr[0] = "com/intellij/util/JavaPsiConstructorUtil";
        } else if (i == 6) {
            objArr[0] = "entry";
        } else if (i != 7) {
            objArr[0] = "constructor";
        } else {
            objArr[0] = "info";
        }
        if (i != 5) {
            objArr[1] = "com/intellij/util/JavaPsiConstructorUtil";
        } else {
            objArr[1] = "getChainedConstructors";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
                objArr[2] = "findConstructorInSuper";
                break;
            case 4:
                objArr[2] = "getChainedConstructors";
                break;
            case 5:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "visitConstructorChain";
                break;
            case 8:
                objArr[2] = "isRecursivelyCalledConstructor";
                break;
            default:
                objArr[2] = "findThisOrSuperCallInConstructor";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 5) {
            throw new IllegalStateException(str2);
        }
    }

    public static /* synthetic */ boolean a(PsiMethod psiMethod, Ref ref, PsiMethodCallExpression psiMethodCallExpression) {
        if (!isConstructorCall(psiMethodCallExpression) || PsiTreeUtil.getParentOfType(psiMethodCallExpression, PsiMethod.class) != psiMethod) {
            return true;
        }
        ref.set(psiMethodCallExpression);
        return false;
    }

    public static /* synthetic */ CachedValueProvider.Result b(final PsiMethod psiMethod) {
        PsiCodeBlock body = psiMethod.getBody();
        if (body == null) {
            return new CachedValueProvider.Result((Object) null, new Object[]{PsiModificationTracker.MODIFICATION_COUNT});
        }
        final Ref ref = new Ref();
        PsiTreeUtil.processElements(body, PsiMethodCallExpression.class, new PsiElementProcessor() { // from class: fk7
            public final boolean execute(PsiElement psiElement) {
                return JavaPsiConstructorUtil.a(psiMethod, ref, (PsiMethodCallExpression) psiElement);
            }
        });
        return new CachedValueProvider.Result((PsiMethodCallExpression) ref.get(), new Object[]{PsiModificationTracker.MODIFICATION_COUNT});
    }

    public static PsiMethodCallExpression findThisOrSuperCallInConstructor(final PsiMethod psiMethod) {
        if (psiMethod == null) {
            $$$reportNull$$$0(0);
        }
        if (psiMethod.isConstructor()) {
            return (PsiMethodCallExpression) CachedValuesManager.getCachedValue(psiMethod, new CachedValueProvider() { // from class: gk7
                public final CachedValueProvider.Result compute() {
                    return JavaPsiConstructorUtil.b(psiMethod);
                }
            });
        }
        return null;
    }

    public static List<PsiMethod> getChainedConstructors(PsiMethod psiMethod) {
        if (psiMethod == null) {
            $$$reportNull$$$0(4);
        }
        ConstructorVisitorInfo constructorVisitorInfo = new ConstructorVisitorInfo();
        visitConstructorChain(psiMethod, constructorVisitorInfo);
        List<PsiMethod> list = constructorVisitorInfo.visitedConstructors;
        if (list != null) {
            list.remove(psiMethod);
        }
        List<PsiMethod> list2 = (List) ObjectUtils.notNull(constructorVisitorInfo.visitedConstructors, Collections.EMPTY_LIST);
        if (list2 == null) {
            $$$reportNull$$$0(5);
        }
        return list2;
    }

    public static boolean isChainedConstructorCall(PsiElement psiElement) {
        if (psiElement instanceof PsiMethodCallExpression) {
            return PsiUtil.isJavaToken(((PsiMethodCallExpression) psiElement).getMethodExpression().getReferenceNameElement(), JavaTokenType.THIS_KEYWORD);
        }
        return false;
    }

    public static boolean isConstructorCall(PsiElement psiElement) {
        if (psiElement instanceof PsiMethodCallExpression) {
            return PsiUtil.isJavaToken(((PsiMethodCallExpression) psiElement).getMethodExpression().getReferenceNameElement(), CONSTRUCTOR_CALL_TOKENS);
        }
        return false;
    }

    private static void visitConstructorChain(PsiMethod psiMethod, ConstructorVisitorInfo constructorVisitorInfo) {
        if (psiMethod == null) {
            $$$reportNull$$$0(6);
        }
        if (constructorVisitorInfo == null) {
            $$$reportNull$$$0(7);
        }
        while (true) {
            PsiMethodCallExpression psiMethodCallExpressionFindThisOrSuperCallInConstructor = findThisOrSuperCallInConstructor(psiMethod);
            if (!isChainedConstructorCall(psiMethodCallExpressionFindThisOrSuperCallInConstructor) || (psiMethod = psiMethodCallExpressionFindThisOrSuperCallInConstructor.resolveMethod()) == null) {
                return;
            }
            List<PsiMethod> list = constructorVisitorInfo.visitedConstructors;
            if (list != null && list.contains(psiMethod)) {
                constructorVisitorInfo.recursivelyCalledConstructor = psiMethod;
                return;
            } else {
                if (constructorVisitorInfo.visitedConstructors == null) {
                    constructorVisitorInfo.visitedConstructors = new ArrayList(5);
                }
                constructorVisitorInfo.visitedConstructors.add(psiMethod);
            }
        }
    }
}

package com.intellij.util;

import androidx.collection.ScatterMapKt;
import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ref.DebugReflectionUtil;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class CachedValueLeakChecker {
    private static final Logger LOG = Logger.getInstance(CachedValueLeakChecker.class);
    private static final boolean DO_CHECKS = ApplicationManager.getApplication().isUnitTestMode();
    private static final Set<String> ourCheckedKeys = ContainerUtil.newConcurrentSet();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 1:
            case 4:
                objArr[0] = "key";
                break;
            case 2:
                objArr[0] = "userDataHolder";
                break;
            case 3:
                objArr[0] = "root";
                break;
            case 5:
                objArr[0] = "toIgnore";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "ancestor";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "element";
                break;
            default:
                objArr[0] = "provider";
                break;
        }
        objArr[1] = "com/intellij/util/CachedValueLeakChecker";
        if (i == 3 || i == 4 || i == 5) {
            objArr[2] = "findReferencedPsi";
        } else if (i == 6 || i == 7) {
            objArr[2] = "isAncestor";
        } else {
            objArr[2] = "checkProviderDoesNotLeakPSI";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static /* synthetic */ boolean a(UserDataHolder userDataHolder, Object obj) {
        if (obj == userDataHolder) {
            return false;
        }
        if (((obj instanceof ASTNode) && (obj = ((ASTNode) obj).getPsi()) == userDataHolder) || (obj instanceof Project) || (obj instanceof Module) || (obj instanceof Application)) {
            return false;
        }
        if (!(obj instanceof PsiElement) || !(userDataHolder instanceof PsiElement)) {
            return true;
        }
        PsiElement psiElement = (PsiElement) userDataHolder;
        return psiElement.getContainingFile() == null || !isAncestor((PsiElement) obj, psiElement);
    }

    public static /* synthetic */ boolean b(Object obj, PsiElement psiElement, DebugReflectionUtil.BackLink backLink) {
        LOG.error("Provider '" + obj + "' is retaining PSI, causing memory leaks and possible invalid element access.\n" + backLink);
        return false;
    }

    public static void checkProviderDoesNotLeakPSI(CachedValueProvider<?> cachedValueProvider, Key<?> key, UserDataHolder userDataHolder) {
        if (cachedValueProvider == null) {
            $$$reportNull$$$0(0);
        }
        if (key == null) {
            $$$reportNull$$$0(1);
        }
        if (userDataHolder == null) {
            $$$reportNull$$$0(2);
        }
        if (DO_CHECKS && !ApplicationManagerEx.isInStressTest() && ourCheckedKeys.add(key.toString())) {
            findReferencedPsi(cachedValueProvider, key, userDataHolder);
        }
    }

    private static synchronized void findReferencedPsi(final Object obj, Key<?> key, final UserDataHolder userDataHolder) {
        if (obj == null) {
            try {
                $$$reportNull$$$0(3);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (key == null) {
            $$$reportNull$$$0(4);
        }
        if (userDataHolder == null) {
            $$$reportNull$$$0(5);
        }
        DebugReflectionUtil.walkObjects(5, Collections.singletonMap(obj, "CachedValueProvider " + key), PsiElement.class, new Predicate() { // from class: com.intellij.util.a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj2) {
                return CachedValueLeakChecker.a(userDataHolder, obj2);
            }
        }, new PairProcessor() { // from class: com.intellij.util.b
            @Override // com.intellij.util.PairProcessor
            public final boolean process(Object obj2, Object obj3) {
                return CachedValueLeakChecker.b(obj, (PsiElement) obj2, (DebugReflectionUtil.BackLink) obj3);
            }
        });
    }

    private static boolean isAncestor(PsiElement psiElement, PsiElement psiElement2) {
        if (psiElement == null) {
            $$$reportNull$$$0(6);
        }
        if (psiElement2 == null) {
            $$$reportNull$$$0(7);
        }
        if ((!(psiElement instanceof StubBasedPsiElementBase) || ((StubBasedPsiElementBase) psiElement).getStub() == null) && (!(psiElement2 instanceof StubBasedPsiElementBase) || ((StubBasedPsiElementBase) psiElement2).getStub() == null)) {
            return PsiTreeUtil.isAncestor(psiElement, psiElement2, true);
        }
        return psiElement.getContainingFile() == psiElement2.getContainingFile() && PsiTreeUtil.isContextAncestor(psiElement, psiElement2, true);
    }
}

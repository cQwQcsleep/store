package org.jetbrains.kotlin.asJava;

import com.intellij.psi.PsiMethod;
import com.intellij.psi.SyntheticElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0005"}, d2 = {"isSyntheticValuesOrValueOfMethod", "", "method", "Lcom/intellij/psi/PsiMethod;", "isGetEntriesMethod", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SyntheticElementUtilsKt {
    public static final boolean isGetEntriesMethod(PsiMethod psiMethod) {
        psiMethod.getClass();
        return Intrinsics.areEqual(psiMethod.getName(), "getEntries") && psiMethod.getParameterList().getParametersCount() == 0 && psiMethod.hasModifierProperty(CompilerOptions.PUBLIC) && psiMethod.hasModifierProperty("static");
    }

    public static final boolean isSyntheticValuesOrValueOfMethod(PsiMethod psiMethod) {
        psiMethod.getClass();
        if (!(psiMethod instanceof SyntheticElement)) {
            return false;
        }
        String name = psiMethod.getName();
        name.getClass();
        if ((!Intrinsics.areEqual(name, "values") && !Intrinsics.areEqual(name, "valueOf")) || !psiMethod.hasModifierProperty(CompilerOptions.PUBLIC) || !psiMethod.hasModifierProperty("static")) {
            return false;
        }
        int parametersCount = psiMethod.getParameterList().getParametersCount();
        if (Intrinsics.areEqual(name, "values")) {
            return parametersCount == 0;
        }
        return Intrinsics.areEqual(name, "valueOf") && parametersCount == 1;
    }
}

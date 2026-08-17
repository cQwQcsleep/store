package org.jetbrains.kotlin.asJava.classes;

import com.intellij.psi.PsiAnonymousClass;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.augment.PsiAugmentProvider;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a'\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005H\u0002¢\u0006\u0002\u0010\b\u001a0\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\b\b\u0000\u0010\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u000e\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u000b0\u000fH\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u0010"}, d2 = {"isAnonymous", "", "Lcom/intellij/psi/PsiClass;", "(Lcom/intellij/psi/PsiClass;)Z", "copy", "", "T", "value", "([Ljava/lang/Object;)[Ljava/lang/Object;", "collectAugments", "", "Psi", "Lcom/intellij/psi/PsiElement;", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "type", "Ljava/lang/Class;", "org.jetbrains.kotlin:light-classes"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class KotlinClassInnerStuffCacheKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final <Psi extends PsiElement> List<Psi> collectAugments(PsiElement psiElement, Class<? extends Psi> cls) {
        List<Psi> listCollectAugments = PsiAugmentProvider.collectAugments(psiElement, cls, (String) null);
        listCollectAugments.getClass();
        return listCollectAugments;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> T[] copy(T[] tArr) {
        return tArr.length == 0 ? tArr : (T[]) ((Object[]) tArr.clone());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isAnonymous(PsiClass psiClass) {
        return psiClass.getName() == null || (psiClass instanceof PsiAnonymousClass);
    }
}

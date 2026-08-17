package org.jetbrains.kotlin.references;

import com.intellij.psi.PsiReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.psi.KtElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001\fR\u001a\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/references/KotlinPsiReferenceProviderContributor;", "T", "Lorg/jetbrains/kotlin/psi/KtElement;", "", "elementClass", "Ljava/lang/Class;", "getElementClass", "()Ljava/lang/Class;", "referenceProvider", "Lorg/jetbrains/kotlin/references/KotlinPsiReferenceProviderContributor$ReferenceProvider;", "getReferenceProvider", "()Lorg/jetbrains/kotlin/references/KotlinPsiReferenceProviderContributor$ReferenceProvider;", "ReferenceProvider", "org.jetbrains.kotlin:kt-references"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KotlinPsiReferenceProviderContributor<T extends KtElement> {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u0000*\n\b\u0001\u0010\u0001 \u0000*\u00020\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/references/KotlinPsiReferenceProviderContributor$ReferenceProvider;", "T", "Lorg/jetbrains/kotlin/psi/KtElement;", "Lkotlin/Function1;", "", "Lcom/intellij/psi/PsiReference;", "org.jetbrains.kotlin:kt-references"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public interface ReferenceProvider<T extends KtElement> extends Function1<T, List<? extends PsiReference>> {
    }

    Class<? extends T> getElementClass();

    ReferenceProvider<T> getReferenceProvider();
}

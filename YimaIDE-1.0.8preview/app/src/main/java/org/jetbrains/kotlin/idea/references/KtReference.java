package org.jetbrains.kotlin.idea.references;

import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.resolution.KtResolvable;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0007\u001a\u00020\bH&R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rÊ\u0001\u0010\b\u000f\u0012\f\b\u0010\u0012\b\b\fJ\u0004\b\t0\u0011ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/idea/references/KtReference;", "Lcom/intellij/psi/PsiPolyVariantReference;", "Lorg/jetbrains/kotlin/resolution/KtResolvable;", "resolver", "Lcom/intellij/psi/impl/source/resolve/ResolveCache$PolyVariantResolver;", "getResolver", "()Lcom/intellij/psi/impl/source/resolve/ResolveCache$PolyVariantResolver;", "getElement", "Lorg/jetbrains/kotlin/psi/KtElement;", "resolvesByNames", "", "Lorg/jetbrains/kotlin/name/Name;", "getResolvesByNames", "()Ljava/util/Collection;", "org.jetbrains.kotlin:kt-references", "Lkotlin/SubclassOptInRequired;", "markerClass", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KtReference extends PsiPolyVariantReference, KtResolvable {
    KtElement getElement();

    ResolveCache.PolyVariantResolver<KtReference> getResolver();

    Collection<Name> getResolvesByNames();
}

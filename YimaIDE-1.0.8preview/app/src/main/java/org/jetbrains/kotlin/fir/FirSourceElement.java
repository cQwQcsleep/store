package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.mpp.SourceElementMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirSourceElement;", "Lorg/jetbrains/kotlin/mpp/SourceElementMarker;", "element", "Lorg/jetbrains/kotlin/KtSourceElement;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "getElement", "()Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSourceElement implements SourceElementMarker {
    private final KtSourceElement element;

    public FirSourceElement(KtSourceElement ktSourceElement) {
        this.element = ktSourceElement;
    }

    public final KtSourceElement getElement() {
        return this.element;
    }
}

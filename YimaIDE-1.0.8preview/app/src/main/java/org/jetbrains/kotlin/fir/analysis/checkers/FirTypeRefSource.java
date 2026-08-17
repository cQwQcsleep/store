package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirTypeRefSource;", Argument.Delimiters.none, "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/KtSourceElement;)V", "getTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "toString", Argument.Delimiters.none, "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class FirTypeRefSource {
    private final KtSourceElement source;
    private final FirTypeRef typeRef;

    public FirTypeRefSource(FirTypeRef firTypeRef, KtSourceElement ktSourceElement) {
        this.typeRef = firTypeRef;
        this.source = ktSourceElement;
    }

    public static /* synthetic */ FirTypeRefSource copy$default(FirTypeRefSource firTypeRefSource, FirTypeRef firTypeRef, KtSourceElement ktSourceElement, int i, Object obj) {
        if ((i & 1) != 0) {
            firTypeRef = firTypeRefSource.typeRef;
        }
        if ((i & 2) != 0) {
            ktSourceElement = firTypeRefSource.source;
        }
        return firTypeRefSource.copy(firTypeRef, ktSourceElement);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirTypeRef getTypeRef() {
        return this.typeRef;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final KtSourceElement getSource() {
        return this.source;
    }

    public final FirTypeRefSource copy(FirTypeRef typeRef, KtSourceElement source) {
        return new FirTypeRefSource(typeRef, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FirTypeRefSource)) {
            return false;
        }
        FirTypeRefSource firTypeRefSource = (FirTypeRefSource) other;
        return Intrinsics.areEqual(this.typeRef, firTypeRefSource.typeRef) && Intrinsics.areEqual(this.source, firTypeRefSource.source);
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public final FirTypeRef getTypeRef() {
        return this.typeRef;
    }

    public int hashCode() {
        FirTypeRef firTypeRef = this.typeRef;
        int iHashCode = (firTypeRef == null ? 0 : firTypeRef.hashCode()) * 31;
        KtSourceElement ktSourceElement = this.source;
        return iHashCode + (ktSourceElement != null ? ktSourceElement.hashCode() : 0);
    }

    public String toString() {
        KtSourceElementKind kind;
        StringBuilder sb = new StringBuilder("FirTypeRefSource(typeRef=");
        FirTypeRef firTypeRef = this.typeRef;
        String simpleName = null;
        sb.append(firTypeRef != null ? UtilsKt.render(firTypeRef) : null);
        sb.append(", source=");
        KtSourceElement ktSourceElement = this.source;
        if (ktSourceElement != null && (kind = ktSourceElement.getKind()) != null) {
            simpleName = kind.getClass().getSimpleName();
        }
        sb.append(simpleName);
        sb.append(')');
        return sb.toString();
    }
}

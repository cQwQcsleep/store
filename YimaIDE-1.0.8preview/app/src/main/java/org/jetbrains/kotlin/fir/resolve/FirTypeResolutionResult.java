package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirTypeResolutionResult;", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;)V", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class FirTypeResolutionResult {
    private final ConeDiagnostic diagnostic;
    private final FirResolvedSymbolOrigin resolvedSymbolOrigin;
    private final ConeKotlinType type;

    public FirTypeResolutionResult(ConeKotlinType coneKotlinType, ConeDiagnostic coneDiagnostic, FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        coneKotlinType.getClass();
        this.type = coneKotlinType;
        this.diagnostic = coneDiagnostic;
        this.resolvedSymbolOrigin = firResolvedSymbolOrigin;
    }

    public static /* synthetic */ FirTypeResolutionResult copy$default(FirTypeResolutionResult firTypeResolutionResult, ConeKotlinType coneKotlinType, ConeDiagnostic coneDiagnostic, FirResolvedSymbolOrigin firResolvedSymbolOrigin, int i, Object obj) {
        if ((i & 1) != 0) {
            coneKotlinType = firTypeResolutionResult.type;
        }
        if ((i & 2) != 0) {
            coneDiagnostic = firTypeResolutionResult.diagnostic;
        }
        if ((i & 4) != 0) {
            firResolvedSymbolOrigin = firTypeResolutionResult.resolvedSymbolOrigin;
        }
        return firTypeResolutionResult.copy(coneKotlinType, coneDiagnostic, firResolvedSymbolOrigin);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ConeKotlinType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final FirResolvedSymbolOrigin getResolvedSymbolOrigin() {
        return this.resolvedSymbolOrigin;
    }

    public final FirTypeResolutionResult copy(ConeKotlinType type, ConeDiagnostic diagnostic, FirResolvedSymbolOrigin resolvedSymbolOrigin) {
        type.getClass();
        return new FirTypeResolutionResult(type, diagnostic, resolvedSymbolOrigin);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FirTypeResolutionResult)) {
            return false;
        }
        FirTypeResolutionResult firTypeResolutionResult = (FirTypeResolutionResult) other;
        return Intrinsics.areEqual(this.type, firTypeResolutionResult.type) && Intrinsics.areEqual(this.diagnostic, firTypeResolutionResult.diagnostic) && this.resolvedSymbolOrigin == firTypeResolutionResult.resolvedSymbolOrigin;
    }

    public final ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    public final FirResolvedSymbolOrigin getResolvedSymbolOrigin() {
        return this.resolvedSymbolOrigin;
    }

    public final ConeKotlinType getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = this.type.hashCode() * 31;
        ConeDiagnostic coneDiagnostic = this.diagnostic;
        int iHashCode2 = (iHashCode + (coneDiagnostic == null ? 0 : coneDiagnostic.hashCode())) * 31;
        FirResolvedSymbolOrigin firResolvedSymbolOrigin = this.resolvedSymbolOrigin;
        return iHashCode2 + (firResolvedSymbolOrigin != null ? firResolvedSymbolOrigin.hashCode() : 0);
    }

    public String toString() {
        return "FirTypeResolutionResult(type=" + this.type + ", diagnostic=" + this.diagnostic + ", resolvedSymbolOrigin=" + this.resolvedSymbolOrigin + ')';
    }

    public /* synthetic */ FirTypeResolutionResult(ConeKotlinType coneKotlinType, ConeDiagnostic coneDiagnostic, FirResolvedSymbolOrigin firResolvedSymbolOrigin, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneKotlinType, coneDiagnostic, (i & 4) != 0 ? null : firResolvedSymbolOrigin);
    }
}

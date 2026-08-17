package org.jetbrains.kotlin.fir.analysis.js.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirWebCommonHelpersKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u000f\u001a\u00020\u0007H\u0002J\u001f\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0000R\u00020\u0011j\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\r\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J+\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\b\u0002\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsStableName;", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isPresentInGeneratedCode", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Z)V", "getName", "()Ljava/lang/String;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "()Z", "isExternalRedeclarable", "clashesWith", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "other", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsStableName;)Z", "component1", "component2", "component3", "copy", "equals", "hashCode", Argument.Delimiters.none, "toString", "Companion", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class FirJsStableName {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean isPresentInGeneratedCode;
    private final String name;
    private final FirBasedSymbol<?> symbol;

    public FirJsStableName(String str, FirBasedSymbol<?> firBasedSymbol, boolean z) {
        str.getClass();
        firBasedSymbol.getClass();
        this.name = str;
        this.symbol = firBasedSymbol;
        this.isPresentInGeneratedCode = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirJsStableName copy$default(FirJsStableName firJsStableName, String str, FirBasedSymbol firBasedSymbol, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = firJsStableName.name;
        }
        if ((i & 2) != 0) {
            firBasedSymbol = firJsStableName.symbol;
        }
        if ((i & 4) != 0) {
            z = firJsStableName.isPresentInGeneratedCode;
        }
        return firJsStableName.copy(str, firBasedSymbol, z);
    }

    private final boolean isExternalRedeclarable() {
        if (this.isPresentInGeneratedCode) {
            return false;
        }
        FirBasedSymbol<?> firBasedSymbol = this.symbol;
        FirCallableSymbol firCallableSymbol = firBasedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) firBasedSymbol : null;
        if (firCallableSymbol == null || firCallableSymbol.getResolvedStatus().getModality() != Modality.FINAL) {
            return this.symbol instanceof FirClassLikeSymbol;
        }
        return true;
    }

    public final boolean clashesWith(CheckerContext checkerContext, FirJsStableName firJsStableName) {
        checkerContext.getClass();
        firJsStableName.getClass();
        if (this.symbol != firJsStableName.symbol && Intrinsics.areEqual(this.name, firJsStableName.name)) {
            return (this.isPresentInGeneratedCode || firJsStableName.isPresentInGeneratedCode) && !isExternalRedeclarable() && !firJsStableName.isExternalRedeclarable() && FirJsHelpersKt.isActual(this.symbol) == FirJsHelpersKt.isActual(firJsStableName.symbol) && FirJsHelpersKt.isExpect(this.symbol) == FirJsHelpersKt.isExpect(firJsStableName.symbol);
        }
        return false;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final FirBasedSymbol<?> component2() {
        return this.symbol;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsPresentInGeneratedCode() {
        return this.isPresentInGeneratedCode;
    }

    public final FirJsStableName copy(String name, FirBasedSymbol<?> symbol, boolean isPresentInGeneratedCode) {
        name.getClass();
        symbol.getClass();
        return new FirJsStableName(name, symbol, isPresentInGeneratedCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FirJsStableName)) {
            return false;
        }
        FirJsStableName firJsStableName = (FirJsStableName) other;
        return Intrinsics.areEqual(this.name, firJsStableName.name) && Intrinsics.areEqual(this.symbol, firJsStableName.symbol) && this.isPresentInGeneratedCode == firJsStableName.isPresentInGeneratedCode;
    }

    public final String getName() {
        return this.name;
    }

    public final FirBasedSymbol<?> getSymbol() {
        return this.symbol;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.symbol.hashCode()) * 31) + Boolean.hashCode(this.isPresentInGeneratedCode);
    }

    public final boolean isPresentInGeneratedCode() {
        return this.isPresentInGeneratedCode;
    }

    public String toString() {
        return "FirJsStableName(name=" + this.name + ", symbol=" + this.symbol + ", isPresentInGeneratedCode=" + this.isPresentInGeneratedCode + ')';
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tR\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsStableName$Companion;", Argument.Delimiters.none, "<init>", "()V", "createStableNameOrNull", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsStableName;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsStableName;", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirJsStableName createStableNameOrNull(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol) {
            checkerContext.getClass();
            firBasedSymbol.getClass();
            FirSession session = checkerContext.getSession();
            String jsName = FirJsHelpersKt.getJsName(firBasedSymbol, session);
            if (jsName != null) {
                return new FirJsStableName(jsName, firBasedSymbol, FirJsHelpersKt.isPresentInGeneratedCode(firBasedSymbol, session));
            }
            if ((firBasedSymbol instanceof FirConstructorSymbol) || (firBasedSymbol instanceof FirPropertyAccessorSymbol) || (firBasedSymbol instanceof FirTypeAliasSymbol)) {
                return null;
            }
            if (FirWebCommonHelpersKt.isEffectivelyExternal(firBasedSymbol, session) || (((firBasedSymbol instanceof FirCallableSymbol) && FirWebCommonHelpersKt.isEffectivelyExternalOrOverridingExternal(checkerContext, (FirCallableSymbol) firBasedSymbol)) || FirJsHelpersKt.isExportedObject(firBasedSymbol, session))) {
                Name memberDeclarationNameOrNull = FirDeclarationUtilKt.getMemberDeclarationNameOrNull(firBasedSymbol);
                String identifierOrNullIfSpecial = memberDeclarationNameOrNull != null ? memberDeclarationNameOrNull.getIdentifierOrNullIfSpecial() : null;
                if (identifierOrNullIfSpecial != null) {
                    return new FirJsStableName(identifierOrNullIfSpecial, firBasedSymbol, FirJsHelpersKt.isPresentInGeneratedCode(firBasedSymbol, session));
                }
            }
            return null;
        }

        private Companion() {
        }
    }
}

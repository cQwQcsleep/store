package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.utils.SmartSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rRA\u0010\u000e\u001a2\u0012\u0004\u0012\u00028\u0000\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00100\u000fj\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0010`\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013RA\u0010\u0014\u001a2\u0012\u0004\u0012\u00028\u0000\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00100\u000fj\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0010`\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirDeclarationCollector;", "D", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)V", "getContext$org_jetbrains_kotlin_checkers", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession$org_jetbrains_kotlin_checkers", "()Lorg/jetbrains/kotlin/fir/FirSession;", "declarationConflictingSymbols", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/utils/SmartSet;", "Lkotlin/collections/HashMap;", "getDeclarationConflictingSymbols", "()Ljava/util/HashMap;", "declarationShadowedViaContextParameters", "getDeclarationShadowedViaContextParameters", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationCollector<D extends FirBasedSymbol<?>> {
    private final CheckerContext context;
    private final HashMap<D, SmartSet<FirBasedSymbol<?>>> declarationConflictingSymbols;
    private final HashMap<D, SmartSet<FirBasedSymbol<?>>> declarationShadowedViaContextParameters;

    public FirDeclarationCollector(CheckerContext checkerContext) {
        checkerContext.getClass();
        this.context = checkerContext;
        this.declarationConflictingSymbols = new HashMap<>();
        this.declarationShadowedViaContextParameters = new HashMap<>();
    }

    /* JADX INFO: renamed from: getContext$org_jetbrains_kotlin_checkers, reason: from getter */
    public final CheckerContext getContext() {
        return this.context;
    }

    public final HashMap<D, SmartSet<FirBasedSymbol<?>>> getDeclarationConflictingSymbols() {
        return this.declarationConflictingSymbols;
    }

    public final HashMap<D, SmartSet<FirBasedSymbol<?>>> getDeclarationShadowedViaContextParameters() {
        return this.declarationShadowedViaContextParameters;
    }

    public final FirSession getSession$org_jetbrains_kotlin_checkers() {
        return this.context.getSessionHolder().getSession();
    }
}

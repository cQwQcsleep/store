package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ$\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f*\u00020\u00102\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u000e\u0010\u0012\u001a\u0004\u0018\u00010\u0010*\u00020\u0010H\u0002¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCommonConstructorDelegationIssuesChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "findCycle", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "knownCyclicConstructors", "getDelegated", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCommonConstructorDelegationIssuesChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirCommonConstructorDelegationIssuesChecker INSTANCE = new FirCommonConstructorDelegationIssuesChecker();

    private FirCommonConstructorDelegationIssuesChecker() {
        super(MppCheckerKind.Common);
    }

    private final Set<FirConstructorSymbol> findCycle(FirConstructorSymbol firConstructorSymbol, Set<FirConstructorSymbol> set) {
        Set<FirConstructorSymbol> setMutableSetOf = SetsKt.mutableSetOf(new FirConstructorSymbol[]{firConstructorSymbol});
        FirConstructorSymbol delegated = getDelegated(firConstructorSymbol);
        while (true) {
            if ((firConstructorSymbol.isPrimary() && !FirDeclarationUtilKt.isErrorPrimaryConstructor(firConstructorSymbol)) || delegated == null) {
                return null;
            }
            if (setMutableSetOf.contains(delegated) || set.contains(delegated)) {
                return setMutableSetOf;
            }
            FirConstructorSymbol delegated2 = getDelegated(delegated);
            setMutableSetOf.add(delegated);
            FirConstructorSymbol firConstructorSymbol2 = delegated;
            delegated = delegated2;
            firConstructorSymbol = firConstructorSymbol2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirConstructorSymbol getDelegated(FirConstructorSymbol firConstructorSymbol) {
        FirReference calleeReference;
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firConstructorSymbol, FirResolvePhase.BODY_RESOLVE);
        FirDelegatedConstructorCall delegatedConstructor = ((FirConstructor) firConstructorSymbol.getFir()).getDelegatedConstructor();
        if (delegatedConstructor == null || (calleeReference = delegatedConstructor.getCalleeReference()) == null) {
            return null;
        }
        return FirReferenceUtilsKt.toResolvedConstructorSymbol(calleeReference, true);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        Object next;
        KtSourceElement source;
        FirDelegatedConstructorCall resolvedDelegatedConstructorCall;
        KtSourceElement source2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
        if (!(containingDeclarations instanceof List)) {
            Iterator it = CollectionsKt.reversed(containingDeclarations).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!(next instanceof FirRegularClassSymbol));
        } else {
            int size = containingDeclarations.size() - 1;
            if (size < 0) {
                next = null;
                break;
            }
            while (true) {
                int i = size - 1;
                next = containingDeclarations.get(size);
                if (next instanceof FirRegularClassSymbol) {
                    break;
                }
                if (i < 0) {
                    next = null;
                    break;
                }
                size = i;
            }
        }
        if (DeclarationUtilsKt.isEffectivelyExternal(checkerContext, firRegularClass, (FirRegularClassSymbol) next)) {
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
        boolean zIsEffectivelyExpect = DeclarationUtilsKt.isEffectivelyExpect(checkerContext, firRegularClass, objLastOrNull instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) objLastOrNull : null);
        LinkedHashSet<FirConstructorSymbol> linkedHashSet2 = new LinkedHashSet();
        boolean z = false;
        for (FirConstructorSymbol firConstructorSymbol : org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.constructors(firRegularClass, checkerContext.getSession())) {
            if (!firConstructorSymbol.isPrimary() || FirDeclarationUtilKt.isErrorPrimaryConstructor(firConstructorSymbol)) {
                linkedHashSet2.add(firConstructorSymbol);
                Set<FirConstructorSymbol> setFindCycle = INSTANCE.findCycle(firConstructorSymbol, linkedHashSet);
                if (setFindCycle != null) {
                    CollectionsKt.addAll(linkedHashSet, setFindCycle);
                }
            } else {
                z = true;
            }
        }
        CollectionsKt.removeAll(linkedHashSet2, linkedHashSet);
        if (z) {
            for (FirConstructorSymbol firConstructorSymbol2 : linkedHashSet2) {
                if (!zIsEffectivelyExpect && ((resolvedDelegatedConstructorCall = firConstructorSymbol2.getResolvedDelegatedConstructorCall()) == null || !resolvedDelegatedConstructorCall.getIsThis())) {
                    KtSourceElement source3 = firConstructorSymbol2.getSource();
                    if (!((source3 != null ? source3.getKind() : null) instanceof KtFakeSourceElementKind.PluginGenerated)) {
                        FirDelegatedConstructorCall resolvedDelegatedConstructorCall2 = firConstructorSymbol2.getResolvedDelegatedConstructorCall();
                        if (resolvedDelegatedConstructorCall2 == null || (source2 = resolvedDelegatedConstructorCall2.getSource()) == null) {
                            source2 = firConstructorSymbol2.getSource();
                        }
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source2, FirErrors.INSTANCE.getPRIMARY_CONSTRUCTOR_DELEGATION_CALL_EXPECTED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
            }
        } else {
            for (FirConstructorSymbol firConstructorSymbol3 : linkedHashSet2) {
                FirDelegatedConstructorCall resolvedDelegatedConstructorCall3 = firConstructorSymbol3.getResolvedDelegatedConstructorCall();
                if ((resolvedDelegatedConstructorCall3 != null ? resolvedDelegatedConstructorCall3.getCalleeReference() : null) instanceof FirDiagnosticHolder) {
                    FirDelegatedConstructorCall resolvedDelegatedConstructorCall4 = firConstructorSymbol3.getResolvedDelegatedConstructorCall();
                    if ((((resolvedDelegatedConstructorCall4 == null || (source = resolvedDelegatedConstructorCall4.getSource()) == null) ? null : source.getKind()) instanceof KtFakeSourceElementKind) && !firConstructorSymbol3.getRawStatus().isExpect()) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firConstructorSymbol3.getSource(), FirErrors.INSTANCE.getEXPLICIT_DELEGATION_CALL_REQUIRED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
            }
        }
        Iterator it2 = linkedHashSet.iterator();
        while (it2.hasNext()) {
            FirDelegatedConstructorCall resolvedDelegatedConstructorCall5 = ((FirConstructorSymbol) it2.next()).getResolvedDelegatedConstructorCall();
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, resolvedDelegatedConstructorCall5 != null ? resolvedDelegatedConstructorCall5.getSource() : null, FirErrors.INSTANCE.getCYCLIC_CONSTRUCTOR_DELEGATION_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }
}

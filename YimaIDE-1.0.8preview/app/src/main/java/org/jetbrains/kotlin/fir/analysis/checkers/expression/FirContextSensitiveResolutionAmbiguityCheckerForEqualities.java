package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirContextSensitiveResolutionAmbiguityCheckerForEqualities;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.ImplicitReceiverUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ContextSensitiveResolutionKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirContextSensitiveResolutionAmbiguityCheckerForEqualities;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityOperatorCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;)V", "contextSensitiveCandidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/name/Name;)Ljava/util/List;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirContextSensitiveResolutionAmbiguityCheckerForEqualities extends FirExpressionChecker<FirEqualityOperatorCall> {
    public static final FirContextSensitiveResolutionAmbiguityCheckerForEqualities INSTANCE = new FirContextSensitiveResolutionAmbiguityCheckerForEqualities();

    private FirContextSensitiveResolutionAmbiguityCheckerForEqualities() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextSensitiveCandidates$lambda$0$0(List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (FirContextSensitiveResolutionAmbiguityCheckerKt.isNoArgumentProperty(firVariableSymbol)) {
            list.add(firVariableSymbol);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit contextSensitiveCandidates$lambda$0$1(List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (FirContextSensitiveResolutionAmbiguityCheckerKt.isNoArgumentProperty(firVariableSymbol)) {
            list.add(firVariableSymbol);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0046  */
    /* JADX WARN: Code duplicated, block: B:18:0x004a  */
    /* JADX WARN: Code duplicated, block: B:20:0x0053  */
    /* JADX WARN: Code duplicated, block: B:27:0x006c  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirEqualityOperatorCall firEqualityOperatorCall) {
        FirPropertyAccessExpression firPropertyAccessExpression;
        FirNamedReference calleeReference;
        FirResolvedNamedReference firResolvedNamedReference;
        FirBasedSymbol<?> resolvedSymbol;
        FirBasedSymbol<?> firBasedSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firEqualityOperatorCall.getClass();
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ContextSensitiveResolutionUsingExpectedType)) {
            return;
        }
        FirExpression firExpression = firEqualityOperatorCall.getArgumentList().getArguments().get(1);
        Name name = null;
        if (firExpression instanceof FirErrorResolvedQualifier) {
            firBasedSymbol = null;
        } else if (firExpression instanceof FirResolvedQualifier) {
            FirResolvedQualifier firResolvedQualifier = (FirResolvedQualifier) firExpression;
            if (FirContextSensitiveResolutionAmbiguityCheckerKt.getShouldWarn(firResolvedQualifier.getResolvedSymbolOrigin()) && firResolvedQualifier.getExplicitParent() == null) {
                resolvedSymbol = firResolvedQualifier.getSymbol();
            } else {
                if (firExpression instanceof FirPropertyAccessExpression) {
                    firPropertyAccessExpression = (FirPropertyAccessExpression) firExpression;
                    if (firPropertyAccessExpression.getExplicitReceiver() == null) {
                        calleeReference = firPropertyAccessExpression.getCalleeReference();
                        if (!(calleeReference instanceof FirErrorNamedReference) && (calleeReference instanceof FirResolvedNamedReference)) {
                            firResolvedNamedReference = (FirResolvedNamedReference) calleeReference;
                            if (FirContextSensitiveResolutionAmbiguityCheckerKt.getShouldWarn(firResolvedNamedReference.getResolvedSymbolOrigin())) {
                                resolvedSymbol = firResolvedNamedReference.getResolvedSymbol();
                            }
                        }
                    }
                }
                firBasedSymbol = null;
            }
            firBasedSymbol = resolvedSymbol;
        } else {
            if (firExpression instanceof FirPropertyAccessExpression) {
                firPropertyAccessExpression = (FirPropertyAccessExpression) firExpression;
                if (firPropertyAccessExpression.getExplicitReceiver() == null) {
                    calleeReference = firPropertyAccessExpression.getCalleeReference();
                    if (!(calleeReference instanceof FirErrorNamedReference)) {
                        firResolvedNamedReference = (FirResolvedNamedReference) calleeReference;
                        if (FirContextSensitiveResolutionAmbiguityCheckerKt.getShouldWarn(firResolvedNamedReference.getResolvedSymbolOrigin())) {
                            resolvedSymbol = firResolvedNamedReference.getResolvedSymbol();
                            firBasedSymbol = resolvedSymbol;
                        }
                    }
                }
            }
            firBasedSymbol = null;
        }
        if (firBasedSymbol == null) {
            return;
        }
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            name = ((FirClassLikeSymbol) firBasedSymbol).getName();
        } else if (firBasedSymbol instanceof FirCallableSymbol) {
            name = ((FirCallableSymbol) firBasedSymbol).getName();
        }
        if (name == null) {
            return;
        }
        Iterator it = ContextSensitiveResolutionKt.getParentChainForContextSensitiveResolutionOfExpressions(FirTypeUtilsKt.getResolvedType(firEqualityOperatorCall.getArgumentList().getArguments().get(0)), checkerContext.getSession()).iterator();
        while (it.hasNext()) {
            List<FirBasedSymbol<?>> listContextSensitiveCandidates = contextSensitiveCandidates(checkerContext, (FirRegularClassSymbol) it.next(), name);
            if (!listContextSensitiveCandidates.isEmpty()) {
                if (listContextSensitiveCandidates.contains(firBasedSymbol)) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpression.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getCONTEXT_SENSITIVE_RESOLUTION_AMBIGUITY(), (Object) firBasedSymbol, (Object) listContextSensitiveCandidates, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                return;
            }
        }
    }

    public final List<FirBasedSymbol<?>> contextSensitiveCandidates(CheckerContext checkerContext, FirRegularClassSymbol firRegularClassSymbol, Name name) {
        FirContainingNamesAwareScope firContainingNamesAwareScopeDeclaredMemberScope;
        checkerContext.getClass();
        firRegularClassSymbol.getClass();
        name.getClass();
        final List listCreateListBuilder = CollectionsKt.createListBuilder();
        if (firRegularClassSymbol.getResolvedStatus().getModality() == Modality.SEALED) {
            FirContainingNamesAwareScope firContainingNamesAwareScopeDeclaredMemberScope2 = FirHelpersKt.declaredMemberScope(checkerContext, firRegularClassSymbol);
            final FirContextSensitiveResolutionAmbiguityCheckerForEqualities$contextSensitiveCandidates$1$1 firContextSensitiveResolutionAmbiguityCheckerForEqualities$contextSensitiveCandidates$1$1 = new FirContextSensitiveResolutionAmbiguityCheckerForEqualities$contextSensitiveCandidates$1$1(listCreateListBuilder);
            firContainingNamesAwareScopeDeclaredMemberScope2.processClassifiersByNameWithSubstitution(name, new Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.expression.FirContextSensitiveResolutionAmbiguityCheckerForEqualities$contextSensitiveCandidates$lambda$0$$inlined$processClassifiersByName$1
                public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
                    firClassifierSymbol.getClass();
                    coneSubstitutor.getClass();
                    firContextSensitiveResolutionAmbiguityCheckerForEqualities$contextSensitiveCandidates$1$1.invoke(firClassifierSymbol);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
                    return Unit.INSTANCE;
                }
            });
        }
        FirContainingNamesAwareScope firContainingNamesAwareScopeStaticScope = ImplicitReceiverUtilsKt.staticScope(firRegularClassSymbol, checkerContext.getSessionHolder());
        if (firContainingNamesAwareScopeStaticScope != null) {
            firContainingNamesAwareScopeStaticScope.processPropertiesByName(name, new Function1() { // from class: d05
                public final Object invoke(Object obj) {
                    return FirContextSensitiveResolutionAmbiguityCheckerForEqualities.contextSensitiveCandidates$lambda$0$0(listCreateListBuilder, (FirVariableSymbol) obj);
                }
            });
        }
        FirRegularClassSymbol resolvedCompanionObjectSymbol = firRegularClassSymbol.getResolvedCompanionObjectSymbol();
        if (resolvedCompanionObjectSymbol != null && (firContainingNamesAwareScopeDeclaredMemberScope = FirHelpersKt.declaredMemberScope(checkerContext, resolvedCompanionObjectSymbol)) != null) {
            firContainingNamesAwareScopeDeclaredMemberScope.processPropertiesByName(name, new Function1() { // from class: e05
                public final Object invoke(Object obj) {
                    return FirContextSensitiveResolutionAmbiguityCheckerForEqualities.contextSensitiveCandidates$lambda$0$1(listCreateListBuilder, (FirVariableSymbol) obj);
                }
            });
        }
        return CollectionsKt.build(listCreateListBuilder);
    }
}

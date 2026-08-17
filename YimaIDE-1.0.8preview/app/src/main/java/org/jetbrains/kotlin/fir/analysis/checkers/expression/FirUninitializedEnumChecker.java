package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagWithFixedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousInitializerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0010\u0010\u0013\u001a\u00020\u0014*\u0006\u0012\u0002\b\u00030\u0015H\u0002R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirUninitializedEnumChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "lazyDelegation", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getLazyDelegation", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "isEnumEntryInitializer", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUninitializedEnumChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirUninitializedEnumChecker INSTANCE = new FirUninitializedEnumChecker();

    private FirUninitializedEnumChecker() {
        super(MppCheckerKind.Common);
    }

    private static final void check$reportIllegalAccessInEnumEntry(List<FirEnumEntrySymbol> list, FirEnumEntrySymbol firEnumEntrySymbol, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, FirEnumEntrySymbol firEnumEntrySymbol2) {
        FirEnumEntrySymbol next;
        Iterator<FirEnumEntrySymbol> it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (Intrinsics.areEqual(next, firEnumEntrySymbol)) {
                    break;
                }
            }
        } while (!Intrinsics.areEqual(next, firEnumEntrySymbol2));
        if (Intrinsics.areEqual(next, firEnumEntrySymbol2)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNINITIALIZED_ENUM_ENTRY(), (Object) firEnumEntrySymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirAnonymousFunction getLazyDelegation(FirPropertySymbol firPropertySymbol) {
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firPropertySymbol, FirResolvePhase.BODY_RESOLVE);
        FirProperty firProperty = (FirProperty) firPropertySymbol.getFir();
        if (firProperty.getDelegate() == null || !(firProperty.getDelegate() instanceof FirFunctionCall)) {
            return null;
        }
        FirExpression delegate = firProperty.getDelegate();
        delegate.getClass();
        FirFunctionCall firFunctionCall = (FirFunctionCall) delegate;
        FirNamedFunctionSymbol resolvedNamedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedNamedFunctionSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        if (resolvedNamedFunctionSymbol$default == null || !Intrinsics.areEqual(resolvedNamedFunctionSymbol$default.getCallableId().asSingleFqName().asString(), "kotlin.lazy")) {
            return null;
        }
        Object objSingleOrNull = CollectionsKt.singleOrNull(firFunctionCall.getArgumentList().getArguments());
        FirAnonymousFunctionExpression firAnonymousFunctionExpression = objSingleOrNull instanceof FirAnonymousFunctionExpression ? (FirAnonymousFunctionExpression) objSingleOrNull : null;
        if (firAnonymousFunctionExpression == null) {
            return null;
        }
        return firAnonymousFunctionExpression.getAnonymousFunction();
    }

    private final boolean isEnumEntryInitializer(FirBasedSymbol<?> firBasedSymbol) {
        FirClassLikeSymbol<?> symbol = null;
        if (firBasedSymbol instanceof FirConstructorSymbol) {
            if (!((FirConstructorSymbol) firBasedSymbol).isPrimary()) {
                return false;
            }
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) firBasedSymbol);
            ConeClassLikeLookupTagWithFixedSymbol coneClassLikeLookupTagWithFixedSymbol = coneClassLikeLookupTagContainingClassLookupTag instanceof ConeClassLikeLookupTagWithFixedSymbol ? (ConeClassLikeLookupTagWithFixedSymbol) coneClassLikeLookupTagContainingClassLookupTag : null;
            if (coneClassLikeLookupTagWithFixedSymbol != null) {
                symbol = coneClassLikeLookupTagWithFixedSymbol.getSymbol();
            }
        } else if (firBasedSymbol instanceof FirAnonymousInitializerSymbol) {
            FirBasedSymbol<?> containingDeclarationSymbol = ((FirAnonymousInitializerSymbol) firBasedSymbol).getContainingDeclarationSymbol();
            if (containingDeclarationSymbol instanceof FirClassSymbol) {
                symbol = (FirClassSymbol) containingDeclarationSymbol;
            }
        }
        return symbol != null && FirHelpersKt.getClassKind(symbol) == ClassKind.ENUM_ENTRY;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        KtSourceElement source;
        FirBasedSymbol<?> firBasedSymbolPrevious;
        FirBasedSymbol<?> firBasedSymbol;
        List listEmptyList;
        List<FirBasedSymbol<?>> declarationSymbols;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ProperUninitializedEnumEntryAccessAnalysis) || (source = firQualifiedAccessExpression.getSource()) == null || (source.getKind() instanceof KtFakeSourceElementKind)) {
            return;
        }
        FirBasedSymbol<?> firBasedSymbol2 = null;
        FirBasedSymbol resolvedBaseSymbol$default = FirReferenceUtilsKt.toResolvedBaseSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        if (resolvedBaseSymbol$default == null) {
            return;
        }
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol((FirBasedSymbol<?>) resolvedBaseSymbol$default);
        FirRegularClassSymbol firRegularClassSymbol = containingClassSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) containingClassSymbol : null;
        if (firRegularClassSymbol != null && firRegularClassSymbol.getClassKind() == ClassKind.ENUM_CLASS) {
            if (Intrinsics.areEqual(firRegularClassSymbol.getRawStatus().getVisibility(), Visibilities.Local.INSTANCE) && (resolvedBaseSymbol$default instanceof FirEnumEntrySymbol)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNINITIALIZED_ENUM_ENTRY(), (Object) resolvedBaseSymbol$default, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
            if ((containingDeclarations instanceof Collection) && containingDeclarations.isEmpty()) {
                return;
            }
            Iterator<T> it = containingDeclarations.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(ContainingClassUtilsKt.getContainingClassSymbol((FirBasedSymbol<?>) it.next()), firRegularClassSymbol)) {
                    List<FirBasedSymbol<?>> declarationSymbols2 = firRegularClassSymbol.getDeclarationSymbols();
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : declarationSymbols2) {
                        if (obj instanceof FirPropertySymbol) {
                            arrayList.add(obj);
                        }
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj2 : declarationSymbols2) {
                        if (obj2 instanceof FirAnonymousInitializerSymbol) {
                            arrayList2.add(obj2);
                        }
                    }
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj3 : declarationSymbols2) {
                        if (obj3 instanceof FirEnumEntrySymbol) {
                            arrayList3.add(obj3);
                        }
                    }
                    Map mapCreateMapBuilder = MapsKt.createMapBuilder();
                    for (Object obj4 : arrayList3) {
                        FirAnonymousObjectSymbol initializerObjectSymbol = ((FirEnumEntrySymbol) obj4).getInitializerObjectSymbol();
                        if (initializerObjectSymbol == null || (declarationSymbols = initializerObjectSymbol.getDeclarationSymbols()) == null) {
                            listEmptyList = null;
                        } else {
                            listEmptyList = new ArrayList();
                            for (Object obj5 : declarationSymbols) {
                                if (obj5 instanceof FirAnonymousInitializerSymbol) {
                                    listEmptyList.add(obj5);
                                }
                            }
                        }
                        if (listEmptyList == null) {
                            listEmptyList = CollectionsKt.emptyList();
                        }
                        Iterator it2 = listEmptyList.iterator();
                        while (it2.hasNext()) {
                            mapCreateMapBuilder.put(it2.next(), obj4);
                        }
                    }
                    Map mapBuild = MapsKt.build(mapCreateMapBuilder);
                    List<FirBasedSymbol<?>> containingDeclarations2 = checkerContext.getContainingDeclarations();
                    ListIterator<FirBasedSymbol<?>> listIterator = containingDeclarations2.listIterator(containingDeclarations2.size());
                    do {
                        if (!listIterator.hasPrevious()) {
                            firBasedSymbolPrevious = null;
                            break;
                        }
                        firBasedSymbolPrevious = listIterator.previous();
                        firBasedSymbol = firBasedSymbolPrevious;
                        if (Intrinsics.areEqual(ContainingClassUtilsKt.getContainingClassSymbol(firBasedSymbol), firRegularClassSymbol)) {
                            break;
                        }
                    } while (!mapBuild.containsKey(firBasedSymbol));
                    FirBasedSymbol<?> firBasedSymbol3 = firBasedSymbolPrevious;
                    if (firBasedSymbol3 == null) {
                        return;
                    }
                    List<FirBasedSymbol<?>> containingDeclarations3 = checkerContext.getContainingDeclarations();
                    ListIterator<FirBasedSymbol<?>> listIterator2 = containingDeclarations3.listIterator(containingDeclarations3.size());
                    while (listIterator2.hasPrevious()) {
                        FirBasedSymbol<?> firBasedSymbolPrevious2 = listIterator2.previous();
                        FirBasedSymbol<?> firBasedSymbol4 = firBasedSymbolPrevious2;
                        if (firBasedSymbol4 instanceof FirCallableSymbol) {
                            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) firBasedSymbol4;
                            if (!firCallableSymbol.isLocal() || firCallableSymbol.getDispatchReceiverType() != null) {
                                firBasedSymbol2 = firBasedSymbolPrevious2;
                                break;
                            }
                        }
                    }
                    FirBasedSymbol<?> firBasedSymbol5 = firBasedSymbol2;
                    if (!CollectionsKt.contains(arrayList, firBasedSymbol3) || getLazyDelegation((FirPropertySymbol) firBasedSymbol3) == null) {
                        if ((!CollectionsKt.contains(arrayList3, firBasedSymbol3) || (firBasedSymbol5 != null && isEnumEntryInitializer(firBasedSymbol5))) && CollectionsKt.contains(arrayList3, resolvedBaseSymbol$default)) {
                            FirEnumEntrySymbol firEnumEntrySymbol = (FirEnumEntrySymbol) resolvedBaseSymbol$default;
                            if (CollectionsKt.contains(arrayList, firBasedSymbol3) || CollectionsKt.contains(arrayList2, firBasedSymbol3)) {
                                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNINITIALIZED_ENUM_ENTRY(), (Object) firEnumEntrySymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                                return;
                            }
                            if (CollectionsKt.contains(arrayList3, firBasedSymbol3)) {
                                check$reportIllegalAccessInEnumEntry(arrayList3, firEnumEntrySymbol, checkerContext, diagnosticReporter, source, (FirEnumEntrySymbol) firBasedSymbol3);
                                return;
                            } else {
                                if (mapBuild.containsKey(firBasedSymbol3)) {
                                    FirEnumEntrySymbol firEnumEntrySymbol2 = (FirEnumEntrySymbol) MapsKt.getValue(mapBuild, (FirAnonymousInitializerSymbol) firBasedSymbol3);
                                    if (Intrinsics.areEqual(firEnumEntrySymbol2, resolvedBaseSymbol$default)) {
                                        return;
                                    }
                                    check$reportIllegalAccessInEnumEntry(arrayList3, firEnumEntrySymbol, checkerContext, diagnosticReporter, source, firEnumEntrySymbol2);
                                    return;
                                }
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
            }
        }
    }
}

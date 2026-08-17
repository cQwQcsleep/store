package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDelegateUsesExtensionPropertyTypeParameterChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ?\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00102\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u0012j\b\u0012\u0004\u0012\u00020\u000f`\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0016¨\u0006\u0017²\u0006\n\u0010\u0018\u001a\u00020\u0019X\u008a\u0084\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDelegateUsesExtensionPropertyTypeParameterChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "findUsedTypeParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "typeParameterSymbols", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "delegate", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/HashSet;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "org.jetbrains.kotlin:checkers", "delegateClassScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegateUsesExtensionPropertyTypeParameterChecker extends FirDeclarationChecker<FirProperty> {
    public static final FirDelegateUsesExtensionPropertyTypeParameterChecker INSTANCE = new FirDelegateUsesExtensionPropertyTypeParameterChecker();

    private FirDelegateUsesExtensionPropertyTypeParameterChecker() {
        super(MppCheckerKind.Common);
    }

    public static FirTypeScope b(CheckerContext checkerContext, FirClassSymbol firClassSymbol) {
        return FirHelpersKt.unsubstitutedScope(checkerContext, (FirClassSymbol<?>) firClassSymbol);
    }

    public static Unit c(Ref.BooleanRef booleanRef, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (ConeTypeUtilsKt.contains(firVariableSymbol.getResolvedReturnType(), new Function1() { // from class: l25
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirDelegateUsesExtensionPropertyTypeParameterChecker.findUsedTypeParameterSymbol$lambda$2$0((ConeKotlinType) obj));
            }
        })) {
            booleanRef.element = true;
        }
        return Unit.INSTANCE;
    }

    private final FirTypeParameterSymbol findUsedTypeParameterSymbol(final CheckerContext checkerContext, ConeKotlinType coneKotlinType, HashSet<FirTypeParameterSymbol> hashSet, FirExpression firExpression) {
        final FirClassSymbol<?> classSymbol;
        ConeSimpleKotlinType coneSimpleKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) checkerContext, ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(FirTypeUtilsKt.getResolvedType(firExpression)));
        ConeClassLikeType coneClassLikeType = coneSimpleKotlinTypeFullyExpandedType instanceof ConeClassLikeType ? (ConeClassLikeType) coneSimpleKotlinTypeFullyExpandedType : null;
        if (coneClassLikeType == null || (classSymbol = ToSymbolUtilsKt.toClassSymbol(checkerContext, coneClassLikeType.getLookupTag())) == null) {
            return null;
        }
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: m25
            public final Object invoke() {
                return FirDelegateUsesExtensionPropertyTypeParameterChecker.b(checkerContext, classSymbol);
            }
        });
        for (ConeTypeProjection coneTypeProjection : coneKotlinType.getTypeArguments()) {
            ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
            if (type != null) {
                FirTypeParameterSymbol typeParameterSymbol = ToSymbolUtilsKt.toTypeParameterSymbol(checkerContext, type);
                if (CollectionsKt.contains(hashSet, typeParameterSymbol)) {
                    final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                    FirContainingNamesAwareScopeKt.processAllProperties(findUsedTypeParameterSymbol$lambda$1(lazy), new Function1() { // from class: n25
                        public final Object invoke(Object obj) {
                            return FirDelegateUsesExtensionPropertyTypeParameterChecker.c(booleanRef, (FirVariableSymbol) obj);
                        }
                    });
                    if (booleanRef.element) {
                        return typeParameterSymbol;
                    }
                }
                FirTypeParameterSymbol firTypeParameterSymbolFindUsedTypeParameterSymbol = findUsedTypeParameterSymbol(checkerContext, type, hashSet, firExpression);
                if (firTypeParameterSymbolFindUsedTypeParameterSymbol != null) {
                    return firTypeParameterSymbolFindUsedTypeParameterSymbol;
                }
            }
        }
        return null;
    }

    private static final FirTypeScope findUsedTypeParameterSymbol$lambda$1(Lazy<? extends FirTypeScope> lazy) {
        return (FirTypeScope) lazy.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean findUsedTypeParameterSymbol$lambda$2$0(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return coneKotlinType instanceof ConeTypeParameterType;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        FirExpression delegate;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        if (FirDeclarationUtilKt.isExtension(firProperty) && (delegate = firProperty.getDelegate()) != null) {
            List<FirTypeParameter> typeParameters = firProperty.getTypeParameters();
            HashSet<FirTypeParameterSymbol> hashSet = new HashSet<>();
            Iterator<T> it = typeParameters.iterator();
            while (it.hasNext()) {
                hashSet.add(((FirTypeParameter) it.next()).getSymbol());
            }
            FirTypeParameterSymbol firTypeParameterSymbolFindUsedTypeParameterSymbol = findUsedTypeParameterSymbol(checkerContext, FirTypeUtilsKt.getResolvedType(delegate), hashSet, delegate);
            if (firTypeParameterSymbolFindUsedTypeParameterSymbol == null) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getDELEGATE_USES_EXTENSION_PROPERTY_TYPE_PARAMETER_ERROR(), (Object) firTypeParameterSymbolFindUsedTypeParameterSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }
}

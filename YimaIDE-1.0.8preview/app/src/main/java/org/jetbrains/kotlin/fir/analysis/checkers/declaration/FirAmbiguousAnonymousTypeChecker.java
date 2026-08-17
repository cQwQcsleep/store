package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ7\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u0010*\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAmbiguousAnonymousTypeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkTypeAndArguments", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "reportOn", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/KtSourceElement;)V", "singleExpressionType", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "getSingleExpressionType", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAmbiguousAnonymousTypeChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirAmbiguousAnonymousTypeChecker INSTANCE = new FirAmbiguousAnonymousTypeChecker();

    private FirAmbiguousAnonymousTypeChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x004f  */
    private final void checkTypeAndArguments(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, KtSourceElement ktSourceElement) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        KtSourceElement ktSourceElement2;
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(checkerContext, coneKotlinType);
        if (symbol instanceof FirAnonymousObjectSymbol) {
            FirAnonymousObjectSymbol firAnonymousObjectSymbol = (FirAnonymousObjectSymbol) symbol;
            if (firAnonymousObjectSymbol.getResolvedSuperTypeRefs().size() > 1) {
                KtDiagnosticFactory1<Collection<ConeKotlinType>> ambiguous_anonymous_type_inferred = FirErrors.INSTANCE.getAMBIGUOUS_ANONYMOUS_TYPE_INFERRED();
                List<FirResolvedTypeRef> resolvedSuperTypeRefs = firAnonymousObjectSymbol.getResolvedSuperTypeRefs();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedSuperTypeRefs, 10));
                Iterator<T> it = resolvedSuperTypeRefs.iterator();
                while (it.hasNext()) {
                    arrayList.add(((FirResolvedTypeRef) it.next()).getConeType());
                }
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                ktSourceElement2 = ktSourceElement;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory1) ambiguous_anonymous_type_inferred, (Object) arrayList, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                ktSourceElement2 = ktSourceElement;
            }
        } else {
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
            ktSourceElement2 = ktSourceElement;
        }
        ConeTypeProjection[] typeArguments = coneKotlinType.getTypeArguments();
        for (ConeTypeProjection coneTypeProjection : typeArguments) {
            ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
            if (type != null) {
                checkTypeAndArguments(checkerContext2, diagnosticReporter2, type, ktSourceElement2);
            }
        }
    }

    private final ConeKotlinType getSingleExpressionType(FirBlock firBlock) {
        FirExpression result;
        FirSingleExpressionBlock firSingleExpressionBlock = firBlock instanceof FirSingleExpressionBlock ? (FirSingleExpressionBlock) firBlock : null;
        FirStatement statement = firSingleExpressionBlock != null ? firSingleExpressionBlock.getStatement() : null;
        FirReturnExpression firReturnExpression = statement instanceof FirReturnExpression ? (FirReturnExpression) statement : null;
        if (firReturnExpression == null || (result = firReturnExpression.getResult()) == null) {
            return null;
        }
        return FirTypeUtilsKt.getResolvedType(result);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        Pair pair;
        KtSourceElement source;
        FirBlock body;
        ConeKotlinType resolvedType;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if ((firDeclaration instanceof FirFunction) || (firDeclaration instanceof FirProperty)) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firDeclaration;
            if (DeclarationUtilsKt.getHasExplicitReturnType(firCallableDeclaration.getSymbol()) || firCallableDeclaration.getIsLocal() || !TypeUtilsKt.shouldApproximateLocalTypesOfNonLocalDeclaration(TypeUtilsKt.visibilityForApproximation(firDeclaration, (FirBasedSymbol) CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations())), ((FirMemberDeclaration) firDeclaration).getStatus().isInline())) {
                return;
            }
            ConeKotlinType singleExpressionType = null;
            if (firCallableDeclaration instanceof FirProperty) {
                FirProperty firProperty = (FirProperty) firDeclaration;
                FirExpression initializer = firProperty.getInitializer();
                if (initializer == null || (resolvedType = FirTypeUtilsKt.getResolvedType(initializer)) == null || (pair = TuplesKt.to(resolvedType, firProperty.getSource())) == null) {
                    FirPropertyAccessor getter = firProperty.getGetter();
                    if (firProperty.getDelegate() != null) {
                        source = firProperty.getSource();
                    } else {
                        source = getter != null ? getter.getSource() : null;
                    }
                    if (getter != null && (body = getter.getBody()) != null) {
                        singleExpressionType = getSingleExpressionType(body);
                    }
                    pair = TuplesKt.to(singleExpressionType, source);
                }
            } else if (!(firCallableDeclaration instanceof FirFunction)) {
                k2d.a("Should not be there");
                return;
            } else {
                FirFunction firFunction = (FirFunction) firDeclaration;
                FirBlock body2 = firFunction.getBody();
                pair = TuplesKt.to(body2 != null ? getSingleExpressionType(body2) : null, firFunction.getSource());
            }
            ConeKotlinType coneKotlinType = (ConeKotlinType) pair.component1();
            KtSourceElement ktSourceElement = (KtSourceElement) pair.component2();
            if (coneKotlinType != null) {
                INSTANCE.checkTypeAndArguments(checkerContext, diagnosticReporter, coneKotlinType, ktSourceElement);
            }
        }
    }
}

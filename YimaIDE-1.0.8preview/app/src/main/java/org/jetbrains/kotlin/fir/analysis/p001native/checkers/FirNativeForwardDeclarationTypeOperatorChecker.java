package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.NativeForwardDeclarationKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeForwardDeclarationTypeOperatorChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTypeOperatorCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeForwardDeclarationTypeOperatorChecker extends FirExpressionChecker<FirTypeOperatorCall> {
    public static final FirNativeForwardDeclarationTypeOperatorChecker INSTANCE = new FirNativeForwardDeclarationTypeOperatorChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FirOperation.values().length];
            try {
                iArr[FirOperation.AS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirOperation.SAFE_AS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirOperation.IS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FirOperation.NOT_IS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirNativeForwardDeclarationTypeOperatorChecker() {
        super(MppCheckerKind.Platform);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeOperatorCall firTypeOperatorCall) {
        NativeForwardDeclarationKind nativeForwardDeclarationKindForwardDeclarationKindOrNull;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTypeOperatorCall.getClass();
        FirTypeRef conversionTypeRef = firTypeOperatorCall.getConversionTypeRef();
        FirRegularClassSymbol regularClassSymbol = TypeUtilsKt.toRegularClassSymbol(conversionTypeRef, checkerContext.getSession());
        if (regularClassSymbol == null || (nativeForwardDeclarationKindForwardDeclarationKindOrNull = FirNativeHelpersKt.forwardDeclarationKindOrNull(regularClassSymbol)) == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[firTypeOperatorCall.getOperation().ordinal()];
        if (i != 1 && i != 2) {
            if (i == 3 || i == 4) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeOperatorCall.getSource(), (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getCANNOT_CHECK_FOR_FORWARD_DECLARATION(), (Object) FirTypeUtilsKt.getConeType(conversionTypeRef), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
            return;
        }
        FirRegularClassSymbol regularClassSymbol2 = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, FirTypeUtilsKt.getResolvedType((FirExpression) CollectionsKt.first(firTypeOperatorCall.getArgumentList().getArguments())));
        if (regularClassSymbol2 != null && regularClassSymbol2.getClassKind() == nativeForwardDeclarationKindForwardDeclarationKindOrNull.getClassKind() && Intrinsics.areEqual(regularClassSymbol2.getName(), regularClassSymbol.getName())) {
            List<ConeClassLikeType> listLookupSuperTypes = SupertypeUtilsKt.lookupSuperTypes(regularClassSymbol2, true, true, checkerContext.getSession());
            if (!(listLookupSuperTypes instanceof Collection) || !listLookupSuperTypes.isEmpty()) {
                Iterator<T> it = listLookupSuperTypes.iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId((ConeClassLikeType) it.next()), nativeForwardDeclarationKindForwardDeclarationKindOrNull.getMatchSuperClassId())) {
                        return;
                    }
                }
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeOperatorCall.getSource(), (KtDiagnosticFactory2) FirNativeErrors.INSTANCE.getUNCHECKED_CAST_TO_FORWARD_DECLARATION(), (Object) FirTypeUtilsKt.getResolvedType((FirExpression) CollectionsKt.first(firTypeOperatorCall.getArgumentList().getArguments())), (Object) FirTypeUtilsKt.getResolvedType((FirExpression) CollectionsKt.first(firTypeOperatorCall.getArgumentList().getArguments())), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }
}

package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.backend.konan.IntrinsicType;
import org.jetbrains.kotlin.backend.konan.KonanFqNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.ArrayOfUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.NativeForwardDeclarationKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000b\u001a\f\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u000e\u001a\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012\u001a\u0012\u0010\u0013\u001a\u00020\u0014*\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016¨\u0006\u0017"}, d2 = {"reportIfHasAnnotation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "annotationClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "error", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;)V", "forwardDeclarationKindOrNull", "Lorg/jetbrains/kotlin/name/NativeForwardDeclarationKind;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "tryGetIntrinsicType", "Lorg/jetbrains/kotlin/backend/konan/IntrinsicType;", "callSite", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "isArrayOfCall", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers.native"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeHelpersKt {
    public static final NativeForwardDeclarationKind forwardDeclarationKindOrNull(FirRegularClassSymbol firRegularClassSymbol) {
        firRegularClassSymbol.getClass();
        return (NativeForwardDeclarationKind) NativeForwardDeclarationKind.Companion.getPackageFqNameToKind().get(firRegularClassSymbol.getClassId().getPackageFqName());
    }

    public static final boolean isArrayOfCall(FirFunctionCall firFunctionCall, FirSession firSession) {
        firFunctionCall.getClass();
        firSession.getClass();
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firFunctionCall);
        FirNamedFunctionSymbol firNamedFunctionSymbol = resolvedCallableSymbol instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) resolvedCallableSymbol : null;
        return firNamedFunctionSymbol != null && ArrayOfUtilsKt.isArrayOfFunction(firNamedFunctionSymbol, firSession, firFunctionCall.getArgumentList());
    }

    public static final void reportIfHasAnnotation(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, ClassId classId, KtDiagnosticFactory0 ktDiagnosticFactory0) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        classId.getClass();
        ktDiagnosticFactory0.getClass();
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firDeclaration, classId, checkerContext.getSession());
        if (annotationByClassId != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), ktDiagnosticFactory0, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    public static final IntrinsicType tryGetIntrinsicType(FirFunctionCall firFunctionCall) {
        Object obj;
        firFunctionCall.getClass();
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firFunctionCall);
        if (resolvedCallableSymbol == null) {
            return null;
        }
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(resolvedCallableSymbol, ClassId.Companion.topLevel(KonanFqNames.INSTANCE.getTypedIntrinsic()), resolvedCallableSymbol.getModuleData().getSession());
        if (annotationByClassId == null) {
            return null;
        }
        Object objFirstOrNull = CollectionsKt.firstOrNull(annotationByClassId.getArgumentMapping().getMapping().values());
        FirLiteralExpression firLiteralExpression = objFirstOrNull instanceof FirLiteralExpression ? (FirLiteralExpression) objFirstOrNull : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        String str = value instanceof String ? (String) value : null;
        if (str == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.constructor-impl(IntrinsicType.valueOf(str));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        return (IntrinsicType) (Result.isFailure-impl(obj) ? null : obj);
    }
}

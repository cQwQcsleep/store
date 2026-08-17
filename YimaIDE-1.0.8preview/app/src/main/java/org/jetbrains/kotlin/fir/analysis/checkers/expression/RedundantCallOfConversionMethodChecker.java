package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ-\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0017\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00070\u0018j\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0007`\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/RedundantCallOfConversionMethodChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "unsafeNumberClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "isRedundant", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "qualifiedClassId", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/FirSession;)Z", "targetClassMap", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/name/Name;", "Lkotlin/collections/HashMap;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantCallOfConversionMethodChecker extends FirExpressionChecker<FirFunctionCall> {
    private static final HashMap<Name, ClassId> targetClassMap;
    public static final RedundantCallOfConversionMethodChecker INSTANCE = new RedundantCallOfConversionMethodChecker();
    private static final ClassId unsafeNumberClassId = ClassId.Companion.fromString$default(ClassId.Companion, "kotlinx.cinterop/UnsafeNumber", false, 2, (Object) null);

    static {
        Name name = OperatorNameConventions.TO_STRING;
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        targetClassMap = MapsKt.hashMapOf(new Pair[]{TuplesKt.to(name, standardClassIds.getString()), TuplesKt.to(OperatorNameConventions.TO_DOUBLE, standardClassIds.getDouble()), TuplesKt.to(OperatorNameConventions.TO_FLOAT, standardClassIds.getFloat()), TuplesKt.to(OperatorNameConventions.TO_LONG, standardClassIds.getLong()), TuplesKt.to(OperatorNameConventions.TO_INT, standardClassIds.getInt()), TuplesKt.to(OperatorNameConventions.TO_CHAR, standardClassIds.getChar()), TuplesKt.to(OperatorNameConventions.TO_SHORT, standardClassIds.getShort()), TuplesKt.to(OperatorNameConventions.TO_BYTE, standardClassIds.getByte()), TuplesKt.to(OperatorNameConventions.TO_ULONG, standardClassIds.getULong()), TuplesKt.to(OperatorNameConventions.TO_UINT, standardClassIds.getUInt()), TuplesKt.to(OperatorNameConventions.TO_USHORT, standardClassIds.getUShort()), TuplesKt.to(OperatorNameConventions.TO_UBYTE, standardClassIds.getUByte())});
    }

    private RedundantCallOfConversionMethodChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean isRedundant(CheckerContext checkerContext, FirExpression firExpression, ClassId classId, FirSession firSession) {
        FirClassLikeSymbol<?> classLikeSymbol;
        ClassId classIdFullyExpandedClassId;
        if (FirHelpersKt.hasIntegerLiteralTypeAmbiguity(firExpression)) {
            return false;
        }
        if (firExpression instanceof FirLiteralExpression) {
            classIdFullyExpandedClassId = ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getResolvedType(firExpression));
        } else {
            classIdFullyExpandedClassId = ((FirTypeUtilsKt.getResolvedType(firExpression) instanceof ConeFlexibleType) || ConeTypeUtilsKt.isMarkedNullable(FirTypeUtilsKt.getResolvedType(firExpression)) || ((classLikeSymbol = ToSymbolUtilsKt.toClassLikeSymbol(checkerContext, AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(FirTypeUtilsKt.getResolvedType(firExpression)))) != null && FirAnnotationUtilsKt.hasAnnotation(classLikeSymbol, unsafeNumberClassId, firSession))) ? null : FirHelpersKt.fullyExpandedClassId(FirTypeUtilsKt.getResolvedType(firExpression), firSession);
        }
        return Intrinsics.areEqual(classIdFullyExpandedClassId, classId);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        FirExpression dispatchReceiver;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        if (firFunctionCall.getExtensionReceiver() != null) {
            return;
        }
        ClassId classId = targetClassMap.get(firFunctionCall.getCalleeReference().getName());
        if (classId == null || (dispatchReceiver = firFunctionCall.getDispatchReceiver()) == null || !isRedundant(checkerContext, dispatchReceiver, classId, checkerContext.getSession())) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), FirErrors.INSTANCE.getREDUNDANT_CALL_OF_CONVERSION_METHOD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }
}

package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0002H\u0016R\u00020\u0013R\u00020\u0015j\u0006\u0010\u0014\u001a\u00020\u0013j\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0002\u0010\u0018J-\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0002H\u0002R\u00020\u0013R\u00020\u0015j\u0006\u0010\u0014\u001a\u00020\u0013j\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0002\u0010\u0018R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJvmIdentitySensitiveCallWithValueTypeObjectChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "synchronizedCallableId", "Lorg/jetbrains/kotlin/name/CallableId;", "lockParameterName", "Lorg/jetbrains/kotlin/name/Name;", "operationsToCheckFirstArgCallableIds", Argument.Delimiters.none, "operationsToCheckFirstTypeArgCallableIds", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "checkSynchronizedCall", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmIdentitySensitiveCallWithValueTypeObjectChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirJvmIdentitySensitiveCallWithValueTypeObjectChecker INSTANCE = new FirJvmIdentitySensitiveCallWithValueTypeObjectChecker();
    private static final Name lockParameterName;
    private static final Set<CallableId> operationsToCheckFirstArgCallableIds;
    private static final Set<CallableId> operationsToCheckFirstTypeArgCallableIds;
    private static final CallableId synchronizedCallableId;

    static {
        FqName fqName = new FqName("kotlin");
        Name nameIdentifier = Name.identifier("synchronized");
        nameIdentifier.getClass();
        synchronizedCallableId = new CallableId(fqName, nameIdentifier);
        Name nameIdentifier2 = Name.identifier("lock");
        nameIdentifier2.getClass();
        lockParameterName = nameIdentifier2;
        FqName fqName2 = new FqName("java.lang");
        FqName fqName3 = new FqName("System");
        Name nameIdentifier3 = Name.identifier("identityHashCode");
        nameIdentifier3.getClass();
        CallableId callableId = new CallableId(fqName2, fqName3, nameIdentifier3);
        FqName fqName4 = new FqName("java.lang.ref");
        FqName fqName5 = new FqName("Cleaner");
        Name nameIdentifier4 = Name.identifier("register");
        nameIdentifier4.getClass();
        CallableId callableId2 = new CallableId(fqName4, fqName5, nameIdentifier4);
        FqName fqName6 = new FqName("java.lang.ref");
        FqName fqName7 = new FqName("PhantomReference");
        Name nameIdentifier5 = Name.identifier("PhantomReference");
        nameIdentifier5.getClass();
        CallableId callableId3 = new CallableId(fqName6, fqName7, nameIdentifier5);
        FqName fqName8 = new FqName("java.lang.ref");
        FqName fqName9 = new FqName("SoftReference");
        Name nameIdentifier6 = Name.identifier("SoftReference");
        nameIdentifier6.getClass();
        CallableId callableId4 = new CallableId(fqName8, fqName9, nameIdentifier6);
        FqName fqName10 = new FqName("java.lang.ref");
        FqName fqName11 = new FqName("WeakReference");
        Name nameIdentifier7 = Name.identifier("WeakReference");
        nameIdentifier7.getClass();
        operationsToCheckFirstArgCallableIds = SetsKt.setOf(new CallableId[]{callableId, callableId2, callableId3, callableId4, new CallableId(fqName10, fqName11, nameIdentifier7)});
        FqName fqName12 = new FqName("java.util");
        FqName fqName13 = new FqName("IdentityHashMap");
        Name nameIdentifier8 = Name.identifier("IdentityHashMap");
        nameIdentifier8.getClass();
        CallableId callableId5 = new CallableId(fqName12, fqName13, nameIdentifier8);
        FqName fqName14 = new FqName("java.util");
        FqName fqName15 = new FqName("WeakHashMap");
        Name nameIdentifier9 = Name.identifier("WeakHashMap");
        nameIdentifier9.getClass();
        operationsToCheckFirstTypeArgCallableIds = SetsKt.setOf(new CallableId[]{callableId5, new CallableId(fqName14, fqName15, nameIdentifier9)});
    }

    private FirJvmIdentitySensitiveCallWithValueTypeObjectChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void checkSynchronizedCall(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) throws KotlinIllegalArgumentExceptionWithAttachments {
        Set<Map.Entry<FirExpression, FirValueParameter>> setEntrySet;
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        FirArgumentList argumentList = firFunctionCall.getArgumentList();
        LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
        if (mapping == null || (setEntrySet = mapping.entrySet()) == null) {
            return;
        }
        for (Map.Entry<FirExpression, FirValueParameter> entry : setEntrySet) {
            entry.getClass();
            Map.Entry<FirExpression, FirValueParameter> entry2 = entry;
            FirExpression key = entry2.getKey();
            key.getClass();
            FirExpression firExpression = key;
            FirValueParameter value = entry2.getValue();
            value.getClass();
            if (Intrinsics.areEqual(value.getName(), lockParameterName)) {
                ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
                if (ConeBuiltinTypeUtilsKt.isPrimitive(resolvedType) || FirHelpersKt.isValueClass(resolvedType, checkerContext.getSession())) {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firExpression.getSource(), (KtDiagnosticFactoryForDeprecation1) FirJvmErrors.INSTANCE.getSYNCHRONIZED_BLOCK_ON_VALUE_CLASS_OR_PRIMITIVE(), (Object) resolvedType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                } else {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                }
                if (FirJavaValueBasedClassUtilsKt.isJavaValueBasedClassAndWarningsEnabled(checkerContext2, resolvedType)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firExpression.getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getSYNCHRONIZED_BLOCK_ON_JAVA_VALUE_BASED_CLASS(), (Object) resolvedType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                if (LanguageVersionUtilsKt.enableWarningsForIdentitySensitiveOperationsOnValueClassesAndPrimitives(checkerContext2) && FirJavaValueBasedClassUtilsKt.isFlexiblePrimitive(resolvedType)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firExpression.getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getIDENTITY_SENSITIVE_OPERATIONS_WITH_VALUE_TYPE(), (Object) resolvedType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                checkerContext = checkerContext2;
                diagnosticReporter = diagnosticReporter2;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        if (resolvedCallableSymbol$default == null) {
            return;
        }
        CallableId callableId = resolvedCallableSymbol$default.getCallableId();
        if (Intrinsics.areEqual(callableId, synchronizedCallableId)) {
            checkSynchronizedCall(checkerContext, diagnosticReporter, firFunctionCall);
            return;
        }
        if (CollectionsKt.contains(operationsToCheckFirstArgCallableIds, callableId)) {
            FirExpression firExpression = (FirExpression) CollectionsKt.firstOrNull(firFunctionCall.getArgumentList().getArguments());
            if (firExpression == null || (resolvedType = FirTypeUtilsKt.getResolvedType(firExpression)) == null || !FirJavaValueBasedClassUtilsKt.isValueTypeAndWarningsEnabled(checkerContext, resolvedType)) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirExpression) CollectionsKt.first(firFunctionCall.getArgumentList().getArguments())).getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getIDENTITY_SENSITIVE_OPERATIONS_WITH_VALUE_TYPE(), (Object) resolvedType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        if (CollectionsKt.contains(operationsToCheckFirstTypeArgCallableIds, callableId)) {
            Object objFirstOrNull = CollectionsKt.firstOrNull(firFunctionCall.getTypeArguments());
            FirTypeProjectionWithVariance firTypeProjectionWithVariance = objFirstOrNull instanceof FirTypeProjectionWithVariance ? (FirTypeProjectionWithVariance) objFirstOrNull : null;
            if (firTypeProjectionWithVariance == null) {
                return;
            }
            ConeRigidType coneRigidTypeUpperBoundIfFlexible = ConeTypeUtilsKt.upperBoundIfFlexible(FirTypeUtilsKt.getConeType(firTypeProjectionWithVariance.getTypeRef()));
            if (FirJavaValueBasedClassUtilsKt.isValueTypeAndWarningsEnabled(checkerContext, coneRigidTypeUpperBoundIfFlexible)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeProjectionWithVariance.getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getIDENTITY_SENSITIVE_OPERATIONS_WITH_VALUE_TYPE(), (Object) coneRigidTypeUpperBoundIfFlexible, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}

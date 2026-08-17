package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
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
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0002H\u0016R\u00020\u0011R\u00020\u0013j\u0006\u0010\u0012\u001a\u00020\u0011j\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJvmSuspensionPointInsideMutexLockChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "synchronizedCallableId", "Lorg/jetbrains/kotlin/name/CallableId;", "withLockCallableId", "synchronizedBlockParamName", "Lorg/jetbrains/kotlin/name/Name;", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmSuspensionPointInsideMutexLockChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirJvmSuspensionPointInsideMutexLockChecker INSTANCE = new FirJvmSuspensionPointInsideMutexLockChecker();
    private static final Name synchronizedBlockParamName;
    private static final CallableId synchronizedCallableId;
    private static final CallableId withLockCallableId;

    static {
        FqName fqName = new FqName("kotlin");
        Name nameIdentifier = Name.identifier("synchronized");
        nameIdentifier.getClass();
        synchronizedCallableId = new CallableId(fqName, nameIdentifier);
        FqName fqName2 = new FqName("kotlin.concurrent");
        Name nameIdentifier2 = Name.identifier("withLock");
        nameIdentifier2.getClass();
        withLockCallableId = new CallableId(fqName2, nameIdentifier2);
        Name nameIdentifier3 = Name.identifier("block");
        nameIdentifier3.getClass();
        synchronizedBlockParamName = nameIdentifier3;
    }

    private FirJvmSuspensionPointInsideMutexLockChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:73:0x00e2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x0032 A[SYNTHETIC] */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        FirValueParameter value;
        ConeKotlinType coneType;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        boolean z = false;
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null);
        if (resolvedCallableSymbol$default != null && resolvedCallableSymbol$default.getRawStatus().isSuspend()) {
            boolean z2 = false;
            FirElement firElement = null;
            for (FirElement firElement2 : CollectionsKt.asReversed(checkerContext.getContainingElements())) {
                if (firElement2 instanceof FirFunctionCall) {
                    FirCallableSymbol resolvedCallableSymbol$default2 = FirReferenceUtilsKt.toResolvedCallableSymbol$default(((FirFunctionCall) firElement2).getCalleeReference(), false, 1, null);
                    if (resolvedCallableSymbol$default2 != null) {
                        FirArgumentList argumentList = ((FirCall) firElement2).getArgumentList();
                        LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
                        if (mapping == null) {
                            value = null;
                            break;
                        }
                        Iterator<Map.Entry<FirExpression, FirValueParameter>> it = mapping.entrySet().iterator();
                        do {
                            if (!it.hasNext()) {
                                value = null;
                                break;
                            } else {
                                Map.Entry<FirExpression, FirValueParameter> next = it.next();
                                FirAnonymousFunction firAnonymousFunctionUnwrapAnonymousFunctionExpression = FirExpressionUtilKt.unwrapAnonymousFunctionExpression(next.getKey());
                                value = (firAnonymousFunctionUnwrapAnonymousFunctionExpression == null || !Intrinsics.areEqual(firAnonymousFunctionUnwrapAnonymousFunctionExpression, firElement)) ? null : next.getValue();
                            }
                        } while (value == null);
                        FirTypeRef returnTypeRef = value != null ? value.getReturnTypeRef() : null;
                        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) returnTypeRef : null;
                        if (firResolvedTypeRef != null && (coneType = firResolvedTypeRef.getConeType()) != null && FunctionalTypeUtilsKt.isSuspendOrKSuspendFunctionType(coneType, checkerContext.getSession())) {
                            z = true;
                            break;
                        } else if (Intrinsics.areEqual(resolvedCallableSymbol$default2.getCallableId(), synchronizedCallableId)) {
                            if (!Intrinsics.areEqual(value != null ? value.getName() : null, synchronizedBlockParamName)) {
                                if (Intrinsics.areEqual(resolvedCallableSymbol$default2.getCallableId(), withLockCallableId)) {
                                }
                            }
                            z2 = true;
                        } else if (Intrinsics.areEqual(resolvedCallableSymbol$default2.getCallableId(), withLockCallableId)) {
                            z2 = true;
                        }
                    } else {
                        continue;
                    }
                } else if (!(firElement2 instanceof FirFunction)) {
                    continue;
                } else if (((FirMemberDeclaration) firElement2).getStatus().isSuspend()) {
                    z = true;
                    break;
                } else if (firElement2 instanceof FirAnonymousFunction) {
                    firElement = firElement2;
                }
            }
            if (z2 && z) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getSUSPENSION_POINT_INSIDE_CRITICAL_SECTION(), (Object) resolvedCallableSymbol$default, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}

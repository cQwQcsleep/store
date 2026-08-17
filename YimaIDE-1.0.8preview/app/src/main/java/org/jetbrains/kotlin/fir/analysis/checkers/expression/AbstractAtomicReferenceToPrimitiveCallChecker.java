package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.CallableIdKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B?\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\f\"\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ-\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0002H\u0016R\u00020\u0017R\u00020\u0019j\u0006\u0010\u0018\u001a\u00020\u0017j\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0002\u0010\u001cJ\u001c\u0010\u001d\u001a\u00020\u001e2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 2\u0006\u0010!\u001a\u00020\"H\u0014R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/AbstractAtomicReferenceToPrimitiveCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "appropriateCandidatesForArgument", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "firstProblematicCallableId", "Lorg/jetbrains/kotlin/name/CallableId;", "remainingProblematicCallableIds", Argument.Delimiters.none, "<init>", "(Ljava/util/Map;Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;Lorg/jetbrains/kotlin/name/CallableId;[Lorg/jetbrains/kotlin/name/CallableId;)V", "getAppropriateCandidatesForArgument", "()Ljava/util/Map;", "problematicCallableIds", Argument.Delimiters.none, "getProblematicCallableIds", "()Ljava/util/Set;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "isDangerousAtomicCallParameterNameWithin", Argument.Delimiters.none, "function", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractAtomicReferenceToPrimitiveCallChecker extends FirExpressionChecker<FirFunctionCall> {
    private final Map<ClassId, ClassId> appropriateCandidatesForArgument;
    private final Set<CallableId> problematicCallableIds;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractAtomicReferenceToPrimitiveCallChecker(Map<ClassId, ClassId> map, MppCheckerKind mppCheckerKind, CallableId callableId, CallableId... callableIdArr) {
        super(mppCheckerKind);
        map.getClass();
        mppCheckerKind.getClass();
        callableId.getClass();
        callableIdArr.getClass();
        this.appropriateCandidatesForArgument = map;
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.add(callableId);
        spreadBuilder.addSpread(callableIdArr);
        this.problematicCallableIds = SetsKt.setOf(spreadBuilder.toArray(new CallableId[spreadBuilder.size()]));
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        FirExpression dispatchReceiver;
        ConeKotlinType resolvedType;
        ConeKotlinType coneKotlinTypeFullyExpandedType;
        ClassId classId;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(firFunctionCall.getCalleeReference());
        FirBasedSymbol<?> resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
        FirFunctionSymbol<?> firFunctionSymbol = resolvedSymbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) resolvedSymbol : null;
        if (firFunctionSymbol == null || (dispatchReceiver = firFunctionCall.getDispatchReceiver()) == null || (resolvedType = FirTypeUtilsKt.getResolvedType(dispatchReceiver)) == null || (coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, resolvedType)) == null || (classId = ConeTypeUtilsKt.getClassId(coneKotlinTypeFullyExpandedType)) == null) {
            return;
        }
        if (this.problematicCallableIds.contains(CallableIdKt.withClassId(firFunctionSymbol.getCallableId(), classId))) {
            FirHelpersKt.checkAtomicCallReceiverForStableIdentity(checkerContext, diagnosticReporter, coneKotlinTypeFullyExpandedType, firFunctionCall.getSource(), classId, this.appropriateCandidatesForArgument);
            for (Pair pair : CollectionsKt.zip(firFunctionCall.getArgumentList().getArguments(), firFunctionSymbol.getValueParameterSymbols())) {
                FirExpression firExpression = (FirExpression) pair.component1();
                FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) pair.component2();
                if (!FirHelpersKt.getHasStableIdentityForAtomicOperations(checkerContext, FirTypeUtilsKt.getResolvedType(firExpression)) && isDangerousAtomicCallParameterNameWithin(firFunctionSymbol, firValueParameterSymbol.getName())) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpression.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getATOMIC_REF_CALL_ARGUMENT_WITHOUT_CONSISTENT_IDENTITY(), (Object) FirTypeUtilsKt.getResolvedType(firExpression), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
    }

    public final Map<ClassId, ClassId> getAppropriateCandidatesForArgument() {
        return this.appropriateCandidatesForArgument;
    }

    public final Set<CallableId> getProblematicCallableIds() {
        return this.problematicCallableIds;
    }

    public boolean isDangerousAtomicCallParameterNameWithin(FirFunctionSymbol<?> function, Name name) {
        function.getClass();
        name.getClass();
        return Intrinsics.areEqual(name, Name.identifier("expectedValue")) || Intrinsics.areEqual(name, Name.identifier("expected")) || Intrinsics.areEqual(name, Name.identifier("newValue")) || Intrinsics.areEqual(name, Name.identifier("expect")) || Intrinsics.areEqual(name, Name.identifier("update"));
    }
}

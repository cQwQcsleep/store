package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirAccidentalOverrideClashChecker;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.scopes.jvm.SignatureUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.load.java.SpecialGenericSignatures;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirAccidentalOverrideClashChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSimpleFunctionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)V", "namesPossibleForRenamedBuiltin", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAccidentalOverrideClashChecker extends FirDeclarationChecker<FirNamedFunction> {
    public static final FirAccidentalOverrideClashChecker INSTANCE = new FirAccidentalOverrideClashChecker();
    private static final Set<Name> namesPossibleForRenamedBuiltin = CollectionsKt.toSet(SpecialGenericSignatures.Companion.getJVM_SHORT_NAME_TO_BUILTIN_SHORT_NAMES_MAP().values());

    private FirAccidentalOverrideClashChecker() {
        super(MppCheckerKind.Platform);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit b(Ref.BooleanRef booleanRef, FirNamedFunction firNamedFunction, boolean z, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        Modality modality;
        firNamedFunctionSymbol.getClass();
        FirNamedFunction firNamedFunction2 = (FirNamedFunction) firNamedFunctionSymbol.getFir();
        if (!booleanRef.element && Intrinsics.areEqual(DeprecationUtilsKt.isHiddenToOvercomeSignatureClash(firNamedFunction2), Boolean.TRUE) && (modality = firNamedFunction2.getStatus().getModality()) != null && modality != Modality.FINAL && Intrinsics.areEqual(SignatureUtilsKt.computeJvmDescriptor$default(firNamedFunction, null, false, null, 7, null), SignatureUtilsKt.computeJvmDescriptor$default(firNamedFunction2, null, false, null, 7, null))) {
            FirNamedFunctionSymbol initialSignatureAttr = ClassMembersKt.getInitialSignatureAttr(firNamedFunction2);
            if (initialSignatureAttr == null) {
                return Unit.INSTANCE;
            }
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunction.getSource(), (KtDiagnosticFactory3<FirNamedFunctionSymbol, String, FirNamedFunctionSymbol>) ((KtDiagnosticFactory3<Object, Object, Object>) FirJvmErrors.INSTANCE.getACCIDENTAL_OVERRIDE_CLASH_BY_JVM_SIGNATURE()), firNamedFunctionSymbol, z ? "a renamed function" : "a function with erased parameters", initialSignatureAttr, (64 & 64) != 0 ? null : null);
            booleanRef.element = true;
        }
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, final FirNamedFunction firNamedFunction) {
        FirRegularClass containingClass;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firNamedFunction.getClass();
        if (firNamedFunction.getStatus().isOverride()) {
            Name name = firNamedFunction.getName();
            final boolean zContains = namesPossibleForRenamedBuiltin.contains(name);
            boolean sameAsBuiltinMethodWithErasedValueParameters = SpecialGenericSignatures.Companion.getSameAsBuiltinMethodWithErasedValueParameters(name);
            if ((zContains || sameAsBuiltinMethodWithErasedValueParameters) && (containingClass = ContainingClassUtilsKt.getContainingClass(firNamedFunction)) != null) {
                final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                FirHelpersKt.unsubstitutedScope(checkerContext, containingClass).processFunctionsByName(name, new Function1() { // from class: zx4
                    public final Object invoke(Object obj) {
                        return FirAccidentalOverrideClashChecker.b(booleanRef, firNamedFunction, zContains, checkerContext, diagnosticReporter, (FirNamedFunctionSymbol) obj);
                    }
                });
            }
        }
    }
}

package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFunctionParameterChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;
import org.jetbrains.kotlin.lexer.KtKeywordToken;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0013J-\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ/\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0000R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0018\u0010\u0013J-\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionParameterChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "checkParameterTypes", "function", "checkParameterType", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)V", "checkVarargParameters", "checkUninitializedParameter", "checkValOrVarParameter", "checkValOrVar", "checkValOrVar$org_jetbrains_kotlin_checkers", "checkParameterNameChangedOnOverride", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFunctionParameterChecker extends FirDeclarationChecker<FirFunction> {
    public static final FirFunctionParameterChecker INSTANCE = new FirFunctionParameterChecker();

    private FirFunctionParameterChecker() {
        super(MppCheckerKind.Common);
    }

    public static Unit b(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirValueParameterSymbol firValueParameterSymbol, FirValueParameterSymbol firValueParameterSymbol2, int i) {
        firValueParameterSymbol.getClass();
        firValueParameterSymbol2.getClass();
        KtSourceElement source = firValueParameterSymbol.getSource();
        KtDiagnosticFactory2<FirRegularClassSymbol, FirValueParameterSymbol> parameter_name_changed_on_override = FirErrors.INSTANCE.getPARAMETER_NAME_CHANGED_ON_OVERRIDE();
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firValueParameterSymbol2.getContainingDeclarationSymbol());
        containingClassSymbol.getClass();
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory2) parameter_name_changed_on_override, containingClassSymbol, (Object) firValueParameterSymbol2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        return Unit.INSTANCE;
    }

    private final void checkParameterNameChangedOnOverride(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        if ((firFunction instanceof FirNamedFunction) && firFunction.getStatus().isOverride() && firFunction.getStatus().getHasStableParameterNames()) {
            FirNamedFunction firNamedFunction = (FirNamedFunction) firFunction;
            for (FirNamedFunctionSymbol firNamedFunctionSymbol : FirHelpersKt.directOverriddenFunctionsSafe(checkerContext, firNamedFunction.getSymbol())) {
                if (firNamedFunctionSymbol.getResolvedStatus().getHasStableParameterNames()) {
                    DeclarationUtilsKt.checkValueParameterNamesWith(firNamedFunction.getSymbol(), firNamedFunctionSymbol, (Function3<? super FirValueParameterSymbol, ? super FirValueParameterSymbol, ? super Integer, Unit>) new Function3() { // from class: z75
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return FirFunctionParameterChecker.b(checkerContext, diagnosticReporter, (FirValueParameterSymbol) obj, (FirValueParameterSymbol) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
        }
    }

    private final void checkParameterType(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirValueParameter firValueParameter) {
        Object returnTypeRef = firValueParameter.getReturnTypeRef();
        if (returnTypeRef instanceof FirErrorTypeRef) {
            FirErrorTypeRef firErrorTypeRef = (FirErrorTypeRef) returnTypeRef;
            KtSourceElement source = firErrorTypeRef.getSource();
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtRealSourceElementKind.INSTANCE)) {
                return;
            }
            ConeDiagnostic diagnostic = firErrorTypeRef.getDiagnostic();
            if ((diagnostic instanceof ConeSimpleDiagnostic) && ((ConeSimpleDiagnostic) diagnostic).getKind() == DiagnosticKind.ValueParameterWithNoTypeAnnotation) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), FirErrors.INSTANCE.getVALUE_PARAMETER_WITHOUT_EXPLICIT_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final void checkParameterTypes(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        if (firFunction instanceof FirAnonymousFunction) {
            return;
        }
        Iterator<FirValueParameter> it = firFunction.getValueParameters().iterator();
        while (it.hasNext()) {
            checkParameterType(diagnosticReporter, checkerContext, it.next());
        }
        Iterator<FirValueParameter> it2 = firFunction.getContextParameters().iterator();
        while (it2.hasNext()) {
            checkParameterType(diagnosticReporter, checkerContext, it2.next());
        }
    }

    private final void checkUninitializedParameter(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, final FirFunction firFunction) {
        Iterator<T> it = firFunction.getValueParameters().iterator();
        final int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            FirExpression defaultValue = ((FirValueParameter) it.next()).getDefaultValue();
            if (defaultValue != null) {
                defaultValue.accept(new FirVisitorVoid() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFunctionParameterChecker.checkUninitializedParameter.1
                    public void visitElement(FirElement element) {
                        element.getClass();
                        element.acceptChildren(this);
                    }

                    public void visitPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression) {
                        propertyAccessExpression.getClass();
                        visitQualifiedAccessExpression(propertyAccessExpression);
                    }

                    public void visitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression) {
                        qualifiedAccessExpression.getClass();
                        int i3 = 0;
                        FirValueParameterSymbol resolvedValueParameterSymbol$default = FirReferenceUtilsKt.toResolvedValueParameterSymbol$default(qualifiedAccessExpression.getCalleeReference(), false, 1, null);
                        if (resolvedValueParameterSymbol$default == null) {
                            return;
                        }
                        Iterator<FirValueParameter> it2 = firFunction.getValueParameters().iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                i3 = -1;
                                break;
                            } else if (Intrinsics.areEqual(it2.next().getSymbol(), resolvedValueParameterSymbol$default)) {
                                break;
                            } else {
                                i3++;
                            }
                        }
                        if (i3 >= 0 && i <= i3) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) qualifiedAccessExpression.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNINITIALIZED_PARAMETER(), (Object) resolvedValueParameterSymbol$default, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                        }
                    }
                });
            }
            i = i2;
        }
    }

    private final void checkValOrVarParameter(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        if ((firFunction instanceof FirConstructor) && ((FirConstructor) firFunction).getIsPrimary()) {
            return;
        }
        Iterator<FirValueParameter> it = firFunction.getValueParameters().iterator();
        while (it.hasNext()) {
            checkValOrVar$org_jetbrains_kotlin_checkers(diagnosticReporter, checkerContext, it.next());
        }
    }

    private final void checkVarargParameters(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        ConeKotlinType coneKotlinTypeFullyExpandedType;
        List<FirValueParameter> valueParameters = firFunction.getValueParameters();
        ArrayList<FirValueParameter> arrayList = new ArrayList();
        for (Object obj : valueParameters) {
            if (((FirValueParameter) obj).getIsVararg()) {
                arrayList.add(obj);
            }
        }
        if (arrayList.size() > 1) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirValueParameter) it.next()).getSource(), FirErrors.INSTANCE.getMULTIPLE_VARARG_PARAMETERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        for (FirValueParameter firValueParameter : arrayList) {
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef());
            if (!(firFunction instanceof FirAnonymousFunction)) {
                coneType = FirTypeUtilsKt.arrayElementType$default(coneType, false, 1, null);
            }
            if (coneType != null && (coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneType)) != null && (ConeBuiltinTypeUtilsKt.isNothingOrNullableNothing(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirHelpersKt.leastUpperBound(coneKotlinTypeFullyExpandedType, checkerContext.getSession()))) || (FirHelpersKt.isValueClass(coneKotlinTypeFullyExpandedType, checkerContext.getSession()) && !ConeBuiltinTypeUtilsKt.isUnsignedTypeOrNullableUnsignedType(coneKotlinTypeFullyExpandedType)))) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getFORBIDDEN_VARARG_PARAMETER_TYPE(), (Object) coneKotlinTypeFullyExpandedType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunction.getClass();
        checkVarargParameters(checkerContext, diagnosticReporter, firFunction);
        checkParameterTypes(checkerContext, diagnosticReporter, firFunction);
        checkUninitializedParameter(checkerContext, diagnosticReporter, firFunction);
        checkValOrVarParameter(checkerContext, diagnosticReporter, firFunction);
        checkParameterNameChangedOnOverride(checkerContext, diagnosticReporter, firFunction);
    }

    public final void checkValOrVar$org_jetbrains_kotlin_checkers(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirValueParameter firValueParameter) {
        KtKeywordToken valOrVarKeyword;
        diagnosticReporter.getClass();
        checkerContext.getClass();
        firValueParameter.getClass();
        KtSourceElement source = firValueParameter.getSource();
        if (((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) || (valOrVarKeyword = FirKeywordUtilsKt.getValOrVarKeyword(source)) == null) {
            return;
        }
        if (firValueParameter.getContainingDeclarationSymbol() instanceof FirConstructorSymbol) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getVAL_OR_VAR_ON_SECONDARY_CONSTRUCTOR_PARAMETER(), (Object) valOrVarKeyword, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getVAL_OR_VAR_ON_FUN_PARAMETER(), (Object) valOrVarKeyword, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }
}

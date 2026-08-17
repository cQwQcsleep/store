package org.jetbrains.kotlin.fir.analysis.js.checkers.expression;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsHelpersKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirComponentCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\u00020\u0007*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J-\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011J%\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00022\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u000b0\u001dH\u0082\bJ-\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020!H\u0002R\u00020\u000eR\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000ej\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\"R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0016\u001a\u00020\u0007*\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0018\u0010\u0019\u001a\u00020\u0007*\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0018¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/expression/FirJsDynamicCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "isArrayAccessWithMultipleIndices", "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isInOperator", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)Z", "isRangeOperator", "checkSpreadOperator", "forAllSpreadArgumentsOf", "callback", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "checkIdentifier", "namedReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;)V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsDynamicCallChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirJsDynamicCallChecker INSTANCE = new FirJsDynamicCallChecker();

    private FirJsDynamicCallChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkIdentifier(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirResolvedNamedReference firResolvedNamedReference) {
        String identifierOrNullIfSpecial;
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.JsAllowInvalidCharsIdentifiersEscaping) || (identifierOrNullIfSpecial = firResolvedNamedReference.getName().getIdentifierOrNullIfSpecial()) == null || Intrinsics.areEqual(FirJsHelpersKt.sanitizeName(identifierOrNullIfSpecial), identifierOrNullIfSpecial)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedNamedReference.getSource(), FirJsErrors.INSTANCE.getNAME_CONTAINS_ILLEGAL_CHARS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void checkSpreadOperator(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        FirCall firCall = firQualifiedAccessExpression instanceof FirCall ? (FirCall) firQualifiedAccessExpression : null;
        if (firCall == null) {
            return;
        }
        for (FirExpression firExpression : firCall.getArgumentList().getArguments()) {
            if (firExpression instanceof FirVarargArgumentsExpression) {
                for (FirExpression firExpression2 : ((FirVarargArgumentsExpression) firExpression).getArguments()) {
                    if (firExpression2 instanceof FirSpreadArgumentExpression) {
                        if (FirTypeUtilsKt.getResolvedType(firExpression2) instanceof ConeDynamicType) {
                            checkerContext2 = checkerContext;
                            diagnosticReporter2 = diagnosticReporter;
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firExpression2.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "spread operator", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                        } else {
                            checkerContext2 = checkerContext;
                            diagnosticReporter2 = diagnosticReporter;
                        }
                        checkerContext = checkerContext2;
                        diagnosticReporter = diagnosticReporter2;
                    }
                }
            }
        }
    }

    private final boolean isArrayAccessWithMultipleIndices(FirCall firCall, FirSession firSession) {
        List<FirExpression> arguments;
        FirReference reference = ReferenceUtilsKt.toReference(firCall, firSession);
        FirNamedReference firNamedReference = reference instanceof FirNamedReference ? (FirNamedReference) reference : null;
        if (firNamedReference == null) {
            return false;
        }
        KtSourceElement source = firNamedReference.getSource();
        if (!Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ArrayAccessNameReference.INSTANCE)) {
            return false;
        }
        Object objSingleOrNull = CollectionsKt.singleOrNull(firCall.getArgumentList().getArguments());
        FirVarargArgumentsExpression firVarargArgumentsExpression = objSingleOrNull instanceof FirVarargArgumentsExpression ? (FirVarargArgumentsExpression) objSingleOrNull : null;
        if (firVarargArgumentsExpression != null && (arguments = firVarargArgumentsExpression.getArguments()) != null) {
            if (Intrinsics.areEqual(firNamedReference.getName(), OperatorNameConventions.GET) && arguments.size() >= 2) {
                return true;
            }
            if (Intrinsics.areEqual(firNamedReference.getName(), OperatorNameConventions.SET) && arguments.size() >= 3) {
                return true;
            }
        }
        return false;
    }

    private final boolean isInOperator(FirFunctionCall firFunctionCall) {
        FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(firFunctionCall.getCalleeReference());
        return Intrinsics.areEqual(resolved != null ? resolved.getName() : null, OperatorNameConventions.CONTAINS) && firFunctionCall.getOrigin() == FirFunctionCallOrigin.Operator;
    }

    private final boolean isRangeOperator(FirFunctionCall firFunctionCall) {
        FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(firFunctionCall.getCalleeReference());
        Name name = resolved != null ? resolved.getName() : null;
        return (Intrinsics.areEqual(name, OperatorNameConventions.RANGE_TO) || Intrinsics.areEqual(name, OperatorNameConventions.RANGE_UNTIL)) && firFunctionCall.getOrigin() == FirFunctionCallOrigin.Operator;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x005c  */
    /* JADX WARN: Code duplicated, block: B:18:0x0060  */
    /* JADX WARN: Code duplicated, block: B:20:0x0069  */
    /* JADX WARN: Code duplicated, block: B:22:0x0087 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:23:0x0089  */
    /* JADX WARN: Code duplicated, block: B:25:0x0092  */
    /* JADX WARN: Code duplicated, block: B:26:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:28:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:29:0x00ec  */
    /* JADX WARN: Instruction removed from duplicated block: B:25:0x0092, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        boolean z;
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        FirFunctionCall firFunctionCall;
        FirFunctionCall firFunctionCall2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(firQualifiedAccessExpression.getCalleeReference());
        if (resolved == null) {
            return;
        }
        if (!(resolved.getResolvedSymbol().getOrigin() instanceof FirDeclarationOrigin.DynamicScope)) {
            checkSpreadOperator(checkerContext, diagnosticReporter, firQualifiedAccessExpression);
            return;
        }
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(resolved, false, 1, null);
        if (resolvedCallableSymbol$default == null) {
            k2d.a("Resolved call callee without a callable symbol");
            return;
        }
        boolean z2 = firQualifiedAccessExpression instanceof FirCall;
        if (z2) {
            FirCall firCall = (FirCall) firQualifiedAccessExpression;
            if (isArrayAccessWithMultipleIndices(firCall, checkerContext.getSession())) {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firCall.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "indexed access with more than one index", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            } else {
                z = firQualifiedAccessExpression instanceof FirFunctionCall;
                if (z) {
                    firFunctionCall2 = (FirFunctionCall) firQualifiedAccessExpression;
                    if (isInOperator(firFunctionCall2)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall2.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "`in` operation", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else if (z) {
                        firFunctionCall = (FirFunctionCall) firQualifiedAccessExpression;
                        if (isRangeOperator(firFunctionCall)) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) ("`" + ((String) FirJsDynamicCallCheckerKt.nameToOperator.get(resolvedCallableSymbol$default.getName())) + "` operation"), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                        } else if (firQualifiedAccessExpression instanceof FirComponentCall) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirComponentCall) firQualifiedAccessExpression).getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "`destructuring declaration", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                        } else {
                            checkerContext2 = checkerContext;
                            diagnosticReporter2 = diagnosticReporter;
                            checkIdentifier(diagnosticReporter2, checkerContext2, resolved);
                        }
                    } else if (firQualifiedAccessExpression instanceof FirComponentCall) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirComponentCall) firQualifiedAccessExpression).getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "`destructuring declaration", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else {
                        checkerContext2 = checkerContext;
                        diagnosticReporter2 = diagnosticReporter;
                        checkIdentifier(diagnosticReporter2, checkerContext2, resolved);
                    }
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                } else if (z) {
                    firFunctionCall = (FirFunctionCall) firQualifiedAccessExpression;
                    if (isRangeOperator(firFunctionCall)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) ("`" + ((String) FirJsDynamicCallCheckerKt.nameToOperator.get(resolvedCallableSymbol$default.getName())) + "` operation"), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else if (firQualifiedAccessExpression instanceof FirComponentCall) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirComponentCall) firQualifiedAccessExpression).getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "`destructuring declaration", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else {
                        checkerContext2 = checkerContext;
                        diagnosticReporter2 = diagnosticReporter;
                        checkIdentifier(diagnosticReporter2, checkerContext2, resolved);
                    }
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                } else if (firQualifiedAccessExpression instanceof FirComponentCall) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirComponentCall) firQualifiedAccessExpression).getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "`destructuring declaration", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                } else {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                    checkIdentifier(diagnosticReporter2, checkerContext2, resolved);
                }
            }
        } else {
            z = firQualifiedAccessExpression instanceof FirFunctionCall;
            if (z) {
                firFunctionCall2 = (FirFunctionCall) firQualifiedAccessExpression;
                if (isInOperator(firFunctionCall2)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall2.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "`in` operation", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                } else if (z) {
                    firFunctionCall = (FirFunctionCall) firQualifiedAccessExpression;
                    if (isRangeOperator(firFunctionCall)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) ("`" + ((String) FirJsDynamicCallCheckerKt.nameToOperator.get(resolvedCallableSymbol$default.getName())) + "` operation"), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else if (firQualifiedAccessExpression instanceof FirComponentCall) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirComponentCall) firQualifiedAccessExpression).getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "`destructuring declaration", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else {
                        checkerContext2 = checkerContext;
                        diagnosticReporter2 = diagnosticReporter;
                        checkIdentifier(diagnosticReporter2, checkerContext2, resolved);
                    }
                } else if (firQualifiedAccessExpression instanceof FirComponentCall) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirComponentCall) firQualifiedAccessExpression).getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "`destructuring declaration", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                } else {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                    checkIdentifier(diagnosticReporter2, checkerContext2, resolved);
                }
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            } else if (z) {
                firFunctionCall = (FirFunctionCall) firQualifiedAccessExpression;
                if (isRangeOperator(firFunctionCall)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) ("`" + ((String) FirJsDynamicCallCheckerKt.nameToOperator.get(resolvedCallableSymbol$default.getName())) + "` operation"), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                } else if (firQualifiedAccessExpression instanceof FirComponentCall) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirComponentCall) firQualifiedAccessExpression).getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "`destructuring declaration", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                } else {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                    checkIdentifier(diagnosticReporter2, checkerContext2, resolved);
                }
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            } else if (firQualifiedAccessExpression instanceof FirComponentCall) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirComponentCall) firQualifiedAccessExpression).getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getWRONG_OPERATION_WITH_DYNAMIC(), (Object) "`destructuring declaration", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                checkIdentifier(diagnosticReporter2, checkerContext2, resolved);
            }
        }
        FirCall firCall2 = z2 ? (FirCall) firQualifiedAccessExpression : null;
        if (firCall2 == null) {
            return;
        }
        for (FirExpression firExpression : firCall2.getArgumentList().getArguments()) {
            if (firExpression instanceof FirVarargArgumentsExpression) {
                for (FirExpression firExpression2 : ((FirVarargArgumentsExpression) firExpression).getArguments()) {
                    if (firExpression2 instanceof FirSpreadArgumentExpression) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firExpression2.getSource(), FirJsErrors.INSTANCE.getSPREAD_OPERATOR_IN_DYNAMIC_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                }
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}

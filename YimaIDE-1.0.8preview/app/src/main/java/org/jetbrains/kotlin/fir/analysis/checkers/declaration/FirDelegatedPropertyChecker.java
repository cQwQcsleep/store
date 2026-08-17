package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDelegatedPropertyChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeConstraintSystemHasContradiction;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableWrongReceiver;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicabilityKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDelegatedPropertyChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegatedPropertyChecker extends FirDeclarationChecker<FirProperty> {
    public static final FirDelegatedPropertyChecker INSTANCE = new FirDelegatedPropertyChecker();

    @Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"org/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDelegatedPropertyChecker$check$DelegatedPropertyAccessorVisitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitorVoid;", "isGet", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Z)V", "visitElement", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "visitFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "visitImplicitInvokeCall", "implicitInvokeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirImplicitInvokeCall;", "checkFunctionCall", "checkFunctionReferenceErrors", "checkReturnType", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DelegatedPropertyAccessorVisitor extends FirVisitorVoid {
        final /* synthetic */ CheckerContext $context;
        final /* synthetic */ FirProperty $declaration;
        final /* synthetic */ ConeKotlinType $delegateType;
        final /* synthetic */ DiagnosticReporter $reporter;
        final /* synthetic */ KtSourceElement $source;
        private final boolean isGet;

        public DelegatedPropertyAccessorVisitor(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, FirProperty firProperty, boolean z) {
            this.$context = checkerContext;
            this.$reporter = diagnosticReporter;
            this.$source = ktSourceElement;
            this.$delegateType = coneKotlinType;
            this.$declaration = firProperty;
            this.isGet = z;
        }

        public static CharSequence b(FirExpression firExpression) {
            firExpression.getClass();
            return ConeTypeUtilsKt.renderReadable(FirTypeUtilsKt.getResolvedType(firExpression));
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        private final void checkFunctionCall(FirFunctionCall functionCall) throws KotlinIllegalArgumentExceptionWithAttachments {
            boolean zCheckFunctionReferenceErrors = checkFunctionReferenceErrors(functionCall);
            if (!this.isGet || zCheckFunctionReferenceErrors) {
                return;
            }
            checkReturnType(functionCall);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final boolean checkFunctionReferenceErrors(FirFunctionCall functionCall) {
            FirNamedReference calleeReference = functionCall.getCalleeReference();
            if (FirReferenceUtilsKt.isError(calleeReference)) {
                FirDiagnosticHolder firDiagnosticHolder = (FirDiagnosticHolder) calleeReference;
                ConeDiagnostic diagnostic = firDiagnosticHolder.getDiagnostic();
                KtSourceElement source = firDiagnosticHolder.getSource();
                if (!Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE)) {
                    return false;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(this.isGet ? "getValue" : "setValue");
                sb.append('(');
                sb.append(CollectionsKt.joinToString$default(functionCall.getArgumentList().getArguments(), ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: u25
                    public final Object invoke(Object obj) {
                        return FirDelegatedPropertyChecker.DelegatedPropertyAccessorVisitor.b((FirExpression) obj);
                    }
                }, 30, (Object) null));
                sb.append(')');
                String string = sb.toString();
                String str = this.isGet ? "delegate" : "delegate for var (read-write property)";
                if (diagnostic instanceof ConeUnresolvedNameError) {
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) this.$context, this.$reporter, (AbstractKtSourceElement) this.$source, (KtDiagnosticFactory3<String, ConeKotlinType, String>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getDELEGATE_SPECIAL_FUNCTION_MISSING()), string, this.$delegateType, str, (64 & 64) != 0 ? null : null);
                    return true;
                }
                if (diagnostic instanceof ConeAmbiguityError) {
                    ConeAmbiguityError coneAmbiguityError = (ConeAmbiguityError) diagnostic;
                    boolean zIsSuccess = CandidateApplicabilityKt.isSuccess(coneAmbiguityError.getApplicability());
                    CheckerContext checkerContext = this.$context;
                    if (!zIsSuccess) {
                        DiagnosticReporter diagnosticReporter = this.$reporter;
                        KtSourceElement ktSourceElement = this.$source;
                        Collection<AbstractCandidate> candidates = coneAmbiguityError.getCandidates();
                        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(candidates, 10));
                        Iterator<T> it = candidates.iterator();
                        while (it.hasNext()) {
                            arrayList.add(((AbstractCandidate) it.next()).getSymbol());
                        }
                        checkFunctionReferenceErrors$reportInapplicableDiagnostics(checkerContext, diagnosticReporter, ktSourceElement, string, arrayList);
                        return true;
                    }
                    DiagnosticReporter diagnosticReporter2 = this.$reporter;
                    KtSourceElement ktSourceElement2 = this.$source;
                    KtDiagnosticFactory2<String, Collection<FirBasedSymbol<?>>> delegate_special_function_ambiguity = FirErrors.INSTANCE.getDELEGATE_SPECIAL_FUNCTION_AMBIGUITY();
                    Collection<AbstractCandidate> candidates2 = coneAmbiguityError.getCandidates();
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(candidates2, 10));
                    Iterator<T> it2 = candidates2.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(((AbstractCandidate) it2.next()).getSymbol());
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter2, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory2) delegate_special_function_ambiguity, (Object) string, (Object) arrayList2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    return true;
                }
                if (diagnostic instanceof ConeInapplicableWrongReceiver) {
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) this.$context, this.$reporter, (AbstractKtSourceElement) this.$source, (KtDiagnosticFactory3<String, ConeKotlinType, String>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getDELEGATE_SPECIAL_FUNCTION_MISSING()), string, this.$delegateType, str, (64 & 64) != 0 ? null : null);
                    return true;
                }
                if (diagnostic instanceof ConeInapplicableCandidateError) {
                    checkFunctionReferenceErrors$reportInapplicableDiagnostics(this.$context, this.$reporter, this.$source, string, CollectionsKt.listOf(((ConeInapplicableCandidateError) diagnostic).getCandidate().getSymbol()));
                    return true;
                }
                if (diagnostic instanceof ConeConstraintSystemHasContradiction) {
                    checkFunctionReferenceErrors$reportInapplicableDiagnostics(this.$context, this.$reporter, this.$source, string, CollectionsKt.listOf(((ConeConstraintSystemHasContradiction) diagnostic).getCandidate().getSymbol()));
                    return true;
                }
            }
            return false;
        }

        private static final void checkFunctionReferenceErrors$reportInapplicableDiagnostics(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, String str, Collection<? extends FirBasedSymbol<?>> collection) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getDELEGATE_SPECIAL_FUNCTION_NONE_APPLICABLE(), (Object) str, (Object) collection, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        private final void checkReturnType(FirFunctionCall functionCall) throws KotlinIllegalArgumentExceptionWithAttachments {
            ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(functionCall);
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(this.$declaration.getReturnTypeRef());
            if (AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(this.$context.getSession()), resolvedType, coneType, false, 8, (Object) null)) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) this.$context, this.$reporter, (AbstractKtSourceElement) this.$source, (KtDiagnosticFactory3<String, ConeKotlinType, ConeKotlinType>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getDELEGATE_SPECIAL_FUNCTION_RETURN_TYPE_MISMATCH()), "getValue", coneType, resolvedType, (64 & 64) != 0 ? null : null);
        }

        public void visitElement(FirElement element) {
            element.getClass();
            element.acceptChildren(this);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public void visitFunctionCall(FirFunctionCall functionCall) throws KotlinIllegalArgumentExceptionWithAttachments {
            functionCall.getClass();
            checkFunctionCall(functionCall);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public void visitImplicitInvokeCall(FirImplicitInvokeCall implicitInvokeCall) throws KotlinIllegalArgumentExceptionWithAttachments {
            implicitInvokeCall.getClass();
            checkFunctionCall(implicitInvokeCall);
        }
    }

    private FirDelegatedPropertyChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirProperty firProperty2;
        FirBlock body;
        FirBlock body2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        FirExpression delegate = firProperty.getDelegate();
        if (delegate == null) {
            return;
        }
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(delegate);
        KtSourceElement source = delegate.getSource();
        if (resolvedType instanceof ConeErrorType) {
            if (source == null || !FirHelpersKt.hasDiagnosticKind(resolvedType, DiagnosticKind.RecursionInImplicitTypes)) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getRECURSION_IN_IMPLICIT_TYPES(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        FirPropertyAccessor getter = firProperty.getGetter();
        if (getter == null || (body2 = getter.getBody()) == null) {
            firProperty2 = firProperty;
        } else {
            firProperty2 = firProperty;
            body2.acceptChildren(new DelegatedPropertyAccessorVisitor(checkerContext, diagnosticReporter, source, resolvedType, firProperty2, true));
        }
        FirPropertyAccessor setter = firProperty2.getSetter();
        if (setter == null || (body = setter.getBody()) == null) {
            return;
        }
        body.acceptChildren(new DelegatedPropertyAccessorVisitor(checkerContext, diagnosticReporter, source, resolvedType, firProperty2, false));
    }
}

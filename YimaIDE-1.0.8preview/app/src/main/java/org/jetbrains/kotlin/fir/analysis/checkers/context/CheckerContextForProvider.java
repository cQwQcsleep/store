package org.jetbrains.kotlin.fir.analysis.checkers.context;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirInlineBodyResolvableExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.extra.FirAnonymousUnusedParamChecker;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ.\u0010\u0014\u001a\u00020\u00002\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&J\u0010\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001cH&J\u0010\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001fH&J\b\u0010 \u001a\u00020\u001cH&J\u0010\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#H&J\b\u0010$\u001a\u00020\u001cH&J\u0010\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'H&J\b\u0010(\u001a\u00020\u001cH&J\b\u0010)\u001a\u00020\u0000H&J\b\u0010*\u001a\u00020\u0000H&J\u0012\u0010+\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u00010-H&J\u0012\u0010.\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u00010/H&J\u0012\u00100\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u000101H&J\u0010\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u000204H&J\u0010\u00105\u001a\u00020\u00002\u0006\u00103\u001a\u000204H&J\u0010\u00106\u001a\u00020\u00002\u0006\u00107\u001a\u000208H&J\b\u00109\u001a\u00020\u001cH&R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0014\u0010\t\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011¨\u0006:"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContextForProvider;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "sessionHolder", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "allInfosSuppressed", Argument.Delimiters.none, "allWarningsSuppressed", "allErrorsSuppressed", "<init>", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;ZZZ)V", "getSessionHolder", "()Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "getReturnTypeCalculator", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "getAllInfosSuppressed", "()Z", "getAllWarningsSuppressed", "getAllErrorsSuppressed", "addSuppressedDiagnostics", "diagnosticNames", Argument.Delimiters.none, Argument.Delimiters.none, "addDeclaration", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "dropDeclaration", Argument.Delimiters.none, "addCallOrAssignment", "qualifiedAccessOrAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "dropCallOrAssignment", "addGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "dropGetClassCall", "addAnnotationContainer", "annotationContainer", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "dropAnnotationContainer", "enterContractBody", "exitContractBody", "setInlineFunctionBodyContext", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;", "setInlinableParameterContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;", "setLambdaBodyContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;", "enterFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "exitFile", "addElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "dropElement", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CheckerContextForProvider extends CheckerContext {
    private final boolean allErrorsSuppressed;
    private final boolean allInfosSuppressed;
    private final boolean allWarningsSuppressed;
    private final ReturnTypeCalculator returnTypeCalculator;
    private final SessionAndScopeSessionHolder sessionHolder;

    public CheckerContextForProvider(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, ReturnTypeCalculator returnTypeCalculator, boolean z, boolean z2, boolean z3) {
        sessionAndScopeSessionHolder.getClass();
        returnTypeCalculator.getClass();
        this.sessionHolder = sessionAndScopeSessionHolder;
        this.returnTypeCalculator = returnTypeCalculator;
        this.allInfosSuppressed = z;
        this.allWarningsSuppressed = z2;
        this.allErrorsSuppressed = z3;
    }

    public abstract CheckerContextForProvider addAnnotationContainer(FirAnnotationContainer annotationContainer);

    public abstract CheckerContextForProvider addCallOrAssignment(FirStatement qualifiedAccessOrAnnotationCall);

    public abstract CheckerContextForProvider addDeclaration(FirDeclaration declaration);

    public abstract CheckerContextForProvider addElement(FirElement element);

    public abstract CheckerContextForProvider addGetClassCall(FirGetClassCall getClassCall);

    public abstract CheckerContextForProvider addSuppressedDiagnostics(Collection<String> diagnosticNames, boolean allInfosSuppressed, boolean allWarningsSuppressed, boolean allErrorsSuppressed);

    public abstract void dropAnnotationContainer();

    public abstract void dropCallOrAssignment();

    public abstract void dropDeclaration();

    public abstract void dropElement();

    public abstract void dropGetClassCall();

    public abstract CheckerContextForProvider enterContractBody();

    public abstract CheckerContextForProvider enterFile(FirFile file);

    public abstract CheckerContextForProvider exitContractBody();

    public abstract CheckerContextForProvider exitFile(FirFile file);

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public boolean getAllErrorsSuppressed() {
        return this.allErrorsSuppressed;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public boolean getAllInfosSuppressed() {
        return this.allInfosSuppressed;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public boolean getAllWarningsSuppressed() {
        return this.allWarningsSuppressed;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public ReturnTypeCalculator getReturnTypeCalculator() {
        return this.returnTypeCalculator;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public SessionAndScopeSessionHolder getSessionHolder() {
        return this.sessionHolder;
    }

    public abstract CheckerContextForProvider setInlinableParameterContext(FirInlineBodyResolvableExpressionChecker.InlinableParameterContext context);

    public abstract CheckerContextForProvider setInlineFunctionBodyContext(FirInlineDeclarationChecker.InlineFunctionBodyContext context);

    public abstract CheckerContextForProvider setLambdaBodyContext(FirAnonymousUnusedParamChecker.LambdaBodyContext context);
}

package org.jetbrains.kotlin.fir.analysis.checkers.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentSet;
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
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B¹\u0001\b\u0002\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0006\u0010\u001e\u001a\u00020\u000e\u0012\u0006\u0010\u001f\u001a\u00020\u000e\u0012\u0006\u0010 \u001a\u00020\u000e¢\u0006\u0004\b!\u0010\"B\u0019\b\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0004\b!\u0010#J\u0010\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020AH\u0016J\b\u0010B\u001a\u00020CH\u0016J\u0010\u0010D\u001a\u00020\u00002\u0006\u0010E\u001a\u00020\u0006H\u0016J\b\u0010F\u001a\u00020CH\u0016J\u0010\u0010G\u001a\u00020\u00002\u0006\u0010H\u001a\u00020\bH\u0016J\b\u0010I\u001a\u00020CH\u0016J\u0010\u0010J\u001a\u00020\u00012\u0006\u0010K\u001a\u00020\nH\u0016J\b\u0010L\u001a\u00020CH\u0016J\u0010\u0010M\u001a\u00020\u00012\u0006\u0010N\u001a\u00020\fH\u0016J\b\u0010O\u001a\u00020CH\u0016J.\u0010P\u001a\u00020\u00012\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u001d0R2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000eH\u0016J\b\u0010S\u001a\u00020\u0001H\u0016J\b\u0010T\u001a\u00020\u0001H\u0016J\u0012\u0010/\u001a\u00020\u00012\b\u0010U\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u00103\u001a\u00020\u00012\b\u0010U\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u00107\u001a\u00020\u00012\b\u0010U\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010V\u001a\u00020\u00012\u0006\u0010W\u001a\u00020XH\u0016J\u0010\u0010Y\u001a\u00020\u00012\u0006\u0010W\u001a\u00020XH\u0016R\u001e\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010%R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010%R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u001a\u0010\r\u001a\u00020\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010*\"\u0004\b+\u0010,R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>¨\u0006Z"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/context/MutableCheckerContext;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContextForProvider;", "containingDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "callsOrAssignments", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "getClassCalls", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "annotationContainers", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "containingElements", "Lorg/jetbrains/kotlin/fir/FirElement;", "isContractBody", Argument.Delimiters.none, "inlineFunctionBodyContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;", "inlinableParameterContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;", "lambdaBodyContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;", "containingFileSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "sessionHolder", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "suppressedDiagnostics", "Lkotlinx/collections/immutable/PersistentSet;", Argument.Delimiters.none, "allInfosSuppressed", "allWarningsSuppressed", "allErrorsSuppressed", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;Lkotlinx/collections/immutable/PersistentSet;ZZZ)V", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;)V", "getContainingDeclarations", "()Ljava/util/List;", "getCallsOrAssignments", "getGetClassCalls", "getAnnotationContainers", "getContainingElements", "()Z", "setContractBody", "(Z)V", "getInlineFunctionBodyContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;", "setInlineFunctionBodyContext", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;)V", "getInlinableParameterContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;", "setInlinableParameterContext", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;)V", "getLambdaBodyContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;", "setLambdaBodyContext", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;)V", "getContainingFileSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "setContainingFileSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;)V", "getSuppressedDiagnostics", "()Lkotlinx/collections/immutable/PersistentSet;", "addDeclaration", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "dropDeclaration", Argument.Delimiters.none, "addCallOrAssignment", "qualifiedAccessOrAnnotationCall", "dropCallOrAssignment", "addGetClassCall", "getClassCall", "dropGetClassCall", "addAnnotationContainer", "annotationContainer", "dropAnnotationContainer", "addElement", "element", "dropElement", "addSuppressedDiagnostics", "diagnosticNames", Argument.Delimiters.none, "enterContractBody", "exitContractBody", "context", "enterFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "exitFile", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MutableCheckerContext extends CheckerContextForProvider {
    private final List<FirAnnotationContainer> annotationContainers;
    private final List<FirStatement> callsOrAssignments;
    private final List<FirBasedSymbol<?>> containingDeclarations;
    private final List<FirElement> containingElements;
    private FirFileSymbol containingFileSymbol;
    private final List<FirGetClassCall> getClassCalls;
    private FirInlineBodyResolvableExpressionChecker.InlinableParameterContext inlinableParameterContext;
    private FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext;
    private boolean isContractBody;
    private FirAnonymousUnusedParamChecker.LambdaBodyContext lambdaBodyContext;
    private final PersistentSet<String> suppressedDiagnostics;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MutableCheckerContext(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, ReturnTypeCalculator returnTypeCalculator) {
        this(new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList(), false, null, null, null, null, sessionAndScopeSessionHolder, returnTypeCalculator, ExtensionsKt.persistentSetOf(), false, false, false);
        sessionAndScopeSessionHolder.getClass();
        returnTypeCalculator.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider addAnnotationContainer(FirAnnotationContainer annotationContainer) {
        annotationContainer.getClass();
        getAnnotationContainers().add(annotationContainer);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public MutableCheckerContext addCallOrAssignment(FirStatement qualifiedAccessOrAnnotationCall) {
        qualifiedAccessOrAnnotationCall.getClass();
        getCallsOrAssignments().add(qualifiedAccessOrAnnotationCall);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public MutableCheckerContext addDeclaration(FirDeclaration declaration) {
        declaration.getClass();
        getContainingDeclarations().add(declaration.getSymbol());
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider addElement(FirElement element) {
        element.getClass();
        CollectionsKt.lastOrNull(getContainingElements());
        getContainingElements().add(element);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public MutableCheckerContext addGetClassCall(FirGetClassCall getClassCall) {
        getClassCall.getClass();
        getGetClassCalls().add(getClassCall);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider addSuppressedDiagnostics(Collection<String> diagnosticNames, boolean allInfosSuppressed, boolean allWarningsSuppressed, boolean allErrorsSuppressed) {
        diagnosticNames.getClass();
        if (diagnosticNames.isEmpty()) {
            return this;
        }
        List<FirBasedSymbol<?>> containingDeclarations = getContainingDeclarations();
        List<FirStatement> callsOrAssignments = getCallsOrAssignments();
        List<FirGetClassCall> getClassCalls = getGetClassCalls();
        List<FirAnnotationContainer> annotationContainers = getAnnotationContainers();
        List<FirElement> containingElements = getContainingElements();
        boolean isContractBody = getIsContractBody();
        FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext = getInlineFunctionBodyContext();
        FirInlineBodyResolvableExpressionChecker.InlinableParameterContext inlinableParameterContext = getInlinableParameterContext();
        FirAnonymousUnusedParamChecker.LambdaBodyContext lambdaBodyContext = getLambdaBodyContext();
        FirFileSymbol containingFileSymbol = getContainingFileSymbol();
        SessionAndScopeSessionHolder sessionHolder = getSessionHolder();
        ReturnTypeCalculator returnTypeCalculator = getReturnTypeCalculator();
        PersistentSet persistentSetAddAll = getSuppressedDiagnostics().addAll(diagnosticNames);
        boolean z = true;
        boolean z2 = false;
        if (!getAllInfosSuppressed() && !allInfosSuppressed) {
            z = false;
        }
        if (getAllWarningsSuppressed() || allWarningsSuppressed) {
            z2 = z;
        }
        return new MutableCheckerContext(containingDeclarations, callsOrAssignments, getClassCalls, annotationContainers, containingElements, isContractBody, inlineFunctionBodyContext, inlinableParameterContext, lambdaBodyContext, containingFileSymbol, sessionHolder, returnTypeCalculator, persistentSetAddAll, z, z2, getAllErrorsSuppressed() || allErrorsSuppressed);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public void dropAnnotationContainer() {
        CollectionsKt.removeLast(getAnnotationContainers());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public void dropCallOrAssignment() {
        CollectionsKt.removeLast(getCallsOrAssignments());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public void dropDeclaration() {
        CollectionsKt.removeLast(getContainingDeclarations());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public void dropElement() {
        CollectionsKt.removeLast(getContainingElements());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public void dropGetClassCall() {
        CollectionsKt.removeLast(getGetClassCalls());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider enterContractBody() {
        if (getIsContractBody()) {
            k2d.a("Check failed.");
            return null;
        }
        setContractBody(true);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider enterFile(FirFile file) {
        file.getClass();
        setContainingFileSymbol(file.getSymbol());
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider exitContractBody() {
        if (getIsContractBody()) {
            setContractBody(false);
            return this;
        }
        k2d.a("Check failed.");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider exitFile(FirFile file) {
        file.getClass();
        setContainingFileSymbol(file.getSymbol());
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public List<FirAnnotationContainer> getAnnotationContainers() {
        return this.annotationContainers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public List<FirStatement> getCallsOrAssignments() {
        return this.callsOrAssignments;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public List<FirBasedSymbol<?>> getContainingDeclarations() {
        return this.containingDeclarations;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public List<FirElement> getContainingElements() {
        return this.containingElements;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public FirFileSymbol getContainingFileSymbol() {
        return this.containingFileSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public List<FirGetClassCall> getGetClassCalls() {
        return this.getClassCalls;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public FirInlineBodyResolvableExpressionChecker.InlinableParameterContext getInlinableParameterContext() {
        return this.inlinableParameterContext;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public FirInlineDeclarationChecker.InlineFunctionBodyContext getInlineFunctionBodyContext() {
        return this.inlineFunctionBodyContext;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public FirAnonymousUnusedParamChecker.LambdaBodyContext getLambdaBodyContext() {
        return this.lambdaBodyContext;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    /* JADX INFO: renamed from: isContractBody, reason: from getter */
    public boolean getIsContractBody() {
        return this.isContractBody;
    }

    public void setContainingFileSymbol(FirFileSymbol firFileSymbol) {
        this.containingFileSymbol = firFileSymbol;
    }

    public void setContractBody(boolean z) {
        this.isContractBody = z;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider setInlinableParameterContext(FirInlineBodyResolvableExpressionChecker.InlinableParameterContext context) {
        m215setInlinableParameterContext(context);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider setInlineFunctionBodyContext(FirInlineDeclarationChecker.InlineFunctionBodyContext context) {
        m216setInlineFunctionBodyContext(context);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider setLambdaBodyContext(FirAnonymousUnusedParamChecker.LambdaBodyContext context) {
        m217setLambdaBodyContext(context);
        return this;
    }

    /* JADX INFO: renamed from: setInlinableParameterContext, reason: collision with other method in class */
    public void m215setInlinableParameterContext(FirInlineBodyResolvableExpressionChecker.InlinableParameterContext inlinableParameterContext) {
        this.inlinableParameterContext = inlinableParameterContext;
    }

    /* JADX INFO: renamed from: setInlineFunctionBodyContext, reason: collision with other method in class */
    public void m216setInlineFunctionBodyContext(FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext) {
        this.inlineFunctionBodyContext = inlineFunctionBodyContext;
    }

    /* JADX INFO: renamed from: setLambdaBodyContext, reason: collision with other method in class */
    public void m217setLambdaBodyContext(FirAnonymousUnusedParamChecker.LambdaBodyContext lambdaBodyContext) {
        this.lambdaBodyContext = lambdaBodyContext;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public PersistentSet<String> getSuppressedDiagnostics() {
        return this.suppressedDiagnostics;
    }

    private MutableCheckerContext(List<FirBasedSymbol<?>> list, List<FirStatement> list2, List<FirGetClassCall> list3, List<FirAnnotationContainer> list4, List<FirElement> list5, boolean z, FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext, FirInlineBodyResolvableExpressionChecker.InlinableParameterContext inlinableParameterContext, FirAnonymousUnusedParamChecker.LambdaBodyContext lambdaBodyContext, FirFileSymbol firFileSymbol, SessionAndScopeSessionHolder sessionAndScopeSessionHolder, ReturnTypeCalculator returnTypeCalculator, PersistentSet<String> persistentSet, boolean z2, boolean z3, boolean z4) {
        super(sessionAndScopeSessionHolder, returnTypeCalculator, z2, z3, z4);
        this.containingDeclarations = list;
        this.callsOrAssignments = list2;
        this.getClassCalls = list3;
        this.annotationContainers = list4;
        this.containingElements = list5;
        this.isContractBody = z;
        this.inlineFunctionBodyContext = inlineFunctionBodyContext;
        this.inlinableParameterContext = inlinableParameterContext;
        this.lambdaBodyContext = lambdaBodyContext;
        this.containingFileSymbol = firFileSymbol;
        this.suppressedDiagnostics = persistentSet;
    }
}

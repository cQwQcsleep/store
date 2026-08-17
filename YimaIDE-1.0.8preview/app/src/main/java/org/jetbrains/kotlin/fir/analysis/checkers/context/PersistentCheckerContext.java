package org.jetbrains.kotlin.fir.analysis.checkers.context;

import java.util.Collection;
import kotlin.Metadata;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
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
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u001e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B¹\u0001\b\u0002\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0010\u001c\u001a\u00020\u000e\u0012\u0006\u0010\u001d\u001a\u00020\u000e\u0012\u0006\u0010\u001e\u001a\u00020\u000e\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b!\u0010\"B\u0019\b\u0016\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b!\u0010#J\u0010\u00105\u001a\u00020\u00002\u0006\u00106\u001a\u000207H\u0016J\b\u00108\u001a\u000209H\u0016J\u0010\u0010:\u001a\u00020\u00002\u0006\u0010;\u001a\u00020\u0006H\u0016J\b\u0010<\u001a\u000209H\u0016J\u0010\u0010=\u001a\u00020\u00002\u0006\u0010>\u001a\u00020\bH\u0016J\b\u0010?\u001a\u000209H\u0016J\u0010\u0010@\u001a\u00020\u00002\u0006\u0010A\u001a\u00020\nH\u0016J\b\u0010B\u001a\u000209H\u0016J\u0010\u0010C\u001a\u00020\u00002\u0006\u0010D\u001a\u00020\fH\u0016J\b\u0010E\u001a\u000209H\u0016J.\u0010F\u001a\u00020\u00012\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001b0H2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000eH\u0016JÄ\u0001\u0010I\u001a\u00020\u00002\u000e\b\u0002\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0012\b\u0002\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u001c\u001a\u00020\u000e2\b\b\u0002\u0010\u001d\u001a\u00020\u000e2\b\b\u0002\u0010\u001e\u001a\u00020\u000e2\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 H\u0002J\u0010\u0010K\u001a\u00020\u00012\u0006\u0010L\u001a\u00020\u000eH\u0002J\b\u0010M\u001a\u00020\u0001H\u0016J\b\u0010N\u001a\u00020\u0001H\u0016J\u0012\u0010O\u001a\u00020\u00002\b\u0010P\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u0010Q\u001a\u00020\u00012\b\u0010P\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010R\u001a\u00020\u00012\b\u0010P\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010S\u001a\u00020\u00012\u0006\u0010T\u001a\u00020UH\u0016J\u0010\u0010V\u001a\u00020\u00012\u0006\u0010T\u001a\u00020UH\u0016R\u001e\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010%R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010%R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010*R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0016\u0010\u001f\u001a\u0004\u0018\u00010 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00104¨\u0006W"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/context/PersistentCheckerContext;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContextForProvider;", "containingDeclarations", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "callsOrAssignments", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "getClassCalls", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "annotationContainers", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "containingElements", "Lorg/jetbrains/kotlin/fir/FirElement;", "isContractBody", Argument.Delimiters.none, "inlineFunctionBodyContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;", "inlinableParameterContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;", "lambdaBodyContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;", "sessionHolder", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "suppressedDiagnostics", "Lkotlinx/collections/immutable/PersistentSet;", Argument.Delimiters.none, "allInfosSuppressed", "allWarningsSuppressed", "allErrorsSuppressed", "containingFileSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "<init>", "(Lkotlinx/collections/immutable/PersistentList;Lkotlinx/collections/immutable/PersistentList;Lkotlinx/collections/immutable/PersistentList;Lkotlinx/collections/immutable/PersistentList;Lkotlinx/collections/immutable/PersistentList;ZLorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;Lkotlinx/collections/immutable/PersistentSet;ZZZLorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;)V", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;)V", "getContainingDeclarations", "()Lkotlinx/collections/immutable/PersistentList;", "getCallsOrAssignments", "getGetClassCalls", "getAnnotationContainers", "getContainingElements", "()Z", "getInlineFunctionBodyContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;", "getInlinableParameterContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;", "getLambdaBodyContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;", "getSuppressedDiagnostics", "()Lkotlinx/collections/immutable/PersistentSet;", "getContainingFileSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "addDeclaration", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "dropDeclaration", Argument.Delimiters.none, "addCallOrAssignment", "qualifiedAccessOrAnnotationCall", "dropCallOrAssignment", "addGetClassCall", "getClassCall", "dropGetClassCall", "addAnnotationContainer", "annotationContainer", "dropAnnotationContainer", "addElement", "element", "dropElement", "addSuppressedDiagnostics", "diagnosticNames", Argument.Delimiters.none, "copy", "qualifiedAccessOrAssignmentsOrAnnotationCalls", "toggleContractBody", "newValue", "enterContractBody", "exitContractBody", "setInlineFunctionBodyContext", "context", "setInlinableParameterContext", "setLambdaBodyContext", "enterFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "exitFile", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PersistentCheckerContext extends CheckerContextForProvider {
    private final PersistentList<FirAnnotationContainer> annotationContainers;
    private final PersistentList<FirStatement> callsOrAssignments;
    private final PersistentList<FirBasedSymbol<?>> containingDeclarations;
    private final PersistentList<FirElement> containingElements;
    private final FirFileSymbol containingFileSymbol;
    private final PersistentList<FirGetClassCall> getClassCalls;
    private final FirInlineBodyResolvableExpressionChecker.InlinableParameterContext inlinableParameterContext;
    private final FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext;
    private final boolean isContractBody;
    private final FirAnonymousUnusedParamChecker.LambdaBodyContext lambdaBodyContext;
    private final PersistentSet<String> suppressedDiagnostics;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PersistentCheckerContext(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, ReturnTypeCalculator returnTypeCalculator) {
        this(ExtensionsKt.persistentListOf(), ExtensionsKt.persistentListOf(), ExtensionsKt.persistentListOf(), ExtensionsKt.persistentListOf(), ExtensionsKt.persistentListOf(), false, null, null, null, sessionAndScopeSessionHolder, returnTypeCalculator, ExtensionsKt.persistentSetOf(), false, false, false, null);
        sessionAndScopeSessionHolder.getClass();
        returnTypeCalculator.getClass();
    }

    private final PersistentCheckerContext copy(PersistentList<? extends FirStatement> qualifiedAccessOrAssignmentsOrAnnotationCalls, PersistentList<? extends FirGetClassCall> getClassCalls, PersistentList<? extends FirAnnotationContainer> annotationContainers, PersistentList<? extends FirElement> containingElements, PersistentList<? extends FirBasedSymbol<?>> containingDeclarations, boolean isContractBody, FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext, FirInlineBodyResolvableExpressionChecker.InlinableParameterContext inlinableParameterContext, FirAnonymousUnusedParamChecker.LambdaBodyContext lambdaBodyContext, boolean allInfosSuppressed, boolean allWarningsSuppressed, boolean allErrorsSuppressed, PersistentSet<String> suppressedDiagnostics, FirFileSymbol containingFileSymbol) {
        return new PersistentCheckerContext(containingDeclarations, qualifiedAccessOrAssignmentsOrAnnotationCalls, getClassCalls, annotationContainers, containingElements, isContractBody, inlineFunctionBodyContext, inlinableParameterContext, lambdaBodyContext, getSessionHolder(), getReturnTypeCalculator(), suppressedDiagnostics, allInfosSuppressed, allWarningsSuppressed, allErrorsSuppressed, containingFileSymbol);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PersistentCheckerContext copy$default(PersistentCheckerContext persistentCheckerContext, PersistentList persistentList, PersistentList persistentList2, PersistentList persistentList3, PersistentList persistentList4, PersistentList persistentList5, boolean z, FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext, FirInlineBodyResolvableExpressionChecker.InlinableParameterContext inlinableParameterContext, FirAnonymousUnusedParamChecker.LambdaBodyContext lambdaBodyContext, boolean z2, boolean z3, boolean z4, PersistentSet persistentSet, FirFileSymbol firFileSymbol, int i, Object obj) {
        if ((i & 1) != 0) {
            persistentList = persistentCheckerContext.getCallsOrAssignments();
        }
        return persistentCheckerContext.copy(persistentList, (i & 2) != 0 ? persistentCheckerContext.getGetClassCalls() : persistentList2, (i & 4) != 0 ? persistentCheckerContext.getAnnotationContainers() : persistentList3, (i & 8) != 0 ? persistentCheckerContext.getContainingElements() : persistentList4, (i & 16) != 0 ? persistentCheckerContext.getContainingDeclarations() : persistentList5, (i & 32) != 0 ? persistentCheckerContext.getIsContractBody() : z, (i & 64) != 0 ? persistentCheckerContext.getInlineFunctionBodyContext() : inlineFunctionBodyContext, (i & 128) != 0 ? persistentCheckerContext.getInlinableParameterContext() : inlinableParameterContext, (i & 256) != 0 ? persistentCheckerContext.getLambdaBodyContext() : lambdaBodyContext, (i & 512) != 0 ? persistentCheckerContext.getAllInfosSuppressed() : z2, (i & 1024) != 0 ? persistentCheckerContext.getAllWarningsSuppressed() : z3, (i & 2048) != 0 ? persistentCheckerContext.getAllErrorsSuppressed() : z4, (i & 4096) != 0 ? persistentCheckerContext.getSuppressedDiagnostics() : persistentSet, (i & 8192) != 0 ? persistentCheckerContext.getContainingFileSymbol() : firFileSymbol);
    }

    private final CheckerContextForProvider toggleContractBody(boolean newValue) {
        if (getIsContractBody() != newValue) {
            return copy$default(this, null, null, null, null, null, newValue, null, null, null, false, false, false, null, null, 16351, null);
        }
        k2d.a("Check failed.");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public PersistentCheckerContext addAnnotationContainer(FirAnnotationContainer annotationContainer) {
        annotationContainer.getClass();
        return copy$default(this, null, null, getAnnotationContainers().add(annotationContainer), null, null, false, null, null, null, false, false, false, null, null, 16379, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public PersistentCheckerContext addCallOrAssignment(FirStatement qualifiedAccessOrAnnotationCall) {
        qualifiedAccessOrAnnotationCall.getClass();
        return copy$default(this, getCallsOrAssignments().add(qualifiedAccessOrAnnotationCall), null, null, null, null, false, null, null, null, false, false, false, null, null, 16382, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public PersistentCheckerContext addDeclaration(FirDeclaration declaration) {
        declaration.getClass();
        return copy$default(this, null, null, null, null, getContainingDeclarations().add(declaration.getSymbol()), false, null, null, null, false, false, false, null, null, 16367, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public PersistentCheckerContext addElement(FirElement element) {
        element.getClass();
        return copy$default(this, null, null, null, getContainingElements().add(element), null, false, null, null, null, false, false, false, null, null, 16375, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public PersistentCheckerContext addGetClassCall(FirGetClassCall getClassCall) {
        getClassCall.getClass();
        return copy$default(this, null, getGetClassCalls().add(getClassCall), null, null, null, false, null, null, null, false, false, false, null, null, 16381, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider addSuppressedDiagnostics(Collection<String> diagnosticNames, boolean allInfosSuppressed, boolean allWarningsSuppressed, boolean allErrorsSuppressed) {
        diagnosticNames.getClass();
        if (diagnosticNames.isEmpty()) {
            return this;
        }
        return copy$default(this, null, null, null, null, null, false, null, null, null, getAllInfosSuppressed() || allInfosSuppressed, getAllWarningsSuppressed() || allWarningsSuppressed, getAllErrorsSuppressed() || allErrorsSuppressed, getSuppressedDiagnostics().addAll(diagnosticNames), null, 8703, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public void dropAnnotationContainer() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public void dropCallOrAssignment() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public void dropDeclaration() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public void dropElement() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public void dropGetClassCall() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider enterContractBody() {
        return toggleContractBody(true);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider enterFile(FirFile file) {
        file.getClass();
        return copy$default(this, null, null, null, null, null, false, null, null, null, false, false, false, null, file.getSymbol(), 8191, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider exitContractBody() {
        return toggleContractBody(false);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider exitFile(FirFile file) {
        file.getClass();
        return copy$default(this, null, null, null, null, null, false, null, null, null, false, false, false, null, null, 8191, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public FirFileSymbol getContainingFileSymbol() {
        return this.containingFileSymbol;
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

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider setInlinableParameterContext(FirInlineBodyResolvableExpressionChecker.InlinableParameterContext context) {
        return copy$default(this, null, null, null, null, null, false, null, context, null, false, false, false, null, null, 16255, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public PersistentCheckerContext setInlineFunctionBodyContext(FirInlineDeclarationChecker.InlineFunctionBodyContext context) {
        return copy$default(this, null, null, null, null, null, false, context, null, null, false, false, false, null, null, 16319, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider
    public CheckerContextForProvider setLambdaBodyContext(FirAnonymousUnusedParamChecker.LambdaBodyContext context) {
        return copy$default(this, null, null, null, null, null, false, null, null, context, false, false, false, null, null, 16127, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public PersistentList<FirAnnotationContainer> getAnnotationContainers() {
        return this.annotationContainers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public PersistentList<FirStatement> getCallsOrAssignments() {
        return this.callsOrAssignments;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public PersistentList<FirBasedSymbol<?>> getContainingDeclarations() {
        return this.containingDeclarations;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public PersistentList<FirElement> getContainingElements() {
        return this.containingElements;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public PersistentList<FirGetClassCall> getGetClassCalls() {
        return this.getClassCalls;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
    public PersistentSet<String> getSuppressedDiagnostics() {
        return this.suppressedDiagnostics;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private PersistentCheckerContext(PersistentList<? extends FirBasedSymbol<?>> persistentList, PersistentList<? extends FirStatement> persistentList2, PersistentList<? extends FirGetClassCall> persistentList3, PersistentList<? extends FirAnnotationContainer> persistentList4, PersistentList<? extends FirElement> persistentList5, boolean z, FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext, FirInlineBodyResolvableExpressionChecker.InlinableParameterContext inlinableParameterContext, FirAnonymousUnusedParamChecker.LambdaBodyContext lambdaBodyContext, SessionAndScopeSessionHolder sessionAndScopeSessionHolder, ReturnTypeCalculator returnTypeCalculator, PersistentSet<String> persistentSet, boolean z2, boolean z3, boolean z4, FirFileSymbol firFileSymbol) {
        super(sessionAndScopeSessionHolder, returnTypeCalculator, z2, z3, z4);
        this.containingDeclarations = persistentList;
        this.callsOrAssignments = persistentList2;
        this.getClassCalls = persistentList3;
        this.annotationContainers = persistentList4;
        this.containingElements = persistentList5;
        this.isContractBody = z;
        this.inlineFunctionBodyContext = inlineFunctionBodyContext;
        this.inlinableParameterContext = inlinableParameterContext;
        this.lambdaBodyContext = lambdaBodyContext;
        this.suppressedDiagnostics = persistentSet;
        this.containingFileSymbol = firFileSymbol;
    }
}

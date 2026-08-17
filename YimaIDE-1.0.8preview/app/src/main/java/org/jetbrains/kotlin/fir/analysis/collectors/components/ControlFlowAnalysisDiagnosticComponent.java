package org.jetbrains.kotlin.fir.analysis.collectors.components;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentKt;
import org.jetbrains.kotlin.fir.analysis.cfa.AbstractFirPropertyInitializationChecker;
import org.jetbrains.kotlin.fir.analysis.cfa.util.PropertyInitializationInfoData;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.cfa.FirControlFlowChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u00014B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\b\u0010\fJ\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\u0018\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\u0018\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\u0018\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\u0018\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\u0018\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020-2\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\u0018\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u0002002\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\u0018\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u0002032\u0006\u0010\u001b\u001a\u00020\u0017H\u0016R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/components/ControlFlowAnalysisDiagnosticComponent;", "Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "declarationCheckers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;)V", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "cfaCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/cfa/FirControlFlowChecker;", "variableAssignmentCheckers", "Lorg/jetbrains/kotlin/fir/analysis/cfa/AbstractFirPropertyInitializationChecker;", "analyze", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirControlFlowGraphOwner;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "visitFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "data", "visitScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visitAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "visitProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "visitFunction", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "visitNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "visitPropertyAccessor", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "visitConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "LocalPropertyCollector", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ControlFlowAnalysisDiagnosticComponent extends AbstractDiagnosticCollectorComponent {
    private final Set<FirControlFlowChecker> cfaCheckers;
    private final Set<AbstractFirPropertyInitializationChecker> variableAssignmentCheckers;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MppCheckerKind.values().length];
            try {
                iArr[MppCheckerKind.Common.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MppCheckerKind.Platform.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ControlFlowAnalysisDiagnosticComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter, MppCheckerKind mppCheckerKind) {
        DeclarationCheckers commonDeclarationCheckers;
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
        mppCheckerKind.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[mppCheckerKind.ordinal()];
        if (i == 1) {
            commonDeclarationCheckers = CheckersComponentKt.getCheckersComponent(firSession).getCommonDeclarationCheckers();
        } else {
            if (i != 2) {
                bu8.a();
                throw null;
            }
            commonDeclarationCheckers = CheckersComponentKt.getCheckersComponent(firSession).getPlatformDeclarationCheckers();
        }
        this(firSession, pendingDiagnosticReporter, commonDeclarationCheckers);
    }

    private final void analyze(FirControlFlowGraphOwner declaration, CheckerContext context) {
        ControlFlowGraph controlFlowGraph;
        PendingDiagnosticReporter reporter = getReporter();
        FirControlFlowGraphReference controlFlowGraphReference = declaration.getControlFlowGraphReference();
        if (controlFlowGraphReference == null || (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference)) == null || controlFlowGraph.isSubGraph()) {
            return;
        }
        Iterator<T> it = this.cfaCheckers.iterator();
        while (it.hasNext()) {
            ((FirControlFlowChecker) it.next()).analyze(reporter, context, controlFlowGraph);
        }
        LocalPropertyCollector localPropertyCollector = new LocalPropertyCollector();
        declaration.acceptChildren(localPropertyCollector, CollectionsKt.toSet(controlFlowGraph.getSubGraphs()));
        Set<FirPropertySymbol> properties = localPropertyCollector.getProperties();
        if (properties.isEmpty()) {
            return;
        }
        PropertyInitializationInfoData propertyInitializationInfoData = new PropertyInitializationInfoData(properties, localPropertyCollector.getConditionallyInitializedProperties(), null, controlFlowGraph);
        Iterator<T> it2 = this.variableAssignmentCheckers.iterator();
        while (it2.hasNext()) {
            ((AbstractFirPropertyInitializationChecker) it2.next()).analyze(reporter, context, propertyInitializationInfoData);
        }
    }

    /* JADX INFO: renamed from: visitAnonymousObject, reason: avoid collision after fix types in other method */
    public void visitAnonymousObject2(FirAnonymousObject anonymousObject, CheckerContext data) {
        anonymousObject.getClass();
        data.getClass();
        analyze(anonymousObject, data);
    }

    /* JADX INFO: renamed from: visitConstructor, reason: avoid collision after fix types in other method */
    public void visitConstructor2(FirConstructor constructor, CheckerContext data) {
        constructor.getClass();
        data.getClass();
        analyze(constructor, data);
    }

    /* JADX INFO: renamed from: visitFile, reason: avoid collision after fix types in other method */
    public void visitFile2(FirFile file, CheckerContext data) {
        file.getClass();
        data.getClass();
        analyze(file, data);
    }

    /* JADX INFO: renamed from: visitFunction, reason: avoid collision after fix types in other method */
    public void visitFunction2(FirFunction function, CheckerContext data) {
        function.getClass();
        data.getClass();
        analyze(function, data);
    }

    /* JADX INFO: renamed from: visitNamedFunction, reason: avoid collision after fix types in other method */
    public void visitNamedFunction2(FirNamedFunction namedFunction, CheckerContext data) {
        namedFunction.getClass();
        data.getClass();
        analyze(namedFunction, data);
    }

    /* JADX INFO: renamed from: visitProperty, reason: avoid collision after fix types in other method */
    public void visitProperty2(FirProperty property, CheckerContext data) {
        property.getClass();
        data.getClass();
        analyze(property, data);
    }

    /* JADX INFO: renamed from: visitPropertyAccessor, reason: avoid collision after fix types in other method */
    public void visitPropertyAccessor2(FirPropertyAccessor propertyAccessor, CheckerContext data) {
        propertyAccessor.getClass();
        data.getClass();
        analyze(propertyAccessor, data);
    }

    /* JADX INFO: renamed from: visitRegularClass, reason: avoid collision after fix types in other method */
    public void visitRegularClass2(FirRegularClass regularClass, CheckerContext data) {
        regularClass.getClass();
        data.getClass();
        analyze(regularClass, data);
    }

    /* JADX INFO: renamed from: visitScript, reason: avoid collision after fix types in other method */
    public void visitScript2(FirScript script, CheckerContext data) {
        script.getClass();
        data.getClass();
        analyze(script, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitAnonymousObject(FirAnonymousObject firAnonymousObject, CheckerContext checkerContext) {
        visitAnonymousObject2(firAnonymousObject, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitConstructor(FirConstructor firConstructor, CheckerContext checkerContext) {
        visitConstructor2(firConstructor, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitFile(FirFile firFile, CheckerContext checkerContext) {
        visitFile2(firFile, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitFunction(FirFunction firFunction, CheckerContext checkerContext) {
        visitFunction2(firFunction, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitNamedFunction(FirNamedFunction firNamedFunction, CheckerContext checkerContext) {
        visitNamedFunction2(firNamedFunction, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitProperty(FirProperty firProperty, CheckerContext checkerContext) {
        visitProperty2(firProperty, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitPropertyAccessor(FirPropertyAccessor firPropertyAccessor, CheckerContext checkerContext) {
        visitPropertyAccessor2(firPropertyAccessor, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitRegularClass(FirRegularClass firRegularClass, CheckerContext checkerContext) {
        visitRegularClass2(firRegularClass, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitScript(FirScript firScript, CheckerContext checkerContext) {
        visitScript2(firScript, checkerContext);
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u001e\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u001e\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u001e\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016J\u001e\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR&\u0010\u000e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/components/ControlFlowAnalysisDiagnosticComponent$LocalPropertyCollector;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "<init>", "()V", "properties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getProperties", "()Ljava/util/Set;", "conditionallyInitializedProperties", "getConditionallyInitializedProperties", "doWhileLoopProperties", "Lkotlin/collections/ArrayDeque;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "insideDoWhileConditions", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "visitProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "visitQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "visitDoWhileLoop", "doWhileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class LocalPropertyCollector extends FirDefaultVisitor<Unit, Set<? extends ControlFlowGraph>> {
        private final Set<FirPropertySymbol> properties = new LinkedHashSet();
        private final Set<FirPropertySymbol> conditionallyInitializedProperties = new LinkedHashSet();
        private final ArrayDeque<Pair<FirLoop, Set<FirPropertySymbol>>> doWhileLoopProperties = new ArrayDeque<>();
        private final Set<FirLoop> insideDoWhileConditions = new LinkedHashSet();

        public final Set<FirPropertySymbol> getConditionallyInitializedProperties() {
            return this.conditionallyInitializedProperties;
        }

        public final Set<FirPropertySymbol> getProperties() {
            return this.properties;
        }

        public void visitDoWhileLoop(FirDoWhileLoop doWhileLoop, Set<ControlFlowGraph> data) {
            doWhileLoop.getClass();
            data.getClass();
            this.doWhileLoopProperties.addLast(TuplesKt.to(doWhileLoop, new LinkedHashSet()));
            doWhileLoop.getBlock().accept(this, data);
            this.insideDoWhileConditions.add(doWhileLoop);
            doWhileLoop.getCondition().accept(this, data);
            this.insideDoWhileConditions.remove(doWhileLoop);
            this.doWhileLoopProperties.removeLast();
        }

        public void visitElement(FirElement element, Set<ControlFlowGraph> data) {
            element.getClass();
            data.getClass();
            if (!(element instanceof FirControlFlowGraphOwner)) {
                element.acceptChildren(this, data);
                return;
            }
            FirControlFlowGraphReference controlFlowGraphReference = ((FirControlFlowGraphOwner) element).getControlFlowGraphReference();
            ControlFlowGraph controlFlowGraph = controlFlowGraphReference != null ? FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference) : null;
            if (controlFlowGraph == null) {
                element.acceptChildren(this, data);
            } else if (data.contains(controlFlowGraph)) {
                element.acceptChildren(this, CollectionsKt.toSet(controlFlowGraph.getSubGraphs()));
            }
        }

        public void visitProperty(FirProperty property, Set<ControlFlowGraph> data) {
            Set set;
            property.getClass();
            data.getClass();
            if ((property.getSymbol() instanceof FirRegularPropertySymbol) || Intrinsics.areEqual(property.getOrigin(), FirDeclarationOrigin.ScriptCustomization.Parameter.INSTANCE) || Intrinsics.areEqual(property.getOrigin(), FirDeclarationOrigin.ScriptCustomization.ParameterFromBaseClass.INSTANCE)) {
                visitElement((FirElement) property, data);
                return;
            }
            FirPropertySymbol symbol = property.getSymbol();
            this.properties.add(symbol);
            Pair pair = (Pair) this.doWhileLoopProperties.lastOrNull();
            if (pair != null && (set = (Set) pair.getSecond()) != null) {
                set.add(symbol);
            }
            visitElement((FirElement) property, data);
        }

        public void visitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, Set<ControlFlowGraph> data) {
            qualifiedAccessExpression.getClass();
            data.getClass();
            if (!this.insideDoWhileConditions.isEmpty()) {
                FirPropertySymbol resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(qualifiedAccessExpression.getCalleeReference(), false, 1, null);
                if (resolvedPropertySymbol$default != null) {
                    ArrayDeque<Pair<FirLoop, Set<FirPropertySymbol>>> arrayDeque = this.doWhileLoopProperties;
                    if (arrayDeque == null || !arrayDeque.isEmpty()) {
                        Iterator it = arrayDeque.iterator();
                        while (it.hasNext()) {
                            Pair pair = (Pair) it.next();
                            if (this.insideDoWhileConditions.contains(pair.getFirst()) && ((Set) pair.getSecond()).contains(resolvedPropertySymbol$default)) {
                                this.conditionallyInitializedProperties.add(resolvedPropertySymbol$default);
                                break;
                            }
                        }
                    }
                } else {
                    return;
                }
            }
            visitElement((FirElement) qualifiedAccessExpression, data);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitDoWhileLoop(FirDoWhileLoop firDoWhileLoop, Object obj) {
            visitDoWhileLoop(firDoWhileLoop, (Set<ControlFlowGraph>) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitElement(FirElement firElement, Object obj) {
            visitElement(firElement, (Set<ControlFlowGraph>) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitProperty(FirProperty firProperty, Object obj) {
            visitProperty(firProperty, (Set<ControlFlowGraph>) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitQualifiedAccessExpression(FirQualifiedAccessExpression firQualifiedAccessExpression, Object obj) {
            visitQualifiedAccessExpression(firQualifiedAccessExpression, (Set<ControlFlowGraph>) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlFlowAnalysisDiagnosticComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter, DeclarationCheckers declarationCheckers) {
        super(firSession, pendingDiagnosticReporter);
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
        declarationCheckers.getClass();
        this.cfaCheckers = declarationCheckers.getControlFlowAnalyserCheckers();
        this.variableAssignmentCheckers = declarationCheckers.getVariableAssignmentCfaBasedCheckers();
    }
}

package com.sun.tools.javac.comp;

import com.sun.source.tree.LambdaExpressionTree;
import com.sun.source.tree.MemberReferenceTree;
import com.sun.source.tree.NewClassTree;
import com.sun.tools.javac.code.DeferredCompletionFailureHandler;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.DeferredAttr;
import com.sun.tools.javac.comp.Infer.GraphSolver;
import com.sun.tools.javac.comp.Infer.GraphSolver.InferenceGraph;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeCopier;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.GraphUtils;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Warner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredAttr extends JCTree.Visitor {
    protected static final Context.Key<DeferredAttr> deferredAttrKey = new Context.Key<>();
    final Annotate annotate;
    final ArgumentAttr argumentAttr;
    final Attr attr;
    final Check chk;
    final DeferredCompletionFailureHandler dcfh;
    final Types.TypeMapping<Void> deferredCopier;
    final JCDiagnostic.Factory diags;
    DeferredStuckPolicy dummyStuckPolicy = new DeferredStuckPolicy() { // from class: com.sun.tools.javac.comp.DeferredAttr.4
        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredStuckPolicy
        public Set<Type> depVars() {
            return Collections.EMPTY_SET;
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredStuckPolicy
        public boolean isStuck() {
            return false;
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredStuckPolicy
        public Set<Type> stuckVars() {
            return Collections.EMPTY_SET;
        }
    };
    final DeferredAttrContext emptyDeferredAttrContext;
    final Enter enter;
    final Flow flow;
    final Infer infer;
    final Log log;
    final TreeMaker make;
    final Names names;
    final Resolve rs;
    final JCTree stuckTree;
    final Symtab syms;
    final TreeCopier<Void> treeCopier;
    final TypeEnvs typeEnvs;
    final Types types;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.DeferredAttr$5, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Kinds$Kind;

        static {
            int[] iArr = new int[Kinds.Kind.values().length];
            $SwitchMap$com$sun$tools$javac$code$Kinds$Kind = iArr;
            try {
                iArr[Kinds.Kind.WRONG_MTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.WRONG_MTHS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.ABSENT_MTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.STATICERR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum AttrMode {
        SPECULATIVE,
        CHECK
    }

    public enum AttributionMode {
        FULL(false, true),
        ATTRIB_TO_TREE(true, true),
        ANALYZER(true, false),
        SPECULATIVE(true, false);

        final boolean isSpeculative;
        final boolean recover;

        AttributionMode(boolean z, boolean z2) {
            this.isSpeculative = z;
            this.recover = z2;
        }

        public boolean isSpeculative() {
            return this.isSpeculative;
        }

        public boolean recover() {
            return this.recover;
        }
    }

    public class CheckStuckPolicy extends PolyScanner implements DeferredStuckPolicy, Infer.FreeTypeListener {
        InferenceContext inferenceContext;
        Type pt;
        Set<Type> stuckVars = new LinkedHashSet();
        Set<Type> depVars = new LinkedHashSet();

        public CheckStuckPolicy(Attr.ResultInfo resultInfo, DeferredType deferredType) {
            this.pt = resultInfo.pt;
            this.inferenceContext = resultInfo.checkContext.inferenceContext();
            scan(deferredType.tree);
            if (this.stuckVars.isEmpty()) {
                return;
            }
            resultInfo.checkContext.inferenceContext().addFreeTypeListener(List.from(this.stuckVars), this);
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredStuckPolicy
        public Set<Type> depVars() {
            return this.depVars;
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredStuckPolicy
        public boolean isStuck() {
            return !this.stuckVars.isEmpty();
        }

        public void scanLambdaBody(JCTree.JCLambda jCLambda, final Type type) {
            if (jCLambda.getBodyKind() != LambdaExpressionTree.BodyKind.EXPRESSION) {
                new LambdaReturnScanner(this) { // from class: com.sun.tools.javac.comp.DeferredAttr.CheckStuckPolicy.1
                    final /* synthetic */ CheckStuckPolicy this$1;

                    {
                        this.this$1 = this;
                    }

                    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                    public void visitReturn(JCTree.JCReturn jCReturn) {
                        JCTree.JCExpression jCExpression = jCReturn.expr;
                        if (jCExpression != null) {
                            CheckStuckPolicy checkStuckPolicy = this.this$1;
                            Type type2 = checkStuckPolicy.pt;
                            try {
                                checkStuckPolicy.pt = type;
                                checkStuckPolicy.scan(jCExpression);
                            } finally {
                                this.this$1.pt = type2;
                            }
                        }
                    }
                }.scan(jCLambda.body);
                return;
            }
            Type type2 = this.pt;
            try {
                this.pt = type;
                scan(jCLambda.body);
            } finally {
                this.pt = type2;
            }
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredStuckPolicy
        public Set<Type> stuckVars() {
            return this.stuckVars;
        }

        @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
        public void typesInferred(InferenceContext inferenceContext) {
            this.stuckVars.clear();
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            if (this.inferenceContext.inferenceVars().contains(this.pt)) {
                this.stuckVars.add(this.pt);
            }
            if (DeferredAttr.this.types.isFunctionalInterface(this.pt)) {
                Type typeFindDescriptorType = DeferredAttr.this.types.findDescriptorType(this.pt);
                List<Type> listFreeVarsIn = this.inferenceContext.freeVarsIn(typeFindDescriptorType.mo71getParameterTypes());
                if (jCLambda.paramKind == JCTree.JCLambda.ParameterKind.IMPLICIT && listFreeVarsIn.nonEmpty()) {
                    this.stuckVars.addAll(listFreeVarsIn);
                    this.depVars.addAll(this.inferenceContext.freeVarsIn(typeFindDescriptorType.mo73getReturnType()));
                    this.depVars.addAll(this.inferenceContext.freeVarsIn(typeFindDescriptorType.mo74getThrownTypes()));
                }
                scanLambdaBody(jCLambda, typeFindDescriptorType.mo73getReturnType());
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitReference(JCTree.JCMemberReference jCMemberReference) {
            scan(jCMemberReference.expr);
            if (this.inferenceContext.inferenceVars().contains(this.pt)) {
                this.stuckVars.add(this.pt);
                return;
            }
            if (DeferredAttr.this.types.isFunctionalInterface(this.pt)) {
                Type typeFindDescriptorType = DeferredAttr.this.types.findDescriptorType(this.pt);
                List<Type> listFreeVarsIn = this.inferenceContext.freeVarsIn(typeFindDescriptorType.mo71getParameterTypes());
                if (!listFreeVarsIn.nonEmpty() || jCMemberReference.getOverloadKind() == JCTree.JCMemberReference.OverloadKind.UNOVERLOADED) {
                    return;
                }
                this.stuckVars.addAll(listFreeVarsIn);
                this.depVars.addAll(this.inferenceContext.freeVarsIn(typeFindDescriptorType.mo73getReturnType()));
                this.depVars.addAll(this.inferenceContext.freeVarsIn(typeFindDescriptorType.mo74getThrownTypes()));
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
            new SwitchExpressionScanner() { // from class: com.sun.tools.javac.comp.DeferredAttr.CheckStuckPolicy.2
                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitYield(JCTree.JCYield jCYield) {
                    CheckStuckPolicy checkStuckPolicy = CheckStuckPolicy.this;
                    Type type = checkStuckPolicy.pt;
                    try {
                        checkStuckPolicy.pt = type;
                        checkStuckPolicy.scan(jCYield.value);
                    } finally {
                        CheckStuckPolicy.this.pt = type;
                    }
                }
            }.scan(jCSwitchExpression.cases);
        }
    }

    public class DeferredAttrContext {
        ArrayList<DeferredAttrNode> deferredAttrNodes = new ArrayList<>();
        final InferenceContext inferenceContext;
        final AttrMode mode;
        final Symbol msym;
        final DeferredAttrContext parent;
        final Resolve.MethodResolutionPhase phase;
        final Warner warn;

        public class StuckNode extends GraphUtils.TarjanNode<DeferredAttrNode, StuckNode> {
            Set<StuckNode> deps;

            public StuckNode(DeferredAttrNode deferredAttrNode) {
                super(deferredAttrNode);
                this.deps = new HashSet();
            }

            @Override // com.sun.tools.javac.util.GraphUtils.TarjanNode
            public Iterable<? extends StuckNode> getAllDependencies() {
                return this.deps;
            }

            @Override // com.sun.tools.javac.util.GraphUtils.AbstractNode
            public Collection<? extends StuckNode> getDependenciesByKind(GraphUtils.DependencyKind dependencyKind) {
                if (dependencyKind == Infer.DependencyKind.STUCK) {
                    return this.deps;
                }
                g33.a();
                return null;
            }

            @Override // com.sun.tools.javac.util.GraphUtils.AbstractNode
            public GraphUtils.DependencyKind[] getSupportedDependencyKinds() {
                return new GraphUtils.DependencyKind[]{Infer.DependencyKind.STUCK};
            }
        }

        public DeferredAttrContext(AttrMode attrMode, Symbol symbol, Resolve.MethodResolutionPhase methodResolutionPhase, InferenceContext inferenceContext, DeferredAttrContext deferredAttrContext, Warner warner) {
            this.mode = attrMode;
            this.msym = symbol;
            this.phase = methodResolutionPhase;
            this.parent = deferredAttrContext;
            this.warn = warner;
            this.inferenceContext = inferenceContext;
        }

        public static /* synthetic */ StuckNode a(DeferredAttrContext deferredAttrContext, DeferredAttrNode deferredAttrNode) {
            deferredAttrContext.getClass();
            return deferredAttrContext.new StuckNode(deferredAttrNode);
        }

        public void addDeferredAttrNode(DeferredType deferredType, Attr.ResultInfo resultInfo, DeferredStuckPolicy deferredStuckPolicy) {
            this.deferredAttrNodes.add(DeferredAttr.this.new DeferredAttrNode(deferredType, resultInfo, deferredStuckPolicy));
        }

        public List<StuckNode> buildStuckGraph() {
            DeferredAttr.this.infer.doIncorporation(this.inferenceContext, this.warn);
            Infer infer = DeferredAttr.this.infer;
            Objects.requireNonNull(infer);
            Infer.GraphSolver.InferenceGraph inferenceGraph = infer.new GraphSolver(this.inferenceContext, DeferredAttr.this.types.noWarnings).new InferenceGraph();
            List<StuckNode> list = (List) this.deferredAttrNodes.stream().map(new Function() { // from class: com.sun.tools.javac.comp.d0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return DeferredAttr.DeferredAttrContext.a(this.b, (DeferredAttr.DeferredAttrNode) obj);
                }
            }).collect(List.collector());
            for (StuckNode stuckNode : list) {
                for (StuckNode stuckNode2 : list) {
                    if (stuckNode != stuckNode2 && canInfluence(inferenceGraph, stuckNode2, stuckNode)) {
                        stuckNode.deps.add(stuckNode2);
                    }
                }
            }
            return list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean canInfluence(final Infer.GraphSolver.InferenceGraph inferenceGraph, StuckNode stuckNode, StuckNode stuckNode2) {
            Set<Type> setDepVars = ((DeferredAttrNode) stuckNode.data).deferredStuckPolicy.depVars();
            Iterator<Type> it = ((DeferredAttrNode) stuckNode2.data).deferredStuckPolicy.stuckVars().iterator();
            while (it.hasNext()) {
                Infer.GraphSolver.InferenceGraph.Node nodeFindNode = inferenceGraph.findNode(it.next());
                if (nodeFindNode != null) {
                    final Set<Infer.GraphSolver.InferenceGraph.Node> setClosure = nodeFindNode.closure();
                    Stream<R> map = setDepVars.stream().map(new Function() { // from class: com.sun.tools.javac.comp.e0
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return inferenceGraph.findNode((Type) obj);
                        }
                    });
                    Objects.requireNonNull(setClosure);
                    if (map.anyMatch(new Predicate() { // from class: com.sun.tools.javac.comp.f0
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return setClosure.contains((Infer.GraphSolver.InferenceGraph.Node) obj);
                        }
                    })) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void complete() {
            while (!this.deferredAttrNodes.isEmpty()) {
                boolean z = false;
                for (DeferredAttrNode deferredAttrNode : List.from(this.deferredAttrNodes)) {
                    if (deferredAttrNode.process(this)) {
                        this.deferredAttrNodes.remove(deferredAttrNode);
                        z = true;
                    }
                }
                if (!z) {
                    if (insideOverloadPhase()) {
                        Iterator<DeferredAttrNode> it = this.deferredAttrNodes.iterator();
                        while (it.hasNext()) {
                            it.next().dt.tree.type = Type.noType;
                        }
                        return;
                    }
                    try {
                        this.inferenceContext.solveAny(List.from(pickDeferredNode().deferredStuckPolicy.stuckVars()), this.warn);
                        this.inferenceContext.notifyChange();
                    } catch (Infer.GraphStrategy.NodeNotFoundException unused) {
                        return;
                    }
                }
            }
        }

        public boolean insideOverloadPhase() {
            if (this == DeferredAttr.this.emptyDeferredAttrContext) {
                return false;
            }
            if (this.mode == AttrMode.SPECULATIVE) {
                return true;
            }
            return this.parent.insideOverloadPhase();
        }

        public DeferredAttrNode pickDeferredNode() {
            List list = (List) GraphUtils.tarjan(buildStuckGraph()).get(0);
            return (DeferredAttrNode) (list.length() == 1 ? ((StuckNode) list.get(0)).data : this.deferredAttrNodes.get(0));
        }
    }

    public static class DeferredAttrDiagHandler extends Log.DeferredDiagnosticHandler {

        public static class PosScanner extends TreeScanner {
            boolean found = false;
            JCDiagnostic.DiagnosticPosition pos;

            public PosScanner(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
                this.pos = diagnosticPosition;
            }

            @Override // com.sun.tools.javac.tree.TreeScanner
            public void scan(JCTree jCTree) {
                if (jCTree != null && jCTree.pos() == this.pos) {
                    this.found = true;
                }
                super.scan(jCTree);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DeferredAttrDiagHandler(Log log, final JCTree jCTree) {
            super(log, new Predicate() { // from class: com.sun.tools.javac.comp.g0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return DeferredAttr.DeferredAttrDiagHandler.h(jCTree, (JCDiagnostic) obj);
                }
            });
            Objects.requireNonNull(log);
        }

        public static /* synthetic */ boolean h(JCTree jCTree, JCDiagnostic jCDiagnostic) {
            PosScanner posScanner = new PosScanner(jCDiagnostic.getDiagnosticPosition());
            posScanner.scan(jCTree);
            return posScanner.found;
        }
    }

    public class DeferredAttrNode {
        DeferredStuckPolicy deferredStuckPolicy;
        DeferredType dt;
        Attr.ResultInfo resultInfo;

        public class LambdaBodyStructChecker extends TreeScanner {
            boolean isVoidCompatible = true;
            boolean isPotentiallyValueCompatible = true;

            public LambdaBodyStructChecker() {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitLambda(JCTree.JCLambda jCLambda) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitReturn(JCTree.JCReturn jCReturn) {
                if (jCReturn.expr != null) {
                    this.isVoidCompatible = false;
                } else {
                    this.isPotentiallyValueCompatible = false;
                }
            }
        }

        public class StructuralStuckChecker extends TreeScanner {
            Env<AttrContext> env;
            InferenceContext inferenceContext;
            Attr.ResultInfo resultInfo;

            public StructuralStuckChecker() {
            }

            public static /* synthetic */ JCTree.JCVariableDecl a(StructuralStuckChecker structuralStuckChecker, JCTree.JCVariableDecl jCVariableDecl) {
                TreeMaker treeMaker = DeferredAttr.this.make;
                return treeMaker.VarDef(jCVariableDecl.mods, jCVariableDecl.name, treeMaker.Erroneous(), null);
            }

            public boolean canLambdaBodyCompleteNormally(JCTree.JCLambda jCLambda) {
                List<JCTree.JCVariableDecl> list = jCLambda.params;
                ArgumentAttr.LocalCacheContext localCacheContextWithLocalCacheContext = DeferredAttr.this.argumentAttr.withLocalCacheContext();
                try {
                    jCLambda.params = (List) jCLambda.params.stream().map(new Function() { // from class: com.sun.tools.javac.comp.h0
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return DeferredAttr.DeferredAttrNode.StructuralStuckChecker.a(this.b, (JCTree.JCVariableDecl) obj);
                        }
                    }).collect(List.collector());
                    DeferredAttr deferredAttr = DeferredAttr.this;
                    return deferredAttr.attribSpeculativeLambda(jCLambda, this.env, deferredAttr.attr.unknownExprInfo).canCompleteNormally;
                } finally {
                    localCacheContextWithLocalCacheContext.leave();
                    jCLambda.params = list;
                }
            }

            public void check(DeferredType deferredType, Attr.ResultInfo resultInfo, DeferredAttrContext deferredAttrContext) {
                this.resultInfo = resultInfo;
                this.inferenceContext = deferredAttrContext.inferenceContext;
                this.env = deferredType.env;
                deferredType.tree.accept(this);
                deferredType.speculativeCache.put(DeferredAttr.this.stuckTree, resultInfo);
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitConditional(JCTree.JCConditional jCConditional) {
                scan(jCConditional.truepart);
                scan(jCConditional.falsepart);
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitLambda(JCTree.JCLambda jCLambda) {
                Type typeFindDescriptorType;
                Attr.ResultInfo resultInfo = this.resultInfo;
                Check.CheckContext checkContext = resultInfo.checkContext;
                Type type = resultInfo.pt;
                if (this.inferenceContext.inferencevars.contains(type)) {
                    return;
                }
                try {
                    typeFindDescriptorType = DeferredAttr.this.types.findDescriptorType(type);
                } catch (Types.FunctionDescriptorLookupError e) {
                    checkContext.report(null, e.getDiagnostic());
                    typeFindDescriptorType = null;
                }
                if (typeFindDescriptorType.mo71getParameterTypes().length() != jCLambda.params.length()) {
                    checkContext.report(jCLambda, DeferredAttr.this.diags.fragment(CompilerProperties.Fragments.IncompatibleArgTypesInLambda));
                }
                Type typeMo73getReturnType = typeFindDescriptorType.mo73getReturnType();
                boolean zHasTag = typeMo73getReturnType.hasTag(TypeTag.VOID);
                if (jCLambda.getBodyKind() == LambdaExpressionTree.BodyKind.EXPRESSION) {
                    if (!zHasTag || TreeInfo.isExpressionStatement((JCTree.JCExpression) jCLambda.getBody())) {
                        return;
                    }
                    this.resultInfo.checkContext.report(jCLambda.pos(), DeferredAttr.this.diags.fragment(CompilerProperties.Fragments.IncompatibleRetTypeInLambda(CompilerProperties.Fragments.MissingRetVal(typeMo73getReturnType))));
                    return;
                }
                LambdaBodyStructChecker lambdaBodyStructChecker = DeferredAttrNode.this.new LambdaBodyStructChecker();
                jCLambda.body.accept(lambdaBodyStructChecker);
                boolean z = lambdaBodyStructChecker.isVoidCompatible;
                if (zHasTag) {
                    if (z) {
                        return;
                    }
                    this.resultInfo.checkContext.report(jCLambda.pos(), DeferredAttr.this.diags.fragment(CompilerProperties.Fragments.UnexpectedRetVal));
                    return;
                }
                boolean z2 = lambdaBodyStructChecker.isPotentiallyValueCompatible && !canLambdaBodyCompleteNormally(jCLambda);
                if (!z2 && !z) {
                    DeferredAttr.this.log.error(jCLambda.body.pos(), CompilerProperties.Errors.LambdaBodyNeitherValueNorVoidCompatible);
                }
                if (z2) {
                    return;
                }
                this.resultInfo.checkContext.report(jCLambda.pos(), DeferredAttr.this.diags.fragment(CompilerProperties.Fragments.IncompatibleRetTypeInLambda(CompilerProperties.Fragments.MissingRetVal(typeMo73getReturnType))));
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitReference(JCTree.JCMemberReference jCMemberReference) {
                Type typeFindDescriptorType;
                Assert.checkNonNull(jCMemberReference.getOverloadKind());
                Attr.ResultInfo resultInfo = this.resultInfo;
                Check.CheckContext checkContext = resultInfo.checkContext;
                Type type = resultInfo.pt;
                if (this.inferenceContext.inferencevars.contains(type)) {
                    return;
                }
                try {
                    typeFindDescriptorType = DeferredAttr.this.types.findDescriptorType(type);
                } catch (Types.FunctionDescriptorLookupError e) {
                    checkContext.report(null, e.getDiagnostic());
                    typeFindDescriptorType = null;
                }
                Env<AttrContext> envDup = this.env.dup(jCMemberReference);
                JCTree.JCExpression jCExpression = (JCTree.JCExpression) DeferredAttr.this.attribSpeculative(jCMemberReference.getQualifierExpression(), envDup, DeferredAttr.this.attr.memberReferenceQualifierResult(jCMemberReference), DeferredAttr.this.argumentAttr.withLocalCacheContext());
                ListBuffer listBuffer = new ListBuffer();
                for (Type type2 : typeFindDescriptorType.mo71getParameterTypes()) {
                    listBuffer.append(Type.noType);
                }
                JCTree.JCMemberReference jCMemberReference2 = (JCTree.JCMemberReference) new TreeCopier(DeferredAttr.this.make).copy(jCMemberReference);
                jCMemberReference2.expr = jCExpression;
                Resolve resolve = DeferredAttr.this.rs;
                Type type3 = jCExpression.type;
                Name name = jCMemberReference.name;
                List<Type> list = listBuffer.toList();
                List<Type> listNil = List.nil();
                Resolve resolve2 = DeferredAttr.this.rs;
                Symbol symbol = resolve.resolveMemberReference(envDup, jCMemberReference2, type3, name, list, listNil, typeFindDescriptorType, resolve2.arityMethodCheck, this.inferenceContext, resolve2.structuralReferenceChooser).fst;
                int i = AnonymousClass5.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[symbol.kind.ordinal()];
                if (i == 1 || i == 2) {
                    checkContext.report(jCMemberReference, DeferredAttr.this.diags.fragment(CompilerProperties.Fragments.IncompatibleArgTypesInMref));
                } else if (i == 3 || i == 4) {
                    JCDiagnostic.DiagnosticType diagnosticType = JCDiagnostic.DiagnosticType.FRAGMENT;
                    Type type4 = jCExpression.type;
                    checkContext.report(jCMemberReference, ((Resolve.ResolveError) symbol).getDiagnostic(diagnosticType, jCMemberReference, type4.tsym, type4, jCMemberReference.name, listBuffer.toList(), List.nil()));
                }
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
                scan(jCSwitchExpression.cases);
            }
        }

        public DeferredAttrNode(DeferredType deferredType, Attr.ResultInfo resultInfo, DeferredStuckPolicy deferredStuckPolicy) {
            this.dt = deferredType;
            this.resultInfo = resultInfo;
            this.deferredStuckPolicy = deferredStuckPolicy;
        }

        public boolean process(final DeferredAttrContext deferredAttrContext) {
            int iOrdinal = deferredAttrContext.mode.ordinal();
            if (iOrdinal != 0) {
                if (iOrdinal != 1) {
                    x01.a("Bad mode");
                    return false;
                }
            } else {
                if (this.deferredStuckPolicy.isStuck()) {
                    new StructuralStuckChecker().check(this.dt, this.resultInfo, deferredAttrContext);
                    return true;
                }
                Assert.error("Cannot get here");
            }
            if (!this.deferredStuckPolicy.isStuck()) {
                Assert.check(!deferredAttrContext.insideOverloadPhase(), "attribution shouldn't be happening here");
                Attr.ResultInfo resultInfo = this.resultInfo;
                this.dt.check(resultInfo.dup(deferredAttrContext.inferenceContext.asInstType(resultInfo.pt)), DeferredAttr.this.dummyStuckPolicy);
                return true;
            }
            DeferredAttrContext deferredAttrContext2 = deferredAttrContext.parent;
            if (deferredAttrContext2 == DeferredAttr.this.emptyDeferredAttrContext || !Type.containsAny(deferredAttrContext2.inferenceContext.inferencevars, List.from(this.deferredStuckPolicy.stuckVars()))) {
                return false;
            }
            DeferredAttrContext deferredAttrContext3 = deferredAttrContext.parent;
            DeferredType deferredType = this.dt;
            Attr.ResultInfo resultInfo2 = this.resultInfo;
            deferredAttrContext3.addDeferredAttrNode(deferredType, resultInfo2.dup(new Check.NestedCheckContext(this, resultInfo2.checkContext) { // from class: com.sun.tools.javac.comp.DeferredAttr.DeferredAttrNode.1
                final /* synthetic */ DeferredAttrNode this$1;

                {
                    this.this$1 = this;
                }

                @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
                public DeferredAttrContext deferredAttrContext() {
                    return deferredAttrContext.parent;
                }

                @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
                public InferenceContext inferenceContext() {
                    return deferredAttrContext.parent.inferenceContext;
                }
            }), this.deferredStuckPolicy);
            this.dt.tree.type = Type.stuckType;
            return true;
        }
    }

    public interface DeferredStuckPolicy {
        Set<Type> depVars();

        boolean isStuck();

        Set<Type> stuckVars();
    }

    public class DeferredTypeMap<T> extends Type.StructuralTypeMapping<T> {
        DeferredAttrContext deferredAttrContext;

        public DeferredTypeMap(AttrMode attrMode, Symbol symbol, Resolve.MethodResolutionPhase methodResolutionPhase) {
            this.deferredAttrContext = DeferredAttr.this.new DeferredAttrContext(attrMode, symbol, methodResolutionPhase, DeferredAttr.this.infer.emptyContext, DeferredAttr.this.emptyDeferredAttrContext, DeferredAttr.this.types.noWarnings);
        }

        public Type typeOf(DeferredType deferredType, T t) {
            int iOrdinal = this.deferredAttrContext.mode.ordinal();
            if (iOrdinal == 0) {
                DeferredAttrContext deferredAttrContext = this.deferredAttrContext;
                return deferredType.speculativeType(deferredAttrContext.msym, deferredAttrContext.phase);
            }
            if (iOrdinal != 1) {
                Assert.error();
                return null;
            }
            Type type = deferredType.tree.type;
            return type == null ? Type.noType : type;
        }

        @Override // com.sun.tools.javac.code.Types.MapVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitType(Type type, T t) {
            return !type.hasTag(TypeTag.DEFERRED) ? super.visitType(type, t) : typeOf((DeferredType) type, t);
        }
    }

    public static abstract class FilterScanner extends TreeScanner {
        final Predicate<JCTree> treeFilter;

        public FilterScanner(final Set<JCTree.Tag> set) {
            this.treeFilter = new Predicate() { // from class: com.sun.tools.javac.comp.i0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return set.contains(((JCTree) obj).getTag());
                }
            };
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            if (jCTree != null) {
                if (this.treeFilter.test(jCTree)) {
                    super.scan(jCTree);
                } else {
                    skip(jCTree);
                }
            }
        }

        public void skip(JCTree jCTree) {
        }
    }

    public static class LambdaReturnScanner extends FilterScanner {
        public LambdaReturnScanner() {
            super(EnumSet.of(JCTree.Tag.BLOCK, JCTree.Tag.CASE, JCTree.Tag.CATCH, JCTree.Tag.DOLOOP, JCTree.Tag.FOREACHLOOP, JCTree.Tag.FORLOOP, JCTree.Tag.IF, JCTree.Tag.RETURN, JCTree.Tag.SYNCHRONIZED, JCTree.Tag.SWITCH, JCTree.Tag.TRY, JCTree.Tag.WHILELOOP));
        }
    }

    public class OverloadStuckPolicy extends CheckStuckPolicy implements DeferredStuckPolicy {
        boolean stuck;

        public OverloadStuckPolicy(Attr.ResultInfo resultInfo, DeferredType deferredType) {
            super(resultInfo, deferredType);
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.CheckStuckPolicy, com.sun.tools.javac.comp.DeferredAttr.DeferredStuckPolicy
        public boolean isStuck() {
            return super.isStuck() || this.stuck;
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.CheckStuckPolicy, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            super.visitLambda(jCLambda);
            if (jCLambda.paramKind == JCTree.JCLambda.ParameterKind.IMPLICIT) {
                this.stuck = true;
            }
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.CheckStuckPolicy, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitReference(JCTree.JCMemberReference jCMemberReference) {
            super.visitReference(jCMemberReference);
            if (jCMemberReference.getOverloadKind() != JCTree.JCMemberReference.OverloadKind.UNOVERLOADED) {
                this.stuck = true;
            }
        }
    }

    public static class PolyScanner extends FilterScanner {
        public PolyScanner() {
            super(EnumSet.of(JCTree.Tag.CONDEXPR, JCTree.Tag.PARENS, JCTree.Tag.LAMBDA, JCTree.Tag.REFERENCE, JCTree.Tag.SWITCH_EXPRESSION));
        }
    }

    public class RecoveryDeferredTypeMap extends DeferredTypeMap<Type> {
        public RecoveryDeferredTypeMap(AttrMode attrMode, Symbol symbol, Resolve.MethodResolutionPhase methodResolutionPhase) {
            super(attrMode, symbol, methodResolutionPhase == null ? Resolve.MethodResolutionPhase.BOX : methodResolutionPhase);
        }

        private List<Type> map(List<Type> list, List<Type> list2) {
            if (list.nonEmpty()) {
                Type type = null;
                List<Type> map = map(list.tail, list2 != null ? list2.tail : null);
                Type type2 = list.head;
                if (list2 != null && list2.nonEmpty()) {
                    type = list2.head;
                }
                Type typeVisit = visit(type2, type);
                if (map != list.tail || typeVisit != list.head) {
                    return map.prepend(typeVisit);
                }
            }
            return list;
        }

        private Type recover(DeferredType deferredType, Type type) {
            boolean z = deferredType.tree.hasTag(JCTree.Tag.REFERENCE) || deferredType.tree.hasTag(JCTree.Tag.LAMBDA);
            if (type == null || (((deferredType instanceof ArgumentAttr.ArgumentType) && ((ArgumentAttr.ArgumentType) deferredType).speculativeTypes.values().stream().allMatch(new Predicate() { // from class: yj3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Type) obj).hasTag(TypeTag.ERROR);
                }
            })) || (z && !DeferredAttr.this.types.isFunctionalInterface(type)))) {
                type = Type.recoveryType;
            }
            Attr attr = DeferredAttr.this.attr;
            Objects.requireNonNull(attr);
            deferredType.check(new Attr.RecoveryInfo(attr, this.deferredAttrContext, type) { // from class: com.sun.tools.javac.comp.DeferredAttr.RecoveryDeferredTypeMap.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(deferredAttrContext, type);
                    Objects.requireNonNull(attr);
                }

                @Override // com.sun.tools.javac.comp.Attr.ResultInfo
                public Type check(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type2) {
                    return DeferredAttr.this.chk.checkNonVoid(diagnosticPosition, super.check(diagnosticPosition, type2));
                }
            });
            return super.visit(deferredType);
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredTypeMap
        public Type typeOf(DeferredType deferredType, Type type) {
            Type typeTypeOf = super.typeOf(deferredType, type);
            return typeTypeOf == Type.noType ? recover(deferredType, type) : typeTypeOf;
        }

        @Override // com.sun.tools.javac.code.Type.StructuralTypeMapping, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitMethodType(Type.MethodType methodType, Type type) {
            TypeTag typeTag = TypeTag.METHOD;
            if (methodType.hasTag(typeTag)) {
                DeferredAttrContext deferredAttrContext = this.deferredAttrContext;
                if (deferredAttrContext.mode == AttrMode.CHECK) {
                    Type type2 = deferredAttrContext.msym.type;
                    Type originalType = type2.hasTag(TypeTag.ERROR) ? ((Type.ErrorType) type2).getOriginalType() : null;
                    if (originalType != null && originalType.hasTag(typeTag)) {
                        List<Type> map = map(methodType.mo71getParameterTypes(), originalType.mo71getParameterTypes());
                        Type typeVisit = visit(methodType.mo73getReturnType(), originalType.mo73getReturnType());
                        List<Type> map2 = map(methodType.mo74getThrownTypes(), originalType.mo74getThrownTypes());
                        return (map == methodType.mo71getParameterTypes() && typeVisit == methodType.mo73getReturnType() && map2 == methodType.mo74getThrownTypes()) ? methodType : new Type.MethodType(map, typeVisit, map2, methodType.tsym);
                    }
                }
            }
            return super.visitMethodType(methodType, type);
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredTypeMap, com.sun.tools.javac.code.Types.MapVisitor, com.sun.tools.javac.code.Type.Visitor
        public /* bridge */ /* synthetic */ Type visitType(Type type, Object obj) {
            return super.visitType(type, obj);
        }
    }

    public static class SwitchExpressionScanner extends FilterScanner {
        public SwitchExpressionScanner() {
            super(EnumSet.of(JCTree.Tag.BLOCK, JCTree.Tag.CASE, JCTree.Tag.CATCH, JCTree.Tag.DOLOOP, JCTree.Tag.FOREACHLOOP, JCTree.Tag.FORLOOP, JCTree.Tag.IF, JCTree.Tag.SYNCHRONIZED, JCTree.Tag.SWITCH, JCTree.Tag.TRY, JCTree.Tag.WHILELOOP, JCTree.Tag.YIELD));
        }
    }

    public static class TypeDeclVisitor extends TreeScanner {
        boolean result;

        private TypeDeclVisitor() {
            this.result = false;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            this.result = true;
        }
    }

    public DeferredAttr(Context context) {
        context.put(deferredAttrKey, this);
        this.annotate = Annotate.instance(context);
        this.attr = Attr.instance(context);
        this.argumentAttr = ArgumentAttr.instance(context);
        this.chk = Check.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        this.enter = Enter.instance(context);
        Infer inferInstance = Infer.instance(context);
        this.infer = inferInstance;
        this.rs = Resolve.instance(context);
        this.log = Log.instance(context);
        this.syms = Symtab.instance(context);
        TreeMaker treeMakerInstance = TreeMaker.instance(context);
        this.make = treeMakerInstance;
        this.types = Types.instance(context);
        this.flow = Flow.instance(context);
        Names namesInstance = Names.instance(context);
        this.names = namesInstance;
        this.stuckTree = treeMakerInstance.Ident(namesInstance.empty).setType((Type) Type.stuckType);
        this.typeEnvs = TypeEnvs.instance(context);
        this.dcfh = DeferredCompletionFailureHandler.instance(context);
        this.emptyDeferredAttrContext = new DeferredAttrContext(AttrMode.CHECK, null, Resolve.MethodResolutionPhase.BOX, inferInstance.emptyContext, null, null) { // from class: com.sun.tools.javac.comp.DeferredAttr.1
            @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredAttrContext
            public void addDeferredAttrNode(DeferredType deferredType, Attr.ResultInfo resultInfo, DeferredStuckPolicy deferredStuckPolicy) {
                Assert.error("Empty deferred context!");
            }

            @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredAttrContext
            public void complete() {
                Assert.error("Empty deferred context!");
            }

            public String toString() {
                return "Empty deferred context!";
            }
        };
        this.treeCopier = new TreeCopier<Void>(treeMakerInstance) { // from class: com.sun.tools.javac.comp.DeferredAttr.2
            @Override // com.sun.tools.javac.tree.TreeCopier, com.sun.source.tree.TreeVisitor
            public JCTree visitMemberReference(MemberReferenceTree memberReferenceTree, Void r9) {
                final JCTree.JCMemberReference jCMemberReference = (JCTree.JCMemberReference) memberReferenceTree;
                JCTree.JCMemberReference jCMemberReference2 = new JCTree.JCMemberReference(this, jCMemberReference.mode, jCMemberReference.name, (JCTree.JCExpression) copy(jCMemberReference.expr, r9), copy(jCMemberReference.typeargs, r9)) { // from class: com.sun.tools.javac.comp.DeferredAttr.2.1
                    final /* synthetic */ AnonymousClass2 this$1;

                    {
                        this.this$1 = this;
                    }

                    @Override // com.sun.tools.javac.tree.JCTree.JCMemberReference
                    public JCTree.JCMemberReference.OverloadKind getOverloadKind() {
                        return jCMemberReference.getOverloadKind();
                    }

                    @Override // com.sun.tools.javac.tree.JCTree.JCMemberReference
                    public void setOverloadKind(JCTree.JCMemberReference.OverloadKind overloadKind) {
                        JCTree.JCMemberReference.OverloadKind overloadKind2;
                        JCTree.JCMemberReference.OverloadKind overloadKind3 = jCMemberReference.getOverloadKind();
                        if (overloadKind3 == null || overloadKind3 == (overloadKind2 = JCTree.JCMemberReference.OverloadKind.ERROR)) {
                            jCMemberReference.setOverloadKind(overloadKind);
                        } else {
                            Assert.check(overloadKind3 == overloadKind || overloadKind == overloadKind2);
                        }
                    }
                };
                jCMemberReference2.pos = jCMemberReference.pos;
                return jCMemberReference2;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.sun.tools.javac.tree.TreeCopier, com.sun.source.tree.TreeVisitor
            public JCTree visitNewClass(NewClassTree newClassTree, Void r10) {
                JCTree.JCNewClass jCNewClass = (JCTree.JCNewClass) newClassTree;
                if (!TreeInfo.isDiamond(jCNewClass)) {
                    return super.visitNewClass(newClassTree, r10);
                }
                return DeferredAttr.this.make.at(jCNewClass.pos).SpeculativeNewClass((JCTree.JCExpression) copy(jCNewClass.encl, r10), copy(jCNewClass.typeargs, r10), (JCTree.JCExpression) copy(jCNewClass.clazz, r10), copy(jCNewClass.args, r10), null, jCNewClass.def != null || jCNewClass.classDeclRemoved());
            }
        };
        this.deferredCopier = new Types.TypeMapping<Void>() { // from class: com.sun.tools.javac.comp.DeferredAttr.3
            @Override // com.sun.tools.javac.code.Types.MapVisitor, com.sun.tools.javac.code.Type.Visitor
            public Type visitType(Type type, Void r4) {
                if (!type.hasTag(TypeTag.DEFERRED)) {
                    return type;
                }
                DeferredType deferredType = (DeferredType) type;
                DeferredAttr deferredAttr = DeferredAttr.this;
                return deferredAttr.new DeferredType((JCTree.JCExpression) deferredAttr.treeCopier.copy(deferredType.tree), deferredType.env);
            }
        };
    }

    public static /* synthetic */ JCTree.JCVariableDecl b(JCTree.JCStatement jCStatement) {
        return (JCTree.JCVariableDecl) jCStatement;
    }

    private boolean hasTypeDeclaration(JCTree jCTree) {
        TypeDeclVisitor typeDeclVisitor = new TypeDeclVisitor();
        typeDeclVisitor.scan(jCTree);
        return typeDeclVisitor.result;
    }

    public static DeferredAttr instance(Context context) {
        DeferredAttr deferredAttr = (DeferredAttr) context.get(deferredAttrKey);
        return deferredAttr == null ? new DeferredAttr(context) : deferredAttr;
    }

    public <Z> JCTree attribSpeculative(JCTree jCTree, Env<AttrContext> env, Attr.ResultInfo resultInfo, Supplier<Log.DiagnosticHandler> supplier, AttributionMode attributionMode, ArgumentAttr.LocalCacheContext localCacheContext) {
        AttrContext attrContext = env.info;
        Env<AttrContext> envDup = env.dup(jCTree, attrContext.dup(attrContext.scope.dupUnshared(attrContext.scope.owner)));
        envDup.info.attributionMode = attributionMode;
        Log.DiagnosticHandler deferredAttrDiagHandler = supplier != null ? supplier.get() : new DeferredAttrDiagHandler(this.log, jCTree);
        DeferredCompletionFailureHandler deferredCompletionFailureHandler = this.dcfh;
        DeferredCompletionFailureHandler.Handler handler = deferredCompletionFailureHandler.setHandler(deferredCompletionFailureHandler.speculativeCodeHandler);
        Annotate.Queues queues = this.annotate.setQueues(new Annotate.Queues());
        Log log = this.log;
        int i = log.nwarnings;
        log.nwarnings = 0;
        try {
            this.attr.attribTree(jCTree, envDup, resultInfo);
            return jCTree;
        } finally {
            this.annotate.setQueues(queues);
            this.dcfh.setHandler(handler);
            this.log.nwarnings += i;
            this.enter.unenter(env.toplevel, jCTree);
            this.log.popDiagnosticHandler(deferredAttrDiagHandler);
            if (localCacheContext != null) {
                localCacheContext.leave();
            }
        }
    }

    public JCTree.JCLambda attribSpeculativeLambda(JCTree.JCLambda jCLambda, Env<AttrContext> env, Attr.ResultInfo resultInfo) {
        ListBuffer listBuffer = new ListBuffer();
        listBuffer.addAll(jCLambda.params);
        if (jCLambda.getBodyKind() == LambdaExpressionTree.BodyKind.EXPRESSION) {
            listBuffer.add(this.make.Return((JCTree.JCExpression) jCLambda.body));
        } else {
            listBuffer.add((JCTree.JCBlock) jCLambda.body);
        }
        JCTree.JCBlock jCBlockBlock = this.make.at(jCLambda.pos).Block(0L, listBuffer.toList());
        Env<AttrContext> envLambdaEnv = this.attr.lambdaEnv(jCLambda, env);
        try {
            envLambdaEnv.info.returnResult = resultInfo;
            JCTree.JCBlock jCBlock = (JCTree.JCBlock) attribSpeculative(jCBlockBlock, envLambdaEnv, resultInfo);
            List<JCTree.JCVariableDecl> list = (List) jCBlock.getStatements().stream().filter(new Predicate() { // from class: wj3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((JCTree.JCStatement) obj).hasTag(JCTree.Tag.VARDEF);
                }
            }).map(new Function() { // from class: xj3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return DeferredAttr.b((JCTree.JCStatement) obj);
                }
            }).collect(List.collector());
            JCTree.JCStatement jCStatementLast = jCBlock.getStatements().last();
            if (jCStatementLast.hasTag(JCTree.Tag.RETURN)) {
                jCStatementLast = ((JCTree.JCReturn) jCStatementLast).expr;
            }
            JCTree.JCLambda jCLambdaLambda = this.make.Lambda(list, jCStatementLast);
            this.attr.preFlow(jCLambdaLambda);
            this.flow.analyzeLambda(env, jCLambdaLambda, this.make, false);
            return jCLambdaLambda;
        } finally {
            envLambdaEnv.info.scope.leave();
        }
    }

    public class DeferredType extends Type {
        Env<AttrContext> env;
        AttrMode mode;
        Set<Symbol> notPertinentToApplicability;
        SpeculativeCache speculativeCache;
        public JCTree.JCExpression tree;

        public class SpeculativeCache {
            private Map<Symbol, List<Entry>> cache = new WeakHashMap();

            public class Entry {
                Attr.ResultInfo resultInfo;
                JCTree speculativeTree;

                public Entry(JCTree jCTree, Attr.ResultInfo resultInfo) {
                    this.speculativeTree = jCTree;
                    this.resultInfo = resultInfo;
                }

                public boolean matches(Resolve.MethodResolutionPhase methodResolutionPhase) {
                    return this.resultInfo.checkContext.deferredAttrContext().phase == methodResolutionPhase;
                }
            }

            public SpeculativeCache() {
            }

            public Entry get(Symbol symbol, Resolve.MethodResolutionPhase methodResolutionPhase) {
                List<Entry> list = this.cache.get(symbol);
                if (list == null) {
                    return null;
                }
                for (Entry entry : list) {
                    if (entry.matches(methodResolutionPhase)) {
                        return entry;
                    }
                }
                return null;
            }

            public void put(JCTree jCTree, Attr.ResultInfo resultInfo) {
                Symbol symbol = resultInfo.checkContext.deferredAttrContext().msym;
                List<Entry> listNil = this.cache.get(symbol);
                if (listNil == null) {
                    listNil = List.nil();
                }
                this.cache.put(symbol, listNil.prepend(new Entry(jCTree, resultInfo)));
            }
        }

        public DeferredType(JCTree.JCExpression jCExpression, Env<AttrContext> env) {
            super(null, List.nil());
            this.notPertinentToApplicability = new HashSet();
            this.tree = jCExpression;
            this.env = DeferredAttr.this.attr.copyEnv(env);
            this.speculativeCache = new SpeculativeCache();
        }

        public Type check(Attr.ResultInfo resultInfo) {
            DeferredStuckPolicy overloadStuckPolicy;
            if (resultInfo.pt.hasTag(TypeTag.NONE) || resultInfo.pt.isErroneous()) {
                overloadStuckPolicy = DeferredAttr.this.dummyStuckPolicy;
            } else {
                overloadStuckPolicy = (resultInfo.checkContext.deferredAttrContext().mode == AttrMode.SPECULATIVE || resultInfo.checkContext.deferredAttrContext().insideOverloadPhase()) ? DeferredAttr.this.new OverloadStuckPolicy(resultInfo, this) : DeferredAttr.this.new CheckStuckPolicy(resultInfo, this);
            }
            return check(resultInfo, overloadStuckPolicy);
        }

        public Type complete(Attr.ResultInfo resultInfo, DeferredAttrContext deferredAttrContext) {
            int iOrdinal = deferredAttrContext.mode.ordinal();
            if (iOrdinal != 0) {
                if (iOrdinal != 1) {
                    Assert.error();
                    return null;
                }
                Assert.check(this.mode != null);
                return DeferredAttr.this.attr.attribTree(this.tree, this.env, resultInfo);
            }
            AttrMode attrMode = this.mode;
            Assert.check(attrMode == null || attrMode == AttrMode.SPECULATIVE);
            JCTree jCTreeAttribSpeculative = DeferredAttr.this.attribSpeculative(this.tree, this.env, resultInfo);
            this.speculativeCache.put(jCTreeAttribSpeculative, resultInfo);
            return jCTreeAttribSpeculative.type;
        }

        @Override // com.sun.tools.javac.code.Type
        public TypeTag getTag() {
            return TypeTag.DEFERRED;
        }

        public JCTree speculativeTree(DeferredAttrContext deferredAttrContext) {
            SpeculativeCache.Entry entry = this.speculativeCache.get(deferredAttrContext.msym, deferredAttrContext.phase);
            return entry != null ? entry.speculativeTree : DeferredAttr.this.stuckTree;
        }

        public Type speculativeType(Symbol symbol, Resolve.MethodResolutionPhase methodResolutionPhase) {
            SpeculativeCache.Entry entry = this.speculativeCache.get(symbol, methodResolutionPhase);
            return entry != null ? entry.speculativeTree.type : Type.noType;
        }

        @Override // com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
        public String toString() {
            return "DeferredType";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Type check(Attr.ResultInfo resultInfo, DeferredStuckPolicy deferredStuckPolicy) {
            DeferredAttrContext deferredAttrContext = resultInfo.checkContext.deferredAttrContext();
            Assert.check(deferredAttrContext != DeferredAttr.this.emptyDeferredAttrContext);
            if (deferredStuckPolicy.isStuck()) {
                deferredAttrContext.addDeferredAttrNode(this, resultInfo, deferredStuckPolicy);
                AttrMode attrMode = deferredAttrContext.mode;
                AttrMode attrMode2 = AttrMode.SPECULATIVE;
                if (attrMode == attrMode2) {
                    this.notPertinentToApplicability.add(deferredAttrContext.msym);
                    this.mode = attrMode2;
                }
                return Type.noType;
            }
            try {
                return complete(resultInfo, deferredAttrContext);
            } finally {
                this.mode = deferredAttrContext.mode;
            }
        }
    }

    public JCTree attribSpeculative(JCTree jCTree, Env<AttrContext> env, Attr.ResultInfo resultInfo, ArgumentAttr.LocalCacheContext localCacheContext) {
        return attribSpeculative(jCTree, env, resultInfo, this.treeCopier, null, AttributionMode.SPECULATIVE, localCacheContext);
    }

    public <Z> JCTree attribSpeculative(JCTree jCTree, Env<AttrContext> env, Attr.ResultInfo resultInfo, TreeCopier<Z> treeCopier, Supplier<Log.DiagnosticHandler> supplier, AttributionMode attributionMode, ArgumentAttr.LocalCacheContext localCacheContext) {
        return attribSpeculative(treeCopier.copy(jCTree), env, resultInfo, supplier, attributionMode, localCacheContext);
    }

    public JCTree attribSpeculative(JCTree jCTree, Env<AttrContext> env, Attr.ResultInfo resultInfo) {
        return attribSpeculative(jCTree, env, resultInfo, this.treeCopier, null, AttributionMode.SPECULATIVE, !hasTypeDeclaration(jCTree) ? null : this.argumentAttr.withLocalCacheContext());
    }
}

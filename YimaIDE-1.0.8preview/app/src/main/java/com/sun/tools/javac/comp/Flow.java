package com.sun.tools.javac.comp;

import com.sun.source.tree.LambdaExpressionTree;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Flow;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.ArrayUtils;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Bits;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Log.DiscardDiagnosticHandler;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Flow {
    protected static final Context.Key<Flow> flowKey = new Context.Key<>();
    private Env<AttrContext> attrEnv;
    private final Check chk;
    private final JCDiagnostic.Factory diags;
    private final ExhaustivenessComputer exhaustiveness;
    private final Log log;
    private TreeMaker make;
    private final Names names;
    private final Resolve rs;
    private final Symtab syms;
    private final Types types;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Flow$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr;
            try {
                iArr[JCTree.Tag.NOT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PREINC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTINC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PREDEC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTDEC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.AND.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.OR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CLASSDEF.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CASE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.LAMBDA.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public enum FlowKind {
        NORMAL("var.might.already.be.assigned", false),
        SPECULATIVE_LOOP("var.might.be.assigned.in.loop", true);

        final String errKey;
        final boolean isFinal;

        FlowKind(String str, boolean z) {
            this.errKey = str;
            this.isFinal = z;
        }

        public boolean isFinal() {
            return this.isFinal;
        }
    }

    public class LambdaAliveAnalyzer extends AliveAnalyzer {
        boolean inLambda;

        public LambdaAliveAnalyzer() {
            super();
        }

        @Override // com.sun.tools.javac.comp.Flow.AliveAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        }

        @Override // com.sun.tools.javac.comp.Flow.AliveAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            if (this.inLambda || jCLambda.getBodyKind() == LambdaExpressionTree.BodyKind.EXPRESSION) {
                return;
            }
            this.inLambda = true;
            try {
                super.visitLambda(jCLambda);
            } finally {
                this.inLambda = false;
            }
        }

        @Override // com.sun.tools.javac.comp.Flow.AliveAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitReturn(JCTree.JCReturn jCReturn) {
            recordExit(new BaseAnalyzer.PendingExit(jCReturn));
        }
    }

    public class LambdaAssignAnalyzer extends AssignAnalyzer {
        Scope.WriteableScope enclosedSymbols;
        boolean inLambda;

        public LambdaAssignAnalyzer(Env<AttrContext> env) {
            super();
            this.enclosedSymbols = Scope.WriteableScope.create(env.enclClass.sym);
        }

        @Override // com.sun.tools.javac.comp.Flow.AssignAnalyzer
        public boolean trackable(Symbol.VarSymbol varSymbol) {
            return this.enclosedSymbols.includes(varSymbol) && varSymbol.owner.kind == Kinds.Kind.MTH;
        }

        @Override // com.sun.tools.javac.comp.Flow.AssignAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        }

        @Override // com.sun.tools.javac.comp.Flow.AssignAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            if (this.inLambda) {
                return;
            }
            this.inLambda = true;
            try {
                super.visitLambda(jCLambda);
            } finally {
                this.inLambda = false;
            }
        }

        @Override // com.sun.tools.javac.comp.Flow.AssignAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            this.enclosedSymbols.enter(jCVariableDecl.sym);
            super.visitVarDef(jCVariableDecl);
        }
    }

    public class LambdaFlowAnalyzer extends FlowAnalyzer {
        boolean inLambda;
        List<Type> inferredThrownTypes;

        public LambdaFlowAnalyzer() {
            super();
        }

        @Override // com.sun.tools.javac.comp.Flow.FlowAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        }

        @Override // com.sun.tools.javac.comp.Flow.FlowAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            Type type = jCLambda.type;
            if ((type == null || !type.isErroneous()) && !this.inLambda) {
                List<Type> list = this.caught;
                List<Type> list2 = this.thrown;
                ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
                this.inLambda = true;
                try {
                    this.pendingExits = new ListBuffer<>();
                    this.caught = List.of(Flow.this.syms.throwableType);
                    this.thrown = List.nil();
                    scan(jCLambda.body);
                    this.inferredThrownTypes = this.thrown;
                } finally {
                    this.pendingExits = listBuffer;
                    this.caught = list;
                    this.thrown = list2;
                    this.inLambda = false;
                }
            }
        }
    }

    public class SnippetAliveAnalyzer extends AliveAnalyzer {
        public SnippetAliveAnalyzer() {
            super();
        }

        public boolean isAlive() {
            return ((AliveAnalyzer) this).alive != Liveness.DEAD;
        }

        @Override // com.sun.tools.javac.comp.Flow.AliveAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        }

        @Override // com.sun.tools.javac.comp.Flow.AliveAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
        }
    }

    public class SnippetBreakToAnalyzer extends AliveAnalyzer {
        private final JCTree breakTo;
        private boolean breaksTo;

        public SnippetBreakToAnalyzer(JCTree jCTree) {
            super();
            this.breakTo = jCTree;
        }

        public boolean breaksTo() {
            return this.breaksTo;
        }

        @Override // com.sun.tools.javac.comp.Flow.AliveAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBreak(JCTree.JCBreak jCBreak) {
            this.breaksTo = (this.breakTo == jCBreak.target && ((AliveAnalyzer) this).alive == Liveness.ALIVE) | this.breaksTo;
        }
    }

    public Flow(Context context) {
        context.put(flowKey, this);
        this.names = Names.instance(context);
        this.log = Log.instance(context);
        this.syms = Symtab.instance(context);
        this.types = Types.instance(context);
        this.chk = Check.instance(context);
        this.rs = Resolve.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        this.exhaustiveness = ExhaustivenessComputer.instance(context);
    }

    public static Flow instance(Context context) {
        Flow flow = (Flow) context.get(flowKey);
        return flow == null ? new Flow(context) : flow;
    }

    public boolean aliveAfter(Env<AttrContext> env, JCTree jCTree, TreeMaker treeMaker) {
        Log log = this.log;
        Objects.requireNonNull(log);
        Log.DiscardDiagnosticHandler discardDiagnosticHandler = log.new DiscardDiagnosticHandler();
        try {
            SnippetAliveAnalyzer snippetAliveAnalyzer = new SnippetAliveAnalyzer();
            snippetAliveAnalyzer.analyzeTree(env, jCTree, treeMaker);
            return snippetAliveAnalyzer.isAlive();
        } finally {
            this.log.popDiagnosticHandler(discardDiagnosticHandler);
        }
    }

    public void analyzeLambda(Env<AttrContext> env, JCTree.JCLambda jCLambda, TreeMaker treeMaker, boolean z) {
        Log.DiscardDiagnosticHandler discardDiagnosticHandler;
        if (z) {
            discardDiagnosticHandler = null;
        } else {
            Log log = this.log;
            Objects.requireNonNull(log);
            discardDiagnosticHandler = log.new DiscardDiagnosticHandler();
        }
        try {
            new LambdaAliveAnalyzer().analyzeTree(env, jCLambda, treeMaker);
        } finally {
            if (!z) {
                this.log.popDiagnosticHandler(discardDiagnosticHandler);
            }
        }
    }

    public List<Type> analyzeLambdaThrownTypes(Env<AttrContext> env, JCTree.JCLambda jCLambda, TreeMaker treeMaker) {
        Log log = this.log;
        Objects.requireNonNull(log);
        Log.DiscardDiagnosticHandler discardDiagnosticHandler = log.new DiscardDiagnosticHandler();
        try {
            new LambdaAssignAnalyzer(env).analyzeTree(env, jCLambda, treeMaker);
            LambdaFlowAnalyzer lambdaFlowAnalyzer = new LambdaFlowAnalyzer();
            lambdaFlowAnalyzer.analyzeTree(env, jCLambda, treeMaker);
            return lambdaFlowAnalyzer.inferredThrownTypes;
        } finally {
            this.log.popDiagnosticHandler(discardDiagnosticHandler);
        }
    }

    public void analyzeTree(Env<AttrContext> env, TreeMaker treeMaker) {
        new AliveAnalyzer().analyzeTree(env, treeMaker);
        new AssignAnalyzer().analyzeTree(env, treeMaker);
        new FlowAnalyzer().analyzeTree(env, treeMaker);
        new CaptureAnalyzer().analyzeTree(env, treeMaker);
    }

    public boolean breaksToTree(Env<AttrContext> env, JCTree jCTree, JCTree jCTree2, TreeMaker treeMaker) {
        Log log = this.log;
        Objects.requireNonNull(log);
        Log.DiscardDiagnosticHandler discardDiagnosticHandler = log.new DiscardDiagnosticHandler();
        try {
            SnippetBreakToAnalyzer snippetBreakToAnalyzer = new SnippetBreakToAnalyzer(jCTree);
            snippetBreakToAnalyzer.analyzeTree(env, jCTree2, treeMaker);
            return snippetBreakToAnalyzer.breaksTo();
        } finally {
            this.log.popDiagnosticHandler(discardDiagnosticHandler);
        }
    }

    public enum Liveness {
        ALIVE { // from class: com.sun.tools.javac.comp.Flow.Liveness.1
            @Override // com.sun.tools.javac.comp.Flow.Liveness
            public Liveness and(Liveness liveness) {
                return liveness;
            }

            @Override // com.sun.tools.javac.comp.Flow.Liveness
            public Liveness or(Liveness liveness) {
                return this;
            }
        },
        DEAD { // from class: com.sun.tools.javac.comp.Flow.Liveness.2
            @Override // com.sun.tools.javac.comp.Flow.Liveness
            public Liveness and(Liveness liveness) {
                return this;
            }

            @Override // com.sun.tools.javac.comp.Flow.Liveness
            public Liveness or(Liveness liveness) {
                return liveness;
            }
        },
        RECOVERY { // from class: com.sun.tools.javac.comp.Flow.Liveness.3
            @Override // com.sun.tools.javac.comp.Flow.Liveness
            public Liveness and(Liveness liveness) {
                Liveness liveness2 = Liveness.DEAD;
                return liveness == liveness2 ? liveness2 : this;
            }

            @Override // com.sun.tools.javac.comp.Flow.Liveness
            public Liveness or(Liveness liveness) {
                Liveness liveness2 = Liveness.ALIVE;
                return liveness == liveness2 ? liveness2 : this;
            }
        };

        public static Liveness from(boolean z) {
            return z ? ALIVE : DEAD;
        }

        public abstract Liveness and(Liveness liveness);

        public Liveness and(boolean z) {
            return and(from(z));
        }

        public abstract Liveness or(Liveness liveness);

        public Liveness or(boolean z) {
            return or(from(z));
        }

        /* synthetic */ Liveness(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static abstract class BaseAnalyzer extends TreeScanner {
        JCTree.JCClassDecl initScanClass;
        ListBuffer<PendingExit> pendingExits;

        public static class PendingExit {
            JCTree tree;

            public PendingExit(JCTree jCTree) {
                this.tree = jCTree;
            }

            public void resolveJump() {
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private Liveness resolveJump(JCTree jCTree, ListBuffer<PendingExit> listBuffer, JumpKind jumpKind) {
            this.pendingExits = listBuffer;
            boolean z = false;
            for (List list = this.pendingExits.toList(); list.nonEmpty(); list = list.tail) {
                PendingExit pendingExit = (PendingExit) list.head;
                if (pendingExit.tree.hasTag(jumpKind.treeTag) && jumpKind.getTarget(pendingExit.tree) == jCTree) {
                    pendingExit.resolveJump();
                    z = true;
                } else {
                    this.pendingExits.append(pendingExit);
                }
            }
            return Liveness.from(z);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void forEachInitializer(JCTree.JCClassDecl jCClassDecl, boolean z, Consumer<? super JCTree> consumer) {
            JCTree.JCClassDecl jCClassDecl2 = this.initScanClass;
            if (jCClassDecl == jCClassDecl2) {
                return;
            }
            this.initScanClass = jCClassDecl;
            try {
                for (List list = jCClassDecl.defs; list.nonEmpty(); list = list.tail) {
                    JCTree jCTree = (JCTree) list.head;
                    if (!jCTree.hasTag(JCTree.Tag.CLASSDEF)) {
                        boolean z2 = ((TreeInfo.flags(jCTree) | (TreeInfo.symbolFor(jCTree) == null ? 0L : TreeInfo.symbolFor(jCTree).flags_field)) & 8) != 0;
                        if (!jCTree.hasTag(JCTree.Tag.METHODDEF) && z2 == z) {
                            consumer.accept(jCTree);
                        }
                    }
                }
            } finally {
                this.initScanClass = jCClassDecl2;
            }
        }

        public abstract void markDead();

        public void recordExit(PendingExit pendingExit) {
            this.pendingExits.append(pendingExit);
            markDead();
        }

        public Liveness resolveBreaks(JCTree jCTree, ListBuffer<PendingExit> listBuffer) {
            return resolveJump(jCTree, listBuffer, JumpKind.BREAK);
        }

        public Liveness resolveContinues(JCTree jCTree) {
            return resolveJump(jCTree, new ListBuffer<>(), JumpKind.CONTINUE);
        }

        public Liveness resolveYields(JCTree jCTree, ListBuffer<PendingExit> listBuffer) {
            return resolveJump(jCTree, listBuffer, JumpKind.YIELD);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            if (jCTree != null) {
                Type type = jCTree.type;
                if (type == null || type != Type.stuckType) {
                    super.scan(jCTree);
                }
            }
        }

        public void scanSyntheticBreak(TreeMaker treeMaker, JCTree jCTree) {
            if (jCTree.hasTag(JCTree.Tag.SWITCH_EXPRESSION)) {
                JCTree.JCYield jCYieldYield = treeMaker.at(-1).Yield(treeMaker.Erroneous().setType(jCTree.type));
                jCYieldYield.target = jCTree;
                scan(jCYieldYield);
            } else {
                JCTree.JCBreak jCBreakBreak = treeMaker.at(-1).Break(null);
                jCBreakBreak.target = jCTree;
                scan(jCBreakBreak);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitPackageDef(JCTree.JCPackageDecl jCPackageDecl) {
        }

        public enum JumpKind {
            BREAK(JCTree.Tag.BREAK) { // from class: com.sun.tools.javac.comp.Flow.BaseAnalyzer.JumpKind.1
                @Override // com.sun.tools.javac.comp.Flow.BaseAnalyzer.JumpKind
                public JCTree getTarget(JCTree jCTree) {
                    return ((JCTree.JCBreak) jCTree).target;
                }
            },
            CONTINUE(JCTree.Tag.CONTINUE) { // from class: com.sun.tools.javac.comp.Flow.BaseAnalyzer.JumpKind.2
                @Override // com.sun.tools.javac.comp.Flow.BaseAnalyzer.JumpKind
                public JCTree getTarget(JCTree jCTree) {
                    return ((JCTree.JCContinue) jCTree).target;
                }
            },
            YIELD(JCTree.Tag.YIELD) { // from class: com.sun.tools.javac.comp.Flow.BaseAnalyzer.JumpKind.3
                @Override // com.sun.tools.javac.comp.Flow.BaseAnalyzer.JumpKind
                public JCTree getTarget(JCTree jCTree) {
                    return ((JCTree.JCYield) jCTree).target;
                }
            };

            final JCTree.Tag treeTag;

            JumpKind(JCTree.Tag tag) {
                this.treeTag = tag;
            }

            public abstract JCTree getTarget(JCTree jCTree);

            /* synthetic */ JumpKind(JCTree.Tag tag, AnonymousClass1 anonymousClass1) {
                this(tag);
            }
        }
    }

    public class CaptureAnalyzer extends BaseAnalyzer {
        JCTree currentTree;
        Scope.WriteableScope declaredInsideGuard;

        public CaptureAnalyzer() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void analyzeTree(Env<AttrContext> env, JCTree jCTree, TreeMaker treeMaker) {
            try {
                Flow.this.attrEnv = env;
                Flow.this.make = treeMaker;
                this.pendingExits = new ListBuffer<>();
                scan(jCTree);
            } finally {
                this.pendingExits = null;
                Flow.this.make = null;
            }
        }

        public void checkEffectivelyFinal(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.VarSymbol varSymbol) {
            if (this.currentTree == null || varSymbol.owner.kind != Kinds.Kind.MTH || varSymbol.pos >= getCurrentTreeStartPosition()) {
                return;
            }
            switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[this.currentTree.getTag().ordinal()]) {
                case 8:
                case 9:
                case 10:
                    if ((varSymbol.flags() & 2199023255568L) == 0) {
                        reportEffectivelyFinalError(diagnosticPosition, varSymbol);
                    }
                    break;
            }
        }

        public int getCurrentTreeStartPosition() {
            JCTree jCTree = this.currentTree;
            return jCTree instanceof JCTree.JCCase ? ((JCTree.JCCase) jCTree).guard.getStartPosition() : jCTree.getStartPosition();
        }

        public void letInit(JCTree jCTree) {
            JCTree jCTreeSkipParens = TreeInfo.skipParens(jCTree);
            if (jCTreeSkipParens.hasTag(JCTree.Tag.IDENT) || jCTreeSkipParens.hasTag(JCTree.Tag.SELECT)) {
                Symbol symbol = TreeInfo.symbol(jCTreeSkipParens);
                JCTree jCTree2 = this.currentTree;
                if (jCTree2 != null) {
                    switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree2.getTag().ordinal()]) {
                        case 8:
                        case 10:
                            if (symbol.kind == Kinds.Kind.VAR && symbol.owner.kind == Kinds.Kind.MTH && ((Symbol.VarSymbol) symbol).pos < this.currentTree.getStartPosition()) {
                                reportEffectivelyFinalError(jCTreeSkipParens, symbol);
                                break;
                            }
                            break;
                        case 9:
                            if (!this.declaredInsideGuard.includes(symbol)) {
                                Flow.this.log.error(jCTreeSkipParens.pos(), CompilerProperties.Errors.CannotAssignNotDeclaredGuard(symbol));
                            }
                            break;
                    }
                }
            }
        }

        @Override // com.sun.tools.javac.comp.Flow.BaseAnalyzer
        public void markDead() {
        }

        public void reportEffectivelyFinalError(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
            JCDiagnostic.Fragment fragment;
            switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[this.currentTree.getTag().ordinal()]) {
                case 8:
                    fragment = CompilerProperties.Fragments.InnerCls;
                    break;
                case 9:
                    fragment = CompilerProperties.Fragments.Guard;
                    break;
                case 10:
                    fragment = CompilerProperties.Fragments.Lambda;
                    break;
                default:
                    pe1.a("Unexpected tree kind: ", this.currentTree.getTag());
                    return;
            }
            Flow.this.log.error(diagnosticPosition, CompilerProperties.Errors.CantRefNonEffectivelyFinalVar(symbol, Flow.this.diags.fragment(fragment)));
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssign(JCTree.JCAssign jCAssign) {
            JCTree.JCExpression jCExpressionSkipParens = TreeInfo.skipParens(jCAssign.lhs);
            if (!(jCExpressionSkipParens instanceof JCTree.JCIdent)) {
                scan(jCExpressionSkipParens);
            }
            scan(jCAssign.rhs);
            letInit(jCExpressionSkipParens);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
            scan(jCAssignOp.lhs);
            scan(jCAssignOp.rhs);
            letInit(jCAssignOp.lhs);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
            scan(jCBindingPattern.var);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitCase(JCTree.JCCase jCCase) {
            scan(jCCase.labels);
            if (jCCase.guard != null) {
                JCTree jCTree = this.currentTree;
                Scope.WriteableScope writeableScope = this.declaredInsideGuard;
                try {
                    this.currentTree = jCCase;
                    this.declaredInsideGuard = Scope.WriteableScope.create(Flow.this.attrEnv.enclClass.sym);
                    scan(jCCase.guard);
                    this.currentTree = jCTree;
                    this.declaredInsideGuard = writeableScope;
                } catch (Throwable th) {
                    this.currentTree = jCTree;
                    this.declaredInsideGuard = writeableScope;
                    throw th;
                }
            }
            scan(jCCase.stats);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            JCTree jCTree = this.currentTree;
            try {
                this.currentTree = jCClassDecl.sym.isDirectlyOrIndirectlyLocal() ? jCClassDecl : null;
                super.visitClassDef(jCClassDecl);
            } finally {
                this.currentTree = jCTree;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIdent(JCTree.JCIdent jCIdent) {
            Symbol symbol = jCIdent.sym;
            if (symbol.kind == Kinds.Kind.VAR) {
                checkEffectivelyFinal(jCIdent, (Symbol.VarSymbol) symbol);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            JCTree jCTree = this.currentTree;
            try {
                this.currentTree = jCLambda;
                super.visitLambda(jCLambda);
            } finally {
                this.currentTree = jCTree;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitRecordPattern(JCTree.JCRecordPattern jCRecordPattern) {
            scan(jCRecordPattern.deconstructor);
            scan(jCRecordPattern.nested);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTry(JCTree.JCTry jCTry) {
            Symbol symbol;
            for (JCTree jCTree : jCTry.resources) {
                if (!jCTree.hasTag(JCTree.Tag.VARDEF) && (symbol = TreeInfo.symbol(jCTree)) != null && (symbol.flags() & 2199023255568L) == 0) {
                    Flow.this.log.error(jCTree.pos(), CompilerProperties.Errors.TryWithResourcesExprEffectivelyFinalVar(symbol));
                }
            }
            super.visitTry(jCTry);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitUnary(JCTree.JCUnary jCUnary) {
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCUnary.getTag().ordinal()];
            if (i != 2 && i != 3 && i != 4 && i != 5) {
                scan(jCUnary.arg);
            } else {
                scan(jCUnary.arg);
                letInit(jCUnary.arg);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            Scope.WriteableScope writeableScope = this.declaredInsideGuard;
            if (writeableScope != null) {
                writeableScope.enter(jCVariableDecl.sym);
            }
            super.visitVarDef(jCVariableDecl);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitYield(JCTree.JCYield jCYield) {
            scan(jCYield.value);
        }

        public void analyzeTree(Env<AttrContext> env, TreeMaker treeMaker) {
            analyzeTree(env, env.tree, treeMaker);
        }
    }

    public class AliveAnalyzer extends BaseAnalyzer {
        private Liveness alive;

        public AliveAnalyzer() {
        }

        public static /* synthetic */ void a(AliveAnalyzer aliveAnalyzer, JCTree jCTree) {
            aliveAnalyzer.scanDef(jCTree);
            aliveAnalyzer.clearPendingExits(false);
        }

        public static /* synthetic */ void b(AliveAnalyzer aliveAnalyzer, JCTree jCTree) {
            aliveAnalyzer.scanDef(jCTree);
            aliveAnalyzer.clearPendingExits(false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void clearPendingExits(boolean z) {
            List list = this.pendingExits.toList();
            this.pendingExits = new ListBuffer<>();
            while (list.nonEmpty()) {
                BaseAnalyzer.PendingExit pendingExit = (BaseAnalyzer.PendingExit) list.head;
                list = list.tail;
                Assert.check((z && pendingExit.tree.hasTag(JCTree.Tag.RETURN)) || Flow.this.log.hasErrorOn(pendingExit.tree.pos()));
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void analyzeTree(Env<AttrContext> env, JCTree jCTree, TreeMaker treeMaker) {
            try {
                Flow.this.attrEnv = env;
                Flow.this.make = treeMaker;
                this.pendingExits = new ListBuffer<>();
                this.alive = Liveness.ALIVE;
                scan(jCTree);
            } finally {
                this.pendingExits = null;
                Flow.this.make = null;
            }
        }

        @Override // com.sun.tools.javac.comp.Flow.BaseAnalyzer
        public void markDead() {
            this.alive = Liveness.DEAD;
        }

        public void scanDef(JCTree jCTree) {
            scanStat(jCTree);
            if (jCTree != null && jCTree.hasTag(JCTree.Tag.BLOCK) && this.alive == Liveness.DEAD) {
                Flow.this.log.error(jCTree.pos(), CompilerProperties.Errors.InitializerMustBeAbleToCompleteNormally);
            }
        }

        public void scanStat(JCTree jCTree) {
            if (this.alive == Liveness.DEAD && jCTree != null) {
                Flow.this.log.error(jCTree.pos(), CompilerProperties.Errors.UnreachableStmt);
                if (!jCTree.hasTag(JCTree.Tag.SKIP)) {
                    this.alive = Liveness.RECOVERY;
                }
            }
            scan(jCTree);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void scanStats(List<? extends JCTree.JCStatement> list) {
            if (list != null) {
                for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                    scanStat((JCTree) list2.head);
                }
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
            scan(jCMethodInvocation.meth);
            scan(jCMethodInvocation.args);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBlock(JCTree.JCBlock jCBlock) {
            scanStats(jCBlock.stats);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBreak(JCTree.JCBreak jCBreak) {
            recordExit(new BaseAnalyzer.PendingExit(jCBreak));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            if (jCClassDecl.sym == null) {
                return;
            }
            Liveness liveness = this.alive;
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            try {
                for (List list = jCClassDecl.defs; list.nonEmpty(); list = list.tail) {
                    if (((JCTree) list.head).hasTag(JCTree.Tag.CLASSDEF)) {
                        scan((JCTree) list.head);
                    }
                }
                forEachInitializer(jCClassDecl, true, new Consumer() { // from class: com.sun.tools.javac.comp.x0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Flow.AliveAnalyzer.b(this.b, (JCTree) obj);
                    }
                });
                forEachInitializer(jCClassDecl, false, new Consumer() { // from class: com.sun.tools.javac.comp.y0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Flow.AliveAnalyzer.a(this.b, (JCTree) obj);
                    }
                });
                for (List list2 = jCClassDecl.defs; list2.nonEmpty(); list2 = list2.tail) {
                    if (((JCTree) list2.head).hasTag(JCTree.Tag.METHODDEF)) {
                        scan((JCTree) list2.head);
                    }
                }
            } finally {
                this.pendingExits = listBuffer;
                this.alive = liveness;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitContinue(JCTree.JCContinue jCContinue) {
            recordExit(new BaseAnalyzer.PendingExit(jCContinue));
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            scanStat(jCDoWhileLoop.body);
            this.alive = this.alive.or(resolveContinues(jCDoWhileLoop));
            scan(jCDoWhileLoop.cond);
            Liveness livenessAnd = this.alive.and(!jCDoWhileLoop.cond.type.isTrue());
            this.alive = livenessAnd;
            this.alive = livenessAnd.or(resolveBreaks(jCDoWhileLoop, listBuffer));
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitForLoop(JCTree.JCForLoop jCForLoop) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            scanStats(jCForLoop.init);
            this.pendingExits = new ListBuffer<>();
            JCTree.JCExpression jCExpression = jCForLoop.cond;
            if (jCExpression != null) {
                scan(jCExpression);
                this.alive = Liveness.from(!jCForLoop.cond.type.isFalse());
            } else {
                this.alive = Liveness.ALIVE;
            }
            scanStat(jCForLoop.body);
            this.alive = this.alive.or(resolveContinues(jCForLoop));
            scan(jCForLoop.step);
            Liveness livenessResolveBreaks = resolveBreaks(jCForLoop, listBuffer);
            JCTree.JCExpression jCExpression2 = jCForLoop.cond;
            this.alive = livenessResolveBreaks.or((jCExpression2 == null || jCExpression2.type.isTrue()) ? false : true);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
            visitVarDef(jCEnhancedForLoop.var);
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            scan(jCEnhancedForLoop.expr);
            this.pendingExits = new ListBuffer<>();
            scanStat(jCEnhancedForLoop.body);
            this.alive = this.alive.or(resolveContinues(jCEnhancedForLoop));
            resolveBreaks(jCEnhancedForLoop, listBuffer);
            this.alive = Liveness.ALIVE;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIf(JCTree.JCIf jCIf) {
            scan(jCIf.cond);
            scanStat(jCIf.thenpart);
            JCTree.JCStatement jCStatement = jCIf.elsepart;
            if (jCStatement == null) {
                this.alive = Liveness.ALIVE;
                return;
            }
            Liveness liveness = this.alive;
            this.alive = Liveness.ALIVE;
            scanStat(jCStatement);
            this.alive = this.alive.or(liveness);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLabelled(JCTree.JCLabeledStatement jCLabeledStatement) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            scanStat(jCLabeledStatement.body);
            this.alive = this.alive.or(resolveBreaks(jCLabeledStatement, listBuffer));
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            Type type = jCLambda.type;
            if (type == null || !type.isErroneous()) {
                ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
                Liveness liveness = this.alive;
                try {
                    this.pendingExits = new ListBuffer<>();
                    this.alive = Liveness.ALIVE;
                    scanStat(jCLambda.body);
                    jCLambda.canCompleteNormally = this.alive != Liveness.DEAD;
                } finally {
                    this.pendingExits = listBuffer;
                    this.alive = liveness;
                }
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            if (jCMethodDecl.body == null) {
                return;
            }
            Assert.check(this.pendingExits.isEmpty());
            Liveness liveness = Liveness.ALIVE;
            this.alive = liveness;
            scanStat(jCMethodDecl.body);
            Liveness liveness2 = this.alive;
            jCMethodDecl.completesNormally = liveness2 != Liveness.DEAD;
            if (liveness2 == liveness && !jCMethodDecl.sym.type.mo73getReturnType().hasTag(TypeTag.VOID)) {
                Flow.this.log.error(TreeInfo.diagEndPos(jCMethodDecl.body), CompilerProperties.Errors.MissingRetStmt);
            }
            clearPendingExits(true);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            scan(jCNewClass.encl);
            scan(jCNewClass.args);
            JCTree.JCClassDecl jCClassDecl = jCNewClass.def;
            if (jCClassDecl != null) {
                scan(jCClassDecl);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitReturn(JCTree.JCReturn jCReturn) {
            scan(jCReturn.expr);
            recordExit(new BaseAnalyzer.PendingExit(jCReturn));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitch(JCTree.JCSwitch jCSwitch) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            scan(jCSwitch.selector);
            boolean zExpectedExhaustive = TreeInfo.expectedExhaustive(jCSwitch);
            for (List list = jCSwitch.cases; list.nonEmpty(); list = list.tail) {
                this.alive = Liveness.ALIVE;
                JCTree.JCCase jCCase = (JCTree.JCCase) list.head;
                Iterator<JCTree.JCCaseLabel> it = jCCase.labels.iterator();
                while (it.hasNext()) {
                    scan(it.next());
                }
                scanStats(jCCase.stats);
                Liveness liveness = this.alive;
                Liveness liveness2 = Liveness.DEAD;
                if (liveness != liveness2 && jCCase.caseKind == JCTree.JCCase.RULE) {
                    scanSyntheticBreak(Flow.this.make, jCSwitch);
                    this.alive = liveness2;
                }
                if (this.alive == Liveness.ALIVE && jCCase.stats.nonEmpty() && list.tail.nonEmpty()) {
                    Flow.this.log.warning(((JCTree.JCCase) list.tail.head).pos(), CompilerProperties.LintWarnings.PossibleFallThroughIntoCase);
                }
            }
            boolean z = jCSwitch.hasUnconditionalPattern || TreeInfo.isErrorEnumSwitch(jCSwitch.selector, jCSwitch.cases);
            jCSwitch.isExhaustive = z;
            if (zExpectedExhaustive) {
                boolean zExhausts = z | Flow.this.exhaustiveness.exhausts(jCSwitch.selector, jCSwitch.cases);
                jCSwitch.isExhaustive = zExhausts;
                if (!zExhausts) {
                    Flow.this.log.error(jCSwitch, CompilerProperties.Errors.NotExhaustiveStatement);
                }
            }
            if (!jCSwitch.hasUnconditionalPattern && !zExpectedExhaustive) {
                this.alive = Liveness.ALIVE;
            }
            this.alive = this.alive.or(resolveBreaks(jCSwitch, listBuffer));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            scan(jCSwitchExpression.selector);
            Liveness liveness = this.alive;
            for (List list = jCSwitchExpression.cases; list.nonEmpty(); list = list.tail) {
                this.alive = Liveness.ALIVE;
                JCTree.JCCase jCCase = (JCTree.JCCase) list.head;
                Iterator<JCTree.JCCaseLabel> it = jCCase.labels.iterator();
                while (it.hasNext()) {
                    scan(it.next());
                }
                scanStats(jCCase.stats);
                if (this.alive == Liveness.ALIVE) {
                    if (jCCase.caseKind == JCTree.JCCase.RULE) {
                        Flow.this.log.error(TreeInfo.diagEndPos(jCCase.body), CompilerProperties.Errors.RuleCompletesNormally);
                    } else if (list.tail.isEmpty()) {
                        Flow.this.log.error(TreeInfo.diagEndPos(jCSwitchExpression), CompilerProperties.Errors.SwitchExpressionCompletesNormally);
                    }
                }
            }
            if (jCSwitchExpression.hasUnconditionalPattern || TreeInfo.isErrorEnumSwitch(jCSwitchExpression.selector, jCSwitchExpression.cases)) {
                jCSwitchExpression.isExhaustive = true;
            } else {
                jCSwitchExpression.isExhaustive = Flow.this.exhaustiveness.exhausts(jCSwitchExpression.selector, jCSwitchExpression.cases);
            }
            if (!jCSwitchExpression.isExhaustive) {
                Flow.this.log.error(jCSwitchExpression, CompilerProperties.Errors.NotExhaustive);
            }
            this.alive = liveness;
            this.alive = liveness.or(resolveYields(jCSwitchExpression, listBuffer));
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitThrow(JCTree.JCThrow jCThrow) {
            scan(jCThrow.expr);
            markDead();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTry(JCTree.JCTry jCTry) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            for (JCTree jCTree : jCTry.resources) {
                if (jCTree instanceof JCTree.JCVariableDecl) {
                    visitVarDef((JCTree.JCVariableDecl) jCTree);
                } else {
                    if (!(jCTree instanceof JCTree.JCExpression)) {
                        x01.a(jCTry);
                        return;
                    }
                    scan((JCTree.JCExpression) jCTree);
                }
            }
            scanStat(jCTry.body);
            Liveness livenessOr = this.alive;
            for (List list = jCTry.catchers; list.nonEmpty(); list = list.tail) {
                this.alive = Liveness.ALIVE;
                scan(((JCTree.JCCatch) list.head).param);
                scanStat(((JCTree.JCCatch) list.head).body);
                livenessOr = livenessOr.or(this.alive);
            }
            JCTree.JCBlock jCBlock = jCTry.finalizer;
            if (jCBlock == null) {
                this.alive = livenessOr;
                ListBuffer<BaseAnalyzer.PendingExit> listBuffer2 = this.pendingExits;
                this.pendingExits = listBuffer;
                while (listBuffer2.nonEmpty()) {
                    this.pendingExits.append(listBuffer2.next());
                }
                return;
            }
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer3 = this.pendingExits;
            this.pendingExits = listBuffer;
            this.alive = Liveness.ALIVE;
            scanStat(jCBlock);
            Liveness liveness = this.alive;
            Liveness liveness2 = Liveness.DEAD;
            jCTry.finallyCanCompleteNormally = liveness != liveness2;
            if (liveness == liveness2) {
                Flow.this.log.warning(TreeInfo.diagEndPos(jCTry.finalizer), CompilerProperties.LintWarnings.FinallyCannotComplete);
                return;
            }
            while (listBuffer3.nonEmpty()) {
                this.pendingExits.append(listBuffer3.next());
            }
            this.alive = livenessOr;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            scan(jCVariableDecl.init);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            scan(jCWhileLoop.cond);
            this.alive = Liveness.from(!jCWhileLoop.cond.type.isFalse());
            scanStat(jCWhileLoop.body);
            this.alive = this.alive.or(resolveContinues(jCWhileLoop));
            this.alive = resolveBreaks(jCWhileLoop, listBuffer).or(!jCWhileLoop.cond.type.isTrue());
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitYield(JCTree.JCYield jCYield) {
            scan(jCYield.value);
            recordExit(new BaseAnalyzer.PendingExit(jCYield));
        }

        public void analyzeTree(Env<AttrContext> env, TreeMaker treeMaker) {
            analyzeTree(env, env.tree, treeMaker);
        }
    }

    public class AssignAnalyzer extends BaseAnalyzer {
        JCTree.JCClassDecl classDef;
        int firstadr;
        private boolean isConstructor;
        protected int nextadr;
        protected int returnadr;
        int startPos;
        Scope.WriteableScope unrefdResources;
        protected JCTree.JCVariableDecl[] vardecls;
        FlowKind flowKind = FlowKind.NORMAL;
        final Bits inits = new Bits();
        final Bits uninits = new Bits();
        final Bits uninitsTry = new Bits();
        final Bits initsWhenTrue = new Bits(true);
        final Bits initsWhenFalse = new Bits(true);
        final Bits uninitsWhenTrue = new Bits(true);
        final Bits uninitsWhenFalse = new Bits(true);

        public class AssignPendingExit extends BaseAnalyzer.PendingExit {
            final Bits exit_inits;
            final Bits exit_uninits;
            final Bits inits;
            final Bits uninits;

            public AssignPendingExit(JCTree jCTree, Bits bits, Bits bits2) {
                super(jCTree);
                Bits bits3 = new Bits(true);
                this.exit_inits = bits3;
                Bits bits4 = new Bits(true);
                this.exit_uninits = bits4;
                this.inits = bits;
                this.uninits = bits2;
                bits3.assign(bits);
                bits4.assign(bits2);
            }

            @Override // com.sun.tools.javac.comp.Flow.BaseAnalyzer.PendingExit
            public void resolveJump() {
                this.inits.andSet(this.exit_inits);
                this.uninits.andSet(this.exit_uninits);
            }
        }

        public AssignAnalyzer() {
        }

        public static /* synthetic */ void a(AssignAnalyzer assignAnalyzer, JCTree jCTree) {
            assignAnalyzer.scan(jCTree);
            assignAnalyzer.clearPendingExits(false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void addVars(List<JCTree.JCStatement> list, Bits bits, Bits bits2) {
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                JCTree jCTree = (JCTree) list2.head;
                if (jCTree.hasTag(JCTree.Tag.VARDEF)) {
                    int i = ((JCTree.JCVariableDecl) jCTree).sym.adr;
                    bits.excl(i);
                    bits2.incl(i);
                }
            }
        }

        public static /* synthetic */ void b(AssignAnalyzer assignAnalyzer, JCTree jCTree) {
            assignAnalyzer.scan(jCTree);
            assignAnalyzer.clearPendingExits(false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void clearPendingExits(boolean z) {
            List list = this.pendingExits.toList();
            this.pendingExits = new ListBuffer<>();
            while (list.nonEmpty()) {
                BaseAnalyzer.PendingExit pendingExit = (BaseAnalyzer.PendingExit) list.head;
                list = list.tail;
                Assert.check((z && pendingExit.tree.hasTag(JCTree.Tag.RETURN)) || Flow.this.log.hasErrorOn(pendingExit.tree.pos()), pendingExit.tree);
                if (z && this.isConstructor) {
                    Assert.check(pendingExit instanceof AssignPendingExit);
                    this.inits.assign(((AssignPendingExit) pendingExit).exit_inits);
                    for (int i = this.firstadr; i < this.nextadr; i++) {
                        checkInit(pendingExit.tree.pos(), this.vardecls[i].sym);
                    }
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void handleSwitch(JCTree jCTree, JCTree.JCExpression jCExpression, List<JCTree.JCCase> list, boolean z) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            int i = this.nextadr;
            scanExpr(jCExpression);
            Bits bits = new Bits(this.inits);
            Bits bits2 = new Bits(this.uninits);
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                this.inits.assign(bits);
                Bits bits3 = this.uninits;
                bits3.assign(bits3.andSet(bits2));
                JCTree.JCCase jCCase = (JCTree.JCCase) list2.head;
                Iterator<JCTree.JCCaseLabel> it = jCCase.labels.iterator();
                while (it.hasNext()) {
                    scanPattern(it.next());
                }
                scan(jCCase.guard);
                if (this.inits.isReset()) {
                    this.inits.assign(this.initsWhenTrue);
                    this.uninits.assign(this.uninitsWhenTrue);
                }
                scan(jCCase.stats);
                if (jCCase.completesNormally && jCCase.caseKind == JCTree.JCCase.RULE) {
                    scanSyntheticBreak(Flow.this.make, jCTree);
                }
                addVars(jCCase.stats, bits, bits2);
            }
            if (!z) {
                if (jCTree.hasTag(JCTree.Tag.SWITCH_EXPRESSION)) {
                    markDead();
                } else if (jCTree.hasTag(JCTree.Tag.SWITCH) && !TreeInfo.expectedExhaustive((JCTree.JCSwitch) jCTree)) {
                    this.inits.assign(bits);
                    Bits bits4 = this.uninits;
                    bits4.assign(bits4.andSet(bits2));
                }
            }
            if (jCTree.hasTag(JCTree.Tag.SWITCH_EXPRESSION)) {
                resolveYields(jCTree, listBuffer);
            } else {
                resolveBreaks(jCTree, listBuffer);
            }
            this.nextadr = i;
        }

        private void resetBits(Bits... bitsArr) {
            for (Bits bits : bitsArr) {
                bits.reset();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void analyzeTree(Env<?> env, JCTree jCTree, TreeMaker treeMaker) {
            try {
                this.startPos = jCTree.pos().getStartPosition();
                if (this.vardecls != null) {
                    int i = 0;
                    while (true) {
                        JCTree.JCVariableDecl[] jCVariableDeclArr = this.vardecls;
                        if (i >= jCVariableDeclArr.length) {
                            break;
                        }
                        jCVariableDeclArr[i] = null;
                        i++;
                    }
                } else {
                    this.vardecls = new JCTree.JCVariableDecl[32];
                }
                this.firstadr = 0;
                this.nextadr = 0;
                Flow.this.make = treeMaker;
                this.pendingExits = new ListBuffer<>();
                this.classDef = null;
                this.unrefdResources = Scope.WriteableScope.create(env.enclClass.sym);
                scan(jCTree);
                this.startPos = -1;
                resetBits(this.inits, this.uninits, this.uninitsTry, this.initsWhenTrue, this.initsWhenFalse, this.uninitsWhenTrue, this.uninitsWhenFalse);
                if (jCVariableDeclArr != null) {
                    while (true) {
                    }
                }
            } finally {
                this.startPos = -1;
                resetBits(this.inits, this.uninits, this.uninitsTry, this.initsWhenTrue, this.initsWhenFalse, this.uninitsWhenTrue, this.uninitsWhenFalse);
                if (this.vardecls != null) {
                    int i2 = 0;
                    while (true) {
                        JCTree.JCVariableDecl[] jCVariableDeclArr2 = this.vardecls;
                        if (i2 >= jCVariableDeclArr2.length) {
                            break;
                        }
                        jCVariableDeclArr2[i2] = null;
                        i2++;
                    }
                }
                this.firstadr = 0;
                this.nextadr = 0;
                Flow.this.make = null;
                this.pendingExits = null;
                this.classDef = null;
                this.unrefdResources = null;
            }
        }

        public void checkInit(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.VarSymbol varSymbol, JCDiagnostic.Error error) {
            if ((varSymbol.adr >= this.firstadr || varSymbol.owner.kind != Kinds.Kind.TYP) && trackable(varSymbol) && !this.inits.isMember(varSymbol.adr) && (varSymbol.flags_field & Flags.CLASH) == 0) {
                Flow.this.log.error(diagnosticPosition, error);
                this.inits.incl(varSymbol.adr);
            }
        }

        public void initParam(JCTree.JCVariableDecl jCVariableDecl) {
            this.inits.incl(jCVariableDecl.sym.adr);
            this.uninits.excl(jCVariableDecl.sym.adr);
        }

        public boolean isFinalUninitializedField(Symbol.VarSymbol varSymbol) {
            return varSymbol.owner.kind == Kinds.Kind.TYP && (varSymbol.flags() & 8590196752L) == 16 && this.classDef.sym.isEnclosedBy((Symbol.ClassSymbol) varSymbol.owner);
        }

        public void letInit(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.VarSymbol varSymbol) {
            if (varSymbol.adr < this.firstadr || !trackable(varSymbol)) {
                if ((varSymbol.flags() & 16) != 0) {
                    Flow.this.log.error(diagnosticPosition, CompilerProperties.Errors.VarMightAlreadyBeAssigned(varSymbol));
                    return;
                }
                return;
            }
            if ((varSymbol.flags() & Flags.EFFECTIVELY_FINAL) != 0) {
                if (this.uninits.isMember(varSymbol.adr)) {
                    uninit(varSymbol);
                } else {
                    varSymbol.flags_field &= -2199023255553L;
                }
            } else if ((varSymbol.flags() & 16) != 0) {
                if ((varSymbol.flags() & 8589934592L) != 0) {
                    long jFlags = varSymbol.flags() & Flags.UNION;
                    Flow flow = Flow.this;
                    if (jFlags != 0) {
                        flow.log.error(diagnosticPosition, CompilerProperties.Errors.MulticatchParameterMayNotBeAssigned(varSymbol));
                    } else {
                        flow.log.error(diagnosticPosition, CompilerProperties.Errors.FinalParameterMayNotBeAssigned(varSymbol));
                    }
                } else if (this.uninits.isMember(varSymbol.adr)) {
                    uninit(varSymbol);
                } else {
                    Flow.this.log.error(diagnosticPosition, Flow.this.diags.errorKey(this.flowKind.errKey, varSymbol));
                }
            }
            this.inits.incl(varSymbol.adr);
        }

        @Override // com.sun.tools.javac.comp.Flow.BaseAnalyzer
        public void markDead() {
            this.inits.inclRange(this.returnadr, this.nextadr);
            this.uninits.inclRange(this.returnadr, this.nextadr);
        }

        public void merge() {
            this.inits.assign(this.initsWhenFalse.andSet(this.initsWhenTrue));
            this.uninits.assign(this.uninitsWhenFalse.andSet(this.uninitsWhenTrue));
        }

        public void newVar(JCTree.JCVariableDecl jCVariableDecl) {
            Symbol.VarSymbol varSymbol = jCVariableDecl.sym;
            this.vardecls = (JCTree.JCVariableDecl[]) ArrayUtils.ensureCapacity(this.vardecls, this.nextadr);
            if ((varSymbol.flags() & 16) == 0) {
                varSymbol.flags_field |= Flags.EFFECTIVELY_FINAL;
            }
            int i = this.nextadr;
            varSymbol.adr = i;
            this.vardecls[i] = jCVariableDecl;
            this.inits.excl(i);
            this.uninits.incl(this.nextadr);
            this.nextadr++;
        }

        public void referenced(Symbol symbol) {
            this.unrefdResources.remove(symbol);
        }

        @Override // com.sun.tools.javac.comp.Flow.BaseAnalyzer, com.sun.tools.javac.tree.TreeScanner
        public /* bridge */ /* synthetic */ void scan(JCTree jCTree) {
            super.scan(jCTree);
        }

        public void scanCond(JCTree jCTree) {
            if (jCTree.type.isFalse()) {
                if (this.inits.isReset()) {
                    merge();
                }
                this.initsWhenTrue.assign(this.inits);
                this.initsWhenTrue.inclRange(this.firstadr, this.nextadr);
                this.uninitsWhenTrue.assign(this.uninits);
                this.uninitsWhenTrue.inclRange(this.firstadr, this.nextadr);
                this.initsWhenFalse.assign(this.inits);
                this.uninitsWhenFalse.assign(this.uninits);
            } else if (jCTree.type.isTrue()) {
                if (this.inits.isReset()) {
                    merge();
                }
                this.initsWhenFalse.assign(this.inits);
                this.initsWhenFalse.inclRange(this.firstadr, this.nextadr);
                this.uninitsWhenFalse.assign(this.uninits);
                this.uninitsWhenFalse.inclRange(this.firstadr, this.nextadr);
                this.initsWhenTrue.assign(this.inits);
                this.uninitsWhenTrue.assign(this.uninits);
            } else {
                scan(jCTree);
                if (!this.inits.isReset()) {
                    split(jCTree.type != Flow.this.syms.unknownType);
                }
            }
            if (jCTree.type != Flow.this.syms.unknownType) {
                resetBits(this.inits, this.uninits);
            }
        }

        public void scanExpr(JCTree jCTree) {
            if (jCTree != null) {
                scan(jCTree);
                if (this.inits.isReset()) {
                    merge();
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void scanExprs(List<? extends JCTree.JCExpression> list) {
            if (list != null) {
                for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                    scanExpr((JCTree) list2.head);
                }
            }
        }

        public void scanPattern(JCTree jCTree) {
            scan(jCTree);
        }

        public void split(boolean z) {
            this.initsWhenFalse.assign(this.inits);
            this.uninitsWhenFalse.assign(this.uninits);
            this.initsWhenTrue.assign(this.inits);
            this.uninitsWhenTrue.assign(this.uninits);
            if (z) {
                resetBits(this.inits, this.uninits);
            }
        }

        public boolean trackable(Symbol.VarSymbol varSymbol) {
            if (varSymbol.pos < this.startPos) {
                return false;
            }
            Kinds.Kind kind = varSymbol.owner.kind;
            return kind == Kinds.Kind.MTH || kind == Kinds.Kind.VAR || isFinalUninitializedField(varSymbol);
        }

        public void uninit(Symbol.VarSymbol varSymbol) {
            boolean zIsMember = this.inits.isMember(varSymbol.adr);
            Bits bits = this.uninits;
            if (zIsMember) {
                bits.excl(varSymbol.adr);
            } else {
                bits.excl(varSymbol.adr);
                this.uninitsTry.excl(varSymbol.adr);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
            jCAnnotatedType.underlyingType.accept(this);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
            scanExpr(jCMethodInvocation.meth);
            scanExprs(jCMethodInvocation.args);
            if (this.isConstructor) {
                Name name = TreeInfo.name(jCMethodInvocation.meth);
                if (name == Flow.this.names._super) {
                    forEachInitializer(this.classDef, false, new Consumer() { // from class: ri5
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            Flow.AssignAnalyzer.a(this.b, (JCTree) obj);
                        }
                    });
                    return;
                }
                if (name == Flow.this.names._this) {
                    for (int i = this.firstadr; i < this.nextadr; i++) {
                        Symbol.VarSymbol varSymbol = this.vardecls[i].sym;
                        if (isFinalUninitializedField(varSymbol) && !varSymbol.isStatic()) {
                            letInit(jCMethodInvocation.pos(), varSymbol);
                        }
                    }
                }
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssert(JCTree.JCAssert jCAssert) {
            Bits bits = new Bits(this.inits);
            Bits bits2 = new Bits(this.uninits);
            scanCond(jCAssert.cond);
            bits2.andSet(this.uninitsWhenTrue);
            if (jCAssert.detail != null) {
                this.inits.assign(this.initsWhenFalse);
                this.uninits.assign(this.uninitsWhenFalse);
                scanExpr(jCAssert.detail);
            }
            this.inits.assign(bits);
            this.uninits.assign(bits2);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssign(JCTree.JCAssign jCAssign) {
            if (!TreeInfo.isIdentOrThisDotIdent(jCAssign.lhs)) {
                scanExpr(jCAssign.lhs);
            }
            scanExpr(jCAssign.rhs);
            letInit(jCAssign.lhs);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
            scanExpr(jCAssignOp.lhs);
            scanExpr(jCAssignOp.rhs);
            letInit(jCAssignOp.lhs);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBinary(JCTree.JCBinary jCBinary) {
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCBinary.getTag().ordinal()];
            if (i == 6) {
                scanCond(jCBinary.lhs);
                Bits bits = new Bits(this.initsWhenFalse);
                Bits bits2 = new Bits(this.uninitsWhenFalse);
                this.inits.assign(this.initsWhenTrue);
                this.uninits.assign(this.uninitsWhenTrue);
                scanCond(jCBinary.rhs);
                this.initsWhenFalse.andSet(bits);
                this.uninitsWhenFalse.andSet(bits2);
                return;
            }
            JCTree.JCExpression jCExpression = jCBinary.lhs;
            if (i != 7) {
                scanExpr(jCExpression);
                scanExpr(jCBinary.rhs);
                return;
            }
            scanCond(jCExpression);
            Bits bits3 = new Bits(this.initsWhenTrue);
            Bits bits4 = new Bits(this.uninitsWhenTrue);
            this.inits.assign(this.initsWhenFalse);
            this.uninits.assign(this.uninitsWhenFalse);
            scanCond(jCBinary.rhs);
            this.initsWhenTrue.andSet(bits3);
            this.uninitsWhenTrue.andSet(bits4);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
            scan(jCBindingPattern.var);
            initParam(jCBindingPattern.var);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBlock(JCTree.JCBlock jCBlock) {
            int i = this.nextadr;
            scan(jCBlock.stats);
            this.nextadr = i;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBreak(JCTree.JCBreak jCBreak) {
            recordExit(new AssignPendingExit(jCBreak, this.inits, this.uninits));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            if (jCClassDecl.sym == null) {
                return;
            }
            JCTree.JCClassDecl jCClassDecl2 = this.classDef;
            int i = this.firstadr;
            int i2 = this.nextadr;
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            if (jCClassDecl.name != Flow.this.names.empty) {
                this.firstadr = this.nextadr;
            }
            this.classDef = jCClassDecl;
            try {
                for (List list = jCClassDecl.defs; list.nonEmpty(); list = list.tail) {
                    if (((JCTree) list.head).hasTag(JCTree.Tag.VARDEF)) {
                        JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) list.head;
                        if ((8 & jCVariableDecl.mods.flags) != 0 && trackable(jCVariableDecl.sym)) {
                            newVar(jCVariableDecl);
                        }
                    }
                }
                forEachInitializer(jCClassDecl, true, new Consumer() { // from class: qi5
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Flow.AssignAnalyzer.b(this.b, (JCTree) obj);
                    }
                });
                for (int i3 = this.firstadr; i3 < this.nextadr; i3++) {
                    JCTree.JCVariableDecl jCVariableDecl2 = this.vardecls[i3];
                    Symbol.VarSymbol varSymbol = jCVariableDecl2.sym;
                    if (varSymbol.owner == this.classDef.sym && varSymbol.isStatic()) {
                        checkInit(TreeInfo.diagnosticPositionFor(varSymbol, jCVariableDecl2), varSymbol);
                    }
                }
                for (List list2 = jCClassDecl.defs; list2.nonEmpty(); list2 = list2.tail) {
                    if (((JCTree) list2.head).hasTag(JCTree.Tag.VARDEF)) {
                        JCTree.JCVariableDecl jCVariableDecl3 = (JCTree.JCVariableDecl) list2.head;
                        if ((jCVariableDecl3.mods.flags & 8) == 0 && trackable(jCVariableDecl3.sym)) {
                            newVar(jCVariableDecl3);
                        }
                    }
                }
                for (List list3 = jCClassDecl.defs; list3.nonEmpty(); list3 = list3.tail) {
                    if (((JCTree) list3.head).hasTag(JCTree.Tag.METHODDEF)) {
                        scan((JCTree) list3.head);
                    }
                }
                for (List list4 = jCClassDecl.defs; list4.nonEmpty(); list4 = list4.tail) {
                    if (((JCTree) list4.head).hasTag(JCTree.Tag.CLASSDEF)) {
                        scan((JCTree) list4.head);
                    }
                }
            } finally {
                this.pendingExits = listBuffer;
                this.nextadr = i2;
                this.firstadr = i;
                this.classDef = jCClassDecl2;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitConditional(JCTree.JCConditional jCConditional) {
            scanCond(jCConditional.cond);
            Bits bits = new Bits(this.initsWhenFalse);
            Bits bits2 = new Bits(this.uninitsWhenFalse);
            this.inits.assign(this.initsWhenTrue);
            this.uninits.assign(this.uninitsWhenTrue);
            Type type = jCConditional.truepart.type;
            TypeTag typeTag = TypeTag.BOOLEAN;
            if (!type.hasTag(typeTag) || !jCConditional.falsepart.type.hasTag(typeTag)) {
                scanExpr(jCConditional.truepart);
                Bits bits3 = new Bits(this.inits);
                Bits bits4 = new Bits(this.uninits);
                this.inits.assign(bits);
                this.uninits.assign(bits2);
                scanExpr(jCConditional.falsepart);
                this.inits.andSet(bits3);
                this.uninits.andSet(bits4);
                return;
            }
            scanCond(jCConditional.truepart);
            Bits bits5 = new Bits(this.initsWhenTrue);
            Bits bits6 = new Bits(this.initsWhenFalse);
            Bits bits7 = new Bits(this.uninitsWhenTrue);
            Bits bits8 = new Bits(this.uninitsWhenFalse);
            this.inits.assign(bits);
            this.uninits.assign(bits2);
            scanCond(jCConditional.falsepart);
            this.initsWhenTrue.andSet(bits5);
            this.initsWhenFalse.andSet(bits6);
            this.uninitsWhenTrue.andSet(bits7);
            this.uninitsWhenFalse.andSet(bits8);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitContinue(JCTree.JCContinue jCContinue) {
            recordExit(new AssignPendingExit(jCContinue, this.inits, this.uninits));
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            FlowKind flowKind = this.flowKind;
            this.flowKind = FlowKind.NORMAL;
            Bits bits = new Bits(true);
            Bits bits2 = new Bits(true);
            this.pendingExits = new ListBuffer<>();
            int i = Flow.this.log.nerrors;
            while (true) {
                Bits bits3 = new Bits(this.uninits);
                bits3.excludeFrom(this.nextadr);
                scan(jCDoWhileLoop.body);
                resolveContinues(jCDoWhileLoop);
                scanCond(jCDoWhileLoop.cond);
                if (!this.flowKind.isFinal()) {
                    bits.assign(this.initsWhenFalse);
                    bits2.assign(this.uninitsWhenFalse);
                }
                if (Flow.this.log.nerrors != i || this.flowKind.isFinal() || new Bits(bits3).diffSet(this.uninitsWhenTrue).nextBit(this.firstadr) == -1) {
                    break;
                }
                this.inits.assign(this.initsWhenTrue);
                this.uninits.assign(bits3.andSet(this.uninitsWhenTrue));
                this.flowKind = FlowKind.SPECULATIVE_LOOP;
            }
            this.flowKind = flowKind;
            this.inits.assign(bits);
            this.uninits.assign(bits2);
            resolveBreaks(jCDoWhileLoop, listBuffer);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitForLoop(JCTree.JCForLoop jCForLoop) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            FlowKind flowKind = this.flowKind;
            this.flowKind = FlowKind.NORMAL;
            int i = this.nextadr;
            scan(jCForLoop.init);
            Bits bits = new Bits(true);
            Bits bits2 = new Bits(true);
            this.pendingExits = new ListBuffer<>();
            int i2 = Flow.this.log.nerrors;
            while (true) {
                Bits bits3 = new Bits(this.uninits);
                bits3.excludeFrom(this.nextadr);
                JCTree.JCExpression jCExpression = jCForLoop.cond;
                if (jCExpression != null) {
                    scanCond(jCExpression);
                    if (!this.flowKind.isFinal()) {
                        bits.assign(this.initsWhenFalse);
                        bits2.assign(this.uninitsWhenFalse);
                    }
                    this.inits.assign(this.initsWhenTrue);
                    this.uninits.assign(this.uninitsWhenTrue);
                } else if (!this.flowKind.isFinal()) {
                    bits.assign(this.inits);
                    bits.inclRange(this.firstadr, this.nextadr);
                    bits2.assign(this.uninits);
                    bits2.inclRange(this.firstadr, this.nextadr);
                }
                scan(jCForLoop.body);
                resolveContinues(jCForLoop);
                scan(jCForLoop.step);
                if (Flow.this.log.nerrors != i2 || this.flowKind.isFinal() || new Bits(bits3).diffSet(this.uninits).nextBit(this.firstadr) == -1) {
                    break;
                }
                Bits bits4 = this.uninits;
                bits4.assign(bits3.andSet(bits4));
                this.flowKind = FlowKind.SPECULATIVE_LOOP;
            }
            this.flowKind = flowKind;
            this.inits.assign(bits);
            this.uninits.assign(bits2);
            resolveBreaks(jCForLoop, listBuffer);
            this.nextadr = i;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
            visitVarDef(jCEnhancedForLoop.var);
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            FlowKind flowKind = this.flowKind;
            this.flowKind = FlowKind.NORMAL;
            int i = this.nextadr;
            scan(jCEnhancedForLoop.expr);
            Bits bits = new Bits(this.inits);
            Bits bits2 = new Bits(this.uninits);
            letInit(jCEnhancedForLoop.pos(), jCEnhancedForLoop.var.sym);
            this.pendingExits = new ListBuffer<>();
            int i2 = Flow.this.log.nerrors;
            while (true) {
                Bits bits3 = new Bits(this.uninits);
                bits3.excludeFrom(this.nextadr);
                scan(jCEnhancedForLoop.body);
                resolveContinues(jCEnhancedForLoop);
                if (Flow.this.log.nerrors != i2 || this.flowKind.isFinal() || new Bits(bits3).diffSet(this.uninits).nextBit(this.firstadr) == -1) {
                    break;
                }
                Bits bits4 = this.uninits;
                bits4.assign(bits3.andSet(bits4));
                this.flowKind = FlowKind.SPECULATIVE_LOOP;
            }
            this.flowKind = flowKind;
            this.inits.assign(bits);
            Bits bits5 = this.uninits;
            bits5.assign(bits2.andSet(bits5));
            resolveBreaks(jCEnhancedForLoop, listBuffer);
            this.nextadr = i;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIdent(JCTree.JCIdent jCIdent) {
            if (jCIdent.sym.kind == Kinds.Kind.VAR) {
                checkInit(jCIdent.pos(), (Symbol.VarSymbol) jCIdent.sym);
                referenced(jCIdent.sym);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIf(JCTree.JCIf jCIf) {
            scanCond(jCIf.cond);
            Bits bits = new Bits(this.initsWhenFalse);
            Bits bits2 = new Bits(this.uninitsWhenFalse);
            this.inits.assign(this.initsWhenTrue);
            this.uninits.assign(this.uninitsWhenTrue);
            scan(jCIf.thenpart);
            if (jCIf.elsepart == null) {
                this.inits.andSet(bits);
                this.uninits.andSet(bits2);
                return;
            }
            Bits bits3 = new Bits(this.inits);
            Bits bits4 = new Bits(this.uninits);
            this.inits.assign(bits);
            this.uninits.assign(bits2);
            scan(jCIf.elsepart);
            this.inits.andSet(bits3);
            this.uninits.andSet(bits4);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLabelled(JCTree.JCLabeledStatement jCLabeledStatement) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            scan(jCLabeledStatement.body);
            resolveBreaks(jCLabeledStatement, listBuffer);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            Bits bits = new Bits(this.uninits);
            Bits bits2 = new Bits(this.uninitsTry);
            Bits bits3 = new Bits(this.inits);
            int i = this.returnadr;
            int i2 = this.nextadr;
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            try {
                this.uninits.excludeFrom(this.firstadr);
                this.returnadr = this.nextadr;
                this.pendingExits = new ListBuffer<>();
                for (List list = jCLambda.params; list.nonEmpty(); list = list.tail) {
                    JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) list.head;
                    scan(jCVariableDecl);
                    this.inits.incl(jCVariableDecl.sym.adr);
                    this.uninits.excl(jCVariableDecl.sym.adr);
                }
                LambdaExpressionTree.BodyKind bodyKind = jCLambda.getBodyKind();
                LambdaExpressionTree.BodyKind bodyKind2 = LambdaExpressionTree.BodyKind.EXPRESSION;
                JCTree jCTree = jCLambda.body;
                if (bodyKind == bodyKind2) {
                    scanExpr(jCTree);
                } else {
                    scan(jCTree);
                }
            } finally {
                this.returnadr = i;
                this.uninits.assign(bits);
                this.uninitsTry.assign(bits2);
                this.inits.assign(bits3);
                this.pendingExits = listBuffer;
                this.nextadr = i2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            boolean z;
            if (jCMethodDecl.body != null && (jCMethodDecl.sym.flags() & 4096) == 0) {
                Bits bits = new Bits(this.inits);
                Bits bits2 = new Bits(this.uninits);
                int i = this.nextadr;
                int i2 = this.firstadr;
                int i3 = this.returnadr;
                Assert.check(this.pendingExits.isEmpty());
                boolean z2 = this.isConstructor;
                try {
                    boolean zIsConstructor = TreeInfo.isConstructor(jCMethodDecl);
                    this.isConstructor = zIsConstructor;
                    if (!zIsConstructor) {
                        this.firstadr = this.nextadr;
                    }
                    List list = jCMethodDecl.params;
                    while (true) {
                        z = false;
                        if (!list.nonEmpty()) {
                            break;
                        }
                        JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) list.head;
                        scan(jCVariableDecl);
                        if ((jCVariableDecl.sym.flags() & 8589934592L) != 0) {
                            z = true;
                        }
                        Assert.check(z, "Method parameter without PARAMETER flag");
                        initParam(jCVariableDecl);
                        list = list.tail;
                    }
                    scan(jCMethodDecl.body);
                    long j = 2251799813685248L;
                    boolean z3 = (jCMethodDecl.sym.flags() & 2251799813685248L) != 0 || (jCMethodDecl.sym.flags() & 2305843077933170688L) == 2305843077933170688L;
                    if (this.isConstructor) {
                        z = (jCMethodDecl.sym.flags() & Flags.GENERATEDCONSTR) != 0;
                        int i4 = this.firstadr;
                        while (i4 < this.nextadr) {
                            JCTree.JCVariableDecl jCVariableDecl2 = this.vardecls[i4];
                            Symbol.VarSymbol varSymbol = jCVariableDecl2.sym;
                            long j2 = j;
                            if (varSymbol.owner == this.classDef.sym && !varSymbol.isStatic()) {
                                if (z && !z3) {
                                    checkInit(TreeInfo.diagnosticPositionFor(varSymbol, jCVariableDecl2), varSymbol, CompilerProperties.Errors.VarNotInitializedInDefaultConstructor(varSymbol));
                                } else if (!z3) {
                                    checkInit(TreeInfo.diagEndPos(jCMethodDecl.body), varSymbol);
                                } else if (!varSymbol.enclClass().isRecord() || (varSymbol.flags_field & 2305843009230471186L) == 0 || varSymbol.owner.kind != Kinds.Kind.TYP) {
                                    checkInit(TreeInfo.diagnosticPositionFor(varSymbol, jCVariableDecl2), varSymbol);
                                } else if (!this.inits.isMember(varSymbol.adr) && this.uninits.isMember(varSymbol.adr) && jCMethodDecl.completesNormally) {
                                    varSymbol.flags_field |= j2;
                                } else {
                                    checkInit(TreeInfo.diagEndPos(jCMethodDecl.body), varSymbol);
                                }
                            }
                            i4++;
                            j = j2;
                        }
                    }
                    clearPendingExits(true);
                } finally {
                    this.inits.assign(bits);
                    this.uninits.assign(bits2);
                    this.nextadr = i;
                    this.firstadr = i2;
                    this.returnadr = i3;
                    this.isConstructor = z2;
                }
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewArray(JCTree.JCNewArray jCNewArray) {
            scanExprs(jCNewArray.dims);
            scanExprs(jCNewArray.elems);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            scanExpr(jCNewClass.encl);
            scanExprs(jCNewClass.args);
            scan(jCNewClass.def);
        }

        @Override // com.sun.tools.javac.comp.Flow.BaseAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public /* bridge */ /* synthetic */ void visitPackageDef(JCTree.JCPackageDecl jCPackageDecl) {
            super.visitPackageDef(jCPackageDecl);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitReturn(JCTree.JCReturn jCReturn) {
            scanExpr(jCReturn.expr);
            recordExit(new AssignPendingExit(jCReturn, this.inits, this.uninits));
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
            super.visitSelect(jCFieldAccess);
            if (TreeInfo.isThisQualifier(jCFieldAccess.selected) && jCFieldAccess.sym.kind == Kinds.Kind.VAR) {
                checkInit(jCFieldAccess.pos(), (Symbol.VarSymbol) jCFieldAccess.sym);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitch(JCTree.JCSwitch jCSwitch) {
            handleSwitch(jCSwitch, jCSwitch.selector, jCSwitch.cases, jCSwitch.isExhaustive);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
            handleSwitch(jCSwitchExpression, jCSwitchExpression.selector, jCSwitchExpression.cases, jCSwitchExpression.isExhaustive);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitThrow(JCTree.JCThrow jCThrow) {
            scanExpr(jCThrow.expr);
            markDead();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTry(JCTree.JCTry jCTry) {
            ListBuffer<JCTree.JCVariableDecl> listBuffer = new ListBuffer();
            Bits bits = new Bits(this.uninitsTry);
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer2 = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            Bits bits2 = new Bits(this.inits);
            this.uninitsTry.assign(this.uninits);
            for (JCTree jCTree : jCTry.resources) {
                if (jCTree instanceof JCTree.JCVariableDecl) {
                    JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) jCTree;
                    visitVarDef(jCVariableDecl);
                    this.unrefdResources.enter(jCVariableDecl.sym);
                    listBuffer.append(jCVariableDecl);
                } else {
                    if (!(jCTree instanceof JCTree.JCExpression)) {
                        x01.a(jCTry);
                        return;
                    }
                    scanExpr((JCTree.JCExpression) jCTree);
                }
            }
            scan(jCTry.body);
            this.uninitsTry.andSet(this.uninits);
            Bits bits3 = new Bits(this.inits);
            Bits bits4 = new Bits(this.uninits);
            int i = this.nextadr;
            if (!listBuffer.isEmpty()) {
                for (JCTree.JCVariableDecl jCVariableDecl2 : listBuffer) {
                    if (this.unrefdResources.includes(jCVariableDecl2.sym) && !jCVariableDecl2.sym.isUnnamedVariable()) {
                        Flow.this.log.warning(jCVariableDecl2.pos(), CompilerProperties.LintWarnings.TryResourceNotReferenced(jCVariableDecl2.sym));
                        this.unrefdResources.remove(jCVariableDecl2.sym);
                    }
                }
            }
            Bits bits5 = new Bits(bits2);
            Bits bits6 = new Bits(this.uninitsTry);
            for (List list = jCTry.catchers; list.nonEmpty(); list = list.tail) {
                JCTree.JCVariableDecl jCVariableDecl3 = ((JCTree.JCCatch) list.head).param;
                this.inits.assign(bits5);
                this.uninits.assign(bits6);
                scan(jCVariableDecl3);
                initParam(jCVariableDecl3);
                scan(((JCTree.JCCatch) list.head).body);
                bits3.andSet(this.inits);
                bits4.andSet(this.uninits);
                this.nextadr = i;
            }
            JCTree.JCBlock jCBlock = jCTry.finalizer;
            Bits bits7 = this.inits;
            if (jCBlock != null) {
                bits7.assign(bits2);
                this.uninits.assign(this.uninitsTry);
                ListBuffer<BaseAnalyzer.PendingExit> listBuffer3 = this.pendingExits;
                this.pendingExits = listBuffer2;
                scan(jCTry.finalizer);
                if (jCTry.finallyCanCompleteNormally) {
                    this.uninits.andSet(bits4);
                    while (listBuffer3.nonEmpty()) {
                        BaseAnalyzer.PendingExit next = listBuffer3.next();
                        if (next instanceof AssignPendingExit) {
                            AssignPendingExit assignPendingExit = (AssignPendingExit) next;
                            assignPendingExit.exit_inits.orSet(this.inits);
                            assignPendingExit.exit_uninits.andSet(this.uninits);
                        }
                        this.pendingExits.append(next);
                    }
                    this.inits.orSet(bits3);
                }
            } else {
                bits7.assign(bits3);
                this.uninits.assign(bits4);
                ListBuffer<BaseAnalyzer.PendingExit> listBuffer4 = this.pendingExits;
                this.pendingExits = listBuffer2;
                while (listBuffer4.nonEmpty()) {
                    this.pendingExits.append(listBuffer4.next());
                }
            }
            this.uninitsTry.andSet(bits).andSet(this.uninits);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
            scanExpr(jCInstanceOf.expr);
            scan(jCInstanceOf.pattern);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitUnary(JCTree.JCUnary jCUnary) {
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCUnary.getTag().ordinal()];
            if (i == 1) {
                scanCond(jCUnary.arg);
                Bits bits = new Bits(this.initsWhenFalse);
                this.initsWhenFalse.assign(this.initsWhenTrue);
                this.initsWhenTrue.assign(bits);
                bits.assign(this.uninitsWhenFalse);
                this.uninitsWhenFalse.assign(this.uninitsWhenTrue);
                this.uninitsWhenTrue.assign(bits);
                return;
            }
            if (i != 2 && i != 3 && i != 4 && i != 5) {
                scanExpr(jCUnary.arg);
            } else {
                scanExpr(jCUnary.arg);
                letInit(jCUnary.arg);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            Kinds.Kind kind;
            boolean zTrackable = trackable(jCVariableDecl.sym);
            if (zTrackable && ((kind = jCVariableDecl.sym.owner.kind) == Kinds.Kind.MTH || kind == Kinds.Kind.VAR)) {
                newVar(jCVariableDecl);
            }
            JCTree.JCExpression jCExpression = jCVariableDecl.init;
            if (jCExpression != null) {
                scanExpr(jCExpression);
                if (zTrackable) {
                    letInit(jCVariableDecl.pos(), jCVariableDecl.sym);
                }
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            FlowKind flowKind = this.flowKind;
            this.flowKind = FlowKind.NORMAL;
            Bits bits = new Bits(true);
            Bits bits2 = new Bits(true);
            this.pendingExits = new ListBuffer<>();
            int i = Flow.this.log.nerrors;
            Bits bits3 = new Bits(this.uninits);
            bits3.excludeFrom(this.nextadr);
            while (true) {
                scanCond(jCWhileLoop.cond);
                if (!this.flowKind.isFinal()) {
                    bits.assign(this.initsWhenFalse);
                    bits2.assign(this.uninitsWhenFalse);
                }
                this.inits.assign(this.initsWhenTrue);
                this.uninits.assign(this.uninitsWhenTrue);
                scan(jCWhileLoop.body);
                resolveContinues(jCWhileLoop);
                if (Flow.this.log.nerrors != i || this.flowKind.isFinal() || new Bits(bits3).diffSet(this.uninits).nextBit(this.firstadr) == -1) {
                    break;
                }
                Bits bits4 = this.uninits;
                bits4.assign(bits3.andSet(bits4));
                this.flowKind = FlowKind.SPECULATIVE_LOOP;
            }
            this.flowKind = flowKind;
            this.inits.assign(bits);
            this.uninits.assign(bits2);
            resolveBreaks(jCWhileLoop, listBuffer);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitYield(JCTree.JCYield jCYield) {
            JCTree.JCSwitchExpression jCSwitchExpression = (JCTree.JCSwitchExpression) jCYield.target;
            if (jCSwitchExpression == null || !jCSwitchExpression.type.hasTag(TypeTag.BOOLEAN)) {
                scanExpr(jCYield.value);
                recordExit(new AssignPendingExit(jCYield, this.inits, this.uninits));
                return;
            }
            scanCond(jCYield.value);
            final Bits bits = new Bits(this.initsWhenTrue);
            final Bits bits2 = new Bits(this.initsWhenFalse);
            final Bits bits3 = new Bits(this.uninitsWhenTrue);
            final Bits bits4 = new Bits(this.uninitsWhenFalse);
            BaseAnalyzer.PendingExit pendingExit = new BaseAnalyzer.PendingExit(this, jCYield) { // from class: com.sun.tools.javac.comp.Flow.AssignAnalyzer.1
                final /* synthetic */ AssignAnalyzer this$1;

                {
                    this.this$1 = this;
                }

                @Override // com.sun.tools.javac.comp.Flow.BaseAnalyzer.PendingExit
                public void resolveJump() {
                    if (!this.this$1.inits.isReset()) {
                        this.this$1.split(true);
                    }
                    this.this$1.initsWhenTrue.andSet(bits);
                    this.this$1.initsWhenFalse.andSet(bits2);
                    this.this$1.uninitsWhenTrue.andSet(bits3);
                    this.this$1.uninitsWhenFalse.andSet(bits4);
                }
            };
            merge();
            recordExit(pendingExit);
        }

        public void checkInit(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.VarSymbol varSymbol) {
            checkInit(diagnosticPosition, varSymbol, CompilerProperties.Errors.VarMightNotHaveBeenInitialized(varSymbol));
        }

        public void analyzeTree(Env<?> env, TreeMaker treeMaker) {
            analyzeTree(env, env.tree, treeMaker);
        }

        public void letInit(JCTree jCTree) {
            JCTree jCTreeSkipParens = TreeInfo.skipParens(jCTree);
            if (jCTreeSkipParens.hasTag(JCTree.Tag.IDENT) || jCTreeSkipParens.hasTag(JCTree.Tag.SELECT)) {
                Symbol symbol = TreeInfo.symbol(jCTreeSkipParens);
                if (symbol.kind == Kinds.Kind.VAR) {
                    letInit(jCTreeSkipParens.pos(), (Symbol.VarSymbol) symbol);
                }
            }
        }
    }

    public class FlowAnalyzer extends BaseAnalyzer {
        List<Type> caught;
        JCTree.JCClassDecl classDef;
        HashMap<Symbol, List<Type>> preciseRethrowTypes;
        List<Type> thrown;

        public class ThrownPendingExit extends BaseAnalyzer.PendingExit {
            Type thrown;

            public ThrownPendingExit(JCTree jCTree, Type type) {
                super(jCTree);
                this.thrown = type;
            }
        }

        public FlowAnalyzer() {
        }

        public static /* synthetic */ void a(FlowAnalyzer flowAnalyzer, JCTree jCTree) {
            flowAnalyzer.scan(jCTree);
            flowAnalyzer.errorUncaught();
        }

        public static /* synthetic */ void b(FlowAnalyzer flowAnalyzer, JCTree jCTree) {
            flowAnalyzer.scan(jCTree);
            flowAnalyzer.errorUncaught();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void handleSwitch(JCTree jCTree, JCTree.JCExpression jCExpression, List<JCTree.JCCase> list) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            scan(jCExpression);
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                JCTree.JCCase jCCase = (JCTree.JCCase) list2.head;
                scan(jCCase.labels);
                scan(jCCase.stats);
            }
            if (jCTree.hasTag(JCTree.Tag.SWITCH_EXPRESSION)) {
                resolveYields(jCTree, listBuffer);
            } else {
                resolveBreaks(jCTree, listBuffer);
            }
        }

        private boolean isExceptionOrThrowable(Type type) {
            return type.tsym == Flow.this.syms.throwableType.tsym || type.tsym == Flow.this.syms.exceptionType.tsym;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void analyzeTree(Env<AttrContext> env, JCTree jCTree, TreeMaker treeMaker) {
            try {
                Flow.this.attrEnv = env;
                Flow.this.make = treeMaker;
                this.pendingExits = new ListBuffer<>();
                this.preciseRethrowTypes = new HashMap<>();
                this.caught = null;
                this.thrown = null;
                this.classDef = null;
                scan(jCTree);
            } finally {
                this.pendingExits = null;
                Flow.this.make = null;
                this.caught = null;
                this.thrown = null;
                this.classDef = null;
            }
        }

        public void checkCaughtType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, List<Type> list, List<Type> list2) {
            boolean zSubset = Flow.this.chk.subset(type, list2);
            Flow flow = Flow.this;
            if (zSubset) {
                flow.log.error(diagnosticPosition, CompilerProperties.Errors.ExceptAlreadyCaught(type));
                return;
            }
            if (!flow.chk.isUnchecked(diagnosticPosition, type) && !isExceptionOrThrowable(type) && !Flow.this.chk.intersects(type, list)) {
                Flow.this.log.error(diagnosticPosition, CompilerProperties.Errors.ExceptNeverThrownInTry(type));
                return;
            }
            List<Type> listIntersect = Flow.this.chk.intersect(List.of(type), list);
            if (!Flow.this.chk.diff(listIntersect, list2).isEmpty() || isExceptionOrThrowable(type)) {
                return;
            }
            Flow.this.log.warning(diagnosticPosition, listIntersect.length() == 1 ? CompilerProperties.Warnings.UnreachableCatch(listIntersect) : CompilerProperties.Warnings.UnreachableCatch1(listIntersect));
        }

        public void errorUncaught() {
            BaseAnalyzer.PendingExit next = this.pendingExits.next();
            while (next != null) {
                if (next instanceof ThrownPendingExit) {
                    ThrownPendingExit thrownPendingExit = (ThrownPendingExit) next;
                    JCTree.JCClassDecl jCClassDecl = this.classDef;
                    if (jCClassDecl != null && jCClassDecl.pos == next.tree.pos) {
                        Flow.this.log.error(next.tree.pos(), CompilerProperties.Errors.UnreportedExceptionDefaultConstructor(thrownPendingExit.thrown));
                    } else if (next.tree.hasTag(JCTree.Tag.VARDEF) && ((JCTree.JCVariableDecl) next.tree).sym.isResourceVariable()) {
                        Flow.this.log.error(next.tree.pos(), CompilerProperties.Errors.UnreportedExceptionImplicitClose(thrownPendingExit.thrown, ((JCTree.JCVariableDecl) next.tree).sym.name));
                    } else {
                        Flow.this.log.error(next.tree.pos(), CompilerProperties.Errors.UnreportedExceptionNeedToCatchOrThrow(thrownPendingExit.thrown));
                    }
                } else {
                    Assert.check(Flow.this.log.hasErrorOn(next.tree.pos()));
                }
                next = this.pendingExits.next();
            }
        }

        @Override // com.sun.tools.javac.comp.Flow.BaseAnalyzer
        public void markDead() {
        }

        public void markThrown(JCTree jCTree, Type type) {
            if (Flow.this.chk.isUnchecked(jCTree.pos(), type)) {
                return;
            }
            if (!Flow.this.chk.isHandled(type, this.caught)) {
                this.pendingExits.append(new ThrownPendingExit(jCTree, type));
            }
            this.thrown = Flow.this.chk.incl(type, this.thrown);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
            scan(jCMethodInvocation.meth);
            scan(jCMethodInvocation.args);
            for (List listMo74getThrownTypes = jCMethodInvocation.meth.type.mo74getThrownTypes(); listMo74getThrownTypes.nonEmpty(); listMo74getThrownTypes = listMo74getThrownTypes.tail) {
                markThrown(jCMethodInvocation, (Type) listMo74getThrownTypes.head);
            }
            if (TreeInfo.name(jCMethodInvocation.meth) == Flow.this.names._super) {
                forEachInitializer(this.classDef, false, new Consumer() { // from class: com.sun.tools.javac.comp.a1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Flow.FlowAnalyzer.b(this.b, (JCTree) obj);
                    }
                });
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBlock(JCTree.JCBlock jCBlock) {
            scan(jCBlock.stats);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBreak(JCTree.JCBreak jCBreak) {
            recordExit(new BaseAnalyzer.PendingExit(jCBreak));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            if (jCClassDecl.sym == null) {
                return;
            }
            JCTree.JCClassDecl jCClassDecl2 = this.classDef;
            List<Type> listUnion = this.thrown;
            List<Type> list = this.caught;
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            boolean z = jCClassDecl.name == Flow.this.names.empty;
            this.pendingExits = new ListBuffer<>();
            if (!z) {
                this.caught = List.nil();
            }
            this.classDef = jCClassDecl;
            this.thrown = List.nil();
            try {
                for (List list2 = jCClassDecl.defs; list2.nonEmpty(); list2 = list2.tail) {
                    if (((JCTree) list2.head).hasTag(JCTree.Tag.CLASSDEF)) {
                        scan((JCTree) list2.head);
                    }
                }
                forEachInitializer(jCClassDecl, true, new Consumer() { // from class: com.sun.tools.javac.comp.z0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Flow.FlowAnalyzer.a(this.b, (JCTree) obj);
                    }
                });
                if (z) {
                    for (List list3 = jCClassDecl.defs; list3.nonEmpty(); list3 = list3.tail) {
                        if (TreeInfo.isConstructor((JCTree) list3.head)) {
                            JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) list3.head;
                            scan(jCMethodDecl);
                            jCMethodDecl.thrown = Flow.this.make.Types(this.thrown);
                            jCMethodDecl.sym.type = Flow.this.types.createMethodTypeWithThrown(jCMethodDecl.sym.type, this.thrown);
                        }
                    }
                    listUnion = Flow.this.chk.union(this.thrown, listUnion);
                }
                for (List list4 = jCClassDecl.defs; list4.nonEmpty(); list4 = list4.tail) {
                    if ((!z || !TreeInfo.isConstructor((JCTree) list4.head)) && ((JCTree) list4.head).hasTag(JCTree.Tag.METHODDEF)) {
                        scan((JCTree) list4.head);
                        errorUncaught();
                    }
                }
                this.thrown = listUnion;
            } finally {
                this.pendingExits = listBuffer;
                this.caught = list;
                this.classDef = jCClassDecl2;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitContinue(JCTree.JCContinue jCContinue) {
            recordExit(new BaseAnalyzer.PendingExit(jCContinue));
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            scan(jCDoWhileLoop.body);
            resolveContinues(jCDoWhileLoop);
            scan(jCDoWhileLoop.cond);
            resolveBreaks(jCDoWhileLoop, listBuffer);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitForLoop(JCTree.JCForLoop jCForLoop) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            scan(jCForLoop.init);
            this.pendingExits = new ListBuffer<>();
            JCTree.JCExpression jCExpression = jCForLoop.cond;
            if (jCExpression != null) {
                scan(jCExpression);
            }
            scan(jCForLoop.body);
            resolveContinues(jCForLoop);
            scan(jCForLoop.step);
            resolveBreaks(jCForLoop, listBuffer);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
            visitVarDef(jCEnhancedForLoop.var);
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            scan(jCEnhancedForLoop.expr);
            this.pendingExits = new ListBuffer<>();
            scan(jCEnhancedForLoop.body);
            resolveContinues(jCEnhancedForLoop);
            resolveBreaks(jCEnhancedForLoop, listBuffer);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIf(JCTree.JCIf jCIf) {
            scan(jCIf.cond);
            scan(jCIf.thenpart);
            JCTree.JCStatement jCStatement = jCIf.elsepart;
            if (jCStatement != null) {
                scan(jCStatement);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLabelled(JCTree.JCLabeledStatement jCLabeledStatement) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            scan(jCLabeledStatement.body);
            resolveBreaks(jCLabeledStatement, listBuffer);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            Type type = jCLambda.type;
            if (type == null || !type.isErroneous()) {
                List<Type> list = this.caught;
                List<Type> list2 = this.thrown;
                ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
                try {
                    this.pendingExits = new ListBuffer<>();
                    this.caught = jCLambda.getDescriptorType(Flow.this.types).mo74getThrownTypes();
                    this.thrown = List.nil();
                    scan(jCLambda.body);
                    List list3 = this.pendingExits.toList();
                    this.pendingExits = new ListBuffer<>();
                    while (list3.nonEmpty()) {
                        BaseAnalyzer.PendingExit pendingExit = (BaseAnalyzer.PendingExit) list3.head;
                        list3 = list3.tail;
                        if (pendingExit instanceof ThrownPendingExit) {
                            this.pendingExits.append(pendingExit);
                        } else {
                            Assert.check(pendingExit.tree.hasTag(JCTree.Tag.RETURN) || Flow.this.log.hasErrorOn(pendingExit.tree.pos()));
                        }
                    }
                    errorUncaught();
                } finally {
                    this.pendingExits = listBuffer;
                    this.caught = list;
                    this.thrown = list2;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            if (jCMethodDecl.body == null) {
                return;
            }
            List<Type> list = this.caught;
            List<Type> listMo74getThrownTypes = jCMethodDecl.sym.type.mo74getThrownTypes();
            Assert.check(this.pendingExits.isEmpty());
            try {
                for (List list2 = jCMethodDecl.params; list2.nonEmpty(); list2 = list2.tail) {
                    scan((JCTree.JCVariableDecl) list2.head);
                }
                if (TreeInfo.hasConstructorCall(jCMethodDecl, Flow.this.names._super)) {
                    this.caught = Flow.this.chk.union(this.caught, listMo74getThrownTypes);
                } else if ((jCMethodDecl.sym.flags() & 1048584) != 1048576) {
                    this.caught = listMo74getThrownTypes;
                }
                scan(jCMethodDecl.body);
                List list3 = this.pendingExits.toList();
                this.pendingExits = new ListBuffer<>();
                while (list3.nonEmpty()) {
                    BaseAnalyzer.PendingExit pendingExit = (BaseAnalyzer.PendingExit) list3.head;
                    list3 = list3.tail;
                    if (pendingExit instanceof ThrownPendingExit) {
                        this.pendingExits.append(pendingExit);
                    } else {
                        Assert.check(pendingExit.tree.hasTag(JCTree.Tag.RETURN) || Flow.this.log.hasErrorOn(pendingExit.tree.pos()));
                    }
                }
            } finally {
                this.caught = list;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            scan(jCNewClass.encl);
            scan(jCNewClass.args);
            for (List listMo74getThrownTypes = jCNewClass.constructorType.mo74getThrownTypes(); listMo74getThrownTypes.nonEmpty(); listMo74getThrownTypes = listMo74getThrownTypes.tail) {
                markThrown(jCNewClass, (Type) listMo74getThrownTypes.head);
            }
            List<Type> list = this.caught;
            try {
                if (jCNewClass.def != null) {
                    for (List listMo74getThrownTypes2 = jCNewClass.constructor.type.mo74getThrownTypes(); listMo74getThrownTypes2.nonEmpty(); listMo74getThrownTypes2 = listMo74getThrownTypes2.tail) {
                        this.caught = Flow.this.chk.incl((Type) listMo74getThrownTypes2.head, this.caught);
                    }
                }
                scan(jCNewClass.def);
            } finally {
                this.caught = list;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitReturn(JCTree.JCReturn jCReturn) {
            scan(jCReturn.expr);
            recordExit(new BaseAnalyzer.PendingExit(jCReturn));
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitch(JCTree.JCSwitch jCSwitch) {
            handleSwitch(jCSwitch, jCSwitch.selector, jCSwitch.cases);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
            handleSwitch(jCSwitchExpression, jCSwitchExpression.selector, jCSwitchExpression.cases);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitThrow(JCTree.JCThrow jCThrow) {
            scan(jCThrow.expr);
            Symbol symbol = TreeInfo.symbol(jCThrow.expr);
            if (symbol == null || symbol.kind != Kinds.Kind.VAR || (symbol.flags() & 2199023255568L) == 0 || this.preciseRethrowTypes.get(symbol) == null) {
                markThrown(jCThrow, jCThrow.expr.type);
            } else {
                Iterator<Type> it = this.preciseRethrowTypes.get(symbol).iterator();
                while (it.hasNext()) {
                    markThrown(jCThrow, it.next());
                }
            }
            markDead();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTry(JCTree.JCTry jCTry) {
            List<Type> list = this.caught;
            List<Type> list2 = this.thrown;
            this.thrown = List.nil();
            for (List list3 = jCTry.catchers; list3.nonEmpty(); list3 = list3.tail) {
                boolean zIsMultiCatch = TreeInfo.isMultiCatch((JCTree.JCCatch) list3.head);
                A a = list3.head;
                Iterator<JCTree.JCExpression> it = (zIsMultiCatch ? ((JCTree.JCTypeUnion) ((JCTree.JCCatch) a).param.vartype).alternatives : List.of(((JCTree.JCCatch) a).param.vartype)).iterator();
                while (it.hasNext()) {
                    this.caught = Flow.this.chk.incl(it.next().type, this.caught);
                }
            }
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            for (JCTree jCTree : jCTry.resources) {
                if (jCTree instanceof JCTree.JCVariableDecl) {
                    visitVarDef((JCTree.JCVariableDecl) jCTree);
                } else {
                    if (!(jCTree instanceof JCTree.JCExpression)) {
                        x01.a(jCTry);
                        return;
                    }
                    scan((JCTree.JCExpression) jCTree);
                }
            }
            for (JCTree jCTree2 : jCTry.resources) {
                for (Type type : jCTree2.type.isCompound() ? Flow.this.types.interfaces(jCTree2.type).prepend(Flow.this.types.supertype(jCTree2.type)) : List.of(jCTree2.type)) {
                    if (Flow.this.types.asSuper(type, Flow.this.syms.autoCloseableType.tsym) != null) {
                        Symbol symbolResolveQualifiedMethod = Flow.this.rs.resolveQualifiedMethod(jCTry, Flow.this.attrEnv, Flow.this.types.skipTypeVars(type, false), Flow.this.names.close, List.nil(), List.nil());
                        Type typeMemberType = Flow.this.types.memberType(jCTree2.type, symbolResolveQualifiedMethod);
                        if (symbolResolveQualifiedMethod.kind == Kinds.Kind.MTH) {
                            Iterator<Type> it2 = typeMemberType.mo74getThrownTypes().iterator();
                            while (it2.hasNext()) {
                                markThrown(jCTree2, it2.next());
                            }
                        }
                    }
                }
            }
            scan(jCTry.body);
            List<Type> listUnion = Flow.this.chk.union(this.thrown, List.of(Flow.this.syms.runtimeExceptionType, Flow.this.syms.errorType));
            this.thrown = list2;
            this.caught = list;
            List<Type> listNil = List.nil();
            for (List list4 = jCTry.catchers; list4.nonEmpty(); list4 = list4.tail) {
                A a2 = list4.head;
                JCTree.JCVariableDecl jCVariableDecl = ((JCTree.JCCatch) a2).param;
                boolean zIsMultiCatch2 = TreeInfo.isMultiCatch((JCTree.JCCatch) a2);
                A a3 = list4.head;
                List<JCTree.JCExpression> listOf = zIsMultiCatch2 ? ((JCTree.JCTypeUnion) ((JCTree.JCCatch) a3).param.vartype).alternatives : List.of(((JCTree.JCCatch) a3).param.vartype);
                List<Type> listNil2 = List.nil();
                List<Type> listDiff = Flow.this.chk.diff(listUnion, listNil);
                Iterator<JCTree.JCExpression> it3 = listOf.iterator();
                while (it3.hasNext()) {
                    JCTree.JCExpression next = it3.next();
                    Type type2 = next.type;
                    if (type2 != Flow.this.syms.unknownType) {
                        listNil2 = listNil2.append(type2);
                        if (!Flow.this.types.isSameType(type2, Flow.this.syms.objectType)) {
                            if (listOf.size() <= 1) {
                                next = (JCTree.JCCatch) list4.head;
                            }
                            checkCaughtType(next.pos(), type2, listUnion, listNil);
                            listNil = Flow.this.chk.incl(type2, listNil);
                        }
                    }
                }
                scan(jCVariableDecl);
                this.preciseRethrowTypes.put(jCVariableDecl.sym, Flow.this.chk.intersect(listNil2, listDiff));
                scan(((JCTree.JCCatch) list4.head).body);
                this.preciseRethrowTypes.remove(jCVariableDecl.sym);
            }
            if (jCTry.finalizer == null) {
                this.thrown = Flow.this.chk.union(this.thrown, Flow.this.chk.diff(listUnion, listNil));
                ListBuffer<BaseAnalyzer.PendingExit> listBuffer2 = this.pendingExits;
                this.pendingExits = listBuffer;
                while (listBuffer2.nonEmpty()) {
                    this.pendingExits.append(listBuffer2.next());
                }
                return;
            }
            List<Type> list5 = this.thrown;
            this.thrown = List.nil();
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer3 = this.pendingExits;
            this.pendingExits = listBuffer;
            scan(jCTry.finalizer);
            boolean z = jCTry.finallyCanCompleteNormally;
            Flow flow = Flow.this;
            if (!z) {
                this.thrown = flow.chk.union(this.thrown, list2);
                return;
            }
            this.thrown = flow.chk.union(this.thrown, Flow.this.chk.diff(listUnion, listNil));
            this.thrown = Flow.this.chk.union(this.thrown, list5);
            while (listBuffer3.nonEmpty()) {
                this.pendingExits.append(listBuffer3.next());
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            scan(jCVariableDecl.init);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
            ListBuffer<BaseAnalyzer.PendingExit> listBuffer = this.pendingExits;
            this.pendingExits = new ListBuffer<>();
            scan(jCWhileLoop.cond);
            scan(jCWhileLoop.body);
            resolveContinues(jCWhileLoop);
            resolveBreaks(jCWhileLoop, listBuffer);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitYield(JCTree.JCYield jCYield) {
            scan(jCYield.value);
            recordExit(new BaseAnalyzer.PendingExit(jCYield));
        }

        public void analyzeTree(Env<AttrContext> env, TreeMaker treeMaker) {
            analyzeTree(env, env.tree, treeMaker);
        }
    }
}

package com.sun.tools.javac.comp;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.source.tree.LambdaExpressionTree;
import com.sun.source.tree.NewClassTree;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeCopier;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.DiagnosticSource;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Log.DeferredDiagnosticHandler;
import com.sun.tools.javac.util.Options;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Analyzer {
    protected static final Context.Key<Analyzer> analyzerKey = new Context.Key<>();
    private final boolean allowDiamondWithAnonymousClassCreation;
    final EnumSet<AnalyzerMode> analyzerModes;
    final ArgumentAttr argumentAttr;
    final Attr attr;
    final AnalyzerCopier copier;
    DeferredAnalysisHelper deferredAnalysisHelper;
    final DeferredAttr deferredAttr;
    final Log log;
    final TreeMaker make;
    DeferredAnalysisHelper queueDeferredHelper;
    final Types types;
    StatementAnalyzer<JCTree, JCTree>[] analyzers = {new DiamondInitializer(), new LambdaAnalyzer(), new RedundantTypeArgAnalyzer(), new RedundantLocalVarTypeAnalyzer(), new RedundantLocalVarTypeAnalyzerForEach()};
    DeferredAnalysisHelper flushDeferredHelper = new DeferredAnalysisHelper() { // from class: com.sun.tools.javac.comp.Analyzer.1
        @Override // com.sun.tools.javac.comp.Analyzer.DeferredAnalysisHelper
        public void flush(Env<AttrContext> env) {
        }

        @Override // com.sun.tools.javac.comp.Analyzer.DeferredAnalysisHelper
        public void queue(RewritingContext rewritingContext) {
        }
    };

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Analyzer$2, reason: invalid class name */
    public class AnonymousClass2 implements DeferredAnalysisHelper {
        Map<Symbol.ClassSymbol, Queue<RewritingContext>> Q = new HashMap();

        public AnonymousClass2() {
        }

        public static /* synthetic */ Queue a(Symbol.ClassSymbol classSymbol) {
            return new ArrayDeque();
        }

        @Override // com.sun.tools.javac.comp.Analyzer.DeferredAnalysisHelper
        public void flush(Env<AttrContext> env) {
            if (this.Q.isEmpty()) {
                return;
            }
            Analyzer analyzer = Analyzer.this;
            DeferredAnalysisHelper deferredAnalysisHelper = analyzer.deferredAnalysisHelper;
            try {
                analyzer.deferredAnalysisHelper = analyzer.flushDeferredHelper;
                Queue<RewritingContext> queue = this.Q.get(env.enclClass.sym.outermostClass());
                while (queue != null && !queue.isEmpty()) {
                    Analyzer.this.doAnalysis(queue.remove());
                }
                Analyzer.this.deferredAnalysisHelper = deferredAnalysisHelper;
            } catch (Throwable th) {
                Analyzer.this.deferredAnalysisHelper = deferredAnalysisHelper;
                throw th;
            }
        }

        @Override // com.sun.tools.javac.comp.Analyzer.DeferredAnalysisHelper
        public void queue(RewritingContext rewritingContext) {
            this.Q.computeIfAbsent(rewritingContext.env.enclClass.sym.outermostClass(), new Function() { // from class: com.sun.tools.javac.comp.b
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Analyzer.AnonymousClass2.a((Symbol.ClassSymbol) obj);
                }
            }).add(rewritingContext);
        }
    }

    public class AnalyzerCopier extends TreeCopier<Void> {
        public AnalyzerCopier() {
            super(Analyzer.this.make);
        }

        @Override // com.sun.tools.javac.tree.TreeCopier, com.sun.source.tree.TreeVisitor
        public JCTree visitLambdaExpression(LambdaExpressionTree lambdaExpressionTree, Void r3) {
            JCTree.JCLambda jCLambda = (JCTree.JCLambda) super.visitLambdaExpression(lambdaExpressionTree, r3);
            JCTree.JCLambda.ParameterKind parameterKind = ((JCTree.JCLambda) lambdaExpressionTree).paramKind;
            JCTree.JCLambda.ParameterKind parameterKind2 = JCTree.JCLambda.ParameterKind.IMPLICIT;
            if (parameterKind == parameterKind2) {
                jCLambda.paramKind = parameterKind2;
                jCLambda.params.forEach(new Consumer() { // from class: com.sun.tools.javac.comp.c
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((JCTree.JCVariableDecl) obj).vartype = null;
                    }
                });
            }
            return jCLambda;
        }

        @Override // com.sun.tools.javac.tree.TreeCopier, com.sun.source.tree.TreeVisitor
        public JCTree visitNewClass(NewClassTree newClassTree, Void r3) {
            JCTree.JCNewClass jCNewClass = (JCTree.JCNewClass) newClassTree;
            JCTree.JCNewClass jCNewClass2 = (JCTree.JCNewClass) super.visitNewClass(newClassTree, r3);
            if (!jCNewClass.args.isEmpty() && jCNewClass.args.head.hasTag(JCTree.Tag.NULLCHK)) {
                List<JCTree.JCExpression> list = jCNewClass2.args;
                jCNewClass2.encl = ((JCTree.JCUnary) list.head).arg;
                jCNewClass2.args = list.tail;
            }
            return jCNewClass2;
        }
    }

    public interface DeferredAnalysisHelper {
        void flush(Env<AttrContext> env);

        void queue(RewritingContext rewritingContext);
    }

    public class DiamondInitializer extends StatementAnalyzer<JCTree.JCNewClass, JCTree.JCNewClass> {
        public DiamondInitializer() {
            super(AnalyzerMode.DIAMOND, JCTree.Tag.NEWCLASS);
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public boolean match(JCTree.JCNewClass jCNewClass) {
            if (!jCNewClass.clazz.hasTag(JCTree.Tag.TYPEAPPLY) || TreeInfo.isDiamond(jCNewClass)) {
                return false;
            }
            return jCNewClass.def == null || Analyzer.this.allowDiamondWithAnonymousClassCreation;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public void process(JCTree.JCNewClass jCNewClass, JCTree.JCNewClass jCNewClass2, boolean z) {
            List<Type> typeArguments;
            List typeArguments2;
            if (z) {
                return;
            }
            if (jCNewClass.def != null) {
                boolean zNonEmpty = jCNewClass2.def.implementing.nonEmpty();
                JCTree.JCClassDecl jCClassDecl = jCNewClass2.def;
                typeArguments = zNonEmpty ? jCClassDecl.implementing.get(0).type.getTypeArguments() : jCClassDecl.extending.type.getTypeArguments();
                boolean zNonEmpty2 = jCNewClass.def.implementing.nonEmpty();
                JCTree.JCClassDecl jCClassDecl2 = jCNewClass.def;
                typeArguments2 = zNonEmpty2 ? jCClassDecl2.implementing.get(0).type.getTypeArguments() : jCClassDecl2.extending.type.getTypeArguments();
            } else {
                typeArguments = jCNewClass2.type.getTypeArguments();
                typeArguments2 = jCNewClass.type.getTypeArguments();
            }
            Iterator<Type> it = typeArguments.iterator();
            while (it.hasNext()) {
                if (!Analyzer.this.types.isSameType(it.next(), (Type) typeArguments2.head)) {
                    return;
                } else {
                    typeArguments2 = typeArguments2.tail;
                }
            }
            Analyzer.this.log.warning(jCNewClass.clazz, CompilerProperties.Warnings.DiamondRedundantArgs);
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public List<JCTree.JCNewClass> rewrite(JCTree.JCNewClass jCNewClass) {
            if (!jCNewClass.clazz.hasTag(JCTree.Tag.TYPEAPPLY) || jCNewClass.type.isErroneous()) {
                return List.of(jCNewClass);
            }
            JCTree.JCNewClass jCNewClass2 = (JCTree.JCNewClass) Analyzer.this.copier.copy(jCNewClass);
            ((JCTree.JCTypeApply) jCNewClass2.clazz).arguments = List.nil();
            return List.of(jCNewClass2);
        }
    }

    public class LambdaAnalyzer extends StatementAnalyzer<JCTree.JCNewClass, JCTree.JCLambda> {
        public LambdaAnalyzer() {
            super(AnalyzerMode.LAMBDA, JCTree.Tag.NEWCLASS);
        }

        private List<JCTree> decls(JCTree.JCClassDecl jCClassDecl) {
            ListBuffer listBuffer = new ListBuffer();
            for (JCTree jCTree : jCClassDecl.defs) {
                if (jCTree.hasTag(JCTree.Tag.METHODDEF)) {
                    JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) jCTree;
                    if ((jCMethodDecl.getModifiers().flags & Flags.GENERATEDCONSTR) == 0) {
                        listBuffer.add(jCMethodDecl);
                    }
                } else {
                    listBuffer.add(jCTree);
                }
            }
            return listBuffer.toList();
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public boolean match(JCTree.JCNewClass jCNewClass) {
            Type type = jCNewClass.clazz.type;
            return jCNewClass.def != null && type.hasTag(TypeTag.CLASS) && Analyzer.this.types.isFunctionalInterface(type.tsym) && decls(jCNewClass.def).length() == 1;
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public void process(JCTree.JCNewClass jCNewClass, JCTree.JCLambda jCLambda, boolean z) {
            if (z) {
                return;
            }
            Analyzer.this.log.warning(jCNewClass.def, CompilerProperties.Warnings.PotentialLambdaFound);
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public List<JCTree.JCLambda> rewrite(JCTree.JCNewClass jCNewClass) {
            JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) Analyzer.this.copier.copy(decls(jCNewClass.def).head);
            return List.of(Analyzer.this.make.at(jCNewClass).Lambda(jCMethodDecl.params, jCMethodDecl.body));
        }
    }

    public abstract class RedundantLocalVarTypeAnalyzerBase<X extends JCTree.JCStatement> extends StatementAnalyzer<X, X> {
        public RedundantLocalVarTypeAnalyzerBase(JCTree.Tag tag) {
            super(AnalyzerMode.LOCAL, tag);
        }

        public void processVar(JCTree.JCVariableDecl jCVariableDecl, JCTree.JCVariableDecl jCVariableDecl2, boolean z) {
            if (z || !Analyzer.this.types.isSameType(jCVariableDecl.type, jCVariableDecl2.type)) {
                return;
            }
            Analyzer.this.log.warning(jCVariableDecl, CompilerProperties.Warnings.LocalRedundantType);
        }

        public JCTree.JCVariableDecl rewriteVarType(JCTree.JCVariableDecl jCVariableDecl) {
            JCTree.JCVariableDecl jCVariableDecl2 = (JCTree.JCVariableDecl) Analyzer.this.copier.copy(jCVariableDecl);
            jCVariableDecl2.vartype = null;
            return jCVariableDecl2;
        }
    }

    public class RedundantTypeArgAnalyzer extends StatementAnalyzer<JCTree.JCMethodInvocation, JCTree.JCMethodInvocation> {
        public RedundantTypeArgAnalyzer() {
            super(AnalyzerMode.METHOD, JCTree.Tag.APPLY);
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public boolean match(JCTree.JCMethodInvocation jCMethodInvocation) {
            List<JCTree.JCExpression> list = jCMethodInvocation.typeargs;
            return list != null && list.nonEmpty();
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public void process(JCTree.JCMethodInvocation jCMethodInvocation, JCTree.JCMethodInvocation jCMethodInvocation2, boolean z) {
            if (z) {
                return;
            }
            Analyzer.this.log.warning(jCMethodInvocation, CompilerProperties.Warnings.MethodRedundantTypeargs);
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public List<JCTree.JCMethodInvocation> rewrite(JCTree.JCMethodInvocation jCMethodInvocation) {
            JCTree.JCMethodInvocation jCMethodInvocation2 = (JCTree.JCMethodInvocation) Analyzer.this.copier.copy(jCMethodInvocation);
            jCMethodInvocation2.typeargs = List.nil();
            return List.of(jCMethodInvocation2);
        }
    }

    public class RewritingContext {
        StatementAnalyzer<JCTree, JCTree> analyzer;
        Env<AttrContext> env;
        boolean erroneous;
        JCTree oldTree;
        JCTree originalTree;
        JCTree replacement;

        public RewritingContext(JCTree jCTree, JCTree jCTree2, JCTree jCTree3, StatementAnalyzer<JCTree, JCTree> statementAnalyzer, Env<AttrContext> env) {
            this.originalTree = jCTree;
            this.oldTree = jCTree2;
            this.replacement = jCTree3;
            this.analyzer = statementAnalyzer;
            this.env = Analyzer.this.attr.copyEnv(env);
            if (jCTree.hasTag(JCTree.Tag.VARDEF)) {
                this.env.info.scope.remove(((JCTree.JCVariableDecl) jCTree).sym);
            }
        }

        public static /* synthetic */ boolean a(RewritingContext rewritingContext, JCDiagnostic jCDiagnostic) {
            rewritingContext.getClass();
            if (jCDiagnostic.getType() == JCDiagnostic.DiagnosticType.ERROR) {
                rewritingContext.erroneous = true;
            }
            return true;
        }

        public Log.DeferredDiagnosticHandler diagHandler() {
            Log log = Analyzer.this.log;
            Objects.requireNonNull(log);
            return log.new DeferredDiagnosticHandler(new Predicate() { // from class: com.sun.tools.javac.comp.d
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Analyzer.RewritingContext.a(this.b, (JCDiagnostic) obj);
                }
            }, false);
        }
    }

    public abstract class StatementAnalyzer<S extends JCTree, T extends JCTree> {
        AnalyzerMode mode;
        JCTree.Tag tag;

        public StatementAnalyzer(AnalyzerMode analyzerMode, JCTree.Tag tag) {
            this.mode = analyzerMode;
            this.tag = tag;
        }

        public boolean isEnabled() {
            return Analyzer.this.analyzerModes.contains(this.mode);
        }

        public abstract boolean match(S s);

        public abstract void process(S s, T t, boolean z);

        public abstract List<T> rewrite(S s);
    }

    public class TreeRewriter extends AnalyzerCopier {
        RewritingContext rewriting;
        JCTree wrappedTree;

        public TreeRewriter(RewritingContext rewritingContext, JCTree jCTree) {
            super();
            this.rewriting = rewritingContext;
            this.wrappedTree = jCTree;
        }

        @Override // com.sun.tools.javac.tree.TreeCopier
        public <Z extends JCTree> Z copy(Z z, Void r4) {
            Z z2 = (Z) super.copy(z, (Object) null);
            if (z != null) {
                RewritingContext rewritingContext = this.rewriting;
                if (z == rewritingContext.oldTree) {
                    Assert.checkNonNull(rewritingContext.replacement);
                    return (Z) this.rewriting.replacement;
                }
            }
            return z2;
        }

        @Override // com.sun.tools.javac.tree.TreeCopier, com.sun.source.tree.TreeVisitor
        public JCTree visitVariable(VariableTree variableTree, Void r6) {
            JCTree jCTreeVisitVariable = super.visitVariable(variableTree, r6);
            if (variableTree == this.wrappedTree) {
                ((JCTree.JCVariableDecl) jCTreeVisitVariable).mods.flags &= 16;
            }
            return jCTreeVisitVariable;
        }
    }

    public Analyzer(Context context) {
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        this.queueDeferredHelper = anonymousClass2;
        this.deferredAnalysisHelper = anonymousClass2;
        context.put(analyzerKey, this);
        this.types = Types.instance(context);
        this.log = Log.instance(context);
        this.attr = Attr.instance(context);
        this.deferredAttr = DeferredAttr.instance(context);
        this.argumentAttr = ArgumentAttr.instance(context);
        this.make = TreeMaker.instance(context);
        this.copier = new AnalyzerCopier();
        String str = Options.instance(context).get("find");
        Source sourceInstance = Source.instance(context);
        this.allowDiamondWithAnonymousClassCreation = Source.Feature.DIAMOND_WITH_ANONYMOUS_CLASS_CREATION.allowedInSource(sourceInstance);
        this.analyzerModes = AnalyzerMode.getAnalyzerModes(str, sourceInstance);
    }

    public static Analyzer instance(Context context) {
        Analyzer analyzer = (Analyzer) context.get(analyzerKey);
        return analyzer == null ? new Analyzer(context) : analyzer;
    }

    public void analyze(JCTree.JCStatement jCStatement, Env<AttrContext> env) {
        StatementScanner statementScanner = new StatementScanner(jCStatement, env);
        statementScanner.scan();
        if (statementScanner.rewritings.isEmpty()) {
            return;
        }
        Iterator<RewritingContext> it = statementScanner.rewritings.iterator();
        while (it.hasNext()) {
            this.deferredAnalysisHelper.queue(it.next());
        }
    }

    public void analyzeIfNeeded(JCTree jCTree, Env<AttrContext> env) {
        if (env != null) {
            analyze((JCTree.JCStatement) jCTree, env);
        }
    }

    public Env<AttrContext> copyEnvIfNeeded(JCTree jCTree, Env<AttrContext> env) {
        Attr.ResultInfo resultInfo = null;
        if (this.analyzerModes.isEmpty() || env.info.attributionMode.isSpeculative || !TreeInfo.isStatement(jCTree) || jCTree.hasTag(JCTree.Tag.LABELLED)) {
            return null;
        }
        JCTree jCTree2 = env.tree;
        AttrContext attrContext = env.info;
        Env<AttrContext> envDup = env.dup(jCTree2, attrContext.dup(attrContext.scope.dupUnshared(attrContext.scope.owner)));
        AttrContext attrContext2 = envDup.info;
        AttrContext attrContext3 = attrContext2;
        if (attrContext2.returnResult != null) {
            Attr attr = this.attr;
            Objects.requireNonNull(attr);
            AttrContext attrContext4 = envDup.info;
            resultInfo = new Attr.ResultInfo(attr, attrContext4.returnResult.pkind, attrContext4.returnResult.pt);
        }
        attrContext3.returnResult = resultInfo;
        return envDup;
    }

    public void doAnalysis(final RewritingContext rewritingContext) {
        JCTree jCTree;
        DiagnosticSource diagnosticSourceCurrentSource = this.log.currentSource();
        ArgumentAttr.LocalCacheContext localCacheContextWithLocalCacheContext = this.argumentAttr.withLocalCacheContext();
        try {
            this.log.useSource(rewritingContext.env.toplevel.getSourceFile());
            JCTree jCTreeBlock = (JCTree.JCStatement) rewritingContext.originalTree;
            if (rewritingContext.env.info.scope.owner.kind == Kinds.Kind.TYP) {
                jCTreeBlock = this.make.at(-1).Block(4096L, List.of((JCTree.JCStatement) rewritingContext.originalTree));
                jCTree = rewritingContext.originalTree;
            } else {
                jCTree = null;
            }
            this.deferredAttr.attribSpeculative(jCTreeBlock, rewritingContext.env, this.attr.statInfo, new TreeRewriter(rewritingContext, jCTree), new Supplier() { // from class: com.sun.tools.javac.comp.a
                @Override // java.util.function.Supplier
                public final Object get() {
                    return rewritingContext.diagHandler();
                }
            }, DeferredAttr.AttributionMode.ANALYZER, this.argumentAttr.withLocalCacheContext());
            rewritingContext.analyzer.process(rewritingContext.oldTree, rewritingContext.replacement, rewritingContext.erroneous);
        } catch (Throwable th) {
            try {
                Assert.error("Analyzer error when processing: " + rewritingContext.originalTree + ":" + th.toString() + "\n" + ((String) Arrays.stream(th.getStackTrace()).map(new Function() { // from class: s30
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((StackTraceElement) obj).toString();
                    }
                }).collect(Collectors.joining("\n"))));
            } finally {
                this.log.useSource(diagnosticSourceCurrentSource.getFile());
                localCacheContextWithLocalCacheContext.leave();
            }
        }
    }

    public void flush(Env<AttrContext> env) {
        this.deferredAnalysisHelper.flush(env);
    }

    public enum AnalyzerMode {
        DIAMOND("diamond"),
        LAMBDA("lambda"),
        METHOD(Constants.ATTRNAME_OUTPUT_METHOD),
        LOCAL("local", Source.Feature.LOCAL_VARIABLE_TYPE_INFERENCE);

        final Source.Feature feature;
        final String opt;

        AnalyzerMode(String str, Source.Feature feature) {
            this.opt = str;
            this.feature = feature;
        }

        public static EnumSet<AnalyzerMode> getAnalyzerModes(String str, Source source) {
            Source.Feature feature;
            if (str == null) {
                return EnumSet.noneOf(AnalyzerMode.class);
            }
            List listFrom = List.from(str.split(","));
            EnumSet<AnalyzerMode> enumSetNoneOf = EnumSet.noneOf(AnalyzerMode.class);
            if (listFrom.contains("all")) {
                enumSetNoneOf = EnumSet.allOf(AnalyzerMode.class);
            }
            for (AnalyzerMode analyzerMode : values()) {
                if (listFrom.contains("-" + analyzerMode.opt) || !((feature = analyzerMode.feature) == null || feature.allowedInSource(source))) {
                    enumSetNoneOf.remove(analyzerMode);
                } else if (listFrom.contains(analyzerMode.opt)) {
                    enumSetNoneOf.add(analyzerMode);
                }
            }
            return enumSetNoneOf;
        }

        AnalyzerMode(String str) {
            this(str, null);
        }
    }

    public class RedundantLocalVarTypeAnalyzer extends RedundantLocalVarTypeAnalyzerBase<JCTree.JCVariableDecl> {
        public RedundantLocalVarTypeAnalyzer() {
            super(JCTree.Tag.VARDEF);
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public boolean match(JCTree.JCVariableDecl jCVariableDecl) {
            return jCVariableDecl.sym.owner.kind == Kinds.Kind.MTH && jCVariableDecl.init != null && !jCVariableDecl.declaredUsingVar() && Analyzer.this.attr.canInferLocalVarType(jCVariableDecl) == null;
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public List<JCTree.JCVariableDecl> rewrite(JCTree.JCVariableDecl jCVariableDecl) {
            return List.of(rewriteVarType(jCVariableDecl));
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public void process(JCTree.JCVariableDecl jCVariableDecl, JCTree.JCVariableDecl jCVariableDecl2, boolean z) {
            processVar(jCVariableDecl, jCVariableDecl2, z);
        }
    }

    public class RedundantLocalVarTypeAnalyzerForEach extends RedundantLocalVarTypeAnalyzerBase<JCTree.JCEnhancedForLoop> {
        public RedundantLocalVarTypeAnalyzerForEach() {
            super(JCTree.Tag.FOREACHLOOP);
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public boolean match(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
            return !jCEnhancedForLoop.var.declaredUsingVar();
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public List<JCTree.JCEnhancedForLoop> rewrite(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
            JCTree.JCEnhancedForLoop jCEnhancedForLoop2 = (JCTree.JCEnhancedForLoop) Analyzer.this.copier.copy(jCEnhancedForLoop);
            jCEnhancedForLoop2.var = rewriteVarType(jCEnhancedForLoop.var);
            jCEnhancedForLoop2.body = Analyzer.this.make.at(jCEnhancedForLoop.body).Block(0L, List.nil());
            return List.of(jCEnhancedForLoop2);
        }

        @Override // com.sun.tools.javac.comp.Analyzer.StatementAnalyzer
        public void process(JCTree.JCEnhancedForLoop jCEnhancedForLoop, JCTree.JCEnhancedForLoop jCEnhancedForLoop2, boolean z) {
            processVar(jCEnhancedForLoop.var, jCEnhancedForLoop2.var, z);
        }
    }

    public class StatementScanner extends TreeScanner {
        Env<AttrContext> env;
        JCTree originalTree;
        ListBuffer<RewritingContext> rewritings = new ListBuffer<>();

        public StatementScanner(JCTree jCTree, Env<AttrContext> env) {
            this.originalTree = jCTree;
            this.env = Analyzer.this.attr.copyEnv(env);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            if (jCTree != null) {
                StatementAnalyzer<JCTree, JCTree>[] statementAnalyzerArr = Analyzer.this.analyzers;
                int length = statementAnalyzerArr.length;
                int i = 0;
                while (i < length) {
                    StatementAnalyzer<JCTree, JCTree> statementAnalyzer = statementAnalyzerArr[i];
                    if (statementAnalyzer.isEnabled() && jCTree.hasTag(statementAnalyzer.tag) && statementAnalyzer.match(jCTree)) {
                        Iterator it = statementAnalyzer.rewrite(jCTree).iterator();
                        while (it.hasNext()) {
                            JCTree jCTree2 = jCTree;
                            this.rewritings.add(Analyzer.this.new RewritingContext(this.originalTree, jCTree2, (JCTree) it.next(), statementAnalyzer, this.env));
                        }
                        break;
                    }
                    i++;
                    jCTree = jCTree;
                }
            }
            super.scan(jCTree);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBlock(JCTree.JCBlock jCBlock) {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
            scan(jCDoWhileLoop.getCondition());
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitForLoop(JCTree.JCForLoop jCForLoop) {
            scan(jCForLoop.getCondition());
            scan(jCForLoop.getUpdate());
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
            scan(jCEnhancedForLoop.getExpression());
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIf(JCTree.JCIf jCIf) {
            scan(jCIf.getCondition());
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitch(JCTree.JCSwitch jCSwitch) {
            scan(jCSwitch.getExpression());
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTry(JCTree.JCTry jCTry) {
            scan(jCTry.getBlock());
            scan(jCTry.getCatches());
            scan(jCTry.getFinallyBlock());
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
            scan(jCWhileLoop.getCondition());
        }

        public void scan() {
            scan(this.originalTree);
        }
    }
}

package com.sun.tools.javac.comp;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.code.Directive;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.LintMapper;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.ThisEscapeAnalyzer;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Pair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ThisEscapeAnalyzer extends TreeScanner {
    protected static final Context.Key<ThisEscapeAnalyzer> contextKey = new Context.Key<>();
    private MethodInfo currentMethod;
    private final LintMapper lintMapper;
    private final Log log;
    private final Names names;
    private RefSet<Ref> refs;
    private final Resolve rs;
    private final Symtab syms;
    private JCTree.JCClassDecl targetClass;
    private Env<AttrContext> topLevelEnv;
    private final Types types;
    private final Map<Symbol, MethodInfo> methodMap = new LinkedHashMap();
    private final Set<Symbol.ClassSymbol> nonPublicOuters = new HashSet();
    private final ArrayList<Warning> warningList = new ArrayList<>();
    private final ArrayList<StackFrame> callStack = new ArrayList<>();
    private final Set<Pair<JCTree.JCMethodDecl, RefSet<Ref>>> invocations = new HashSet();
    private int depth = -1;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.ThisEscapeAnalyzer$1ForeachMethods, reason: invalid class name */
    public static final class C1ForeachMethods {
        private final Symbol.MethodSymbol hasNext;
        private final Symbol.MethodSymbol iterator;
        private final Symbol.MethodSymbol next;

        public C1ForeachMethods(Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2, Symbol.MethodSymbol methodSymbol3) {
            this.iterator = methodSymbol;
            this.hasNext = methodSymbol2;
            this.next = methodSymbol3;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof C1ForeachMethods)) {
                return false;
            }
            C1ForeachMethods c1ForeachMethods = (C1ForeachMethods) obj;
            return Objects.equals(this.next, c1ForeachMethods.next) && Objects.equals(this.hasNext, c1ForeachMethods.hasNext) && Objects.equals(this.iterator, c1ForeachMethods.iterator);
        }

        public Symbol.MethodSymbol hasNext() {
            return this.hasNext;
        }

        public final int hashCode() {
            return (((Objects.hashCode(this.iterator) * 31) + Objects.hashCode(this.hasNext)) * 31) + Objects.hashCode(this.next);
        }

        public Symbol.MethodSymbol iterator() {
            return this.iterator;
        }

        public Symbol.MethodSymbol next() {
            return this.next;
        }

        public final String toString() {
            return "ForeachMethods[iterator=" + Objects.toString(this.iterator) + ", hasNext=" + Objects.toString(this.hasNext) + ", next=" + Objects.toString(this.next) + "]";
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.ThisEscapeAnalyzer$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[JCTree.JCMemberReference.ReferenceKind.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind = iArr;
            try {
                iArr[JCTree.JCMemberReference.ReferenceKind.UNBOUND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.STATIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.TOPLEVEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.ARRAY_CTOR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.SUPER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.BOUND.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.IMPLICIT_INNER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr2;
            try {
                iArr2[JCTree.Tag.SWITCH_EXPRESSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CONDEXPR.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.YIELD.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.APPLY.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NEWCLASS.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NEWARRAY.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.LAMBDA.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PARENS.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.ASSIGN.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TYPECAST.ordinal()] = 10;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.INDEXED.ordinal()] = 11;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SELECT.ordinal()] = 12;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.REFERENCE.ordinal()] = 13;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.IDENT.ordinal()] = 14;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NULLCHK.ordinal()] = 15;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.LETEXPR.ordinal()] = 16;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    public enum Indirection {
        DIRECT,
        OUTER,
        INDIRECT
    }

    public static final class MethodInfo {
        private final boolean analyzable;
        private final boolean constructor;
        private final JCTree.JCMethodDecl declaration;
        private final JCTree.JCClassDecl declaringClass;
        private final boolean invokable;

        private MethodInfo(JCTree.JCClassDecl jCClassDecl, JCTree.JCMethodDecl jCMethodDecl, boolean z, boolean z2, boolean z3) {
            this.declaringClass = jCClassDecl;
            this.declaration = jCMethodDecl;
            this.constructor = z;
            this.analyzable = z2;
            this.invokable = z3;
        }

        public boolean analyzable() {
            return this.analyzable;
        }

        public boolean constructor() {
            return this.constructor;
        }

        public JCTree.JCMethodDecl declaration() {
            return this.declaration;
        }

        public JCTree.JCClassDecl declaringClass() {
            return this.declaringClass;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof MethodInfo)) {
                return false;
            }
            MethodInfo methodInfo = (MethodInfo) obj;
            return this.invokable == methodInfo.invokable && this.analyzable == methodInfo.analyzable && this.constructor == methodInfo.constructor && Objects.equals(this.declaration, methodInfo.declaration) && Objects.equals(this.declaringClass, methodInfo.declaringClass);
        }

        public final int hashCode() {
            return (((((((Objects.hashCode(this.declaringClass) * 31) + Objects.hashCode(this.declaration)) * 31) + Boolean.hashCode(this.constructor)) * 31) + Boolean.hashCode(this.analyzable)) * 31) + Boolean.hashCode(this.invokable);
        }

        public boolean invokable() {
            return this.invokable;
        }

        public String toString() {
            return "MethodInfo[method=" + this.declaringClass.sym.flatname + Constants.ATTRVAL_THIS + this.declaration.sym + ",constructor=" + this.constructor + ",analyzable=" + this.analyzable + ",invokable=" + this.invokable + "]";
        }
    }

    public class StackFrame {
        final JCTree initializer;
        final MethodInfo method;
        final JCTree site;
        final boolean suppressible;

        public StackFrame(MethodInfo methodInfo, JCTree jCTree, JCTree jCTree2) {
            this.method = methodInfo;
            this.initializer = jCTree;
            this.site = jCTree2;
            this.suppressible = jCTree != null || (methodInfo.constructor && methodInfo.declaringClass == ThisEscapeAnalyzer.this.targetClass);
        }

        public int comparePos(StackFrame stackFrame) {
            return Integer.compare(this.site.pos().getPreferredPosition(), stackFrame.site.pos().getPreferredPosition());
        }

        public boolean isSuppressed() {
            return this.suppressible && !lint().isEnabled(Lint.LintCategory.THIS_ESCAPE);
        }

        public Lint lint() {
            return ThisEscapeAnalyzer.this.lintMapper.lintAt(ThisEscapeAnalyzer.this.topLevelEnv.toplevel.sourcefile, this.site.pos()).get();
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder("StackFrame[");
            sb.append(this.method.declaration.sym);
            sb.append("@");
            sb.append(this.site.pos().getPreferredPosition());
            if (this.initializer != null) {
                str = ",init@" + this.initializer.pos().getPreferredPosition();
            } else {
                str = "";
            }
            sb.append(str);
            sb.append("]");
            return sb.toString();
        }

        public JCDiagnostic.DiagnosticPosition warningPos() {
            return this.site.pos().withLintPosition(-1);
        }
    }

    public class Warning {
        final JCTree.JCClassDecl declaringClass;
        final JCTree origin;
        final ArrayList<StackFrame> stack;

        public Warning(JCTree.JCClassDecl jCClassDecl, final ArrayList<StackFrame> arrayList) {
            this.declaringClass = jCClassDecl;
            this.stack = arrayList;
            this.origin = (JCTree) arrayList.stream().map(new Function() { // from class: com.sun.tools.javac.comp.v5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((ThisEscapeAnalyzer.StackFrame) obj).initializer;
                }
            }).filter(new Predicate() { // from class: mde
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Objects.nonNull((JCTree) obj);
                }
            }).findFirst().orElseGet(new Supplier() { // from class: com.sun.tools.javac.comp.w5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ((ThisEscapeAnalyzer.StackFrame) arrayList.get(0)).method.declaration;
                }
            });
        }

        public static /* synthetic */ boolean a(Warning warning, int i, Warning warning2, int i2) {
            return warning.stack.get(i + i2).comparePos(warning2.stack.get(i2)) == 0;
        }

        public static int sortByStackFrames(Warning warning, Warning warning2) {
            int iComparePos;
            int size = warning.stack.size();
            int size2 = warning2.stack.size();
            do {
                size--;
                boolean z = size < 0;
                size2--;
                boolean z2 = size2 < 0;
                if (z && z2) {
                    return 0;
                }
                if (z) {
                    return -1;
                }
                if (z2) {
                    return 1;
                }
                iComparePos = warning.stack.get(size).comparePos(warning2.stack.get(size2));
            } while (iComparePos == 0);
            return iComparePos;
        }

        public boolean isRedundantWith(final Warning warning) {
            final int size = this.stack.size() - warning.stack.size();
            return size >= 0 && IntStream.range(0, warning.stack.size()).allMatch(new IntPredicate() { // from class: com.sun.tools.javac.comp.u5
                @Override // java.util.function.IntPredicate
                public final boolean test(int i) {
                    return ThisEscapeAnalyzer.Warning.a(this.b, size, warning, i);
                }
            });
        }

        public boolean isSuppressed() {
            for (int size = this.stack.size() - 1; size >= 0; size--) {
                if (this.stack.get(size).isSuppressed()) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return "Warning[class=" + this.declaringClass.sym.flatname + ",stack=[\n    " + ((String) this.stack.stream().map(new Function() { // from class: com.sun.tools.javac.comp.t5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((ThisEscapeAnalyzer.StackFrame) obj).toString();
                }
            }).collect(Collectors.joining("\n    "))) + "]]";
        }

        public void trimInitializerFrames() {
            for (int i = 0; i < this.stack.size(); i++) {
                if (this.stack.get(i).initializer != null) {
                    this.stack.subList(0, i + 1).clear();
                    return;
                }
            }
        }
    }

    public ThisEscapeAnalyzer(Context context) {
        context.put(contextKey, this);
        this.names = Names.instance(context);
        this.log = Log.instance(context);
        this.syms = Symtab.instance(context);
        this.types = Types.instance(context);
        this.rs = Resolve.instance(context);
        this.lintMapper = LintMapper.instance(context);
    }

    public static /* synthetic */ ExprRef A(ThisEscapeAnalyzer thisEscapeAnalyzer, YieldRef yieldRef) {
        return new ExprRef(thisEscapeAnalyzer.depth, yieldRef);
    }

    public static /* synthetic */ boolean B(JCTree jCTree) {
        return (TreeInfo.flags(jCTree) & 8) == 0;
    }

    public static /* synthetic */ boolean C(ThisEscapeAnalyzer thisEscapeAnalyzer, Symbol.ModuleSymbol moduleSymbol) {
        return moduleSymbol != thisEscapeAnalyzer.syms.unnamedModule;
    }

    public static /* synthetic */ void E(ThisEscapeAnalyzer thisEscapeAnalyzer, JCTree.JCMemberReference jCMemberReference, RefSet refSet) {
        thisEscapeAnalyzer.getClass();
        thisEscapeAnalyzer.invoke(jCMemberReference, (Symbol.MethodSymbol) jCMemberReference.sym, List.nil(), refSet);
    }

    public static /* synthetic */ Ref G(Symbol.VarSymbol varSymbol, ExprRef exprRef) {
        return new VarRef(varSymbol, exprRef);
    }

    public static /* synthetic */ boolean J(ThisEscapeAnalyzer thisEscapeAnalyzer, Symbol.ModuleSymbol moduleSymbol) {
        return moduleSymbol != thisEscapeAnalyzer.syms.noModule;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void K(final ThisEscapeAnalyzer thisEscapeAnalyzer, JCTree.JCSwitchExpression jCSwitchExpression) {
        thisEscapeAnalyzer.getClass();
        thisEscapeAnalyzer.scan(jCSwitchExpression.selector);
        thisEscapeAnalyzer.refs.discardExprs(thisEscapeAnalyzer.depth);
        RefSet refSetNewEmpty = RefSet.newEmpty();
        for (List list = jCSwitchExpression.cases; list.nonEmpty(); list = list.tail) {
            thisEscapeAnalyzer.scan(((JCTree.JCCase) list.head).stats);
            Stream map = thisEscapeAnalyzer.refs.remove(YieldRef.class).map(new Function() { // from class: com.sun.tools.javac.comp.p4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.A(this.b, (ThisEscapeAnalyzer.YieldRef) obj);
                }
            });
            Objects.requireNonNull(refSetNewEmpty);
            map.forEach(new e4(refSetNewEmpty));
            thisEscapeAnalyzer.refs.removeExprs(thisEscapeAnalyzer.depth).forEach(new e4(refSetNewEmpty));
        }
        thisEscapeAnalyzer.refs.addAll(refSetNewEmpty);
    }

    public static /* synthetic */ boolean L(Symbol.VarSymbol varSymbol, VarRef varRef) {
        return varRef.sym == varSymbol;
    }

    public static /* synthetic */ VarRef M(JCTree.JCEnhancedForLoop jCEnhancedForLoop, ExprRef exprRef) {
        return new VarRef(jCEnhancedForLoop.var.sym, exprRef);
    }

    public static /* synthetic */ ExprRef N(ThisEscapeAnalyzer thisEscapeAnalyzer, ThisRef thisRef) {
        return new ExprRef(thisEscapeAnalyzer.depth, thisRef);
    }

    public static /* synthetic */ Stream P(Optional optional) {
        return optional.isPresent() ? Stream.of((ThisRef) optional.get()) : Stream.empty();
    }

    public static /* synthetic */ ExprRef Q(ThisEscapeAnalyzer thisEscapeAnalyzer, VarRef varRef) {
        return new ExprRef(thisEscapeAnalyzer.depth, varRef);
    }

    public static /* synthetic */ Ref R(JCTree.JCEnhancedForLoop jCEnhancedForLoop, ExprRef exprRef) {
        return new VarRef(jCEnhancedForLoop.var.sym, exprRef);
    }

    public static /* synthetic */ ExprRef S(ThisEscapeAnalyzer thisEscapeAnalyzer, ThisRef thisRef) {
        return new ExprRef(thisEscapeAnalyzer.depth, thisRef);
    }

    public static /* synthetic */ boolean U(Indirection indirection) {
        return indirection != Indirection.OUTER;
    }

    public static /* synthetic */ Set V(Symbol.ModuleSymbol moduleSymbol) {
        return (Set) moduleSymbol.exports.stream().map(new Function() { // from class: kde
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Directive.ExportsDirective) obj).getPackage();
            }
        }).collect(Collectors.toSet());
    }

    public static /* synthetic */ Stream W(Optional optional) {
        return optional.isPresent() ? Stream.of((ExprRef) optional.get()) : Stream.empty();
    }

    public static /* synthetic */ ExprRef X(ThisEscapeAnalyzer thisEscapeAnalyzer, ThisRef thisRef) {
        return new ExprRef(thisEscapeAnalyzer.depth, thisRef);
    }

    public static /* synthetic */ void Y(ThisEscapeAnalyzer thisEscapeAnalyzer, MethodInfo methodInfo) {
        thisEscapeAnalyzer.getClass();
        thisEscapeAnalyzer.scan(methodInfo.declaration.body);
    }

    public static /* synthetic */ ExprRef a0(int i, ReturnRef returnRef) {
        return new ExprRef(i, returnRef);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void analyzeConstructor(final MethodInfo methodInfo) {
        boolean z = true;
        Assert.check(this.targetClass == null);
        Assert.check(this.currentMethod == null);
        Assert.check(this.depth == -1);
        Assert.check(this.refs == null);
        this.targetClass = methodInfo.declaringClass;
        this.currentMethod = methodInfo;
        try {
            RefSet<Ref> refSetNewEmpty = RefSet.newEmpty();
            this.refs = refSetNewEmpty;
            refSetNewEmpty.add(new ThisRef(this.targetClass.sym, EnumSet.of(Indirection.DIRECT)));
            visitScoped(false, new Runnable() { // from class: com.sun.tools.javac.comp.d4
                @Override // java.lang.Runnable
                public final void run() {
                    ThisEscapeAnalyzer.Y(this.b, methodInfo);
                }
            });
        } finally {
            Assert.check(this.depth == -1);
            this.currentMethod = null;
            this.targetClass = null;
            this.refs = null;
        }
    }

    private void analyzeInitializer(JCTree.JCMethodInvocation jCMethodInvocation, JCTree jCTree, RefSet<ThisRef> refSet, Runnable runnable) {
        RefSet<Ref> refSet2 = this.refs;
        this.refs = RefSet.newEmpty();
        int i = this.depth;
        this.depth = 0;
        this.callStack.add(new StackFrame(this.currentMethod, jCTree, jCMethodInvocation));
        try {
            this.refs.addAll(refSet);
            runnable.run();
        } finally {
            ArrayList<StackFrame> arrayList = this.callStack;
            arrayList.remove(arrayList.size() - 1);
            this.depth = i;
            this.refs = refSet2;
        }
    }

    public static /* synthetic */ boolean c(Warning warning) {
        return !warning.isSuppressed();
    }

    public static /* synthetic */ Stream c0(Optional optional) {
        return optional.isPresent() ? Stream.of((ExprRef) optional.get()) : Stream.empty();
    }

    private boolean checkInvariants(boolean z, final boolean z2) {
        Assert.check(z == isAnalyzing());
        if (isAnalyzing()) {
            Assert.check(this.currentMethod != null);
            Assert.check(this.targetClass != null);
            Assert.check(this.refs != null);
            Assert.check(this.depth >= 0);
            Assert.check(this.refs.find(ExprRef.class).allMatch(new Predicate() { // from class: com.sun.tools.javac.comp.a4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.h(this.b, z2, (ThisEscapeAnalyzer.ExprRef) obj);
                }
            }));
        } else {
            Assert.check(this.targetClass == null);
            Assert.check(this.refs == null);
            Assert.check(this.depth == -1);
            Assert.check(this.callStack.isEmpty());
            Assert.check(this.invocations.isEmpty());
        }
        return true;
    }

    public static /* synthetic */ Stream d0(Optional optional) {
        return optional.isPresent() ? Stream.of((ThisRef) optional.get()) : Stream.empty();
    }

    private void doAnalyzeTree(Env<AttrContext> env) {
        Assert.check(checkInvariants(false, false));
        Assert.check(this.methodMap.isEmpty());
        if (this.lintMapper.lintAt(env.toplevel.sourcefile, env.tree.pos()).get().isEnabled(Lint.LintCategory.THIS_ESCAPE)) {
            final Set set = (Set) Optional.ofNullable(env.toplevel.modle).filter(new Predicate() { // from class: wce
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.J(this.b, (Symbol.ModuleSymbol) obj);
                }
            }).filter(new Predicate() { // from class: xce
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.C(this.b, (Symbol.ModuleSymbol) obj);
                }
            }).map(new Function() { // from class: yce
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.V((Symbol.ModuleSymbol) obj);
                }
            }).orElse(null);
            new TreeScanner(this) { // from class: com.sun.tools.javac.comp.ThisEscapeAnalyzer.1
                private JCTree.JCClassDecl currentClass;
                private boolean nonPublicOuter;
                final /* synthetic */ ThisEscapeAnalyzer this$0;

                {
                    this.this$0 = this;
                }

                private boolean currentClassIsExternallyExtendable() {
                    if (this.currentClass.sym.isFinal() || !this.currentClass.sym.isPublic()) {
                        return false;
                    }
                    Set set2 = set;
                    return ((set2 != null && !set2.contains(this.currentClass.sym.packge())) || this.currentClass.sym.isSealed() || this.currentClass.sym.isDirectlyOrIndirectlyLocal() || this.nonPublicOuter) ? false : true;
                }

                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
                    JCTree.JCClassDecl jCClassDecl2 = this.currentClass;
                    boolean z = this.nonPublicOuter;
                    try {
                        this.currentClass = jCClassDecl;
                        boolean zIsAnonymous = jCClassDecl.sym.isAnonymous() | z;
                        this.nonPublicOuter = zIsAnonymous;
                        boolean z2 = zIsAnonymous | ((jCClassDecl.mods.flags & 1) == 0);
                        this.nonPublicOuter = z2;
                        if (z2) {
                            this.this$0.nonPublicOuters.add(this.currentClass.sym);
                        }
                        super.visitClassDef(jCClassDecl);
                    } finally {
                        this.currentClass = jCClassDecl2;
                        this.nonPublicOuter = z;
                    }
                }

                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
                    boolean zIsConstructor = TreeInfo.isConstructor(jCMethodDecl);
                    boolean zCurrentClassIsExternallyExtendable = currentClassIsExternallyExtendable();
                    this.this$0.methodMap.put(jCMethodDecl.sym, new MethodInfo(this.currentClass, jCMethodDecl, zIsConstructor, zCurrentClassIsExternallyExtendable && zIsConstructor && (((jCMethodDecl.sym.flags() & 5) > 0L ? 1 : ((jCMethodDecl.sym.flags() & 5) == 0L ? 0 : -1)) != 0), !zCurrentClassIsExternallyExtendable || zIsConstructor || (((jCMethodDecl.mods.flags & 26) > 0L ? 1 : ((jCMethodDecl.mods.flags & 26) == 0L ? 0 : -1)) != 0)));
                    super.visitMethodDef(jCMethodDecl);
                }
            }.scan(env.tree);
            this.methodMap.values().stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.o3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((ThisEscapeAnalyzer.MethodInfo) obj).analyzable();
                }
            }).forEach(new Consumer() { // from class: com.sun.tools.javac.comp.p3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.analyzeConstructor((ThisEscapeAnalyzer.MethodInfo) obj);
                }
            });
            filterWarnings(new Predicate() { // from class: com.sun.tools.javac.comp.q3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.c((ThisEscapeAnalyzer.Warning) obj);
                }
            });
            this.warningList.forEach(new Consumer() { // from class: com.sun.tools.javac.comp.r3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ThisEscapeAnalyzer.Warning) obj).trimInitializerFrames();
                }
            });
            this.warningList.sort(new Comparator() { // from class: com.sun.tools.javac.comp.s3
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ThisEscapeAnalyzer.Warning.sortByStackFrames((ThisEscapeAnalyzer.Warning) obj, (ThisEscapeAnalyzer.Warning) obj2);
                }
            });
            final AtomicReference atomicReference = new AtomicReference();
            filterWarnings(new Predicate() { // from class: com.sun.tools.javac.comp.t3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.f0(atomicReference, (ThisEscapeAnalyzer.Warning) obj);
                }
            });
            final HashSet hashSet = new HashSet();
            filterWarnings(new Predicate() { // from class: com.sun.tools.javac.comp.u3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return hashSet.add(((ThisEscapeAnalyzer.Warning) obj).origin);
                }
            });
            for (Warning warning : this.warningList) {
                JCDiagnostic.LintWarning lintWarning = CompilerProperties.LintWarnings.PossibleThisEscape;
                Iterator<StackFrame> it = warning.stack.iterator();
                while (it.hasNext()) {
                    this.log.warning(it.next().warningPos(), lintWarning);
                    lintWarning = CompilerProperties.LintWarnings.PossibleThisEscapeLocation;
                }
            }
            this.warningList.clear();
        }
    }

    public static /* synthetic */ ExprRef e(ThisEscapeAnalyzer thisEscapeAnalyzer, ExprRef exprRef) {
        return new ExprRef(thisEscapeAnalyzer.depth - 1, exprRef);
    }

    public static /* synthetic */ boolean f0(AtomicReference atomicReference, Warning warning) {
        Warning warning2 = (Warning) atomicReference.get();
        if (warning2 != null && warning.isRedundantWith(warning2)) {
            return false;
        }
        atomicReference.set(warning);
        return true;
    }

    private void filterWarnings(Predicate<Warning> predicate) {
        int i = 0;
        for (Warning warning : this.warningList) {
            if (predicate.test(warning)) {
                this.warningList.set(i, warning);
                i++;
            }
        }
        ArrayList<Warning> arrayList = this.warningList;
        arrayList.subList(i, arrayList.size()).clear();
    }

    public static /* synthetic */ Ref g(Symbol.VarSymbol varSymbol, ExprRef exprRef) {
        return new VarRef(varSymbol, exprRef);
    }

    public static /* synthetic */ boolean h(ThisEscapeAnalyzer thisEscapeAnalyzer, boolean z, ExprRef exprRef) {
        thisEscapeAnalyzer.getClass();
        return z && exprRef.depth <= thisEscapeAnalyzer.depth;
    }

    private boolean hasImplicitOuterInstance(Symbol.TypeSymbol typeSymbol) {
        Symbol.ClassSymbol classSymbol = this.currentMethod.declaringClass.sym;
        return typeSymbol != classSymbol && typeSymbol.hasOuterInstance() && typeSymbol.isEnclosedBy(classSymbol);
    }

    public static /* synthetic */ Stream i(Optional optional) {
        return optional.isPresent() ? Stream.of((ExprRef) optional.get()) : Stream.empty();
    }

    public static ThisEscapeAnalyzer instance(Context context) {
        ThisEscapeAnalyzer thisEscapeAnalyzer = (ThisEscapeAnalyzer) context.get(contextKey);
        return thisEscapeAnalyzer == null ? new ThisEscapeAnalyzer(context) : thisEscapeAnalyzer;
    }

    private void invoke(JCTree jCTree, final Symbol symbol, List<JCTree.JCExpression> list, RefSet<ThisRef> refSet) {
        if (symbol != null) {
            Symbol symbol2 = symbol.owner;
            if (symbol2.kind == Kinds.Kind.TYP && symbol2.type.tsym == this.syms.objectType.tsym && symbol.isFinal()) {
                return;
            }
        }
        MethodInfo methodInfoOrElse = this.methodMap.get(symbol);
        if (methodInfoOrElse == null && refSet.size() == 1) {
            final ThisRef next = refSet.iterator().next();
            methodInfoOrElse = this.methodMap.values().stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.q4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.j(this.b, symbol, next, (ThisEscapeAnalyzer.MethodInfo) obj);
                }
            }).findFirst().orElse(null);
        }
        if (methodInfoOrElse == null || !methodInfoOrElse.invokable) {
            invokeUnknown(jCTree, list, refSet);
        } else {
            invokeInvokable(jCTree, list, refSet, methodInfoOrElse);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void invokeInvokable(JCTree jCTree, List<JCTree.JCExpression> list, RefSet<ThisRef> refSet, MethodInfo methodInfo) {
        Assert.check(methodInfo.invokable);
        JCTree.JCMethodDecl jCMethodDecl = methodInfo.declaration;
        RefSet refSetNewEmpty = RefSet.newEmpty();
        List list2 = jCMethodDecl.params;
        List list3 = list;
        while (list3.nonEmpty() && list2.nonEmpty()) {
            final Symbol.VarSymbol varSymbol = ((JCTree.JCVariableDecl) list2.head).sym;
            scan((JCTree) list3.head);
            Stream<R> map = this.refs.removeExprs(this.depth).map(new Function() { // from class: com.sun.tools.javac.comp.i4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.m(varSymbol, (ThisEscapeAnalyzer.ExprRef) obj);
                }
            });
            Objects.requireNonNull(refSetNewEmpty);
            map.forEach(new x3(refSetNewEmpty));
            List list4 = list3.tail;
            list2 = list2.tail;
            list3 = list4;
        }
        MethodInfo methodInfo2 = this.currentMethod;
        this.currentMethod = methodInfo;
        RefSet<Ref> refSet2 = this.refs;
        this.refs = RefSet.newEmpty();
        final int i = this.depth;
        this.depth = 0;
        this.callStack.add(new StackFrame(methodInfo2, null, jCTree));
        try {
            this.refs.addAll(refSet);
            this.refs.addAll(refSetNewEmpty);
            if (!this.refs.isEmpty()) {
                Pair<JCTree.JCMethodDecl, RefSet<Ref>> pairOf = Pair.of(methodInfo.declaration, this.refs.clone());
                if (this.invocations.add(pairOf)) {
                    try {
                        scan(jCMethodDecl.body);
                        this.invocations.remove(pairOf);
                        if (TreeInfo.isConstructor(methodInfo.declaration)) {
                            Stream map2 = this.refs.remove(ThisRef.class).map(new Function() { // from class: com.sun.tools.javac.comp.j4
                                @Override // java.util.function.Function
                                public final Object apply(Object obj) {
                                    return new ThisEscapeAnalyzer.ReturnRef((ThisEscapeAnalyzer.ThisRef) obj);
                                }
                            });
                            final RefSet<Ref> refSet3 = this.refs;
                            Objects.requireNonNull(refSet3);
                            map2.forEach(new Consumer() { // from class: com.sun.tools.javac.comp.k4
                                @Override // java.util.function.Consumer
                                public final void accept(Object obj) {
                                    refSet3.add((ThisEscapeAnalyzer.ReturnRef) obj);
                                }
                            });
                        }
                        Stream map3 = this.refs.remove(ReturnRef.class).map(new Function() { // from class: com.sun.tools.javac.comp.l4
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return ThisEscapeAnalyzer.a0(i, (ThisEscapeAnalyzer.ReturnRef) obj);
                            }
                        });
                        Objects.requireNonNull(refSet2);
                        map3.forEach(new e4(refSet2));
                    } catch (Throwable th) {
                        this.invocations.remove(pairOf);
                        throw th;
                    }
                }
            }
            ArrayList<StackFrame> arrayList = this.callStack;
            arrayList.remove(arrayList.size() - 1);
            this.depth = i;
            this.refs = refSet2;
            this.currentMethod = methodInfo2;
        } catch (Throwable th2) {
            ArrayList<StackFrame> arrayList2 = this.callStack;
            arrayList2.remove(arrayList2.size() - 1);
            this.depth = i;
            this.refs = refSet2;
            this.currentMethod = methodInfo2;
            throw th2;
        }
    }

    private void invokeUnknown(JCTree jCTree, List<JCTree.JCExpression> list, RefSet<ThisRef> refSet) {
        if (refSet.stream().anyMatch(new Predicate() { // from class: com.sun.tools.javac.comp.t4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.triggersUnknownInvokeLeak((ThisEscapeAnalyzer.ThisRef) obj);
            }
        })) {
            leakAt(jCTree);
        }
        for (JCTree.JCExpression jCExpression : list) {
            scan(jCExpression);
            if (this.refs.removeExprs(this.depth).anyMatch(new Predicate() { // from class: com.sun.tools.javac.comp.u4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.triggersUnknownInvokeLeak((ThisEscapeAnalyzer.ExprRef) obj);
                }
            })) {
                leakAt(jCExpression);
            }
        }
        if (jCTree.hasTag(JCTree.Tag.NEWCLASS)) {
            Stream<R> map = refSet.stream().map(new Function() { // from class: com.sun.tools.javac.comp.v4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.w(this.b, (ThisEscapeAnalyzer.ThisRef) obj);
                }
            });
            RefSet<Ref> refSet2 = this.refs;
            Objects.requireNonNull(refSet2);
            map.forEach(new e4(refSet2));
        }
    }

    private boolean isAnalyzing() {
        return this.targetClass != null;
    }

    private boolean isExplicitOuterThisReference(Types types, Type.ClassType classType, JCTree.JCFieldAccess jCFieldAccess) {
        Type typeErasure = types.erasure(jCFieldAccess.selected.type);
        if (!typeErasure.hasTag(TypeTag.CLASS)) {
            return false;
        }
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) classType.tsym;
        return jCFieldAccess.name == this.names._this && classSymbol.hasOuterInstance() && classSymbol.owner.enclClass() == ((Symbol.ClassSymbol) typeErasure.tsym);
    }

    private boolean isParamOrVar(Symbol symbol) {
        if (symbol == null) {
            return false;
        }
        Kinds.Kind kind = symbol.kind;
        Kinds.Kind kind2 = Kinds.Kind.VAR;
        if (kind != kind2) {
            return false;
        }
        Kinds.Kind kind3 = symbol.owner.kind;
        return kind3 == Kinds.Kind.MTH || kind3 == kind2;
    }

    private boolean isTargetMethod(MethodInfo methodInfo, Symbol symbol, Symbol.TypeSymbol typeSymbol) {
        return symbol.kind == Kinds.Kind.MTH && methodInfo.declaration.name == symbol.name && methodInfo.declaringClass.sym == typeSymbol && !methodInfo.declaration.sym.isConstructor() && (methodInfo.declaration.sym.flags() & 8) == 0 && methodInfo.declaration.sym.overrides(symbol, typeSymbol, this.types, false);
    }

    public static /* synthetic */ boolean j(ThisEscapeAnalyzer thisEscapeAnalyzer, Symbol symbol, ThisRef thisRef, MethodInfo methodInfo) {
        thisEscapeAnalyzer.getClass();
        return thisEscapeAnalyzer.isTargetMethod(methodInfo, symbol, thisRef.tsym);
    }

    public static /* synthetic */ void l(ThisEscapeAnalyzer thisEscapeAnalyzer, JCTree.JCSwitch jCSwitch) {
        thisEscapeAnalyzer.getClass();
        thisEscapeAnalyzer.scan(jCSwitch.selector);
        thisEscapeAnalyzer.refs.discardExprs(thisEscapeAnalyzer.depth);
        thisEscapeAnalyzer.scan(jCSwitch.cases);
    }

    private void leakAt(JCTree jCTree) {
        this.callStack.add(new StackFrame(this.currentMethod, null, jCTree));
        this.warningList.add(new Warning(this.targetClass, new ArrayList(this.callStack)));
        ArrayList<StackFrame> arrayList = this.callStack;
        arrayList.remove(arrayList.size() - 1);
    }

    public static /* synthetic */ VarRef m(Symbol.VarSymbol varSymbol, ExprRef exprRef) {
        return new VarRef(varSymbol, exprRef);
    }

    public static /* synthetic */ void n(final ThisEscapeAnalyzer thisEscapeAnalyzer, final JCTree.JCLambda jCLambda) {
        thisEscapeAnalyzer.getClass();
        thisEscapeAnalyzer.visitScoped(true, new Runnable() { // from class: qce
            @Override // java.lang.Runnable
            public final void run() {
                ThisEscapeAnalyzer.r(this.b, jCLambda);
            }
        });
    }

    public static /* synthetic */ void o(ThisEscapeAnalyzer thisEscapeAnalyzer, Consumer consumer, JCTree jCTree) {
        HashSet hashSetClone;
        do {
            hashSetClone = thisEscapeAnalyzer.refs.clone();
            consumer.accept(jCTree);
        } while (!thisEscapeAnalyzer.refs.equals(hashSetClone));
    }

    private void popScope() {
        Assert.check(this.depth >= 0);
        this.refs.discardExprs(this.depth);
        this.depth--;
    }

    private void pushScope() {
        this.depth++;
    }

    public static /* synthetic */ void r(ThisEscapeAnalyzer thisEscapeAnalyzer, JCTree.JCLambda jCLambda) {
        thisEscapeAnalyzer.getClass();
        thisEscapeAnalyzer.scan(jCLambda.body);
    }

    private RefSet<ThisRef> receiverRefsForConstructor(final JCTree.JCExpression jCExpression, final Symbol.TypeSymbol typeSymbol) {
        if (jCExpression == null) {
            return hasImplicitOuterInstance(typeSymbol) ? (RefSet) this.refs.find(ThisRef.class).map(new Function() { // from class: com.sun.tools.javac.comp.e3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((ThisEscapeAnalyzer.ThisRef) obj).toOuter(typeSymbol);
                }
            }).flatMap(new Function() { // from class: sce
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.d0((Optional) obj);
                }
            }).collect(RefSet.collector()) : RefSet.newEmpty();
        }
        scan(jCExpression);
        return (RefSet) this.refs.removeExprs(this.depth).map(new Function() { // from class: com.sun.tools.javac.comp.d3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ThisEscapeAnalyzer.ExprRef) obj).toOuter(jCExpression.type.tsym);
            }
        }).flatMap(new Function() { // from class: rce
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ThisEscapeAnalyzer.P((Optional) obj);
            }
        }).collect(RefSet.collector());
    }

    public static /* synthetic */ Stream s(Optional optional) {
        return optional.isPresent() ? Stream.of((ExprRef) optional.get()) : Stream.empty();
    }

    public static /* synthetic */ void t(final ThisEscapeAnalyzer thisEscapeAnalyzer, JCTree.JCMethodInvocation jCMethodInvocation, RefSet refSet, JCTree jCTree) {
        thisEscapeAnalyzer.getClass();
        Objects.requireNonNull(jCTree);
        if (jCTree instanceof JCTree.JCBlock) {
            final JCTree.JCBlock jCBlock = (JCTree.JCBlock) jCTree;
            thisEscapeAnalyzer.analyzeInitializer(jCMethodInvocation, jCBlock, refSet, new Runnable() { // from class: bde
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.visitBlock(jCBlock);
                }
            });
        } else if (jCTree instanceof JCTree.JCVariableDecl) {
            final JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) jCTree;
            thisEscapeAnalyzer.analyzeInitializer(jCMethodInvocation, jCVariableDecl, refSet, new Runnable() { // from class: cde
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.scan(jCVariableDecl);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean triggersUnknownInvokeLeak(Ref ref) {
        return !this.nonPublicOuters.contains(ref.tsym) || ref.indirections.stream().anyMatch(new Predicate() { // from class: com.sun.tools.javac.comp.f4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ThisEscapeAnalyzer.U((ThisEscapeAnalyzer.Indirection) obj);
            }
        });
    }

    public static /* synthetic */ Stream u(Optional optional) {
        return optional.isPresent() ? Stream.of((ExprRef) optional.get()) : Stream.empty();
    }

    private <T extends JCTree> void visitDeferred(Runnable runnable) {
        int size = this.warningList.size();
        RefSet<T> refSetClone = this.refs.clone();
        try {
            runnable.run();
            boolean zDiscardExprs = (this.warningList.size() > size) | this.refs.discardExprs(this.depth);
            this.refs = refSetClone;
            ArrayList<Warning> arrayList = this.warningList;
            arrayList.subList(size, arrayList.size()).clear();
            if (zDiscardExprs) {
                this.refs.add(new ExprRef(this.depth, this.syms.objectType.tsym, EnumSet.of(Indirection.INDIRECT)));
            }
        } catch (Throwable th) {
            this.refs = refSetClone;
            ArrayList<Warning> arrayList2 = this.warningList;
            arrayList2.subList(size, arrayList2.size()).clear();
            throw th;
        }
    }

    private <T extends JCTree> void visitLooped(final T t, final Consumer<T> consumer) {
        visitScoped(false, new Runnable() { // from class: pce
            @Override // java.lang.Runnable
            public final void run() {
                ThisEscapeAnalyzer.o(this.b, consumer, t);
            }
        });
    }

    private void visitScoped(boolean z, Runnable runnable) {
        pushScope();
        try {
            Assert.check(checkInvariants(true, false));
            runnable.run();
            Assert.check(checkInvariants(true, z));
            if (z) {
                Assert.check(this.depth > 0);
                Stream<R> map = this.refs.removeExprs(this.depth).map(new Function() { // from class: com.sun.tools.javac.comp.s4
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ThisEscapeAnalyzer.e(this.b, (ThisEscapeAnalyzer.ExprRef) obj);
                    }
                });
                RefSet<Ref> refSet = this.refs;
                Objects.requireNonNull(refSet);
                map.forEach(new e4(refSet));
            }
        } finally {
            popScope();
        }
    }

    private void visitVarDef(final Symbol.VarSymbol varSymbol, JCTree.JCExpression jCExpression) {
        scan(jCExpression);
        boolean zIsParamOrVar = isParamOrVar(varSymbol);
        RefSet<Ref> refSet = this.refs;
        if (zIsParamOrVar) {
            refSet.replaceExprs(this.depth, new Function() { // from class: com.sun.tools.javac.comp.o4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.G(varSymbol, (ThisEscapeAnalyzer.ExprRef) obj);
                }
            });
        } else {
            refSet.discardExprs(this.depth);
        }
    }

    public static /* synthetic */ ExprRef w(ThisEscapeAnalyzer thisEscapeAnalyzer, ThisRef thisRef) {
        return new ExprRef(thisEscapeAnalyzer.depth, thisRef);
    }

    public static /* synthetic */ void x(ThisEscapeAnalyzer thisEscapeAnalyzer, final Type type, C1ForeachMethods c1ForeachMethods, final JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        thisEscapeAnalyzer.getClass();
        thisEscapeAnalyzer.scan(jCEnhancedForLoop.expr);
        if (type != null) {
            boolean zIsParamOrVar = thisEscapeAnalyzer.isParamOrVar(jCEnhancedForLoop.var.sym);
            RefSet<Ref> refSet = thisEscapeAnalyzer.refs;
            int i = thisEscapeAnalyzer.depth;
            if (zIsParamOrVar) {
                Stream map = refSet.removeExprs(i).map(new Function() { // from class: com.sun.tools.javac.comp.v3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((ThisEscapeAnalyzer.ExprRef) obj).toIndirect(type.tsym);
                    }
                }).flatMap(new Function() { // from class: zce
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ThisEscapeAnalyzer.W((Optional) obj);
                    }
                }).map(new Function() { // from class: com.sun.tools.javac.comp.w3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ThisEscapeAnalyzer.M(jCEnhancedForLoop, (ThisEscapeAnalyzer.ExprRef) obj);
                    }
                });
                RefSet<Ref> refSet2 = thisEscapeAnalyzer.refs;
                Objects.requireNonNull(refSet2);
                map.forEach(new x3(refSet2));
            } else {
                refSet.discardExprs(i);
            }
        } else {
            RefSet<Ref> refSet3 = thisEscapeAnalyzer.refs;
            int i2 = thisEscapeAnalyzer.depth;
            if (c1ForeachMethods != null) {
                thisEscapeAnalyzer.invoke(jCEnhancedForLoop.expr, c1ForeachMethods.iterator, List.nil(), (RefSet) refSet3.removeExprs(i2).map(new y3()).collect(RefSet.collector()));
                RefSet<ThisRef> refSet4 = (RefSet) thisEscapeAnalyzer.refs.removeExprs(thisEscapeAnalyzer.depth).map(new y3()).collect(RefSet.collector());
                thisEscapeAnalyzer.invoke(jCEnhancedForLoop.expr, c1ForeachMethods.hasNext, List.nil(), refSet4);
                thisEscapeAnalyzer.refs.discardExprs(thisEscapeAnalyzer.depth);
                thisEscapeAnalyzer.invoke(jCEnhancedForLoop.expr, c1ForeachMethods.next, List.nil(), refSet4);
                boolean zIsParamOrVar2 = thisEscapeAnalyzer.isParamOrVar(jCEnhancedForLoop.var.sym);
                RefSet<Ref> refSet5 = thisEscapeAnalyzer.refs;
                int i3 = thisEscapeAnalyzer.depth;
                if (zIsParamOrVar2) {
                    refSet5.replaceExprs(i3, new Function() { // from class: com.sun.tools.javac.comp.z3
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ThisEscapeAnalyzer.R(jCEnhancedForLoop, (ThisEscapeAnalyzer.ExprRef) obj);
                        }
                    });
                } else {
                    refSet5.discardExprs(i3);
                }
            } else {
                refSet3.discardExprs(i2);
            }
        }
        thisEscapeAnalyzer.scan(jCEnhancedForLoop.body);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void analyzeTree(Env<AttrContext> env) {
        this.topLevelEnv = env;
        try {
            doAnalyzeTree(env);
        } finally {
            this.topLevelEnv = null;
            this.methodMap.clear();
            this.nonPublicOuters.clear();
            this.targetClass = null;
            this.warningList.clear();
            this.currentMethod = null;
            this.callStack.clear();
            this.invocations.clear();
            this.depth = -1;
            this.refs = null;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner
    public void scan(JCTree jCTree) {
        if (jCTree == null || jCTree.type == Type.stuckType) {
            return;
        }
        boolean z = false;
        Assert.check(checkInvariants(true, false));
        switch (AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                z = true;
                break;
        }
        super.scan(jCTree);
        Assert.check(checkInvariants(true, z));
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitApply(final JCTree.JCMethodInvocation jCMethodInvocation) {
        Symbol symbolSymbolFor = TreeInfo.symbolFor(jCMethodInvocation.meth);
        scan(jCMethodInvocation.meth);
        final RefSet<ThisRef> refSetNewEmpty = RefSet.newEmpty();
        if (symbolSymbolFor == null || symbolSymbolFor.isStatic()) {
            this.refs.discardExprs(this.depth);
        } else {
            Stream<R> map = this.refs.removeExprs(this.depth).map(new y3());
            Objects.requireNonNull(refSetNewEmpty);
            map.forEach(new b4(refSetNewEmpty));
        }
        if (TreeInfo.name(jCMethodInvocation.meth) == this.names._super) {
            this.currentMethod.declaringClass.defs.stream().filter(new Predicate() { // from class: fde
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.B((JCTree) obj);
                }
            }).forEach(new Consumer() { // from class: com.sun.tools.javac.comp.h4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ThisEscapeAnalyzer.t(this.b, jCMethodInvocation, refSetNewEmpty, (JCTree) obj);
                }
            });
        } else {
            invoke(jCMethodInvocation, symbolSymbolFor, jCMethodInvocation.args, refSetNewEmpty);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssert(JCTree.JCAssert jCAssert) {
        scan(jCAssert.cond);
        this.refs.discardExprs(this.depth);
        scan(jCAssert.detail);
        this.refs.discardExprs(this.depth);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssign(JCTree.JCAssign jCAssign) {
        final Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) TreeInfo.symbolFor(jCAssign.lhs);
        scan(jCAssign.lhs);
        this.refs.discardExprs(this.depth);
        scan(jCAssign.rhs);
        boolean zIsParamOrVar = isParamOrVar(varSymbol);
        RefSet<Ref> refSet = this.refs;
        if (zIsParamOrVar) {
            refSet.replaceExprs(this.depth, new Function() { // from class: com.sun.tools.javac.comp.n4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.g(varSymbol, (ThisEscapeAnalyzer.ExprRef) obj);
                }
            });
        } else {
            refSet.discardExprs(this.depth);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
        scan(jCAssignOp.lhs);
        this.refs.discardExprs(this.depth);
        scan(jCAssignOp.rhs);
        this.refs.discardExprs(this.depth);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBinary(JCTree.JCBinary jCBinary) {
        scan(jCBinary.lhs);
        this.refs.discardExprs(this.depth);
        scan(jCBinary.rhs);
        this.refs.discardExprs(this.depth);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBlock(final JCTree.JCBlock jCBlock) {
        visitScoped(false, new Runnable() { // from class: uce
            @Override // java.lang.Runnable
            public final void run() {
                super/*com.sun.tools.javac.tree.TreeScanner*/.visitBlock(jCBlock);
            }
        });
        Assert.check(checkInvariants(true, false));
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitCase(JCTree.JCCase jCCase) {
        scan(jCCase.stats);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConditional(JCTree.JCConditional jCConditional) {
        scan(jCConditional.cond);
        this.refs.discardExprs(this.depth);
        RefSet refSetNewEmpty = RefSet.newEmpty();
        scan(jCConditional.truepart);
        Stream<ExprRef> streamRemoveExprs = this.refs.removeExprs(this.depth);
        Objects.requireNonNull(refSetNewEmpty);
        streamRemoveExprs.forEach(new e4(refSetNewEmpty));
        scan(jCConditional.falsepart);
        this.refs.removeExprs(this.depth).forEach(new e4(refSetNewEmpty));
        this.refs.addAll(refSetNewEmpty);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConstantCaseLabel(JCTree.JCConstantCaseLabel jCConstantCaseLabel) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
        visitLooped(jCDoWhileLoop, new Consumer() { // from class: gde
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                super/*com.sun.tools.javac.tree.TreeScanner*/.visitDoLoop((JCTree.JCDoWhileLoop) obj);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitExec(JCTree.JCExpressionStatement jCExpressionStatement) {
        scan(jCExpressionStatement.expr);
        this.refs.discardExprs(this.depth);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForLoop(JCTree.JCForLoop jCForLoop) {
        visitLooped(jCForLoop, new Consumer() { // from class: dde
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                super/*com.sun.tools.javac.tree.TreeScanner*/.visitForLoop((JCTree.JCForLoop) obj);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:14:0x007e  */
    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        Symbol.MethodSymbol methodSymbol;
        Symbol.MethodSymbol methodSymbol2;
        Symbol.MethodSymbol methodSymbol3;
        final Type typeElemtype = this.types.elemtype(jCEnhancedForLoop.expr.type);
        final C1ForeachMethods c1ForeachMethods = null;
        if (typeElemtype == null) {
            Symbol symbolResolveQualifiedMethod = this.rs.resolveQualifiedMethod(jCEnhancedForLoop.expr.pos(), this.topLevelEnv, jCEnhancedForLoop.expr.type, this.names.iterator, List.nil(), List.nil());
            if (symbolResolveQualifiedMethod instanceof Symbol.MethodSymbol) {
                methodSymbol = (Symbol.MethodSymbol) symbolResolveQualifiedMethod;
                Symbol symbolResolveQualifiedMethod2 = this.rs.resolveQualifiedMethod(jCEnhancedForLoop.expr.pos(), this.topLevelEnv, methodSymbol.getReturnType(), this.names.hasNext, List.nil(), List.nil());
                Symbol symbolResolveQualifiedMethod3 = this.rs.resolveQualifiedMethod(jCEnhancedForLoop.expr.pos(), this.topLevelEnv, methodSymbol.getReturnType(), this.names.next, List.nil(), List.nil());
                methodSymbol2 = symbolResolveQualifiedMethod2 instanceof Symbol.MethodSymbol ? (Symbol.MethodSymbol) symbolResolveQualifiedMethod2 : null;
                methodSymbol3 = symbolResolveQualifiedMethod3 instanceof Symbol.MethodSymbol ? (Symbol.MethodSymbol) symbolResolveQualifiedMethod3 : null;
            } else {
                methodSymbol = null;
                methodSymbol2 = null;
                methodSymbol3 = null;
            }
        } else {
            methodSymbol = null;
            methodSymbol2 = null;
            methodSymbol3 = null;
        }
        if (methodSymbol != null && methodSymbol2 != null && methodSymbol3 != null) {
            c1ForeachMethods = new C1ForeachMethods(methodSymbol, methodSymbol2, methodSymbol3);
        }
        visitLooped(jCEnhancedForLoop, new Consumer() { // from class: com.sun.tools.javac.comp.g4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ThisEscapeAnalyzer.x(this.b, typeElemtype, c1ForeachMethods, (JCTree.JCEnhancedForLoop) obj);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        Name name = jCIdent.name;
        Names names = this.names;
        if (name == names._this || name == names._super) {
            Stream map = this.refs.find(ThisRef.class).map(new Function() { // from class: com.sun.tools.javac.comp.i3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.X(this.b, (ThisEscapeAnalyzer.ThisRef) obj);
                }
            });
            RefSet<Ref> refSet = this.refs;
            Objects.requireNonNull(refSet);
            map.forEach(new e4(refSet));
            return;
        }
        boolean zIsParamOrVar = isParamOrVar(jCIdent.sym);
        Symbol symbol = jCIdent.sym;
        if (zIsParamOrVar) {
            final Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) symbol;
            Stream map2 = this.refs.find(VarRef.class, new Predicate() { // from class: com.sun.tools.javac.comp.j3
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.L(varSymbol, (ThisEscapeAnalyzer.VarRef) obj);
                }
            }).map(new Function() { // from class: com.sun.tools.javac.comp.k3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.Q(this.b, (ThisEscapeAnalyzer.VarRef) obj);
                }
            });
            RefSet<Ref> refSet2 = this.refs;
            Objects.requireNonNull(refSet2);
            map2.forEach(new e4(refSet2));
            return;
        }
        if (symbol.kind == Kinds.Kind.MTH && (symbol.flags() & 8) == 0) {
            Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) jCIdent.sym;
            Symbol.ClassSymbol classSymbol = this.currentMethod.declaringClass.sym;
            if (classSymbol.isSubClass(methodSymbol.owner, this.types)) {
                Stream map3 = this.refs.find(ThisRef.class).map(new Function() { // from class: com.sun.tools.javac.comp.l3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ThisEscapeAnalyzer.S(this.b, (ThisEscapeAnalyzer.ThisRef) obj);
                    }
                });
                RefSet<Ref> refSet3 = this.refs;
                Objects.requireNonNull(refSet3);
                map3.forEach(new e4(refSet3));
                return;
            }
            if (classSymbol.isEnclosedBy((Symbol.ClassSymbol) methodSymbol.owner)) {
                Stream streamFlatMap = this.refs.find(ThisRef.class).map(new Function() { // from class: com.sun.tools.javac.comp.m3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((ThisEscapeAnalyzer.ThisRef) obj).fromOuter(this.b.depth);
                    }
                }).flatMap(new Function() { // from class: vce
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ThisEscapeAnalyzer.i((Optional) obj);
                    }
                });
                RefSet<Ref> refSet4 = this.refs;
                Objects.requireNonNull(refSet4);
                streamFlatMap.forEach(new e4(refSet4));
            }
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIf(JCTree.JCIf jCIf) {
        scan(jCIf.cond);
        this.refs.discardExprs(this.depth);
        scan(jCIf.thenpart);
        scan(jCIf.elsepart);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIndexed(final JCTree.JCArrayAccess jCArrayAccess) {
        scan(jCArrayAccess.index);
        this.refs.discardExprs(this.depth);
        scan(jCArrayAccess.indexed);
        Stream streamFlatMap = this.refs.removeExprs(this.depth).map(new Function() { // from class: com.sun.tools.javac.comp.n3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ThisEscapeAnalyzer.ExprRef) obj).toDirect(jCArrayAccess.type.tsym);
            }
        }).flatMap(new Function() { // from class: ade
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ThisEscapeAnalyzer.s((Optional) obj);
            }
        });
        RefSet<Ref> refSet = this.refs;
        Objects.requireNonNull(refSet);
        streamFlatMap.forEach(new e4(refSet));
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLambda(final JCTree.JCLambda jCLambda) {
        visitDeferred(new Runnable() { // from class: ede
            @Override // java.lang.Runnable
            public final void run() {
                ThisEscapeAnalyzer.n(this.b, jCLambda);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLetExpr(final JCTree.LetExpr letExpr) {
        visitScoped(true, new Runnable() { // from class: jde
            @Override // java.lang.Runnable
            public final void run() {
                super/*com.sun.tools.javac.tree.TreeScanner*/.visitLetExpr(letExpr);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
        Assert.check(false);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModifiers(JCTree.JCModifiers jCModifiers) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewArray(final JCTree.JCNewArray jCNewArray) {
        RefSet refSetNewEmpty = RefSet.newEmpty();
        List list = jCNewArray.elems;
        if (list != null) {
            while (list.nonEmpty()) {
                scan((JCTree) list.head);
                Stream streamFlatMap = this.refs.removeExprs(this.depth).map(new Function() { // from class: com.sun.tools.javac.comp.w4
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((ThisEscapeAnalyzer.ExprRef) obj).toIndirect(jCNewArray.type.tsym);
                    }
                }).flatMap(new Function() { // from class: oce
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ThisEscapeAnalyzer.u((Optional) obj);
                    }
                });
                Objects.requireNonNull(refSetNewEmpty);
                streamFlatMap.forEach(new e4(refSetNewEmpty));
                list = list.tail;
            }
        }
        Stream<T> stream = refSetNewEmpty.stream();
        RefSet<Ref> refSet = this.refs;
        Objects.requireNonNull(refSet);
        stream.forEach(new e4(refSet));
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewClass(JCTree.JCNewClass jCNewClass) {
        MethodInfo methodInfo = this.methodMap.get(jCNewClass.constructor);
        JCTree.JCClassDecl jCClassDecl = jCNewClass.def;
        RefSet<ThisRef> refSetReceiverRefsForConstructor = receiverRefsForConstructor(jCNewClass.encl, jCClassDecl != null ? jCClassDecl.sym : jCNewClass.clazz.type.tsym);
        if (methodInfo == null || !methodInfo.invokable) {
            invokeUnknown(jCNewClass, jCNewClass.args, refSetReceiverRefsForConstructor);
        } else {
            invokeInvokable(jCNewClass, jCNewClass.args, refSetReceiverRefsForConstructor, methodInfo);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitPatternCaseLabel(JCTree.JCPatternCaseLabel jCPatternCaseLabel) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitRecordPattern(JCTree.JCRecordPattern jCRecordPattern) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReference(final JCTree.JCMemberReference jCMemberReference) {
        if (jCMemberReference.type.isErroneous()) {
            return;
        }
        scan(jCMemberReference.expr);
        final RefSet refSetNewEmpty = RefSet.newEmpty();
        switch (AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[jCMemberReference.kind.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                this.refs.discardExprs(this.depth);
                return;
            case 5:
            case 6:
                Stream<R> map = this.refs.removeExprs(this.depth).map(new y3());
                Objects.requireNonNull(refSetNewEmpty);
                map.forEach(new b4(refSetNewEmpty));
                break;
            case 7:
                RefSet<ThisRef> refSetReceiverRefsForConstructor = receiverRefsForConstructor(null, jCMemberReference.expr.type.tsym);
                Objects.requireNonNull(refSetNewEmpty);
                refSetReceiverRefsForConstructor.forEach(new b4(refSetNewEmpty));
                break;
            default:
                f63.a("non-exhaustive?");
                return;
        }
        visitDeferred(new Runnable() { // from class: com.sun.tools.javac.comp.c4
            @Override // java.lang.Runnable
            public final void run() {
                ThisEscapeAnalyzer.E(this.b, jCMemberReference, refSetNewEmpty);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReturn(JCTree.JCReturn jCReturn) {
        scan(jCReturn.expr);
        this.refs.replaceExprs(this.depth, new Function() { // from class: com.sun.tools.javac.comp.h3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new ThisEscapeAnalyzer.ReturnRef((ThisEscapeAnalyzer.ExprRef) obj);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
        scan(jCFieldAccess.selected);
        Stream<ExprRef> streamRemoveExprs = this.refs.removeExprs(this.depth);
        Type.ClassType classType = (Type.ClassType) this.currentMethod.declaringClass.sym.type;
        if (TreeInfo.isExplicitThisReference(this.types, classType, jCFieldAccess)) {
            Stream map = this.refs.find(ThisRef.class).map(new Function() { // from class: com.sun.tools.javac.comp.f3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.N(this.b, (ThisEscapeAnalyzer.ThisRef) obj);
                }
            });
            RefSet<Ref> refSet = this.refs;
            Objects.requireNonNull(refSet);
            map.forEach(new e4(refSet));
            return;
        }
        if (isExplicitOuterThisReference(this.types, classType, jCFieldAccess)) {
            Stream streamFlatMap = this.refs.find(ThisRef.class).map(new Function() { // from class: com.sun.tools.javac.comp.g3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((ThisEscapeAnalyzer.ThisRef) obj).fromOuter(this.b.depth);
                }
            }).flatMap(new Function() { // from class: tce
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.c0((Optional) obj);
                }
            });
            RefSet<Ref> refSet2 = this.refs;
            Objects.requireNonNull(refSet2);
            streamFlatMap.forEach(new e4(refSet2));
            return;
        }
        Symbol symbol = jCFieldAccess.sym;
        if (symbol.kind == Kinds.Kind.MTH && (symbol.flags() & 8) == 0) {
            RefSet<Ref> refSet3 = this.refs;
            Objects.requireNonNull(refSet3);
            streamRemoveExprs.forEach(new e4(refSet3));
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitch(final JCTree.JCSwitch jCSwitch) {
        visitScoped(false, new Runnable() { // from class: hde
            @Override // java.lang.Runnable
            public final void run() {
                ThisEscapeAnalyzer.l(this.b, jCSwitch);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitchExpression(final JCTree.JCSwitchExpression jCSwitchExpression) {
        visitScoped(true, new Runnable() { // from class: nce
            @Override // java.lang.Runnable
            public final void run() {
                ThisEscapeAnalyzer.K(this.b, jCSwitchExpression);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSynchronized(JCTree.JCSynchronized jCSynchronized) {
        scan(jCSynchronized.lock);
        this.refs.discardExprs(this.depth);
        scan(jCSynchronized.body);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitThrow(JCTree.JCThrow jCThrow) {
        scan(jCThrow.expr);
        if (this.refs.discardExprs(this.depth)) {
            leakAt(jCThrow);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeArray(JCTree.JCArrayTypeTree jCArrayTypeTree) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeBoundKind(JCTree.TypeBoundKind typeBoundKind) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeCast(final JCTree.JCTypeCast jCTypeCast) {
        scan(jCTypeCast.expr);
        this.refs.replaceExprs(this.depth, new Function() { // from class: com.sun.tools.javac.comp.m4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ThisEscapeAnalyzer.ExprRef) obj).withType(jCTypeCast.expr.type.tsym);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIntersection(JCTree.JCTypeIntersection jCTypeIntersection) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
        scan(jCInstanceOf.expr);
        this.refs.discardExprs(this.depth);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeUnion(JCTree.JCTypeUnion jCTypeUnion) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUnary(JCTree.JCUnary jCUnary) {
        scan(jCUnary.arg);
        this.refs.discardExprs(this.depth);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
        visitLooped(jCWhileLoop, new Consumer() { // from class: ide
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                super/*com.sun.tools.javac.tree.TreeScanner*/.visitWhileLoop((JCTree.JCWhileLoop) obj);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWildcard(JCTree.JCWildcard jCWildcard) {
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitYield(JCTree.JCYield jCYield) {
        scan(jCYield.value);
        this.refs.replaceExprs(this.depth, new Function() { // from class: com.sun.tools.javac.comp.r4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return new ThisEscapeAnalyzer.YieldRef((ThisEscapeAnalyzer.ExprRef) obj);
            }
        });
    }

    public static class ReturnRef extends Ref {
        public ReturnRef(Ref ref) {
            super(ref);
        }

        @Override // com.sun.tools.javac.comp.ThisEscapeAnalyzer.Ref
        public ReturnRef withType(Symbol.TypeSymbol typeSymbol) {
            return new ReturnRef(typeSymbol, this.indirections);
        }

        public ReturnRef(Symbol.TypeSymbol typeSymbol, EnumSet<Indirection> enumSet) {
            super(typeSymbol, enumSet);
        }
    }

    public static class ThisRef extends Ref {
        public ThisRef(Ref ref) {
            super(ref);
        }

        public static /* synthetic */ void e(EnumSet enumSet) {
            enumSet.remove(Indirection.OUTER);
            enumSet.remove(Indirection.INDIRECT);
            enumSet.add(Indirection.DIRECT);
        }

        public static /* synthetic */ ExprRef f(int i, Symbol.ClassSymbol classSymbol, ThisRef thisRef) {
            return new ExprRef(i, classSymbol, thisRef.modifiedIndirections(new Consumer() { // from class: com.sun.tools.javac.comp.s5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ThisEscapeAnalyzer.ThisRef.e((EnumSet) obj);
                }
            }));
        }

        public Optional<ExprRef> fromOuter(final int i) {
            final Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) Optional.of(this.tsym.owner).map(new Function() { // from class: lde
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Symbol) obj).enclClass();
                }
            }).orElse(null);
            return classSymbol == null ? Optional.empty() : Optional.of(this).filter(new Predicate() { // from class: com.sun.tools.javac.comp.q5
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((ThisEscapeAnalyzer.ThisRef) obj).indirections.contains(ThisEscapeAnalyzer.Indirection.OUTER);
                }
            }).map(new Function() { // from class: com.sun.tools.javac.comp.r5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.ThisRef.f(i, classSymbol, (ThisEscapeAnalyzer.ThisRef) obj);
                }
            });
        }

        @Override // com.sun.tools.javac.comp.ThisEscapeAnalyzer.Ref
        public ThisRef withType(Symbol.TypeSymbol typeSymbol) {
            return new ThisRef(typeSymbol, this.indirections);
        }

        public ThisRef(Symbol.TypeSymbol typeSymbol, EnumSet<Indirection> enumSet) {
            super(typeSymbol, enumSet);
        }
    }

    public static class YieldRef extends Ref {
        public YieldRef(Ref ref) {
            super(ref);
        }

        @Override // com.sun.tools.javac.comp.ThisEscapeAnalyzer.Ref
        public YieldRef withType(Symbol.TypeSymbol typeSymbol) {
            return new YieldRef(typeSymbol, this.indirections);
        }

        public YieldRef(Symbol.TypeSymbol typeSymbol, EnumSet<Indirection> enumSet) {
            super(typeSymbol, enumSet);
        }
    }

    public static class ExprRef extends Ref {
        final int depth;

        public ExprRef(int i, Ref ref) {
            super(ref);
            this.depth = i;
        }

        public static /* synthetic */ boolean d(ExprRef exprRef) {
            return exprRef.indirections.contains(Indirection.DIRECT) || exprRef.indirections.contains(Indirection.INDIRECT);
        }

        public static /* synthetic */ ExprRef e(ExprRef exprRef, Symbol.TypeSymbol typeSymbol, ExprRef exprRef2) {
            return new ExprRef(exprRef.depth, typeSymbol, exprRef2.modifiedIndirections(new Consumer() { // from class: com.sun.tools.javac.comp.c5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ThisEscapeAnalyzer.ExprRef.g((EnumSet) obj);
                }
            }));
        }

        public static /* synthetic */ void g(EnumSet enumSet) {
            enumSet.remove(Indirection.OUTER);
            enumSet.add(Indirection.DIRECT);
        }

        public static /* synthetic */ void h(EnumSet enumSet) {
            enumSet.remove(Indirection.DIRECT);
            enumSet.remove(Indirection.OUTER);
            enumSet.add(Indirection.INDIRECT);
        }

        public static /* synthetic */ ExprRef i(ExprRef exprRef, Symbol.TypeSymbol typeSymbol, ExprRef exprRef2) {
            return new ExprRef(exprRef.depth, typeSymbol, exprRef2.modifiedIndirections(new Consumer() { // from class: com.sun.tools.javac.comp.x4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ThisEscapeAnalyzer.ExprRef.h((EnumSet) obj);
                }
            }));
        }

        @Override // com.sun.tools.javac.comp.ThisEscapeAnalyzer.Ref
        public void addProperties(ArrayList<String> arrayList) {
            super.addProperties(arrayList);
            arrayList.add("depth=" + this.depth);
        }

        @Override // com.sun.tools.javac.comp.ThisEscapeAnalyzer.Ref
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return super.equals(obj) && this.depth == ((ExprRef) obj).depth;
        }

        @Override // com.sun.tools.javac.comp.ThisEscapeAnalyzer.Ref
        public int hashCode() {
            return Integer.hashCode(this.depth) ^ super.hashCode();
        }

        public Optional<ExprRef> toDirect(final Symbol.TypeSymbol typeSymbol) {
            return Optional.of(this).filter(new Predicate() { // from class: com.sun.tools.javac.comp.y4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((ThisEscapeAnalyzer.ExprRef) obj).indirections.contains(ThisEscapeAnalyzer.Indirection.INDIRECT);
                }
            }).map(new Function() { // from class: com.sun.tools.javac.comp.z4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.ExprRef.e(this.b, typeSymbol, (ThisEscapeAnalyzer.ExprRef) obj);
                }
            });
        }

        public Optional<ExprRef> toIndirect(final Symbol.TypeSymbol typeSymbol) {
            return Optional.of(this).filter(new Predicate() { // from class: com.sun.tools.javac.comp.a5
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.ExprRef.d((ThisEscapeAnalyzer.ExprRef) obj);
                }
            }).map(new Function() { // from class: com.sun.tools.javac.comp.b5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.ExprRef.i(this.b, typeSymbol, (ThisEscapeAnalyzer.ExprRef) obj);
                }
            });
        }

        @Override // com.sun.tools.javac.comp.ThisEscapeAnalyzer.Ref
        public ExprRef withType(Symbol.TypeSymbol typeSymbol) {
            return new ExprRef(this.depth, typeSymbol, this.indirections);
        }

        public ExprRef(int i, Symbol.TypeSymbol typeSymbol, EnumSet<Indirection> enumSet) {
            super(typeSymbol, enumSet);
            this.depth = i;
        }
    }

    public static class VarRef extends Ref {
        final Symbol.VarSymbol sym;

        public VarRef(Symbol.VarSymbol varSymbol, Ref ref) {
            super(ref);
            this.sym = varSymbol;
        }

        @Override // com.sun.tools.javac.comp.ThisEscapeAnalyzer.Ref
        public void addProperties(ArrayList<String> arrayList) {
            super.addProperties(arrayList);
            arrayList.add("sym=" + this.sym);
        }

        @Override // com.sun.tools.javac.comp.ThisEscapeAnalyzer.Ref
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (super.equals(obj)) {
                return Objects.equals(this.sym, ((VarRef) obj).sym);
            }
            return false;
        }

        @Override // com.sun.tools.javac.comp.ThisEscapeAnalyzer.Ref
        public int hashCode() {
            return Objects.hashCode(this.sym) ^ super.hashCode();
        }

        @Override // com.sun.tools.javac.comp.ThisEscapeAnalyzer.Ref
        public VarRef withType(Symbol.TypeSymbol typeSymbol) {
            return new VarRef(this.sym, typeSymbol, this.indirections);
        }

        public VarRef(Symbol.VarSymbol varSymbol, Symbol.TypeSymbol typeSymbol, EnumSet<Indirection> enumSet) {
            super(typeSymbol, enumSet);
            this.sym = varSymbol;
        }
    }

    public static abstract class Ref {
        final EnumSet<Indirection> indirections;
        final Symbol.TypeSymbol tsym;

        public Ref(Symbol.TypeSymbol typeSymbol, EnumSet<Indirection> enumSet) {
            Assert.check(typeSymbol != null);
            Assert.check(enumSet != null);
            this.tsym = typeSymbol;
            this.indirections = EnumSet.copyOf((EnumSet) enumSet);
        }

        public static /* synthetic */ ThisRef a(Symbol.TypeSymbol typeSymbol, Ref ref) {
            return new ThisRef(typeSymbol, ref.modifiedIndirections(new Consumer() { // from class: com.sun.tools.javac.comp.f5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ThisEscapeAnalyzer.Ref.b((EnumSet) obj);
                }
            }));
        }

        public static /* synthetic */ void b(EnumSet enumSet) {
            enumSet.remove(Indirection.DIRECT);
            enumSet.remove(Indirection.INDIRECT);
            enumSet.add(Indirection.OUTER);
        }

        public void addProperties(ArrayList<String> arrayList) {
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && obj.getClass() == getClass()) {
                Ref ref = (Ref) obj;
                if (this.tsym == ref.tsym && this.indirections.equals(ref.indirections)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return this.indirections.hashCode() ^ (getClass().hashCode() ^ this.tsym.hashCode());
        }

        public EnumSet<Indirection> modifiedIndirections(Consumer<? super EnumSet<Indirection>> consumer) {
            EnumSet<Indirection> enumSetCopyOf = EnumSet.copyOf((EnumSet) this.indirections);
            consumer.accept(enumSetCopyOf);
            Assert.check(!enumSetCopyOf.isEmpty());
            return enumSetCopyOf;
        }

        public Optional<ThisRef> toOuter(final Symbol.TypeSymbol typeSymbol) {
            return Optional.of(this).filter(new Predicate() { // from class: com.sun.tools.javac.comp.d5
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((ThisEscapeAnalyzer.Ref) obj).indirections.contains(ThisEscapeAnalyzer.Indirection.DIRECT);
                }
            }).map(new Function() { // from class: com.sun.tools.javac.comp.e5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ThisEscapeAnalyzer.Ref.a(typeSymbol, (ThisEscapeAnalyzer.Ref) obj);
                }
            });
        }

        public final String toString() {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("tsym=" + this.tsym);
            addProperties(arrayList);
            arrayList.add((String) this.indirections.stream().map(new Function() { // from class: com.sun.tools.javac.comp.g5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((ThisEscapeAnalyzer.Indirection) obj).name();
                }
            }).collect(Collectors.joining(",")));
            return getClass().getSimpleName() + "[" + ((String) arrayList.stream().collect(Collectors.joining(","))) + "]";
        }

        public abstract Ref withType(Symbol.TypeSymbol typeSymbol);

        public Ref(Ref ref) {
            this(ref.tsym, ref.indirections);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        visitVarDef(jCVariableDecl.sym, jCVariableDecl.init);
    }

    public static class RefSet<T extends Ref> extends HashSet<T> {
        private RefSet() {
            super(8);
        }

        public static /* synthetic */ boolean D(int i, Ref ref) {
            return (ref instanceof ExprRef) && ((ExprRef) ref).depth == i;
        }

        public static /* synthetic */ RefSet P() {
            return new RefSet();
        }

        public static /* synthetic */ boolean b(int i, ExprRef exprRef) {
            return exprRef.depth == i;
        }

        public static <T extends Ref> Collector<T, ?, RefSet<T>> collector() {
            return Collectors.toCollection(new Supplier() { // from class: com.sun.tools.javac.comp.m5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ThisEscapeAnalyzer.RefSet.P();
                }
            });
        }

        public static /* synthetic */ boolean d(int i, ExprRef exprRef) {
            return exprRef.depth == i;
        }

        public static /* synthetic */ boolean n(Ref ref) {
            return true;
        }

        public static <T extends Ref> RefSet<T> newEmpty() {
            return new RefSet<>();
        }

        public static /* synthetic */ boolean z(Ref ref) {
            return true;
        }

        @Override // java.util.HashSet
        public RefSet<T> clone() {
            return (RefSet) super.clone();
        }

        public boolean discardExprs(final int i) {
            return removeIf(new Predicate() { // from class: com.sun.tools.javac.comp.i5
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.RefSet.D(i, (ThisEscapeAnalyzer.Ref) obj);
                }
            });
        }

        public <T extends Ref> Stream<T> find(Class<T> cls, Predicate<? super T> predicate) {
            Stream<T> stream = stream();
            Objects.requireNonNull(cls);
            return ((java.util.List) stream.filter(new o5(cls)).map(new p5(cls)).filter(predicate).collect(Collectors.toList())).stream();
        }

        public Stream<ExprRef> findExprs(final int i) {
            return find(ExprRef.class, new Predicate() { // from class: com.sun.tools.javac.comp.l5
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.RefSet.d(i, (ThisEscapeAnalyzer.ExprRef) obj);
                }
            });
        }

        public <T extends Ref> Stream<T> remove(Class<T> cls, Predicate<? super T> predicate) {
            Stream<T> stream = stream();
            Objects.requireNonNull(cls);
            ArrayList arrayList = (ArrayList) stream.filter(new o5(cls)).map(new p5(cls)).filter(predicate).collect(Collectors.toCollection(new vef()));
            removeAll(arrayList);
            return arrayList.stream();
        }

        public Stream<ExprRef> removeExprs(final int i) {
            return remove(ExprRef.class, new Predicate() { // from class: com.sun.tools.javac.comp.k5
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.RefSet.b(i, (ThisEscapeAnalyzer.ExprRef) obj);
                }
            });
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void replaceExprs(int i, Function<? super ExprRef, ? extends T> function) {
            removeExprs(i).map(function).forEach(new Consumer() { // from class: com.sun.tools.javac.comp.h5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.add((ThisEscapeAnalyzer.Ref) obj);
                }
            });
        }

        public <T extends Ref> Stream<T> find(Class<T> cls) {
            return find(cls, new Predicate() { // from class: com.sun.tools.javac.comp.j5
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.RefSet.n((ThisEscapeAnalyzer.Ref) obj);
                }
            });
        }

        public <T extends Ref> Stream<T> remove(Class<T> cls) {
            return remove(cls, new Predicate() { // from class: com.sun.tools.javac.comp.n5
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ThisEscapeAnalyzer.RefSet.z((ThisEscapeAnalyzer.Ref) obj);
                }
            });
        }
    }
}

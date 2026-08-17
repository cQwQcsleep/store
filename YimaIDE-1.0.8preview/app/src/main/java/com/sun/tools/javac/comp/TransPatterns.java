package com.sun.tools.javac.comp;

import com.intellij.psi.PsiKeyword;
import com.sun.source.tree.CaseTree;
import com.sun.tools.javac.code.BoundKind;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.TransPatterns;
import com.sun.tools.javac.jvm.PoolConstant;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TransPatterns extends TreeTranslator {
    protected static final Context.Key<TransPatterns> transPatternsKey = new Context.Key<>();
    private final Attr attr;
    private Set<JCTree.JCMethodInvocation> deconstructorCalls;
    private Env<AttrContext> env;
    private TreeMaker make;
    private final Names names;
    private final Operators operators;
    private final Preview preview;
    private final Resolve rs;
    private final Symtab syms;
    private final Target target;
    private final Types types;
    BindingContext bindingContext = new BindingContext() { // from class: com.sun.tools.javac.comp.TransPatterns.1
        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public Symbol.VarSymbol bindingDeclared(Symbol.BindingSymbol bindingSymbol) {
            return null;
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public List<JCTree.JCStatement> bindingVars(int i) {
            return List.nil();
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public JCTree.JCExpression decorateExpression(JCTree.JCExpression jCExpression) {
            return jCExpression;
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public JCTree.JCStatement decorateStatement(JCTree.JCStatement jCStatement) {
            return jCStatement;
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public Symbol.VarSymbol getBindingFor(Symbol.BindingSymbol bindingSymbol) {
            return null;
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public BindingContext pop() {
            return this;
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public boolean tryPrepend(Symbol.BindingSymbol bindingSymbol, JCTree.JCVariableDecl jCVariableDecl) {
            return false;
        }
    };
    private Symbol.ClassSymbol currentClass = null;
    private JCTree.JCClassDecl currentClassTree = null;
    private ListBuffer<JCTree> pendingMethods = null;
    private Symbol.MethodSymbol currentMethodSym = null;
    private Symbol.VarSymbol currentValue = null;
    private Map<Symbol.RecordComponent, Symbol.MethodSymbol> component2Proxy = null;
    private int variableIndex = 0;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.TransPatterns$1AccummulatorResolver, reason: invalid class name */
    public interface C1AccummulatorResolver {
        void resolve(Symbol.VarSymbol varSymbol, JCTree.JCExpression jCExpression, Symbol.VarSymbol varSymbol2);
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.TransPatterns$4, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.INT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.LONG.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DOUBLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CLASS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public class BasicBindingContext extends BindingContext {
        Map<Symbol.BindingSymbol, Symbol.VarSymbol> hoistedVarMap;
        BindingContext parent;

        public BasicBindingContext() {
            super();
            this.parent = TransPatterns.this.bindingContext;
            this.hoistedVarMap = new LinkedHashMap();
        }

        public static /* synthetic */ Symbol.VarSymbol a(Map.Entry entry) {
            return (Symbol.VarSymbol) entry.getValue();
        }

        private JCTree.JCVariableDecl makeHoistedVarDecl(int i, Symbol.VarSymbol varSymbol) {
            return TransPatterns.this.make.at(i).VarDef(varSymbol, null);
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public Symbol.VarSymbol bindingDeclared(Symbol.BindingSymbol bindingSymbol) {
            Symbol.VarSymbol varSymbolBindingDeclared = this.parent.bindingDeclared(bindingSymbol);
            if (varSymbolBindingDeclared != null) {
                return varSymbolBindingDeclared;
            }
            Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(bindingSymbol.flags() & (-576460752303423489L), bindingSymbol.name, bindingSymbol.type, TransPatterns.this.currentMethodSym);
            varSymbol.setTypeAttributes(bindingSymbol.getRawTypeAttributes());
            this.hoistedVarMap.put(bindingSymbol, varSymbol);
            return varSymbol;
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public List<JCTree.JCStatement> bindingVars(int i) {
            if (this.hoistedVarMap.isEmpty()) {
                return List.nil();
            }
            ListBuffer listBuffer = new ListBuffer();
            for (Map.Entry<Symbol.BindingSymbol, Symbol.VarSymbol> entry : this.hoistedVarMap.entrySet()) {
                JCTree.JCVariableDecl jCVariableDeclMakeHoistedVarDecl = makeHoistedVarDecl(i, entry.getValue());
                if (!entry.getValue().isUnnamedVariable() && (!entry.getKey().isPreserved() || !this.parent.tryPrepend(entry.getKey(), jCVariableDeclMakeHoistedVarDecl))) {
                    listBuffer.add(jCVariableDeclMakeHoistedVarDecl);
                }
            }
            return listBuffer.toList();
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public JCTree.JCExpression decorateExpression(JCTree.JCExpression jCExpression) {
            Iterator<Symbol.VarSymbol> it = this.hoistedVarMap.values().iterator();
            while (it.hasNext()) {
                jCExpression = TransPatterns.this.make.at(jCExpression.pos).LetExpr(makeHoistedVarDecl(jCExpression.pos, it.next()), jCExpression).setType(jCExpression.type);
            }
            return jCExpression;
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public JCTree.JCStatement decorateStatement(JCTree.JCStatement jCStatement) {
            List<JCTree.JCStatement> listBindingVars = bindingVars(jCStatement.pos);
            return listBindingVars.nonEmpty() ? TransPatterns.this.make.at(jCStatement.pos).Block(0L, listBindingVars.append(jCStatement)) : jCStatement;
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public Symbol.VarSymbol getBindingFor(final Symbol.BindingSymbol bindingSymbol) {
            Symbol.VarSymbol bindingFor = this.parent.getBindingFor(bindingSymbol);
            return bindingFor != null ? bindingFor : (Symbol.VarSymbol) this.hoistedVarMap.entrySet().stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.y5
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Symbol.BindingSymbol) ((Map.Entry) obj).getKey()).isAliasFor(bindingSymbol);
                }
            }).findFirst().map(new Function() { // from class: com.sun.tools.javac.comp.z5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TransPatterns.BasicBindingContext.a((Map.Entry) obj);
                }
            }).orElse(null);
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public BindingContext pop() {
            TransPatterns transPatterns = TransPatterns.this;
            BindingContext bindingContext = this.parent;
            transPatterns.bindingContext = bindingContext;
            return bindingContext;
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BindingContext
        public boolean tryPrepend(Symbol.BindingSymbol bindingSymbol, JCTree.JCVariableDecl jCVariableDecl) {
            return false;
        }
    }

    public abstract class BindingContext {
        public BindingContext() {
        }

        public abstract Symbol.VarSymbol bindingDeclared(Symbol.BindingSymbol bindingSymbol);

        public abstract List<JCTree.JCStatement> bindingVars(int i);

        public abstract JCTree.JCExpression decorateExpression(JCTree.JCExpression jCExpression);

        public abstract JCTree.JCStatement decorateStatement(JCTree.JCStatement jCStatement);

        public abstract Symbol.VarSymbol getBindingFor(Symbol.BindingSymbol bindingSymbol);

        public abstract BindingContext pop();

        public abstract boolean tryPrepend(Symbol.BindingSymbol bindingSymbol, JCTree.JCVariableDecl jCVariableDecl);
    }

    public class BindingDeclarationFenceBindingContext extends BasicBindingContext {
        private BindingDeclarationFenceBindingContext() {
            super();
        }

        @Override // com.sun.tools.javac.comp.TransPatterns.BasicBindingContext, com.sun.tools.javac.comp.TransPatterns.BindingContext
        public Symbol.VarSymbol bindingDeclared(Symbol.BindingSymbol bindingSymbol) {
            return null;
        }
    }

    public class ReplaceVar extends TreeScanner {
        private final Map<Symbol, Symbol> fromTo;

        public ReplaceVar(Map<Symbol, Symbol> map) {
            this.fromTo = map;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIdent(JCTree.JCIdent jCIdent) {
            Map<Symbol, Symbol> map = this.fromTo;
            Symbol symbol = jCIdent.sym;
            jCIdent.sym = map.getOrDefault(symbol, symbol);
            super.visitIdent(jCIdent);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitPatternCaseLabel(JCTree.JCPatternCaseLabel jCPatternCaseLabel) {
            super.visitPatternCaseLabel(jCPatternCaseLabel);
            scan(jCPatternCaseLabel.syntheticGuard);
        }
    }

    public static final class UnrolledRecordPattern {
        private final JCTree.JCExpression newGuard;
        private final JCTree.JCBindingPattern primaryPattern;

        public UnrolledRecordPattern(JCTree.JCBindingPattern jCBindingPattern, JCTree.JCExpression jCExpression) {
            this.primaryPattern = jCBindingPattern;
            this.newGuard = jCExpression;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof UnrolledRecordPattern)) {
                return false;
            }
            UnrolledRecordPattern unrolledRecordPattern = (UnrolledRecordPattern) obj;
            return Objects.equals(this.newGuard, unrolledRecordPattern.newGuard) && Objects.equals(this.primaryPattern, unrolledRecordPattern.primaryPattern);
        }

        public final int hashCode() {
            return (Objects.hashCode(this.primaryPattern) * 31) + Objects.hashCode(this.newGuard);
        }

        public JCTree.JCExpression newGuard() {
            return this.newGuard;
        }

        public JCTree.JCBindingPattern primaryPattern() {
            return this.primaryPattern;
        }

        public final String toString() {
            return "UnrolledRecordPattern[primaryPattern=" + Objects.toString(this.primaryPattern) + ", newGuard=" + Objects.toString(this.newGuard) + "]";
        }
    }

    public TransPatterns(Context context) {
        context.put(transPatternsKey, this);
        this.syms = Symtab.instance(context);
        this.attr = Attr.instance(context);
        this.rs = Resolve.instance(context);
        this.make = TreeMaker.instance(context);
        this.types = Types.instance(context);
        this.operators = Operators.instance(context);
        this.names = Names.instance(context);
        this.target = Target.instance(context);
        this.preview = Preview.instance(context);
    }

    public static /* synthetic */ boolean b(JCTree.JCCaseLabel jCCaseLabel) {
        return (jCCaseLabel instanceof JCTree.JCPatternCaseLabel) && ((JCTree.JCPatternCaseLabel) jCCaseLabel).syntheticGuard != null;
    }

    public static /* synthetic */ PoolConstant.LoadableConstant[] c(int i) {
        return new PoolConstant.LoadableConstant[i];
    }

    private PoolConstant.LoadableConstant createEnumDesc(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.ClassSymbol classSymbol, Name name) {
        Resolve resolve = this.rs;
        Env<AttrContext> env = this.env;
        Symtab symtab = this.syms;
        Symbol.MethodSymbol methodSymbolResolveInternalMethod = resolve.resolveInternalMethod(diagnosticPosition, env, symtab.classDescType, this.names.of, List.of(symtab.stringType), List.nil());
        Resolve resolve2 = this.rs;
        Env<AttrContext> env2 = this.env;
        Symtab symtab2 = this.syms;
        return invokeMethodWrapper(diagnosticPosition, resolve2.resolveInternalMethod(diagnosticPosition, env2, symtab2.enumDescType, this.names.of, List.of(symtab2.classDescType, symtab2.stringType), List.nil()).asHandle(), invokeMethodWrapper(diagnosticPosition, methodSymbolResolveInternalMethod.asHandle(), PoolConstant.LoadableConstant.String(classSymbol.flatname.toString())), PoolConstant.LoadableConstant.String(name.toString()));
    }

    public static /* synthetic */ boolean e(JCTree.JCCaseLabel jCCaseLabel) {
        return !TreeInfo.isNullCaseLabel(jCCaseLabel);
    }

    private void fixupContinue(final JCTree jCTree, JCTree.JCCase jCCase, final Symbol.VarSymbol varSymbol, final int i) {
        new TreeScanner(this) { // from class: com.sun.tools.javac.comp.TransPatterns.2
            final /* synthetic */ TransPatterns this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitCase(JCTree.JCCase jCCase2) {
                if (jCCase2.stats.size() == 1) {
                    JCTree.JCStatement jCStatement = jCCase2.stats.head;
                    if ((jCStatement instanceof JCTree.JCContinue) && ((JCTree.JCContinue) jCStatement).target == jCTree) {
                        TreeMaker treeMaker = this.this$0.make;
                        TreeMaker treeMaker2 = this.this$0.make;
                        JCTree.JCIdent jCIdentIdent = this.this$0.make.Ident(varSymbol);
                        TransPatterns transPatterns = this.this$0;
                        jCCase2.stats = jCCase2.stats.prepend(treeMaker.Exec(treeMaker2.Assign(jCIdentIdent, transPatterns.makeLit(transPatterns.syms.intType, Integer.valueOf(i + 1))).setType((Type) this.this$0.syms.intType)));
                    }
                }
            }
        }.scan(jCCase.stats);
    }

    public static /* synthetic */ boolean h(PoolConstant.LoadableConstant loadableConstant) {
        return loadableConstant != null;
    }

    /* JADX WARN: Code duplicated, block: B:59:0x0303 A[Catch: all -> 0x02f0, TryCatch #0 {all -> 0x02f0, blocks: (B:47:0x02b8, B:49:0x02c1, B:56:0x02f6, B:57:0x02fd, B:59:0x0303, B:61:0x0319, B:63:0x0344, B:65:0x0348, B:67:0x0356, B:70:0x036e, B:72:0x037a, B:73:0x0389), top: B:116:0x02b8 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x0319 A[Catch: all -> 0x02f0, TryCatch #0 {all -> 0x02f0, blocks: (B:47:0x02b8, B:49:0x02c1, B:56:0x02f6, B:57:0x02fd, B:59:0x0303, B:61:0x0319, B:63:0x0344, B:65:0x0348, B:67:0x0356, B:70:0x036e, B:72:0x037a, B:73:0x0389), top: B:116:0x02b8 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x033c  */
    /* JADX WARN: Code duplicated, block: B:65:0x0348 A[Catch: all -> 0x02f0, TryCatch #0 {all -> 0x02f0, blocks: (B:47:0x02b8, B:49:0x02c1, B:56:0x02f6, B:57:0x02fd, B:59:0x0303, B:61:0x0319, B:63:0x0344, B:65:0x0348, B:67:0x0356, B:70:0x036e, B:72:0x037a, B:73:0x0389), top: B:116:0x02b8 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x0356 A[Catch: all -> 0x02f0, TryCatch #0 {all -> 0x02f0, blocks: (B:47:0x02b8, B:49:0x02c1, B:56:0x02f6, B:57:0x02fd, B:59:0x0303, B:61:0x0319, B:63:0x0344, B:65:0x0348, B:67:0x0356, B:70:0x036e, B:72:0x037a, B:73:0x0389), top: B:116:0x02b8 }] */
    /* JADX WARN: Code duplicated, block: B:68:0x035e  */
    /* JADX WARN: Code duplicated, block: B:72:0x037a A[Catch: all -> 0x02f0, TryCatch #0 {all -> 0x02f0, blocks: (B:47:0x02b8, B:49:0x02c1, B:56:0x02f6, B:57:0x02fd, B:59:0x0303, B:61:0x0319, B:63:0x0344, B:65:0x0348, B:67:0x0356, B:70:0x036e, B:72:0x037a, B:73:0x0389), top: B:116:0x02b8 }] */
    /* JADX WARN: Multi-variable type inference failed */
    private void handleSwitch(JCTree jCTree, JCTree.JCExpression jCExpression, List<JCTree.JCCase> list, boolean z, boolean z2) {
        boolean z3;
        Symbol.VarSymbol varSymbol;
        Iterator<JCTree.JCCase> it;
        boolean z4;
        int i;
        boolean z5;
        Iterator it2;
        JCTree.JCExpression jCExpressionMakeBinary;
        boolean z6;
        JCTree.JCExpression jCExpression2;
        boolean z7;
        JCTree.JCPatternCaseLabel jCPatternCaseLabel;
        JCTree.JCExpression jCExpressionMakeBinary2;
        JCTree.JCExpression jCExpression3;
        if (!z2) {
            if (jCTree.hasTag(JCTree.Tag.SWITCH)) {
                super.visitSwitch((JCTree.JCSwitch) jCTree);
                return;
            } else {
                super.visitSwitchExpression((JCTree.JCSwitchExpression) jCTree);
                return;
            }
        }
        final Type type = (jCExpression.type.hasTag(TypeTag.BOT) || this.target.usesReferenceOnlySelectorTypes()) ? this.syms.objectType : jCExpression.type;
        ListBuffer listBuffer = new ListBuffer();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            JCTree.JCCase jCCase = (JCTree.JCCase) list2.head;
            jCCase.labels = jCCase.labels.map(new Function() { // from class: fje
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return TransPatterns.m(this.b, (JCTree.JCCaseLabel) obj);
                }
            });
            listBuffer.add((JCTree.JCCase) list2.head);
            appendBreakIfNeeded(jCTree, list, (JCTree.JCCase) list2.head);
        }
        List<JCTree.JCCase> list3 = listBuffer.toList();
        patchCompletingNormallyCases(list3);
        List<JCTree.JCCase> listProcessCases = processCases(jCTree, list3);
        ListBuffer listBuffer2 = new ListBuffer();
        Names names = this.names;
        StringBuilder sb = new StringBuilder("selector");
        int i2 = this.variableIndex;
        this.variableIndex = i2 + 1;
        sb.append(i2);
        sb.append(this.target.syntheticNameChar());
        sb.append("temp");
        Symbol.VarSymbol varSymbol2 = new Symbol.VarSymbol(4096L, names.fromString(sb.toString()), type, this.currentMethodSym);
        boolean zAnyMatch = listProcessCases.stream().flatMap(new Function() { // from class: jje
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JCTree.JCCase) obj).labels.stream();
            }
        }).anyMatch(new Predicate() { // from class: kje
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TreeInfo.isNullCaseLabel((JCTree.JCCaseLabel) obj);
            }
        });
        JCTree.JCCase jCCaseLast = listProcessCases.last();
        JCTree.JCExpression jCExpressionMakeNullCheck = (JCTree.JCExpression) translate(jCExpression);
        boolean z8 = (zAnyMatch || type.isPrimitive()) ? false : true;
        TreeMaker treeMakerAt = this.make.at(jCTree.pos);
        if (z8) {
            jCExpressionMakeNullCheck = this.attr.makeNullCheck(jCExpressionMakeNullCheck);
        }
        listBuffer2.append(treeMakerAt.VarDef(varSymbol2, jCExpressionMakeNullCheck));
        Names names2 = this.names;
        StringBuilder sb2 = new StringBuilder("index");
        sb2.append(this.target.syntheticNameChar());
        int i3 = this.variableIndex;
        this.variableIndex = i3 + 1;
        sb2.append(i3);
        Symbol.VarSymbol varSymbol3 = new Symbol.VarSymbol(4096L, names2.fromString(sb2.toString()), this.syms.intType, this.currentMethodSym);
        listBuffer2.append(this.make.at(jCTree.pos).VarDef(varSymbol3, makeLit(this.syms.intType, 0)));
        Symtab symtab = this.syms;
        Type type2 = symtab.methodHandleLookupType;
        Type type3 = symtab.stringType;
        Type type4 = symtab.methodTypeType;
        Types types = this.types;
        Type enclosingType = this.syms.classType.getEnclosingType();
        int i4 = 0;
        Symtab symtab2 = this.syms;
        List<Type> listOf = List.of(type2, type3, type4, types.makeArrayType(new Type.ClassType(enclosingType, List.of(new Type.WildcardType(symtab2.objectType, BoundKind.UNBOUND, symtab2.boundClass)), this.syms.classType.tsym)));
        PoolConstant.LoadableConstant[] loadableConstantArr = (PoolConstant.LoadableConstant[]) listProcessCases.stream().flatMap(new Function() { // from class: lje
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JCTree.JCCase) obj).labels.stream();
            }
        }).map(new Function() { // from class: mje
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.toLoadableConstant((JCTree.JCCaseLabel) obj, type);
            }
        }).filter(new Predicate() { // from class: yie
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TransPatterns.h((PoolConstant.LoadableConstant) obj);
            }
        }).toArray(new IntFunction() { // from class: zie
            @Override // java.util.function.IntFunction
            public final Object apply(int i5) {
                return TransPatterns.c(i5);
            }
        });
        boolean zIsEnum = type.tsym.isEnum();
        Names names3 = this.names;
        Name name = zIsEnum ? names3.enumSwitch : names3.typeSwitch;
        Symbol.MethodSymbol methodSymbolResolveInternalMethod = this.rs.resolveInternalMethod(jCTree.pos(), this.env, this.syms.switchBootstrapsType, name, listOf, List.nil());
        Symbol.DynamicMethodSymbol dynamicMethodSymbol = new Symbol.DynamicMethodSymbol(name, this.syms.noSymbol, methodSymbolResolveInternalMethod.asHandle(), new Type.MethodType(List.of((Type.JCPrimitiveType) type, this.syms.intType), this.syms.intType, List.nil(), this.syms.methodClass), loadableConstantArr);
        TreeMaker treeMaker = this.make;
        JCTree.JCFieldAccess jCFieldAccessSelect = treeMaker.Select(treeMaker.QualIdent(methodSymbolResolveInternalMethod.owner), dynamicMethodSymbol.name);
        jCFieldAccessSelect.sym = dynamicMethodSymbol;
        jCFieldAccessSelect.type = this.syms.intType;
        JCTree.JCMethodInvocation type5 = this.make.Apply(List.nil(), jCFieldAccessSelect, List.of(this.make.Ident(varSymbol2), this.make.Ident(varSymbol3))).setType((Type) this.syms.intType);
        Iterator<JCTree.JCCase> it3 = listProcessCases.iterator();
        boolean z9 = false;
        int i5 = 0;
        boolean z10 = false;
        while (it3.hasNext()) {
            JCTree.JCCase next = it3.next();
            List<JCTree.JCCaseLabel> list4 = next.labels;
            if (list4.size() > 1 && next.labels.stream().anyMatch(new Predicate() { // from class: aje
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return TreeInfo.isNullCaseLabel((JCTree.JCCaseLabel) obj);
                }
            })) {
                list4 = (List) next.labels.stream().filter(new Predicate() { // from class: bje
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return TransPatterns.e((JCTree.JCCaseLabel) obj);
                    }
                }).collect(List.collector());
            }
            if ((!(list4.size() > 1 ? list4.stream().allMatch(new Predicate() { // from class: cje
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((JCTree.JCCaseLabel) obj).hasTag(JCTree.Tag.PATTERNCASELABEL);
                }
            }) : list4.head.hasTag(JCTree.Tag.PATTERNCASELABEL)) || z9) && next.guard == null) {
                z3 = zIsEnum;
                varSymbol = varSymbol2;
                it = it3;
                z4 = z10;
                next.stats = translate(next.stats);
            } else {
                List list5 = (List) list4.stream().map(new Function() { // from class: gje
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return TransPatterns.i((JCTree.JCCaseLabel) obj);
                    }
                }).collect(List.collector());
                this.bindingContext = new BasicBindingContext();
                Symbol.VarSymbol varSymbol4 = this.currentValue;
                try {
                    this.currentValue = varSymbol2;
                    if (list5.size() > 1) {
                        Stream map = list5.stream().map(new Function() { // from class: hje
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return ((JCTree.JCPatternCaseLabel) obj).pat.type.tsym;
                            }
                        });
                        final Symbol.TypeSymbol typeSymbol = ((JCTree.JCPatternCaseLabel) list5.get(i4)).pat.type.tsym;
                        Objects.requireNonNull(typeSymbol);
                        it = it3;
                        z5 = map.allMatch(new Predicate() { // from class: ije
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                return typeSymbol.equals((Symbol.TypeSymbol) obj);
                            }
                        }) ? false : true;
                        it2 = list5.iterator();
                        jCExpressionMakeBinary = null;
                        z6 = true;
                        while (it2.hasNext()) {
                            z7 = z5;
                            jCPatternCaseLabel = (JCTree.JCPatternCaseLabel) it2.next();
                            List list6 = list5;
                            jCExpressionMakeBinary2 = (JCTree.JCExpression) translate(jCPatternCaseLabel.pat);
                            if (z7) {
                                jCExpressionMakeBinary2 = makeBinary(JCTree.Tag.AND, makeTypeTest(this.make.Ident(varSymbol2), this.make.Type(jCPatternCaseLabel.pat.type)), jCExpressionMakeBinary2);
                            }
                            jCExpression3 = jCPatternCaseLabel.syntheticGuard;
                            if (jCExpression3 != null) {
                                jCExpressionMakeBinary2 = makeBinary(JCTree.Tag.AND, jCExpressionMakeBinary2, (JCTree.JCExpression) translate(jCExpression3));
                            }
                            if (z6) {
                                jCExpressionMakeBinary = jCExpressionMakeBinary2;
                                z6 = false;
                            } else {
                                jCExpressionMakeBinary = makeBinary(JCTree.Tag.OR, jCExpressionMakeBinary, jCExpressionMakeBinary2);
                            }
                            list5 = list6;
                            z5 = z7;
                            z10 = z10;
                            it2 = it2;
                            varSymbol2 = varSymbol2;
                            zIsEnum = zIsEnum;
                        }
                        z3 = zIsEnum;
                        varSymbol = varSymbol2;
                        List list7 = list5;
                        z4 = z10;
                        jCExpression2 = next.guard;
                        if (jCExpression2 != null) {
                            jCExpressionMakeBinary = makeBinary(JCTree.Tag.AND, jCExpressionMakeBinary, (JCTree.JCExpression) translate(jCExpression2));
                            next.guard = null;
                        }
                        next.stats = translate(next.stats);
                        JCTree.JCContinue jCContinueContinue = this.make.at(list4.head.pos()).Continue(null);
                        jCContinueContinue.target = jCTree;
                        List<JCTree.JCStatement> list8 = next.stats;
                        TreeMaker treeMaker2 = this.make;
                        JCTree.JCExpression type6 = makeUnary(JCTree.Tag.NOT, jCExpressionMakeBinary).setType((Type) this.syms.booleanType);
                        TreeMaker treeMaker3 = this.make;
                        List<JCTree.JCStatement> listPrepend = list8.prepend(treeMaker2.If(type6, treeMaker3.Block(0L, List.of((JCTree.JCContinue) treeMaker3.Exec(treeMaker3.Assign(treeMaker3.Ident(varSymbol3), makeLit(this.syms.intType, Integer.valueOf(list7.length() + i5))).setType((Type) this.syms.intType)), jCContinueContinue)), null));
                        next.stats = listPrepend;
                        next.stats = listPrepend.prependList(this.bindingContext.bindingVars(next.pos));
                        this.currentValue = varSymbol4;
                        this.bindingContext.pop();
                    } else {
                        it = it3;
                    }
                    it2 = list5.iterator();
                    jCExpressionMakeBinary = null;
                    z6 = true;
                    while (it2.hasNext()) {
                        z7 = z5;
                        jCPatternCaseLabel = (JCTree.JCPatternCaseLabel) it2.next();
                        List list9 = list5;
                        jCExpressionMakeBinary2 = (JCTree.JCExpression) translate(jCPatternCaseLabel.pat);
                        if (z7) {
                            jCExpressionMakeBinary2 = makeBinary(JCTree.Tag.AND, makeTypeTest(this.make.Ident(varSymbol2), this.make.Type(jCPatternCaseLabel.pat.type)), jCExpressionMakeBinary2);
                        }
                        jCExpression3 = jCPatternCaseLabel.syntheticGuard;
                        if (jCExpression3 != null) {
                            jCExpressionMakeBinary2 = makeBinary(JCTree.Tag.AND, jCExpressionMakeBinary2, (JCTree.JCExpression) translate(jCExpression3));
                        }
                        if (z6) {
                            jCExpressionMakeBinary = makeBinary(JCTree.Tag.OR, jCExpressionMakeBinary, jCExpressionMakeBinary2);
                        } else {
                            jCExpressionMakeBinary = jCExpressionMakeBinary2;
                            z6 = false;
                        }
                        list5 = list9;
                        z5 = z7;
                        z10 = z10;
                        it2 = it2;
                        varSymbol2 = varSymbol2;
                        zIsEnum = zIsEnum;
                    }
                    z3 = zIsEnum;
                    varSymbol = varSymbol2;
                    List list10 = list5;
                    z4 = z10;
                    jCExpression2 = next.guard;
                    if (jCExpression2 != null) {
                        jCExpressionMakeBinary = makeBinary(JCTree.Tag.AND, jCExpressionMakeBinary, (JCTree.JCExpression) translate(jCExpression2));
                        next.guard = null;
                    }
                    next.stats = translate(next.stats);
                    JCTree.JCContinue jCContinueContinue2 = this.make.at(list4.head.pos()).Continue(null);
                    jCContinueContinue2.target = jCTree;
                    List<JCTree.JCStatement> list11 = next.stats;
                    TreeMaker treeMaker4 = this.make;
                    JCTree.JCExpression type7 = makeUnary(JCTree.Tag.NOT, jCExpressionMakeBinary).setType((Type) this.syms.booleanType);
                    TreeMaker treeMaker5 = this.make;
                    List<JCTree.JCStatement> listPrepend2 = list11.prepend(treeMaker4.If(type7, treeMaker5.Block(0L, List.of((JCTree.JCContinue) treeMaker5.Exec(treeMaker5.Assign(treeMaker5.Ident(varSymbol3), makeLit(this.syms.intType, Integer.valueOf(list10.length() + i5))).setType((Type) this.syms.intType)), jCContinueContinue2)), null));
                    next.stats = listPrepend2;
                    next.stats = listPrepend2.prependList(this.bindingContext.bindingVars(next.pos));
                    this.currentValue = varSymbol4;
                    this.bindingContext.pop();
                } catch (Throwable th) {
                    this.currentValue = varSymbol4;
                    this.bindingContext.pop();
                    throw th;
                }
            }
            fixupContinue(jCTree, next, varSymbol3, i5);
            ListBuffer listBuffer3 = new ListBuffer();
            List<JCTree.JCCaseLabel> list12 = next.labels;
            z10 = z4;
            boolean z11 = false;
            for (int i6 = 0; i6 < list12.size() && !z11; i6++) {
                JCTree.JCCaseLabel jCCaseLabel = list12.get(i6);
                if (jCCaseLabel.hasTag(JCTree.Tag.DEFAULTCASELABEL)) {
                    listBuffer3.add(jCCaseLabel);
                    z10 = true;
                } else if (z && !z10 && next == jCCaseLast && jCCaseLabel.hasTag(JCTree.Tag.PATTERNCASELABEL)) {
                    listBuffer3.add(this.make.DefaultCaseLabel());
                    z11 = true;
                } else {
                    if (TreeInfo.isNullCaseLabel(jCCaseLabel)) {
                        i = i5;
                        i5 = -1;
                    } else {
                        i = i5 + 1;
                    }
                    TreeMaker treeMaker6 = this.make;
                    listBuffer3.add(treeMaker6.ConstantCaseLabel(treeMaker6.Literal(Integer.valueOf(i5))));
                    i5 = i;
                }
            }
            next.labels = listBuffer3.toList();
            z9 = next.caseKind == CaseTree.CaseKind.STATEMENT && next.completesNormally;
            it3 = it;
            varSymbol2 = varSymbol;
            zIsEnum = z3;
            i4 = 0;
        }
        boolean z12 = zIsEnum;
        if (jCTree.hasTag(JCTree.Tag.SWITCH)) {
            JCTree.JCSwitch jCSwitch = (JCTree.JCSwitch) jCTree;
            jCSwitch.selector = type5;
            jCSwitch.cases = listProcessCases;
            jCSwitch.wasEnumSelector = z12;
            listBuffer2.append(jCSwitch);
            this.result = this.make.Block(0L, listBuffer2.toList());
            return;
        }
        JCTree.JCSwitchExpression jCSwitchExpression = (JCTree.JCSwitchExpression) jCTree;
        jCSwitchExpression.selector = type5;
        jCSwitchExpression.cases = listProcessCases;
        jCSwitchExpression.wasEnumSelector = z12;
        JCTree.LetExpr letExpr = (JCTree.LetExpr) this.make.LetExpr(listBuffer2.toList(), jCSwitchExpression).setType(jCTree.type);
        letExpr.needsCond = true;
        this.result = letExpr;
    }

    public static /* synthetic */ JCTree.JCPatternCaseLabel i(JCTree.JCCaseLabel jCCaseLabel) {
        return (JCTree.JCPatternCaseLabel) jCCaseLabel;
    }

    public static TransPatterns instance(Context context) {
        TransPatterns transPatterns = (TransPatterns) context.get(transPatternsKey);
        return transPatterns == null ? new TransPatterns(context) : transPatterns;
    }

    private PoolConstant.LoadableConstant invokeMethodWrapper(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.MethodHandleSymbol methodHandleSymbol, PoolConstant.LoadableConstant... loadableConstantArr) {
        Symtab symtab = this.syms;
        Type type = symtab.methodHandleLookupType;
        Type type2 = symtab.stringType;
        Type.ClassType classType = new Type.ClassType(this.syms.classType.getEnclosingType(), List.of(this.syms.botType), this.syms.classType.tsym);
        Symtab symtab2 = this.syms;
        Symbol.MethodSymbol methodSymbolResolveInternalMethod = this.rs.resolveInternalMethod(diagnosticPosition, this.env, this.syms.constantBootstrapsType, this.names.invoke, List.of((Type.ClassType) type, (Type.ClassType) type2, classType, (Type.ClassType[]) new Type[]{symtab2.methodHandleType, this.types.makeArrayType(symtab2.objectType)}), List.nil());
        PoolConstant.LoadableConstant[] loadableConstantArr2 = new PoolConstant.LoadableConstant[loadableConstantArr.length + 1];
        loadableConstantArr2[0] = methodHandleSymbol;
        System.arraycopy(loadableConstantArr, 0, loadableConstantArr2, 1, loadableConstantArr.length);
        return new Symbol.DynamicVarSymbol(methodSymbolResolveInternalMethod.name, methodSymbolResolveInternalMethod.owner, methodSymbolResolveInternalMethod.asHandle(), methodHandleSymbol.getReturnType(), loadableConstantArr2);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0092  */
    /* JADX WARN: Code duplicated, block: B:26:0x009a  */
    /* JADX WARN: Code duplicated, block: B:30:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:32:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:35:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:39:0x00d8  */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void j(TransPatterns transPatterns, ListBuffer listBuffer, JCTree jCTree, ListBuffer listBuffer2, final Symbol.VarSymbol varSymbol, final JCTree.JCExpression jCExpression, final Symbol.VarSymbol varSymbol2) {
        ListBuffer listBuffer3;
        JCTree.JCInstanceOf jCInstanceOf;
        JCTree.JCPatternCaseLabel jCPatternCaseLabelPatternCaseLabel;
        List<JCTree.JCCaseLabel> listOf;
        transPatterns.getClass();
        if (listBuffer.size() > 1) {
            Assert.check((varSymbol == null || jCExpression == null || varSymbol2 == null) ? false : true, (Supplier<String>) new Supplier() { // from class: dje
                @Override // java.util.function.Supplier
                public final Object get() {
                    return TransPatterns.k(varSymbol, jCExpression, varSymbol2);
                }
            });
            ListBuffer listBuffer4 = new ListBuffer();
            List list = listBuffer.toList();
            boolean z = false;
            boolean z2 = false;
            while (true) {
                JCTree.JCExpression jCExpression2 = null;
                if (!list.nonEmpty()) {
                    break;
                }
                JCTree.JCCase jCCase = (JCTree.JCCase) list.head;
                JCTree.JCPatternCaseLabel jCPatternCaseLabel = (JCTree.JCPatternCaseLabel) jCCase.labels.head;
                transPatterns.new ReplaceVar(Collections.unmodifiableMap(Collections.singletonMap(((JCTree.JCBindingPattern) jCPatternCaseLabel.pat).var.sym, varSymbol))).scan(jCCase);
                JCTree.JCExpression jCExpression3 = jCPatternCaseLabel.syntheticGuard;
                if (jCExpression3 instanceof JCTree.JCBinary) {
                    JCTree.JCBinary jCBinary = (JCTree.JCBinary) jCExpression3;
                    jCExpression2 = jCBinary.rhs;
                    jCInstanceOf = (JCTree.JCInstanceOf) jCBinary.lhs;
                } else {
                    jCInstanceOf = (JCTree.JCInstanceOf) jCExpression3;
                }
                JCTree.JCBindingPattern jCBindingPattern = (JCTree.JCBindingPattern) jCInstanceOf.pattern;
                if (transPatterns.types.erasure(jCBindingPattern.type).isPrimitive()) {
                    Types types = transPatterns.types;
                    if (types.isUnconditionallyExactTypeBased(jCExpression.type, types.erasure(jCBindingPattern.type))) {
                        if (list.tail.isEmpty()) {
                            z2 = true;
                        }
                    }
                    jCPatternCaseLabelPatternCaseLabel = transPatterns.make.PatternCaseLabel(jCBindingPattern);
                    jCPatternCaseLabelPatternCaseLabel.syntheticGuard = jCExpression2;
                    if (z2) {
                        listOf = List.of((JCTree.JCPatternCaseLabel) transPatterns.make.ConstantCaseLabel(transPatterns.makeNull()), jCPatternCaseLabelPatternCaseLabel);
                    } else {
                        listOf = List.of(jCPatternCaseLabelPatternCaseLabel);
                    }
                    listBuffer4.add(transPatterns.make.Case(CaseTree.CaseKind.STATEMENT, listOf, jCCase.guard, jCCase.stats, null));
                    if (jCExpression2 == null) {
                        z = true;
                    } else {
                        z = true;
                    }
                    list = list.tail;
                } else {
                    if (jCInstanceOf.allowNull) {
                        if (list.tail.isEmpty()) {
                            z2 = true;
                        }
                    }
                    jCPatternCaseLabelPatternCaseLabel = transPatterns.make.PatternCaseLabel(jCBindingPattern);
                    jCPatternCaseLabelPatternCaseLabel.syntheticGuard = jCExpression2;
                    if (z2) {
                        listOf = List.of((JCTree.JCPatternCaseLabel) transPatterns.make.ConstantCaseLabel(transPatterns.makeNull()), jCPatternCaseLabelPatternCaseLabel);
                    } else {
                        listOf = List.of(jCPatternCaseLabelPatternCaseLabel);
                    }
                    listBuffer4.add(transPatterns.make.Case(CaseTree.CaseKind.STATEMENT, listOf, jCCase.guard, jCCase.stats, null));
                    if (jCExpression2 == null || jCCase.guard != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    list = list.tail;
                }
                z2 = false;
                jCPatternCaseLabelPatternCaseLabel = transPatterns.make.PatternCaseLabel(jCBindingPattern);
                jCPatternCaseLabelPatternCaseLabel.syntheticGuard = jCExpression2;
                if (z2) {
                    listOf = List.of((JCTree.JCPatternCaseLabel) transPatterns.make.ConstantCaseLabel(transPatterns.makeNull()), jCPatternCaseLabelPatternCaseLabel);
                } else {
                    listOf = List.of(jCPatternCaseLabelPatternCaseLabel);
                }
                listBuffer4.add(transPatterns.make.Case(CaseTree.CaseKind.STATEMENT, listOf, jCCase.guard, jCCase.stats, null));
                if (jCExpression2 == null) {
                    z = true;
                } else {
                    z = true;
                }
                list = list.tail;
            }
            if (z || !z2) {
                JCTree.JCContinue jCContinueContinue = transPatterns.make.Continue(null);
                jCContinueContinue.target = jCTree;
                TreeMaker treeMaker = transPatterns.make;
                listBuffer4.add(treeMaker.Case(CaseTree.CaseKind.STATEMENT, z2 ? List.of(treeMaker.DefaultCaseLabel()) : List.of((JCTree.JCDefaultCaseLabel) treeMaker.ConstantCaseLabel(transPatterns.makeNull()), transPatterns.make.DefaultCaseLabel()), null, List.of(jCContinueContinue), null));
            }
            JCTree.JCSwitch jCSwitchSwitch = transPatterns.make.Switch(jCExpression, listBuffer4.toList());
            jCSwitchSwitch.patternSwitch = true;
            jCSwitchSwitch.hasUnconditionalPattern = z2;
            JCTree.JCPatternCaseLabel jCPatternCaseLabel2 = (JCTree.JCPatternCaseLabel) ((JCTree.JCCase) listBuffer.first()).labels.head;
            jCPatternCaseLabel2.syntheticGuard = null;
            listBuffer2.add(transPatterns.make.Case(CaseTree.CaseKind.STATEMENT, List.of(jCPatternCaseLabel2), null, List.of(jCSwitchSwitch), null));
            listBuffer3 = listBuffer;
        } else {
            listBuffer3 = listBuffer;
            listBuffer2.addAll(listBuffer3);
        }
        listBuffer3.clear();
    }

    public static /* synthetic */ String k(Symbol.VarSymbol varSymbol, JCTree.JCExpression jCExpression, Symbol.VarSymbol varSymbol2) {
        return "commonBinding: " + varSymbol + "commonNestedExpression: " + jCExpression + "commonNestedBinding: " + varSymbol2;
    }

    public static /* synthetic */ JCTree.JCCaseLabel m(TransPatterns transPatterns, JCTree.JCCaseLabel jCCaseLabel) {
        transPatterns.getClass();
        if (jCCaseLabel instanceof JCTree.JCPatternCaseLabel) {
            JCTree.JCPattern jCPattern = ((JCTree.JCPatternCaseLabel) jCCaseLabel).pat;
            if (jCPattern instanceof JCTree.JCRecordPattern) {
                UnrolledRecordPattern unrolledRecordPatternUnrollRecordPattern = transPatterns.unrollRecordPattern((JCTree.JCRecordPattern) jCPattern);
                JCTree.JCExpression jCExpressionNewGuard = unrolledRecordPatternUnrollRecordPattern.newGuard();
                JCTree.JCPatternCaseLabel jCPatternCaseLabelPatternCaseLabel = transPatterns.make.PatternCaseLabel(unrolledRecordPatternUnrollRecordPattern.primaryPattern());
                jCPatternCaseLabelPatternCaseLabel.syntheticGuard = jCExpressionNewGuard;
                return jCPatternCaseLabelPatternCaseLabel;
            }
        }
        return jCCaseLabel;
    }

    private Symbol.DynamicVarSymbol makeBooleanConstant(JCDiagnostic.DiagnosticPosition diagnosticPosition, int i) {
        Assert.checkNonNull(this.currentClass);
        Symtab symtab = this.syms;
        return new Symbol.DynamicVarSymbol(this.names.fromString(i == 0 ? "FALSE" : "TRUE"), this.syms.noSymbol, new Symbol.MethodHandleSymbol(this.rs.resolveInternalMethod(diagnosticPosition, this.env, this.syms.constantBootstrapsType, this.names.fromString("getStaticFinal"), List.of((Type.ClassType) symtab.methodHandleLookupType, (Type.ClassType) symtab.stringType, new Type.ClassType(this.syms.classType.getEnclosingType(), List.of(this.syms.constantBootstrapsType), this.syms.classType.tsym)), List.nil())), this.types.boxedTypeOrType(this.syms.booleanType), new PoolConstant.LoadableConstant[0]);
    }

    private Symbol.DynamicVarSymbol makePrimitive(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        Assert.checkNonNull(this.currentClass);
        Symtab symtab = this.syms;
        List<Type> listOf = List.of((Type.ClassType) symtab.methodHandleLookupType, (Type.ClassType) symtab.stringType, new Type.ClassType(this.syms.classType.getEnclosingType(), List.of(this.syms.constantBootstrapsType), this.syms.classType.tsym));
        Symbol.MethodSymbol methodSymbolResolveInternalMethod = this.rs.resolveInternalMethod(diagnosticPosition, this.env, this.syms.constantBootstrapsType, this.names.fromString("primitiveClass"), listOf, List.nil());
        PrimitiveGenerator primitiveGenerator = new PrimitiveGenerator();
        primitiveGenerator.assembleSig(type);
        return new Symbol.DynamicVarSymbol(this.names.fromString(primitiveGenerator.sb.toString()), this.syms.noSymbol, new Symbol.MethodHandleSymbol(methodSymbolResolveInternalMethod), this.syms.classType, new PoolConstant.LoadableConstant[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void patchCompletingNormallyCases(List<JCTree.JCCase> list) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            JCTree.JCCase jCCase = (JCTree.JCCase) list2.head;
            if (jCCase.caseKind == CaseTree.CaseKind.STATEMENT && jCCase.completesNormally && list2.tail.nonEmpty()) {
                A a = list2.tail.head;
                if (((JCTree.JCCase) a).guard != null || ((JCTree.JCCase) a).labels.stream().anyMatch(new Predicate() { // from class: eje
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return TransPatterns.b((JCTree.JCCaseLabel) obj);
                    }
                })) {
                    ListBuffer listBuffer = new ListBuffer();
                    for (List list3 = list2; list3.nonEmpty(); list3 = list3.tail) {
                        listBuffer.appendList(((JCTree.JCCase) list3.head).stats);
                        if (!((JCTree.JCCase) list3.head).completesNormally) {
                            break;
                        }
                    }
                    jCCase.stats = listBuffer.toList();
                    jCCase.completesNormally = false;
                }
            }
        }
    }

    private void preparePatternMatchingCatchIfNeeded(JCTree.JCBlock jCBlock) {
        if (this.deconstructorCalls != null) {
            Names names = this.names;
            StringBuilder sb = new StringBuilder(PsiKeyword.CATCH);
            int i = this.variableIndex;
            this.variableIndex = i + 1;
            sb.append(i);
            sb.append(this.target.syntheticNameChar());
            Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4096L, names.fromString(sb.toString()), this.syms.throwableType, this.currentMethodSym);
            TreeMaker treeMaker = this.make;
            JCTree.JCVariableDecl jCVariableDeclVarDef = treeMaker.VarDef(varSymbol, null);
            TreeMaker treeMaker2 = this.make;
            jCBlock.patternMatchingCatch = new JCTree.JCBlock.PatternMatchingCatch(treeMaker.Catch(jCVariableDeclVarDef, treeMaker2.Block(0L, List.of(treeMaker2.Throw(makeNewClass(this.syms.matchExceptionType, List.of((JCTree.JCIdent) makeApply(treeMaker2.Ident(varSymbol), this.names.toString, List.nil()), this.make.Ident(varSymbol))))))), this.deconstructorCalls);
            this.deconstructorCalls = null;
        }
    }

    private Type principalType(JCTree jCTree) {
        return this.types.erasure(TreeInfo.primaryPatternType(jCTree));
    }

    /* JADX WARN: Code duplicated, block: B:16:0x006a  */
    /* JADX WARN: Code duplicated, block: B:18:0x006e  */
    /* JADX WARN: Code duplicated, block: B:20:0x0076  */
    /* JADX WARN: Code duplicated, block: B:21:0x0089  */
    /* JADX WARN: Multi-variable type inference failed */
    private List<JCTree.JCCase> processCases(final JCTree jCTree, List<JCTree.JCCase> list) {
        Symbol.VarSymbol varSymbol;
        Symbol.VarSymbol varSymbol2;
        boolean z;
        JCTree.JCExpression jCExpression;
        Symbol.VarSymbol varSymbol3;
        JCTree.JCInstanceOf jCInstanceOf;
        JCTree jCTree2;
        final ListBuffer listBuffer = new ListBuffer();
        final ListBuffer listBuffer2 = new ListBuffer();
        C1AccummulatorResolver c1AccummulatorResolver = new C1AccummulatorResolver() { // from class: com.sun.tools.javac.comp.x5
            @Override // com.sun.tools.javac.comp.TransPatterns.C1AccummulatorResolver
            public final void resolve(Symbol.VarSymbol varSymbol4, JCTree.JCExpression jCExpression2, Symbol.VarSymbol varSymbol5) {
                TransPatterns.j(this.a, listBuffer, jCTree, listBuffer2, varSymbol4, jCExpression2, varSymbol5);
            }
        };
        List list2 = list;
        Symbol.VarSymbol varSymbol4 = null;
        JCTree.JCExpression jCExpression2 = null;
        Symbol.VarSymbol varSymbol5 = null;
        boolean z2 = false;
        boolean z3 = false;
        while (list2.nonEmpty()) {
            A a = list2.head;
            boolean z4 = ((JCTree.JCCase) a).completesNormally;
            if (((JCTree.JCCase) a).labels.size() == 1) {
                JCTree.JCCaseLabel jCCaseLabel = ((JCTree.JCCase) list2.head).labels.head;
                if (jCCaseLabel instanceof JCTree.JCPatternCaseLabel) {
                    JCTree.JCPatternCaseLabel jCPatternCaseLabel = (JCTree.JCPatternCaseLabel) jCCaseLabel;
                    JCTree.JCExpression jCExpression3 = jCPatternCaseLabel.syntheticGuard;
                    if (jCExpression3 instanceof JCTree.JCBinary) {
                        JCTree.JCExpression jCExpression4 = ((JCTree.JCBinary) jCExpression3).lhs;
                        if (jCExpression4 instanceof JCTree.JCInstanceOf) {
                            JCTree.JCInstanceOf jCInstanceOf2 = (JCTree.JCInstanceOf) jCExpression4;
                            JCTree jCTree3 = jCInstanceOf2.pattern;
                            if (jCTree3 instanceof JCTree.JCBindingPattern) {
                                varSymbol2 = ((JCTree.JCBindingPattern) jCPatternCaseLabel.pat).var.sym;
                                z = jCInstanceOf2.allowNull;
                                jCExpression = jCInstanceOf2.expr;
                                varSymbol = ((JCTree.JCBindingPattern) jCTree3).var.sym;
                            } else if (jCExpression3 instanceof JCTree.JCInstanceOf) {
                                jCInstanceOf = (JCTree.JCInstanceOf) jCExpression3;
                                jCTree2 = jCInstanceOf.pattern;
                                if (jCTree2 instanceof JCTree.JCBindingPattern) {
                                    varSymbol2 = ((JCTree.JCBindingPattern) jCPatternCaseLabel.pat).var.sym;
                                    z = jCInstanceOf.allowNull;
                                    jCExpression = jCInstanceOf.expr;
                                    varSymbol = ((JCTree.JCBindingPattern) jCTree2).var.sym;
                                } else {
                                    varSymbol = null;
                                    varSymbol2 = null;
                                    z = false;
                                    jCExpression = null;
                                }
                            } else {
                                varSymbol = null;
                                varSymbol2 = null;
                                z = false;
                                jCExpression = null;
                            }
                        } else if (jCExpression3 instanceof JCTree.JCInstanceOf) {
                            jCInstanceOf = (JCTree.JCInstanceOf) jCExpression3;
                            jCTree2 = jCInstanceOf.pattern;
                            if (jCTree2 instanceof JCTree.JCBindingPattern) {
                                varSymbol2 = ((JCTree.JCBindingPattern) jCPatternCaseLabel.pat).var.sym;
                                z = jCInstanceOf.allowNull;
                                jCExpression = jCInstanceOf.expr;
                                varSymbol = ((JCTree.JCBindingPattern) jCTree2).var.sym;
                            } else {
                                varSymbol = null;
                                varSymbol2 = null;
                                z = false;
                                jCExpression = null;
                            }
                        } else {
                            varSymbol = null;
                            varSymbol2 = null;
                            z = false;
                            jCExpression = null;
                        }
                    } else if (jCExpression3 instanceof JCTree.JCInstanceOf) {
                        jCInstanceOf = (JCTree.JCInstanceOf) jCExpression3;
                        jCTree2 = jCInstanceOf.pattern;
                        if (jCTree2 instanceof JCTree.JCBindingPattern) {
                            varSymbol2 = ((JCTree.JCBindingPattern) jCPatternCaseLabel.pat).var.sym;
                            z = jCInstanceOf.allowNull;
                            jCExpression = jCInstanceOf.expr;
                            varSymbol = ((JCTree.JCBindingPattern) jCTree2).var.sym;
                        } else {
                            varSymbol = null;
                            varSymbol2 = null;
                            z = false;
                            jCExpression = null;
                        }
                    } else {
                        varSymbol = null;
                        varSymbol2 = null;
                        z = false;
                        jCExpression = null;
                    }
                } else {
                    varSymbol = null;
                    varSymbol2 = null;
                    z = false;
                    jCExpression = null;
                }
            } else {
                varSymbol = null;
                varSymbol2 = null;
                z = false;
                jCExpression = null;
            }
            if (varSymbol4 == null) {
                A a2 = list2.head;
                if (varSymbol2 != null) {
                    listBuffer.add((JCTree.JCCase) a2);
                    varSymbol3 = varSymbol;
                    varSymbol5 = varSymbol3;
                    varSymbol4 = varSymbol2;
                    jCExpression2 = jCExpression;
                } else {
                    listBuffer2.add((JCTree.JCCase) a2);
                }
            } else {
                if (varSymbol2 != null) {
                    varSymbol3 = varSymbol;
                    if (varSymbol4.type.tsym == varSymbol2.type.tsym && varSymbol4.isUnnamedVariable() == varSymbol2.isUnnamedVariable() && !z2 && !z && !z3 && !z4 && new TreeDiffer(this.types, List.of(varSymbol4), List.of(varSymbol2)).scan(jCExpression2, jCExpression)) {
                        listBuffer.add((JCTree.JCCase) list2.head);
                    }
                } else {
                    varSymbol3 = varSymbol;
                }
                c1AccummulatorResolver.resolve(varSymbol4, jCExpression2, varSymbol5);
                A a3 = list2.head;
                if (varSymbol2 != null) {
                    listBuffer.add((JCTree.JCCase) a3);
                } else {
                    listBuffer2.add((JCTree.JCCase) a3);
                }
                varSymbol5 = varSymbol3;
                varSymbol4 = varSymbol2;
                jCExpression2 = jCExpression;
            }
            list2 = list2.tail;
            z2 = z;
            z3 = z4;
        }
        c1AccummulatorResolver.resolve(varSymbol4, jCExpression2, varSymbol5);
        return listBuffer2.toList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public PoolConstant.LoadableConstant toLoadableConstant(JCTree.JCCaseLabel jCCaseLabel, Type type) {
        Type typeBoxedTypeOrType;
        if (jCCaseLabel.hasTag(JCTree.Tag.PATTERNCASELABEL)) {
            Type typePrincipalType = principalType(((JCTree.JCPatternCaseLabel) jCCaseLabel).pat);
            if (this.target.switchBootstrapOnlyAllowsReferenceTypesAsCaseLabels()) {
                typeBoxedTypeOrType = typePrincipalType;
                typeBoxedTypeOrType = this.types.boxedTypeOrType(typePrincipalType);
            }
            typeBoxedTypeOrType = typePrincipalType;
            if (typeBoxedTypeOrType.isReference()) {
                return this.types.isSubtype(type, typeBoxedTypeOrType) ? (PoolConstant.LoadableConstant) type : (PoolConstant.LoadableConstant) typeBoxedTypeOrType;
            }
            return makePrimitive(jCCaseLabel.pos(), typeBoxedTypeOrType);
        }
        if (!jCCaseLabel.hasTag(JCTree.Tag.CONSTANTCASELABEL) || TreeInfo.isNullCaseLabel(jCCaseLabel)) {
            return null;
        }
        JCTree.JCExpression jCExpression = ((JCTree.JCConstantCaseLabel) jCCaseLabel).expr;
        Symbol symbol = TreeInfo.symbol(jCExpression);
        if (symbol != null && symbol.isEnum() && symbol.kind == Kinds.Kind.VAR) {
            return type.tsym.isEnum() ? PoolConstant.LoadableConstant.String(symbol.getSimpleName().toString()) : createEnumDesc(jCCaseLabel.pos(), (Symbol.ClassSymbol) symbol.owner, symbol.getSimpleName());
        }
        Assert.checkNonNull(jCExpression.type.constValue());
        switch (AnonymousClass4.$SwitchMap$com$sun$tools$javac$code$TypeTag[jCExpression.type.getTag().ordinal()]) {
            case 1:
                return makeBooleanConstant(jCCaseLabel.pos(), ((Integer) jCExpression.type.constValue()).intValue());
            case 2:
            case 3:
            case 4:
            case 5:
                return PoolConstant.LoadableConstant.Int(((Integer) jCExpression.type.constValue()).intValue());
            case 6:
                return PoolConstant.LoadableConstant.Long(((Long) jCExpression.type.constValue()).longValue());
            case 7:
                return PoolConstant.LoadableConstant.Float(((Float) jCExpression.type.constValue()).floatValue());
            case 8:
                return PoolConstant.LoadableConstant.Double(((Double) jCExpression.type.constValue()).doubleValue());
            case 9:
                return PoolConstant.LoadableConstant.String((String) jCExpression.type.constValue());
            default:
                x1f.a();
                return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private UnrolledRecordPattern unrollRecordPattern(JCTree.JCRecordPattern jCRecordPattern) {
        JCTree.JCExpression jCExpressionMergeConditions;
        JCTree jCTreePrimaryPattern;
        JCTree.JCRecordPattern jCRecordPattern2 = jCRecordPattern;
        Type typeErasure = jCRecordPattern2.record.erasure(this.types);
        Names names = this.names;
        StringBuilder sb = new StringBuilder();
        sb.append(this.target.syntheticNameChar());
        sb.append("b");
        sb.append(this.target.syntheticNameChar());
        int i = this.variableIndex;
        this.variableIndex = i + 1;
        sb.append(i);
        JCTree.JCVariableDecl jCVariableDeclVarDef = this.make.at(jCRecordPattern2.pos()).VarDef(new Symbol.BindingSymbol(4096L, names.fromString(sb.toString()), typeErasure, this.currentMethodSym), null);
        Symbol.VarSymbol varSymbol = jCVariableDeclVarDef.sym;
        List recordComponents = jCRecordPattern2.record.getRecordComponents();
        List list = jCRecordPattern2.fullComponentTypes;
        List list2 = jCRecordPattern2.nested;
        JCTree.JCExpression jCExpression = null;
        JCTree.JCExpression jCExpressionMergeConditions2 = null;
        while (true) {
            boolean zIsSubtype = false;
            if (!recordComponents.nonEmpty()) {
                break;
            }
            Symbol.RecordComponent recordComponent = (Symbol.RecordComponent) recordComponents.head;
            Type typeErasure2 = this.types.erasure((Type) list.head);
            JCTree.JCPattern jCPattern = (JCTree.JCPattern) list2.head;
            if (jCPattern instanceof JCTree.JCRecordPattern) {
                UnrolledRecordPattern unrolledRecordPatternUnrollRecordPattern = unrollRecordPattern((JCTree.JCRecordPattern) jCPattern);
                JCTree.JCExpression jCExpressionNewGuard = unrolledRecordPatternUnrollRecordPattern.newGuard();
                if (jCExpressionNewGuard != null) {
                    jCExpressionMergeConditions2 = jCExpressionMergeConditions2 == null ? jCExpressionNewGuard : mergeConditions(jCExpressionMergeConditions2, jCExpressionNewGuard);
                }
                jCTreePrimaryPattern = unrolledRecordPatternUnrollRecordPattern.primaryPattern();
            } else if (jCPattern instanceof JCTree.JCAnyPattern) {
                jCTreePrimaryPattern = (JCTree.JCAnyPattern) jCPattern;
                zIsSubtype = true;
            } else {
                jCTreePrimaryPattern = (JCTree.JCBindingPattern) jCPattern;
                Types types = this.types;
                zIsSubtype = types.isSubtype(typeErasure2, types.boxedTypeOrType(types.erasure(jCTreePrimaryPattern.type)));
            }
            TreeMaker treeMakerAt = this.make.at(jCRecordPattern2.pos());
            TreeMaker treeMaker = this.make;
            JCTree.JCMethodInvocation type = treeMakerAt.App(treeMaker.Select(convert(treeMaker.Ident(varSymbol), varSymbol.type), recordComponent.accessor)).setType(this.types.erasure(recordComponent.accessor.getReturnType()));
            if (this.deconstructorCalls == null) {
                this.deconstructorCalls = Collections.newSetFromMap(new IdentityHashMap());
            }
            this.deconstructorCalls.add(type);
            JCTree.JCInstanceOf jCInstanceOf = (JCTree.JCInstanceOf) this.make.TypeTest(convert(type, typeErasure2), jCTreePrimaryPattern).setType((Type) this.syms.booleanType);
            jCInstanceOf.allowNull = zIsSubtype;
            JCTree.JCExpression jCExpressionMergeConditions3 = jCInstanceOf;
            if (jCExpression != null) {
                jCExpressionMergeConditions3 = mergeConditions(jCExpression, jCInstanceOf);
            }
            jCExpression = jCExpressionMergeConditions3;
            recordComponents = recordComponents.tail;
            list = list.tail;
            list2 = list2.tail;
            jCRecordPattern2 = jCRecordPattern;
        }
        Assert.check(recordComponents.isEmpty() == list2.isEmpty());
        if (jCExpression != null) {
            jCExpressionMergeConditions = jCExpressionMergeConditions2 != null ? mergeConditions(jCExpression, jCExpressionMergeConditions2) : jCExpression;
        } else {
            jCExpressionMergeConditions = null;
        }
        return new UnrolledRecordPattern((JCTree.JCBindingPattern) this.make.BindingPattern(jCVariableDeclVarDef).setType(varSymbol.type), jCExpressionMergeConditions);
    }

    public void appendBreakIfNeeded(JCTree jCTree, List<JCTree.JCCase> list, JCTree.JCCase jCCase) {
        if (jCCase.caseKind == CaseTree.CaseKind.RULE || (list.last() == jCCase && jCCase.completesNormally)) {
            JCTree.JCBreak jCBreakBreak = this.make.at(TreeInfo.endPos(jCCase.stats.nonEmpty() ? jCCase.stats.last() : jCCase)).Break(null);
            jCBreakBreak.target = jCTree;
            jCCase.stats = jCCase.stats.append(jCBreakBreak);
        }
    }

    public JCTree.JCExpression convert(JCTree.JCExpression jCExpression, Type type) {
        if (this.types.isSubtype(jCExpression.type, type)) {
            return jCExpression;
        }
        JCTree.JCTypeCast jCTypeCastTypeCast = this.make.at(jCExpression.pos()).TypeCast(this.make.Type(type), jCExpression);
        jCTypeCastTypeCast.type = type;
        return jCTypeCastTypeCast;
    }

    public JCTree.JCMethodInvocation makeApply(JCTree.JCExpression jCExpression, Name name, List<JCTree.JCExpression> list) {
        Symbol.MethodSymbol methodSymbolResolveInternalMethod = this.rs.resolveInternalMethod(this.currentClassTree.pos(), this.env, jCExpression.type, name, TreeInfo.types(list), List.nil());
        TreeMaker treeMaker = this.make;
        return treeMaker.App(treeMaker.Select(jCExpression, methodSymbolResolveInternalMethod), list).setType(this.types.erasure(methodSymbolResolveInternalMethod.getReturnType()));
    }

    public JCTree.JCBinary makeBinary(JCTree.Tag tag, JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        JCTree.JCBinary jCBinaryBinary = this.make.Binary(tag, jCExpression, jCExpression2);
        Symbol.OperatorSymbol operatorSymbolResolveBinary = this.operators.resolveBinary(jCBinaryBinary, tag, jCExpression.type, jCExpression2.type);
        jCBinaryBinary.operator = operatorSymbolResolveBinary;
        jCBinaryBinary.type = operatorSymbolResolveBinary.type.mo73getReturnType();
        return jCBinaryBinary;
    }

    public JCTree.JCExpression makeLit(Type type, Object obj) {
        return this.make.Literal(type.getTag(), obj).setType(type.constType(obj));
    }

    public JCTree.JCNewClass makeNewClass(Type type, List<JCTree.JCExpression> list) {
        TreeMaker treeMaker = this.make;
        JCTree.JCNewClass jCNewClassNewClass = treeMaker.NewClass(null, null, treeMaker.QualIdent(type.tsym), list, null);
        jCNewClassNewClass.constructor = this.rs.resolveConstructor(this.currentClassTree.pos(), this.env, type, TreeInfo.types(list), List.nil());
        jCNewClassNewClass.type = type;
        return jCNewClassNewClass;
    }

    public JCTree.JCExpression makeNull() {
        return makeLit(this.syms.botType, null);
    }

    public JCTree.JCInstanceOf makeTypeTest(JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        JCTree.JCInstanceOf jCInstanceOfTypeTest = this.make.TypeTest(jCExpression, jCExpression2);
        jCInstanceOfTypeTest.type = this.syms.booleanType;
        return jCInstanceOfTypeTest;
    }

    public JCTree.JCUnary makeUnary(JCTree.Tag tag, JCTree.JCExpression jCExpression) {
        JCTree.JCUnary jCUnaryUnary = this.make.Unary(tag, jCExpression);
        Symbol.OperatorSymbol operatorSymbolResolveUnary = this.operators.resolveUnary(jCUnaryUnary, tag, jCExpression.type);
        jCUnaryUnary.operator = operatorSymbolResolveUnary;
        jCUnaryUnary.type = operatorSymbolResolveUnary.type.mo73getReturnType();
        return jCUnaryUnary;
    }

    public JCTree.JCExpression mergeConditions(JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        if (!(jCExpression instanceof JCTree.JCBinary)) {
            return makeBinary(JCTree.Tag.AND, jCExpression, jCExpression2);
        }
        JCTree.JCExpression jCExpression3 = jCExpression;
        while (true) {
            JCTree.JCBinary jCBinary = (JCTree.JCBinary) jCExpression3;
            JCTree.JCExpression jCExpression4 = jCBinary.rhs;
            if (!(jCExpression4 instanceof JCTree.JCBinary)) {
                jCBinary.rhs = makeBinary(JCTree.Tag.AND, jCExpression4, jCExpression2);
                return jCExpression;
            }
            jCExpression3 = jCExpression4;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JCTree translateTopLevelClass(Env<AttrContext> env, JCTree jCTree, TreeMaker treeMaker) {
        try {
            this.make = treeMaker;
            this.env = env;
            translate(jCTree);
            return jCTree;
        } finally {
            this.make = null;
            this.env = null;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnyPattern(JCTree.JCAnyPattern jCAnyPattern) {
        this.result = this.make.Literal(Boolean.TRUE);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBinary(JCTree.JCBinary jCBinary) {
        this.bindingContext = new BasicBindingContext();
        try {
            super.visitBinary(jCBinary);
            this.result = this.bindingContext.decorateExpression(jCBinary);
        } finally {
            this.bindingContext.pop();
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
        Symbol.BindingSymbol bindingSymbol = (Symbol.BindingSymbol) jCBindingPattern.var.sym;
        Type typeErasure = this.types.erasure(TreeInfo.primaryPatternType(jCBindingPattern));
        Symbol.VarSymbol varSymbolBindingDeclared = this.bindingContext.bindingDeclared(bindingSymbol);
        if (varSymbolBindingDeclared == null || varSymbolBindingDeclared.isUnnamedVariable()) {
            this.result = this.make.Literal(Boolean.TRUE);
            return;
        }
        JCTree.JCAssign jCAssign = (JCTree.JCAssign) this.make.at(TreeInfo.getStartPos(jCBindingPattern)).Assign(this.make.Ident(varSymbolBindingDeclared), convert(this.make.Ident(this.currentValue).setType(this.currentValue.erasure(this.types)), typeErasure)).setType(varSymbolBindingDeclared.erasure(this.types));
        TreeMaker treeMaker = this.make;
        JCTree.LetExpr LetExpr = treeMaker.LetExpr(List.of(treeMaker.Exec(jCAssign)), this.make.Literal(Boolean.TRUE));
        LetExpr.needsCond = true;
        LetExpr.setType((Type) this.syms.booleanType);
        this.result = LetExpr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBlock(JCTree.JCBlock jCBlock) {
        final ListBuffer listBuffer = new ListBuffer();
        this.bindingContext = new BindingDeclarationFenceBindingContext(this) { // from class: com.sun.tools.javac.comp.TransPatterns.3
            final /* synthetic */ TransPatterns this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.comp.TransPatterns.BasicBindingContext, com.sun.tools.javac.comp.TransPatterns.BindingContext
            public boolean tryPrepend(Symbol.BindingSymbol bindingSymbol, JCTree.JCVariableDecl jCVariableDecl) {
                this.hoistedVarMap.put(bindingSymbol, jCVariableDecl.sym);
                listBuffer.append(jCVariableDecl);
                return true;
            }
        };
        Symbol.MethodSymbol methodSymbol = this.currentMethodSym;
        int i = this.variableIndex;
        boolean z = methodSymbol == null;
        try {
            Set<JCTree.JCMethodInvocation> set = this.deconstructorCalls;
            if (z) {
                try {
                    this.currentMethodSym = new Symbol.MethodSymbol(jCBlock.flags | 1048576, this.names.empty, null, this.currentClass);
                    this.variableIndex = 0;
                    this.deconstructorCalls = null;
                } catch (Throwable th) {
                    if (!z) {
                        throw th;
                    }
                    this.deconstructorCalls = set;
                    throw th;
                }
            }
            for (List list = jCBlock.stats; list.nonEmpty(); list = list.tail) {
                listBuffer.append((JCTree.JCStatement) translate((JCTree.JCStatement) list.head));
            }
            if (z) {
                preparePatternMatchingCatchIfNeeded(jCBlock);
            }
            if (z) {
                this.deconstructorCalls = set;
            }
            jCBlock.stats = listBuffer.toList();
            this.result = jCBlock;
            this.variableIndex = i;
            this.currentMethodSym = methodSymbol;
            this.bindingContext.pop();
        } catch (Throwable th2) {
            this.variableIndex = i;
            this.currentMethodSym = methodSymbol;
            this.bindingContext.pop();
            throw th2;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        Symbol.ClassSymbol classSymbol = this.currentClass;
        JCTree.JCClassDecl jCClassDecl2 = this.currentClassTree;
        ListBuffer<JCTree> listBuffer = this.pendingMethods;
        Symbol.MethodSymbol methodSymbol = this.currentMethodSym;
        Map<Symbol.RecordComponent, Symbol.MethodSymbol> map = this.component2Proxy;
        try {
            this.currentClass = jCClassDecl.sym;
            this.currentClassTree = jCClassDecl;
            this.pendingMethods = new ListBuffer<>();
            this.currentMethodSym = null;
            this.component2Proxy = new HashMap();
            super.visitClassDef(jCClassDecl);
            jCClassDecl.defs = jCClassDecl.defs.prependList(this.pendingMethods.toList());
        } finally {
            this.currentClass = classSymbol;
            this.currentClassTree = jCClassDecl2;
            this.pendingMethods = listBuffer;
            this.currentMethodSym = methodSymbol;
            this.component2Proxy = map;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConditional(JCTree.JCConditional jCConditional) {
        this.bindingContext = new BasicBindingContext();
        try {
            super.visitConditional(jCConditional);
            this.result = this.bindingContext.decorateExpression(jCConditional);
        } finally {
            this.bindingContext.pop();
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
        this.bindingContext = new BasicBindingContext();
        try {
            super.visitDoLoop(jCDoWhileLoop);
            this.result = this.bindingContext.decorateStatement(jCDoWhileLoop);
        } finally {
            this.bindingContext.pop();
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForLoop(JCTree.JCForLoop jCForLoop) {
        this.bindingContext = new BasicBindingContext();
        try {
            super.visitForLoop(jCForLoop);
            this.result = this.bindingContext.decorateStatement(jCForLoop);
        } finally {
            this.bindingContext.pop();
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        Symbol.VarSymbol bindingFor = (jCIdent.sym.flags() & Flags.MATCH_BINDING) != 0 ? this.bindingContext.getBindingFor((Symbol.BindingSymbol) jCIdent.sym) : null;
        if (bindingFor == null) {
            super.visitIdent(jCIdent);
        } else {
            this.result = this.make.at(jCIdent.pos).Ident(bindingFor).setType(bindingFor.erasure(this.types));
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIf(JCTree.JCIf jCIf) {
        this.bindingContext = new BasicBindingContext();
        try {
            super.visitIf(jCIf);
            this.result = this.bindingContext.decorateStatement(jCIf);
        } finally {
            this.bindingContext.pop();
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLambda(JCTree.JCLambda jCLambda) {
        BindingContext bindingContext = this.bindingContext;
        int i = this.variableIndex;
        try {
            this.bindingContext = new BindingDeclarationFenceBindingContext();
            this.variableIndex = 0;
            jCLambda.params = translate(jCLambda.params);
            Set<JCTree.JCMethodInvocation> set = this.deconstructorCalls;
            try {
                this.deconstructorCalls = null;
                JCTree jCTreeTranslate = translate(jCLambda.body);
                jCLambda.body = jCTreeTranslate;
                if (this.deconstructorCalls != null) {
                    if (jCTreeTranslate instanceof JCTree.JCExpression) {
                        JCTree.JCExpression jCExpression = (JCTree.JCExpression) jCTreeTranslate;
                        boolean zHasTag = jCExpression.type.hasTag(TypeTag.VOID);
                        TreeMaker treeMaker = this.make;
                        if (zHasTag) {
                            jCLambda.body = treeMaker.Block(0L, List.of(treeMaker.Exec(jCExpression)));
                        } else {
                            jCLambda.body = treeMaker.Block(0L, List.of(treeMaker.Return(jCExpression)));
                        }
                    }
                    JCTree jCTree = jCLambda.body;
                    if (!(jCTree instanceof JCTree.JCBlock)) {
                        throw Assert.error("Unexpected lambda body type: " + jCLambda.body.getKind());
                    }
                    preparePatternMatchingCatchIfNeeded((JCTree.JCBlock) jCTree);
                }
                this.deconstructorCalls = set;
                this.result = jCLambda;
                this.variableIndex = i;
                this.bindingContext = bindingContext;
            } catch (Throwable th) {
                this.deconstructorCalls = set;
                throw th;
            }
        } catch (Throwable th2) {
            this.variableIndex = i;
            this.bindingContext = bindingContext;
            throw th2;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
        Symbol.MethodSymbol methodSymbol = this.currentMethodSym;
        int i = this.variableIndex;
        Set<JCTree.JCMethodInvocation> set = this.deconstructorCalls;
        try {
            this.currentMethodSym = jCMethodDecl.sym;
            this.variableIndex = 0;
            this.deconstructorCalls = null;
            super.visitMethodDef(jCMethodDecl);
            preparePatternMatchingCatchIfNeeded(jCMethodDecl.body);
        } finally {
            this.variableIndex = i;
            this.currentMethodSym = methodSymbol;
            this.deconstructorCalls = set;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitRecordPattern(JCTree.JCRecordPattern jCRecordPattern) {
        Assert.error();
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitch(JCTree.JCSwitch jCSwitch) {
        handleSwitch(jCSwitch, jCSwitch.selector, jCSwitch.cases, jCSwitch.hasUnconditionalPattern, jCSwitch.patternSwitch);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
        handleSwitch(jCSwitchExpression, jCSwitchExpression.selector, jCSwitchExpression.cases, jCSwitchExpression.hasUnconditionalPattern, jCSwitchExpression.patternSwitch);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTry(JCTree.JCTry jCTry) {
        jCTry.resources = translate(jCTry.resources);
        Set<JCTree.JCMethodInvocation> set = this.deconstructorCalls;
        try {
            this.deconstructorCalls = null;
            JCTree.JCBlock jCBlock = (JCTree.JCBlock) translate(jCTry.body);
            jCTry.body = jCBlock;
            preparePatternMatchingCatchIfNeeded(jCBlock);
            this.deconstructorCalls = set;
            jCTry.catchers = translateCatchers(jCTry.catchers);
            jCTry.finalizer = (JCTree.JCBlock) translate(jCTry.finalizer);
            this.result = jCTry;
        } catch (Throwable th) {
            this.deconstructorCalls = set;
            throw th;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
        Type type = jCInstanceOf.erasedExprOriginalType;
        JCTree.JCExpression jCExpressionNewGuard = null;
        if (type != null && !this.types.isSameType(jCInstanceOf.expr.type, type)) {
            Names names = this.names;
            StringBuilder sb = new StringBuilder("temp");
            int i = this.variableIndex;
            this.variableIndex = i + 1;
            sb.append(i);
            sb.append(this.target.syntheticNameChar());
            JCTree.JCVariableDecl jCVariableDeclVarDef = this.make.at(jCInstanceOf.pos()).VarDef(new Symbol.BindingSymbol(4112L, names.fromString(sb.toString()), jCInstanceOf.erasedExprOriginalType, this.currentMethodSym), null);
            JCTree.Tag tag = JCTree.Tag.AND;
            TreeMaker treeMaker = this.make;
            JCTree.JCExpression type2 = treeMaker.TypeTest(jCInstanceOf.expr, treeMaker.BindingPattern(jCVariableDeclVarDef).setType(jCInstanceOf.erasedExprOriginalType)).setType((Type) this.syms.booleanType);
            TreeMaker treeMaker2 = this.make;
            this.result = translate(makeBinary(tag, type2, treeMaker2.TypeTest(treeMaker2.Ident(jCVariableDeclVarDef), jCInstanceOf.pattern).setType((Type) this.syms.booleanType)));
            return;
        }
        JCTree jCTree = jCInstanceOf.pattern;
        if (!(jCTree instanceof JCTree.JCPattern)) {
            super.visitTypeTest(jCInstanceOf);
            return;
        }
        JCTree.JCPattern jCPattern = (JCTree.JCPattern) jCTree;
        if (jCPattern instanceof JCTree.JCRecordPattern) {
            UnrolledRecordPattern unrolledRecordPatternUnrollRecordPattern = unrollRecordPattern((JCTree.JCRecordPattern) jCPattern);
            JCTree.JCBindingPattern jCBindingPatternPrimaryPattern = unrolledRecordPatternUnrollRecordPattern.primaryPattern();
            jCExpressionNewGuard = unrolledRecordPatternUnrollRecordPattern.newGuard();
            jCPattern = jCBindingPatternPrimaryPattern;
        }
        Type type3 = jCInstanceOf.expr.type.hasTag(TypeTag.BOT) ? this.syms.objectType : jCInstanceOf.expr.type;
        Symbol.VarSymbol varSymbol = this.currentValue;
        this.bindingContext = new BasicBindingContext();
        try {
            JCTree.JCExpression jCExpression = (JCTree.JCExpression) translate(jCInstanceOf.expr);
            Symbol symbol = TreeInfo.symbol(jCExpression);
            if (symbol != null && symbol.kind == Kinds.Kind.VAR && symbol.owner.kind.matches(Kinds.KindSelector.VAL_MTH)) {
                this.currentValue = (Symbol.VarSymbol) symbol;
            } else {
                Names names2 = this.names;
                StringBuilder sb2 = new StringBuilder("patt");
                int i2 = this.variableIndex;
                this.variableIndex = i2 + 1;
                sb2.append(i2);
                sb2.append(this.target.syntheticNameChar());
                sb2.append("temp");
                this.currentValue = new Symbol.VarSymbol(4112L, names2.fromString(sb2.toString()), type3, this.currentMethodSym);
            }
            Type typeErasure = this.types.erasure(TreeInfo.primaryPatternType(jCPattern));
            JCTree.JCExpression jCExpressionMakeBinary = (JCTree.JCExpression) translate(jCPattern);
            if (!jCInstanceOf.allowNull || !this.types.isSubtype(this.currentValue.type, typeErasure)) {
                jCExpressionMakeBinary = makeBinary(JCTree.Tag.AND, makeTypeTest(this.make.Ident(this.currentValue), this.make.Type(typeErasure)), jCExpressionMakeBinary);
            }
            if (jCExpressionNewGuard != null) {
                jCExpressionMakeBinary = makeBinary(JCTree.Tag.AND, jCExpressionMakeBinary, (JCTree.JCExpression) translate(jCExpressionNewGuard));
            }
            if (this.currentValue != symbol) {
                jCExpressionMakeBinary = this.make.at(jCInstanceOf.pos).LetExpr(this.make.VarDef(this.currentValue, jCExpression), jCExpressionMakeBinary).setType((Type) this.syms.booleanType);
                ((JCTree.LetExpr) jCExpressionMakeBinary).needsCond = true;
            }
            this.result = this.bindingContext.decorateExpression(jCExpressionMakeBinary);
        } finally {
            this.currentValue = varSymbol;
            this.bindingContext.pop();
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        Symbol.MethodSymbol methodSymbol = this.currentMethodSym;
        int i = this.variableIndex;
        try {
            jCVariableDecl.mods = (JCTree.JCModifiers) translate(jCVariableDecl.mods);
            jCVariableDecl.vartype = (JCTree.JCExpression) translate(jCVariableDecl.vartype);
            if (this.currentMethodSym == null) {
                this.currentMethodSym = new Symbol.MethodSymbol((jCVariableDecl.mods.flags & 8) | 1048576, this.names.empty, null, this.currentClass);
                this.variableIndex = 0;
            }
            JCTree.JCExpression jCExpression = jCVariableDecl.init;
            if (jCExpression != null) {
                jCVariableDecl.init = (JCTree.JCExpression) translate(jCExpression);
            }
            this.result = jCVariableDecl;
        } finally {
            this.variableIndex = i;
            this.currentMethodSym = methodSymbol;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
        this.bindingContext = new BasicBindingContext();
        try {
            super.visitWhileLoop(jCWhileLoop);
            this.result = this.bindingContext.decorateStatement(jCWhileLoop);
        } finally {
            this.bindingContext.pop();
        }
    }

    public class PrimitiveGenerator extends Types.SignatureGenerator {
        StringBuilder sb;

        /* JADX WARN: Illegal instructions before constructor call */
        public PrimitiveGenerator() {
            Types types = TransPatterns.this.types;
            Objects.requireNonNull(types);
            super();
            this.sb = new StringBuilder();
        }

        @Override // com.sun.tools.javac.code.Types.SignatureGenerator
        public void append(byte[] bArr) {
            this.sb.append(new String(bArr));
        }

        public String toString() {
            return this.sb.toString();
        }

        @Override // com.sun.tools.javac.code.Types.SignatureGenerator
        public void append(char c) {
            this.sb.append(c);
        }

        @Override // com.sun.tools.javac.code.Types.SignatureGenerator
        public void append(Name name) {
            this.sb.append(name.toString());
        }
    }
}

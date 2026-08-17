package com.sun.tools.javac.jvm;

import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.TargetType;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Annotate;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Check;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.comp.Lower;
import com.sun.tools.javac.comp.Resolve;
import com.sun.tools.javac.jvm.Items.LocalItem;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.model.FilteredMemberList;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import defpackage.by5;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import javax.lang.model.element.ElementKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Gen extends JCTree.Visitor {
    protected static final Context.Key<Gen> genKey = new Context.Key<>();
    private final String accessDollar;
    private final Annotate annotate;
    private Env<AttrContext> attrEnv;
    private final Check chk;
    private Code code;
    private final StringConcat concat;
    private final boolean debugCode;
    private boolean disableVirtualizedPrivateInvoke;
    EndPosTable endPosTable;
    Env<GenContext> env;
    private final boolean genCrt;
    boolean inCondSwitchExpression;
    private Items items;
    private final boolean lineDebugInfo;
    private final Log log;
    private final Lower lower;
    private final TreeMaker make;
    private final Type methodType;
    private final Names names;
    final PoolWriter poolWriter;
    Type pt;
    Map<Type, Symbol> qualifiedSymbolCache;
    Items.Item result;
    private final Resolve rs;
    List<Items.LocalItem> stackBeforeSwitchExpression;
    private final Code.StackMapFormat stackMap;
    Code.Chain switchExpressionFalseChain;
    Code.Chain switchExpressionTrueChain;
    Items.LocalItem switchResult;
    private final Symtab syms;
    private final Target target;
    private JCTree.JCCompilationUnit toplevel;
    private final Types types;
    private final boolean varDebugInfo;
    private int nerrs = 0;
    PatternMatchingCatchConfiguration patternMatchingCatchConfiguration = new PatternMatchingCatchConfiguration(Collections.unmodifiableSet(new HashSet(Arrays.asList(new JCTree.JCMethodInvocation[0]))), null, null, null);
    private ClassReferenceVisitor classReferenceVisitor = new ClassReferenceVisitor();

    /* JADX INFO: renamed from: com.sun.tools.javac.jvm.Gen$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr;
            try {
                iArr[JCTree.Tag.BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.METHODDEF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.VARDEF.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTINC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTDEC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NEG.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.COMPL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PREINC.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PREDEC.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NULLCHK.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr2 = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr2;
            try {
                iArr2[TypeTag.METHOD.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public class ClassReferenceVisitor extends JCTree.Visitor {
        public ClassReferenceVisitor() {
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBinary(JCTree.JCBinary jCBinary) {
            jCBinary.lhs.accept(this);
            jCBinary.rhs.accept(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitConditional(JCTree.JCConditional jCConditional) {
            jCConditional.cond.accept(this);
            jCConditional.truepart.accept(this);
            jCConditional.falsepart.accept(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIdent(JCTree.JCIdent jCIdent) {
            Symbol symbol = jCIdent.sym.owner;
            if (symbol instanceof Symbol.ClassSymbol) {
                Gen.this.poolWriter.putClass((Symbol.ClassSymbol) symbol);
            }
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitParens(JCTree.JCParens jCParens) {
            jCParens.expr.accept(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
            if (jCFieldAccess.selected.type.hasTag(TypeTag.CLASS)) {
                Gen.this.makeRef(jCFieldAccess.selected.pos(), jCFieldAccess.selected.type);
            }
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTree(JCTree jCTree) {
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
            jCTypeCast.expr.accept(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitUnary(JCTree.JCUnary jCUnary) {
            jCUnary.arg.accept(this);
        }
    }

    public static class CodeSizeOverflow extends RuntimeException {
        private static final long serialVersionUID = 0;
    }

    public final class GenContext {
        final int limit;
        Code.Chain exit = null;
        Code.Chain cont = null;
        GenFinalizer finalize = null;
        boolean isSwitch = false;
        ListBuffer<Integer> gaps = null;

        public GenContext() {
            Code code = Gen.this.code;
            this.limit = code != null ? code.nextreg : 0;
        }

        public void addCont(Code.Chain chain) {
            if (chain != null) {
                chain.state.defined.excludeFrom(this.limit);
            }
            this.cont = Code.mergeChains(chain, this.cont);
        }

        public void addExit(Code.Chain chain) {
            if (chain != null) {
                chain.state.defined.excludeFrom(this.limit);
            }
            this.exit = Code.mergeChains(chain, this.exit);
        }
    }

    public abstract class GenFinalizer {
        public GenFinalizer() {
        }

        public void afterBody() {
        }

        public abstract void gen();

        public abstract void genLast();

        public boolean hasFinalizer() {
            return true;
        }
    }

    public static final class PatternMatchingCatchConfiguration {
        private final JCTree.JCCatch handler;
        private final Set<JCTree.JCMethodInvocation> invocations;
        private final ListBuffer<int[]> ranges;
        private final Code.State startState;

        public PatternMatchingCatchConfiguration(Set<JCTree.JCMethodInvocation> set, ListBuffer<int[]> listBuffer, JCTree.JCCatch jCCatch, Code.State state) {
            this.invocations = set;
            this.ranges = listBuffer;
            this.handler = jCCatch;
            this.startState = state;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof PatternMatchingCatchConfiguration)) {
                return false;
            }
            PatternMatchingCatchConfiguration patternMatchingCatchConfiguration = (PatternMatchingCatchConfiguration) obj;
            return Objects.equals(this.startState, patternMatchingCatchConfiguration.startState) && Objects.equals(this.handler, patternMatchingCatchConfiguration.handler) && Objects.equals(this.ranges, patternMatchingCatchConfiguration.ranges) && Objects.equals(this.invocations, patternMatchingCatchConfiguration.invocations);
        }

        public JCTree.JCCatch handler() {
            return this.handler;
        }

        public final int hashCode() {
            return (((((Objects.hashCode(this.invocations) * 31) + Objects.hashCode(this.ranges)) * 31) + Objects.hashCode(this.handler)) * 31) + Objects.hashCode(this.startState);
        }

        public Set<JCTree.JCMethodInvocation> invocations() {
            return this.invocations;
        }

        public ListBuffer<int[]> ranges() {
            return this.ranges;
        }

        public PatternMatchingCatchConfiguration restart(Code.State state) {
            return new PatternMatchingCatchConfiguration(invocations(), new ListBuffer(), handler(), state);
        }

        public Code.State startState() {
            return this.startState;
        }

        public final String toString() {
            return "PatternMatchingCatchConfiguration[invocations=" + Objects.toString(this.invocations) + ", ranges=" + Objects.toString(this.ranges) + ", handler=" + Objects.toString(this.handler) + ", startState=" + Objects.toString(this.startState) + "]";
        }
    }

    public Gen(Context context) {
        context.put(genKey, this);
        Names namesInstance = Names.instance(context);
        this.names = namesInstance;
        this.log = Log.instance(context);
        Symtab symtabInstance = Symtab.instance(context);
        this.syms = symtabInstance;
        this.chk = Check.instance(context);
        this.rs = Resolve.instance(context);
        this.make = TreeMaker.instance(context);
        Target targetInstance = Target.instance(context);
        this.target = targetInstance;
        Types typesInstance = Types.instance(context);
        this.types = typesInstance;
        this.concat = StringConcat.instance(context);
        this.methodType = new Type.MethodType(null, null, null, symtabInstance.methodClass);
        this.accessDollar = "access" + targetInstance.syntheticNameChar();
        this.lower = Lower.instance(context);
        Options optionsInstance = Options.instance(context);
        Option option = Option.G_CUSTOM;
        this.lineDebugInfo = optionsInstance.isUnset(option) || optionsInstance.isSet(option, "lines");
        this.varDebugInfo = optionsInstance.isUnset(option) ? optionsInstance.isSet(Option.G) : optionsInstance.isSet(option, "vars");
        this.genCrt = optionsInstance.isSet(Option.XJCOV);
        this.debugCode = optionsInstance.isSet("debug.code");
        this.disableVirtualizedPrivateInvoke = optionsInstance.isSet("disableVirtualizedPrivateInvoke");
        this.poolWriter = new PoolWriter(typesInstance, namesInstance);
        this.stackMap = Code.StackMapFormat.JSR202;
        this.annotate = Annotate.instance(context);
        this.qualifiedSymbolCache = new HashMap();
    }

    private Type checkDimension(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        checkDimensionInternal(diagnosticPosition, type);
        return type;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void checkDimensionInternal(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        int i = AnonymousClass3.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
        if (i != 1) {
            if (i == 2 && this.types.dimensions(type) > 255) {
                this.log.error(diagnosticPosition, CompilerProperties.Errors.LimitDimensions);
                this.nerrs++;
                return;
            }
            return;
        }
        checkDimension(diagnosticPosition, type.mo73getReturnType());
        for (List listMo71getParameterTypes = type.mo71getParameterTypes(); listMo71getParameterTypes.nonEmpty(); listMo71getParameterTypes = listMo71getParameterTypes.tail) {
            checkDimension(diagnosticPosition, (Type) listMo71getParameterTypes.head);
        }
    }

    private void checkStringConstant(JCDiagnostic.DiagnosticPosition diagnosticPosition, Object obj) {
        if (this.nerrs != 0 || obj == null || !(obj instanceof String) || ((String) obj).length() < 65535) {
            return;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.LimitString);
        this.nerrs++;
    }

    private void doHandleSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
        List<Items.LocalItem> list = this.stackBeforeSwitchExpression;
        Items.LocalItem localItem = this.switchResult;
        int i = this.code.nextreg;
        try {
            this.stackBeforeSwitchExpression = List.nil();
            this.switchResult = null;
            if (hasTry(jCSwitchExpression)) {
                while (true) {
                    Code.State state = this.code.state;
                    if (state.stacksize <= 0) {
                        break;
                    }
                    Type typePeek = state.peek();
                    Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4096L, this.names.fromString(this.target.syntheticNameChar() + "stack" + this.target.syntheticNameChar() + jCSwitchExpression.pos + this.target.syntheticNameChar() + this.code.state.stacksize), typePeek, this.env.enclMethod.sym);
                    Items items = this.items;
                    Objects.requireNonNull(items);
                    Items.LocalItem localItem2 = items.new LocalItem(typePeek, this.code.newLocal(varSymbol));
                    this.stackBeforeSwitchExpression = this.stackBeforeSwitchExpression.prepend(localItem2);
                    localItem2.store();
                }
                this.switchResult = makeTemp(jCSwitchExpression.type);
            }
            Code code = this.code;
            int letExprStackPos = code.setLetExprStackPos(code.state.stacksize);
            try {
                handleSwitch(jCSwitchExpression, jCSwitchExpression.selector, jCSwitchExpression.cases, jCSwitchExpression.patternSwitch);
                this.code.setLetExprStackPos(letExprStackPos);
                this.stackBeforeSwitchExpression = list;
                this.switchResult = localItem;
                this.code.endScopes(i);
            } catch (Throwable th) {
                this.code.setLetExprStackPos(letExprStackPos);
                throw th;
            }
        } catch (Throwable th2) {
            this.stackBeforeSwitchExpression = list;
            this.switchResult = localItem;
            this.code.endScopes(i);
            throw th2;
        }
    }

    private void genLoop(JCTree.JCStatement jCStatement, JCTree.JCStatement jCStatement2, JCTree.JCExpression jCExpression, List<JCTree.JCExpressionStatement> list, boolean z) {
        Items.CondItem condItemMakeCondItem;
        Items.CondItem condItemMakeCondItem2;
        Env<GenContext> envDup = this.env.dup(jCStatement, new GenContext());
        int iEntryPoint = this.code.entryPoint();
        if (z) {
            if (jCExpression != null) {
                this.code.statBegin(jCExpression.pos);
                Assert.check(this.code.isStatementStart());
                condItemMakeCondItem2 = genCond(TreeInfo.skipParens(jCExpression), 8);
            } else {
                condItemMakeCondItem2 = this.items.makeCondItem(167);
            }
            Code.Chain chainJumpFalse = condItemMakeCondItem2.jumpFalse();
            this.code.resolve(condItemMakeCondItem2.trueJumps);
            Assert.check(this.code.isStatementStart());
            genStat(jCStatement2, envDup, 17);
            this.code.resolve(envDup.info.cont);
            genStats(list, envDup);
            Code code = this.code;
            code.resolve(code.branch(167), iEntryPoint);
            this.code.resolve(chainJumpFalse);
        } else {
            genStat(jCStatement2, envDup, 17);
            this.code.resolve(envDup.info.cont);
            genStats(list, envDup);
            if (this.code.isAlive()) {
                if (jCExpression != null) {
                    this.code.statBegin(jCExpression.pos);
                    Assert.check(this.code.isStatementStart());
                    condItemMakeCondItem = genCond(TreeInfo.skipParens(jCExpression), 8);
                } else {
                    condItemMakeCondItem = this.items.makeCondItem(167);
                }
                this.code.resolve(condItemMakeCondItem.jumpTrue(), iEntryPoint);
                Assert.check(this.code.isStatementStart());
                this.code.resolve(condItemMakeCondItem.falseJumps);
            }
        }
        this.code.resolve(envDup.info.exit);
    }

    private void genNullCheck(JCTree jCTree) {
        this.code.statBegin(jCTree.pos);
        JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCTree.pos();
        Symtab symtab = this.syms;
        callMethod(diagnosticPositionPos, symtab.objectsType, this.names.requireNonNull, List.of(symtab.objectType), true);
        this.code.emitop0(87);
    }

    private void generatePatternMatchingCatch(Env<GenContext> env) {
        if (this.patternMatchingCatchConfiguration.handler == null || this.patternMatchingCatchConfiguration.ranges.isEmpty()) {
            return;
        }
        Code.Chain chainBranch = this.code.branch(167);
        JCTree.JCCatch jCCatchHandler = this.patternMatchingCatchConfiguration.handler();
        this.code.entryPoint(this.patternMatchingCatchConfiguration.startState(), jCCatchHandler.param.sym.type);
        genPatternMatchingCatch(jCCatchHandler, env, this.patternMatchingCatchConfiguration.ranges.toList());
        this.code.resolve(chainBranch);
    }

    private void generateReferencesToPrunedTree(Symbol.ClassSymbol classSymbol) {
        List<JCTree> list = this.lower.prunedTree.get(classSymbol);
        if (list != null) {
            Iterator<JCTree> it = list.iterator();
            while (it.hasNext()) {
                it.next().accept(this.classReferenceVisitor);
            }
        }
    }

    private List<Attribute.TypeCompound> getAndRemoveNonFieldTAs(Symbol.VarSymbol varSymbol) {
        List<Attribute.TypeCompound> rawTypeAttributes = varSymbol.getRawTypeAttributes();
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        for (Attribute.TypeCompound typeCompound : rawTypeAttributes) {
            Assert.check(typeCompound.getPosition().type != TargetType.UNKNOWN);
            if (typeCompound.getPosition().type == TargetType.FIELD) {
                listBuffer.add(typeCompound);
            } else {
                listBuffer2.add(typeCompound);
            }
        }
        varSymbol.setTypeAttributes(listBuffer.toList());
        return listBuffer2.toList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void handleSwitch(JCTree jCTree, JCTree.JCExpression jCExpression, List<JCTree.JCCase> list, boolean z) {
        int[] iArr;
        int i;
        int i2;
        int i3 = this.code.nextreg;
        Assert.check(!jCExpression.type.hasTag(TypeTag.CLASS));
        int iEntryPoint = z ? this.code.entryPoint() : -1;
        int iCurCP = this.genCrt ? this.code.curCP() : 0;
        Assert.check(this.code.isStatementStart());
        Items.Item itemGenExpr = genExpr(jCExpression, this.syms.intType);
        if (list.isEmpty()) {
            itemGenExpr.load().drop();
            if (this.genCrt) {
                this.code.crt.put(TreeInfo.skipParens(jCExpression), 8, iCurCP, this.code.curCP());
            }
        } else {
            itemGenExpr.load();
            if (this.genCrt) {
                this.code.crt.put(TreeInfo.skipParens(jCExpression), 8, iCurCP, this.code.curCP());
            }
            Env<GenContext> envDup = this.env.dup(jCTree, new GenContext());
            envDup.info.isSwitch = true;
            int length = list.length();
            int[] iArr2 = new int[length];
            int i4 = Integer.MAX_VALUE;
            int i5 = Integer.MIN_VALUE;
            List list2 = list;
            int i6 = 0;
            int i7 = -1;
            for (int i8 = 0; i8 < length; i8++) {
                A a = list2.head;
                JCTree.JCCaseLabel jCCaseLabel = ((JCTree.JCCase) a).labels.head;
                if (jCCaseLabel instanceof JCTree.JCConstantCaseLabel) {
                    JCTree.JCConstantCaseLabel jCConstantCaseLabel = (JCTree.JCConstantCaseLabel) jCCaseLabel;
                    Assert.check(((JCTree.JCCase) a).labels.size() == 1);
                    int iIntValue = ((Number) jCConstantCaseLabel.expr.type.constValue()).intValue();
                    iArr2[i8] = iIntValue;
                    if (iIntValue < i4) {
                        i4 = iIntValue;
                    }
                    if (i5 < iIntValue) {
                        i5 = iIntValue;
                    }
                    i6++;
                } else {
                    Assert.check(i7 == -1);
                    i7 = i8;
                }
                list2 = list2.tail;
            }
            long j = i5;
            long j2 = i4;
            long j3 = j - j2;
            long j4 = i6;
            int i9 = (i6 <= 0 || j3 + 14 > ((2 * j4) + 3) + (j4 * 3)) ? 171 : 170;
            int iCurCP2 = this.code.curCP();
            this.code.emitop0(i9);
            this.code.align(4);
            int iCurCP3 = this.code.curCP();
            this.code.emit4(-1);
            Code code = this.code;
            if (i9 == 170) {
                code.emit4(i4);
                this.code.emit4(i5);
                for (long j5 = j2; j5 <= j; j5++) {
                    this.code.emit4(-1);
                }
                iArr = null;
            } else {
                int i10 = -1;
                code.emit4(i6);
                int i11 = 0;
                while (i11 < i6) {
                    this.code.emit4(i10);
                    this.code.emit4(i10);
                    i11++;
                    i10 = -1;
                }
                iArr = new int[length];
            }
            Code.State stateDup = this.code.state.dup();
            this.code.markDead();
            List list3 = list;
            int i12 = 0;
            while (i12 < length) {
                int i13 = length;
                JCTree.JCCase jCCase = (JCTree.JCCase) list3.head;
                List list4 = list3.tail;
                int i14 = i4;
                int iEntryPoint2 = this.code.entryPoint(stateDup);
                if (i12 != i7) {
                    i2 = i12;
                    if (i9 == 170) {
                        this.code.put4(iCurCP3 + (((iArr2[i2] - i14) + 3) * 4), iEntryPoint2 - iCurCP2);
                    } else {
                        iArr[i2] = iEntryPoint2 - iCurCP2;
                    }
                } else {
                    i2 = i12;
                    this.code.put4(iCurCP3, iEntryPoint2 - iCurCP2);
                }
                genStats(jCCase.stats, envDup, 16);
                i12 = i2 + 1;
                length = i13;
                i4 = i14;
                list3 = list4;
            }
            int i15 = length;
            if (envDup.info.cont != null) {
                Assert.check(z);
                this.code.resolve(envDup.info.cont, iEntryPoint);
            }
            this.code.resolve(envDup.info.exit);
            if (this.code.get4(iCurCP3) == -1) {
                Code code2 = this.code;
                code2.put4(iCurCP3, code2.entryPoint(stateDup) - iCurCP2);
            }
            if (i9 == 170) {
                int i16 = this.code.get4(iCurCP3);
                for (long j6 = j2; j6 <= j; j6++) {
                    int i17 = (int) (((long) iCurCP3) + (((j6 - j2) + 3) * 4));
                    if (this.code.get4(i17) == -1) {
                        this.code.put4(i17, i16);
                    }
                }
            } else {
                if (i7 >= 0) {
                    while (i7 < i15 - 1) {
                        int i18 = i7 + 1;
                        iArr2[i7] = iArr2[i18];
                        iArr[i7] = iArr[i18];
                        i7 = i18;
                    }
                }
                if (i6 > 0) {
                    i = 0;
                    qsort2(iArr2, iArr, 0, i6 - 1);
                } else {
                    i = 0;
                }
                while (i < i6) {
                    int i19 = i + 1;
                    int i20 = (i19 * 8) + iCurCP3;
                    this.code.put4(i20, iArr2[i]);
                    this.code.put4(i20 + 4, iArr[i]);
                    i = i19;
                }
            }
            if (jCTree instanceof JCTree.JCSwitchExpression) {
                this.code.statBegin(TreeInfo.endPos(jCTree));
            }
        }
        this.code.endScopes(i3);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.sun.tools.javac.jvm.Gen$1HasTryScanner, com.sun.tools.javac.tree.TreeScanner] */
    private boolean hasTry(JCTree.JCSwitchExpression jCSwitchExpression) {
        ?? r0 = new TreeScanner() { // from class: com.sun.tools.javac.jvm.Gen.1HasTryScanner
            private boolean hasTry;

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitLambda(JCTree.JCLambda jCLambda) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitSynchronized(JCTree.JCSynchronized jCSynchronized) {
                this.hasTry = true;
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitTry(JCTree.JCTry jCTry) {
                this.hasTry = true;
            }
        };
        r0.scan(jCSwitchExpression);
        return ((C1HasTryScanner) r0).hasTry;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int initCode(JCTree.JCMethodDecl jCMethodDecl, Env<GenContext> env, boolean z) {
        Symbol.MethodSymbol methodSymbol = jCMethodDecl.sym;
        Code code = new Code(methodSymbol, z, this.lineDebugInfo ? this.toplevel.lineMap : null, this.varDebugInfo, this.stackMap, this.debugCode, this.genCrt ? new CRTable(jCMethodDecl, env.toplevel.endPositions) : null, this.syms, this.types, this.poolWriter);
        this.code = code;
        methodSymbol.code = code;
        this.items = new Items(this.poolWriter, code, this.syms, this.types);
        if (this.code.debugCode) {
            System.err.println(methodSymbol + " for body " + jCMethodDecl);
        }
        if ((jCMethodDecl.mods.flags & 8) == 0) {
            Type typeUninitializedThis = methodSymbol.owner.type;
            if (methodSymbol.isConstructor() && typeUninitializedThis != this.syms.objectType) {
                typeUninitializedThis = UninitializedType.uninitializedThis(typeUninitializedThis);
            }
            Type type = typeUninitializedThis;
            Code code2 = this.code;
            code2.setDefined(code2.newLocal(new Symbol.VarSymbol(16L, this.names._this, type, methodSymbol.owner)));
        }
        for (List list = jCMethodDecl.params; list.nonEmpty(); list = list.tail) {
            checkDimension(((JCTree.JCVariableDecl) list.head).pos(), ((JCTree.JCVariableDecl) list.head).sym.type);
            Code code3 = this.code;
            code3.setDefined(code3.newLocal(((JCTree.JCVariableDecl) list.head).sym));
        }
        int iCurCP = this.genCrt ? this.code.curCP() : 0;
        this.code.entryPoint();
        this.code.pendingStackMap = false;
        return iCurCP;
    }

    public static Gen instance(Context context) {
        Gen gen = (Gen) context.get(genKey);
        return gen == null ? new Gen(context) : gen;
    }

    private void internalVisitBlock(JCTree.JCBlock jCBlock) {
        int i = this.code.nextreg;
        genStats(jCBlock.stats, this.env.dup(jCBlock, new GenContext()));
        if (this.env.tree.hasTag(JCTree.Tag.METHODDEF)) {
            return;
        }
        this.code.statBegin(jCBlock.bracePos);
        this.code.endScopes(i);
        this.code.pendingStatPos = -1;
    }

    private boolean isAccessSuper(JCTree.JCMethodDecl jCMethodDecl) {
        return (jCMethodDecl.mods.flags & 4096) != 0 && isOddAccessName(jCMethodDecl.name);
    }

    private boolean isOddAccessName(Name name) {
        String string = name.toString();
        return string.startsWith(this.accessDollar) && (string.charAt(string.length() - 1) & 1) != 0;
    }

    private boolean nonVirtualForPrivateAccess(Symbol symbol) {
        return (!this.target.hasVirtualPrivateInvoke() || this.disableVirtualizedPrivateInvoke) && (symbol.flags() & 2) != 0;
    }

    public static int one(int i) {
        return zero(i) + 1;
    }

    public static void qsort2(int[] iArr, int[] iArr2, int i, int i2) {
        int i3;
        int i4 = iArr[(i + i2) / 2];
        int i5 = i;
        int i6 = i2;
        while (true) {
            if (iArr[i5] < i4) {
                i5++;
            } else {
                while (true) {
                    i3 = iArr[i6];
                    if (i4 >= i3) {
                        break;
                    } else {
                        i6--;
                    }
                }
                if (i5 <= i6) {
                    int i7 = iArr[i5];
                    iArr[i5] = i3;
                    iArr[i6] = i7;
                    int i8 = iArr2[i5];
                    iArr2[i5] = iArr2[i6];
                    iArr2[i6] = i8;
                    i5++;
                    i6--;
                }
                if (i5 > i6) {
                    break;
                }
            }
        }
        if (i < i6) {
            qsort2(iArr, iArr2, i, i6);
        }
        if (i5 < i2) {
            qsort2(iArr, iArr2, i5, i2);
        }
    }

    private void reloadStackBeforeSwitchExpr() {
        Iterator<Items.LocalItem> it = this.stackBeforeSwitchExpression.iterator();
        while (it.hasNext()) {
            it.next().load();
        }
    }

    private void setTypeAnnotationPositions(int i) {
        Symbol.MethodSymbol methodSymbol = this.code.meth;
        boolean z = methodSymbol.getKind() == ElementKind.CONSTRUCTOR || this.code.meth.getKind() == ElementKind.STATIC_INIT;
        for (Attribute.TypeCompound typeCompound : methodSymbol.getRawTypeAttributes()) {
            if (typeCompound.hasUnknownPosition()) {
                typeCompound.tryFixPosition();
            }
            if (typeCompound.position.matchesPos(i)) {
                typeCompound.position.updatePosOffset(this.code.cp);
            }
        }
        if (z) {
            for (Attribute.TypeCompound typeCompound2 : methodSymbol.owner.getRawTypeAttributes()) {
                if (typeCompound2.hasUnknownPosition()) {
                    typeCompound2.tryFixPosition();
                }
                if (typeCompound2.position.matchesPos(i)) {
                    typeCompound2.position.updatePosOffset(this.code.cp);
                }
            }
            for (Symbol symbol : new FilteredMemberList(methodSymbol.enclClass().members())) {
                if (symbol.getKind().isField()) {
                    for (Attribute.TypeCompound typeCompound3 : symbol.getRawTypeAttributes()) {
                        if (typeCompound3.hasUnknownPosition()) {
                            typeCompound3.tryFixPosition();
                        }
                        if (typeCompound3.position.matchesPos(i)) {
                            typeCompound3.position.updatePosOffset(this.code.cp);
                        }
                    }
                }
            }
        }
    }

    private void undefineVariablesInChain(Code.Chain chain, int i) {
        while (chain != null) {
            chain.state.defined.excludeFrom(i);
            chain = chain.next;
        }
    }

    private Env<GenContext> unwindBreak(JCTree jCTree) {
        int i = this.code.pendingStatPos;
        Env<GenContext> envUnwind = unwind(jCTree, this.env);
        this.code.pendingStatPos = i;
        return envUnwind;
    }

    private void visitBlockWithPatterns(JCTree.JCBlock jCBlock) {
        PatternMatchingCatchConfiguration patternMatchingCatchConfiguration = this.patternMatchingCatchConfiguration;
        try {
            this.patternMatchingCatchConfiguration = new PatternMatchingCatchConfiguration(jCBlock.patternMatchingCatch.calls2Handle(), new ListBuffer(), jCBlock.patternMatchingCatch.handler(), this.code.state.dup());
            internalVisitBlock(jCBlock);
        } finally {
            generatePatternMatchingCatch(this.env);
            this.patternMatchingCatchConfiguration = patternMatchingCatchConfiguration;
        }
    }

    public static int zero(int i) {
        if (i != 0) {
            if (i == 1) {
                return 9;
            }
            if (i == 2) {
                return 11;
            }
            if (i == 3) {
                return 14;
            }
            if (i != 5 && i != 6 && i != 7) {
                x01.a("zero");
                return 0;
            }
        }
        return 3;
    }

    public Symbol binaryQualifier(Symbol symbol, Type type) {
        if (!type.hasTag(TypeTag.ARRAY)) {
            return (symbol.owner == type.tsym || (symbol.flags() & 4104) == 4104 || symbol.owner == this.syms.objectType.tsym) ? symbol : symbol.clone(type.tsym);
        }
        Symtab symtab = this.syms;
        if (symbol == symtab.lengthVar || symbol.owner != symtab.arrayClass) {
            return symbol;
        }
        Symbol symbol2 = this.qualifiedSymbolCache.get(type);
        if (symbol2 == null) {
            Symbol.ClassSymbol classSymbol = new Symbol.ClassSymbol(1L, type.tsym.name, type, this.syms.noSymbol);
            this.qualifiedSymbolCache.put(type, classSymbol);
            symbol2 = classSymbol;
        }
        return symbol.clone(symbol2);
    }

    public void callMethod(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Name name, List<Type> list, boolean z) {
        Symbol.MethodSymbol methodSymbolResolveInternalMethod = this.rs.resolveInternalMethod(diagnosticPosition, this.attrEnv, type, name, list, null);
        Items items = this.items;
        if (z) {
            items.makeStaticItem(methodSymbolResolveInternalMethod).invoke();
        } else {
            items.makeMemberItem(methodSymbolResolveInternalMethod, name == this.names.init).invoke();
        }
    }

    public List<Pair<List<Attribute.TypeCompound>, JCTree.JCExpression>> catchTypesWithAnnotations(JCTree.JCCatch jCCatch) {
        if (!TreeInfo.isMultiCatch(jCCatch)) {
            return List.of(new Pair(jCCatch.param.sym.getRawTypeAttributes(), jCCatch.param.vartype));
        }
        JCTree.JCVariableDecl jCVariableDecl = jCCatch.param;
        return catchTypesWithAnnotationsFromMulticatch((JCTree.JCTypeUnion) jCVariableDecl.vartype, jCVariableDecl.sym.getRawTypeAttributes());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Pair<List<Attribute.TypeCompound>, JCTree.JCExpression>> catchTypesWithAnnotationsFromMulticatch(JCTree.JCTypeUnion jCTypeUnion, List<Attribute.TypeCompound> list) {
        List<JCTree.JCExpression> list2 = jCTypeUnion.alternatives;
        List listOf = List.of(new Pair(list, list2.head));
        for (List list3 = list2.tail; list3 != null; list3 = list3.tail) {
            A a = list3.head;
            if (a == 0) {
                break;
            }
            JCTree.JCExpression jCExpression = (JCTree.JCExpression) a;
            listOf = jCExpression instanceof JCTree.JCAnnotatedType ? listOf.prepend(new Pair(this.annotate.fromAnnotations(((JCTree.JCAnnotatedType) jCExpression).annotations), jCExpression)) : listOf.prepend(new Pair(List.nil(), jCExpression));
        }
        return listOf.reverse();
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025  */
    /* JADX WARN: Code duplicated, block: B:17:0x0034  */
    /* JADX WARN: Code duplicated, block: B:19:0x0048  */
    /* JADX WARN: Code duplicated, block: B:24:0x005d  */
    public Items.Item completeBinop(JCTree jCTree, JCTree jCTree2, Symbol.OperatorSymbol operatorSymbol) {
        Type type;
        Type.MethodType methodType = (Type.MethodType) operatorSymbol.type;
        int i = operatorSymbol.opcode;
        if (i >= 159 && i <= 164) {
            Object objConstValue = jCTree2.type.constValue();
            if ((objConstValue instanceof Number) && ((Number) objConstValue).intValue() == 0) {
                i -= 6;
            } else if (i >= 165) {
                type = operatorSymbol.erasure(this.types).mo71getParameterTypes().tail.head;
                if (i >= 270) {
                    i -= 150;
                    type = this.syms.intType;
                }
                genExpr(jCTree2, type).load();
                if (i >= 512) {
                    this.code.emitop0(i >> 9);
                    i &= 255;
                }
            } else {
                type = operatorSymbol.erasure(this.types).mo71getParameterTypes().tail.head;
                if (i >= 270) {
                    i -= 150;
                    type = this.syms.intType;
                }
                genExpr(jCTree2, type).load();
                if (i >= 512) {
                    this.code.emitop0(i >> 9);
                    i &= 255;
                }
            }
        } else if (i >= 165 || i > 166 || !TreeInfo.isNull(jCTree2)) {
            type = operatorSymbol.erasure(this.types).mo71getParameterTypes().tail.head;
            if (i >= 270 && i <= 275) {
                i -= 150;
                type = this.syms.intType;
            }
            genExpr(jCTree2, type).load();
            if (i >= 512) {
                this.code.emitop0(i >> 9);
                i &= 255;
            }
        } else {
            i += 33;
        }
        if ((i >= 153 && i <= 166) || i == 198 || i == 199) {
            return this.items.makeCondItem(i);
        }
        this.code.emitop0(i);
        return this.items.makeStackItem(methodType.restype);
    }

    public void emitMinusOne(int i) {
        if (i == 1) {
            this.items.makeImmediateItem(this.syms.longType, -1L).load();
        } else {
            this.code.emitop0(2);
        }
    }

    public void endFinalizerGap(Env<GenContext> env) {
        GenContext genContext = env.info;
        if (genContext.gaps == null || genContext.gaps.length() % 2 != 1) {
            return;
        }
        env.info.gaps.append(Integer.valueOf(this.code.curCP()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void endFinalizerGaps(Env<GenContext> env, Env<GenContext> env2) {
        Env env3 = null;
        Env env4 = env;
        while (env3 != env2) {
            endFinalizerGap(env4);
            env3 = env4;
            env4 = env4.next;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void genArgs(List<JCTree.JCExpression> list, List<Type> list2) {
        List list3 = list;
        List list4 = list2;
        while (list3.nonEmpty()) {
            genExpr((JCTree) list3.head, (Type) list4.head).load();
            List list5 = list4.tail;
            list3 = list3.tail;
            list4 = list5;
        }
        Assert.check(list4.isEmpty());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void genCatch(JCTree.JCCatch jCCatch, Env<GenContext> env, int i, int i2, List<Integer> list) {
        if (i != i2) {
            List<Pair<List<Attribute.TypeCompound>, JCTree.JCExpression>> listCatchTypesWithAnnotations = catchTypesWithAnnotations(jCCatch);
            int iIntValue = i;
            List list2 = list;
            while (list2.nonEmpty()) {
                for (Pair<List<Attribute.TypeCompound>, JCTree.JCExpression> pair : listCatchTypesWithAnnotations) {
                    int iMakeRef = this.makeRef(jCCatch.pos(), pair.snd.type);
                    int iIntValue2 = ((Integer) list2.head).intValue();
                    Gen gen = this;
                    gen.registerCatch(jCCatch.pos(), iIntValue, iIntValue2, this.code.curCP(), iMakeRef);
                    Iterator<Attribute.TypeCompound> it = pair.fst.iterator();
                    while (it.hasNext()) {
                        it.next().position.setCatchInfo(iMakeRef, iIntValue);
                    }
                    this = gen;
                }
                Gen gen2 = this;
                List<A> list3 = list2.tail;
                iIntValue = ((Integer) list3.head).intValue();
                List list4 = list3.tail;
                this = gen2;
                list2 = list4;
            }
            Gen gen3 = this;
            if (iIntValue < i2) {
                for (Pair<List<Attribute.TypeCompound>, JCTree.JCExpression> pair2 : listCatchTypesWithAnnotations) {
                    int iMakeRef2 = gen3.makeRef(jCCatch.pos(), pair2.snd.type);
                    int i3 = i2;
                    gen3.registerCatch(jCCatch.pos(), iIntValue, i3, gen3.code.curCP(), iMakeRef2);
                    Iterator<Attribute.TypeCompound> it2 = pair2.fst.iterator();
                    while (it2.hasNext()) {
                        it2.next().position.setCatchInfo(iMakeRef2, iIntValue);
                    }
                    i2 = i3;
                }
            }
            gen3.genCatchBlock(jCCatch, env);
        }
    }

    public void genCatchBlock(JCTree.JCCatch jCCatch, Env<GenContext> env) {
        Symbol.VarSymbol varSymbol = jCCatch.param.sym;
        this.code.statBegin(jCCatch.pos);
        this.code.markStatBegin();
        Code code = this.code;
        int i = code.nextreg;
        code.newLocal(varSymbol);
        this.items.makeLocalItem(varSymbol).store();
        this.code.statBegin(TreeInfo.firstStatPos(jCCatch.body));
        genStat(jCCatch.body, env, 2);
        this.code.endScopes(i);
        this.code.statBegin(TreeInfo.endPos(jCCatch.body));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean genClass(Env<AttrContext> env, JCTree.JCClassDecl jCClassDecl) {
        try {
            this.attrEnv = env;
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            JCTree.JCCompilationUnit jCCompilationUnit = env.toplevel;
            this.toplevel = jCCompilationUnit;
            this.endPosTable = jCCompilationUnit.endPositions;
            jCClassDecl.defs = normalizeDefs(jCClassDecl.defs, classSymbol);
            generateReferencesToPrunedTree(classSymbol);
            Env<GenContext> env2 = new Env<>(jCClassDecl, new GenContext());
            env2.toplevel = env.toplevel;
            env2.enclClass = jCClassDecl;
            for (List list = jCClassDecl.defs; list.nonEmpty(); list = list.tail) {
                genDef((JCTree) list.head, env2);
            }
            if (this.poolWriter.size() > 65535) {
                this.log.error(jCClassDecl.pos(), CompilerProperties.Errors.LimitPool);
                this.nerrs++;
            }
            if (this.nerrs != 0) {
                for (List list2 = jCClassDecl.defs; list2.nonEmpty(); list2 = list2.tail) {
                    if (((JCTree) list2.head).hasTag(JCTree.Tag.METHODDEF)) {
                        ((JCTree.JCMethodDecl) list2.head).sym.code = null;
                    }
                }
            }
            jCClassDecl.defs = List.nil();
            return this.nerrs == 0;
        } finally {
            this.attrEnv = null;
            this.env = null;
            this.toplevel = null;
            this.endPosTable = null;
            this.nerrs = 0;
            this.qualifiedSymbolCache.clear();
        }
    }

    public Items.CondItem genCond(JCTree jCTree, boolean z) {
        JCTree jCTreeSkipParens = TreeInfo.skipParens(jCTree);
        if (jCTreeSkipParens.hasTag(JCTree.Tag.CONDEXPR)) {
            JCTree.JCConditional jCConditional = (JCTree.JCConditional) jCTreeSkipParens;
            Items.CondItem condItemGenCond = genCond(jCConditional.cond, 8);
            if (condItemGenCond.isTrue()) {
                this.code.resolve(condItemGenCond.trueJumps);
                Items.CondItem condItemGenCond2 = genCond(jCConditional.truepart, 16);
                if (z) {
                    condItemGenCond2.tree = jCConditional.truepart;
                }
                return condItemGenCond2;
            }
            if (condItemGenCond.isFalse()) {
                this.code.resolve(condItemGenCond.falseJumps);
                Items.CondItem condItemGenCond3 = genCond(jCConditional.falsepart, 16);
                if (z) {
                    condItemGenCond3.tree = jCConditional.falsepart;
                }
                return condItemGenCond3;
            }
            Code.Chain chainJumpFalse = condItemGenCond.jumpFalse();
            this.code.resolve(condItemGenCond.trueJumps);
            Items.CondItem condItemGenCond4 = genCond(jCConditional.truepart, 16);
            if (z) {
                condItemGenCond4.tree = jCConditional.truepart;
            }
            Code.Chain chainJumpFalse2 = condItemGenCond4.jumpFalse();
            this.code.resolve(condItemGenCond4.trueJumps);
            Code.Chain chainBranch = this.code.branch(167);
            this.code.resolve(chainJumpFalse);
            Items.CondItem condItemGenCond5 = genCond(jCConditional.falsepart, 16);
            Items.CondItem condItemMakeCondItem = this.items.makeCondItem(condItemGenCond5.opcode, Code.mergeChains(chainBranch, condItemGenCond5.trueJumps), Code.mergeChains(chainJumpFalse2, condItemGenCond5.falseJumps));
            if (z) {
                condItemMakeCondItem.tree = jCConditional.falsepart;
            }
            return condItemMakeCondItem;
        }
        if (!jCTreeSkipParens.hasTag(JCTree.Tag.SWITCH_EXPRESSION)) {
            if (jCTreeSkipParens.hasTag(JCTree.Tag.LETEXPR)) {
                JCTree.LetExpr letExpr = (JCTree.LetExpr) jCTreeSkipParens;
                if (letExpr.needsCond) {
                    this.code.resolvePending();
                    Code code = this.code;
                    int i = code.nextreg;
                    int letExprStackPos = code.setLetExprStackPos(code.state.stacksize);
                    try {
                        genStats(letExpr.defs, this.env);
                        this.code.setLetExprStackPos(letExprStackPos);
                        Items.CondItem condItemGenCond6 = genCond(letExpr.expr, z);
                        this.code.endScopes(i);
                        undefineVariablesInChain(condItemGenCond6.falseJumps, i);
                        undefineVariablesInChain(condItemGenCond6.trueJumps, i);
                        return condItemGenCond6;
                    } catch (Throwable th) {
                        this.code.setLetExprStackPos(letExprStackPos);
                        throw th;
                    }
                }
            }
            Items.CondItem condItemMkCond = genExpr(jCTree, this.syms.booleanType).mkCond();
            if (z) {
                condItemMkCond.tree = jCTree;
            }
            return condItemMkCond;
        }
        this.code.resolvePending();
        boolean z2 = this.inCondSwitchExpression;
        Code.Chain chain = this.switchExpressionTrueChain;
        Code.Chain chain2 = this.switchExpressionFalseChain;
        try {
            this.inCondSwitchExpression = true;
            this.switchExpressionTrueChain = null;
            this.switchExpressionFalseChain = null;
            try {
                doHandleSwitchExpression((JCTree.JCSwitchExpression) jCTreeSkipParens);
            } catch (Symbol.CompletionFailure e) {
                this.chk.completionError(jCTree.pos(), e);
                this.code.state.stacksize = 1;
            }
            Items.CondItem condItemMakeCondItem2 = this.items.makeCondItem(167, this.switchExpressionTrueChain, this.switchExpressionFalseChain);
            if (z) {
                condItemMakeCondItem2.tree = jCTree;
            }
            this.inCondSwitchExpression = z2;
            this.switchExpressionTrueChain = chain;
            this.switchExpressionFalseChain = chain2;
            return condItemMakeCondItem2;
        } catch (Throwable th2) {
            this.inCondSwitchExpression = z2;
            this.switchExpressionTrueChain = chain;
            this.switchExpressionFalseChain = chain2;
            throw th2;
        }
    }

    public void genDef(JCTree jCTree, Env<GenContext> env) {
        Env<GenContext> env2 = this.env;
        try {
            this.env = env;
            jCTree.accept(this);
        } catch (Symbol.CompletionFailure e) {
            this.chk.completionError(jCTree.pos(), e);
        } finally {
            this.env = env2;
        }
    }

    public Items.Item genExpr(JCTree jCTree, Type type) {
        if (!this.code.isAlive()) {
            return this.items.makeStackItem(type);
        }
        Type type2 = this.pt;
        try {
            if (jCTree.type.constValue() != null) {
                jCTree.accept(this.classReferenceVisitor);
                checkStringConstant(jCTree.pos(), jCTree.type.constValue());
                Symbol symbol = TreeInfo.symbol(jCTree);
                if (symbol == null || !isConstantDynamic(symbol)) {
                    Items items = this.items;
                    Type type3 = jCTree.type;
                    this.result = items.makeImmediateItem(type3, type3.constValue());
                } else {
                    this.result = this.items.makeDynamicItem(symbol);
                }
            } else {
                this.pt = type;
                jCTree.accept(this);
            }
            return this.result.coerce(type);
        } catch (Symbol.CompletionFailure e) {
            this.chk.completionError(jCTree.pos(), e);
            this.code.state.stacksize = 1;
            return this.items.makeStackItem(type);
        } finally {
            this.pt = type2;
        }
    }

    public void genFinalizer(Env<GenContext> env) {
        if (this.code.isAlive()) {
            GenContext genContext = env.info;
            if (genContext.finalize != null) {
                genContext.finalize.gen();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0021  */
    public void genMethod(JCTree.JCMethodDecl jCMethodDecl, Env<GenContext> env, boolean z) {
        int i;
        Symbol.MethodSymbol methodSymbol = jCMethodDecl.sym;
        if (methodSymbol.isConstructor()) {
            if (!methodSymbol.enclClass().isInner() || methodSymbol.enclClass().isStatic()) {
                i = 1;
            } else {
                i = 2;
            }
        } else if ((jCMethodDecl.mods.flags & 8) == 0) {
            i = 1;
        } else {
            i = 0;
        }
        if (Code.width(this.types.erasure(env.enclMethod.sym.type).mo71getParameterTypes()) + i > 255) {
            this.log.error(jCMethodDecl.pos(), CompilerProperties.Errors.LimitParameters);
            this.nerrs++;
            return;
        }
        if (jCMethodDecl.body != null) {
            int iInitCode = initCode(jCMethodDecl, env, z);
            try {
                genStat(jCMethodDecl.body, env);
            } catch (CodeSizeOverflow unused) {
                iInitCode = initCode(jCMethodDecl, env, z);
                genStat(jCMethodDecl.body, env);
            }
            Code code = this.code;
            if (code.state.stacksize != 0) {
                this.log.error(jCMethodDecl.body.pos(), CompilerProperties.Errors.StackSimError(jCMethodDecl.sym));
                x1f.a();
                return;
            }
            if (code.isAlive()) {
                this.code.statBegin(TreeInfo.endPos(jCMethodDecl.body));
                JCTree.JCMethodDecl jCMethodDecl2 = env.enclMethod;
                if (jCMethodDecl2 == null || jCMethodDecl2.sym.type.mo73getReturnType().hasTag(TypeTag.VOID)) {
                    this.code.emitop0(177);
                } else {
                    this.code.resolve(this.items.makeCondItem(167).jumpTrue(), this.code.entryPoint());
                }
            }
            if (this.genCrt) {
                Code code2 = this.code;
                code2.crt.put(jCMethodDecl.body, 2, iInitCode, code2.curCP());
            }
            this.code.endScopes(0);
            if (this.code.checkLimits(jCMethodDecl.pos(), this.log)) {
                this.nerrs++;
                return;
            }
            if (!z && this.code.fatcode) {
                genMethod(jCMethodDecl, env, true);
            }
            if (this.stackMap == Code.StackMapFormat.JSR202) {
                Code code3 = this.code;
                code3.lastFrame = null;
                code3.frameBeforeLast = null;
            }
            this.code.compressCatchTable();
            this.code.fillExceptionParameterPositions();
        }
    }

    public void genPatternMatchingCatch(JCTree.JCCatch jCCatch, Env<GenContext> env, List<int[]> list) {
        for (int[] iArr : list) {
            registerCatch(jCCatch.pos(), iArr[0], iArr[1], this.code.curCP(), makeRef(jCCatch.pos(), jCCatch.param.vartype.type));
        }
        genCatchBlock(jCCatch, env);
    }

    public void genStat(JCTree jCTree, Env<GenContext> env) {
        if (this.code.isAlive()) {
            this.code.statBegin(jCTree.pos);
            genDef(jCTree, env);
        } else if (env.info.isSwitch && jCTree.hasTag(JCTree.Tag.VARDEF)) {
            this.code.newLocal(((JCTree.JCVariableDecl) jCTree).sym);
        }
    }

    public void genStats(List<JCTree.JCStatement> list, Env<GenContext> env, int i) {
        if (!this.genCrt) {
            genStats(list, env);
            return;
        }
        if (list.length() == 1) {
            genStat(list.head, env, i | 1);
            return;
        }
        int iCurCP = this.code.curCP();
        genStats(list, env);
        Code code = this.code;
        code.crt.put(list, i, iCurCP, code.curCP());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void genTry(JCTree jCTree, List<JCTree.JCCatch> list, Env<GenContext> env) {
        Code.Chain chainMergeChains;
        Env<GenContext> env2 = env;
        Code code = this.code;
        int i = code.nextreg;
        int iCurCP = code.curCP();
        Code.State stateDup = this.code.state.dup();
        genStat(jCTree, env2, 2);
        int iCurCP2 = this.code.curCP();
        List<Integer> list2 = env2.info.gaps.toList();
        this.code.statBegin(TreeInfo.endPos(jCTree));
        genFinalizer(env2);
        this.code.statBegin(TreeInfo.endPos(env2.tree));
        boolean zHasTag = env2.tree.hasTag(JCTree.Tag.TRY);
        Code.Chain chainBranch = (iCurCP == iCurCP2 && zHasTag) ? this.code.branch(168) : this.code.branch(167);
        endFinalizerGap(env2);
        env2.info.finalize.afterBody();
        GenContext genContext = env2.info;
        boolean z = genContext.finalize != null && genContext.finalize.hasFinalizer();
        if (iCurCP != iCurCP2) {
            List list3 = list;
            chainMergeChains = chainBranch;
            while (list3.nonEmpty()) {
                this.code.entryPoint(stateDup, ((JCTree.JCCatch) list3.head).param.sym.type);
                genCatch((JCTree.JCCatch) list3.head, env2, iCurCP, iCurCP2, list2);
                Env<GenContext> env3 = env2;
                genFinalizer(env3);
                if (z || list3.tail.nonEmpty()) {
                    this.code.statBegin(TreeInfo.endPos(env3.tree));
                    chainMergeChains = Code.mergeChains(chainMergeChains, this.code.branch(167));
                }
                endFinalizerGap(env3);
                list3 = list3.tail;
                env2 = env3;
            }
        } else {
            chainMergeChains = chainBranch;
        }
        Env<GenContext> env4 = env2;
        if (z && (iCurCP != iCurCP2 || !zHasTag)) {
            this.code.newRegSegment();
            int iEntryPoint = this.code.entryPoint(stateDup, this.syms.throwableType);
            int iIntValue = iCurCP;
            while (env4.info.gaps.nonEmpty()) {
                registerCatch(jCTree.pos(), iIntValue, env4.info.gaps.next().intValue(), iEntryPoint, 0);
                iIntValue = env4.info.gaps.next().intValue();
            }
            Code code2 = this.code;
            JCTree jCTree2 = env4.tree;
            TreeInfo.PosKind posKind = TreeInfo.PosKind.FIRST_STAT_POS;
            code2.statBegin(TreeInfo.finalizerPos(jCTree2, posKind));
            this.code.markStatBegin();
            Items.LocalItem localItemMakeTemp = makeTemp(this.syms.throwableType);
            localItemMakeTemp.store();
            genFinalizer(env4);
            this.code.resolvePending();
            this.code.statBegin(TreeInfo.finalizerPos(env4.tree, TreeInfo.PosKind.END_POS));
            this.code.markStatBegin();
            localItemMakeTemp.load();
            registerCatch(jCTree.pos(), iIntValue, env4.info.gaps.next().intValue(), iEntryPoint, 0);
            this.code.emitop0(191);
            this.code.markDead();
            GenContext genContext2 = env4.info;
            if (genContext2.cont != null) {
                this.code.resolve(genContext2.cont);
                this.code.statBegin(TreeInfo.finalizerPos(env4.tree, posKind));
                this.code.markStatBegin();
                Items.LocalItem localItemMakeTemp2 = makeTemp(this.syms.throwableType);
                localItemMakeTemp2.store();
                env4.info.finalize.genLast();
                this.code.emitop1w(169, localItemMakeTemp2.reg);
                this.code.markDead();
            }
        }
        this.code.resolve(chainMergeChains);
        this.code.endScopes(i);
    }

    public Env<AttrContext> getAttrEnv() {
        return this.attrEnv;
    }

    public Code getCode() {
        return this.code;
    }

    public Items getItems() {
        return this.items;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean hasFinally(JCTree jCTree, Env<GenContext> env) {
        Env env2 = env;
        while (true) {
            JCTree jCTree2 = env2.tree;
            if (jCTree2 == jCTree) {
                return false;
            }
            if (jCTree2.hasTag(JCTree.Tag.TRY) && ((GenContext) env2.info).finalize.hasFinalizer()) {
                return true;
            }
            env2 = env2.next;
        }
    }

    public boolean isConstantDynamic(Symbol symbol) {
        return symbol.kind == Kinds.Kind.VAR && (symbol instanceof Symbol.DynamicVarSymbol) && ((Symbol.DynamicVarSymbol) symbol).isDynamic();
    }

    public boolean isInvokeDynamic(Symbol symbol) {
        return symbol.kind == Kinds.Kind.MTH && ((Symbol.MethodSymbol) symbol).isDynamic();
    }

    public void loadIntConst(int i) {
        this.items.makeImmediateItem(this.syms.intType, Integer.valueOf(i)).load();
    }

    public Items.Item makeNewArray(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, int i) {
        Type typeElemtype = this.types.elemtype(type);
        if (this.types.dimensions(type) > 255) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.LimitDimensions);
            this.nerrs++;
        }
        int iArraycode = Code.arraycode(typeElemtype);
        if (iArraycode == 0 || (iArraycode == 1 && i == 1)) {
            this.code.emitAnewarray(makeRef(diagnosticPosition, typeElemtype), type);
        } else {
            Code code = this.code;
            if (iArraycode == 1) {
                code.emitMultianewarray(i, makeRef(diagnosticPosition, type), type);
            } else {
                code.emitNewarray(iArraycode, type);
            }
        }
        return this.items.makeStackItem(type);
    }

    public int makeRef(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        return this.poolWriter.putClass(checkDimension(diagnosticPosition, type));
    }

    public Items.LocalItem makeTemp(Type type) {
        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4096L, this.names.empty, type, this.env.enclMethod.sym);
        this.code.newLocal(varSymbol);
        return this.items.makeLocalItem(varSymbol);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<JCTree> normalizeDefs(List<JCTree> list, Symbol.ClassSymbol classSymbol) {
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        ListBuffer listBuffer3 = new ListBuffer();
        ListBuffer listBuffer4 = new ListBuffer();
        ListBuffer listBuffer5 = new ListBuffer();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            JCTree jCTree = (JCTree) list2.head;
            int i = AnonymousClass3.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
            if (i == 1) {
                JCTree.JCBlock jCBlock = (JCTree.JCBlock) jCTree;
                long j = jCBlock.flags;
                if ((8 & j) != 0) {
                    listBuffer3.append(jCBlock);
                } else if ((4096 & j) == 0) {
                    listBuffer.append(jCBlock);
                }
            } else if (i == 2) {
                listBuffer5.append(jCTree);
            } else if (i != 3) {
                Assert.error();
            } else {
                JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) jCTree;
                Symbol.VarSymbol varSymbol = jCVariableDecl.sym;
                checkDimension(jCVariableDecl.pos(), varSymbol.type);
                if (jCVariableDecl.init != null) {
                    if ((8 & varSymbol.flags()) == 0) {
                        JCTree.JCStatement jCStatementAssignment = this.make.at(jCVariableDecl.pos()).Assignment(varSymbol, jCVariableDecl.init);
                        listBuffer.append(jCStatementAssignment);
                        this.endPosTable.replaceTree(jCVariableDecl, jCStatementAssignment);
                        listBuffer2.addAll(getAndRemoveNonFieldTAs(varSymbol));
                    } else if (varSymbol.getConstValue() == null) {
                        JCTree.JCStatement jCStatementAssignment2 = this.make.at(jCVariableDecl.pos).Assignment(varSymbol, jCVariableDecl.init);
                        listBuffer3.append(jCStatementAssignment2);
                        this.endPosTable.replaceTree(jCVariableDecl, jCStatementAssignment2);
                        listBuffer4.addAll(getAndRemoveNonFieldTAs(varSymbol));
                    } else {
                        checkStringConstant(jCVariableDecl.init.pos(), varSymbol.getConstValue());
                        jCVariableDecl.init.accept(this.classReferenceVisitor);
                    }
                }
            }
        }
        if (listBuffer.length() != 0) {
            List<JCTree.JCStatement> list3 = listBuffer.toList();
            listBuffer2.addAll(classSymbol.getInitTypeAttributes());
            List<Attribute.TypeCompound> list4 = listBuffer2.toList();
            Iterator it = listBuffer5.iterator();
            while (it.hasNext()) {
                normalizeMethod((JCTree.JCMethodDecl) ((JCTree) it.next()), list3, list4);
            }
        }
        if (listBuffer3.length() != 0) {
            Symbol.MethodSymbol methodSymbol = new Symbol.MethodSymbol((classSymbol.flags() & 2048) | 8, this.names.clinit, new Type.MethodType(List.nil(), this.syms.voidType, List.nil(), this.syms.methodClass), classSymbol);
            classSymbol.members().enter(methodSymbol);
            List<JCTree.JCStatement> list5 = listBuffer3.toList();
            JCTree.JCBlock jCBlockBlock = this.make.at(list5.head.pos()).Block(0L, list5);
            jCBlockBlock.bracePos = TreeInfo.endPos(list5.last());
            listBuffer5.append(this.make.MethodDef(methodSymbol, jCBlockBlock));
            if (!listBuffer4.isEmpty()) {
                methodSymbol.appendUniqueTypeAttributes(listBuffer4.toList());
            }
            if (!classSymbol.getClassInitTypeAttributes().isEmpty()) {
                methodSymbol.appendUniqueTypeAttributes(classSymbol.getClassInitTypeAttributes());
            }
        }
        return listBuffer5.toList();
    }

    public void normalizeMethod(JCTree.JCMethodDecl jCMethodDecl, final List<JCTree.JCStatement> list, List<Attribute.TypeCompound> list2) {
        if (TreeInfo.isConstructor(jCMethodDecl) && TreeInfo.hasConstructorCall(jCMethodDecl, this.names._super)) {
            TreeInfo.mapSuperCalls(jCMethodDecl.body, new Function() { // from class: ay5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.make.Block(0L, list.prepend((JCTree.JCExpressionStatement) obj));
                }
            });
            JCTree.JCBlock jCBlock = jCMethodDecl.body;
            if (jCBlock.bracePos == -1) {
                jCBlock.bracePos = TreeInfo.endPos(jCBlock.stats.last());
            }
            jCMethodDecl.sym.appendUniqueTypeAttributes(list2);
        }
    }

    public void registerCatch(JCDiagnostic.DiagnosticPosition diagnosticPosition, int i, int i2, int i3, int i4) {
        char c = (char) i;
        char c2 = (char) i2;
        char c3 = (char) i3;
        if (c == i && c2 == i2 && c3 == i3) {
            this.code.addCatch(c, c2, c3, (char) i4);
        } else {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.LimitCodeTooLargeForTryStmt);
            this.nerrs++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.sun.tools.javac.jvm.Gen] */
    public Env<GenContext> unwind(JCTree jCTree, Env<GenContext> env) {
        Env env2 = env;
        while (true) {
            genFinalizer(env2);
            if (env2.tree == jCTree) {
                return env2;
            }
            env2 = env2.next;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
        setTypeAnnotationPositions(jCMethodInvocation.pos);
        Items.Item itemGenExpr = genExpr(jCMethodInvocation.meth, this.methodType);
        Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) TreeInfo.symbol(jCMethodInvocation.meth);
        genArgs(jCMethodInvocation.args, methodSymbol.externalType(this.types).mo71getParameterTypes());
        if (!methodSymbol.isDynamic()) {
            this.code.statBegin(jCMethodInvocation.pos);
        }
        if (this.patternMatchingCatchConfiguration.invocations().contains(jCMethodInvocation)) {
            int iCurCP = this.code.curCP();
            this.result = itemGenExpr.invoke();
            this.patternMatchingCatchConfiguration.ranges().add(new int[]{iCurCP, this.code.curCP()});
        } else {
            if (!methodSymbol.isConstructor() || !TreeInfo.isConstructorCall(jCMethodInvocation)) {
                this.result = itemGenExpr.invoke();
                return;
            }
            generatePatternMatchingCatch(this.env);
            this.result = itemGenExpr.invoke();
            this.patternMatchingCatchConfiguration = this.patternMatchingCatchConfiguration.restart(this.code.state.dup());
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssign(JCTree.JCAssign jCAssign) {
        JCTree.JCExpression jCExpression = jCAssign.lhs;
        Items.Item itemGenExpr = genExpr(jCExpression, jCExpression.type);
        genExpr(jCAssign.rhs, jCAssign.lhs.type).load();
        if (jCAssign.rhs.type.hasTag(TypeTag.BOT)) {
            this.code.state.forceStackTop(jCAssign.lhs.type);
        }
        this.result = this.items.makeAssignItem(itemGenExpr);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
        Items.Item itemMakeConcat;
        Symbol.OperatorSymbol operatorSymbol = jCAssignOp.operator;
        if (operatorSymbol.opcode == 256) {
            itemMakeConcat = this.concat.makeConcat(jCAssignOp);
        } else {
            JCTree.JCExpression jCExpression = jCAssignOp.lhs;
            Items.Item itemGenExpr = genExpr(jCExpression, jCExpression.type);
            if ((jCAssignOp.hasTag(JCTree.Tag.PLUS_ASG) || jCAssignOp.hasTag(JCTree.Tag.MINUS_ASG)) && (itemGenExpr instanceof Items.LocalItem)) {
                Items.LocalItem localItem = (Items.LocalItem) itemGenExpr;
                TypeTag tag = jCAssignOp.lhs.type.getTag();
                TypeTag typeTag = TypeTag.INT;
                if (tag.isSubRangeOf(typeTag) && jCAssignOp.rhs.type.getTag().isSubRangeOf(typeTag) && jCAssignOp.rhs.type.constValue() != null) {
                    int iIntValue = ((Number) jCAssignOp.rhs.type.constValue()).intValue();
                    if (jCAssignOp.hasTag(JCTree.Tag.MINUS_ASG)) {
                        iIntValue = -iIntValue;
                    }
                    localItem.incr(iIntValue);
                    this.result = itemGenExpr;
                    return;
                }
            }
            itemGenExpr.duplicate();
            itemGenExpr.coerce(operatorSymbol.type.mo71getParameterTypes().head).load();
            completeBinop(jCAssignOp.lhs, jCAssignOp.rhs, operatorSymbol).coerce(jCAssignOp.lhs.type);
            itemMakeConcat = itemGenExpr;
        }
        this.result = this.items.makeAssignItem(itemMakeConcat);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBinary(JCTree.JCBinary jCBinary) {
        Symbol.OperatorSymbol operatorSymbol = jCBinary.operator;
        if (operatorSymbol.opcode == 256) {
            this.result = this.concat.makeConcat(jCBinary);
            return;
        }
        if (jCBinary.hasTag(JCTree.Tag.AND)) {
            Items.CondItem condItemGenCond = genCond(jCBinary.lhs, 8);
            if (condItemGenCond.isFalse()) {
                this.result = condItemGenCond;
                return;
            }
            Code.Chain chainJumpFalse = condItemGenCond.jumpFalse();
            this.code.resolve(condItemGenCond.trueJumps);
            Items.CondItem condItemGenCond2 = genCond(jCBinary.rhs, 16);
            this.result = this.items.makeCondItem(condItemGenCond2.opcode, condItemGenCond2.trueJumps, Code.mergeChains(chainJumpFalse, condItemGenCond2.falseJumps));
            return;
        }
        boolean zHasTag = jCBinary.hasTag(JCTree.Tag.OR);
        JCTree.JCExpression jCExpression = jCBinary.lhs;
        if (!zHasTag) {
            genExpr(jCExpression, operatorSymbol.type.mo71getParameterTypes().head).load();
            this.result = completeBinop(jCBinary.lhs, jCBinary.rhs, operatorSymbol);
            return;
        }
        Items.CondItem condItemGenCond3 = genCond(jCExpression, 8);
        if (condItemGenCond3.isTrue()) {
            this.result = condItemGenCond3;
            return;
        }
        Code.Chain chainJumpTrue = condItemGenCond3.jumpTrue();
        this.code.resolve(condItemGenCond3.falseJumps);
        Items.CondItem condItemGenCond4 = genCond(jCBinary.rhs, 16);
        this.result = this.items.makeCondItem(condItemGenCond4.opcode, Code.mergeChains(chainJumpTrue, condItemGenCond4.trueJumps), condItemGenCond4.falseJumps);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBlock(JCTree.JCBlock jCBlock) {
        if (jCBlock.patternMatchingCatch != null) {
            visitBlockWithPatterns(jCBlock);
        } else {
            internalVisitBlock(jCBlock);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBreak(JCTree.JCBreak jCBreak) {
        Assert.check(this.code.isStatementStart());
        Env<GenContext> envUnwindBreak = unwindBreak(jCBreak.target);
        envUnwindBreak.info.addExit(this.code.branch(167));
        endFinalizerGaps(this.env, envUnwindBreak);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConditional(JCTree.JCConditional jCConditional) {
        Code.Chain chainBranch;
        this.code.statBegin(jCConditional.cond.pos);
        Items.CondItem condItemGenCond = genCond(jCConditional.cond, 8);
        Code.Chain chainJumpFalse = condItemGenCond.jumpFalse();
        if (condItemGenCond.isFalse()) {
            chainBranch = null;
        } else {
            this.code.resolve(condItemGenCond.trueJumps);
            int iCurCP = this.genCrt ? this.code.curCP() : 0;
            this.code.statBegin(jCConditional.truepart.pos);
            genExpr(jCConditional.truepart, this.pt).load();
            if (this.genCrt) {
                Code code = this.code;
                code.crt.put(jCConditional.truepart, 16, iCurCP, code.curCP());
            }
            chainBranch = this.code.branch(167);
        }
        if (chainJumpFalse != null) {
            this.code.resolve(chainJumpFalse);
            int iCurCP2 = this.genCrt ? this.code.curCP() : 0;
            this.code.statBegin(jCConditional.falsepart.pos);
            genExpr(jCConditional.falsepart, this.pt).load();
            if (this.genCrt) {
                Code code2 = this.code;
                code2.crt.put(jCConditional.falsepart, 16, iCurCP2, code2.curCP());
            }
        }
        this.code.resolve(chainBranch);
        this.result = this.items.makeStackItem(this.pt);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitContinue(JCTree.JCContinue jCContinue) {
        int i = this.code.pendingStatPos;
        Env<GenContext> envUnwind = unwind(jCContinue.target, this.env);
        Code code = this.code;
        code.pendingStatPos = i;
        Assert.check(code.isStatementStart());
        envUnwind.info.addCont(this.code.branch(167));
        endFinalizerGaps(this.env, envUnwind);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
        genLoop(jCDoWhileLoop, jCDoWhileLoop.body, jCDoWhileLoop.cond, List.nil(), false);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitExec(JCTree.JCExpressionStatement jCExpressionStatement) {
        JCTree.JCExpression jCExpression = jCExpressionStatement.expr;
        int i = AnonymousClass3.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpression.getTag().ordinal()];
        if (i == 4) {
            ((JCTree.JCUnary) jCExpression).setTag(JCTree.Tag.PREINC);
        } else if (i == 5) {
            ((JCTree.JCUnary) jCExpression).setTag(JCTree.Tag.PREDEC);
        }
        Assert.check(this.code.isStatementStart());
        JCTree.JCExpression jCExpression2 = jCExpressionStatement.expr;
        genExpr(jCExpression2, jCExpression2.type).drop();
        Assert.check(this.code.isStatementStart());
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForLoop(JCTree.JCForLoop jCForLoop) {
        int i = this.code.nextreg;
        genStats(jCForLoop.init, this.env);
        genLoop(jCForLoop, jCForLoop.body, jCForLoop.cond, jCForLoop.step, true);
        this.code.endScopes(i);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        throw new AssertionError();
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        Kinds.Kind kind;
        Symbol symbolBinaryQualifier = jCIdent.sym;
        Name name = jCIdent.name;
        Names names = this.names;
        Name name2 = names._this;
        if (name == name2 || name == names._super) {
            Items items = this.items;
            Items.Item itemMakeThisItem = name == name2 ? items.makeThisItem() : items.makeSuperItem();
            if (symbolBinaryQualifier.kind == Kinds.Kind.MTH) {
                itemMakeThisItem.load();
                itemMakeThisItem = this.items.makeMemberItem(symbolBinaryQualifier, true);
            }
            this.result = itemMakeThisItem;
            return;
        }
        if (isInvokeDynamic(symbolBinaryQualifier) || isConstantDynamic(symbolBinaryQualifier)) {
            if (isConstantDynamic(symbolBinaryQualifier)) {
                setTypeAnnotationPositions(jCIdent.pos);
            }
            this.result = this.items.makeDynamicItem(symbolBinaryQualifier);
            return;
        }
        Kinds.Kind kind2 = symbolBinaryQualifier.kind;
        Kinds.Kind kind3 = Kinds.Kind.VAR;
        if (kind2 == kind3 && ((kind = symbolBinaryQualifier.owner.kind) == Kinds.Kind.MTH || kind == kind3)) {
            this.result = this.items.makeLocalItem((Symbol.VarSymbol) symbolBinaryQualifier);
            return;
        }
        if ((symbolBinaryQualifier.flags() & 8) != 0) {
            if (!isAccessSuper(this.env.enclMethod)) {
                symbolBinaryQualifier = binaryQualifier(symbolBinaryQualifier, this.env.enclClass.type);
            }
            this.result = this.items.makeStaticItem(symbolBinaryQualifier);
        } else {
            this.items.makeThisItem().load();
            Symbol symbolBinaryQualifier2 = binaryQualifier(symbolBinaryQualifier, this.env.enclClass.type);
            this.result = this.items.makeMemberItem(symbolBinaryQualifier2, nonVirtualForPrivateAccess(symbolBinaryQualifier2));
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIf(JCTree.JCIf jCIf) {
        Code.Chain chainBranch;
        Code code = this.code;
        int i = code.nextreg;
        Assert.check(code.isStatementStart());
        Items.CondItem condItemGenCond = genCond(TreeInfo.skipParens(jCIf.cond), 8);
        Code.Chain chainJumpFalse = condItemGenCond.jumpFalse();
        Assert.check(this.code.isStatementStart());
        if (condItemGenCond.isFalse()) {
            chainBranch = null;
        } else {
            this.code.resolve(condItemGenCond.trueJumps);
            genStat(jCIf.thenpart, this.env, 17);
            chainBranch = this.code.branch(167);
        }
        if (chainJumpFalse != null) {
            this.code.resolve(chainJumpFalse);
            JCTree.JCStatement jCStatement = jCIf.elsepart;
            if (jCStatement != null) {
                genStat(jCStatement, this.env, 17);
            }
        }
        this.code.resolve(chainBranch);
        this.code.endScopes(i);
        Assert.check(this.code.isStatementStart());
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIndexed(JCTree.JCArrayAccess jCArrayAccess) {
        JCTree.JCExpression jCExpression = jCArrayAccess.indexed;
        genExpr(jCExpression, jCExpression.type).load();
        genExpr(jCArrayAccess.index, this.syms.intType).load();
        this.result = this.items.makeIndexedItem(jCArrayAccess.type);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLabelled(JCTree.JCLabeledStatement jCLabeledStatement) {
        Env<GenContext> envDup = this.env.dup(jCLabeledStatement, new GenContext());
        genStat(jCLabeledStatement.body, envDup, 1);
        this.code.resolve(envDup.info.exit);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLetExpr(JCTree.LetExpr letExpr) {
        this.code.resolvePending();
        Code code = this.code;
        int i = code.nextreg;
        int letExprStackPos = code.setLetExprStackPos(code.state.stacksize);
        try {
            genStats(letExpr.defs, this.env);
            this.code.setLetExprStackPos(letExprStackPos);
            JCTree.JCExpression jCExpression = letExpr.expr;
            this.result = genExpr(jCExpression, jCExpression.type).load();
            this.code.endScopes(i);
        } catch (Throwable th) {
            this.code.setLetExprStackPos(letExprStackPos);
            throw th;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLiteral(JCTree.JCLiteral jCLiteral) {
        if (!jCLiteral.type.hasTag(TypeTag.BOT)) {
            this.result = this.items.makeImmediateItem(jCLiteral.type, jCLiteral.value);
        } else {
            this.code.emitop0(1);
            this.result = this.items.makeStackItem(jCLiteral.type);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
        Env<GenContext> envDup = this.env.dup(jCMethodDecl);
        envDup.enclMethod = jCMethodDecl;
        this.pt = jCMethodDecl.sym.erasure(this.types).mo73getReturnType();
        checkDimension(jCMethodDecl.pos(), jCMethodDecl.sym.erasure(this.types));
        genMethod(jCMethodDecl, envDup, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewArray(JCTree.JCNewArray jCNewArray) {
        setTypeAnnotationPositions(jCNewArray.pos);
        if (jCNewArray.elems == null) {
            for (List list = jCNewArray.dims; list.nonEmpty(); list = list.tail) {
                genExpr((JCTree) list.head, this.syms.intType).load();
            }
            this.result = makeNewArray(jCNewArray.pos(), jCNewArray.type, jCNewArray.dims.length());
            return;
        }
        Type typeElemtype = this.types.elemtype(jCNewArray.type);
        loadIntConst(jCNewArray.elems.length());
        Items.Item itemMakeNewArray = makeNewArray(jCNewArray.pos(), jCNewArray.type, 1);
        int i = 0;
        for (List list2 = jCNewArray.elems; list2.nonEmpty(); list2 = list2.tail) {
            itemMakeNewArray.duplicate();
            loadIntConst(i);
            i++;
            genExpr((JCTree) list2.head, typeElemtype).load();
            this.items.makeIndexedItem(typeElemtype).store();
        }
        this.result = itemMakeNewArray;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewClass(JCTree.JCNewClass jCNewClass) {
        Assert.check(jCNewClass.encl == null && jCNewClass.def == null);
        setTypeAnnotationPositions(jCNewClass.pos);
        this.code.emitop2(187, checkDimension(jCNewClass.pos(), jCNewClass.type), new by5());
        this.code.emitop0(89);
        genArgs(jCNewClass.args, jCNewClass.constructor.externalType(this.types).mo71getParameterTypes());
        this.items.makeMemberItem(jCNewClass.constructor, true).invoke();
        this.result = this.items.makeStackItem(jCNewClass.type);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitParens(JCTree.JCParens jCParens) {
        JCTree.JCExpression jCExpression = jCParens.expr;
        this.result = genExpr(jCExpression, jCExpression.type);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReturn(JCTree.JCReturn jCReturn) {
        Env<GenContext> envUnwind;
        Code code = this.code;
        int i = code.nextreg;
        int i2 = code.pendingStatPos;
        if (jCReturn.expr != null) {
            Assert.check(code.isStatementStart());
            Items.Item itemLoad = genExpr(jCReturn.expr, this.pt).load();
            Env<GenContext> env = this.env;
            if (hasFinally(env.enclMethod, env)) {
                itemLoad = makeTemp(this.pt);
                itemLoad.store();
            }
            Env<GenContext> env2 = this.env;
            envUnwind = unwind(env2.enclMethod, env2);
            this.code.pendingStatPos = i2;
            itemLoad.load();
            this.code.emitop0(Code.truncate(Code.typecode(this.pt)) + 172);
        } else {
            Env<GenContext> env3 = this.env;
            envUnwind = unwind(env3.enclMethod, env3);
            Code code2 = this.code;
            code2.pendingStatPos = i2;
            code2.emitop0(177);
        }
        endFinalizerGaps(this.env, envUnwind);
        this.code.endScopes(i);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
        Items.Item itemGenExpr;
        Symbol symbol = jCFieldAccess.sym;
        if (jCFieldAccess.name == this.names._class) {
            this.code.emitLdc((PoolConstant.LoadableConstant) checkDimension(jCFieldAccess.pos(), jCFieldAccess.selected.type));
            this.result = this.items.makeStackItem(this.pt);
            return;
        }
        Symbol symbol2 = TreeInfo.symbol(jCFieldAccess.selected);
        boolean z = symbol2 != null && (symbol2.kind == Kinds.Kind.TYP || symbol2.name == this.names._super);
        boolean zIsAccessSuper = isAccessSuper(this.env.enclMethod);
        if (z) {
            itemGenExpr = this.items.makeSuperItem();
        } else {
            JCTree.JCExpression jCExpression = jCFieldAccess.selected;
            itemGenExpr = genExpr(jCExpression, jCExpression.type);
        }
        if (symbol.kind == Kinds.Kind.VAR) {
            Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) symbol;
            if (varSymbol.getConstValue() != null) {
                if ((symbol.flags() & 8) != 0) {
                    if (!z && (symbol2 == null || symbol2.kind != Kinds.Kind.TYP)) {
                        itemGenExpr = itemGenExpr.load();
                    }
                    itemGenExpr.drop();
                } else {
                    itemGenExpr.load();
                    genNullCheck(jCFieldAccess.selected);
                }
                this.result = this.items.makeImmediateItem(symbol.type, varSymbol.getConstValue());
                return;
            }
        }
        if (isInvokeDynamic(symbol)) {
            this.result = this.items.makeDynamicItem(symbol);
            return;
        }
        Symbol symbolBinaryQualifier = binaryQualifier(symbol, jCFieldAccess.selected.type);
        if ((symbolBinaryQualifier.flags() & 8) != 0) {
            if (!z && (symbol2 == null || symbol2.kind != Kinds.Kind.TYP)) {
                itemGenExpr = itemGenExpr.load();
            }
            itemGenExpr.drop();
            this.result = this.items.makeStaticItem(symbolBinaryQualifier);
            return;
        }
        itemGenExpr.load();
        if (symbolBinaryQualifier != this.syms.lengthVar) {
            this.result = this.items.makeMemberItem(symbolBinaryQualifier, nonVirtualForPrivateAccess(symbolBinaryQualifier) || z || zIsAccessSuper);
        } else {
            this.code.emitop0(190);
            this.result = this.items.makeStackItem(this.syms.intType);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSkip(JCTree.JCSkip jCSkip) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitch(JCTree.JCSwitch jCSwitch) {
        handleSwitch(jCSwitch, jCSwitch.selector, jCSwitch.cases, jCSwitch.patternSwitch);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
        this.code.resolvePending();
        boolean z = this.inCondSwitchExpression;
        try {
            this.inCondSwitchExpression = false;
            doHandleSwitchExpression(jCSwitchExpression);
            this.inCondSwitchExpression = z;
            this.result = this.items.makeStackItem(this.pt);
        } catch (Throwable th) {
            this.inCondSwitchExpression = z;
            throw th;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSynchronized(JCTree.JCSynchronized jCSynchronized) {
        int i = this.code.nextreg;
        final Items.LocalItem localItemMakeTemp = makeTemp(this.syms.objectType);
        Assert.check(this.code.isStatementStart());
        JCTree.JCExpression jCExpression = jCSynchronized.lock;
        genExpr(jCExpression, jCExpression.type).load().duplicate();
        localItemMakeTemp.store();
        this.code.emitop0(194);
        this.code.state.lock(localItemMakeTemp.reg);
        final Env<GenContext> envDup = this.env.dup(jCSynchronized, new GenContext());
        envDup.info.finalize = new GenFinalizer(this) { // from class: com.sun.tools.javac.jvm.Gen.1
            final /* synthetic */ Gen this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.this$0 = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.sun.tools.javac.jvm.Gen.GenFinalizer
            public void gen() {
                genLast();
                Assert.check(((GenContext) envDup.info).gaps.length() % 2 == 0);
                ((GenContext) envDup.info).gaps.append(Integer.valueOf(this.this$0.code.curCP()));
            }

            @Override // com.sun.tools.javac.jvm.Gen.GenFinalizer
            public void genLast() {
                if (this.this$0.code.isAlive()) {
                    localItemMakeTemp.load();
                    this.this$0.code.emitop0(195);
                    this.this$0.code.state.unlock(localItemMakeTemp.reg);
                }
            }
        };
        envDup.info.gaps = new ListBuffer<>();
        genTry(jCSynchronized.body, List.nil(), envDup);
        this.code.endScopes(i);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitThrow(JCTree.JCThrow jCThrow) {
        Assert.check(this.code.isStatementStart());
        JCTree.JCExpression jCExpression = jCThrow.expr;
        genExpr(jCExpression, jCExpression.type).load();
        this.code.emitop0(191);
        Assert.check(this.code.isStatementStart());
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTry(final JCTree.JCTry jCTry) {
        final Env<GenContext> envDup = this.env.dup(jCTry, new GenContext());
        final Env<GenContext> env = this.env;
        envDup.info.finalize = new GenFinalizer(this) { // from class: com.sun.tools.javac.jvm.Gen.2
            final /* synthetic */ Gen this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.this$0 = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.sun.tools.javac.jvm.Gen.GenFinalizer
            public void afterBody() {
                JCTree.JCBlock jCBlock = jCTry.finalizer;
                if (jCBlock == null || (jCBlock.flags & Flags.BODY_ONLY_FINALIZE) == 0) {
                    return;
                }
                ((GenContext) envDup.info).finalize = null;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.sun.tools.javac.jvm.Gen.GenFinalizer
            public void gen() {
                Assert.check(((GenContext) envDup.info).gaps.length() % 2 == 0);
                ((GenContext) envDup.info).gaps.append(Integer.valueOf(this.this$0.code.curCP()));
                genLast();
            }

            @Override // com.sun.tools.javac.jvm.Gen.GenFinalizer
            public void genLast() {
                JCTree.JCBlock jCBlock = jCTry.finalizer;
                if (jCBlock != null) {
                    this.this$0.genStat(jCBlock, env, 2);
                }
            }

            @Override // com.sun.tools.javac.jvm.Gen.GenFinalizer
            public boolean hasFinalizer() {
                return jCTry.finalizer != null;
            }
        };
        envDup.info.gaps = new ListBuffer<>();
        genTry(jCTry.body, jCTry.catchers, envDup);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
        this.result = genExpr(jCTypeCast.expr, jCTypeCast.clazz.type).load();
        setTypeAnnotationPositions(jCTypeCast.pos);
        if (jCTypeCast.clazz.type.isPrimitive() || this.types.isSameType(jCTypeCast.expr.type, jCTypeCast.clazz.type) || this.types.asSuper(jCTypeCast.expr.type, jCTypeCast.clazz.type.tsym) != null) {
            return;
        }
        this.code.emitop2(192, checkDimension(jCTypeCast.pos(), jCTypeCast.clazz.type), new by5());
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
        JCTree.JCExpression jCExpression = jCInstanceOf.expr;
        genExpr(jCExpression, jCExpression.type).load();
        setTypeAnnotationPositions(jCInstanceOf.pos);
        this.code.emitop2(193, makeRef(jCInstanceOf.pos(), jCInstanceOf.pattern.type));
        this.result = this.items.makeStackItem(this.syms.booleanType);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUnary(JCTree.JCUnary jCUnary) {
        Symbol.OperatorSymbol operatorSymbol = jCUnary.operator;
        boolean zHasTag = jCUnary.hasTag(JCTree.Tag.NOT);
        JCTree.JCExpression jCExpression = jCUnary.arg;
        if (zHasTag) {
            this.result = genCond((JCTree) jCExpression, false).negate();
        }
        Items.Item itemGenExpr = genExpr(jCExpression, operatorSymbol.type.mo71getParameterTypes().head);
        switch (AnonymousClass3.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCUnary.getTag().ordinal()]) {
            case 4:
            case 5:
                itemGenExpr.duplicate();
                if (itemGenExpr instanceof Items.LocalItem) {
                    Items.LocalItem localItem = (Items.LocalItem) itemGenExpr;
                    int i = operatorSymbol.opcode;
                    if (i == 96 || i == 100) {
                        Items.Item itemLoad = itemGenExpr.load();
                        localItem.incr(jCUnary.hasTag(JCTree.Tag.POSTINC) ? 1 : -1);
                        this.result = itemLoad;
                    }
                }
                Items.Item itemLoad2 = itemGenExpr.load();
                itemGenExpr.stash(itemGenExpr.typecode);
                this.code.emitop0(one(itemGenExpr.typecode));
                this.code.emitop0(operatorSymbol.opcode);
                int i2 = itemGenExpr.typecode;
                if (i2 != 0 && Code.truncate(i2) == 0) {
                    this.code.emitop0(itemGenExpr.typecode + 140);
                }
                itemGenExpr.store();
                this.result = itemLoad2;
                break;
            case 6:
                this.result = itemGenExpr.load();
                break;
            case 7:
                this.result = itemGenExpr.load();
                this.code.emitop0(operatorSymbol.opcode);
                break;
            case 8:
                this.result = itemGenExpr.load();
                emitMinusOne(itemGenExpr.typecode);
                this.code.emitop0(operatorSymbol.opcode);
                break;
            case 9:
            case 10:
                itemGenExpr.duplicate();
                if (itemGenExpr instanceof Items.LocalItem) {
                    Items.LocalItem localItem2 = (Items.LocalItem) itemGenExpr;
                    int i3 = operatorSymbol.opcode;
                    if (i3 == 96 || i3 == 100) {
                        localItem2.incr(jCUnary.hasTag(JCTree.Tag.PREINC) ? 1 : -1);
                        this.result = itemGenExpr;
                    }
                }
                itemGenExpr.load();
                this.code.emitop0(one(itemGenExpr.typecode));
                this.code.emitop0(operatorSymbol.opcode);
                int i4 = itemGenExpr.typecode;
                if (i4 != 0 && Code.truncate(i4) == 0) {
                    this.code.emitop0(itemGenExpr.typecode + 140);
                }
                this.result = this.items.makeAssignItem(itemGenExpr);
                break;
            case 11:
                this.result = itemGenExpr.load();
                this.code.emitop0(89);
                genNullCheck(jCUnary);
                break;
            default:
                Assert.error();
                break;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        Symbol.VarSymbol varSymbol = jCVariableDecl.sym;
        JCTree.JCExpression jCExpression = jCVariableDecl.init;
        if (jCExpression != null) {
            checkStringConstant(jCExpression.pos(), varSymbol.getConstValue());
            if (varSymbol.getConstValue() == null || this.varDebugInfo) {
                Assert.check(this.code.isStatementStart());
                this.code.newLocal(varSymbol);
                genExpr(jCVariableDecl.init, varSymbol.erasure(this.types)).load();
                this.items.makeLocalItem(varSymbol).store();
                Assert.check(this.code.isStatementStart());
            }
        } else {
            this.code.newLocal(varSymbol);
        }
        checkDimension(jCVariableDecl.pos(), varSymbol.type);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
        genLoop(jCWhileLoop, jCWhileLoop.body, jCWhileLoop.cond, List.nil(), true);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWildcard(JCTree.JCWildcard jCWildcard) {
        throw new AssertionError(getClass().getName());
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitYield(JCTree.JCYield jCYield) {
        Env<GenContext> envUnwindBreak;
        Assert.check(this.code.isStatementStart());
        if (this.inCondSwitchExpression) {
            Items.CondItem condItemGenCond = genCond(jCYield.value, 16);
            Code.Chain chainJumpFalse = condItemGenCond.jumpFalse();
            this.code.resolve(condItemGenCond.trueJumps);
            Env<GenContext> envUnwindBreak2 = unwindBreak(jCYield.target);
            reloadStackBeforeSwitchExpr();
            Code.Chain chainBranch = this.code.branch(167);
            endFinalizerGaps(this.env, envUnwindBreak2);
            this.code.resolve(chainJumpFalse);
            envUnwindBreak = unwindBreak(jCYield.target);
            reloadStackBeforeSwitchExpr();
            Code.Chain chainBranch2 = this.code.branch(167);
            Code.Chain chain = this.switchExpressionTrueChain;
            if (chain == null) {
                this.switchExpressionTrueChain = chainBranch;
            } else {
                this.switchExpressionTrueChain = Code.mergeChains(chain, chainBranch);
            }
            Code.Chain chain2 = this.switchExpressionFalseChain;
            if (chain2 == null) {
                this.switchExpressionFalseChain = chainBranch2;
            } else {
                this.switchExpressionFalseChain = Code.mergeChains(chain2, chainBranch2);
            }
        } else {
            genExpr(jCYield.value, this.pt).load();
            Items.LocalItem localItem = this.switchResult;
            if (localItem != null) {
                localItem.store();
            }
            envUnwindBreak = unwindBreak(jCYield.target);
            if (this.code.isAlive()) {
                reloadStackBeforeSwitchExpr();
                Items.LocalItem localItem2 = this.switchResult;
                if (localItem2 != null) {
                    localItem2.load();
                }
                envUnwindBreak.info.addExit(this.code.branch(167));
                this.code.markDead();
            }
        }
        endFinalizerGaps(this.env, envUnwindBreak);
    }

    public void genStat(JCTree jCTree, Env<GenContext> env, int i) {
        if (!this.genCrt) {
            genStat(jCTree, env);
            return;
        }
        int iCurCP = this.code.curCP();
        genStat(jCTree, env);
        if (jCTree.hasTag(JCTree.Tag.BLOCK)) {
            i |= 2;
        }
        Code code = this.code;
        code.crt.put(jCTree, i, iCurCP, code.curCP());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void genStats(List<? extends JCTree> list, Env<GenContext> env) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            genStat((JCTree) list2.head, env, 1);
        }
    }

    public Items.CondItem genCond(JCTree jCTree, int i) {
        if (!this.genCrt) {
            return genCond(jCTree, false);
        }
        int iCurCP = this.code.curCP();
        Items.CondItem condItemGenCond = genCond(jCTree, (i & 8) != 0);
        Code code = this.code;
        code.crt.put(jCTree, i, iCurCP, code.curCP());
        return condItemGenCond;
    }
}

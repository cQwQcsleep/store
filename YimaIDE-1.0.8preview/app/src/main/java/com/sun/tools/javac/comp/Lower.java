package com.sun.tools.javac.comp;

import com.intellij.psi.PsiKeyword;
import com.reandroid.arsc.value.CompoundEntry;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.source.tree.LambdaExpressionTree;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Lower;
import com.sun.tools.javac.jvm.ByteCodes;
import com.sun.tools.javac.jvm.ClassFile;
import com.sun.tools.javac.jvm.PoolConstant;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import defpackage.s22;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Lower extends TreeTranslator {
    protected static final Context.Key<Lower> lowerKey = new Context.Key<>();
    private List<Symbol.ClassSymbol> accessConstrTags;
    private Map<Symbol, Symbol.MethodSymbol> accessConstrs;
    private Map<Symbol, Integer> accessNums;
    private Map<Symbol, Symbol.MethodSymbol[]> accessSyms;
    private ListBuffer<Symbol> accessed;
    Map<Symbol, Symbol> actualSymbols;
    private Symbol.ClassSymbol assertionsDisabledClassCache;
    private final Attr attr;
    Env<AttrContext> attrEnv;
    private final ConstFold cfolder;
    private final Check chk;
    Map<Symbol.ClassSymbol, JCTree.JCClassDecl> classdefs;
    Symbol.ClassSymbol currentClass;
    JCTree.JCMethodDecl currentMethodDef;
    Symbol.MethodSymbol currentMethodSym;
    Type currentRestype;
    private final boolean debugLower;
    private final boolean disableProtectedAccessors;
    private final Name dollarAssertionsDisabled;
    private JCTree.JCExpression enclOp;
    EndPosTable endPosTable;
    Map<Symbol.ClassSymbol, List<Symbol.VarSymbol>> freevarCache;
    private final Log log;
    private TreeMaker make;
    private JCDiagnostic.DiagnosticPosition make_pos;
    private final Names names;
    private final boolean nullCheckOuterThis;
    private final Operators operators;
    private final boolean optimizeOuterThis;
    List<Symbol.VarSymbol> outerThisStack;
    JCTree.JCClassDecl outermostClassDef;
    JCTree outermostMemberDef;
    private final Option.PkgInfo pkginfoOpt;
    Map<Symbol, Symbol> proxies;
    private final Resolve rs;
    private final Symtab syms;
    private Symbol.MethodSymbol systemArraycopyMethod;
    private final Target target;
    private final TransTypes transTypes;
    ListBuffer<JCTree> translated;
    Scope.WriteableScope twrVars;
    private final TypeEnvs typeEnvs;
    private final HashMap<TypePairs, String> typePairToName;
    private final Types types;
    private final boolean useMatchException;
    private int variableIndex = 0;
    public Map<Symbol.ClassSymbol, List<JCTree>> prunedTree = new WeakHashMap();
    ClassMap classMap = new ClassMap();
    Map<Symbol.TypeSymbol, EnumMapping> enumSwitchMap = new LinkedHashMap();
    JCTree.Visitor conflictsChecker = new TreeScanner() { // from class: com.sun.tools.javac.comp.Lower.1
        Symbol.TypeSymbol currentClass;

        private void syntheticError(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
            if (symbol.type.isErroneous()) {
                return;
            }
            Lower.this.log.error(diagnosticPosition, CompilerProperties.Errors.CannotGenerateClass(symbol.location(), CompilerProperties.Fragments.SyntheticNameConflict(symbol, symbol.location())));
        }

        public void checkConflicts(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Symbol.TypeSymbol typeSymbol) {
            Type typeSupertype = typeSymbol.type;
            while (typeSupertype != Type.noType) {
                for (Symbol symbol2 : typeSupertype.tsym.members().getSymbolsByName(symbol.name, Scope.LookupKind.NON_RECURSIVE)) {
                    if (symbol.kind == symbol2.kind && Lower.this.types.isSameType(Lower.this.types.erasure(symbol.type), Lower.this.types.erasure(symbol2.type)) && symbol != symbol2 && (symbol.flags() & 4096) != (symbol2.flags() & 4096) && (symbol.flags() & Flags.BRIDGE) == 0 && (symbol2.flags() & Flags.BRIDGE) == 0) {
                        if ((symbol2.flags() & 4096) == 0) {
                            symbol = symbol2;
                        }
                        syntheticError(diagnosticPosition, symbol);
                        return;
                    }
                }
                typeSupertype = Lower.this.types.supertype(typeSupertype);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            Symbol.TypeSymbol typeSymbol = this.currentClass;
            this.currentClass = jCClassDecl.sym;
            try {
                super.visitClassDef(jCClassDecl);
            } finally {
                this.currentClass = typeSymbol;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            checkConflicts(jCMethodDecl.pos(), jCMethodDecl.sym, this.currentClass);
            super.visitMethodDef(jCMethodDecl);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            if (jCVariableDecl.sym.owner.kind == Kinds.Kind.TYP) {
                checkConflicts(jCVariableDecl.pos(), jCVariableDecl.sym, this.currentClass);
            }
            super.visitVarDef(jCVariableDecl);
        }
    };

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Lower$2, reason: invalid class name */
    public class AnonymousClass2 extends TreeScanner {
        boolean noClassDef = true;

        public AnonymousClass2() {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            this.noClassDef = false;
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Lower$4, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Kinds$Kind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Symbol$OperatorSymbol$AccessCode;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$main$Option$PkgInfo;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[Option.PkgInfo.values().length];
            $SwitchMap$com$sun$tools$javac$main$Option$PkgInfo = iArr;
            try {
                iArr[Option.PkgInfo.ALWAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$main$Option$PkgInfo[Option.PkgInfo.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$main$Option$PkgInfo[Option.PkgInfo.NONEMPTY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr2;
            try {
                iArr2[JCTree.Tag.LITERAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.IDENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SELECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.INDEXED.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TYPECAST.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.EQ.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NE.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PREINC.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PREDEC.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTINC.ordinal()] = 10;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.POSTDEC.ordinal()] = 11;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.OR.ordinal()] = 12;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.AND.ordinal()] = 13;
            } catch (NoSuchFieldError unused16) {
            }
            int[] iArr3 = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr3;
            try {
                iArr3[TypeTag.BYTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.INT.ordinal()] = 4;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DOUBLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BOOLEAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.VOID.ordinal()] = 9;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CLASS.ordinal()] = 10;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.ARRAY.ordinal()] = 11;
            } catch (NoSuchFieldError unused27) {
            }
            int[] iArr4 = new int[Symbol.OperatorSymbol.AccessCode.values().length];
            $SwitchMap$com$sun$tools$javac$code$Symbol$OperatorSymbol$AccessCode = iArr4;
            try {
                iArr4[Symbol.OperatorSymbol.AccessCode.DEREF.ordinal()] = 1;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Symbol$OperatorSymbol$AccessCode[Symbol.OperatorSymbol.AccessCode.ASSIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Symbol$OperatorSymbol$AccessCode[Symbol.OperatorSymbol.AccessCode.PREINC.ordinal()] = 3;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Symbol$OperatorSymbol$AccessCode[Symbol.OperatorSymbol.AccessCode.POSTINC.ordinal()] = 4;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Symbol$OperatorSymbol$AccessCode[Symbol.OperatorSymbol.AccessCode.PREDEC.ordinal()] = 5;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Symbol$OperatorSymbol$AccessCode[Symbol.OperatorSymbol.AccessCode.POSTDEC.ordinal()] = 6;
            } catch (NoSuchFieldError unused33) {
            }
            int[] iArr5 = new int[Kinds.Kind.values().length];
            $SwitchMap$com$sun$tools$javac$code$Kinds$Kind = iArr5;
            try {
                iArr5[Kinds.Kind.VAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.MTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.TYP.ordinal()] = 3;
            } catch (NoSuchFieldError unused36) {
            }
        }
    }

    public class AssignopDependencyScanner extends TreeScanner {
        boolean dependencyFound = false;
        Symbol sym;

        public AssignopDependencyScanner(JCTree.JCAssignOp jCAssignOp) {
            this.sym = TreeInfo.symbol(jCAssignOp.lhs);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            if (jCTree == null || this.sym == null) {
                return;
            }
            jCTree.accept(this);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
            if (TreeInfo.symbol(jCAssignOp.lhs) == this.sym) {
                this.dependencyFound = true;
            } else {
                super.visitAssignop(jCAssignOp);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitUnary(JCTree.JCUnary jCUnary) {
            if (TreeInfo.symbol(jCUnary.arg) == this.sym) {
                this.dependencyFound = true;
            } else {
                super.visitUnary(jCUnary);
            }
        }
    }

    public class ClassMap extends TreeScanner {
        public ClassMap() {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            Lower.this.classdefs.put(jCClassDecl.sym, jCClassDecl);
            super.visitClassDef(jCClassDecl);
        }
    }

    public class CompileTimeEnumMapping implements EnumMapping {
        final List<Name> enumNames;

        public CompileTimeEnumMapping(List<Name> list) {
            Assert.check(list != null);
            this.enumNames = list;
        }

        @Override // com.sun.tools.javac.comp.Lower.EnumMapping
        public JCTree.JCLiteral caseValue(Symbol.VarSymbol varSymbol) {
            int iIndexOf = this.enumNames.indexOf(varSymbol.name);
            Assert.check(iIndexOf != -1);
            return Lower.this.make.Literal(Integer.valueOf(iIndexOf));
        }

        @Override // com.sun.tools.javac.comp.Lower.EnumMapping
        public JCTree.JCExpression switchValue(JCTree.JCExpression jCExpression) {
            return jCExpression;
        }
    }

    public interface EnumMapping {
        JCTree.JCLiteral caseValue(Symbol.VarSymbol varSymbol);

        JCTree.JCExpression switchValue(JCTree.JCExpression jCExpression);

        default void translate() {
        }
    }

    public class FreeVarCollector extends CaptureScanner {
        public FreeVarCollector(JCTree jCTree) {
            super(jCTree);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void addFreeVars(Symbol.ClassSymbol classSymbol) {
            List list = Lower.this.freevarCache.get(classSymbol);
            if (list != null) {
                while (list.nonEmpty()) {
                    addFreeVar((Symbol.VarSymbol) list.head);
                    list = list.tail;
                }
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
            if (TreeInfo.name(jCMethodInvocation.meth) == Lower.this.names._super) {
                addFreeVars((Symbol.ClassSymbol) TreeInfo.symbol(jCMethodInvocation.meth).owner);
            }
            super.visitApply(jCMethodInvocation);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            addFreeVars((Symbol.ClassSymbol) jCNewClass.constructor.owner);
            super.visitNewClass(jCNewClass);
        }
    }

    public class RuntimeEnumMapping implements EnumMapping {
        final Symbol.TypeSymbol forEnum;
        final Symbol.VarSymbol mapVar;
        JCDiagnostic.DiagnosticPosition pos;
        int next = 1;
        final Map<Symbol.VarSymbol, Integer> values = new LinkedHashMap();

        public RuntimeEnumMapping(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.TypeSymbol typeSymbol) {
            this.pos = null;
            this.forEnum = typeSymbol;
            this.pos = diagnosticPosition;
            Name nameFromString = Lower.this.names.fromString(Lower.this.target.syntheticNameChar() + "SwitchMap" + Lower.this.target.syntheticNameChar() + ClassFile.externalize(typeSymbol.type.tsym.flatName().toString()).replace('/', '.').replace('.', Lower.this.target.syntheticNameChar()));
            Symbol.ClassSymbol classSymbolOuterCacheClass = Lower.this.outerCacheClass();
            Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4120L, nameFromString, new Type.ArrayType(Lower.this.syms.intType, Lower.this.syms.arrayClass), classSymbolOuterCacheClass);
            this.mapVar = varSymbol;
            Lower.this.enterSynthetic(diagnosticPosition, varSymbol, classSymbolOuterCacheClass.members());
        }

        @Override // com.sun.tools.javac.comp.Lower.EnumMapping
        public JCTree.JCLiteral caseValue(Symbol.VarSymbol varSymbol) {
            Integer num = this.values.get(varSymbol);
            if (num == null) {
                Map<Symbol.VarSymbol, Integer> map = this.values;
                int i = this.next;
                this.next = i + 1;
                Integer numValueOf = Integer.valueOf(i);
                map.put(varSymbol, numValueOf);
                num = numValueOf;
            }
            return Lower.this.make.Literal(num);
        }

        @Override // com.sun.tools.javac.comp.Lower.EnumMapping
        public JCTree.JCExpression switchValue(JCTree.JCExpression jCExpression) {
            return Lower.this.make.Indexed(this.mapVar, jCExpression);
        }

        @Override // com.sun.tools.javac.comp.Lower.EnumMapping
        public void translate() {
            Lower lower = Lower.this;
            boolean z = lower.attrEnv.info.allowProtectedAccess;
            try {
                lower.make.at(this.pos.getStartPosition());
                Lower lower2 = Lower.this;
                lower2.attrEnv.info.allowProtectedAccess = true;
                JCTree.JCClassDecl jCClassDeclClassDef = lower2.classDef((Symbol.ClassSymbol) this.mapVar.owner);
                Lower lower3 = Lower.this;
                JCTree.JCExpression type = Lower.this.make.NewArray(Lower.this.make.Type(Lower.this.syms.intType), List.of(Lower.this.make.Select(Lower.this.make.App(Lower.this.make.QualIdent(lower3.lookupMethod(this.pos, lower3.names.values, this.forEnum.type, List.nil()))), Lower.this.syms.lengthVar)), null).setType((Type) new Type.ArrayType(Lower.this.syms.intType, Lower.this.syms.arrayClass));
                ListBuffer listBuffer = new ListBuffer();
                Lower lower4 = Lower.this;
                Symbol.MethodSymbol methodSymbolLookupMethod = lower4.lookupMethod(this.pos, lower4.names.ordinal, this.forEnum.type, List.nil());
                List<JCTree.JCCatch> listPrepend = List.nil().prepend(Lower.this.make.Catch(Lower.this.make.VarDef(new Symbol.VarSymbol(8589934592L, Lower.this.names.ex, Lower.this.syms.noSuchFieldErrorType, Lower.this.syms.noSymbol), null), Lower.this.make.Block(0L, List.nil())));
                for (Map.Entry<Symbol.VarSymbol, Integer> entry : this.values.entrySet()) {
                    listBuffer.append(Lower.this.make.Try(Lower.this.make.Block(0L, List.of(Lower.this.make.Exec(Lower.this.make.Assign(Lower.this.make.Indexed(this.mapVar, Lower.this.make.App(Lower.this.make.Select(Lower.this.make.QualIdent(entry.getKey()), methodSymbolLookupMethod))), Lower.this.make.Literal(entry.getValue())).setType((Type) Lower.this.syms.intType)))), listPrepend, null));
                }
                jCClassDeclClassDef.defs = jCClassDeclClassDef.defs.prepend(Lower.this.make.Block(8L, listBuffer.toList())).prepend(Lower.this.make.VarDef(this.mapVar, type));
            } finally {
                Lower.this.attrEnv.info.allowProtectedAccess = z;
            }
        }
    }

    public interface TreeBuilder {
        JCTree.JCExpression build(JCTree.JCExpression jCExpression);
    }

    public Lower(Context context) {
        boolean z = false;
        context.put(lowerKey, this);
        Names namesInstance = Names.instance(context);
        this.names = namesInstance;
        this.log = Log.instance(context);
        Symtab symtabInstance = Symtab.instance(context);
        this.syms = symtabInstance;
        this.rs = Resolve.instance(context);
        this.operators = Operators.instance(context);
        this.chk = Check.instance(context);
        this.attr = Attr.instance(context);
        this.make = TreeMaker.instance(context);
        this.cfolder = ConstFold.instance(context);
        Target targetInstance = Target.instance(context);
        this.target = targetInstance;
        this.typeEnvs = TypeEnvs.instance(context);
        this.dollarAssertionsDisabled = namesInstance.fromString(targetInstance.syntheticNameChar() + "assertionsDisabled");
        this.types = Types.instance(context);
        this.transTypes = TransTypes.instance(context);
        Options optionsInstance = Options.instance(context);
        this.debugLower = optionsInstance.isSet("debuglower");
        this.pkginfoOpt = Option.PkgInfo.get(optionsInstance);
        this.optimizeOuterThis = targetInstance.optimizeOuterThis() || optionsInstance.getBoolean("optimizeOuterThis", false);
        this.nullCheckOuterThis = optionsInstance.getBoolean("nullCheckOuterThis", targetInstance.nullCheckOuterThisByDefault());
        this.disableProtectedAccessors = optionsInstance.isSet("disableProtectedAccessors");
        Source sourceInstance = Source.instance(context);
        Preview previewInstance = Preview.instance(context);
        Source.Feature feature = Source.Feature.PATTERN_SWITCH;
        if (feature.allowedInSource(sourceInstance) && (previewInstance.isEnabled() || !previewInstance.isPreview(feature))) {
            z = true;
        }
        this.useMatchException = z;
        this.typePairToName = TypePairs.initialize(symtabInstance);
    }

    public static /* synthetic */ JCTree.JCExpression a(Lower lower, JCTree.JCExpression jCExpression, JCTree.JCArrayAccess jCArrayAccess, TreeBuilder treeBuilder, JCTree.JCExpression jCExpression2) {
        JCTree.JCArrayAccess jCArrayAccessIndexed = lower.make.Indexed(jCExpression, jCExpression2);
        jCArrayAccessIndexed.setType(jCArrayAccess.type);
        return treeBuilder.build(jCArrayAccessIndexed);
    }

    private static int accessCode(JCTree jCTree, JCTree jCTree2) {
        if (jCTree2 == null) {
            return Symbol.OperatorSymbol.AccessCode.DEREF.code;
        }
        if (jCTree2.hasTag(JCTree.Tag.ASSIGN) && jCTree == TreeInfo.skipParens(((JCTree.JCAssign) jCTree2).lhs)) {
            return Symbol.OperatorSymbol.AccessCode.ASSIGN.code;
        }
        if (jCTree2.getTag().isIncOrDecUnaryOp() || jCTree2.getTag().isAssignop()) {
            JCTree.JCOperatorExpression jCOperatorExpression = (JCTree.JCOperatorExpression) jCTree2;
            if (jCTree == TreeInfo.skipParens(jCOperatorExpression.getOperand(JCTree.JCOperatorExpression.OperandPos.LEFT))) {
                return jCOperatorExpression.operator.getAccessCode(jCTree2.getTag());
            }
        }
        return Symbol.OperatorSymbol.AccessCode.DEREF.code;
    }

    private List<JCTree.JCCase> addDefaultIfNeeded(boolean z, boolean z2, List<JCTree.JCCase> list) {
        if (!list.stream().flatMap(new Function() { // from class: dj9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((JCTree.JCCase) obj).labels.stream();
            }
        }).noneMatch(new Predicate() { // from class: ej9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((JCTree.JCCaseLabel) obj).hasTag(JCTree.Tag.DEFAULTCASELABEL);
            }
        })) {
            return list;
        }
        boolean z3 = (z && !z2) | this.useMatchException;
        Symtab symtab = this.syms;
        JCTree.JCThrow jCThrowThrow = this.make.Throw(makeNewClass(z3 ? symtab.matchExceptionType : symtab.incompatibleClassChangeErrorType, z3 ? List.of(makeNull(), makeNull()) : List.nil()));
        TreeMaker treeMaker = this.make;
        return list.prepend(treeMaker.Case(JCTree.JCCase.STATEMENT, List.of(treeMaker.DefaultCaseLabel()), null, List.of(jCThrowThrow), null));
    }

    private void addPrunedInfo(JCTree jCTree) {
        List<JCTree> list = this.prunedTree.get(this.currentClass);
        this.prunedTree.put(this.currentClass, list == null ? List.of(jCTree) : list.prepend(jCTree));
    }

    private String argsTypeSig(List<Type> list) {
        LowerSignatureGenerator lowerSignatureGenerator = new LowerSignatureGenerator();
        lowerSignatureGenerator.assembleSig(list);
        return lowerSignatureGenerator.toString();
    }

    private JCTree.JCExpression assertFlagTest(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        Symbol.ClassSymbol classSymbol = this.outermostClassDef.sym;
        Symbol.ClassSymbol classSymbolAssertionsDisabledClass = !this.currentClass.isInterface() ? this.currentClass : assertionsDisabledClass();
        Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) lookupSynthetic(this.dollarAssertionsDisabled, classSymbolAssertionsDisabledClass.members());
        if (varSymbol == null) {
            Symbol.VarSymbol varSymbol2 = new Symbol.VarSymbol(4120L, this.dollarAssertionsDisabled, this.syms.booleanType, classSymbolAssertionsDisabledClass);
            enterSynthetic(diagnosticPosition, varSymbol2, classSymbolAssertionsDisabledClass.members());
            Symbol.MethodSymbol methodSymbolLookupMethod = lookupMethod(diagnosticPosition, this.names.desiredAssertionStatus, this.types.erasure(this.syms.classType), List.nil());
            JCTree.JCClassDecl jCClassDeclClassDef = classDef(classSymbolAssertionsDisabledClass);
            make_at(jCClassDeclClassDef.pos());
            JCTree.Tag tag = JCTree.Tag.NOT;
            TreeMaker treeMaker = this.make;
            jCClassDeclClassDef.defs = jCClassDeclClassDef.defs.prepend(this.make.VarDef(varSymbol2, makeUnary(tag, treeMaker.App(treeMaker.Select(classOfType(this.types.erasure(classSymbol.type), jCClassDeclClassDef.pos()), methodSymbolLookupMethod)))));
            if (this.currentClass.isInterface()) {
                JCTree.JCClassDecl jCClassDeclClassDef2 = classDef(this.currentClass);
                make_at(jCClassDeclClassDef2.pos());
                TreeMaker treeMaker2 = this.make;
                jCClassDeclClassDef2.defs = jCClassDeclClassDef2.defs.prepend(this.make.Block(8L, List.of(treeMaker2.If(treeMaker2.QualIdent(varSymbol2), this.make.Skip(), null))));
            }
            varSymbol = varSymbol2;
        }
        make_at(diagnosticPosition);
        return makeUnary(JCTree.Tag.NOT, this.make.Ident(varSymbol));
    }

    private Symbol.ClassSymbol assertionsDisabledClass() {
        Symbol.ClassSymbol classSymbol = this.assertionsDisabledClassCache;
        if (classSymbol != null) {
            return classSymbol;
        }
        Symbol.ClassSymbol classSymbol2 = makeEmptyClass(4104L, this.outermostClassDef.sym).sym;
        this.assertionsDisabledClassCache = classSymbol2;
        return classSymbol2;
    }

    private Symbol.OperatorSymbol binaryAccessOperator(final int i, final JCTree.Tag tag) {
        return this.operators.lookupBinaryOp(new Predicate() { // from class: lj9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Lower.d(tag, i, (Symbol.OperatorSymbol) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void checkAccessConstructorTags() {
        Lower lower;
        List list = this.accessConstrTags;
        while (list.nonEmpty()) {
            Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) list.head;
            if (this.isTranslatedClassAvailable(classSymbol)) {
                lower = this;
            } else {
                lower = this;
                JCTree.JCClassDecl jCClassDeclMakeEmptyClass = lower.makeEmptyClass(4104L, classSymbol.outermostClass(), classSymbol.flatname, false);
                lower.swapAccessConstructorTag(classSymbol, jCClassDeclMakeEmptyClass.sym);
                lower.translated.append(jCClassDeclMakeEmptyClass);
            }
            list = list.tail;
            this = lower;
        }
    }

    private JCTree.JCExpression classOf(JCTree jCTree) {
        return classOfType(jCTree.type, jCTree.pos());
    }

    private JCTree.JCExpression classOfType(Type type, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        switch (AnonymousClass4.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                Symbol.ClassSymbol classSymbolBoxedClass = this.types.boxedClass(type);
                Resolve resolve = this.rs;
                Symbol symbolAccessBase = resolve.accessBase(resolve.findIdentInType(diagnosticPosition, this.attrEnv, classSymbolBoxedClass.type, this.names.TYPE, Kinds.KindSelector.VAR), diagnosticPosition, classSymbolBoxedClass.type, this.names.TYPE, true);
                if (symbolAccessBase.kind == Kinds.Kind.VAR) {
                    ((Symbol.VarSymbol) symbolAccessBase).getConstValue();
                }
                return this.make.QualIdent(symbolAccessBase);
            case 10:
            case 11:
                return make_at(diagnosticPosition).Select(this.make.Type(type), new Symbol.VarSymbol(25L, this.names._class, this.syms.classType, type.tsym));
            default:
                x1f.a();
                return null;
        }
    }

    private JCTree.JCExpression convert(JCTree.JCExpression jCExpression, Type type) {
        Type type2 = jCExpression.type;
        if (type2 == type || type2.hasTag(TypeTag.BOT)) {
            return jCExpression;
        }
        JCTree.JCTypeCast jCTypeCastTypeCast = make_at(jCExpression.pos()).TypeCast(this.make.Type(type), jCExpression);
        if (jCExpression.type.constValue() != null) {
            type = this.cfolder.coerce(jCExpression.type, type);
        }
        jCTypeCastTypeCast.type = type;
        return jCTypeCastTypeCast;
    }

    private void createInfoClass(List<JCTree.JCAnnotation> list, Symbol.ClassSymbol classSymbol) {
        TreeMaker treeMaker = this.make;
        JCTree.JCClassDecl jCClassDeclClassDef = treeMaker.ClassDef(treeMaker.Modifiers(1536L, list), classSymbol.name, List.nil(), null, List.nil(), List.nil());
        jCClassDeclClassDef.sym = classSymbol;
        this.translated.append(jCClassDeclClassDef);
    }

    public static /* synthetic */ boolean d(JCTree.Tag tag, int i, Symbol.OperatorSymbol operatorSymbol) {
        return operatorSymbol.getAccessCode(tag) == i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterSynthetic(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Scope.WriteableScope writeableScope) {
        writeableScope.enter(symbol);
    }

    private Boolean expValue(JCTree jCTree) {
        boolean z;
        while (jCTree.hasTag(JCTree.Tag.PARENS)) {
            jCTree = ((JCTree.JCParens) jCTree).expr;
        }
        int i = AnonymousClass4.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
        if (i == 6) {
            z = true;
        } else {
            if (i != 7) {
                return null;
            }
            z = false;
        }
        JCTree.JCBinary jCBinary = (JCTree.JCBinary) jCTree;
        Type type = jCBinary.lhs.type;
        TypeTag typeTag = TypeTag.BOT;
        boolean zHasTag = type.hasTag(typeTag);
        JCTree.JCExpression jCExpression = jCBinary.rhs;
        if (zHasTag) {
            return expValueIsNull(z, jCExpression);
        }
        if (jCExpression.type.hasTag(typeTag)) {
            return expValueIsNull(z, jCBinary.lhs);
        }
        return null;
    }

    private Boolean expValueIsNull(boolean z, JCTree jCTree) {
        if (jCTree.type.hasTag(TypeTag.BOT)) {
            return Boolean.valueOf(z);
        }
        if (jCTree.hasTag(JCTree.Tag.LITERAL)) {
            return Boolean.valueOf(!z);
        }
        return null;
    }

    public static /* synthetic */ JCTree.JCExpression g(Lower lower, JCTree.JCUnary jCUnary, JCTree.JCExpression jCExpression, boolean z, JCTree.JCExpression jCExpression2) {
        lower.getClass();
        JCTree.Tag tag = jCUnary.hasTag(JCTree.Tag.POSTINC) ? JCTree.Tag.PLUS_ASG : JCTree.Tag.MINUS_ASG;
        JCTree.JCExpression jCExpressionTypeCast = (JCTree.JCExpression) jCExpression.clone();
        if (z) {
            jCExpressionTypeCast = lower.make.TypeCast(jCUnary.arg.type, jCExpressionTypeCast);
        }
        return lower.makeComma(lower.makeAssignop(tag, jCExpressionTypeCast, lower.make.Literal(1)), jCExpression2);
    }

    private JCTree.JCExpression getExactnessCheck(JCTree.JCInstanceOf jCInstanceOf, JCTree.JCExpression jCExpression) {
        TypePairs typePairsOf = TypePairs.of(this.syms, this.types.unboxedTypeOrType(jCInstanceOf.expr.type), jCInstanceOf.pattern.type);
        Name nameFromString = this.names.fromString(this.typePairToName.get(typePairsOf));
        Symbol.MethodSymbol methodSymbolLookupMethod = lookupMethod(jCInstanceOf.pos(), nameFromString, this.syms.exactConversionsSupportType, List.of(typePairsOf.from.type));
        TreeMaker treeMaker = this.make;
        JCTree.JCFieldAccess jCFieldAccessSelect = treeMaker.Select(treeMaker.QualIdent(this.syms.exactConversionsSupportType.tsym), nameFromString);
        jCFieldAccessSelect.sym = methodSymbolLookupMethod;
        jCFieldAccessSelect.setType((Type) this.syms.booleanType);
        JCTree.JCMethodInvocation jCMethodInvocationApply = this.make.Apply(List.nil(), jCFieldAccessSelect, List.of(jCExpression));
        jCMethodInvocationApply.setType((Type) this.syms.booleanType);
        return jCMethodInvocationApply;
    }

    public static /* synthetic */ JCTree.JCMethodDecl h(Lower lower, List list, JCTree.JCClassDecl jCClassDecl, final Symbol.RecordComponent recordComponent) {
        lower.getClass();
        JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) list.stream().filter(new Predicate() { // from class: mj9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Lower.s(recordComponent, (JCTree.JCVariableDecl) obj);
            }
        }).findAny().get();
        lower.make_at(jCClassDecl.pos());
        TreeMaker treeMaker = lower.make;
        return treeMaker.MethodDef(recordComponent.accessor, treeMaker.Block(0L, List.of(treeMaker.Return(treeMaker.Ident(jCVariableDecl)))));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void handleSwitch(JCTree jCTree, JCTree.JCExpression jCExpression, List<JCTree.JCCase> list) {
        ListBuffer<JCTree.JCCase> listBuffer = new ListBuffer();
        Iterator<JCTree.JCCase> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            JCTree.JCCase next = it.next();
            int size = next.labels.size();
            if (size == 0 || size == 1) {
                listBuffer.append(next);
            } else {
                List list2 = next.labels;
                while (list2.tail.nonEmpty()) {
                    listBuffer.append(make_at(next.pos()).Case(JCTree.JCCase.STATEMENT, List.of((JCTree.JCCaseLabel) list2.head), null, List.nil(), null));
                    list2 = list2.tail;
                }
                next.labels = list2;
                listBuffer.append(next);
            }
        }
        for (JCTree.JCCase jCCase : listBuffer) {
            if (jCCase.caseKind == JCTree.JCCase.RULE && jCCase.completesNormally) {
                JCTree.JCBreak jCBreakBreak = this.make.at(TreeInfo.endPos(jCCase.stats.last())).Break(null);
                jCBreakBreak.target = jCTree;
                jCCase.stats = jCCase.stats.append(jCBreakBreak);
            }
        }
        List<JCTree.JCCase> list3 = listBuffer.toList();
        Type typeSupertype = this.types.supertype(jCExpression.type);
        boolean z = (typeSupertype == null || (jCExpression.type.tsym.flags() & 16384) == 0) ? false : true;
        boolean z2 = typeSupertype != null && this.types.isSameType(jCExpression.type, this.syms.stringType);
        boolean z3 = (z || z2 || jCExpression.type.isPrimitive()) ? false : true;
        JCTree.JCExpression jCExpressionTranslate = translate(jCExpression, jCExpression.type);
        List<JCTree.JCCase> listTranslateCases = translateCases(list3);
        if (jCTree.hasTag(JCTree.Tag.SWITCH)) {
            JCTree.JCSwitch jCSwitch = (JCTree.JCSwitch) jCTree;
            jCSwitch.selector = jCExpressionTranslate;
            jCSwitch.cases = listTranslateCases;
        } else if (jCTree.hasTag(JCTree.Tag.SWITCH_EXPRESSION)) {
            JCTree.JCSwitchExpression jCSwitchExpression = (JCTree.JCSwitchExpression) jCTree;
            jCSwitchExpression.selector = jCExpressionTranslate;
            jCSwitchExpression.cases = listTranslateCases;
        } else {
            Assert.error();
        }
        if (z) {
            this.result = visitEnumSwitch(jCTree, jCExpressionTranslate, listTranslateCases);
            return;
        }
        if (z2) {
            this.result = visitStringSwitch(jCTree, jCExpressionTranslate, listTranslateCases);
        } else if (z3) {
            this.result = visitBoxedPrimitiveSwitch(jCTree, jCExpressionTranslate, listTranslateCases);
        } else {
            this.result = jCTree;
        }
    }

    public static /* synthetic */ JCTree.JCExpression i(final Lower lower, final JCTree.JCUnary jCUnary, final boolean z, final JCTree.JCExpression jCExpression) {
        lower.getClass();
        return lower.abstractRval(jCExpression, jCUnary.arg.type, new TreeBuilder() { // from class: com.sun.tools.javac.comp.i1
            @Override // com.sun.tools.javac.comp.Lower.TreeBuilder
            public final JCTree.JCExpression build(JCTree.JCExpression jCExpression2) {
                return Lower.g(this.a, jCUnary, jCExpression, z, jCExpression2);
            }
        });
    }

    public static Lower instance(Context context) {
        Lower lower = (Lower) context.get(lowerKey);
        return lower == null ? new Lower(context) : lower;
    }

    private boolean isFalse(JCTree jCTree) {
        if (jCTree.type.isFalse()) {
            return true;
        }
        Boolean boolExpValue = expValue(jCTree);
        return (boolExpValue == null || boolExpValue.booleanValue()) ? false : true;
    }

    private boolean isTranslatedClassAvailable(Symbol.ClassSymbol classSymbol) {
        for (JCTree jCTree : this.translated) {
            if (jCTree.hasTag(JCTree.Tag.CLASSDEF) && ((JCTree.JCClassDecl) jCTree).sym == classSymbol) {
                return true;
            }
        }
        return false;
    }

    private boolean isTrue(JCTree jCTree) {
        if (jCTree.type.isTrue()) {
            return true;
        }
        Boolean boolExpValue = expValue(jCTree);
        if (boolExpValue == null) {
            return false;
        }
        return boolExpValue.booleanValue();
    }

    public static /* synthetic */ boolean j(Symbol symbol) {
        return symbol.kind == Kinds.Kind.VAR && (symbol.flags() & Flags.RECORD) != 0;
    }

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
    public static /* synthetic */ JCTree.JCExpression k(Lower lower, JCTree.JCAssignOp jCAssignOp, boolean z, JCTree.JCExpression jCExpression) {
        lower.getClass();
        JCTree.Tag tagNoAssignOp = jCAssignOp.getTag().noAssignOp();
        Symbol.OperatorSymbol operatorSymbolResolveBinary = lower.operators.resolveBinary(jCAssignOp, tagNoAssignOp, jCAssignOp.type, jCAssignOp.rhs.type);
        JCTree.JCExpression jCExpressionTypeCast = (JCTree.JCExpression) jCExpression.clone();
        Type type = jCExpressionTypeCast.type;
        Type type2 = jCAssignOp.type;
        if (type != type2) {
            jCExpressionTypeCast = lower.make.TypeCast(type2, jCExpressionTypeCast);
        }
        JCTree.JCBinary jCBinaryBinary = lower.make.Binary(tagNoAssignOp, jCExpressionTypeCast, jCAssignOp.rhs);
        jCBinaryBinary.operator = operatorSymbolResolveBinary;
        jCBinaryBinary.type = operatorSymbolResolveBinary.type.mo73getReturnType();
        JCTree.JCExpression jCExpressionTypeCast2 = jCBinaryBinary;
        if (z) {
            jCExpressionTypeCast2 = lower.make.TypeCast(lower.types.unboxedType(jCAssignOp.type), jCBinaryBinary);
        }
        return lower.make.Assign(jCExpression, jCExpressionTypeCast2).setType(jCAssignOp.type);
    }

    public static /* synthetic */ boolean l(JCTree.JCExpression jCExpression) {
        return jCExpression == null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Symbol.MethodSymbol lookupMethod(JCDiagnostic.DiagnosticPosition diagnosticPosition, Name name, Type type, List<Type> list) {
        return this.rs.resolveInternalMethod(diagnosticPosition, this.attrEnv, type, name, list, List.nil());
    }

    private Symbol lookupSynthetic(Name name, Scope scope) {
        Symbol symbolFindFirst = scope.findFirst(name);
        if (symbolFindFirst == null || (symbolFindFirst.flags() & 4096) == 0) {
            return null;
        }
        return symbolFindFirst;
    }

    private JCTree.JCMethodInvocation makeCall(JCTree.JCExpression jCExpression, Name name, List<JCTree.JCExpression> list) {
        Assert.checkNonNull(jCExpression.type);
        Symbol.MethodSymbol methodSymbolLookupMethod = lookupMethod(this.make_pos, name, jCExpression.type, TreeInfo.types(list));
        TreeMaker treeMaker = this.make;
        return treeMaker.App(treeMaker.Select(jCExpression, methodSymbolLookupMethod), list);
    }

    private JCTree.JCExpression makeNonNullCheck(JCTree.JCExpression jCExpression) {
        return makeBinary(JCTree.Tag.NE, jCExpression, makeNull());
    }

    private JCTree.JCVariableDecl makeOuterThisVarDecl(int i, Symbol.VarSymbol varSymbol) {
        JCTree.JCVariableDecl jCVariableDeclVarDef = this.make.at(i).VarDef(varSymbol, null);
        jCVariableDeclVarDef.vartype = access(jCVariableDeclVarDef.vartype);
        return jCVariableDeclVarDef;
    }

    private Symbol.VarSymbol makeOuterThisVarSymbol(Symbol symbol, long j) {
        Type typeErasure = symbol.innermostAccessibleEnclosingClass().erasure(this.types);
        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(j | ClassFileConstants.JDK20, outerThisName(typeErasure, symbol), typeErasure, symbol);
        this.outerThisStack = this.outerThisStack.prepend(varSymbol);
        return varSymbol;
    }

    private JCTree.JCStatement makeResourceCloseInvocation(JCTree.JCExpression jCExpression) {
        if (this.types.asSuper(jCExpression.type, this.syms.autoCloseableType.tsym) == null) {
            jCExpression = convert(jCExpression, this.syms.autoCloseableType);
        }
        return this.make.Exec(makeCall(jCExpression, this.names.close, List.nil()));
    }

    private Name makeSyntheticName(Name name, Scope scope) {
        do {
            name = name.append(this.target.syntheticNameChar(), this.names.empty);
        } while (lookupSynthetic(name, scope) != null);
        return name;
    }

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
    private JCTree.JCBlock makeTwrBlock(List<JCTree> list, JCTree.JCBlock jCBlock, int i) {
        JCTree.JCExpression type;
        if (list.isEmpty()) {
            return jCBlock;
        }
        ListBuffer listBuffer = new ListBuffer();
        JCTree jCTree = list.head;
        boolean z = false;
        if (jCTree instanceof JCTree.JCVariableDecl) {
            JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) jCTree;
            type = this.make.Ident(jCVariableDecl.sym).setType(jCTree.type);
            JCTree.JCExpression jCExpression = jCVariableDecl.init;
            if (jCExpression != null && TreeInfo.skipParens(jCExpression).hasTag(JCTree.Tag.NEWCLASS)) {
                z = true;
            }
            listBuffer.add(jCVariableDecl);
        } else {
            Assert.check(jCTree instanceof JCTree.JCExpression);
            Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4112L, makeSyntheticName(this.names.fromString("twrVar" + i), this.twrVars), jCTree.type.hasTag(TypeTag.BOT) ? this.syms.autoCloseableType : jCTree.type, this.currentMethodSym);
            this.twrVars.enter(varSymbol);
            JCTree.JCVariableDecl jCVariableDeclVarDef = this.make.VarDef(varSymbol, (JCTree.JCExpression) jCTree);
            JCTree.JCIdent jCIdentIdent = this.make.Ident(varSymbol);
            listBuffer.add(jCVariableDeclVarDef);
            type = jCIdentIdent;
        }
        TreeMaker treeMaker = this.make;
        int i2 = treeMaker.pos;
        treeMaker.at(TreeInfo.endPos(jCBlock));
        JCTree.JCStatement jCStatementMakeResourceCloseInvocation = makeResourceCloseInvocation(type);
        if (!z) {
            jCStatementMakeResourceCloseInvocation = this.make.If(makeNonNullCheck(type), jCStatementMakeResourceCloseInvocation, null);
        }
        JCTree.JCBlock jCBlockBlock = this.make.Block(Flags.BODY_ONLY_FINALIZE, List.of(jCStatementMakeResourceCloseInvocation));
        this.make.at(i2);
        Symbol.VarSymbol varSymbol2 = new Symbol.VarSymbol(4112L, this.names.fromString("t" + this.target.syntheticNameChar()), this.syms.throwableType, this.currentMethodSym);
        JCTree.JCVariableDecl jCVariableDeclVarDef2 = this.make.VarDef(varSymbol2, null);
        Symbol.VarSymbol varSymbol3 = new Symbol.VarSymbol(4096L, this.make.paramName(2), this.syms.throwableType, this.currentMethodSym);
        TreeMaker treeMaker2 = this.make;
        boolean z2 = z;
        JCTree.JCTry jCTryTry = this.make.Try(this.make.Block(0L, List.of(makeResourceCloseInvocation(type))), List.of(this.make.Catch(this.make.VarDef(varSymbol3, null), this.make.Block(0L, List.of(treeMaker2.Exec(makeCall(treeMaker2.Ident(varSymbol2), this.names.addSuppressed, List.of(this.make.Ident(varSymbol3)))))))), null);
        jCTryTry.finallyCanCompleteNormally = true;
        Object objIf = jCTryTry;
        if (!z2) {
            objIf = this.make.If(makeNonNullCheck(type), jCTryTry, null);
        }
        TreeMaker treeMaker3 = this.make;
        JCTree.JCTry jCTryTry2 = this.make.Try(makeTwrBlock(list.tail, jCBlock, i + 1), List.of(this.make.Catch(jCVariableDeclVarDef2, this.make.Block(0L, List.of((JCTree.JCThrow) objIf, treeMaker3.Throw(treeMaker3.Ident(varSymbol2)))))), jCBlockBlock);
        jCTryTry2.finallyCanCompleteNormally = true;
        listBuffer.add(jCTryTry2);
        return this.make.Block(0L, listBuffer.toList());
    }

    private boolean needPackageInfoClass(JCTree.JCPackageDecl jCPackageDecl) {
        int i = AnonymousClass4.$SwitchMap$com$sun$tools$javac$main$Option$PkgInfo[this.pkginfoOpt.ordinal()];
        if (i == 1) {
            return true;
        }
        if (i == 2) {
            return jCPackageDecl.getAnnotations().nonEmpty();
        }
        if (i != 3) {
            x1f.a();
            return false;
        }
        Iterator<Attribute.Compound> it = jCPackageDecl.packge.getDeclarationAttributes().iterator();
        while (it.hasNext()) {
            if (this.types.getRetention(it.next()) != Attribute.RetentionPolicy.SOURCE) {
                return true;
            }
        }
        return false;
    }

    private boolean noClassDefIn(JCTree jCTree) {
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.scan(jCTree);
        return anonymousClass2.noClassDef;
    }

    public static /* synthetic */ JCTree.JCExpression o(final Lower lower, final JCTree.JCArrayAccess jCArrayAccess, final TreeBuilder treeBuilder, final JCTree.JCExpression jCExpression) {
        lower.getClass();
        return lower.abstractRval(jCArrayAccess.index, lower.syms.intType, new TreeBuilder() { // from class: com.sun.tools.javac.comp.j1
            @Override // com.sun.tools.javac.comp.Lower.TreeBuilder
            public final JCTree.JCExpression build(JCTree.JCExpression jCExpression2) {
                return Lower.a(this.a, jCExpression, jCArrayAccess, treeBuilder, jCExpression2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Symbol.ClassSymbol outerCacheClass() {
        Symbol.ClassSymbol classSymbol = this.outermostClassDef.sym;
        for (Symbol symbol : classSymbol.members().getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
            if (symbol.kind == Kinds.Kind.TYP && symbol.name == this.names.empty && (symbol.flags() & 512) == 0) {
                return (Symbol.ClassSymbol) symbol;
            }
        }
        return makeEmptyClass(4104L, classSymbol).sym;
    }

    public static /* synthetic */ EnumMapping p(Lower lower, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.TypeSymbol typeSymbol) {
        lower.getClass();
        return lower.new RuntimeEnumMapping(diagnosticPosition, typeSymbol);
    }

    private void patchTargets(JCTree jCTree, final JCTree jCTree2, final JCTree jCTree3) {
        new TreeScanner(this) { // from class: com.sun.tools.javac.comp.Lower.1Patcher
            final /* synthetic */ Lower this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitBreak(JCTree.JCBreak jCBreak) {
                if (jCBreak.target == jCTree2) {
                    jCBreak.target = jCTree3;
                }
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitContinue(JCTree.JCContinue jCContinue) {
                if (jCContinue.target == jCTree2) {
                    jCContinue.target = jCTree3;
                }
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitYield(JCTree.JCYield jCYield) {
                if (jCYield.target == jCTree2) {
                    jCYield.target = jCTree3;
                }
                scan(jCYield.value);
            }
        }.scan(jCTree);
    }

    public static /* synthetic */ boolean q(Symbol.VarSymbol varSymbol, JCTree.JCVariableDecl jCVariableDecl) {
        return jCVariableDecl.name == varSymbol.name;
    }

    private List<Symbol.VarSymbol> recordVars(Type type) {
        List<Symbol.VarSymbol> listNil = List.nil();
        while (!type.hasTag(TypeTag.NONE)) {
            if (type.hasTag(TypeTag.CLASS)) {
                Iterator<Symbol> it = type.tsym.members().getSymbols(new Predicate() { // from class: cj9
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Lower.j((Symbol) obj);
                    }
                }).iterator();
                while (it.hasNext()) {
                    listNil = listNil.prepend((Symbol.VarSymbol) it.next());
                }
            }
            type = this.types.supertype(type);
        }
        return listNil;
    }

    public static /* synthetic */ boolean s(Symbol.RecordComponent recordComponent, JCTree.JCVariableDecl jCVariableDecl) {
        return jCVariableDecl.name == recordComponent.name;
    }

    private boolean shouldEmitOuterThis(Symbol.ClassSymbol classSymbol) {
        return !this.optimizeOuterThis || (this.outerThisStack.head.flags_field & ClassFileConstants.JDK20) == 0 || this.rs.isSerializable(classSymbol.type);
    }

    private Name syntheticName(JCTree.JCClassDecl jCClassDecl, String str) {
        Name nameFromString = this.names.fromString(this.target.syntheticNameChar() + str);
        while (jCClassDecl.sym.members().findFirst(nameFromString) != null) {
            nameFromString = this.names.fromString(nameFromString + "" + this.target.syntheticNameChar());
        }
        return nameFromString;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x001d  */
    /* JADX WARN: Code duplicated, block: B:17:0x0020  */
    /* JADX WARN: Code duplicated, block: B:19:0x0023  */
    private static JCTree.Tag treeTag(Symbol.OperatorSymbol operatorSymbol) {
        int i = operatorSymbol.opcode;
        if (i != 256) {
            switch (i) {
                case 96:
                case 97:
                case 98:
                case 99:
                    break;
                case 100:
                case 101:
                case 102:
                case 103:
                    return JCTree.Tag.MINUS_ASG;
                case 104:
                case 105:
                case 106:
                case 107:
                    return JCTree.Tag.MUL_ASG;
                case 108:
                case 109:
                case 110:
                case 111:
                    return JCTree.Tag.DIV_ASG;
                case 112:
                case 113:
                case 114:
                case 115:
                    return JCTree.Tag.MOD_ASG;
                default:
                    switch (i) {
                        case 120:
                        case 121:
                            return JCTree.Tag.SL_ASG;
                        case 122:
                        case 123:
                            return JCTree.Tag.SR_ASG;
                        case 124:
                        case 125:
                            return JCTree.Tag.USR_ASG;
                        case 126:
                        case 127:
                            return JCTree.Tag.BITAND_ASG;
                        case 128:
                        case 129:
                            return JCTree.Tag.BITOR_ASG;
                        case 130:
                        case 131:
                            return JCTree.Tag.BITXOR_ASG;
                        default:
                            switch (i) {
                                case ByteCodes.ishll /* 270 */:
                                case ByteCodes.lshll /* 271 */:
                                    return JCTree.Tag.SL_ASG;
                                case ByteCodes.ishrl /* 272 */:
                                case ByteCodes.lshrl /* 273 */:
                                    return JCTree.Tag.SR_ASG;
                                case 274:
                                case 275:
                                    return JCTree.Tag.USR_ASG;
                                default:
                                    x1f.a();
                                    return null;
                            }
                    }
            }
        }
        return JCTree.Tag.PLUS_ASG;
    }

    public static /* synthetic */ boolean u(Symbol.RecordComponent recordComponent) {
        return (recordComponent.accessor.flags() & 16777216) != 0;
    }

    private boolean useClone() {
        try {
            return this.syms.objectType.tsym.members().findFirst(this.names.clone) != null;
        } catch (Symbol.CompletionFailure unused) {
        }
    }

    private void visitArrayForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        make_at(jCEnhancedForLoop.expr.pos());
        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4096L, this.names.fromString("arr" + this.target.syntheticNameChar()), jCEnhancedForLoop.expr.type, this.currentMethodSym);
        JCTree.JCVariableDecl jCVariableDeclVarDef = this.make.VarDef(varSymbol, jCEnhancedForLoop.expr);
        Symbol.VarSymbol varSymbol2 = new Symbol.VarSymbol(4096L, this.names.fromString("len" + this.target.syntheticNameChar()), this.syms.intType, this.currentMethodSym);
        TreeMaker treeMaker = this.make;
        JCTree.JCVariableDecl jCVariableDeclVarDef2 = treeMaker.VarDef(varSymbol2, treeMaker.Select(treeMaker.Ident(varSymbol), this.syms.lengthVar));
        Symbol.VarSymbol varSymbol3 = new Symbol.VarSymbol(4096L, this.names.fromString("i" + this.target.syntheticNameChar()), this.syms.intType, this.currentMethodSym);
        TreeMaker treeMaker2 = this.make;
        JCTree.JCVariableDecl jCVariableDeclVarDef3 = treeMaker2.VarDef(varSymbol3, treeMaker2.Literal(TypeTag.INT, 0));
        JCTree.JCExpression jCExpression = jCVariableDeclVarDef3.init;
        Type typeConstType = this.syms.intType.constType(0);
        jCVariableDeclVarDef3.type = typeConstType;
        jCExpression.type = typeConstType;
        List<JCTree.JCStatement> listOf = List.of(jCVariableDeclVarDef, jCVariableDeclVarDef2, jCVariableDeclVarDef3);
        JCTree.JCBinary jCBinaryMakeBinary = makeBinary(JCTree.Tag.LT, this.make.Ident(varSymbol3), this.make.Ident(varSymbol2));
        TreeMaker treeMaker3 = this.make;
        JCTree.JCExpressionStatement jCExpressionStatementExec = treeMaker3.Exec(makeUnary(JCTree.Tag.PREINC, treeMaker3.Ident(varSymbol3)));
        Type typeElemtype = this.types.elemtype(jCEnhancedForLoop.expr.type);
        TreeMaker treeMaker4 = this.make;
        JCTree.JCExpression type = treeMaker4.Indexed(treeMaker4.Ident(varSymbol), this.make.Ident(varSymbol3)).setType(typeElemtype);
        TreeMaker treeMaker5 = this.make;
        JCTree.JCVariableDecl jCVariableDecl = jCEnhancedForLoop.var;
        JCTree.JCVariableDecl jCVariableDecl2 = (JCTree.JCVariableDecl) treeMaker5.VarDef(jCVariableDecl.mods, jCVariableDecl.name, jCVariableDecl.vartype, type).setType(jCEnhancedForLoop.var.type);
        jCVariableDecl2.sym = jCEnhancedForLoop.var.sym;
        JCTree.JCBlock jCBlockBlock = this.make.Block(0L, List.of((JCTree.JCStatement) jCVariableDecl2, jCEnhancedForLoop.body));
        JCTree jCTreeTranslate = translate(this.make.ForLoop(listOf, jCBinaryMakeBinary, List.of(jCExpressionStatementExec), jCBlockBlock));
        this.result = jCTreeTranslate;
        patchTargets(jCBlockBlock, jCEnhancedForLoop, jCTreeTranslate);
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [A, com.sun.tools.javac.tree.JCTree$JCConstantCaseLabel] */
    private JCTree visitBoxedPrimitiveSwitch(JCTree jCTree, JCTree.JCExpression jCExpression, List<JCTree.JCCase> list) {
        JCTree.JCExpression jCExpressionUnbox;
        if (list.stream().anyMatch(new Predicate() { // from class: hj9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TreeInfo.isNullCaseLabel(((JCTree.JCCase) obj).labels.head);
            }
        })) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            JCTree.JCCase jCCase = null;
            for (JCTree.JCCase jCCase2 : list) {
                if (TreeInfo.isNullCaseLabel(jCCase2.labels.head)) {
                    jCCase = jCCase2;
                } else if (!jCCase2.labels.head.hasTag(JCTree.Tag.DEFAULTCASELABEL)) {
                    Integer num = (Integer) ((JCTree.JCConstantCaseLabel) jCCase2.labels.head).expr.type.constValue();
                    num.intValue();
                    linkedHashSet.add(num);
                }
            }
            Assert.checkNonNull(jCCase);
            int iIntValue = linkedHashSet.isEmpty() ? 0 : ((Integer) linkedHashSet.iterator().next()).intValue();
            while (linkedHashSet.contains(Integer.valueOf(iIntValue))) {
                iIntValue++;
            }
            linkedHashSet.add(Integer.valueOf(iIntValue));
            jCCase.labels.head = this.make.ConstantCaseLabel(makeLit(this.syms.intType, Integer.valueOf(iIntValue)));
            int i = iIntValue;
            while (linkedHashSet.contains(Integer.valueOf(i))) {
                i++;
            }
            Names names = this.names;
            StringBuilder sb = new StringBuilder("s");
            int i2 = this.variableIndex;
            this.variableIndex = i2 + 1;
            sb.append(i2);
            sb.append(this.target.syntheticNameChar());
            Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4112L, names.fromString(sb.toString()), jCExpression.type, this.currentMethodSym);
            JCTree.JCStatement type = this.make.at(jCTree.pos()).VarDef(varSymbol, jCExpression).setType(varSymbol.type);
            TreeMaker treeMaker = this.make;
            JCTree.Tag tag = JCTree.Tag.NE;
            JCTree.JCExpression type2 = treeMaker.Conditional(makeBinary(tag, unbox(treeMaker.Ident(varSymbol), this.syms.intType), makeLit(this.syms.intType, Integer.valueOf(iIntValue))), unbox(this.make.Ident(varSymbol), this.syms.intType), makeLit(this.syms.intType, Integer.valueOf(i))).setType((Type) this.syms.intType);
            TreeMaker treeMaker2 = this.make;
            jCExpressionUnbox = this.make.LetExpr(List.of(type), treeMaker2.Conditional(makeBinary(tag, treeMaker2.Ident(varSymbol), makeNull()), type2, makeLit(this.syms.intType, Integer.valueOf(iIntValue))).setType((Type) this.syms.intType)).setType((Type) this.syms.intType);
        } else {
            jCExpressionUnbox = unbox(jCExpression, this.syms.intType);
        }
        if (jCTree.hasTag(JCTree.Tag.SWITCH)) {
            ((JCTree.JCSwitch) jCTree).selector = jCExpressionUnbox;
            return jCTree;
        }
        ((JCTree.JCSwitchExpression) jCTree).selector = jCExpressionUnbox;
        return jCTree;
    }

    private void visitEnumConstantDef(JCTree.JCVariableDecl jCVariableDecl, int i) {
        JCTree.JCNewClass jCNewClass = (JCTree.JCNewClass) jCVariableDecl.init;
        jCNewClass.args = jCNewClass.args.prepend(makeLit(this.syms.intType, Integer.valueOf(i))).prepend(makeLit(this.syms.stringType, jCVariableDecl.name.toString()));
    }

    /* JADX WARN: Code duplicated, block: B:13:0x008b  */
    /* JADX WARN: Multi-variable type inference failed */
    private void visitEnumDef(JCTree.JCClassDecl jCClassDecl) {
        boolean z;
        List<JCTree.JCStatement> listOf;
        make_at(jCClassDecl.pos());
        if (jCClassDecl.extending == null) {
            jCClassDecl.extending = this.make.Type(this.types.supertype(jCClassDecl.type));
        }
        JCTree.JCExpression type = classOfType(jCClassDecl.sym.type, jCClassDecl.pos()).setType(this.types.erasure(this.syms.classType));
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        ListBuffer listBuffer3 = new ListBuffer();
        int i = 0;
        for (List list = jCClassDecl.defs; list.nonEmpty(); list = list.tail) {
            if (((JCTree) list.head).hasTag(JCTree.Tag.VARDEF)) {
                A a = list.head;
                if ((((JCTree.JCVariableDecl) a).mods.flags & 16384) != 0) {
                    JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) a;
                    visitEnumConstantDef(jCVariableDecl, i);
                    listBuffer.append(this.make.QualIdent(jCVariableDecl.sym));
                    listBuffer2.append(jCVariableDecl);
                    i++;
                } else {
                    listBuffer3.append((JCTree) list.head);
                }
            } else {
                listBuffer3.append((JCTree) list.head);
            }
        }
        Name nameSyntheticName = syntheticName(jCClassDecl, "VALUES");
        Type.ArrayType arrayType = new Type.ArrayType(this.types.erasure(jCClassDecl.type), this.syms.arrayClass);
        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4122L, nameSyntheticName, arrayType, jCClassDecl.type.tsym);
        TreeMaker treeMaker = this.make;
        JCTree.JCNewArray jCNewArrayNewArray = treeMaker.NewArray(treeMaker.Type(this.types.erasure(jCClassDecl.type)), List.nil(), listBuffer.toList());
        jCNewArrayNewArray.type = arrayType;
        Symbol.MethodSymbol methodSymbol = new Symbol.MethodSymbol(4106L, syntheticName(jCClassDecl, CompoundEntry.NAME_values), new Type.MethodType(List.nil(), arrayType, List.nil(), jCClassDecl.type.tsym), jCClassDecl.type.tsym);
        TreeMaker treeMaker2 = this.make;
        listBuffer2.append(treeMaker2.MethodDef(methodSymbol, treeMaker2.Block(0L, List.of(treeMaker2.Return(jCNewArrayNewArray)))));
        jCClassDecl.sym.members().enter(methodSymbol);
        TreeMaker treeMaker3 = this.make;
        listBuffer2.append(treeMaker3.VarDef(varSymbol, treeMaker3.App(treeMaker3.QualIdent(methodSymbol))));
        jCClassDecl.sym.members().enter(varSymbol);
        Symbol.MethodSymbol methodSymbolLookupMethod = lookupMethod(jCClassDecl.pos(), this.names.values, jCClassDecl.type, List.nil());
        if (useClone()) {
            TreeMaker treeMaker4 = this.make;
            Type typeMo73getReturnType = methodSymbolLookupMethod.type.mo73getReturnType();
            TreeMaker treeMaker5 = this.make;
            listOf = List.of(this.make.Return(treeMaker4.TypeCast(typeMo73getReturnType, treeMaker5.App(treeMaker5.Select(treeMaker5.Ident(varSymbol), this.syms.arrayCloneMethod)))));
            z = true;
        } else {
            Symbol.VarSymbol varSymbol2 = new Symbol.VarSymbol(4112L, syntheticName(jCClassDecl, Constants.EXSLT_ELEMNAME_FUNCRESULT_STRING), arrayType, methodSymbolLookupMethod);
            TreeMaker treeMaker6 = this.make;
            JCTree.JCExpression jCExpressionType = treeMaker6.Type(this.types.erasure(jCClassDecl.type));
            TreeMaker treeMaker7 = this.make;
            z = true;
            JCTree.JCNewArray jCNewArrayNewArray2 = treeMaker6.NewArray(jCExpressionType, List.of(treeMaker7.Select(treeMaker7.Ident(varSymbol), this.syms.lengthVar)), null);
            jCNewArrayNewArray2.type = arrayType;
            JCTree.JCVariableDecl jCVariableDeclVarDef = this.make.VarDef(varSymbol2, jCNewArrayNewArray2);
            if (this.systemArraycopyMethod == null) {
                Name nameFromString = this.names.fromString("arraycopy");
                Symtab symtab = this.syms;
                Type type2 = symtab.objectType;
                Type.JCPrimitiveType jCPrimitiveType = symtab.intType;
                this.systemArraycopyMethod = new Symbol.MethodSymbol(9L, nameFromString, new Type.MethodType(List.of(type2, (Type) jCPrimitiveType, type2, jCPrimitiveType, jCPrimitiveType), this.syms.voidType, List.nil(), this.syms.methodClass), this.syms.systemType.tsym);
            }
            TreeMaker treeMaker8 = this.make;
            JCTree.JCFieldAccess jCFieldAccessSelect = treeMaker8.Select(treeMaker8.Ident(this.syms.systemType.tsym), this.systemArraycopyMethod);
            JCTree.JCIdent jCIdentIdent = this.make.Ident(varSymbol);
            JCTree.JCLiteral jCLiteralLiteral = this.make.Literal(0);
            JCTree.JCIdent jCIdentIdent2 = this.make.Ident(varSymbol2);
            JCTree.JCLiteral jCLiteralLiteral2 = this.make.Literal(0);
            TreeMaker treeMaker9 = this.make;
            JCTree.JCExpressionStatement jCExpressionStatementExec = treeMaker8.Exec(treeMaker8.App(jCFieldAccessSelect, List.of(jCIdentIdent, (JCTree.JCIdent) jCLiteralLiteral, jCIdentIdent2, (JCTree.JCIdent[]) new JCTree.JCExpression[]{jCLiteralLiteral2, treeMaker9.Select(treeMaker9.Ident(varSymbol), this.syms.lengthVar)})));
            TreeMaker treeMaker10 = this.make;
            listOf = List.of((JCTree.JCReturn) jCVariableDeclVarDef, (JCTree.JCReturn) jCExpressionStatementExec, treeMaker10.Return(treeMaker10.Ident(varSymbol2)));
        }
        TreeMaker treeMaker11 = this.make;
        JCTree.JCMethodDecl jCMethodDeclMethodDef = treeMaker11.MethodDef(methodSymbolLookupMethod, treeMaker11.Block(0L, listOf));
        listBuffer2.append(jCMethodDeclMethodDef);
        if (this.debugLower) {
            System.err.println(jCClassDecl.sym + ".valuesDef = " + jCMethodDeclMethodDef);
        }
        Symbol.MethodSymbol methodSymbolLookupMethod2 = lookupMethod(jCClassDecl.pos(), this.names.valueOf, jCClassDecl.sym.type, List.of(this.syms.stringType));
        if ((methodSymbolLookupMethod2.flags() & 8) == 0) {
            z = false;
        }
        Assert.check(z);
        JCTree.JCIdent jCIdentIdent3 = this.make.Ident(methodSymbolLookupMethod2.params.head);
        TreeMaker treeMaker12 = this.make;
        JCTree.JCReturn jCReturnReturn = treeMaker12.Return(treeMaker12.TypeCast(jCClassDecl.sym.type, makeCall(treeMaker12.Ident(this.syms.enumSym), this.names.valueOf, List.of((JCTree.JCIdent) type, jCIdentIdent3))));
        TreeMaker treeMaker13 = this.make;
        JCTree.JCMethodDecl jCMethodDeclMethodDef2 = treeMaker13.MethodDef(methodSymbolLookupMethod2, treeMaker13.Block(0L, List.of(jCReturnReturn)));
        jCIdentIdent3.sym = jCMethodDeclMethodDef2.params.head.sym;
        if (this.debugLower) {
            System.err.println(jCClassDecl.sym + ".valueOf = " + jCMethodDeclMethodDef2);
        }
        listBuffer2.append(jCMethodDeclMethodDef2);
        listBuffer2.appendList(listBuffer3.toList());
        jCClassDecl.defs = listBuffer2.toList();
    }

    private void visitIterableForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        make_at(jCEnhancedForLoop.expr.pos());
        Type typeErasure = this.syms.objectType;
        Types types = this.types;
        Type typeAsSuper = types.asSuper(types.cvarUpperBound(jCEnhancedForLoop.expr.type), this.syms.iterableType.tsym);
        if (typeAsSuper.getTypeArguments().nonEmpty()) {
            typeErasure = this.types.erasure(typeAsSuper.getTypeArguments().head);
        }
        JCTree.JCExpression jCExpression = jCEnhancedForLoop.expr;
        Types types2 = this.types;
        jCExpression.type = types2.erasure(types2.skipTypeVars(jCExpression.type, false));
        JCTree.JCExpression jCExpressionCoerce = this.transTypes.coerce(this.attrEnv, jCEnhancedForLoop.expr, this.types.erasure(typeAsSuper));
        jCEnhancedForLoop.expr = jCExpressionCoerce;
        Symbol.MethodSymbol methodSymbolLookupMethod = lookupMethod(jCExpressionCoerce.pos(), this.names.iterator, jCEnhancedForLoop.expr.type, List.nil());
        Types types3 = this.types;
        Assert.check(types3.isSameType(types3.erasure(types3.asSuper(methodSymbolLookupMethod.type.mo73getReturnType(), this.syms.iteratorType.tsym)), this.types.erasure(this.syms.iteratorType)));
        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4096L, this.names.fromString("i" + this.target.syntheticNameChar()), this.types.erasure(this.syms.iteratorType), this.currentMethodSym);
        TreeMaker treeMaker = this.make;
        JCTree.JCVariableDecl jCVariableDeclVarDef = treeMaker.VarDef(varSymbol, treeMaker.App(treeMaker.Select(jCEnhancedForLoop.expr, methodSymbolLookupMethod).setType(this.types.erasure(methodSymbolLookupMethod.type))));
        Symbol.MethodSymbol methodSymbolLookupMethod2 = lookupMethod(jCEnhancedForLoop.expr.pos(), this.names.hasNext, varSymbol.type, List.nil());
        TreeMaker treeMaker2 = this.make;
        JCTree.JCMethodInvocation jCMethodInvocationApp = treeMaker2.App(treeMaker2.Select(treeMaker2.Ident(varSymbol), methodSymbolLookupMethod2));
        Symbol.MethodSymbol methodSymbolLookupMethod3 = lookupMethod(jCEnhancedForLoop.expr.pos(), this.names.next, varSymbol.type, List.nil());
        TreeMaker treeMaker3 = this.make;
        JCTree.JCMethodInvocation jCMethodInvocationApp2 = treeMaker3.App(treeMaker3.Select(treeMaker3.Ident(varSymbol), methodSymbolLookupMethod3));
        boolean zIsPrimitive = jCEnhancedForLoop.var.type.isPrimitive();
        TreeMaker treeMaker4 = this.make;
        JCTree.JCTypeCast jCTypeCastTypeCast = zIsPrimitive ? treeMaker4.TypeCast(this.types.cvarUpperBound(typeErasure), jCMethodInvocationApp2) : treeMaker4.TypeCast(jCEnhancedForLoop.var.type, jCMethodInvocationApp2);
        TreeMaker treeMaker5 = this.make;
        JCTree.JCVariableDecl jCVariableDecl = jCEnhancedForLoop.var;
        JCTree.JCVariableDecl jCVariableDecl2 = (JCTree.JCVariableDecl) treeMaker5.VarDef(jCVariableDecl.mods, jCVariableDecl.name, jCVariableDecl.vartype, jCTypeCastTypeCast).setType(jCEnhancedForLoop.var.type);
        jCVariableDecl2.sym = jCEnhancedForLoop.var.sym;
        JCTree.JCBlock jCBlockBlock = this.make.Block(0L, List.of((JCTree.JCStatement) jCVariableDecl2, jCEnhancedForLoop.body));
        jCBlockBlock.bracePos = TreeInfo.endPos(jCEnhancedForLoop.body);
        JCTree jCTreeTranslate = translate(this.make.ForLoop(List.of(jCVariableDeclVarDef), jCMethodInvocationApp, List.nil(), jCBlockBlock));
        this.result = jCTreeTranslate;
        patchTargets(jCBlockBlock, jCEnhancedForLoop, jCTreeTranslate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void visitMethodDefInternal(JCTree.JCMethodDecl jCMethodDecl) {
        Lower lower;
        if (jCMethodDecl.name == this.names.init && !this.currentClass.isStatic() && (this.currentClass.isInner() || this.currentClass.isDirectlyOrIndirectlyLocal())) {
            Symbol.MethodSymbol methodSymbol = jCMethodDecl.sym;
            Map<Symbol, Symbol> map = this.proxies;
            this.proxies = new HashMap(this.proxies);
            List<Symbol.VarSymbol> list = this.outerThisStack;
            List listFreevars = freevars(this.currentClass);
            JCTree.JCVariableDecl jCVariableDeclOuterThisDef = this.currentClass.hasOuterInstance() ? outerThisDef(jCMethodDecl.pos, methodSymbol) : null;
            final Lower lower2 = this;
            List<JCTree.JCVariableDecl> listFreevarDefs = lower2.freevarDefs(jCMethodDecl.pos, listFreevars, methodSymbol, 8589934592L);
            jCMethodDecl.restype = (JCTree.JCExpression) lower2.translate(jCMethodDecl.restype);
            jCMethodDecl.params = lower2.translateVarDefs(jCMethodDecl.params);
            jCMethodDecl.thrown = lower2.translate(jCMethodDecl.thrown);
            if (jCMethodDecl.body == null) {
                lower2.result = jCMethodDecl;
                return;
            }
            jCMethodDecl.params = jCMethodDecl.params.appendList(listFreevarDefs);
            if (lower2.currentClass.hasOuterInstance()) {
                jCMethodDecl.params = jCMethodDecl.params.prepend(jCVariableDeclOuterThisDef);
            }
            boolean zHasConstructorCall = TreeInfo.hasConstructorCall(jCMethodDecl, lower2.names._super);
            ListBuffer listBuffer = new ListBuffer();
            if (listFreevars.nonEmpty()) {
                List<Type> listNil = List.nil();
                while (listFreevars.nonEmpty()) {
                    methodSymbol.capturedLocals = methodSymbol.capturedLocals.prepend((Symbol.VarSymbol) lower2.proxies.get(listFreevars.head));
                    if (zHasConstructorCall) {
                        listBuffer = listBuffer.prepend(lower2.initField(jCMethodDecl.body.pos, lower2.proxies.get(listFreevars.head), map.get(listFreevars.head)));
                    }
                    listNil = listNil.prepend(((Symbol.VarSymbol) listFreevars.head).erasure(lower2.types));
                    listFreevars = listFreevars.tail;
                }
                Type typeErasure = methodSymbol.erasure(lower2.types);
                methodSymbol.erasure_field = new Type.MethodType(typeErasure.mo71getParameterTypes().appendList(listNil), typeErasure.mo73getReturnType(), typeErasure.mo74getThrownTypes(), lower2.syms.methodClass);
            }
            JCTree.JCBlock jCBlock = jCMethodDecl.body;
            jCBlock.stats = lower2.translate(jCBlock.stats);
            if (listBuffer.nonEmpty()) {
                final List list2 = listBuffer.toList();
                TreeInfo.mapSuperCalls(jCMethodDecl.body, new Function() { // from class: fj9
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return this.b.make.Block(0L, list2.append((JCTree.JCExpressionStatement) obj));
                    }
                });
            }
            lower2.proxies = map;
            lower2.outerThisStack = list;
            lower = lower2;
        } else {
            Lower lower3 = this;
            super.visitMethodDef(jCMethodDecl);
            lower = lower3;
        }
        if (jCMethodDecl.name == lower.names.init) {
            long j = jCMethodDecl.sym.flags_field;
            if ((j & 2251799813685248L) != 0 || (j & 2305843077933170688L) == 2305843077933170688L) {
                ListBuffer<Symbol.VarSymbol> listBuffer2 = new ListBuffer();
                for (Symbol symbol : lower.currentClass.getEnclosedElements()) {
                    if (symbol.kind == Kinds.Kind.VAR && (symbol.flags() & Flags.RECORD) != 0) {
                        listBuffer2.append((Symbol.VarSymbol) symbol);
                    }
                }
                for (final Symbol.VarSymbol varSymbol : listBuffer2) {
                    if ((varSymbol.flags_field & 2251799813685248L) != 0) {
                        Symbol.VarSymbol varSymbol2 = jCMethodDecl.params.stream().filter(new Predicate() { // from class: gj9
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                return Lower.q(varSymbol, (JCTree.JCVariableDecl) obj);
                            }
                        }).findFirst().get().sym;
                        lower.make.at(jCMethodDecl.pos);
                        JCTree.JCBlock jCBlock2 = jCMethodDecl.body;
                        List<JCTree.JCStatement> list3 = jCBlock2.stats;
                        TreeMaker treeMaker = lower.make;
                        jCBlock2.stats = list3.append(treeMaker.Exec(treeMaker.Assign(treeMaker.Select(treeMaker.This(varSymbol.owner.erasure(lower.types)), varSymbol), lower.make.Ident(varSymbol2)).setType(varSymbol.erasure(lower.types))));
                        varSymbol.flags_field &= -2251799813685249L;
                    }
                }
            }
        }
        lower.result = jCMethodDecl;
    }

    private void visitRecordDef(JCTree.JCClassDecl jCClassDecl) {
        make_at(jCClassDecl.pos());
        List<Symbol.VarSymbol> listRecordVars = recordVars(jCClassDecl.type);
        Symbol.MethodHandleSymbol[] methodHandleSymbolArr = new Symbol.MethodHandleSymbol[listRecordVars.size()];
        int i = 0;
        for (Symbol.VarSymbol varSymbol : listRecordVars) {
            Symbol symbol = varSymbol.owner;
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            if (symbol != classSymbol) {
                varSymbol = new Symbol.VarSymbol(varSymbol.flags_field, varSymbol.name, varSymbol.type, classSymbol);
            }
            methodHandleSymbolArr[i] = varSymbol.asMethodHandle(true);
            i++;
        }
        List<JCTree> listAppendList = jCClassDecl.defs.appendList(generateMandatedAccessors(jCClassDecl));
        jCClassDecl.defs = listAppendList;
        jCClassDecl.defs = listAppendList.appendList(List.of(generateRecordMethod(jCClassDecl, this.names.toString, listRecordVars, methodHandleSymbolArr), generateRecordMethod(jCClassDecl, this.names.hashCode, listRecordVars, methodHandleSymbolArr), generateRecordMethod(jCClassDecl, this.names.equals, listRecordVars, methodHandleSymbolArr)));
    }

    public JCTree.JCExpression abstractLval(JCTree.JCExpression jCExpression, final TreeBuilder treeBuilder) {
        JCTree.JCExpression jCExpressionSkipParens = TreeInfo.skipParens(jCExpression);
        int i = AnonymousClass4.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpressionSkipParens.getTag().ordinal()];
        if (i == 2) {
            return treeBuilder.build(jCExpressionSkipParens);
        }
        if (i == 3) {
            final JCTree.JCFieldAccess jCFieldAccess = (JCTree.JCFieldAccess) jCExpressionSkipParens;
            Symbol symbol = TreeInfo.symbol(jCFieldAccess.selected);
            return (symbol == null || symbol.kind != Kinds.Kind.TYP) ? abstractRval(jCFieldAccess.selected, new TreeBuilder() { // from class: com.sun.tools.javac.comp.k1
                @Override // com.sun.tools.javac.comp.Lower.TreeBuilder
                public final JCTree.JCExpression build(JCTree.JCExpression jCExpression2) {
                    return treeBuilder.build(this.a.make.Select(jCExpression2, jCFieldAccess.sym));
                }
            }) : treeBuilder.build(jCExpressionSkipParens);
        }
        if (i == 4) {
            final JCTree.JCArrayAccess jCArrayAccess = (JCTree.JCArrayAccess) jCExpressionSkipParens;
            return abstractRval(jCArrayAccess.indexed, new TreeBuilder() { // from class: com.sun.tools.javac.comp.l1
                @Override // com.sun.tools.javac.comp.Lower.TreeBuilder
                public final JCTree.JCExpression build(JCTree.JCExpression jCExpression2) {
                    return Lower.o(this.a, jCArrayAccess, treeBuilder, jCExpression2);
                }
            });
        }
        if (i == 5) {
            return abstractLval(((JCTree.JCTypeCast) jCExpressionSkipParens).expr, treeBuilder);
        }
        x01.a(jCExpressionSkipParens);
        return null;
    }

    public JCTree.JCExpression abstractRval(JCTree.JCExpression jCExpression, Type type, TreeBuilder treeBuilder) {
        JCTree.JCExpression jCExpressionSkipParens = TreeInfo.skipParens(jCExpression);
        int i = AnonymousClass4.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpressionSkipParens.getTag().ordinal()];
        if (i == 1) {
            return treeBuilder.build(jCExpressionSkipParens);
        }
        if (i == 2) {
            JCTree.JCIdent jCIdent = (JCTree.JCIdent) jCExpressionSkipParens;
            if ((jCIdent.sym.flags() & 16) != 0 && jCIdent.sym.owner.kind == Kinds.Kind.MTH) {
                return treeBuilder.build(jCExpressionSkipParens);
            }
        }
        Name name = TreeInfo.name(jCExpressionSkipParens);
        Names names = this.names;
        if (name == names._super || name == names._this) {
            return treeBuilder.build(jCExpressionSkipParens);
        }
        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4112L, names.fromString(this.target.syntheticNameChar() + "" + jCExpressionSkipParens.hashCode()), type, this.currentMethodSym);
        JCTree.JCVariableDecl jCVariableDeclVarDef = this.make.VarDef(varSymbol, convert(jCExpressionSkipParens, type));
        JCTree.JCExpression jCExpressionBuild = treeBuilder.build(this.make.Ident(varSymbol));
        JCTree.LetExpr LetExpr = this.make.LetExpr(jCVariableDeclVarDef, jCExpressionBuild);
        LetExpr.type = jCExpressionBuild.type;
        return LetExpr;
    }

    public JCTree.JCExpression access(Symbol symbol, JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2, boolean z) {
        Kinds.Kind kind;
        boolean z2;
        List<JCTree.JCExpression> listPrepend;
        Object constValue;
        Symbol symbol2 = symbol;
        JCTree.JCExpression jCExpressionIdent = jCExpression;
        while (true) {
            Kinds.Kind kind2 = symbol2.kind;
            kind = Kinds.Kind.VAR;
            z2 = false;
            if (kind2 != kind) {
                break;
            }
            Symbol symbol3 = symbol2.owner;
            if (symbol3.kind != Kinds.Kind.MTH || symbol3.enclClass() == this.currentClass) {
                break;
            }
            Object constValue2 = ((Symbol.VarSymbol) symbol2).getConstValue();
            if (constValue2 != null) {
                this.make.at(jCExpressionIdent.pos);
                return makeLit(symbol2.type, constValue2);
            }
            symbol2 = this.proxies.get(symbol2);
            if (symbol2 != null && (symbol2.flags_field & 16) != 0) {
                z2 = true;
            }
            Assert.check(z2);
            jCExpressionIdent = this.make.at(jCExpressionIdent.pos).Ident(symbol2);
        }
        JCTree.JCExpression jCExpressionQualIdent = null;
        JCTree.JCExpression jCExpressionMakeOwnerThis = jCExpressionIdent.hasTag(JCTree.Tag.SELECT) ? ((JCTree.JCFieldAccess) jCExpressionIdent).selected : null;
        int i = AnonymousClass4.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[symbol2.kind.ordinal()];
        if (i == 1 || i == 2) {
            if (symbol2.owner.kind == Kinds.Kind.TYP) {
                boolean z3 = (z && !needsPrivateAccess(symbol2)) || needsProtectedAccess(symbol2, jCExpressionIdent);
                boolean z4 = z3 || needsPrivateAccess(symbol2);
                if (jCExpressionMakeOwnerThis == null && symbol2.owner != this.syms.predefClass && !symbol2.isMemberOf(this.currentClass, this.types)) {
                    z2 = true;
                }
                if (z4 || z2) {
                    this.make.at(jCExpressionIdent.pos);
                    if (symbol2.kind == kind && (constValue = ((Symbol.VarSymbol) symbol2).getConstValue()) != null) {
                        addPrunedInfo(jCExpressionIdent);
                        return makeLit(symbol2.type, constValue);
                    }
                    if (z4) {
                        List<JCTree.JCExpression> listNil = List.nil();
                        if ((symbol2.flags() & 8) == 0) {
                            if (jCExpressionMakeOwnerThis == null) {
                                jCExpressionMakeOwnerThis = makeOwnerThis(jCExpressionIdent.pos(), symbol2, true);
                            }
                            listPrepend = listNil.prepend(jCExpressionMakeOwnerThis);
                        } else {
                            jCExpressionQualIdent = jCExpressionMakeOwnerThis;
                            listPrepend = listNil;
                        }
                        Symbol.MethodSymbol methodSymbolAccessSymbol = accessSymbol(symbol2, jCExpressionIdent, jCExpression2, z3, z);
                        TreeMaker treeMaker = this.make;
                        if (jCExpressionQualIdent == null) {
                            jCExpressionQualIdent = treeMaker.QualIdent(methodSymbolAccessSymbol.owner);
                        }
                        return this.make.App(treeMaker.Select(jCExpressionQualIdent, methodSymbolAccessSymbol), listPrepend);
                    }
                    if (z2) {
                        return this.make.at(jCExpressionIdent.pos).Select(accessBase(jCExpressionIdent.pos(), symbol2), symbol2).setType(jCExpressionIdent.type);
                    }
                }
            }
        } else if (i == 3 && symbol2.owner.kind != Kinds.Kind.PCK) {
            Name nameShortName = Convert.shortName(symbol2.flatName());
            while (jCExpressionMakeOwnerThis != null && TreeInfo.symbol(jCExpressionMakeOwnerThis) != null && TreeInfo.symbol(jCExpressionMakeOwnerThis).kind != Kinds.Kind.PCK) {
                jCExpressionMakeOwnerThis = jCExpressionMakeOwnerThis.hasTag(JCTree.Tag.SELECT) ? ((JCTree.JCFieldAccess) jCExpressionMakeOwnerThis).selected : null;
            }
            if (jCExpressionIdent.hasTag(JCTree.Tag.IDENT)) {
                ((JCTree.JCIdent) jCExpressionIdent).name = nameShortName;
                return jCExpressionIdent;
            }
            if (jCExpressionMakeOwnerThis == null) {
                JCTree.JCIdent jCIdentIdent = this.make.at(jCExpressionIdent.pos).Ident(symbol2);
                jCIdentIdent.name = nameShortName;
                return jCIdentIdent;
            }
            JCTree.JCFieldAccess jCFieldAccess = (JCTree.JCFieldAccess) jCExpressionIdent;
            jCFieldAccess.selected = jCExpressionMakeOwnerThis;
            jCFieldAccess.name = nameShortName;
            return jCExpressionIdent;
        }
        return jCExpressionIdent;
    }

    public JCTree.JCExpression accessBase(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
        return (symbol.flags() & 8) != 0 ? access(this.make.at(diagnosticPosition.getStartPosition()).QualIdent(symbol.owner)) : makeOwnerThis(diagnosticPosition, symbol, true);
    }

    public Symbol.ClassSymbol accessClass(Symbol symbol, boolean z, JCTree jCTree) {
        if (!z) {
            return symbol.owner.enclClass();
        }
        Symbol.ClassSymbol classSymbolEnclClass = this.currentClass;
        if (!jCTree.hasTag(JCTree.Tag.SELECT) || (symbol.flags() & 8) != 0) {
            while (!classSymbolEnclClass.isSubClass(symbol.owner, this.types)) {
                classSymbolEnclClass = classSymbolEnclClass.owner.enclClass();
            }
            return classSymbolEnclClass;
        }
        Symbol.TypeSymbol typeSymbol = ((JCTree.JCFieldAccess) jCTree).selected.type.tsym;
        while (!typeSymbol.isSubClass(classSymbolEnclClass, this.types)) {
            classSymbolEnclClass = classSymbolEnclClass.owner.enclClass();
        }
        return classSymbolEnclClass;
    }

    public Symbol accessConstructor(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
        if (!needsPrivateAccess(symbol)) {
            return symbol;
        }
        Symbol.ClassSymbol classSymbolEnclClass = symbol.owner.enclClass();
        Symbol.MethodSymbol methodSymbol = this.accessConstrs.get(symbol);
        if (methodSymbol != null) {
            return methodSymbol;
        }
        List<Type> listMo71getParameterTypes = symbol.type.mo71getParameterTypes();
        if ((classSymbolEnclClass.flags_field & 16384) != 0) {
            listMo71getParameterTypes = listMo71getParameterTypes.prepend(this.syms.intType).prepend(this.syms.stringType);
        }
        Symbol.MethodSymbol methodSymbol2 = new Symbol.MethodSymbol(4096L, this.names.init, new Type.MethodType(listMo71getParameterTypes.append(accessConstructorTag().erasure(this.types)), symbol.type.mo73getReturnType(), symbol.type.mo74getThrownTypes(), this.syms.methodClass), classSymbolEnclClass);
        enterSynthetic(diagnosticPosition, methodSymbol2, classSymbolEnclClass.members());
        this.accessConstrs.put(symbol, methodSymbol2);
        this.accessed.append(symbol);
        return methodSymbol2;
    }

    public JCTree accessConstructorDef(int i, Symbol symbol, Symbol.MethodSymbol methodSymbol) {
        this.make.at(i);
        JCTree.JCMethodDecl jCMethodDeclMethodDef = this.make.MethodDef(methodSymbol, methodSymbol.externalType(this.types), null);
        JCTree.JCIdent jCIdentIdent = this.make.Ident(this.names._this);
        jCIdentIdent.sym = symbol;
        jCIdentIdent.type = symbol.type;
        TreeMaker treeMaker = this.make;
        jCMethodDeclMethodDef.body = treeMaker.Block(0L, List.of(treeMaker.Call(treeMaker.App(jCIdentIdent, treeMaker.Idents(jCMethodDeclMethodDef.params.reverse().tail.reverse())))));
        return jCMethodDeclMethodDef;
    }

    public Symbol.ClassSymbol accessConstructorTag() {
        Symbol.ClassSymbol compiled;
        Symbol.ClassSymbol classSymbolOutermostClass = this.currentClass.outermostClass();
        Symbol.ModuleSymbol moduleSymbol = classSymbolOutermostClass.packge().modle;
        int i = 1;
        while (true) {
            compiled = this.chk.getCompiled(moduleSymbol, this.names.fromString("" + classSymbolOutermostClass.getQualifiedName() + this.target.syntheticNameChar() + i));
            if (compiled != null) {
                if (compiled.isAnonymous()) {
                    break;
                }
                i++;
            } else {
                compiled = makeEmptyClass(4104L, classSymbolOutermostClass).sym;
                break;
            }
        }
        this.accessConstrTags = this.accessConstrTags.prepend(compiled);
        return compiled;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v11, types: [A, com.sun.tools.javac.tree.JCTree$JCExpression] */
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
    public JCTree accessDef(int i, Symbol symbol, Symbol.MethodSymbol methodSymbol, int i2) {
        List<JCTree.JCExpression> listIdents;
        JCTree.JCExpression jCExpression;
        JCTree.JCStatement jCStatementCall;
        this.currentClass = symbol.owner.enclClass();
        this.make.at(i);
        JCTree.JCMethodDecl jCMethodDeclMethodDef = this.make.MethodDef(methodSymbol, null);
        Symbol symbol2 = this.actualSymbols.get(symbol);
        if (symbol2 == null) {
            symbol2 = symbol;
        }
        long jFlags = symbol2.flags() & 8;
        TreeMaker treeMaker = this.make;
        if (jFlags != 0) {
            JCTree.JCIdent jCIdentIdent = treeMaker.Ident(symbol2);
            listIdents = this.make.Idents(jCMethodDeclMethodDef.params);
            jCExpression = jCIdentIdent;
        } else {
            JCTree.JCExpression jCExpressionIdent = treeMaker.Ident(jCMethodDeclMethodDef.params.head);
            if (i2 % 2 != 0) {
                Types types = this.types;
                jCExpressionIdent.setType(types.erasure(types.supertype(symbol.owner.enclClass().type)));
            }
            JCTree.JCFieldAccess jCFieldAccessSelect = this.make.Select(jCExpressionIdent, symbol2);
            listIdents = this.make.Idents(jCMethodDeclMethodDef.params.tail);
            jCExpression = jCFieldAccessSelect;
        }
        if (symbol2.kind == Kinds.Kind.VAR) {
            int i3 = i2 - (i2 & 1);
            Symbol.OperatorSymbol.AccessCode fromCode = Symbol.OperatorSymbol.AccessCode.getFromCode(i3);
            JCTree jCTreeAssign = jCExpression;
            switch (AnonymousClass4.$SwitchMap$com$sun$tools$javac$code$Symbol$OperatorSymbol$AccessCode[fromCode.ordinal()]) {
                case 1:
                    break;
                case 2:
                    jCTreeAssign = this.make.Assign(jCExpression, listIdents.head);
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                    jCTreeAssign = makeUnary(fromCode.tag, jCExpression);
                    break;
                default:
                    TreeMaker treeMaker2 = this.make;
                    JCTree.Tag tag = JCTree.Tag.NO_TAG;
                    JCTree.JCAssignOp jCAssignOpAssignop = treeMaker2.Assignop(treeTag(binaryAccessOperator(i3, tag)), jCExpression, listIdents.head);
                    jCAssignOpAssignop.operator = binaryAccessOperator(i3, tag);
                    jCTreeAssign = jCAssignOpAssignop;
                    break;
            }
            jCStatementCall = this.make.Return(jCTreeAssign.setType(symbol2.type));
        } else {
            TreeMaker treeMaker3 = this.make;
            jCStatementCall = treeMaker3.Call(treeMaker3.App(jCExpression, listIdents));
        }
        jCMethodDeclMethodDef.body = this.make.Block(0L, List.of(jCStatementCall));
        for (List list = jCMethodDeclMethodDef.params; list.nonEmpty(); list = list.tail) {
            A a = list.head;
            ((JCTree.JCVariableDecl) a).vartype = access(((JCTree.JCVariableDecl) a).vartype);
        }
        jCMethodDeclMethodDef.restype = access(jCMethodDeclMethodDef.restype);
        for (List list2 = jCMethodDeclMethodDef.thrown; list2.nonEmpty(); list2 = list2.tail) {
            list2.head = access((JCTree.JCExpression) list2.head);
        }
        return jCMethodDeclMethodDef;
    }

    public Name accessName(int i, int i2) {
        return this.names.fromString("access" + this.target.syntheticNameChar() + i + (i2 / 10) + (i2 % 10));
    }

    public Symbol.MethodSymbol accessSymbol(Symbol symbol, JCTree jCTree, JCTree jCTree2, boolean z, boolean z2) {
        List<Type> listOf;
        Type typeErasure;
        List<Type> listNil;
        List<Type> listPrepend;
        int i;
        Symbol.ClassSymbol classSymbolAccessClass = (z2 && z) ? (Symbol.ClassSymbol) ((JCTree.JCFieldAccess) jCTree).selected.type.tsym : accessClass(symbol, z, jCTree);
        if (symbol.owner != classSymbolAccessClass) {
            Symbol symbolClone = symbol.clone(classSymbolAccessClass);
            this.actualSymbols.put(symbolClone, symbol);
            symbol = symbolClone;
        }
        Integer numValueOf = this.accessNums.get(symbol);
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(this.accessed.length());
            this.accessNums.put(symbol, numValueOf);
            this.accessSyms.put(symbol, new Symbol.MethodSymbol[Symbol.OperatorSymbol.AccessCode.numberOfAccessCodes]);
            this.accessed.append(symbol);
        }
        int i2 = AnonymousClass4.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[symbol.kind.ordinal()];
        if (i2 == 1) {
            int iAccessCode = accessCode(jCTree, jCTree2);
            if (iAccessCode >= Symbol.OperatorSymbol.AccessCode.FIRSTASGOP.code) {
                Symbol.OperatorSymbol operatorSymbolBinaryAccessOperator = binaryAccessOperator(iAccessCode, jCTree2.getTag());
                listOf = operatorSymbolBinaryAccessOperator.opcode == 256 ? List.of(this.syms.objectType) : operatorSymbolBinaryAccessOperator.type.mo71getParameterTypes().tail;
            } else {
                listOf = iAccessCode == Symbol.OperatorSymbol.AccessCode.ASSIGN.code ? List.of(symbol.erasure(this.types)) : List.nil();
            }
            typeErasure = symbol.erasure(this.types);
            listNil = List.nil();
            listPrepend = listOf;
            i = iAccessCode;
        } else {
            if (i2 != 2) {
                x1f.a();
                return null;
            }
            i = Symbol.OperatorSymbol.AccessCode.DEREF.code;
            listPrepend = symbol.erasure(this.types).mo71getParameterTypes();
            typeErasure = symbol.erasure(this.types).mo73getReturnType();
            listNil = symbol.type.mo74getThrownTypes();
        }
        if (z && z2) {
            i++;
        }
        if ((symbol.flags() & 8) == 0) {
            listPrepend = listPrepend.prepend(symbol.owner.erasure(this.types));
        }
        Symbol.MethodSymbol[] methodSymbolArr = this.accessSyms.get(symbol);
        Symbol.MethodSymbol methodSymbol = methodSymbolArr[i];
        if (methodSymbol != null) {
            return methodSymbol;
        }
        Symbol.MethodSymbol methodSymbol2 = new Symbol.MethodSymbol((classSymbolAccessClass.isInterface() ? 1 : 0) | 4104, accessName(numValueOf.intValue(), i), new Type.MethodType(listPrepend, typeErasure, listNil, this.syms.methodClass), classSymbolAccessClass);
        enterSynthetic(jCTree.pos(), methodSymbol2, classSymbolAccessClass.members());
        methodSymbolArr[i] = methodSymbol2;
        return methodSymbol2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x008c, code lost:
    
        if (r8 == false) goto L27;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public List<JCTree.JCExpression> boxArgs(List<Type> list, List<JCTree.JCExpression> list2, Type type) {
        if (!list.isEmpty()) {
            ListBuffer listBuffer = new ListBuffer();
            List list3 = list2;
            boolean z = false;
            List list4 = list;
            while (true) {
                boolean z2 = true;
                if (!list4.tail.nonEmpty()) {
                    break;
                }
                JCTree.JCExpression jCExpressionTranslate = translate((JCTree.JCExpression) list3.head, (Type) list4.head);
                if (jCExpressionTranslate == list3.head) {
                    z2 = false;
                }
                z |= z2;
                listBuffer.append(jCExpressionTranslate);
                list3 = list3.tail;
                list4 = list4.tail;
            }
            Type type2 = (Type) list4.head;
            if (type != null) {
                ListBuffer listBuffer2 = new ListBuffer();
                while (list3.nonEmpty()) {
                    listBuffer2.append(translate((JCTree.JCExpression) list3.head, type));
                    list3 = list3.tail;
                }
                TreeMaker treeMaker = this.make;
                JCTree.JCNewArray jCNewArrayNewArray = treeMaker.NewArray(treeMaker.Type(type), List.nil(), listBuffer2.toList());
                jCNewArrayNewArray.type = new Type.ArrayType(type, this.syms.arrayClass);
                listBuffer.append(jCNewArrayNewArray);
            } else {
                if (list3.length() != 1) {
                    x01.a(list3);
                    return null;
                }
                JCTree.JCExpression jCExpressionTranslate2 = translate((JCTree.JCExpression) list3.head, type2);
                boolean z3 = z | (jCExpressionTranslate2 != list3.head);
                listBuffer.append(jCExpressionTranslate2);
            }
            return listBuffer.toList();
        }
        return list2;
    }

    public <T extends JCTree.JCExpression> T boxIfNeeded(T t, Type type) {
        boolean zIsPrimitive;
        Assert.check(!type.hasTag(TypeTag.VOID));
        TypeTag typeTag = TypeTag.NONE;
        if (type.hasTag(typeTag) || (zIsPrimitive = t.type.isPrimitive()) == type.isPrimitive()) {
            return t;
        }
        if (!zIsPrimitive) {
            return (T) unbox(t, type);
        }
        Type typeUnboxedType = this.types.unboxedType(type);
        if (typeUnboxedType.hasTag(typeTag)) {
            return (T) boxPrimitive(t);
        }
        if (!this.types.isSubtype(t.type, typeUnboxedType)) {
            t.type = typeUnboxedType.constType(t.type.constValue());
        }
        return (T) boxPrimitive(t, this.types.erasure(type));
    }

    public JCTree.JCExpression boxPrimitive(JCTree.JCExpression jCExpression, Type type) {
        make_at(jCExpression.pos());
        Symbol.MethodSymbol methodSymbolLookupMethod = lookupMethod(jCExpression.pos(), this.names.valueOf, type, List.nil().prepend(jCExpression.type));
        TreeMaker treeMaker = this.make;
        return treeMaker.App(treeMaker.QualIdent(methodSymbolLookupMethod), List.of(jCExpression));
    }

    public void checkConflicts(List<JCTree> list) {
        Iterator<JCTree> it = list.iterator();
        while (it.hasNext()) {
            it.next().accept(this.conflictsChecker);
        }
    }

    public JCTree.JCClassDecl classDef(Symbol.ClassSymbol classSymbol) {
        JCTree jCTree;
        JCTree.JCClassDecl jCClassDecl = this.classdefs.get(classSymbol);
        if (jCClassDecl == null && (jCTree = this.outermostMemberDef) != null) {
            this.classMap.scan(jCTree);
            jCClassDecl = this.classdefs.get(classSymbol);
        }
        if (jCClassDecl != null) {
            return jCClassDecl;
        }
        this.classMap.scan(this.outermostClassDef);
        return this.classdefs.get(classSymbol);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Name> enumNamesFor(Symbol.ClassSymbol classSymbol) {
        JCTree.JCClassDecl jCClassDeclClassDef = classDef(classSymbol);
        if (jCClassDeclClassDef == null || (jCClassDeclClassDef.mods.flags & 16384) == 0 || (this.types.supertype(this.currentClass.type).tsym.flags() & 16384) != 0) {
            return null;
        }
        ListBuffer listBuffer = new ListBuffer();
        for (List list = jCClassDeclClassDef.defs; list.nonEmpty(); list = list.tail) {
            if (((JCTree) list.head).hasTag(JCTree.Tag.VARDEF)) {
                A a = list.head;
                if ((((JCTree.JCVariableDecl) a).mods.flags & 16384) != 0) {
                    listBuffer.append(((JCTree.JCVariableDecl) a).name);
                }
            }
        }
        return listBuffer.toList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<JCTree.JCVariableDecl> freevarDefs(int i, List<Symbol.VarSymbol> list, Symbol symbol, long j) {
        Name nameProxyName;
        long j2 = j | 4112;
        List<JCTree.JCVariableDecl> listNil = List.nil();
        HashSet hashSet = new HashSet();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            final Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) list2.head;
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                nameProxyName = this.proxyName(varSymbol.name, i2);
                if (hashSet.add(nameProxyName)) {
                    break;
                }
                i2 = i3;
            }
            Lower lower = this;
            Symbol symbol2 = symbol;
            Symbol.VarSymbol varSymbol2 = new Symbol.VarSymbol(lower, j2, nameProxyName, varSymbol.erasure(this.types), symbol2) { // from class: com.sun.tools.javac.comp.Lower.3
                final /* synthetic */ Lower this$0;

                {
                    this.this$0 = lower;
                }

                @Override // com.sun.tools.javac.code.Symbol
                public Symbol baseSymbol() {
                    return varSymbol;
                }
            };
            lower.proxies.put(varSymbol, varSymbol2);
            JCTree.JCVariableDecl jCVariableDeclVarDef = lower.make.at(i).VarDef(varSymbol2, null);
            jCVariableDeclVarDef.vartype = lower.access(jCVariableDeclVarDef.vartype);
            listNil = listNil.prepend(jCVariableDeclVarDef);
            this = lower;
            symbol = symbol2;
        }
        return listNil;
    }

    public List<Symbol.VarSymbol> freevars(Symbol.ClassSymbol classSymbol) {
        List<Symbol.VarSymbol> list = this.freevarCache.get(classSymbol);
        if (list != null) {
            return list;
        }
        List<Symbol.VarSymbol> listReverse = new FreeVarCollector(classDef(classSymbol)).analyzeCaptures().reverse();
        this.freevarCache.put(classSymbol, listReverse);
        return listReverse;
    }

    public List<JCTree> generateMandatedAccessors(final JCTree.JCClassDecl jCClassDecl) {
        final List<JCTree.JCVariableDecl> listRecordFields = TreeInfo.recordFields(jCClassDecl);
        return (List) jCClassDecl.sym.getRecordComponents().stream().filter(new Predicate() { // from class: nj9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Lower.u((Symbol.RecordComponent) obj);
            }
        }).map(new Function() { // from class: oj9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Lower.h(this.b, listRecordFields, jCClassDecl, (Symbol.RecordComponent) obj);
            }
        }).collect(List.collector());
    }

    public JCTree generateRecordMethod(JCTree.JCClassDecl jCClassDecl, Name name, List<Symbol.VarSymbol> list, Symbol.MethodHandleSymbol[] methodHandleSymbolArr) {
        JCTree.JCMethodInvocation jCMethodInvocationApply;
        make_at(jCClassDecl.pos());
        boolean z = name == this.names.equals;
        Symbol.MethodSymbol methodSymbolLookupMethod = lookupMethod(jCClassDecl.pos(), name, jCClassDecl.sym.type, z ? List.of(this.syms.objectType) : List.nil());
        if ((methodSymbolLookupMethod.flags() & Flags.RECORD) == 0) {
            return this.make.Block(4096L, List.nil());
        }
        Name name2 = this.names.bootstrap;
        int i = 2;
        PoolConstant.LoadableConstant[] loadableConstantArr = new PoolConstant.LoadableConstant[methodHandleSymbolArr.length + 2];
        loadableConstantArr[0] = (Type.ClassType) jCClassDecl.sym.type;
        loadableConstantArr[1] = PoolConstant.LoadableConstant.String((String) list.stream().map(new Function() { // from class: kj9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symbol.VarSymbol) obj).name;
            }
        }).collect(Collectors.joining(";", "", "")));
        for (Symbol.MethodHandleSymbol methodHandleSymbol : methodHandleSymbolArr) {
            loadableConstantArr[i] = methodHandleSymbol;
            i++;
        }
        Symtab symtab = this.syms;
        Type type = symtab.classType;
        Type type2 = symtab.stringType;
        Symtab symtab2 = this.syms;
        List listOf = List.of((Type.ArrayType) type, (Type.ArrayType) type2, new Type.ArrayType(symtab2.methodHandleType, symtab2.arrayClass));
        Symtab symtab3 = this.syms;
        JCTree.JCFieldAccess jCFieldAccessMakeIndyQualifier = makeIndyQualifier(symtab3.objectMethodsType, jCClassDecl, methodSymbolLookupMethod, List.of(symtab3.methodHandleLookupType, symtab3.stringType, symtab3.typeDescriptorType).appendList(listOf), loadableConstantArr, name2, name, false);
        Name name3 = this.names._this;
        Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4096L, name3, classSymbol.type, classSymbol);
        if (z) {
            Symbol.VarSymbol varSymbol2 = methodSymbolLookupMethod.params.head;
            varSymbol2.adr = 0;
            jCMethodInvocationApply = this.make.Apply(List.nil(), jCFieldAccessMakeIndyQualifier, List.of(this.make.Ident(varSymbol), this.make.Ident(varSymbol2)));
        } else {
            jCMethodInvocationApply = this.make.Apply(List.nil(), jCFieldAccessMakeIndyQualifier, List.of(this.make.Ident(varSymbol)));
        }
        jCMethodInvocationApply.type = jCFieldAccessMakeIndyQualifier.type;
        TreeMaker treeMaker = this.make;
        return treeMaker.MethodDef(methodSymbolLookupMethod, treeMaker.Block(0L, List.of(treeMaker.Return(jCMethodInvocationApply))));
    }

    public JCTree.JCStatement initField(int i, Symbol symbol, Symbol symbol2) {
        Assert.check(symbol.owner.kind == Kinds.Kind.MTH);
        Assert.check(symbol.owner.owner == symbol2.owner);
        this.make.at(i);
        TreeMaker treeMaker = this.make;
        return treeMaker.Exec(treeMaker.Assign(treeMaker.Select(treeMaker.This(symbol2.owner.erasure(this.types)), symbol2), this.make.Ident(symbol)).setType(symbol2.erasure(this.types)));
    }

    public JCTree.JCStatement initOuterThis(int i, Symbol.VarSymbol varSymbol, boolean z) {
        Assert.check(varSymbol.owner.kind == Kinds.Kind.MTH);
        Assert.check(this.nullCheckOuterThis || z);
        this.make.at(i);
        JCTree.JCExpression jCExpressionIdent = this.make.Ident(varSymbol);
        if (this.nullCheckOuterThis) {
            jCExpressionIdent = this.attr.makeNullCheck(jCExpressionIdent);
        }
        if (z) {
            Symbol.VarSymbol varSymbol2 = this.outerThisStack.head;
            Assert.check(varSymbol.owner.owner == varSymbol2.owner);
            TreeMaker treeMaker = this.make;
            jCExpressionIdent = treeMaker.Assign(treeMaker.Select(treeMaker.This(varSymbol2.owner.erasure(this.types)), varSymbol2), jCExpressionIdent).setType(varSymbol2.erasure(this.types));
        }
        return this.make.Exec(jCExpressionIdent);
    }

    public JCTree.JCExpression loadFreevar(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.VarSymbol varSymbol) {
        return access(varSymbol, this.make.at(diagnosticPosition).Ident(varSymbol), null, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<JCTree.JCExpression> loadFreevars(JCDiagnostic.DiagnosticPosition diagnosticPosition, List<Symbol.VarSymbol> list) {
        List<JCTree.JCExpression> listNil = List.nil();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            listNil = listNil.prepend(loadFreevar(diagnosticPosition, (Symbol.VarSymbol) list2.head));
        }
        return listNil;
    }

    public JCTree.JCExpression lowerBoxedPostop(final JCTree.JCUnary jCUnary) {
        final boolean zHasTag = TreeInfo.skipParens(jCUnary.arg).hasTag(JCTree.Tag.TYPECAST);
        return abstractLval(jCUnary.arg, new TreeBuilder() { // from class: com.sun.tools.javac.comp.n1
            @Override // com.sun.tools.javac.comp.Lower.TreeBuilder
            public final JCTree.JCExpression build(JCTree.JCExpression jCExpression) {
                return Lower.i(this.a, jCUnary, zHasTag, jCExpression);
            }
        });
    }

    public void makeAccessible(Symbol symbol) {
        JCTree.JCClassDecl jCClassDeclClassDef = classDef(symbol.owner.enclClass());
        if (jCClassDeclClassDef == null) {
            Assert.error("class def not found: " + symbol + " in " + symbol.owner);
        }
        if (symbol.name == this.names.init) {
            jCClassDeclClassDef.defs = jCClassDeclClassDef.defs.prepend(accessConstructorDef(jCClassDeclClassDef.pos, symbol, this.accessConstrs.get(symbol)));
            return;
        }
        Symbol.MethodSymbol[] methodSymbolArr = this.accessSyms.get(symbol);
        for (int i = 0; i < Symbol.OperatorSymbol.AccessCode.numberOfAccessCodes; i++) {
            Symbol.MethodSymbol methodSymbol = methodSymbolArr[i];
            if (methodSymbol != null) {
                jCClassDeclClassDef.defs = jCClassDeclClassDef.defs.prepend(accessDef(jCClassDeclClassDef.pos, symbol, methodSymbol, i));
            }
        }
    }

    public JCTree.JCAssignOp makeAssignop(JCTree.Tag tag, JCTree jCTree, JCTree jCTree2) {
        JCTree.JCAssignOp jCAssignOpAssignop = this.make.Assignop(tag, jCTree, jCTree2);
        jCAssignOpAssignop.operator = this.operators.resolveBinary(jCAssignOpAssignop, jCAssignOpAssignop.getTag().noAssignOp(), jCTree.type, jCTree2.type);
        jCAssignOpAssignop.type = jCTree.type;
        return jCAssignOpAssignop;
    }

    public JCTree.JCBinary makeBinary(JCTree.Tag tag, JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        JCTree.JCBinary jCBinaryBinary = this.make.Binary(tag, jCExpression, jCExpression2);
        Symbol.OperatorSymbol operatorSymbolResolveBinary = this.operators.resolveBinary(jCBinaryBinary, tag, jCExpression.type, jCExpression2.type);
        jCBinaryBinary.operator = operatorSymbolResolveBinary;
        jCBinaryBinary.type = operatorSymbolResolveBinary.type.mo73getReturnType();
        return jCBinaryBinary;
    }

    public JCTree.JCExpression makeComma(JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        TreeMaker treeMaker = this.make;
        JCTree.LetExpr LetExpr = treeMaker.LetExpr(List.of(treeMaker.Exec(jCExpression)), jCExpression2);
        LetExpr.type = jCExpression2.type;
        return LetExpr;
    }

    public JCTree.JCClassDecl makeEmptyClass(long j, Symbol.ClassSymbol classSymbol, Name name, boolean z) {
        Symbol.ClassSymbol classSymbolDefineClass = this.syms.defineClass(this.names.empty, classSymbol);
        if (name != null) {
            classSymbolDefineClass.flatname = name;
        } else {
            classSymbolDefineClass.flatname = this.chk.localClassName(classSymbolDefineClass);
        }
        classSymbolDefineClass.sourcefile = classSymbol.sourcefile;
        classSymbolDefineClass.completer = Symbol.Completer.NULL_COMPLETER;
        classSymbolDefineClass.members_field = Scope.WriteableScope.create(classSymbolDefineClass);
        classSymbolDefineClass.flags_field = j;
        Type.ClassType classType = (Type.ClassType) classSymbolDefineClass.type;
        classType.supertype_field = this.syms.objectType;
        classType.interfaces_field = List.nil();
        JCTree.JCClassDecl jCClassDeclClassDef = classDef(classSymbol);
        enterSynthetic(jCClassDeclClassDef.pos(), classSymbolDefineClass, classSymbol.members());
        this.chk.putCompiled(classSymbolDefineClass);
        TreeMaker treeMaker = this.make;
        JCTree.JCClassDecl jCClassDeclClassDef2 = treeMaker.ClassDef(treeMaker.Modifiers(j), this.names.empty, List.nil(), null, List.nil(), List.nil());
        jCClassDeclClassDef2.sym = classSymbolDefineClass;
        jCClassDeclClassDef2.type = classSymbolDefineClass.type;
        if (z) {
            jCClassDeclClassDef.defs = jCClassDeclClassDef.defs.prepend(jCClassDeclClassDef2);
        }
        return jCClassDeclClassDef2;
    }

    public JCTree.JCFieldAccess makeIndyQualifier(Type type, JCTree.JCClassDecl jCClassDecl, Symbol.MethodSymbol methodSymbol, List<Type> list, PoolConstant.LoadableConstant[] loadableConstantArr, Name name, Name name2, boolean z) {
        Symbol.MethodSymbol methodSymbolResolveInternalMethod = this.rs.resolveInternalMethod(jCClassDecl.pos(), this.attrEnv, type, name, list, List.nil());
        Type.MethodType methodTypeAsMethodType = methodSymbol.type.asMethodType();
        Symbol.DynamicMethodSymbol dynamicMethodSymbol = new Symbol.DynamicMethodSymbol(name2, this.syms.noSymbol, methodSymbolResolveInternalMethod.asHandle(), new Type.MethodType(z ? List.nil() : methodTypeAsMethodType.argtypes.prepend(jCClassDecl.sym.type), methodTypeAsMethodType.restype, methodTypeAsMethodType.thrown, this.syms.methodClass), loadableConstantArr);
        TreeMaker treeMaker = this.make;
        JCTree.JCFieldAccess jCFieldAccessSelect = treeMaker.Select(treeMaker.QualIdent(type.tsym), name2);
        jCFieldAccessSelect.sym = dynamicMethodSymbol;
        jCFieldAccessSelect.type = methodSymbol.type.asMethodType().restype;
        return jCFieldAccessSelect;
    }

    public JCTree.JCExpression makeLit(Type type, Object obj) {
        return this.make.Literal(type.getTag(), obj).setType(type.constType(obj));
    }

    public JCTree.JCNewClass makeNewClass(Type type, List<JCTree.JCExpression> list) {
        TreeMaker treeMaker = this.make;
        JCTree.JCNewClass jCNewClassNewClass = treeMaker.NewClass(null, null, treeMaker.QualIdent(type.tsym), list, null);
        jCNewClassNewClass.constructor = this.rs.resolveConstructor(this.make_pos, this.attrEnv, type, TreeInfo.types(list), List.nil());
        jCNewClassNewClass.type = type;
        return jCNewClassNewClass;
    }

    public JCTree.JCExpression makeNull() {
        return makeLit(this.syms.botType, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JCTree.JCExpression makeOuterThis(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.TypeSymbol typeSymbol) {
        Symbol.VarSymbol varSymbol;
        List list = this.outerThisStack;
        if (list.isEmpty()) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.NoEnclInstanceOfTypeInScope(typeSymbol));
            return makeNull();
        }
        Symbol.VarSymbol varSymbol2 = (Symbol.VarSymbol) list.head;
        JCTree.JCExpression jCExpressionAccess = access(this.make.at(diagnosticPosition).Ident(varSymbol2));
        varSymbol2.flags_field &= -4194305;
        Symbol.TypeSymbol typeSymbol2 = varSymbol2.type.tsym;
        while (typeSymbol2 != typeSymbol) {
            do {
                list = list.tail;
                if (list.isEmpty()) {
                    this.log.error(diagnosticPosition, CompilerProperties.Errors.NoEnclInstanceOfTypeInScope(typeSymbol));
                    Assert.error();
                    return jCExpressionAccess;
                }
                varSymbol = (Symbol.VarSymbol) list.head;
            } while (varSymbol.owner != typeSymbol2);
            if (typeSymbol2.owner.kind != Kinds.Kind.PCK && !typeSymbol2.hasOuterInstance()) {
                this.log.error(diagnosticPosition, CompilerProperties.Errors.NoEnclInstanceOfTypeInScope(typeSymbol));
                Assert.error();
                return makeNull();
            }
            jCExpressionAccess = access(this.make.at(diagnosticPosition).Select(jCExpressionAccess, varSymbol));
            varSymbol.flags_field &= -4194305;
            typeSymbol2 = varSymbol.type.tsym;
        }
        return jCExpressionAccess;
    }

    public JCTree.JCExpression makeOwnerThis(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, boolean z) {
        Symbol.ClassSymbol classSymbol = this.currentClass;
        return (!z ? classSymbol.isSubClass(symbol.owner, this.types) : symbol.isMemberOf(classSymbol, this.types)) ? makeOwnerThisN(diagnosticPosition, symbol, z) : this.make.at(diagnosticPosition).This(this.currentClass.erasure(this.types));
    }

    /* JADX WARN: Code duplicated, block: B:17:0x005d  */
    /* JADX WARN: Code duplicated, block: B:23:0x0053 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    public JCTree.JCExpression makeOwnerThisN(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, boolean z) {
        Symbol.VarSymbol varSymbol;
        Symbol symbol2 = symbol.owner;
        List list = this.outerThisStack;
        if (list.isEmpty()) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.NoEnclInstanceOfTypeInScope(symbol2));
            return makeNull();
        }
        Symbol.VarSymbol varSymbol2 = (Symbol.VarSymbol) list.head;
        JCTree.JCExpression jCExpressionAccess = access(this.make.at(diagnosticPosition).Ident(varSymbol2));
        varSymbol2.flags_field &= -4194305;
        Symbol.TypeSymbol typeSymbol = varSymbol2.type.tsym;
        while (true) {
            if (z) {
                if (symbol.isMemberOf(typeSymbol, this.types)) {
                    break;
                }
                do {
                    list = list.tail;
                    if (list.isEmpty()) {
                        this.log.error(diagnosticPosition, CompilerProperties.Errors.NoEnclInstanceOfTypeInScope(symbol2));
                        return jCExpressionAccess;
                    }
                    varSymbol = (Symbol.VarSymbol) list.head;
                } while (varSymbol.owner != typeSymbol);
                jCExpressionAccess = access(this.make.at(diagnosticPosition).Select(jCExpressionAccess, varSymbol));
                varSymbol.flags_field &= -4194305;
                typeSymbol = varSymbol.type.tsym;
            } else {
                if (typeSymbol.isSubClass(symbol.owner, this.types)) {
                    break;
                }
                do {
                    list = list.tail;
                    if (list.isEmpty()) {
                        this.log.error(diagnosticPosition, CompilerProperties.Errors.NoEnclInstanceOfTypeInScope(symbol2));
                        return jCExpressionAccess;
                    }
                    varSymbol = (Symbol.VarSymbol) list.head;
                } while (varSymbol.owner != typeSymbol);
                jCExpressionAccess = access(this.make.at(diagnosticPosition).Select(jCExpressionAccess, varSymbol));
                varSymbol.flags_field &= -4194305;
                typeSymbol = varSymbol.type.tsym;
            }
        }
        return jCExpressionAccess;
    }

    public JCTree.JCExpression makeString(JCTree.JCExpression jCExpression) {
        if (!jCExpression.type.isPrimitiveOrVoid()) {
            return jCExpression;
        }
        Symbol.MethodSymbol methodSymbolLookupMethod = lookupMethod(jCExpression.pos(), this.names.valueOf, this.syms.stringType, List.of(jCExpression.type));
        TreeMaker treeMaker = this.make;
        return treeMaker.App(treeMaker.QualIdent(methodSymbolLookupMethod), List.of(jCExpression));
    }

    public JCTree.JCExpression makeThis(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.TypeSymbol typeSymbol) {
        return this.currentClass == typeSymbol ? this.make.at(diagnosticPosition).This(typeSymbol.erasure(this.types)) : makeOuterThis(diagnosticPosition, typeSymbol);
    }

    public JCTree makeTwrTry(JCTree.JCTry jCTry) {
        make_at(jCTry.pos());
        this.twrVars = this.twrVars.dup();
        JCTree.JCBlock jCBlockMakeTwrBlock = makeTwrBlock(jCTry.resources, jCTry.body, 0);
        if (jCTry.catchers.isEmpty() && jCTry.finalizer == null) {
            this.result = translate(jCBlockMakeTwrBlock);
        } else {
            this.result = translate(this.make.Try(jCBlockMakeTwrBlock, jCTry.catchers, jCTry.finalizer));
        }
        this.twrVars = this.twrVars.leave();
        return this.result;
    }

    public JCTree.JCUnary makeUnary(JCTree.Tag tag, JCTree.JCExpression jCExpression) {
        JCTree.JCUnary jCUnaryUnary = this.make.Unary(tag, jCExpression);
        Symbol.OperatorSymbol operatorSymbolResolveUnary = this.operators.resolveUnary(jCUnaryUnary, tag, jCExpression.type);
        jCUnaryUnary.operator = operatorSymbolResolveUnary;
        jCUnaryUnary.type = operatorSymbolResolveUnary.type.mo73getReturnType();
        return jCUnaryUnary;
    }

    public TreeMaker make_at(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        this.make_pos = diagnosticPosition;
        return this.make.at(diagnosticPosition);
    }

    public EnumMapping mapForEnum(final JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.TypeSymbol typeSymbol) {
        List<Name> listEnumNamesFor;
        return (typeSymbol.kind != Kinds.Kind.TYP || (listEnumNamesFor = enumNamesFor((Symbol.ClassSymbol) typeSymbol)) == null) ? this.enumSwitchMap.computeIfAbsent(typeSymbol, new Function() { // from class: ij9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Lower.p(this.b, diagnosticPosition, (Symbol.TypeSymbol) obj);
            }
        }) : new CompileTimeEnumMapping(listEnumNamesFor);
    }

    public boolean needsPrivateAccess(Symbol symbol) {
        Symbol symbol2;
        if (this.target.hasNestmateAccess() || (symbol.flags() & 2) == 0 || (symbol2 = symbol.owner) == this.currentClass) {
            return false;
        }
        if (symbol.name != this.names.init || !symbol2.isDirectlyOrIndirectlyLocal()) {
            return true;
        }
        symbol.flags_field &= -3;
        return false;
    }

    public boolean needsProtectedAccess(Symbol symbol, JCTree jCTree) {
        if (!this.disableProtectedAccessors && (symbol.flags() & 4) != 0 && symbol.owner.owner != this.currentClass.owner && symbol.packge() != this.currentClass.packge()) {
            if (!this.currentClass.isSubClass(symbol.owner, this.types)) {
                return true;
            }
            if ((symbol.flags() & 8) == 0 && jCTree.hasTag(JCTree.Tag.SELECT)) {
                JCTree.JCFieldAccess jCFieldAccess = (JCTree.JCFieldAccess) jCTree;
                if (TreeInfo.name(jCFieldAccess.selected) != this.names._super) {
                    return !jCFieldAccess.selected.type.tsym.isSubClass(this.currentClass, this.types);
                }
            }
        }
        return false;
    }

    public JCTree.JCVariableDecl outerThisDef(int i, Symbol.MethodSymbol methodSymbol) {
        Symbol.ClassSymbol classSymbolEnclClass = methodSymbol.enclClass();
        Symbol.VarSymbol varSymbolMakeOuterThisVarSymbol = makeOuterThisVarSymbol(methodSymbol, ((long) (((methodSymbol.isConstructor() && methodSymbol.isAnonymous()) || (methodSymbol.isConstructor() && classSymbolEnclClass.isInner() && !classSymbolEnclClass.isPrivate() && !classSymbolEnclClass.isStatic()) ? 32768 : 4096) | 16)) | 8589934592L);
        methodSymbol.extraParams = methodSymbol.extraParams.prepend(varSymbolMakeOuterThisVarSymbol);
        return makeOuterThisVarDecl(i, varSymbolMakeOuterThisVarSymbol);
    }

    public Name outerThisName(Type type, Symbol symbol) {
        Type enclosingType = type.getEnclosingType();
        int i = 0;
        while (enclosingType.hasTag(TypeTag.CLASS)) {
            enclosingType = enclosingType.getEnclosingType();
            i++;
        }
        Name nameFromString = this.names.fromString(PsiKeyword.THIS + this.target.syntheticNameChar() + i);
        while (symbol.kind == Kinds.Kind.TYP && ((Symbol.ClassSymbol) symbol).members().findFirst(nameFromString) != null) {
            nameFromString = this.names.fromString(nameFromString.toString() + this.target.syntheticNameChar());
        }
        return nameFromString;
    }

    public Name proxyName(Name name, int i) {
        Name nameFromString = this.names.fromString("val" + this.target.syntheticNameChar() + name);
        if (i <= 0) {
            return nameFromString;
        }
        return nameFromString.append(this.names.fromString("" + this.target.syntheticNameChar() + i));
    }

    public void swapAccessConstructorTag(Symbol.ClassSymbol classSymbol, Symbol.ClassSymbol classSymbol2) {
        for (Symbol.MethodSymbol methodSymbol : this.accessConstrs.values()) {
            Assert.check(methodSymbol.type.hasTag(TypeTag.METHOD));
            Type.MethodType methodType = (Type.MethodType) methodSymbol.type;
            if (methodType.argtypes.head.tsym == classSymbol) {
                methodSymbol.type = this.types.createMethodTypeWithParameters(methodType, methodType.mo71getParameterTypes().tail.prepend(classSymbol2.erasure(this.types)));
            }
        }
    }

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
    @Override // com.sun.tools.javac.tree.TreeTranslator
    public <T extends JCTree> T translate(T t) {
        if (t == null) {
            return null;
        }
        make_at(t.pos());
        T t2 = (T) super.translate(t);
        EndPosTable endPosTable = this.endPosTable;
        if (endPosTable != null && t2 != t) {
            endPosTable.replaceTree(t, t2);
        }
        return t2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JCTree.JCMethodDecl translateMethod(Env<AttrContext> env, JCTree.JCMethodDecl jCMethodDecl, TreeMaker treeMaker) {
        try {
            this.attrEnv = env;
            this.make = treeMaker;
            this.currentClass = jCMethodDecl.sym.enclClass();
            this.proxies = new HashMap();
            return (JCTree.JCMethodDecl) translate(jCMethodDecl);
        } finally {
            this.attrEnv = null;
            this.make = null;
            this.currentClass = null;
            this.currentMethodSym = null;
            this.currentMethodDef = null;
            this.proxies = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<JCTree> translateTopLevelClass(Env<AttrContext> env, JCTree jCTree, TreeMaker treeMaker) {
        try {
            this.attrEnv = env;
            this.make = treeMaker;
            this.endPosTable = env.toplevel.endPositions;
            this.currentClass = null;
            this.currentRestype = null;
            this.currentMethodDef = null;
            this.outermostClassDef = jCTree.hasTag(JCTree.Tag.CLASSDEF) ? (JCTree.JCClassDecl) jCTree : null;
            this.outermostMemberDef = null;
            this.translated = new ListBuffer<>();
            this.classdefs = new HashMap();
            this.actualSymbols = new HashMap();
            this.freevarCache = new HashMap();
            this.proxies = new HashMap();
            this.twrVars = Scope.WriteableScope.create(this.syms.noSymbol);
            this.outerThisStack = List.nil();
            this.accessNums = new HashMap();
            this.accessSyms = new HashMap();
            this.accessConstrs = new HashMap();
            this.accessConstrTags = List.nil();
            this.accessed = new ListBuffer<>();
            translate(jCTree, (JCTree.JCExpression) null);
            for (List list = this.accessed.toList(); list.nonEmpty(); list = list.tail) {
                makeAccessible((Symbol) list.head);
            }
            Iterator<EnumMapping> it = this.enumSwitchMap.values().iterator();
            while (it.hasNext()) {
                it.next().translate();
            }
            checkConflicts(this.translated.toList());
            checkAccessConstructorTags();
            ListBuffer<JCTree> listBuffer = this.translated;
            Object[] objArr = objArr == true ? 1 : 0;
            Object[] objArr2 = objArr == true ? 1 : 0;
            Object[] objArr3 = objArr == true ? 1 : 0;
            Object[] objArr4 = objArr == true ? 1 : 0;
            Object[] objArr5 = objArr == true ? 1 : 0;
            Object[] objArr6 = objArr == true ? 1 : 0;
            Object[] objArr7 = objArr == true ? 1 : 0;
            Object[] objArr8 = objArr == true ? 1 : 0;
            Object[] objArr9 = objArr == true ? 1 : 0;
            Object[] objArr10 = objArr == true ? 1 : 0;
            Object[] objArr11 = objArr == true ? 1 : 0;
            Object[] objArr12 = objArr == true ? 1 : 0;
            Object[] objArr13 = objArr == true ? 1 : 0;
            Object[] objArr14 = objArr == true ? 1 : 0;
            Object[] objArr15 = objArr == true ? 1 : 0;
            Object[] objArr16 = objArr == true ? 1 : 0;
            Object[] objArr17 = objArr == true ? 1 : 0;
            Object[] objArr18 = objArr == true ? 1 : 0;
            Object[] objArr19 = objArr == true ? 1 : 0;
            return listBuffer.toList();
        } finally {
            this.attrEnv = null;
            this.make = null;
            this.endPosTable = null;
            this.currentClass = null;
            this.currentRestype = null;
            this.currentMethodDef = null;
            this.outermostClassDef = null;
            this.outermostMemberDef = null;
            this.translated = null;
            this.classdefs = null;
            this.actualSymbols = null;
            this.freevarCache = null;
            this.proxies = null;
            this.outerThisStack = null;
            this.accessNums = null;
            this.accessSyms = null;
            this.accessConstrs = null;
            this.accessConstrTags = null;
            this.accessed = null;
            this.enumSwitchMap.clear();
            this.assertionsDisabledClassCache = null;
        }
    }

    public JCTree.JCExpression unbox(JCTree.JCExpression jCExpression, Type type) {
        Type typeUnboxedType = this.types.unboxedType(jCExpression.type);
        if (typeUnboxedType.hasTag(TypeTag.NONE)) {
            if (!type.isPrimitive()) {
                x01.a(type);
                return null;
            }
            make_at(jCExpression.pos());
            jCExpression = this.make.TypeCast(this.types.boxedClass(type).type, jCExpression);
        } else {
            if (!this.types.isSubtype(typeUnboxedType, type)) {
                x01.a(jCExpression);
                return null;
            }
            type = typeUnboxedType;
        }
        make_at(jCExpression.pos());
        Symbol.MethodSymbol methodSymbolLookupMethod = lookupMethod(jCExpression.pos(), type.tsym.name.append(this.names.Value), jCExpression.type, List.nil());
        TreeMaker treeMaker = this.make;
        return treeMaker.App(treeMaker.Select(jCExpression, methodSymbolLookupMethod));
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
        this.result = jCAnnotation;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
        JCTree.JCExpression jCExpressionMakeThis;
        Symbol symbol = TreeInfo.symbol(jCMethodInvocation.meth);
        List listMo71getParameterTypes = symbol.type.mo71getParameterTypes();
        if (symbol.name == this.names.init && symbol.owner == this.syms.enumSym) {
            listMo71getParameterTypes = listMo71getParameterTypes.tail.tail;
        }
        jCMethodInvocation.args = boxArgs(listMo71getParameterTypes, jCMethodInvocation.args, jCMethodInvocation.varargsElement);
        jCMethodInvocation.varargsElement = null;
        Name name = TreeInfo.name(jCMethodInvocation.meth);
        if (symbol.name == this.names.init) {
            Symbol symbolAccessConstructor = accessConstructor(jCMethodInvocation.pos(), symbol);
            if (symbolAccessConstructor != symbol) {
                jCMethodInvocation.args = jCMethodInvocation.args.append(makeNull());
                TreeInfo.setSymbol(jCMethodInvocation.meth, symbolAccessConstructor);
            }
            Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbolAccessConstructor.owner;
            if (classSymbol.isDirectlyOrIndirectlyLocal() && !classSymbol.isStatic()) {
                jCMethodInvocation.args = jCMethodInvocation.args.appendList(loadFreevars(jCMethodInvocation.pos(), freevars(classSymbol)));
            }
            if ((classSymbol.flags_field & 16384) != 0 || classSymbol.getQualifiedName() == this.names.java_lang_Enum) {
                List list = this.currentMethodDef.params;
                if (this.currentMethodSym.owner.hasOuterInstance()) {
                    list = list.tail;
                }
                jCMethodInvocation.args = jCMethodInvocation.args.prepend(make_at(jCMethodInvocation.pos()).Ident(((JCTree.JCVariableDecl) list.tail.head).sym)).prepend(this.make.Ident(((JCTree.JCVariableDecl) list.head).sym));
            }
            if (classSymbol.hasOuterInstance()) {
                if (jCMethodInvocation.meth.hasTag(JCTree.Tag.SELECT)) {
                    jCExpressionMakeThis = this.attr.makeNullCheck((JCTree.JCExpression) translate(((JCTree.JCFieldAccess) jCMethodInvocation.meth).selected));
                    JCTree.JCIdent jCIdentIdent = this.make.Ident(symbolAccessConstructor);
                    jCMethodInvocation.meth = jCIdentIdent;
                    jCIdentIdent.name = name;
                } else if (classSymbol.isDirectlyOrIndirectlyLocal() || name == this.names._this) {
                    jCExpressionMakeThis = makeThis(jCMethodInvocation.meth.pos(), classSymbol.innermostAccessibleEnclosingClass());
                } else if (this.currentClass.isStatic()) {
                    this.log.error(jCMethodInvocation.pos(), CompilerProperties.Errors.NoEnclInstanceOfTypeInScope(classSymbol.type.getEnclosingType().tsym));
                    jCExpressionMakeThis = this.make.Literal(TypeTag.BOT, null).setType(this.syms.botType);
                } else {
                    jCExpressionMakeThis = makeOwnerThisN(jCMethodInvocation.meth.pos(), classSymbol, false);
                }
                jCMethodInvocation.args = jCMethodInvocation.args.prepend(jCExpressionMakeThis);
            }
        } else {
            JCTree.JCExpression jCExpression = (JCTree.JCExpression) translate(jCMethodInvocation.meth);
            jCMethodInvocation.meth = jCExpression;
            if (jCExpression.hasTag(JCTree.Tag.APPLY)) {
                JCTree.JCMethodInvocation jCMethodInvocation2 = (JCTree.JCMethodInvocation) jCMethodInvocation.meth;
                jCMethodInvocation2.args = jCMethodInvocation.args.prependList(jCMethodInvocation2.args);
                this.result = jCMethodInvocation2;
                return;
            }
        }
        if (jCMethodInvocation.args.stream().anyMatch(new Predicate() { // from class: pj9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Lower.l((JCTree.JCExpression) obj);
            }
        })) {
            s22.a("Whooops before: ", jCMethodInvocation);
        } else {
            this.result = jCMethodInvocation;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssert(JCTree.JCAssert jCAssert) {
        JCTree.JCExpression jCExpressionTranslate = translate(jCAssert.cond, this.syms.booleanType);
        jCAssert.cond = jCExpressionTranslate;
        if (jCExpressionTranslate.type.isTrue()) {
            this.result = this.make.Skip();
            return;
        }
        JCTree.JCExpression jCExpressionAssertFlagTest = assertFlagTest(jCAssert.pos());
        JCTree.JCExpression jCExpression = jCAssert.detail;
        List<JCTree.JCExpression> listNil = jCExpression == null ? List.nil() : List.of((JCTree.JCExpression) translate(jCExpression));
        if (!jCAssert.cond.type.isFalse()) {
            jCExpressionAssertFlagTest = makeBinary(JCTree.Tag.AND, jCExpressionAssertFlagTest, makeUnary(JCTree.Tag.NOT, jCAssert.cond));
        }
        this.result = this.make.If(jCExpressionAssertFlagTest, make_at(jCAssert).Throw(makeNewClass(this.syms.assertionErrorType, listNil)), null);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssign(JCTree.JCAssign jCAssign) {
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) translate(jCAssign.lhs, jCAssign);
        jCAssign.lhs = jCExpression;
        jCAssign.rhs = translate(jCAssign.rhs, jCExpression.type);
        if (!jCAssign.lhs.hasTag(JCTree.Tag.APPLY)) {
            this.result = jCAssign;
            return;
        }
        JCTree.JCMethodInvocation jCMethodInvocation = (JCTree.JCMethodInvocation) jCAssign.lhs;
        jCMethodInvocation.args = List.of(jCAssign.rhs).prependList(jCMethodInvocation.args);
        this.result = jCMethodInvocation;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssignop(final JCTree.JCAssignOp jCAssignOp) {
        final boolean z = !jCAssignOp.lhs.type.isPrimitive() && jCAssignOp.operator.type.mo73getReturnType().isPrimitive();
        AssignopDependencyScanner assignopDependencyScanner = new AssignopDependencyScanner(jCAssignOp);
        assignopDependencyScanner.scan(jCAssignOp.rhs);
        if (z || assignopDependencyScanner.dependencyFound) {
            this.result = translate(abstractLval(jCAssignOp.lhs, new TreeBuilder() { // from class: com.sun.tools.javac.comp.m1
                @Override // com.sun.tools.javac.comp.Lower.TreeBuilder
                public final JCTree.JCExpression build(JCTree.JCExpression jCExpression) {
                    return Lower.k(this.a, jCAssignOp, z, jCExpression);
                }
            }));
            return;
        }
        jCAssignOp.lhs = (JCTree.JCExpression) translate(jCAssignOp.lhs, jCAssignOp);
        jCAssignOp.rhs = translate(jCAssignOp.rhs, jCAssignOp.operator.type.mo71getParameterTypes().tail.head);
        if (!jCAssignOp.lhs.hasTag(JCTree.Tag.APPLY)) {
            this.result = jCAssignOp;
            return;
        }
        JCTree.JCMethodInvocation jCMethodInvocation = (JCTree.JCMethodInvocation) jCAssignOp.lhs;
        int i = jCAssignOp.operator.opcode;
        JCTree.JCExpression jCExpressionMakeString = jCAssignOp.rhs;
        if (i == 256) {
            jCExpressionMakeString = makeString(jCExpressionMakeString);
        }
        jCMethodInvocation.args = List.of(jCExpressionMakeString).prependList(jCMethodInvocation.args);
        this.result = jCMethodInvocation;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBinary(JCTree.JCBinary jCBinary) {
        List<Type> listMo71getParameterTypes = jCBinary.operator.type.mo71getParameterTypes();
        JCTree.JCExpression jCExpressionTranslate = translate(jCBinary.lhs, listMo71getParameterTypes.head);
        jCBinary.lhs = jCExpressionTranslate;
        int i = AnonymousClass4.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCBinary.getTag().ordinal()];
        if (i != 12) {
            if (i == 13) {
                if (isFalse(jCExpressionTranslate)) {
                    this.result = jCExpressionTranslate;
                    return;
                } else if (isTrue(jCExpressionTranslate)) {
                    this.result = translate(jCBinary.rhs, listMo71getParameterTypes.tail.head);
                    return;
                }
            }
        } else if (isTrue(jCExpressionTranslate)) {
            this.result = jCExpressionTranslate;
            return;
        } else if (isFalse(jCExpressionTranslate)) {
            this.result = translate(jCBinary.rhs, listMo71getParameterTypes.tail.head);
            return;
        }
        jCBinary.rhs = translate(jCBinary.rhs, listMo71getParameterTypes.tail.head);
        this.result = jCBinary;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBlock(JCTree.JCBlock jCBlock) {
        Symbol.MethodSymbol methodSymbol = this.currentMethodSym;
        if (methodSymbol == null) {
            this.currentMethodSym = new Symbol.MethodSymbol(jCBlock.flags | 1048576, this.names.empty, null, this.currentClass);
        }
        int i = this.variableIndex;
        try {
            this.variableIndex = 0;
            super.visitBlock(jCBlock);
        } finally {
            this.currentMethodSym = methodSymbol;
            this.variableIndex = i;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBreak(JCTree.JCBreak jCBreak) {
        this.result = jCBreak;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v2, types: [A, com.sun.tools.javac.tree.JCTree] */
    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        Env<AttrContext> env = this.attrEnv;
        Symbol.ClassSymbol classSymbol = this.currentClass;
        Symbol.MethodSymbol methodSymbol = this.currentMethodSym;
        Symbol.ClassSymbol classSymbol2 = jCClassDecl.sym;
        this.currentClass = classSymbol2;
        this.currentMethodSym = null;
        Env<AttrContext> envRemove = this.typeEnvs.remove(classSymbol2);
        this.attrEnv = envRemove;
        if (envRemove == null) {
            this.attrEnv = env;
        }
        this.classdefs.put(this.currentClass, jCClassDecl);
        Map<Symbol, Symbol> map = this.proxies;
        this.proxies = new HashMap(this.proxies);
        List<Symbol.VarSymbol> list = this.outerThisStack;
        if ((jCClassDecl.mods.flags & 16384) != 0 && (this.types.supertype(this.currentClass.type).tsym.flags() & 16384) == 0) {
            visitEnumDef(jCClassDecl);
        }
        if ((jCClassDecl.mods.flags & Flags.RECORD) != 0) {
            visitRecordDef(jCClassDecl);
        }
        JCTree.JCVariableDecl jCVariableDeclOuterThisDef = this.currentClass.hasOuterInstance() ? outerThisDef(jCClassDecl.pos, this.currentClass) : null;
        jCClassDecl.extending = (JCTree.JCExpression) translate(jCClassDecl.extending);
        jCClassDecl.implementing = translate(jCClassDecl.implementing);
        if (this.currentClass.isDirectlyOrIndirectlyLocal()) {
            Symbol.ClassSymbol classSymbolEnclClass = this.currentClass.owner.enclClass();
            if (classSymbolEnclClass.trans_local == null) {
                classSymbolEnclClass.trans_local = List.nil();
            }
            classSymbolEnclClass.trans_local = classSymbolEnclClass.trans_local.prepend(this.currentClass);
        }
        List<JCTree> listNil = List.nil();
        while (true) {
            List<JCTree> list2 = jCClassDecl.defs;
            if (list2 == listNil) {
                break;
            }
            for (List list3 = list2; list3.nonEmpty() && list3 != listNil; list3 = list3.tail) {
                JCTree jCTree = this.outermostMemberDef;
                if (jCTree == null) {
                    this.outermostMemberDef = (JCTree) list3.head;
                }
                list3.head = translate((JCTree) list3.head);
                this.outermostMemberDef = jCTree;
            }
            listNil = list2;
        }
        JCTree.JCModifiers jCModifiers = jCClassDecl.mods;
        long j = jCModifiers.flags;
        if ((4 & j) != 0) {
            jCModifiers.flags = j | 1;
        }
        jCModifiers.flags &= 32273;
        jCClassDecl.name = Convert.shortName(this.currentClass.flatName());
        for (List listFreevarDefs = freevarDefs(jCClassDecl.pos, freevars(this.currentClass), this.currentClass); listFreevarDefs.nonEmpty(); listFreevarDefs = listFreevarDefs.tail) {
            jCClassDecl.defs = jCClassDecl.defs.prepend((JCTree) listFreevarDefs.head);
            enterSynthetic(jCClassDecl.pos(), ((JCTree.JCVariableDecl) listFreevarDefs.head).sym, this.currentClass.members());
        }
        if (this.currentClass.hasOuterInstance()) {
            boolean zShouldEmitOuterThis = shouldEmitOuterThis(this.currentClass);
            if (zShouldEmitOuterThis) {
                jCClassDecl.defs = jCClassDecl.defs.prepend(jCVariableDeclOuterThisDef);
                enterSynthetic(jCClassDecl.pos(), jCVariableDeclOuterThisDef.sym, this.currentClass.members());
            } else if (this.nullCheckOuterThis) {
            }
            for (JCTree jCTree2 : jCClassDecl.defs) {
                if (TreeInfo.isConstructor(jCTree2)) {
                    JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) jCTree2;
                    if (TreeInfo.hasConstructorCall(jCMethodDecl, this.names._super)) {
                        final List listOf = List.of(initOuterThis(jCMethodDecl.body.pos, jCMethodDecl.params.head.sym, zShouldEmitOuterThis));
                        TreeInfo.mapSuperCalls(jCMethodDecl.body, new Function() { // from class: jj9
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return this.b.make.Block(0L, listOf.append((JCTree.JCExpressionStatement) obj));
                            }
                        });
                    }
                }
            }
        }
        this.proxies = map;
        this.outerThisStack = list;
        this.translated.append(jCClassDecl);
        this.attrEnv = env;
        this.currentClass = classSymbol;
        this.currentMethodSym = methodSymbol;
        this.result = make_at(jCClassDecl.pos()).Block(4096L, List.nil());
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConditional(JCTree.JCConditional jCConditional) {
        JCTree.JCExpression jCExpressionTranslate = translate(jCConditional.cond, this.syms.booleanType);
        jCConditional.cond = jCExpressionTranslate;
        if (isTrue(jCExpressionTranslate) && noClassDefIn(jCConditional.falsepart)) {
            this.result = convert(translate(jCConditional.truepart, jCConditional.type), jCConditional.type);
            addPrunedInfo(jCExpressionTranslate);
        } else if (isFalse(jCExpressionTranslate) && noClassDefIn(jCConditional.truepart)) {
            this.result = convert(translate(jCConditional.falsepart, jCConditional.type), jCConditional.type);
            addPrunedInfo(jCExpressionTranslate);
        } else {
            jCConditional.truepart = translate(jCConditional.truepart, jCConditional.type);
            jCConditional.falsepart = translate(jCConditional.falsepart, jCConditional.type);
            this.result = jCConditional;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
        jCDoWhileLoop.body = (JCTree.JCStatement) translate(jCDoWhileLoop.body);
        jCDoWhileLoop.cond = translate(jCDoWhileLoop.cond, this.syms.booleanType);
        this.result = jCDoWhileLoop;
    }

    public JCTree visitEnumSwitch(JCTree jCTree, JCTree.JCExpression jCExpression, List<JCTree.JCCase> list) {
        JCTree.JCExpression jCExpressionSwitchValue;
        JCTree jCTreeSwitchExpression;
        EnumMapping enumMappingMapForEnum = mapForEnum(jCTree.pos(), jCExpression.type.tsym);
        make_at(jCTree.pos());
        Symbol.MethodSymbol methodSymbolLookupMethod = lookupMethod(jCTree.pos(), this.names.ordinal, jCExpression.type, List.nil());
        if (list.stream().anyMatch(new Predicate() { // from class: bj9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TreeInfo.isNullCaseLabel(((JCTree.JCCase) obj).labels.head);
            }
        })) {
            Names names = this.names;
            StringBuilder sb = new StringBuilder("s");
            int i = this.variableIndex;
            this.variableIndex = i + 1;
            sb.append(i);
            sb.append(this.target.syntheticNameChar());
            Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4112L, names.fromString(sb.toString()), jCExpression.type, this.currentMethodSym);
            JCTree.JCStatement type = this.make.at(jCTree.pos()).VarDef(varSymbol, jCExpression).setType(varSymbol.type);
            TreeMaker treeMaker = this.make;
            JCTree.JCExpression jCExpressionSwitchValue2 = enumMappingMapForEnum.switchValue(treeMaker.App(treeMaker.Select(treeMaker.Ident(varSymbol), methodSymbolLookupMethod)));
            TreeMaker treeMaker2 = this.make;
            List<JCTree.JCStatement> listOf = List.of(type);
            TreeMaker treeMaker3 = this.make;
            jCExpressionSwitchValue = treeMaker2.LetExpr(listOf, treeMaker3.Conditional(makeBinary(JCTree.Tag.NE, treeMaker3.Ident(varSymbol), makeNull()), jCExpressionSwitchValue2, makeLit(this.syms.intType, -1)).setType(jCExpressionSwitchValue2.type)).setType(jCExpressionSwitchValue2.type);
        } else {
            TreeMaker treeMaker4 = this.make;
            jCExpressionSwitchValue = enumMappingMapForEnum.switchValue(treeMaker4.App(treeMaker4.Select(jCExpression, methodSymbolLookupMethod)));
        }
        ListBuffer listBuffer = new ListBuffer();
        for (JCTree.JCCase jCCase : list) {
            if (jCCase.labels.head.hasTag(JCTree.Tag.CONSTANTCASELABEL)) {
                JCTree.JCExpression jCExpressionMakeLit = TreeInfo.isNullCaseLabel(jCCase.labels.head) ? makeLit(this.syms.intType, -1) : enumMappingMapForEnum.caseValue((Symbol.VarSymbol) TreeInfo.symbol(((JCTree.JCConstantCaseLabel) jCCase.labels.head).expr));
                TreeMaker treeMaker5 = this.make;
                listBuffer.append(treeMaker5.Case(JCTree.JCCase.STATEMENT, List.of(treeMaker5.ConstantCaseLabel(jCExpressionMakeLit)), null, jCCase.stats, null));
            } else {
                listBuffer.append(jCCase);
            }
        }
        if (jCTree.hasTag(JCTree.Tag.SWITCH)) {
            jCTreeSwitchExpression = this.make.Switch(jCExpressionSwitchValue, listBuffer.toList());
        } else {
            if (!jCTree.hasTag(JCTree.Tag.SWITCH_EXPRESSION)) {
                Assert.error();
                x1f.a();
                return null;
            }
            jCTreeSwitchExpression = this.make.SwitchExpression(jCExpressionSwitchValue, listBuffer.toList());
            jCTreeSwitchExpression.setType(jCTree.type);
        }
        patchTargets(jCTreeSwitchExpression, jCTree, jCTreeSwitchExpression);
        return jCTreeSwitchExpression;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForLoop(JCTree.JCForLoop jCForLoop) {
        jCForLoop.init = translate(jCForLoop.init);
        JCTree.JCExpression jCExpression = jCForLoop.cond;
        if (jCExpression != null) {
            jCForLoop.cond = translate(jCExpression, this.syms.booleanType);
        }
        jCForLoop.step = translate(jCForLoop.step);
        jCForLoop.body = (JCTree.JCStatement) translate(jCForLoop.body);
        this.result = jCForLoop;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        if (this.types.elemtype(jCEnhancedForLoop.expr.type) == null) {
            visitIterableForeachLoop(jCEnhancedForLoop);
        } else {
            visitArrayForeachLoop(jCEnhancedForLoop);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        this.result = access(jCIdent.sym, jCIdent, this.enclOp, false);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIf(JCTree.JCIf jCIf) {
        JCTree.JCExpression jCExpressionTranslate = translate(jCIf.cond, this.syms.booleanType);
        jCIf.cond = jCExpressionTranslate;
        if (isTrue(jCExpressionTranslate) && noClassDefIn(jCIf.elsepart)) {
            this.result = translate(jCIf.thenpart);
            addPrunedInfo(jCExpressionTranslate);
            return;
        }
        if (!isFalse(jCExpressionTranslate) || !noClassDefIn(jCIf.thenpart)) {
            jCIf.thenpart = (JCTree.JCStatement) translate(jCIf.thenpart);
            jCIf.elsepart = (JCTree.JCStatement) translate(jCIf.elsepart);
            this.result = jCIf;
        } else {
            JCTree.JCStatement jCStatement = jCIf.elsepart;
            if (jCStatement != null) {
                this.result = translate(jCStatement);
            } else {
                this.result = this.make.Skip();
            }
            addPrunedInfo(jCExpressionTranslate);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIndexed(JCTree.JCArrayAccess jCArrayAccess) {
        jCArrayAccess.indexed = (JCTree.JCExpression) translate(jCArrayAccess.indexed);
        jCArrayAccess.index = translate(jCArrayAccess.index, this.syms.intType);
        this.result = jCArrayAccess;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLambda(JCTree.JCLambda jCLambda) {
        Type type = this.currentRestype;
        try {
            Types types = this.types;
            Type typeMo73getReturnType = types.erasure(jCLambda.getDescriptorType(types)).mo73getReturnType();
            this.currentRestype = typeMo73getReturnType;
            if (typeMo73getReturnType.hasTag(TypeTag.VOID)) {
                this.currentRestype = Type.noType;
            }
            LambdaExpressionTree.BodyKind bodyKind = jCLambda.getBodyKind();
            LambdaExpressionTree.BodyKind bodyKind2 = LambdaExpressionTree.BodyKind.EXPRESSION;
            JCTree jCTree = jCLambda.body;
            jCLambda.body = bodyKind == bodyKind2 ? translate((JCTree.JCExpression) jCTree, this.currentRestype) : translate(jCTree);
            this.currentRestype = type;
            this.result = jCLambda;
        } catch (Throwable th) {
            this.currentRestype = type;
            throw th;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLetExpr(JCTree.LetExpr letExpr) {
        letExpr.defs = translate(letExpr.defs);
        letExpr.expr = translate(letExpr.expr, letExpr.type);
        this.result = letExpr;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
        if (jCMethodDecl.name == this.names.init && (this.currentClass.flags_field & 16384) != 0) {
            JCTree.JCVariableDecl jCVariableDeclParam = make_at(jCMethodDecl.pos()).Param(this.names.fromString(this.target.syntheticNameChar() + PsiKeyword.ENUM + this.target.syntheticNameChar() + "name"), this.syms.stringType, jCMethodDecl.sym);
            JCTree.JCModifiers jCModifiers = jCVariableDeclParam.mods;
            jCModifiers.flags = jCModifiers.flags | 4096;
            Symbol.VarSymbol varSymbol = jCVariableDeclParam.sym;
            varSymbol.flags_field = varSymbol.flags_field | 4096;
            JCTree.JCVariableDecl jCVariableDeclParam2 = this.make.Param(this.names.fromString(this.target.syntheticNameChar() + PsiKeyword.ENUM + this.target.syntheticNameChar() + "ordinal"), this.syms.intType, jCMethodDecl.sym);
            JCTree.JCModifiers jCModifiers2 = jCVariableDeclParam2.mods;
            jCModifiers2.flags = jCModifiers2.flags | 4096;
            Symbol.VarSymbol varSymbol2 = jCVariableDeclParam2.sym;
            varSymbol2.flags_field = varSymbol2.flags_field | 4096;
            Symbol.MethodSymbol methodSymbol = jCMethodDecl.sym;
            jCMethodDecl.params = jCMethodDecl.params.prepend(jCVariableDeclParam2).prepend(jCVariableDeclParam);
            List<Symbol.VarSymbol> listPrepend = methodSymbol.extraParams.prepend(jCVariableDeclParam2.sym);
            methodSymbol.extraParams = listPrepend;
            methodSymbol.extraParams = listPrepend.prepend(jCVariableDeclParam.sym);
            Type typeErasure = methodSymbol.erasure(this.types);
            methodSymbol.erasure_field = new Type.MethodType(typeErasure.mo71getParameterTypes().prepend(this.syms.intType).prepend(this.syms.stringType), typeErasure.mo73getReturnType(), typeErasure.mo74getThrownTypes(), this.syms.methodClass);
        }
        Type type = this.currentRestype;
        JCTree.JCMethodDecl jCMethodDecl2 = this.currentMethodDef;
        Symbol.MethodSymbol methodSymbol2 = this.currentMethodSym;
        int i = this.variableIndex;
        try {
            this.currentRestype = this.types.erasure(jCMethodDecl.type.mo73getReturnType());
            this.currentMethodDef = jCMethodDecl;
            this.currentMethodSym = jCMethodDecl.sym;
            this.variableIndex = 0;
            visitMethodDefInternal(jCMethodDecl);
        } finally {
            this.currentRestype = type;
            this.currentMethodDef = jCMethodDecl2;
            this.currentMethodSym = methodSymbol2;
            this.variableIndex = i;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
        Symbol.ModuleSymbol moduleSymbol = jCModuleDecl.sym;
        Symbol.ClassSymbol classSymbol = moduleSymbol.module_info;
        classSymbol.setAttributes(moduleSymbol);
        classSymbol.flags_field |= 2251799813685248L;
        createInfoClass(List.nil(), jCModuleDecl.sym.module_info);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [A, com.sun.tools.javac.tree.JCTree$JCExpression] */
    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewArray(JCTree.JCNewArray jCNewArray) {
        jCNewArray.elemtype = (JCTree.JCExpression) translate(jCNewArray.elemtype);
        for (List list = jCNewArray.dims; list.tail != null; list = list.tail) {
            A a = list.head;
            if (a != 0) {
                list.head = translate((JCTree.JCExpression) a, this.syms.intType);
            }
        }
        jCNewArray.elems = translate(jCNewArray.elems, this.types.elemtype(jCNewArray.type));
        this.result = jCNewArray;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewClass(JCTree.JCNewClass jCNewClass) {
        JCTree.JCExpression jCExpressionMakeThis;
        Symbol symbol = jCNewClass.constructor.owner;
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbol;
        boolean z = (symbol.flags() & 16384) != 0;
        List<Type> listMo71getParameterTypes = jCNewClass.constructor.type.mo71getParameterTypes();
        if (z) {
            listMo71getParameterTypes = listMo71getParameterTypes.prepend(this.syms.intType).prepend(this.syms.stringType);
        }
        jCNewClass.args = boxArgs(listMo71getParameterTypes, jCNewClass.args, jCNewClass.varargsElement);
        jCNewClass.varargsElement = null;
        if (classSymbol.isDirectlyOrIndirectlyLocal() && !classSymbol.isStatic()) {
            jCNewClass.args = jCNewClass.args.appendList(loadFreevars(jCNewClass.pos(), freevars(classSymbol)));
        }
        Symbol symbolAccessConstructor = accessConstructor(jCNewClass.pos(), jCNewClass.constructor);
        if (symbolAccessConstructor != jCNewClass.constructor) {
            jCNewClass.args = jCNewClass.args.append(makeNull());
            jCNewClass.constructor = symbolAccessConstructor;
        }
        if (classSymbol.hasOuterInstance()) {
            JCTree.JCExpression jCExpression = jCNewClass.encl;
            if (jCExpression != null) {
                jCExpressionMakeThis = this.attr.makeNullCheck((JCTree.JCExpression) translate(jCExpression));
                jCExpressionMakeThis.type = jCNewClass.encl.type;
            } else {
                jCExpressionMakeThis = classSymbol.isDirectlyOrIndirectlyLocal() ? makeThis(jCNewClass.pos(), classSymbol.innermostAccessibleEnclosingClass()) : makeOwnerThis(jCNewClass.pos(), classSymbol, false);
            }
            jCNewClass.args = jCNewClass.args.prepend(jCExpressionMakeThis);
        }
        jCNewClass.encl = null;
        JCTree.JCClassDecl jCClassDecl = jCNewClass.def;
        if (jCClassDecl != null) {
            translate(jCClassDecl);
            jCNewClass.clazz = access(make_at(jCNewClass.clazz.pos()).Ident(jCNewClass.def.sym));
            jCNewClass.def = null;
        } else {
            jCNewClass.clazz = access(classSymbol, jCNewClass.clazz, this.enclOp, false);
        }
        this.result = jCNewClass;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitPackageDef(JCTree.JCPackageDecl jCPackageDecl) {
        if (needPackageInfoClass(jCPackageDecl)) {
            Symbol.PackageSymbol packageSymbol = jCPackageDecl.packge;
            Symbol.ClassSymbol classSymbol = packageSymbol.package_info;
            classSymbol.setAttributes(packageSymbol);
            classSymbol.flags_field |= 5632;
            Type.ClassType classType = (Type.ClassType) classSymbol.type;
            classType.supertype_field = this.syms.objectType;
            classType.interfaces_field = List.nil();
            createInfoClass(jCPackageDecl.annotations, classSymbol);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitParens(JCTree.JCParens jCParens) {
        JCTree jCTreeTranslate = translate(jCParens.expr);
        JCTree.JCExpression jCExpression = jCParens.expr;
        JCTree jCTree = jCParens;
        if (jCTreeTranslate != jCExpression) {
            jCTree = jCTreeTranslate;
        }
        this.result = jCTree;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReturn(JCTree.JCReturn jCReturn) {
        JCTree.JCExpression jCExpression = jCReturn.expr;
        if (jCExpression != null) {
            jCReturn.expr = translate(jCExpression, this.currentRestype);
        }
        this.result = jCReturn;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
        boolean z = jCFieldAccess.selected.hasTag(JCTree.Tag.SELECT) && TreeInfo.name(jCFieldAccess.selected) == this.names._super && !this.types.isDirectSuperInterface(((JCTree.JCFieldAccess) jCFieldAccess.selected).selected.type.tsym, this.currentClass);
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) translate(jCFieldAccess.selected);
        jCFieldAccess.selected = jCExpression;
        if (jCFieldAccess.name == this.names._class && jCExpression.type.isPrimitiveOrVoid()) {
            this.result = classOf(jCFieldAccess.selected);
            return;
        }
        if (jCFieldAccess.name == this.names._super && this.types.isDirectSuperInterface(jCFieldAccess.selected.type.tsym, this.currentClass)) {
            Assert.checkNonNull(this.types.asSuper(this.currentClass.type, jCFieldAccess.selected.type.tsym));
            this.result = jCFieldAccess;
            return;
        }
        Name name = jCFieldAccess.name;
        Names names = this.names;
        if (name == names._this || name == names._super) {
            this.result = makeThis(jCFieldAccess.pos(), jCFieldAccess.selected.type.tsym);
        } else {
            this.result = access(jCFieldAccess.sym, jCFieldAccess, this.enclOp, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JCTree visitStringSwitch(JCTree jCTree, JCTree.JCExpression jCExpression, List<JCTree.JCCase> list) {
        JCTree.JCLiteral jCLiteralLiteral;
        int size = list.size();
        if (size == 0) {
            return this.make.at(jCTree.pos()).Exec(this.attr.makeNullCheck(jCExpression));
        }
        ListBuffer listBuffer = new ListBuffer();
        int i = 1;
        int i2 = size + 1;
        LinkedHashMap linkedHashMap = new LinkedHashMap(i2, 1.0f);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(i2, 1.0f);
        int i3 = 0;
        JCTree.JCCase jCCase = null;
        int i4 = -1;
        for (JCTree.JCCase jCCase2 : list) {
            if (jCCase2.labels.head.hasTag(JCTree.Tag.CONSTANTCASELABEL)) {
                if (TreeInfo.isNullCaseLabel(jCCase2.labels.head)) {
                    i4 = i3;
                    jCCase = jCCase2;
                } else {
                    String str = (String) ((JCTree.JCConstantCaseLabel) jCCase2.labels.head).expr.type.constValue();
                    Assert.checkNull((Integer) linkedHashMap.put(str, Integer.valueOf(i3)));
                    int iHashCode = str.hashCode();
                    Set set = (Set) linkedHashMap2.get(Integer.valueOf(iHashCode));
                    if (set == null) {
                        LinkedHashSet linkedHashSet = new LinkedHashSet(1, 1.0f);
                        linkedHashSet.add(str);
                        linkedHashMap2.put(Integer.valueOf(iHashCode), linkedHashSet);
                    } else {
                        Assert.check(set.add(str));
                    }
                }
            }
            i3++;
        }
        Names names = this.names;
        StringBuilder sb = new StringBuilder("s");
        int i5 = this.variableIndex;
        this.variableIndex = i5 + 1;
        sb.append(i5);
        sb.append(this.target.syntheticNameChar());
        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4112L, names.fromString(sb.toString()), this.syms.stringType, this.currentMethodSym);
        listBuffer.append(this.make.at(jCTree.pos()).VarDef(varSymbol, jCExpression).setType(varSymbol.type));
        Names names2 = this.names;
        StringBuilder sb2 = new StringBuilder("tmp");
        int i6 = this.variableIndex;
        this.variableIndex = i6 + 1;
        sb2.append(i6);
        sb2.append(this.target.syntheticNameChar());
        Symbol.VarSymbol varSymbol2 = new Symbol.VarSymbol(4096L, names2.fromString(sb2.toString()), this.syms.intType, this.currentMethodSym);
        TreeMaker treeMaker = this.make;
        JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) treeMaker.VarDef(varSymbol2, treeMaker.Literal(TypeTag.INT, -1)).setType(varSymbol2.type);
        JCTree.JCExpression jCExpression2 = jCVariableDecl.init;
        Type.JCPrimitiveType jCPrimitiveType = this.syms.intType;
        varSymbol2.type = jCPrimitiveType;
        jCExpression2.type = jCPrimitiveType;
        listBuffer.append(jCVariableDecl);
        ListBuffer listBuffer2 = new ListBuffer();
        JCTree.JCSwitch jCSwitchSwitch = this.make.Switch(makeCall(this.make.Ident(varSymbol), this.names.hashCode, List.nil()).setType(this.syms.intType), listBuffer2.toList());
        Iterator it = linkedHashMap2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Integer num = (Integer) entry.getKey();
            num.intValue();
            Set set2 = (Set) entry.getValue();
            Assert.check(set2.size() >= i ? i : 0);
            Iterator it2 = set2.iterator();
            JCTree.JCIf jCIfIf = null;
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                Iterator it3 = it;
                Iterator it4 = it2;
                JCTree.JCMethodInvocation jCMethodInvocationMakeCall = makeCall(this.make.Ident(varSymbol), this.names.equals, List.of(this.make.Literal(str2)));
                TreeMaker treeMaker2 = this.make;
                jCIfIf = treeMaker2.If(jCMethodInvocationMakeCall, treeMaker2.Exec(treeMaker2.Assign(treeMaker2.Ident(varSymbol2), this.make.Literal(linkedHashMap.get(str2))).setType(varSymbol2.type)), jCIfIf);
                it = it3;
                it2 = it4;
                i4 = i4;
            }
            Iterator it5 = it;
            ListBuffer listBuffer3 = new ListBuffer();
            JCTree.JCBreak jCBreakBreak = this.make.Break(null);
            jCBreakBreak.target = jCSwitchSwitch;
            listBuffer3.append(jCIfIf).append(jCBreakBreak);
            TreeMaker treeMaker3 = this.make;
            listBuffer2.append(treeMaker3.Case(JCTree.JCCase.STATEMENT, List.of(treeMaker3.ConstantCaseLabel(treeMaker3.Literal(num))), null, listBuffer3.toList(), null));
            it = it5;
            i = 1;
        }
        int i7 = i4;
        jCSwitchSwitch.cases = listBuffer2.toList();
        if (jCCase != null) {
            TreeMaker treeMaker4 = this.make;
            JCTree.JCBinary jCBinaryMakeBinary = makeBinary(JCTree.Tag.NE, treeMaker4.Ident(varSymbol), makeNull());
            TreeMaker treeMaker5 = this.make;
            listBuffer.append(treeMaker4.If(jCBinaryMakeBinary, jCSwitchSwitch, treeMaker5.Exec(treeMaker5.Assign(treeMaker5.Ident(varSymbol2), this.make.Literal(Integer.valueOf(i7))).setType(varSymbol2.type))).setType((Type) this.syms.intType));
        } else {
            listBuffer.append(jCSwitchSwitch);
        }
        ListBuffer listBuffer4 = new ListBuffer();
        for (JCTree.JCCase jCCase3 : list) {
            if (!jCCase3.labels.head.hasTag(JCTree.Tag.CONSTANTCASELABEL)) {
                jCLiteralLiteral = null;
            } else if (jCCase3 == jCCase) {
                jCLiteralLiteral = this.make.Literal(Integer.valueOf(i7));
            } else {
                jCLiteralLiteral = this.make.Literal(linkedHashMap.get((String) TreeInfo.skipParens(((JCTree.JCConstantCaseLabel) jCCase3.labels.head).expr).type.constValue()));
            }
            TreeMaker treeMaker6 = this.make;
            listBuffer4.append(treeMaker6.Case(JCTree.JCCase.STATEMENT, jCLiteralLiteral == null ? List.of(treeMaker6.DefaultCaseLabel()) : List.of(treeMaker6.ConstantCaseLabel(jCLiteralLiteral)), null, jCCase3.stats, null));
        }
        boolean zHasTag = jCTree.hasTag(JCTree.Tag.SWITCH);
        TreeMaker treeMaker7 = this.make;
        if (zHasTag) {
            JCTree.JCSwitch jCSwitchSwitch2 = treeMaker7.Switch(treeMaker7.Ident(varSymbol2), listBuffer4.toList());
            patchTargets(jCSwitchSwitch2, jCTree, jCSwitchSwitch2);
            listBuffer.append(jCSwitchSwitch2);
            JCTree.JCBlock jCBlockBlock = this.make.Block(0L, listBuffer.toList());
            jCBlockBlock.bracePos = TreeInfo.endPos(jCTree);
            return jCBlockBlock;
        }
        JCTree.JCSwitchExpression jCSwitchExpressionSwitchExpression = treeMaker7.SwitchExpression(treeMaker7.Ident(varSymbol2), listBuffer4.toList());
        patchTargets(jCSwitchExpressionSwitchExpression, jCTree, jCSwitchExpressionSwitchExpression);
        jCSwitchExpressionSwitchExpression.setType(jCTree.type);
        JCTree.LetExpr LetExpr = this.make.LetExpr(listBuffer.toList(), jCSwitchExpressionSwitchExpression);
        LetExpr.needsCond = true;
        LetExpr.setType(jCTree.type);
        return LetExpr;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitch(JCTree.JCSwitch jCSwitch) {
        boolean z = jCSwitch.patternSwitch;
        handleSwitch(jCSwitch, jCSwitch.selector, z ? addDefaultIfNeeded(z, jCSwitch.wasEnumSelector, jCSwitch.cases) : jCSwitch.cases);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
        handleSwitch(jCSwitchExpression, jCSwitchExpression.selector, addDefaultIfNeeded(jCSwitchExpression.patternSwitch, jCSwitchExpression.wasEnumSelector, jCSwitchExpression.cases));
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTry(JCTree.JCTry jCTry) {
        if (jCTry.resources.nonEmpty()) {
            this.result = makeTwrTry(jCTry);
            return;
        }
        boolean zNonEmpty = jCTry.body.getStatements().nonEmpty();
        boolean zNonEmpty2 = jCTry.catchers.nonEmpty();
        JCTree.JCBlock jCBlock = jCTry.finalizer;
        boolean z = jCBlock != null && jCBlock.getStatements().nonEmpty();
        if (!zNonEmpty2 && !z) {
            this.result = translate(jCTry.body);
            return;
        }
        if (zNonEmpty) {
            super.visitTry(jCTry);
        } else if (z) {
            this.result = translate(jCTry.finalizer);
        } else {
            this.result = translate(jCTry.body);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
        jCTypeCast.clazz = translate(jCTypeCast.clazz);
        boolean zIsPrimitive = jCTypeCast.type.isPrimitive();
        boolean zIsPrimitive2 = jCTypeCast.expr.type.isPrimitive();
        JCTree.JCExpression jCExpression = jCTypeCast.expr;
        if (zIsPrimitive != zIsPrimitive2) {
            jCTypeCast.expr = translate(jCExpression, jCTypeCast.type);
        } else {
            jCTypeCast.expr = (JCTree.JCExpression) translate(jCExpression);
        }
        this.result = jCTypeCast;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
        JCTree.JCExpression jCExpressionMakeBinary;
        Object objExec;
        if (!jCInstanceOf.expr.type.isPrimitive() && !jCInstanceOf.pattern.type.isPrimitive()) {
            jCInstanceOf.expr = (JCTree.JCExpression) translate(jCInstanceOf.expr);
            jCInstanceOf.pattern = translate(jCInstanceOf.pattern);
            this.result = jCInstanceOf;
            return;
        }
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) translate(jCInstanceOf.expr);
        if (this.types.isUnconditionallyExactTypeBased(jCInstanceOf.expr.type, jCInstanceOf.pattern.type)) {
            objExec = this.make.Exec(jCExpression);
            jCExpressionMakeBinary = this.make.Literal(TypeTag.BOOLEAN, 1).setType(this.syms.booleanType.constType(1));
        } else {
            if (jCInstanceOf.expr.type.isPrimitive()) {
                jCExpressionMakeBinary = getExactnessCheck(jCInstanceOf, jCExpression);
            } else {
                if (!jCInstanceOf.expr.type.isReference()) {
                    throw Assert.error("Non primitive or reference type: " + jCInstanceOf.expr.type);
                }
                Types types = this.types;
                if (types.isUnconditionallyExactTypeBased(types.unboxedType(jCInstanceOf.expr.type), jCInstanceOf.pattern.type)) {
                    jCExpressionMakeBinary = makeBinary(JCTree.Tag.NE, jCExpression, makeNull());
                } else {
                    Names names = this.names;
                    StringBuilder sb = new StringBuilder("tmp");
                    int i = this.variableIndex;
                    this.variableIndex = i + 1;
                    sb.append(i);
                    sb.append(this.target.syntheticNameChar());
                    Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4112L, names.fromString(sb.toString()), this.types.erasure(jCInstanceOf.expr.type), this.currentMethodSym);
                    JCTree.JCVariableDecl jCVariableDeclVarDef = this.make.at(jCInstanceOf.pos()).VarDef(varSymbol, jCExpression);
                    JCTree.JCBinary jCBinaryMakeBinary = makeBinary(JCTree.Tag.NE, this.make.Ident(varSymbol), makeNull());
                    jCExpressionMakeBinary = this.types.unboxedType(jCInstanceOf.expr.type).isPrimitive() ? makeBinary(JCTree.Tag.AND, jCBinaryMakeBinary, getExactnessCheck(jCInstanceOf, boxIfNeeded(this.make.Ident(varSymbol), this.types.unboxedType(jCInstanceOf.expr.type)))) : makeBinary(JCTree.Tag.AND, jCBinaryMakeBinary, this.make.at(jCInstanceOf.pos()).TypeTest(this.make.Ident(varSymbol), this.make.Type(this.types.boxedClass(jCInstanceOf.pattern.type).type)).setType((Type) this.syms.booleanType));
                    objExec = jCVariableDeclVarDef;
                }
            }
            objExec = null;
        }
        if (objExec != null) {
            jCExpressionMakeBinary = this.make.LetExpr(List.of(objExec), jCExpressionMakeBinary);
        }
        this.result = jCExpressionMakeBinary.setType((Type) this.syms.booleanType);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUnary(JCTree.JCUnary jCUnary) {
        boolean zIsIncOrDecUnaryOp = jCUnary.getTag().isIncOrDecUnaryOp();
        if (zIsIncOrDecUnaryOp && !jCUnary.arg.type.isPrimitive()) {
            switch (AnonymousClass4.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCUnary.getTag().ordinal()]) {
                case 8:
                case 9:
                    this.result = translate(makeAssignop(jCUnary.hasTag(JCTree.Tag.PREINC) ? JCTree.Tag.PLUS_ASG : JCTree.Tag.MINUS_ASG, jCUnary.arg, this.make.Literal(1)), jCUnary.type);
                    break;
                case 10:
                case 11:
                    this.result = translate(lowerBoxedPostop(jCUnary), jCUnary.type);
                    break;
                default:
                    x01.a(jCUnary);
                    break;
            }
            return;
        }
        jCUnary.arg = boxIfNeeded((JCTree.JCExpression) translate(jCUnary.arg, jCUnary), jCUnary.type);
        if (jCUnary.hasTag(JCTree.Tag.NOT) && jCUnary.arg.type.constValue() != null) {
            jCUnary.type = this.cfolder.fold1(257, jCUnary.arg.type);
        }
        if (zIsIncOrDecUnaryOp && jCUnary.arg.hasTag(JCTree.Tag.APPLY)) {
            this.result = jCUnary.arg;
        } else {
            this.result = jCUnary;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        Symbol.MethodSymbol methodSymbol = this.currentMethodSym;
        int i = this.variableIndex;
        jCVariableDecl.mods = (JCTree.JCModifiers) translate(jCVariableDecl.mods);
        jCVariableDecl.vartype = (JCTree.JCExpression) translate(jCVariableDecl.vartype);
        if (this.currentMethodSym == null) {
            this.currentMethodSym = new Symbol.MethodSymbol((jCVariableDecl.mods.flags & 8) | 1048576, this.names.empty, null, this.currentClass);
        }
        try {
            JCTree.JCExpression jCExpression = jCVariableDecl.init;
            if (jCExpression != null) {
                jCVariableDecl.init = translate(jCExpression, jCVariableDecl.type);
            }
            this.result = jCVariableDecl;
        } finally {
            this.currentMethodSym = methodSymbol;
            this.variableIndex = i;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
        jCWhileLoop.cond = translate(jCWhileLoop.cond, this.syms.booleanType);
        jCWhileLoop.body = (JCTree.JCStatement) translate(jCWhileLoop.body);
        this.result = jCWhileLoop;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitYield(JCTree.JCYield jCYield) {
        jCYield.value = translate(jCYield.value, jCYield.target.type);
        this.result = jCYield;
    }

    public static final class TypePairs {
        private final Symbol.TypeSymbol from;
        private final Symbol.TypeSymbol to;

        public TypePairs(Symbol.TypeSymbol typeSymbol, Symbol.TypeSymbol typeSymbol2) {
            this.from = typeSymbol;
            this.to = typeSymbol2;
        }

        public static HashMap<TypePairs, String> initialize(Symtab symtab) {
            HashMap<TypePairs, String> map = new HashMap<>();
            map.put(new TypePairs(symtab.byteType, symtab.charType), "isIntToCharExact");
            map.put(new TypePairs(symtab.shortType, symtab.byteType), "isIntToByteExact");
            map.put(new TypePairs(symtab.shortType, symtab.charType), "isIntToCharExact");
            map.put(new TypePairs(symtab.charType, symtab.byteType), "isIntToByteExact");
            map.put(new TypePairs(symtab.charType, symtab.shortType), "isIntToShortExact");
            map.put(new TypePairs(symtab.intType, symtab.byteType), "isIntToByteExact");
            map.put(new TypePairs(symtab.intType, symtab.shortType), "isIntToShortExact");
            map.put(new TypePairs(symtab.intType, symtab.charType), "isIntToCharExact");
            map.put(new TypePairs(symtab.intType, symtab.floatType), "isIntToFloatExact");
            map.put(new TypePairs(symtab.longType, symtab.byteType), "isLongToByteExact");
            map.put(new TypePairs(symtab.longType, symtab.shortType), "isLongToShortExact");
            map.put(new TypePairs(symtab.longType, symtab.charType), "isLongToCharExact");
            map.put(new TypePairs(symtab.longType, symtab.intType), "isLongToIntExact");
            map.put(new TypePairs(symtab.longType, symtab.floatType), "isLongToFloatExact");
            map.put(new TypePairs(symtab.longType, symtab.doubleType), "isLongToDoubleExact");
            map.put(new TypePairs(symtab.floatType, symtab.byteType), "isFloatToByteExact");
            map.put(new TypePairs(symtab.floatType, symtab.shortType), "isFloatToShortExact");
            map.put(new TypePairs(symtab.floatType, symtab.charType), "isFloatToCharExact");
            map.put(new TypePairs(symtab.floatType, symtab.intType), "isFloatToIntExact");
            map.put(new TypePairs(symtab.floatType, symtab.longType), "isFloatToLongExact");
            map.put(new TypePairs(symtab.doubleType, symtab.byteType), "isDoubleToByteExact");
            map.put(new TypePairs(symtab.doubleType, symtab.shortType), "isDoubleToShortExact");
            map.put(new TypePairs(symtab.doubleType, symtab.charType), "isDoubleToCharExact");
            map.put(new TypePairs(symtab.doubleType, symtab.intType), "isDoubleToIntExact");
            map.put(new TypePairs(symtab.doubleType, symtab.longType), "isDoubleToLongExact");
            map.put(new TypePairs(symtab.doubleType, symtab.floatType), "isDoubleToFloatExact");
            return map;
        }

        public static TypePairs of(Symtab symtab, Type type, Type type2) {
            if (type == symtab.byteType || type == symtab.shortType || type == symtab.charType) {
                type = symtab.intType;
            }
            return new TypePairs(type, type2);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof TypePairs)) {
                return false;
            }
            TypePairs typePairs = (TypePairs) obj;
            return Objects.equals(this.to, typePairs.to) && Objects.equals(this.from, typePairs.from);
        }

        public Symbol.TypeSymbol from() {
            return this.from;
        }

        public final int hashCode() {
            return (Objects.hashCode(this.from) * 31) + Objects.hashCode(this.to);
        }

        public Symbol.TypeSymbol to() {
            return this.to;
        }

        public final String toString() {
            return "TypePairs[from=" + Objects.toString(this.from) + ", to=" + Objects.toString(this.to) + "]";
        }

        public TypePairs(Type type, Type type2) {
            this(type.tsym, type2.tsym);
        }
    }

    public class LowerSignatureGenerator extends Types.SignatureGenerator {
        StringBuilder sb;

        /* JADX WARN: Illegal instructions before constructor call */
        public LowerSignatureGenerator() {
            Types types = Lower.this.types;
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

    /* JADX WARN: Multi-variable type inference failed */
    public <T extends JCTree.JCExpression> T translate(T t, Type type) {
        if (t == null) {
            return null;
        }
        return (T) boxIfNeeded((JCTree.JCExpression) translate(t), type);
    }

    public <T extends JCTree> T translate(T t, JCTree.JCExpression jCExpression) {
        JCTree.JCExpression jCExpression2 = this.enclOp;
        this.enclOp = jCExpression;
        T t2 = (T) translate(t);
        this.enclOp = jCExpression2;
        return t2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [A, com.sun.tools.javac.tree.JCTree$JCExpression] */
    public <T extends JCTree.JCExpression> List<T> translate(List<T> list, Type type) {
        if (list == null) {
            return null;
        }
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            list2.head = translate((JCTree.JCExpression) list2.head, type);
        }
        return list;
    }

    public JCTree.JCExpression boxPrimitive(JCTree.JCExpression jCExpression) {
        return boxPrimitive(jCExpression, this.types.boxedClass(jCExpression.type).type);
    }

    public JCTree.JCVariableDecl outerThisDef(int i, Symbol.ClassSymbol classSymbol) {
        return makeOuterThisVarDecl(i, makeOuterThisVarSymbol(classSymbol, 4112L));
    }

    public List<JCTree.JCVariableDecl> freevarDefs(int i, List<Symbol.VarSymbol> list, Symbol symbol) {
        return freevarDefs(i, list, symbol, 562949953421312L);
    }

    public JCTree.JCClassDecl makeEmptyClass(long j, Symbol.ClassSymbol classSymbol) {
        return makeEmptyClass(j, classSymbol, null, true);
    }

    public JCTree.JCExpression abstractRval(JCTree.JCExpression jCExpression, TreeBuilder treeBuilder) {
        return abstractRval(jCExpression, jCExpression.type, treeBuilder);
    }

    public JCTree.JCExpression access(JCTree.JCExpression jCExpression) {
        Symbol symbol = TreeInfo.symbol(jCExpression);
        return symbol == null ? jCExpression : access(symbol, jCExpression, null, false);
    }
}

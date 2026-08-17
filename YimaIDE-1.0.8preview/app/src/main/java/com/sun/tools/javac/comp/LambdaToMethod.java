package com.sun.tools.javac.comp;

import com.intellij.psi.PsiKeyword;
import com.sun.source.tree.LambdaExpressionTree;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.jvm.PoolConstant;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.DiagnosticSource;
import com.sun.tools.javac.util.InvalidUtfException;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.lang.model.element.ElementKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LambdaToMethod extends TreeTranslator {
    public static final int FLAG_BRIDGES = 4;
    public static final int FLAG_MARKERS = 2;
    public static final int FLAG_SERIALIZABLE = 1;
    protected static final Context.Key<LambdaToMethod> unlambdaKey = new Context.Key<>();
    private final Attr attr;
    private Env<AttrContext> attrEnv;
    private final boolean debugLinesOrVars;
    private final boolean deduplicateLambdas;
    private final JCDiagnostic.Factory diags;
    private final boolean dumpLambdaToMethodStats;
    private final boolean forceSerializable;
    private KlassInfo kInfo;
    private LambdaTranslationContext lambdaContext;
    private final Log log;
    private final Lower lower;
    private TreeMaker make;
    private final Names names;
    private final Operators operators;
    private Symbol.VarSymbol pendingVar;
    private final Resolve rs;
    private final Symtab syms;
    private final TransTypes transTypes;
    private final Types types;
    private final boolean verboseDeduplication;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.LambdaToMethod$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind;

        static {
            int[] iArr = new int[JCTree.JCMemberReference.ReferenceKind.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind = iArr;
            try {
                iArr[JCTree.JCMemberReference.ReferenceKind.IMPLICIT_INNER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.SUPER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.BOUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.UNBOUND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.STATIC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.TOPLEVEL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.ARRAY_CTOR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public class DedupedLambda {
        private int hashCode;
        private final Symbol.MethodSymbol symbol;
        private final JCTree tree;

        public DedupedLambda(Symbol.MethodSymbol methodSymbol, JCTree jCTree) {
            this.symbol = methodSymbol;
            this.tree = jCTree;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof DedupedLambda)) {
                return false;
            }
            DedupedLambda dedupedLambda = (DedupedLambda) obj;
            return LambdaToMethod.this.types.isSameType(this.symbol.asType(), dedupedLambda.symbol.asType()) && new TreeDiffer(LambdaToMethod.this.types, this.symbol.params(), dedupedLambda.symbol.params()).scan(this.tree, dedupedLambda.tree);
        }

        public int hashCode() {
            int i = this.hashCode;
            if (i != 0) {
                return i;
            }
            int iHash = TreeHasher.hash(LambdaToMethod.this.types, this.tree, this.symbol.params());
            this.hashCode = iHash;
            return iHash;
        }
    }

    private LambdaToMethod(Context context) {
        context.put(unlambdaKey, this);
        this.diags = JCDiagnostic.Factory.instance(context);
        this.log = Log.instance(context);
        this.lower = Lower.instance(context);
        this.names = Names.instance(context);
        this.syms = Symtab.instance(context);
        this.rs = Resolve.instance(context);
        this.operators = Operators.instance(context);
        this.make = TreeMaker.instance(context);
        this.types = Types.instance(context);
        this.transTypes = TransTypes.instance(context);
        Options optionsInstance = Options.instance(context);
        this.dumpLambdaToMethodStats = optionsInstance.isSet("debug.dumpLambdaToMethodStats");
        this.attr = Attr.instance(context);
        this.forceSerializable = optionsInstance.isSet("forceSerializable");
        Option option = Option.G_CUSTOM;
        this.debugLinesOrVars = (optionsInstance.isUnset(option) || optionsInstance.isSet(option, "lines")) || (optionsInstance.isUnset(option) ? optionsInstance.isSet(Option.G) : optionsInstance.isSet(option, "vars"));
        this.verboseDeduplication = optionsInstance.isSet("debug.dumpLambdaToMethodDeduplication");
        this.deduplicateLambdas = optionsInstance.getBoolean("deduplicateLambdas", true);
    }

    private void addDeserializationCase(Symbol.MethodHandleSymbol methodHandleSymbol, Type type, Symbol.MethodSymbol methodSymbol, JCDiagnostic.DiagnosticPosition diagnosticPosition, List<PoolConstant.LoadableConstant> list, Type.MethodType methodType) {
        String strClassSig = classSig(type);
        String string = methodSymbol.getSimpleName().toString();
        String strTypeSig = typeSig(this.types.erasure(methodSymbol.type));
        Symbol symbolBaseSymbol = methodHandleSymbol.baseSymbol();
        Symbol symbolBaseSymbol2 = symbolBaseSymbol.baseSymbol();
        Symbol.MethodHandleSymbol methodHandleSymbolAsHandle = (symbolBaseSymbol == symbolBaseSymbol2 || symbolBaseSymbol2.owner != this.syms.objectType.tsym) ? methodHandleSymbol : ((Symbol.MethodSymbol) symbolBaseSymbol2).asHandle();
        String strClassSig2 = classSig(this.types.erasure(methodHandleSymbolAsHandle.owner.type));
        String string2 = methodHandleSymbolAsHandle.getQualifiedName().toString();
        String strTypeSig2 = typeSig(this.types.erasure(methodHandleSymbolAsHandle.type));
        Type.JCPrimitiveType jCPrimitiveType = this.syms.intType;
        JCTree.JCExpression jCExpressionEqTest = eqTest(jCPrimitiveType, deserGetter("getImplMethodKind", jCPrimitiveType), this.make.Literal(Integer.valueOf(methodHandleSymbolAsHandle.referenceKind())));
        ListBuffer listBuffer = new ListBuffer();
        int i = 0;
        for (Iterator<Type> it = methodType.mo71getParameterTypes().iterator(); it.hasNext(); it = it) {
            listBuffer.add(this.make.TypeCast(this.types.erasure(it.next()), deserGetter("getCapturedArg", this.syms.objectType, new ListBuffer().append(this.syms.intType).toList(), new ListBuffer().append(this.make.Literal(Integer.valueOf(i))).toList())));
            i++;
        }
        JCTree.JCIf jCIfIf = this.make.If(deserTest(deserTest(deserTest(deserTest(deserTest(jCExpressionEqTest, "getFunctionalInterfaceClass", strClassSig), "getFunctionalInterfaceMethodName", string), "getFunctionalInterfaceMethodSignature", strTypeSig), "getImplClass", strClassSig2), "getImplMethodSignature", strTypeSig2), this.make.Return(makeIndyCall(diagnosticPosition, this.syms.lambdaMetafactory, this.names.altMetafactory, list, methodType, listBuffer.toList(), methodSymbol.name)), null);
        ListBuffer listBuffer2 = (ListBuffer) this.kInfo.deserializeCases.get(string2);
        if (listBuffer2 == null) {
            listBuffer2 = new ListBuffer();
            this.kInfo.deserializeCases.put(string2, listBuffer2);
        }
        listBuffer2.append(jCIfIf);
    }

    private void apportionTypeAnnotations(JCTree.JCLambda jCLambda, Supplier<List<Attribute.TypeCompound>> supplier, Consumer<List<Attribute.TypeCompound>> consumer, Consumer<List<Attribute.TypeCompound>> consumer2) {
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        for (Attribute.TypeCompound typeCompound : supplier.get()) {
            if (typeCompound.hasUnknownPosition()) {
                typeCompound.tryFixPosition();
            }
            if (typeCompound.position.onLambda == jCLambda) {
                listBuffer2.append(typeCompound);
            } else {
                listBuffer.append(typeCompound);
            }
        }
        if (listBuffer2.nonEmpty()) {
            consumer.accept(listBuffer.toList());
            consumer2.accept(listBuffer2.toList());
        }
    }

    private String classSig(Type type) {
        try {
            L2MSignatureGenerator l2MSignatureGenerator = new L2MSignatureGenerator(false);
            l2MSignatureGenerator.assembleClassSig(type);
            return l2MSignatureGenerator.toString();
        } catch (Types.SignatureGenerator.InvalidSignatureException e) {
            Symbol.ClassSymbol classSymbol = this.attrEnv.enclClass.sym;
            this.log.error(CompilerProperties.Errors.CannotGenerateClass(classSymbol, CompilerProperties.Fragments.IllegalSignature(classSymbol, e.type())));
            return "<ERRONEOUS>";
        }
    }

    private JCTree.JCExpression deserGetter(String str, Type type, List<Type> list, List<JCTree.JCExpression> list2) {
        Type.MethodType methodType = new Type.MethodType(list, type, List.nil(), this.syms.methodClass);
        Symbol symbolResolveQualifiedMethod = this.rs.resolveQualifiedMethod(null, this.attrEnv, this.syms.serializedLambdaType, this.names.fromString(str), list, List.nil());
        TreeMaker treeMaker = this.make;
        List<JCTree.JCExpression> listNil = List.nil();
        TreeMaker treeMaker2 = this.make;
        return treeMaker.Apply(listNil, treeMaker2.Select(treeMaker2.Ident(this.kInfo.deserParamSym).setType(this.syms.serializedLambdaType), symbolResolveQualifiedMethod).setType((Type) methodType), list2).setType(type);
    }

    private JCTree.JCExpression deserTest(JCTree.JCExpression jCExpression, String str, String str2) {
        Type.MethodType methodType = new Type.MethodType(List.of(this.syms.objectType), this.syms.booleanType, List.nil(), this.syms.methodClass);
        Resolve resolve = this.rs;
        Env<AttrContext> env = this.attrEnv;
        Type type = this.syms.objectType;
        JCTree.JCMethodInvocation jCMethodInvocationApply = this.make.Apply(List.nil(), this.make.Select(deserGetter(str, this.syms.stringType), resolve.resolveQualifiedMethod(null, env, type, this.names.equals, List.of(type), List.nil())).setType((Type) methodType), List.of(this.make.Literal(str2)));
        jCMethodInvocationApply.setType((Type) this.syms.booleanType);
        TreeMaker treeMaker = this.make;
        JCTree.Tag tag = JCTree.Tag.AND;
        JCTree.JCBinary jCBinaryBinary = treeMaker.Binary(tag, jCExpression, jCMethodInvocationApply);
        Operators operators = this.operators;
        Type.JCPrimitiveType jCPrimitiveType = this.syms.booleanType;
        jCBinaryBinary.operator = operators.resolveBinary(jCBinaryBinary, tag, jCPrimitiveType, jCPrimitiveType);
        jCBinaryBinary.setType((Type) this.syms.booleanType);
        return jCBinaryBinary;
    }

    private JCTree.JCExpression eqTest(Type type, JCTree.JCExpression jCExpression, JCTree.JCExpression jCExpression2) {
        TreeMaker treeMaker = this.make;
        JCTree.Tag tag = JCTree.Tag.EQ;
        JCTree.JCBinary jCBinaryBinary = treeMaker.Binary(tag, jCExpression, jCExpression2);
        jCBinaryBinary.operator = this.operators.resolveBinary(jCBinaryBinary, tag, type, type);
        jCBinaryBinary.setType((Type) this.syms.booleanType);
        return jCBinaryBinary;
    }

    public static LambdaToMethod instance(Context context) {
        LambdaToMethod lambdaToMethod = (LambdaToMethod) context.get(unlambdaKey);
        return lambdaToMethod == null ? new LambdaToMethod(context) : lambdaToMethod;
    }

    private JCTree.JCMethodDecl makeDeserializeMethod() {
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        for (Map.Entry entry : this.kInfo.deserializeCases.entrySet()) {
            JCTree.JCBreak jCBreakBreak = this.make.Break(null);
            listBuffer2.add(jCBreakBreak);
            List<JCTree.JCStatement> list = ((ListBuffer) entry.getValue()).append(jCBreakBreak).toList();
            TreeMaker treeMaker = this.make;
            listBuffer.add(treeMaker.Case(JCTree.JCCase.STATEMENT, List.of(treeMaker.ConstantCaseLabel(treeMaker.Literal(entry.getKey()))), null, list, null));
        }
        JCTree.JCSwitch jCSwitchSwitch = this.make.Switch(deserGetter("getImplMethodName", this.syms.stringType), listBuffer.toList());
        Iterator it = listBuffer2.iterator();
        while (it.hasNext()) {
            ((JCTree.JCBreak) it.next()).target = jCSwitchSwitch;
        }
        TreeMaker treeMaker2 = this.make;
        JCTree.JCBlock jCBlockBlock = treeMaker2.Block(0L, List.of((JCTree.JCThrow) jCSwitchSwitch, treeMaker2.Throw(makeNewClass(this.syms.illegalArgumentExceptionType, List.of(treeMaker2.Literal("Invalid lambda deserialization"))))));
        TreeMaker treeMaker3 = this.make;
        JCTree.JCMethodDecl jCMethodDeclMethodDef = treeMaker3.MethodDef(treeMaker3.Modifiers(this.kInfo.deserMethodSym.flags()), this.names.deserializeLambda, this.make.QualIdent(this.kInfo.deserMethodSym.getReturnType().tsym), List.nil(), List.of(this.make.VarDef(this.kInfo.deserParamSym, null)), List.nil(), jCBlockBlock, null);
        jCMethodDeclMethodDef.sym = this.kInfo.deserMethodSym;
        jCMethodDeclMethodDef.type = this.kInfo.deserMethodSym.type;
        return this.lower.translateMethod(this.attrEnv, jCMethodDeclMethodDef, this.make);
    }

    private JCTree.JCExpression makeIndyCall(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Name name, List<PoolConstant.LoadableConstant> list, Type.MethodType methodType, List<JCTree.JCExpression> list2, Name name2) {
        TreeMaker treeMaker = this.make;
        int i = treeMaker.pos;
        try {
            treeMaker.at(diagnosticPosition);
            Symtab symtab = this.syms;
            List listOf = List.of(symtab.methodHandleLookupType, symtab.stringType, symtab.methodTypeType);
            final Types types = this.types;
            Objects.requireNonNull(types);
            Symbol.DynamicMethodSymbol dynamicMethodSymbol = new Symbol.DynamicMethodSymbol(name2, this.syms.noSymbol, this.rs.resolveInternalMethod(diagnosticPosition, this.attrEnv, type, name, listOf.appendList((List) list.map(new Function() { // from class: dn8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return types.constantType((PoolConstant.LoadableConstant) obj);
                }
            })), List.nil()).asHandle(), methodType, (PoolConstant.LoadableConstant[]) list.toArray(new PoolConstant.LoadableConstant[list.length()]));
            TreeMaker treeMaker2 = this.make;
            JCTree.JCFieldAccess jCFieldAccessSelect = treeMaker2.Select(treeMaker2.QualIdent(type.tsym), name);
            Symbol.DynamicMethodSymbol dynamicMethodSymbol2 = (Symbol.DynamicMethodSymbol) this.kInfo.dynMethSyms.putIfAbsent(dynamicMethodSymbol.poolKey(this.types), dynamicMethodSymbol);
            if (dynamicMethodSymbol2 != null) {
                dynamicMethodSymbol = dynamicMethodSymbol2;
            }
            jCFieldAccessSelect.sym = dynamicMethodSymbol;
            jCFieldAccessSelect.type = methodType.mo73getReturnType();
            JCTree.JCMethodInvocation jCMethodInvocationApply = this.make.Apply(List.nil(), jCFieldAccessSelect, list2);
            jCMethodInvocationApply.type = methodType.mo73getReturnType();
            return jCMethodInvocationApply;
        } finally {
            this.make.at(i);
        }
    }

    private JCTree.JCBlock makeLambdaBody(JCTree.JCLambda jCLambda, JCTree.JCMethodDecl jCMethodDecl) {
        LambdaExpressionTree.BodyKind bodyKind = jCLambda.getBodyKind();
        LambdaExpressionTree.BodyKind bodyKind2 = LambdaExpressionTree.BodyKind.EXPRESSION;
        JCTree jCTree = jCLambda.body;
        return bodyKind == bodyKind2 ? makeLambdaExpressionBody((JCTree.JCExpression) jCTree, jCMethodDecl) : makeLambdaStatementBody((JCTree.JCBlock) jCTree, jCMethodDecl, jCLambda.canCompleteNormally);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v3, types: [com.sun.tools.javac.tree.TreeMaker] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private JCTree.JCBlock makeLambdaExpressionBody(JCTree.JCExpression jCExpression, JCTree.JCMethodDecl jCMethodDecl) {
        JCTree.JCBlock jCBlockBlock;
        Type typeMo73getReturnType = jCMethodDecl.type.mo73getReturnType();
        Type type = jCExpression.type;
        TypeTag typeTag = TypeTag.VOID;
        boolean zHasTag = type.hasTag(typeTag);
        boolean zHasTag2 = typeMo73getReturnType.hasTag(typeTag);
        Types types = this.types;
        boolean zIsSameType = types.isSameType(typeMo73getReturnType, types.boxedClass(this.syms.voidType).type);
        TreeMaker treeMaker = this.make;
        int i = treeMaker.pos;
        try {
            if (zHasTag2) {
                jCBlockBlock = this.make.Block(0L, List.of(treeMaker.at(jCExpression).Exec(jCExpression)));
            } else if (zHasTag && zIsSameType) {
                ListBuffer listBuffer = new ListBuffer();
                listBuffer.append(this.make.at(jCExpression).Exec(jCExpression));
                TreeMaker treeMaker2 = this.make;
                listBuffer.append(treeMaker2.Return(treeMaker2.Literal(TypeTag.BOT, null).setType(this.syms.botType)));
                jCBlockBlock = this.make.Block(0L, listBuffer.toList());
            } else {
                jCBlockBlock = treeMaker.at(jCExpression).Block(0L, List.of(this.make.Return(jCExpression)));
            }
            this = this.make;
            this.at(i);
            return jCBlockBlock;
        } catch (Throwable th) {
            this.make.at(i);
            throw th;
        }
    }

    private JCTree.JCBlock makeLambdaStatementBody(JCTree.JCBlock jCBlock, final JCTree.JCMethodDecl jCMethodDecl, boolean z) {
        Type typeMo73getReturnType = jCMethodDecl.type.mo73getReturnType();
        final boolean zHasTag = typeMo73getReturnType.hasTag(TypeTag.VOID);
        Types types = this.types;
        boolean zIsSameType = types.isSameType(typeMo73getReturnType, types.boxedClass(this.syms.voidType).type);
        JCTree.JCBlock jCBlock2 = (JCTree.JCBlock) new TreeTranslator(this) { // from class: com.sun.tools.javac.comp.LambdaToMethod.1LambdaBodyTranslator
            final /* synthetic */ LambdaToMethod this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
                this.result = jCClassDecl;
            }

            @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitLambda(JCTree.JCLambda jCLambda) {
                this.result = jCLambda;
            }

            @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitReturn(JCTree.JCReturn jCReturn) {
                boolean z2 = jCReturn.expr == null;
                if (!zHasTag || z2) {
                    this.result = jCReturn;
                } else {
                    this.result = this.this$0.make.Block(0L, List.of((JCTree.JCReturn) this.this$0.make.VarDef(new Symbol.VarSymbol(4096L, this.this$0.names.fromString("$loc"), jCReturn.expr.type, jCMethodDecl.sym), jCReturn.expr), this.this$0.make.Return(null)));
                }
            }
        }.translate(jCBlock);
        if (z && zIsSameType) {
            List<JCTree.JCStatement> list = jCBlock2.stats;
            TreeMaker treeMaker = this.make;
            jCBlock2.stats = list.append(treeMaker.Return(treeMaker.Literal(TypeTag.BOT, null).setType(this.syms.botType)));
        }
        return jCBlock2;
    }

    private JCTree.JCExpression makeMetafactoryIndyCall(JCTree.JCFunctionalExpression jCFunctionalExpression, Symbol.MethodHandleSymbol methodHandleSymbol, Symbol.MethodSymbol methodSymbol, List<JCTree.JCExpression> list) {
        Type.MethodType methodType;
        List<PoolConstant.LoadableConstant> list2;
        List<PoolConstant.LoadableConstant> list3;
        Symbol.MethodSymbol methodSymbol2 = (Symbol.MethodSymbol) this.types.findDescriptorSymbol(jCFunctionalExpression.target.tsym);
        List<PoolConstant.LoadableConstant> listOf = List.of(typeToMethodType(methodSymbol2.type), (Type.MethodType) methodHandleSymbol.asHandle(), typeToMethodType(jCFunctionalExpression.getDescriptorType(this.types)));
        ListBuffer listBuffer = new ListBuffer();
        Iterator<JCTree.JCExpression> it = list.iterator();
        while (it.hasNext()) {
            listBuffer.append(it.next().type);
        }
        Type.MethodType methodType2 = new Type.MethodType(listBuffer.toList(), jCFunctionalExpression.type, List.nil(), this.syms.methodClass);
        List<Symbol> listBridges = bridges(jCFunctionalExpression);
        boolean zIsSerializable = isSerializable(jCFunctionalExpression);
        boolean z = jCFunctionalExpression.target.isIntersection() || zIsSerializable || listBridges.length() > 1;
        dumpStats(jCFunctionalExpression, z, methodSymbol);
        Names names = this.names;
        Name name = z ? names.altMetafactory : names.metafactory;
        if (z) {
            ListBuffer listBuffer2 = new ListBuffer();
            Iterator<Type> it2 = (jCFunctionalExpression.target.isIntersection() ? this.types.directSupertypes(jCFunctionalExpression.target) : List.nil()).iterator();
            while (it2.hasNext()) {
                Type typeErasure = this.types.erasure(it2.next());
                Symbol.TypeSymbol typeSymbol = typeErasure.tsym;
                Symtab symtab = this.syms;
                if (typeSymbol != symtab.serializableType.tsym && typeSymbol != jCFunctionalExpression.type.tsym && typeSymbol != symtab.objectType.tsym) {
                    listBuffer2.append(typeErasure);
                }
            }
            boolean zNonEmpty = listBuffer2.nonEmpty();
            boolean zNonEmpty2 = listBridges.nonEmpty();
            int i = zNonEmpty ? (zIsSerializable ? 1 : 0) | 2 : zIsSerializable ? 1 : 0;
            if (zNonEmpty2) {
                i = (i == true ? 1 : 0) | 4;
            }
            List<PoolConstant.LoadableConstant> listAppend = listOf.append(PoolConstant.LoadableConstant.Int(i));
            if (zNonEmpty) {
                listAppend = listAppend.append(PoolConstant.LoadableConstant.Int(listBuffer2.length())).appendList(List.convert(PoolConstant.LoadableConstant.class, listBuffer2.toList()));
            }
            if (zNonEmpty2) {
                listAppend = listAppend.append(PoolConstant.LoadableConstant.Int(listBridges.length() - 1));
                for (Symbol symbol : listBridges) {
                    Type typeErasure2 = symbol.erasure(this.types);
                    Types types = this.types;
                    if (!types.isSameType(typeErasure2, methodSymbol2.erasure(types))) {
                        listAppend = listAppend.append((Type.MethodType) symbol.erasure(this.types));
                    }
                }
            }
            if (zIsSerializable) {
                TreeMaker treeMaker = this.make;
                int i2 = treeMaker.pos;
                try {
                    treeMaker.at(this.kInfo.clazz);
                    methodType = methodType2;
                    list3 = listAppend;
                    addDeserializationCase(methodHandleSymbol, jCFunctionalExpression.type, methodSymbol2, jCFunctionalExpression, list3, methodType);
                    this.make.at(i2);
                } catch (Throwable th) {
                    this.make.at(i2);
                    throw th;
                }
            } else {
                methodType = methodType2;
                list3 = listAppend;
            }
            list2 = list3;
        } else {
            methodType = methodType2;
            list2 = listOf;
        }
        return makeIndyCall(jCFunctionalExpression, this.syms.lambdaMetafactory, name, list2, methodType, list, methodSymbol2.name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Symbol.MethodSymbol makePrivateSyntheticMethod(long j, Name name, Type type, Symbol symbol) {
        return new Symbol.MethodSymbol(j | 4098, name, type, symbol);
    }

    private JCTree.JCIdent makeThis(Type type, Symbol symbol) {
        return this.make.Ident(new Symbol.VarSymbol(8589938704L, this.names._this, type, symbol));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String typeSig(Type type, boolean z) {
        try {
            L2MSignatureGenerator l2MSignatureGenerator = new L2MSignatureGenerator(z);
            l2MSignatureGenerator.assembleSig(type);
            return l2MSignatureGenerator.toString();
        } catch (Types.SignatureGenerator.InvalidSignatureException e) {
            Symbol.ClassSymbol classSymbol = this.attrEnv.enclClass.sym;
            this.log.error(CompilerProperties.Errors.CannotGenerateClass(classSymbol, CompilerProperties.Fragments.IllegalSignature(classSymbol, e.type())));
            return "<ERRONEOUS>";
        }
    }

    private Type.MethodType typeToMethodType(Type type) {
        Type typeErasure = this.types.erasure(type);
        return new Type.MethodType(typeErasure.mo71getParameterTypes(), typeErasure.mo73getReturnType(), typeErasure.mo74getThrownTypes(), this.syms.methodClass);
    }

    public List<Symbol> bridges(JCTree.JCFunctionalExpression jCFunctionalExpression) {
        return this.types.functionalInterfaceBridges(this.types.makeFunctionalInterfaceClass(this.attrEnv, this.names.empty, jCFunctionalExpression.target, 1536L));
    }

    public void dumpStats(JCTree.JCFunctionalExpression jCFunctionalExpression, boolean z, Symbol symbol) {
        if (this.dumpLambdaToMethodStats) {
            if (jCFunctionalExpression instanceof JCTree.JCLambda) {
                this.log.note(jCFunctionalExpression, this.diags.noteKey(((JCTree.JCLambda) jCFunctionalExpression).wasMethodReference ? "mref.stat.1" : "lambda.stat", Boolean.valueOf(z), symbol));
            } else if (jCFunctionalExpression instanceof JCTree.JCMemberReference) {
                this.log.note(jCFunctionalExpression, CompilerProperties.Notes.MrefStat(z, null));
            }
        }
    }

    public boolean isSerializable(JCTree.JCFunctionalExpression jCFunctionalExpression) {
        return this.forceSerializable || this.types.asSuper(jCFunctionalExpression.target, this.syms.serializableType.tsym) != null;
    }

    public JCTree.JCNewClass makeNewClass(Type type, List<JCTree.JCExpression> list) {
        return makeNewClass(type, list, this.rs.resolveConstructor(null, this.attrEnv, type, TreeInfo.types(list), List.nil()));
    }

    public JCTree translateTopLevelClass(Env<AttrContext> env, JCTree jCTree, TreeMaker treeMaker) {
        this.make = treeMaker;
        this.attrEnv = env;
        return translate(jCTree);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        KlassInfo klassInfo = this.kInfo;
        DiagnosticSource diagnosticSourceCurrentSource = this.log.currentSource();
        LambdaTranslationContext lambdaTranslationContext = this.lambdaContext;
        Symbol.VarSymbol varSymbol = this.pendingVar;
        try {
            this.kInfo = new KlassInfo(this, jCClassDecl, null);
            this.log.useSource(jCClassDecl.sym.sourcefile);
            this.lambdaContext = null;
            this.pendingVar = null;
            super.visitClassDef(jCClassDecl);
            if (lambdaTranslationContext != null) {
                jCClassDecl.sym.owner = lambdaTranslationContext.translatedSym;
            }
            if (!this.kInfo.deserializeCases.isEmpty()) {
                TreeMaker treeMaker = this.make;
                int i = treeMaker.pos;
                try {
                    treeMaker.at(jCClassDecl);
                    this.kInfo.addMethod(makeDeserializeMethod());
                    this.make.at(i);
                } catch (Throwable th) {
                    this.make.at(i);
                    throw th;
                }
            }
            List<JCTree> list = this.kInfo.appendedMethodList.toList();
            jCClassDecl.defs = jCClassDecl.defs.appendList(list);
            Iterator<JCTree> it = list.iterator();
            while (it.hasNext()) {
                jCClassDecl.sym.members().enter(((JCTree.JCMethodDecl) it.next()).sym);
            }
            this.result = jCClassDecl;
            this.kInfo = klassInfo;
            this.log.useSource(diagnosticSourceCurrentSource.getFile());
            this.lambdaContext = lambdaTranslationContext;
            this.pendingVar = varSymbol;
        } catch (Throwable th2) {
            this.kInfo = klassInfo;
            this.log.useSource(diagnosticSourceCurrentSource.getFile());
            this.lambdaContext = lambdaTranslationContext;
            this.pendingVar = varSymbol;
            throw th2;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        if (this.lambdaContext == null) {
            super.visitIdent(jCIdent);
            return;
        }
        TreeMaker treeMaker = this.make;
        int i = treeMaker.pos;
        try {
            treeMaker.at(jCIdent);
            JCTree jCTreeTranslate = this.lambdaContext.translate(jCIdent);
            if (jCTreeTranslate != null) {
                this.result = jCTreeTranslate;
            } else {
                super.visitIdent(jCIdent);
            }
        } finally {
            this.make.at(i);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLambda(JCTree.JCLambda jCLambda) {
        LambdaTranslationContext lambdaTranslationContext = new LambdaTranslationContext(jCLambda);
        final Symbol.MethodSymbol methodSymbol = lambdaTranslationContext.translatedSym;
        Type.MethodType methodType = (Type.MethodType) methodSymbol.type;
        final Symbol symbol = jCLambda.owner;
        Objects.requireNonNull(symbol);
        apportionTypeAnnotations(jCLambda, new Supplier() { // from class: fn8
            @Override // java.util.function.Supplier
            public final Object get() {
                return symbol.getRawTypeAttributes();
            }
        }, new Consumer() { // from class: hn8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                symbol.setTypeAttributes((List) obj);
            }
        }, new Consumer() { // from class: in8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                methodSymbol.setTypeAttributes((List) obj);
            }
        });
        long jFlags = symbol.flags();
        boolean z = false;
        if ((1048576 & jFlags) != 0) {
            final Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbol.owner;
            boolean z2 = (jFlags & 8) != 0;
            Objects.requireNonNull(classSymbol);
            Supplier<List<Attribute.TypeCompound>> supplier = z2 ? new Supplier() { // from class: jn8
                @Override // java.util.function.Supplier
                public final Object get() {
                    return classSymbol.getClassInitTypeAttributes();
                }
            } : new Supplier() { // from class: kn8
                @Override // java.util.function.Supplier
                public final Object get() {
                    return classSymbol.getInitTypeAttributes();
                }
            };
            Objects.requireNonNull(classSymbol);
            apportionTypeAnnotations(jCLambda, supplier, z2 ? new Consumer() { // from class: ln8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    classSymbol.setClassInitTypeAttributes((List) obj);
                }
            } : new Consumer() { // from class: mn8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    classSymbol.setInitTypeAttributes((List) obj);
                }
            }, new Consumer() { // from class: gn8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    methodSymbol.appendUniqueTypeAttributes((List) obj);
                }
            });
        }
        Symbol.VarSymbol varSymbol = this.pendingVar;
        if (varSymbol != null && varSymbol.getKind() == ElementKind.FIELD) {
            final Symbol.VarSymbol varSymbol2 = this.pendingVar;
            Objects.requireNonNull(varSymbol2);
            Supplier<List<Attribute.TypeCompound>> supplier2 = new Supplier() { // from class: nn8
                @Override // java.util.function.Supplier
                public final Object get() {
                    return varSymbol2.getRawTypeAttributes();
                }
            };
            final Symbol.VarSymbol varSymbol3 = this.pendingVar;
            Objects.requireNonNull(varSymbol3);
            apportionTypeAnnotations(jCLambda, supplier2, new Consumer() { // from class: en8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    varSymbol3.setTypeAttributes((List) obj);
                }
            }, new Consumer() { // from class: gn8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    methodSymbol.appendUniqueTypeAttributes((List) obj);
                }
            });
        }
        TreeMaker treeMaker = this.make;
        JCTree.JCMethodDecl jCMethodDeclMethodDef = treeMaker.MethodDef(treeMaker.Modifiers(methodSymbol.flags_field), methodSymbol.name, this.make.QualIdent(methodType.mo73getReturnType().tsym), List.nil(), lambdaTranslationContext.syntheticParams, methodType.mo74getThrownTypes() == null ? List.nil() : this.make.Types(methodType.mo74getThrownTypes()), null, null);
        jCMethodDeclMethodDef.sym = methodSymbol;
        jCMethodDeclMethodDef.type = methodType;
        ListBuffer listBuffer = new ListBuffer();
        if (!methodSymbol.isStatic()) {
            listBuffer.append(makeThis(methodSymbol.owner.enclClass().asType(), jCLambda.owner.enclClass()));
        }
        for (Symbol.VarSymbol varSymbol4 : lambdaTranslationContext.capturedVars) {
            listBuffer.append(this.make.Ident(varSymbol4).setType(varSymbol4.type));
        }
        List<JCTree.JCExpression> listTranslate = translate(listBuffer.toList());
        LambdaTranslationContext lambdaTranslationContext2 = this.lambdaContext;
        try {
            this.lambdaContext = lambdaTranslationContext;
            jCMethodDeclMethodDef.body = (JCTree.JCBlock) translate(makeLambdaBody(jCLambda, jCMethodDeclMethodDef));
            this.lambdaContext = lambdaTranslationContext2;
            if (this.deduplicateLambdas && !this.debugLinesOrVars && !isSerializable(jCLambda)) {
                DedupedLambda dedupedLambda = new DedupedLambda(jCMethodDeclMethodDef.sym, jCMethodDeclMethodDef.body);
                DedupedLambda dedupedLambda2 = (DedupedLambda) this.kInfo.dedupedLambdas.putIfAbsent(dedupedLambda, dedupedLambda);
                if (dedupedLambda2 != null) {
                    methodSymbol = dedupedLambda2.symbol;
                    if (this.verboseDeduplication) {
                        this.log.note(jCLambda, CompilerProperties.Notes.VerboseL2mDeduplicate(methodSymbol));
                    }
                    z = true;
                }
            }
            if (!z) {
                this.kInfo.addMethod(jCMethodDeclMethodDef);
            }
            this.result = makeMetafactoryIndyCall(jCLambda, methodSymbol.asHandle(), lambdaTranslationContext.translatedSym, listTranslate);
        } catch (Throwable th) {
            this.lambdaContext = lambdaTranslationContext2;
            throw th;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReference(JCTree.JCMemberReference jCMemberReference) {
        Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) jCMemberReference.sym;
        JCTree.JCExpression jCExpressionMakeThis = null;
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[jCMemberReference.kind.ordinal()]) {
            case 1:
            case 2:
                jCExpressionMakeThis = makeThis(jCMemberReference.owner.enclClass().asType(), jCMemberReference.owner.enclClass());
                break;
            case 3:
                jCExpressionMakeThis = this.attr.makeNullCheck(this.transTypes.coerce(this.attrEnv, jCMemberReference.getQualifierExpression(), this.types.erasure(jCMemberReference.sym.owner.type)));
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                break;
            default:
                throw new IllegalStateException(null, null);
        }
        this.result = makeMetafactoryIndyCall(jCMemberReference, methodSymbol.asHandle(), methodSymbol, jCExpressionMakeThis == null ? List.nil() : translate(List.of(jCExpressionMakeThis)));
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        Symbol.VarSymbol varSymbol = this.pendingVar;
        try {
            Symbol.VarSymbol varSymbol2 = jCVariableDecl.sym;
            this.pendingVar = varSymbol2;
            LambdaTranslationContext lambdaTranslationContext = this.lambdaContext;
            if (lambdaTranslationContext != null) {
                jCVariableDecl.sym = lambdaTranslationContext.addLocal(varSymbol2);
                jCVariableDecl.init = (JCTree.JCExpression) translate(jCVariableDecl.init);
                this.result = jCVariableDecl;
            } else {
                super.visitVarDef(jCVariableDecl);
            }
        } finally {
            this.pendingVar = varSymbol;
        }
    }

    public JCTree.JCNewClass makeNewClass(Type type, List<JCTree.JCExpression> list, Symbol symbol) {
        TreeMaker treeMaker = this.make;
        JCTree.JCNewClass jCNewClassNewClass = treeMaker.NewClass(null, null, treeMaker.QualIdent(type.tsym), list, null);
        jCNewClassNewClass.constructor = symbol;
        jCNewClassNewClass.type = type;
        return jCNewClassNewClass;
    }

    public class L2MSignatureGenerator extends Types.SignatureGenerator {
        boolean allowIllegalSignatures;
        StringBuilder sb;

        /* JADX WARN: Illegal instructions before constructor call */
        public L2MSignatureGenerator(boolean z) {
            Types types = LambdaToMethod.this.types;
            Objects.requireNonNull(types);
            super();
            this.sb = new StringBuilder();
            this.allowIllegalSignatures = z;
        }

        @Override // com.sun.tools.javac.code.Types.SignatureGenerator
        public void append(byte[] bArr) {
            try {
                this.sb.append(LambdaToMethod.this.names.fromUtf(bArr).toString());
            } catch (InvalidUtfException e) {
                x01.a(e);
            }
        }

        @Override // com.sun.tools.javac.code.Types.SignatureGenerator
        public void reportIllegalSignature(Type type) {
            if (this.allowIllegalSignatures) {
                return;
            }
            super.reportIllegalSignature(type);
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

    private String typeSig(Type type) {
        return typeSig(type, false);
    }

    private JCTree.JCExpression deserGetter(String str, Type type) {
        return deserGetter(str, type, List.nil(), List.nil());
    }

    public class KlassInfo {
        private ListBuffer<JCTree> appendedMethodList;
        private final JCTree.JCClassDecl clazz;
        private final Map<DedupedLambda, DedupedLambda> dedupedLambdas;
        private final Symbol.MethodSymbol deserMethodSym;
        private final Symbol.VarSymbol deserParamSym;
        private final Map<String, ListBuffer<JCTree.JCStatement>> deserializeCases;
        private final Map<Object, Symbol.DynamicMethodSymbol> dynMethSyms;
        private final Map<String, Integer> syntheticNames;

        private KlassInfo(JCTree.JCClassDecl jCClassDecl) {
            this.appendedMethodList = new ListBuffer<>();
            this.dedupedLambdas = new HashMap();
            this.dynMethSyms = new HashMap();
            this.deserializeCases = new HashMap();
            this.syntheticNames = new HashMap();
            this.clazz = jCClassDecl;
            Symbol.MethodSymbol methodSymbolMakePrivateSyntheticMethod = LambdaToMethod.this.makePrivateSyntheticMethod(8L, LambdaToMethod.this.names.deserializeLambda, new Type.MethodType(List.of(LambdaToMethod.this.syms.serializedLambdaType), LambdaToMethod.this.syms.objectType, List.nil(), LambdaToMethod.this.syms.methodClass), jCClassDecl.sym);
            this.deserMethodSym = methodSymbolMakePrivateSyntheticMethod;
            this.deserParamSym = new Symbol.VarSymbol(16L, LambdaToMethod.this.names.fromString("lambda"), LambdaToMethod.this.syms.serializedLambdaType, methodSymbolMakePrivateSyntheticMethod);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addMethod(JCTree jCTree) {
            this.appendedMethodList = this.appendedMethodList.prepend(jCTree);
        }

        public int syntheticNameIndex(StringBuilder sb, int i) {
            String string = sb.toString();
            Integer numValueOf = this.syntheticNames.get(string);
            if (numValueOf == null) {
                numValueOf = Integer.valueOf(i);
            }
            this.syntheticNames.put(string, Integer.valueOf(numValueOf.intValue() + 1));
            return numValueOf.intValue();
        }

        public /* synthetic */ KlassInfo(LambdaToMethod lambdaToMethod, JCTree.JCClassDecl jCClassDecl, AnonymousClass1 anonymousClass1) {
            this(jCClassDecl);
        }
    }

    public class LambdaTranslationContext {
        final List<Symbol.VarSymbol> capturedVars;
        final Map<Symbol.VarSymbol, Symbol.VarSymbol> lambdaProxies = new HashMap();
        final List<JCTree.JCVariableDecl> syntheticParams;
        final Symbol.MethodSymbol translatedSym;
        final JCTree.JCFunctionalExpression tree;

        public class LambdaCaptureScanner extends CaptureScanner {
            boolean capturesThis;
            Set<Symbol.ClassSymbol> seenClasses;

            public LambdaCaptureScanner(JCTree.JCLambda jCLambda) {
                super(jCLambda);
                this.seenClasses = new HashSet();
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
                this.seenClasses.add(jCClassDecl.sym);
                super.visitClassDef(jCClassDecl);
            }

            @Override // com.sun.tools.javac.comp.CaptureScanner, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitIdent(JCTree.JCIdent jCIdent) {
                Kinds.Kind kind;
                if (!jCIdent.sym.isStatic()) {
                    Symbol symbol = jCIdent.sym;
                    Symbol symbol2 = symbol.owner;
                    if (symbol2.kind == Kinds.Kind.TYP && (((kind = symbol.kind) == Kinds.Kind.VAR || kind == Kinds.Kind.MTH) && !this.seenClasses.contains(symbol2))) {
                        if ((jCIdent.sym.flags() & 562949953421312L) != 0) {
                            addFreeVar((Symbol.VarSymbol) jCIdent.sym);
                            return;
                        } else {
                            this.capturesThis = true;
                            return;
                        }
                    }
                }
                super.visitIdent(jCIdent);
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
                Symbol symbol = jCFieldAccess.sym;
                if (symbol.kind == Kinds.Kind.VAR && ((symbol.name == LambdaToMethod.this.names._this || jCFieldAccess.sym.name == LambdaToMethod.this.names._super) && !this.seenClasses.contains(jCFieldAccess.sym.type.tsym))) {
                    this.capturesThis = true;
                }
                super.visitSelect(jCFieldAccess);
            }
        }

        public enum LambdaSymbolKind {
            PARAM,
            LOCAL_VAR,
            CAPTURED_VAR
        }

        public LambdaTranslationContext(JCTree.JCLambda jCLambda) {
            LambdaTranslationContext lambdaTranslationContext;
            LambdaToMethod lambdaToMethod;
            this.tree = jCLambda;
            Symbol symbol = jCLambda.owner;
            if (symbol.kind == Kinds.Kind.MTH) {
                final Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) symbol.clone(symbol.owner);
                lambdaTranslationContext = this;
                lambdaTranslationContext.translatedSym = new Symbol.MethodSymbol(lambdaTranslationContext, 0L, null, null, symbol.enclClass()) { // from class: com.sun.tools.javac.comp.LambdaToMethod.LambdaTranslationContext.1
                    final /* synthetic */ LambdaTranslationContext this$1;

                    {
                        this.this$1 = lambdaTranslationContext;
                    }

                    @Override // com.sun.tools.javac.code.Symbol.MethodSymbol
                    public Symbol.MethodSymbol originalEnclosingMethod() {
                        return methodSymbol;
                    }
                };
                lambdaToMethod = LambdaToMethod.this;
            } else {
                lambdaTranslationContext = this;
                lambdaToMethod = LambdaToMethod.this;
                lambdaTranslationContext.translatedSym = lambdaToMethod.makePrivateSyntheticMethod(0L, null, null, symbol.enclClass());
            }
            ListBuffer listBuffer = new ListBuffer();
            ListBuffer listBuffer2 = new ListBuffer();
            LambdaCaptureScanner lambdaCaptureScanner = lambdaTranslationContext.new LambdaCaptureScanner(jCLambda);
            List<Symbol.VarSymbol> listAnalyzeCaptures = lambdaCaptureScanner.analyzeCaptures();
            lambdaTranslationContext.capturedVars = listAnalyzeCaptures;
            Iterator<Symbol.VarSymbol> it = listAnalyzeCaptures.iterator();
            while (it.hasNext()) {
                Symbol.VarSymbol varSymbolAddSymbol = lambdaTranslationContext.addSymbol(it.next(), LambdaSymbolKind.CAPTURED_VAR);
                listBuffer.append(lambdaToMethod.make.VarDef(varSymbolAddSymbol, null));
                listBuffer2.add(varSymbolAddSymbol);
            }
            Iterator<JCTree.JCVariableDecl> it2 = jCLambda.params.iterator();
            while (it2.hasNext()) {
                Symbol.VarSymbol varSymbolAddSymbol2 = lambdaTranslationContext.addSymbol(it2.next().sym, LambdaSymbolKind.PARAM);
                listBuffer.append(lambdaToMethod.make.VarDef(varSymbolAddSymbol2, null));
                listBuffer2.add(varSymbolAddSymbol2);
            }
            lambdaTranslationContext.syntheticParams = listBuffer.toList();
            lambdaTranslationContext.completeLambdaMethodSymbol(symbol, lambdaCaptureScanner.capturesThis);
            lambdaTranslationContext.translatedSym.params = listBuffer2.toList();
        }

        private Symbol.VarSymbol addSymbol(Symbol.VarSymbol varSymbol, final LambdaSymbolKind lambdaSymbolKind) {
            return this.lambdaProxies.computeIfAbsent(varSymbol, new Function() { // from class: com.sun.tools.javac.comp.h1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.translate((Symbol.VarSymbol) obj, lambdaSymbolKind);
                }
            });
        }

        private Name lambdaName(Symbol symbol) {
            StringBuilder sb = new StringBuilder();
            sb.append((CharSequence) LambdaToMethod.this.names.lambda);
            sb.append(syntheticMethodNameComponent(symbol));
            sb.append("$");
            sb.append(LambdaToMethod.this.kInfo.syntheticNameIndex(sb, 0));
            return LambdaToMethod.this.names.fromString(sb.toString());
        }

        private String serializedLambdaDisambiguation(Symbol symbol) {
            StringBuilder sb = new StringBuilder();
            Assert.check((symbol.type == null && LambdaToMethod.this.lambdaContext == null) ? false : true);
            Type type = symbol.type;
            if (type != null) {
                sb.append(LambdaToMethod.this.typeSig(type, true));
                sb.append(":");
            }
            sb.append((CharSequence) LambdaToMethod.this.types.findDescriptorSymbol(this.tree.type.tsym).owner.flatName());
            sb.append(" ");
            if (LambdaToMethod.this.pendingVar != null) {
                sb.append((CharSequence) LambdaToMethod.this.pendingVar.flatName());
                sb.append("=");
            }
            for (Symbol.VarSymbol varSymbol : this.capturedVars) {
                if (varSymbol != symbol) {
                    sb.append(LambdaToMethod.this.typeSig(varSymbol.type, true));
                    sb.append(" ");
                    sb.append((CharSequence) varSymbol.flatName());
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        private Name serializedLambdaName(Symbol symbol) {
            StringBuilder sb = new StringBuilder();
            sb.append((CharSequence) LambdaToMethod.this.names.lambda);
            sb.append(syntheticMethodNameComponent(symbol));
            sb.append('$');
            sb.append(Integer.toHexString(serializedLambdaDisambiguation(symbol).hashCode()));
            sb.append('$');
            sb.append(LambdaToMethod.this.kInfo.syntheticNameIndex(sb, 1));
            return LambdaToMethod.this.names.fromString(sb.toString());
        }

        public Symbol.VarSymbol addLocal(Symbol.VarSymbol varSymbol) {
            return addSymbol(varSymbol, LambdaSymbolKind.LOCAL_VAR);
        }

        public void completeLambdaMethodSymbol(Symbol symbol, boolean z) {
            long j;
            boolean zIsInterface = symbol.enclClass().isInterface();
            Name nameSerializedLambdaName = LambdaToMethod.this.isSerializable(this.tree) ? serializedLambdaName(symbol) : lambdaName(symbol);
            Type typeCreateMethodTypeWithParameters = LambdaToMethod.this.types.createMethodTypeWithParameters(generatedLambdaSig(), TreeInfo.types(this.syntheticParams));
            long j2 = (symbol.flags_field & 2048) | 562949953425408L | (2048 & symbol.owner.flags_field) | 2;
            if (z) {
                j = zIsInterface ? Flags.DEFAULT : 0L;
            } else {
                j = 8;
            }
            Symbol.MethodSymbol methodSymbol = this.translatedSym;
            methodSymbol.type = typeCreateMethodTypeWithParameters;
            methodSymbol.name = nameSerializedLambdaName;
            methodSymbol.flags_field = j | j2;
        }

        public Type generatedLambdaSig() {
            return LambdaToMethod.this.types.erasure(this.tree.getDescriptorType(LambdaToMethod.this.types));
        }

        public String syntheticMethodNameComponent(Symbol symbol) {
            long jFlags = symbol.flags();
            if ((1048576 & jFlags) != 0) {
                return (8 & jFlags) != 0 ? PsiKeyword.STATIC : PsiKeyword.NEW;
            }
            return symbol.isConstructor() ? PsiKeyword.NEW : symbol.name.toString();
        }

        public Symbol.VarSymbol translate(Symbol.VarSymbol varSymbol, LambdaSymbolKind lambdaSymbolKind) {
            Symbol.VarSymbol varSymbol2;
            int iOrdinal = lambdaSymbolKind.ordinal();
            boolean z = true;
            if (iOrdinal == 0) {
                Symbol.VarSymbol varSymbol3 = new Symbol.VarSymbol(8589934592L | (varSymbol.flags() & 16), varSymbol.name, LambdaToMethod.this.types.erasure(varSymbol.type), this.translatedSym);
                varSymbol3.pos = varSymbol.pos;
                varSymbol2 = varSymbol3;
            } else if (iOrdinal == 1) {
                Symbol.VarSymbol varSymbol4 = new Symbol.VarSymbol(varSymbol.flags() & 16, varSymbol.name, varSymbol.type, this.translatedSym);
                varSymbol4.pos = varSymbol.pos;
                if (varSymbol.isExceptionParameter()) {
                    varSymbol4.setData(ElementKind.EXCEPTION_PARAMETER);
                }
                varSymbol2 = varSymbol4;
            } else {
                if (iOrdinal != 2) {
                    Assert.error(lambdaSymbolKind.name());
                    x1f.a();
                    return null;
                }
                varSymbol2 = new Symbol.VarSymbol(8589938704L, (varSymbol.flags() & 562949953421312L) != 0 ? varSymbol.baseSymbol().name : varSymbol.name, LambdaToMethod.this.types.erasure(varSymbol.type), this.translatedSym);
                z = false;
            }
            if (varSymbol2 != varSymbol && z) {
                varSymbol2.setDeclarationAttributes(varSymbol.getRawAttributes());
                varSymbol2.setTypeAttributes(varSymbol.getRawTypeAttributes());
            }
            return varSymbol2;
        }

        public JCTree translate(JCTree.JCIdent jCIdent) {
            Symbol.VarSymbol varSymbol = this.lambdaProxies.get(jCIdent.sym);
            if (varSymbol != null) {
                return LambdaToMethod.this.make.Ident(varSymbol).setType(jCIdent.type);
            }
            return null;
        }
    }
}

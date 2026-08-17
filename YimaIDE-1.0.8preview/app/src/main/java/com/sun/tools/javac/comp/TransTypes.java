package com.sun.tools.javac.comp;

import com.sun.source.tree.MemberReferenceTree;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Names;
import java.util.Iterator;
import java.util.function.Function;
import javax.lang.model.type.TypeKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TransTypes extends TreeTranslator {
    private static final String statePreviousToFlowAssertMsg = "The current compile state [%s] of class %s is previous to WARN";
    protected static final Context.Key<TransTypes> transTypesKey = new Context.Key<>();
    private Annotate annotate;
    private Attr attr;
    private final CompileStates compileStates;
    private Enter enter;
    private Env<AttrContext> env;
    private Log log;
    private TreeMaker make;
    private Names names;
    private Type pt;
    private final Resolve resolve;
    Type returnType = null;
    private Symtab syms;
    private final Target target;
    private Types types;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.TransTypes$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind;
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$type$TypeKind;

        static {
            int[] iArr = new int[JCTree.JCMemberReference.ReferenceKind.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind = iArr;
            try {
                iArr[JCTree.JCMemberReference.ReferenceKind.BOUND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[JCTree.JCMemberReference.ReferenceKind.UNBOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[TypeKind.values().length];
            $SwitchMap$javax$lang$model$type$TypeKind = iArr2;
            try {
                iArr2[TypeKind.INTERSECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.UNION.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$lang$model$type$TypeKind[TypeKind.TYPEVAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public class MemberReferenceToLambda {
        private final Symbol.MethodSymbol owner;
        private final JCTree.JCMemberReference tree;
        private final ListBuffer<JCTree.JCExpression> args = new ListBuffer<>();
        private final ListBuffer<JCTree.JCVariableDecl> params = new ListBuffer<>();
        private JCTree.JCExpression receiverExpression = null;

        public MemberReferenceToLambda(JCTree.JCMemberReference jCMemberReference) {
            this.owner = new Symbol.MethodSymbol(0L, TransTypes.this.names.empty, Type.noType, TransTypes.this.env.enclClass.sym);
            this.tree = jCMemberReference;
        }

        private Symbol.VarSymbol addParameter(String str, Type type, boolean z) {
            Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(8589938688L, TransTypes.this.names.fromString(str), type, this.owner);
            varSymbol.pos = this.tree.pos;
            this.params.append(TransTypes.this.make.VarDef(varSymbol, null));
            if (z) {
                this.args.append(TransTypes.this.make.Ident(varSymbol));
            }
            return varSymbol;
        }

        private JCTree.JCExpression expressionInvoke(Symbol.VarSymbol varSymbol) {
            JCTree.JCFieldAccess jCFieldAccessSelect = TransTypes.this.make.Select(varSymbol != null ? TransTypes.this.make.Ident(varSymbol) : this.tree.getQualifierExpression(), this.tree.sym.name);
            JCTree.JCMemberReference jCMemberReference = this.tree;
            jCFieldAccessSelect.sym = jCMemberReference.sym;
            jCFieldAccessSelect.type = jCMemberReference.referentType;
            JCTree.JCMethodInvocation type = TransTypes.this.make.Apply(List.nil(), jCFieldAccessSelect, this.args.toList()).setType(this.tree.referentType.mo73getReturnType());
            TreeInfo.setVarargsElement(type, this.tree.varargsElement);
            return type;
        }

        private JCTree.JCExpression expressionNew() {
            JCTree.JCMemberReference.ReferenceKind referenceKind = this.tree.kind;
            JCTree.JCMemberReference.ReferenceKind referenceKind2 = JCTree.JCMemberReference.ReferenceKind.ARRAY_CTOR;
            TransTypes transTypes = TransTypes.this;
            if (referenceKind == referenceKind2) {
                JCTree.JCNewArray jCNewArrayNewArray = transTypes.make.NewArray(TransTypes.this.make.Type(TransTypes.this.types.elemtype(this.tree.getQualifierExpression().type)), List.of(TransTypes.this.make.Ident(this.params.first())), null);
                jCNewArrayNewArray.type = this.tree.getQualifierExpression().type;
                return jCNewArrayNewArray;
            }
            JCTree.JCNewClass jCNewClassNewClass = transTypes.make.NewClass(null, List.nil(), TransTypes.this.make.Type(this.tree.getQualifierExpression().type), this.args.toList(), null);
            Symbol symbol = this.tree.sym;
            jCNewClassNewClass.constructor = symbol;
            jCNewClassNewClass.constructorType = symbol.erasure(TransTypes.this.types);
            jCNewClassNewClass.type = this.tree.getQualifierExpression().type;
            TreeInfo.setVarargsElement(jCNewClassNewClass, this.tree.varargsElement);
            return jCNewClassNewClass;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Symbol.VarSymbol addParametersReturnReceiver() {
            Symbol.VarSymbol varSymbolAddParameter;
            List listMo71getParameterTypes = this.tree.getDescriptorType(TransTypes.this.types).mo71getParameterTypes();
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$JCMemberReference$ReferenceKind[this.tree.kind.ordinal()];
            if (i == 1) {
                Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(4096L, TransTypes.this.names.fromString("rec$"), this.tree.getQualifierExpression().type, this.owner);
                varSymbol.pos = this.tree.pos;
                this.receiverExpression = TransTypes.this.attr.makeNullCheck(this.tree.getQualifierExpression());
                varSymbolAddParameter = varSymbol;
            } else if (i != 2) {
                varSymbolAddParameter = null;
            } else {
                varSymbolAddParameter = addParameter("rec$", (Type) listMo71getParameterTypes.head, false);
                listMo71getParameterTypes = listMo71getParameterTypes.tail;
            }
            List listMo71getParameterTypes2 = this.tree.sym.type.mo71getParameterTypes();
            int size = listMo71getParameterTypes2.size();
            int size2 = listMo71getParameterTypes.size();
            if (TransTypes.this.needsVarArgsConversion(this.tree)) {
                size--;
            }
            for (int i2 = 0; listMo71getParameterTypes2.nonEmpty() && i2 < size; i2++) {
                addParameter("x$" + i2, (Type) listMo71getParameterTypes.head, true);
                listMo71getParameterTypes2 = listMo71getParameterTypes2.tail;
                listMo71getParameterTypes = listMo71getParameterTypes.tail;
            }
            while (size < size2) {
                addParameter("xva$" + size, this.tree.varargsElement, true);
                size++;
            }
            return varSymbolAddParameter;
        }

        public JCTree.JCExpression lambda() {
            int i = TransTypes.this.make.pos;
            try {
                TransTypes.this.make.at(this.tree);
                Symbol.VarSymbol varSymbolAddParametersReturnReceiver = addParametersReturnReceiver();
                JCTree.JCLambda jCLambdaLambda = TransTypes.this.make.Lambda(this.params.toList(), this.tree.getMode() == MemberReferenceTree.ReferenceMode.INVOKE ? expressionInvoke(varSymbolAddParametersReturnReceiver) : expressionNew());
                JCTree.JCMemberReference jCMemberReference = this.tree;
                jCLambdaLambda.target = jCMemberReference.target;
                jCLambdaLambda.owner = jCMemberReference.owner;
                jCLambdaLambda.type = jCMemberReference.type;
                jCLambdaLambda.pos = jCMemberReference.pos;
                jCLambdaLambda.wasMethodReference = true;
                JCTree.JCExpression jCExpression = this.receiverExpression;
                TransTypes transTypes = TransTypes.this;
                if (jCExpression == null) {
                    transTypes.make.at(i);
                    return jCLambdaLambda;
                }
                JCTree.JCExpression type = transTypes.make.at(this.tree.pos).LetExpr(TransTypes.this.make.VarDef(varSymbolAddParametersReturnReceiver, this.receiverExpression), jCLambdaLambda).setType(this.tree.type);
                TransTypes.this.make.at(i);
                return type;
            } catch (Throwable th) {
                TransTypes.this.make.at(i);
                throw th;
            }
        }
    }

    public TransTypes(Context context) {
        context.put(transTypesKey, this);
        this.compileStates = CompileStates.instance(context);
        this.names = Names.instance(context);
        this.log = Log.instance(context);
        this.syms = Symtab.instance(context);
        this.enter = Enter.instance(context);
        this.types = Types.instance(context);
        this.make = TreeMaker.instance(context);
        this.resolve = Resolve.instance(context);
        this.annotate = Annotate.instance(context);
        this.attr = Attr.instance(context);
        this.target = Target.instance(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private List<Symbol.VarSymbol> createBridgeParams(Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2, Type type) {
        if (methodSymbol.params == null) {
            return null;
        }
        List<Symbol.VarSymbol> listNil = List.nil();
        List list = methodSymbol.params;
        List list2 = ((Type.MethodType) type).argtypes;
        while (list.nonEmpty() && list2.nonEmpty()) {
            Symbol.MethodSymbol methodSymbol3 = methodSymbol2;
            Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(((Symbol.VarSymbol) list.head).flags() | 8589938688L, ((Symbol.VarSymbol) list.head).name, (Type) list2.head, methodSymbol3);
            varSymbol.setAttributes((Symbol) list.head);
            listNil = listNil.append(varSymbol);
            list = list.tail;
            list2 = list2.tail;
            methodSymbol2 = methodSymbol3;
        }
        return listNil;
    }

    private Type erasure(Type type) {
        return this.types.erasure(type);
    }

    public static TransTypes instance(Context context) {
        TransTypes transTypes = (TransTypes) context.get(transTypesKey);
        return transTypes == null ? new TransTypes(context) : transTypes;
    }

    private boolean isBridgeNeeded(Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2, Type type) {
        boolean zIsSameMemberWhenErased;
        if (methodSymbol2 != methodSymbol) {
            Type typeErasure = methodSymbol.erasure(this.types);
            if (!isSameMemberWhenErased(type, methodSymbol, typeErasure)) {
                return true;
            }
            Type typeErasure2 = methodSymbol2.erasure(this.types);
            if (!isSameMemberWhenErased(type, methodSymbol2, typeErasure2)) {
                return true;
            }
            zIsSameMemberWhenErased = this.types.isSameType(typeErasure2, typeErasure);
        } else {
            if ((methodSymbol.flags() & 1024) != 0) {
                return false;
            }
            zIsSameMemberWhenErased = isSameMemberWhenErased(type, methodSymbol, methodSymbol.erasure(this.types));
        }
        return !zIsSameMemberWhenErased;
    }

    private boolean isProtectedInSuperClassOfEnclosingClassInOtherPackage(Symbol symbol, Symbol symbol2) {
        return ((symbol.flags() & 4) == 0 || symbol.packge() == symbol2.packge()) ? false : true;
    }

    private boolean isSameMemberWhenErased(Type type, Symbol.MethodSymbol methodSymbol, Type type2) {
        Types types = this.types;
        return types.isSameType(erasure(types.memberType(type, methodSymbol)), type2);
    }

    public void addBridge(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2, Symbol.ClassSymbol classSymbol, ListBuffer<JCTree> listBuffer) {
        this.make.at(diagnosticPosition);
        Type typeErasure = erasure(methodSymbol2.type);
        Type typeErasure2 = methodSymbol.erasure(this.types);
        Symbol.MethodSymbol methodSymbol3 = new Symbol.MethodSymbol((classSymbol.isInterface() ? Flags.DEFAULT : 0L) | (methodSymbol2.flags() & 7) | 2147487744L, methodSymbol.name, typeErasure2, classSymbol);
        methodSymbol3.params = createBridgeParams(methodSymbol2, methodSymbol3, typeErasure2);
        methodSymbol3.setAttributes(methodSymbol2);
        JCTree.JCMethodDecl jCMethodDeclMethodDef = this.make.MethodDef(methodSymbol3, null);
        Symbol symbol = methodSymbol2.owner;
        TreeMaker treeMaker = this.make;
        JCTree.JCExpression jCExpressionThis = symbol == classSymbol ? treeMaker.This(classSymbol.erasure(this.types)) : treeMaker.Super(this.types.supertype(classSymbol.type).tsym.erasure(this.types), classSymbol);
        Type typeMo73getReturnType = typeErasure.mo73getReturnType();
        TreeMaker treeMaker2 = this.make;
        JCTree.JCMethodInvocation type = treeMaker2.Apply(null, treeMaker2.Select(jCExpressionThis, methodSymbol2).setType(typeMo73getReturnType), translateArgs(this.make.Idents(jCMethodDeclMethodDef.params), typeErasure.mo71getParameterTypes(), null)).setType(typeMo73getReturnType);
        boolean zHasTag = typeErasure.mo73getReturnType().hasTag(TypeTag.VOID);
        TreeMaker treeMaker3 = this.make;
        jCMethodDeclMethodDef.body = this.make.Block(0L, List.of(zHasTag ? treeMaker3.Exec(type) : treeMaker3.Return(coerce(type, typeErasure2.mo73getReturnType()))));
        listBuffer.append(jCMethodDeclMethodDef);
        classSymbol.members().enter(methodSymbol3);
    }

    public void addBridgeIfNeeded(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Symbol.ClassSymbol classSymbol, ListBuffer<JCTree> listBuffer) {
        if (symbol.kind == Kinds.Kind.MTH && symbol.name != this.names.init && (symbol.flags() & 10) == 0 && (symbol.flags() & 4096) != 4096 && symbol.isMemberOf(classSymbol, this.types)) {
            Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) symbol;
            Symbol.MethodSymbol methodSymbolBinaryImplementation = methodSymbol.binaryImplementation(classSymbol, this.types);
            Symbol.MethodSymbol methodSymbolImplementation = methodSymbol.implementation(classSymbol, this.types, true);
            if (methodSymbolBinaryImplementation == null || methodSymbolBinaryImplementation == methodSymbol || !(methodSymbolImplementation == null || methodSymbolBinaryImplementation.owner.isSubClass(methodSymbolImplementation.owner, this.types))) {
                if (methodSymbolImplementation != null && methodSymbolBinaryImplementation != methodSymbolImplementation && isBridgeNeeded(methodSymbol, methodSymbolImplementation, classSymbol.type)) {
                    addBridge(diagnosticPosition, methodSymbol, methodSymbolImplementation, classSymbol, listBuffer);
                    return;
                }
                if (methodSymbolImplementation == methodSymbol && methodSymbolImplementation.owner != classSymbol && (methodSymbolImplementation.flags() & 16) == 0 && (methodSymbol.flags() & Flags.AnnotationTypeElementMask) == 1 && (classSymbol.flags() & 1) > (1 & methodSymbolImplementation.owner.flags())) {
                    addBridge(diagnosticPosition, methodSymbol, methodSymbolImplementation, classSymbol, listBuffer);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addBridges(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.TypeSymbol typeSymbol, Symbol.ClassSymbol classSymbol, ListBuffer<JCTree> listBuffer) {
        Iterator<Symbol> it = typeSymbol.members().getSymbols(Scope.LookupKind.NON_RECURSIVE).iterator();
        while (it.hasNext()) {
            addBridgeIfNeeded(diagnosticPosition, it.next(), classSymbol, listBuffer);
        }
        for (List listInterfaces = this.types.interfaces(typeSymbol.type); listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
            addBridges(diagnosticPosition, ((Type) listInterfaces.head).tsym, classSymbol, listBuffer);
        }
    }

    public JCTree.JCExpression cast(JCTree.JCExpression jCExpression, Type type) {
        TreeMaker treeMaker = this.make;
        int i = treeMaker.pos;
        treeMaker.at(jCExpression.pos);
        if (!this.types.isSameType(jCExpression.type, type)) {
            if (!this.resolve.isAccessible(this.env, type.tsym)) {
                this.resolve.logAccessErrorInternal(this.env, jCExpression, type);
            }
            TreeMaker treeMaker2 = this.make;
            jCExpression = treeMaker2.TypeCast(treeMaker2.Type(type), jCExpression).setType(type);
        }
        this.make.pos = i;
        return jCExpression;
    }

    public JCTree.JCExpression coerce(JCTree.JCExpression jCExpression, Type type) {
        Type typeBaseType = type.baseType();
        if (jCExpression.type.isPrimitive() == type.isPrimitive()) {
            Types types = this.types;
            if (!types.isAssignable(jCExpression.type, typeBaseType, types.noWarnings)) {
                return cast(jCExpression, typeBaseType);
            }
        }
        return jCExpression;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean interfaceParameterIsIntersectionOrUnionType(JCTree.JCMemberReference jCMemberReference) {
        for (List listMo71getParameterTypes = jCMemberReference.getDescriptorType(this.types).mo71getParameterTypes(); listMo71getParameterTypes.nonEmpty(); listMo71getParameterTypes = listMo71getParameterTypes.tail) {
            if (isIntersectionOrUnionType((Type) listMo71getParameterTypes.head)) {
                return true;
            }
        }
        return false;
    }

    public boolean isArrayOp(JCTree.JCMemberReference jCMemberReference) {
        return jCMemberReference.sym.owner == this.syms.arrayClass;
    }

    public boolean isIntersectionOrUnionType(Type type) {
        int i = AnonymousClass1.$SwitchMap$javax$lang$model$type$TypeKind[type.getKind().ordinal()];
        if (i == 1 || i == 2) {
            return true;
        }
        if (i != 3) {
            return false;
        }
        return isIntersectionOrUnionType(((Type.TypeVar) type).getUpperBound());
    }

    public boolean isPrivateInOtherClass(JCTree.JCMemberReference jCMemberReference) {
        if ((jCMemberReference.sym.flags() & 2) == 0) {
            return false;
        }
        Types types = this.types;
        return !types.isSameType(types.erasure(jCMemberReference.sym.enclClass().asType()), this.types.erasure(this.env.enclClass.sym.asType()));
    }

    public boolean needsConversionToLambda(JCTree.JCMemberReference jCMemberReference) {
        if (interfaceParameterIsIntersectionOrUnionType(jCMemberReference) || jCMemberReference.hasKind(JCTree.JCMemberReference.ReferenceKind.SUPER) || needsVarArgsConversion(jCMemberReference) || isArrayOp(jCMemberReference)) {
            return true;
        }
        if ((!this.target.runtimeUseNestAccess() && isPrivateInOtherClass(jCMemberReference)) || isProtectedInSuperClassOfEnclosingClassInOtherPackage(jCMemberReference.sym, this.env.enclClass.sym) || !receiverAccessible(jCMemberReference)) {
            return true;
        }
        if (jCMemberReference.getMode() != MemberReferenceTree.ReferenceMode.NEW || jCMemberReference.kind == JCTree.JCMemberReference.ReferenceKind.ARRAY_CTOR) {
            return false;
        }
        return jCMemberReference.sym.owner.isDirectlyOrIndirectlyLocal() || jCMemberReference.sym.owner.isInner();
    }

    public boolean needsVarArgsConversion(JCTree.JCMemberReference jCMemberReference) {
        return jCMemberReference.varargsElement != null;
    }

    public boolean receiverAccessible(JCTree.JCMemberReference jCMemberReference) {
        return jCMemberReference.ownerAccessible;
    }

    public JCTree.JCExpression retype(JCTree.JCExpression jCExpression, Type type, Type type2) {
        if (!type.isPrimitive()) {
            if (type2 != null && type2.isPrimitive()) {
                type2 = erasure(jCExpression.type);
            }
            jCExpression.type = type;
            if (type2 != null) {
                return coerce(jCExpression, type2);
            }
        }
        return jCExpression;
    }

    public <T extends JCTree> T translate(T t, Type type) {
        Type type2 = this.pt;
        try {
            this.pt = type;
            return (T) translate(t);
        } finally {
            this.pt = type2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [A, com.sun.tools.javac.tree.JCTree] */
    /* JADX WARN: Type inference failed for: r3v1, types: [A, com.sun.tools.javac.tree.JCTree] */
    /* JADX WARN: Type inference failed for: r5v7, types: [A, com.sun.tools.javac.tree.JCTree] */
    public <T extends JCTree> List<T> translateArgs(List<T> list, List<Type> list2, Type type) {
        if (!list2.isEmpty()) {
            List list3 = list;
            List list4 = list2;
            while (list4.tail.nonEmpty()) {
                list3.head = translate((JCTree) list3.head, (Type) list4.head);
                list3 = list3.tail;
                list4 = list4.tail;
            }
            Type type2 = (Type) list4.head;
            boolean z = true;
            if (type == null && list3.length() != 1) {
                z = false;
            }
            Assert.check(z);
            if (type == null) {
                list3.head = translate((JCTree) list3.head, type2);
                return list;
            }
            while (list3.nonEmpty()) {
                list3.head = translate((JCTree) list3.head, type);
                list3 = list3.tail;
            }
        }
        return list;
    }

    public void translateClass(Symbol.ClassSymbol classSymbol) {
        Type typeSupertype = this.types.supertype(classSymbol.type);
        if (typeSupertype.hasTag(TypeTag.CLASS)) {
            translateClass((Symbol.ClassSymbol) typeSupertype.tsym);
        }
        Env<AttrContext> env = this.enter.getEnv(classSymbol);
        if (env != null) {
            long j = classSymbol.flags_field;
            if ((j & Flags.TYPE_TRANSLATED) != 0) {
                return;
            }
            classSymbol.flags_field = j | Flags.TYPE_TRANSLATED;
            boolean z = this.compileStates.get(env) != null;
            if (!z && classSymbol.outermostClass() == classSymbol) {
                Assert.error("No info for outermost class: " + env.enclClass.sym);
            }
            if (z && CompileStates.CompileState.WARN.isAfter(this.compileStates.get(env))) {
                Assert.error(String.format(statePreviousToFlowAssertMsg, this.compileStates.get(env), env.enclClass.sym));
            }
            Env<AttrContext> env2 = this.env;
            try {
                this.env = env;
                TreeMaker treeMaker = this.make;
                Type type = this.pt;
                this.make = treeMaker.forToplevel(env.toplevel);
                this.pt = null;
                try {
                    JCTree.JCClassDecl jCClassDecl = (JCTree.JCClassDecl) this.env.tree;
                    jCClassDecl.typarams = List.nil();
                    super.visitClassDef(jCClassDecl);
                    this.make.at(jCClassDecl.pos);
                    ListBuffer<JCTree> listBuffer = new ListBuffer<>();
                    addBridges(jCClassDecl.pos(), classSymbol, listBuffer);
                    jCClassDecl.defs = listBuffer.toList().prependList(jCClassDecl.defs);
                    jCClassDecl.type = erasure(jCClassDecl.type);
                    this.make = treeMaker;
                    this.pt = type;
                    this.env = env2;
                } catch (Throwable th) {
                    this.make = treeMaker;
                    this.pt = type;
                    throw th;
                }
            } catch (Throwable th2) {
                this.env = env2;
                throw th2;
            }
        }
    }

    public JCTree translateTopLevelClass(JCTree jCTree, TreeMaker treeMaker) {
        this.make = treeMaker;
        this.pt = null;
        return translate(jCTree, (Type) null);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
        List<Attribute.TypeCompound> listFromAnnotations = this.annotate.fromAnnotations(jCAnnotatedType.annotations);
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) translate(jCAnnotatedType.underlyingType);
        jCAnnotatedType.underlyingType = jCExpression;
        jCAnnotatedType.type = jCExpression.type.annotatedType(listFromAnnotations);
        this.result = jCAnnotatedType;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
        this.result = jCAnnotation;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnyPattern(JCTree.JCAnyPattern jCAnyPattern) {
        this.result = jCAnyPattern;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) translate(jCMethodInvocation.meth, (Type) null);
        jCMethodInvocation.meth = jCExpression;
        Symbol symbol = TreeInfo.symbol(jCExpression);
        Type typeErasure = symbol.erasure(this.types);
        List listMo71getParameterTypes = !this.types.isSignaturePolymorphic((Symbol.MethodSymbol) symbol.baseSymbol()) ? jCMethodInvocation.meth.type.mo71getParameterTypes() : typeErasure.mo71getParameterTypes();
        if (symbol.name == this.names.init && symbol.owner == this.syms.enumSym) {
            listMo71getParameterTypes = listMo71getParameterTypes.tail.tail;
        }
        Type type = jCMethodInvocation.varargsElement;
        if (type != null) {
            jCMethodInvocation.varargsElement = this.types.erasure(type);
        } else if (jCMethodInvocation.args.length() != listMo71getParameterTypes.length()) {
            Assert.error(String.format("Incorrect number of arguments; expected %d, found %d", Integer.valueOf(jCMethodInvocation.args.length()), Integer.valueOf(listMo71getParameterTypes.length())));
        }
        jCMethodInvocation.args = translateArgs(jCMethodInvocation.args, listMo71getParameterTypes, jCMethodInvocation.varargsElement);
        jCMethodInvocation.type = this.types.erasure(jCMethodInvocation.type);
        this.result = retype(jCMethodInvocation, typeErasure.mo73getReturnType(), this.pt);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssert(JCTree.JCAssert jCAssert) {
        jCAssert.cond = (JCTree.JCExpression) translate(jCAssert.cond, this.syms.booleanType);
        JCTree.JCExpression jCExpression = jCAssert.detail;
        if (jCExpression != null) {
            jCAssert.detail = (JCTree.JCExpression) translate(jCExpression, erasure(jCExpression.type));
        }
        this.result = jCAssert;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssign(JCTree.JCAssign jCAssign) {
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) translate(jCAssign.lhs, (Type) null);
        jCAssign.lhs = jCExpression;
        jCAssign.rhs = (JCTree.JCExpression) translate(jCAssign.rhs, erasure(jCExpression.type));
        Type typeErasure = erasure(jCAssign.lhs.type);
        jCAssign.type = typeErasure;
        this.result = retype(jCAssign, typeErasure, this.pt);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
        jCAssignOp.lhs = (JCTree.JCExpression) translate(jCAssignOp.lhs, (Type) null);
        jCAssignOp.rhs = (JCTree.JCExpression) translate(jCAssignOp.rhs, jCAssignOp.operator.type.mo71getParameterTypes().tail.head);
        jCAssignOp.type = erasure(jCAssignOp.type);
        this.result = jCAssignOp;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBinary(JCTree.JCBinary jCBinary) {
        jCBinary.lhs = (JCTree.JCExpression) translate(jCBinary.lhs, jCBinary.operator.type.mo71getParameterTypes().head);
        jCBinary.rhs = (JCTree.JCExpression) translate(jCBinary.rhs, jCBinary.operator.type.mo71getParameterTypes().tail.head);
        this.result = jCBinary;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
        jCBindingPattern.var = (JCTree.JCVariableDecl) translate(jCBindingPattern.var, (Type) null);
        this.result = jCBindingPattern;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBreak(JCTree.JCBreak jCBreak) {
        this.result = jCBreak;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitCase(JCTree.JCCase jCCase) {
        jCCase.labels = translate(jCCase.labels, (Type) null);
        jCCase.guard = (JCTree.JCExpression) translate(jCCase.guard, this.syms.booleanType);
        jCCase.stats = translate(jCCase.stats);
        this.result = jCCase;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        translateClass(jCClassDecl.sym);
        this.result = jCClassDecl;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConditional(JCTree.JCConditional jCConditional) {
        jCConditional.cond = (JCTree.JCExpression) translate(jCConditional.cond, this.syms.booleanType);
        jCConditional.truepart = (JCTree.JCExpression) translate(jCConditional.truepart, erasure(jCConditional.type));
        jCConditional.falsepart = (JCTree.JCExpression) translate(jCConditional.falsepart, erasure(jCConditional.type));
        Type typeErasure = erasure(jCConditional.type);
        jCConditional.type = typeErasure;
        this.result = retype(jCConditional, typeErasure, this.pt);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConstantCaseLabel(JCTree.JCConstantCaseLabel jCConstantCaseLabel) {
        jCConstantCaseLabel.expr = (JCTree.JCExpression) translate(jCConstantCaseLabel.expr, (Type) null);
        this.result = jCConstantCaseLabel;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
        jCDoWhileLoop.body = (JCTree.JCStatement) translate(jCDoWhileLoop.body);
        jCDoWhileLoop.cond = (JCTree.JCExpression) translate(jCDoWhileLoop.cond, this.syms.booleanType);
        this.result = jCDoWhileLoop;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitExec(JCTree.JCExpressionStatement jCExpressionStatement) {
        jCExpressionStatement.expr = (JCTree.JCExpression) translate(jCExpressionStatement.expr, (Type) null);
        this.result = jCExpressionStatement;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForLoop(JCTree.JCForLoop jCForLoop) {
        jCForLoop.init = translate(jCForLoop.init, (Type) null);
        JCTree.JCExpression jCExpression = jCForLoop.cond;
        if (jCExpression != null) {
            jCForLoop.cond = (JCTree.JCExpression) translate(jCExpression, this.syms.booleanType);
        }
        jCForLoop.step = translate(jCForLoop.step, (Type) null);
        jCForLoop.body = (JCTree.JCStatement) translate(jCForLoop.body);
        this.result = jCForLoop;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        jCEnhancedForLoop.var = (JCTree.JCVariableDecl) translate(jCEnhancedForLoop.var, (Type) null);
        JCTree.JCExpression jCExpression = jCEnhancedForLoop.expr;
        Type type = jCExpression.type;
        JCTree.JCExpression jCExpression2 = (JCTree.JCExpression) translate(jCExpression, erasure(type));
        jCEnhancedForLoop.expr = jCExpression2;
        if (this.types.elemtype(jCExpression2.type) == null) {
            jCEnhancedForLoop.expr.type = type;
        }
        jCEnhancedForLoop.body = (JCTree.JCStatement) translate(jCEnhancedForLoop.body);
        this.result = jCEnhancedForLoop;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        Type typeErasure = jCIdent.sym.erasure(this.types);
        Symbol symbol = jCIdent.sym;
        if (symbol.kind == Kinds.Kind.TYP && symbol.type.hasTag(TypeTag.TYPEVAR)) {
            this.result = this.make.at(jCIdent.pos).Type(typeErasure);
            return;
        }
        if (jCIdent.type.constValue() != null) {
            this.result = jCIdent;
        } else if (jCIdent.sym.kind == Kinds.Kind.VAR) {
            this.result = retype(jCIdent, typeErasure, this.pt);
        } else {
            jCIdent.type = erasure(jCIdent.type);
            this.result = jCIdent;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIf(JCTree.JCIf jCIf) {
        jCIf.cond = (JCTree.JCExpression) translate(jCIf.cond, this.syms.booleanType);
        jCIf.thenpart = (JCTree.JCStatement) translate(jCIf.thenpart);
        jCIf.elsepart = (JCTree.JCStatement) translate(jCIf.elsepart);
        this.result = jCIf;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIndexed(JCTree.JCArrayAccess jCArrayAccess) {
        JCTree.JCExpression jCExpression = jCArrayAccess.indexed;
        jCArrayAccess.indexed = (JCTree.JCExpression) translate(jCExpression, erasure(jCExpression.type));
        jCArrayAccess.index = (JCTree.JCExpression) translate(jCArrayAccess.index, this.syms.intType);
        this.result = retype(jCArrayAccess, this.types.elemtype(jCArrayAccess.indexed.type), this.pt);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLambda(JCTree.JCLambda jCLambda) {
        Type type = this.returnType;
        try {
            this.returnType = erasure(jCLambda.getDescriptorType(this.types)).mo73getReturnType();
            jCLambda.params = translate(jCLambda.params);
            JCTree jCTree = jCLambda.body;
            jCLambda.body = translate(jCTree, (jCTree.type == null || this.returnType.hasTag(TypeTag.VOID)) ? null : this.returnType);
            if (jCLambda.type.isIntersection()) {
                Types types = this.types;
                jCLambda.type = types.erasure(types.findDescriptorSymbol(jCLambda.type.tsym).owner.type);
            } else {
                jCLambda.type = erasure(jCLambda.type);
            }
            this.result = jCLambda;
        } finally {
            this.returnType = type;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
        Type type = this.returnType;
        try {
            this.returnType = erasure(jCMethodDecl.type).mo73getReturnType();
            jCMethodDecl.restype = (JCTree.JCExpression) translate(jCMethodDecl.restype, (Type) null);
            jCMethodDecl.typarams = List.nil();
            jCMethodDecl.params = translateVarDefs(jCMethodDecl.params);
            jCMethodDecl.recvparam = (JCTree.JCVariableDecl) translate(jCMethodDecl.recvparam, (Type) null);
            jCMethodDecl.thrown = translate(jCMethodDecl.thrown, (Type) null);
            jCMethodDecl.body = (JCTree.JCBlock) translate(jCMethodDecl.body, jCMethodDecl.sym.erasure(this.types).mo73getReturnType());
            jCMethodDecl.type = erasure(jCMethodDecl.type);
            this.result = jCMethodDecl;
        } finally {
            this.returnType = type;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewArray(JCTree.JCNewArray jCNewArray) {
        jCNewArray.elemtype = (JCTree.JCExpression) translate(jCNewArray.elemtype, (Type) null);
        translate(jCNewArray.dims, this.syms.intType);
        Type type = jCNewArray.type;
        List<JCTree.JCExpression> list = jCNewArray.elems;
        if (type != null) {
            jCNewArray.elems = translate(list, erasure(this.types.elemtype(type)));
            jCNewArray.type = erasure(jCNewArray.type);
        } else {
            jCNewArray.elems = translate(list, (Type) null);
        }
        this.result = jCNewArray;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewClass(JCTree.JCNewClass jCNewClass) {
        JCTree.JCExpression jCExpression = jCNewClass.encl;
        if (jCExpression != null) {
            if (jCNewClass.def == null) {
                jCNewClass.encl = (JCTree.JCExpression) translate(jCExpression, erasure(jCExpression.type));
            } else {
                jCNewClass.args = jCNewClass.args.prepend(this.attr.makeNullCheck(jCExpression));
                jCNewClass.encl = null;
            }
        }
        Type type = jCNewClass.constructorType;
        Type typeErasure = type != null ? erasure(type) : null;
        List<Type> listMo71getParameterTypes = typeErasure != null ? typeErasure.mo71getParameterTypes() : jCNewClass.constructor.erasure(this.types).mo71getParameterTypes();
        jCNewClass.clazz = (JCTree.JCExpression) translate(jCNewClass.clazz, (Type) null);
        Type type2 = jCNewClass.varargsElement;
        if (type2 != null) {
            jCNewClass.varargsElement = this.types.erasure(type2);
        }
        jCNewClass.args = translateArgs(jCNewClass.args, listMo71getParameterTypes, jCNewClass.varargsElement);
        jCNewClass.def = (JCTree.JCClassDecl) translate(jCNewClass.def, (Type) null);
        if (typeErasure != null) {
            jCNewClass.constructorType = typeErasure;
        }
        jCNewClass.type = erasure(jCNewClass.type);
        this.result = jCNewClass;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitParens(JCTree.JCParens jCParens) {
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) translate(jCParens.expr, this.pt);
        jCParens.expr = jCExpression;
        jCParens.type = erasure(jCExpression.type);
        this.result = jCParens;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitPatternCaseLabel(JCTree.JCPatternCaseLabel jCPatternCaseLabel) {
        jCPatternCaseLabel.pat = (JCTree.JCPattern) translate(jCPatternCaseLabel.pat, (Type) null);
        this.result = jCPatternCaseLabel;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitRecordPattern(final JCTree.JCRecordPattern jCRecordPattern) {
        jCRecordPattern.fullComponentTypes = jCRecordPattern.record.getRecordComponents().map(new Function() { // from class: nje
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.types.memberType(jCRecordPattern.type, (Symbol.RecordComponent) obj);
            }
        });
        jCRecordPattern.deconstructor = (JCTree.JCExpression) translate(jCRecordPattern.deconstructor, (Type) null);
        jCRecordPattern.nested = translate(jCRecordPattern.nested, (Type) null);
        this.result = jCRecordPattern;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReference(JCTree.JCMemberReference jCMemberReference) {
        if (needsConversionToLambda(jCMemberReference)) {
            this.result = translate(new MemberReferenceToLambda(jCMemberReference).lambda());
            return;
        }
        Type typeSkipTypeVars = this.types.skipTypeVars(jCMemberReference.expr.type, false);
        if (typeSkipTypeVars.isCompound()) {
            typeSkipTypeVars = jCMemberReference.sym.owner.type;
        }
        Type typeErasure = erasure(typeSkipTypeVars);
        if (jCMemberReference.kind == JCTree.JCMemberReference.ReferenceKind.UNBOUND) {
            jCMemberReference.expr = this.make.Type(typeErasure);
        } else {
            jCMemberReference.expr = (JCTree.JCExpression) translate(jCMemberReference.expr, typeErasure);
        }
        if (jCMemberReference.type.isIntersection()) {
            Types types = this.types;
            jCMemberReference.type = types.erasure(types.findDescriptorSymbol(jCMemberReference.type.tsym).owner.type);
        } else {
            jCMemberReference.type = erasure(jCMemberReference.type);
        }
        this.result = jCMemberReference;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReturn(JCTree.JCReturn jCReturn) {
        if (!this.returnType.hasTag(TypeTag.VOID)) {
            jCReturn.expr = (JCTree.JCExpression) translate(jCReturn.expr, this.returnType);
        }
        this.result = jCReturn;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
        Type typeSkipTypeVars = this.types.skipTypeVars(jCFieldAccess.selected.type, false);
        boolean zIsCompound = typeSkipTypeVars.isCompound();
        JCTree.JCExpression jCExpression = jCFieldAccess.selected;
        if (zIsCompound) {
            jCFieldAccess.selected = coerce((JCTree.JCExpression) translate(jCExpression, erasure(jCExpression.type)), erasure(jCFieldAccess.sym.owner.type));
        } else {
            jCFieldAccess.selected = (JCTree.JCExpression) translate(jCExpression, erasure(typeSkipTypeVars));
        }
        if (jCFieldAccess.type.constValue() != null) {
            this.result = jCFieldAccess;
            return;
        }
        Symbol symbol = jCFieldAccess.sym;
        if (symbol.kind == Kinds.Kind.VAR) {
            this.result = retype(jCFieldAccess, symbol.erasure(this.types), this.pt);
        } else {
            jCFieldAccess.type = erasure(jCFieldAccess.type);
            this.result = jCFieldAccess;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitch(JCTree.JCSwitch jCSwitch) {
        JCTree.JCExpression jCExpression = jCSwitch.selector;
        jCSwitch.selector = (JCTree.JCExpression) translate(jCExpression, erasure(jCExpression.type));
        jCSwitch.cases = translateCases(jCSwitch.cases);
        this.result = jCSwitch;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
        JCTree.JCExpression jCExpression = jCSwitchExpression.selector;
        jCSwitchExpression.selector = (JCTree.JCExpression) translate(jCExpression, erasure(jCExpression.type));
        jCSwitchExpression.cases = translate(jCSwitchExpression.cases, erasure(jCSwitchExpression.type));
        Type typeErasure = erasure(jCSwitchExpression.type);
        jCSwitchExpression.type = typeErasure;
        this.result = retype(jCSwitchExpression, typeErasure, this.pt);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSynchronized(JCTree.JCSynchronized jCSynchronized) {
        JCTree.JCExpression jCExpression = jCSynchronized.lock;
        jCSynchronized.lock = (JCTree.JCExpression) translate(jCExpression, erasure(jCExpression.type));
        jCSynchronized.body = (JCTree.JCBlock) translate(jCSynchronized.body);
        this.result = jCSynchronized;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitThrow(JCTree.JCThrow jCThrow) {
        JCTree.JCExpression jCExpression = jCThrow.expr;
        jCThrow.expr = (JCTree.JCExpression) translate(jCExpression, erasure(jCExpression.type));
        this.result = jCThrow;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTry(JCTree.JCTry jCTry) {
        jCTry.resources = translate(jCTry.resources, this.syms.autoCloseableType);
        jCTry.body = (JCTree.JCBlock) translate(jCTry.body);
        jCTry.catchers = translateCatchers(jCTry.catchers);
        jCTry.finalizer = (JCTree.JCBlock) translate(jCTry.finalizer);
        this.result = jCTry;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
        this.result = translate(jCTypeApply.clazz, (Type) null);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeArray(JCTree.JCArrayTypeTree jCArrayTypeTree) {
        jCArrayTypeTree.elemtype = (JCTree.JCExpression) translate(jCArrayTypeTree.elemtype, (Type) null);
        jCArrayTypeTree.type = erasure(jCArrayTypeTree.type);
        this.result = jCArrayTypeTree;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
        jCTypeCast.clazz = translate(jCTypeCast.clazz, (Type) null);
        Type type = jCTypeCast.type;
        Type typeErasure = erasure(type);
        jCTypeCast.type = typeErasure;
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) translate(jCTypeCast.expr, typeErasure);
        if (jCExpression != jCTypeCast.expr) {
            JCTree.JCTypeCast jCTypeCast2 = jCExpression.hasTag(JCTree.Tag.TYPECAST) ? (JCTree.JCTypeCast) jCExpression : null;
            if (jCTypeCast2 != null && this.types.isSameType(jCTypeCast2.type, jCTypeCast.type)) {
                jCExpression = jCTypeCast2.expr;
            }
            jCTypeCast.expr = jCExpression;
        }
        if (type.isIntersection()) {
            Iterator<Type> it = ((Type.IntersectionClassType) type).getExplicitComponents().iterator();
            while (it.hasNext()) {
                Type typeErasure2 = erasure(it.next());
                if (!this.types.isSameType(typeErasure2, jCTypeCast.type) && !this.types.isSameType(typeErasure2, this.pt)) {
                    jCTypeCast.expr = coerce(jCTypeCast.expr, typeErasure2);
                }
            }
        }
        this.result = retype(jCTypeCast, jCTypeCast.type, this.pt);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIntersection(JCTree.JCTypeIntersection jCTypeIntersection) {
        this.result = translate(jCTypeIntersection.bounds.head, (Type) null);
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
        JCTree jCTreeTranslate = translate(jCInstanceOf.pattern, (Type) null);
        jCInstanceOf.pattern = jCTreeTranslate;
        boolean zIsPrimitive = jCTreeTranslate.type.isPrimitive();
        JCTree.JCExpression jCExpression = jCInstanceOf.expr;
        if (zIsPrimitive) {
            jCInstanceOf.erasedExprOriginalType = erasure(jCExpression.type);
            jCInstanceOf.expr = (JCTree.JCExpression) translate(jCInstanceOf.expr, (Type) null);
        } else {
            jCInstanceOf.expr = (JCTree.JCExpression) translate(jCExpression, (Type) null);
        }
        this.result = jCInstanceOf;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUnary(JCTree.JCUnary jCUnary) {
        jCUnary.arg = (JCTree.JCExpression) translate(jCUnary.arg, jCUnary.getTag() == JCTree.Tag.NULLCHK ? jCUnary.type : jCUnary.operator.type.mo71getParameterTypes().head);
        this.result = jCUnary;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        jCVariableDecl.vartype = (JCTree.JCExpression) translate(jCVariableDecl.vartype, (Type) null);
        jCVariableDecl.init = (JCTree.JCExpression) translate(jCVariableDecl.init, jCVariableDecl.sym.erasure(this.types));
        jCVariableDecl.type = erasure(jCVariableDecl.type);
        this.result = jCVariableDecl;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
        jCWhileLoop.cond = (JCTree.JCExpression) translate(jCWhileLoop.cond, this.syms.booleanType);
        jCWhileLoop.body = (JCTree.JCStatement) translate(jCWhileLoop.body);
        this.result = jCWhileLoop;
    }

    @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitYield(JCTree.JCYield jCYield) {
        JCTree.JCExpression jCExpression = jCYield.value;
        JCTree.JCExpression jCExpression2 = (JCTree.JCExpression) translate(jCExpression, erasure(jCExpression.type));
        jCYield.value = jCExpression2;
        jCExpression2.type = erasure(jCExpression2.type);
        JCTree.JCExpression jCExpression3 = jCYield.value;
        jCYield.value = retype(jCExpression3, jCExpression3.type, this.pt);
        this.result = jCYield;
    }

    public <T extends JCTree> List<T> translate(List<T> list, Type type) {
        Type type2 = this.pt;
        try {
            this.pt = type;
            return translate(list);
        } finally {
            this.pt = type2;
        }
    }

    public JCTree.JCExpression coerce(Env<AttrContext> env, JCTree.JCExpression jCExpression, Type type) {
        Env<AttrContext> env2 = this.env;
        try {
            this.env = env;
            return coerce(jCExpression, type);
        } finally {
            this.env = env2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addBridges(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.ClassSymbol classSymbol, ListBuffer<JCTree> listBuffer) {
        Type typeSupertype = this.types.supertype(classSymbol.type);
        while (typeSupertype.hasTag(TypeTag.CLASS)) {
            addBridges(diagnosticPosition, typeSupertype.tsym, classSymbol, listBuffer);
            typeSupertype = this.types.supertype(typeSupertype);
        }
        for (List listInterfaces = this.types.interfaces(classSymbol.type); listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
            addBridges(diagnosticPosition, ((Type) listInterfaces.head).tsym, classSymbol, listBuffer);
        }
    }

    public <T extends JCTree> List<T> translateArgs(List<T> list, List<Type> list2, Type type, Env<AttrContext> env) {
        Env<AttrContext> env2 = this.env;
        try {
            this.env = env;
            return translateArgs(list, list2, type);
        } finally {
            this.env = env2;
        }
    }
}

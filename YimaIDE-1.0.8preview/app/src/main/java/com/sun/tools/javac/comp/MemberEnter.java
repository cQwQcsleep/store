package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Names;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MemberEnter extends JCTree.Visitor {
    protected static final Context.Key<MemberEnter> memberEnterKey = new Context.Key<>();
    private final Annotate annotate;
    private final Attr attr;
    private final Check chk;
    private final Enter enter;
    protected Env<AttrContext> env;
    private final Log log;
    private final Names names;
    private final Source source;
    private final Symtab syms;
    private final Types types;

    public static class InitTreeVisitor extends JCTree.Visitor {
        private static final Set<JCTree.Tag> ALLOWED_OPERATORS = EnumSet.of(JCTree.Tag.POS, JCTree.Tag.NEG, JCTree.Tag.NOT, JCTree.Tag.COMPL, JCTree.Tag.PLUS, JCTree.Tag.MINUS, JCTree.Tag.MUL, JCTree.Tag.DIV, JCTree.Tag.MOD, JCTree.Tag.SL, JCTree.Tag.SR, JCTree.Tag.USR, JCTree.Tag.LT, JCTree.Tag.LE, JCTree.Tag.GT, JCTree.Tag.GE, JCTree.Tag.EQ, JCTree.Tag.NE, JCTree.Tag.BITAND, JCTree.Tag.BITXOR, JCTree.Tag.BITOR, JCTree.Tag.AND, JCTree.Tag.OR);
        boolean result = true;

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBinary(JCTree.JCBinary jCBinary) {
            if (!ALLOWED_OPERATORS.contains(jCBinary.getTag())) {
                this.result = false;
            } else {
                jCBinary.lhs.accept(this);
                jCBinary.rhs.accept(this);
            }
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitConditional(JCTree.JCConditional jCConditional) {
            jCConditional.cond.accept(this);
            jCConditional.truepart.accept(this);
            jCConditional.falsepart.accept(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIdent(JCTree.JCIdent jCIdent) {
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLiteral(JCTree.JCLiteral jCLiteral) {
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitParens(JCTree.JCParens jCParens) {
            jCParens.expr.accept(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
            jCFieldAccess.selected.accept(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTree(JCTree jCTree) {
            this.result = false;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
            jCTypeCast.expr.accept(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitUnary(JCTree.JCUnary jCUnary) {
            if (ALLOWED_OPERATORS.contains(jCUnary.getTag())) {
                jCUnary.arg.accept(this);
            } else {
                this.result = false;
            }
        }
    }

    public MemberEnter(Context context) {
        context.put(memberEnterKey, this);
        this.enter = Enter.instance(context);
        this.log = Log.instance(context);
        this.chk = Check.instance(context);
        this.attr = Attr.instance(context);
        this.syms = Symtab.instance(context);
        this.annotate = Annotate.instance(context);
        this.types = Types.instance(context);
        this.source = Source.instance(context);
        this.names = Names.instance(context);
    }

    public static MemberEnter instance(Context context) {
        MemberEnter memberEnter = (MemberEnter) context.get(memberEnterKey);
        return memberEnter == null ? new MemberEnter(context) : memberEnter;
    }

    public void checkReceiver(JCTree.JCVariableDecl jCVariableDecl, Env<AttrContext> env) {
        this.attr.attribExpr(jCVariableDecl.nameexpr, env);
        Symbol.MethodSymbol methodSymbol = env.enclMethod.sym;
        if (!methodSymbol.isConstructor()) {
            JCTree.JCExpression jCExpression = jCVariableDecl.vartype;
            Type type = methodSymbol.owner.type;
            checkType(jCExpression, type, CompilerProperties.Errors.IncorrectReceiverType(type, jCExpression.type));
            JCTree.JCExpression jCExpression2 = jCVariableDecl.nameexpr;
            Type type2 = methodSymbol.owner.type;
            checkType(jCExpression2, type2, CompilerProperties.Errors.IncorrectReceiverName(type2, jCExpression2.type));
            return;
        }
        Type type3 = methodSymbol.owner.owner.type;
        if (type3.hasTag(TypeTag.METHOD)) {
            type3 = methodSymbol.owner.owner.owner.type;
        }
        if (!type3.hasTag(TypeTag.CLASS)) {
            this.log.error(jCVariableDecl, CompilerProperties.Errors.ReceiverParameterNotApplicableConstructorToplevelClass);
            return;
        }
        JCTree.JCExpression jCExpression3 = jCVariableDecl.vartype;
        checkType(jCExpression3, type3, CompilerProperties.Errors.IncorrectConstructorReceiverType(type3, jCExpression3.type));
        JCTree.JCExpression jCExpression4 = jCVariableDecl.nameexpr;
        checkType(jCExpression4, type3, CompilerProperties.Errors.IncorrectConstructorReceiverName(type3, jCExpression4.type));
    }

    public void checkType(JCTree jCTree, Type type, JCDiagnostic.Error error) {
        if (jCTree.type.isErroneous() || this.types.isSameType(jCTree.type, type)) {
            return;
        }
        this.log.error(jCTree, error);
    }

    public Env<AttrContext> getInitEnv(JCTree.JCVariableDecl jCVariableDecl, Env<AttrContext> env) {
        return initEnv(jCVariableDecl, env);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Env<AttrContext> getMethodEnv(JCTree.JCMethodDecl jCMethodDecl, Env<AttrContext> env) {
        Env<AttrContext> envMethodEnv = methodEnv(jCMethodDecl, env);
        AttrContext attrContext = envMethodEnv.info;
        attrContext.lint = attrContext.lint.augment(jCMethodDecl.sym);
        for (List list = jCMethodDecl.typarams; list.nonEmpty(); list = list.tail) {
            envMethodEnv.info.scope.enterIfAbsent(((JCTree.JCTypeParameter) list.head).type.tsym);
        }
        for (List list2 = jCMethodDecl.params; list2.nonEmpty(); list2 = list2.tail) {
            envMethodEnv.info.scope.enterIfAbsent(((JCTree.JCVariableDecl) list2.head).sym);
        }
        return envMethodEnv;
    }

    public Env<AttrContext> initEnv(JCTree.JCVariableDecl jCVariableDecl, Env<AttrContext> env) {
        Env<AttrContext> envDupto = env.dupto(new AttrContextEnv(jCVariableDecl, env.info.dup()));
        Symbol.VarSymbol varSymbol = jCVariableDecl.sym;
        if (varSymbol.owner.kind == Kinds.Kind.TYP) {
            envDupto.info.scope = env.info.scope.dupUnshared(varSymbol);
        }
        if ((jCVariableDecl.mods.flags & 8) == 0 && ((env.enclClass.sym.flags() & 512) == 0 || env.enclMethod != null)) {
            return envDupto;
        }
        envDupto.info.staticLevel++;
        return envDupto;
    }

    public void memberEnter(JCTree jCTree, Env<AttrContext> env) {
        Env<AttrContext> env2 = this.env;
        try {
            this.env = env;
            jCTree.accept(this);
        } catch (Symbol.CompletionFailure e) {
            this.chk.completionError(jCTree.pos(), e);
        } finally {
            this.env = env2;
        }
    }

    public Env<AttrContext> methodEnv(JCTree.JCMethodDecl jCMethodDecl, Env<AttrContext> env) {
        AttrContext attrContext = env.info;
        Env<AttrContext> envDup = env.dup(jCMethodDecl, attrContext.dup(attrContext.scope.dupUnshared(jCMethodDecl.sym)));
        envDup.enclMethod = jCMethodDecl;
        if (jCMethodDecl.sym.type != null) {
            AttrContext attrContext2 = envDup.info;
            Attr attr = this.attr;
            Objects.requireNonNull(attr);
            attrContext2.returnResult = new Attr.ResultInfo(attr, Kinds.KindSelector.VAL, jCMethodDecl.sym.type.mo73getReturnType());
        }
        if ((jCMethodDecl.mods.flags & 8) != 0) {
            envDup.info.staticLevel++;
        }
        envDup.info.yieldResult = null;
        return envDup;
    }

    public boolean needsLazyConstValue(JCTree jCTree) {
        InitTreeVisitor initTreeVisitor = new InitTreeVisitor();
        jCTree.accept(initTreeVisitor);
        return initTreeVisitor.result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Type signature(Symbol.MethodSymbol methodSymbol, List<JCTree.JCTypeParameter> list, List<JCTree.JCVariableDecl> list2, JCTree jCTree, JCTree.JCVariableDecl jCVariableDecl, List<JCTree.JCExpression> list3, Env<AttrContext> env) {
        Type type;
        List<Type> listClassEnter = this.enter.classEnter(list, env);
        this.attr.attribTypeVariables(list, env, true);
        ListBuffer listBuffer = new ListBuffer();
        for (List list4 = list2; list4.nonEmpty(); list4 = list4.tail) {
            memberEnter((JCTree) list4.head, env);
            listBuffer.append(((JCTree.JCVariableDecl) list4.head).vartype.type);
        }
        Type typeAttribType = jCTree == null ? this.syms.voidType : this.attr.attribType(jCTree, env);
        if (jCVariableDecl != null) {
            memberEnter(jCVariableDecl, env);
            type = jCVariableDecl.vartype.type;
        } else {
            type = null;
        }
        ListBuffer listBuffer2 = new ListBuffer();
        for (List list5 = list3; list5.nonEmpty(); list5 = list5.tail) {
            Type typeAttribType2 = this.attr.attribType((JCTree) list5.head, env);
            if (typeAttribType2.hasTag(TypeTag.TYPEVAR)) {
                Symbol.TypeSymbol typeSymbol = typeAttribType2.tsym;
                if (typeSymbol.owner == methodSymbol) {
                    typeSymbol.flags_field |= Flags.THROWS;
                }
            } else {
                typeAttribType2 = this.chk.checkClassType(((JCTree.JCExpression) list5.head).pos(), typeAttribType2);
            }
            listBuffer2.append(typeAttribType2);
        }
        Type.MethodType methodType = new Type.MethodType(listBuffer.toList(), typeAttribType, listBuffer2.toList(), this.syms.methodClass);
        methodType.recvtype = type;
        return listClassEnter.isEmpty() ? methodType : new Type.ForAll(listClassEnter, methodType);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitErroneous(JCTree.JCErroneous jCErroneous) {
        List<? extends JCTree> list = jCErroneous.errs;
        if (list != null) {
            memberEnter(list, this.env);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
        Scope.WriteableScope writeableScopeEnterScope = this.enter.enterScope(this.env);
        Symbol.MethodSymbol methodSymbol = new Symbol.MethodSymbol(0L, jCMethodDecl.name, null, writeableScopeEnterScope.owner);
        methodSymbol.flags_field = this.chk.checkFlags(jCMethodDecl.mods.flags, methodSymbol, jCMethodDecl);
        jCMethodDecl.sym = methodSymbol;
        if ((jCMethodDecl.mods.flags & Flags.DEFAULT) != 0) {
            methodSymbol.owner.flags_field |= Flags.DEFAULT;
        }
        Env<AttrContext> envMethodEnv = methodEnv(jCMethodDecl, this.env);
        methodSymbol.type = signature(methodSymbol, jCMethodDecl.typarams, jCMethodDecl.params, jCMethodDecl.restype, jCMethodDecl.recvparam, jCMethodDecl.thrown, envMethodEnv);
        if (this.types.isSignaturePolymorphic(methodSymbol)) {
            methodSymbol.flags_field |= Flags.SIGNATURE_POLYMORPHIC;
        }
        ListBuffer listBuffer = new ListBuffer();
        JCTree.JCVariableDecl jCVariableDecl = null;
        for (List list = jCMethodDecl.params; list.nonEmpty(); list = list.tail) {
            jCVariableDecl = (JCTree.JCVariableDecl) list.head;
            listBuffer.append((Symbol.VarSymbol) Assert.checkNonNull(jCVariableDecl.sym));
        }
        methodSymbol.params = listBuffer.toList();
        if (jCVariableDecl != null && (jCVariableDecl.mods.flags & Flags.VARARGS) != 0) {
            methodSymbol.flags_field |= Flags.VARARGS;
        }
        envMethodEnv.info.scope.leave();
        if (this.chk.checkUnique(jCMethodDecl.pos(), methodSymbol, writeableScopeEnterScope)) {
            writeableScopeEnterScope.enter(methodSymbol);
        }
        this.annotate.annotateLater(jCMethodDecl.mods.annotations, envMethodEnv, methodSymbol);
        this.annotate.queueScanTreeAndTypeAnnotate(jCMethodDecl, envMethodEnv, methodSymbol);
        if (jCMethodDecl.defaultValue != null) {
            methodSymbol.defaultValue = this.annotate.unfinishedDefaultValue();
            this.annotate.annotateDefaultValueLater(jCMethodDecl.defaultValue, envMethodEnv, methodSymbol);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTree(JCTree jCTree) {
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
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        Type type;
        Env<AttrContext> envDup = this.env;
        if ((jCVariableDecl.mods.flags & 8) != 0 || (envDup.info.scope.owner.flags() & 512) != 0) {
            Env<AttrContext> env = this.env;
            envDup = env.dup(jCVariableDecl, env.info.dup());
            envDup.info.staticLevel++;
        }
        if (TreeInfo.isEnumInit(jCVariableDecl)) {
            this.attr.attribIdentAsEnumType(envDup, (JCTree.JCIdent) jCVariableDecl.vartype);
        } else if (!jCVariableDecl.isImplicitlyTyped()) {
            this.attr.attribType(jCVariableDecl.vartype, envDup);
            if (TreeInfo.isReceiverParam(jCVariableDecl)) {
                checkReceiver(jCVariableDecl, envDup);
            }
        }
        if ((jCVariableDecl.mods.flags & Flags.VARARGS) != 0) {
            JCTree.JCExpression jCExpression = jCVariableDecl.vartype;
            jCExpression.type = ((Type.ArrayType) jCExpression.type).makeVarargs();
        }
        Scope.WriteableScope writeableScopeEnterScope = this.enter.enterScope(this.env);
        if (jCVariableDecl.isImplicitlyTyped()) {
            type = this.env.info.scope.owner.kind == Kinds.Kind.MTH ? Type.noType : this.syms.errType;
        } else {
            type = jCVariableDecl.vartype.type;
        }
        Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(0L, jCVariableDecl.name, type, writeableScopeEnterScope.owner);
        long jCheckFlags = this.chk.checkFlags(jCVariableDecl.mods.flags, varSymbol, jCVariableDecl);
        varSymbol.flags_field = jCheckFlags;
        jCVariableDecl.sym = varSymbol;
        JCTree.JCExpression jCExpression2 = jCVariableDecl.init;
        if (jCExpression2 != null) {
            long j = jCheckFlags | 262144;
            varSymbol.flags_field = j;
            if ((j & 16) != 0 && needsLazyConstValue(jCExpression2)) {
                Env<AttrContext> initEnv = getInitEnv(jCVariableDecl, this.env);
                initEnv.info.enclVar = varSymbol;
                varSymbol.setLazyConstValue(initEnv(jCVariableDecl, initEnv), this.env, this.attr, jCVariableDecl);
            }
        }
        if (!Source.Feature.UNNAMED_VARIABLES.allowedInSource(this.source) || !jCVariableDecl.sym.isUnnamedVariable()) {
            if (this.chk.checkUnique(jCVariableDecl.pos(), varSymbol, writeableScopeEnterScope)) {
                this.chk.checkTransparentVar(jCVariableDecl.pos(), varSymbol, writeableScopeEnterScope);
                writeableScopeEnterScope.enter(varSymbol);
            } else if (varSymbol.owner.kind == Kinds.Kind.MTH || (varSymbol.flags_field & 2305843009230471186L) != 0) {
                writeableScopeEnterScope.enter(varSymbol);
            }
        }
        this.annotate.annotateLater(jCVariableDecl.mods.annotations, envDup, varSymbol);
        if (!jCVariableDecl.isImplicitlyTyped()) {
            this.annotate.queueScanTreeAndTypeAnnotate(jCVariableDecl.vartype, envDup, varSymbol);
        }
        varSymbol.pos = jCVariableDecl.pos;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void memberEnter(List<? extends JCTree> list, Env<AttrContext> env) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            memberEnter((JCTree) list2.head, env);
        }
    }
}

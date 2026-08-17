package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Attr.ResultInfo;
import com.sun.tools.javac.comp.AttrRecover;
import com.sun.tools.javac.comp.DeferredAttr.RecoveryDeferredTypeMap;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Names;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AttrRecover {
    protected static final Context.Key<AttrRecover> attrRepairKey = new Context.Key<>();
    final Attr attr;
    final Check chk;
    final DeferredAttr deferredAttr;
    final TreeMaker make;
    final Names names;
    private final ListBuffer<RecoverTodo> recoveryTodo = new ListBuffer<>();
    final Symtab syms;
    final Types types;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.AttrRecover$1, reason: invalid class name */
    public class AnonymousClass1 extends TreeTranslator {
        final /* synthetic */ AttrRecover this$0;
        final /* synthetic */ JCTree.JCLambda val$lambda;
        final /* synthetic */ ListBuffer val$rollback;
        final /* synthetic */ boolean val$voidCompatible;

        public AnonymousClass1(AttrRecover attrRecover, boolean z, ListBuffer listBuffer, JCTree.JCLambda jCLambda) {
            this.val$voidCompatible = z;
            this.val$rollback = listBuffer;
            this.val$lambda = jCLambda;
            this.this$0 = attrRecover;
        }

        public static /* synthetic */ void b(AnonymousClass1 anonymousClass1, JCTree.JCLambda jCLambda, final JCTree.JCErroneous jCErroneous, final JCTree.JCReturn jCReturn) {
            anonymousClass1.getClass();
            jCLambda.body = new TreeTranslator(anonymousClass1) { // from class: com.sun.tools.javac.comp.AttrRecover.1.1
                final /* synthetic */ AnonymousClass1 this$1;

                {
                    this.this$1 = anonymousClass1;
                }

                @Override // com.sun.tools.javac.tree.TreeTranslator
                public <T extends JCTree> T translate(T t) {
                    return t == jCErroneous ? jCReturn : (T) super.translate(t);
                }
            }.translate(jCLambda.body);
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
        public void visitReturn(final JCTree.JCReturn jCReturn) {
            this.result = jCReturn;
            if (!this.val$voidCompatible) {
                if (jCReturn.expr == null) {
                    jCReturn.expr = this.this$0.make.Erroneous().setType(this.this$0.syms.errType);
                    this.val$rollback.append(new Runnable() { // from class: com.sun.tools.javac.comp.r
                        @Override // java.lang.Runnable
                        public final void run() {
                            jCReturn.expr = null;
                        }
                    });
                    return;
                }
                return;
            }
            if (jCReturn.expr != null) {
                final JCTree.JCErroneous jCErroneousErroneous = this.this$0.make.Erroneous(List.of(jCReturn));
                this.result = jCErroneousErroneous;
                ListBuffer listBuffer = this.val$rollback;
                final JCTree.JCLambda jCLambda = this.val$lambda;
                listBuffer.append(new Runnable() { // from class: com.sun.tools.javac.comp.q
                    @Override // java.lang.Runnable
                    public final void run() {
                        AttrRecover.AnonymousClass1.b(this.b, jCLambda, jCErroneousErroneous, jCReturn);
                    }
                });
            }
        }
    }

    public static class RecoverTodo {
        public final Symbol candSym;
        public final Env<AttrContext> env;
        public final Symbol errSym;
        public final Attr.ResultInfo resultInfo;
        public final Type site;
        public final JCTree tree;

        public RecoverTodo(JCTree jCTree, Type type, Symbol symbol, Symbol symbol2, Env<AttrContext> env, Attr.ResultInfo resultInfo) {
            this.tree = jCTree;
            this.site = type;
            this.errSym = symbol;
            this.candSym = symbol2;
            this.env = env;
            this.resultInfo = resultInfo;
        }
    }

    public static class RecoveryErrorType extends Type.ErrorType {
        public final Symbol candidateSymbol;

        public RecoveryErrorType(Type.ErrorType errorType, Symbol symbol) {
            super(errorType.getOriginalType(), errorType.tsym);
            this.candidateSymbol = symbol;
        }
    }

    public AttrRecover(Context context) {
        context.put(attrRepairKey, this);
        this.attr = Attr.instance(context);
        this.chk = Check.instance(context);
        this.deferredAttr = DeferredAttr.instance(context);
        this.names = Names.instance(context);
        this.make = TreeMaker.instance(context);
        this.syms = Symtab.instance(context);
        this.types = Types.instance(context);
    }

    public static /* synthetic */ void b(JCTree.JCLambda jCLambda, JCTree.JCReturn jCReturn) {
        JCTree.JCBlock jCBlock = (JCTree.JCBlock) jCLambda.body;
        jCBlock.stats = List.filter(jCBlock.stats, jCReturn);
    }

    private Type basicMethodInvocationRecovery(JCTree jCTree, Type type, Symbol symbol, Env<AttrContext> env, Attr.ResultInfo resultInfo) {
        Type type2 = resultInfo.pt;
        DeferredAttr deferredAttr = this.deferredAttr;
        Objects.requireNonNull(deferredAttr);
        Type typeCheckIdInternal = this.attr.checkIdInternal(jCTree, type, symbol, type2.map(deferredAttr.new RecoveryDeferredTypeMap(DeferredAttr.AttrMode.SPECULATIVE, symbol, env.info.pendingResolutionPhase)), env, resultInfo);
        Type type3 = resultInfo.pt;
        DeferredAttr deferredAttr2 = this.deferredAttr;
        Objects.requireNonNull(deferredAttr2);
        type3.map(deferredAttr2.new RecoveryDeferredTypeMap(DeferredAttr.AttrMode.CHECK, symbol, env.info.pendingResolutionPhase));
        return typeCheckIdInternal;
    }

    public static AttrRecover instance(Context context) {
        AttrRecover attrRecover = (AttrRecover) context.get(attrRepairKey);
        return attrRecover == null ? new AttrRecover(context) : attrRecover;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void doRecovery() {
        AttrRecover attrRecover;
        Type typeBasicMethodInvocationRecovery;
        while (this.recoveryTodo.nonEmpty()) {
            RecoverTodo recoverTodoRemove = this.recoveryTodo.remove();
            ListBuffer listBuffer = new ListBuffer();
            boolean z = false;
            if (recoverTodoRemove.env.tree.hasTag(JCTree.Tag.APPLY)) {
                final JCTree.JCMethodInvocation jCMethodInvocation = (JCTree.JCMethodInvocation) recoverTodoRemove.env.tree;
                boolean z2 = (recoverTodoRemove.candSym.flags() & Flags.VARARGS) != 0;
                if (z2 || jCMethodInvocation.args.length() <= recoverTodoRemove.candSym.type.mo71getParameterTypes().length()) {
                    List list = jCMethodInvocation.args;
                    List listMo71getParameterTypes = recoverTodoRemove.candSym.type.mo71getParameterTypes();
                    while (list.nonEmpty() && listMo71getParameterTypes.nonEmpty()) {
                        JCTree.JCExpression jCExpression = (JCTree.JCExpression) list.head;
                        Type type = (listMo71getParameterTypes.tail.nonEmpty() || !z2) ? (Type) listMo71getParameterTypes.head : ((Type.ArrayType) listMo71getParameterTypes.head).elemtype;
                        if (jCExpression.hasTag(JCTree.Tag.LAMBDA)) {
                            final JCTree.JCLambda jCLambda = (JCTree.JCLambda) jCExpression;
                            if (jCLambda.paramKind == JCTree.JCLambda.ParameterKind.IMPLICIT) {
                                Iterator<JCTree.JCVariableDecl> it = jCLambda.params.iterator();
                                while (it.hasNext()) {
                                    it.next().vartype = null;
                                }
                            }
                            if (this.types.isFunctionalInterface(type)) {
                                boolean zHasTag = this.types.findDescriptorType(type).mo73getReturnType().hasTag(TypeTag.VOID);
                                JCTree jCTreeTranslate = new AnonymousClass1(this, zHasTag, listBuffer, jCLambda).translate(jCLambda.body);
                                jCLambda.body = jCTreeTranslate;
                                if (!zHasTag && jCTreeTranslate.hasTag(JCTree.Tag.BLOCK)) {
                                    TreeMaker treeMaker = this.make;
                                    final JCTree.JCReturn jCReturnReturn = treeMaker.Return(treeMaker.Erroneous().setType(this.syms.errType));
                                    JCTree jCTree = jCLambda.body;
                                    ((JCTree.JCBlock) jCTree).stats = ((JCTree.JCBlock) jCTree).stats.append(jCReturnReturn);
                                    listBuffer.append(new Runnable() { // from class: aj0
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            AttrRecover.b(jCLambda, jCReturnReturn);
                                        }
                                    });
                                }
                            }
                            z = true;
                        }
                        list = list.tail;
                        if (listMo71getParameterTypes.tail.nonEmpty() || !z2) {
                            listMo71getParameterTypes = listMo71getParameterTypes.tail;
                        }
                    }
                    final List<JCTree.JCExpression> list2 = jCMethodInvocation.args;
                    while (listMo71getParameterTypes.nonEmpty()) {
                        jCMethodInvocation.args = jCMethodInvocation.args.append(this.make.Erroneous().setType(this.syms.errType));
                        listMo71getParameterTypes = listMo71getParameterTypes.tail;
                        z = true;
                    }
                    listBuffer.append(new Runnable() { // from class: bj0
                        @Override // java.lang.Runnable
                        public final void run() {
                            jCMethodInvocation.args = list2;
                        }
                    });
                }
            }
            if (z) {
                List<JCTree.JCExpression> listArgs = TreeInfo.args(recoverTodoRemove.env.tree);
                List<Type> listMo71getParameterTypes2 = recoverTodoRemove.resultInfo.pt.mo71getParameterTypes();
                while (listMo71getParameterTypes2.length() < listArgs.length()) {
                    listMo71getParameterTypes2 = listMo71getParameterTypes2.append(this.syms.errType);
                }
                Check.NestedCheckContext nestedCheckContext = new Check.NestedCheckContext(recoverTodoRemove.resultInfo.checkContext) { // from class: com.sun.tools.javac.comp.AttrRecover.2
                    @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
                    public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
                        AttrRecover.this.chk.basicHandler.report(diagnosticPosition, jCDiagnostic);
                    }
                };
                Attr attr = this.attr;
                Type type2 = recoverTodoRemove.site;
                Symbol symbol = recoverTodoRemove.candSym;
                Objects.requireNonNull(attr);
                Attr.ResultInfo resultInfo = recoverTodoRemove.resultInfo;
                typeBasicMethodInvocationRecovery = attr.checkMethod(type2, symbol, attr.new ResultInfo(resultInfo.pkind, resultInfo.pt.mo73getReturnType(), nestedCheckContext, recoverTodoRemove.resultInfo.checkMode), recoverTodoRemove.env, listArgs, listMo71getParameterTypes2, recoverTodoRemove.resultInfo.pt.getTypeArguments());
                listBuffer.forEach(new Consumer() { // from class: cj0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((Runnable) obj).run();
                    }
                });
                attrRecover = this;
            } else {
                attrRecover = this;
                typeBasicMethodInvocationRecovery = attrRecover.basicMethodInvocationRecovery(recoverTodoRemove.tree, recoverTodoRemove.site, recoverTodoRemove.errSym, recoverTodoRemove.env, recoverTodoRemove.resultInfo);
            }
            recoverTodoRemove.tree.type = typeBasicMethodInvocationRecovery;
            this = attrRecover;
        }
    }

    public Type recoverMethodInvocation(JCTree jCTree, Type type, Symbol symbol, Env<AttrContext> env, Attr.ResultInfo resultInfo) {
        if ((symbol.flags_field & Flags.RECOVERABLE) == 0 || !env.info.attributionMode.recover()) {
            return basicMethodInvocationRecovery(jCTree, type, symbol, env, resultInfo);
        }
        this.recoveryTodo.append(new RecoverTodo(jCTree, type, symbol, ((RecoveryErrorType) symbol.type).candidateSymbol, this.attr.copyEnv(env), resultInfo));
        return this.syms.errType;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002a  */
    /* JADX WARN: Code duplicated, block: B:12:0x002c  */
    /* JADX WARN: Code duplicated, block: B:15:0x0035  */
    /* JADX WARN: Code duplicated, block: B:16:0x0037  */
    /* JADX WARN: Code duplicated, block: B:19:0x0040  */
    /* JADX WARN: Code duplicated, block: B:20:0x0042  */
    /* JADX WARN: Code duplicated, block: B:23:0x004b  */
    /* JADX WARN: Code duplicated, block: B:25:0x004f  */
    /* JADX WARN: Code duplicated, block: B:27:0x0056  */
    /* JADX WARN: Code duplicated, block: B:29:0x005c  */
    /* JADX WARN: Code duplicated, block: B:34:0x0065 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:5:0x000a  */
    /* JADX WARN: Code duplicated, block: B:8:0x0021  */
    /* JADX WARN: Multi-variable type inference failed */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:25:0x004f
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public void wrongMethodSymbolCandidate(com.sun.tools.javac.code.Symbol.TypeSymbol r5, com.sun.tools.javac.code.Symbol r6, com.sun.tools.javac.util.JCDiagnostic r7) {
        /*
            r4 = this;
            com.sun.tools.javac.util.List r4 = com.sun.tools.javac.util.List.of(r7)
        L4:
            boolean r7 = r4.nonEmpty()
            if (r7 == 0) goto L7a
            A r7 = r4.head
            com.sun.tools.javac.util.JCDiagnostic r7 = (com.sun.tools.javac.util.JCDiagnostic) r7
            com.sun.tools.javac.util.List<A> r4 = r4.tail
            java.lang.String r0 = r7.getCode()
            r0.getClass()
            int r1 = r0.hashCode()
            r2 = 0
            r3 = -1
            switch(r1) {
                case -1824826549: goto L42;
                case -1288879943: goto L37;
                case 302711722: goto L2c;
                case 1780358827: goto L21;
                default: goto L20;
            }
        L20:
            goto L4c
        L21:
            java.lang.String r1 = "compiler.misc.arg.length.mismatch"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L2a
            goto L4c
        L2a:
            r3 = 3
            goto L4c
        L2c:
            java.lang.String r1 = "compiler.misc.unexpected.ret.val"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L35
            goto L4c
        L35:
            r3 = 2
            goto L4c
        L37:
            java.lang.String r1 = "compiler.misc.missing.ret.val"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L40
            goto L4c
        L40:
            r3 = 1
            goto L4c
        L42:
            java.lang.String r1 = "compiler.misc.infer.arg.length.mismatch"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L4b
            goto L4c
        L4b:
            r3 = r2
        L4c:
            switch(r3) {
                case 0: goto L65;
                case 1: goto L65;
                case 2: goto L65;
                case 3: goto L65;
                default: goto L4f;
            }
        L4f:
            java.lang.Object[] r7 = r7.getArgs()
            int r0 = r7.length
        L54:
            if (r2 >= r0) goto L4
            r1 = r7[r2]
            boolean r3 = r1 instanceof com.sun.tools.javac.util.JCDiagnostic
            if (r3 == 0) goto L62
            com.sun.tools.javac.util.JCDiagnostic r1 = (com.sun.tools.javac.util.JCDiagnostic) r1
            com.sun.tools.javac.util.List r4 = r4.prepend(r1)
        L62:
            int r2 = r2 + 1
            goto L54
        L65:
            com.sun.tools.javac.comp.AttrRecover$RecoveryErrorType r4 = new com.sun.tools.javac.comp.AttrRecover$RecoveryErrorType
            com.sun.tools.javac.code.Type r7 = r5.type
            com.sun.tools.javac.code.Type$ErrorType r7 = (com.sun.tools.javac.code.Type.ErrorType) r7
            r4.<init>(r7, r6)
            r5.type = r4
            long r6 = r5.flags_field
            r0 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            long r6 = r6 | r0
            r5.flags_field = r6
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.tools.javac.comp.AttrRecover.wrongMethodSymbolCandidate(com.sun.tools.javac.code.Symbol$TypeSymbol, com.sun.tools.javac.code.Symbol, com.sun.tools.javac.util.JCDiagnostic):void");
    }
}

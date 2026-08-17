package com.sun.tools.javac.comp;

import com.sun.source.tree.LambdaExpressionTree;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.ArgumentAttr;
import com.sun.tools.javac.comp.DeferredAttr.DeferredType;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeCopier;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.DiagnosticSource;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArgumentAttr extends JCTree.Visitor {
    protected static final Context.Key<ArgumentAttr> methodAttrKey = new Context.Key<>();
    Map<UniquePos, ArgumentType<?>> argumentTypeCache = new LinkedHashMap();
    private final Attr attr;
    private final DeferredAttr deferredAttr;
    private final JCDiagnostic.Factory diags;
    private Env<AttrContext> env;
    private final Log log;
    Type result;
    private final Symtab syms;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.ArgumentAttr$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$source$tree$LambdaExpressionTree$BodyKind;

        static {
            int[] iArr = new int[LambdaExpressionTree.BodyKind.values().length];
            $SwitchMap$com$sun$source$tree$LambdaExpressionTree$BodyKind = iArr;
            try {
                iArr[LambdaExpressionTree.BodyKind.EXPRESSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$source$tree$LambdaExpressionTree$BodyKind[LambdaExpressionTree.BodyKind.STATEMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public abstract class ArgumentType<T extends JCTree.JCExpression> extends DeferredAttr.DeferredType {
        T speculativeTree;
        Map<Attr.ResultInfo, Type> speculativeTypes;

        /* JADX WARN: Illegal instructions before constructor call */
        public ArgumentType(JCTree.JCExpression jCExpression, Env<AttrContext> env, T t, Map<Attr.ResultInfo, Type> map) {
            DeferredAttr deferredAttr = ArgumentAttr.this.deferredAttr;
            Objects.requireNonNull(deferredAttr);
            super(jCExpression, env);
            this.speculativeTree = t;
            this.speculativeTypes = map;
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredType
        public final Type complete(Attr.ResultInfo resultInfo, DeferredAttr.DeferredAttrContext deferredAttrContext) {
            if (deferredAttrContext.mode == DeferredAttr.AttrMode.SPECULATIVE) {
                Type typeComplete = resultInfo.pt == Type.recoveryType ? super.complete(resultInfo, deferredAttrContext) : overloadCheck(resultInfo, deferredAttrContext);
                this.speculativeTypes.put(resultInfo, typeComplete);
                return typeComplete;
            }
            if (!this.env.info.attributionMode.isSpeculative) {
                ArgumentAttr argumentAttr = ArgumentAttr.this;
                argumentAttr.argumentTypeCache.remove(argumentAttr.new UniquePos(this.tree));
            }
            return super.complete(resultInfo, deferredAttrContext);
        }

        public abstract ArgumentType<T> dup(T t, Env<AttrContext> env);

        public abstract Type overloadCheck(Attr.ResultInfo resultInfo, DeferredAttr.DeferredAttrContext deferredAttrContext);

        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredType
        public JCTree speculativeTree(DeferredAttr.DeferredAttrContext deferredAttrContext) {
            return this.notPertinentToApplicability.contains(deferredAttrContext.msym) ? super.speculativeTree(deferredAttrContext) : this.speculativeTree;
        }

        @Override // com.sun.tools.javac.comp.DeferredAttr.DeferredType
        public Type speculativeType(Symbol symbol, Resolve.MethodResolutionPhase methodResolutionPhase) {
            if (this.notPertinentToApplicability.contains(symbol)) {
                return super.speculativeType(symbol, methodResolutionPhase);
            }
            for (Map.Entry<Attr.ResultInfo, Type> entry : this.speculativeTypes.entrySet()) {
                DeferredAttr.DeferredAttrContext deferredAttrContext = entry.getKey().checkContext.deferredAttrContext();
                if (deferredAttrContext.phase == methodResolutionPhase && deferredAttrContext.msym == symbol) {
                    return entry.getValue();
                }
            }
            return Type.noType;
        }
    }

    public class LocalCacheContext {
        Map<UniquePos, ArgumentType<?>> prevCache;

        public LocalCacheContext() {
            this.prevCache = ArgumentAttr.this.argumentTypeCache;
            ArgumentAttr.this.argumentTypeCache = new HashMap();
        }

        public void leave() {
            ArgumentAttr.this.argumentTypeCache = this.prevCache;
        }
    }

    public abstract class ResolvedMemberType<E extends JCTree.JCExpression> extends ArgumentType<E> {
        public ResolvedMemberType(JCTree.JCExpression jCExpression, Env<AttrContext> env, E e, Map<Attr.ResultInfo, Type> map) {
            super(jCExpression, env, e, map);
        }

        public abstract Type methodType();

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ArgumentType
        public Type overloadCheck(Attr.ResultInfo resultInfo, DeferredAttr.DeferredAttrContext deferredAttrContext) {
            Type typeMethodType = methodType();
            Attr.ResultInfo resultInfo2 = resultInfo(resultInfo);
            Type typeCheck = (typeMethodType != null && typeMethodType.hasTag(TypeTag.METHOD) && typeMethodType.isPartial()) ? ((Infer.PartiallyInferredMethodType) typeMethodType).check(resultInfo2) : resultInfo2.check(this.tree.pos(), this.speculativeTree.type);
            this.speculativeTypes.put(resultInfo2, typeCheck);
            return typeCheck;
        }

        public abstract Attr.ResultInfo resultInfo(Attr.ResultInfo resultInfo);
    }

    public class UniquePos {
        int pos;
        DiagnosticSource source;

        public UniquePos(JCTree jCTree) {
            this.pos = jCTree.pos;
            this.source = ArgumentAttr.this.log.currentSource();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof UniquePos)) {
                return false;
            }
            UniquePos uniquePos = (UniquePos) obj;
            return this.pos == uniquePos.pos && this.source == uniquePos.source;
        }

        public int hashCode() {
            return this.pos << (this.source.hashCode() + 16);
        }

        public String toString() {
            return this.source.getFile().getName() + " @ " + this.source.getLineNumber(this.pos);
        }
    }

    public ArgumentAttr(Context context) {
        context.put(methodAttrKey, this);
        this.deferredAttr = DeferredAttr.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        this.attr = Attr.instance(context);
        this.syms = Symtab.instance(context);
        this.log = Log.instance(context);
    }

    public static /* synthetic */ ExplicitLambdaType a(ArgumentAttr argumentAttr, JCTree.JCLambda jCLambda) {
        return new ExplicitLambdaType(argumentAttr, jCLambda, argumentAttr.env, argumentAttr.deferredAttr.attribSpeculativeLambda(jCLambda, argumentAttr.env, argumentAttr.attr.methodAttrInfo));
    }

    public static /* synthetic */ ParensType b(ArgumentAttr argumentAttr, JCTree.JCParens jCParens, JCTree.JCParens jCParens2) {
        argumentAttr.getClass();
        return new ParensType(argumentAttr, jCParens, argumentAttr.env, jCParens2);
    }

    public static /* synthetic */ SwitchExpressionType c(ArgumentAttr argumentAttr, JCTree.JCSwitchExpression jCSwitchExpression, JCTree.JCSwitchExpression jCSwitchExpression2) {
        argumentAttr.getClass();
        return new SwitchExpressionType(argumentAttr, jCSwitchExpression, argumentAttr.env, jCSwitchExpression2);
    }

    public static /* synthetic */ ConditionalType d(ArgumentAttr argumentAttr, JCTree.JCConditional jCConditional, JCTree.JCConditional jCConditional2) {
        argumentAttr.getClass();
        return new ConditionalType(argumentAttr, jCConditional, argumentAttr.env, jCConditional2);
    }

    public static /* synthetic */ ArgumentType e(ArgumentAttr argumentAttr, JCTree.JCExpression jCExpression, UniquePos uniquePos, Function function) {
        DeferredAttr deferredAttr = argumentAttr.deferredAttr;
        Env<AttrContext> env = argumentAttr.env;
        Attr attr = argumentAttr.attr;
        Objects.requireNonNull(attr);
        return (ArgumentType) function.apply((JCTree.JCExpression) deferredAttr.attribSpeculative(jCExpression, env, new Attr.MethodAttrInfo(argumentAttr, attr, uniquePos) { // from class: com.sun.tools.javac.comp.ArgumentAttr.1
            final /* synthetic */ ArgumentAttr this$0;
            final /* synthetic */ UniquePos val$pos;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(attr);
                this.val$pos = uniquePos;
                this.this$0 = argumentAttr;
                Objects.requireNonNull(attr);
            }

            @Override // com.sun.tools.javac.comp.Attr.MethodAttrInfo, com.sun.tools.javac.comp.Attr.ResultInfo
            public boolean needsArgumentAttr(JCTree jCTree) {
                return !this.this$0.new UniquePos(jCTree).equals(this.val$pos);
            }
        }));
    }

    public static /* synthetic */ ResolvedMethodType f(ArgumentAttr argumentAttr, JCTree.JCMethodInvocation jCMethodInvocation, JCTree.JCMethodInvocation jCMethodInvocation2) {
        argumentAttr.getClass();
        return new ResolvedMethodType(argumentAttr, jCMethodInvocation, argumentAttr.env, jCMethodInvocation2);
    }

    public static /* synthetic */ ResolvedConstructorType g(ArgumentAttr argumentAttr, JCTree.JCNewClass jCNewClass, JCTree.JCNewClass jCNewClass2) {
        argumentAttr.getClass();
        return new ResolvedConstructorType(argumentAttr, jCNewClass, argumentAttr.env, jCNewClass2);
    }

    public static ArgumentAttr instance(Context context) {
        ArgumentAttr argumentAttr = (ArgumentAttr) context.get(methodAttrKey);
        return argumentAttr == null ? new ArgumentAttr(context) : argumentAttr;
    }

    public Type attribArg(JCTree jCTree, Env<AttrContext> env) {
        Env<AttrContext> env2 = this.env;
        try {
            this.env = env;
            jCTree.accept(this);
            return this.result;
        } finally {
            this.env = env2;
        }
    }

    public Type checkSpeculative(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Attr.ResultInfo resultInfo) {
        return type.hasTag(TypeTag.DEFERRED) ? ((DeferredAttr.DeferredType) type).check(resultInfo) : resultInfo.check(diagnosticPosition, type);
    }

    public <T extends JCTree.JCExpression, Z extends ArgumentType<T>> void processArg(T t, Supplier<Z> supplier) {
        UniquePos uniquePos = new UniquePos(t);
        ArgumentType<?> argumentType = this.argumentTypeCache.get(uniquePos);
        if (argumentType != null) {
            setResult(t, argumentType.dup(t, this.env));
            return;
        }
        Z z = supplier.get();
        this.argumentTypeCache.put(uniquePos, z);
        setResult(t, z);
    }

    public void setResult(JCTree.JCExpression jCExpression, Type type) {
        this.result = type;
        if (this.env.info.attributionMode == DeferredAttr.AttributionMode.SPECULATIVE) {
            jCExpression.type = type;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitApply(final JCTree.JCMethodInvocation jCMethodInvocation) {
        if (jCMethodInvocation.getTypeArguments().isEmpty()) {
            processArg(jCMethodInvocation, (Function<JCTree.JCMethodInvocation, Z>) new Function() { // from class: ke0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ArgumentAttr.f(this.b, jCMethodInvocation, (JCTree.JCMethodInvocation) obj);
                }
            });
        } else {
            Attr attr = this.attr;
            setResult(jCMethodInvocation, attr.attribTree(jCMethodInvocation, this.env, attr.unknownExprInfo));
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConditional(final JCTree.JCConditional jCConditional) {
        processArg(jCConditional, (Function<JCTree.JCConditional, Z>) new Function() { // from class: oe0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArgumentAttr.d(this.b, jCConditional, (JCTree.JCConditional) obj);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLambda(final JCTree.JCLambda jCLambda) {
        if (jCLambda.paramKind == JCTree.JCLambda.ParameterKind.EXPLICIT) {
            processArg(jCLambda, new Supplier() { // from class: ne0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ArgumentAttr.a(this.b, jCLambda);
                }
            });
            return;
        }
        DeferredAttr deferredAttr = this.deferredAttr;
        Objects.requireNonNull(deferredAttr);
        setResult(jCLambda, deferredAttr.new DeferredType(jCLambda, this.env));
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewClass(final JCTree.JCNewClass jCNewClass) {
        if (TreeInfo.isDiamond(jCNewClass)) {
            processArg(jCNewClass, (Function<JCTree.JCNewClass, Z>) new Function() { // from class: me0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ArgumentAttr.g(this.b, jCNewClass, (JCTree.JCNewClass) obj);
                }
            });
        } else {
            Attr attr = this.attr;
            setResult(jCNewClass, attr.attribTree(jCNewClass, this.env, attr.unknownExprInfo));
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitParens(final JCTree.JCParens jCParens) {
        processArg(jCParens, (Function<JCTree.JCParens, Z>) new Function() { // from class: pe0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArgumentAttr.b(this.b, jCParens, (JCTree.JCParens) obj);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0044  */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReference(JCTree.JCMemberReference jCMemberReference) {
        boolean z;
        Env<AttrContext> envDup = this.env.dup(jCMemberReference);
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) this.deferredAttr.attribSpeculative(jCMemberReference.getQualifierExpression(), envDup, this.attr.memberReferenceQualifierResult(jCMemberReference), withLocalCacheContext());
        JCTree.JCMemberReference jCMemberReference2 = (JCTree.JCMemberReference) new TreeCopier(this.attr.make).copy(jCMemberReference);
        jCMemberReference2.expr = jCExpression;
        Symbol symbol = TreeInfo.symbol(jCExpression);
        AttrContext attrContext = envDup.info;
        if (symbol != null) {
            Name name = symbol.name;
            if (name == name.table.names._super) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        attrContext.selectSuper = z;
        Symbol memberReference = this.attr.rs.getMemberReference(jCMemberReference, envDup, jCMemberReference2, jCExpression.type, jCMemberReference.name);
        if (!memberReference.kind.isResolutionError()) {
            jCMemberReference.sym = memberReference;
        }
        if (memberReference.kind.isResolutionTargetError()) {
            jCMemberReference.setOverloadKind(JCTree.JCMemberReference.OverloadKind.ERROR);
        } else {
            Type type = memberReference.type;
            if ((type == null || !type.hasTag(TypeTag.FORALL)) && (memberReference.flags() & Flags.VARARGS) == 0 && !(TreeInfo.isStaticSelector(jCExpression, jCMemberReference.name.table.names) && jCExpression.type.isRaw() && !jCExpression.type.hasTag(TypeTag.ARRAY))) {
                jCMemberReference.setOverloadKind(JCTree.JCMemberReference.OverloadKind.UNOVERLOADED);
            } else {
                jCMemberReference.setOverloadKind(JCTree.JCMemberReference.OverloadKind.OVERLOADED);
            }
        }
        DeferredAttr deferredAttr = this.deferredAttr;
        Objects.requireNonNull(deferredAttr);
        setResult(jCMemberReference, deferredAttr.new DeferredType(jCMemberReference, this.env));
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitchExpression(final JCTree.JCSwitchExpression jCSwitchExpression) {
        processArg(jCSwitchExpression, (Function<JCTree.JCSwitchExpression, Z>) new Function() { // from class: le0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ArgumentAttr.c(this.b, jCSwitchExpression, (JCTree.JCSwitchExpression) obj);
            }
        });
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTree(JCTree jCTree) {
        jCTree.accept(this.attr);
        this.result = this.attr.result;
    }

    public LocalCacheContext withLocalCacheContext() {
        return new LocalCacheContext();
    }

    public class ConditionalType extends ArgumentType<JCTree.JCConditional> {
        public ConditionalType(ArgumentAttr argumentAttr, JCTree.JCExpression jCExpression, Env<AttrContext> env, JCTree.JCConditional jCConditional) {
            this(jCExpression, env, jCConditional, new HashMap());
        }

        public ArgumentType<JCTree.JCConditional> dup(JCTree.JCConditional jCConditional, Env<AttrContext> env) {
            return ArgumentAttr.this.new ConditionalType(jCConditional, env, (JCTree.JCConditional) this.speculativeTree, this.speculativeTypes);
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ArgumentType
        public Type overloadCheck(Attr.ResultInfo resultInfo, DeferredAttr.DeferredAttrContext deferredAttrContext) {
            Attr.ResultInfo resultInfoDup = resultInfo.dup(ArgumentAttr.this.attr.conditionalContext(resultInfo.checkContext));
            if (((JCTree.JCConditional) this.speculativeTree).isStandalone()) {
                JCDiagnostic.DiagnosticPosition diagnosticPosition = this.speculativeTree;
                return resultInfoDup.check(diagnosticPosition, ((JCTree.JCConditional) diagnosticPosition).type);
            }
            if (resultInfo.pt.hasTag(TypeTag.VOID)) {
                resultInfo.checkContext.report(this.tree, ArgumentAttr.this.attr.diags.fragment(CompilerProperties.Fragments.ConditionalTargetCantBeVoid));
                return ArgumentAttr.this.attr.types.createErrorType(resultInfo.pt);
            }
            ArgumentAttr.this.checkSpeculative(((JCTree.JCConditional) this.speculativeTree).truepart, resultInfoDup);
            ArgumentAttr.this.checkSpeculative(((JCTree.JCConditional) this.speculativeTree).falsepart, resultInfoDup);
            return resultInfoDup.pt;
        }

        public ConditionalType(JCTree.JCExpression jCExpression, Env<AttrContext> env, JCTree.JCConditional jCConditional, Map<Attr.ResultInfo, Type> map) {
            super(jCExpression, env, jCConditional, map);
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ArgumentType
        public /* bridge */ /* synthetic */ ArgumentType dup(JCTree.JCExpression jCExpression, Env env) {
            return dup((JCTree.JCConditional) jCExpression, (Env<AttrContext>) env);
        }
    }

    public class ParensType extends ArgumentType<JCTree.JCParens> {
        public ParensType(ArgumentAttr argumentAttr, JCTree.JCExpression jCExpression, Env<AttrContext> env, JCTree.JCParens jCParens) {
            this(jCExpression, env, jCParens, new HashMap());
        }

        public ArgumentType<JCTree.JCParens> dup(JCTree.JCParens jCParens, Env<AttrContext> env) {
            return ArgumentAttr.this.new ParensType(jCParens, env, (JCTree.JCParens) this.speculativeTree, this.speculativeTypes);
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ArgumentType
        public Type overloadCheck(Attr.ResultInfo resultInfo, DeferredAttr.DeferredAttrContext deferredAttrContext) {
            return ArgumentAttr.this.checkSpeculative(((JCTree.JCParens) this.speculativeTree).expr, resultInfo);
        }

        public ParensType(JCTree.JCExpression jCExpression, Env<AttrContext> env, JCTree.JCParens jCParens, Map<Attr.ResultInfo, Type> map) {
            super(jCExpression, env, jCParens, map);
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ArgumentType
        public /* bridge */ /* synthetic */ ArgumentType dup(JCTree.JCExpression jCExpression, Env env) {
            return dup((JCTree.JCParens) jCExpression, (Env<AttrContext>) env);
        }
    }

    public class ResolvedConstructorType extends ResolvedMemberType<JCTree.JCNewClass> {
        public ResolvedConstructorType(ArgumentAttr argumentAttr, JCTree.JCExpression jCExpression, Env<AttrContext> env, JCTree.JCNewClass jCNewClass) {
            this(jCExpression, env, jCNewClass, new HashMap());
        }

        public ArgumentType<JCTree.JCNewClass> dup(JCTree.JCNewClass jCNewClass, Env<AttrContext> env) {
            return ArgumentAttr.this.new ResolvedConstructorType(jCNewClass, env, (JCTree.JCNewClass) this.speculativeTree, this.speculativeTypes);
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ResolvedMemberType
        public Type methodType() {
            T t = this.speculativeTree;
            return ((JCTree.JCNewClass) t).constructorType != null ? ((JCTree.JCNewClass) t).constructorType.baseType() : ArgumentAttr.this.syms.errType;
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ResolvedMemberType
        public Attr.ResultInfo resultInfo(Attr.ResultInfo resultInfo) {
            Attr attr = ArgumentAttr.this.attr;
            T t = this.speculativeTree;
            return resultInfo.dup(attr.diamondContext((JCTree.JCNewClass) t, ((JCTree.JCNewClass) t).clazz.type.tsym, resultInfo.checkContext));
        }

        public ResolvedConstructorType(JCTree.JCExpression jCExpression, Env<AttrContext> env, JCTree.JCNewClass jCNewClass, Map<Attr.ResultInfo, Type> map) {
            super(jCExpression, env, jCNewClass, map);
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ArgumentType
        public /* bridge */ /* synthetic */ ArgumentType dup(JCTree.JCExpression jCExpression, Env env) {
            return dup((JCTree.JCNewClass) jCExpression, (Env<AttrContext>) env);
        }
    }

    public class ResolvedMethodType extends ResolvedMemberType<JCTree.JCMethodInvocation> {
        public ResolvedMethodType(ArgumentAttr argumentAttr, JCTree.JCExpression jCExpression, Env<AttrContext> env, JCTree.JCMethodInvocation jCMethodInvocation) {
            this(jCExpression, env, jCMethodInvocation, new HashMap());
        }

        public ArgumentType<JCTree.JCMethodInvocation> dup(JCTree.JCMethodInvocation jCMethodInvocation, Env<AttrContext> env) {
            return ArgumentAttr.this.new ResolvedMethodType(jCMethodInvocation, env, (JCTree.JCMethodInvocation) this.speculativeTree, this.speculativeTypes);
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ResolvedMemberType
        public Type methodType() {
            return ((JCTree.JCMethodInvocation) this.speculativeTree).meth.type;
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ResolvedMemberType
        public Attr.ResultInfo resultInfo(Attr.ResultInfo resultInfo) {
            return resultInfo;
        }

        public ResolvedMethodType(JCTree.JCExpression jCExpression, Env<AttrContext> env, JCTree.JCMethodInvocation jCMethodInvocation, Map<Attr.ResultInfo, Type> map) {
            super(jCExpression, env, jCMethodInvocation, map);
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ArgumentType
        public /* bridge */ /* synthetic */ ArgumentType dup(JCTree.JCExpression jCExpression, Env env) {
            return dup((JCTree.JCMethodInvocation) jCExpression, (Env<AttrContext>) env);
        }
    }

    public class SwitchExpressionType extends ArgumentType<JCTree.JCSwitchExpression> {
        Optional<List<JCTree.JCYield>> yieldExpressions;

        public SwitchExpressionType(ArgumentAttr argumentAttr, JCTree.JCExpression jCExpression, Env<AttrContext> env, JCTree.JCSwitchExpression jCSwitchExpression) {
            this(jCExpression, env, jCSwitchExpression, new HashMap());
        }

        public static /* synthetic */ List a(SwitchExpressionType switchExpressionType) {
            switchExpressionType.getClass();
            final ListBuffer listBuffer = new ListBuffer();
            new DeferredAttr.SwitchExpressionScanner(switchExpressionType) { // from class: com.sun.tools.javac.comp.ArgumentAttr.SwitchExpressionType.1
                final /* synthetic */ SwitchExpressionType this$1;

                {
                    this.this$1 = switchExpressionType;
                }

                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitYield(JCTree.JCYield jCYield) {
                    if (jCYield.target == this.this$1.speculativeTree) {
                        listBuffer.add(jCYield);
                    }
                    super.visitYield(jCYield);
                }
            }.scan(((JCTree.JCSwitchExpression) switchExpressionType.speculativeTree).cases);
            List list = listBuffer.toList();
            switchExpressionType.yieldExpressions = Optional.of(list);
            return list;
        }

        public ArgumentType<JCTree.JCSwitchExpression> dup(JCTree.JCSwitchExpression jCSwitchExpression, Env<AttrContext> env) {
            return ArgumentAttr.this.new SwitchExpressionType(jCSwitchExpression, env, (JCTree.JCSwitchExpression) this.speculativeTree, this.speculativeTypes);
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ArgumentType
        public Type overloadCheck(Attr.ResultInfo resultInfo, DeferredAttr.DeferredAttrContext deferredAttrContext) {
            Attr.ResultInfo resultInfoDup = resultInfo.dup(ArgumentAttr.this.attr.conditionalContext(resultInfo.checkContext));
            if (resultInfo.pt.hasTag(TypeTag.VOID)) {
                resultInfo.checkContext.report(this.tree, ArgumentAttr.this.attr.diags.fragment(CompilerProperties.Fragments.SwitchExpressionTargetCantBeVoid));
                return ArgumentAttr.this.attr.types.createErrorType(resultInfo.pt);
            }
            for (JCTree.JCYield jCYield : yieldExpressions()) {
                ArgumentAttr argumentAttr = ArgumentAttr.this;
                JCTree.JCExpression jCExpression = jCYield.value;
                argumentAttr.checkSpeculative(jCExpression, jCExpression.type, resultInfo);
            }
            return resultInfoDup.pt;
        }

        public List<JCTree.JCYield> yieldExpressions() {
            return this.yieldExpressions.orElseGet(new Supplier() { // from class: com.sun.tools.javac.comp.h
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ArgumentAttr.SwitchExpressionType.a(this.b);
                }
            });
        }

        public SwitchExpressionType(JCTree.JCExpression jCExpression, Env<AttrContext> env, JCTree.JCSwitchExpression jCSwitchExpression, Map<Attr.ResultInfo, Type> map) {
            super(jCExpression, env, jCSwitchExpression, map);
            this.yieldExpressions = Optional.empty();
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ArgumentType
        public /* bridge */ /* synthetic */ ArgumentType dup(JCTree.JCExpression jCExpression, Env env) {
            return dup((JCTree.JCSwitchExpression) jCExpression, (Env<AttrContext>) env);
        }
    }

    public class ExplicitLambdaType extends ArgumentType<JCTree.JCLambda> {
        Optional<List<Type>> argtypes;
        Optional<List<JCTree.JCReturn>> returnExpressions;

        public ExplicitLambdaType(JCTree.JCLambda jCLambda, Env<AttrContext> env, JCTree.JCLambda jCLambda2, Map<Attr.ResultInfo, Type> map) {
            super(jCLambda, env, jCLambda2, map);
            this.argtypes = Optional.empty();
            this.returnExpressions = Optional.empty();
        }

        public static /* synthetic */ List a(ExplicitLambdaType explicitLambdaType) {
            List<Type> listTypes = TreeInfo.types(((JCTree.JCLambda) explicitLambdaType.speculativeTree).params);
            explicitLambdaType.argtypes = Optional.of(listTypes);
            return listTypes;
        }

        public static /* synthetic */ List b(ExplicitLambdaType explicitLambdaType) {
            explicitLambdaType.getClass();
            final ListBuffer listBuffer = new ListBuffer();
            new DeferredAttr.LambdaReturnScanner(explicitLambdaType) { // from class: com.sun.tools.javac.comp.ArgumentAttr.ExplicitLambdaType.1
                final /* synthetic */ ExplicitLambdaType this$1;

                {
                    this.this$1 = explicitLambdaType;
                }

                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitReturn(JCTree.JCReturn jCReturn) {
                    listBuffer.add(jCReturn);
                }
            }.scan(((JCTree.JCLambda) explicitLambdaType.speculativeTree).body);
            List list = listBuffer.toList();
            explicitLambdaType.returnExpressions = Optional.of(list);
            return list;
        }

        private void checkLambdaCompatible(Type type, Attr.ResultInfo resultInfo) {
            Check.CheckContext checkContext = resultInfo.checkContext;
            Attr.ResultInfo resultInfoLambdaBodyResult = ArgumentAttr.this.attr.lambdaBodyResult((JCTree.JCLambda) this.speculativeTree, type, resultInfo);
            int i = AnonymousClass2.$SwitchMap$com$sun$source$tree$LambdaExpressionTree$BodyKind[((JCTree.JCLambda) this.speculativeTree).getBodyKind().ordinal()];
            if (i == 1) {
                ArgumentAttr argumentAttr = ArgumentAttr.this;
                T t = this.speculativeTree;
                argumentAttr.checkSpeculative(((JCTree.JCLambda) t).body, ((JCTree.JCLambda) t).body.type, resultInfoLambdaBodyResult);
            } else if (i == 2) {
                Iterator<JCTree.JCReturn> it = returnExpressions().iterator();
                while (it.hasNext()) {
                    checkReturnInStatementLambda(it.next(), resultInfoLambdaBodyResult);
                }
            }
            ArgumentAttr.this.attr.checkLambdaCompatible((JCTree.JCLambda) this.speculativeTree, type, checkContext);
        }

        public List<Type> argtypes() {
            return this.argtypes.orElseGet(new Supplier() { // from class: com.sun.tools.javac.comp.f
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ArgumentAttr.ExplicitLambdaType.a(this.b);
                }
            });
        }

        public void checkReturnInStatementLambda(JCTree.JCReturn jCReturn, Attr.ResultInfo resultInfo) {
            Type type = resultInfo.pt;
            TypeTag typeTag = TypeTag.VOID;
            if (type.hasTag(typeTag) && jCReturn.expr != null) {
                resultInfo.checkContext.report(((JCTree.JCLambda) this.speculativeTree).pos(), ArgumentAttr.this.diags.fragment("unexpected.ret.val", new Object[0]));
                return;
            }
            if (resultInfo.pt.hasTag(typeTag)) {
                return;
            }
            if (jCReturn.expr == null) {
                resultInfo.checkContext.report(((JCTree.JCLambda) this.speculativeTree).pos(), ArgumentAttr.this.diags.fragment("missing.ret.val", new Object[0]));
            }
            ArgumentAttr argumentAttr = ArgumentAttr.this;
            JCTree.JCExpression jCExpression = jCReturn.expr;
            argumentAttr.checkSpeculative(jCExpression, jCExpression.type, resultInfo);
        }

        public ArgumentType<JCTree.JCLambda> dup(JCTree.JCLambda jCLambda, Env<AttrContext> env) {
            return ArgumentAttr.this.new ExplicitLambdaType(jCLambda, env, (JCTree.JCLambda) this.speculativeTree, this.speculativeTypes);
        }

        public Type getReturnType(JCTree.JCReturn jCReturn) {
            JCTree.JCExpression jCExpression = jCReturn.expr;
            return jCExpression == null ? ArgumentAttr.this.syms.voidType : jCExpression.type;
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ArgumentType
        public Type overloadCheck(Attr.ResultInfo resultInfo, DeferredAttr.DeferredAttrContext deferredAttrContext) {
            try {
                Attr.TargetInfo targetInfo = ArgumentAttr.this.attr.getTargetInfo((JCTree.JCPolyExpression) this.speculativeTree, resultInfo, argtypes());
                Type type = targetInfo.descriptor;
                Type type2 = targetInfo.target;
                checkLambdaCompatible(type, resultInfo);
                return type2;
            } catch (Types.FunctionDescriptorLookupError e) {
                resultInfo.checkContext.report(null, e.getDiagnostic());
                return null;
            }
        }

        public List<JCTree.JCReturn> returnExpressions() {
            return this.returnExpressions.orElseGet(new Supplier() { // from class: com.sun.tools.javac.comp.g
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ArgumentAttr.ExplicitLambdaType.b(this.b);
                }
            });
        }

        @Override // com.sun.tools.javac.comp.ArgumentAttr.ArgumentType
        public /* bridge */ /* synthetic */ ArgumentType dup(JCTree.JCExpression jCExpression, Env env) {
            return dup((JCTree.JCLambda) jCExpression, (Env<AttrContext>) env);
        }

        public ExplicitLambdaType(ArgumentAttr argumentAttr, JCTree.JCLambda jCLambda, Env<AttrContext> env, JCTree.JCLambda jCLambda2) {
            this(jCLambda, env, jCLambda2, new HashMap());
        }
    }

    public Type checkSpeculative(JCTree jCTree, Attr.ResultInfo resultInfo) {
        return checkSpeculative(jCTree, jCTree.type, resultInfo);
    }

    public <T extends JCTree.JCExpression, Z extends ArgumentType<T>> void processArg(final T t, final Function<T, Z> function) {
        final UniquePos uniquePos = new UniquePos(t);
        processArg(t, new Supplier() { // from class: com.sun.tools.javac.comp.e
            @Override // java.util.function.Supplier
            public final Object get() {
                return ArgumentAttr.e(this.b, t, uniquePos, function);
            }
        });
    }
}

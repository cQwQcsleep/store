package com.sun.tools.javac.comp;

import com.sun.source.tree.LambdaExpressionTree;
import com.sun.tools.javac.api.Formattable;
import com.sun.tools.javac.code.ClassFinder;
import com.sun.tools.javac.code.Directive;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.ModuleFinder;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.DeferredAttr.DeferredAttrContext;
import com.sun.tools.javac.comp.DeferredAttr.RecoveryDeferredTypeMap;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.comp.Resolve;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.CompilerInternalException;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.DiagnosticSource;
import com.sun.tools.javac.util.FatalError;
import com.sun.tools.javac.util.Iterators;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.Warner;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.lang.model.element.ElementVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Resolve {
    protected static final Context.Key<Resolve> resolveKey = new Context.Key<>();
    private final boolean allowLocalVariableTypeInference;
    public final boolean allowModules;
    private final boolean allowPrivateMembersInPermitsClause;
    public final boolean allowRecords;
    private final boolean allowYieldStatement;
    Attr attr;
    AttrRecover attrRecover;
    Check chk;
    private final boolean compactMethodDiags;
    DeferredAttr deferredAttr;
    JCDiagnostic.Factory diags;
    final boolean dumpMethodReferenceSearchResults;
    final boolean dumpStacktraceOnError;
    ClassFinder finder;
    Infer infer;
    Log log;
    private final SymbolNotFoundError methodNotFound;
    ModuleFinder moduleFinder;
    Names names;
    Scope.WriteableScope polymorphicSignatureScope;
    Preview preview;
    private final ReferenceLookupResult referenceNotFound;
    Symtab syms;
    private final SymbolNotFoundError typeNotFound;
    Types types;
    private final SymbolNotFoundError varNotFound;
    final EnumSet<VerboseResolutionMode> verboseResolutionMode;
    Types.SimpleVisitor<Void, Env<AttrContext>> accessibilityChecker = new Types.SimpleVisitor<Void, Env<AttrContext>>() { // from class: com.sun.tools.javac.comp.Resolve.1
        public void visit(List<Type> list, Env<AttrContext> env) {
            Iterator<Type> it = list.iterator();
            while (it.hasNext()) {
                visit(it.next(), env);
            }
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitClassType(Type.ClassType classType, Env<AttrContext> env) {
            visit(classType.getTypeArguments(), env);
            if (!Resolve.this.isAccessible(env, (Type) classType, true)) {
                Resolve resolve = Resolve.this;
                resolve.accessBase(resolve.new AccessError(env, null, classType.tsym), env.tree.pos(), env.enclClass.sym, classType, classType.tsym.name, true);
            }
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitMethodType(Type.MethodType methodType, Env<AttrContext> env) {
            visit(methodType.mo71getParameterTypes(), env);
            visit(methodType.mo73getReturnType(), env);
            visit(methodType.mo74getThrownTypes(), env);
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitArrayType(Type.ArrayType arrayType, Env<AttrContext> env) {
            visit(arrayType.elemtype, env);
            return null;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Void visitType(Type type, Env<AttrContext> env) {
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitWildcardType(Type.WildcardType wildcardType, Env<AttrContext> env) {
            visit(wildcardType.type, env);
            return null;
        }
    };
    MethodCheck nilMethodCheck = new MethodCheck() { // from class: com.sun.tools.javac.comp.Resolve.2
        @Override // com.sun.tools.javac.comp.Resolve.MethodCheck
        public void argumentsAcceptable(Env<AttrContext> env, DeferredAttr.DeferredAttrContext deferredAttrContext, List<Type> list, List<Type> list2, Warner warner) {
        }

        @Override // com.sun.tools.javac.comp.Resolve.MethodCheck
        public MethodCheck mostSpecificCheck(List<Type> list) {
            return this;
        }
    };
    MethodCheck arityMethodCheck = new AbstractMethodCheck() { // from class: com.sun.tools.javac.comp.Resolve.3
        @Override // com.sun.tools.javac.comp.Resolve.AbstractMethodCheck
        public void checkArg(JCDiagnostic.DiagnosticPosition diagnosticPosition, boolean z, Type type, Type type2, DeferredAttr.DeferredAttrContext deferredAttrContext, Warner warner) {
        }

        public String toString() {
            return "arityMethodCheck";
        }
    };
    MethodCheck resolveMethodCheck = new AnonymousClass4();
    Warner noteWarner = new Warner();
    private final RecoveryLoadClass noRecovery = new RecoveryLoadClass() { // from class: mgc
        @Override // com.sun.tools.javac.comp.Resolve.RecoveryLoadClass
        public final Symbol loadClass(Env env, Name name) {
            return Resolve.f(env, name);
        }
    };
    private final RecoveryLoadClass doRecoveryLoadClass = new AnonymousClass7();
    private final RecoveryLoadClass namedImportScopeRecovery = new RecoveryLoadClass() { // from class: ngc
        @Override // com.sun.tools.javac.comp.Resolve.RecoveryLoadClass
        public final Symbol loadClass(Env env, Name name) {
            return Resolve.d(this.a, env, name);
        }
    };
    private final RecoveryLoadClass starImportScopeRecovery = onDemandImportScopeRecovery(false);
    private final RecoveryLoadClass moduleImportScopeRecovery = onDemandImportScopeRecovery(true);
    LogResolveHelper basicLogResolveHelper = new LogResolveHelper() { // from class: com.sun.tools.javac.comp.Resolve.8
        @Override // com.sun.tools.javac.comp.Resolve.LogResolveHelper
        public List<Type> getArgumentTypes(ResolveError resolveError, Symbol symbol, Name name, List<Type> list) {
            return list;
        }

        @Override // com.sun.tools.javac.comp.Resolve.LogResolveHelper
        public boolean resolveDiagnosticNeeded(Type type, List<Type> list, List<Type> list2) {
            return !type.isErroneous();
        }
    };
    LogResolveHelper silentLogResolveHelper = new LogResolveHelper() { // from class: com.sun.tools.javac.comp.Resolve.9
        @Override // com.sun.tools.javac.comp.Resolve.LogResolveHelper
        public List<Type> getArgumentTypes(ResolveError resolveError, Symbol symbol, Name name, List<Type> list) {
            return list;
        }

        @Override // com.sun.tools.javac.comp.Resolve.LogResolveHelper
        public boolean resolveDiagnosticNeeded(Type type, List<Type> list, List<Type> list2) {
            return false;
        }
    };
    LogResolveHelper methodLogResolveHelper = new LogResolveHelper() { // from class: com.sun.tools.javac.comp.Resolve.10
        @Override // com.sun.tools.javac.comp.Resolve.LogResolveHelper
        public List<Type> getArgumentTypes(ResolveError resolveError, Symbol symbol, Name name, List<Type> list) {
            Resolve resolve = Resolve.this;
            return list.map(resolve.new ResolveDeferredRecoveryMap(DeferredAttr.AttrMode.SPECULATIVE, symbol, resolve.currentResolutionContext.step));
        }

        @Override // com.sun.tools.javac.comp.Resolve.LogResolveHelper
        public boolean resolveDiagnosticNeeded(Type type, List<Type> list, List<Type> list2) {
            if (type.isErroneous() || Type.isErroneous(list)) {
                return false;
            }
            return list2 == null || !Type.isErroneous(list2);
        }
    };
    ReferenceChooser basicReferenceChooser = new ReferenceChooser() { // from class: com.sun.tools.javac.comp.Resolve.17
        @Override // com.sun.tools.javac.comp.Resolve.ReferenceChooser
        public ReferenceLookupResult boundResult(ReferenceLookupResult referenceLookupResult) {
            return (!referenceLookupResult.isSuccess() || referenceLookupResult.hasKind(ReferenceLookupResult.StaticKind.NON_STATIC)) ? referenceLookupResult : ReferenceLookupResult.error(Resolve.this.new BadMethodReferenceError(referenceLookupResult.sym, false));
        }

        @Override // com.sun.tools.javac.comp.Resolve.ReferenceChooser
        public ReferenceLookupResult unboundResult(ReferenceLookupResult referenceLookupResult, ReferenceLookupResult referenceLookupResult2) {
            if (!referenceLookupResult.isSuccess() || !referenceLookupResult.sym.isStatic() || (referenceLookupResult2.isSuccess() && !referenceLookupResult2.hasKind(ReferenceLookupResult.StaticKind.STATIC))) {
                if (!referenceLookupResult2.isSuccess() || referenceLookupResult2.sym.isStatic() || (referenceLookupResult.isSuccess() && !referenceLookupResult.hasKind(ReferenceLookupResult.StaticKind.NON_STATIC))) {
                    if (referenceLookupResult.isSuccess() && referenceLookupResult2.isSuccess()) {
                        return ReferenceLookupResult.error(Resolve.this.ambiguityError(referenceLookupResult.sym, referenceLookupResult2.sym));
                    }
                    if (referenceLookupResult.isSuccess() || referenceLookupResult2.isSuccess()) {
                        Resolve resolve = Resolve.this;
                        if (!referenceLookupResult.isSuccess()) {
                            referenceLookupResult = referenceLookupResult2;
                        }
                        return ReferenceLookupResult.error(resolve.new BadMethodReferenceError(referenceLookupResult.sym, true));
                    }
                    if (!referenceLookupResult.canIgnore() || referenceLookupResult2.canIgnore()) {
                    }
                }
                return referenceLookupResult2;
            }
            return referenceLookupResult;
        }
    };
    ReferenceChooser structuralReferenceChooser = new ReferenceChooser() { // from class: com.sun.tools.javac.comp.Resolve.18
        @Override // com.sun.tools.javac.comp.Resolve.ReferenceChooser
        public ReferenceLookupResult boundResult(ReferenceLookupResult referenceLookupResult) {
            return (referenceLookupResult.isSuccess() && referenceLookupResult.hasKind(ReferenceLookupResult.StaticKind.STATIC)) ? ReferenceLookupResult.error(Resolve.this.new BadMethodReferenceError(referenceLookupResult.sym, false)) : referenceLookupResult;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ReferenceChooser
        public ReferenceLookupResult unboundResult(ReferenceLookupResult referenceLookupResult, ReferenceLookupResult referenceLookupResult2) {
            if (!referenceLookupResult.isSuccess() || referenceLookupResult.hasKind(ReferenceLookupResult.StaticKind.NON_STATIC)) {
                if (!referenceLookupResult2.isSuccess() || referenceLookupResult2.hasKind(ReferenceLookupResult.StaticKind.STATIC)) {
                    if (referenceLookupResult.isSuccess() || referenceLookupResult2.isSuccess()) {
                        Resolve resolve = Resolve.this;
                        if (!referenceLookupResult.isSuccess()) {
                            referenceLookupResult = referenceLookupResult2;
                        }
                        return ReferenceLookupResult.error(resolve.new BadMethodReferenceError(referenceLookupResult.sym, true));
                    }
                    if (!referenceLookupResult.canIgnore() || referenceLookupResult2.canIgnore()) {
                    }
                }
                return referenceLookupResult2;
            }
            return referenceLookupResult;
        }
    };
    private final Formattable.LocalizedString noArgs = new Formattable.LocalizedString("compiler.misc.no.args");
    final List<MethodResolutionPhase> methodResolutionSteps = List.of(MethodResolutionPhase.BASIC, MethodResolutionPhase.BOX, MethodResolutionPhase.VARARITY);
    MethodResolutionContext currentResolutionContext = null;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Resolve$19, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass19 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Kinds$Kind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Kinds$KindName;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[Kinds.KindName.values().length];
            $SwitchMap$com$sun$tools$javac$code$Kinds$KindName = iArr;
            try {
                iArr[Kinds.KindName.METHOD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$KindName[Kinds.KindName.CONSTRUCTOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr2;
            try {
                iArr2[JCTree.Tag.LAMBDA.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.REFERENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CONDEXPR.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.IDENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SELECT.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[Kinds.Kind.values().length];
            $SwitchMap$com$sun$tools$javac$code$Kinds$Kind = iArr3;
            try {
                iArr3[Kinds.Kind.ABSENT_MTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.HIDDEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.WRONG_MTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.MTH.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.AMBIGUOUS.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.WRONG_MTHS.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Resolve$4, reason: invalid class name */
    public class AnonymousClass4 extends AbstractMethodCheck {
        public AnonymousClass4() {
            super();
        }

        public static /* synthetic */ void a(AnonymousClass4 anonymousClass4, Env env, Type type, InferenceContext inferenceContext) {
            anonymousClass4.getClass();
            anonymousClass4.varargsAccessible(env, inferenceContext.asInstType(type), inferenceContext);
        }

        private Attr.ResultInfo methodCheckResult(boolean z, Type type, DeferredAttr.DeferredAttrContext deferredAttrContext, Warner warner) {
            return Resolve.this.new MethodResultInfo(type, new MethodCheckContext(this, !deferredAttrContext.phase.isBoxingRequired(), deferredAttrContext, warner, z) { // from class: com.sun.tools.javac.comp.Resolve.4.1
                MethodCheckDiag methodDiag;
                final /* synthetic */ AnonymousClass4 this$1;
                final /* synthetic */ boolean val$varargsCheck;

                {
                    this.val$varargsCheck = z;
                    this.this$1 = this;
                    Resolve resolve = Resolve.this;
                    this.methodDiag = z ? MethodCheckDiag.VARARG_MISMATCH : MethodCheckDiag.ARG_MISMATCH;
                }

                @Override // com.sun.tools.javac.comp.Resolve.MethodCheckContext, com.sun.tools.javac.comp.Check.CheckContext
                public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
                    this.this$1.reportMC(diagnosticPosition, this.methodDiag, this.deferredAttrContext.inferenceContext, jCDiagnostic);
                }
            });
        }

        private void varargsAccessible(final Env<AttrContext> env, final Type type, InferenceContext inferenceContext) {
            if (inferenceContext.free(type)) {
                inferenceContext.addFreeTypeListener(List.of(type), new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.r2
                    @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                    public final void typesInferred(InferenceContext inferenceContext2) {
                        Resolve.AnonymousClass4.a(this.a, env, type, inferenceContext2);
                    }
                });
                return;
            }
            Resolve resolve = Resolve.this;
            if (resolve.isAccessible(env, resolve.types.erasure(type))) {
                return;
            }
            Symbol.ClassSymbol classSymbol = env.enclClass.sym;
            reportMC(env.tree, MethodCheckDiag.INACCESSIBLE_VARARGS, inferenceContext, type, Kinds.kindName(classSymbol), classSymbol);
        }

        @Override // com.sun.tools.javac.comp.Resolve.AbstractMethodCheck, com.sun.tools.javac.comp.Resolve.MethodCheck
        public void argumentsAcceptable(Env<AttrContext> env, DeferredAttr.DeferredAttrContext deferredAttrContext, List<Type> list, List<Type> list2, Warner warner) {
            super.argumentsAcceptable(env, deferredAttrContext, list, list2, warner);
            if (deferredAttrContext.phase.isVarargsRequired() && deferredAttrContext.mode == DeferredAttr.AttrMode.CHECK) {
                varargsAccessible(env, Resolve.this.types.elemtype(list2.last()), deferredAttrContext.inferenceContext);
            }
        }

        @Override // com.sun.tools.javac.comp.Resolve.AbstractMethodCheck
        public void checkArg(JCDiagnostic.DiagnosticPosition diagnosticPosition, boolean z, Type type, Type type2, DeferredAttr.DeferredAttrContext deferredAttrContext, Warner warner) {
            methodCheckResult(z, type2, deferredAttrContext, warner).check(diagnosticPosition, type);
        }

        @Override // com.sun.tools.javac.comp.Resolve.AbstractMethodCheck, com.sun.tools.javac.comp.Resolve.MethodCheck
        public MethodCheck mostSpecificCheck(List<Type> list) {
            return Resolve.this.new MostSpecificCheck(list);
        }

        public String toString() {
            return "resolveMethodCheck";
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Resolve$7, reason: invalid class name */
    public class AnonymousClass7 implements RecoveryLoadClass {
        public AnonymousClass7() {
        }

        public static /* synthetic */ Iterable a(final AnonymousClass7 anonymousClass7, final List list, Name name) {
            anonymousClass7.getClass();
            return new Iterable() { // from class: com.sun.tools.javac.comp.s2
                @Override // java.lang.Iterable
                public final Iterator iterator() {
                    return Resolve.AnonymousClass7.e(this.b, list);
                }
            };
        }

        public static /* synthetic */ Symbol.ClassSymbol c(AnonymousClass7 anonymousClass7, List list, Symbol.ModuleSymbol moduleSymbol, Name name) {
            anonymousClass7.getClass();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                try {
                    return Resolve.this.finder.loadClass(moduleSymbol, (Name) it.next());
                } catch (Symbol.CompletionFailure unused) {
                }
            }
            return null;
        }

        public static /* synthetic */ boolean d(Symbol.ClassSymbol classSymbol) {
            return classSymbol.kind == Kinds.Kind.TYP;
        }

        public static /* synthetic */ Iterator e(final AnonymousClass7 anonymousClass7, List list) {
            anonymousClass7.getClass();
            return Iterators.createCompoundIterator(list, new Function() { // from class: com.sun.tools.javac.comp.t2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Resolve.this.syms.getClassesForName((Name) obj).iterator();
                }
            });
        }

        @Override // com.sun.tools.javac.comp.Resolve.RecoveryLoadClass
        public Symbol loadClass(Env<AttrContext> env, Name name) {
            final List<Name> listClassCandidates = Convert.classCandidates(name);
            return Resolve.this.lookupInvisibleSymbol(env, name, new Function() { // from class: com.sun.tools.javac.comp.u2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Resolve.AnonymousClass7.a(this.b, listClassCandidates, (Name) obj);
                }
            }, new BiFunction() { // from class: com.sun.tools.javac.comp.v2
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return Resolve.AnonymousClass7.c(this.b, listClassCandidates, (Symbol.ModuleSymbol) obj, (Name) obj2);
                }
            }, new Predicate() { // from class: com.sun.tools.javac.comp.w2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Resolve.AnonymousClass7.d((Symbol.ClassSymbol) obj);
                }
            }, Resolve.this.typeNotFound);
        }
    }

    public abstract class AbstractMethodCheck implements MethodCheck {
        private SharedInapplicableMethodException methodCheckFailure;

        public class SharedInapplicableMethodException extends InapplicableMethodException {
            private static final long serialVersionUID = 0;

            public SharedInapplicableMethodException() {
                super(null, Resolve.this.dumpStacktraceOnError);
            }

            public SharedInapplicableMethodException setMessage(JCDiagnostic jCDiagnostic) {
                this.diagnostic = jCDiagnostic;
                return this;
            }
        }

        public AbstractMethodCheck() {
        }

        private SharedInapplicableMethodException getMethodCheckFailure() {
            SharedInapplicableMethodException sharedInapplicableMethodException = this.methodCheckFailure;
            if (sharedInapplicableMethodException != null) {
                return sharedInapplicableMethodException;
            }
            SharedInapplicableMethodException sharedInapplicableMethodException2 = new SharedInapplicableMethodException();
            this.methodCheckFailure = sharedInapplicableMethodException2;
            return sharedInapplicableMethodException2;
        }

        private JCTree treeForDiagnostics(Env<AttrContext> env) {
            AttrContext attrContext = env.info;
            return attrContext.preferredTreeForDiagnostics != null ? attrContext.preferredTreeForDiagnostics : env.tree;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.comp.Resolve.MethodCheck
        public void argumentsAcceptable(Env<AttrContext> env, DeferredAttr.DeferredAttrContext deferredAttrContext, List<Type> list, List<Type> list2, Warner warner) {
            A a;
            AbstractMethodCheck abstractMethodCheck = this;
            DeferredAttr.DeferredAttrContext deferredAttrContext2 = deferredAttrContext;
            boolean zIsVarargsRequired = deferredAttrContext2.phase.isVarargsRequired();
            JCTree jCTreeTreeForDiagnostics = treeForDiagnostics(env);
            List<JCTree.JCExpression> listArgs = TreeInfo.args(jCTreeTreeForDiagnostics);
            InferenceContext inferenceContext = deferredAttrContext2.inferenceContext;
            Type typeLast = zIsVarargsRequired ? list2.last() : null;
            if (typeLast == null && list.size() != list2.size()) {
                abstractMethodCheck.reportMC(jCTreeTreeForDiagnostics, MethodCheckDiag.ARITY_MISMATCH, inferenceContext, new Object[0]);
            }
            List list3 = list;
            List list4 = list2;
            List<JCTree.JCExpression> list5 = listArgs;
            while (list3.nonEmpty() && (a = list4.head) != typeLast) {
                abstractMethodCheck.checkArg(list5 != null ? list5.head : null, false, (Type) list3.head, (Type) a, deferredAttrContext2, warner);
                list3 = list3.tail;
                list4 = list4.tail;
                if (list5 != null) {
                    list5 = list5.tail;
                }
                deferredAttrContext2 = deferredAttrContext;
            }
            if (list4.head != typeLast) {
                abstractMethodCheck.reportMC(jCTreeTreeForDiagnostics, MethodCheckDiag.ARITY_MISMATCH, inferenceContext, new Object[0]);
            }
            if (zIsVarargsRequired) {
                Type typeElemtype = Resolve.this.types.elemtype(typeLast);
                while (list3.nonEmpty()) {
                    abstractMethodCheck.checkArg(list5 != null ? list5.head : null, true, (Type) list3.head, typeElemtype, deferredAttrContext, warner);
                    list3 = list3.tail;
                    if (list5 != null) {
                        list5 = list5.tail;
                    }
                    abstractMethodCheck = this;
                }
            }
        }

        public abstract void checkArg(JCDiagnostic.DiagnosticPosition diagnosticPosition, boolean z, Type type, Type type2, DeferredAttr.DeferredAttrContext deferredAttrContext, Warner warner);

        @Override // com.sun.tools.javac.comp.Resolve.MethodCheck
        public MethodCheck mostSpecificCheck(List<Type> list) {
            return Resolve.this.nilMethodCheck;
        }

        public void reportMC(JCDiagnostic.DiagnosticPosition diagnosticPosition, MethodCheckDiag methodCheckDiag, InferenceContext inferenceContext, Object... objArr) {
            Object[] objArr2;
            boolean z = inferenceContext != Resolve.this.infer.emptyContext;
            if (!z || methodCheckDiag.inferKey.equals(methodCheckDiag.basicKey)) {
                objArr2 = objArr;
            } else {
                Object[] objArr3 = new Object[objArr.length + 1];
                System.arraycopy(objArr, 0, objArr3, 1, objArr.length);
                objArr3[0] = inferenceContext.inferenceVars();
                objArr2 = objArr3;
            }
            String str = z ? methodCheckDiag.inferKey : methodCheckDiag.basicKey;
            if (z) {
                Resolve resolve = Resolve.this;
                throw resolve.infer.error(resolve.diags.create(JCDiagnostic.DiagnosticType.FRAGMENT, resolve.log.currentSource(), diagnosticPosition, str, objArr2));
            }
            SharedInapplicableMethodException methodCheckFailure = getMethodCheckFailure();
            Resolve resolve2 = Resolve.this;
            throw methodCheckFailure.setMessage(resolve2.diags.create(JCDiagnostic.DiagnosticType.FRAGMENT, resolve2.log.currentSource(), diagnosticPosition, str, objArr2));
        }
    }

    public class AccessError extends InvalidSymbolError {
        private Env<AttrContext> env;
        private Type site;

        public AccessError(Env<AttrContext> env, Type type, Symbol symbol) {
            super(Kinds.Kind.HIDDEN, symbol, "access error");
            this.env = env;
            this.site = type;
        }

        private String toString(Type type) {
            StringBuilder sb = new StringBuilder();
            sb.append(type);
            if (type != null) {
                sb.append("[tsym:");
                sb.append(type.tsym);
                if (type.tsym != null) {
                    sb.append("packge:");
                    sb.append(type.tsym.packge());
                }
                sb.append("]");
            }
            return sb.toString();
        }

        @Override // com.sun.tools.javac.comp.Resolve.InvalidSymbolError, com.sun.tools.javac.comp.Resolve.ResolveError, com.sun.tools.javac.code.Symbol
        public boolean exists() {
            return false;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            Env<AttrContext> env;
            Type type2;
            Symbol symbol2 = this.sym;
            Name name2 = symbol2.name;
            Resolve resolve = Resolve.this;
            if (name2 == resolve.names.init && symbol2.owner != type.tsym) {
                return new SymbolNotFoundError(resolve, Kinds.Kind.ABSENT_MTH).getDiagnostic(diagnosticType, diagnosticPosition, symbol, type, name, list, list2);
            }
            if ((symbol2.flags() & 1) == 0 && ((env = this.env) == null || (type2 = this.site) == null || Resolve.this.isAccessible(env, type2))) {
                long jFlags = this.sym.flags() & 6;
                Resolve resolve2 = Resolve.this;
                if (jFlags != 0) {
                    JCDiagnostic.Factory factory = resolve2.diags;
                    DiagnosticSource diagnosticSourceCurrentSource = resolve2.log.currentSource();
                    Symbol symbol3 = this.sym;
                    return factory.create(diagnosticType, diagnosticSourceCurrentSource, diagnosticPosition, "report.access", symbol3, Flags.asFlagSet(symbol3.flags() & 6), this.sym.location());
                }
                JCDiagnostic.Factory factory2 = resolve2.diags;
                DiagnosticSource diagnosticSourceCurrentSource2 = resolve2.log.currentSource();
                Symbol symbol4 = this.sym;
                return factory2.create(diagnosticType, diagnosticSourceCurrentSource2, diagnosticPosition, "not.def.public.cant.access", symbol4, symbol4.location());
            }
            Symbol symbol5 = this.sym;
            if (symbol5.owner.kind == Kinds.Kind.PCK) {
                Resolve resolve3 = Resolve.this;
                JCDiagnostic.Factory factory3 = resolve3.diags;
                DiagnosticSource diagnosticSourceCurrentSource3 = resolve3.log.currentSource();
                Symbol symbol6 = this.sym;
                return factory3.create(diagnosticType, diagnosticSourceCurrentSource3, diagnosticPosition, "not.def.access.package.cant.access", symbol6, symbol6.location(), Resolve.this.inaccessiblePackageReason(this.env, this.sym.packge()));
            }
            Symbol.PackageSymbol packageSymbolPackge = symbol5.packge();
            Resolve resolve4 = Resolve.this;
            if (packageSymbolPackge == resolve4.syms.rootPackage || resolve4.symbolPackageVisible(this.env, this.sym)) {
                Resolve resolve5 = Resolve.this;
                JCDiagnostic.Factory factory4 = resolve5.diags;
                DiagnosticSource diagnosticSourceCurrentSource4 = resolve5.log.currentSource();
                Symbol symbol7 = this.sym;
                return factory4.create(diagnosticType, diagnosticSourceCurrentSource4, diagnosticPosition, "not.def.access.class.intf.cant.access", symbol7, symbol7.location());
            }
            Resolve resolve6 = Resolve.this;
            JCDiagnostic.Factory factory5 = resolve6.diags;
            DiagnosticSource diagnosticSourceCurrentSource5 = resolve6.log.currentSource();
            Symbol symbol8 = this.sym;
            return factory5.create(diagnosticType, diagnosticSourceCurrentSource5, diagnosticPosition, "not.def.access.class.intf.cant.access.reason", symbol8, symbol8.location(), this.sym.location().packge(), Resolve.this.inaccessiblePackageReason(this.env, this.sym.packge()));
        }
    }

    public class AmbiguityError extends ResolveError {
        List<Symbol> ambiguousSyms;

        public AmbiguityError(Symbol symbol, Symbol symbol2) {
            super(Kinds.Kind.AMBIGUOUS, "ambiguity error");
            this.ambiguousSyms = List.nil();
            this.ambiguousSyms = flatten(symbol2).appendList(flatten(symbol));
        }

        private List<Symbol> flatten(Symbol symbol) {
            return symbol.kind == Kinds.Kind.AMBIGUOUS ? ((AmbiguityError) symbol.baseSymbol()).ambiguousSyms : List.of(symbol);
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public Symbol access(Name name, Symbol.TypeSymbol typeSymbol) {
            Symbol symbolLast = this.ambiguousSyms.last();
            return symbolLast.kind == Kinds.Kind.TYP ? Resolve.this.types.createErrorType(name, typeSymbol, symbolLast.type).tsym : symbolLast;
        }

        public AmbiguityError addAmbiguousSymbol(Symbol symbol) {
            this.ambiguousSyms = this.ambiguousSyms.prepend(symbol);
            return this;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError, com.sun.tools.javac.code.Symbol
        public boolean exists() {
            return true;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            List<Symbol> listReverse = this.ambiguousSyms.reverse();
            Symbol symbol2 = listReverse.head;
            Symbol symbol3 = listReverse.tail.head;
            Name name2 = symbol2.name;
            Resolve resolve = Resolve.this;
            if (name2 == resolve.names.init) {
                name2 = symbol2.owner.name;
            }
            return resolve.diags.create(diagnosticType, resolve.log.currentSource(), diagnosticPosition, "ref.ambiguous", name2, Kinds.kindName(symbol2), symbol2, symbol2.location(type, Resolve.this.types), Kinds.kindName(symbol3), symbol3, symbol3.location(type, Resolve.this.types));
        }

        public Symbol mergeAbstracts(Type type) {
            return Resolve.this.types.mergeAbstracts(this.ambiguousSyms.reverse(), type, true).orElse(this);
        }
    }

    public class ArrayConstructorReferenceLookupHelper extends ReferenceLookupHelper {
        public ArrayConstructorReferenceLookupHelper(JCTree.JCMemberReference jCMemberReference, Type type, List<Type> list, List<Type> list2, MethodResolutionPhase methodResolutionPhase) {
            super(jCMemberReference, Resolve.this.names.init, type, list, list2, methodResolutionPhase);
        }

        @Override // com.sun.tools.javac.comp.Resolve.LookupHelper
        public Symbol lookup(Env<AttrContext> env, MethodResolutionPhase methodResolutionPhase) {
            Scope.WriteableScope writeableScopeCreate = Scope.WriteableScope.create(Resolve.this.syms.arrayClass);
            Symbol.MethodSymbol methodSymbol = new Symbol.MethodSymbol(1L, this.name, null, this.site.tsym);
            methodSymbol.type = new Type.MethodType(List.of(Resolve.this.syms.intType), this.site, List.nil(), Resolve.this.syms.methodClass);
            writeableScopeCreate.enter(methodSymbol);
            Resolve resolve = Resolve.this;
            return resolve.findMethodInScope(env, this.site, this.name, this.argtypes, this.typeargtypes, writeableScopeCreate, resolve.methodNotFound, methodResolutionPhase.isBoxingRequired(), methodResolutionPhase.isVarargsRequired(), false);
        }

        @Override // com.sun.tools.javac.comp.Resolve.ReferenceLookupHelper
        public JCTree.JCMemberReference.ReferenceKind referenceKind(Symbol symbol) {
            return JCTree.JCMemberReference.ReferenceKind.ARRAY_CTOR;
        }
    }

    public class BadClassFileError extends InvalidSymbolError {
        private final Symbol.CompletionFailure ex;

        public BadClassFileError(Symbol.CompletionFailure completionFailure) {
            super(Kinds.Kind.HIDDEN, completionFailure.sym, "BadClassFileError");
            this.name = this.sym.name;
            this.ex = completionFailure;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            Resolve resolve = Resolve.this;
            JCDiagnostic.Factory factory = resolve.diags;
            DiagnosticSource diagnosticSourceCurrentSource = resolve.log.currentSource();
            Symbol.CompletionFailure completionFailure = this.ex;
            JCDiagnostic jCDiagnosticCreate = factory.create(diagnosticType, diagnosticSourceCurrentSource, diagnosticPosition, "cant.access", completionFailure.sym, completionFailure.getDetailValue());
            jCDiagnosticCreate.setFlag(JCDiagnostic.DiagnosticFlag.NON_DEFERRABLE);
            return jCDiagnosticCreate;
        }
    }

    public class BadLocalClassCreation extends StaticError {
        public BadLocalClassCreation(Symbol symbol) {
            super(symbol, "bad local class creation");
        }

        @Override // com.sun.tools.javac.comp.Resolve.StaticError, com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            Resolve resolve = Resolve.this;
            return resolve.diags.create(diagnosticType, resolve.log.currentSource(), diagnosticPosition, "local.cant.be.inst.static", Kinds.kindName(this.sym), this.sym);
        }
    }

    public class BadMethodReferenceError extends StaticError {
        boolean unboundLookup;

        public BadMethodReferenceError(Symbol symbol, boolean z) {
            super(symbol, "bad method ref error");
            this.unboundLookup = z;
        }

        @Override // com.sun.tools.javac.comp.Resolve.StaticError, com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            String str;
            if (this.unboundLookup) {
                str = this.sym.isStatic() ? "bad.static.method.in.unbound.lookup" : "bad.instance.method.in.unbound.lookup";
            } else {
                str = "bad.static.method.in.bound.lookup";
            }
            if (this.sym.kind.isResolutionError()) {
                return ((ResolveError) this.sym).getDiagnostic(diagnosticType, diagnosticPosition, symbol, type, name, list, list2);
            }
            Resolve resolve = Resolve.this;
            return resolve.diags.create(diagnosticType, resolve.log.currentSource(), diagnosticPosition, str, Kinds.kindName(this.sym), this.sym);
        }
    }

    public class BadRestrictedTypeError extends ResolveError {
        private final Name typeName;

        public BadRestrictedTypeError(Name name) {
            super(Kinds.Kind.BAD_RESTRICTED_TYPE, "bad var use");
            this.typeName = name;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            Resolve resolve = Resolve.this;
            return resolve.diags.create(diagnosticType, resolve.log.currentSource(), diagnosticPosition, "illegal.ref.to.restricted.type", this.typeName);
        }
    }

    public class BadVarargsMethod extends ResolveError {
        ResolveError delegatedError;

        public BadVarargsMethod(ResolveError resolveError) {
            super(resolveError.kind, "badVarargs");
            this.delegatedError = resolveError;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public Symbol access(Name name, Symbol.TypeSymbol typeSymbol) {
            return this.delegatedError.access(name, typeSymbol);
        }

        @Override // com.sun.tools.javac.code.Symbol
        public Symbol baseSymbol() {
            return this.delegatedError.baseSymbol();
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError, com.sun.tools.javac.code.Symbol
        public boolean exists() {
            return true;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            return this.delegatedError.getDiagnostic(diagnosticType, diagnosticPosition, symbol, type, name, list, list2);
        }
    }

    public class ConstructorReferenceLookupHelper extends ReferenceLookupHelper {
        boolean needsInference;

        public ConstructorReferenceLookupHelper(JCTree.JCMemberReference jCMemberReference, Type type, List<Type> list, List<Type> list2, MethodResolutionPhase methodResolutionPhase) {
            super(jCMemberReference, Resolve.this.names.init, type, list, list2, methodResolutionPhase);
            if (type.isRaw()) {
                this.site = new Type.ClassType(type.getEnclosingType(), (type.tsym.isInner() && type.getEnclosingType().isRaw()) ? List.nil() : type.tsym.type.getTypeArguments(), type.tsym, type.getMetadata());
                this.needsInference = true;
            }
        }

        @Override // com.sun.tools.javac.comp.Resolve.LookupHelper
        public Symbol lookup(Env<AttrContext> env, MethodResolutionPhase methodResolutionPhase) {
            boolean z = this.needsInference;
            Resolve resolve = Resolve.this;
            return z ? resolve.findDiamond(env, this.site, this.argtypes, this.typeargtypes, methodResolutionPhase.isBoxingRequired(), methodResolutionPhase.isVarargsRequired()) : resolve.findMethod(env, this.site, this.name, this.argtypes, this.typeargtypes, methodResolutionPhase.isBoxingRequired(), methodResolutionPhase.isVarargsRequired());
        }

        @Override // com.sun.tools.javac.comp.Resolve.ReferenceLookupHelper
        public JCTree.JCMemberReference.ReferenceKind referenceKind(Symbol symbol) {
            return this.site.getEnclosingType().hasTag(TypeTag.NONE) ? JCTree.JCMemberReference.ReferenceKind.TOPLEVEL : JCTree.JCMemberReference.ReferenceKind.IMPLICIT_INNER;
        }
    }

    public class DiamondError extends InapplicableSymbolError {
        Symbol sym;

        public DiamondError(Symbol symbol, MethodResolutionContext methodResolutionContext) {
            super(symbol.kind, "diamondError", methodResolutionContext);
            this.sym = symbol;
        }

        public JCDiagnostic getDetails() {
            Symbol symbol = this.sym;
            if (symbol.kind == Kinds.Kind.WRONG_MTH) {
                return ((InapplicableSymbolError) symbol.baseSymbol()).errCandidate().snd;
            }
            return null;
        }

        @Override // com.sun.tools.javac.comp.Resolve.InapplicableSymbolError, com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            JCDiagnostic details = getDetails();
            if (details != null && Resolve.this.compactMethodDiags) {
                Resolve resolve = Resolve.this;
                JCDiagnostic jCDiagnosticRewrite = MethodResolutionDiagHelper.rewrite(resolve.diags, diagnosticPosition, resolve.log.currentSource(), diagnosticType, details);
                if (jCDiagnosticRewrite != null) {
                    return jCDiagnosticRewrite;
                }
            }
            String str = details == null ? "cant.apply.diamond" : "cant.apply.diamond.1";
            Resolve resolve2 = Resolve.this;
            return resolve2.diags.create(diagnosticType, resolve2.log.currentSource(), diagnosticPosition, str, CompilerProperties.Fragments.Diamond(type.tsym), details);
        }
    }

    public static class InapplicableMethodException extends CompilerInternalException {
        private static final long serialVersionUID = 0;
        transient JCDiagnostic diagnostic;

        public InapplicableMethodException(JCDiagnostic jCDiagnostic, boolean z) {
            super(z);
            this.diagnostic = jCDiagnostic;
        }

        public JCDiagnostic getDiagnostic() {
            return this.diagnostic;
        }
    }

    public class InapplicableSymbolsError extends InapplicableSymbolError {

        public class MostSpecificMap extends LinkedHashMap<Symbol, JCDiagnostic> {
            private MostSpecificMap() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void put(MethodResolutionContext.Candidate candidate) {
                ListBuffer listBuffer = new ListBuffer();
                for (Symbol symbol : keySet()) {
                    Symbol symbol2 = candidate.sym;
                    if (symbol != symbol2) {
                        if (symbol2.overrides(symbol, (Symbol.TypeSymbol) symbol.owner, Resolve.this.types, false)) {
                            listBuffer.add(symbol);
                        } else {
                            Symbol symbol3 = candidate.sym;
                            if (symbol.overrides(symbol3, (Symbol.TypeSymbol) symbol3.owner, Resolve.this.types, false)) {
                                return;
                            }
                        }
                    }
                }
                Iterator it = listBuffer.iterator();
                while (it.hasNext()) {
                    remove((Symbol) it.next());
                }
                put(candidate.sym, candidate.details);
            }
        }

        public InapplicableSymbolsError(MethodResolutionContext methodResolutionContext) {
            super(Kinds.Kind.WRONG_MTHS, "inapplicable symbols", methodResolutionContext);
        }

        private List<JCDiagnostic> candidateDetails(Map<Symbol, JCDiagnostic> map, Type type) {
            List<JCDiagnostic> listNil = List.nil();
            for (Map.Entry<Symbol, JCDiagnostic> entry : map.entrySet()) {
                Symbol key = entry.getKey();
                listNil = listNil.prepend(Resolve.this.diags.fragment(CompilerProperties.Fragments.InapplicableMethod(Kinds.kindName(key), key.location(type, Resolve.this.types), key.asMemberOf(type, Resolve.this.types), entry.getValue())));
            }
            return listNil;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<Symbol, JCDiagnostic> mapCandidates() {
            MostSpecificMap mostSpecificMap = new MostSpecificMap();
            for (MethodResolutionContext.Candidate candidate : this.resolveContext.candidates) {
                if (!candidate.isApplicable()) {
                    mostSpecificMap.put(candidate);
                }
            }
            return mostSpecificMap;
        }

        @Override // com.sun.tools.javac.comp.Resolve.InapplicableSymbolError
        public Pair<Symbol, JCDiagnostic> errCandidate() {
            Map<Symbol, JCDiagnostic> mapFilterCandidates = filterCandidates(mapCandidates());
            if (mapFilterCandidates.size() == 1) {
                return Pair.of(mapFilterCandidates.keySet().iterator().next(), mapFilterCandidates.values().iterator().next());
            }
            return null;
        }

        public Map<Symbol, JCDiagnostic> filterCandidates(Map<Symbol, JCDiagnostic> map) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<Symbol, JCDiagnostic> entry : map.entrySet()) {
                JCDiagnostic value = entry.getValue();
                if (!new MethodResolutionDiagHelper.Template(MethodCheckDiag.ARITY_MISMATCH.regex(), new MethodResolutionDiagHelper.Template[0]).matches(value)) {
                    linkedHashMap.put(entry.getKey(), value);
                }
            }
            return linkedHashMap;
        }

        @Override // com.sun.tools.javac.comp.Resolve.InapplicableSymbolError, com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            Name name2 = name;
            Map<Symbol, JCDiagnostic> mapMapCandidates = mapCandidates();
            Map<Symbol, JCDiagnostic> mapFilterCandidates = Resolve.this.compactMethodDiags ? filterCandidates(mapMapCandidates) : mapCandidates();
            if (mapFilterCandidates.isEmpty()) {
                mapFilterCandidates = mapMapCandidates;
            }
            boolean z = mapMapCandidates.size() != mapFilterCandidates.size();
            if (mapFilterCandidates.size() > 1) {
                JCDiagnostic.Factory factory = Resolve.this.diags;
                EnumSet enumSetOf = z ? EnumSet.of(JCDiagnostic.DiagnosticFlag.COMPRESSED) : EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class);
                DiagnosticSource diagnosticSourceCurrentSource = Resolve.this.log.currentSource();
                Kinds.KindName kindNameAbsentKind = name2 == Resolve.this.names.init ? Kinds.KindName.CONSTRUCTOR : this.kind.absentKind();
                Resolve resolve = Resolve.this;
                if (name2 == resolve.names.init) {
                    name2 = type.tsym.name;
                }
                return new JCDiagnostic.MultilineDiagnostic(factory.create(diagnosticType, null, enumSetOf, diagnosticSourceCurrentSource, diagnosticPosition, "cant.apply.symbols", kindNameAbsentKind, name2, resolve.methodArguments(list)), candidateDetails(mapFilterCandidates, type));
            }
            if (mapFilterCandidates.size() != 1) {
                return new SymbolNotFoundError(Resolve.this, Kinds.Kind.ABSENT_MTH).getDiagnostic(diagnosticType, diagnosticPosition, symbol, type, name, list, list2);
            }
            Map.Entry<Symbol, JCDiagnostic> next = mapFilterCandidates.entrySet().iterator().next();
            final Pair pair = new Pair(next.getKey(), next.getValue());
            JCDiagnostic diagnostic = new InapplicableSymbolError(this, this.resolveContext) { // from class: com.sun.tools.javac.comp.Resolve.InapplicableSymbolsError.1
                final /* synthetic */ InapplicableSymbolsError this$1;

                {
                    this.this$1 = this;
                    Resolve resolve2 = Resolve.this;
                }

                @Override // com.sun.tools.javac.comp.Resolve.InapplicableSymbolError
                public Pair<Symbol, JCDiagnostic> errCandidate() {
                    return pair;
                }
            }.getDiagnostic(diagnosticType, diagnosticPosition, symbol, type, name2, list, list2);
            if (z) {
                diagnostic.setFlag(JCDiagnostic.DiagnosticFlag.COMPRESSED);
            }
            return diagnostic;
        }
    }

    public enum InterfaceLookupPhase {
        ABSTRACT_OK { // from class: com.sun.tools.javac.comp.Resolve.InterfaceLookupPhase.1
            @Override // com.sun.tools.javac.comp.Resolve.InterfaceLookupPhase
            public InterfaceLookupPhase update(Symbol symbol, Resolve resolve) {
                return (symbol.flags() & 17920) != 0 ? this : InterfaceLookupPhase.DEFAULT_OK;
            }
        },
        DEFAULT_OK { // from class: com.sun.tools.javac.comp.Resolve.InterfaceLookupPhase.2
            @Override // com.sun.tools.javac.comp.Resolve.InterfaceLookupPhase
            public InterfaceLookupPhase update(Symbol symbol, Resolve resolve) {
                return this;
            }
        };

        public abstract InterfaceLookupPhase update(Symbol symbol, Resolve resolve);
    }

    public abstract class InvalidSymbolError extends ResolveError {
        Symbol sym;

        public InvalidSymbolError(Kinds.Kind kind, Symbol symbol, String str) {
            super(kind, str);
            this.sym = symbol;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public Symbol access(Name name, Symbol.TypeSymbol typeSymbol) {
            return (this.sym.kind.isResolutionError() || !this.sym.kind.matches(Kinds.KindSelector.TYP)) ? this.sym : Resolve.this.types.createErrorType(name, typeSymbol, this.sym.type).tsym;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError, com.sun.tools.javac.code.Symbol
        public boolean exists() {
            return true;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError, com.sun.tools.javac.code.Symbol
        public String toString() {
            return super.toString() + " wrongSym=" + this.sym;
        }
    }

    public class InvisibleSymbolError extends InvalidSymbolError {
        private final Env<AttrContext> env;
        private final boolean suppressError;

        public InvisibleSymbolError(Env<AttrContext> env, boolean z, Symbol symbol) {
            super(Kinds.Kind.HIDDEN, symbol, "invisible class error");
            this.env = env;
            this.suppressError = z;
            this.name = symbol.name;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            Kinds.Kind kind;
            if (this.suppressError) {
                return null;
            }
            Symbol symbol2 = this.sym;
            Kinds.Kind kind2 = symbol2.kind;
            Kinds.Kind kind3 = Kinds.Kind.PCK;
            Resolve resolve = Resolve.this;
            if (kind2 == kind3) {
                JCDiagnostic jCDiagnosticInaccessiblePackageReason = resolve.inaccessiblePackageReason(this.env, symbol2.packge());
                Resolve resolve2 = Resolve.this;
                return resolve2.diags.create(diagnosticType, resolve2.log.currentSource(), diagnosticPosition, "package.not.visible", this.sym, jCDiagnosticInaccessiblePackageReason);
            }
            JCDiagnostic jCDiagnosticInaccessiblePackageReason2 = resolve.inaccessiblePackageReason(this.env, symbol2.packge());
            if (diagnosticPosition.getTree() != null) {
                Symbol symbol3 = this.sym;
                JCTree tree = diagnosticPosition.getTree();
                while (true) {
                    Kinds.Kind kind4 = symbol3.kind;
                    kind = Kinds.Kind.PCK;
                    if (kind4 == kind || !tree.hasTag(JCTree.Tag.SELECT)) {
                        break;
                    }
                    symbol3 = symbol3.owner;
                    tree = ((JCTree.JCFieldAccess) tree).selected;
                }
                if (symbol3.kind == kind) {
                    JCDiagnostic.DiagnosticPosition diagnosticPositionPos = tree.pos();
                    Resolve resolve3 = Resolve.this;
                    return resolve3.diags.create(diagnosticType, resolve3.log.currentSource(), diagnosticPositionPos, "package.not.visible", symbol3, jCDiagnosticInaccessiblePackageReason2);
                }
            }
            Resolve resolve4 = Resolve.this;
            JCDiagnostic.Factory factory = resolve4.diags;
            DiagnosticSource diagnosticSourceCurrentSource = resolve4.log.currentSource();
            Symbol symbol4 = this.sym;
            return factory.create(diagnosticType, diagnosticSourceCurrentSource, diagnosticPosition, "not.def.access.package.cant.access", symbol4, symbol4.packge(), jCDiagnosticInaccessiblePackageReason2);
        }
    }

    public interface LogResolveHelper {
        List<Type> getArgumentTypes(ResolveError resolveError, Symbol symbol, Name name, List<Type> list);

        boolean resolveDiagnosticNeeded(Type type, List<Type> list, List<Type> list2);
    }

    public class LookupFilter implements Predicate<Symbol> {
        boolean abstractOk;

        public LookupFilter(boolean z) {
            this.abstractOk = z;
        }

        @Override // java.util.function.Predicate
        public boolean test(Symbol symbol) {
            long jFlags = symbol.flags();
            if (symbol.kind == Kinds.Kind.MTH && (4096 & jFlags) == 0) {
                return this.abstractOk || (Flags.DEFAULT & jFlags) != 0 || (1024 & jFlags) == 0;
            }
            return false;
        }
    }

    public abstract class LookupHelper {
        List<Type> argtypes;
        MethodResolutionPhase maxPhase;
        Name name;
        Type site;
        List<Type> typeargtypes;

        public LookupHelper(Name name, Type type, List<Type> list, List<Type> list2, MethodResolutionPhase methodResolutionPhase) {
            this.name = name;
            this.site = type;
            this.argtypes = list;
            this.typeargtypes = list2;
            this.maxPhase = methodResolutionPhase;
        }

        public abstract Symbol access(Env<AttrContext> env, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Symbol symbol2);

        public void debug(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
        }

        public abstract Symbol lookup(Env<AttrContext> env, MethodResolutionPhase methodResolutionPhase);

        public final boolean shouldStop(Symbol symbol, MethodResolutionPhase methodResolutionPhase) {
            Kinds.Kind kind;
            return methodResolutionPhase.ordinal() > this.maxPhase.ordinal() || !symbol.kind.isResolutionError() || (kind = symbol.kind) == Kinds.Kind.AMBIGUOUS || kind == Kinds.Kind.STATICERR;
        }
    }

    public interface MethodCheck {
        void argumentsAcceptable(Env<AttrContext> env, DeferredAttr.DeferredAttrContext deferredAttrContext, List<Type> list, List<Type> list2, Warner warner);

        MethodCheck mostSpecificCheck(List<Type> list);
    }

    public abstract class MethodCheckContext implements Check.CheckContext {
        DeferredAttr.DeferredAttrContext deferredAttrContext;
        Warner rsWarner;
        boolean strict;

        public MethodCheckContext(boolean z, DeferredAttr.DeferredAttrContext deferredAttrContext, Warner warner) {
            this.strict = z;
            this.deferredAttrContext = deferredAttrContext;
            this.rsWarner = warner;
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public Warner checkWarner(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2) {
            return this.rsWarner;
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public boolean compatible(Type type, Type type2, Warner warner) {
            InferenceContext inferenceContext = this.deferredAttrContext.inferenceContext;
            boolean z = this.strict;
            Resolve resolve = Resolve.this;
            return z ? resolve.types.isSubtypeUnchecked(inferenceContext.asUndetVar(type), inferenceContext.asUndetVar(type2), warner) : resolve.types.isConvertible(inferenceContext.asUndetVar(type), inferenceContext.asUndetVar(type2), warner);
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public DeferredAttr.DeferredAttrContext deferredAttrContext() {
            return this.deferredAttrContext;
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public InferenceContext inferenceContext() {
            return this.deferredAttrContext.inferenceContext;
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
            throw new InapplicableMethodException(jCDiagnostic, Resolve.this.dumpStacktraceOnError);
        }

        public String toString() {
            return "MethodCheckContext";
        }
    }

    public enum MethodCheckDiag {
        ARITY_MISMATCH("arg.length.mismatch", "infer.arg.length.mismatch"),
        ARG_MISMATCH("no.conforming.assignment.exists", "infer.no.conforming.assignment.exists"),
        VARARG_MISMATCH("varargs.argument.mismatch", "infer.varargs.argument.mismatch"),
        INACCESSIBLE_VARARGS("inaccessible.varargs.type", "inaccessible.varargs.type");

        final String basicKey;
        final String inferKey;

        MethodCheckDiag(String str, String str2) {
            this.basicKey = str;
            this.inferKey = str2;
        }

        public String regex() {
            return String.format("([a-z]*\\.)*(%s|%s)", this.basicKey, this.inferKey);
        }
    }

    public class MethodReferenceCheck extends AbstractMethodCheck {
        InferenceContext pendingInferenceContext;

        public MethodReferenceCheck(InferenceContext inferenceContext) {
            super();
            this.pendingInferenceContext = inferenceContext;
        }

        private Attr.ResultInfo methodCheckResult(boolean z, Type type, DeferredAttr.DeferredAttrContext deferredAttrContext, Warner warner) {
            return Resolve.this.new MethodResultInfo(type, new MethodCheckContext(this, !deferredAttrContext.phase.isBoxingRequired(), deferredAttrContext, warner, z) { // from class: com.sun.tools.javac.comp.Resolve.MethodReferenceCheck.1
                MethodCheckDiag methodDiag;
                final /* synthetic */ MethodReferenceCheck this$1;
                final /* synthetic */ boolean val$varargsCheck;

                {
                    this.val$varargsCheck = z;
                    this.this$1 = this;
                    Resolve resolve = Resolve.this;
                    this.methodDiag = z ? MethodCheckDiag.VARARG_MISMATCH : MethodCheckDiag.ARG_MISMATCH;
                }

                @Override // com.sun.tools.javac.comp.Resolve.MethodCheckContext, com.sun.tools.javac.comp.Check.CheckContext
                public boolean compatible(Type type2, Type type3, Warner warner2) {
                    Type typeAsUndetVar = this.this$1.pendingInferenceContext.asUndetVar(type2);
                    if (typeAsUndetVar.hasTag(TypeTag.UNDETVAR) && type3.isPrimitive()) {
                        type3 = Resolve.this.types.boxedClass(type3).type;
                    }
                    return super.compatible(typeAsUndetVar, type3, warner2);
                }

                @Override // com.sun.tools.javac.comp.Resolve.MethodCheckContext, com.sun.tools.javac.comp.Check.CheckContext
                public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
                    this.this$1.reportMC(diagnosticPosition, this.methodDiag, this.deferredAttrContext.inferenceContext, jCDiagnostic);
                }
            });
        }

        @Override // com.sun.tools.javac.comp.Resolve.AbstractMethodCheck
        public void checkArg(JCDiagnostic.DiagnosticPosition diagnosticPosition, boolean z, Type type, Type type2, DeferredAttr.DeferredAttrContext deferredAttrContext, Warner warner) {
            methodCheckResult(z, type2, deferredAttrContext, warner).check(diagnosticPosition, type);
        }

        @Override // com.sun.tools.javac.comp.Resolve.AbstractMethodCheck, com.sun.tools.javac.comp.Resolve.MethodCheck
        public MethodCheck mostSpecificCheck(List<Type> list) {
            return Resolve.this.new MostSpecificCheck(list);
        }

        public String toString() {
            return "MethodReferenceCheck";
        }
    }

    public class MethodReferenceLookupHelper extends ReferenceLookupHelper {
        Type originalSite;

        public MethodReferenceLookupHelper(JCTree.JCMemberReference jCMemberReference, Name name, Type type, List<Type> list, List<Type> list2, MethodResolutionPhase methodResolutionPhase) {
            super(jCMemberReference, name, Resolve.this.types.skipTypeVars(type, true), list, list2, methodResolutionPhase);
            this.originalSite = type;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ReferenceLookupHelper, com.sun.tools.javac.comp.Resolve.LookupHelper
        public Symbol access(Env<AttrContext> env, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Symbol symbol2) {
            if (this.originalSite.hasTag(TypeTag.TYPEVAR) && symbol2.kind == Kinds.Kind.MTH) {
                return Resolve.this.accessBase((symbol2.flags() & 2) != 0 ? Resolve.this.new AccessError(env, this.site, symbol2) : symbol2, diagnosticPosition, symbol, this.originalSite, this.name, true);
            }
            return super.access(env, diagnosticPosition, symbol, symbol2);
        }

        @Override // com.sun.tools.javac.comp.Resolve.LookupHelper
        public final Symbol lookup(Env<AttrContext> env, MethodResolutionPhase methodResolutionPhase) {
            return Resolve.this.findMethod(env, this.site, this.name, this.argtypes, this.typeargtypes, methodResolutionPhase.isBoxingRequired(), methodResolutionPhase.isVarargsRequired());
        }

        @Override // com.sun.tools.javac.comp.Resolve.ReferenceLookupHelper
        public JCTree.JCMemberReference.ReferenceKind referenceKind(Symbol symbol) {
            if (symbol.isStatic()) {
                return JCTree.JCMemberReference.ReferenceKind.STATIC;
            }
            Name name = TreeInfo.name(this.referenceTree.getQualifierExpression());
            return (name == null || name != Resolve.this.names._super) ? JCTree.JCMemberReference.ReferenceKind.BOUND : JCTree.JCMemberReference.ReferenceKind.SUPER;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ReferenceLookupHelper
        public ReferenceLookupHelper unboundLookup(InferenceContext inferenceContext) {
            if (TreeInfo.isStaticSelector(this.referenceTree.expr, Resolve.this.names)) {
                return (this.argtypes.nonEmpty() && (this.argtypes.head.hasTag(TypeTag.NONE) || Resolve.this.types.isSubtypeUnchecked(inferenceContext.asUndetVar(this.argtypes.head), this.originalSite))) ? Resolve.this.new UnboundMethodReferenceLookupHelper(this.referenceTree, this.name, this.originalSite, this.argtypes, this.typeargtypes, this.maxPhase) : new ReferenceLookupHelper(this.referenceTree, this.name, this.site, this.argtypes, this.typeargtypes, this.maxPhase) { // from class: com.sun.tools.javac.comp.Resolve.MethodReferenceLookupHelper.1
                    {
                        Resolve resolve = Resolve.this;
                    }

                    @Override // com.sun.tools.javac.comp.Resolve.LookupHelper
                    public Symbol lookup(Env<AttrContext> env, MethodResolutionPhase methodResolutionPhase) {
                        return Resolve.this.methodNotFound;
                    }

                    @Override // com.sun.tools.javac.comp.Resolve.ReferenceLookupHelper
                    public JCTree.JCMemberReference.ReferenceKind referenceKind(Symbol symbol) {
                        Assert.error();
                        return null;
                    }

                    @Override // com.sun.tools.javac.comp.Resolve.ReferenceLookupHelper
                    public ReferenceLookupHelper unboundLookup(InferenceContext inferenceContext2) {
                        return this;
                    }
                };
            }
            return super.unboundLookup(inferenceContext);
        }
    }

    public class MethodResolutionContext {
        MethodCheck methodCheck;
        private List<Candidate> candidates = List.nil();
        MethodResolutionPhase step = null;
        private boolean internalResolution = false;
        private DeferredAttr.AttrMode attrMode = DeferredAttr.AttrMode.SPECULATIVE;

        public class Candidate {
            final JCDiagnostic details;
            final Type mtype;
            final MethodResolutionPhase step;
            final Symbol sym;

            private Candidate(MethodResolutionPhase methodResolutionPhase, Symbol symbol, JCDiagnostic jCDiagnostic, Type type) {
                this.step = methodResolutionPhase;
                this.sym = symbol;
                this.details = jCDiagnostic;
                this.mtype = type;
            }

            public boolean isApplicable() {
                return this.mtype != null;
            }
        }

        public MethodResolutionContext() {
            this.methodCheck = Resolve.this.resolveMethodCheck;
        }

        public void addApplicableCandidate(Symbol symbol, Type type) {
            this.candidates = this.candidates.append(new Candidate(Resolve.this.currentResolutionContext.step, symbol, null, type));
        }

        public void addInapplicableCandidate(Symbol symbol, JCDiagnostic jCDiagnostic) {
            this.candidates = this.candidates.append(new Candidate(Resolve.this.currentResolutionContext.step, symbol, jCDiagnostic, null));
        }

        public DeferredAttr.AttrMode attrMode() {
            return this.attrMode;
        }

        public DeferredAttr.DeferredAttrContext deferredAttrContext(Symbol symbol, InferenceContext inferenceContext, Attr.ResultInfo resultInfo, Warner warner) {
            DeferredAttr.DeferredAttrContext deferredAttrContext = resultInfo == null ? Resolve.this.deferredAttr.emptyDeferredAttrContext : resultInfo.checkContext.deferredAttrContext();
            DeferredAttr deferredAttr = Resolve.this.deferredAttr;
            Objects.requireNonNull(deferredAttr);
            return deferredAttr.new DeferredAttrContext(this.attrMode, symbol, this.step, inferenceContext, deferredAttrContext, warner);
        }

        public boolean internal() {
            return this.internalResolution;
        }
    }

    public static class MethodResolutionDiagHelper {
        static final Template argMismatchTemplate;
        static final Template inferArgMismatchTemplate;
        static final Map<Template, DiagnosticRewriter> rewriters;
        static final Template skip;

        /* JADX INFO: renamed from: com.sun.tools.javac.comp.Resolve$MethodResolutionDiagHelper$2, reason: invalid class name */
        public class AnonymousClass2 extends Template {
            BiPredicate<Object, List<Type>> containsPredicate;

            public AnonymousClass2(String str, Template... templateArr) {
                super(str, templateArr);
                this.containsPredicate = new BiPredicate() { // from class: com.sun.tools.javac.comp.z2
                    @Override // java.util.function.BiPredicate
                    public final boolean test(Object obj, Object obj2) {
                        return Resolve.MethodResolutionDiagHelper.AnonymousClass2.b(this.b, obj, (List) obj2);
                    }
                };
            }

            public static /* synthetic */ boolean b(AnonymousClass2 anonymousClass2, Object obj, List list) {
                anonymousClass2.getClass();
                if (obj instanceof Type) {
                    return ((Type) obj).containsAny(list);
                }
                if (obj instanceof JCDiagnostic) {
                    return anonymousClass2.containsAny((JCDiagnostic) obj, list);
                }
                return false;
            }

            public boolean containsAny(JCDiagnostic jCDiagnostic, final List<Type> list) {
                return Stream.of(jCDiagnostic.getArgs()).anyMatch(new Predicate() { // from class: com.sun.tools.javac.comp.y2
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return this.b.containsPredicate.test(obj, list);
                    }
                });
            }

            @Override // com.sun.tools.javac.comp.Resolve.MethodResolutionDiagHelper.Template
            public boolean matches(Object obj) {
                if (!super.matches(obj)) {
                    return false;
                }
                JCDiagnostic jCDiagnostic = (JCDiagnostic) obj;
                return !containsAny(jCDiagnostic, (List) jCDiagnostic.getArgs()[0]);
            }
        }

        public static class ArgMismatchRewriter implements DiagnosticRewriter {
            int causeIndex;

            public ArgMismatchRewriter(int i) {
                this.causeIndex = i;
            }

            @Override // com.sun.tools.javac.comp.Resolve.MethodResolutionDiagHelper.DiagnosticRewriter
            public JCDiagnostic rewriteDiagnostic(JCDiagnostic.Factory factory, JCDiagnostic.DiagnosticPosition diagnosticPosition, DiagnosticSource diagnosticSource, JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic jCDiagnostic) {
                JCDiagnostic jCDiagnostic2 = (JCDiagnostic) jCDiagnostic.getArgs()[this.causeIndex];
                JCDiagnostic.DiagnosticPosition diagnosticPosition2 = jCDiagnostic.getDiagnosticPosition();
                return factory.create(diagnosticType, diagnosticSource, diagnosticPosition2 == null ? diagnosticPosition : diagnosticPosition2, "prob.found.req", jCDiagnostic2);
            }
        }

        public interface DiagnosticRewriter {
            JCDiagnostic rewriteDiagnostic(JCDiagnostic.Factory factory, JCDiagnostic.DiagnosticPosition diagnosticPosition, DiagnosticSource diagnosticSource, JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic jCDiagnostic);
        }

        public static class Template {
            String regex;
            Template[] subTemplates;

            public Template(String str, Template... templateArr) {
                this.regex = str;
                this.subTemplates = templateArr;
            }

            public boolean matches(Object obj) {
                JCDiagnostic jCDiagnostic = (JCDiagnostic) obj;
                Object[] args = jCDiagnostic.getArgs();
                if (!jCDiagnostic.getCode().matches(this.regex) || this.subTemplates.length != jCDiagnostic.getArgs().length) {
                    return false;
                }
                for (int i = 0; i < args.length; i++) {
                    if (!this.subTemplates[i].matches(args[i])) {
                        return false;
                    }
                }
                return true;
            }
        }

        static {
            Template template = new Template("", new Template[0]) { // from class: com.sun.tools.javac.comp.Resolve.MethodResolutionDiagHelper.1
                @Override // com.sun.tools.javac.comp.Resolve.MethodResolutionDiagHelper.Template
                public boolean matches(Object obj) {
                    return true;
                }
            };
            skip = template;
            MethodCheckDiag methodCheckDiag = MethodCheckDiag.ARG_MISMATCH;
            Template template2 = new Template(methodCheckDiag.regex(), template);
            argMismatchTemplate = template2;
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(methodCheckDiag.regex(), template, template);
            inferArgMismatchTemplate = anonymousClass2;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            rewriters = linkedHashMap;
            linkedHashMap.put(template2, new ArgMismatchRewriter(0));
            linkedHashMap.put(anonymousClass2, new ArgMismatchRewriter(1));
        }

        public static JCDiagnostic rewrite(JCDiagnostic.Factory factory, JCDiagnostic.DiagnosticPosition diagnosticPosition, DiagnosticSource diagnosticSource, JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic jCDiagnostic) {
            for (Map.Entry<Template, DiagnosticRewriter> entry : rewriters.entrySet()) {
                if (entry.getKey().matches(jCDiagnostic)) {
                    JCDiagnostic jCDiagnosticRewriteDiagnostic = entry.getValue().rewriteDiagnostic(factory, diagnosticPosition, diagnosticSource, diagnosticType, jCDiagnostic);
                    jCDiagnosticRewriteDiagnostic.setFlag(JCDiagnostic.DiagnosticFlag.COMPRESSED);
                    return jCDiagnosticRewriteDiagnostic;
                }
            }
            return null;
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'VARARITY' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static class MethodResolutionPhase {
        public static final MethodResolutionPhase VARARITY;
        final boolean isBoxingRequired;
        final boolean isVarargsRequired;
        public static final MethodResolutionPhase BASIC = new MethodResolutionPhase("BASIC", 0, false, false);
        public static final MethodResolutionPhase BOX = new MethodResolutionPhase("BOX", 1, true, false);
        private static final /* synthetic */ MethodResolutionPhase[] $VALUES = $values();

        private static /* synthetic */ MethodResolutionPhase[] $values() {
            return new MethodResolutionPhase[]{BASIC, BOX, VARARITY};
        }

        static {
            boolean z = true;
            VARARITY = new MethodResolutionPhase("VARARITY", 2, z, z) { // from class: com.sun.tools.javac.comp.Resolve.MethodResolutionPhase.1
                @Override // com.sun.tools.javac.comp.Resolve.MethodResolutionPhase
                public Symbol mergeResults(Symbol symbol, Symbol symbol2) {
                    int[] iArr;
                    int i;
                    int i2;
                    Assert.check(symbol.kind.isResolutionError() && symbol.kind != Kinds.Kind.AMBIGUOUS);
                    return (symbol2.kind.isResolutionError() && (!((i = (iArr = AnonymousClass19.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind)[symbol.kind.ordinal()]) == 3 || i == 6) || (i2 = iArr[symbol2.kind.ordinal()]) == 1 || (i2 == 3 && symbol.kind == Kinds.Kind.WRONG_MTHS))) ? symbol : symbol2;
                }
            };
        }

        private MethodResolutionPhase(String str, int i, boolean z, boolean z2) {
            super(str, i);
            this.isBoxingRequired = z;
            this.isVarargsRequired = z2;
        }

        public static MethodResolutionPhase valueOf(String str) {
            return (MethodResolutionPhase) Enum.valueOf(MethodResolutionPhase.class, str);
        }

        public static MethodResolutionPhase[] values() {
            return (MethodResolutionPhase[]) $VALUES.clone();
        }

        public boolean isBoxingRequired() {
            return this.isBoxingRequired;
        }

        public boolean isVarargsRequired() {
            return this.isVarargsRequired;
        }

        public Symbol mergeResults(Symbol symbol, Symbol symbol2) {
            return symbol2;
        }
    }

    public class MostSpecificCheck implements MethodCheck {
        List<Type> actuals;

        public class MostSpecificCheckContext extends MethodCheckContext {
            Type actual;

            public class MostSpecificFunctionReturnChecker extends DeferredAttr.PolyScanner {
                boolean result = true;
                final Type sRet;
                final Type tRet;

                public MostSpecificFunctionReturnChecker(Type type, Type type2) {
                    this.tRet = type;
                    this.sRet = type2;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public JCTree.JCExpression asExpr(JCTree.JCExpression jCExpression) {
                    JCTree jCTreeSpeculativeTree;
                    return (!jCExpression.type.hasTag(TypeTag.DEFERRED) || (jCTreeSpeculativeTree = ((DeferredAttr.DeferredType) jCExpression.type).speculativeTree(MostSpecificCheckContext.this.deferredAttrContext)) == Resolve.this.deferredAttr.stuckTree) ? jCExpression : (JCTree.JCExpression) jCTreeSpeculativeTree;
                }

                private List<JCTree.JCExpression> lambdaResults(JCTree.JCLambda jCLambda) {
                    if (jCLambda.getBodyKind() == LambdaExpressionTree.BodyKind.EXPRESSION) {
                        return List.of(asExpr((JCTree.JCExpression) jCLambda.body));
                    }
                    final ListBuffer listBuffer = new ListBuffer();
                    new DeferredAttr.LambdaReturnScanner(this) { // from class: com.sun.tools.javac.comp.Resolve.MostSpecificCheck.MostSpecificCheckContext.MostSpecificFunctionReturnChecker.1
                        final /* synthetic */ MostSpecificFunctionReturnChecker this$3;

                        {
                            this.this$3 = this;
                        }

                        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                        public void visitReturn(JCTree.JCReturn jCReturn) {
                            JCTree.JCExpression jCExpression = jCReturn.expr;
                            if (jCExpression != null) {
                                listBuffer.append(this.this$3.asExpr(jCExpression));
                            }
                        }
                    }.scan(jCLambda.body);
                    return listBuffer.toList();
                }

                @Override // com.sun.tools.javac.comp.DeferredAttr.FilterScanner
                public void skip(JCTree jCTree) {
                    this.result = false;
                }

                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitConditional(JCTree.JCConditional jCConditional) {
                    scan(asExpr(jCConditional.truepart));
                    scan(asExpr(jCConditional.falsepart));
                }

                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitLambda(JCTree.JCLambda jCLambda) {
                    Type type = this.sRet;
                    TypeTag typeTag = TypeTag.VOID;
                    if (type.hasTag(typeTag)) {
                        return;
                    }
                    if (this.tRet.hasTag(typeTag)) {
                        this.result = false;
                        return;
                    }
                    List<JCTree.JCExpression> listLambdaResults = lambdaResults(jCLambda);
                    if (!listLambdaResults.isEmpty() && MostSpecificCheckContext.this.unrelatedFunctionalInterfaces(this.tRet, this.sRet)) {
                        Iterator<JCTree.JCExpression> it = listLambdaResults.iterator();
                        while (it.hasNext()) {
                            this.result = MostSpecificCheckContext.this.functionalInterfaceMostSpecific(this.tRet, this.sRet, it.next()) & this.result;
                        }
                        return;
                    }
                    if (listLambdaResults.isEmpty() || this.tRet.isPrimitive() == this.sRet.isPrimitive()) {
                        this.result &= MostSpecificCheckContext.this.compatibleBySubtyping(this.tRet, this.sRet);
                        return;
                    }
                    for (JCTree.JCExpression jCExpression : listLambdaResults) {
                        boolean z = true;
                        boolean z2 = jCExpression.isStandalone() && jCExpression.type.isPrimitive();
                        boolean z3 = this.result;
                        if (z2 != this.tRet.isPrimitive() || z2 == this.sRet.isPrimitive()) {
                            z = false;
                        }
                        this.result = z3 & z;
                    }
                }

                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitParens(JCTree.JCParens jCParens) {
                    scan(asExpr(jCParens.expr));
                }

                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitReference(JCTree.JCMemberReference jCMemberReference) {
                    Type type = this.sRet;
                    TypeTag typeTag = TypeTag.VOID;
                    if (type.hasTag(typeTag)) {
                        return;
                    }
                    boolean zHasTag = this.tRet.hasTag(typeTag);
                    boolean z = false;
                    if (zHasTag) {
                        this.result = false;
                        return;
                    }
                    if (this.tRet.isPrimitive() == this.sRet.isPrimitive()) {
                        this.result &= MostSpecificCheckContext.this.compatibleBySubtyping(this.tRet, this.sRet);
                        return;
                    }
                    boolean z2 = jCMemberReference.refPolyKind == JCTree.JCPolyExpression.PolyKind.STANDALONE && jCMemberReference.sym.type.mo73getReturnType().isPrimitive();
                    boolean z3 = this.result;
                    if (z2 == this.tRet.isPrimitive() && z2 != this.sRet.isPrimitive()) {
                        z = true;
                    }
                    this.result = z3 & z;
                }
            }

            public MostSpecificCheckContext(DeferredAttr.DeferredAttrContext deferredAttrContext, Warner warner, Type type) {
                super(true, deferredAttrContext, warner);
                this.actual = type;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public boolean compatibleBySubtyping(Type type, Type type2) {
                if (!this.strict && type.isPrimitive() != type2.isPrimitive()) {
                    boolean zIsPrimitive = type.isPrimitive();
                    MostSpecificCheck mostSpecificCheck = MostSpecificCheck.this;
                    type = zIsPrimitive ? Resolve.this.types.boxedClass(type).type : Resolve.this.types.unboxedType(type);
                }
                return Resolve.this.types.isSubtypeNoCapture(type, this.deferredAttrContext.inferenceContext.asUndetVar(type2));
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Multi-variable type inference failed */
            public boolean functionalInterfaceMostSpecific(Type type, Type type2, JCTree jCTree) {
                try {
                    Types types = Resolve.this.types;
                    Type typeFindDescriptorType = types.findDescriptorType(types.capture(type));
                    Type typeFindDescriptorType2 = Resolve.this.types.findDescriptorType(type);
                    Type typeFindDescriptorType3 = Resolve.this.types.findDescriptorType(type2);
                    List<Type> typeArguments = typeFindDescriptorType.getTypeArguments();
                    List<Type> typeArguments2 = typeFindDescriptorType2.getTypeArguments();
                    List<Type> typeArguments3 = typeFindDescriptorType3.getTypeArguments();
                    if (typeFindDescriptorType.hasTag(TypeTag.FORALL) && !Resolve.this.types.hasSameBounds((Type.ForAll) typeFindDescriptorType, (Type.ForAll) typeFindDescriptorType2)) {
                        return false;
                    }
                    List list = typeArguments;
                    List list2 = typeArguments3;
                    while (list.nonEmpty() && list2.nonEmpty()) {
                        Type upperBound = ((Type) list.head).getUpperBound();
                        Type typeSubst = Resolve.this.types.subst(((Type) list2.head).getUpperBound(), typeArguments3, typeArguments);
                        if ((upperBound.containsAny(typeArguments) && inferenceContext().free(typeSubst)) || !Resolve.this.types.isSameType(upperBound, inferenceContext().asUndetVar(typeSubst))) {
                            return false;
                        }
                        list = list.tail;
                        list2 = list2.tail;
                    }
                    if (list.isEmpty() && list2.isEmpty()) {
                        List listMo71getParameterTypes = typeFindDescriptorType.mo71getParameterTypes();
                        List listMo71getParameterTypes2 = typeFindDescriptorType2.mo71getParameterTypes();
                        List listMo71getParameterTypes3 = typeFindDescriptorType3.mo71getParameterTypes();
                        while (listMo71getParameterTypes.nonEmpty() && listMo71getParameterTypes2.nonEmpty() && listMo71getParameterTypes3.nonEmpty()) {
                            Type type3 = (Type) listMo71getParameterTypes.head;
                            Type typeSubst2 = Resolve.this.types.subst((Type) listMo71getParameterTypes2.head, typeArguments2, typeArguments);
                            Type typeSubst3 = Resolve.this.types.subst((Type) listMo71getParameterTypes3.head, typeArguments3, typeArguments);
                            if ((type3.containsAny(typeArguments) && inferenceContext().free(typeSubst3)) || !Resolve.this.types.isSubtype(inferenceContext().asUndetVar(typeSubst3), type3) || !Resolve.this.types.isSameType(typeSubst2, inferenceContext().asUndetVar(typeSubst3))) {
                                return false;
                            }
                            listMo71getParameterTypes = listMo71getParameterTypes.tail;
                            listMo71getParameterTypes2 = listMo71getParameterTypes2.tail;
                            listMo71getParameterTypes3 = listMo71getParameterTypes3.tail;
                        }
                        if (listMo71getParameterTypes.isEmpty() && listMo71getParameterTypes2.isEmpty() && listMo71getParameterTypes3.isEmpty()) {
                            Type typeMo73getReturnType = typeFindDescriptorType.mo73getReturnType();
                            Type typeSubst4 = Resolve.this.types.subst(typeFindDescriptorType3.mo73getReturnType(), typeArguments3, typeArguments);
                            if (typeMo73getReturnType.containsAny(typeArguments) && inferenceContext().free(typeSubst4)) {
                                return false;
                            }
                            MostSpecificFunctionReturnChecker mostSpecificFunctionReturnChecker = new MostSpecificFunctionReturnChecker(typeMo73getReturnType, typeSubst4);
                            mostSpecificFunctionReturnChecker.scan(jCTree);
                            return mostSpecificFunctionReturnChecker.result;
                        }
                    }
                    return false;
                } catch (Types.FunctionDescriptorLookupError unused) {
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public boolean unrelatedFunctionalInterfaces(Type type, Type type2) {
                return Resolve.this.types.isFunctionalInterface(type.tsym) && Resolve.this.types.isFunctionalInterface(type2.tsym) && unrelatedInterfaces(type, type2);
            }

            private boolean unrelatedInterfaces(Type type, Type type2) {
                if (type.isCompound()) {
                    Iterator<Type> it = Resolve.this.types.interfaces(type).iterator();
                    while (it.hasNext()) {
                        if (!unrelatedInterfaces(it.next(), type2)) {
                            return false;
                        }
                    }
                    return true;
                }
                boolean zIsCompound = type2.isCompound();
                MostSpecificCheck mostSpecificCheck = MostSpecificCheck.this;
                if (!zIsCompound) {
                    return Resolve.this.types.asSuper(type, type2.tsym) == null && Resolve.this.types.asSuper(type2, type.tsym) == null;
                }
                Iterator<Type> it2 = Resolve.this.types.interfaces(type2).iterator();
                while (it2.hasNext()) {
                    if (!unrelatedInterfaces(type, it2.next())) {
                        return false;
                    }
                }
                return true;
            }

            @Override // com.sun.tools.javac.comp.Resolve.MethodCheckContext, com.sun.tools.javac.comp.Check.CheckContext
            public boolean compatible(Type type, Type type2, Warner warner) {
                Type type3;
                JCTree jCTreeSpeculativeTree;
                return (!unrelatedFunctionalInterfaces(type, type2) || (type3 = this.actual) == null || type3.getTag() != TypeTag.DEFERRED || (jCTreeSpeculativeTree = ((DeferredAttr.DeferredType) this.actual).speculativeTree(this.deferredAttrContext)) == Resolve.this.deferredAttr.stuckTree) ? compatibleBySubtyping(type, type2) : functionalInterfaceMostSpecific(type, type2, jCTreeSpeculativeTree);
            }
        }

        public MostSpecificCheck(List<Type> list) {
            this.actuals = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.comp.Resolve.MethodCheck
        public void argumentsAcceptable(Env<AttrContext> env, DeferredAttr.DeferredAttrContext deferredAttrContext, List<Type> list, List<Type> list2, Warner warner) {
            List listAdjustArgs = Resolve.this.adjustArgs(list2, deferredAttrContext.msym, list.length(), deferredAttrContext.phase.isVarargsRequired());
            List list3 = list;
            while (listAdjustArgs.nonEmpty()) {
                methodCheckResult((Type) listAdjustArgs.head, deferredAttrContext, warner, this.actuals.head).check(null, (Type) list3.head);
                List list4 = list3.tail;
                listAdjustArgs = listAdjustArgs.tail;
                boolean zIsEmpty = this.actuals.isEmpty();
                List list5 = this.actuals;
                if (!zIsEmpty) {
                    list5 = list5.tail;
                }
                this.actuals = list5;
                list3 = list4;
            }
        }

        public Attr.ResultInfo methodCheckResult(Type type, DeferredAttr.DeferredAttrContext deferredAttrContext, Warner warner, Type type2) {
            Attr attr = Resolve.this.attr;
            Objects.requireNonNull(attr);
            return new Attr.ResultInfo(attr, Kinds.KindSelector.VAL, type, new MostSpecificCheckContext(deferredAttrContext, warner, type2));
        }

        @Override // com.sun.tools.javac.comp.Resolve.MethodCheck
        public MethodCheck mostSpecificCheck(List<Type> list) {
            Assert.error("Cannot get here!");
            return null;
        }
    }

    public interface RecoveryLoadClass {
        Symbol loadClass(Env<AttrContext> env, Name name);
    }

    public class RefBeforeCtorCalledError extends StaticError {
        public RefBeforeCtorCalledError(Symbol symbol) {
            super(symbol, "prologue error");
        }

        @Override // com.sun.tools.javac.comp.Resolve.StaticError, com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            Symbol symbol2 = this.sym;
            Object obj = (symbol2.kind == Kinds.Kind.TYP && symbol2.type.hasTag(TypeTag.CLASS)) ? Resolve.this.types.erasure(this.sym.type).tsym : this.sym;
            Resolve resolve = Resolve.this;
            return resolve.diags.create(diagnosticType, resolve.log.currentSource(), diagnosticPosition, "cant.ref.before.ctor.called", obj);
        }
    }

    public abstract class ReferenceChooser {
        public ReferenceChooser() {
        }

        public abstract ReferenceLookupResult boundResult(ReferenceLookupResult referenceLookupResult);

        public ReferenceLookupResult result(ReferenceLookupResult referenceLookupResult, ReferenceLookupResult referenceLookupResult2) {
            return referenceLookupResult2 != Resolve.this.referenceNotFound ? unboundResult(referenceLookupResult, referenceLookupResult2) : boundResult(referenceLookupResult);
        }

        public abstract ReferenceLookupResult unboundResult(ReferenceLookupResult referenceLookupResult, ReferenceLookupResult referenceLookupResult2);
    }

    public abstract class ReferenceLookupHelper extends LookupHelper {
        JCTree.JCMemberReference referenceTree;

        public ReferenceLookupHelper(JCTree.JCMemberReference jCMemberReference, Name name, Type type, List<Type> list, List<Type> list2, MethodResolutionPhase methodResolutionPhase) {
            super(name, type, list, list2, methodResolutionPhase);
            this.referenceTree = jCMemberReference;
        }

        @Override // com.sun.tools.javac.comp.Resolve.LookupHelper
        public Symbol access(Env<AttrContext> env, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Symbol symbol2) {
            return symbol2;
        }

        public abstract JCTree.JCMemberReference.ReferenceKind referenceKind(Symbol symbol);

        public ReferenceLookupHelper unboundLookup(InferenceContext inferenceContext) {
            return null;
        }
    }

    public class ResolveDeferredRecoveryMap extends DeferredAttr.RecoveryDeferredTypeMap {
        /* JADX WARN: Illegal instructions before constructor call */
        public ResolveDeferredRecoveryMap(DeferredAttr.AttrMode attrMode, Symbol symbol, MethodResolutionPhase methodResolutionPhase) {
            DeferredAttr deferredAttr = Resolve.this.deferredAttr;
            Objects.requireNonNull(deferredAttr);
            super(attrMode, symbol, methodResolutionPhase);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sun.tools.javac.comp.DeferredAttr.RecoveryDeferredTypeMap, com.sun.tools.javac.comp.DeferredAttr.DeferredTypeMap
        public Type typeOf(DeferredAttr.DeferredType deferredType, Type type) {
            int i;
            Type typeTypeOf = super.typeOf(deferredType, type);
            return (typeTypeOf.isErroneous() || !((i = AnonymousClass19.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[TreeInfo.skipParens(deferredType.tree).getTag().ordinal()]) == 1 || i == 2 || (i == 3 && typeTypeOf == Type.recoveryType))) ? typeTypeOf : deferredType;
        }
    }

    public abstract class ResolveError extends Symbol {
        final String debugName;

        public ResolveError(Kinds.Kind kind, String str) {
            super(kind, 0L, null, null, null);
            this.debugName = str;
        }

        @Override // javax.lang.model.element.Element
        public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
            throw new AssertionError();
        }

        public Symbol access(Name name, Symbol.TypeSymbol typeSymbol) {
            Resolve resolve = Resolve.this;
            return resolve.types.createErrorType(name, typeSymbol, resolve.syms.errSymbol.type).tsym;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public boolean exists() {
            return false;
        }

        public abstract JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2);

        @Override // com.sun.tools.javac.code.Symbol
        public boolean isStatic() {
            return false;
        }

        @Override // com.sun.tools.javac.code.Symbol
        public String toString() {
            return this.debugName;
        }
    }

    public class UnboundMethodReferenceLookupHelper extends MethodReferenceLookupHelper {
        public UnboundMethodReferenceLookupHelper(JCTree.JCMemberReference jCMemberReference, Name name, Type type, List<Type> list, List<Type> list2, MethodResolutionPhase methodResolutionPhase) {
            super(jCMemberReference, name, type, list.tail, list2, methodResolutionPhase);
            if (!type.isRaw() || list.head.hasTag(TypeTag.NONE)) {
                return;
            }
            this.site = Resolve.this.types.skipTypeVars(Resolve.this.types.asSuper(list.head, type.tsym), true);
        }

        @Override // com.sun.tools.javac.comp.Resolve.MethodReferenceLookupHelper, com.sun.tools.javac.comp.Resolve.ReferenceLookupHelper
        public JCTree.JCMemberReference.ReferenceKind referenceKind(Symbol symbol) {
            return JCTree.JCMemberReference.ReferenceKind.UNBOUND;
        }

        @Override // com.sun.tools.javac.comp.Resolve.MethodReferenceLookupHelper, com.sun.tools.javac.comp.Resolve.ReferenceLookupHelper
        public ReferenceLookupHelper unboundLookup(InferenceContext inferenceContext) {
            return this;
        }
    }

    public enum VerboseResolutionMode {
        SUCCESS("success"),
        FAILURE("failure"),
        APPLICABLE("applicable"),
        INAPPLICABLE("inapplicable"),
        DEFERRED_INST("deferred-inference"),
        PREDEF("predef"),
        OBJECT_INIT("object-init"),
        INTERNAL("internal");

        final String opt;

        VerboseResolutionMode(String str) {
            this.opt = str;
        }

        public static EnumSet<VerboseResolutionMode> getVerboseResolutionMode(Options options) {
            String str = options.get("debug.verboseResolution");
            EnumSet<VerboseResolutionMode> enumSetNoneOf = EnumSet.noneOf(VerboseResolutionMode.class);
            if (str == null) {
                return enumSetNoneOf;
            }
            if (str.contains("all")) {
                enumSetNoneOf = EnumSet.allOf(VerboseResolutionMode.class);
            }
            java.util.List listAsList = Arrays.asList(str.split(","));
            for (VerboseResolutionMode verboseResolutionMode : values()) {
                if (listAsList.contains(verboseResolutionMode.opt)) {
                    enumSetNoneOf.add(verboseResolutionMode);
                } else {
                    if (listAsList.contains("-" + verboseResolutionMode.opt)) {
                        enumSetNoneOf.remove(verboseResolutionMode);
                    }
                }
            }
            return enumSetNoneOf;
        }
    }

    public Resolve(Context context) {
        context.put(resolveKey, this);
        this.syms = Symtab.instance(context);
        this.varNotFound = new SymbolNotFoundError(this, Kinds.Kind.ABSENT_VAR);
        SymbolNotFoundError symbolNotFoundError = new SymbolNotFoundError(this, Kinds.Kind.ABSENT_MTH);
        this.methodNotFound = symbolNotFoundError;
        this.typeNotFound = new SymbolNotFoundError(this, Kinds.Kind.ABSENT_TYP);
        this.referenceNotFound = ReferenceLookupResult.error(symbolNotFoundError);
        this.names = Names.instance(context);
        this.log = Log.instance(context);
        this.attr = Attr.instance(context);
        this.attrRecover = AttrRecover.instance(context);
        this.deferredAttr = DeferredAttr.instance(context);
        this.chk = Check.instance(context);
        this.infer = Infer.instance(context);
        this.finder = ClassFinder.instance(context);
        this.moduleFinder = ModuleFinder.instance(context);
        this.types = Types.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        this.preview = Preview.instance(context);
        Source sourceInstance = Source.instance(context);
        Options optionsInstance = Options.instance(context);
        Option option = Option.XDIAGS;
        this.compactMethodDiags = optionsInstance.isSet(option, "compact") || (optionsInstance.isUnset(option) && optionsInstance.isUnset("rawDiagnostics"));
        this.verboseResolutionMode = VerboseResolutionMode.getVerboseResolutionMode(optionsInstance);
        Target.instance(context);
        this.allowLocalVariableTypeInference = Source.Feature.LOCAL_VARIABLE_TYPE_INFERENCE.allowedInSource(sourceInstance);
        this.allowYieldStatement = Source.Feature.SWITCH_EXPRESSION.allowedInSource(sourceInstance);
        this.allowPrivateMembersInPermitsClause = Source.Feature.PRIVATE_MEMBERS_IN_PERMITS_CLAUSE.allowedInSource(sourceInstance);
        this.polymorphicSignatureScope = Scope.WriteableScope.create(this.syms.noSymbol);
        this.allowModules = Source.Feature.MODULES.allowedInSource(sourceInstance);
        this.allowRecords = Source.Feature.RECORDS.allowedInSource(sourceInstance);
        this.dumpMethodReferenceSearchResults = optionsInstance.isSet("debug.dumpMethodReferenceSearchResults");
        this.dumpStacktraceOnError = optionsInstance.isSet("dev") || optionsInstance.isSet(Option.DOE);
    }

    public static /* synthetic */ Symbol a(Resolve resolve, boolean z, Env env, final Name name) {
        resolve.getClass();
        Symbol symbolFindFirst = (z ? env.toplevel.moduleImportScope : env.toplevel.starImportScope).findFirst(Convert.shortName(name), new Predicate() { // from class: lgc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Resolve.c(name, (Symbol) obj);
            }
        });
        if (symbolFindFirst == null) {
            return null;
        }
        try {
            return resolve.new InvisibleSymbolError(env, true, resolve.finder.loadClass(symbolFindFirst.packge().modle, name));
        } catch (Symbol.CompletionFailure unused) {
            return null;
        }
    }

    public static /* synthetic */ boolean b(Symbol.PackageSymbol packageSymbol) {
        packageSymbol.complete();
        return !packageSymbol.members().isEmpty();
    }

    private static Symbol bestOf(Symbol symbol, Symbol symbol2) {
        return symbol.kind.betterThan(symbol2.kind) ? symbol : symbol2;
    }

    public static /* synthetic */ boolean c(Name name, Symbol symbol) {
        return symbol.kind == Kinds.Kind.TYP && symbol.flatName() == name;
    }

    private Symbol checkNonExistentType(Symbol symbol) {
        if (symbol instanceof Symbol.ClassSymbol) {
            Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbol;
            if (classSymbol.type.isErroneous() && classSymbol.classfile == null) {
                return this.typeNotFound;
            }
        }
        return symbol;
    }

    private Symbol checkRestrictedType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Name name) {
        Kinds.Kind kind = symbol.kind;
        if (kind == Kinds.Kind.TYP || kind == Kinds.Kind.ABSENT_TYP) {
            if (this.allowLocalVariableTypeInference && name.equals(this.names.var)) {
                return new BadRestrictedTypeError(this.names.var);
            }
            if (name.equals(this.names.yield)) {
                if (this.allowYieldStatement) {
                    return new BadRestrictedTypeError(this.names.yield);
                }
                if (diagnosticPosition != null) {
                    this.log.warning(diagnosticPosition, CompilerProperties.Warnings.IllegalRefToRestrictedType(this.names.yield));
                }
            }
        }
        return symbol;
    }

    private Symbol createInvisibleSymbolError(Env<AttrContext> env, Symbol symbol) {
        return symbolPackageVisible(env, symbol) ? new AccessError(env, null, symbol) : new InvisibleSymbolError(env, false, symbol);
    }

    public static /* synthetic */ Symbol d(Resolve resolve, Env env, final Name name) {
        resolve.getClass();
        Symbol symbolFindFirst = env.toplevel.namedImportScope.findFirst(Convert.shortName(name), new Predicate() { // from class: pgc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Resolve.g(name, (Symbol) obj);
            }
        });
        if (symbolFindFirst != null) {
            return resolve.new InvisibleSymbolError(env, true, symbolFindFirst);
        }
        return null;
    }

    private void dumpMethodReferenceSearchResults(JCTree.JCMemberReference jCMemberReference, MethodResolutionContext methodResolutionContext, Symbol symbol, boolean z) {
        ListBuffer listBuffer = new ListBuffer();
        int i = 0;
        int i2 = -1;
        for (MethodResolutionContext.Candidate candidate : methodResolutionContext.candidates) {
            if (methodResolutionContext.step == candidate.step && candidate.isApplicable()) {
                JCDiagnostic jCDiagnosticFragment = candidate.sym.type.hasTag(TypeTag.FORALL) ? this.diags.fragment(CompilerProperties.Fragments.PartialInstSig(candidate.mtype)) : null;
                listBuffer.append(this.diags.fragment(jCDiagnosticFragment == null ? "applicable.method.found.2" : "applicable.method.found.3", Integer.valueOf(i), candidate.sym.isStatic() ? CompilerProperties.Fragments.Static : CompilerProperties.Fragments.NonStatic, candidate.sym, jCDiagnosticFragment));
                if (candidate.sym == symbol) {
                    i2 = i;
                }
                i++;
            }
        }
        this.log.report(new JCDiagnostic.MultilineDiagnostic(this.diags.note(this.log.currentSource(), jCMemberReference, "method.ref.search.results.multi", z ? CompilerProperties.Fragments.Bound : CompilerProperties.Fragments.Unbound, jCMemberReference.toString(), Integer.valueOf(i2)), listBuffer.toList()));
    }

    public static /* synthetic */ boolean e(Symbol.PackageSymbol packageSymbol, Directive.ExportsDirective exportsDirective) {
        return exportsDirective.packge == packageSymbol;
    }

    public static /* synthetic */ Symbol f(Env env, Name name) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Symbol findDiamond(Env<AttrContext> env, Type type, List<Type> list, List<Type> list2, boolean z, boolean z2) {
        Symbol symbolSelectBest = this.methodNotFound;
        for (final Symbol symbol : (type.tsym.isInterface() ? this.syms.objectType.tsym : type.tsym).members().getSymbolsByName(this.names.init)) {
            symbolSelectBest = (symbol.kind == Kinds.Kind.MTH && (symbol.flags_field & 4096) == 0) ? selectBest(env, type, list, list2, new Symbol.MethodSymbol(this, symbol.flags(), this.names.init, new Type.ForAll(type.tsym.type.getTypeArguments().appendList(symbol.type.hasTag(TypeTag.FORALL) ? ((Type.ForAll) symbol.type).tvars : List.nil()), this.types.createMethodTypeWithReturn(symbol.type.asMethodType(), type)), type.tsym) { // from class: com.sun.tools.javac.comp.Resolve.16
                final /* synthetic */ Resolve this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.sun.tools.javac.code.Symbol
                public Symbol baseSymbol() {
                    return symbol;
                }
            }, symbolSelectBest, z, z2) : symbolSelectBest;
        }
        return symbolSelectBest;
    }

    private Symbol findMethod(Env<AttrContext> env, Type type, Name name, List<Type> list, List<Type> list2, Type type2, Symbol symbol, boolean z, boolean z2) {
        List<Type>[] listArr = {List.nil(), List.nil()};
        InterfaceLookupPhase interfaceLookupPhaseUpdate = InterfaceLookupPhase.ABSTRACT_OK;
        Type type3 = type;
        boolean zIsInterface = type3.tsym.isInterface();
        Symbol symbolFindMethodInScope = symbol;
        for (Symbol.TypeSymbol typeSymbol : zIsInterface ? List.of(type2.tsym) : superclasses(type2)) {
            InterfaceLookupPhase interfaceLookupPhase = interfaceLookupPhaseUpdate;
            List<Type>[] listArr2 = listArr;
            symbolFindMethodInScope = findMethodInScope(env, type3, name, list, list2, typeSymbol.members(), symbolFindMethodInScope, z, z2, true);
            if (name == this.names.init) {
                return symbolFindMethodInScope;
            }
            interfaceLookupPhaseUpdate = interfaceLookupPhase == null ? null : interfaceLookupPhase.update(typeSymbol, this);
            if (interfaceLookupPhaseUpdate != null) {
                for (Type type4 : this.types.interfaces(typeSymbol.type)) {
                    int iOrdinal = interfaceLookupPhaseUpdate.ordinal();
                    Types types = this.types;
                    listArr2[iOrdinal] = types.union(types.closure(type4), listArr2[interfaceLookupPhaseUpdate.ordinal()]);
                }
            }
            type3 = type;
            listArr = listArr2;
        }
        List<Type>[] listArr3 = listArr;
        Symbol symbol2 = (symbolFindMethodInScope.kind.isValid() && (symbolFindMethodInScope.flags() & 1024) == 0) ? symbolFindMethodInScope : this.methodNotFound;
        InterfaceLookupPhase[] interfaceLookupPhaseArrValues = InterfaceLookupPhase.values();
        int length = interfaceLookupPhaseArrValues.length;
        int i = 0;
        while (i < length) {
            InterfaceLookupPhase interfaceLookupPhase2 = interfaceLookupPhaseArrValues[i];
            for (Type type5 : listArr3[interfaceLookupPhase2.ordinal()]) {
                if (type5.isInterface() && (interfaceLookupPhase2 != InterfaceLookupPhase.DEFAULT_OK || (type5.tsym.flags() & Flags.DEFAULT) != 0)) {
                    InterfaceLookupPhase[] interfaceLookupPhaseArr = interfaceLookupPhaseArrValues;
                    int i2 = length;
                    int i3 = i;
                    InterfaceLookupPhase interfaceLookupPhase3 = interfaceLookupPhase2;
                    Symbol symbolFindMethodInScope2 = findMethodInScope(env, type, name, list, list2, type5.tsym.members(), symbolFindMethodInScope, z, z2, true);
                    symbolFindMethodInScope = (symbol2 != symbolFindMethodInScope2 && symbol2.kind.isValid() && symbolFindMethodInScope2.kind.isValid() && this.types.isSubSignature(symbol2.type, symbolFindMethodInScope2.type)) ? symbol2 : symbolFindMethodInScope2;
                    name = name;
                    interfaceLookupPhaseArrValues = interfaceLookupPhaseArr;
                    length = i2;
                    i = i3;
                    interfaceLookupPhase2 = interfaceLookupPhase3;
                }
            }
            i++;
        }
        if (!zIsInterface || !symbolFindMethodInScope.kind.isResolutionError()) {
            return symbolFindMethodInScope;
        }
        final Symbol symbolFindMethodInScope3 = findMethodInScope(env, type, name, list, list2, this.syms.objectType.tsym.members(), symbolFindMethodInScope, z, z2, true);
        return symbolFindMethodInScope3.kind.isValid() ? new Symbol.MethodSymbol(this, symbolFindMethodInScope3.flags_field, symbolFindMethodInScope3.name, symbolFindMethodInScope3.type, type2.tsym) { // from class: com.sun.tools.javac.comp.Resolve.5
            final /* synthetic */ Resolve this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.code.Symbol
            public Symbol baseSymbol() {
                return symbolFindMethodInScope3;
            }
        } : symbolFindMethodInScope3;
    }

    public static /* synthetic */ boolean g(Name name, Symbol symbol) {
        return symbol.kind == Kinds.Kind.TYP && symbol.flatName() == name;
    }

    public static /* synthetic */ boolean h(Resolve resolve, Env env, boolean z, Type type) {
        resolve.getClass();
        return resolve.isAccessible((Env<AttrContext>) env, type.tsym, z);
    }

    public static /* synthetic */ Iterator i(Resolve resolve, Type type) {
        resolve.getClass();
        return new Iterator<Symbol.TypeSymbol>(resolve, type) { // from class: com.sun.tools.javac.comp.Resolve.6
            Symbol.TypeSymbol currentSym;
            final /* synthetic */ Resolve this$0;
            final /* synthetic */ Type val$intype;
            List<Symbol.TypeSymbol> seen = List.nil();
            Symbol.TypeSymbol prevSym = null;

            {
                this.val$intype = type;
                this.this$0 = resolve;
                this.currentSym = symbolFor(type);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                Symbol.TypeSymbol typeSymbol = this.currentSym;
                Resolve resolve2 = this.this$0;
                if (typeSymbol == resolve2.syms.noSymbol) {
                    this.currentSym = symbolFor(resolve2.types.supertype(this.prevSym.type));
                }
                return this.currentSym != null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Symbol.TypeSymbol next() {
                Symbol.TypeSymbol typeSymbol = this.currentSym;
                this.prevSym = typeSymbol;
                Symbol.TypeSymbol typeSymbol2 = this.this$0.syms.noSymbol;
                this.currentSym = typeSymbol2;
                Assert.check((typeSymbol == null && typeSymbol == typeSymbol2) ? false : true);
                return this.prevSym;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            public Symbol.TypeSymbol symbolFor(Type type2) {
                if (!type2.hasTag(TypeTag.CLASS) && !type2.hasTag(TypeTag.TYPEVAR)) {
                    return null;
                }
                Type typeSkipTypeVars = this.this$0.types.skipTypeVars(type2, false);
                if (this.seen.contains(typeSkipTypeVars.tsym)) {
                    return null;
                }
                this.seen = this.seen.prepend(typeSkipTypeVars.tsym);
                return typeSkipTypeVars.tsym;
            }
        };
    }

    public static Resolve instance(Context context) {
        Resolve resolve = (Resolve) context.get(resolveKey);
        return resolve == null ? new Resolve(context) : resolve;
    }

    private boolean isAllowedEarlyReference(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Symbol.VarSymbol varSymbol) {
        JCTree.JCExpression jCExpression;
        Assert.check(env.info.ctorPrologue);
        Assert.check((varSymbol.flags_field & 8) == 0);
        JCTree jCTree = env.tree;
        if (!(jCTree instanceof JCTree.JCAssign)) {
            return false;
        }
        JCTree.JCAssign jCAssign = (JCTree.JCAssign) jCTree;
        if (env.info.isLambda) {
            return false;
        }
        JCTree.JCExpression jCExpressionSkipParens = TreeInfo.skipParens(jCAssign.lhs);
        int i = AnonymousClass19.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpressionSkipParens.getTag().ordinal()];
        if (i == 4) {
            jCExpression = null;
        } else {
            if (i != 5) {
                return false;
            }
            jCExpression = ((JCTree.JCFieldAccess) jCExpressionSkipParens).selected;
            if (!TreeInfo.isExplicitThisReference(this.types, (Type.ClassType) env.enclClass.type, jCExpression)) {
                return false;
            }
        }
        if (isEarlyReference(env, jCExpression, varSymbol) && varSymbol.owner != env.enclClass.sym) {
            return false;
        }
        this.preview.checkSourceLevel(diagnosticPosition, Source.Feature.FLEXIBLE_CONSTRUCTORS);
        return true;
    }

    private boolean isImportOnDemand(Env<AttrContext> env, Name name) {
        if (!env.tree.hasTag(JCTree.Tag.IMPORT)) {
            return false;
        }
        JCTree.JCFieldAccess jCFieldAccess = ((JCTree.JCImport) env.tree).qualid;
        return jCFieldAccess.hasTag(JCTree.Tag.SELECT) && TreeInfo.name(jCFieldAccess) == this.names.asterisk && TreeInfo.fullName(jCFieldAccess.selected) == name;
    }

    public static boolean isInitializer(Env<AttrContext> env) {
        Symbol symbol = env.info.scope.owner;
        if (symbol.isConstructor()) {
            return true;
        }
        if (symbol.owner.kind != Kinds.Kind.TYP) {
            return false;
        }
        Kinds.Kind kind = symbol.kind;
        return (kind == Kinds.Kind.VAR || (kind == Kinds.Kind.MTH && (symbol.flags() & 1048576) != 0)) && (symbol.flags() & 8) == 0;
    }

    private boolean isInnerSubClass(Symbol.ClassSymbol classSymbol, Symbol symbol) {
        while (classSymbol != null && !classSymbol.isSubClass(symbol, this.types)) {
            classSymbol = classSymbol.owner.enclClass();
        }
        return classSymbol != null;
    }

    private boolean isProtectedAccessible(Symbol symbol, Symbol.ClassSymbol classSymbol, Type type) {
        if (type.hasTag(TypeTag.TYPEVAR)) {
            type = type.getUpperBound();
        }
        while (classSymbol != null && (!classSymbol.isSubClass(symbol.owner, this.types) || (classSymbol.flags() & 512) != 0 || ((symbol.flags() & 8) == 0 && symbol.kind != Kinds.Kind.TYP && !type.tsym.isSubClass(classSymbol, this.types)))) {
            classSymbol = classSymbol.owner.enclClass();
        }
        return classSymbol != null;
    }

    private boolean isReceiverParameter(Env<AttrContext> env, JCTree.JCFieldAccess jCFieldAccess) {
        JCTree.JCVariableDecl jCVariableDecl;
        return env.tree.getTag() == JCTree.Tag.METHODDEF && (jCVariableDecl = ((JCTree.JCMethodDecl) env.tree).recvparam) != null && jCFieldAccess == jCVariableDecl.nameexpr;
    }

    public static boolean isStatic(Env<AttrContext> env) {
        Env<AttrContext> env2 = env.outer;
        return env2 != null && env.info.staticLevel > env2.info.staticLevel;
    }

    private void logResolveError(ResolveError resolveError, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
        JCDiagnostic diagnostic = resolveError.getDiagnostic(JCDiagnostic.DiagnosticType.ERROR, diagnosticPosition, symbol, type, name, list, list2);
        if (diagnostic != null) {
            diagnostic.setFlag(JCDiagnostic.DiagnosticFlag.RESOLVE_ERROR);
            this.log.report(diagnostic);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <S extends Symbol> Symbol lookupInvisibleSymbol(Env<AttrContext> env, Name name, Function<Name, Iterable<S>> function, BiFunction<Symbol.ModuleSymbol, Name, S> biFunction, Predicate<S> predicate, Symbol symbol) {
        S sApply;
        for (S s : function.apply(name)) {
            if (predicate.test(s)) {
                return createInvisibleSymbolError(env, s);
            }
        }
        HashSet<Symbol.ModuleSymbol> hashSet = new HashSet(this.syms.getAllModules());
        hashSet.add(this.syms.unnamedModule);
        hashSet.remove(env.toplevel.modle);
        for (Symbol.ModuleSymbol moduleSymbolFindModule : hashSet) {
            if (moduleSymbolFindModule.sourceLocation == null) {
                if (moduleSymbolFindModule.classLocation == null) {
                    moduleSymbolFindModule = this.moduleFinder.findModule(moduleSymbolFindModule);
                }
                if (moduleSymbolFindModule.kind != Kinds.Kind.ERR && (sApply = biFunction.apply(moduleSymbolFindModule, name)) != null && predicate.test(sApply)) {
                    return createInvisibleSymbolError(env, sApply);
                }
            }
        }
        return symbol;
    }

    private boolean notOverriddenIn(Type type, Symbol symbol) {
        Symbol.MethodSymbol methodSymbolImplementation;
        Symbol symbol2;
        if (symbol.kind == Kinds.Kind.MTH && !symbol.isConstructor() && !symbol.isStatic() && (methodSymbolImplementation = ((Symbol.MethodSymbol) symbol).implementation(type.tsym, this.types, true)) != null && methodSymbolImplementation != symbol && (symbol2 = symbol.owner) != methodSymbolImplementation.owner && (!symbol2.isInterface() || methodSymbolImplementation.owner != this.syms.objectType.tsym)) {
            Types types = this.types;
            if (types.isSubSignature(types.memberType(type, methodSymbolImplementation), this.types.memberType(type, symbol))) {
                return false;
            }
        }
        return true;
    }

    private RecoveryLoadClass onDemandImportScopeRecovery(final boolean z) {
        return new RecoveryLoadClass() { // from class: jgc
            @Override // com.sun.tools.javac.comp.Resolve.RecoveryLoadClass
            public final Symbol loadClass(Env env, Name name) {
                return Resolve.a(this.a, z, env, name);
            }
        };
    }

    private boolean privateMemberInPermitsClauseIfAllowed(Env<AttrContext> env, Symbol symbol) {
        return this.allowPrivateMembersInPermitsClause && env.info.isPermitsClause && ((JCTree.JCClassDecl) env.tree).sym.outermostClass() == symbol.owner.outermostClass();
    }

    private List<Type> pruneInterfaces(Type type) {
        ListBuffer listBuffer = new ListBuffer();
        for (Type type2 : this.types.interfaces(type)) {
            boolean z = true;
            for (Type type3 : this.types.directSupertypes(type)) {
                if (type2 != type3 && !type3.hasTag(TypeTag.ERROR) && this.types.isSubtypeNoCapture(type3, type2)) {
                    z = false;
                }
            }
            if (z) {
                listBuffer.append(type2);
            }
        }
        return listBuffer.toList();
    }

    private Symbol resolveConstructor(MethodResolutionContext methodResolutionContext, final JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Type type, List<Type> list, List<Type> list2) {
        return lookupMethod(env, diagnosticPosition, type.tsym, methodResolutionContext, new BasicLookupHelper(this, this.names.init, type, list, list2) { // from class: com.sun.tools.javac.comp.Resolve.14
            final /* synthetic */ Resolve this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.comp.Resolve.LookupHelper
            public Symbol lookup(Env<AttrContext> env2, MethodResolutionPhase methodResolutionPhase) {
                return this.this$0.findConstructor(diagnosticPosition, env2, this.site, this.argtypes, this.typeargtypes, methodResolutionPhase.isBoxingRequired(), methodResolutionPhase.isVarargsRequired());
            }
        });
    }

    private boolean signatureMoreSpecific(List<Type> list, Env<AttrContext> env, Type type, Symbol symbol, Symbol symbol2, boolean z) {
        this.noteWarner.clear();
        int iMax = Math.max(Math.max(symbol.type.mo71getParameterTypes().length(), list.length()), symbol2.type.mo71getParameterTypes().length());
        MethodResolutionContext methodResolutionContext = this.currentResolutionContext;
        try {
            MethodResolutionContext methodResolutionContext2 = new MethodResolutionContext();
            this.currentResolutionContext = methodResolutionContext2;
            methodResolutionContext2.step = methodResolutionContext.step;
            methodResolutionContext2.methodCheck = methodResolutionContext.methodCheck.mostSpecificCheck(list);
            Types types = this.types;
            return (instantiate(env, type, symbol2, null, adjustArgs(types.cvarLowerBounds(types.memberType(type, symbol).mo71getParameterTypes()), symbol, iMax, z), null, false, z, this.noteWarner) == null || this.noteWarner.hasLint(Lint.LintCategory.UNCHECKED)) ? false : true;
        } finally {
            this.currentResolutionContext = methodResolutionContext;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean symbolPackageVisible(Env<AttrContext> env, Symbol symbol) {
        Symbol.ModuleSymbol moduleSymbol = env.toplevel.modle;
        Symbol.PackageSymbol packageSymbolPackge = symbol.packge();
        return moduleSymbol == packageSymbolPackge.modle || moduleSymbol.visiblePackages.containsKey(packageSymbolPackge.fullname);
    }

    public Symbol accessBase(Symbol symbol, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol2, Type type, Name name, boolean z) {
        return accessInternal(symbol, diagnosticPosition, symbol2, type, name, z, List.nil(), null, this.basicLogResolveHelper);
    }

    public Symbol accessInternal(Symbol symbol, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol2, Type type, Name name, boolean z, List<Type> list, List<Type> list2, LogResolveHelper logResolveHelper) {
        if (!symbol.kind.isResolutionError()) {
            return symbol;
        }
        ResolveError resolveError = (ResolveError) symbol.baseSymbol();
        Symbol symbolAccess = resolveError.access(name, z ? type.tsym : this.syms.noSymbol);
        List<Type> argumentTypes = logResolveHelper.getArgumentTypes(resolveError, symbolAccess, name, list);
        if (logResolveHelper.resolveDiagnosticNeeded(type, argumentTypes, list2)) {
            logResolveError(resolveError, diagnosticPosition, symbol2, type, name, argumentTypes, list2);
        }
        return symbolAccess;
    }

    public Symbol accessMethod(Symbol symbol, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol2, Type type, Name name, boolean z, List<Type> list, List<Type> list2) {
        return accessInternal(symbol, diagnosticPosition, symbol2, type, name, z, list, list2, this.methodLogResolveHelper);
    }

    public List<Type> adjustArgs(List<Type> list, Symbol symbol, int i, boolean z) {
        if ((symbol.flags() & Flags.VARARGS) == 0 || !z) {
            return list;
        }
        Type typeElemtype = this.types.elemtype(list.last());
        if (typeElemtype == null) {
            Assert.error("Bad varargs = " + list.last() + " " + symbol);
        }
        List<Type> listReverse = list.reverse().tail.prepend(typeElemtype).reverse();
        while (listReverse.length() < i) {
            listReverse = listReverse.append(listReverse.last());
        }
        return listReverse;
    }

    public Symbol ambiguityError(Symbol symbol, Symbol symbol2) {
        if (((symbol.flags() | symbol2.flags()) & Flags.CLASH) != 0) {
            return (symbol.flags() & Flags.CLASH) == 0 ? symbol : symbol2;
        }
        return new AmbiguityError(symbol, symbol2);
    }

    public void checkAccessibleType(Env<AttrContext> env, Type type) {
        this.accessibilityChecker.visit(type, env);
    }

    public Type checkMethod(Env<AttrContext> env, Type type, Symbol symbol, Attr.ResultInfo resultInfo, List<Type> list, List<Type> list2, Warner warner) {
        MethodResolutionContext methodResolutionContext = this.currentResolutionContext;
        try {
            MethodResolutionContext methodResolutionContext2 = new MethodResolutionContext();
            this.currentResolutionContext = methodResolutionContext2;
            methodResolutionContext2.attrMode = resultInfo.pt == Infer.anyPoly ? DeferredAttr.AttrMode.SPECULATIVE : DeferredAttr.AttrMode.CHECK;
            if (env.tree.hasTag(JCTree.Tag.REFERENCE)) {
                this.currentResolutionContext.methodCheck = new MethodReferenceCheck(resultInfo.checkContext.inferenceContext());
            }
            MethodResolutionContext methodResolutionContext3 = this.currentResolutionContext;
            MethodResolutionPhase methodResolutionPhase = env.info.pendingResolutionPhase;
            methodResolutionContext3.step = methodResolutionPhase;
            return rawInstantiate(env, type, symbol, resultInfo, list, list2, methodResolutionPhase.isBoxingRequired(), methodResolutionPhase.isVarargsRequired(), warner);
        } finally {
            this.currentResolutionContext = methodResolutionContext;
        }
    }

    public void checkNonAbstract(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
        if ((symbol.flags() & 1024) == 0 || (symbol.flags() & Flags.DEFAULT) != 0) {
            return;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.AbstractCantBeAccessedDirectly(Kinds.kindName(symbol), symbol, symbol.location()));
    }

    public Symbol findConstructor(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Type type, List<Type> list, List<Type> list2, boolean z, boolean z2) {
        Symbol symbolFindMethod = findMethod(env, type, this.names.init, list, list2, z, z2);
        this.chk.checkDeprecated(diagnosticPosition, env.info.scope.owner, symbolFindMethod);
        this.chk.checkPreview(diagnosticPosition, env.info.scope.owner, symbolFindMethod);
        return symbolFindMethod;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Symbol findField(Env<AttrContext> env, Type type, Name name, Symbol.TypeSymbol typeSymbol) {
        while (typeSymbol.type.hasTag(TypeTag.TYPEVAR)) {
            typeSymbol = typeSymbol.type.getUpperBound().tsym;
        }
        Symbol ambiguityError = this.varNotFound;
        for (Symbol symbol : typeSymbol.members().getSymbolsByName(name)) {
            if (symbol.kind == Kinds.Kind.VAR && (symbol.flags_field & 4096) == 0) {
                return isAccessible(env, type, symbol) ? symbol : new AccessError(env, type, symbol);
            }
        }
        Type typeSupertype = this.types.supertype(typeSymbol.type);
        if (typeSupertype != null && (typeSupertype.hasTag(TypeTag.CLASS) || typeSupertype.hasTag(TypeTag.TYPEVAR))) {
            ambiguityError = bestOf(ambiguityError, findField(env, type, name, typeSupertype.tsym));
        }
        for (List listInterfaces = this.types.interfaces(typeSymbol.type); ambiguityError.kind != Kinds.Kind.AMBIGUOUS && listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
            Symbol symbolFindField = findField(env, type, name, ((Type) listInterfaces.head).tsym);
            ambiguityError = (ambiguityError.exists() && symbolFindField.exists() && symbolFindField.owner != ambiguityError.owner) ? new AmbiguityError(ambiguityError, symbolFindField) : bestOf(ambiguityError, symbolFindField);
        }
        return ambiguityError;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Symbol findFun(Env<AttrContext> env, Name name, List<Type> list, List<Type> list2, boolean z, boolean z2) {
        Symbol refBeforeCtorCalledError;
        Symbol symbolBestOf = this.methodNotFound;
        boolean z3 = false;
        for (Env env2 = env; env2.outer != null; env2 = env2.outer) {
            boolean z4 = isStatic(env2) ? true : z3;
            Assert.check(((AttrContext) env2.info).preferredTreeForDiagnostics == null);
            ((AttrContext) env2.info).preferredTreeForDiagnostics = env.tree;
            try {
                Symbol symbolFindMethod = findMethod(env2, env2.enclClass.sym.type, name, list, list2, z, z2);
                if (symbolFindMethod.exists()) {
                    if (symbolFindMethod.kind == Kinds.Kind.MTH && symbolFindMethod.owner.kind == Kinds.Kind.TYP && (8 & symbolFindMethod.flags()) == 0) {
                        if (z4) {
                            refBeforeCtorCalledError = new StaticError(this, symbolFindMethod);
                        } else if (((AttrContext) env2.info).ctorPrologue && env2 == env) {
                            refBeforeCtorCalledError = new RefBeforeCtorCalledError(symbolFindMethod);
                        }
                        ((AttrContext) env2.info).preferredTreeForDiagnostics = null;
                        return refBeforeCtorCalledError;
                    }
                    ((AttrContext) env2.info).preferredTreeForDiagnostics = null;
                    return symbolFindMethod;
                }
                symbolBestOf = bestOf(symbolBestOf, symbolFindMethod);
                ((AttrContext) env2.info).preferredTreeForDiagnostics = null;
                z3 = (env2.enclClass.sym.flags() & 8) != 0 ? true : z4;
            } catch (Throwable th) {
                ((AttrContext) env2.info).preferredTreeForDiagnostics = null;
                throw th;
            }
        }
        Symbol symbolFindMethod2 = findMethod(env, this.syms.predefClass.type, name, list, list2, z, z2);
        if (symbolFindMethod2.exists()) {
            return symbolFindMethod2;
        }
        Iterator<Symbol> it = env.toplevel.namedImportScope.getSymbolsByName(name).iterator();
        Symbol symbolSelectBest = symbolBestOf;
        while (it.hasNext()) {
            Symbol next = it.next();
            Symbol symbol = env.toplevel.namedImportScope.getOrigin(next).owner;
            if (next.kind == Kinds.Kind.MTH) {
                if (next.owner.type != symbol.type) {
                    next = next.clone(symbol);
                }
                symbolSelectBest = selectBest(env, symbol.type, list, list2, !isAccessible(env, symbol.type, next) ? new AccessError(env, symbol.type, next) : next, symbolSelectBest, z, z2);
            }
        }
        if (symbolSelectBest.exists()) {
            return symbolSelectBest;
        }
        Iterator<Symbol> it2 = env.toplevel.starImportScope.getSymbolsByName(name).iterator();
        while (it2.hasNext()) {
            Symbol next2 = it2.next();
            Symbol symbol2 = env.toplevel.starImportScope.getOrigin(next2).owner;
            if (next2.kind == Kinds.Kind.MTH) {
                if (next2.owner.type != symbol2.type) {
                    next2 = next2.clone(symbol2);
                }
                symbolSelectBest = selectBest(env, symbol2.type, list, list2, !isAccessible(env, symbol2.type, next2) ? new AccessError(env, symbol2.type, next2) : next2, symbolSelectBest, z, z2);
            }
        }
        return symbolSelectBest;
    }

    public Symbol findGlobalType(Env<AttrContext> env, Scope scope, Name name, RecoveryLoadClass recoveryLoadClass) {
        Symbol symbolBestOf = this.typeNotFound;
        Iterator<Symbol> it = scope.getSymbolsByName(name).iterator();
        while (it.hasNext()) {
            Symbol symbolLoadClass = loadClass(env, it.next().flatName(), recoveryLoadClass);
            Kinds.Kind kind = symbolBestOf.kind;
            Kinds.Kind kind2 = Kinds.Kind.TYP;
            if (kind == kind2 && symbolLoadClass.kind == kind2 && symbolBestOf != symbolLoadClass) {
                return new AmbiguityError(symbolBestOf, symbolLoadClass);
            }
            symbolBestOf = bestOf(symbolBestOf, symbolLoadClass);
        }
        return symbolBestOf;
    }

    public Symbol findIdent(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Name name, Kinds.KindSelector kindSelector) {
        try {
            return checkNonExistentType(checkRestrictedType(diagnosticPosition, findIdentInternal(diagnosticPosition, env, name, kindSelector), name));
        } catch (ClassFinder.BadClassFile e) {
            return new BadClassFileError(e);
        } catch (Symbol.CompletionFailure e2) {
            this.chk.completionError(diagnosticPosition, e2);
            return this.typeNotFound;
        }
    }

    public Symbol findIdentInPackage(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Symbol.TypeSymbol typeSymbol, Name name, Kinds.KindSelector kindSelector) {
        return checkNonExistentType(checkRestrictedType(diagnosticPosition, findIdentInPackageInternal(env, typeSymbol, name, kindSelector), name));
    }

    public Symbol findIdentInPackageInternal(Env<AttrContext> env, Symbol.TypeSymbol typeSymbol, Name name, Kinds.KindSelector kindSelector) {
        Name nameFormFullName = Symbol.TypeSymbol.formFullName(name, typeSymbol);
        Symbol symbolBestOf = this.typeNotFound;
        if (kindSelector.contains(Kinds.KindSelector.TYP)) {
            Symbol symbolLoadClass = loadClass(env, nameFormFullName, (!this.allowModules || kindSelector.contains(Kinds.KindSelector.PCK) || typeSymbol.exists() || env.info.attributionMode.isSpeculative) ? this.noRecovery : this.doRecoveryLoadClass);
            if (!symbolLoadClass.exists()) {
                symbolBestOf = bestOf(symbolBestOf, symbolLoadClass);
            } else if (name == symbolLoadClass.name) {
                return symbolLoadClass;
            }
        }
        return kindSelector.contains(Kinds.KindSelector.PCK) ? lookupPackage(env, nameFormFullName) : symbolBestOf;
    }

    public Symbol findIdentInType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Type type, Name name, Kinds.KindSelector kindSelector) {
        try {
            return checkNonExistentType(checkRestrictedType(diagnosticPosition, findIdentInTypeInternal(env, type, name, kindSelector), name));
        } catch (ClassFinder.BadClassFile e) {
            return new BadClassFileError(e);
        } catch (Symbol.CompletionFailure e2) {
            this.chk.completionError(diagnosticPosition, e2);
            return this.typeNotFound;
        }
    }

    public Symbol findIdentInTypeInternal(Env<AttrContext> env, Type type, Name name, Kinds.KindSelector kindSelector) {
        Symbol symbolBestOf = this.typeNotFound;
        if (kindSelector.contains(Kinds.KindSelector.VAL)) {
            Symbol symbolFindField = findField(env, type, name, type.tsym);
            if (symbolFindField.exists()) {
                return symbolFindField;
            }
            symbolBestOf = bestOf(symbolBestOf, symbolFindField);
        }
        if (!kindSelector.contains(Kinds.KindSelector.TYP)) {
            return symbolBestOf;
        }
        Symbol symbolFindMemberType = findMemberType(env, type, name, type.tsym);
        return symbolFindMemberType.exists() ? symbolFindMemberType : bestOf(symbolBestOf, symbolFindMemberType);
    }

    public Symbol findIdentInternal(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Name name, Kinds.KindSelector kindSelector) {
        Symbol symbolBestOf = this.typeNotFound;
        if (kindSelector.contains(Kinds.KindSelector.VAL)) {
            Symbol symbolFindVar = findVar(diagnosticPosition, env, name);
            if (symbolFindVar.exists()) {
                return symbolFindVar;
            }
            symbolBestOf = bestOf(symbolBestOf, symbolFindVar);
        }
        if (kindSelector.contains(Kinds.KindSelector.TYP)) {
            Symbol symbolFindType = findType(env, name);
            if (symbolFindType.exists()) {
                return symbolFindType;
            }
            symbolBestOf = bestOf(symbolBestOf, symbolFindType);
        }
        return kindSelector.contains(Kinds.KindSelector.PCK) ? lookupPackage(env, name) : symbolBestOf;
    }

    public Symbol findImmediateMemberType(Env<AttrContext> env, Type type, Name name, Symbol.TypeSymbol typeSymbol) {
        for (Symbol symbol : typeSymbol.members().getSymbolsByName(name)) {
            if (symbol.kind == Kinds.Kind.TYP) {
                return isAccessible(env, type, symbol) ? symbol : new AccessError(env, type, symbol);
            }
        }
        return this.typeNotFound;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Symbol findInheritedMemberType(Env<AttrContext> env, Type type, Name name, Symbol.TypeSymbol typeSymbol) {
        Symbol symbolBestOf = this.typeNotFound;
        Type typeSupertype = this.types.supertype(typeSymbol.type);
        if (typeSupertype != null && typeSupertype.hasTag(TypeTag.CLASS)) {
            symbolBestOf = bestOf(symbolBestOf, findMemberType(env, type, name, typeSupertype.tsym));
        }
        for (List listInterfaces = this.types.interfaces(typeSymbol.type); symbolBestOf.kind != Kinds.Kind.AMBIGUOUS && listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
            Symbol symbolFindMemberType = findMemberType(env, type, name, ((Type) listInterfaces.head).tsym);
            symbolBestOf = (symbolBestOf.kind.isResolutionError() || symbolFindMemberType.kind.isResolutionError() || symbolFindMemberType.owner == symbolBestOf.owner) ? bestOf(symbolBestOf, symbolFindMemberType) : new AmbiguityError(symbolBestOf, symbolFindMemberType);
        }
        return symbolBestOf;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Symbol findLocalClassOwner(Env<AttrContext> env, Symbol.TypeSymbol typeSymbol) {
        Symbol symbol = typeSymbol.owner;
        Kinds.Kind kind = symbol.kind;
        boolean z = false;
        Assert.check(kind == Kinds.Kind.MTH || kind == Kinds.Kind.VAR);
        for (Env env2 = env; env2.outer != null; env2 = env2.outer) {
            if (((AttrContext) env2.info).scope.owner == symbol) {
                return z ? new BadLocalClassCreation(typeSymbol) : symbol;
            }
            if (isStatic(env2)) {
                z = true;
            }
        }
        return symbol.kind == Kinds.Kind.MTH ? this.methodNotFound : this.varNotFound;
    }

    public Symbol findMemberType(Env<AttrContext> env, Type type, Name name, Symbol.TypeSymbol typeSymbol) {
        Symbol symbolFindImmediateMemberType = findImmediateMemberType(env, type, name, typeSymbol);
        return symbolFindImmediateMemberType != this.typeNotFound ? symbolFindImmediateMemberType : findInheritedMemberType(env, type, name, typeSymbol);
    }

    public Symbol findMethodInScope(Env<AttrContext> env, Type type, Name name, List<Type> list, List<Type> list2, Scope scope, Symbol symbol, boolean z, boolean z2, boolean z3) {
        Iterator<Symbol> it = scope.getSymbolsByName(name, new LookupFilter(z3)).iterator();
        Symbol symbolSelectBest = symbol;
        while (it.hasNext()) {
            symbolSelectBest = selectBest(env, type, list, list2, it.next(), symbolSelectBest, z, z2);
        }
        return symbolSelectBest;
    }

    public Symbol findPolymorphicSignatureInstance(final Symbol symbol, Type type) {
        for (Symbol symbol2 : this.polymorphicSignatureScope.getSymbolsByName(symbol.name)) {
            if (this.types.isSameType(type, symbol2.type) && symbol.owner == symbol2.owner) {
                return symbol2;
            }
        }
        Type typeMo73getReturnType = symbol.asType().mo73getReturnType();
        Type methodType = (this.types.isSameType(typeMo73getReturnType, this.syms.objectType) || this.types.isSameType(typeMo73getReturnType, type.mo73getReturnType())) ? type : new Type.MethodType(type.mo71getParameterTypes(), typeMo73getReturnType, type.mo74getThrownTypes(), this.syms.methodClass);
        Symbol.MethodSymbol methodSymbol = new Symbol.MethodSymbol(this, (symbol.flags() & 15) | 137438954496L, symbol.name, methodType, symbol.owner) { // from class: com.sun.tools.javac.comp.Resolve.13
            final /* synthetic */ Resolve this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.code.Symbol
            public Symbol baseSymbol() {
                return symbol;
            }
        };
        if (!methodType.isErroneous()) {
            this.polymorphicSignatureScope.enter(methodSymbol);
        }
        return methodSymbol;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Symbol findSelfContaining(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Symbol.TypeSymbol typeSymbol, boolean z) {
        Symbol symbolFindFirst;
        Env<AttrContext> env2 = env;
        if (z) {
            env2 = env.outer;
        }
        boolean z2 = false;
        for (Env env3 = env2; env3.outer != null; env3 = env3.outer) {
            if (isStatic(env3)) {
                z2 = true;
            }
            if (env3.enclClass.sym.isSubClass(typeSymbol, this.types) && (symbolFindFirst = ((AttrContext) env3.info).scope.findFirst(this.names._this)) != null) {
                if (z2) {
                    return new StaticError(this, symbolFindFirst);
                }
                return (!((AttrContext) env3.info).ctorPrologue || isAllowedEarlyReference(diagnosticPosition, env3, (Symbol.VarSymbol) symbolFindFirst)) ? symbolFindFirst : new RefBeforeCtorCalledError(symbolFindFirst);
            }
            if ((env3.enclClass.sym.flags() & 8) != 0) {
                z2 = true;
            }
        }
        return this.varNotFound;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Symbol findType(Env<AttrContext> env, Name name) {
        Name name2 = this.names.empty;
        Symbol symbolBestOf = this.typeNotFound;
        if (name == name2) {
            return symbolBestOf;
        }
        boolean z = false;
        for (Env env2 = env; env2.outer != null; env2 = env2.outer) {
            Symbol symbolFindTypeVar = findTypeVar(env2, name, z);
            if (isStatic(env2)) {
                z = true;
            }
            Symbol.ClassSymbol classSymbol = env2.enclClass.sym;
            Symbol symbolFindImmediateMemberType = findImmediateMemberType(env2, classSymbol.type, name, classSymbol);
            SymbolNotFoundError symbolNotFoundError = this.typeNotFound;
            if (symbolFindTypeVar != symbolNotFoundError && (env.baseClause || symbolFindImmediateMemberType == symbolNotFoundError || (symbolFindTypeVar.kind == Kinds.Kind.TYP && symbolFindTypeVar.exists() && symbolFindTypeVar.owner.kind == Kinds.Kind.MTH))) {
                return symbolFindTypeVar;
            }
            if (symbolFindImmediateMemberType == this.typeNotFound) {
                Symbol.ClassSymbol classSymbol2 = env2.enclClass.sym;
                symbolFindImmediateMemberType = findInheritedMemberType(env2, classSymbol2.type, name, classSymbol2);
            }
            if (z && symbolFindImmediateMemberType.kind == Kinds.Kind.TYP) {
                Type type = symbolFindImmediateMemberType.type;
                TypeTag typeTag = TypeTag.CLASS;
                if (type.hasTag(typeTag) && symbolFindImmediateMemberType.type.getEnclosingType().hasTag(typeTag) && env2.enclClass.sym.type.isParameterized() && symbolFindImmediateMemberType.type.getEnclosingType().isParameterized()) {
                    return new StaticError(this, symbolFindImmediateMemberType);
                }
            }
            if (symbolFindImmediateMemberType.exists()) {
                return symbolFindImmediateMemberType;
            }
            symbolBestOf = bestOf(symbolBestOf, symbolFindImmediateMemberType);
            if (((env2.baseClause ? (JCTree.JCClassDecl) env2.tree : env2.enclClass).sym.flags() & 8) != 0) {
                z = true;
            }
        }
        if (env.tree.hasTag(JCTree.Tag.IMPORT)) {
            return symbolBestOf;
        }
        Symbol symbolFindGlobalType = findGlobalType(env, env.toplevel.namedImportScope, name, this.namedImportScopeRecovery);
        if (symbolFindGlobalType.exists()) {
            return symbolFindGlobalType;
        }
        Symbol symbolBestOf2 = bestOf(symbolBestOf, symbolFindGlobalType);
        Symbol symbolFindGlobalType2 = findGlobalType(env, env.toplevel.toplevelScope, name, this.noRecovery);
        if (symbolFindGlobalType2.exists()) {
            return symbolFindGlobalType2;
        }
        Symbol symbolBestOf3 = bestOf(symbolBestOf2, symbolFindGlobalType2);
        Symbol symbolFindGlobalType3 = findGlobalType(env, env.toplevel.packge.members(), name, this.noRecovery);
        if (symbolFindGlobalType3.exists()) {
            return symbolFindGlobalType3;
        }
        Symbol symbolBestOf4 = bestOf(symbolBestOf3, symbolFindGlobalType3);
        Symbol symbolFindGlobalType4 = findGlobalType(env, env.toplevel.starImportScope, name, this.starImportScopeRecovery);
        if (symbolFindGlobalType4.exists()) {
            return symbolFindGlobalType4;
        }
        Symbol symbolBestOf5 = bestOf(symbolBestOf4, symbolFindGlobalType4);
        Symbol symbolFindGlobalType5 = findGlobalType(env, env.toplevel.moduleImportScope, name, this.moduleImportScopeRecovery);
        return symbolFindGlobalType5.exists() ? symbolFindGlobalType5 : bestOf(symbolBestOf5, symbolFindGlobalType5);
    }

    public Symbol findTypeVar(Env<AttrContext> env, Name name, boolean z) {
        for (Symbol symbol : env.info.scope.getSymbolsByName(name)) {
            Kinds.Kind kind = symbol.kind;
            Kinds.Kind kind2 = Kinds.Kind.TYP;
            if (kind == kind2) {
                return (symbol.type.hasTag(TypeTag.TYPEVAR) && (z || (isStatic(env) && symbol.owner.kind == kind2))) ? new StaticError(this, symbol) : symbol;
            }
        }
        return this.typeNotFound;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Symbol findVar(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Name name) {
        Symbol accessError = this.varNotFound;
        Env env2 = env;
        boolean z = false;
        while (true) {
            Symbol symbolFindField = null;
            if (env2.outer == null) {
                Symbol.ClassSymbol classSymbol = this.syms.predefClass;
                Symbol symbolFindField2 = findField(env, classSymbol.type, name, classSymbol);
                if (symbolFindField2.exists()) {
                    return symbolFindField2;
                }
                if (accessError.exists()) {
                    return accessError;
                }
                JCTree.JCCompilationUnit jCCompilationUnit = env.toplevel;
                Scope[] scopeArr = {jCCompilationUnit.namedImportScope, jCCompilationUnit.starImportScope};
                for (int i = 0; i < 2; i++) {
                    Scope scope = scopeArr[i];
                    for (Symbol symbol : scope.getSymbolsByName(name)) {
                        Kinds.Kind kind = symbol.kind;
                        Kinds.Kind kind2 = Kinds.Kind.VAR;
                        if (kind == kind2) {
                            if (!accessError.kind.isResolutionError() && symbol.owner != accessError.owner) {
                                return new AmbiguityError(accessError, symbol);
                            }
                            if (!accessError.kind.betterThan(kind2)) {
                                symbolFindField = scope.getOrigin(symbol).owner;
                                accessError = isAccessible(env, symbolFindField.type, symbol) ? symbol : new AccessError(env, symbolFindField.type, symbol);
                            }
                        }
                    }
                    if (accessError.exists()) {
                        break;
                    }
                }
                return (accessError.kind != Kinds.Kind.VAR || accessError.owner.type == symbolFindField.type) ? accessError : accessError.clone(symbolFindField);
            }
            for (Symbol symbol2 : ((AttrContext) env2.info).scope.getSymbolsByName(name)) {
                if (symbol2.kind == Kinds.Kind.VAR && (symbol2.flags_field & 4096) == 0) {
                    if (!z) {
                        symbolFindField = symbol2;
                        break;
                    }
                    return new StaticError(this, symbol2);
                }
            }
            if (isStatic(env2)) {
                z = true;
            }
            if (symbolFindField == null) {
                Symbol.ClassSymbol classSymbol2 = env2.enclClass.sym;
                symbolFindField = findField(env2, classSymbol2.type, name, classSymbol2);
            }
            if (symbolFindField.exists()) {
                if (symbolFindField.kind == Kinds.Kind.VAR && symbolFindField.owner.kind == Kinds.Kind.TYP && (symbolFindField.flags() & 8) == 0) {
                    if (z) {
                        return new StaticError(this, symbolFindField);
                    }
                    if (((AttrContext) env2.info).ctorPrologue && !isAllowedEarlyReference(diagnosticPosition, env2, (Symbol.VarSymbol) symbolFindField)) {
                        return new RefBeforeCtorCalledError(symbolFindField);
                    }
                }
                return symbolFindField;
            }
            accessError = bestOf(accessError, symbolFindField);
            if ((8 & env2.enclClass.sym.flags()) != 0) {
                z = true;
            }
            env2 = env2.outer;
        }
    }

    public Symbol getMemberReference(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, JCTree.JCMemberReference jCMemberReference, Type type, Name name) {
        Type typeCapture = this.types.capture(type);
        ReferenceLookupHelper referenceLookupHelperMakeReferenceLookupHelper = makeReferenceLookupHelper(jCMemberReference, typeCapture, name, List.nil(), null, MethodResolutionPhase.VARARITY);
        Env<AttrContext> envDup = env.dup(env.tree, env.info.dup());
        Symbol symbolLookupMethod = lookupMethod(envDup, env.tree.pos(), typeCapture.tsym, this.nilMethodCheck, referenceLookupHelperMakeReferenceLookupHelper);
        env.info.pendingResolutionPhase = envDup.info.pendingResolutionPhase;
        return symbolLookupMethod;
    }

    public JCDiagnostic getVerboseApplicableCandidateDiag(int i, Symbol symbol, Type type) {
        JCDiagnostic jCDiagnosticFragment = symbol.type.hasTag(TypeTag.FORALL) ? this.diags.fragment(CompilerProperties.Fragments.PartialInstSig(type)) : null;
        return this.diags.fragment(jCDiagnosticFragment == null ? "applicable.method.found" : "applicable.method.found.1", Integer.valueOf(i), symbol, jCDiagnosticFragment);
    }

    public JCDiagnostic getVerboseInapplicableCandidateDiag(int i, Symbol symbol, JCDiagnostic jCDiagnostic) {
        return this.diags.fragment(CompilerProperties.Fragments.NotApplicableMethodFound(i, symbol, jCDiagnostic));
    }

    public JCDiagnostic inaccessiblePackageReason(Env<AttrContext> env, final Symbol.PackageSymbol packageSymbol) {
        if (!env.toplevel.modle.readModules.contains(packageSymbol.modle)) {
            Symbol.ModuleSymbol moduleSymbol = packageSymbol.modle;
            Symbol.ModuleSymbol moduleSymbol2 = this.syms.unnamedModule;
            if (moduleSymbol == moduleSymbol2) {
                return this.diags.fragment(CompilerProperties.Fragments.NotDefAccessDoesNotReadUnnamed(packageSymbol, env.toplevel.modle));
            }
            Symbol.ModuleSymbol moduleSymbol3 = env.toplevel.modle;
            JCDiagnostic.Factory factory = this.diags;
            return moduleSymbol3 != moduleSymbol2 ? factory.fragment(CompilerProperties.Fragments.NotDefAccessDoesNotRead(moduleSymbol3, packageSymbol, moduleSymbol)) : factory.fragment(CompilerProperties.Fragments.NotDefAccessDoesNotReadFromUnnamed(packageSymbol, moduleSymbol));
        }
        boolean zAnyMatch = packageSymbol.packge().modle.exports.stream().anyMatch(new Predicate() { // from class: ogc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Resolve.e(packageSymbol, (Directive.ExportsDirective) obj);
            }
        });
        JCTree.JCCompilationUnit jCCompilationUnit = env.toplevel;
        if (zAnyMatch) {
            Symbol.ModuleSymbol moduleSymbol4 = jCCompilationUnit.modle;
            Symbol.ModuleSymbol moduleSymbol5 = this.syms.unnamedModule;
            JCDiagnostic.Factory factory2 = this.diags;
            return moduleSymbol4 != moduleSymbol5 ? factory2.fragment(CompilerProperties.Fragments.NotDefAccessNotExportedToModule(packageSymbol, packageSymbol.modle, moduleSymbol4)) : factory2.fragment(CompilerProperties.Fragments.NotDefAccessNotExportedToModuleFromUnnamed(packageSymbol, packageSymbol.modle));
        }
        Symbol.ModuleSymbol moduleSymbol6 = jCCompilationUnit.modle;
        Symbol.ModuleSymbol moduleSymbol7 = this.syms.unnamedModule;
        JCDiagnostic.Factory factory3 = this.diags;
        return moduleSymbol6 != moduleSymbol7 ? factory3.fragment(CompilerProperties.Fragments.NotDefAccessNotExported(packageSymbol, packageSymbol.modle)) : factory3.fragment(CompilerProperties.Fragments.NotDefAccessNotExportedFromUnnamed(packageSymbol, packageSymbol.modle));
    }

    public Type instantiate(Env<AttrContext> env, Type type, Symbol symbol, Attr.ResultInfo resultInfo, List<Type> list, List<Type> list2, boolean z, boolean z2, Warner warner) {
        try {
            return rawInstantiate(env, type, symbol, resultInfo, list, list2, z, z2, warner);
        } catch (InapplicableMethodException unused) {
            return null;
        }
    }

    public boolean isAccessible(Env<AttrContext> env, Type type, Symbol symbol, boolean z) {
        if (symbol.name == this.names.init && symbol.owner != type.tsym) {
            return false;
        }
        JCTree.JCMethodDecl jCMethodDecl = env.enclMethod;
        if (jCMethodDecl != null && (jCMethodDecl.mods.flags & 536870912) != 0) {
            return true;
        }
        if (env.info.visitingServiceImplementation && env.toplevel.modle == symbol.packge().modle) {
            return true;
        }
        short sFlags = (short) (symbol.flags() & 7);
        if (sFlags == 0) {
            Symbol.PackageSymbol packageSymbol = env.toplevel.packge;
            return (packageSymbol == symbol.owner.owner || packageSymbol == symbol.packge()) && isAccessible(env, type, z) && symbol.isInheritedIn(type.tsym, this.types) && notOverriddenIn(type, symbol);
        }
        if (sFlags == 2) {
            Symbol.ClassSymbol classSymbol = env.enclClass.sym;
            return (classSymbol == symbol.owner || classSymbol.outermostClass() == symbol.owner.outermostClass() || privateMemberInPermitsClauseIfAllowed(env, symbol)) && symbol.isInheritedIn(type.tsym, this.types);
        }
        if (sFlags != 4) {
            return isAccessible(env, type, z) && notOverriddenIn(type, symbol);
        }
        Symbol.PackageSymbol packageSymbol2 = env.toplevel.packge;
        return (packageSymbol2 == symbol.owner.owner || packageSymbol2 == symbol.packge() || isProtectedAccessible(symbol, env.enclClass.sym, type) || (env.info.selectSuper && (symbol.flags() & 8) == 0 && symbol.kind != Kinds.Kind.TYP)) && isAccessible(env, type, z) && notOverriddenIn(type, symbol);
    }

    public boolean isEarlyReference(Env<AttrContext> env, JCTree jCTree, Symbol.VarSymbol varSymbol) {
        if (!env.info.ctorPrologue || (varSymbol.flags() & 8) != 0 || !varSymbol.isMemberOf(env.enclClass.sym, this.types)) {
            return false;
        }
        if (jCTree != null) {
            return TreeInfo.isExplicitThisReference(this.types, (Type.ClassType) env.enclClass.type, jCTree);
        }
        return true;
    }

    public boolean isSerializable(Type type) {
        try {
            this.syms.serializableType.complete();
            return this.types.isSubtype(type, this.syms.serializableType);
        } catch (Symbol.CompletionFailure unused) {
            return false;
        }
    }

    public Symbol loadClass(Env<AttrContext> env, Name name, RecoveryLoadClass recoveryLoadClass) {
        try {
            Symbol.ClassSymbol classSymbolLoadClass = this.finder.loadClass(env.toplevel.modle, name);
            return isAccessible(env, classSymbolLoadClass) ? classSymbolLoadClass : new AccessError(env, null, classSymbolLoadClass);
        } catch (ClassFinder.BadClassFile e) {
            return new BadClassFileError(e);
        } catch (Symbol.CompletionFailure unused) {
            Symbol symbolLoadClass = recoveryLoadClass.loadClass(env, name);
            return symbolLoadClass != null ? symbolLoadClass : this.typeNotFound;
        }
    }

    public void logAccessErrorInternal(Env<AttrContext> env, JCTree jCTree, Type type) {
        AccessError accessError = new AccessError(env, env.enclClass.type, type.tsym);
        JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCTree.pos();
        JCTree.JCClassDecl jCClassDecl = env.enclClass;
        logResolveError(accessError, diagnosticPositionPos, jCClassDecl.sym, jCClassDecl.type, null, null, null);
    }

    public Symbol lookupMethod(Env<AttrContext> env, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, MethodResolutionContext methodResolutionContext, LookupHelper lookupHelper) {
        MethodResolutionContext methodResolutionContext2 = this.currentResolutionContext;
        try {
            Symbol symbol2 = this.methodNotFound;
            this.currentResolutionContext = methodResolutionContext;
            for (MethodResolutionPhase methodResolutionPhase : this.methodResolutionSteps) {
                if (lookupHelper.shouldStop(symbol2, methodResolutionPhase)) {
                    break;
                }
                MethodResolutionContext methodResolutionContext3 = this.currentResolutionContext;
                MethodResolutionPhase methodResolutionPhase2 = methodResolutionContext3.step;
                methodResolutionContext3.step = methodResolutionPhase;
                Symbol symbolLookup = lookupHelper.lookup(env, methodResolutionPhase);
                lookupHelper.debug(diagnosticPosition, symbolLookup);
                Symbol symbolMergeResults = methodResolutionPhase.mergeResults(symbol2, symbolLookup);
                AttrContext attrContext = env.info;
                if (symbol2 == symbolMergeResults) {
                    methodResolutionPhase = methodResolutionPhase2;
                }
                attrContext.pendingResolutionPhase = methodResolutionPhase;
                symbol2 = symbolMergeResults;
            }
            return lookupHelper.access(env, diagnosticPosition, symbol, symbol2);
        } finally {
            this.currentResolutionContext = methodResolutionContext2;
        }
    }

    public Symbol lookupPackage(Env<AttrContext> env, Name name) {
        Symbol.PackageSymbol packageSymbolLookupPackage = this.syms.lookupPackage(env.toplevel.modle, name);
        if (!this.allowModules || !isImportOnDemand(env, name) || !packageSymbolLookupPackage.members().isEmpty()) {
            return packageSymbolLookupPackage;
        }
        final Symtab symtab = this.syms;
        Objects.requireNonNull(symtab);
        Function function = new Function() { // from class: sgc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return symtab.getPackagesForName((Name) obj);
            }
        };
        final Symtab symtab2 = this.syms;
        Objects.requireNonNull(symtab2);
        return lookupInvisibleSymbol(env, name, function, new BiFunction() { // from class: tgc
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return symtab2.enterPackage((Symbol.ModuleSymbol) obj, (Name) obj2);
            }
        }, new Predicate() { // from class: kgc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Resolve.b((Symbol.PackageSymbol) obj);
            }
        }, packageSymbolLookupPackage);
    }

    public ReferenceLookupHelper makeReferenceLookupHelper(JCTree.JCMemberReference jCMemberReference, Type type, Name name, List<Type> list, List<Type> list2, MethodResolutionPhase methodResolutionPhase) {
        if (name.equals(this.names.init)) {
            return type.hasTag(TypeTag.ARRAY) ? new ArrayConstructorReferenceLookupHelper(jCMemberReference, type, list, list2, methodResolutionPhase) : new ConstructorReferenceLookupHelper(jCMemberReference, type, list, list2, methodResolutionPhase);
        }
        return new MethodReferenceLookupHelper(jCMemberReference, name, type, list, list2, methodResolutionPhase);
    }

    public Object methodArguments(List<Type> list) {
        if (list == null || list.isEmpty()) {
            return this.noArgs;
        }
        ListBuffer listBuffer = new ListBuffer();
        for (Type type : list) {
            if (type.hasTag(TypeTag.DEFERRED)) {
                listBuffer.append(((DeferredAttr.DeferredType) type).tree);
            } else {
                listBuffer.append(type);
            }
        }
        return listBuffer;
    }

    public Symbol mostSpecific(List<Type> list, Symbol symbol, Symbol symbol2, Env<AttrContext> env, Type type, boolean z) {
        Symbol symbol3;
        int i = AnonymousClass19.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[symbol2.kind.ordinal()];
        if (i == 4) {
            symbol3 = symbol;
            if (symbol3 != symbol2) {
                boolean zSignatureMoreSpecific = signatureMoreSpecific(list, env, type, symbol3, symbol2, z);
                boolean zSignatureMoreSpecific2 = signatureMoreSpecific(list, env, type, symbol2, symbol3, z);
                if (zSignatureMoreSpecific && zSignatureMoreSpecific2) {
                    if (!this.types.overrideEquivalent(this.types.memberType(type, symbol3), this.types.memberType(type, symbol2))) {
                        return ambiguityError(symbol3, symbol2);
                    }
                    if ((symbol3.flags() & Flags.BRIDGE) == (symbol2.flags() & Flags.BRIDGE)) {
                        if (symbol3.baseSymbol() != symbol2.baseSymbol()) {
                            Symbol.TypeSymbol typeSymbol = (Symbol.TypeSymbol) symbol3.owner;
                            Symbol.TypeSymbol typeSymbol2 = (Symbol.TypeSymbol) symbol2.owner;
                            if (typeSymbol != typeSymbol2) {
                                if (this.types.asSuper(typeSymbol.type, typeSymbol2) == null || (((symbol3.owner.flags_field & 512) != 0 && (symbol2.owner.flags_field & 512) == 0) || !symbol3.overrides(symbol2, typeSymbol, this.types, false))) {
                                    if (this.types.asSuper(typeSymbol2.type, typeSymbol) == null || (((symbol2.owner.flags_field & 512) != 0 && (symbol3.owner.flags_field & 512) == 0) || !symbol2.overrides(symbol3, typeSymbol2, this.types, false))) {
                                    }
                                }
                            }
                            boolean z2 = (symbol3.flags() & 1024) != 0;
                            boolean z3 = (symbol2.flags() & 1024) != 0;
                            if (!z2 || z3) {
                                if (!z3 || z2) {
                                    return ambiguityError(symbol3, symbol2);
                                }
                            }
                        }
                        return symbol3;
                    }
                    if ((symbol3.flags() & Flags.BRIDGE) == 0) {
                        return symbol3;
                    }
                } else if (!zSignatureMoreSpecific) {
                    if (!zSignatureMoreSpecific2) {
                        return ambiguityError(symbol3, symbol2);
                    }
                }
                return symbol2;
            }
        } else {
            if (i != 5) {
                x1f.a();
                return null;
            }
            AmbiguityError ambiguityError = (AmbiguityError) symbol2.baseSymbol();
            Iterator<Symbol> it = ambiguityError.ambiguousSyms.iterator();
            boolean z4 = true;
            boolean z5 = true;
            while (it.hasNext()) {
                Symbol next = it.next();
                Symbol symbolMostSpecific = mostSpecific(list, symbol, next, env, type, z);
                z4 &= symbolMostSpecific == symbol;
                z5 &= symbolMostSpecific == next;
            }
            symbol3 = symbol;
            if (!z4) {
                if (!z5) {
                    ambiguityError.addAmbiguousSymbol(symbol3);
                }
                return ambiguityError;
            }
        }
        return symbol3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Type rawInstantiate(Env<AttrContext> env, Type type, Symbol symbol, Attr.ResultInfo resultInfo, List<Type> list, List<Type> list2, boolean z, boolean z2, Warner warner) throws Infer.InferenceException {
        Type typeMemberType = this.types.memberType(type, symbol);
        List<Type> listNil = List.nil();
        List<Type> listNil2 = list2 == null ? List.nil() : list2;
        TypeTag typeTag = TypeTag.FORALL;
        if (typeMemberType.hasTag(typeTag) || !listNil2.nonEmpty()) {
            if (typeMemberType.hasTag(typeTag) && listNil2.nonEmpty()) {
                Type.ForAll forAll = (Type.ForAll) typeMemberType;
                if (listNil2.length() != forAll.tvars.length()) {
                    throw new InapplicableMethodException(this.diags.fragment(CompilerProperties.Fragments.WrongNumberTypeArgs(Integer.toString(forAll.tvars.length()))), this.dumpStacktraceOnError);
                }
                List list3 = forAll.tvars;
                for (List list4 = listNil2; list3.nonEmpty() && list4.nonEmpty(); list4 = list4.tail) {
                    Types types = this.types;
                    for (List listSubst = types.subst(types.getBounds((Type.TypeVar) list3.head), forAll.tvars, listNil2); listSubst.nonEmpty(); listSubst = listSubst.tail) {
                        if (!this.types.isSubtypeUnchecked((Type) list4.head, (Type) listSubst.head, warner)) {
                            throw new InapplicableMethodException(this.diags.fragment(CompilerProperties.Fragments.ExplicitParamDoNotConformToBounds((Type) list4.head, listSubst)), this.dumpStacktraceOnError);
                        }
                    }
                    list3 = list3.tail;
                }
                typeMemberType = this.types.subst(forAll.qtype, forAll.tvars, listNil2);
            } else if (typeMemberType.hasTag(typeTag)) {
                Type.ForAll forAll2 = (Type.ForAll) typeMemberType;
                List<Type> listNewInstances = this.types.newInstances(forAll2.tvars);
                listNil = listNil.appendList(listNewInstances);
                typeMemberType = this.types.subst(forAll2.qtype, forAll2.tvars, listNewInstances);
            }
        }
        List<Type> list5 = listNil;
        boolean z3 = list5.tail != null;
        for (List list6 = list; list6.tail != null && !z3; list6 = list6.tail) {
            if (((Type) list6.head).hasTag(TypeTag.FORALL)) {
                z3 = true;
            }
        }
        if (z3) {
            return this.infer.instantiateMethod(env, list5, (Type.MethodType) typeMemberType, resultInfo, (Symbol.MethodSymbol) symbol, list, z, z2, this.currentResolutionContext, warner);
        }
        DeferredAttr.DeferredAttrContext deferredAttrContext = this.currentResolutionContext.deferredAttrContext(symbol, this.infer.emptyContext, resultInfo, warner);
        this.currentResolutionContext.methodCheck.argumentsAcceptable(env, deferredAttrContext, list, typeMemberType.mo71getParameterTypes(), warner);
        deferredAttrContext.complete();
        return typeMemberType;
    }

    public void reportVerboseResolutionDiagnostic(JCDiagnostic.DiagnosticPosition diagnosticPosition, Name name, Type type, List<Type> list, List<Type> list2, Symbol symbol) {
        boolean zIsResolutionError = symbol.kind.isResolutionError();
        if (zIsResolutionError || this.verboseResolutionMode.contains(VerboseResolutionMode.SUCCESS)) {
            if (!zIsResolutionError || this.verboseResolutionMode.contains(VerboseResolutionMode.FAILURE)) {
                if (symbol.name == this.names.init && symbol.owner == this.syms.objectType.tsym && !this.verboseResolutionMode.contains(VerboseResolutionMode.OBJECT_INIT)) {
                    return;
                }
                if (type != this.syms.predefClass.type || this.verboseResolutionMode.contains(VerboseResolutionMode.PREDEF)) {
                    if (!this.currentResolutionContext.internalResolution || this.verboseResolutionMode.contains(VerboseResolutionMode.INTERNAL)) {
                        ListBuffer listBuffer = new ListBuffer();
                        int i = 0;
                        int i2 = -1;
                        for (MethodResolutionContext.Candidate candidate : this.currentResolutionContext.candidates) {
                            if (this.currentResolutionContext.step == candidate.step && (!candidate.isApplicable() || this.verboseResolutionMode.contains(VerboseResolutionMode.APPLICABLE))) {
                                if (candidate.isApplicable() || this.verboseResolutionMode.contains(VerboseResolutionMode.INAPPLICABLE)) {
                                    boolean zIsApplicable = candidate.isApplicable();
                                    Symbol symbol2 = candidate.sym;
                                    listBuffer.append(zIsApplicable ? getVerboseApplicableCandidateDiag(i, symbol2, candidate.mtype) : getVerboseInapplicableCandidateDiag(i, symbol2, candidate.details));
                                    if (candidate.sym == symbol) {
                                        i2 = i;
                                    }
                                    i++;
                                }
                            }
                        }
                        String str = !zIsResolutionError ? "verbose.resolve.multi" : "verbose.resolve.multi.1";
                        DeferredAttr deferredAttr = this.deferredAttr;
                        Objects.requireNonNull(deferredAttr);
                        this.log.report(new JCDiagnostic.MultilineDiagnostic(this.diags.note(this.log.currentSource(), diagnosticPosition, str, name, type.tsym, Integer.valueOf(i2), this.currentResolutionContext.step, methodArguments(list.map(deferredAttr.new RecoveryDeferredTypeMap(DeferredAttr.AttrMode.SPECULATIVE, symbol, this.currentResolutionContext.step))), methodArguments(list2)), listBuffer.toList()));
                    }
                }
            }
        }
    }

    public Symbol resolveDiamond(final JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Type type, List<Type> list, List<Type> list2) {
        return lookupMethod(env, diagnosticPosition, type.tsym, this.resolveMethodCheck, new BasicLookupHelper(this, this.names.init, type, list, list2) { // from class: com.sun.tools.javac.comp.Resolve.15
            final /* synthetic */ Resolve this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.comp.Resolve.BasicLookupHelper, com.sun.tools.javac.comp.Resolve.LookupHelper
            public Symbol access(Env<AttrContext> env2, JCDiagnostic.DiagnosticPosition diagnosticPosition2, Symbol symbol, Symbol symbol2) {
                if (!symbol2.kind.isResolutionError()) {
                    return symbol2;
                }
                Kinds.Kind kind = symbol2.kind;
                if (kind != Kinds.Kind.WRONG_MTH && kind != Kinds.Kind.WRONG_MTHS) {
                    return super.access(env2, diagnosticPosition2, symbol, symbol2);
                }
                Resolve resolve = this.this$0;
                DiamondError diamondError = resolve.new DiamondError(symbol2, resolve.currentResolutionContext);
                Resolve resolve2 = this.this$0;
                Symbol symbolAccessMethod = resolve2.accessMethod(diamondError, diagnosticPosition2, this.site, resolve2.names.init, true, this.argtypes, this.typeargtypes);
                env2.info.pendingResolutionPhase = this.this$0.currentResolutionContext.step;
                return symbolAccessMethod;
            }

            @Override // com.sun.tools.javac.comp.Resolve.LookupHelper
            public Symbol lookup(Env<AttrContext> env2, MethodResolutionPhase methodResolutionPhase) {
                return this.this$0.findDiamond(diagnosticPosition, env2, this.site, this.argtypes, this.typeargtypes, methodResolutionPhase.isBoxingRequired(), methodResolutionPhase.isVarargsRequired());
            }
        });
    }

    public Symbol resolveIdent(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Name name, Kinds.KindSelector kindSelector) {
        return accessBase(findIdent(diagnosticPosition, env, name, kindSelector), diagnosticPosition, env.enclClass.sym.type, name, false);
    }

    public Symbol.MethodSymbol resolveInternalConstructor(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Type type, List<Type> list, List<Type> list2) {
        MethodResolutionContext methodResolutionContext = new MethodResolutionContext();
        methodResolutionContext.internalResolution = true;
        Symbol symbolResolveConstructor = resolveConstructor(methodResolutionContext, diagnosticPosition, env, type, list, list2);
        if (symbolResolveConstructor.kind == Kinds.Kind.MTH) {
            return (Symbol.MethodSymbol) symbolResolveConstructor;
        }
        throw new FatalError(this.diags.fragment(CompilerProperties.Fragments.FatalErrCantLocateCtor(type)));
    }

    public Symbol.VarSymbol resolveInternalField(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Type type, Name name) {
        Symbol symbolFindField = findField(env, type, name, type.tsym);
        if (symbolFindField.kind == Kinds.Kind.VAR) {
            return (Symbol.VarSymbol) symbolFindField;
        }
        throw new FatalError(this.diags.fragment(CompilerProperties.Fragments.FatalErrCantLocateField(name)));
    }

    public Symbol.MethodSymbol resolveInternalMethod(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Type type, Name name, List<Type> list, List<Type> list2) {
        MethodResolutionContext methodResolutionContext = new MethodResolutionContext();
        methodResolutionContext.internalResolution = true;
        Symbol symbolResolveQualifiedMethod = resolveQualifiedMethod(methodResolutionContext, diagnosticPosition, env, type.tsym, type, name, list, list2);
        if (symbolResolveQualifiedMethod.kind == Kinds.Kind.MTH) {
            return (Symbol.MethodSymbol) symbolResolveQualifiedMethod;
        }
        throw new FatalError(this.diags.fragment(CompilerProperties.Fragments.FatalErrCantLocateMeth(name)));
    }

    public Pair<Symbol, ReferenceLookupHelper> resolveMemberReference(Env<AttrContext> env, JCTree.JCMemberReference jCMemberReference, Type type, Name name, List<Type> list, List<Type> list2, Type type2, MethodCheck methodCheck, InferenceContext inferenceContext, ReferenceChooser referenceChooser) {
        ReferenceLookupHelper referenceLookupHelperMakeReferenceLookupHelper = makeReferenceLookupHelper(jCMemberReference, type, name, list, list2, MethodResolutionPhase.VARARITY);
        Env<AttrContext> envDup = env.dup(env.tree, env.info.dup());
        MethodResolutionContext methodResolutionContext = new MethodResolutionContext();
        methodResolutionContext.methodCheck = methodCheck;
        Symbol symbolLookupMethod = lookupMethod(envDup, env.tree.pos(), type.tsym, methodResolutionContext, referenceLookupHelperMakeReferenceLookupHelper);
        boolean zIsStaticSelector = TreeInfo.isStaticSelector(jCMemberReference.expr, this.names);
        ReferenceLookupResult referenceLookupResult = new ReferenceLookupResult(symbolLookupMethod, methodResolutionContext, zIsStaticSelector);
        if (this.dumpMethodReferenceSearchResults) {
            dumpMethodReferenceSearchResults(jCMemberReference, methodResolutionContext, symbolLookupMethod, true);
        }
        Env<AttrContext> envDup2 = env.dup(env.tree, env.info.dup());
        ReferenceLookupHelper referenceLookupHelperUnboundLookup = referenceLookupHelperMakeReferenceLookupHelper.unboundLookup(inferenceContext);
        ReferenceLookupResult referenceLookupResult2 = this.referenceNotFound;
        if (referenceLookupHelperUnboundLookup != null) {
            MethodResolutionContext methodResolutionContext2 = new MethodResolutionContext();
            methodResolutionContext2.methodCheck = methodCheck;
            Symbol symbolLookupMethod2 = lookupMethod(envDup2, env.tree.pos(), type.tsym, methodResolutionContext2, referenceLookupHelperUnboundLookup);
            ReferenceLookupResult referenceLookupResult3 = new ReferenceLookupResult(symbolLookupMethod2, methodResolutionContext2, zIsStaticSelector);
            if (this.dumpMethodReferenceSearchResults) {
                dumpMethodReferenceSearchResults(jCMemberReference, methodResolutionContext2, symbolLookupMethod2, false);
            }
            referenceLookupResult2 = referenceLookupResult3;
        }
        ReferenceLookupResult referenceLookupResultResult = referenceChooser.result(referenceLookupResult, referenceLookupResult2);
        Symbol symbol = referenceLookupResultResult.sym;
        if (referenceLookupResultResult != referenceLookupResult2) {
            referenceLookupHelperUnboundLookup = referenceLookupHelperMakeReferenceLookupHelper;
        }
        Pair<Symbol, ReferenceLookupHelper> pair = new Pair<>(symbol, referenceLookupHelperUnboundLookup);
        env.info.pendingResolutionPhase = referenceLookupResultResult == referenceLookupResult2 ? envDup2.info.pendingResolutionPhase : envDup.info.pendingResolutionPhase;
        if (!pair.fst.kind.isResolutionError()) {
            Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) pair.fst;
            if ((methodSymbol.flags() & Flags.SIGNATURE_POLYMORPHIC) != 0) {
                env.info.pendingResolutionPhase = MethodResolutionPhase.BASIC;
                return new Pair<>(findPolymorphicSignatureInstance(methodSymbol, type2), pair.snd);
            }
        }
        return pair;
    }

    public Symbol resolveMethod(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Name name, List<Type> list, List<Type> list2) {
        Symbol.ClassSymbol classSymbol = env.enclClass.sym;
        return lookupMethod(env, diagnosticPosition, classSymbol, this.resolveMethodCheck, new BasicLookupHelper(name, classSymbol.type, list, list2) { // from class: com.sun.tools.javac.comp.Resolve.11
            @Override // com.sun.tools.javac.comp.Resolve.LookupHelper
            public Symbol lookup(Env<AttrContext> env2, MethodResolutionPhase methodResolutionPhase) {
                return Resolve.this.findFun(env2, this.name, this.argtypes, this.typeargtypes, methodResolutionPhase.isBoxingRequired(), methodResolutionPhase.isVarargsRequired());
            }
        });
    }

    public Symbol resolveQualifiedMethod(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
        try {
            return resolveQualifiedMethod(new MethodResolutionContext(), diagnosticPosition, env, symbol, type, name, list, list2);
        } catch (Symbol.CompletionFailure e) {
            this.chk.completionError(diagnosticPosition, e);
            return this.methodNotFound.access(name, type.tsym);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Symbol resolveSelf(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Symbol.TypeSymbol typeSymbol, JCTree.JCFieldAccess jCFieldAccess) {
        Symbol symbolFindFirst;
        Symbol refBeforeCtorCalledError;
        Name name = jCFieldAccess.name;
        Names names = this.names;
        boolean z = false;
        Assert.check(name == names._this || name == names._super);
        for (Env env2 = env; env2.outer != null; env2 = env2.outer) {
            if (isStatic(env2)) {
                z = true;
            }
            if (env2.enclClass.sym == typeSymbol && (symbolFindFirst = ((AttrContext) env2.info).scope.findFirst(name)) != null) {
                if (!z) {
                    if (((AttrContext) env2.info).ctorPrologue && !isReceiverParameter(env, jCFieldAccess) && !isAllowedEarlyReference(diagnosticPosition, env2, (Symbol.VarSymbol) symbolFindFirst)) {
                        refBeforeCtorCalledError = new RefBeforeCtorCalledError(symbolFindFirst);
                    }
                    return accessBase(symbolFindFirst, diagnosticPosition, env.enclClass.sym.type, name, true);
                }
                refBeforeCtorCalledError = new StaticError(this, symbolFindFirst);
                symbolFindFirst = refBeforeCtorCalledError;
                return accessBase(symbolFindFirst, diagnosticPosition, env.enclClass.sym.type, name, true);
            }
            if ((env2.enclClass.sym.flags() & 8) != 0) {
                z = true;
            }
        }
        if (typeSymbol.isInterface() && name == this.names._super && !isStatic(env) && this.types.isDirectSuperInterface(typeSymbol, env.enclClass.sym)) {
            for (Type type : pruneInterfaces(env.enclClass.type)) {
                if (type.tsym == typeSymbol) {
                    if (env.info.ctorPrologue) {
                        this.log.error(diagnosticPosition, CompilerProperties.Errors.CantRefBeforeCtorCalled(name));
                    }
                    env.info.defaultSuperCallSite = type;
                    return new Symbol.VarSymbol(0L, this.names._super, this.types.asSuper(env.enclClass.type, typeSymbol), env.enclClass.sym);
                }
            }
            for (Type type2 : this.types.directSupertypes(env.enclClass.type)) {
                if (type2.tsym.isSubClass(typeSymbol, this.types) && type2.tsym != typeSymbol) {
                    this.log.error(diagnosticPosition, CompilerProperties.Errors.IllegalDefaultSuperCall(typeSymbol, CompilerProperties.Fragments.RedundantSupertype(typeSymbol, type2)));
                    return this.syms.errSymbol;
                }
            }
            Assert.error();
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.NotEnclClass(typeSymbol));
        return this.syms.errSymbol;
    }

    public Symbol selectBest(Env<AttrContext> env, Type type, List<Type> list, List<Type> list2, Symbol symbol, Symbol symbol2, boolean z, boolean z2) {
        Symbol.TypeSymbol typeSymbol;
        Symbol symbol3 = symbol;
        if (symbol3.kind == Kinds.Kind.ERR || !(((typeSymbol = type.tsym) == symbol3.owner || symbol3.isInheritedIn(typeSymbol, this.types)) && notOverriddenIn(type, symbol3))) {
            return symbol2;
        }
        if (z2 && (symbol3.flags() & Flags.VARARGS) == 0) {
            return symbol2.kind.isResolutionError() ? new BadVarargsMethod((ResolveError) symbol2.baseSymbol()) : symbol2;
        }
        Assert.check(!symbol3.kind.isResolutionError());
        try {
            this.types.noWarnings.clear();
            try {
                try {
                    this.currentResolutionContext.addApplicableCandidate(symbol3, rawInstantiate(env, type, symbol3, null, list, list2, z, z2, this.types.noWarnings));
                    if (isAccessible(env, type, symbol3)) {
                        return (!symbol2.kind.isResolutionError() || symbol2.kind == Kinds.Kind.AMBIGUOUS) ? mostSpecific(list, symbol3, symbol2, env, type, z2) : symbol3;
                    }
                    AccessError accessError = new AccessError(env, type, symbol3);
                    JCDiagnostic.DiagnosticType diagnosticType = JCDiagnostic.DiagnosticType.FRAGMENT;
                    JCDiagnostic diagnostic = accessError.getDiagnostic(diagnosticType, null, null, type, null, list, list2);
                    Kinds.Kind kind = symbol2.kind;
                    if (kind == Kinds.Kind.ABSENT_MTH) {
                        return accessError;
                    }
                    if (kind == Kinds.Kind.WRONG_MTH) {
                        this.currentResolutionContext.addInapplicableCandidate(symbol3, diagnostic);
                        return new InapplicableSymbolsError(this.currentResolutionContext);
                    }
                    if (kind == Kinds.Kind.WRONG_MTHS) {
                        this.currentResolutionContext.addInapplicableCandidate(symbol3, diagnostic);
                        return symbol2;
                    }
                    if (kind != Kinds.Kind.HIDDEN || !(symbol2 instanceof AccessError)) {
                        return symbol2;
                    }
                    AccessError accessError2 = (AccessError) symbol2;
                    this.currentResolutionContext.addInapplicableCandidate(accessError2.sym, accessError2.getDiagnostic(diagnosticType, null, null, type, null, list, list2));
                    this.currentResolutionContext.addInapplicableCandidate(symbol3, diagnostic);
                    return new InapplicableSymbolsError(this.currentResolutionContext);
                } catch (InapplicableMethodException e) {
                    e = e;
                    symbol3 = symbol3;
                    this.currentResolutionContext.addInapplicableCandidate(symbol3, e.getDiagnostic());
                    int i = AnonymousClass19.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[symbol2.kind.ordinal()];
                    if (i == 1) {
                        return new InapplicableSymbolError(this, this.currentResolutionContext);
                    }
                    if (i != 2) {
                        if (i != 3) {
                            return symbol2;
                        }
                    } else {
                        if (!(symbol2 instanceof AccessError)) {
                            return symbol2;
                        }
                        AccessError accessError3 = (AccessError) symbol2;
                        this.currentResolutionContext.addInapplicableCandidate(accessError3.sym, accessError3.getDiagnostic(JCDiagnostic.DiagnosticType.FRAGMENT, null, null, type, null, list, list2));
                    }
                    return new InapplicableSymbolsError(this.currentResolutionContext);
                }
            } catch (InapplicableMethodException e2) {
                e = e2;
                symbol3 = symbol3;
            }
        } catch (InapplicableMethodException e3) {
            e = e3;
        }
    }

    public Iterable<Symbol.TypeSymbol> superclasses(final Type type) {
        return new Iterable() { // from class: rgc
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return Resolve.i(this.b, type);
            }
        };
    }

    public class SymbolNotFoundError extends ResolveError {
        public SymbolNotFoundError(Resolve resolve, Kinds.Kind kind) {
            this(kind, "symbol not found error");
        }

        private Object args(List<Type> list) {
            return list.isEmpty() ? list : Resolve.this.methodArguments(list);
        }

        private String getErrorKey(Kinds.KindName kindName, boolean z, boolean z2) {
            String strConcat = z2 ? ".location" : "";
            int i = AnonymousClass19.$SwitchMap$com$sun$tools$javac$code$Kinds$KindName[kindName.ordinal()];
            if (i == 1 || i == 2) {
                strConcat = strConcat.concat(".args").concat(z ? ".params" : "");
            }
            return "cant.resolve".concat(strConcat);
        }

        private JCDiagnostic getLocationDiag(Symbol symbol, Type type) {
            Kinds.Kind kind = symbol.kind;
            Kinds.Kind kind2 = Kinds.Kind.VAR;
            Resolve resolve = Resolve.this;
            return kind == kind2 ? resolve.diags.fragment(CompilerProperties.Fragments.Location1(Kinds.kindName(symbol), symbol, symbol.type)) : resolve.diags.fragment(CompilerProperties.Fragments.Location(Kinds.typeKindName(type), type, (Void) null));
        }

        /* JADX WARN: Code duplicated, block: B:31:0x0080  */
        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            boolean z;
            Name name2 = name;
            List<Type> listNil = list == null ? List.nil() : list;
            List<Type> listNil2 = list2 == null ? List.nil() : list2;
            if (name2 == Resolve.this.names.error) {
                return null;
            }
            Symbol symbol2 = symbol == null ? type.tsym : symbol;
            if (symbol2.name.length() == 0) {
                z = false;
            } else {
                if (symbol2.kind == Kinds.Kind.PCK && !type.tsym.exists()) {
                    Name name3 = symbol2.name;
                    Resolve resolve = Resolve.this;
                    if (name3 != resolve.names.java) {
                        return resolve.diags.create(diagnosticType, resolve.log.currentSource(), diagnosticPosition, "doesnt.exist", symbol2);
                    }
                }
                if (symbol2.name.equals(Resolve.this.names._this) || symbol2.name.equals(Resolve.this.names._super)) {
                    z = false;
                } else {
                    z = true;
                }
            }
            boolean z2 = name2 == Resolve.this.names.init;
            Kinds.KindName kindNameAbsentKind = z2 ? Kinds.KindName.CONSTRUCTOR : this.kind.absentKind();
            if (z2) {
                name2 = type.tsym.name;
            }
            String errorKey = getErrorKey(kindNameAbsentKind, listNil2.nonEmpty(), z);
            Resolve resolve2 = Resolve.this;
            return z ? resolve2.diags.create(diagnosticType, resolve2.log.currentSource(), diagnosticPosition, errorKey, kindNameAbsentKind, name2, listNil2, args(listNil), getLocationDiag(symbol2, type)) : resolve2.diags.create(diagnosticType, resolve2.log.currentSource(), diagnosticPosition, errorKey, kindNameAbsentKind, name2, listNil2, args(listNil));
        }

        public SymbolNotFoundError(Kinds.Kind kind, String str) {
            super(kind, str);
        }
    }

    public class InapplicableSymbolError extends ResolveError {
        protected MethodResolutionContext resolveContext;

        public InapplicableSymbolError(Resolve resolve, MethodResolutionContext methodResolutionContext) {
            this(Kinds.Kind.WRONG_MTH, "inapplicable symbol error", methodResolutionContext);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ JCDiagnostic a(InapplicableSymbolError inapplicableSymbolError, JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.DiagnosticType diagnosticType, Pair pair, JCDiagnostic jCDiagnostic) {
            Resolve resolve = Resolve.this;
            return MethodResolutionDiagHelper.rewrite(resolve.diags, diagnosticPosition, resolve.log.currentSource(), diagnosticType, (JCDiagnostic) pair.snd);
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public Symbol access(Name name, Symbol.TypeSymbol typeSymbol) {
            Pair<Symbol, JCDiagnostic> pairErrCandidate = errCandidate();
            Resolve resolve = Resolve.this;
            Symbol.TypeSymbol typeSymbol2 = resolve.types.createErrorType(name, typeSymbol, (pairErrCandidate != null ? pairErrCandidate.fst : resolve.syms.errSymbol).type).tsym;
            if (pairErrCandidate != null) {
                Resolve.this.attrRecover.wrongMethodSymbolCandidate(typeSymbol2, pairErrCandidate.fst, pairErrCandidate.snd);
            }
            return typeSymbol2;
        }

        public Pair<Symbol, JCDiagnostic> errCandidate() {
            MethodResolutionContext.Candidate candidate = null;
            for (MethodResolutionContext.Candidate candidate2 : this.resolveContext.candidates) {
                if (!candidate2.isApplicable()) {
                    candidate = candidate2;
                }
            }
            Assert.checkNonNull(candidate);
            return new Pair<>(candidate.sym, candidate.details);
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError, com.sun.tools.javac.code.Symbol
        public boolean exists() {
            return true;
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            final JCDiagnostic.DiagnosticType diagnosticType2;
            final JCDiagnostic.DiagnosticPosition diagnosticPosition2;
            UnaryOperator<JCDiagnostic> unaryOperator = null;
            if (name == Resolve.this.names.error) {
                return null;
            }
            final Pair<Symbol, JCDiagnostic> pairErrCandidate = errCandidate();
            Symbol symbolAsMemberOf = pairErrCandidate.fst.asMemberOf(type, Resolve.this.types);
            if (Resolve.this.compactMethodDiags) {
                diagnosticType2 = diagnosticType;
                diagnosticPosition2 = diagnosticPosition;
                unaryOperator = new UnaryOperator() { // from class: com.sun.tools.javac.comp.x2
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Resolve.InapplicableSymbolError.a(this.b, diagnosticPosition2, diagnosticType2, pairErrCandidate, (JCDiagnostic) obj);
                    }
                };
            } else {
                diagnosticType2 = diagnosticType;
                diagnosticPosition2 = diagnosticPosition;
            }
            UnaryOperator<JCDiagnostic> unaryOperator2 = unaryOperator;
            String code = pairErrCandidate.snd.getCode();
            code.getClass();
            if (code.equals("compiler.misc.wrong.number.type.args") || code.equals("compiler.misc.explicit.param.do.not.conform.to.bounds")) {
                Resolve resolve = Resolve.this;
                JCDiagnostic.Factory factory = resolve.diags;
                DiagnosticSource diagnosticSourceCurrentSource = resolve.log.currentSource();
                Kinds.KindName kindName = Kinds.kindName(symbolAsMemberOf);
                Name name2 = symbolAsMemberOf.name;
                if (name2 == Resolve.this.names.init) {
                    name2 = symbolAsMemberOf.owner.name;
                }
                return factory.create(diagnosticType, diagnosticSourceCurrentSource, diagnosticPosition, "cant.apply.symbol.noargs", unaryOperator2, kindName, name2, Kinds.kindName(symbolAsMemberOf.owner), symbolAsMemberOf.owner.type, pairErrCandidate.snd);
            }
            Symbol symbol2 = symbolAsMemberOf.owner;
            Resolve resolve2 = Resolve.this;
            if (symbol2 == resolve2.syms.arrayClass && symbolAsMemberOf.name == resolve2.names.init) {
                return resolve2.diags.create(diagnosticType2, resolve2.log.currentSource(), diagnosticPosition2, "cant.apply.array.ctor", unaryOperator2, Resolve.this.methodArguments(symbolAsMemberOf.type.mo71getParameterTypes()), Resolve.this.methodArguments(list), pairErrCandidate.snd);
            }
            JCDiagnostic.Factory factory2 = resolve2.diags;
            DiagnosticSource diagnosticSourceCurrentSource2 = resolve2.log.currentSource();
            Kinds.KindName kindName2 = Kinds.kindName(symbolAsMemberOf);
            Name name3 = symbolAsMemberOf.name;
            Resolve resolve3 = Resolve.this;
            if (name3 == resolve3.names.init) {
                name3 = symbolAsMemberOf.owner.name;
            }
            return factory2.create(diagnosticType, diagnosticSourceCurrentSource2, diagnosticPosition, "cant.apply.symbol", unaryOperator2, kindName2, name3, resolve3.methodArguments(symbolAsMemberOf.type.mo71getParameterTypes()), Resolve.this.methodArguments(list), Kinds.kindName(symbolAsMemberOf.owner), symbolAsMemberOf.owner.type, pairErrCandidate.snd);
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError, com.sun.tools.javac.code.Symbol
        public String toString() {
            return super.toString();
        }

        public InapplicableSymbolError(Kinds.Kind kind, String str, MethodResolutionContext methodResolutionContext) {
            super(kind, str);
            this.resolveContext = methodResolutionContext;
        }
    }

    public static class ReferenceLookupResult {
        StaticKind staticKind;
        Symbol sym;

        public enum StaticKind {
            STATIC,
            NON_STATIC,
            BOTH,
            UNDEFINED;

            public static StaticKind from(Symbol symbol) {
                return symbol.isStatic() ? STATIC : NON_STATIC;
            }

            public static StaticKind reduce(StaticKind staticKind, StaticKind staticKind2) {
                StaticKind staticKind3 = UNDEFINED;
                if (staticKind == staticKind3) {
                    return staticKind2;
                }
                return (staticKind2 == staticKind3 || staticKind == staticKind2) ? staticKind : BOTH;
            }
        }

        public ReferenceLookupResult(Symbol symbol, MethodResolutionContext methodResolutionContext, boolean z) {
            this(symbol, staticKind(symbol, methodResolutionContext, z));
        }

        public static /* synthetic */ boolean b(MethodResolutionContext methodResolutionContext, MethodResolutionContext.Candidate candidate) {
            return candidate.isApplicable() && candidate.step == methodResolutionContext.step;
        }

        public static ReferenceLookupResult error(Symbol symbol) {
            return new ReferenceLookupResult(symbol, StaticKind.UNDEFINED);
        }

        private static StaticKind staticKind(Symbol symbol, final MethodResolutionContext methodResolutionContext, boolean z) {
            Kinds.Kind kind = symbol.kind;
            Kinds.Kind kind2 = Kinds.Kind.MTH;
            if (kind != kind2 || z) {
                return (kind == kind2 || kind == Kinds.Kind.AMBIGUOUS) ? (StaticKind) methodResolutionContext.candidates.stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.a3
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Resolve.ReferenceLookupResult.b(methodResolutionContext, (Resolve.MethodResolutionContext.Candidate) obj);
                    }
                }).map(new Function() { // from class: com.sun.tools.javac.comp.b3
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Resolve.ReferenceLookupResult.StaticKind.from(((Resolve.MethodResolutionContext.Candidate) obj).sym);
                    }
                }).reduce(new BinaryOperator() { // from class: com.sun.tools.javac.comp.c3
                    @Override // java.util.function.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        return Resolve.ReferenceLookupResult.StaticKind.reduce((Resolve.ReferenceLookupResult.StaticKind) obj, (Resolve.ReferenceLookupResult.StaticKind) obj2);
                    }
                }).orElse(StaticKind.UNDEFINED) : StaticKind.UNDEFINED;
            }
            return StaticKind.from(symbol);
        }

        public boolean canIgnore() {
            int i = AnonymousClass19.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[this.sym.kind.ordinal()];
            if (i == 1) {
                return true;
            }
            if (i == 3) {
                return new MethodResolutionDiagHelper.Template(MethodCheckDiag.ARITY_MISMATCH.regex(), new MethodResolutionDiagHelper.Template[0]).matches(((InapplicableSymbolError) this.sym.baseSymbol()).errCandidate().snd);
            }
            if (i != 6) {
                return false;
            }
            InapplicableSymbolsError inapplicableSymbolsError = (InapplicableSymbolsError) this.sym.baseSymbol();
            return inapplicableSymbolsError.filterCandidates(inapplicableSymbolsError.mapCandidates()).isEmpty();
        }

        public boolean hasKind(StaticKind staticKind) {
            return this.staticKind == staticKind;
        }

        public boolean isSuccess() {
            return this.staticKind != StaticKind.UNDEFINED;
        }

        private ReferenceLookupResult(Symbol symbol, StaticKind staticKind) {
            this.staticKind = staticKind;
            this.sym = symbol;
        }
    }

    public class StaticError extends InvalidSymbolError {
        public StaticError(Symbol symbol, String str) {
            super(Kinds.Kind.STATICERR, symbol, str);
        }

        @Override // com.sun.tools.javac.comp.Resolve.ResolveError
        public JCDiagnostic getDiagnostic(JCDiagnostic.DiagnosticType diagnosticType, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
            Symbol symbol2 = this.sym;
            Object obj = (symbol2.kind == Kinds.Kind.TYP && symbol2.type.hasTag(TypeTag.CLASS)) ? Resolve.this.types.erasure(this.sym.type).tsym : this.sym;
            Resolve resolve = Resolve.this;
            return resolve.diags.create(diagnosticType, resolve.log.currentSource(), diagnosticPosition, "non-static.cant.be.ref", Kinds.kindName(this.sym), obj);
        }

        public StaticError(Resolve resolve, Symbol symbol) {
            this(symbol, "static error");
        }
    }

    public class MethodResultInfo extends Attr.ResultInfo {
        /* JADX WARN: Illegal instructions before constructor call */
        public MethodResultInfo(Type type, Check.CheckContext checkContext) {
            Attr attr = Resolve.this.attr;
            Objects.requireNonNull(attr);
            super(attr, Kinds.KindSelector.VAL, type, checkContext);
        }

        private Type U(Type type) {
            return type == this.pt ? type : Resolve.this.types.cvarUpperBound(type);
        }

        @Override // com.sun.tools.javac.comp.Attr.ResultInfo
        public Type check(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
            if (type.hasTag(TypeTag.DEFERRED)) {
                return ((DeferredAttr.DeferredType) type).check(this);
            }
            Type typeU = U(type);
            return super.check(diagnosticPosition, Resolve.this.chk.checkNonVoid(diagnosticPosition, (diagnosticPosition == null || diagnosticPosition.getTree() == null) ? Resolve.this.types.capture(typeU) : this.checkContext.inferenceContext().cachedCapture(diagnosticPosition.getTree(), typeU, true)));
        }

        @Override // com.sun.tools.javac.comp.Attr.ResultInfo
        public MethodResultInfo dup(Type type) {
            return Resolve.this.new MethodResultInfo(type, this.checkContext);
        }

        @Override // com.sun.tools.javac.comp.Attr.ResultInfo
        public Attr.ResultInfo dup(Check.CheckContext checkContext) {
            return Resolve.this.new MethodResultInfo(this.pt, checkContext);
        }

        @Override // com.sun.tools.javac.comp.Attr.ResultInfo
        public Attr.ResultInfo dup(Type type, Check.CheckContext checkContext) {
            return Resolve.this.new MethodResultInfo(type, checkContext);
        }
    }

    public abstract class BasicLookupHelper extends LookupHelper {
        public BasicLookupHelper(Resolve resolve, Name name, Type type, List<Type> list, List<Type> list2) {
            this(name, type, list, list2, MethodResolutionPhase.VARARITY);
        }

        @Override // com.sun.tools.javac.comp.Resolve.LookupHelper
        public Symbol access(Env<AttrContext> env, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Symbol symbol2) {
            return symbol2.kind.isResolutionError() ? Resolve.this.accessMethod(symbol2, diagnosticPosition, symbol, this.site, this.name, true, this.argtypes, this.typeargtypes) : symbol2;
        }

        @Override // com.sun.tools.javac.comp.Resolve.LookupHelper
        public void debug(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
            Resolve.this.reportVerboseResolutionDiagnostic(diagnosticPosition, this.name, this.site, this.argtypes, this.typeargtypes, symbol);
        }

        public BasicLookupHelper(Name name, Type type, List<Type> list, List<Type> list2, MethodResolutionPhase methodResolutionPhase) {
            super(name, type, list, list2, methodResolutionPhase);
        }
    }

    public Symbol accessMethod(Symbol symbol, JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Name name, boolean z, List<Type> list, List<Type> list2) {
        return accessMethod(symbol, diagnosticPosition, type.tsym, type, name, z, list, list2);
    }

    public Symbol accessBase(Symbol symbol, JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Name name, boolean z) {
        return accessBase(symbol, diagnosticPosition, type.tsym, type, name, z);
    }

    public Symbol resolveConstructor(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Type type, List<Type> list, List<Type> list2) {
        return resolveConstructor(new MethodResolutionContext(), diagnosticPosition, env, type, list, list2);
    }

    public Symbol resolveQualifiedMethod(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Type type, Name name, List<Type> list, List<Type> list2) {
        return resolveQualifiedMethod(diagnosticPosition, env, type.tsym, type, name, list, list2);
    }

    private Symbol resolveQualifiedMethod(MethodResolutionContext methodResolutionContext, JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Symbol symbol, Type type, Name name, List<Type> list, List<Type> list2) {
        return lookupMethod(env, diagnosticPosition, symbol, methodResolutionContext, new BasicLookupHelper(name, type, list, list2) { // from class: com.sun.tools.javac.comp.Resolve.12
            @Override // com.sun.tools.javac.comp.Resolve.BasicLookupHelper, com.sun.tools.javac.comp.Resolve.LookupHelper
            public Symbol access(Env<AttrContext> env2, JCDiagnostic.DiagnosticPosition diagnosticPosition2, Symbol symbol2, Symbol symbol3) {
                if (symbol3.kind.isResolutionError()) {
                    return super.access(env2, diagnosticPosition2, symbol2, symbol3);
                }
                if ((((Symbol.MethodSymbol) symbol3).flags() & Flags.SIGNATURE_POLYMORPHIC) == 0) {
                    return symbol3;
                }
                env2.info.pendingResolutionPhase = MethodResolutionPhase.BASIC;
                return Resolve.this.findPolymorphicSignatureInstance(env2, symbol3, this.argtypes);
            }

            @Override // com.sun.tools.javac.comp.Resolve.LookupHelper
            public Symbol lookup(Env<AttrContext> env2, MethodResolutionPhase methodResolutionPhase) {
                return Resolve.this.findMethod(env2, this.site, this.name, this.argtypes, this.typeargtypes, methodResolutionPhase.isBoxingRequired(), methodResolutionPhase.isVarargsRequired());
            }
        });
    }

    public Symbol lookupMethod(Env<AttrContext> env, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, MethodCheck methodCheck, LookupHelper lookupHelper) {
        MethodResolutionContext methodResolutionContext = new MethodResolutionContext();
        methodResolutionContext.methodCheck = methodCheck;
        return lookupMethod(env, diagnosticPosition, symbol, methodResolutionContext, lookupHelper);
    }

    public Symbol findPolymorphicSignatureInstance(Env<AttrContext> env, Symbol symbol, List<Type> list) {
        return findPolymorphicSignatureInstance(symbol, this.infer.instantiatePolymorphicSignatureInstance(env, (Symbol.MethodSymbol) symbol, this.currentResolutionContext, list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Symbol findDiamond(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Type type, List<Type> list, List<Type> list2, boolean z, boolean z2) {
        Symbol symbolFindDiamond = findDiamond(env, type, list, list2, z, z2);
        this.chk.checkDeprecated(diagnosticPosition, env.info.scope.owner, symbolFindDiamond);
        this.chk.checkPreview(diagnosticPosition, env.info.scope.owner, symbolFindDiamond);
        return symbolFindDiamond;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0063  */
    public boolean isAccessible(Env<AttrContext> env, Symbol.TypeSymbol typeSymbol, boolean z) {
        boolean z2;
        Symbol.ModuleSymbol moduleSymbol;
        JCTree.JCMethodDecl jCMethodDecl = env.enclMethod;
        if (jCMethodDecl != null && (jCMethodDecl.mods.flags & 536870912) != 0) {
            return true;
        }
        if (env.info.visitingServiceImplementation && env.toplevel.modle == typeSymbol.packge().modle) {
            return true;
        }
        short sFlags = (short) (typeSymbol.flags() & 7);
        if (sFlags == 0) {
            Symbol.PackageSymbol packageSymbol = env.toplevel.packge;
            if (packageSymbol == typeSymbol.owner || packageSymbol == typeSymbol.packge()) {
                z2 = true;
            } else {
                z2 = false;
            }
        } else if (sFlags == 1) {
            if (this.allowModules) {
                Symbol.ModuleSymbol moduleSymbol2 = env.toplevel.modle;
                moduleSymbol2.complete();
                Symbol.PackageSymbol packageSymbolPackge = typeSymbol.packge();
                if (moduleSymbol2 != packageSymbolPackge.modle && moduleSymbol2.visiblePackages.get(packageSymbolPackge.fullname) != packageSymbolPackge) {
                    Symtab symtab = this.syms;
                    if (packageSymbolPackge != symtab.rootPackage && ((moduleSymbol = packageSymbolPackge.modle) != symtab.unnamedModule || !moduleSymbol2.readModules.contains(moduleSymbol))) {
                        z2 = false;
                    }
                }
            }
            z2 = true;
        } else if (sFlags != 2) {
            z2 = false;
        } else {
            z2 = false;
        }
        if (!z || typeSymbol.type.getEnclosingType() == Type.noType) {
            return z2;
        }
        return z2 && isAccessible(env, typeSymbol.type.getEnclosingType(), z);
    }

    public boolean isAccessible(Env<AttrContext> env, Type type) {
        return isAccessible(env, type, false);
    }

    public boolean isAccessible(final Env<AttrContext> env, Type type, final boolean z) {
        if (type.hasTag(TypeTag.ARRAY)) {
            Types types = this.types;
            return isAccessible(env, types.cvarUpperBound(types.elemtype(type)));
        }
        if (type.isUnion()) {
            return StreamSupport.stream(((Type.UnionClassType) type).getAlternativeTypes().spliterator(), false).allMatch(new Predicate() { // from class: qgc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Resolve.h(this.b, env, z, (Type) obj);
                }
            });
        }
        return isAccessible(env, type.tsym, z);
    }

    public boolean isAccessible(Env<AttrContext> env, Type type, Symbol symbol) {
        return isAccessible(env, type, symbol, false);
    }

    public boolean isAccessible(Env<AttrContext> env, Symbol.TypeSymbol typeSymbol) {
        return isAccessible(env, typeSymbol, false);
    }

    public Symbol findMethod(Env<AttrContext> env, Type type, Name name, List<Type> list, List<Type> list2, boolean z, boolean z2) {
        Symbol symbolFindMethod = findMethod(env, type, name, list, list2, type.tsym.type, this.methodNotFound, z, z2);
        return symbolFindMethod.kind == Kinds.Kind.AMBIGUOUS ? ((AmbiguityError) symbolFindMethod.baseSymbol()).mergeAbstracts(type) : symbolFindMethod;
    }
}

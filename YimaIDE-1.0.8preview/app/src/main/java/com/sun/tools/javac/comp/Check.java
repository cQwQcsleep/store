package com.sun.tools.javac.comp;

import com.intellij.psi.PsiKeyword;
import com.sun.source.tree.CaseTree;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.ClassFinder;
import com.sun.tools.javac.code.Directive;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.SymbolMetadata;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeAnnotations;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Check;
import com.sun.tools.javac.jvm.ByteCodes;
import com.sun.tools.javac.jvm.Profile;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.DiagnosticSource;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Log.DiscardDiagnosticHandler;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.Warner;
import defpackage.s22;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.ElementKindVisitor14;
import javax.tools.JavaFileManager;
import nbjavac.ObjectsWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Check {
    private static final int FIRST = 1;
    private static final int SECOND = 2;
    protected static final Context.Key<Check> checkKey = new Context.Key<>();
    private static final Types.SimpleVisitor<Boolean, Void> denotableChecker = new Types.SimpleVisitor<Boolean, Void>() { // from class: com.sun.tools.javac.comp.Check.2
        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitArrayType(Type.ArrayType arrayType, Void r2) {
            return visit(arrayType.elemtype, r2);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitClassType(Type.ClassType classType, Void r3) {
            if (classType.isUnion() || classType.isIntersection()) {
                return Boolean.FALSE;
            }
            Iterator<Type> it = classType.allparams().iterator();
            while (it.hasNext()) {
                if (!visit(it.next(), r3).booleanValue()) {
                    return Boolean.FALSE;
                }
            }
            return Boolean.TRUE;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitTypeVar(Type.TypeVar typeVar, Void r4) {
            return Boolean.valueOf((typeVar.tsym.flags() & 4096) == 0);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitWildcardType(Type.WildcardType wildcardType, Void r2) {
            return visit(wildcardType.type, r2);
        }

        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitCapturedType(Type.CapturedType capturedType, Void r2) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Boolean visitType(Type type, Void r2) {
            return Boolean.TRUE;
        }
    };
    private static final boolean ignoreAnnotatedCasts = true;
    private final boolean allowModules;
    private final boolean allowPrimitivePatterns;
    private final boolean allowRecords;
    private final boolean allowSealed;
    private Set<Name> defaultTargets;
    private final DeferredAttr deferredAttr;
    private Name[] dfltTargetMeta;
    private final JCDiagnostic.Factory diags;
    public boolean disablePreviewCheck;
    private final Enter enter;
    private final JavaFileManager fileManager;
    private boolean importSuppression;
    private final Infer infer;
    private Lint lint;
    private final Log log;
    private Symbol.MethodSymbol method;
    private final Names names;
    private final Preview preview;
    private final Profile profile;
    private final Resolve rs;
    private final Source source;
    private final Symtab syms;
    char syntheticNameChar;
    private final Target target;
    private final TypeAnnotations typeAnnotations;
    private final Types types;
    private final boolean warnOnAnyAccessToMembers;
    private Map<Pair<Symbol.ModuleSymbol, Name>, Symbol.ClassSymbol> compiled = new HashMap();
    private Map<Pair<Name, Name>, Integer> localClassNameIndexes = new HashMap();
    CheckContext basicHandler = new CheckContext() { // from class: com.sun.tools.javac.comp.Check.1
        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public Warner checkWarner(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2) {
            return Check.this.convertWarner(diagnosticPosition, type, type2);
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public boolean compatible(Type type, Type type2, Warner warner) {
            return Check.this.types.isAssignable(type, type2, warner);
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public DeferredAttr.DeferredAttrContext deferredAttrContext() {
            return Check.this.deferredAttr.emptyDeferredAttrContext;
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public InferenceContext inferenceContext() {
            return Check.this.infer.emptyContext;
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
            Check.this.log.error(diagnosticPosition, CompilerProperties.Errors.ProbFoundReq(jCDiagnostic));
        }

        public String toString() {
            return "CheckContext: basicHandler";
        }
    };
    Types.UnaryVisitor<Boolean> isTypeArgErroneous = new Types.UnaryVisitor<Boolean>() { // from class: com.sun.tools.javac.comp.Check.3
        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitCapturedType(Type.CapturedType capturedType, Void r2) {
            return Boolean.valueOf(visit(capturedType.getUpperBound()).booleanValue() || visit(capturedType.getLowerBound()).booleanValue());
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Boolean visitType(Type type, Void r2) {
            return Boolean.valueOf(type.isErroneous());
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitTypeVar(Type.TypeVar typeVar, Void r2) {
            return visit(typeVar.getUpperBound());
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Boolean visitWildcardType(Type.WildcardType wildcardType, Void r2) {
            return visit(wildcardType.type);
        }
    };
    Warner overrideWarner = new Warner();
    private Predicate<Symbol> equalsHasCodeFilter = new Predicate() { // from class: xh1
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return Check.e((Symbol) obj);
        }
    };

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Check$1SpecialTreeVisitor, reason: invalid class name */
    public class C1SpecialTreeVisitor extends JCTree.Visitor {
        boolean specialized = false;

        public C1SpecialTreeVisitor() {
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTree(JCTree jCTree) {
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            if ((jCVariableDecl.mods.flags & 16384) != 0) {
                JCTree.JCExpression jCExpression = jCVariableDecl.init;
                if (!(jCExpression instanceof JCTree.JCNewClass) || ((JCTree.JCNewClass) jCExpression).def == null) {
                    return;
                }
                this.specialized = true;
            }
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Check$6, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Attribute$RetentionPolicy;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Kinds$Kind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Lint$LintCategory;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$element$ElementKind;
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$element$NestingKind;

        static {
            int[] iArr = new int[ElementKind.values().length];
            $SwitchMap$javax$lang$model$element$ElementKind = iArr;
            try {
                iArr[ElementKind.FIELD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.METHOD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.ENUM_CONSTANT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Lint.LintCategory.values().length];
            $SwitchMap$com$sun$tools$javac$code$Lint$LintCategory = iArr2;
            try {
                iArr2[Lint.LintCategory.UNCHECKED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Lint$LintCategory[Lint.LintCategory.VARARGS.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[NestingKind.values().length];
            $SwitchMap$javax$lang$model$element$NestingKind = iArr3;
            try {
                iArr3[NestingKind.ANONYMOUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$lang$model$element$NestingKind[NestingKind.LOCAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$javax$lang$model$element$NestingKind[NestingKind.TOP_LEVEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$javax$lang$model$element$NestingKind[NestingKind.MEMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr4 = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr4;
            try {
                iArr4[TypeTag.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr5 = new int[Attribute.RetentionPolicy.values().length];
            $SwitchMap$com$sun$tools$javac$code$Attribute$RetentionPolicy = iArr5;
            try {
                iArr5[Attribute.RetentionPolicy.RUNTIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Attribute$RetentionPolicy[Attribute.RetentionPolicy.CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            int[] iArr6 = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr6;
            try {
                iArr6[JCTree.Tag.SELECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.IDENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PATTERNCASELABEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CONSTANTCASELABEL.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
            int[] iArr7 = new int[Kinds.Kind.values().length];
            $SwitchMap$com$sun$tools$javac$code$Kinds$Kind = iArr7;
            try {
                iArr7[Kinds.Kind.VAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.MTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.TYP.ordinal()] = 3;
            } catch (NoSuchFieldError unused20) {
            }
        }
    }

    public interface CheckContext {
        Warner checkWarner(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2);

        boolean compatible(Type type, Type type2, Warner warner);

        DeferredAttr.DeferredAttrContext deferredAttrContext();

        InferenceContext inferenceContext();

        void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic);
    }

    public class ClashFilter implements Predicate<Symbol> {
        Type site;

        public ClashFilter(Type type) {
            this.site = type;
        }

        public boolean shouldSkip(Symbol symbol) {
            return (symbol.flags() & Flags.CLASH) != 0 && symbol.owner == this.site.tsym;
        }

        @Override // java.util.function.Predicate
        public boolean test(Symbol symbol) {
            return symbol.kind == Kinds.Kind.MTH && (symbol.flags() & 4096) == 0 && !shouldSkip(symbol) && symbol.isInheritedIn(this.site.tsym, Check.this.types) && !symbol.isConstructor();
        }
    }

    public class ConversionWarner extends Warner {
        final Type expected;
        final Type found;
        final String uncheckedKey;

        public ConversionWarner(JCDiagnostic.DiagnosticPosition diagnosticPosition, String str, Type type, Type type2) {
            super(diagnosticPosition);
            this.uncheckedKey = str;
            this.found = type;
            this.expected = type2;
        }

        @Override // com.sun.tools.javac.util.Warner
        public void warn(Lint.LintCategory lintCategory) {
            boolean z = this.warned;
            super.warn(lintCategory);
            if (z) {
                return;
            }
            int i = AnonymousClass6.$SwitchMap$com$sun$tools$javac$code$Lint$LintCategory[lintCategory.ordinal()];
            if (i == 1) {
                Check.this.warnUnchecked(pos(), CompilerProperties.LintWarnings.ProbFoundReq(Check.this.diags.fragment(this.uncheckedKey, new Object[0]), this.found, this.expected));
                return;
            }
            if (i != 2) {
                s22.a("Unexpected lint: ", lintCategory);
                return;
            }
            if (Check.this.method == null || Check.this.method.attribute(Check.this.syms.trustMeType.tsym) == null) {
                return;
            }
            Check check = Check.this;
            if (!check.isTrustMeAllowedOnMethod(check.method) || Check.this.types.isReifiable(Check.this.method.type.mo71getParameterTypes().last())) {
                return;
            }
            Check.this.log.warning(pos(), CompilerProperties.LintWarnings.VarargsUnsafeUseVarargsParam(Check.this.method.params.last()));
        }
    }

    public class CycleChecker extends TreeScanner {
        Set<Symbol> seenClasses = new HashSet();
        boolean errorFound = false;
        boolean partialCheck = false;

        public CycleChecker() {
        }

        public static /* synthetic */ boolean a(Symbol symbol) {
            return !symbol.type.isErroneous();
        }

        private void checkSymbol(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
            if (symbol != null) {
                Kinds.Kind kind = symbol.kind;
                Kinds.Kind kind2 = Kinds.Kind.TYP;
                if (kind == kind2) {
                    Env<AttrContext> env = Check.this.enter.getEnv((Symbol.TypeSymbol) symbol);
                    if (env == null) {
                        if (symbol.kind == kind2) {
                            checkClass(diagnosticPosition, symbol, List.nil());
                            return;
                        }
                        return;
                    } else {
                        DiagnosticSource diagnosticSourceCurrentSource = Check.this.log.currentSource();
                        try {
                            Check.this.log.useSource(env.toplevel.sourcefile);
                            scan(env.tree);
                            return;
                        } finally {
                            Check.this.log.useSource(diagnosticSourceCurrentSource.getFile());
                        }
                    }
                }
            }
            if (symbol == null || symbol.kind != Kinds.Kind.PCK) {
                this.partialCheck = true;
            }
        }

        public void checkClass(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, List<JCTree> list) {
            if ((symbol.flags_field & 1073741824) != 0) {
                return;
            }
            if (this.seenClasses.contains(symbol)) {
                this.errorFound = true;
                Check.this.log.error(diagnosticPosition, CompilerProperties.Errors.CyclicInheritance(symbol));
                final Class<Symbol.ClassSymbol> cls = Symbol.ClassSymbol.class;
                Stream<R> map = this.seenClasses.stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.u
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Check.CycleChecker.a((Symbol) obj);
                    }
                }).filter(new Predicate() { // from class: cj1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return cls.isInstance((Symbol) obj);
                    }
                }).map(new Function() { // from class: dj1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return (Symbol.ClassSymbol) cls.cast((Symbol) obj);
                    }
                });
                final Check check = Check.this;
                map.forEach(new Consumer() { // from class: com.sun.tools.javac.comp.v
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        check.handleCyclic((Symbol.ClassSymbol) obj);
                    }
                });
                return;
            }
            if (symbol.type.isErroneous()) {
                return;
            }
            try {
                this.seenClasses.add(symbol);
                if (symbol.type.hasTag(TypeTag.CLASS)) {
                    if (!list.nonEmpty()) {
                        Type.ClassType classType = (Type.ClassType) symbol.type;
                        Type type = classType.supertype_field;
                        if (type != null && classType.interfaces_field != null) {
                            checkSymbol(diagnosticPosition, type.tsym);
                            Iterator<Type> it = classType.interfaces_field.iterator();
                            while (it.hasNext()) {
                                checkSymbol(diagnosticPosition, it.next().tsym);
                            }
                        }
                        this.partialCheck = true;
                        return;
                    }
                    scan(list);
                    Symbol symbol2 = symbol.owner;
                    if (symbol2.kind == Kinds.Kind.TYP) {
                        checkSymbol(diagnosticPosition, symbol2);
                    }
                }
            } finally {
                this.seenClasses.remove(symbol);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            List<JCTree> listNil = List.nil();
            if (jCClassDecl.getExtendsClause() != null) {
                listNil = listNil.prepend(jCClassDecl.getExtendsClause());
            }
            if (jCClassDecl.getImplementsClause() != null) {
                Iterator<JCTree.JCExpression> it = jCClassDecl.getImplementsClause().iterator();
                while (it.hasNext()) {
                    listNil = listNil.prepend(it.next());
                }
            }
            checkClass(jCClassDecl.pos(), jCClassDecl.sym, listNil);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIdent(JCTree.JCIdent jCIdent) {
            checkSymbol(jCIdent.pos(), jCIdent.sym);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
            super.visitSelect(jCFieldAccess);
            checkSymbol(jCFieldAccess.pos(), jCFieldAccess.sym);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
            scan(jCTypeApply.clazz);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeArray(JCTree.JCArrayTypeTree jCArrayTypeTree) {
            scan(jCArrayTypeTree.elemtype);
        }
    }

    public class DefaultMethodClashFilter implements Predicate<Symbol> {
        Type site;

        public DefaultMethodClashFilter(Type type) {
            this.site = type;
        }

        @Override // java.util.function.Predicate
        public boolean test(Symbol symbol) {
            return symbol.kind == Kinds.Kind.MTH && (symbol.flags() & Flags.DEFAULT) != 0 && symbol.isInheritedIn(this.site.tsym, Check.this.types) && !symbol.isConstructor();
        }
    }

    public static class NestedCheckContext implements CheckContext {
        CheckContext enclosingContext;

        public NestedCheckContext(CheckContext checkContext) {
            this.enclosingContext = checkContext;
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public Warner checkWarner(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2) {
            return this.enclosingContext.checkWarner(diagnosticPosition, type, type2);
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public boolean compatible(Type type, Type type2, Warner warner) {
            return this.enclosingContext.compatible(type, type2, warner);
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public DeferredAttr.DeferredAttrContext deferredAttrContext() {
            return this.enclosingContext.deferredAttrContext();
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public InferenceContext inferenceContext() {
            return this.enclosingContext.inferenceContext();
        }

        @Override // com.sun.tools.javac.comp.Check.CheckContext
        public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
            this.enclosingContext.report(diagnosticPosition, jCDiagnostic);
        }
    }

    public class PotentiallyAmbiguousFilter extends ClashFilter {
        public PotentiallyAmbiguousFilter(Type type) {
            super(type);
        }

        @Override // com.sun.tools.javac.comp.Check.ClashFilter
        public boolean shouldSkip(Symbol symbol) {
            return symbol.owner.type.tsym == Check.this.syms.objectType.tsym || super.shouldSkip(symbol);
        }
    }

    public class SuperThisChecker extends TreeScanner {
        private static final int MATCH_SCAN_DEPTH = 3;
        private boolean constructor;
        private JCTree.JCReturn earlyReturn;
        private boolean firstStatement;
        private Name initCall;
        private int scanDepth;

        private SuperThisChecker() {
        }

        public void check(JCTree.JCClassDecl jCClassDecl) {
            scan(jCClassDecl.defs);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            this.scanDepth++;
            try {
                super.scan(jCTree);
            } finally {
                this.scanDepth--;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
            Name name = TreeInfo.name(jCMethodInvocation.meth);
            if (name == Check.this.names._super || name == Check.this.names._this) {
                if (!this.constructor) {
                    Check.this.log.error(jCMethodInvocation.pos(), CompilerProperties.Errors.CallMustOnlyAppearInCtor);
                } else if (this.scanDepth != 3) {
                    Check.this.log.error(jCMethodInvocation.pos(), CompilerProperties.Errors.CtorCallsNotAllowedHere);
                } else if (this.initCall != null) {
                    Check.this.log.error(jCMethodInvocation.pos(), CompilerProperties.Errors.RedundantSuperclassInit);
                } else {
                    if (!this.firstStatement) {
                        Check.this.preview.checkSourceLevel(jCMethodInvocation.pos(), Source.Feature.FLEXIBLE_CONSTRUCTORS);
                    }
                    this.initCall = name;
                }
            }
            super.visitApply(jCMethodInvocation);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            boolean z = this.constructor;
            boolean z2 = this.firstStatement;
            JCTree.JCReturn jCReturn = this.earlyReturn;
            Name name = this.initCall;
            int i = this.scanDepth;
            this.constructor = false;
            this.firstStatement = false;
            this.earlyReturn = null;
            this.initCall = null;
            this.scanDepth = 0;
            try {
                super.visitLambda(jCLambda);
            } finally {
                this.constructor = z;
                this.firstStatement = z2;
                this.earlyReturn = jCReturn;
                this.initCall = name;
                this.scanDepth = i;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            Assert.check(!this.constructor);
            Assert.check(this.earlyReturn == null);
            Assert.check(this.initCall == null);
            Assert.check(this.scanDepth == 1);
            this.constructor = TreeInfo.isConstructor(jCMethodDecl);
            try {
                JCTree.JCBlock jCBlock = jCMethodDecl.body;
                if (jCBlock != null) {
                    this.firstStatement = true;
                    for (List list = jCBlock.stats; list.nonEmpty(); list = list.tail) {
                        scan((JCTree) list.head);
                        this.firstStatement = false;
                    }
                }
                if (this.constructor && this.earlyReturn != null && this.initCall != null) {
                    Check.this.log.error(this.earlyReturn.pos(), CompilerProperties.Errors.ReturnBeforeSuperclassInitialized);
                }
            } finally {
                this.firstStatement = false;
                this.constructor = false;
                this.earlyReturn = null;
                this.initCall = null;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitReturn(JCTree.JCReturn jCReturn) {
            if (this.constructor && this.initCall == null && this.earlyReturn == null) {
                this.earlyReturn = jCReturn;
            }
            super.visitReturn(jCReturn);
        }
    }

    public class Validator extends JCTree.Visitor {
        boolean checkRaw;
        Env<AttrContext> env;
        boolean isOuter;

        public Validator(Env<AttrContext> env) {
            this.env = env;
        }

        public void validateTree(JCTree jCTree, boolean z, boolean z2) {
            if (jCTree != null) {
                boolean z3 = this.checkRaw;
                this.checkRaw = z;
                this.isOuter = z2;
                try {
                    jCTree.accept(this);
                    if (z) {
                        Check.this.checkRaw(jCTree, this.env);
                    }
                } catch (Symbol.CompletionFailure e) {
                    Check.this.completionError(jCTree.pos(), e);
                } finally {
                    this.checkRaw = z3;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void validateTrees(List<? extends JCTree> list, boolean z, boolean z2) {
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                validateTree((JCTree) list2.head, z, z2);
            }
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
            jCAnnotatedType.underlyingType.accept(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
            if (jCFieldAccess.type.hasTag(TypeTag.CLASS)) {
                visitSelectInternal(jCFieldAccess);
                if (jCFieldAccess.selected.type.isParameterized() && jCFieldAccess.type.tsym.type.getTypeArguments().nonEmpty()) {
                    Check.this.log.error(jCFieldAccess.pos(), CompilerProperties.Errors.ImproperlyFormedTypeParamMissing);
                }
            }
        }

        public void visitSelectInternal(JCTree.JCFieldAccess jCFieldAccess) {
            if (jCFieldAccess.type.tsym.isStatic() && jCFieldAccess.selected.type.isParameterized()) {
                Check.this.log.error(jCFieldAccess.pos(), CompilerProperties.Errors.CantSelectStaticClassFromParamType);
            } else {
                jCFieldAccess.selected.accept(this);
            }
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTree(JCTree jCTree) {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
            if (jCTypeApply.type.hasTag(TypeTag.CLASS)) {
                List list = jCTypeApply.arguments;
                List typeArguments = jCTypeApply.type.tsym.type.getTypeArguments();
                Type typeFirstIncompatibleTypeArg = Check.this.firstIncompatibleTypeArg(jCTypeApply.type);
                if (typeFirstIncompatibleTypeArg != null) {
                    for (JCTree.JCExpression jCExpression : jCTypeApply.arguments) {
                        if (jCExpression.type == typeFirstIncompatibleTypeArg) {
                            Check.this.log.error(jCExpression, CompilerProperties.Errors.NotWithinBounds(typeFirstIncompatibleTypeArg, (Type) typeArguments.head));
                        }
                        typeArguments = typeArguments.tail;
                    }
                }
                boolean z = jCTypeApply.type.tsym.flatName() == Check.this.names.java_lang_Class;
                for (List typeArguments2 = jCTypeApply.type.tsym.type.getTypeArguments(); list.nonEmpty() && typeArguments2.nonEmpty(); typeArguments2 = typeArguments2.tail) {
                    validateTree((JCTree) list.head, (this.isOuter && z) ? false : true, false);
                    list = list.tail;
                }
                if (jCTypeApply.type.getEnclosingType().isRaw()) {
                    Check.this.log.error(jCTypeApply.pos(), CompilerProperties.Errors.ImproperlyFormedTypeInnerRawParam);
                }
                if (jCTypeApply.clazz.hasTag(JCTree.Tag.SELECT)) {
                    visitSelectInternal((JCTree.JCFieldAccess) jCTypeApply.clazz);
                }
            }
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeArray(JCTree.JCArrayTypeTree jCArrayTypeTree) {
            validateTree(jCArrayTypeTree.elemtype, this.checkRaw, this.isOuter);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeIdent(JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree) {
            if (jCPrimitiveTypeTree.type.hasTag(TypeTag.VOID)) {
                Check.this.log.error(jCPrimitiveTypeTree.pos(), CompilerProperties.Errors.VoidNotAllowedHere);
            }
            super.visitTypeIdent(jCPrimitiveTypeTree);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
            validateTrees(jCTypeParameter.bounds, true, this.isOuter);
            Check.this.checkClassBounds(jCTypeParameter.pos(), jCTypeParameter.type);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitWildcard(JCTree.JCWildcard jCWildcard) {
            JCTree jCTree = jCWildcard.inner;
            if (jCTree != null) {
                validateTree(jCTree, true, this.isOuter);
            }
        }
    }

    public Check(Context context) {
        context.put(checkKey, this);
        this.names = Names.instance(context);
        this.log = Log.instance(context);
        this.rs = Resolve.instance(context);
        this.syms = Symtab.instance(context);
        this.enter = Enter.instance(context);
        this.deferredAttr = DeferredAttr.instance(context);
        this.infer = Infer.instance(context);
        this.types = Types.instance(context);
        this.typeAnnotations = TypeAnnotations.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        Options optionsInstance = Options.instance(context);
        this.lint = Lint.instance(context);
        this.fileManager = (JavaFileManager) context.get(JavaFileManager.class);
        Source sourceInstance = Source.instance(context);
        this.source = sourceInstance;
        this.target = Target.instance(context);
        this.warnOnAnyAccessToMembers = optionsInstance.isSet("warnOnAccessToMembers");
        boolean z = false;
        this.disablePreviewCheck = false;
        this.syntheticNameChar = Target.instance(context).syntheticNameChar();
        this.profile = Profile.instance(context);
        Preview previewInstance = Preview.instance(context);
        this.preview = previewInstance;
        this.allowModules = Source.Feature.MODULES.allowedInSource(sourceInstance);
        this.allowRecords = Source.Feature.RECORDS.allowedInSource(sourceInstance);
        this.allowSealed = Source.Feature.SEALED_CLASSES.allowedInSource(sourceInstance);
        if (previewInstance.isEnabled() && Source.Feature.PRIMITIVE_PATTERNS.allowedInSource(sourceInstance)) {
            z = true;
        }
        this.allowPrimitivePatterns = z;
    }

    public static /* synthetic */ JCTree.JCPatternCaseLabel A(JCTree.JCCaseLabel jCCaseLabel) {
        return (JCTree.JCPatternCaseLabel) jCCaseLabel;
    }

    public static /* synthetic */ boolean B(JCTree.JCCaseLabel jCCaseLabel) {
        return jCCaseLabel instanceof JCTree.JCPatternCaseLabel;
    }

    public static /* synthetic */ boolean C(JCTree.JCCaseLabel jCCaseLabel) {
        return jCCaseLabel instanceof JCTree.JCPatternCaseLabel;
    }

    public static /* synthetic */ boolean D(final Check check, Attribute.Compound compound) {
        check.getClass();
        return Arrays.stream(check.getTargetNames(compound.type.tsym)).anyMatch(new Predicate() { // from class: yi1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Check.c(this.b, (Name) obj);
            }
        });
    }

    public static /* synthetic */ boolean E(JCTree.JCMethodDecl jCMethodDecl, Symbol.RecordComponent recordComponent) {
        Symbol.MethodSymbol methodSymbol = recordComponent.accessor;
        return methodSymbol == jCMethodDecl.sym && (methodSymbol.flags_field & 16777216) == 0;
    }

    public static /* synthetic */ boolean F(Check check, JCTree.JCPatternCaseLabel jCPatternCaseLabel) {
        check.getClass();
        return !check.hasBindings(jCPatternCaseLabel.getPattern());
    }

    public static /* synthetic */ boolean a(Check check, List list, Attribute.TypeCompound typeCompound) {
        check.getClass();
        return check.isRequiresIdentityAnnotation(typeCompound.type.tsym) && ((JCTree.JCExpression) list.get(typeCompound.position.parameter_index)).type != null && ((JCTree.JCExpression) list.get(typeCompound.position.parameter_index)).type.isValueBased();
    }

    private Object asTypeParam(Type type) {
        return type.hasTag(TypeTag.TYPEVAR) ? this.diags.fragment(CompilerProperties.Fragments.TypeParameter(type)) : type;
    }

    private void assertConvertible(JCTree jCTree, Type type, Type type2, Warner warner) {
        if (!this.types.isConvertible(type, type2, warner) && type2.isCompound()) {
            Types types = this.types;
            if (types.isSubtype(type, types.supertype(type2))) {
                Types types2 = this.types;
                types2.isSubtypeUnchecked(type, types2.interfaces(type2), warner);
            }
        }
    }

    private boolean belongsToRestrictedPackage(Symbol symbol) {
        String string = symbol.packge().fullname.toString();
        return string.startsWith("java.") || string.startsWith("javax.") || string.startsWith("sun.") || string.contains(".internal.");
    }

    public static /* synthetic */ boolean c(Check check, Name name) {
        return name == check.names.RECORD_COMPONENT;
    }

    private void checkAccessFromSerializableElementInner(JCTree jCTree, boolean z) {
        Symbol symbol = TreeInfo.symbol(jCTree);
        if (symbol.kind.matches(Kinds.KindSelector.VAL_MTH)) {
            if (symbol.kind == Kinds.Kind.VAR) {
                if ((symbol.flags() & 8589934592L) != 0 || symbol.isDirectlyOrIndirectlyLocal()) {
                    return;
                }
                Name name = symbol.name;
                Names names = this.names;
                if (name == names._this || name == names._super) {
                    return;
                }
            }
            if (this.types.isSubtype(symbol.owner.type, this.syms.serializableType) || !isEffectivelyNonPublic(symbol)) {
                return;
            }
            JCDiagnostic.DiagnosticFlag diagnosticFlag = this.warnOnAnyAccessToMembers ? JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED : null;
            if (!z) {
                this.log.warning(diagnosticFlag, jCTree.pos(), CompilerProperties.LintWarnings.AccessToMemberFromSerializableElement(symbol));
            } else if (belongsToRestrictedPackage(symbol)) {
                this.log.warning(diagnosticFlag, jCTree.pos(), CompilerProperties.LintWarnings.AccessToMemberFromSerializableLambda(symbol));
            }
        }
    }

    private void checkClassOverrideEqualsAndHash(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.ClassSymbol classSymbol) {
        if (this.lint.isEnabled(Lint.LintCategory.OVERRIDES)) {
            Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) this.syms.objectType.tsym.members().findFirst(this.names.equals);
            Symbol.MethodSymbol methodSymbol2 = (Symbol.MethodSymbol) this.syms.objectType.tsym.members().findFirst(this.names.hashCode);
            Symbol.MethodSymbol methodSymbolImplementation = this.types.implementation(methodSymbol, classSymbol, false, this.equalsHasCodeFilter);
            boolean z = methodSymbolImplementation != null && methodSymbolImplementation.owner == classSymbol;
            boolean z2 = this.types.implementation(methodSymbol2, classSymbol, false, this.equalsHasCodeFilter) != methodSymbol2;
            if (!z || z2) {
                return;
            }
            this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.OverrideEqualsButNotHashcode(classSymbol));
        }
    }

    private void checkCyclicConstructor(JCTree.JCClassDecl jCClassDecl, Symbol symbol, Map<Symbol, Symbol> map) {
        if (symbol != null) {
            long j = symbol.flags_field;
            if ((j & 1073741824) == 0) {
                if ((j & 134217728) != 0) {
                    this.log.error(TreeInfo.diagnosticPositionFor(symbol, jCClassDecl, false, new Predicate() { // from class: li1
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return ((JCTree) obj).hasTag(JCTree.Tag.IDENT);
                        }
                    }), CompilerProperties.Errors.RecursiveCtorInvocation);
                } else {
                    symbol.flags_field = j | 134217728;
                    checkCyclicConstructor(jCClassDecl, map.remove(symbol), map);
                    symbol.flags_field &= -134217729;
                }
                symbol.flags_field |= 1073741824;
            }
        }
    }

    private boolean checkExtends(Type type, Type type2) {
        if (type.isUnbound()) {
            return true;
        }
        if (!type.hasTag(TypeTag.WILDCARD)) {
            return this.types.isSubtype(this.types.cvarUpperBound(type), type2);
        }
        if (type.isExtendsBound()) {
            Types types = this.types;
            return types.isCastable(type2, types.wildUpperBound(type), this.types.noWarnings);
        }
        if (!type.isSuperBound()) {
            return true;
        }
        Types types2 = this.types;
        return !types2.notSoftSubtype(types2.wildLowerBound(type), type2);
    }

    private boolean checkIfIdentityIsExpected(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Lint lint) {
        if (type == null || lint == null || !lint.isEnabled(Lint.LintCategory.IDENTITY)) {
            return false;
        }
        RequiresIdentityVisitor requiresIdentityVisitor = new RequiresIdentityVisitor();
        requiresIdentityVisitor.visit(type, new HashSet());
        if (!requiresIdentityVisitor.requiresWarning) {
            return false;
        }
        this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.AttemptToUseValueBasedWhereIdentityExpected);
        return true;
    }

    private void checkIfTypeParamsRequiresIdentity(SymbolMetadata symbolMetadata, final List<JCTree.JCExpression> list, Lint lint) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (JCTree.JCExpression jCExpression : list) {
            checkIfIdentityIsExpected(jCExpression.pos(), jCExpression.type, lint);
        }
        if (symbolMetadata != null) {
            symbolMetadata.getTypeAttributes().stream().filter(new Predicate() { // from class: zh1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Check.a(this.b, list, (Attribute.TypeCompound) obj);
                }
            }).forEach(new Consumer() { // from class: ai1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.log.warning(((JCTree.JCExpression) list.get(((Attribute.TypeCompound) obj).position.parameter_index)).pos(), CompilerProperties.LintWarnings.AttemptToUseValueBasedWhereIdentityExpected);
                }
            });
        }
    }

    private boolean checkNameClash(Symbol.ClassSymbol classSymbol, Symbol symbol, Symbol symbol2) {
        ClashFilter clashFilter = new ClashFilter(classSymbol.type);
        if (!clashFilter.test(symbol) || !clashFilter.test(symbol2)) {
            return false;
        }
        Types types = this.types;
        return types.hasSameArgs(symbol.erasure(types), symbol2.erasure(this.types));
    }

    private void checkNonCyclic1(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, List<Type.TypeVar> list) {
        TypeTag typeTag = TypeTag.TYPEVAR;
        if (!type.hasTag(typeTag) || (type.tsym.flags() & 268435456) == 0) {
            if (list.contains(type)) {
                ((Type.TypeVar) type).setUpperBound(this.types.createErrorType(type));
                this.log.error(diagnosticPosition, CompilerProperties.Errors.CyclicInheritance(type));
            } else if (type.hasTag(typeTag)) {
                Type.TypeVar typeVar = (Type.TypeVar) type;
                List<Type.TypeVar> listPrepend = list.prepend(typeVar);
                Iterator<Type> it = this.types.getBounds(typeVar).iterator();
                while (it.hasNext()) {
                    checkNonCyclic1(diagnosticPosition, it.next(), listPrepend);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0093  */
    /* JADX WARN: Code duplicated, block: B:41:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:44:0x00a8  */
    /* JADX WARN: Multi-variable type inference failed */
    private boolean checkNonCyclicInternal(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        boolean zCheckNonCyclicInternal;
        Symbol.TypeSymbol typeSymbol = type.tsym;
        long j = typeSymbol.flags_field;
        if ((j & 1073741824) != 0) {
            return true;
        }
        if ((j & 134217728) == 0) {
            if (!typeSymbol.type.isErroneous()) {
                try {
                    typeSymbol.flags_field = 134217728 | typeSymbol.flags_field;
                    if (typeSymbol.type.hasTag(TypeTag.CLASS)) {
                        Type.ClassType classType = (Type.ClassType) typeSymbol.type;
                        List list = classType.interfaces_field;
                        zCheckNonCyclicInternal = true;
                        if (list != null) {
                            while (list.nonEmpty()) {
                                zCheckNonCyclicInternal &= checkNonCyclicInternal(diagnosticPosition, (Type) list.head);
                                list = list.tail;
                            }
                        }
                        Type type2 = classType.supertype_field;
                        if (type2 != null && type2 != null && type2.hasTag(TypeTag.CLASS)) {
                            zCheckNonCyclicInternal &= checkNonCyclicInternal(diagnosticPosition, type2);
                        }
                        Symbol symbol = typeSymbol.owner;
                        if (symbol.kind == Kinds.Kind.TYP) {
                            zCheckNonCyclicInternal &= checkNonCyclicInternal(diagnosticPosition, symbol.type);
                        }
                    } else {
                        zCheckNonCyclicInternal = true;
                    }
                    typeSymbol.flags_field &= -134217729;
                } catch (Throwable th) {
                    typeSymbol.flags_field = (-134217729) & typeSymbol.flags_field;
                    throw th;
                }
            }
            if (zCheckNonCyclicInternal) {
                zCheckNonCyclicInternal = (typeSymbol.flags_field & 268435456) != 0 && typeSymbol.isCompleted();
            }
            if (zCheckNonCyclicInternal) {
                typeSymbol.flags_field |= 1073741824;
            }
            return zCheckNonCyclicInternal;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.CyclicInheritance(typeSymbol));
        handleCyclic((Symbol.ClassSymbol) typeSymbol);
        zCheckNonCyclicInternal = true;
        if (zCheckNonCyclicInternal) {
            zCheckNonCyclicInternal = (typeSymbol.flags_field & 268435456) != 0 && typeSymbol.isCompleted();
        }
        if (zCheckNonCyclicInternal) {
            typeSymbol.flags_field |= 1073741824;
        }
        return zCheckNonCyclicInternal;
    }

    private boolean checkTypeContainsImportableElement(Symbol.TypeSymbol typeSymbol, Symbol.TypeSymbol typeSymbol2, Symbol.PackageSymbol packageSymbol, Name name, Set<Symbol> set) {
        if (typeSymbol != null && set.add(typeSymbol)) {
            if (checkTypeContainsImportableElement(this.types.supertype(typeSymbol.type).tsym, typeSymbol2, packageSymbol, name, set)) {
                return true;
            }
            Iterator<Type> it = this.types.interfaces(typeSymbol.type).iterator();
            while (it.hasNext()) {
                if (checkTypeContainsImportableElement(it.next().tsym, typeSymbol2, packageSymbol, name, set)) {
                    return true;
                }
            }
            for (Symbol symbol : typeSymbol.members().getSymbolsByName(name)) {
                if (symbol.isStatic() && importAccessible(symbol, packageSymbol) && symbol.isMemberOf(typeSymbol2, this.types)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkUniqueImport(JCDiagnostic.DiagnosticPosition diagnosticPosition, Scope scope, Scope scope2, Scope scope3, final Symbol symbol, boolean z) {
        Predicate<Symbol> predicate = new Predicate() { // from class: bi1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Check.v(symbol, (Symbol) obj);
            }
        };
        Symbol symbolFindFirst = scope.findFirst(symbol.name, predicate);
        Symbol symbolFindFirst2 = (symbolFindFirst != null || z) ? null : scope2.findFirst(symbol.name, predicate);
        if (symbolFindFirst == null && symbolFindFirst2 == null) {
            Symbol symbolFindFirst3 = scope3.findFirst(symbol.name, predicate);
            if (symbolFindFirst3 == null) {
                return true;
            }
            this.log.error(diagnosticPosition, CompilerProperties.Errors.AlreadyDefinedThisUnit(symbolFindFirst3));
            return false;
        }
        Log log = this.log;
        if (symbolFindFirst != null) {
            log.error(diagnosticPosition, CompilerProperties.Errors.AlreadyDefinedSingleImport(symbolFindFirst));
        } else {
            log.error(diagnosticPosition, CompilerProperties.Errors.AlreadyDefinedStaticSingleImport(symbolFindFirst2));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void checkVisible(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Symbol.PackageSymbol packageSymbol, boolean z) {
        List<Symbol.ModuleSymbol> list;
        if (!isAPISymbol(symbol) && !z) {
            this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.LeaksNotAccessible(Kinds.kindName(symbol), symbol, symbol.packge().modle));
            return;
        }
        Symbol.PackageSymbol packageSymbolPackge = symbol.packge();
        Directive.ExportsDirective exportsDirectiveFindExport = findExport(packageSymbolPackge);
        Directive.ExportsDirective exportsDirectiveFindExport2 = findExport(packageSymbol);
        if (exportsDirectiveFindExport == null) {
            this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.LeaksNotAccessibleUnexported(Kinds.kindName(symbol), symbol, symbol.packge().modle));
            return;
        }
        List<Symbol.ModuleSymbol> list2 = exportsDirectiveFindExport.modules;
        if (list2 != null && ((list = exportsDirectiveFindExport2.modules) == null || !list2.containsAll(list))) {
            this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.LeaksNotAccessibleUnexportedQualified(Kinds.kindName(symbol), symbol, symbol.packge().modle));
        }
        Symbol.ModuleSymbol moduleSymbol = packageSymbolPackge.modle;
        Symbol.ModuleSymbol moduleSymbol2 = packageSymbol.modle;
        if (moduleSymbol == moduleSymbol2 || moduleSymbol == this.syms.java_base) {
            return;
        }
        List listOf = List.of(moduleSymbol2);
        while (listOf.nonEmpty()) {
            Symbol.ModuleSymbol moduleSymbol3 = (Symbol.ModuleSymbol) listOf.head;
            listOf = listOf.tail;
            if (moduleSymbol3 == packageSymbolPackge.modle) {
                return;
            }
            if ((moduleSymbol3.flags() & 4503599627370496L) == 0) {
                for (Directive.RequiresDirective requiresDirective : moduleSymbol3.requires) {
                    if (requiresDirective.isTransitive()) {
                        listOf = listOf.prepend(requiresDirective.module);
                    }
                }
            }
        }
        this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.LeaksNotAccessibleNotRequiredTransitive(Kinds.kindName(symbol), symbol, symbol.packge().modle));
    }

    private void closure(Type type, Map<Symbol.TypeSymbol, Type> map, Map<Symbol.TypeSymbol, Type> map2) {
        if (type.hasTag(TypeTag.CLASS) && map.get(type.tsym) == null && map2.put(type.tsym, type) == null) {
            closure(this.types.supertype(type), map, map2);
            Iterator<Type> it = this.types.interfaces(type).iterator();
            while (it.hasNext()) {
                closure(it.next(), map, map2);
            }
        }
    }

    public static /* synthetic */ boolean d(Symbol symbol) {
        return symbol.kind == Kinds.Kind.TYP;
    }

    private Name[] defaultTargetMetaInfo() {
        if (this.dfltTargetMeta == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.names.PACKAGE);
            arrayList.add(this.names.TYPE);
            arrayList.add(this.names.FIELD);
            arrayList.add(this.names.METHOD);
            arrayList.add(this.names.CONSTRUCTOR);
            arrayList.add(this.names.ANNOTATION_TYPE);
            arrayList.add(this.names.LOCAL_VARIABLE);
            arrayList.add(this.names.PARAMETER);
            if (this.allowRecords) {
                arrayList.add(this.names.RECORD_COMPONENT);
            }
            if (this.allowModules) {
                arrayList.add(this.names.MODULE);
            }
            this.dfltTargetMeta = (Name[]) arrayList.toArray(new Name[0]);
        }
        return this.dfltTargetMeta;
    }

    public static /* synthetic */ boolean e(Symbol symbol) {
        return Symbol.MethodSymbol.implementation_filter.test(symbol) && (symbol.flags() & 35184372088832L) == 0;
    }

    public static /* synthetic */ JCDiagnostic.DiagnosticPosition f(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        return diagnosticPosition;
    }

    private Directive.ExportsDirective findExport(Symbol.PackageSymbol packageSymbol) {
        for (Directive.ExportsDirective exportsDirective : packageSymbol.modle.exports) {
            if (exportsDirective.packge == packageSymbol) {
                return exportsDirective;
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:49:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:51:0x0113  */
    /* JADX WARN: Code duplicated, block: B:67:0x00f5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x016d A[SYNTHETIC] */
    private Symbol firstDirectIncompatibility(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2, Type type3) {
        Symbol.MethodSymbol methodSymbolImplementation;
        boolean zIsSameType;
        Log log;
        Iterator<Symbol> it = type.tsym.members().getSymbols(Scope.LookupKind.NON_RECURSIVE).iterator();
        while (true) {
            Type typeMemberType = null;
            if (!it.hasNext()) {
                return null;
            }
            Symbol next = it.next();
            if (next.kind == Kinds.Kind.MTH && next.isInheritedIn(type3.tsym, this.types)) {
                long j = 4096;
                long j2 = 0;
                if ((next.flags() & 4096) == 0 && ((methodSymbolImplementation = ((Symbol.MethodSymbol) next).implementation(type3.tsym, this.types, false)) == null || (methodSymbolImplementation.flags() & 1024) != 0)) {
                    for (Symbol symbol : type2.tsym.members().getSymbolsByName(next.name)) {
                        if (next != symbol && symbol.kind == Kinds.Kind.MTH && symbol.isInheritedIn(type3.tsym, this.types) && (symbol.flags() & j) == j2) {
                            if (typeMemberType == null) {
                                typeMemberType = this.types.memberType(type, next);
                            }
                            Type typeMemberType2 = this.types.memberType(type2, symbol);
                            if (this.types.overrideEquivalent(typeMemberType, typeMemberType2)) {
                                List<Type> typeArguments = typeMemberType.getTypeArguments();
                                List<Type> typeArguments2 = typeMemberType2.getTypeArguments();
                                Type typeMo73getReturnType = typeMemberType.mo73getReturnType();
                                Type typeSubst = this.types.subst(typeMemberType2.mo73getReturnType(), typeArguments2, typeArguments);
                                if (this.types.isSameType(typeMo73getReturnType, typeSubst)) {
                                    continue;
                                } else if (typeMo73getReturnType.isPrimitiveOrVoid() || typeSubst.isPrimitiveOrVoid()) {
                                    if (checkCommonOverriderIn(next, symbol, type3)) {
                                        zIsSameType = this.types.isSameType(type, type2);
                                        log = this.log;
                                        if (zIsSameType) {
                                            log.error(diagnosticPosition, CompilerProperties.Errors.IncompatibleDiffRetSameType(type, symbol.name, this.types.memberType(type2, symbol).mo71getParameterTypes()));
                                            return symbol;
                                        }
                                        log.error(diagnosticPosition, CompilerProperties.Errors.TypesIncompatible(type, type2, CompilerProperties.Fragments.IncompatibleDiffRet(symbol.name, this.types.memberType(type2, symbol).mo71getParameterTypes())));
                                        return symbol;
                                    }
                                } else {
                                    Types types = this.types;
                                    if (types.covariantReturnType(typeMo73getReturnType, typeSubst, types.noWarnings)) {
                                        continue;
                                    } else {
                                        Types types2 = this.types;
                                        if (types2.covariantReturnType(typeSubst, typeMo73getReturnType, types2.noWarnings)) {
                                            continue;
                                        } else if (checkCommonOverriderIn(next, symbol, type3)) {
                                            zIsSameType = this.types.isSameType(type, type2);
                                            log = this.log;
                                            if (zIsSameType) {
                                                log.error(diagnosticPosition, CompilerProperties.Errors.IncompatibleDiffRetSameType(type, symbol.name, this.types.memberType(type2, symbol).mo71getParameterTypes()));
                                                return symbol;
                                            }
                                            log.error(diagnosticPosition, CompilerProperties.Errors.TypesIncompatible(type, type2, CompilerProperties.Fragments.IncompatibleDiffRet(symbol.name, this.types.memberType(type2, symbol).mo71getParameterTypes())));
                                            return symbol;
                                        }
                                    }
                                }
                            } else if (checkNameClash((Symbol.ClassSymbol) type3.tsym, next, symbol) && !checkCommonOverriderIn(next, symbol, type3)) {
                                this.log.error(diagnosticPosition, CompilerProperties.Errors.NameClashSameErasureNoOverride(next.name, this.types.memberType(type3, next).asMethodType().mo71getParameterTypes(), next.location(), symbol.name, this.types.memberType(type3, symbol).asMethodType().mo71getParameterTypes(), symbol.location()));
                                return symbol;
                            }
                        }
                        j = 4096;
                        j2 = 0;
                    }
                }
            }
        }
    }

    private Symbol firstIncompatibility(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2, Type type3) {
        HashMap map;
        HashMap map2 = new HashMap();
        closure(type, map2);
        if (type == type2) {
            map = map2;
        } else {
            map = new HashMap();
            closure(type2, map2, map);
        }
        for (Type type4 : map2.values()) {
            Iterator<Type> it = map.values().iterator();
            while (it.hasNext()) {
                Symbol symbolFirstDirectIncompatibility = firstDirectIncompatibility(diagnosticPosition, type4, it.next(), type3);
                if (symbolFirstDirectIncompatibility != null) {
                    return symbolFirstDirectIncompatibility;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public Type firstIncompatibleTypeArg(Type type) {
        List<Type> listAllparams = type.tsym.type.allparams();
        List<Type> listAllparams2 = type.allparams();
        List typeArguments = type.getTypeArguments();
        ListBuffer listBuffer = new ListBuffer();
        for (List typeArguments2 = type.tsym.type.getTypeArguments(); typeArguments.nonEmpty() && typeArguments2.nonEmpty(); typeArguments2 = typeArguments2.tail) {
            listBuffer.append(this.types.subst(((Type) typeArguments2.head).getUpperBound(), listAllparams, listAllparams2));
            typeArguments = typeArguments.tail;
        }
        List typeArguments3 = type.getTypeArguments();
        Types types = this.types;
        for (List listSubstBounds = types.substBounds(listAllparams, listAllparams, types.capture(type).allparams()); typeArguments3.nonEmpty() && listSubstBounds.nonEmpty(); listSubstBounds = listSubstBounds.tail) {
            ((Type) typeArguments3.head).withTypeVar((Type.TypeVar) listSubstBounds.head);
            typeArguments3 = typeArguments3.tail;
        }
        List typeArguments4 = type.getTypeArguments();
        for (List list = listBuffer.toList(); typeArguments4.nonEmpty() && list.nonEmpty(); list = list.tail) {
            Type type2 = (Type) typeArguments4.head;
            if (!isTypeArgErroneous(type2) && !((Type) list.head).isErroneous() && !checkExtends(type2, (Type) list.head)) {
                return (Type) typeArguments4.head;
            }
            typeArguments4 = typeArguments4.tail;
        }
        List typeArguments5 = type.getTypeArguments();
        List list2 = listBuffer.toList();
        for (Type type3 : this.types.capture(type).getTypeArguments()) {
            if (type3.hasTag(TypeTag.TYPEVAR) && type3.getUpperBound().isErroneous() && !((Type) list2.head).isErroneous() && !isTypeArgErroneous((Type) typeArguments5.head)) {
                return (Type) typeArguments5.head;
            }
            list2 = list2.tail;
            typeArguments5 = typeArguments5.tail;
        }
        return null;
    }

    public static /* synthetic */ boolean g(Check check, Type type, Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2) {
        check.getClass();
        return methodSymbol.overrides(methodSymbol2, type.tsym, check.types, false);
    }

    private Set<Name> getDefaultTargetSet() {
        if (this.defaultTargets == null) {
            this.defaultTargets = Collections.unmodifiableSet(new HashSet(Arrays.asList(defaultTargetMetaInfo())));
        }
        return this.defaultTargets;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [A, com.sun.tools.javac.code.Type] */
    public void handleCyclic(Symbol.ClassSymbol classSymbol) {
        Types types;
        List listInterfaces = this.types.interfaces(classSymbol.type);
        while (true) {
            boolean zNonEmpty = listInterfaces.nonEmpty();
            types = this.types;
            if (!zNonEmpty) {
                break;
            }
            listInterfaces.head = types.createErrorType((Symbol.ClassSymbol) ((Type) listInterfaces.head).tsym, Type.noType);
            listInterfaces = listInterfaces.tail;
        }
        Type typeSupertype = types.supertype(classSymbol.type);
        if (typeSupertype.hasTag(TypeTag.CLASS)) {
            ((Type.ClassType) classSymbol.type).supertype_field = this.types.createErrorType((Symbol.ClassSymbol) typeSupertype.tsym, Type.noType);
        }
        classSymbol.type = this.types.createErrorType(classSymbol, classSymbol.type);
        classSymbol.flags_field |= 1073741824;
    }

    public static /* synthetic */ boolean i(final Check check, HashMap map, Type type, Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2) {
        check.getClass();
        final java.util.List listSingletonList = (java.util.List) map.get(methodSymbol);
        final java.util.List listSingletonList2 = (java.util.List) map.get(methodSymbol2);
        if (listSingletonList != null && listSingletonList.isEmpty()) {
            return true;
        }
        if (listSingletonList2 != null && listSingletonList2.isEmpty()) {
            return true;
        }
        if (listSingletonList == null) {
            listSingletonList = Collections.singletonList(methodSymbol);
        }
        if (listSingletonList2 == null) {
            listSingletonList2 = Collections.singletonList(methodSymbol2);
        }
        return check.types.directSupertypes(type).stream().filter(new Predicate() { // from class: vi1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Check.t(this.b, (Type) obj);
            }
        }).map(new Function() { // from class: wi1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Type) obj).tsym.type;
            }
        }).noneMatch(new Predicate() { // from class: xi1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Check.x(this.b, listSingletonList, listSingletonList2, (Type) obj);
            }
        });
    }

    private long implicitEnumFinalFlag(JCTree jCTree) {
        if (!jCTree.hasTag(JCTree.Tag.CLASSDEF)) {
            return 0L;
        }
        C1SpecialTreeVisitor c1SpecialTreeVisitor = new C1SpecialTreeVisitor();
        Iterator<JCTree> it = ((JCTree.JCClassDecl) jCTree).defs.iterator();
        while (it.hasNext()) {
            it.next().accept(c1SpecialTreeVisitor);
            if (c1SpecialTreeVisitor.specialized) {
                if (this.allowSealed) {
                    return Flags.SEALED;
                }
                return 0L;
            }
        }
        return 16L;
    }

    public static Check instance(Context context) {
        Check check = (Check) context.get(checkKey);
        return check == null ? new Check(context) : check;
    }

    private boolean is292targetTypeCast(JCTree.JCTypeCast jCTypeCast) {
        Symbol symbol;
        JCTree.JCExpression jCExpressionSkipParens = TreeInfo.skipParens(jCTypeCast.expr);
        return jCExpressionSkipParens.hasTag(JCTree.Tag.APPLY) && (symbol = TreeInfo.symbol(((JCTree.JCMethodInvocation) jCExpressionSkipParens).meth)) != null && symbol.kind == Kinds.Kind.MTH && (symbol.flags() & Flags.HYPOTHETICAL) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAPISymbol(Symbol symbol) {
        while (symbol.kind != Kinds.Kind.PCK) {
            if ((symbol.flags() & 1) == 0 && (symbol.flags() & 4) == 0) {
                return false;
            }
            symbol = symbol.owner;
        }
        return true;
    }

    private boolean isCanonical(JCTree jCTree) {
        while (jCTree.hasTag(JCTree.Tag.SELECT)) {
            JCTree.JCFieldAccess jCFieldAccess = (JCTree.JCFieldAccess) jCTree;
            if (jCFieldAccess.sym.owner.getQualifiedName() != TreeInfo.symbol(jCFieldAccess.selected).getQualifiedName()) {
                return false;
            }
            jCTree = jCFieldAccess.selected;
        }
        return true;
    }

    private boolean isDeprecatedOverrideIgnorable(Symbol.MethodSymbol methodSymbol, Symbol.ClassSymbol classSymbol) {
        Symbol.ClassSymbol classSymbolEnclClass = methodSymbol.enclClass();
        Type typeSupertype = this.types.supertype(classSymbol.type);
        if (!typeSupertype.hasTag(TypeTag.CLASS)) {
            return true;
        }
        Symbol.MethodSymbol methodSymbolImplementation = methodSymbol.implementation((Symbol.ClassSymbol) typeSupertype.tsym, this.types, false);
        if (classSymbolEnclClass == null || (classSymbolEnclClass.flags() & 512) == 0) {
            return methodSymbolImplementation != methodSymbol;
        }
        return (this.types.interfaces(classSymbol.type).contains(classSymbolEnclClass.type) || methodSymbolImplementation == null) ? false : true;
    }

    private boolean isEffectivelyNonPublic(Symbol symbol) {
        if (symbol.packge() == this.syms.rootPackage) {
            return false;
        }
        while (symbol.kind != Kinds.Kind.PCK) {
            if ((symbol.flags() & 1) == 0) {
                return true;
            }
            symbol = symbol.owner;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isRequiresIdentityAnnotation(Symbol.TypeSymbol typeSymbol) {
        return typeSymbol == this.syms.requiresIdentityType.tsym || typeSymbol.flatName() == this.syms.requiresIdentityInternalType.tsym.flatName();
    }

    private boolean isTargetSubsetOf(Set<Name> set, Set<Name> set2) {
        Names names;
        Name name;
        for (Name name2 : set) {
            for (Name name3 : set2) {
                if (name3 != name2 && ((name3 != (name = (names = this.names).TYPE) || name2 != names.ANNOTATION_TYPE) && (name3 != names.TYPE_USE || (name2 != name && name2 != names.ANNOTATION_TYPE && name2 != names.TYPE_PARAMETER)))) {
                }
            }
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTrustMeAllowedOnMethod(Symbol symbol) {
        if ((symbol.flags() & Flags.VARARGS) != 0) {
            if (symbol.isConstructor()) {
                return true;
            }
            if ((((long) ((Source.Feature.PRIVATE_SAFE_VARARGS.allowedInSource(this.source) ? 2 : 0) | 24)) & symbol.flags()) != 0) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ boolean j(Symbol.MethodSymbol methodSymbol, BiPredicate biPredicate, Symbol.MethodSymbol methodSymbol2) {
        return methodSymbol2 != methodSymbol && biPredicate.test(methodSymbol, methodSymbol2);
    }

    public static /* synthetic */ boolean k(Check check, Type type, Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2) {
        check.getClass();
        return methodSymbol.overrides(methodSymbol2, type.tsym, check.types, false);
    }

    public static /* synthetic */ int l(BiPredicate biPredicate, Object obj, Object obj2) {
        int i = biPredicate.test(obj, obj2) ? 2 : 0;
        return biPredicate.test(obj2, obj) ? i | 1 : i;
    }

    private Type labelType(JCTree.JCCaseLabel jCCaseLabel) {
        Type type;
        Types types = this.types;
        int i = AnonymousClass6.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCCaseLabel.getTag().ordinal()];
        if (i == 3) {
            type = ((JCTree.JCPatternCaseLabel) jCCaseLabel).pat.type;
        } else {
            if (i != 4) {
                throw Assert.error("Unexpected tree kind: " + jCCaseLabel.getTag());
            }
            type = ((JCTree.JCConstantCaseLabel) jCCaseLabel).expr.type;
        }
        return types.erasure(type);
    }

    public static /* synthetic */ void n(Check check, Type type, Symbol symbol, Env env, List list, List list2, boolean z, InferenceContext inferenceContext) {
        check.getClass();
        check.checkMethod(inferenceContext.asInstType(type), symbol, env, list, list2, z, inferenceContext);
    }

    public static /* synthetic */ boolean o(Check check, Type type, Symbol.MethodSymbol methodSymbol) {
        check.getClass();
        return methodSymbol.owner == type.tsym && !check.lint.augment(methodSymbol).isEnabled(Lint.LintCategory.OVERLOADS);
    }

    public static /* synthetic */ void p(final Check check, final Type type, final BiPredicate biPredicate, final JCTree.JCClassDecl jCClassDecl, java.util.List list) {
        check.getClass();
        check.compareAndRemove(list, new ToIntBiFunction() { // from class: di1
            @Override // java.util.function.ToIntBiFunction
            public final int applyAsInt(Object obj, Object obj2) {
                return Check.z(this.a, type, biPredicate, jCClassDecl, (Symbol.MethodSymbol) obj, (Symbol.MethodSymbol) obj2);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean patternDominated(JCTree.JCPattern jCPattern, JCTree.JCPattern jCPattern2) {
        if (!this.types.isUnconditionallyExactTypeBased(this.types.erasure(jCPattern2.type), this.types.erasure(jCPattern.type))) {
            return false;
        }
        if ((jCPattern2 instanceof JCTree.JCBindingPattern) || (jCPattern2 instanceof JCTree.JCAnyPattern)) {
            return (jCPattern instanceof JCTree.JCBindingPattern) || (jCPattern instanceof JCTree.JCAnyPattern);
        }
        if (jCPattern2 instanceof JCTree.JCRecordPattern) {
            JCTree.JCRecordPattern jCRecordPattern = (JCTree.JCRecordPattern) jCPattern2;
            if ((jCPattern instanceof JCTree.JCBindingPattern) || (jCPattern instanceof JCTree.JCAnyPattern)) {
                return true;
            }
            if (jCPattern instanceof JCTree.JCRecordPattern) {
                List list = ((JCTree.JCRecordPattern) jCPattern).nested;
                List list2 = jCRecordPattern.nested;
                if (list.size() != list2.size()) {
                    return false;
                }
                while (list.nonEmpty()) {
                    if (!patternDominated((JCTree.JCPattern) list.head, (JCTree.JCPattern) list2.head)) {
                        return false;
                    }
                    list = list.tail;
                    list2 = list2.tail;
                }
                return true;
            }
            Assert.error("Unknown pattern: " + jCPattern.getTag());
        } else {
            Assert.error("Unknown pattern: " + jCPattern2.getTag());
        }
        return false;
    }

    public static int protection(long j) {
        short s = (short) (j & 7);
        if (s == 0) {
            return 2;
        }
        if (s != 2) {
            return s != 4 ? 0 : 1;
        }
        return 3;
    }

    public static /* synthetic */ void r(final Check check, final Type type, java.util.List list) {
        check.getClass();
        check.removePreempted(list, new BiPredicate() { // from class: oi1
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return Check.g(this.b, type, (Symbol.MethodSymbol) obj, (Symbol.MethodSymbol) obj2);
            }
        });
    }

    public static /* synthetic */ void s(final Check check, final Type type, java.util.List list) {
        check.getClass();
        list.removeIf(new Predicate() { // from class: th1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Check.o(this.b, type, (Symbol.MethodSymbol) obj);
            }
        });
    }

    private boolean shouldCheckPreview(Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2, Symbol.ClassSymbol classSymbol) {
        if (methodSymbol.owner == classSymbol && ((methodSymbol2.flags() & Flags.PREVIEW_API) != 0 || (methodSymbol2.owner.flags() & Flags.PREVIEW_API) != 0)) {
            for (Symbol symbol : this.types.membersClosure(classSymbol.type, false).getSymbolsByName(methodSymbol.name)) {
                if (methodSymbol != symbol && methodSymbol.overrides(symbol, classSymbol, this.types, false)) {
                    if (symbol == methodSymbol2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static /* synthetic */ boolean t(Check check, Type type) {
        return type != check.syms.objectType;
    }

    public static /* synthetic */ void u(Check check, Type type, final BiPredicate biPredicate, HashMap map, Collection collection) {
        check.getClass();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            final Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) it.next();
            if (methodSymbol.owner == type.tsym) {
                ArrayList arrayList = (ArrayList) collection.stream().filter(new Predicate() { // from class: ti1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Check.j(methodSymbol, biPredicate, (Symbol.MethodSymbol) obj);
                    }
                }).collect(Collectors.toCollection(new vef()));
                check.removePreempted(arrayList, biPredicate);
                map.put(methodSymbol, arrayList);
            }
        }
    }

    public static /* synthetic */ boolean v(Symbol symbol, Symbol symbol2) {
        return (symbol2 == symbol || symbol2.type.isErroneous()) ? false : true;
    }

    private void validateAnnotation(JCTree.JCAnnotation jCAnnotation, JCTree jCTree, Symbol symbol) {
        long j;
        validateAnnotationTree(jCAnnotation);
        long j2 = 0;
        boolean z = (symbol.flags_field & Flags.RECORD) != 0 || (symbol.enclClass() != null && symbol.enclClass().isRecord());
        boolean z2 = (Flags.RECORD & symbol.flags_field) != 0 && jCTree.hasTag(JCTree.Tag.VARDEF) && symbol.owner.kind == Kinds.Kind.TYP;
        if (z2) {
            Name[] targetNames = getTargetNames(jCAnnotation);
            int length = targetNames.length;
            boolean z3 = false;
            for (int i = 0; i < length; i++) {
                Name name = targetNames[i];
                Names names = this.names;
                z3 = name == names.FIELD || name == names.PARAMETER || name == names.METHOD || name == names.TYPE_USE || name == names.RECORD_COMPONENT;
                if (z3) {
                    break;
                }
            }
            if (z3) {
                Symbol.RecordComponent recordComponent = ((Symbol.ClassSymbol) symbol.owner).getRecordComponent((Symbol.VarSymbol) symbol);
                SymbolMetadata metadata = recordComponent.getMetadata();
                if (metadata == null || metadata.isEmpty()) {
                    recordComponent.appendAttributes((List) symbol.getRawAttributes().stream().filter(new Predicate() { // from class: hi1
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return Check.D(this.b, (Attribute.Compound) obj);
                        }
                    }).collect(List.collector()));
                    for (JCTree.JCAnnotation jCAnnotation2 : ((JCTree.JCVariableDecl) jCTree).mods.annotations) {
                        Iterator<JCTree.JCAnnotation> it = recordComponent.declarationFor().mods.annotations.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                j = j2;
                                break;
                            }
                            JCTree.JCAnnotation next = it.next();
                            j = j2;
                            if (next.pos == jCAnnotation2.pos) {
                                next.setType(jCAnnotation2.type);
                                break;
                            }
                            j2 = j;
                        }
                        j2 = j;
                    }
                }
            } else {
                this.log.error(jCAnnotation.pos(), CompilerProperties.Errors.AnnotationTypeNotApplicable);
            }
        }
        long j3 = j2;
        if (jCAnnotation.type.tsym.isAnnotationType()) {
            Optional<Set<Name>> applicableTargets = getApplicableTargets(jCAnnotation, symbol);
            if (applicableTargets.isPresent()) {
                Set<Name> set = applicableTargets.get();
                boolean z4 = set.isEmpty() || (set.size() == 1 && set.contains(this.names.TYPE_USE));
                boolean z5 = z && (symbol.flags_field & 16777216) != j3;
                boolean z6 = z5 && z4;
                if (set.isEmpty() || z6) {
                    if (z6) {
                        JCTree.JCModifiers modifiers = TreeInfo.getModifiers(jCTree);
                        if (modifiers != null && set.isEmpty()) {
                            ListBuffer listBuffer = new ListBuffer();
                            for (JCTree.JCAnnotation jCAnnotation3 : modifiers.annotations) {
                                if (jCAnnotation3 != jCAnnotation) {
                                    listBuffer.add(jCAnnotation3);
                                }
                            }
                            modifiers.annotations = listBuffer.toList();
                        }
                        symbol.getMetadata().removeDeclarationMetadata(jCAnnotation.attribute);
                    } else {
                        this.log.error(jCAnnotation.pos(), CompilerProperties.Errors.AnnotationTypeNotApplicable);
                    }
                }
                if (z5 && !z2 && jCAnnotation.type.tsym == this.syms.trustMeType.tsym && jCTree.hasTag(JCTree.Tag.METHODDEF)) {
                    this.log.error(jCAnnotation.pos(), CompilerProperties.Errors.VarargsInvalidTrustmeAnno(this.syms.trustMeType.tsym, CompilerProperties.Fragments.VarargsTrustmeOnNonVarargsAccessor(symbol)));
                }
            }
        }
        if (jCAnnotation.annotationType.type.tsym == this.syms.functionalInterfaceType.tsym) {
            if (symbol.kind != Kinds.Kind.TYP) {
                this.log.error(jCAnnotation.pos(), CompilerProperties.Errors.BadFunctionalIntfAnno);
            } else {
                if (symbol.isInterface() && (symbol.flags() & 8192) == j3) {
                    return;
                }
                this.log.error(jCAnnotation.pos(), CompilerProperties.Errors.BadFunctionalIntfAnno1(CompilerProperties.Fragments.NotAFunctionalIntf(symbol)));
            }
        }
    }

    private void validateDefault(Symbol symbol, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        for (Symbol symbol2 : symbol.members().getSymbols()) {
            if (symbol2.name != this.names.value && symbol2.kind == Kinds.Kind.MTH && ((Symbol.MethodSymbol) symbol2).defaultValue == null) {
                this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotationElemNondefault(symbol, symbol2));
            }
        }
    }

    private void validateDocumented(Symbol symbol, Symbol symbol2, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        if (symbol2.attribute(this.syms.documentedType.tsym) == null || symbol.attribute(this.syms.documentedType.tsym) != null) {
            return;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotationNotDocumented(symbol, symbol2));
    }

    private void validateInherited(Symbol symbol, Symbol symbol2, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        if (symbol2.attribute(this.syms.inheritedType.tsym) == null || symbol.attribute(this.syms.inheritedType.tsym) != null) {
            return;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotationNotInherited(symbol, symbol2));
    }

    private void validateRetention(Symbol.TypeSymbol typeSymbol, Symbol.TypeSymbol typeSymbol2, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        Attribute.RetentionPolicy retention = this.types.getRetention(typeSymbol);
        Attribute.RetentionPolicy retention2 = this.types.getRetention(typeSymbol2);
        int i = AnonymousClass6.$SwitchMap$com$sun$tools$javac$code$Attribute$RetentionPolicy[retention2.ordinal()];
        if (i != 1) {
            if (i != 2 || retention != Attribute.RetentionPolicy.SOURCE) {
                return;
            }
        } else if (retention == Attribute.RetentionPolicy.RUNTIME) {
            return;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotationRetention(typeSymbol, retention.name(), typeSymbol2, retention2.name()));
    }

    private void validateTarget(Symbol.TypeSymbol typeSymbol, Symbol.TypeSymbol typeSymbol2, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        Set<Name> defaultTargetSet;
        Set<Name> defaultTargetSet2;
        Attribute.Array attributeTargetAttribute = getAttributeTargetAttribute(typeSymbol);
        if (attributeTargetAttribute == null) {
            defaultTargetSet = getDefaultTargetSet();
        } else {
            HashSet hashSet = new HashSet();
            for (Attribute attribute : attributeTargetAttribute.values) {
                if (attribute instanceof Attribute.Enum) {
                    hashSet.add(((Attribute.Enum) attribute).value.name);
                }
            }
            defaultTargetSet = hashSet;
        }
        Attribute.Array attributeTargetAttribute2 = getAttributeTargetAttribute(typeSymbol2);
        if (attributeTargetAttribute2 == null) {
            defaultTargetSet2 = getDefaultTargetSet();
        } else {
            HashSet hashSet2 = new HashSet();
            for (Attribute attribute2 : attributeTargetAttribute2.values) {
                if (attribute2 instanceof Attribute.Enum) {
                    hashSet2.add(((Attribute.Enum) attribute2).value.name);
                }
            }
            defaultTargetSet2 = hashSet2;
        }
        if (isTargetSubsetOf(defaultTargetSet, defaultTargetSet2)) {
            return;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotationIncompatibleTarget(typeSymbol, typeSymbol2));
    }

    private void validateValue(Symbol.TypeSymbol typeSymbol, Symbol.TypeSymbol typeSymbol2, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        Symbol symbolFindFirst = typeSymbol.members().findFirst(this.names.value);
        if (symbolFindFirst == null || symbolFindFirst.kind != Kinds.Kind.MTH) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotationNoValue(typeSymbol));
            return;
        }
        Type returnType = ((Symbol.MethodSymbol) symbolFindFirst).getReturnType();
        if (returnType.hasTag(TypeTag.ARRAY) && this.types.isSameType(((Type.ArrayType) returnType).elemtype, typeSymbol2.type)) {
            return;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotationValueReturn(typeSymbol, returnType, this.types.makeArrayType(typeSymbol2.type)));
    }

    private boolean withinAnonConstr(Env<AttrContext> env) {
        JCTree.JCMethodDecl jCMethodDecl;
        return env.enclClass.name.length() == 0 && (jCMethodDecl = env.enclMethod) != null && jCMethodDecl.name == this.names.init;
    }

    public static /* synthetic */ boolean x(Check check, java.util.List list, java.util.List list2, Type type) {
        check.getClass();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) it.next();
            Types types = check.types;
            if (types.isSubtype(types.erasure(type), check.types.erasure(methodSymbol.owner.type))) {
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    Symbol.MethodSymbol methodSymbol2 = (Symbol.MethodSymbol) it2.next();
                    Types types2 = check.types;
                    if (types2.isSubtype(types2.erasure(type), check.types.erasure(methodSymbol2.owner.type)) && check.potentiallyAmbiguousOverload(type, methodSymbol, methodSymbol2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static /* synthetic */ void y(Check check, JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2, CheckContext checkContext, InferenceContext inferenceContext) {
        check.getClass();
        check.checkType(diagnosticPosition, inferenceContext.asInstType(type), inferenceContext.asInstType(type2), checkContext);
    }

    public static /* synthetic */ int z(Check check, Type type, BiPredicate biPredicate, JCTree.JCClassDecl jCClassDecl, Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2) {
        JCDiagnostic.DiagnosticPosition diagnosticPositionDiagnosticPositionFor;
        if (!check.potentiallyAmbiguousOverload(type, methodSymbol, methodSymbol2) || !biPredicate.test(methodSymbol, methodSymbol2)) {
            return 0;
        }
        Symbol symbol = methodSymbol.owner;
        Symbol.TypeSymbol typeSymbol = type.tsym;
        if (symbol == typeSymbol) {
            diagnosticPositionDiagnosticPositionFor = TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl);
        } else {
            diagnosticPositionDiagnosticPositionFor = methodSymbol2.owner == typeSymbol ? TreeInfo.diagnosticPositionFor(methodSymbol2, jCClassDecl) : jCClassDecl.pos();
        }
        check.log.warning(diagnosticPositionDiagnosticPositionFor, CompilerProperties.LintWarnings.PotentiallyAmbiguousOverload(methodSymbol.asMemberOf(type, check.types), methodSymbol.location(), methodSymbol2.asMemberOf(type, check.types), methodSymbol2.location()));
        return 3;
    }

    public boolean annotationApplicable(JCTree.JCAnnotation jCAnnotation, Symbol symbol) {
        Optional<Set<Name>> applicableTargets = getApplicableTargets(jCAnnotation, symbol);
        if (applicableTargets.isPresent()) {
            return applicableTargets.isPresent() && !applicableTargets.get().isEmpty();
        }
        return true;
    }

    public BiPredicate<Symbol.MethodSymbol, Symbol.MethodSymbol> buildResponsiblePredicate(final Type type, List<? extends Collection<Symbol.MethodSymbol>> list) {
        final BiPredicate biPredicate = new BiPredicate() { // from class: ii1
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return Check.k(this.b, type, (Symbol.MethodSymbol) obj, (Symbol.MethodSymbol) obj2);
            }
        };
        final HashMap map = new HashMap();
        list.forEach(new Consumer() { // from class: ji1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Check.u(this.b, type, biPredicate, map, (Collection) obj);
            }
        });
        return new BiPredicate() { // from class: ki1
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return Check.i(this.b, map, type, (Symbol.MethodSymbol) obj, (Symbol.MethodSymbol) obj2);
            }
        };
    }

    public JCDiagnostic.Fragment cannotOverride(Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2) {
        Symbol symbolLocation = methodSymbol.location();
        Symbol symbolLocation2 = methodSymbol2.location();
        if ((methodSymbol2.owner.flags() & 512) == 0) {
            return CompilerProperties.Fragments.CantOverride(methodSymbol, symbolLocation, methodSymbol2, symbolLocation2);
        }
        return (methodSymbol.owner.flags() & 512) == 0 ? CompilerProperties.Fragments.CantImplement(methodSymbol, symbolLocation, methodSymbol2, symbolLocation2) : CompilerProperties.Fragments.ClashesWith(methodSymbol, symbolLocation, methodSymbol2, symbolLocation2);
    }

    public Warner castWarner(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2) {
        return new ConversionWarner(diagnosticPosition, "unchecked.cast.to.type", type, type2);
    }

    public void checkAccessFromSerializableElement(JCTree jCTree, boolean z) {
        if (this.warnOnAnyAccessToMembers || z) {
            checkAccessFromSerializableElementInner(jCTree, z);
        }
    }

    public void checkAllDefined(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.ClassSymbol classSymbol) {
        Symbol.MethodSymbol methodSymbolFirstUnimplementedAbstract = this.types.firstUnimplementedAbstract(classSymbol);
        if (methodSymbolFirstUnimplementedAbstract != null) {
            Symbol.MethodSymbol methodSymbol = new Symbol.MethodSymbol(methodSymbolFirstUnimplementedAbstract.flags(), methodSymbolFirstUnimplementedAbstract.name, this.types.memberType(classSymbol.type, methodSymbolFirstUnimplementedAbstract), methodSymbolFirstUnimplementedAbstract.owner);
            this.log.error(diagnosticPosition, CompilerProperties.Errors.DoesNotOverrideAbstract(classSymbol, methodSymbol, methodSymbol.location()));
        }
    }

    public void checkAnnotationResType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        int i = AnonymousClass6.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            checkAnnotationResType(diagnosticPosition, this.types.elemtype(type));
        } else if ((type.tsym.flags() & 8192) != 0) {
            checkNonCyclicElementsInternal(diagnosticPosition, type.tsym);
        }
    }

    public void checkCanonical(JCTree jCTree) {
        if (isCanonical(jCTree)) {
            return;
        }
        this.log.error(jCTree.pos(), CompilerProperties.Errors.ImportRequiresCanonical(TreeInfo.symbol(jCTree)));
    }

    public Type checkCastable(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2, CheckContext checkContext) {
        if (this.types.isCastable(type, type2, castWarner(diagnosticPosition, type, type2))) {
            return type2;
        }
        checkContext.report(diagnosticPosition, this.diags.fragment(CompilerProperties.Fragments.InconvertibleTypes(type, type2)));
        return this.types.createErrorType(type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void checkClassBounds(JCDiagnostic.DiagnosticPosition diagnosticPosition, Map<Symbol.TypeSymbol, Type> map, Type type) {
        if (type.isErroneous()) {
            return;
        }
        for (List listInterfaces = this.types.interfaces(type); listInterfaces.nonEmpty(); listInterfaces = listInterfaces.tail) {
            Type type2 = (Type) listInterfaces.head;
            TypeTag typeTag = TypeTag.CLASS;
            if (!type.hasTag(typeTag) || type2.hasTag(typeTag)) {
                Type typePut = map.put(type2.tsym, type2);
                if (typePut != null) {
                    List<Type> listAllparams = typePut.allparams();
                    List<Type> listAllparams2 = type2.allparams();
                    if (!this.types.containsTypeEquivalent(listAllparams, listAllparams2)) {
                        this.log.error(diagnosticPosition, CompilerProperties.Errors.CantInheritDiffArg(type2.tsym, Type.toString(listAllparams), Type.toString(listAllparams2)));
                    }
                }
                checkClassBounds(diagnosticPosition, map, type2);
            }
        }
        Type typeSupertype = this.types.supertype(type);
        TypeTag typeTag2 = TypeTag.CLASS;
        if ((!type.hasTag(typeTag2) || typeSupertype.hasTag(typeTag2)) && typeSupertype != Type.noType) {
            checkClassBounds(diagnosticPosition, map, typeSupertype);
        }
    }

    public Type checkClassOrArrayType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        return (type.hasTag(TypeTag.CLASS) || type.hasTag(TypeTag.ARRAY) || type.hasTag(TypeTag.ERROR)) ? type : typeTagError(diagnosticPosition, this.diags.fragment(CompilerProperties.Fragments.TypeReqClassArray), asTypeParam(type));
    }

    public void checkClassOverrideEqualsAndHashIfNeeded(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.ClassSymbol classSymbol) {
        List<Type> listInterfaces;
        if (classSymbol == ((Symbol.ClassSymbol) this.syms.objectType.tsym) || classSymbol.isInterface() || classSymbol.isEnum() || (classSymbol.flags() & 8192) != 0 || (classSymbol.flags() & 1024) != 0) {
            return;
        }
        if (!classSymbol.isAnonymous() || (listInterfaces = this.types.interfaces(classSymbol.type)) == null || listInterfaces.isEmpty() || listInterfaces.head.tsym != this.syms.comparatorType.tsym) {
            checkClassOverrideEqualsAndHash(diagnosticPosition, classSymbol);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Type checkClassType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, boolean z) {
        Type typeCheckClassType = checkClassType(diagnosticPosition, type);
        if (z && typeCheckClassType.isParameterized()) {
            for (List typeArguments = typeCheckClassType.getTypeArguments(); typeArguments.nonEmpty(); typeArguments = typeArguments.tail) {
                if (((Type) typeArguments.head).hasTag(TypeTag.WILDCARD)) {
                    return typeTagError(diagnosticPosition, this.diags.fragment(CompilerProperties.Fragments.TypeReqExact), typeArguments.head);
                }
            }
        }
        return typeCheckClassType;
    }

    public boolean checkCommonOverriderIn(Symbol symbol, Symbol symbol2, Type type) {
        HashMap map = new HashMap();
        Type typeMemberType = this.types.memberType(type, symbol);
        Type typeMemberType2 = this.types.memberType(type, symbol2);
        closure(type, map);
        Iterator<Type> it = map.values().iterator();
        while (it.hasNext()) {
            for (Symbol symbol3 : it.next().tsym.members().getSymbolsByName(symbol.name)) {
                if (symbol3 != symbol && symbol3 != symbol2 && symbol3.kind == Kinds.Kind.MTH && (symbol3.flags() & 2147487744L) == 0) {
                    Type typeMemberType3 = this.types.memberType(type, symbol3);
                    if (this.types.overrideEquivalent(typeMemberType3, typeMemberType) && this.types.overrideEquivalent(typeMemberType3, typeMemberType2) && this.types.returnTypeSubstitutable(typeMemberType3, typeMemberType) && this.types.returnTypeSubstitutable(typeMemberType3, typeMemberType2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkCompatibleAbstracts(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2, Type type3) {
        if ((type3.tsym.flags() & 16777216) != 0) {
            type = this.types.capture(type);
            type2 = this.types.capture(type2);
        }
        return firstIncompatibility(diagnosticPosition, type, type2, type3) == null;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x0100  */
    public void checkCompatibleConcretes(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        Type typeSupertype = this.types.supertype(type);
        if (typeSupertype.hasTag(TypeTag.CLASS)) {
            Type typeSupertype2 = typeSupertype;
            while (typeSupertype2.hasTag(TypeTag.CLASS) && typeSupertype2.tsym.type.isParameterized()) {
                for (Symbol symbol : typeSupertype2.tsym.members().getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
                    if (symbol.kind == Kinds.Kind.MTH) {
                        long j = 2147487752L;
                        long j2 = 0;
                        if ((symbol.flags() & 2147487752L) == 0 && symbol.isInheritedIn(type.tsym, this.types)) {
                            if (((Symbol.MethodSymbol) symbol).implementation(type.tsym, this.types, true) == symbol) {
                                Type typeMemberType = this.types.memberType(typeSupertype2, symbol);
                                int length = typeMemberType.mo71getParameterTypes().length();
                                if (typeMemberType != symbol.type) {
                                    Type typeSupertype3 = typeSupertype;
                                    while (typeSupertype3.hasTag(TypeTag.CLASS)) {
                                        for (Symbol symbol2 : typeSupertype3.tsym.members().getSymbolsByName(symbol.name)) {
                                            if (symbol2 != symbol) {
                                                long j3 = j;
                                                if (symbol2.kind == Kinds.Kind.MTH && (symbol2.flags() & j3) == j2 && symbol2.type.mo71getParameterTypes().length() == length && symbol2.isInheritedIn(type.tsym, this.types)) {
                                                    if (((Symbol.MethodSymbol) symbol2).implementation(type.tsym, this.types, true) == symbol2) {
                                                        if (this.types.overrideEquivalent(typeMemberType, this.types.memberType(typeSupertype3, symbol2))) {
                                                            this.log.error(diagnosticPosition, CompilerProperties.Errors.ConcreteInheritanceConflict(symbol, typeSupertype2, symbol2, typeSupertype3, typeSupertype));
                                                        }
                                                    }
                                                }
                                                j = j3;
                                            }
                                            j2 = 0;
                                        }
                                        typeSupertype3 = this.types.supertype(typeSupertype3);
                                        j = j;
                                        j2 = 0;
                                    }
                                }
                            }
                        }
                    }
                }
                typeSupertype2 = this.types.supertype(typeSupertype2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void checkCompatibleSupertypes(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        List<Type> listInterfaces = this.types.interfaces(type);
        Type typeSupertype = this.types.supertype(type);
        if (typeSupertype.hasTag(TypeTag.CLASS) && (typeSupertype.tsym.flags() & 1024) != 0) {
            listInterfaces = listInterfaces.prepend(typeSupertype);
        }
        for (List list = listInterfaces; list.nonEmpty(); list = list.tail) {
            if (!((Type) list.head).getTypeArguments().isEmpty()) {
                A a = list.head;
                if (!checkCompatibleAbstracts(diagnosticPosition, (Type) a, (Type) a, type)) {
                    return;
                }
            }
            for (List list2 = listInterfaces; list2 != list; list2 = list2.tail) {
                if (!checkCompatibleAbstracts(diagnosticPosition, (Type) list.head, (Type) list2.head, type)) {
                    return;
                }
            }
        }
        checkCompatibleConcretes(diagnosticPosition, type);
    }

    public Type checkConstructorRefType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        Type typeCheckClassOrArrayType = checkClassOrArrayType(diagnosticPosition, type);
        if (!typeCheckClassOrArrayType.hasTag(TypeTag.CLASS)) {
            if (!typeCheckClassOrArrayType.hasTag(TypeTag.ARRAY) || this.types.isReifiable(((Type.ArrayType) typeCheckClassOrArrayType).elemtype)) {
                return typeCheckClassOrArrayType;
            }
            this.log.error(diagnosticPosition, CompilerProperties.Errors.GenericArrayCreation);
            return this.types.createErrorType(typeCheckClassOrArrayType);
        }
        if ((typeCheckClassOrArrayType.tsym.flags() & 1536) != 0) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.AbstractCantBeInstantiated(typeCheckClassOrArrayType.tsym));
            return this.types.createErrorType(typeCheckClassOrArrayType);
        }
        if ((typeCheckClassOrArrayType.tsym.flags() & 16384) == 0) {
            return checkClassType(diagnosticPosition, typeCheckClassOrArrayType, true);
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.EnumCantBeInstantiated);
        return this.types.createErrorType(typeCheckClassOrArrayType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void checkCyclicConstructors(JCTree.JCClassDecl jCClassDecl) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (List list = jCClassDecl.defs; list.nonEmpty(); list = list.tail) {
            if (TreeInfo.isConstructor((JCTree) list.head)) {
                JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) list.head;
                JCTree.JCMethodInvocation jCMethodInvocationFindConstructorCall = TreeInfo.findConstructorCall(jCMethodDecl);
                if (jCMethodInvocationFindConstructorCall == null || TreeInfo.name(jCMethodInvocationFindConstructorCall.meth) != this.names._this) {
                    jCMethodDecl.sym.flags_field |= 1073741824;
                } else {
                    linkedHashMap.put(jCMethodDecl.sym, TreeInfo.symbol(jCMethodInvocationFindConstructorCall.meth));
                }
            }
        }
        for (Symbol symbol : (Symbol[]) linkedHashMap.keySet().toArray(new Symbol[0])) {
            checkCyclicConstructor(jCClassDecl, symbol, linkedHashMap);
        }
    }

    public void checkDefaultConstructor(Symbol.ClassSymbol classSymbol, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        if (this.lint.isEnabled(Lint.LintCategory.MISSING_EXPLICIT_CTOR) && (classSymbol.flags() & 2305843009213710336L) == 0 && !classSymbol.isAnonymous() && (classSymbol.flags() & 5) != 0 && Source.Feature.MODULES.allowedInSource(this.source)) {
            int i = AnonymousClass6.$SwitchMap$javax$lang$model$element$NestingKind[classSymbol.getNestingKind().ordinal()];
            if (i == 1 || i == 2) {
                return;
            }
            if (i == 4) {
                for (Symbol symbol = classSymbol.owner; symbol != null && symbol.kind == Kinds.Kind.TYP; symbol = symbol.owner) {
                    if ((symbol.flags() & 5) == 0) {
                        return;
                    }
                }
            }
            Symbol.PackageSymbol packageSymbolPackge = classSymbol.packge();
            if (packageSymbolPackge.isUnnamed()) {
                return;
            }
            Symbol.ModuleSymbol moduleSymbol = packageSymbolPackge.modle;
            for (Directive.ExportsDirective exportsDirective : moduleSymbol.exports) {
                if (exportsDirective.packge.equals(packageSymbolPackge)) {
                    List<Symbol.ModuleSymbol> list = exportsDirective.modules;
                    if (list != null && !list.isEmpty()) {
                        return;
                    } else {
                        this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.MissingExplicitCtor(classSymbol, packageSymbolPackge, moduleSymbol));
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void checkDefaultMethodClashes(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        Symbol symbol;
        JCDiagnostic.Fragment fragmentIncompatibleAbstractDefault;
        Type type2 = type;
        for (Symbol symbol2 : this.types.membersClosure(type2, false).getSymbols(new DefaultMethodClashFilter(type2))) {
            Assert.check(symbol2.kind == Kinds.Kind.MTH);
            List<Symbol.MethodSymbol> listInterfaceCandidates = this.types.interfaceCandidates(type2, (Symbol.MethodSymbol) symbol2);
            if (listInterfaceCandidates.size() <= 1) {
                break;
                break;
            }
            ListBuffer listBuffer = new ListBuffer();
            ListBuffer listBuffer2 = new ListBuffer();
            Iterator<Symbol.MethodSymbol> it = listInterfaceCandidates.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Symbol.MethodSymbol next = it.next();
                if ((next.flags() & Flags.DEFAULT) != 0) {
                    listBuffer2 = listBuffer2.append(next);
                } else if ((next.flags() & 1024) != 0) {
                    listBuffer = listBuffer.append(next);
                }
                if (listBuffer2.nonEmpty() && listBuffer2.size() + listBuffer.size() >= 2) {
                    Symbol symbol3 = (Symbol) listBuffer2.first();
                    if (listBuffer2.size() > 1) {
                        symbol = (Symbol) listBuffer2.toList().tail.head;
                        fragmentIncompatibleAbstractDefault = CompilerProperties.Fragments.IncompatibleUnrelatedDefaults(Kinds.kindName(type2.tsym), type2, symbol2.name, this.types.memberType(type2, symbol2).mo71getParameterTypes(), symbol3.location(), symbol.location());
                    } else {
                        symbol = (Symbol) listBuffer.first();
                        fragmentIncompatibleAbstractDefault = CompilerProperties.Fragments.IncompatibleAbstractDefault(Kinds.kindName(type2.tsym), type2, symbol2.name, this.types.memberType(type2, symbol2).mo71getParameterTypes(), symbol3.location(), symbol.location());
                    }
                    this.log.error(diagnosticPosition, CompilerProperties.Errors.TypesIncompatible(symbol3.location().type, symbol.location().type, fragmentIncompatibleAbstractDefault));
                    break;
                }
                type2 = type;
            }
            type2 = type;
        }
    }

    public boolean checkDenotable(Type type) {
        return denotableChecker.visit(type, null).booleanValue();
    }

    public void checkDeprecated(Supplier<JCDiagnostic.DiagnosticPosition> supplier, Symbol symbol, Symbol symbol2) {
        if (this.importSuppression) {
            return;
        }
        if (symbol2.isDeprecatedForRemoval() || (symbol2.isDeprecated() && !symbol.isDeprecated())) {
            if ((symbol2.outermostClass() != symbol.outermostClass() || symbol2.outermostClass() == null) && symbol2.kind != Kinds.Kind.PCK) {
                warnDeprecated(supplier.get(), symbol2);
            }
        }
    }

    public void checkDeprecatedAnnotation(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
        if (this.lint.isEnabled(Lint.LintCategory.DEP_ANN) && symbol.isDeprecatableViaAnnotation() && (symbol.flags() & Flags.BODY_ONLY_FINALIZE) != 0 && !this.syms.deprecatedType.isErroneous() && symbol.attribute(this.syms.deprecatedType.tsym) == null) {
            this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.MissingDeprecatedAnnotation);
        }
        if (!this.lint.isEnabled(Lint.LintCategory.DEPRECATION) || symbol.isDeprecatableViaAnnotation() || this.syms.deprecatedType.isErroneous() || symbol.attribute(this.syms.deprecatedType.tsym) == null) {
            return;
        }
        this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.DeprecatedAnnotationHasNoEffect(Kinds.kindName(symbol)));
    }

    public Type checkDiamond(JCTree.JCNewClass jCNewClass, Type type) {
        if (!TreeInfo.isDiamond(jCNewClass) || type.isErroneous()) {
            return checkClassType(jCNewClass.clazz.pos(), type, true);
        }
        if (jCNewClass.def != null) {
            Source.Feature feature = Source.Feature.DIAMOND_WITH_ANONYMOUS_CLASS_CREATION;
            if (!feature.allowedInSource(this.source)) {
                this.log.error(JCDiagnostic.DiagnosticFlag.SOURCE_LEVEL, jCNewClass.clazz.pos(), CompilerProperties.Errors.CantApplyDiamond1(type, feature.fragment(this.source.name)));
            }
        }
        if (type.tsym.type.getTypeArguments().isEmpty()) {
            this.log.error(jCNewClass.clazz.pos(), CompilerProperties.Errors.CantApplyDiamond1(type, CompilerProperties.Fragments.DiamondNonGeneric(type)));
            return this.types.createErrorType(type);
        }
        List<JCTree.JCExpression> list = jCNewClass.typeargs;
        if (list == null || !list.nonEmpty()) {
            return type;
        }
        this.log.error(jCNewClass.clazz.pos(), CompilerProperties.Errors.CantApplyDiamond1(type, CompilerProperties.Fragments.DiamondAndExplicitParams(type)));
        return this.types.createErrorType(type);
    }

    public List<Type> checkDiamondDenotable(Type.ClassType classType) {
        ListBuffer listBuffer = new ListBuffer();
        for (Type type : classType.allparams()) {
            if (!checkDenotable(type)) {
                listBuffer.append(type);
            }
        }
        return listBuffer.toList();
    }

    public boolean checkDisjoint(JCDiagnostic.DiagnosticPosition diagnosticPosition, long j, long j2, long j3) {
        long j4 = j2 & j;
        if (j4 == 0) {
            return true;
        }
        long j5 = j & j3;
        if (j5 == 0) {
            return true;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.IllegalCombinationOfModifiers(Flags.asFlagSet(TreeInfo.firstFlag(j4)), Flags.asFlagSet(TreeInfo.firstFlag(j5))));
        return false;
    }

    public void checkDivZero(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type) {
        if (type.constValue() != null && type.getTag().isSubRangeOf(TypeTag.LONG) && ((Number) type.constValue()).longValue() == 0) {
            int i = ((Symbol.OperatorSymbol) symbol).opcode;
            if (i == 108 || i == 112 || i == 109 || i == 113) {
                this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.DivZero);
            }
        }
    }

    public void checkEmptyIf(JCTree.JCIf jCIf) {
        if (jCIf.thenpart.hasTag(JCTree.Tag.SKIP) && jCIf.elsepart == null) {
            this.log.warning(jCIf.thenpart.pos(), CompilerProperties.LintWarnings.EmptyIf);
        }
    }

    /* JADX WARN: Code duplicated, block: B:129:0x0206  */
    /* JADX WARN: Code duplicated, block: B:131:0x020e  */
    /* JADX WARN: Code duplicated, block: B:133:0x0214  */
    /* JADX WARN: Code duplicated, block: B:134:0x0217  */
    /* JADX WARN: Code duplicated, block: B:136:0x021f  */
    /* JADX WARN: Code duplicated, block: B:137:0x022c  */
    public long checkFlags(long j, Symbol symbol, JCTree jCTree) {
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        Kinds.Kind kind;
        Kinds.Kind kind2;
        long j7;
        Log log;
        JCDiagnostic.Error error;
        long j8;
        long j9;
        JCTree jCTree2;
        JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCTree.pos();
        int i = AnonymousClass6.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[symbol.kind.ordinal()];
        boolean z = true;
        if (i != 1) {
            if (i == 2) {
                j2 = 8192;
                Name name = symbol.name;
                Name name2 = this.names.init;
                Symbol symbol2 = symbol.owner;
                j3 = 2;
                if (name != name2) {
                    long j10 = symbol2.flags_field;
                    if ((j10 & 512) != 0) {
                        long j11 = j10 & 8192;
                        j5 = Flags.AnnotationTypeElementMask;
                        if (j11 == 0 && (j & 8796093022218L) != 0) {
                            long j12 = (j & 2) != 0 ? 0L : 1L;
                            if ((j & Flags.DEFAULT) != 0) {
                                j12 |= 1024;
                            }
                            j3 = 8796093025291L;
                            j5 = j12;
                        } else {
                            j3 = 1025;
                        }
                    } else {
                        j8 = (j10 & Flags.RECORD) != 0 ? 3135L : 3391L;
                        j3 = j8;
                        j5 = 0;
                    }
                } else if ((symbol2.flags_field & 16384) != 0) {
                    j5 = 2;
                } else {
                    j8 = 7;
                    j3 = j8;
                    j5 = 0;
                }
                if ((j & 2048) != 0) {
                    this.log.warning(jCTree.pos(), CompilerProperties.LintWarnings.Strictfp);
                }
                if (((j | j5) & 1024) == 0 || (j & Flags.DEFAULT) != 0) {
                    j9 = j5 | (symbol.owner.flags_field & 2048);
                }
            } else {
                if (i != 3) {
                    x1f.a();
                    return 0L;
                }
                long jImplicitEnumFinalFlag = 8;
                if (symbol.owner.kind.matches(Kinds.KindSelector.VAL_MTH) || (symbol.isDirectlyOrIndirectlyLocal() && (j & 8192) != 0)) {
                    j2 = 8192;
                    if (symbol.isAnonymous() || ((j & Flags.RECORD) == 0 && (j & 16384) == 0 && (j & 512) == 0)) {
                        z = false;
                    }
                    j3 = (((j & 8) != 0 || z) && this.allowRecords && (j & 8192) == 0) ? 24088L : 23568L;
                    if (!z) {
                        jImplicitEnumFinalFlag = 0;
                    }
                } else {
                    Symbol symbol3 = symbol.owner;
                    if (symbol3.kind == Kinds.Kind.TYP) {
                        j3 = ((j & 8) != 0 && this.allowRecords && (j & 8192) == 0) ? Flags.ExtendedMemberStaticClassFlags : Flags.ExtendedMemberClassFlags;
                        j2 = 8192;
                        if (symbol3.owner.kind == Kinds.Kind.PCK || (symbol3.flags_field & 8) != 0) {
                            j3 |= 8;
                        } else if (!this.allowRecords && ((j & 16384) != 0 || (j & Flags.RECORD) != 0)) {
                            this.log.error(diagnosticPositionPos, CompilerProperties.Errors.StaticDeclarationNotAllowedInInnerClasses);
                        }
                        if ((j & 2305843009213710848L) == 0) {
                        }
                    } else {
                        j2 = 8192;
                        j3 = Flags.ExtendedClassFlags;
                    }
                    jImplicitEnumFinalFlag = 0;
                }
                if ((j & 512) != 0) {
                    jImplicitEnumFinalFlag |= 1024;
                }
                if ((j & 16384) != 0) {
                    j3 &= 9223090561878064111L;
                    jCTree2 = jCTree;
                    jImplicitEnumFinalFlag |= implicitEnumFinalFlag(jCTree2);
                } else {
                    jCTree2 = jCTree;
                }
                if ((j & Flags.RECORD) != 0) {
                    j3 &= -1025;
                    jImplicitEnumFinalFlag |= 16;
                }
                if ((j & 2048) != 0) {
                    this.log.warning(jCTree2.pos(), CompilerProperties.LintWarnings.Strictfp);
                }
                j9 = jImplicitEnumFinalFlag | (symbol.owner.flags_field & 2048);
            }
            j5 = j9;
        } else {
            j2 = 8192;
            if (TreeInfo.isReceiverParam(jCTree)) {
                j3 = 8589934592L;
            } else {
                Symbol symbol4 = symbol.owner;
                if (symbol4.kind == Kinds.Kind.TYP) {
                    if ((symbol4.flags_field & 512) != 0) {
                        j4 = 25;
                        j5 = 25;
                    } else {
                        j3 = 16607;
                    }
                    j6 = j & Flags.ExtendedStandardFlags & (~j4);
                    if (j6 != 0) {
                        j7 = j6 & 512;
                        log = this.log;
                        if (j7 != 0) {
                            if ((j & j2) != 0) {
                                error = CompilerProperties.Errors.AnnotationDeclNotAllowedHere;
                            } else {
                                error = CompilerProperties.Errors.IntfNotAllowedHere;
                            }
                            log.error(diagnosticPositionPos, error);
                            j4 |= 512;
                        } else {
                            log.error(diagnosticPositionPos, CompilerProperties.Errors.ModNotAllowedHere(Flags.asFlagSet(j6)));
                        }
                    } else {
                        kind = symbol.kind;
                        kind2 = Kinds.Kind.TYP;
                        if ((kind != kind2 || checkDisjoint(diagnosticPositionPos, j, 1024L, 8796093022218L)) && checkDisjoint(diagnosticPositionPos, j, 10L, Flags.DEFAULT) && checkDisjoint(diagnosticPositionPos, j, 1536L, 304L) && checkDisjoint(diagnosticPositionPos, j, 1L, 6L) && checkDisjoint(diagnosticPositionPos, j, 2L, 5L) && checkDisjoint(diagnosticPositionPos, j, 16L, 64L) && ((symbol.kind == kind2 || checkDisjoint(diagnosticPositionPos, j, 1280L, 2048L)) && checkDisjoint(diagnosticPositionPos, j, 16L, -9223090561878065152L) && checkDisjoint(diagnosticPositionPos, j, Flags.SEALED, -9223372036854775792L))) {
                            checkDisjoint(diagnosticPositionPos, j, Flags.SEALED, 8192L);
                        }
                    }
                    return (j & (9223081765785038848L | j4)) | j5;
                }
                j3 = Flags.LocalVarFlags;
            }
            j5 = 0;
        }
        j4 = j3;
        j6 = j & Flags.ExtendedStandardFlags & (~j4);
        if (j6 != 0) {
            j7 = j6 & 512;
            log = this.log;
            if (j7 != 0) {
                if ((j & j2) != 0) {
                    error = CompilerProperties.Errors.AnnotationDeclNotAllowedHere;
                } else {
                    error = CompilerProperties.Errors.IntfNotAllowedHere;
                }
                log.error(diagnosticPositionPos, error);
                j4 |= 512;
            } else {
                log.error(diagnosticPositionPos, CompilerProperties.Errors.ModNotAllowedHere(Flags.asFlagSet(j6)));
            }
        } else {
            kind = symbol.kind;
            kind2 = Kinds.Kind.TYP;
            if (kind != kind2) {
                checkDisjoint(diagnosticPositionPos, j, Flags.SEALED, 8192L);
            } else {
                checkDisjoint(diagnosticPositionPos, j, Flags.SEALED, 8192L);
            }
        }
        return (j & (9223081765785038848L | j4)) | j5;
    }

    public void checkForBadAuxiliaryClassAccess(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Symbol.ClassSymbol classSymbol) {
        if ((classSymbol.flags() & Flags.AUXILIARY) == 0 || !this.rs.isAccessible(env, classSymbol) || this.fileManager.isSameFile(classSymbol.sourcefile, env.toplevel.sourcefile)) {
            return;
        }
        this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.AuxiliaryClassAccessedFromOutsideOfItsSourceFile(classSymbol, classSymbol.sourcefile));
    }

    public void checkFunctionalInterface(JCTree.JCClassDecl jCClassDecl, Symbol.ClassSymbol classSymbol) {
        if (classSymbol.attribute(this.syms.functionalInterfaceType.tsym) != null) {
            try {
                this.types.findDescriptorSymbol(classSymbol);
            } catch (Types.FunctionDescriptorLookupError e) {
                JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCClassDecl.pos();
                for (JCTree.JCAnnotation jCAnnotation : jCClassDecl.getModifiers().annotations) {
                    if (jCAnnotation.annotationType.type.tsym == this.syms.functionalInterfaceType.tsym) {
                        diagnosticPositionPos = jCAnnotation.pos();
                        break;
                    }
                }
                this.log.error(diagnosticPositionPos, CompilerProperties.Errors.BadFunctionalIntfAnno1(e.getDiagnostic()));
            }
        }
    }

    public void checkHasMain(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.ClassSymbol classSymbol) {
        for (Symbol symbol : classSymbol.members().getSymbolsByName(this.names.main)) {
            if (symbol.kind == Kinds.Kind.MTH && (symbol.flags() & 2) == 0) {
                Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) symbol;
                if (!this.types.isSameType(methodSymbol.getReturnType(), this.syms.voidType)) {
                    continue;
                } else {
                    if (methodSymbol.params.isEmpty()) {
                        return;
                    }
                    if (methodSymbol.params.size() != 1) {
                        continue;
                    } else {
                        Types types = this.types;
                        if (types.isSameType(methodSymbol.params.head.type, types.makeArrayType(this.syms.stringType))) {
                            return;
                        }
                    }
                }
            }
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.ImplicitClassDoesNotHaveMainMethod);
    }

    public void checkHideClashes(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Symbol.MethodSymbol methodSymbol) {
        for (Symbol symbol : this.types.membersClosure(type, true).getSymbolsByName(methodSymbol.name, new ClashFilter(type))) {
            Types types = this.types;
            if (!types.isSubSignature(methodSymbol.type, types.memberType(type, symbol))) {
                Types types2 = this.types;
                if (types2.hasSameArgs(symbol.erasure(types2), methodSymbol.erasure(this.types))) {
                    this.log.error(diagnosticPosition, CompilerProperties.Errors.NameClashSameErasureNoHide(methodSymbol, methodSymbol.location(), symbol, symbol.location()));
                    return;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void checkImplementations(JCTree jCTree, Symbol.ClassSymbol classSymbol, Symbol.ClassSymbol classSymbol2) {
        Symbol.MethodSymbol methodSymbol;
        Symbol.MethodSymbol methodSymbolImplementation;
        for (List listClosure = this.types.closure(classSymbol2.type); listClosure.nonEmpty(); listClosure = listClosure.tail) {
            Symbol.ClassSymbol classSymbol3 = (Symbol.ClassSymbol) ((Type) listClosure.head).tsym;
            if ((classSymbol3.flags() & 1024) != 0) {
                for (Symbol symbol : classSymbol3.members().getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
                    if (symbol.kind == Kinds.Kind.MTH && (symbol.flags() & 1032) == 1024 && (methodSymbolImplementation = (methodSymbol = (Symbol.MethodSymbol) symbol).implementation(classSymbol, this.types, false)) != null && methodSymbolImplementation != methodSymbol && (methodSymbolImplementation.owner.flags() & 512) == (512 & classSymbol.flags())) {
                        checkOverride(jCTree, methodSymbolImplementation, methodSymbol, classSymbol);
                    }
                }
            }
        }
    }

    public void checkImportedPackagesObservable(JCTree.JCCompilationUnit jCCompilationUnit) {
        for (JCTree.JCImportBase jCImportBase : jCCompilationUnit.getImports()) {
            if (jCImportBase instanceof JCTree.JCImport) {
                JCTree.JCImport jCImport = (JCTree.JCImport) jCImportBase;
                if (!jCImport.staticImport && TreeInfo.name(jCImport.qualid) == this.names.asterisk) {
                    Symbol.TypeSymbol typeSymbol = jCImport.qualid.selected.type.tsym;
                    if (typeSymbol.kind == Kinds.Kind.PCK && typeSymbol.members().isEmpty() && (!Source.Feature.IMPORT_ON_DEMAND_OBSERVABLE_PACKAGES.allowedInSource(this.source) || !typeSymbol.exists())) {
                        this.log.error(JCDiagnostic.DiagnosticFlag.RESOLVE_ERROR, jCImport.qualid.selected.pos(), CompilerProperties.Errors.DoesntExist(typeSymbol));
                    }
                }
            }
        }
    }

    public void checkImportsResolvable(JCTree.JCCompilationUnit jCCompilationUnit) {
        Symbol symbol;
        for (JCTree.JCImportBase jCImportBase : jCCompilationUnit.getImports()) {
            if (jCImportBase instanceof JCTree.JCImport) {
                JCTree.JCImport jCImport = (JCTree.JCImport) jCImportBase;
                if (jCImport.staticImport && jCImport.qualid.hasTag(JCTree.Tag.SELECT)) {
                    JCTree.JCFieldAccess jCFieldAccess = jCImport.qualid;
                    if (jCFieldAccess.name != this.names.asterisk && (symbol = TreeInfo.symbol(jCFieldAccess.selected)) != null && symbol.kind == Kinds.Kind.TYP) {
                        Symbol.TypeSymbol typeSymbol = (Symbol.TypeSymbol) TreeInfo.symbol(jCFieldAccess.selected);
                        Check check = this;
                        if (!check.checkTypeContainsImportableElement(typeSymbol, typeSymbol, jCCompilationUnit.packge, jCFieldAccess.name, new HashSet())) {
                            check.log.error(jCImport.pos(), CompilerProperties.Errors.CantResolveLocation(Kinds.KindName.STATIC, jCFieldAccess.name, (Void) null, (Void) null, CompilerProperties.Fragments.Location(Kinds.kindName(typeSymbol), typeSymbol, (Void) null)));
                        }
                        this = check;
                    }
                }
            }
        }
    }

    public void checkImportsUnique(JCTree.JCCompilationUnit jCCompilationUnit) {
        JCTree.JCImport jCImport;
        Scope scope;
        Check check;
        Scope.WriteableScope writeableScopeCreate = Scope.WriteableScope.create(jCCompilationUnit.packge);
        Scope.WriteableScope writeableScopeCreate2 = Scope.WriteableScope.create(jCCompilationUnit.packge);
        Scope.WriteableScope writeableScope = jCCompilationUnit.toplevelScope;
        for (JCTree jCTree : jCCompilationUnit.defs) {
            if (jCTree.hasTag(JCTree.Tag.IMPORT) && (scope = (jCImport = (JCTree.JCImport) jCTree).importScope) != null) {
                for (Symbol symbol : scope.getSymbols(new Predicate() { // from class: zi1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Check.d((Symbol) obj);
                    }
                })) {
                    if (jCImport.isStatic()) {
                        check = this;
                        check.checkUniqueImport(jCImport.pos(), writeableScopeCreate, writeableScopeCreate2, writeableScope, symbol, true);
                        writeableScopeCreate2.enter(symbol);
                    } else {
                        check = this;
                        check.checkUniqueImport(jCImport.pos(), writeableScopeCreate, writeableScopeCreate2, writeableScope, symbol, false);
                        writeableScopeCreate.enter(symbol);
                    }
                    this = check;
                }
                jCImport.importScope = null;
                this = this;
            }
        }
    }

    public void checkLeaksNotAccessible(Env<AttrContext> env, JCTree.JCClassDecl jCClassDecl) {
        Directive.ExportsDirective exportsDirectiveFindExport;
        JCTree.JCCompilationUnit jCCompilationUnit = env.toplevel;
        Symbol.ModuleSymbol moduleSymbol = jCCompilationUnit.modle;
        Symtab symtab = this.syms;
        if (moduleSymbol == symtab.unnamedModule || moduleSymbol == symtab.noModule || (jCClassDecl.sym.flags() & 16777216) != 0 || (exportsDirectiveFindExport = findExport(jCCompilationUnit.packge)) == null || exportsDirectiveFindExport.modules != null) {
            return;
        }
        new TreeScanner(this, env, jCClassDecl, jCCompilationUnit) { // from class: com.sun.tools.javac.comp.Check.4
            boolean inSuperType;
            Lint lint;
            final /* synthetic */ Check this$0;
            final /* synthetic */ JCTree.JCClassDecl val$check;
            final /* synthetic */ Env val$env;
            final /* synthetic */ JCTree.JCCompilationUnit val$toplevel;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.val$env = env;
                this.val$check = jCClassDecl;
                this.val$toplevel = jCCompilationUnit;
                this.this$0 = this;
                this.lint = ((AttrContext) env.info).lint;
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
                if (jCAnnotation.attribute.type.tsym.getAnnotation(Documented.class) != null) {
                    super.visitAnnotation(jCAnnotation);
                }
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitBlock(JCTree.JCBlock jCBlock) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitClassDef(JCTree.JCClassDecl jCClassDecl2) {
                if (jCClassDecl2 == this.val$check && this.this$0.isAPISymbol(jCClassDecl2.sym)) {
                    Lint lint = this.lint;
                    try {
                        Lint lintAugment = lint.augment(jCClassDecl2.sym);
                        this.lint = lintAugment;
                        if (lintAugment.isEnabled(Lint.LintCategory.EXPORTS)) {
                            scan(jCClassDecl2.mods);
                            scan(jCClassDecl2.typarams);
                            try {
                                this.inSuperType = true;
                                scan(jCClassDecl2.extending);
                                scan(jCClassDecl2.implementing);
                                this.inSuperType = false;
                                scan(jCClassDecl2.defs);
                            } catch (Throwable th) {
                                this.inSuperType = false;
                                throw th;
                            }
                        }
                        this.lint = lint;
                    } catch (Throwable th2) {
                        this.lint = lint;
                        throw th2;
                    }
                }
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitIdent(JCTree.JCIdent jCIdent) {
                Symbol symbol = TreeInfo.symbol(jCIdent);
                if (symbol.kind != Kinds.Kind.TYP || symbol.type.hasTag(TypeTag.TYPEVAR)) {
                    return;
                }
                this.this$0.checkVisible(jCIdent.pos(), symbol, this.val$toplevel.packge, this.inSuperType);
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
                if (this.this$0.isAPISymbol(jCMethodDecl.sym)) {
                    Lint lint = this.lint;
                    try {
                        Lint lintAugment = lint.augment(jCMethodDecl.sym);
                        this.lint = lintAugment;
                        if (lintAugment.isEnabled(Lint.LintCategory.EXPORTS)) {
                            super.visitMethodDef(jCMethodDecl);
                        }
                    } finally {
                        this.lint = lint;
                    }
                }
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
                Symbol symbol = TreeInfo.symbol(jCFieldAccess);
                Symbol symbol2 = TreeInfo.symbol(jCFieldAccess.selected);
                if (symbol.kind == Kinds.Kind.TYP && symbol2.kind == Kinds.Kind.PCK) {
                    this.this$0.checkVisible(jCFieldAccess.pos(), symbol, this.val$toplevel.packge, this.inSuperType);
                } else {
                    super.visitSelect(jCFieldAccess);
                }
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
                scan(jCTypeApply.clazz);
                boolean z = this.inSuperType;
                try {
                    this.inSuperType = false;
                    scan(jCTypeApply.arguments);
                } finally {
                    this.inSuperType = z;
                }
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
                if (this.this$0.isAPISymbol(jCVariableDecl.sym) || jCVariableDecl.sym.owner.kind == Kinds.Kind.MTH) {
                    Lint lint = this.lint;
                    try {
                        Lint lintAugment = lint.augment(jCVariableDecl.sym);
                        this.lint = lintAugment;
                        if (lintAugment.isEnabled(Lint.LintCategory.EXPORTS)) {
                            scan(jCVariableDecl.mods);
                            scan(jCVariableDecl.vartype);
                        }
                    } finally {
                        this.lint = lint;
                    }
                }
            }
        }.scan(jCClassDecl);
    }

    public Type checkLocalVarType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Name name) {
        if (type.hasTag(TypeTag.BOT)) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.CantInferLocalVarType(name, CompilerProperties.Fragments.LocalCantInferNull));
            return this.types.createErrorType(type);
        }
        if (type.hasTag(TypeTag.VOID)) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.CantInferLocalVarType(name, CompilerProperties.Fragments.LocalCantInferVoid));
            return this.types.createErrorType(type);
        }
        Types types = this.types;
        return types.upward(type, types.captures(type)).baseType();
    }

    public void checkLossOfPrecision(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2) {
        if (type.isNumeric() && type2.isNumeric() && !this.types.isAssignable(type, type2)) {
            this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.PossibleLossOfPrecision(type, type2));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Type checkMethod(final Type type, final Symbol symbol, final Env<AttrContext> env, final List<JCTree.JCExpression> list, final List<Type> list2, final boolean z, InferenceContext inferenceContext) {
        if (inferenceContext.free(type)) {
            inferenceContext.addFreeTypeListener(List.of(type), new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.t
                @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                public final void typesInferred(InferenceContext inferenceContext2) {
                    Check.n(this.a, type, symbol, env, list, list2, z, inferenceContext2);
                }
            });
            return type;
        }
        List listMo71getParameterTypes = type.mo71getParameterTypes();
        List listMo71getParameterTypes2 = symbol.type.mo71getParameterTypes();
        if (listMo71getParameterTypes2.length() != listMo71getParameterTypes.length()) {
            listMo71getParameterTypes2 = listMo71getParameterTypes;
        }
        Type type2 = z ? (Type) listMo71getParameterTypes.last() : null;
        if (symbol.name == this.names.init && symbol.owner == this.syms.enumSym) {
            listMo71getParameterTypes = listMo71getParameterTypes.tail.tail;
            listMo71getParameterTypes2 = listMo71getParameterTypes2.tail.tail;
        }
        if ((symbol.flags() & Flags.ANONCONSTR_BASED) != 0) {
            listMo71getParameterTypes = listMo71getParameterTypes.tail;
            listMo71getParameterTypes2 = listMo71getParameterTypes2.tail;
        }
        if (list != null) {
            List list3 = list;
            while (listMo71getParameterTypes.head != type2) {
                JCTree jCTree = (JCTree) list3.head;
                assertConvertible(jCTree, jCTree.type, (Type) listMo71getParameterTypes.head, convertWarner(jCTree.pos(), jCTree.type, (Type) listMo71getParameterTypes2.head));
                list3 = list3.tail;
                listMo71getParameterTypes = listMo71getParameterTypes.tail;
                listMo71getParameterTypes2 = listMo71getParameterTypes2.tail;
            }
            if (z) {
                Type typeElemtype = this.types.elemtype(type2);
                while (list3.tail != null) {
                    JCTree jCTree2 = (JCTree) list3.head;
                    assertConvertible(jCTree2, jCTree2.type, typeElemtype, convertWarner(jCTree2.pos(), jCTree2.type, typeElemtype));
                    list3 = list3.tail;
                }
            } else if ((symbol.flags() & 70385924046848L) == Flags.VARARGS) {
                Type typeLast = type.mo71getParameterTypes().last();
                Type typeLast2 = list2.last();
                Types types = this.types;
                if (types.isSubtypeUnchecked(typeLast2, types.elemtype(typeLast))) {
                    Types types2 = this.types;
                    if (!types2.isSameType(types2.erasure(typeLast), this.types.erasure(typeLast2))) {
                        this.log.warning(list.last().pos(), CompilerProperties.Warnings.InexactNonVarargsCall(this.types.elemtype(typeLast), typeLast));
                    }
                }
            }
        }
        if (z) {
            Type typeLast3 = type.mo71getParameterTypes().last();
            if (!this.types.isReifiable(typeLast3) && (symbol.baseSymbol().attribute(this.syms.trustMeType.tsym) == null || !isTrustMeAllowedOnMethod(symbol))) {
                warnUnchecked(env.tree.pos(), CompilerProperties.LintWarnings.UncheckedGenericArrayCreation(typeLast3));
            }
            TreeInfo.setVarargsElement(env.tree, this.types.elemtype(typeLast3));
        }
        return type;
    }

    public void checkModuleExists(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.ModuleSymbol moduleSymbol) {
        if (moduleSymbol.kind != Kinds.Kind.MDL) {
            this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.ModuleNotFound(moduleSymbol));
        }
    }

    public void checkModuleName(JCTree.JCModuleDecl jCModuleDecl) {
        Name name;
        JCTree.JCExpression jCExpression;
        JCDiagnostic.DiagnosticPosition diagnosticPositionPos;
        String string;
        int length;
        Assert.checkNonNull(jCModuleDecl.sym.name);
        if (this.lint.isEnabled(Lint.LintCategory.MODULE)) {
            JCTree.JCExpression jCExpression2 = jCModuleDecl.qualId;
            while (jCExpression2 != null) {
                int i = AnonymousClass6.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpression2.getTag().ordinal()];
                if (i == 1) {
                    JCTree.JCFieldAccess jCFieldAccess = (JCTree.JCFieldAccess) jCExpression2;
                    name = jCFieldAccess.name;
                    JCDiagnostic.DiagnosticPosition diagnosticPositionPos2 = jCFieldAccess.pos();
                    jCExpression = jCFieldAccess.selected;
                    diagnosticPositionPos = diagnosticPositionPos2;
                } else {
                    if (i != 2) {
                        throw new AssertionError("Unexpected qualified identifier: " + jCExpression2.toString());
                    }
                    name = ((JCTree.JCIdent) jCExpression2).name;
                    diagnosticPositionPos = jCExpression2.pos();
                    jCExpression = null;
                }
                if (name != null && (length = (string = name.toString()).length()) > 0 && Character.isDigit(string.charAt(length - 1))) {
                    this.log.warning(diagnosticPositionPos, CompilerProperties.LintWarnings.PoorChoiceForModuleName(name));
                }
                jCExpression2 = jCExpression;
            }
        }
    }

    public void checkModuleRequires(JCDiagnostic.DiagnosticPosition diagnosticPosition, Directive.RequiresDirective requiresDirective) {
        if ((requiresDirective.module.flags() & 4503599627370496L) != 0) {
            boolean zIsTransitive = requiresDirective.isTransitive();
            Log log = this.log;
            if (zIsTransitive) {
                log.warning(diagnosticPosition, CompilerProperties.LintWarnings.RequiresTransitiveAutomatic);
            } else {
                log.warning(diagnosticPosition, CompilerProperties.LintWarnings.RequiresAutomatic);
            }
        }
    }

    public void checkNonCyclic(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type.TypeVar typeVar) {
        checkNonCyclic1(diagnosticPosition, typeVar, List.nil());
    }

    public void checkNonCyclicDecl(JCTree.JCClassDecl jCClassDecl) {
        CycleChecker cycleChecker = new CycleChecker();
        cycleChecker.scan(jCClassDecl);
        if (cycleChecker.errorFound || cycleChecker.partialCheck) {
            return;
        }
        jCClassDecl.sym.flags_field |= 1073741824;
    }

    public void checkNonCyclicElements(JCTree.JCClassDecl jCClassDecl) {
        long j = jCClassDecl.sym.flags_field;
        if ((8192 & j) == 0) {
            return;
        }
        Assert.check((j & 134217728) == 0);
        try {
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            classSymbol.flags_field = 134217728 | classSymbol.flags_field;
            for (JCTree jCTree : jCClassDecl.defs) {
                if (jCTree.hasTag(JCTree.Tag.METHODDEF)) {
                    JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) jCTree;
                    checkAnnotationResType(jCMethodDecl.pos(), jCMethodDecl.restype.type);
                }
            }
            Symbol.ClassSymbol classSymbol2 = jCClassDecl.sym;
            classSymbol2.flags_field = Flags.ACYCLIC_ANN | (classSymbol2.flags_field & (-134217729));
        } catch (Throwable th) {
            Symbol.ClassSymbol classSymbol3 = jCClassDecl.sym;
            classSymbol3.flags_field = Flags.ACYCLIC_ANN | (classSymbol3.flags_field & (-134217729));
            throw th;
        }
    }

    public void checkNonCyclicElementsInternal(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.TypeSymbol typeSymbol) {
        long j = typeSymbol.flags_field;
        if ((j & Flags.ACYCLIC_ANN) != 0) {
            return;
        }
        if ((j & 134217728) != 0) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.CyclicAnnotationElement(typeSymbol));
            return;
        }
        try {
            typeSymbol.flags_field = j | 134217728;
            for (Symbol symbol : typeSymbol.members().getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
                if (symbol.kind == Kinds.Kind.MTH) {
                    checkAnnotationResType(diagnosticPosition, ((Symbol.MethodSymbol) symbol).type.mo73getReturnType());
                }
            }
            typeSymbol.flags_field = (typeSymbol.flags_field & (-134217729)) | Flags.ACYCLIC_ANN;
        } catch (Throwable th) {
            typeSymbol.flags_field = (typeSymbol.flags_field & (-134217729)) | Flags.ACYCLIC_ANN;
            throw th;
        }
    }

    public Type checkNonVoid(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        if (!type.hasTag(TypeTag.VOID)) {
            return type;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.VoidNotAllowedHere);
        return this.types.createErrorType(type);
    }

    public void checkNotRepeated(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Set<Symbol> set) {
        if (set.contains(type.tsym)) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.RepeatedInterface);
        } else {
            set.add(type.tsym);
        }
    }

    public Type checkNullOrRefType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        return (type.isReference() || type.hasTag(TypeTag.BOT)) ? type : typeTagError(diagnosticPosition, this.diags.fragment(CompilerProperties.Fragments.TypeReqRef), type);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x002b  */
    /* JADX WARN: Code duplicated, block: B:8:0x0015  */
    /* JADX WARN: Code duplicated, block: B:9:0x001c  */
    public void checkOutOfRangeShift(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type) {
        Type.JCPrimitiveType jCPrimitiveType;
        int i;
        long jLongValue;
        Object objConstValue = type.constValue();
        if (objConstValue instanceof Number) {
            Number number = (Number) objConstValue;
            int i2 = ((Symbol.OperatorSymbol) symbol).opcode;
            switch (i2) {
                case 120:
                case 122:
                case 124:
                    jCPrimitiveType = this.syms.intType;
                    i = 31;
                    jLongValue = number.longValue();
                    if (jLongValue <= i || jLongValue < (-i)) {
                        this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.BitShiftOutOfRange(jCPrimitiveType, jLongValue, ((int) jLongValue) & (i - 1)));
                    }
                    break;
                case 121:
                case 123:
                case 125:
                    jCPrimitiveType = this.syms.longType;
                    i = 63;
                    jLongValue = number.longValue();
                    if (jLongValue <= i) {
                    }
                    this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.BitShiftOutOfRange(jCPrimitiveType, jLongValue, ((int) jLongValue) & (i - 1)));
                    break;
                default:
                    switch (i2) {
                        case ByteCodes.ishll /* 270 */:
                        case ByteCodes.ishrl /* 272 */:
                        case 274:
                            jCPrimitiveType = this.syms.intType;
                            i = 31;
                            break;
                        case ByteCodes.lshll /* 271 */:
                        case ByteCodes.lshrl /* 273 */:
                        case 275:
                            jCPrimitiveType = this.syms.longType;
                            i = 63;
                            break;
                    }
                    jLongValue = number.longValue();
                    if (jLongValue <= i) {
                    }
                    this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.BitShiftOutOfRange(jCPrimitiveType, jLongValue, ((int) jLongValue) & (i - 1)));
                    break;
            }
        }
    }

    public void checkOverride(final JCTree jCTree, final Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2, Symbol.ClassSymbol classSymbol) {
        if ((methodSymbol.flags() & 2147487744L) == 0 && (methodSymbol2.flags() & 4096) == 0) {
            if ((methodSymbol.flags() & 8) != 0 && (methodSymbol2.flags() & 8) == 0) {
                this.log.error(TreeInfo.diagnosticPositionFor(methodSymbol, jCTree), CompilerProperties.Errors.OverrideStatic(cannotOverride(methodSymbol, methodSymbol2)));
                methodSymbol.flags_field |= 35184372088832L;
                return;
            }
            if ((methodSymbol2.flags() & 16) != 0 || ((methodSymbol.flags() & 8) == 0 && (methodSymbol2.flags() & 8) != 0)) {
                this.log.error(TreeInfo.diagnosticPositionFor(methodSymbol, jCTree), CompilerProperties.Errors.OverrideMeth(cannotOverride(methodSymbol, methodSymbol2), Flags.asFlagSet(methodSymbol2.flags() & 24)));
                methodSymbol.flags_field |= 35184372088832L;
                return;
            }
            if ((methodSymbol.owner.flags() & 8192) != 0) {
                return;
            }
            if (protection(methodSymbol.flags()) > protection(methodSymbol2.flags())) {
                this.log.error(TreeInfo.diagnosticPositionFor(methodSymbol, jCTree), (methodSymbol2.flags() & 7) == 0 ? CompilerProperties.Errors.OverrideWeakerAccess(cannotOverride(methodSymbol, methodSymbol2), PsiKeyword.PACKAGE) : CompilerProperties.Errors.OverrideWeakerAccess(cannotOverride(methodSymbol, methodSymbol2), Flags.asFlagSet(methodSymbol2.flags() & 7)));
                methodSymbol.flags_field |= 35184372088832L;
                return;
            }
            if (shouldCheckPreview(methodSymbol, methodSymbol2, classSymbol)) {
                checkPreview(TreeInfo.diagnosticPositionFor(methodSymbol, jCTree), methodSymbol, classSymbol.type, methodSymbol2);
            }
            Type typeMemberType = this.types.memberType(classSymbol.type, methodSymbol);
            Type typeMemberType2 = this.types.memberType(classSymbol.type, methodSymbol2);
            List<Type> typeArguments = typeMemberType.getTypeArguments();
            List<Type> typeArguments2 = typeMemberType2.getTypeArguments();
            Type typeMo73getReturnType = typeMemberType.mo73getReturnType();
            Type typeSubst = this.types.subst(typeMemberType2.mo73getReturnType(), typeArguments2, typeArguments);
            this.overrideWarner.clear();
            if (!this.types.returnTypeSubstitutable(typeMemberType, typeMemberType2, typeSubst, this.overrideWarner)) {
                if ((methodSymbol.flags() & 8) == 0 || (methodSymbol2.flags() & 8) == 0) {
                    this.log.error(TreeInfo.diagnosticPositionFor(methodSymbol, jCTree), CompilerProperties.Errors.OverrideIncompatibleRet(cannotOverride(methodSymbol, methodSymbol2), typeMo73getReturnType, typeSubst));
                    methodSymbol.flags_field |= 35184372088832L;
                    return;
                } else {
                    this.log.error(TreeInfo.diagnosticPositionFor(methodSymbol, jCTree), CompilerProperties.Errors.OverrideIncompatibleRet(CompilerProperties.Fragments.CantHide(methodSymbol, methodSymbol.location(), methodSymbol2, methodSymbol2.location()), typeMo73getReturnType, typeSubst));
                    methodSymbol.flags_field |= 35184372088832L;
                    return;
                }
            }
            if (this.overrideWarner.hasNonSilentLint(Lint.LintCategory.UNCHECKED)) {
                warnUnchecked(TreeInfo.diagnosticPositionFor(methodSymbol, jCTree), CompilerProperties.LintWarnings.OverrideUncheckedRet(uncheckedOverrides(methodSymbol, methodSymbol2), typeMo73getReturnType, typeSubst));
            }
            List<Type> listSubst = this.types.subst(typeMemberType2.mo74getThrownTypes(), typeArguments2, typeArguments);
            List<Type> listUnhandled = unhandled(typeMemberType.mo74getThrownTypes(), this.types.erasure(listSubst));
            List<Type> listUnhandled2 = unhandled(typeMemberType.mo74getThrownTypes(), listSubst);
            if (listUnhandled.nonEmpty()) {
                this.log.error(TreeInfo.diagnosticPositionFor(methodSymbol, jCTree), CompilerProperties.Errors.OverrideMethDoesntThrow(cannotOverride(methodSymbol, methodSymbol2), listUnhandled2.head));
                methodSymbol.flags_field |= 35184372088832L;
            } else {
                if (listUnhandled2.nonEmpty()) {
                    warnUnchecked(TreeInfo.diagnosticPositionFor(methodSymbol, jCTree), CompilerProperties.LintWarnings.OverrideUncheckedThrown(cannotOverride(methodSymbol, methodSymbol2), listUnhandled2.head));
                    return;
                }
                if (((methodSymbol.flags() ^ methodSymbol2.flags()) & Flags.VARARGS) != 0) {
                    this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCTree), (Flags.VARARGS & methodSymbol.flags()) != 0 ? CompilerProperties.LintWarnings.OverrideVarargsMissing(varargsOverrides(methodSymbol, methodSymbol2)) : CompilerProperties.LintWarnings.OverrideVarargsExtra(varargsOverrides(methodSymbol, methodSymbol2)));
                }
                if ((methodSymbol2.flags() & Flags.BRIDGE) != 0) {
                    this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCTree), CompilerProperties.Warnings.OverrideBridge(uncheckedOverrides(methodSymbol, methodSymbol2)));
                }
                if (isDeprecatedOverrideIgnorable(methodSymbol2, classSymbol)) {
                    return;
                }
                checkDeprecated(new Supplier() { // from class: mi1
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return TreeInfo.diagnosticPositionFor(methodSymbol, jCTree);
                    }
                }, methodSymbol, methodSymbol2);
            }
        }
    }

    public void checkOverrideClashes(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Symbol.MethodSymbol methodSymbol) {
        ClashFilter clashFilter = new ClashFilter(type);
        final ArrayList<Symbol> arrayList = new ArrayList();
        this.types.membersClosure(type, false).getSymbolsByName(methodSymbol.name, clashFilter).forEach(new Consumer() { // from class: ci1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add((Symbol) obj);
            }
        });
        for (Symbol symbol : arrayList) {
            if (methodSymbol.overrides(symbol, type.tsym, this.types, false)) {
                for (Symbol symbol2 : arrayList) {
                    if (symbol2 != symbol) {
                        Types types = this.types;
                        if (types.isSubSignature(methodSymbol.type, types.memberType(type, symbol2))) {
                            continue;
                        } else {
                            Types types2 = this.types;
                            if (types2.hasSameArgs(symbol2.erasure(types2), symbol.erasure(this.types))) {
                                methodSymbol.flags_field |= Flags.CLASH;
                                if (symbol == methodSymbol) {
                                    this.log.error(diagnosticPosition, CompilerProperties.Errors.NameClashSameErasureNoOverride(symbol.name, this.types.memberType(type, symbol).asMethodType().mo71getParameterTypes(), symbol.location(), symbol2.name, this.types.memberType(type, symbol2).asMethodType().mo71getParameterTypes(), symbol2.location()));
                                    return;
                                } else {
                                    Type.ClassType classType = (Type.ClassType) type;
                                    this.log.error(diagnosticPosition, CompilerProperties.Errors.NameClashSameErasureNoOverride1(classType.isInterface() ? PsiKeyword.INTERFACE : "class", classType.tsym.name, symbol.name, this.types.memberType(type, symbol).asMethodType().mo71getParameterTypes(), symbol.location(), symbol2.name, this.types.memberType(type, symbol2).asMethodType().mo71getParameterTypes(), symbol2.location()));
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void checkPackageExistsForOpens(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.PackageSymbol packageSymbol) {
        if (packageSymbol.members().isEmpty() && (packageSymbol.flags() & 4503599627370496L) == 0) {
            this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.PackageEmptyOrNotFound(packageSymbol));
        }
    }

    public void checkPotentiallyAmbiguousOverloads(final JCTree.JCClassDecl jCClassDecl, final Type type) {
        if (this.lint.isEnabled(Lint.LintCategory.OVERLOADS)) {
            List<? extends Collection<Symbol.MethodSymbol>> listMethodsGroupedByName = methodsGroupedByName(type, new PotentiallyAmbiguousFilter(type), new vef());
            final BiPredicate<Symbol.MethodSymbol, Symbol.MethodSymbol> biPredicateBuildResponsiblePredicate = buildResponsiblePredicate(type, listMethodsGroupedByName);
            listMethodsGroupedByName.forEach(new Consumer() { // from class: ei1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Check.r(this.b, type, (java.util.List) obj);
                }
            });
            listMethodsGroupedByName.forEach(new Consumer() { // from class: fi1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Check.s(this.b, type, (java.util.List) obj);
                }
            });
            listMethodsGroupedByName.forEach(new Consumer() { // from class: gi1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Check.p(this.b, type, biPredicateBuildResponsiblePredicate, jCClassDecl, (java.util.List) obj);
                }
            });
        }
    }

    public void checkPreview(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Type type, Symbol symbol2) {
        Symbol.TypeSymbol typeSymbol;
        Symbol symbol3;
        boolean z = true;
        if ((symbol2.flags() & Flags.PREVIEW_API) != 0) {
            symbol3 = symbol2;
        } else {
            Kinds.Kind kind = symbol2.kind;
            if ((kind == Kinds.Kind.MTH || kind == Kinds.Kind.VAR) && (typeSymbol = type.tsym) != null && (typeSymbol.flags() & Flags.PREVIEW_API) == 0 && (Flags.PREVIEW_API & symbol2.owner.flags()) != 0) {
                symbol3 = symbol2.owner;
            } else {
                z = false;
                symbol3 = null;
            }
        }
        if (z && !this.preview.participatesInPreview(this.syms, symbol, symbol2) && !this.disablePreviewCheck) {
            if ((symbol3.flags() & Flags.PREVIEW_REFLECTIVE) != 0) {
                warnPreviewAPI(diagnosticPosition, CompilerProperties.LintWarnings.IsPreviewReflective(symbol2));
            } else if (this.preview.isEnabled()) {
                this.preview.markUsesPreview(diagnosticPosition);
                warnPreviewAPI(diagnosticPosition, CompilerProperties.LintWarnings.IsPreview(symbol2));
            } else {
                this.log.error(diagnosticPosition, CompilerProperties.Errors.IsPreview(symbol2));
            }
        }
        if (this.preview.declaredUsingPreviewFeature(symbol2) && this.preview.isEnabled()) {
            this.preview.markUsesPreview(diagnosticPosition);
            warnPreviewAPI(diagnosticPosition, CompilerProperties.LintWarnings.DeclaredUsingPreview(Kinds.kindName(symbol2), symbol2));
        }
    }

    public void checkProfile(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
        if (this.profile == Profile.DEFAULT || (symbol.flags() & 35184372088832L) == 0) {
            return;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.NotInProfile(symbol, this.profile));
    }

    public void checkRaw(JCTree jCTree, Env<AttrContext> env) {
        if (!jCTree.type.hasTag(TypeTag.CLASS) || TreeInfo.isDiamond(jCTree) || withinAnonConstr(env) || !jCTree.type.isRaw()) {
            return;
        }
        Log log = this.log;
        JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCTree.pos();
        Type type = jCTree.type;
        log.warning(diagnosticPositionPos, CompilerProperties.LintWarnings.RawClassUse(type, type.tsym.type));
    }

    public void checkRedundantCast(Env<AttrContext> env, JCTree.JCTypeCast jCTypeCast) {
        if (jCTypeCast.type.isErroneous() || !this.types.isSameType(jCTypeCast.expr.type, jCTypeCast.clazz.type) || TreeInfo.containsTypeAnnotation(jCTypeCast.clazz) || is292targetTypeCast(jCTypeCast)) {
            return;
        }
        this.log.warning(jCTypeCast.pos(), CompilerProperties.LintWarnings.RedundantCast(jCTypeCast.clazz.type));
    }

    public Type checkRefType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        return type.isReference() ? type : typeTagError(diagnosticPosition, this.diags.fragment(CompilerProperties.Fragments.TypeReqRef), type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [A, com.sun.tools.javac.code.Type] */
    public List<Type> checkRefTypes(List<JCTree.JCExpression> list, List<Type> list2) {
        List list3 = list2;
        List list4 = list;
        while (list3.nonEmpty()) {
            list3.head = checkRefType(((JCTree.JCExpression) list4.head).pos(), (Type) list3.head);
            List list5 = list4.tail;
            list3 = list3.tail;
            list4 = list5;
        }
        return list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void checkRequiresIdentity(JCTree jCTree, Lint lint) {
        JCTree.JCPolyExpression jCPolyExpression;
        boolean z;
        List<Symbol.VarSymbol> list;
        JCTree.JCExpression jCExpression;
        Objects.requireNonNull(jCTree);
        char c = 0;
        while (true) {
            switch (c) {
                case 0:
                    if (jCTree instanceof JCTree.JCClassDecl) {
                        JCTree.JCClassDecl jCClassDecl = (JCTree.JCClassDecl) jCTree;
                        Type typeSupertype = this.types.supertype(jCClassDecl.sym.type);
                        if (typeSupertype != null && typeSupertype.tsym != this.syms.objectType.tsym && (jCExpression = jCClassDecl.extending) != null) {
                            checkIfIdentityIsExpected(jCExpression.pos(), typeSupertype, lint);
                        }
                        for (JCTree.JCExpression jCExpression2 : jCClassDecl.implementing) {
                            checkIfIdentityIsExpected(jCExpression2.pos(), jCExpression2.type, lint);
                        }
                        for (JCTree.JCTypeParameter jCTypeParameter : jCClassDecl.typarams) {
                            checkIfIdentityIsExpected(jCTypeParameter.pos(), jCTypeParameter.type, lint);
                        }
                        break;
                    }
                case 1:
                    if (jCTree instanceof JCTree.JCVariableDecl) {
                        JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) jCTree;
                        JCTree.JCExpression jCExpression3 = jCVariableDecl.vartype;
                        if ((jCExpression3 != null && (jCVariableDecl.sym.flags_field & Flags.RECORD) == 0) || (jCVariableDecl.sym.flags_field & (-2305843017820405761L)) != 0) {
                            checkIfIdentityIsExpected(jCExpression3.pos(), jCVariableDecl.vartype.type, lint);
                            break;
                        }
                    }
                case 2:
                    if (jCTree instanceof JCTree.JCTypeCast) {
                        JCTree.JCTypeCast jCTypeCast = (JCTree.JCTypeCast) jCTree;
                        checkIfIdentityIsExpected(jCTypeCast.clazz.pos(), jCTypeCast.clazz.type, lint);
                        break;
                    }
                case 3:
                    if (jCTree instanceof JCTree.JCBindingPattern) {
                        JCTree.JCBindingPattern jCBindingPattern = (JCTree.JCBindingPattern) jCTree;
                        JCTree.JCExpression jCExpression4 = jCBindingPattern.var.vartype;
                        if (jCExpression4 != null) {
                            checkIfIdentityIsExpected(jCExpression4.pos(), jCBindingPattern.var.vartype.type, lint);
                        }
                        break;
                    }
                case 4:
                    if (jCTree instanceof JCTree.JCMethodDecl) {
                        JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) jCTree;
                        for (JCTree.JCTypeParameter jCTypeParameter2 : jCMethodDecl.typarams) {
                            checkIfIdentityIsExpected(jCTypeParameter2.pos(), jCTypeParameter2.type, lint);
                        }
                        JCTree.JCExpression jCExpression5 = jCMethodDecl.restype;
                        if (jCExpression5 != null && !jCExpression5.type.hasTag(TypeTag.VOID)) {
                            checkIfIdentityIsExpected(jCMethodDecl.restype.pos(), jCMethodDecl.restype.type, lint);
                            break;
                        }
                    }
                case 5:
                    if (jCTree instanceof JCTree.JCMemberReference) {
                        JCTree.JCMemberReference jCMemberReference = (JCTree.JCMemberReference) jCTree;
                        checkIfIdentityIsExpected(jCMemberReference.expr.pos(), jCMemberReference.target, lint);
                        checkIfTypeParamsRequiresIdentity(jCMemberReference.sym.getMetadata(), jCMemberReference.typeargs, lint);
                        break;
                    }
                case 6:
                    if (jCTree instanceof JCTree.JCPolyExpression) {
                        jCPolyExpression = (JCTree.JCPolyExpression) jCTree;
                        z = jCPolyExpression instanceof JCTree.JCNewClass;
                        if (!z && !(jCPolyExpression instanceof JCTree.JCMethodInvocation)) {
                            c = 7;
                        }
                    } else {
                        s22.a("unexpected tree ", jCTree);
                    }
                    break;
                default:
                    s22.a("unexpected tree ", jCTree);
                    break;
            }
        }
        if (z) {
            JCTree.JCNewClass jCNewClass = (JCTree.JCNewClass) jCPolyExpression;
            checkIfIdentityIsExpected(jCNewClass.clazz.pos(), jCNewClass.clazz.type, lint);
        }
        List list2 = z ? ((JCTree.JCNewClass) jCPolyExpression).args : ((JCTree.JCMethodInvocation) jCPolyExpression).args;
        Symbol symbolSymbolFor = TreeInfo.symbolFor(jCPolyExpression);
        if (symbolSymbolFor != null) {
            if (!list2.isEmpty() && (symbolSymbolFor instanceof Symbol.MethodSymbol) && (list = ((Symbol.MethodSymbol) symbolSymbolFor).params) != null) {
                Symbol.VarSymbol next = list.head;
                Iterator<Symbol.VarSymbol> it = list.iterator();
                while (it.hasNext()) {
                    next = it.next();
                    if ((next.flags_field & 4611686018427387904L) != 0 && ((JCTree.JCExpression) list2.head).type.isValueBased()) {
                        this.log.warning(((JCTree.JCExpression) list2.head).pos(), CompilerProperties.LintWarnings.AttemptToUseValueBasedWhereIdentityExpected);
                    }
                    list2 = list2.tail;
                }
                while (list2 != null && !list2.isEmpty() && next != null) {
                    if ((next.flags_field & 4611686018427387904L) != 0 && ((JCTree.JCExpression) list2.head).type.isValueBased()) {
                        this.log.warning(((JCTree.JCExpression) list2.head).pos(), CompilerProperties.LintWarnings.AttemptToUseValueBasedWhereIdentityExpected);
                    }
                    list2 = list2.tail;
                }
            }
            checkIfTypeParamsRequiresIdentity(symbolSymbolFor.getMetadata(), z ? ((JCTree.JCNewClass) jCPolyExpression).typeargs : ((JCTree.JCMethodInvocation) jCPolyExpression).typeargs, lint);
        }
    }

    public void checkRestricted(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
        if (symbol.kind != Kinds.Kind.MTH || (symbol.flags() & 4611686018427387904L) == 0) {
            return;
        }
        this.log.warning(diagnosticPosition, CompilerProperties.LintWarnings.RestrictedMethod(symbol.enclClass(), symbol));
    }

    public void checkSerialStructure(JCTree.JCClassDecl jCClassDecl, Symbol.ClassSymbol classSymbol) {
        new SerialTypeVisitor().visit(classSymbol, jCClassDecl);
    }

    public void checkSunAPI(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
        if ((symbol.flags() & Flags.PROPRIETARY) != 0) {
            this.log.warning(diagnosticPosition, CompilerProperties.Warnings.SunProprietary(symbol));
        }
    }

    public void checkSuperInitCalls(JCTree.JCClassDecl jCClassDecl) {
        new SuperThisChecker().check(jCClassDecl);
    }

    /* JADX WARN: Code duplicated, block: B:53:0x00ed  */
    /* JADX WARN: Multi-variable type inference failed */
    public void checkSwitchCaseLabelDominated(JCTree.JCCaseLabel jCCaseLabel, List<JCTree.JCCase> list) {
        boolean zPatternDominated;
        List<Pair> listNil = List.nil();
        boolean z = false;
        boolean zIsNullCaseLabel = false;
        boolean z2 = false;
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            JCTree.JCCase jCCase = (JCTree.JCCase) list2.head;
            for (JCTree.JCCaseLabel jCCaseLabel2 : jCCase.labels) {
                if (jCCaseLabel2.hasTag(JCTree.Tag.DEFAULTCASELABEL)) {
                    zIsNullCaseLabel |= TreeInfo.isNullCaseLabel(jCCase.labels.head);
                    z = true;
                } else if (!TreeInfo.isNullCaseLabel(jCCaseLabel2)) {
                    if (z && !z2 && (jCCaseLabel2.hasTag(JCTree.Tag.PATTERNCASELABEL) || ((jCCaseLabel2 instanceof JCTree.JCConstantCaseLabel) && zIsNullCaseLabel))) {
                        this.log.error(jCCaseLabel2.pos(), CompilerProperties.Errors.PatternDominated);
                        z2 = true;
                    }
                    Type typeLabelType = labelType(jCCaseLabel2);
                    for (Pair pair : listNil) {
                        JCTree.JCCase jCCase2 = (JCTree.JCCase) pair.fst;
                        JCTree.JCCaseLabel jCCaseLabel3 = (JCTree.JCCaseLabel) pair.snd;
                        Type typeLabelType2 = labelType(jCCaseLabel3);
                        if (this.allowPrimitivePatterns && jCCaseLabel == jCCaseLabel3 && jCCaseLabel != jCCaseLabel2) {
                            this.log.error(jCCaseLabel2.pos(), CompilerProperties.Errors.PatternDominated);
                        } else {
                            TypeTag typeTag = TypeTag.ERROR;
                            if (typeLabelType.hasTag(typeTag) || typeLabelType2.hasTag(typeTag)) {
                                zPatternDominated = false;
                            } else if (this.types.isUnconditionallyExactCombined(typeLabelType, typeLabelType2) && (jCCaseLabel2 instanceof JCTree.JCConstantCaseLabel)) {
                                if ((jCCaseLabel3 instanceof JCTree.JCConstantCaseLabel) || !TreeInfo.unguardedCase(jCCase2)) {
                                    zPatternDominated = false;
                                } else {
                                    zPatternDominated = true;
                                }
                            } else if (jCCaseLabel2 instanceof JCTree.JCPatternCaseLabel) {
                                JCTree.JCPatternCaseLabel jCPatternCaseLabel = (JCTree.JCPatternCaseLabel) jCCaseLabel2;
                                if (jCCaseLabel3 instanceof JCTree.JCPatternCaseLabel) {
                                    JCTree.JCPatternCaseLabel jCPatternCaseLabel2 = (JCTree.JCPatternCaseLabel) jCCaseLabel3;
                                    if (jCCase2.equals(jCCase) || TreeInfo.unguardedCase(jCCase2)) {
                                        zPatternDominated = patternDominated(jCPatternCaseLabel2.pat, jCPatternCaseLabel.pat);
                                    } else {
                                        zPatternDominated = false;
                                    }
                                } else {
                                    zPatternDominated = false;
                                }
                            } else {
                                zPatternDominated = false;
                            }
                            if (zPatternDominated) {
                                this.log.error(jCCaseLabel2.pos(), CompilerProperties.Errors.PatternDominated);
                            }
                        }
                    }
                    listNil = listNil.prepend(Pair.of(jCCase, jCCaseLabel2));
                } else if (z) {
                    this.log.error(jCCaseLabel2.pos(), CompilerProperties.Errors.PatternDominated);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:58:0x0162  */
    /* JADX WARN: Code duplicated, block: B:60:0x016a  */
    /* JADX WARN: Code duplicated, block: B:89:0x019d A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    public void checkSwitchCaseStructure(List<JCTree.JCCase> list) {
        JCTree.JCCaseLabel jCCaseLabel;
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            JCTree.JCCase jCCase = (JCTree.JCCase) list2.head;
            List<JCTree.JCCaseLabel> list3 = jCCase.labels;
            JCTree.JCCaseLabel jCCaseLabel2 = list3.head;
            if (jCCaseLabel2 instanceof JCTree.JCConstantCaseLabel) {
                boolean zIsNull = TreeInfo.isNull(((JCTree.JCConstantCaseLabel) jCCaseLabel2).expr);
                List<JCTree.JCCaseLabel> list4 = jCCase.labels;
                if (!zIsNull) {
                    for (JCTree.JCCaseLabel jCCaseLabel3 : list4.tail) {
                        if (!(jCCaseLabel3 instanceof JCTree.JCConstantCaseLabel) || TreeInfo.isNullCaseLabel(jCCaseLabel3)) {
                            this.log.error(jCCaseLabel3.pos(), CompilerProperties.Errors.InvalidCaseLabelCombination);
                            break;
                        }
                    }
                } else if (list4.tail.nonEmpty()) {
                    List<JCTree.JCCaseLabel> list5 = jCCase.labels.tail;
                    JCTree.JCCaseLabel jCCaseLabel4 = list5.head;
                    if (!(jCCaseLabel4 instanceof JCTree.JCDefaultCaseLabel)) {
                        this.log.error(jCCaseLabel4.pos(), CompilerProperties.Errors.InvalidCaseLabelCombination);
                    } else if (list5.tail.nonEmpty()) {
                        this.log.error(jCCase.labels.tail.tail.head.pos(), CompilerProperties.Errors.InvalidCaseLabelCombination);
                    }
                }
            } else if (list3.tail.nonEmpty()) {
                if (!jCCase.labels.stream().filter(new Predicate() { // from class: pi1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Check.C((JCTree.JCCaseLabel) obj);
                    }
                }).map(new Function() { // from class: qi1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Check.A((JCTree.JCCaseLabel) obj);
                    }
                }).allMatch(new Predicate() { // from class: ri1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Check.F(this.b, (JCTree.JCPatternCaseLabel) obj);
                    }
                })) {
                    this.log.error(jCCase.labels.tail.head.pos(), CompilerProperties.Errors.FlowsThroughFromPattern);
                }
                if (jCCase.labels.stream().allMatch(new Predicate() { // from class: si1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Check.B((JCTree.JCCaseLabel) obj);
                    }
                })) {
                    this.preview.checkSourceLevel(jCCase.labels.tail.head.pos(), Source.Feature.UNNAMED_VARIABLES);
                }
                for (JCTree.JCCaseLabel jCCaseLabel5 : jCCase.labels.tail) {
                    if (jCCaseLabel5 instanceof JCTree.JCConstantCaseLabel) {
                        this.log.error(jCCaseLabel5.pos(), CompilerProperties.Errors.InvalidCaseLabelCombination);
                        break;
                    }
                }
            }
        }
        if (list.nonEmpty() && list.head.caseKind == CaseTree.CaseKind.STATEMENT) {
            boolean z = false;
            for (List list6 = list; list6.nonEmpty(); list6 = list6.tail) {
                JCTree.JCCase jCCase2 = (JCTree.JCCase) list6.head;
                if (z && jCCase2.stats.nonEmpty()) {
                    JCTree.JCCaseLabel jCCaseLabel6 = jCCase2.labels.head;
                    if ((jCCaseLabel6 instanceof JCTree.JCPatternCaseLabel) && (hasBindings(((JCTree.JCPatternCaseLabel) jCCaseLabel6).pat) || hasBindings(jCCase2.guard))) {
                        this.log.error(jCCase2.labels.head.pos(), CompilerProperties.Errors.FlowsThroughToPattern);
                    } else if (jCCase2.stats.isEmpty()) {
                        jCCaseLabel = jCCase2.labels.head;
                        if (!(jCCaseLabel instanceof JCTree.JCPatternCaseLabel)) {
                        }
                    }
                } else if (jCCase2.stats.isEmpty()) {
                    jCCaseLabel = jCCase2.labels.head;
                    if (!(jCCaseLabel instanceof JCTree.JCPatternCaseLabel) && ((hasBindings(((JCTree.JCPatternCaseLabel) jCCaseLabel).pat) || hasBindings(jCCase2.guard)) && hasStatements(list6.tail))) {
                        this.log.error(jCCase2.labels.head.pos(), CompilerProperties.Errors.FlowsThroughFromPattern);
                    }
                }
                z = jCCase2.completesNormally;
            }
        }
    }

    public void checkTransparentClass(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.ClassSymbol classSymbol, Scope scope) {
        for (Symbol symbol : scope.getSymbolsByName(classSymbol.name)) {
            if (symbol.owner != classSymbol.owner) {
                return;
            }
            if (symbol.kind == Kinds.Kind.TYP && !symbol.type.hasTag(TypeTag.TYPEVAR) && symbol.owner.kind.matches(Kinds.KindSelector.VAL_MTH) && classSymbol.name != this.names.error) {
                duplicateError(diagnosticPosition, symbol);
                return;
            }
        }
    }

    public void checkTransparentVar(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.VarSymbol varSymbol, Scope scope) {
        Symbol next;
        Symbol symbol;
        Iterator<Symbol> it = scope.getSymbolsByName(varSymbol.name).iterator();
        while (it.hasNext() && (symbol = (next = it.next()).owner) == varSymbol.owner) {
            if (next.kind == Kinds.Kind.VAR && symbol.kind.matches(Kinds.KindSelector.VAL_MTH) && varSymbol.name != this.names.error) {
                duplicateError(diagnosticPosition, next);
                return;
            }
        }
    }

    public Type checkType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2, CheckContext checkContext) {
        final Check check;
        final JCDiagnostic.DiagnosticPosition diagnosticPosition2;
        final Type type3;
        final Type type4;
        final CheckContext checkContext2;
        InferenceContext inferenceContext = checkContext.inferenceContext();
        if (inferenceContext.free(type2) || inferenceContext.free(type)) {
            check = this;
            diagnosticPosition2 = diagnosticPosition;
            type3 = type;
            type4 = type2;
            checkContext2 = checkContext;
            inferenceContext.addFreeTypeListener(List.of(type2, type), new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.s
                @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                public final void typesInferred(InferenceContext inferenceContext2) {
                    Check.y(this.a, diagnosticPosition2, type3, type4, checkContext2, inferenceContext2);
                }
            });
        } else {
            check = this;
            diagnosticPosition2 = diagnosticPosition;
            type3 = type;
            type4 = type2;
            checkContext2 = checkContext;
        }
        if (type4.hasTag(TypeTag.ERROR)) {
            return type4;
        }
        if (type4.hasTag(TypeTag.NONE) || checkContext2.compatible(type3, type4, checkContext2.checkWarner(diagnosticPosition2, type3, type4))) {
            return type3;
        }
        if (type3.isNumeric() && type4.isNumeric()) {
            checkContext2.report(diagnosticPosition2, check.diags.fragment(CompilerProperties.Fragments.PossibleLossOfPrecision(type3, type4)));
            return check.types.createErrorType(type3);
        }
        checkContext2.report(diagnosticPosition2, check.diags.fragment(CompilerProperties.Fragments.InconvertibleTypes(type3, type4)));
        return check.types.createErrorType(type3);
    }

    public boolean checkUnique(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Scope scope) {
        Kinds.Kind kind;
        if (symbol.type.isErroneous()) {
            return true;
        }
        if (symbol.owner.name == this.names.any) {
            return false;
        }
        for (Symbol symbol2 : scope.getSymbolsByName(symbol.name, Scope.LookupKind.NON_RECURSIVE)) {
            if (symbol != symbol2 && (symbol2.flags() & Flags.CLASH) == 0 && (kind = symbol.kind) == symbol2.kind && symbol.name != this.names.error) {
                Kinds.Kind kind2 = Kinds.Kind.MTH;
                if (kind == kind2 && !this.types.hasSameArgs(symbol.type, symbol2.type)) {
                    Types types = this.types;
                    if (types.hasSameArgs(types.erasure(symbol.type), this.types.erasure(symbol2.type))) {
                    }
                }
                if ((symbol.flags() & Flags.VARARGS) != (symbol2.flags() & Flags.VARARGS)) {
                    symbol.flags_field |= Flags.CLASH;
                    varargsDuplicateError(diagnosticPosition, symbol, symbol2);
                    return true;
                }
                if (symbol.kind == kind2 && !this.types.hasSameArgs(symbol.type, symbol2.type, false)) {
                    duplicateErasureError(diagnosticPosition, symbol, symbol2);
                    symbol.flags_field |= Flags.CLASH;
                    return true;
                }
                if ((symbol.flags() & Flags.MATCH_BINDING) == 0 || (symbol2.flags() & Flags.MATCH_BINDING) == 0 || (symbol2.flags() & Flags.MATCH_BINDING_TO_OUTER) != 0) {
                    duplicateError(diagnosticPosition, symbol2);
                    return false;
                }
                if (!symbol.type.isErroneous()) {
                    this.log.error(diagnosticPosition, CompilerProperties.Errors.MatchBindingExists);
                    symbol.flags_field |= Flags.CLASH;
                }
                return false;
            }
        }
        return true;
    }

    public boolean checkUniqueClassName(JCDiagnostic.DiagnosticPosition diagnosticPosition, Name name, Scope scope) {
        Name name2;
        for (Symbol symbol : scope.getSymbolsByName(name, Scope.LookupKind.NON_RECURSIVE)) {
            if (symbol.kind == Kinds.Kind.TYP && symbol.name != this.names.error) {
                duplicateError(diagnosticPosition, symbol);
                return false;
            }
        }
        for (Symbol symbol2 = scope.owner; symbol2 != null; symbol2 = symbol2.owner) {
            if (symbol2.kind == Kinds.Kind.TYP && (name2 = symbol2.name) == name && name2 != this.names.error && !symbol2.isImplicit()) {
                duplicateError(diagnosticPosition, symbol2);
                return true;
            }
        }
        return true;
    }

    public boolean checkValidGenericType(Type type) {
        return firstIncompatibleTypeArg(type) == null;
    }

    public void checkVarargsMethodDecl(Env<AttrContext> env, JCTree.JCMethodDecl jCMethodDecl) {
        Symbol.MethodSymbol methodSymbol = jCMethodDecl.sym;
        boolean z = methodSymbol.attribute(this.syms.trustMeType.tsym) != null;
        Type typeElemtype = methodSymbol.isVarArgs() ? this.types.elemtype(jCMethodDecl.params.last().type) : null;
        if (z && !isTrustMeAllowedOnMethod(methodSymbol)) {
            if (typeElemtype == null) {
                this.log.error(jCMethodDecl, CompilerProperties.Errors.VarargsInvalidTrustmeAnno(this.syms.trustMeType.tsym, CompilerProperties.Fragments.VarargsTrustmeOnNonVarargsMeth(methodSymbol)));
                return;
            }
            boolean zAllowedInSource = Source.Feature.PRIVATE_SAFE_VARARGS.allowedInSource(this.source);
            JCDiagnostic.Factory factory = this.diags;
            this.log.error(jCMethodDecl, CompilerProperties.Errors.VarargsInvalidTrustmeAnno(this.syms.trustMeType.tsym, zAllowedInSource ? factory.fragment(CompilerProperties.Fragments.VarargsTrustmeOnVirtualVarargs(methodSymbol)) : factory.fragment(CompilerProperties.Fragments.VarargsTrustmeOnVirtualVarargsFinalOnly(methodSymbol))));
            return;
        }
        if (z && typeElemtype != null && this.types.isReifiable(typeElemtype)) {
            this.log.warning(jCMethodDecl.pos(), CompilerProperties.LintWarnings.VarargsRedundantTrustmeAnno(this.syms.trustMeType.tsym, this.diags.fragment(CompilerProperties.Fragments.VarargsTrustmeOnReifiableVarargs(typeElemtype))));
        } else {
            if (z || typeElemtype == null || this.types.isReifiable(typeElemtype)) {
                return;
            }
            warnUnchecked(jCMethodDecl.params.head.pos(), CompilerProperties.LintWarnings.UncheckedVarargsNonReifiableType(typeElemtype));
        }
    }

    public void clearLocalClassNameIndexes(Symbol.ClassSymbol classSymbol) {
        Symbol symbol = classSymbol.owner;
        if (symbol == null || symbol.kind == Kinds.Kind.NIL) {
            return;
        }
        this.localClassNameIndexes.remove(new Pair(symbol.enclClass().flatname, classSymbol.name));
    }

    public <T> void compareAndRemove(java.util.List<T> list, ToIntBiFunction<? super T, ? super T> toIntBiFunction) {
        int i = 0;
        while (i < list.size() - 1) {
            T t = list.get(i);
            int i2 = i + 1;
            while (i2 < list.size()) {
                int iApplyAsInt = toIntBiFunction.applyAsInt(t, list.get(i2));
                if ((iApplyAsInt & 2) != 0) {
                    list.remove(i2);
                    i2--;
                }
                if ((iApplyAsInt & 1) != 0) {
                    list.remove(i);
                    i--;
                    break;
                }
                i2++;
            }
            i++;
        }
    }

    public Type completionError(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.CompletionFailure completionFailure) {
        this.log.error(JCDiagnostic.DiagnosticFlag.NON_DEFERRABLE, diagnosticPosition, CompilerProperties.Errors.CantAccess(completionFailure.sym, completionFailure.getDetailValue()));
        return this.syms.errType;
    }

    public Warner convertWarner(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2) {
        return new ConversionWarner(diagnosticPosition, "unchecked.assign", type, type2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Type> diff(List<Type> list, List<Type> list2) {
        for (List list3 = list2; list3.nonEmpty(); list3 = list3.tail) {
            list = excl((Type) list3.head, list);
        }
        return list;
    }

    public void duplicateErasureError(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Symbol symbol2) {
        if (symbol.type.isErroneous() || symbol2.type.isErroneous()) {
            return;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.NameClashSameErasure(symbol, symbol2));
    }

    public void duplicateError(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
        if (symbol.type.isErroneous()) {
            return;
        }
        Symbol symbolLocation = symbol.location();
        Kinds.Kind kind = symbolLocation.kind;
        Kinds.Kind kind2 = Kinds.Kind.MTH;
        if (kind == kind2 && ((Symbol.MethodSymbol) symbolLocation).isStaticOrInstanceInit()) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.AlreadyDefinedInClinit(Kinds.kindName(symbol), symbol, Kinds.kindName(symbol.location()), Kinds.kindName(symbol.location().enclClass()), symbol.location().enclClass()));
            return;
        }
        if (symbolLocation.kind == kind2) {
            long j = symbol.owner.flags_field;
            if ((Flags.GENERATEDCONSTR & j) != 0 && (j & Flags.RECORD) != 0) {
                return;
            }
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.AlreadyDefined(Kinds.kindName(symbol), symbol, Kinds.kindName(symbol.location()), symbol.location()));
    }

    public List<Type> excl(Type type, List<Type> list) {
        if (!list.isEmpty()) {
            List<Type> listExcl = excl(type, list.tail);
            if (this.types.isSubtype(list.head, type)) {
                return listExcl;
            }
            if (listExcl != list.tail) {
                return listExcl.prepend(list.head);
            }
        }
        return list;
    }

    public Optional<Set<Name>> getApplicableTargets(JCTree.JCAnnotation jCAnnotation, Symbol symbol) {
        Name[] nameArrDefaultTargetMetaInfo;
        Kinds.Kind kind;
        Kinds.Kind kind2;
        Attribute.Array attributeTargetAttribute = getAttributeTargetAttribute(jCAnnotation.annotationType.type.tsym);
        HashSet hashSet = new HashSet();
        if (attributeTargetAttribute != null) {
            Name[] nameArr = new Name[attributeTargetAttribute.values.length];
            int i = 0;
            while (true) {
                Attribute[] attributeArr = attributeTargetAttribute.values;
                if (i >= attributeArr.length) {
                    nameArrDefaultTargetMetaInfo = nameArr;
                    break;
                }
                Attribute attribute = attributeArr[i];
                if (!(attribute instanceof Attribute.Enum)) {
                    return Optional.empty();
                }
                nameArr[i] = ((Attribute.Enum) attribute).value.name;
                i++;
            }
        } else {
            nameArrDefaultTargetMetaInfo = defaultTargetMetaInfo();
        }
        for (Name name : nameArrDefaultTargetMetaInfo) {
            Names names = this.names;
            Name name2 = names.TYPE;
            if (name != name2) {
                Name name3 = names.FIELD;
                if (name == name3) {
                    if (symbol.kind == Kinds.Kind.VAR && symbol.owner.kind != Kinds.Kind.MTH) {
                        hashSet.add(name3);
                    }
                } else if (name == names.RECORD_COMPONENT) {
                    if (symbol.getKind() == ElementKind.RECORD_COMPONENT) {
                        hashSet.add(this.names.RECORD_COMPONENT);
                    }
                } else if (name == names.METHOD) {
                    if (symbol.kind == Kinds.Kind.MTH && !symbol.isConstructor()) {
                        hashSet.add(this.names.METHOD);
                    }
                } else if (name == names.PARAMETER) {
                    if (symbol.kind == Kinds.Kind.VAR && symbol.owner.kind == Kinds.Kind.MTH && (symbol.flags() & 8589934592L) != 0) {
                        hashSet.add(this.names.PARAMETER);
                    }
                } else if (name == names.CONSTRUCTOR) {
                    if (symbol.kind == Kinds.Kind.MTH && symbol.isConstructor()) {
                        hashSet.add(this.names.CONSTRUCTOR);
                    }
                } else if (name == names.LOCAL_VARIABLE) {
                    if (symbol.kind == Kinds.Kind.VAR && symbol.owner.kind == Kinds.Kind.MTH && (symbol.flags() & 8589934592L) == 0) {
                        hashSet.add(this.names.LOCAL_VARIABLE);
                    }
                } else if (name != names.ANNOTATION_TYPE) {
                    Name name4 = names.PACKAGE;
                    if (name == name4) {
                        if (symbol.kind == Kinds.Kind.PCK) {
                            hashSet.add(name4);
                        }
                    } else if (name == names.TYPE_USE) {
                        Kinds.Kind kind3 = symbol.kind;
                        Kinds.Kind kind4 = Kinds.Kind.VAR;
                        if ((kind3 != kind4 || symbol.owner.kind != Kinds.Kind.MTH || !symbol.type.hasTag(TypeTag.NONE)) && ((kind = symbol.kind) == Kinds.Kind.TYP || kind == kind4 || ((kind == (kind2 = Kinds.Kind.MTH) && !symbol.isConstructor() && !symbol.type.mo73getReturnType().hasTag(TypeTag.VOID)) || (symbol.kind == kind2 && symbol.isConstructor())))) {
                            hashSet.add(this.names.TYPE_USE);
                        }
                    } else if (name != names.TYPE_PARAMETER) {
                        Name name5 = names.MODULE;
                        if (name != name5) {
                            this.log.error(jCAnnotation, CompilerProperties.Errors.AnnotationUnrecognizedAttributeName(jCAnnotation.type, name));
                            return Optional.empty();
                        }
                        if (symbol.kind == Kinds.Kind.MDL) {
                            hashSet.add(name5);
                        }
                    } else if (symbol.kind == Kinds.Kind.TYP && symbol.type.hasTag(TypeTag.TYPEVAR)) {
                        hashSet.add(this.names.TYPE_PARAMETER);
                    }
                } else if (symbol.kind == Kinds.Kind.TYP && (symbol.flags() & 8192) != 0) {
                    hashSet.add(this.names.ANNOTATION_TYPE);
                }
            } else if (symbol.kind == Kinds.Kind.TYP) {
                hashSet.add(name2);
            }
        }
        return Optional.of(hashSet);
    }

    public Attribute.Array getAttributeTargetAttribute(Symbol.TypeSymbol typeSymbol) {
        Attribute.Compound target = typeSymbol.getAnnotationTypeMetadata().getTarget();
        if (target == null) {
            return null;
        }
        Attribute attributeMember = target.member(this.names.value);
        if (attributeMember instanceof Attribute.Array) {
            return (Attribute.Array) attributeMember;
        }
        return null;
    }

    public Symbol.ClassSymbol getCompiled(Symbol.ClassSymbol classSymbol) {
        return this.compiled.get(Pair.of(classSymbol.packge().modle, classSymbol.flatname));
    }

    public Name[] getTargetNames(Symbol.TypeSymbol typeSymbol) {
        Attribute.Array attributeTargetAttribute = getAttributeTargetAttribute(typeSymbol);
        if (attributeTargetAttribute == null) {
            return defaultTargetMetaInfo();
        }
        Name[] nameArr = new Name[attributeTargetAttribute.values.length];
        int i = 0;
        while (true) {
            Attribute[] attributeArr = attributeTargetAttribute.values;
            if (i >= attributeArr.length) {
                return nameArr;
            }
            Attribute attribute = attributeArr[i];
            if (!(attribute instanceof Attribute.Enum)) {
                return new Name[0];
            }
            nameArr[i] = ((Attribute.Enum) attribute).value.name;
            i++;
        }
    }

    public boolean hasBindings(JCTree jCTree) {
        final boolean[] zArr = new boolean[1];
        new TreeScanner(this) { // from class: com.sun.tools.javac.comp.Check.5
            final /* synthetic */ Check this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
                boolean[] zArr2 = zArr;
                zArr2[0] = zArr2[0] | (!jCBindingPattern.var.sym.isUnnamedVariable());
                super.visitBindingPattern(jCBindingPattern);
            }
        }.scan(jCTree);
        return zArr[0];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean hasStatements(List<JCTree.JCCase> list) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            if (((JCTree.JCCase) list2.head).stats.nonEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean importAccessible(Symbol symbol, Symbol.PackageSymbol packageSymbol) {
        try {
            int iFlags = (int) (symbol.flags() & 7);
            if (iFlags != 0) {
                if (iFlags == 2) {
                    return false;
                }
                if (iFlags != 4) {
                    return true;
                }
            }
            return symbol.packge() == packageSymbol;
        } catch (ClassFinder.BadClassFile e) {
            throw e;
        } catch (Symbol.CompletionFailure unused) {
        }
    }

    public List<Type> incl(Type type, List<Type> list) {
        return subset(type, list) ? list : excl(type, list).prepend(type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.sun.tools.javac.util.List, com.sun.tools.javac.util.List<com.sun.tools.javac.code.Type>] */
    /* JADX WARN: Type inference failed for: r5v1, types: [com.sun.tools.javac.util.List] */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.sun.tools.javac.util.List<A>] */
    public List<Type> intersect(List<Type> list, List<Type> list2) {
        List<Type> listNil = List.nil();
        for (List list3 = list; list3.nonEmpty(); list3 = list3.tail) {
            if (subset((Type) list3.head, list2)) {
                listNil = incl((Type) list3.head, listNil);
            }
        }
        while (list2.nonEmpty()) {
            if (subset((Type) list2.head, list)) {
                listNil = incl((Type) list2.head, listNil);
            }
            list2 = list2.tail;
        }
        return listNil;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean intersects(Type type, List<Type> list) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            if (this.types.isSubtype(type, (Type) list2.head) || this.types.isSubtype((Type) list2.head, type)) {
                return true;
            }
        }
        return false;
    }

    public boolean isChecked(Type type) {
        return !isUnchecked(type);
    }

    public boolean isExternalizable(Type type) {
        try {
            this.syms.externalizableType.complete();
            return this.types.isSubtype(type, this.syms.externalizableType);
        } catch (Symbol.CompletionFailure unused) {
            return false;
        }
    }

    public boolean isHandled(Type type, List<Type> list) {
        return isUnchecked(type) || subset(type, list);
    }

    public boolean isOverrider(Symbol symbol) {
        if (symbol.kind == Kinds.Kind.MTH && !symbol.isStatic()) {
            Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) symbol;
            Symbol.TypeSymbol typeSymbol = (Symbol.TypeSymbol) methodSymbol.owner;
            for (Type type : this.types.closure(typeSymbol.type)) {
                if (type != typeSymbol.type) {
                    for (Symbol symbol2 : type.tsym.members().getSymbolsByName(methodSymbol.name)) {
                        if (!symbol2.isStatic() && methodSymbol.overrides(symbol2, typeSymbol, this.types, true)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isTypeAnnotation(JCTree.JCAnnotation jCAnnotation, final boolean z) {
        List<Attribute> listAnnotationTargets = this.typeAnnotations.annotationTargets(jCAnnotation.annotationType.type.tsym);
        if (listAnnotationTargets == null) {
            return Source.Feature.NO_TARGET_ANNOTATION_APPLICABILITY.allowedInSource(this.source) && z;
        }
        return listAnnotationTargets.stream().anyMatch(new Predicate() { // from class: ui1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.isTypeAnnotation((Attribute) obj, z);
            }
        });
    }

    public boolean isTypeArgErroneous(Type type) {
        return this.isTypeArgErroneous.visit(type).booleanValue();
    }

    public boolean isUnchecked(Type type) {
        if (type.hasTag(TypeTag.TYPEVAR)) {
            return isUnchecked(this.types.supertype(type));
        }
        return type.hasTag(TypeTag.CLASS) ? isUnchecked((Symbol.ClassSymbol) type.tsym) : type.hasTag(TypeTag.BOT);
    }

    public Name localClassName(Symbol.ClassSymbol classSymbol) {
        Name name = classSymbol.owner.enclClass().flatname;
        String string = name.toString();
        Pair<Name, Name> pair = new Pair<>(name, classSymbol.name);
        Integer num = this.localClassNameIndexes.get(pair);
        int iIntValue = num == null ? 1 : num.intValue();
        while (true) {
            Name nameFromString = this.names.fromString(string + this.syntheticNameChar + iIntValue + classSymbol.name);
            if (getCompiled(classSymbol.packge().modle, nameFromString) == null) {
                this.localClassNameIndexes.put(pair, Integer.valueOf(iIntValue + 1));
                return nameFromString;
            }
            iIntValue++;
        }
    }

    public <C extends Collection<Symbol.MethodSymbol>> List<C> methodsGroupedByName(Type type, Predicate<Symbol> predicate, Supplier<? extends C> supplier) {
        Stream stream = StreamSupport.stream(this.types.membersClosure(type, false).getSymbols(predicate, Scope.LookupKind.RECURSIVE).spliterator(), false);
        final Class<Symbol.MethodSymbol> cls = Symbol.MethodSymbol.class;
        return (List) ((Map) stream.map(new Function() { // from class: bj1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (Symbol.MethodSymbol) cls.cast((Symbol) obj);
            }
        }).collect(Collectors.groupingBy(new Function() { // from class: uh1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symbol.MethodSymbol) obj).name;
            }
        }, Collectors.toCollection(supplier)))).entrySet().stream().sorted(Comparator.comparing(new Function() { // from class: vh1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Name) ((Map.Entry) obj).getKey()).toString();
            }
        })).map(new Function() { // from class: wh1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (Collection) ((Map.Entry) obj).getValue();
            }
        }).collect(List.collector());
    }

    public void newRound() {
        this.compiled.clear();
        this.localClassNameIndexes.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean potentiallyAmbiguousOverload(Type type, Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2) {
        Assert.check(methodSymbol.name == methodSymbol2.name);
        if (methodSymbol == methodSymbol2) {
            return false;
        }
        Type typeMemberType = this.types.memberType(type, methodSymbol);
        Type typeMemberType2 = this.types.memberType(type, methodSymbol2);
        TypeTag typeTag = TypeTag.FORALL;
        if (typeMemberType.hasTag(typeTag) && typeMemberType2.hasTag(typeTag)) {
            Type.ForAll forAll = (Type.ForAll) typeMemberType;
            Type.ForAll forAll2 = (Type.ForAll) typeMemberType2;
            if (this.types.hasSameBounds(forAll, forAll2)) {
                typeMemberType2 = this.types.subst(typeMemberType2, forAll2.tvars, forAll.tvars);
            }
        }
        int iMax = Math.max(typeMemberType.mo71getParameterTypes().length(), typeMemberType2.mo71getParameterTypes().length());
        List listAdjustArgs = this.rs.adjustArgs(typeMemberType.mo71getParameterTypes(), methodSymbol, iMax, true);
        List listAdjustArgs2 = this.rs.adjustArgs(typeMemberType2.mo71getParameterTypes(), methodSymbol2, iMax, true);
        if (listAdjustArgs.length() != listAdjustArgs2.length()) {
            return false;
        }
        boolean z = false;
        while (listAdjustArgs.nonEmpty() && listAdjustArgs2.nonEmpty()) {
            Type type2 = (Type) listAdjustArgs.head;
            Type type3 = (Type) listAdjustArgs2.head;
            if (!this.types.isSubtype(type3, type2) && !this.types.isSubtype(type2, type3)) {
                if (!this.types.isFunctionalInterface(type2) || !this.types.isFunctionalInterface(type3) || this.types.findDescriptorType(type2).mo71getParameterTypes().length() <= 0 || this.types.findDescriptorType(type2).mo71getParameterTypes().length() != this.types.findDescriptorType(type3).mo71getParameterTypes().length()) {
                    return false;
                }
                z = true;
            }
            listAdjustArgs = listAdjustArgs.tail;
            listAdjustArgs2 = listAdjustArgs2.tail;
        }
        return z;
    }

    public void putCompiled(Symbol.ClassSymbol classSymbol) {
        this.compiled.put(Pair.of(classSymbol.packge().modle, classSymbol.flatname), classSymbol);
    }

    public void removeCompiled(Symbol.ClassSymbol classSymbol) {
        this.compiled.remove(Pair.of(classSymbol.packge().modle, classSymbol.flatname));
    }

    public <T> void removePreempted(java.util.List<T> list, final BiPredicate<? super T, ? super T> biPredicate) {
        compareAndRemove(list, new ToIntBiFunction() { // from class: aj1
            @Override // java.util.function.ToIntBiFunction
            public final int applyAsInt(Object obj, Object obj2) {
                return Check.l(biPredicate, obj, obj2);
            }
        });
    }

    public boolean setImportSuppression(boolean z) {
        boolean z2 = this.importSuppression;
        this.importSuppression = z;
        return z2;
    }

    public Lint setLint(Lint lint) {
        Lint lint2 = this.lint;
        this.lint = lint;
        return lint2;
    }

    public Symbol.MethodSymbol setMethod(Symbol.MethodSymbol methodSymbol) {
        Symbol.MethodSymbol methodSymbol2 = this.method;
        this.method = methodSymbol;
        return methodSymbol2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean subset(Type type, List<Type> list) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            if (this.types.isSubtype(type, (Type) list2.head)) {
                return true;
            }
        }
        return false;
    }

    public Type typeTagError(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic, Object obj) {
        boolean z = obj instanceof Type;
        if (z && ((Type) obj).hasTag(TypeTag.VOID)) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.IllegalStartOfType);
            return this.syms.errType;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.TypeFoundReq(obj, jCDiagnostic));
        return this.types.createErrorType(z ? (Type) obj : this.syms.errType);
    }

    public JCDiagnostic.Fragment uncheckedOverrides(Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2) {
        Symbol symbolLocation = methodSymbol.location();
        Symbol symbolLocation2 = methodSymbol2.location();
        if ((methodSymbol2.owner.flags() & 512) == 0) {
            return CompilerProperties.Fragments.UncheckedOverride(methodSymbol, symbolLocation, methodSymbol2, symbolLocation2);
        }
        return (methodSymbol.owner.flags() & 512) == 0 ? CompilerProperties.Fragments.UncheckedImplement(methodSymbol, symbolLocation, methodSymbol2, symbolLocation2) : CompilerProperties.Fragments.UncheckedClashWith(methodSymbol, symbolLocation, methodSymbol2, symbolLocation2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Type> unhandled(List<Type> list, List<Type> list2) {
        List<Type> listNil = List.nil();
        for (List list3 = list; list3.nonEmpty(); list3 = list3.tail) {
            if (!isHandled((Type) list3.head, list2)) {
                listNil = listNil.prepend((Type) list3.head);
            }
        }
        return listNil;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Type> union(List<Type> list, List<Type> list2) {
        for (List list3 = list2; list3.nonEmpty(); list3 = list3.tail) {
            list = incl((Type) list3.head, list);
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void validate(List<? extends JCTree> list, Env<AttrContext> env) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            validate((JCTree) list2.head, env);
        }
    }

    public boolean validateAnnotationDeferErrors(JCTree.JCAnnotation jCAnnotation) {
        Log log = this.log;
        Objects.requireNonNull(log);
        Log.DiscardDiagnosticHandler discardDiagnosticHandler = log.new DiscardDiagnosticHandler();
        try {
            return validateAnnotation(jCAnnotation);
        } finally {
            this.log.popDiagnosticHandler(discardDiagnosticHandler);
        }
    }

    public void validateAnnotationMethod(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.MethodSymbol methodSymbol) {
        Type typeSupertype = this.syms.annotationType;
        while (typeSupertype.hasTag(TypeTag.CLASS)) {
            for (Symbol symbol : typeSupertype.tsym.members().getSymbolsByName(methodSymbol.name)) {
                if (symbol.kind == Kinds.Kind.MTH && (symbol.flags() & 5) != 0 && this.types.overrideEquivalent(methodSymbol.type, symbol.type)) {
                    this.log.error(diagnosticPosition, CompilerProperties.Errors.IntfAnnotationMemberClash(symbol, typeSupertype));
                }
            }
            typeSupertype = this.types.supertype(typeSupertype);
        }
    }

    public void validateAnnotationTree(JCTree jCTree) {
        jCTree.accept(new TreeScanner() { // from class: com.sun.tools.javac.comp.Check.1AnnotationValidator
            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
                if (jCAnnotation.type.isErroneous() || !jCAnnotation.type.tsym.isAnnotationType()) {
                    return;
                }
                super.visitAnnotation(jCAnnotation);
                Check.this.validateAnnotation(jCAnnotation);
            }
        });
    }

    public void validateAnnotationType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        if (type.isPrimitive() || this.types.isSameType(type, this.syms.stringType) || (type.tsym.flags() & 16384) != 0 || (type.tsym.flags() & 8192) != 0 || this.types.cvarLowerBound(type).tsym == this.syms.classType.tsym) {
            return;
        }
        if (this.types.isArray(type)) {
            Types types = this.types;
            if (!types.isArray(types.elemtype(type))) {
                validateAnnotationType(diagnosticPosition, this.types.elemtype(type));
                return;
            }
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidAnnotationMemberType);
    }

    public void validateAnnotations(List<JCTree.JCAnnotation> list, JCTree jCTree, Symbol symbol) {
        Iterator<JCTree.JCAnnotation> it = list.iterator();
        while (it.hasNext()) {
            validateAnnotation(it.next(), jCTree, symbol);
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0043  */
    public void validateRepeatable(Symbol.TypeSymbol typeSymbol, Attribute.Compound compound, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        Type value;
        Assert.check(this.types.isSameType(compound.type, this.syms.repeatableType));
        List<Pair<Symbol.MethodSymbol, Attribute>> list = compound.values;
        if (list.isEmpty()) {
            value = null;
        } else {
            Assert.check(list.head.fst.name == this.names.value);
            Pair<Symbol.MethodSymbol, Attribute> pair = list.head;
            if (pair.snd instanceof Attribute.Class) {
                value = ((Attribute.Class) pair.snd).getValue();
            } else {
                value = null;
            }
        }
        if (value == null) {
            return;
        }
        validateValue(value.tsym, typeSymbol, diagnosticPosition);
        validateRetention(value.tsym, typeSymbol, diagnosticPosition);
        validateDocumented(value.tsym, typeSymbol, diagnosticPosition);
        validateInherited(value.tsym, typeSymbol, diagnosticPosition);
        validateTarget(value.tsym, typeSymbol, diagnosticPosition);
        validateDefault(value.tsym, diagnosticPosition);
    }

    public boolean validateTargetAnnotationValue(JCTree.JCAnnotation jCAnnotation) {
        boolean z = true;
        if (jCAnnotation.annotationType.type.tsym == this.syms.annotationTargetType.tsym) {
            List<JCTree.JCExpression> list = jCAnnotation.args;
            if (list.tail != null) {
                if (!list.head.hasTag(JCTree.Tag.ASSIGN)) {
                    return false;
                }
                JCTree.JCAssign jCAssign = (JCTree.JCAssign) jCAnnotation.args.head;
                if (TreeInfo.symbol(jCAssign.lhs).name != this.names.value) {
                    return false;
                }
                JCTree.JCExpression jCExpression = jCAssign.rhs;
                if (!jCExpression.hasTag(JCTree.Tag.NEWARRAY)) {
                    return false;
                }
                HashSet hashSet = new HashSet();
                for (JCTree.JCExpression jCExpression2 : ((JCTree.JCNewArray) jCExpression).elems) {
                    if (!hashSet.add(TreeInfo.symbol(jCExpression2))) {
                        this.log.error(jCExpression2.pos(), CompilerProperties.Errors.RepeatedAnnotationTarget);
                        z = false;
                    }
                }
            }
        }
        return z;
    }

    public void validateTypeAnnotation(JCTree.JCAnnotation jCAnnotation, Symbol symbol, boolean z) {
        Assert.checkNonNull(jCAnnotation.type);
        if (symbol != null) {
            getApplicableTargets(jCAnnotation, symbol);
        }
        validateAnnotationTree(jCAnnotation);
        if (!jCAnnotation.hasTag(JCTree.Tag.TYPE_ANNOTATION) || jCAnnotation.annotationType.type.isErroneous() || isTypeAnnotation(jCAnnotation, z)) {
            return;
        }
        this.log.error(jCAnnotation.pos(), CompilerProperties.Errors.AnnotationTypeNotApplicableToType(jCAnnotation.type));
    }

    public void validateTypeAnnotations(List<JCTree.JCAnnotation> list, Symbol symbol, boolean z) {
        Iterator<JCTree.JCAnnotation> it = list.iterator();
        while (it.hasNext()) {
            validateTypeAnnotation(it.next(), symbol, z);
        }
    }

    public void varargsDuplicateError(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Symbol symbol2) {
        if (symbol.type.isErroneous() || symbol2.type.isErroneous()) {
            return;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.ArrayAndVarargs(symbol, symbol2, symbol2.location()));
    }

    public JCDiagnostic.Fragment varargsOverrides(Symbol.MethodSymbol methodSymbol, Symbol.MethodSymbol methodSymbol2) {
        Symbol symbolLocation = methodSymbol.location();
        Symbol symbolLocation2 = methodSymbol2.location();
        if ((methodSymbol2.owner.flags() & 512) == 0) {
            return CompilerProperties.Fragments.VarargsOverride(methodSymbol, symbolLocation, methodSymbol2, symbolLocation2);
        }
        return (methodSymbol.owner.flags() & 512) == 0 ? CompilerProperties.Fragments.VarargsImplement(methodSymbol, symbolLocation, methodSymbol2, symbolLocation2) : CompilerProperties.Fragments.VarargsClashWith(methodSymbol, symbolLocation, methodSymbol2, symbolLocation2);
    }

    public void warnDeprecated(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol) {
        JCDiagnostic.LintWarning lintWarningHasBeenDeprecatedModule;
        Assert.check(!this.importSuppression);
        boolean zIsDeprecatedForRemoval = symbol.isDeprecatedForRemoval();
        Kinds.Kind kind = symbol.kind;
        if (zIsDeprecatedForRemoval) {
            lintWarningHasBeenDeprecatedModule = kind == Kinds.Kind.MDL ? CompilerProperties.LintWarnings.HasBeenDeprecatedForRemovalModule(symbol) : CompilerProperties.LintWarnings.HasBeenDeprecatedForRemoval(symbol, symbol.location());
        } else {
            lintWarningHasBeenDeprecatedModule = kind == Kinds.Kind.MDL ? CompilerProperties.LintWarnings.HasBeenDeprecatedModule(symbol) : CompilerProperties.LintWarnings.HasBeenDeprecated(symbol, symbol.location());
        }
        this.log.warning(diagnosticPosition, lintWarningHasBeenDeprecatedModule);
    }

    public void warnPreviewAPI(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.LintWarning lintWarning) {
        if (this.importSuppression) {
            return;
        }
        this.log.warning(diagnosticPosition, lintWarning);
    }

    public void warnUnchecked(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic.LintWarning lintWarning) {
        this.log.warning(diagnosticPosition, lintWarning);
    }

    public class RequiresIdentityVisitor extends Types.SimpleVisitor<Void, Set<Type>> {
        boolean requiresWarning;

        private RequiresIdentityVisitor() {
            this.requiresWarning = false;
        }

        public static /* synthetic */ boolean a(RequiresIdentityVisitor requiresIdentityVisitor, Type.ClassType classType, Attribute.TypeCompound typeCompound) {
            return Check.this.isRequiresIdentityAnnotation(typeCompound.type.tsym) && classType.getTypeArguments().get(typeCompound.position.parameter_index) != null && classType.getTypeArguments().get(typeCompound.position.parameter_index).isValueBased();
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitArrayType(Type.ArrayType arrayType, Set<Type> set) {
            return visit(arrayType.elemtype, set);
        }

        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitCapturedType(Type.CapturedType capturedType, Set<Type> set) {
            if (!set.add(capturedType)) {
                return null;
            }
            visit(capturedType.getUpperBound(), set);
            visit(capturedType.getLowerBound(), set);
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitClassType(final Type.ClassType classType, Set<Type> set) {
            Symbol.TypeSymbol typeSymbol;
            SymbolMetadata metadata;
            if (classType != null && (typeSymbol = classType.tsym) != null && (metadata = typeSymbol.getMetadata()) != null && !classType.getTypeArguments().isEmpty() && metadata.getTypeAttributes().stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.w
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Check.RequiresIdentityVisitor.a(this.b, classType, (Attribute.TypeCompound) obj);
                }
            }).findAny().isPresent()) {
                this.requiresWarning = true;
                return null;
            }
            visit(classType.getEnclosingType(), set);
            Iterator<Type> it = classType.getTypeArguments().iterator();
            while (it.hasNext()) {
                visit(it.next(), set);
            }
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitTypeVar(Type.TypeVar typeVar, Set<Type> set) {
            if (!set.add(typeVar)) {
                return null;
            }
            visit(typeVar.getUpperBound(), set);
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitWildcardType(Type.WildcardType wildcardType, Set<Type> set) {
            return visit(wildcardType.type, set);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Void visitType(Type type, Set<Type> set) {
            return null;
        }
    }

    public class SerialTypeVisitor extends ElementKindVisitor14<Void, JCTree.JCClassDecl> {
        private final Type OSF_TYPE;
        Lint lint;
        private static final Set<String> serialMethodNames = Collections.unmodifiableSet(new HashSet(Arrays.asList("writeObject", "writeReplace", "readObject", "readObjectNoData", "readResolve")));
        private static final Set<String> serialFieldNames = Collections.unmodifiableSet(new HashSet(Arrays.asList("serialVersionUID", "serialPersistentFields")));

        public SerialTypeVisitor() {
            this.OSF_TYPE = new Type.ArrayType(Check.this.syms.objectStreamFieldType, Check.this.syms.arrayClass);
            this.lint = Check.this.lint;
        }

        public static /* synthetic */ void a(SerialTypeVisitor serialTypeVisitor, boolean z, TypeElement typeElement, JCTree.JCClassDecl jCClassDecl, Element element, JCTree.JCClassDecl jCClassDecl2) {
            JCTree.JCClassDecl jCClassDecl3;
            serialTypeVisitor.getClass();
            String string = element.getSimpleName().toString();
            int i = AnonymousClass6.$SwitchMap$javax$lang$model$element$ElementKind[element.getKind().ordinal()];
            if (i == 1) {
                Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) element;
                if (serialFieldNames.contains(string)) {
                    Check.this.log.warning(TreeInfo.diagnosticPositionFor(varSymbol, jCClassDecl2), CompilerProperties.LintWarnings.IneffectualSerialFieldEnum(string));
                    return;
                }
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
                JCTree.JCExpression jCExpression = ((JCTree.JCVariableDecl) TreeInfo.declarationFor((Symbol.VarSymbol) element, jCClassDecl)).init;
                if (!(jCExpression instanceof JCTree.JCNewClass) || (jCClassDecl3 = ((JCTree.JCNewClass) jCExpression).def) == null) {
                    return;
                }
                serialTypeVisitor.visitTypeAsEnum((TypeElement) jCClassDecl3.sym, jCClassDecl);
                return;
            }
            Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) element;
            if (serialMethodNames.contains(string)) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl2), CompilerProperties.LintWarnings.IneffectualSerialMethodEnum(string));
            }
            if (z) {
                string.getClass();
                if (string.equals("writeExternal")) {
                    serialTypeVisitor.checkWriteExternalEnum(jCClassDecl2, typeElement, methodSymbol);
                } else if (string.equals("readExternal")) {
                    serialTypeVisitor.checkReadExternalEnum(jCClassDecl2, typeElement, methodSymbol);
                }
            }
        }

        public static /* synthetic */ boolean b(Symbol symbol) {
            return symbol.kind == Kinds.Kind.VAR;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void checkCtorAccess(JCTree.JCClassDecl jCClassDecl, Symbol.ClassSymbol classSymbol) {
            if (Check.this.isExternalizable(classSymbol.type)) {
                for (Symbol symbol : classSymbol.getEnclosedElements()) {
                    if (symbol.isConstructor() && (symbol.flags() & 1) == 1 && ((Symbol.MethodSymbol) symbol).getParameters().isEmpty()) {
                        return;
                    }
                }
                Check.this.log.warning(jCClassDecl.pos(), CompilerProperties.LintWarnings.ExternalizableMissingPublicNoArgCtor);
                return;
            }
            Type superclass = classSymbol.getSuperclass();
            while (Check.this.rs.isSerializable(superclass)) {
                try {
                    superclass = (Type) ((TypeElement) ((DeclaredType) superclass).asElement()).getSuperclass();
                } catch (ClassCastException unused) {
                    return;
                }
            }
            Symbol.ClassSymbol classSymbol2 = (Symbol.ClassSymbol) ((DeclaredType) superclass).asElement();
            for (Symbol symbol2 : classSymbol2.getEnclosedElements()) {
                if (symbol2.isConstructor()) {
                    Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) symbol2;
                    if (methodSymbol.getParameters().isEmpty() && ((methodSymbol.flags() & 2) == 2 || (classSymbol2.getNestingKind() == NestingKind.MEMBER && (classSymbol2.flags() & 8) == 0))) {
                        Check.this.log.warning(jCClassDecl.pos(), CompilerProperties.LintWarnings.SerializableMissingAccessNoArgCtor(classSymbol2.getQualifiedName()));
                    }
                }
            }
        }

        private void checkDefaultIneffective(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            if ((methodSymbol.flags() & Flags.DEFAULT) == Flags.DEFAULT) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.DefaultIneffective);
            }
        }

        private void checkExceptions(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol, Type... typeArr) {
            for (Type type : methodSymbol.getThrownTypes()) {
                if (!Check.this.types.isSubtype(type, Check.this.syms.runtimeExceptionType) && !Check.this.types.isSubtype(type, Check.this.syms.errorType)) {
                    boolean z = false;
                    for (Type type2 : typeArr) {
                        if (Check.this.types.isSubtype(type, type2)) {
                            z = true;
                        }
                    }
                    if (!z) {
                        Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.SerialMethodUnexpectedException(methodSymbol.getSimpleName(), type));
                    }
                }
            }
        }

        private void checkExternMethodEnum(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol, Type type) {
            if (isExternMethod(jCClassDecl, element, methodSymbol, type)) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.IneffectualExternMethodEnum(methodSymbol.getSimpleName().toString()));
            }
        }

        private void checkExternMethodRecord(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol, Type type, boolean z) {
            if (z && isExternMethod(jCClassDecl, element, methodSymbol, type)) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.IneffectualExternalizableMethodRecord(methodSymbol.getSimpleName().toString()));
            }
        }

        private void checkExternalizable(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            if (Check.this.isExternalizable((Type) element.asType())) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.IneffectualSerialMethodExternalizable(methodSymbol.getSimpleName()));
            }
        }

        private void checkNoArgs(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            List<Symbol.VarSymbol> parameters = methodSymbol.getParameters();
            if (parameters.isEmpty()) {
                return;
            }
            Check.this.log.warning(TreeInfo.diagnosticPositionFor(parameters.get(0), jCClassDecl), CompilerProperties.LintWarnings.SerialMethodNoArgs(methodSymbol.getSimpleName()));
        }

        private void checkOneArg(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol, Type type) {
            methodSymbol.getSimpleName().toString();
            List<Symbol.VarSymbol> parameters = methodSymbol.getParameters();
            if (parameters.size() != 1) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.SerialMethodOneArg(methodSymbol.getSimpleName(), parameters.size()));
                return;
            }
            Type typeAsType = parameters.get(0).asType();
            if (Check.this.types.isSameType(typeAsType, type)) {
                return;
            }
            Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.SerialMethodParameterType(methodSymbol.getSimpleName(), type, typeAsType));
        }

        private void checkPrivateMethod(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            if ((methodSymbol.flags() & 2) == 0) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.NonPrivateMethodWeakerAccess);
            }
        }

        private void checkReadExternalEnum(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            checkExternMethodEnum(jCClassDecl, element, methodSymbol, Check.this.syms.objectInputType);
        }

        private void checkReadExternalRecord(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol, boolean z) {
            checkExternMethodRecord(jCClassDecl, element, methodSymbol, Check.this.syms.objectInputType, z);
        }

        private void checkReadObject(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            checkPrivateNonStaticMethod(jCClassDecl, methodSymbol);
            checkReturnType(jCClassDecl, element, methodSymbol, Check.this.syms.voidType);
            checkOneArg(jCClassDecl, element, methodSymbol, Check.this.syms.objectInputStreamType);
            checkExceptions(jCClassDecl, element, methodSymbol, Check.this.syms.ioExceptionType, Check.this.syms.classNotFoundExceptionType);
            checkExternalizable(jCClassDecl, element, methodSymbol);
        }

        private void checkReadObjectNoData(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            checkPrivateNonStaticMethod(jCClassDecl, methodSymbol);
            checkReturnType(jCClassDecl, element, methodSymbol, Check.this.syms.voidType);
            checkNoArgs(jCClassDecl, element, methodSymbol);
            checkExceptions(jCClassDecl, element, methodSymbol, Check.this.syms.objectStreamExceptionType);
            checkExternalizable(jCClassDecl, element, methodSymbol);
        }

        private void checkReadResolve(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            checkConcreteInstanceMethod(jCClassDecl, element, methodSymbol);
            checkReturnType(jCClassDecl, element, methodSymbol, Check.this.syms.objectType);
            checkNoArgs(jCClassDecl, element, methodSymbol);
            checkExceptions(jCClassDecl, element, methodSymbol, Check.this.syms.objectStreamExceptionType);
        }

        private void checkReturnType(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol, Type type) {
            Type returnType = methodSymbol.getReturnType();
            if (Check.this.types.isSameType(type, returnType)) {
                return;
            }
            Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.SerialMethodUnexpectedReturnType(methodSymbol.getSimpleName(), returnType, type));
        }

        private void checkSerialPersistentFields(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.VarSymbol varSymbol) {
            JCTree.JCExpression jCExpression;
            if ((varSymbol.flags() & 26) != 26) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(varSymbol, jCClassDecl), CompilerProperties.LintWarnings.ImproperSPF);
            }
            if (!Check.this.types.isSameType(varSymbol.type, this.OSF_TYPE)) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(varSymbol, jCClassDecl), CompilerProperties.LintWarnings.OSFArraySPF);
            }
            if (Check.this.isExternalizable((Type) element.asType())) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(varSymbol, jCClassDecl), CompilerProperties.LintWarnings.IneffectualSerialFieldExternalizable);
            }
            JCTree jCTreeDeclarationFor = TreeInfo.declarationFor(varSymbol, jCClassDecl);
            if (jCTreeDeclarationFor == null || jCTreeDeclarationFor.getTag() != JCTree.Tag.VARDEF || (jCExpression = ((JCTree.JCVariableDecl) jCTreeDeclarationFor).init) == null || !TreeInfo.isNull(jCExpression)) {
                return;
            }
            Check.this.log.warning(jCExpression.pos(), CompilerProperties.LintWarnings.SPFNullInit);
        }

        private void checkSerialVersionUID(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.VarSymbol varSymbol) {
            if ((varSymbol.flags() & 24) != 24) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(varSymbol, jCClassDecl), CompilerProperties.LintWarnings.ImproperSVUID((Symbol) element));
            }
            if (!varSymbol.type.hasTag(TypeTag.LONG)) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(varSymbol, jCClassDecl), CompilerProperties.LintWarnings.LongSVUID((Symbol) element));
            }
            if (varSymbol.getConstValue() == null) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(varSymbol, jCClassDecl), CompilerProperties.LintWarnings.ConstantSVUID((Symbol) element));
            }
        }

        private void checkWriteExternalEnum(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            checkExternMethodEnum(jCClassDecl, element, methodSymbol, Check.this.syms.objectOutputType);
        }

        private void checkWriteExternalRecord(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol, boolean z) {
            checkExternMethodRecord(jCClassDecl, element, methodSymbol, Check.this.syms.objectOutputType, z);
        }

        private void checkWriteObject(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            checkPrivateNonStaticMethod(jCClassDecl, methodSymbol);
            checkReturnType(jCClassDecl, element, methodSymbol, Check.this.syms.voidType);
            checkOneArg(jCClassDecl, element, methodSymbol, Check.this.syms.objectOutputStreamType);
            checkExceptions(jCClassDecl, element, methodSymbol, Check.this.syms.ioExceptionType);
            checkExternalizable(jCClassDecl, element, methodSymbol);
        }

        private void checkWriteReplace(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            checkConcreteInstanceMethod(jCClassDecl, element, methodSymbol);
            checkReturnType(jCClassDecl, element, methodSymbol, Check.this.syms.objectType);
            checkNoArgs(jCClassDecl, element, methodSymbol);
            checkExceptions(jCClassDecl, element, methodSymbol, Check.this.syms.objectStreamExceptionType);
        }

        public static /* synthetic */ void d(SerialTypeVisitor serialTypeVisitor, TypeElement typeElement, Element element, JCTree.JCClassDecl jCClassDecl) {
            serialTypeVisitor.getClass();
            int i = AnonymousClass6.$SwitchMap$javax$lang$model$element$ElementKind[element.getKind().ordinal()];
            if (i == 1) {
                Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) element;
                String string = varSymbol.getSimpleName().toString();
                string.getClass();
                if (string.equals("serialVersionUID")) {
                    serialTypeVisitor.checkSerialVersionUID(jCClassDecl, typeElement, varSymbol);
                    return;
                } else {
                    if (string.equals("serialPersistentFields")) {
                        Check.this.log.warning(TreeInfo.diagnosticPositionFor(varSymbol, jCClassDecl), CompilerProperties.LintWarnings.IneffectualSerialFieldInterface);
                        return;
                    }
                    return;
                }
            }
            if (i != 2) {
                return;
            }
            Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) element;
            String string2 = element.getSimpleName().toString();
            if (serialMethodNames.contains(string2)) {
                string2.getClass();
                switch (string2) {
                    case "readObject":
                    case "writeObject":
                    case "readObjectNoData":
                        serialTypeVisitor.checkPrivateMethod(jCClassDecl, typeElement, methodSymbol);
                        break;
                    case "writeReplace":
                    case "readResolve":
                        serialTypeVisitor.checkDefaultIneffective(jCClassDecl, typeElement, methodSymbol);
                        break;
                    default:
                        x1f.a();
                        break;
                }
            }
        }

        public static /* synthetic */ void e(SerialTypeVisitor serialTypeVisitor, TypeElement typeElement, boolean z, Element element, JCTree.JCClassDecl jCClassDecl) {
            serialTypeVisitor.getClass();
            String string = element.getSimpleName().toString();
            int i = AnonymousClass6.$SwitchMap$javax$lang$model$element$ElementKind[element.getKind().ordinal()];
            if (i == 1) {
                Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) element;
                string.getClass();
                if (string.equals("serialVersionUID")) {
                    serialTypeVisitor.checkSerialVersionUID(jCClassDecl, typeElement, varSymbol);
                    return;
                } else {
                    if (string.equals("serialPersistentFields")) {
                        Check.this.log.warning(TreeInfo.diagnosticPositionFor(varSymbol, jCClassDecl), CompilerProperties.LintWarnings.IneffectualSerialFieldRecord);
                        return;
                    }
                    return;
                }
            }
            if (i != 2) {
                return;
            }
            Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) element;
            string.getClass();
            switch (string) {
                case "writeReplace":
                    serialTypeVisitor.checkWriteReplace(jCClassDecl, typeElement, methodSymbol);
                    break;
                case "readResolve":
                    serialTypeVisitor.checkReadResolve(jCClassDecl, typeElement, methodSymbol);
                    break;
                case "writeExternal":
                    serialTypeVisitor.checkWriteExternalRecord(jCClassDecl, typeElement, methodSymbol, z);
                    break;
                case "readExternal":
                    serialTypeVisitor.checkReadExternalRecord(jCClassDecl, typeElement, methodSymbol, z);
                    break;
                default:
                    if (serialMethodNames.contains(string)) {
                        Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.IneffectualSerialMethodRecord(string));
                        break;
                    }
                    break;
            }
        }

        public static /* synthetic */ void f(SerialTypeVisitor serialTypeVisitor, boolean z, TypeElement typeElement, Symbol symbol, JCTree.JCClassDecl jCClassDecl) {
            serialTypeVisitor.getClass();
            int i = AnonymousClass6.$SwitchMap$javax$lang$model$element$ElementKind[symbol.getKind().ordinal()];
            if (i == 1) {
                if (!z) {
                    long jFlags = symbol.flags();
                    if ((128 & jFlags) == 0 && (jFlags & 8) == 0) {
                        Type typeAsType = symbol.asType();
                        if (!serialTypeVisitor.canBeSerialized(typeAsType)) {
                            Check.this.log.warning(TreeInfo.diagnosticPositionFor(symbol, jCClassDecl), CompilerProperties.LintWarnings.NonSerializableInstanceField);
                        } else if (typeAsType.hasTag(TypeTag.ARRAY)) {
                            Type type = ((Type.ArrayType) typeAsType).elemtype;
                            while (type.hasTag(TypeTag.ARRAY)) {
                                type = ((Type.ArrayType) type).elemtype;
                            }
                            if (!serialTypeVisitor.canBeSerialized(type)) {
                                Check.this.log.warning(TreeInfo.diagnosticPositionFor(symbol, jCClassDecl), CompilerProperties.LintWarnings.NonSerializableInstanceFieldArray(type));
                            }
                        }
                    }
                }
                String string = symbol.getSimpleName().toString();
                if (serialFieldNames.contains(string)) {
                    Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) symbol;
                    string.getClass();
                    if (string.equals("serialVersionUID")) {
                        serialTypeVisitor.checkSerialVersionUID(jCClassDecl, typeElement, varSymbol);
                        return;
                    } else if (string.equals("serialPersistentFields")) {
                        serialTypeVisitor.checkSerialPersistentFields(jCClassDecl, typeElement, varSymbol);
                        return;
                    } else {
                        x1f.a();
                        return;
                    }
                }
                return;
            }
            if (i != 2) {
                return;
            }
            Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) symbol;
            String string2 = methodSymbol.getSimpleName().toString();
            if (serialMethodNames.contains(string2)) {
                string2.getClass();
                switch (string2) {
                    case "readObject":
                        serialTypeVisitor.checkReadObject(jCClassDecl, typeElement, methodSymbol);
                        break;
                    case "writeReplace":
                        serialTypeVisitor.checkWriteReplace(jCClassDecl, typeElement, methodSymbol);
                        break;
                    case "readResolve":
                        serialTypeVisitor.checkReadResolve(jCClassDecl, typeElement, methodSymbol);
                        break;
                    case "writeObject":
                        serialTypeVisitor.checkWriteObject(jCClassDecl, typeElement, methodSymbol);
                        break;
                    case "readObjectNoData":
                        serialTypeVisitor.checkReadObjectNoData(jCClassDecl, typeElement, methodSymbol);
                        break;
                    default:
                        x1f.a();
                        break;
                }
            }
        }

        private boolean hasExactlyOneArgWithType(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol, Type type) {
            List<Symbol.VarSymbol> parameters = methodSymbol.getParameters();
            return parameters.size() == 1 && Check.this.types.isSameType(parameters.get(0).asType(), type);
        }

        private boolean isExternMethod(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol, Type type) {
            long jFlags = methodSymbol.flags();
            return (1 & jFlags) != 0 && (jFlags & 8) == 0 && Check.this.types.isSameType(Check.this.syms.voidType, methodSymbol.getReturnType()) && hasExactlyOneArgWithType(jCClassDecl, element, methodSymbol, type);
        }

        private <E extends Element> Void runUnderLint(E e, JCTree.JCClassDecl jCClassDecl, BiConsumer<E, JCTree.JCClassDecl> biConsumer) {
            Lint lint = this.lint;
            try {
                Lint lintAugment = lint.augment((Symbol) e);
                this.lint = lintAugment;
                if (lintAugment.isEnabled(Lint.LintCategory.SERIAL)) {
                    biConsumer.accept(e, jCClassDecl);
                }
                return null;
            } finally {
                this.lint = lint;
            }
        }

        public boolean canBeSerialized(Type type) {
            return type.isPrimitive() || Check.this.rs.isSerializable(type);
        }

        public void checkConcreteInstanceMethod(JCTree.JCClassDecl jCClassDecl, Element element, Symbol.MethodSymbol methodSymbol) {
            if ((methodSymbol.flags() & 1032) != 0) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.SerialConcreteInstanceMethod(methodSymbol.getSimpleName()));
            }
        }

        public void checkPrivateNonStaticMethod(JCTree.JCClassDecl jCClassDecl, Symbol.MethodSymbol methodSymbol) {
            long jFlags = methodSymbol.flags();
            if ((2 & jFlags) == 0) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.SerialMethodNotPrivate(methodSymbol.getSimpleName()));
            }
            if ((jFlags & 8) != 0) {
                Check.this.log.warning(TreeInfo.diagnosticPositionFor(methodSymbol, jCClassDecl), CompilerProperties.LintWarnings.SerialMethodStatic(methodSymbol.getSimpleName()));
            }
        }

        public Void defaultAction(Element element, JCTree.JCClassDecl jCClassDecl) {
            throw new IllegalArgumentException((String) ObjectsWrapper.requireNonNullElse(element.toString(), ""));
        }

        public Void visitType(TypeElement typeElement, JCTree.JCClassDecl jCClassDecl) {
            runUnderLint(typeElement, jCClassDecl, new BiConsumer() { // from class: com.sun.tools.javac.comp.b0
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    super/*javax.lang.model.util.ElementKindVisitor6*/.visitType((TypeElement) obj, (JCTree.JCClassDecl) obj2);
                }
            });
            return null;
        }

        public Void visitTypeAsClass(final TypeElement typeElement, JCTree.JCClassDecl jCClassDecl) {
            Symbol.VarSymbol varSymbol;
            Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) typeElement;
            checkCtorAccess(jCClassDecl, classSymbol);
            Iterator<Symbol> it = classSymbol.members().getSymbolsByName(Check.this.names.serialVersionUID).iterator();
            while (true) {
                if (!it.hasNext()) {
                    varSymbol = null;
                    break;
                }
                Symbol next = it.next();
                if (next.kind == Kinds.Kind.VAR) {
                    varSymbol = (Symbol.VarSymbol) next;
                    break;
                }
            }
            if (varSymbol == null) {
                Check.this.log.warning(jCClassDecl.pos(), CompilerProperties.LintWarnings.MissingSVUID(classSymbol));
            }
            final boolean zHasNext = classSymbol.members().getSymbolsByName(Check.this.names.serialPersistentFields, new Predicate() { // from class: com.sun.tools.javac.comp.x
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Check.SerialTypeVisitor.b((Symbol) obj);
                }
            }).iterator().hasNext();
            Iterator<Symbol> it2 = classSymbol.getEnclosedElements().iterator();
            while (it2.hasNext()) {
                runUnderLint(it2.next(), jCClassDecl, new BiConsumer() { // from class: com.sun.tools.javac.comp.y
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        Check.SerialTypeVisitor.f(this.a, zHasNext, typeElement, (Symbol) obj, (JCTree.JCClassDecl) obj2);
                    }
                });
            }
            return null;
        }

        public Void visitTypeAsEnum(final TypeElement typeElement, final JCTree.JCClassDecl jCClassDecl) {
            final boolean zIsExternalizable = Check.this.isExternalizable((Type) typeElement.asType());
            Iterator<? extends Element> it = typeElement.getEnclosedElements().iterator();
            while (it.hasNext()) {
                runUnderLint(it.next(), jCClassDecl, new BiConsumer() { // from class: com.sun.tools.javac.comp.z
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        Check.SerialTypeVisitor.a(this.a, zIsExternalizable, typeElement, jCClassDecl, (Element) obj, (JCTree.JCClassDecl) obj2);
                    }
                });
            }
            return null;
        }

        public Void visitTypeAsInterface(final TypeElement typeElement, JCTree.JCClassDecl jCClassDecl) {
            Iterator<? extends Element> it = typeElement.getEnclosedElements().iterator();
            while (it.hasNext()) {
                runUnderLint(it.next(), jCClassDecl, new BiConsumer() { // from class: com.sun.tools.javac.comp.a0
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        Check.SerialTypeVisitor.d(this.a, typeElement, (Element) obj, (JCTree.JCClassDecl) obj2);
                    }
                });
            }
            return null;
        }

        public Void visitTypeAsRecord(final TypeElement typeElement, JCTree.JCClassDecl jCClassDecl) {
            final boolean zIsExternalizable = Check.this.isExternalizable((Type) typeElement.asType());
            Iterator<? extends Element> it = typeElement.getEnclosedElements().iterator();
            while (it.hasNext()) {
                runUnderLint(it.next(), jCClassDecl, new BiConsumer() { // from class: com.sun.tools.javac.comp.c0
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        Check.SerialTypeVisitor.e(this.a, typeElement, zIsExternalizable, (Element) obj, (JCTree.JCClassDecl) obj2);
                    }
                });
            }
            return null;
        }

        public Void visitTypeAsAnnotationType(TypeElement typeElement, JCTree.JCClassDecl jCClassDecl) {
            return null;
        }
    }

    public void checkNonCyclic(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        checkNonCyclicInternal(diagnosticPosition, type);
    }

    public void validate(JCTree jCTree, Env<AttrContext> env, boolean z) {
        new Validator(env).validateTree(jCTree, z, true);
    }

    public void validate(JCTree jCTree, Env<AttrContext> env) {
        validate(jCTree, env, true);
    }

    public Symbol.ClassSymbol getCompiled(Symbol.ModuleSymbol moduleSymbol, Name name) {
        return this.compiled.get(Pair.of(moduleSymbol, name));
    }

    public Type checkCastable(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2) {
        return checkCastable(diagnosticPosition, type, type2, this.basicHandler);
    }

    public Name[] getTargetNames(JCTree.JCAnnotation jCAnnotation) {
        return getTargetNames(jCAnnotation.annotationType.type.tsym);
    }

    public boolean isUnchecked(Symbol.ClassSymbol classSymbol) {
        return classSymbol.kind == Kinds.Kind.ERR || classSymbol.isSubClass(this.syms.errorType.tsym, this.types) || classSymbol.isSubClass(this.syms.runtimeExceptionType.tsym, this.types);
    }

    public boolean isTypeAnnotation(Attribute attribute, boolean z) {
        Name name = ((Attribute.Enum) attribute).value.name;
        Names names = this.names;
        if (name != names.TYPE_USE) {
            return z && name == names.TYPE_PARAMETER;
        }
        return true;
    }

    public boolean isUnchecked(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        try {
            return isUnchecked(type);
        } catch (Symbol.CompletionFailure e) {
            completionError(diagnosticPosition, e);
            return true;
        }
    }

    public Type checkClassType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        return (type.hasTag(TypeTag.CLASS) || type.hasTag(TypeTag.ERROR)) ? type : typeTagError(diagnosticPosition, this.diags.fragment(CompilerProperties.Fragments.TypeReqClass), asTypeParam(type));
    }

    public void checkDeprecated(final JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Symbol symbol2) {
        checkDeprecated(new Supplier() { // from class: ni1
            @Override // java.util.function.Supplier
            public final Object get() {
                return Check.f(diagnosticPosition);
            }
        }, symbol, symbol2);
    }

    private void closure(Type type, Map<Symbol.TypeSymbol, Type> map) {
        if (type.hasTag(TypeTag.CLASS) && map.put(type.tsym, type) == null) {
            closure(this.types.supertype(type), map);
            Iterator<Type> it = this.types.interfaces(type).iterator();
            while (it.hasNext()) {
                closure(it.next(), map);
            }
        }
    }

    public void validateAnnotationType(JCTree jCTree) {
        if (jCTree != null) {
            validateAnnotationType(jCTree.pos(), jCTree.type);
        }
    }

    public void checkImplementations(JCTree.JCClassDecl jCClassDecl) {
        Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
        checkImplementations(jCClassDecl, classSymbol, classSymbol);
    }

    public void checkClassBounds(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
        checkClassBounds(diagnosticPosition, new HashMap(), type);
    }

    public Type checkType(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2) {
        return checkType(diagnosticPosition, type, type2, this.basicHandler);
    }

    public void checkPreview(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol symbol, Symbol symbol2) {
        checkPreview(diagnosticPosition, symbol, Type.noType, symbol2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean validateAnnotation(JCTree.JCAnnotation jCAnnotation) {
        JCDiagnostic.Error errorAnnotationMissingDefaultValue;
        Annotate.AnnotationTypeMetadata annotationTypeMetadata = jCAnnotation.annotationType.type.tsym.getAnnotationTypeMetadata();
        Set<Symbol.MethodSymbol> annotationElements = annotationTypeMetadata.getAnnotationElements();
        boolean z = true;
        for (JCTree.JCExpression jCExpression : jCAnnotation.args) {
            if (jCExpression.hasTag(JCTree.Tag.ASSIGN)) {
                JCTree.JCAssign jCAssign = (JCTree.JCAssign) jCExpression;
                Symbol symbol = TreeInfo.symbol(jCAssign.lhs);
                if (symbol != null && !symbol.type.isErroneous() && !annotationElements.remove(symbol)) {
                    this.log.error(jCAssign.lhs.pos(), CompilerProperties.Errors.DuplicateAnnotationMemberValue(symbol.name, jCAnnotation.type));
                    z = false;
                }
            }
        }
        List listNil = List.nil();
        Set<Symbol.MethodSymbol> annotationElementsWithDefault = annotationTypeMetadata.getAnnotationElementsWithDefault();
        for (Symbol.MethodSymbol methodSymbol : annotationElements) {
            if (!methodSymbol.type.isErroneous() && !annotationElementsWithDefault.contains(methodSymbol)) {
                listNil = listNil.append(methodSymbol.name);
            }
        }
        List listReverse = listNil.reverse();
        if (listReverse.nonEmpty()) {
            int size = listReverse.size();
            Type type = jCAnnotation.type;
            if (size > 1) {
                errorAnnotationMissingDefaultValue = CompilerProperties.Errors.AnnotationMissingDefaultValue1(type, listReverse);
            } else {
                errorAnnotationMissingDefaultValue = CompilerProperties.Errors.AnnotationMissingDefaultValue(type, listReverse);
            }
            this.log.error(jCAnnotation.pos(), errorAnnotationMissingDefaultValue);
            z = false;
        }
        return z && validateTargetAnnotationValue(jCAnnotation);
    }

    public void checkOverride(Env<AttrContext> env, final JCTree.JCMethodDecl jCMethodDecl, Symbol.MethodSymbol methodSymbol) {
        JCDiagnostic.Error errorAnonymousDiamondMethodDoesNotOverrideSuperclass;
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) methodSymbol.owner;
        if ((classSymbol.flags() & 16384) != 0 && this.names.finalize.equals(methodSymbol.name) && methodSymbol.overrides(this.syms.enumFinalFinalize, classSymbol, this.types, false)) {
            this.log.error(jCMethodDecl.pos(), CompilerProperties.Errors.EnumNoFinalize);
            return;
        }
        if (this.allowRecords && classSymbol.isRecord() && classSymbol.getRecordComponents().stream().filter(new Predicate() { // from class: yh1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Check.E(jCMethodDecl, (Symbol.RecordComponent) obj);
            }
        }).findFirst().isPresent()) {
            return;
        }
        Type typeSupertype = classSymbol.type;
        while (typeSupertype.hasTag(TypeTag.CLASS)) {
            if (typeSupertype != classSymbol.type) {
                checkOverride(jCMethodDecl, typeSupertype, classSymbol, methodSymbol);
            }
            Iterator<Type> it = this.types.interfaces(typeSupertype).iterator();
            while (it.hasNext()) {
                checkOverride(jCMethodDecl, it.next(), classSymbol, methodSymbol);
            }
            typeSupertype = this.types.supertype(typeSupertype);
        }
        boolean z = methodSymbol.attribute(this.syms.overrideType.tsym) != null;
        if ((z || !(!env.info.isAnonymousDiamond || methodSymbol.isConstructor() || methodSymbol.isPrivate())) && !isOverrider(methodSymbol)) {
            JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCMethodDecl.pos();
            for (JCTree.JCAnnotation jCAnnotation : jCMethodDecl.getModifiers().annotations) {
                if (jCAnnotation.annotationType.type.tsym == this.syms.overrideType.tsym) {
                    diagnosticPositionPos = jCAnnotation.pos();
                    break;
                }
            }
            Log log = this.log;
            if (z) {
                errorAnonymousDiamondMethodDoesNotOverrideSuperclass = methodSymbol.isStatic() ? CompilerProperties.Errors.StaticMethodsCannotBeAnnotatedWithOverride(methodSymbol, methodSymbol.enclClass()) : CompilerProperties.Errors.MethodDoesNotOverrideSuperclass(methodSymbol, methodSymbol.enclClass());
            } else {
                errorAnonymousDiamondMethodDoesNotOverrideSuperclass = CompilerProperties.Errors.AnonymousDiamondMethodDoesNotOverrideSuperclass(CompilerProperties.Fragments.DiamondAnonymousMethodsImplicitlyOverride);
            }
            log.error(diagnosticPositionPos, errorAnonymousDiamondMethodDoesNotOverrideSuperclass);
        }
    }

    public void checkOverride(JCTree jCTree, Type type, Symbol.ClassSymbol classSymbol, Symbol.MethodSymbol methodSymbol) {
        for (Symbol symbol : type.tsym.members().getSymbolsByName(methodSymbol.name)) {
            if (methodSymbol.overrides(symbol, classSymbol, this.types, false) && (symbol.flags() & 1024) == 0) {
                checkOverride(jCTree, methodSymbol, (Symbol.MethodSymbol) symbol, classSymbol);
            }
        }
    }
}

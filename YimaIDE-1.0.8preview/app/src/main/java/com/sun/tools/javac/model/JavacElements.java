package com.sun.tools.javac.model;

import com.sun.source.util.JavacTask;
import com.sun.tools.javac.api.JavacTaskImpl;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Directive;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Attr;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Enter;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.comp.Modules;
import com.sun.tools.javac.comp.Resolve;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.PrintingProcessor;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.DocCommentTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Constants;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Pair;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.lang.model.AnnotatedConstruct;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacElements implements Elements {
    private final boolean allowModules;
    private final Attr attr;
    private final Enter enter;
    private final JavaCompiler javaCompiler;
    private final JavacTaskImpl javacTaskImpl;
    private final Log log;
    private final Modules modules;
    private final Names names;
    private final Resolve resolve;
    private final Symtab syms;
    private final Types types;
    private final Set<String> alreadyWarnedDuplicates = new HashSet();
    private final Map<Pair<String, String>, Optional<Symbol>> resultCache = new HashMap();

    /* JADX INFO: renamed from: com.sun.tools.javac.model.JavacElements$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Kinds$Kind;
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$element$ModuleElement$DirectiveKind;

        static {
            int[] iArr = new int[Kinds.Kind.values().length];
            $SwitchMap$com$sun$tools$javac$code$Kinds$Kind = iArr;
            try {
                iArr[Kinds.Kind.PCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.MDL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.TYP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.ERR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ModuleElement.DirectiveKind.values().length];
            $SwitchMap$javax$lang$model$element$ModuleElement$DirectiveKind = iArr2;
            try {
                iArr2[ModuleElement.DirectiveKind.REQUIRES.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ModuleElement$DirectiveKind[ModuleElement.DirectiveKind.EXPORTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ModuleElement$DirectiveKind[ModuleElement.DirectiveKind.OPENS.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.model.JavacElements$1TS, reason: invalid class name */
    public class C1TS extends TreeScanner {
        JCTree.JCExpression result = null;
        final /* synthetic */ JavacElements this$0;
        final /* synthetic */ Symbol.MethodSymbol val$sym;
        final /* synthetic */ JCTree val$tree;

        public C1TS(JavacElements javacElements, JCTree jCTree, Symbol.MethodSymbol methodSymbol) {
            this.val$tree = jCTree;
            this.val$sym = methodSymbol;
            this.this$0 = javacElements;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            if (jCTree == null || this.result != null) {
                return;
            }
            jCTree.accept(this);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
            if (jCAnnotation == this.val$tree) {
                scan(jCAnnotation.args);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssign(JCTree.JCAssign jCAssign) {
            if (jCAssign.lhs.hasTag(JCTree.Tag.IDENT) && ((JCTree.JCIdent) jCAssign.lhs).sym == this.val$sym) {
                this.result = jCAssign.rhs;
            }
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.model.JavacElements$1Vis, reason: invalid class name */
    public class C1Vis extends JCTree.Visitor {
        List<JCTree.JCAnnotation> result = null;

        public C1Vis() {
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            this.result = jCClassDecl.mods.annotations;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            this.result = jCMethodDecl.mods.annotations;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
            this.result = jCModuleDecl.mods.annotations;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitPackageDef(JCTree.JCPackageDecl jCPackageDecl) {
            this.result = jCPackageDecl.annotations;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
            this.result = jCTypeParameter.annotations;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            this.result = jCVariableDecl.mods.annotations;
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.model.JavacElements$2Vis, reason: invalid class name */
    public class C2Vis implements Attribute.Visitor {
        JCTree result = null;
        final /* synthetic */ JavacElements this$0;
        final /* synthetic */ Attribute val$findme;
        final /* synthetic */ JCTree val$tree;

        public C2Vis(JavacElements javacElements, JCTree jCTree, Attribute attribute) {
            this.val$tree = jCTree;
            this.val$findme = attribute;
            this.this$0 = javacElements;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitArray(Attribute.Array array) {
            if (!this.val$tree.hasTag(JCTree.Tag.NEWARRAY)) {
                Attribute[] attributeArr = array.values;
                if (attributeArr.length == 1) {
                    this.result = this.this$0.matchAttributeToTree(this.val$findme, attributeArr[0], this.val$tree);
                    return;
                }
                return;
            }
            List list = ((JCTree.JCNewArray) this.val$tree).elems;
            for (Attribute attribute : array.values) {
                JCTree jCTreeMatchAttributeToTree = this.this$0.matchAttributeToTree(this.val$findme, attribute, (JCTree) list.head);
                if (jCTreeMatchAttributeToTree != null) {
                    this.result = jCTreeMatchAttributeToTree;
                    return;
                }
                list = list.tail;
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitClass(Attribute.Class r1) {
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitCompound(Attribute.Compound compound) {
            JCTree jCTreeMatchAttributeToTree;
            for (Pair<Symbol.MethodSymbol, Attribute> pair : compound.values) {
                JCTree.JCExpression jCExpressionScanForAssign = this.this$0.scanForAssign(pair.fst, this.val$tree);
                if (jCExpressionScanForAssign != null && (jCTreeMatchAttributeToTree = this.this$0.matchAttributeToTree(this.val$findme, pair.snd, jCExpressionScanForAssign)) != null) {
                    this.result = jCTreeMatchAttributeToTree;
                    return;
                }
            }
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitConstant(Attribute.Constant constant) {
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitEnum(Attribute.Enum r1) {
        }

        @Override // com.sun.tools.javac.code.Attribute.Visitor
        public void visitError(Attribute.Error error) {
        }
    }

    public JavacElements(Context context) {
        context.put((Class<JavacElements>) JavacElements.class, this);
        this.javaCompiler = JavaCompiler.instance(context);
        this.syms = Symtab.instance(context);
        this.modules = Modules.instance(context);
        this.names = Names.instance(context);
        this.types = Types.instance(context);
        this.enter = Enter.instance(context);
        this.attr = Attr.instance(context);
        this.resolve = Resolve.instance(context);
        JavacTask javacTask = (JavacTask) context.get(JavacTask.class);
        this.javacTaskImpl = javacTask instanceof JavacTaskImpl ? (JavacTaskImpl) javacTask : null;
        this.log = Log.instance(context);
        this.allowModules = Source.Feature.MODULES.allowedInSource(Source.instance(context));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void addMembers(Scope.WriteableScope writeableScope, Type type) {
        for (Symbol symbol : type.asElement().members().getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
            Iterator<Symbol> it = writeableScope.getSymbolsByName(symbol.getSimpleName()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    boolean z = symbol.getEnclosingElement() != writeableScope.owner;
                    ElementKind kind = symbol.getKind();
                    boolean z2 = kind == ElementKind.CONSTRUCTOR || kind == ElementKind.INSTANCE_INIT || kind == ElementKind.STATIC_INIT;
                    if (z && (z2 || !symbol.isInheritedIn(writeableScope.owner, this.types))) {
                        break;
                    }
                    writeableScope.enter(symbol);
                    break;
                }
                Symbol next = it.next();
                if (next.kind == symbol.kind && (next.flags() & 4096) == 0 && next.getKind() == ElementKind.METHOD && overrides((ExecutableElement) next, (ExecutableElement) symbol, (TypeElement) type.asElement())) {
                    break;
                }
            }
        }
    }

    public static /* synthetic */ Optional b(JavacElements javacElements, String str, Class cls, String str2, Pair pair) {
        javacElements.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        HashSet hashSet = new HashSet(javacElements.modules.allModules());
        hashSet.removeAll(javacElements.modules.getRootModules());
        Iterator it = Arrays.asList(javacElements.modules.getRootModules(), hashSet).iterator();
        while (it.hasNext()) {
            Iterator it2 = ((Set) it.next()).iterator();
            while (it2.hasNext()) {
                Symbol symbolNameToSymbol = javacElements.nameToSymbol((Symbol.ModuleSymbol) it2.next(), str, cls);
                if (symbolNameToSymbol != null) {
                    if (cls == Symbol.ClassSymbol.class) {
                        linkedHashSet.add(symbolNameToSymbol);
                    } else if (cls == Symbol.PackageSymbol.class && (!symbolNameToSymbol.members().isEmpty() || ((Symbol.PackageSymbol) symbolNameToSymbol).package_info != null)) {
                        linkedHashSet.add(symbolNameToSymbol);
                    }
                }
            }
            if (linkedHashSet.size() == 1) {
                return Optional.of((Symbol) linkedHashSet.iterator().next());
            }
            if (linkedHashSet.size() > 1) {
                if (javacElements.alreadyWarnedDuplicates.add(str2 + ":" + str)) {
                    javacElements.log.note(CompilerProperties.Notes.MultipleElements(str2, str, (String) linkedHashSet.stream().map(new Function() { // from class: tl7
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ((Symbol) obj).packge().modle;
                        }
                    }).map(new Function() { // from class: ul7
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ((Symbol.ModuleSymbol) obj).toString();
                        }
                    }).collect(Collectors.joining(", "))));
                }
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private static <T> T cast(Class<T> cls, Object obj) {
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        b6c.a(obj);
        return null;
    }

    private static boolean containsAnnoOfType(List<Attribute.Compound> list, Type type) {
        Iterator<Attribute.Compound> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().type.tsym == type.tsym) {
                return true;
            }
        }
        return false;
    }

    private <S extends Symbol> S doGetElement(ModuleElement moduleElement, String str, CharSequence charSequence, Class<S> cls) {
        String string = charSequence.toString();
        if (SourceVersion.isName(string) || (string.length() == 0 && cls != Symbol.ClassSymbol.class)) {
            return moduleElement == null ? (S) unboundNameToSymbol(str, string, cls) : (S) nameToSymbol((Symbol.ModuleSymbol) moduleElement, string, cls);
        }
        return null;
    }

    private Symbol.PackageSymbol doGetPackageElement(ModuleElement moduleElement, CharSequence charSequence) {
        ensureEntered("getPackageElement");
        return (Symbol.PackageSymbol) doGetElement(moduleElement, "getPackageElement", charSequence, Symbol.PackageSymbol.class);
    }

    private Symbol.ClassSymbol doGetTypeElement(ModuleElement moduleElement, CharSequence charSequence) {
        ensureEntered("getTypeElement");
        return (Symbol.ClassSymbol) doGetElement(moduleElement, "getTypeElement", charSequence, Symbol.ClassSymbol.class);
    }

    private void ensureEntered(String str) {
        JavacTaskImpl javacTaskImpl = this.javacTaskImpl;
        if (javacTaskImpl != null) {
            javacTaskImpl.ensureEntered();
        }
        if (this.javaCompiler.isEnterDone()) {
            return;
        }
        rc9.a("Cannot use Elements.", str, " before the TaskEvent.Kind.ENTER finished event.");
    }

    private <R> R getDocCommentItem(Element element, BiFunction<DocCommentTable, JCTree, R> biFunction) {
        Pair<JCTree, JCTree.JCCompilationUnit> treeAndTopLevel = getTreeAndTopLevel(element);
        if (treeAndTopLevel == null) {
            return null;
        }
        JCTree jCTree = treeAndTopLevel.fst;
        DocCommentTable docCommentTable = treeAndTopLevel.snd.docComments;
        if (docCommentTable == null) {
            return null;
        }
        return biFunction.apply(docCommentTable, jCTree);
    }

    private Env<AttrContext> getEnterEnv(Symbol symbol) {
        Symbol.TypeSymbol typeSymbolEnclClass;
        int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[symbol.kind.ordinal()];
        if (i != 1) {
            typeSymbolEnclClass = i != 2 ? symbol.enclClass() : (Symbol.ModuleSymbol) symbol;
        } else {
            typeSymbolEnclClass = (Symbol.PackageSymbol) symbol;
        }
        if (typeSymbolEnclClass != null) {
            return this.enter.getEnv(typeSymbolEnclClass);
        }
        return null;
    }

    private JCTree getTreeAlt(Element element) {
        Symbol symbol = (Symbol) cast(Symbol.class, element);
        Env<AttrContext> enterEnv = getEnterEnv(symbol);
        if (enterEnv == null) {
            return null;
        }
        return TreeInfo.declarationFor(symbol, enterEnv.tree);
    }

    public static JavacElements instance(Context context) {
        JavacElements javacElements = (JavacElements) context.get(JavacElements.class);
        return javacElements == null ? new JavacElements(context) : javacElements;
    }

    private boolean isInherited(Type type) {
        return type.tsym.attribute(this.syms.inheritedType.tsym) != null;
    }

    private JCTree matchAnnoToTree(Attribute.Compound compound, List<Attribute.Compound> list, List<JCTree.JCAnnotation> list2) {
        JCTree jCTreeMatchAttributeToTree;
        for (Attribute.Compound compound2 : list) {
            for (JCTree.JCAnnotation jCAnnotation : list2) {
                if (jCAnnotation.type.tsym == compound2.type.tsym && (jCTreeMatchAttributeToTree = matchAttributeToTree(compound, compound2, jCAnnotation)) != null) {
                    return jCTreeMatchAttributeToTree;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JCTree matchAttributeToTree(Attribute attribute, Attribute attribute2, JCTree jCTree) {
        if (attribute2 == attribute) {
            return jCTree;
        }
        C2Vis c2Vis = new C2Vis(this, jCTree, attribute);
        attribute2.accept(c2Vis);
        return c2Vis.result;
    }

    private <S extends Symbol> S nameToSymbol(Symbol.ModuleSymbol moduleSymbol, String str, Class<S> cls) {
        Name nameFromString = this.names.fromString(str);
        Symtab symtab = this.syms;
        Symbol symbolResolveIdent = cls == Symbol.ClassSymbol.class ? symtab.getClass(moduleSymbol, nameFromString) : symtab.lookupPackage(moduleSymbol, nameFromString);
        if (symbolResolveIdent == null) {
            try {
                symbolResolveIdent = this.javaCompiler.resolveIdent(moduleSymbol, str);
            } catch (Symbol.CompletionFailure e) {
                e.dcfh.handleAPICompletionFailure(e);
                return null;
            }
        }
        if (cls.isInstance(symbolResolveIdent)) {
            symbolResolveIdent.complete();
            if (symbolResolveIdent.kind != Kinds.Kind.ERR && symbolResolveIdent.exists() && nameFromString.equals(symbolResolveIdent.getQualifiedName())) {
                return cls.cast(symbolResolveIdent);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JCTree.JCExpression scanForAssign(Symbol.MethodSymbol methodSymbol, JCTree jCTree) {
        C1TS c1ts = new C1TS(this, jCTree, methodSymbol);
        jCTree.accept(c1ts);
        return c1ts.result;
    }

    private <S extends Symbol> S unboundNameToSymbol(final String str, final String str2, final Class<S> cls) {
        Symbol.ModuleSymbol defaultModule = this.modules.getDefaultModule();
        Symbol.ModuleSymbol moduleSymbol = this.syms.noModule;
        return defaultModule == moduleSymbol ? (S) nameToSymbol(moduleSymbol, str2, cls) : (S) this.resultCache.computeIfAbsent(Pair.of(str, str2), new Function() { // from class: xl7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return JavacElements.b(this.b, str2, cls, str, (Pair) obj);
            }
        }).orElse(null);
    }

    @Override // javax.lang.model.util.Elements
    public List<Attribute.Compound> getAllAnnotationMirrors(Element element) {
        Element element2 = (Symbol) cast(Symbol.class, element);
        List<Attribute.Compound> annotationMirrors = element2.getAnnotationMirrors();
        while (element2.getKind() == ElementKind.CLASS) {
            Type superclass = ((Symbol.ClassSymbol) element2).getSuperclass();
            if (!superclass.hasTag(TypeTag.CLASS) || superclass.isErroneous() || (element2 = superclass.tsym) == this.syms.objectType.tsym) {
                break;
            }
            List<Attribute.Compound> listPrepend = annotationMirrors;
            for (Attribute.Compound compound : element2.getAnnotationMirrors()) {
                if (isInherited(compound.type) && !containsAnnoOfType(annotationMirrors, compound.type)) {
                    listPrepend = listPrepend.prepend(compound);
                }
            }
            annotationMirrors = listPrepend;
        }
        return annotationMirrors;
    }

    @Override // javax.lang.model.util.Elements
    public FilteredMemberList getAllMembers(TypeElement typeElement) {
        Symbol symbol = (Symbol) cast(Symbol.class, typeElement);
        Scope.WriteableScope writeableScopeDupUnshared = symbol.members().dupUnshared();
        Iterator<Type> it = this.types.closure(symbol.asType()).iterator();
        while (it.hasNext()) {
            addMembers(writeableScopeDupUnshared, it.next());
        }
        return new FilteredMemberList(writeableScopeDupUnshared);
    }

    @Override // javax.lang.model.util.Elements
    public Set<? extends ModuleElement> getAllModuleElements() {
        ensureEntered("getAllModuleElements");
        return this.allowModules ? Collections.unmodifiableSet(this.modules.allModules()) : Collections.EMPTY_SET;
    }

    @Override // javax.lang.model.util.Elements
    public Name getBinaryName(TypeElement typeElement) {
        return ((Symbol.TypeSymbol) cast(Symbol.TypeSymbol.class, typeElement)).flatName();
    }

    @Override // javax.lang.model.util.Elements
    public String getConstantExpression(Object obj) {
        return Constants.format(obj);
    }

    @Override // javax.lang.model.util.Elements
    public String getDocComment(Element element) {
        return (String) getDocCommentItem(element, new BiFunction() { // from class: vl7
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((DocCommentTable) obj).getCommentText((JCTree) obj2);
            }
        });
    }

    @Override // javax.lang.model.util.Elements
    public Elements.DocCommentKind getDocCommentKind(Element element) {
        return (Elements.DocCommentKind) getDocCommentItem(element, new BiFunction() { // from class: wl7
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((DocCommentTable) obj).getCommentKind((JCTree) obj2);
            }
        });
    }

    @Override // javax.lang.model.util.Elements
    public Map<Symbol.MethodSymbol, Attribute> getElementValuesWithDefaults(AnnotationMirror annotationMirror) {
        Attribute.Compound compound = (Attribute.Compound) cast(Attribute.Compound.class, annotationMirror);
        DeclaredType annotationType = annotationMirror.getAnnotationType();
        Map<Symbol.MethodSymbol, Attribute> elementValues = compound.getElementValues();
        Iterator<ExecutableElement> it = ElementFilter.methodsIn(annotationType.asElement().getEnclosedElements()).iterator();
        while (it.hasNext()) {
            Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) it.next();
            Attribute defaultValue = methodSymbol.getDefaultValue();
            if (defaultValue != null && !elementValues.containsKey(methodSymbol)) {
                elementValues.put(methodSymbol, defaultValue);
            }
        }
        return elementValues;
    }

    @Override // javax.lang.model.util.Elements
    public TypeElement getEnumConstantBody(VariableElement variableElement) {
        if (variableElement.getKind() != ElementKind.ENUM_CONSTANT) {
            w01.a("Argument not an enum constant");
            return null;
        }
        JCTree treeAlt = getTreeAlt(variableElement);
        JCTree treeAlt2 = getTreeAlt(variableElement.getEnclosingElement());
        if (treeAlt instanceof JCTree.JCVariableDecl) {
            JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) treeAlt;
            if (treeAlt2 instanceof JCTree.JCClassDecl) {
                JCTree.JCClassDecl jCClassDecl = (JCTree.JCClassDecl) treeAlt2;
                JCTree.JCExpression jCExpression = jCVariableDecl.init;
                if (jCExpression instanceof JCTree.JCNewClass) {
                    JCTree.JCNewClass jCNewClass = (JCTree.JCNewClass) jCExpression;
                    if (jCNewClass.def != null) {
                        if ((jCClassDecl.sym.flags_field & 268435456) != 0) {
                            this.attr.attribClass(jCClassDecl.pos(), jCClassDecl.sym);
                        }
                        return jCNewClass.def.sym;
                    }
                }
            }
        }
        return null;
    }

    @Override // javax.lang.model.util.Elements
    public JavaFileObject getFileObjectOf(Element element) {
        Symbol symbol = (Symbol) element;
        int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[symbol.kind.ordinal()];
        if (i == 1) {
            Symbol.ClassSymbol classSymbol = ((Symbol.PackageSymbol) symbol).package_info;
            if (classSymbol == null) {
                return null;
            }
            return classSymbol.classfile;
        }
        if (i == 2) {
            Symbol.ClassSymbol classSymbol2 = ((Symbol.ModuleSymbol) symbol).module_info;
            if (classSymbol2 == null) {
                return null;
            }
            return classSymbol2.classfile;
        }
        if (i == 3) {
            return ((Symbol.ClassSymbol) symbol).classfile;
        }
        if (i != 4) {
            return symbol.enclClass().classfile;
        }
        return null;
    }

    @Override // javax.lang.model.util.Elements
    public Symbol.ModuleSymbol getModuleElement(CharSequence charSequence) {
        ensureEntered("getModuleElement");
        if (this.modules.getDefaultModule() == this.syms.noModule) {
            return null;
        }
        String string = charSequence.toString();
        return string.equals("") ? this.syms.unnamedModule : this.modules.getObservableModule(this.names.fromString(string));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.lang.model.util.Elements
    public ModuleElement getModuleOf(Element element) {
        Symbol symbol = (Symbol) cast(Symbol.class, element);
        if (this.modules.getDefaultModule() == this.syms.noModule) {
            return null;
        }
        Kinds.Kind kind = symbol.kind;
        Kinds.Kind kind2 = Kinds.Kind.MDL;
        if (kind == kind2) {
            return (ModuleElement) element;
        }
        Symbol symbol2 = symbol.owner;
        return symbol2.kind == kind2 ? (ModuleElement) symbol2 : symbol.packge().modle;
    }

    @Override // javax.lang.model.util.Elements
    public Name getName(CharSequence charSequence) {
        return this.names.fromString(charSequence.toString());
    }

    @Override // javax.lang.model.util.Elements
    public Elements.Origin getOrigin(ModuleElement moduleElement, ModuleElement.Directive directive) {
        int i = AnonymousClass1.$SwitchMap$javax$lang$model$element$ModuleElement$DirectiveKind[directive.getKind().ordinal()];
        if (i == 1) {
            Directive.RequiresDirective requiresDirective = (Directive.RequiresDirective) cast(Directive.RequiresDirective.class, directive);
            if (requiresDirective.flags.contains(Directive.RequiresFlag.MANDATED)) {
                return Elements.Origin.MANDATED;
            }
            return requiresDirective.flags.contains(Directive.RequiresFlag.SYNTHETIC) ? Elements.Origin.SYNTHETIC : Elements.Origin.EXPLICIT;
        }
        if (i == 2) {
            Directive.ExportsDirective exportsDirective = (Directive.ExportsDirective) cast(Directive.ExportsDirective.class, directive);
            if (exportsDirective.flags.contains(Directive.ExportsFlag.MANDATED)) {
                return Elements.Origin.MANDATED;
            }
            return exportsDirective.flags.contains(Directive.ExportsFlag.SYNTHETIC) ? Elements.Origin.SYNTHETIC : Elements.Origin.EXPLICIT;
        }
        if (i != 3) {
            return Elements.Origin.EXPLICIT;
        }
        Directive.OpensDirective opensDirective = (Directive.OpensDirective) cast(Directive.OpensDirective.class, directive);
        if (opensDirective.flags.contains(Directive.OpensFlag.MANDATED)) {
            return Elements.Origin.MANDATED;
        }
        return opensDirective.flags.contains(Directive.OpensFlag.SYNTHETIC) ? Elements.Origin.SYNTHETIC : Elements.Origin.EXPLICIT;
    }

    @Override // javax.lang.model.util.Elements
    public TypeElement getOutermostTypeElement(Element element) {
        return ((Symbol) cast(Symbol.class, element)).outermostClass();
    }

    @Override // javax.lang.model.util.Elements
    public Symbol.PackageSymbol getPackageElement(ModuleElement moduleElement, CharSequence charSequence) {
        moduleElement.getClass();
        return doGetPackageElement(moduleElement, charSequence);
    }

    @Override // javax.lang.model.util.Elements
    public PackageElement getPackageOf(Element element) {
        Symbol symbol = (Symbol) cast(Symbol.class, element);
        Kinds.Kind kind = symbol.kind;
        Kinds.Kind kind2 = Kinds.Kind.MDL;
        if (kind == kind2 || symbol.owner.kind == kind2) {
            return null;
        }
        return symbol.packge();
    }

    public JCTree getTree(Element element) {
        Pair<JCTree, JCTree.JCCompilationUnit> treeAndTopLevel = getTreeAndTopLevel(element);
        if (treeAndTopLevel != null) {
            return treeAndTopLevel.fst;
        }
        return null;
    }

    public Pair<JCTree, JCTree.JCCompilationUnit> getTreeAndTopLevel(Element element, AnnotationMirror annotationMirror, AnnotationValue annotationValue) {
        Pair<JCTree, JCTree.JCCompilationUnit> treeAndTopLevel;
        JCTree jCTreeMatchAnnoToTree;
        if (element == null || (treeAndTopLevel = getTreeAndTopLevel(element)) == null) {
            return null;
        }
        if (annotationMirror == null || (jCTreeMatchAnnoToTree = matchAnnoToTree(annotationMirror, element, treeAndTopLevel.fst)) == null) {
            return treeAndTopLevel;
        }
        if (annotationValue == null) {
            return new Pair<>(jCTreeMatchAnnoToTree, treeAndTopLevel.snd);
        }
        JCTree jCTreeMatchAttributeToTree = matchAttributeToTree((Attribute) cast(Attribute.class, annotationValue), (Attribute) cast(Attribute.class, annotationMirror), jCTreeMatchAnnoToTree);
        JCTree.JCCompilationUnit jCCompilationUnit = treeAndTopLevel.snd;
        return jCTreeMatchAttributeToTree == null ? new Pair<>(jCTreeMatchAnnoToTree, jCCompilationUnit) : new Pair<>(jCTreeMatchAttributeToTree, jCCompilationUnit);
    }

    @Override // javax.lang.model.util.Elements
    public Symbol.ClassSymbol getTypeElement(ModuleElement moduleElement, CharSequence charSequence) {
        moduleElement.getClass();
        return doGetTypeElement(moduleElement, charSequence);
    }

    @Override // javax.lang.model.util.Elements
    public boolean hides(Element element, Element element2) {
        Kinds.Kind kind;
        Symbol symbol = (Symbol) cast(Symbol.class, element);
        Symbol symbol2 = (Symbol) cast(Symbol.class, element2);
        if (symbol == symbol2 || (kind = symbol.kind) != symbol2.kind || symbol.name != symbol2.name || (kind == Kinds.Kind.MTH && !(symbol.isStatic() && this.types.isSubSignature(symbol.type, symbol2.type)))) {
            return false;
        }
        Symbol.ClassSymbol classSymbolEnclClass = symbol.owner.enclClass();
        Symbol.ClassSymbol classSymbolEnclClass2 = symbol2.owner.enclClass();
        if (classSymbolEnclClass != null && classSymbolEnclClass2 != null && classSymbolEnclClass.isSubClass(classSymbolEnclClass2, this.types)) {
            return symbol2.isAccessibleIn(classSymbolEnclClass, this.types);
        }
        return false;
    }

    @Override // javax.lang.model.util.Elements
    public boolean isAutomaticModule(ModuleElement moduleElement) {
        return (((Symbol.ModuleSymbol) moduleElement).flags() & 4503599627370496L) != 0;
    }

    @Override // javax.lang.model.util.Elements
    public boolean isCanonicalConstructor(ExecutableElement executableElement) {
        return (((Symbol.MethodSymbol) executableElement).flags() & Flags.RECORD) != 0;
    }

    @Override // javax.lang.model.util.Elements
    public boolean isCompactConstructor(ExecutableElement executableElement) {
        return (((Symbol.MethodSymbol) executableElement).flags() & 2251799813685248L) != 0;
    }

    @Override // javax.lang.model.util.Elements
    public boolean isDeprecated(Element element) {
        Symbol symbol = (Symbol) cast(Symbol.class, element);
        symbol.apiComplete();
        return symbol.isDeprecated();
    }

    @Override // javax.lang.model.util.Elements
    public boolean isFunctionalInterface(TypeElement typeElement) {
        if (typeElement.getKind() != ElementKind.INTERFACE) {
            return false;
        }
        return this.types.isFunctionalInterface((Symbol.TypeSymbol) cast(Symbol.TypeSymbol.class, typeElement));
    }

    public void newRound() {
        this.resultCache.clear();
    }

    @Override // javax.lang.model.util.Elements
    public boolean overrides(ExecutableElement executableElement, ExecutableElement executableElement2, TypeElement typeElement) {
        Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) cast(Symbol.MethodSymbol.class, executableElement);
        Symbol.MethodSymbol methodSymbol2 = (Symbol.MethodSymbol) cast(Symbol.MethodSymbol.class, executableElement2);
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) cast(Symbol.ClassSymbol.class, typeElement);
        return methodSymbol.name == methodSymbol2.name && methodSymbol != methodSymbol2 && !methodSymbol.isStatic() && methodSymbol2.isMemberOf(classSymbol, this.types) && methodSymbol.overrides(methodSymbol2, classSymbol, this.types, false);
    }

    @Override // javax.lang.model.util.Elements
    public void printElements(Writer writer, Element... elementArr) {
        for (Element element : elementArr) {
            ((PrintingProcessor.PrintingElementVisitor) new PrintingProcessor.PrintingElementVisitor(writer, this).visit(element)).flush();
        }
    }

    @Override // javax.lang.model.util.Elements
    public Symbol.PackageSymbol getPackageElement(CharSequence charSequence) {
        return doGetPackageElement(null, charSequence);
    }

    @Override // javax.lang.model.util.Elements
    public Symbol.ClassSymbol getTypeElement(CharSequence charSequence) {
        return doGetTypeElement(null, charSequence);
    }

    private JCTree matchAnnoToTree(AnnotationMirror annotationMirror, Element element, JCTree jCTree) {
        Symbol symbol = (Symbol) cast(Symbol.class, element);
        C1Vis c1Vis = new C1Vis();
        jCTree.accept(c1Vis);
        if (c1Vis.result == null) {
            return null;
        }
        return matchAnnoToTree((Attribute.Compound) cast(Attribute.Compound.class, annotationMirror), symbol.getAnnotationMirrors(), c1Vis.result);
    }

    private Pair<JCTree, JCTree.JCCompilationUnit> getTreeAndTopLevel(Element element) {
        JCTree jCTreeDeclarationFor;
        JCTree.JCCompilationUnit jCCompilationUnit;
        Symbol.ClassSymbol classSymbol;
        Symbol symbol = (Symbol) cast(Symbol.class, element);
        if (symbol.kind == Kinds.Kind.PCK && (classSymbol = ((Symbol.PackageSymbol) symbol).package_info) != null) {
            classSymbol.complete();
        }
        Env<AttrContext> enterEnv = getEnterEnv(symbol);
        if (enterEnv == null || (jCTreeDeclarationFor = TreeInfo.declarationFor(symbol, enterEnv.tree)) == null || (jCCompilationUnit = enterEnv.toplevel) == null) {
            return null;
        }
        return new Pair<>(jCTreeDeclarationFor, jCCompilationUnit);
    }

    @Override // javax.lang.model.util.Elements
    public Elements.Origin getOrigin(AnnotatedConstruct annotatedConstruct, AnnotationMirror annotationMirror) {
        if (((Attribute.Compound) cast(Attribute.Compound.class, annotationMirror)).isSynthesized()) {
            return Elements.Origin.MANDATED;
        }
        return Elements.Origin.EXPLICIT;
    }

    @Override // javax.lang.model.util.Elements
    public Elements.Origin getOrigin(Element element) {
        Symbol symbol = (Symbol) cast(Symbol.class, element);
        if ((symbol.flags() & Flags.GENERATEDCONSTR) != 0) {
            return Elements.Origin.MANDATED;
        }
        if ((symbol.flags() & 32768) != 0) {
            return Elements.Origin.MANDATED;
        }
        return Elements.Origin.EXPLICIT;
    }
}

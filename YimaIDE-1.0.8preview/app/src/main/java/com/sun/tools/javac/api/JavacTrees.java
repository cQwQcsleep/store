package com.sun.tools.javac.api;

import com.intellij.psi.PsiKeyword;
import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;
import com.sun.source.doctree.EntityTree;
import com.sun.source.tree.CatchTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.DocSourcePositions;
import com.sun.source.util.DocTreePath;
import com.sun.source.util.DocTrees;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TreePath;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Attr;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Check;
import com.sun.tools.javac.comp.Enter;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.comp.MemberEnter;
import com.sun.tools.javac.comp.Modules;
import com.sun.tools.javac.comp.Resolve;
import com.sun.tools.javac.file.BaseFileManager;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.parser.DocCommentParser;
import com.sun.tools.javac.parser.ParserFactory;
import com.sun.tools.javac.parser.Tokens;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.DCTree;
import com.sun.tools.javac.tree.DocCommentTable;
import com.sun.tools.javac.tree.DocTreeMaker;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeCopier;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Abort;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.DiagnosticSource;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.Position;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.ForwardingFileObject;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import nbjavac.ServiceLoaderWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacTrees extends DocTrees {
    private final Attr attr;
    private final Check chk;
    private DocCommentTreeTransformer docCommentTreeTransformer;
    private final DocTreeMaker docTreeMaker;
    private final JavacElements elements;
    private final Enter enter;
    private final JavaFileManager fileManager;
    private final JavacTaskImpl javacTaskImpl;
    private final Log log;
    private final MemberEnter memberEnter;
    private final Modules modules;
    private final Names names;
    private final ParserFactory parserFactory;
    private final Resolve resolve;
    private final Symtab syms;
    private final TreeMaker treeMaker;
    private final Types types;
    private final Map<Type, Type> extraType2OriginalMap = new WeakHashMap();
    private BreakIterator breakIterator = null;

    /* JADX INFO: renamed from: com.sun.tools.javac.api.JavacTrees$7, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$source$tree$Tree$Kind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle;
        static final /* synthetic */ int[] $SwitchMap$javax$tools$Diagnostic$Kind;
        static final /* synthetic */ int[] $SwitchMap$javax$tools$JavaFileObject$Kind;

        static {
            int[] iArr = new int[Diagnostic.Kind.values().length];
            $SwitchMap$javax$tools$Diagnostic$Kind = iArr;
            try {
                iArr[Diagnostic.Kind.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$tools$Diagnostic$Kind[Diagnostic.Kind.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$tools$Diagnostic$Kind[Diagnostic.Kind.MANDATORY_WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[JavaFileObject.Kind.values().length];
            $SwitchMap$javax$tools$JavaFileObject$Kind = iArr2;
            try {
                iArr2[JavaFileObject.Kind.HTML.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$tools$JavaFileObject$Kind[JavaFileObject.Kind.OTHER.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[Tokens.Comment.CommentStyle.values().length];
            $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle = iArr3;
            try {
                iArr3[Tokens.Comment.CommentStyle.JAVADOC_BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle[Tokens.Comment.CommentStyle.JAVADOC_LINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr4 = new int[Tree.Kind.values().length];
            $SwitchMap$com$sun$source$tree$Tree$Kind = iArr4;
            try {
                iArr4[Tree.Kind.PACKAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.MODULE.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.COMPILATION_UNIT.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.ANNOTATION_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.CLASS.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.ENUM.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.INTERFACE.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.RECORD.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.METHOD.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.VARIABLE.ordinal()] = 10;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.BLOCK.ordinal()] = 11;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    public static class Copier extends TreeCopier<JCTree> {
        private Map<JCTree.JCClassDecl, JCTree.JCClassDecl> copiedClasses;
        JCTree leafCopy;

        public Copier(TreeMaker treeMaker) {
            super(treeMaker);
            this.leafCopy = null;
            this.copiedClasses = new HashMap();
        }

        @Override // com.sun.tools.javac.tree.TreeCopier
        public <T extends JCTree> T copy(T t, JCTree jCTree) {
            T t2 = (T) super.copy((JCTree) t, jCTree);
            if (t == jCTree) {
                this.leafCopy = t2;
            }
            return t2;
        }

        @Override // com.sun.tools.javac.tree.TreeCopier, com.sun.source.tree.TreeVisitor
        public JCTree visitClass(ClassTree classTree, JCTree jCTree) {
            JCTree jCTreeVisitClass = super.visitClass(classTree, jCTree);
            this.copiedClasses.put((JCTree.JCClassDecl) jCTreeVisitClass, (JCTree.JCClassDecl) classTree);
            return jCTreeVisitClass;
        }
    }

    public interface DocCommentTreeTransformer {
        public static final String STANDARD = "standard";

        String name();

        DocCommentTree transform(DocTrees docTrees, DocCommentTree docCommentTree);
    }

    public static class DocFileObject extends ForwardingFileObject<FileObject> implements JavaFileObject {
        public DocFileObject(FileObject fileObject) {
            super(fileObject);
        }

        @Override // javax.tools.JavaFileObject
        public Modifier getAccessLevel() {
            return null;
        }

        @Override // javax.tools.JavaFileObject
        public JavaFileObject.Kind getKind() {
            return BaseFileManager.getKind(this.fileObject.getName());
        }

        @Override // javax.tools.JavaFileObject
        public NestingKind getNestingKind() {
            return null;
        }

        @Override // javax.tools.JavaFileObject
        public boolean isNameCompatible(String str, JavaFileObject.Kind kind) {
            return false;
        }
    }

    public static class IdentityTransformer implements DocCommentTreeTransformer {
        @Override // com.sun.tools.javac.api.JavacTrees.DocCommentTreeTransformer
        public String name() {
            return "identity";
        }

        @Override // com.sun.tools.javac.api.JavacTrees.DocCommentTreeTransformer
        public DocCommentTree transform(DocTrees docTrees, DocCommentTree docCommentTree) {
            return docCommentTree;
        }
    }

    public JavacTrees(Context context) {
        context.put((Class<JavacTrees>) JavacTrees.class, this);
        this.modules = Modules.instance(context);
        this.attr = Attr.instance(context);
        this.chk = Check.instance(context);
        this.enter = Enter.instance(context);
        this.elements = JavacElements.instance(context);
        this.log = Log.instance(context);
        this.resolve = Resolve.instance(context);
        this.treeMaker = TreeMaker.instance(context);
        this.memberEnter = MemberEnter.instance(context);
        this.names = Names.instance(context);
        this.types = Types.instance(context);
        this.docTreeMaker = DocTreeMaker.instance(context);
        this.parserFactory = ParserFactory.instance(context);
        this.syms = Symtab.instance(context);
        this.fileManager = (JavaFileManager) context.get(JavaFileManager.class);
        JavacTask javacTask = (JavacTask) context.get(JavacTask.class);
        this.javacTaskImpl = javacTask instanceof JavacTaskImpl ? (JavacTaskImpl) javacTask : null;
    }

    public static JavaFileObject asDocFileObject(FileObject fileObject) {
        if (fileObject instanceof JavaFileObject) {
            JavaFileObject javaFileObject = (JavaFileObject) fileObject;
            int i = AnonymousClass7.$SwitchMap$javax$tools$JavaFileObject$Kind[javaFileObject.getKind().ordinal()];
            if (i == 1 || (i == 2 && isMarkdownFile(javaFileObject))) {
                return javaFileObject;
            }
        } else if (isHtmlFile(fileObject) || isMarkdownFile(fileObject)) {
            return new DocFileObject(fileObject);
        }
        z01.a("Not a documentation file: ", fileObject.getName());
        return null;
    }

    private Env<AttrContext> attribExprToTree(JCTree.JCExpression jCExpression, Env<AttrContext> env, JCTree jCTree, Map<JCTree.JCClassDecl, JCTree.JCClassDecl> map) {
        Env<AttrContext> envAttribExprToTree = this.attr.attribExprToTree(jCExpression, env, jCTree);
        fixLocalClassNames(map, env);
        return envAttribExprToTree;
    }

    private Env<AttrContext> attribStatToTree(JCTree jCTree, Env<AttrContext> env, JCTree jCTree2, Map<JCTree.JCClassDecl, JCTree.JCClassDecl> map) {
        Env<AttrContext> envAttribStatToTree = this.attr.attribStatToTree(jCTree, env, jCTree2);
        fixLocalClassNames(map, env);
        return envAttribStatToTree;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x017d  */
    /* JADX WARN: Multi-variable type inference failed */
    private Symbol attributeDocReference(TreePath treePath, DCTree.DCReference dCReference) throws Throwable {
        JavacTrees javacTrees;
        Throwable th;
        Log log;
        Symbol.ModuleSymbol observableModule;
        Symbol observableModule2;
        Name name;
        Log log2;
        List<Type> list;
        Env<AttrContext> attrContext = getAttrContext(treePath);
        if (attrContext == null) {
            return null;
        }
        if (dCReference.moduleName != null && dCReference.qualifierExpression == null && dCReference.memberName != null) {
            return null;
        }
        Log log3 = this.log;
        Objects.requireNonNull(log3);
        Log.DeferredDiagnosticHandler deferredDiagnosticHandler = new Log.DeferredDiagnosticHandler(log3);
        JavaFileObject javaFileObjectUseSource = this.log.useSource(attrContext.toplevel.sourcefile);
        try {
            try {
                JCTree.JCExpression jCExpression = dCReference.moduleName;
                Modules modules = this.modules;
                if (jCExpression != null) {
                    try {
                        observableModule = modules.modulesInitialized() ? this.modules.getObservableModule(this.names.fromString(dCReference.moduleName.toString())) : null;
                        if (observableModule == null) {
                            this.log.useSource(javaFileObjectUseSource);
                            log = this.log;
                        } else if (dCReference.qualifierExpression == null) {
                            this.log.useSource(javaFileObjectUseSource);
                            this.log.popDiagnosticHandler(deferredDiagnosticHandler);
                            return observableModule;
                        }
                        log.popDiagnosticHandler(deferredDiagnosticHandler);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        javacTrees = this;
                        javacTrees.log.useSource(javaFileObjectUseSource);
                        javacTrees.log.popDiagnosticHandler(deferredDiagnosticHandler);
                        throw th;
                    }
                }
                observableModule = modules.getDefaultModule();
                JCTree jCTree = dCReference.qualifierExpression;
                if (jCTree == null) {
                    int i = AnonymousClass7.$SwitchMap$com$sun$source$tree$Tree$Kind[treePath.getLeaf().getKind().ordinal()];
                    if (i == 1) {
                        observableModule2 = attrContext.toplevel.packge;
                    } else if (i == 2) {
                        observableModule2 = attrContext.toplevel.modle;
                    } else if (i != 3) {
                        observableModule2 = attrContext.enclClass.sym;
                    } else {
                        observableModule2 = treePath.getCompilationUnit().getSourceFile().isNameCompatible(PsiKeyword.PACKAGE, JavaFileObject.Kind.HTML) ? attrContext.toplevel.packge : null;
                    }
                    name = (Name) dCReference.memberName;
                } else {
                    Type typeAttribType = dCReference.moduleName == null ? this.attr.attribType(jCTree, attrContext) : null;
                    if (typeAttribType == null || typeAttribType.isErroneous()) {
                        JCTree.JCCompilationUnit jCCompilationUnitTopLevel = this.treeMaker.TopLevel(List.nil());
                        jCCompilationUnitTopLevel.modle = observableModule;
                        jCCompilationUnitTopLevel.packge = observableModule.unnamedPackage;
                        Symbol symbolAttribIdent = this.attr.attribIdent(dCReference.qualifierExpression, jCCompilationUnitTopLevel);
                        if (symbolAttribIdent != null) {
                            symbolAttribIdent.complete();
                            Kinds.Kind kind = symbolAttribIdent.kind;
                            Kinds.Kind kind2 = Kinds.Kind.PCK;
                            if ((kind == kind2 || kind == Kinds.Kind.TYP) && symbolAttribIdent.exists()) {
                                Symbol.TypeSymbol typeSymbol = (Symbol.TypeSymbol) symbolAttribIdent;
                                Name name2 = (Name) dCReference.memberName;
                                if (symbolAttribIdent.kind != kind2 || name2 == null) {
                                    observableModule2 = typeSymbol;
                                    name = name2;
                                }
                                log.popDiagnosticHandler(deferredDiagnosticHandler);
                                return null;
                            }
                            if (!this.modules.modulesInitialized() || dCReference.moduleName != null || dCReference.memberName != null || (observableModule2 = this.modules.getObservableModule(this.names.fromString(dCReference.signature))) == null) {
                                if (dCReference.qualifierExpression.hasTag(JCTree.Tag.IDENT) && dCReference.moduleName == null && dCReference.memberName == null) {
                                    observableModule2 = attrContext.enclClass.sym;
                                    name = ((JCTree.JCIdent) dCReference.qualifierExpression).name;
                                } else {
                                    javacTrees = this;
                                }
                                javacTrees.log.useSource(javaFileObjectUseSource);
                                log = javacTrees.log;
                                log.popDiagnosticHandler(deferredDiagnosticHandler);
                                return null;
                            }
                            this.log.useSource(javaFileObjectUseSource);
                            log2 = this.log;
                            log2.popDiagnosticHandler(deferredDiagnosticHandler);
                            return observableModule2;
                        }
                        this.log.useSource(javaFileObjectUseSource);
                        log = this.log;
                        log.popDiagnosticHandler(deferredDiagnosticHandler);
                        return null;
                    }
                    while (typeAttribType instanceof Type.ArrayType) {
                        typeAttribType = ((Type.ArrayType) typeAttribType).elemtype;
                    }
                    observableModule2 = typeAttribType.tsym;
                    name = (Name) dCReference.memberName;
                }
                Name name3 = name;
                if (name3 != null) {
                    if (observableModule2 == null || observableModule2.getKind() == ElementKind.PACKAGE || observableModule2.getKind() == ElementKind.MODULE) {
                        javacTrees = this;
                    } else {
                        if (observableModule2.type.isPrimitive()) {
                            this.log.useSource(javaFileObjectUseSource);
                            log = this.log;
                            log.popDiagnosticHandler(deferredDiagnosticHandler);
                            return null;
                        }
                        if (dCReference.paramTypes == null) {
                            list = null;
                        } else {
                            ListBuffer listBuffer = new ListBuffer();
                            for (List list2 = (List) dCReference.paramTypes; list2.nonEmpty(); list2 = list2.tail) {
                                listBuffer.add(this.attr.attribType((JCTree) list2.head, attrContext));
                            }
                            list = listBuffer.toList();
                        }
                        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) this.types.skipTypeVars(observableModule2.type, false).tsym;
                        boolean z = dCReference.qualifierExpression != null;
                        if (name3 == classSymbol.name) {
                            observableModule2 = findConstructor(classSymbol, list, true);
                            javacTrees = this;
                        } else {
                            javacTrees = this;
                            try {
                                observableModule2 = javacTrees.findMethod(classSymbol, name3, list, true, z);
                            } catch (Abort unused) {
                            } catch (Throwable th3) {
                                th = th3;
                                th = th;
                                javacTrees.log.useSource(javaFileObjectUseSource);
                                javacTrees.log.popDiagnosticHandler(deferredDiagnosticHandler);
                                throw th;
                            }
                        }
                        if (observableModule2 == null) {
                            observableModule2 = name3 == classSymbol.name ? javacTrees.findConstructor(classSymbol, list, false) : javacTrees.findMethod(classSymbol, name3, list, false, z);
                        }
                        if (list == null) {
                            Symbol.VarSymbol varSymbolFindField = dCReference.paramTypes != null ? null : javacTrees.findField(classSymbol, name3, z);
                            if (varSymbolFindField != null && (observableModule2 == null || javacTrees.types.isSubtypeUnchecked(varSymbolFindField.enclClass().asType(), observableModule2.enclClass().asType()))) {
                                javacTrees.log.useSource(javaFileObjectUseSource);
                                javacTrees.log.popDiagnosticHandler(deferredDiagnosticHandler);
                                return varSymbolFindField;
                            }
                        }
                        javacTrees.log.useSource(javaFileObjectUseSource);
                        log2 = javacTrees.log;
                        javacTrees = this;
                    }
                    javacTrees.log.useSource(javaFileObjectUseSource);
                    log = javacTrees.log;
                    log.popDiagnosticHandler(deferredDiagnosticHandler);
                    return null;
                }
                this.log.useSource(javaFileObjectUseSource);
                log2 = this.log;
                log2.popDiagnosticHandler(deferredDiagnosticHandler);
                return observableModule2;
            } catch (Abort unused2) {
            }
        } catch (Throwable th4) {
            th = th4;
            javacTrees = this;
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0054  */
    /* JADX WARN: Code duplicated, block: B:31:0x0068 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:32:? A[LOOP:0: B:24:0x004e->B:32:?, LOOP_END, SYNTHETIC] */
    private Symbol attributeParamIdentifier(TreePath treePath, DCTree.DCParam dCParam) {
        List<? extends Symbol.RecordComponent> typeParameters;
        Symbol element = getElement(treePath);
        if (element == null) {
            return null;
        }
        ElementKind kind = element.getKind();
        List<? extends Symbol.RecordComponent> listNil = List.nil();
        if (kind != ElementKind.METHOD && kind != ElementKind.CONSTRUCTOR) {
            if (kind.isClass() || kind.isInterface()) {
                Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) element;
                typeParameters = dCParam.isTypeParameter() ? classSymbol.getTypeParameters() : classSymbol.getRecordComponents();
            }
            for (Symbol symbol : listNil) {
                if (symbol.getSimpleName() == dCParam.getName().getName()) {
                    return symbol;
                }
            }
            return null;
        }
        Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) element;
        typeParameters = dCParam.isTypeParameter() ? methodSymbol.getTypeParameters() : methodSymbol.getParameters();
        listNil = typeParameters;
        while (r3.hasNext()) {
            if (symbol.getSimpleName() == dCParam.getName().getName()) {
                return symbol;
            }
        }
        return null;
    }

    public static /* synthetic */ void b(JavacTrees javacTrees, Symbol.ClassSymbol classSymbol) {
        javacTrees.chk.clearLocalClassNameIndexes(classSymbol);
        javacTrees.chk.removeCompiled(classSymbol);
    }

    public static /* synthetic */ DocCommentTreeTransformer c() {
        return new IdentityTransformer();
    }

    public static /* synthetic */ Type d(JavacTrees javacTrees, final Type.ClassType classType, Type type) {
        javacTrees.getClass();
        return new Type.ClassType(javacTrees, classType.getEnclosingType(), classType.typarams_field, classType.tsym, classType.getMetadata()) { // from class: com.sun.tools.javac.api.JavacTrees.4
            final /* synthetic */ JavacTrees this$0;

            {
                this.this$0 = javacTrees;
            }

            @Override // com.sun.tools.javac.code.Type
            public Type baseType() {
                return classType;
            }

            @Override // com.sun.tools.javac.code.Type.ClassType, com.sun.tools.javac.code.Type, javax.lang.model.type.TypeMirror
            public TypeKind getKind() {
                return TypeKind.DECLARED;
            }
        };
    }

    private Symbol.VarSymbol findField(Symbol.ClassSymbol classSymbol, Name name, boolean z) {
        return searchField(classSymbol, name, z, new HashSet());
    }

    private Symbol.MethodSymbol findMethod(Symbol.ClassSymbol classSymbol, Name name, List<Type> list, boolean z, boolean z2) {
        return searchMethod(classSymbol, name, list, z, z2, new HashSet());
    }

    private void fixLocalClassNames(Map<JCTree.JCClassDecl, JCTree.JCClassDecl> map, Env<AttrContext> env) {
        Name name;
        Map<JCTree.JCClassDecl, Name> mapPrepareFlatnameForClass = null;
        for (Map.Entry<JCTree.JCClassDecl, JCTree.JCClassDecl> entry : map.entrySet()) {
            if (entry.getKey().sym != null) {
                if (entry.getValue().sym != null) {
                    name = entry.getValue().sym.flatname;
                } else {
                    if (mapPrepareFlatnameForClass == null) {
                        mapPrepareFlatnameForClass = prepareFlatnameForClass(env);
                    }
                    name = mapPrepareFlatnameForClass.get(entry.getValue());
                }
                if (name != null) {
                    entry.getKey().sym.flatname = name;
                }
            }
        }
    }

    private Env<AttrContext> getAttrContext(TreePath treePath) {
        if (!(treePath.getLeaf() instanceof JCTree)) {
            j2d.a();
            return null;
        }
        JavacTaskImpl javacTaskImpl = this.javacTaskImpl;
        if (javacTaskImpl != null) {
            javacTaskImpl.enter(null);
        }
        Copier copierCreateCopier = createCopier(this.treeMaker.forToplevel((JCTree.JCCompilationUnit) treePath.getCompilationUnit()));
        List listNil = List.nil();
        for (TreePath parentPath = treePath; parentPath != null; parentPath = parentPath.getParentPath()) {
            listNil = listNil.prepend(parentPath.getLeaf());
        }
        JCTree.JCVariableDecl jCVariableDecl = null;
        Env<AttrContext> topLevelEnv = null;
        JCTree.JCMethodDecl jCMethodDecl = null;
        while (listNil.nonEmpty()) {
            Tree tree = (Tree) listNil.head;
            switch (AnonymousClass7.$SwitchMap$com$sun$source$tree$Tree$Kind[tree.getKind().ordinal()]) {
                case 3:
                    topLevelEnv = this.enter.getTopLevelEnv((JCTree.JCCompilationUnit) tree);
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    topLevelEnv = this.enter.getClassEnv(((JCTree.JCClassDecl) tree).sym);
                    if (topLevelEnv == null) {
                        return null;
                    }
                    break;
                    break;
                case 9:
                    jCMethodDecl = (JCTree.JCMethodDecl) tree;
                    topLevelEnv = this.memberEnter.getMethodEnv(jCMethodDecl, topLevelEnv);
                    break;
                case 10:
                    jCVariableDecl = (JCTree.JCVariableDecl) tree;
                    break;
                case 11:
                    if (jCMethodDecl == null) {
                        return attribStatToTree((JCTree.JCBlock) copierCreateCopier.copy((JCTree.JCBlock) tree, (JCTree) treePath.getLeaf()), topLevelEnv, copierCreateCopier.leafCopy, copierCreateCopier.copiedClasses);
                    }
                    try {
                        Assert.check(jCMethodDecl.body == tree);
                        JCTree.JCBlock jCBlock = (JCTree.JCBlock) copierCreateCopier.copy((JCTree.JCBlock) tree, (JCTree) treePath.getLeaf());
                        jCMethodDecl.body = jCBlock;
                        return attribStatToTree(jCBlock, topLevelEnv, copierCreateCopier.leafCopy, copierCreateCopier.copiedClasses);
                    } finally {
                        jCMethodDecl.body = (JCTree.JCBlock) tree;
                    }
                default:
                    if (jCVariableDecl != null && jCVariableDecl.getInitializer() == tree) {
                        return attribExprToTree((JCTree.JCExpression) copierCreateCopier.copy((JCTree.JCExpression) tree, (JCTree) treePath.getLeaf()), this.memberEnter.getInitEnv(jCVariableDecl, topLevelEnv), copierCreateCopier.leafCopy, copierCreateCopier.copiedClasses);
                    }
                    break;
                    break;
            }
            listNil = listNil.tail;
        }
        return jCVariableDecl != null ? this.memberEnter.getInitEnv(jCVariableDecl, topLevelEnv) : topLevelEnv;
    }

    private boolean hasParameterTypes(Symbol.MethodSymbol methodSymbol, List<Type> list, boolean z) {
        if (list == null) {
            return true;
        }
        if (methodSymbol.params().size() != list.size()) {
            return false;
        }
        List<Type> listMo71getParameterTypes = methodSymbol.asType().mo71getParameterTypes();
        if (!z && !Type.isErroneous(list) && this.types.isSubtypes(list, listMo71getParameterTypes)) {
            return true;
        }
        return this.types.isSameTypes(list, this.types.erasureRecursive(listMo71getParameterTypes));
    }

    public static JavacTrees instance(JavaCompiler.CompilationTask compilationTask) {
        if (compilationTask instanceof BasicJavacTask) {
            return instance(((BasicJavacTask) compilationTask).getContext());
        }
        j2d.a();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isHtmlFile(FileObject fileObject) {
        return fileObject.getName().endsWith(".html");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isMarkdownFile(FileObject fileObject) {
        return fileObject.getName().endsWith(".md");
    }

    private TreePath makeTreePath(Symbol.PackageSymbol packageSymbol, final JavaFileObject javaFileObject, final DocCommentTree docCommentTree) {
        JCTree.JCCompilationUnit jCCompilationUnit = new JCTree.JCCompilationUnit(this, List.nil()) { // from class: com.sun.tools.javac.api.JavacTrees.5
            final /* synthetic */ JavacTrees this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.tree.JCTree.JCCompilationUnit, com.sun.source.tree.CompilationUnitTree
            public Position.LineMap getLineMap() {
                try {
                    String string = javaFileObject.getCharContent(true).toString();
                    return Position.makeLineMap(string.toCharArray(), string.length(), true);
                } catch (IOException unused) {
                    return null;
                }
            }

            public int getPos() {
                return 0;
            }

            public JavaFileObject getSourcefile() {
                return javaFileObject;
            }
        };
        jCCompilationUnit.docComments = new DocCommentTable(this) { // from class: com.sun.tools.javac.api.JavacTrees.6
            final /* synthetic */ JavacTrees this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.tree.DocCommentTable
            public Tokens.Comment getComment(JCTree jCTree) {
                throw new UnsupportedOperationException();
            }

            @Override // com.sun.tools.javac.tree.DocCommentTable
            public Elements.DocCommentKind getCommentKind(JCTree jCTree) {
                throw new UnsupportedOperationException();
            }

            @Override // com.sun.tools.javac.tree.DocCommentTable
            public String getCommentText(JCTree jCTree) {
                throw new UnsupportedOperationException();
            }

            @Override // com.sun.tools.javac.tree.DocCommentTable
            public boolean hasComment(JCTree jCTree) {
                return false;
            }

            @Override // com.sun.tools.javac.tree.DocCommentTable
            public void putComment(JCTree jCTree, Tokens.Comment comment) {
                throw new UnsupportedOperationException();
            }

            @Override // com.sun.tools.javac.tree.DocCommentTable
            public DCTree.DCDocComment getCommentTree(JCTree jCTree) {
                return (DCTree.DCDocComment) docCommentTree;
            }
        };
        jCCompilationUnit.lineMap = jCCompilationUnit.getLineMap();
        jCCompilationUnit.modle = packageSymbol.modle;
        jCCompilationUnit.sourcefile = javaFileObject;
        jCCompilationUnit.namedImportScope = new Scope.NamedImportScope(packageSymbol);
        jCCompilationUnit.packge = packageSymbol;
        jCCompilationUnit.starImportScope = new Scope.StarImportScope(packageSymbol);
        jCCompilationUnit.moduleImportScope = new Scope.StarImportScope(packageSymbol);
        jCCompilationUnit.toplevelScope = Scope.WriteableScope.create(packageSymbol);
        return new TreePath(jCCompilationUnit);
    }

    private Map<JCTree.JCClassDecl, Name> prepareFlatnameForClass(Env<AttrContext> env) {
        final HashMap map = new HashMap();
        Symbol.ClassSymbol classSymbol = env.enclClass.sym;
        if (classSymbol != null && (classSymbol.flags_field & 268435456) != 0) {
            final ListBuffer listBuffer = new ListBuffer();
            new TreeScanner(this) { // from class: com.sun.tools.javac.api.JavacTrees.2
                boolean localContext;
                Symbol owner;
                final /* synthetic */ JavacTrees this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitBlock(JCTree.JCBlock jCBlock) {
                    Symbol symbol = this.owner;
                    try {
                        this.owner = new Symbol.MethodSymbol(0L, this.this$0.names.empty, Type.noType, this.owner);
                        super.visitBlock(jCBlock);
                    } finally {
                        this.owner = symbol;
                    }
                }

                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
                    Symbol symbol = this.owner;
                    try {
                        Symbol.ClassSymbol classSymbolDefineClass = jCClassDecl.sym;
                        if (classSymbolDefineClass == null) {
                            classSymbolDefineClass = this.this$0.syms.defineClass(jCClassDecl.name, this.owner);
                            if (this.owner.kind != Kinds.Kind.TYP) {
                                classSymbolDefineClass.flatname = this.this$0.chk.localClassName(classSymbolDefineClass);
                                this.this$0.chk.putCompiled(classSymbolDefineClass);
                                listBuffer.add(classSymbolDefineClass);
                            }
                            map.put(jCClassDecl, classSymbolDefineClass.flatname);
                        }
                        this.owner = classSymbolDefineClass;
                        super.visitClassDef(jCClassDecl);
                    } finally {
                        this.owner = symbol;
                    }
                }

                @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
                public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
                    Symbol symbol = this.owner;
                    try {
                        this.owner = new Symbol.MethodSymbol(0L, this.this$0.names.empty, Type.noType, this.owner);
                        super.visitVarDef(jCVariableDecl);
                    } finally {
                        this.owner = symbol;
                    }
                }
            }.scan(env.enclClass);
            listBuffer.forEach(new Consumer() { // from class: rn7
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    JavacTrees.b(this.b, (Symbol.ClassSymbol) obj);
                }
            });
        }
        return map;
    }

    private void printMessage(Diagnostic.Kind kind, CharSequence charSequence, JCDiagnostic.DiagnosticPosition diagnosticPosition, CompilationUnitTree compilationUnitTree) {
        JavaFileObject javaFileObjectUseSource = null;
        JavaFileObject sourceFile = compilationUnitTree == null ? null : compilationUnitTree.getSourceFile();
        if (sourceFile == null) {
            diagnosticPosition = null;
        } else {
            javaFileObjectUseSource = this.log.useSource(sourceFile);
        }
        try {
            int i = AnonymousClass7.$SwitchMap$javax$tools$Diagnostic$Kind[kind.ordinal()];
            if (i == 1) {
                this.log.error(JCDiagnostic.DiagnosticFlag.API, diagnosticPosition, CompilerProperties.Errors.ProcMessager(charSequence.toString()));
            } else if (i != 2) {
                Log log = this.log;
                if (i != 3) {
                    log.note(diagnosticPosition, CompilerProperties.Notes.ProcMessager(charSequence.toString()));
                } else {
                    log.warning(JCDiagnostic.DiagnosticFlag.MANDATORY, diagnosticPosition, CompilerProperties.Warnings.ProcMessager(charSequence.toString()));
                }
            } else {
                this.log.warning(diagnosticPosition, CompilerProperties.Warnings.ProcMessager(charSequence.toString()));
            }
        } finally {
            if (javaFileObjectUseSource != null) {
                this.log.useSource(javaFileObjectUseSource);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Symbol.VarSymbol searchField(Symbol.ClassSymbol classSymbol, Name name, boolean z, Set<Symbol.ClassSymbol> set) {
        Symbol.VarSymbol varSymbolSearchField;
        Symbol.VarSymbol varSymbolSearchField2;
        Symbol.ClassSymbol classSymbolEnclClass;
        Symbol.VarSymbol varSymbolSearchField3;
        if (set.contains(classSymbol)) {
            return null;
        }
        set.add(classSymbol);
        for (Symbol symbol : classSymbol.members().getSymbolsByName(name)) {
            if (symbol.kind == Kinds.Kind.VAR) {
                return (Symbol.VarSymbol) symbol;
            }
        }
        if (!z && (classSymbolEnclClass = classSymbol.owner.enclClass()) != null && (varSymbolSearchField3 = searchField(classSymbolEnclClass, name, z, set)) != null) {
            return varSymbolSearchField3;
        }
        Symbol.TypeSymbol typeSymbol = classSymbol.getSuperclass().tsym;
        if (typeSymbol != null && (varSymbolSearchField2 = searchField((Symbol.ClassSymbol) typeSymbol, name, z, set)) != null) {
            return varSymbolSearchField2;
        }
        for (List interfaces = classSymbol.getInterfaces(); interfaces.nonEmpty(); interfaces = interfaces.tail) {
            Type type = (Type) interfaces.head;
            if (!type.isErroneous() && (varSymbolSearchField = searchField((Symbol.ClassSymbol) type.tsym, name, z, set)) != null) {
                return varSymbolSearchField;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Symbol.MethodSymbol searchMethod(Symbol.ClassSymbol classSymbol, Name name, List<Type> list, boolean z, boolean z2, Set<Symbol.ClassSymbol> set) {
        JavacTrees javacTrees;
        Name name2;
        List<Type> list2;
        boolean z3;
        boolean z4;
        Set<Symbol.ClassSymbol> set2;
        Symbol.ClassSymbol classSymbolEnclClass;
        Symbol.MethodSymbol methodSymbolSearchMethod;
        Symbol.MethodSymbol methodSymbolSearchMethod2;
        if (name == this.names.init || set.contains(classSymbol)) {
            return null;
        }
        set.add(classSymbol);
        if (list == null) {
            Symbol.MethodSymbol methodSymbol = null;
            for (Symbol symbol : classSymbol.members().getSymbolsByName(name)) {
                if (symbol.kind == Kinds.Kind.MTH && symbol.name == name) {
                    methodSymbol = (Symbol.MethodSymbol) symbol;
                }
            }
            if (methodSymbol != null) {
                return methodSymbol;
            }
        } else {
            for (Symbol symbol2 : classSymbol.members().getSymbolsByName(name)) {
                if (symbol2 != null && symbol2.kind == Kinds.Kind.MTH) {
                    Symbol.MethodSymbol methodSymbol2 = (Symbol.MethodSymbol) symbol2;
                    if (hasParameterTypes(methodSymbol2, list, z)) {
                        return methodSymbol2;
                    }
                }
            }
        }
        Symbol.TypeSymbol typeSymbol = classSymbol.getSuperclass().tsym;
        if (typeSymbol != null) {
            javacTrees = this;
            name2 = name;
            list2 = list;
            z3 = z;
            z4 = z2;
            set2 = set;
            Symbol.MethodSymbol methodSymbolSearchMethod3 = javacTrees.searchMethod((Symbol.ClassSymbol) typeSymbol, name2, list2, z3, z4, set2);
            if (methodSymbolSearchMethod3 != null) {
                return methodSymbolSearchMethod3;
            }
        } else {
            javacTrees = this;
            name2 = name;
            list2 = list;
            z3 = z;
            z4 = z2;
            set2 = set;
        }
        for (List interfaces = classSymbol.getInterfaces(); interfaces.nonEmpty(); interfaces = interfaces.tail) {
            Type type = (Type) interfaces.head;
            if (!type.isErroneous() && (methodSymbolSearchMethod2 = javacTrees.searchMethod((Symbol.ClassSymbol) type.tsym, name2, list2, z3, z4, set2)) != null) {
                return methodSymbolSearchMethod2;
            }
        }
        if (z4 || (classSymbolEnclClass = classSymbol.owner.enclClass()) == null || (methodSymbolSearchMethod = javacTrees.searchMethod(classSymbolEnclClass, name2, list2, z3, z4, set2)) == null) {
            return null;
        }
        return methodSymbolSearchMethod;
    }

    private DocCommentTree transform(DocCommentTree docCommentTree) {
        initDocCommentTreeTransformer();
        return this.docCommentTreeTransformer.transform(this, docCommentTree);
    }

    public Copier createCopier(TreeMaker treeMaker) {
        return new Copier(treeMaker);
    }

    public Symbol.MethodSymbol findConstructor(Symbol.ClassSymbol classSymbol, List<Type> list, boolean z) {
        for (Symbol symbol : classSymbol.members().getSymbolsByName(this.names.init)) {
            if (symbol.kind == Kinds.Kind.MTH) {
                Symbol.MethodSymbol methodSymbol = (Symbol.MethodSymbol) symbol;
                if (hasParameterTypes(methodSymbol, list, z)) {
                    return methodSymbol;
                }
            }
        }
        return null;
    }

    @Override // com.sun.source.util.DocTrees
    public BreakIterator getBreakIterator() {
        return this.breakIterator;
    }

    @Override // com.sun.source.util.DocTrees
    public String getCharacters(EntityTree entityTree) {
        return Entity.getCharacters(entityTree);
    }

    @Override // com.sun.source.util.Trees
    public String getDocComment(TreePath treePath) {
        CompilationUnitTree compilationUnit = treePath.getCompilationUnit();
        Tree leaf = treePath.getLeaf();
        if (!(compilationUnit instanceof JCTree.JCCompilationUnit)) {
            return null;
        }
        JCTree.JCCompilationUnit jCCompilationUnit = (JCTree.JCCompilationUnit) compilationUnit;
        if (!(leaf instanceof JCTree)) {
            return null;
        }
        JCTree jCTree = (JCTree) leaf;
        DocCommentTable docCommentTable = jCCompilationUnit.docComments;
        if (docCommentTable != null) {
            return docCommentTable.getCommentText(jCTree);
        }
        return null;
    }

    @Override // com.sun.source.util.DocTrees
    public Elements.DocCommentKind getDocCommentKind(TreePath treePath) {
        Tokens.Comment comment;
        CompilationUnitTree compilationUnit = treePath.getCompilationUnit();
        Tree leaf = treePath.getLeaf();
        if (compilationUnit instanceof JCTree.JCCompilationUnit) {
            JCTree.JCCompilationUnit jCCompilationUnit = (JCTree.JCCompilationUnit) compilationUnit;
            if (leaf instanceof JCTree) {
                JCTree jCTree = (JCTree) leaf;
                DocCommentTable docCommentTable = jCCompilationUnit.docComments;
                if (docCommentTable == null || (comment = docCommentTable.getComment(jCTree)) == null) {
                    return null;
                }
                int i = AnonymousClass7.$SwitchMap$com$sun$tools$javac$parser$Tokens$Comment$CommentStyle[comment.getStyle().ordinal()];
                if (i == 1) {
                    return Elements.DocCommentKind.TRADITIONAL;
                }
                if (i != 2) {
                    return null;
                }
                return Elements.DocCommentKind.END_OF_LINE;
            }
        }
        return null;
    }

    @Override // com.sun.source.util.DocTrees
    public DocCommentTree getDocCommentTree(final FileObject fileObject) {
        JavaFileObject javaFileObjectAsDocFileObject = asDocFileObject(fileObject);
        return transform(new DocCommentParser(this.parserFactory, new DiagnosticSource(javaFileObjectAsDocFileObject, this.log), new Tokens.Comment(this) { // from class: com.sun.tools.javac.api.JavacTrees.3
            int offset = 0;
            final /* synthetic */ JavacTrees this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public JCDiagnostic.DiagnosticPosition getPos() {
                return null;
            }

            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public int getSourcePos(int i) {
                return this.offset + i;
            }

            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public Tokens.Comment.CommentStyle getStyle() {
                if (JavacTrees.isHtmlFile(fileObject)) {
                    return Tokens.Comment.CommentStyle.JAVADOC_BLOCK;
                }
                if (JavacTrees.isMarkdownFile(fileObject)) {
                    return Tokens.Comment.CommentStyle.JAVADOC_LINE;
                }
                return null;
            }

            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public String getText() {
                try {
                    return fileObject.getCharContent(true).toString();
                } catch (IOException unused) {
                    return "";
                }
            }

            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public boolean isDeprecated() {
                return false;
            }

            @Override // com.sun.tools.javac.parser.Tokens.Comment
            public Tokens.Comment stripIndent() {
                return this;
            }
        }, javaFileObjectAsDocFileObject.getKind() == JavaFileObject.Kind.HTML).parse());
    }

    public DocCommentTreeTransformer getDocCommentTreeTransformer() {
        return this.docCommentTreeTransformer;
    }

    @Override // com.sun.source.util.DocTrees
    public DocTreePath getDocTreePath(FileObject fileObject, PackageElement packageElement) {
        JavaFileObject javaFileObjectAsDocFileObject = asDocFileObject(fileObject);
        DocCommentTree docCommentTree = getDocCommentTree(javaFileObjectAsDocFileObject);
        if (docCommentTree == null) {
            return null;
        }
        return new DocTreePath(makeTreePath((Symbol.PackageSymbol) packageElement, javaFileObjectAsDocFileObject, docCommentTree), docCommentTree);
    }

    @Override // com.sun.source.util.Trees
    public Symbol getElement(TreePath treePath) {
        JCTree.JCClassDecl jCClassDecl;
        Symbol.ClassSymbol classSymbol;
        JCTree jCTree = (JCTree) treePath.getLeaf();
        Symbol symbolSymbolFor = TreeInfo.symbolFor(jCTree);
        if (symbolSymbolFor == null) {
            while (treePath != null) {
                JCTree jCTree2 = (JCTree) treePath.getLeaf();
                if (jCTree2.hasTag(JCTree.Tag.CLASSDEF) && (classSymbol = (jCClassDecl = (JCTree.JCClassDecl) jCTree2).sym) != null) {
                    if ((classSymbol.flags_field & 268435456) == 0) {
                        break;
                    }
                    this.attr.attribClass(jCClassDecl.pos(), jCClassDecl.sym);
                    return TreeInfo.symbolFor(jCTree);
                }
                treePath = treePath.getParentPath();
            }
        }
        return symbolSymbolFor;
    }

    @Override // com.sun.source.util.DocTrees
    public java.util.List<DocTree> getFirstSentence(java.util.List<? extends DocTree> list) {
        return this.docTreeMaker.getFirstSentence(list);
    }

    @Override // com.sun.source.util.Trees
    public TypeMirror getLub(CatchTree catchTree) {
        JCTree.JCVariableDecl jCVariableDecl = ((JCTree.JCCatch) catchTree).param;
        Type type = jCVariableDecl.type;
        return (type == null || type.getKind() != TypeKind.UNION) ? jCVariableDecl.type : ((Type.UnionClassType) jCVariableDecl.type).getLub();
    }

    @Override // com.sun.source.util.Trees
    public TypeMirror getOriginalType(ErrorType errorType) {
        if (errorType instanceof Type.ErrorType) {
            return ((Type.ErrorType) errorType).getOriginalType();
        }
        if (errorType instanceof Type.ClassType) {
            final Type.ClassType classType = (Type.ClassType) errorType;
            if (errorType.getKind() == TypeKind.ERROR) {
                return this.extraType2OriginalMap.computeIfAbsent(classType, new Function() { // from class: qn7
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return JavacTrees.d(this.b, classType, (Type) obj);
                    }
                });
            }
        }
        return Type.noType;
    }

    public ParserFactory getParserFactory() {
        return this.parserFactory;
    }

    @Override // com.sun.source.util.Trees
    public TreePath getPath(Element element, AnnotationMirror annotationMirror, AnnotationValue annotationValue) {
        Pair<JCTree, JCTree.JCCompilationUnit> treeAndTopLevel = this.elements.getTreeAndTopLevel(element, annotationMirror, annotationValue);
        if (treeAndTopLevel == null) {
            return null;
        }
        return TreePath.getPath(treeAndTopLevel.snd, treeAndTopLevel.fst);
    }

    @Override // com.sun.source.util.Trees
    public JavacScope getScope(TreePath treePath) {
        return JavacScope.create(getAttrContext(treePath));
    }

    @Override // com.sun.source.util.DocTrees, com.sun.source.util.Trees
    public DocSourcePositions getSourcePositions() {
        return new DocSourcePositions() { // from class: com.sun.tools.javac.api.JavacTrees.1
            @Override // com.sun.source.util.DocSourcePositions
            public long getEndPosition(CompilationUnitTree compilationUnitTree, DocCommentTree docCommentTree, DocTree docTree) {
                return ((DCTree.DCDocComment) docCommentTree).getSourcePosition(((DCTree) docTree).getEndPosition());
            }

            @Override // com.sun.source.util.DocSourcePositions
            public long getStartPosition(CompilationUnitTree compilationUnitTree, DocCommentTree docCommentTree, DocTree docTree) {
                return ((DCTree.DCDocComment) docCommentTree).getSourcePosition(((DCTree) docTree).getStartPosition());
            }

            @Override // com.sun.source.util.SourcePositions
            public long getEndPosition(CompilationUnitTree compilationUnitTree, Tree tree) {
                return TreeInfo.getEndPos((JCTree) tree, ((JCTree.JCCompilationUnit) compilationUnitTree).endPositions);
            }

            @Override // com.sun.source.util.SourcePositions
            public long getStartPosition(CompilationUnitTree compilationUnitTree, Tree tree) {
                return TreeInfo.getStartPos((JCTree) tree);
            }
        };
    }

    @Override // com.sun.source.util.Trees
    public JCTree getTree(Element element, AnnotationMirror annotationMirror, AnnotationValue annotationValue) {
        Pair<JCTree, JCTree.JCCompilationUnit> treeAndTopLevel = this.elements.getTreeAndTopLevel(element, annotationMirror, annotationValue);
        if (treeAndTopLevel == null) {
            return null;
        }
        return treeAndTopLevel.fst;
    }

    @Override // com.sun.source.util.DocTrees
    public TypeMirror getType(DocTreePath docTreePath) {
        DocTree leaf = docTreePath.getLeaf();
        if (leaf instanceof DCTree.DCReference) {
            DCTree.DCReference dCReference = (DCTree.DCReference) leaf;
            if (dCReference.qualifierExpression != null) {
                Log log = this.log;
                Objects.requireNonNull(log);
                Log.DeferredDiagnosticHandler deferredDiagnosticHandler = new Log.DeferredDiagnosticHandler(log);
                try {
                    Env<AttrContext> attrContext = getAttrContext(docTreePath.getTreePath());
                    JavaFileObject javaFileObjectUseSource = this.log.useSource(attrContext.toplevel.sourcefile);
                    try {
                        Type typeAttribType = this.attr.attribType(dCReference.qualifierExpression, attrContext);
                        if (typeAttribType != null && !typeAttribType.isErroneous()) {
                            this.log.useSource(javaFileObjectUseSource);
                            this.log.popDiagnosticHandler(deferredDiagnosticHandler);
                            return typeAttribType;
                        }
                        this.log.useSource(javaFileObjectUseSource);
                        this.log.popDiagnosticHandler(deferredDiagnosticHandler);
                    } catch (Throwable th) {
                        this.log.useSource(javaFileObjectUseSource);
                        throw th;
                    }
                } catch (Abort unused) {
                    this.log.popDiagnosticHandler(deferredDiagnosticHandler);
                    return null;
                } catch (Throwable th2) {
                    this.log.popDiagnosticHandler(deferredDiagnosticHandler);
                    throw th2;
                }
            }
        }
        Element element = getElement(docTreePath);
        if (element == null) {
            return null;
        }
        return element.asType();
    }

    @Override // com.sun.source.util.Trees
    public TypeMirror getTypeMirror(TreePath treePath) {
        Type type = ((JCTree) treePath.getLeaf()).type;
        if (type == null) {
            return null;
        }
        return type.stripMetadataIfNeeded();
    }

    public void initDocCommentTreeTransformer() {
        if (this.docCommentTreeTransformer == null) {
            this.docCommentTreeTransformer = (DocCommentTreeTransformer) ServiceLoaderWrapper.load(DocCommentTreeTransformer.class).stream().map(new Function() { // from class: nn7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return (JavacTrees.DocCommentTreeTransformer) ((ServiceLoaderWrapper.Provider) obj).get();
                }
            }).filter(new Predicate() { // from class: on7
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((JavacTrees.DocCommentTreeTransformer) obj).name().equals(JavacTrees.DocCommentTreeTransformer.STANDARD);
                }
            }).findFirst().orElseGet(new Supplier() { // from class: pn7
                @Override // java.util.function.Supplier
                public final Object get() {
                    return JavacTrees.c();
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.util.Trees
    public boolean isAccessible(com.sun.source.tree.Scope scope, Element element, DeclaredType declaredType) {
        if (!(scope instanceof JavacScope)) {
            return false;
        }
        JavacScope javacScope = (JavacScope) scope;
        if (!(element instanceof Symbol)) {
            return false;
        }
        Symbol symbol = (Symbol) element;
        if (declaredType instanceof Type) {
            return this.resolve.isAccessible(javacScope.env, (Type) declaredType, symbol, true);
        }
        return false;
    }

    @Override // com.sun.source.util.DocTrees
    public void setBreakIterator(BreakIterator breakIterator) {
        this.breakIterator = breakIterator;
    }

    public void setDocCommentTreeTransformer(DocCommentTreeTransformer docCommentTreeTransformer) {
        this.docCommentTreeTransformer = docCommentTreeTransformer;
    }

    @Override // com.sun.source.util.DocTrees
    public DocTreeMaker getDocTreeFactory() {
        return this.docTreeMaker;
    }

    @Override // com.sun.source.util.Trees
    public JCTree.JCClassDecl getTree(TypeElement typeElement) {
        return (JCTree.JCClassDecl) getTree((Element) typeElement);
    }

    public static JavacTrees instance(ProcessingEnvironment processingEnvironment) {
        if (processingEnvironment instanceof JavacProcessingEnvironment) {
            return instance(((JavacProcessingEnvironment) processingEnvironment).getContext());
        }
        j2d.a();
        return null;
    }

    @Override // com.sun.source.util.Trees
    public JCTree.JCMethodDecl getTree(ExecutableElement executableElement) {
        return (JCTree.JCMethodDecl) getTree((Element) executableElement);
    }

    @Override // com.sun.source.util.Trees
    public JCTree getTree(Element element) {
        return getTree(element, (AnnotationMirror) null);
    }

    @Override // com.sun.source.util.Trees
    public JCTree getTree(Element element, AnnotationMirror annotationMirror) {
        return getTree(element, annotationMirror, (AnnotationValue) null);
    }

    public static JavacTrees instance(Context context) {
        JavacTrees javacTrees = (JavacTrees) context.get(JavacTrees.class);
        return javacTrees == null ? new JavacTrees(context) : javacTrees;
    }

    @Override // com.sun.source.util.Trees
    public TreePath getPath(Element element) {
        return getPath(element, null, null);
    }

    @Override // com.sun.source.util.Trees
    public TreePath getPath(Element element, AnnotationMirror annotationMirror) {
        return getPath(element, annotationMirror, null);
    }

    @Override // com.sun.source.util.Trees
    public TreePath getPath(CompilationUnitTree compilationUnitTree, Tree tree) {
        return TreePath.getPath(compilationUnitTree, tree);
    }

    @Override // com.sun.source.util.Trees
    public boolean isAccessible(com.sun.source.tree.Scope scope, TypeElement typeElement) {
        if (!(scope instanceof JavacScope)) {
            return false;
        }
        JavacScope javacScope = (JavacScope) scope;
        if (typeElement instanceof Symbol.ClassSymbol) {
            return this.resolve.isAccessible(javacScope.env, (Symbol.TypeSymbol) typeElement, true);
        }
        return false;
    }

    @Override // com.sun.source.util.DocTrees
    public DocCommentTree getDocCommentTree(Element element) {
        TreePath path = getPath(element);
        if (path == null) {
            return null;
        }
        return getDocCommentTree(path);
    }

    @Override // com.sun.source.util.DocTrees
    public DocCommentTree getDocCommentTree(Element element, String str) throws IOException {
        FileObject fileForInput = this.fileManager.getFileForInput(StandardLocation.SOURCE_PATH, this.elements.getPackageOf(element).getQualifiedName().toString(), str);
        if (fileForInput != null) {
            return getDocCommentTree(fileForInput);
        }
        throw new FileNotFoundException(str);
    }

    @Override // com.sun.source.util.DocTrees
    public DocCommentTree getDocCommentTree(TreePath treePath) {
        CompilationUnitTree compilationUnit = treePath.getCompilationUnit();
        Tree leaf = treePath.getLeaf();
        if (!(compilationUnit instanceof JCTree.JCCompilationUnit)) {
            return null;
        }
        JCTree.JCCompilationUnit jCCompilationUnit = (JCTree.JCCompilationUnit) compilationUnit;
        if (!(leaf instanceof JCTree)) {
            return null;
        }
        JCTree jCTree = (JCTree) leaf;
        DocCommentTable docCommentTable = jCCompilationUnit.docComments;
        if (docCommentTable != null) {
            return docCommentTable.getCommentTree(jCTree);
        }
        return null;
    }

    public DocCommentTree getDocCommentTree(DiagnosticSource diagnosticSource, Tokens.Comment comment) {
        return transform(new DocCommentParser(this.parserFactory, diagnosticSource, comment).parse());
    }

    @Override // com.sun.source.util.DocTrees
    public Element getElement(DocTreePath docTreePath) {
        DocTree leaf = docTreePath.getLeaf();
        if (leaf instanceof DCTree.DCReference) {
            return attributeDocReference(docTreePath.getTreePath(), (DCTree.DCReference) leaf);
        }
        if (!(leaf instanceof DCTree.DCIdentifier)) {
            return null;
        }
        DocTree leaf2 = docTreePath.getParentPath().getLeaf();
        if (!(leaf2 instanceof DCTree.DCParam)) {
            return null;
        }
        return attributeParamIdentifier(docTreePath.getTreePath(), (DCTree.DCParam) leaf2);
    }

    @Override // com.sun.source.util.DocTrees
    public void printMessage(Diagnostic.Kind kind, CharSequence charSequence, DocTree docTree, DocCommentTree docCommentTree, CompilationUnitTree compilationUnitTree) {
        printMessage(kind, charSequence, ((DCTree) docTree).pos((DCTree.DCDocComment) docCommentTree), compilationUnitTree);
    }

    public void printMessage(Diagnostic.Kind kind, CharSequence charSequence) {
        printMessage(kind, charSequence, (JCDiagnostic.DiagnosticPosition) null, (CompilationUnitTree) null);
    }

    @Override // com.sun.source.util.Trees
    public void printMessage(Diagnostic.Kind kind, CharSequence charSequence, Tree tree, CompilationUnitTree compilationUnitTree) {
        printMessage(kind, charSequence, ((JCTree) tree).pos(), compilationUnitTree);
    }
}

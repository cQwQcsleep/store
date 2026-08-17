package com.sun.tools.javac.main;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.source.util.TaskEvent;
import com.sun.tools.javac.api.MultiTaskListener;
import com.sun.tools.javac.code.ClassFinder;
import com.sun.tools.javac.code.DeferredCompletionFailureHandler;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.LintMapper;
import com.sun.tools.javac.code.ModuleFinder;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Analyzer;
import com.sun.tools.javac.comp.Annotate;
import com.sun.tools.javac.comp.Attr;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Check;
import com.sun.tools.javac.comp.CompileStates;
import com.sun.tools.javac.comp.Enter;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.comp.Flow;
import com.sun.tools.javac.comp.LambdaToMethod;
import com.sun.tools.javac.comp.Lower;
import com.sun.tools.javac.comp.Modules;
import com.sun.tools.javac.comp.Todo;
import com.sun.tools.javac.comp.TransPatterns;
import com.sun.tools.javac.comp.TransTypes;
import com.sun.tools.javac.comp.WarningAnalyzer;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.jvm.ClassReader;
import com.sun.tools.javac.jvm.ClassWriter;
import com.sun.tools.javac.jvm.Gen;
import com.sun.tools.javac.jvm.JNIWriter;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.parser.ParserFactory;
import com.sun.tools.javac.platform.PlatformDescription;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.Pretty;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Abort;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.FatalError;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Log.DiscardDiagnosticHandler;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.RichDiagnosticFormatter;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import javax.annotation.processing.Processor;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementVisitor;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavaCompiler {
    private static ResourceBundle versionRB = null;
    private static final String versionRBName = "com.sun.tools.javac.resources.version";
    protected Analyzer analyzer;
    protected Annotate annotate;
    protected boolean annotationProcessingOccurred;
    protected Attr attr;
    protected Check chk;
    public List<Closeable> closeables;
    protected CompilePolicy compilePolicy;
    protected CompileStates compileStates;
    protected final Name completionFailureName;
    protected Context context;
    protected DeferredCompletionFailureHandler dcfh;
    Log.DeferredDiagnosticHandler deferredDiagnosticHandler;
    protected boolean devVerbose;
    JCDiagnostic.Factory diagFactory;
    protected JCDiagnostic.Factory diags;
    protected Enter enter;
    private boolean enterDone;
    protected JavaFileManager fileManager;
    protected ClassFinder finder;
    protected Flow flow;
    protected Gen gen;
    public boolean genEndPos;
    protected boolean implicitSourceFilesRead;
    protected ImplicitSourcePolicy implicitSourcePolicy;
    protected JNIWriter jniWriter;
    public boolean lineDebugInfo;
    protected LintMapper lintMapper;
    public Log log;
    protected Lower lower;
    protected TreeMaker make;
    protected ModuleFinder moduleFinder;
    protected Modules modules;
    protected Names names;
    protected Options options;
    protected ParserFactory parserFactory;
    protected boolean processPcks;
    protected ClassReader reader;
    private List<JCTree.JCClassDecl> rootClasses;
    public CompileStates.CompileState shouldStopPolicyIfError;
    public CompileStates.CompileState shouldStopPolicyIfNoError;
    private final Symbol silentFail;
    protected Source source;
    public boolean sourceOutput;
    protected Symtab syms;
    protected MultiTaskListener taskListener;
    public Todo todo;
    protected TransTypes transTypes;
    protected Types types;
    public boolean verbose;
    public boolean verboseCompilePolicy;
    protected WarningAnalyzer warningAnalyzer;
    protected boolean werrorAny;
    protected EnumSet<Lint.LintCategory> werrorLint;
    protected ClassWriter writer;
    public static final Context.Key<JavaCompiler> compilerKey = new Context.Key<>();
    private static final CompilePolicy DEFAULT_COMPILE_POLICY = CompilePolicy.BY_TODO;
    protected final Symbol.Completer sourceCompleter = new Symbol.Completer() { // from class: tc7
        @Override // com.sun.tools.javac.code.Symbol.Completer
        public final void complete(Symbol symbol) {
            JavaCompiler.g(this.b, symbol);
        }
    };
    protected boolean explicitAnnotationProcessingRequested = false;
    protected Set<JavaFileObject> inputFiles = new HashSet();
    public boolean keepComments = false;
    private boolean hasBeenUsed = false;
    private long start_msec = 0;
    public long elapsed_msec = 0;
    boolean processAnnotations = false;
    private JavacProcessingEnvironment procEnvImpl = null;
    HashMap<Env<AttrContext>, Queue<Pair<Env<AttrContext>, JCTree.JCClassDecl>>> desugaredEnvs = new HashMap<>();

    /* JADX INFO: renamed from: com.sun.tools.javac.main.JavaCompiler$1ScanNested, reason: invalid class name */
    public class C1ScanNested extends TreeScanner {
        Set<Env<AttrContext>> dependencies = new LinkedHashSet();
        protected boolean hasLambdas;
        protected boolean hasPatterns;
        final /* synthetic */ JavaCompiler this$0;
        final /* synthetic */ Env val$env;

        public C1ScanNested(JavaCompiler javaCompiler, Env env) {
            this.val$env = env;
            this.this$0 = javaCompiler;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
            this.hasPatterns = true;
            super.visitBindingPattern(jCBindingPattern);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            Type typeSupertype = this.this$0.types.supertype(jCClassDecl.sym.type);
            boolean z = false;
            while (!z && typeSupertype.hasTag(TypeTag.CLASS)) {
                Env<AttrContext> env = this.this$0.enter.getEnv(typeSupertype.tsym.outermostClass());
                if (env != null && this.val$env != env) {
                    if (this.dependencies.add(env)) {
                        boolean z2 = this.hasLambdas;
                        boolean z3 = this.hasPatterns;
                        try {
                            scan(env.tree);
                            this.hasLambdas = z2;
                            this.hasPatterns = z3;
                        } catch (Throwable th) {
                            this.hasLambdas = z2;
                            this.hasPatterns = z3;
                            throw th;
                        }
                    }
                    z = true;
                }
                typeSupertype = this.this$0.types.supertype(typeSupertype);
            }
            super.visitClassDef(jCClassDecl);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            this.hasLambdas = true;
            super.visitLambda(jCLambda);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitRecordPattern(JCTree.JCRecordPattern jCRecordPattern) {
            this.hasPatterns = true;
            super.visitRecordPattern(jCRecordPattern);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitReference(JCTree.JCMemberReference jCMemberReference) {
            this.hasLambdas = true;
            super.visitReference(jCMemberReference);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitch(JCTree.JCSwitch jCSwitch) {
            this.hasPatterns |= jCSwitch.patternSwitch;
            super.visitSwitch(jCSwitch);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
            this.hasPatterns |= jCSwitchExpression.patternSwitch;
            super.visitSwitchExpression(jCSwitchExpression);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
            if (jCInstanceOf.pattern.type.isPrimitive()) {
                this.hasPatterns = true;
            }
            super.visitTypeTest(jCInstanceOf);
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.main.JavaCompiler$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr;
            try {
                iArr[JCTree.Tag.CLASSDEF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.METHODDEF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.VARDEF.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public enum CompilePolicy {
        SIMPLE,
        BY_FILE,
        BY_TODO;

        public static CompilePolicy decode(String str) {
            if (str == null) {
                return JavaCompiler.DEFAULT_COMPILE_POLICY;
            }
            if (str.equals("simple")) {
                return SIMPLE;
            }
            if (str.equals("byfile")) {
                return BY_FILE;
            }
            return str.equals("bytodo") ? BY_TODO : JavaCompiler.DEFAULT_COMPILE_POLICY;
        }
    }

    public enum ImplicitSourcePolicy {
        NONE,
        CLASS,
        UNSET;

        public static ImplicitSourcePolicy decode(String str) {
            if (str == null) {
                return UNSET;
            }
            if (str.equals(Option.LINT_CUSTOM_NONE)) {
                return NONE;
            }
            return str.equals("class") ? CLASS : UNSET;
        }
    }

    public static class InitialFileParser implements InitialFileParserIntf {
        public static final Context.Key<InitialFileParserIntf> initialParserKey = new Context.Key<>();
        private final JavaCompiler compiler;

        private InitialFileParser(Context context) {
            context.put((Context.Key<InitialFileParser>) initialParserKey, this);
            this.compiler = JavaCompiler.instance(context);
        }

        public static InitialFileParserIntf instance(Context context) {
            InitialFileParserIntf initialFileParserIntf = (InitialFileParserIntf) context.get(initialParserKey);
            return initialFileParserIntf == null ? new InitialFileParser(context) : initialFileParserIntf;
        }

        @Override // com.sun.tools.javac.main.JavaCompiler.InitialFileParserIntf
        public List<JCTree.JCCompilationUnit> parse(Iterable<JavaFileObject> iterable) {
            return this.compiler.parseFiles(iterable, false);
        }
    }

    public interface InitialFileParserIntf {
        List<JCTree.JCCompilationUnit> parse(Iterable<JavaFileObject> iterable);
    }

    public JavaCompiler(Context context) {
        this.closeables = List.nil();
        this.context = context;
        context.put(compilerKey, this);
        if (context.get(JavaFileManager.class) == null) {
            JavacFileManager.preRegister(context);
        }
        this.names = Names.instance(context);
        this.log = Log.instance(context);
        this.lintMapper = LintMapper.instance(context);
        this.diagFactory = JCDiagnostic.Factory.instance(context);
        this.finder = ClassFinder.instance(context);
        this.reader = ClassReader.instance(context);
        this.make = TreeMaker.instance(context);
        this.writer = ClassWriter.instance(context);
        this.jniWriter = JNIWriter.instance(context);
        this.enter = Enter.instance(context);
        this.todo = Todo.instance(context);
        this.fileManager = (JavaFileManager) context.get(JavaFileManager.class);
        this.parserFactory = ParserFactory.instance(context);
        this.compileStates = CompileStates.instance(context);
        try {
            this.syms = Symtab.instance(context);
        } catch (Symbol.CompletionFailure e) {
            this.log.error(CompilerProperties.Errors.CantAccess(e.sym, e.getDetailValue()));
        }
        this.source = Source.instance(context);
        this.attr = Attr.instance(context);
        this.analyzer = Analyzer.instance(context);
        this.chk = Check.instance(context);
        this.gen = Gen.instance(context);
        this.flow = Flow.instance(context);
        this.warningAnalyzer = WarningAnalyzer.instance(context);
        this.transTypes = TransTypes.instance(context);
        this.lower = Lower.instance(context);
        this.annotate = Annotate.instance(context);
        this.types = Types.instance(context);
        this.taskListener = MultiTaskListener.instance(context);
        this.modules = Modules.instance(context);
        this.moduleFinder = ModuleFinder.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        this.dcfh = DeferredCompletionFailureHandler.instance(context);
        this.finder.sourceCompleter = this.sourceCompleter;
        this.modules.findPackageInFile = new Modules.PackageNameFinder() { // from class: uc7
            @Override // com.sun.tools.javac.comp.Modules.PackageNameFinder
            public final Name findPackageNameOf(JavaFileObject javaFileObject) {
                return this.a.findPackageInFile(javaFileObject);
            }
        };
        this.moduleFinder.moduleNameFromSourceReader = new ModuleFinder.ModuleNameFromSourceReader() { // from class: vc7
            @Override // com.sun.tools.javac.code.ModuleFinder.ModuleNameFromSourceReader
            public final Name readModuleName(JavaFileObject javaFileObject) {
                return this.a.readModuleName(javaFileObject);
            }
        };
        Options optionsInstance = Options.instance(context);
        this.options = optionsInstance;
        this.verbose = optionsInstance.isSet(Option.VERBOSE);
        this.sourceOutput = this.options.isSet(Option.PRINTSOURCE);
        Options options = this.options;
        Option option = Option.G_CUSTOM;
        this.lineDebugInfo = options.isUnset(option) || this.options.isSet(option, "lines");
        this.genEndPos = this.options.isSet(Option.XJCOV) || context.get(DiagnosticListener.class) != null;
        this.devVerbose = this.options.isSet("dev");
        this.processPcks = this.options.isSet("process.packages");
        Options options2 = this.options;
        Option option2 = Option.WERROR;
        this.werrorAny = options2.isSet(option2) || this.options.isSet(Option.WERROR_CUSTOM, "all");
        this.werrorLint = this.options.getLintCategoriesOf(option2, new Supplier() { // from class: wc7
            @Override // java.util.function.Supplier
            public final Object get() {
                return Lint.LintCategory.newEmptySet();
            }
        });
        this.verboseCompilePolicy = this.options.isSet("verboseCompilePolicy");
        this.compilePolicy = CompilePolicy.decode(this.options.get("compilePolicy"));
        this.implicitSourcePolicy = ImplicitSourcePolicy.decode(this.options.get("-implicit"));
        this.completionFailureName = this.options.isSet("failcomplete") ? this.names.fromString(this.options.get("failcomplete")) : null;
        boolean zIsSet = this.options.isSet("should-stop.at");
        Options options3 = this.options;
        this.shouldStopPolicyIfError = zIsSet ? CompileStates.CompileState.valueOf(options3.get("should-stop.at")) : options3.isSet("should-stop.ifError") ? CompileStates.CompileState.valueOf(this.options.get("should-stop.ifError")) : CompileStates.CompileState.INIT;
        this.shouldStopPolicyIfNoError = this.options.isSet("should-stop.ifNoError") ? CompileStates.CompileState.valueOf(this.options.get("should-stop.ifNoError")) : CompileStates.CompileState.GENERATE;
        if (this.options.isUnset("diags.legacy")) {
            this.log.setDiagnosticFormatter(RichDiagnosticFormatter.instance(context));
        }
        PlatformDescription platformDescription = (PlatformDescription) context.get(PlatformDescription.class);
        if (platformDescription != null) {
            this.closeables = this.closeables.prepend(platformDescription);
        }
        this.silentFail = new Symbol(Kinds.Kind.ABSENT_TYP, 0L, this.names.empty, Type.noType, this.syms.rootPackage) { // from class: com.sun.tools.javac.main.JavaCompiler.1
            @Override // javax.lang.model.element.Element
            public <R, P> R accept(ElementVisitor<R, P> elementVisitor, P p) {
                return elementVisitor.visitUnknown(this, p);
            }

            @Override // com.sun.tools.javac.code.Symbol
            public boolean exists() {
                return false;
            }
        };
    }

    public static /* synthetic */ int b(JCDiagnostic jCDiagnostic) {
        return jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.RECOVERABLE) ? 1 : 0;
    }

    public static /* synthetic */ int d(ToIntFunction toIntFunction, JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2) {
        return toIntFunction.applyAsInt(jCDiagnostic) - toIntFunction.applyAsInt(jCDiagnostic2);
    }

    private static long elapsed(long j) {
        return now() - j;
    }

    public static boolean explicitAnnotationProcessingRequested(Options options, JavaFileManager javaFileManager) {
        if (options.isSet(Option.PROCESSOR) || options.isSet(Option.PROCESSOR_PATH) || options.isSet(Option.PROCESSOR_MODULE_PATH)) {
            return true;
        }
        Option option = Option.PROC;
        return options.isSet(option, Constants.ATTRNAME_ONLY) || options.isSet(option, "full") || options.isSet(Option.A) || options.isSet(Option.XPRINT) || javaFileManager.hasLocation(StandardLocation.ANNOTATION_PROCESSOR_PATH);
    }

    public static /* synthetic */ Name f(JCTree.JCCompilationUnit jCCompilationUnit) {
        JCTree.JCModuleDecl moduleDecl = jCCompilationUnit.getModuleDecl();
        if (moduleDecl != null) {
            return TreeInfo.fullName(moduleDecl.getName());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Name findPackageInFile(JavaFileObject javaFileObject) {
        return parseAndGetName(javaFileObject, new Function() { // from class: sc7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return JavaCompiler.i((JCTree.JCCompilationUnit) obj);
            }
        });
    }

    public static String fullVersion() {
        return version("full");
    }

    public static /* synthetic */ void g(JavaCompiler javaCompiler, Symbol symbol) {
        javaCompiler.getClass();
        javaCompiler.readSourceFile((Symbol.ClassSymbol) symbol);
    }

    public static /* synthetic */ Name i(JCTree.JCCompilationUnit jCCompilationUnit) {
        if (jCCompilationUnit.getPackage() != null) {
            return TreeInfo.fullName(jCCompilationUnit.getPackage().getPackageName());
        }
        return null;
    }

    public static JavaCompiler instance(Context context) {
        JavaCompiler javaCompiler = (JavaCompiler) context.get(compilerKey);
        return javaCompiler == null ? new JavaCompiler(context) : javaCompiler;
    }

    private TaskEvent newAnalyzeTaskEvent(Env<AttrContext> env) {
        JCTree.JCCompilationUnit jCCompilationUnit = env.toplevel;
        Symbol.ClassSymbol classSymbol = env.enclClass.sym;
        if (classSymbol == this.syms.predefClass) {
            if (TreeInfo.isModuleInfo(jCCompilationUnit)) {
                classSymbol = jCCompilationUnit.modle.module_info;
            } else {
                if (!TreeInfo.isPackageInfo(jCCompilationUnit)) {
                    k2d.a("unknown env.toplevel");
                    return null;
                }
                classSymbol = jCCompilationUnit.packge.package_info;
            }
        }
        return new TaskEvent(TaskEvent.Kind.ANALYZE, jCCompilationUnit, classSymbol);
    }

    private static long now() {
        return System.currentTimeMillis();
    }

    private JCTree.JCCompilationUnit parse(JavaFileObject javaFileObject, CharSequence charSequence, boolean z) {
        CharSequence charSequence2;
        long jNow = now();
        JCTree.JCCompilationUnit jCCompilationUnitTopLevel = this.make.TopLevel(List.nil());
        this.lintMapper.startParsingFile(javaFileObject);
        if (charSequence != null) {
            if (this.verbose) {
                this.log.printVerbose("parsing.started", javaFileObject);
            }
            if (!this.taskListener.isEmpty() && !z) {
                this.taskListener.started(new TaskEvent(TaskEvent.Kind.PARSE, javaFileObject));
                this.keepComments = true;
                this.genEndPos = true;
            }
            charSequence2 = charSequence;
            jCCompilationUnitTopLevel = this.parserFactory.newParser(charSequence2, keepComments(), this.genEndPos, this.lineDebugInfo, javaFileObject.isNameCompatible("module-info", JavaFileObject.Kind.SOURCE)).parseCompilationUnit();
            if (this.verbose) {
                this.log.printVerbose("parsing.done", Long.toString(elapsed(jNow)));
            }
        } else {
            charSequence2 = charSequence;
        }
        jCCompilationUnitTopLevel.sourcefile = javaFileObject;
        this.lintMapper.finishParsingFile(jCCompilationUnitTopLevel);
        if (charSequence2 != null && !this.taskListener.isEmpty() && !z) {
            this.taskListener.finished(new TaskEvent(TaskEvent.Kind.PARSE, jCCompilationUnitTopLevel));
        }
        return jCCompilationUnitTopLevel;
    }

    private Name parseAndGetName(JavaFileObject javaFileObject, Function<JCTree.JCCompilationUnit, Name> function) {
        Log log = this.log;
        Objects.requireNonNull(log);
        Log.DiscardDiagnosticHandler discardDiagnosticHandler = log.new DiscardDiagnosticHandler();
        JavaFileObject javaFileObjectUseSource = this.log.useSource(javaFileObject);
        try {
            return function.apply(parse(javaFileObject, javaFileObject.getCharContent(false), true));
        } catch (IOException unused) {
            return null;
        } finally {
            this.log.popDiagnosticHandler(discardDiagnosticHandler);
            this.log.useSource(javaFileObjectUseSource);
        }
    }

    private void printSuppressedCount(int i, int i2, String str) {
        if (i2 > 0) {
            Log log = this.log;
            Log.WriterKind writerKind = Log.WriterKind.ERROR;
            log.printLines(writerKind, str, String.valueOf(i), String.valueOf(i2 + i));
            this.log.flush(writerKind);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Name readModuleName(JavaFileObject javaFileObject) {
        return parseAndGetName(javaFileObject, new Function() { // from class: nc7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return JavaCompiler.f((JCTree.JCCompilationUnit) obj);
            }
        });
    }

    private boolean unrecoverableError() {
        Log.DeferredDiagnosticHandler deferredDiagnosticHandler = this.deferredDiagnosticHandler;
        if (deferredDiagnosticHandler == null) {
            return false;
        }
        for (JCDiagnostic jCDiagnostic : deferredDiagnosticHandler.getDiagnostics()) {
            if (jCDiagnostic.getKind() == Diagnostic.Kind.ERROR && !jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.RECOVERABLE)) {
                return true;
            }
        }
        return false;
    }

    private static String version(String str) {
        if (versionRB == null) {
            try {
                versionRB = ResourceBundle.getBundle(versionRBName);
            } catch (MissingResourceException unused) {
                return Log.getLocalizedString("version.not.available", new Object[0]);
            }
        }
        try {
            return versionRB.getString(str);
        } catch (MissingResourceException unused2) {
            return Log.getLocalizedString("version.not.available", new Object[0]);
        }
    }

    public Env<AttrContext> attribute(Env<AttrContext> env) {
        CompileStates compileStates = this.compileStates;
        CompileStates.CompileState compileState = CompileStates.CompileState.ATTR;
        if (compileStates.isDone(env, compileState)) {
            return env;
        }
        if (this.verboseCompilePolicy) {
            printNote("[attribute " + env.enclClass.sym + "]");
        }
        if (this.verbose) {
            this.log.printVerbose("checking.attribution", env.enclClass.sym);
        }
        if (!this.taskListener.isEmpty()) {
            this.taskListener.started(newAnalyzeTaskEvent(env));
        }
        Log log = this.log;
        JavaFileObject javaFileObject = env.enclClass.sym.sourcefile;
        if (javaFileObject == null) {
            javaFileObject = env.toplevel.sourcefile;
        }
        JavaFileObject javaFileObjectUseSource = log.useSource(javaFileObject);
        try {
            this.attr.attrib(env);
            if (errorCount() > 0 && !shouldStop(compileState)) {
                this.attr.postAttr(env.tree);
            }
            this.compileStates.put(env, compileState);
            return env;
        } finally {
            this.log.useSource(javaFileObjectUseSource);
        }
    }

    public void checkReusable() {
        throw new AssertionError("attempt to reuse JavaCompiler");
    }

    public void close() {
        FatalError fatalError = null;
        this.rootClasses = null;
        this.finder = null;
        this.reader = null;
        this.make = null;
        this.writer = null;
        this.enter = null;
        Todo todo = this.todo;
        if (todo != null) {
            todo.clear();
        }
        this.todo = null;
        this.parserFactory = null;
        this.syms = null;
        this.source = null;
        this.attr = null;
        this.chk = null;
        this.gen = null;
        this.lintMapper = null;
        this.flow = null;
        this.transTypes = null;
        this.lower = null;
        this.annotate = null;
        this.types = null;
        this.log.flush();
        try {
            try {
                this.fileManager.flush();
                Names names = this.names;
                if (names != null) {
                    names.dispose();
                }
                this.names = null;
                Iterator<Closeable> it = this.closeables.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().close();
                    } catch (IOException e) {
                        if (fatalError == null) {
                            fatalError = new FatalError(this.diagFactory.fragment(CompilerProperties.Fragments.FatalErrCantClose), e);
                        } else {
                            fatalError.addSuppressed(e);
                        }
                    }
                }
                if (fatalError != null) {
                    throw fatalError;
                }
                this.closeables = List.nil();
            } catch (IOException e2) {
                throw new Abort(e2);
            }
        } catch (Throwable th) {
            Names names2 = this.names;
            if (names2 != null) {
                names2.dispose();
            }
            this.names = null;
            Iterator<Closeable> it2 = this.closeables.iterator();
            while (it2.hasNext()) {
                try {
                    it2.next().close();
                } catch (IOException e3) {
                    if (fatalError == null) {
                        fatalError = new FatalError(this.diagFactory.fragment(CompilerProperties.Fragments.FatalErrCantClose), e3);
                    } else {
                        fatalError.addSuppressed(e3);
                    }
                }
            }
            if (fatalError != null) {
                throw fatalError;
            }
            this.closeables = List.nil();
            throw th;
        }
    }

    public void compile(Collection<JavaFileObject> collection, Collection<String> collection2, Iterable<? extends Processor> iterable, Collection<String> collection3) {
        if (!this.taskListener.isEmpty()) {
            this.taskListener.started(new TaskEvent(TaskEvent.Kind.COMPILATION));
        }
        if (this.hasBeenUsed) {
            checkReusable();
        }
        this.hasBeenUsed = true;
        this.start_msec = now();
        try {
            try {
                initProcessAnnotations(iterable, collection, collection2);
                for (String str : collection2) {
                    int iIndexOf = str.indexOf(47);
                    if (iIndexOf != -1) {
                        this.modules.addExtraAddModules(str.substring(0, iIndexOf));
                    }
                }
                Iterator<String> it = collection3.iterator();
                while (it.hasNext()) {
                    this.modules.addExtraAddModules(it.next());
                }
                CompileStates.CompileState compileState = CompileStates.CompileState.ENTER;
                processAnnotations(enterTrees(stopIfError(compileState, initModules(stopIfError(compileState, parseFiles(collection))))), collection2);
                if (this.taskListener.isEmpty() && this.implicitSourcePolicy == ImplicitSourcePolicy.NONE) {
                    this.todo.retainFiles(this.inputFiles);
                }
                if (!CompileStates.CompileState.ATTR.isAfter(this.shouldStopPolicyIfNoError)) {
                    int iOrdinal = this.compilePolicy.ordinal();
                    if (iOrdinal == 0) {
                        generate(desugar(warn(flow(attribute(this.todo)))));
                    } else if (iOrdinal == 1) {
                        Queue<Queue<Env<AttrContext>>> queueGroupByFile = this.todo.groupByFile();
                        while (!queueGroupByFile.isEmpty() && !shouldStop(CompileStates.CompileState.ATTR)) {
                            generate(desugar(warn(flow(attribute(queueGroupByFile.remove())))));
                        }
                    } else if (iOrdinal != 2) {
                        Assert.error("unknown compile policy");
                    } else {
                        while (!this.todo.isEmpty()) {
                            generate(desugar(warn(flow(attribute(this.todo.remove())))));
                        }
                    }
                }
                if (this.verbose) {
                    long jElapsed = elapsed(this.start_msec);
                    this.elapsed_msec = jElapsed;
                    this.log.printVerbose("total", Long.toString(jElapsed));
                }
                reportDeferredDiagnostics();
                if (!this.log.hasDiagnosticListener()) {
                    printCount("error", errorCount());
                    printCount("warn", warningCount());
                    printSuppressedCount(errorCount(), this.log.nsuppressederrors, "count.error.recompile");
                    printSuppressedCount(warningCount(), this.log.nsuppressedwarns, "count.warn.recompile");
                }
                if (!this.taskListener.isEmpty()) {
                    this.taskListener.finished(new TaskEvent(TaskEvent.Kind.COMPILATION));
                }
                close();
                JavacProcessingEnvironment javacProcessingEnvironment = this.procEnvImpl;
                if (javacProcessingEnvironment != null) {
                    javacProcessingEnvironment.close();
                }
            } catch (Abort e) {
                if (this.devVerbose) {
                    e.printStackTrace(System.err);
                }
                reportDeferredDiagnosticAndClearHandler();
                if (this.verbose) {
                    long jElapsed2 = elapsed(this.start_msec);
                    this.elapsed_msec = jElapsed2;
                    this.log.printVerbose("total", Long.toString(jElapsed2));
                }
                reportDeferredDiagnostics();
                if (!this.log.hasDiagnosticListener()) {
                    printCount("error", errorCount());
                    printCount("warn", warningCount());
                    printSuppressedCount(errorCount(), this.log.nsuppressederrors, "count.error.recompile");
                    printSuppressedCount(warningCount(), this.log.nsuppressedwarns, "count.warn.recompile");
                }
                if (!this.taskListener.isEmpty()) {
                    this.taskListener.finished(new TaskEvent(TaskEvent.Kind.COMPILATION));
                }
                close();
                JavacProcessingEnvironment javacProcessingEnvironment2 = this.procEnvImpl;
                if (javacProcessingEnvironment2 != null) {
                    javacProcessingEnvironment2.close();
                }
            }
        } catch (Throwable th) {
            if (this.verbose) {
                long jElapsed3 = elapsed(this.start_msec);
                this.elapsed_msec = jElapsed3;
                this.log.printVerbose("total", Long.toString(jElapsed3));
            }
            reportDeferredDiagnostics();
            if (!this.log.hasDiagnosticListener()) {
                printCount("error", errorCount());
                printCount("warn", warningCount());
                printSuppressedCount(errorCount(), this.log.nsuppressederrors, "count.error.recompile");
                printSuppressedCount(warningCount(), this.log.nsuppressedwarns, "count.warn.recompile");
            }
            if (!this.taskListener.isEmpty()) {
                this.taskListener.finished(new TaskEvent(TaskEvent.Kind.COMPILATION));
            }
            close();
            JavacProcessingEnvironment javacProcessingEnvironment3 = this.procEnvImpl;
            if (javacProcessingEnvironment3 != null) {
                javacProcessingEnvironment3.close();
            }
            throw th;
        }
    }

    public boolean continueAfterProcessAnnotations() {
        return !shouldStop(CompileStates.CompileState.ATTR);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void desugar(Env<AttrContext> env, Queue<Pair<Env<AttrContext>, JCTree.JCClassDecl>> queue) {
        if (shouldStop(CompileStates.CompileState.TRANSTYPES)) {
            return;
        }
        if (this.implicitSourcePolicy != ImplicitSourcePolicy.NONE || this.inputFiles.contains(env.toplevel.sourcefile)) {
            Modules modules = this.modules;
            if (modules.multiModuleMode || env.toplevel.modle == modules.getDefaultModule()) {
                if (this.compileStates.isDone(env, CompileStates.CompileState.LOWER)) {
                    queue.addAll(this.desugaredEnvs.get(env));
                    return;
                }
                if (!this.compileStates.isDone(env, CompileStates.CompileState.WARN)) {
                    warn(env);
                }
                C1ScanNested c1ScanNested = new C1ScanNested(this, env);
                c1ScanNested.scan(env.tree);
                for (Env<AttrContext> env2 : c1ScanNested.dependencies) {
                    if (!this.compileStates.isDone(env2, CompileStates.CompileState.WARN)) {
                        this.desugaredEnvs.put(env2, desugar(warn(flow(attribute(env2)))));
                    }
                }
                CompileStates.CompileState compileState = CompileStates.CompileState.TRANSTYPES;
                if (shouldStop(compileState)) {
                    return;
                }
                if (this.verboseCompilePolicy) {
                    printNote("[desugar " + env.enclClass.sym + "]");
                }
                Log log = this.log;
                JavaFileObject javaFileObject = env.enclClass.sym.sourcefile;
                if (javaFileObject == null) {
                    javaFileObject = env.toplevel.sourcefile;
                }
                JavaFileObject javaFileObjectUseSource = log.useSource(javaFileObject);
                try {
                    JCTree jCTree = env.tree;
                    this.make.at(0);
                    TreeMaker treeMakerForToplevel = this.make.forToplevel(env.toplevel);
                    if (!env.tree.hasTag(JCTree.Tag.PACKAGEDEF) && !env.tree.hasTag(JCTree.Tag.MODULEDEF)) {
                        if (shouldStop(compileState)) {
                            return;
                        }
                        env.tree = this.transTypes.translateTopLevelClass(env.tree, treeMakerForToplevel);
                        this.compileStates.put(env, compileState);
                        CompileStates.CompileState compileState2 = CompileStates.CompileState.TRANSPATTERNS;
                        if (shouldStop(compileState2)) {
                            return;
                        }
                        if (c1ScanNested.hasPatterns) {
                            env.tree = TransPatterns.instance(this.context).translateTopLevelClass(env, env.tree, treeMakerForToplevel);
                        }
                        this.compileStates.put(env, compileState2);
                        CompileStates.CompileState compileState3 = CompileStates.CompileState.LOWER;
                        if (shouldStop(compileState3)) {
                            return;
                        }
                        if (this.sourceOutput) {
                            JCTree.JCClassDecl jCClassDecl = (JCTree.JCClassDecl) env.tree;
                            if (jCTree instanceof JCTree.JCClassDecl) {
                                if (this.rootClasses.contains((JCTree.JCClassDecl) jCTree)) {
                                    queue.add(new Pair<>(env, jCClassDecl));
                                }
                            }
                            return;
                        }
                        List listTranslateTopLevelClass = this.lower.translateTopLevelClass(env, env.tree, treeMakerForToplevel);
                        this.compileStates.put(env, compileState3);
                        if (shouldStop(compileState3)) {
                            return;
                        }
                        if (c1ScanNested.hasLambdas) {
                            if (shouldStop(CompileStates.CompileState.UNLAMBDA)) {
                                return;
                            }
                            Iterator it = listTranslateTopLevelClass.iterator();
                            while (it.hasNext()) {
                                LambdaToMethod.instance(this.context).translateTopLevelClass(env, (JCTree) it.next(), treeMakerForToplevel);
                            }
                            this.compileStates.put(env, CompileStates.CompileState.UNLAMBDA);
                        }
                        while (listTranslateTopLevelClass.nonEmpty()) {
                            queue.add(new Pair<>(env, (JCTree.JCClassDecl) listTranslateTopLevelClass.head));
                            listTranslateTopLevelClass = listTranslateTopLevelClass.tail;
                        }
                        return;
                    }
                    if (!this.sourceOutput) {
                        if (shouldStop(CompileStates.CompileState.LOWER)) {
                            return;
                        }
                        List<JCTree> listTranslateTopLevelClass2 = this.lower.translateTopLevelClass(env, env.tree, treeMakerForToplevel);
                        if (listTranslateTopLevelClass2.head != null) {
                            Assert.check(listTranslateTopLevelClass2.tail.isEmpty());
                            queue.add(new Pair<>(env, (JCTree.JCClassDecl) listTranslateTopLevelClass2.head));
                        }
                    }
                } finally {
                    this.log.useSource(javaFileObjectUseSource);
                }
            }
        }
    }

    public void enterDone() {
        this.enterDone = true;
        this.annotate.enterDone();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<JCTree.JCCompilationUnit> enterTrees(List<JCTree.JCCompilationUnit> list) {
        if (!this.taskListener.isEmpty()) {
            Iterator<JCTree.JCCompilationUnit> it = list.iterator();
            while (it.hasNext()) {
                this.taskListener.started(new TaskEvent(TaskEvent.Kind.ENTER, it.next()));
            }
        }
        this.enter.main(list);
        enterDone();
        if (!this.taskListener.isEmpty()) {
            Iterator<JCTree.JCCompilationUnit> it2 = list.iterator();
            while (it2.hasNext()) {
                this.taskListener.finished(new TaskEvent(TaskEvent.Kind.ENTER, it2.next()));
            }
        }
        if (this.sourceOutput) {
            ListBuffer listBuffer = new ListBuffer();
            Iterator<JCTree.JCCompilationUnit> it3 = list.iterator();
            while (it3.hasNext()) {
                for (List list2 = it3.next().defs; list2.nonEmpty(); list2 = list2.tail) {
                    A a = list2.head;
                    if (a instanceof JCTree.JCClassDecl) {
                        listBuffer.append((JCTree.JCClassDecl) a);
                    }
                }
            }
            this.rootClasses = listBuffer.toList();
        }
        Iterator<JCTree.JCCompilationUnit> it4 = list.iterator();
        while (it4.hasNext()) {
            this.inputFiles.add(it4.next().sourcefile);
        }
        return list;
    }

    public int errorCount() {
        this.log.reportOutstandingWarnings();
        Log log = this.log;
        if (log.nerrors == 0 && log.nwarnings > 0 && (this.werrorAny || this.werrorLint.clone().removeAll(this.log.lintWarnings))) {
            this.log.error(CompilerProperties.Errors.WarningsAndWerror);
        }
        return this.log.nerrors;
    }

    public void flow(Env<AttrContext> env, Queue<Env<AttrContext>> queue) {
        CompileStates compileStates = this.compileStates;
        CompileStates.CompileState compileState = CompileStates.CompileState.FLOW;
        if (compileStates.isDone(env, compileState)) {
            queue.add(env);
            return;
        }
        try {
            if (!shouldStop(compileState)) {
                if (this.verboseCompilePolicy) {
                    printNote("[flow " + env.enclClass.sym + "]");
                }
                Log log = this.log;
                JavaFileObject javaFileObject = env.enclClass.sym.sourcefile;
                if (javaFileObject == null) {
                    javaFileObject = env.toplevel.sourcefile;
                }
                JavaFileObject javaFileObjectUseSource = log.useSource(javaFileObject);
                try {
                    this.make.at(0);
                    this.flow.analyzeTree(env, this.make.forToplevel(env.toplevel));
                    this.compileStates.put(env, compileState);
                    if (shouldStop(compileState)) {
                        this.log.useSource(javaFileObjectUseSource);
                        if (this.taskListener.isEmpty()) {
                            return;
                        }
                    } else {
                        this.analyzer.flush(env);
                        queue.add(env);
                        this.log.useSource(javaFileObjectUseSource);
                        if (this.taskListener.isEmpty()) {
                            return;
                        }
                    }
                } catch (Throwable th) {
                    this.log.useSource(javaFileObjectUseSource);
                    throw th;
                }
            } else if (this.taskListener.isEmpty()) {
                return;
            }
            this.taskListener.finished(newAnalyzeTaskEvent(env));
        } catch (Throwable th2) {
            if (!this.taskListener.isEmpty()) {
                this.taskListener.finished(newAnalyzeTaskEvent(env));
            }
            throw th2;
        }
    }

    public JavaFileObject genCode(Env<AttrContext> env, JCTree.JCClassDecl jCClassDecl) throws IOException {
        try {
            if (this.gen.genClass(env, jCClassDecl) && errorCount() == 0) {
                return this.writer.writeClass(jCClassDecl.sym);
            }
            return null;
        } catch (Symbol.CompletionFailure e) {
            this.chk.completionError(jCClassDecl.pos(), e);
            return null;
        } catch (ClassWriter.PoolOverflow unused) {
            this.log.error(jCClassDecl.pos(), CompilerProperties.Errors.LimitPool);
            return null;
        } catch (ClassWriter.StringOverflow e2) {
            this.log.error(jCClassDecl.pos(), CompilerProperties.Errors.LimitStringOverflow(e2.value.substring(0, 20)));
            return null;
        }
    }

    public void generate(Queue<Pair<Env<AttrContext>, JCTree.JCClassDecl>> queue, Queue<JavaFileObject> queue2) {
        JavaFileObject javaFileObjectGenCode;
        if (shouldStop(CompileStates.CompileState.GENERATE)) {
            return;
        }
        for (Pair<Env<AttrContext>, JCTree.JCClassDecl> pair : queue) {
            Env<AttrContext> env = pair.fst;
            JCTree.JCClassDecl jCClassDecl = pair.snd;
            if (this.verboseCompilePolicy) {
                StringBuilder sb = new StringBuilder("[generate ");
                sb.append(this.sourceOutput ? " source" : "code");
                sb.append(" ");
                sb.append(jCClassDecl.sym);
                sb.append("]");
                printNote(sb.toString());
            }
            if (!this.taskListener.isEmpty()) {
                this.taskListener.started(new TaskEvent(TaskEvent.Kind.GENERATE, env.toplevel, jCClassDecl.sym));
            }
            Log log = this.log;
            JavaFileObject javaFileObject = env.enclClass.sym.sourcefile;
            if (javaFileObject == null) {
                javaFileObject = env.toplevel.sourcefile;
            }
            JavaFileObject javaFileObjectUseSource = log.useSource(javaFileObject);
            try {
                try {
                    if (this.sourceOutput) {
                        javaFileObjectGenCode = printSource(env, jCClassDecl);
                    } else {
                        if (this.fileManager.hasLocation(StandardLocation.NATIVE_HEADER_OUTPUT) && this.jniWriter.needsHeader(jCClassDecl.sym)) {
                            this.jniWriter.write(jCClassDecl.sym);
                        }
                        javaFileObjectGenCode = genCode(env, jCClassDecl);
                    }
                    if (queue2 != null && javaFileObjectGenCode != null) {
                        queue2.add(javaFileObjectGenCode);
                    }
                    this.log.useSource(javaFileObjectUseSource);
                    if (!this.taskListener.isEmpty()) {
                        this.taskListener.finished(new TaskEvent(TaskEvent.Kind.GENERATE, env.toplevel, jCClassDecl.sym));
                    }
                } catch (IOException | UncheckedIOException | FileSystemNotFoundException | InvalidPathException | ReadOnlyFileSystemException e) {
                    this.log.error(jCClassDecl.pos(), CompilerProperties.Errors.ClassCantWrite(jCClassDecl.sym, e.getMessage()));
                    this.log.useSource(javaFileObjectUseSource);
                    return;
                }
            } catch (Throwable th) {
                this.log.useSource(javaFileObjectUseSource);
                throw th;
            }
        }
    }

    public Map<JCTree.JCCompilationUnit, Queue<Env<AttrContext>>> groupByFile(Queue<Env<AttrContext>> queue) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Env<AttrContext> env : queue) {
            Queue listBuffer = (Queue) linkedHashMap.get(env.toplevel);
            if (listBuffer == null) {
                listBuffer = new ListBuffer();
                linkedHashMap.put(env.toplevel, listBuffer);
            }
            listBuffer.add(env);
        }
        return linkedHashMap;
    }

    public List<JCTree.JCCompilationUnit> initModules(List<JCTree.JCCompilationUnit> list) {
        this.modules.initModules(list);
        if (list.isEmpty()) {
            enterDone();
        }
        return list;
    }

    public void initProcessAnnotations(Iterable<? extends Processor> iterable, Collection<? extends JavaFileObject> collection, Collection<String> collection2) {
        if (iterable != null && iterable.iterator().hasNext()) {
            this.explicitAnnotationProcessingRequested = true;
        }
        boolean z = false;
        if (this.options.isSet(Option.PROC, Option.LINT_CUSTOM_NONE)) {
            this.processAnnotations = false;
            return;
        }
        JavacProcessingEnvironment javacProcessingEnvironment = this.procEnvImpl;
        if (javacProcessingEnvironment != null) {
            javacProcessingEnvironment.close();
            return;
        }
        JavacProcessingEnvironment javacProcessingEnvironmentInstance = JavacProcessingEnvironment.instance(this.context);
        this.procEnvImpl = javacProcessingEnvironmentInstance;
        javacProcessingEnvironmentInstance.setProcessors(iterable);
        if (this.procEnvImpl.atLeastOneProcessor() && explicitAnnotationProcessingRequested()) {
            z = true;
        }
        this.processAnnotations = z;
        if (z) {
            this.options.put("parameters", "parameters");
            this.reader.saveParameterNames = true;
            this.keepComments = true;
            this.genEndPos = true;
            if (!this.taskListener.isEmpty()) {
                this.taskListener.started(new TaskEvent(TaskEvent.Kind.ANNOTATION_PROCESSING));
            }
            Log log = this.log;
            Objects.requireNonNull(log);
            this.deferredDiagnosticHandler = new Log.DeferredDiagnosticHandler(log);
            this.procEnvImpl.getFiler().setInitialState(collection, collection2);
        }
    }

    public boolean isEnterDone() {
        return this.enterDone;
    }

    public boolean isWerror(Lint.LintCategory lintCategory) {
        return this.werrorAny || this.werrorLint.contains(lintCategory);
    }

    public boolean keepComments() {
        return this.keepComments || this.sourceOutput;
    }

    public void newRound() {
        this.inputFiles.clear();
        this.todo.clear();
    }

    public List<JCTree.JCCompilationUnit> parseFiles(Iterable<JavaFileObject> iterable, boolean z) {
        if (!z && shouldStop(CompileStates.CompileState.PARSE)) {
            return List.nil();
        }
        ListBuffer listBuffer = new ListBuffer();
        HashSet hashSet = new HashSet();
        for (JavaFileObject javaFileObject : iterable) {
            if (!hashSet.contains(javaFileObject)) {
                hashSet.add(javaFileObject);
                listBuffer.append(parse(javaFileObject));
            }
        }
        return listBuffer.toList();
    }

    public void printCount(String str, int i) {
        String str2;
        if (i != 0) {
            if (i == 1) {
                str2 = "count." + str;
            } else {
                str2 = "count." + str + ".plural";
            }
            Log log = this.log;
            Log.WriterKind writerKind = Log.WriterKind.ERROR;
            log.printLines(writerKind, str2, String.valueOf(i));
            this.log.flush(writerKind);
        }
    }

    public void printNote(String str) {
        this.log.printRawLines(Log.WriterKind.NOTICE, str);
    }

    public JavaFileObject printSource(Env<AttrContext> env, JCTree.JCClassDecl jCClassDecl) throws IOException {
        JavaFileObject javaFileForOutput = this.fileManager.getJavaFileForOutput(StandardLocation.CLASS_OUTPUT, jCClassDecl.sym.flatname.toString(), JavaFileObject.Kind.SOURCE, null);
        if (this.inputFiles.contains(javaFileForOutput)) {
            this.log.error(jCClassDecl.pos(), CompilerProperties.Errors.SourceCantOverwriteInputFile(javaFileForOutput));
            return null;
        }
        BufferedWriter bufferedWriter = new BufferedWriter(javaFileForOutput.openWriter());
        try {
            new Pretty(bufferedWriter, true).printUnit(env.toplevel, jCClassDecl);
            if (this.verbose) {
                this.log.printVerbose("wrote.file", javaFileForOutput.getName());
            }
            bufferedWriter.close();
            return javaFileForOutput;
        } catch (Throwable th) {
            try {
                bufferedWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void processAnnotations(List<JCTree.JCCompilationUnit> list, Collection<String> collection) {
        Kinds.Kind kind;
        Kinds.Kind kind2;
        if (shouldStop(CompileStates.CompileState.PROCESS) && this.processAnnotations) {
            reportDeferredDiagnosticAndClearHandler();
            return;
        }
        if (!this.processAnnotations) {
            if (this.options.isSet(Option.PROC, Constants.ATTRNAME_ONLY)) {
                this.log.warning(CompilerProperties.Warnings.ProcProcOnlyRequestedNoProcs);
                this.todo.clear();
            }
            if (!collection.isEmpty()) {
                this.log.error(CompilerProperties.Errors.ProcNoExplicitAnnotationProcessingRequested(collection));
            }
            Assert.checkNull(this.deferredDiagnosticHandler);
            return;
        }
        Assert.checkNonNull(this.deferredDiagnosticHandler);
        try {
            List<Symbol.ClassSymbol> listNil = List.nil();
            List listNil2 = List.nil();
            if (!collection.isEmpty()) {
                if (!explicitAnnotationProcessingRequested()) {
                    this.log.error(CompilerProperties.Errors.ProcNoExplicitAnnotationProcessingRequested(collection));
                    reportDeferredDiagnosticAndClearHandler();
                    return;
                }
                boolean z = false;
                for (String str : collection) {
                    Symbol symbolResolveBinaryNameOrIdent = resolveBinaryNameOrIdent(str);
                    if (symbolResolveBinaryNameOrIdent == null || (((kind = symbolResolveBinaryNameOrIdent.kind) == (kind2 = Kinds.Kind.PCK) && !this.processPcks) || kind == Kinds.Kind.ABSENT_TYP)) {
                        if (symbolResolveBinaryNameOrIdent != this.silentFail) {
                            this.log.error(CompilerProperties.Errors.ProcCantFindClass(str));
                        }
                        z = true;
                    } else {
                        if (kind == kind2) {
                            try {
                                symbolResolveBinaryNameOrIdent.complete();
                            } catch (Symbol.CompletionFailure unused) {
                                this.log.error(CompilerProperties.Errors.ProcCantFindClass(str));
                                z = true;
                            }
                        }
                        boolean zExists = symbolResolveBinaryNameOrIdent.exists();
                        Kinds.Kind kind3 = symbolResolveBinaryNameOrIdent.kind;
                        if (!zExists) {
                            Assert.check(kind3 == kind2);
                            this.log.warning(CompilerProperties.Warnings.ProcPackageDoesNotExist(str));
                            listNil2 = listNil2.prepend((Symbol.PackageSymbol) symbolResolveBinaryNameOrIdent);
                        } else if (kind3 == kind2) {
                            listNil2 = listNil2.prepend((Symbol.PackageSymbol) symbolResolveBinaryNameOrIdent);
                        } else {
                            listNil = listNil.prepend((Symbol.ClassSymbol) symbolResolveBinaryNameOrIdent);
                        }
                    }
                }
                if (z) {
                    reportDeferredDiagnosticAndClearHandler();
                    return;
                }
            }
            try {
                this.annotationProcessingOccurred = this.procEnvImpl.doProcessing(list, listNil, listNil2, this.deferredDiagnosticHandler);
            } finally {
                this.procEnvImpl.close();
            }
        } catch (Symbol.CompletionFailure e) {
            this.log.error(CompilerProperties.Errors.CantAccess(e.sym, e.getDetailValue()));
            reportDeferredDiagnosticAndClearHandler();
        }
    }

    public CharSequence readSource(JavaFileObject javaFileObject) {
        try {
            this.inputFiles.add(javaFileObject);
            return javaFileObject.getCharContent(false);
        } catch (IOException e) {
            this.log.error(CompilerProperties.Errors.ErrorReadingFile(javaFileObject, JavacFileManager.getMessage(e)));
            return null;
        }
    }

    public void readSourceFile(JCTree.JCCompilationUnit jCCompilationUnit, Symbol.ClassSymbol classSymbol) throws Symbol.CompletionFailure {
        if (this.completionFailureName == classSymbol.fullname) {
            throw new Symbol.CompletionFailure(classSymbol, new Supplier() { // from class: oc7
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.b.diagFactory.fragment(CompilerProperties.Fragments.UserSelectedCompletionFailure);
                }
            }, this.dcfh);
        }
        JavaFileObject javaFileObject = classSymbol.classfile;
        JavaFileObject javaFileObjectUseSource = this.log.useSource(javaFileObject);
        if (jCCompilationUnit == null) {
            try {
                try {
                    jCCompilationUnit = parse(javaFileObject, javaFileObject.getCharContent(false));
                } catch (IOException e) {
                    this.log.error(CompilerProperties.Errors.ErrorReadingFile(javaFileObject, JavacFileManager.getMessage(e)));
                    jCCompilationUnit = this.make.TopLevel(List.nil());
                }
                this.log.useSource(javaFileObjectUseSource);
            } catch (Throwable th) {
                this.log.useSource(javaFileObjectUseSource);
                throw th;
            }
        }
        if (!this.taskListener.isEmpty()) {
            this.taskListener.started(new TaskEvent(TaskEvent.Kind.ENTER, jCCompilationUnit));
        }
        if (!this.modules.enter(List.of(jCCompilationUnit), classSymbol)) {
            throw new Symbol.CompletionFailure(classSymbol, new Supplier() { // from class: pc7
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.b.diags.fragment(CompilerProperties.Fragments.CantResolveModules);
                }
            }, this.dcfh);
        }
        this.enter.complete(List.of(jCCompilationUnit), classSymbol);
        if (!this.taskListener.isEmpty()) {
            this.taskListener.finished(new TaskEvent(TaskEvent.Kind.ENTER, jCCompilationUnit));
        }
        if (this.enter.getEnv(classSymbol) == null) {
            JavaFileObject javaFileObject2 = jCCompilationUnit.sourcefile;
            JavaFileObject.Kind kind = JavaFileObject.Kind.SOURCE;
            boolean zIsNameCompatible = javaFileObject2.isNameCompatible("package-info", kind);
            if (jCCompilationUnit.sourcefile.isNameCompatible("module-info", kind)) {
                if (this.enter.getEnv(jCCompilationUnit.modle) == null) {
                    throw new ClassFinder.BadClassFile(classSymbol, javaFileObject, this.diagFactory.fragment(CompilerProperties.Fragments.FileDoesNotContainModule), this.diagFactory, this.dcfh);
                }
            } else {
                if (!zIsNameCompatible) {
                    throw new ClassFinder.BadClassFile(classSymbol, javaFileObject, this.diagFactory.fragment(CompilerProperties.Fragments.FileDoesntContainClass(classSymbol.getQualifiedName())), this.diagFactory, this.dcfh);
                }
                if (this.enter.getEnv(jCCompilationUnit.packge) == null) {
                    throw new ClassFinder.BadClassFile(classSymbol, javaFileObject, this.diagFactory.fragment(CompilerProperties.Fragments.FileDoesNotContainPackage(classSymbol.location())), this.diagFactory, this.dcfh);
                }
            }
        }
        this.implicitSourceFilesRead = true;
    }

    public JCTree.JCClassDecl removeMethodBodies(JCTree.JCClassDecl jCClassDecl) {
        final boolean z = (jCClassDecl.mods.flags & 512) != 0;
        return (JCTree.JCClassDecl) new TreeTranslator(this) { // from class: com.sun.tools.javac.main.JavaCompiler.1MethodBodyRemover
            final /* synthetic */ JavaCompiler this$0;

            {
                this.this$0 = this;
            }

            /* JADX WARN: Code duplicated, block: B:20:0x0054  */
            /* JADX WARN: Code duplicated, block: B:31:0x008a  */
            /* JADX WARN: Code duplicated, block: B:40:0x00b6  */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitClassDef(JCTree.JCClassDecl jCClassDecl2) {
                ListBuffer listBuffer = new ListBuffer();
                for (List list = jCClassDecl2.defs; list.tail != null; list = list.tail) {
                    JCTree jCTree = (JCTree) list.head;
                    int i = AnonymousClass2.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()];
                    if (i != 1) {
                        if (i != 2) {
                            if (i == 3) {
                                if (z) {
                                    listBuffer.append(jCTree);
                                } else {
                                    JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) jCTree;
                                    long j = jCVariableDecl.mods.flags;
                                    if ((5 & j) != 0 || ((j & 2) == 0 && jCVariableDecl.sym.packge().getQualifiedName() == this.this$0.names.java_lang)) {
                                        listBuffer.append(jCTree);
                                    }
                                }
                            }
                        } else if (z) {
                            listBuffer.append(jCTree);
                        } else {
                            JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) jCTree;
                            long j2 = jCMethodDecl.mods.flags;
                            if ((5 & j2) == 0) {
                                Symbol.MethodSymbol methodSymbol = jCMethodDecl.sym;
                                if (methodSymbol.name == this.this$0.names.init || ((j2 & 2) == 0 && methodSymbol.packge().getQualifiedName() == this.this$0.names.java_lang)) {
                                    listBuffer.append(jCTree);
                                }
                            } else {
                                listBuffer.append(jCTree);
                            }
                        }
                    } else if (z) {
                        listBuffer.append(jCTree);
                    } else {
                        JCTree.JCClassDecl jCClassDecl3 = (JCTree.JCClassDecl) jCTree;
                        long j3 = jCClassDecl3.mods.flags;
                        if ((5 & j3) != 0 || ((j3 & 2) == 0 && jCClassDecl3.sym.packge().getQualifiedName() == this.this$0.names.java_lang)) {
                            listBuffer.append(jCTree);
                        }
                    }
                }
                jCClassDecl2.defs = listBuffer.toList();
                super.visitClassDef(jCClassDecl2);
            }

            @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
                jCMethodDecl.mods.flags &= -33;
                Iterator<JCTree.JCVariableDecl> it = jCMethodDecl.params.iterator();
                while (it.hasNext()) {
                    it.next().mods.flags &= -17;
                }
                jCMethodDecl.body = null;
                super.visitMethodDef(jCMethodDecl);
            }

            @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
                JCTree.JCExpression jCExpression = jCVariableDecl.init;
                if (jCExpression != null && jCExpression.type.constValue() == null) {
                    jCVariableDecl.init = null;
                }
                super.visitVarDef(jCVariableDecl);
            }
        }.translate(jCClassDecl);
    }

    public void reportDeferredDiagnosticAndClearHandler() {
        if (this.deferredDiagnosticHandler != null) {
            final ToIntFunction toIntFunction = new ToIntFunction() { // from class: qc7
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return JavaCompiler.b((JCDiagnostic) obj);
                }
            };
            this.deferredDiagnosticHandler.reportDeferredDiagnostics(new Comparator() { // from class: rc7
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return JavaCompiler.d(toIntFunction, (JCDiagnostic) obj, (JCDiagnostic) obj2);
                }
            });
            this.log.popDiagnosticHandler(this.deferredDiagnosticHandler);
            this.deferredDiagnosticHandler = null;
        }
    }

    public void reportDeferredDiagnostics() {
        if (errorCount() == 0 && this.annotationProcessingOccurred && this.implicitSourceFilesRead && this.implicitSourcePolicy == ImplicitSourcePolicy.UNSET) {
            boolean zExplicitAnnotationProcessingRequested = explicitAnnotationProcessingRequested();
            Log log = this.log;
            if (zExplicitAnnotationProcessingRequested) {
                log.warning(CompilerProperties.Warnings.ProcUseImplicit);
            } else {
                log.warning(CompilerProperties.Warnings.ProcUseProcOrImplicit);
            }
        }
        this.log.reportOutstandingWarnings();
        this.log.reportOutstandingNotes();
        Log log2 = this.log;
        if (log2.compressedOutput) {
            log2.note(CompilerProperties.Notes.CompressedDiags);
        }
    }

    public Symbol resolveBinaryNameOrIdent(String str) {
        Symbol.ModuleSymbol defaultModule;
        int iIndexOf = str.indexOf(47);
        if (iIndexOf == -1) {
            defaultModule = this.modules.getDefaultModule();
        } else {
            if (!Source.Feature.MODULES.allowedInSource(this.source)) {
                this.log.error(CompilerProperties.Errors.InvalidModuleSpecifier(str));
                return this.silentFail;
            }
            Symbol.ModuleSymbol moduleSymbolFindModule = this.moduleFinder.findModule(this.names.fromString(str.substring(0, iIndexOf)));
            str = str.substring(iIndexOf + 1);
            defaultModule = moduleSymbolFindModule;
        }
        return resolveBinaryNameOrIdent(defaultModule, str);
    }

    public Symbol resolveIdent(Symbol.ModuleSymbol moduleSymbol, String str) {
        Symbol symbolAttribIdent;
        if (str.equals("")) {
            return this.syms.errSymbol;
        }
        JCTree.JCExpression jCExpressionIdent = null;
        JavaFileObject javaFileObjectUseSource = this.log.useSource(null);
        try {
            for (String str2 : str.split("\\.", -1)) {
                if (!SourceVersion.isIdentifier(str2)) {
                    symbolAttribIdent = this.syms.errSymbol;
                    return symbolAttribIdent;
                }
                TreeMaker treeMaker = this.make;
                jCExpressionIdent = jCExpressionIdent == null ? treeMaker.Ident(this.names.fromString(str2)) : treeMaker.Select(jCExpressionIdent, this.names.fromString(str2));
            }
            JCTree.JCCompilationUnit jCCompilationUnitTopLevel = this.make.TopLevel(List.nil());
            jCCompilationUnitTopLevel.modle = moduleSymbol;
            jCCompilationUnitTopLevel.packge = moduleSymbol.unnamedPackage;
            symbolAttribIdent = this.attr.attribIdent(jCExpressionIdent, jCCompilationUnitTopLevel);
            return symbolAttribIdent;
        } finally {
            this.log.useSource(javaFileObjectUseSource);
        }
    }

    public void setDeferredDiagnosticHandler(Log.DeferredDiagnosticHandler deferredDiagnosticHandler) {
        this.deferredDiagnosticHandler = deferredDiagnosticHandler;
    }

    public boolean shouldStop(CompileStates.CompileState compileState) {
        return compileState.isAfter((errorCount() > 0 || unrecoverableError()) ? this.shouldStopPolicyIfError : this.shouldStopPolicyIfNoError);
    }

    public final <T> Queue<T> stopIfError(CompileStates.CompileState compileState, Queue<T> queue) {
        return shouldStop(compileState) ? new ListBuffer() : queue;
    }

    public void warn(Env<AttrContext> env, Queue<Env<AttrContext>> queue) {
        CompileStates compileStates = this.compileStates;
        CompileStates.CompileState compileState = CompileStates.CompileState.WARN;
        if (compileStates.isDone(env, compileState)) {
            queue.add(env);
            return;
        }
        if (shouldStop(compileState)) {
            return;
        }
        if (this.verboseCompilePolicy) {
            printNote("[warn " + env.enclClass.sym + "]");
        }
        Log log = this.log;
        JavaFileObject javaFileObject = env.enclClass.sym.sourcefile;
        if (javaFileObject == null) {
            javaFileObject = env.toplevel.sourcefile;
        }
        JavaFileObject javaFileObjectUseSource = log.useSource(javaFileObject);
        try {
            this.warningAnalyzer.analyzeTree(env);
            this.compileStates.put(env, compileState);
            queue.add(env);
        } finally {
            this.log.useSource(javaFileObjectUseSource);
        }
    }

    public int warningCount() {
        return this.log.nwarnings;
    }

    public final <T> List<T> stopIfError(CompileStates.CompileState compileState, List<T> list) {
        return shouldStop(compileState) ? List.nil() : list;
    }

    public static String version() {
        return version("release");
    }

    public List<JCTree.JCCompilationUnit> parseFiles(Iterable<JavaFileObject> iterable) {
        return InitialFileParser.instance(this.context).parse(iterable);
    }

    public Symbol resolveBinaryNameOrIdent(Symbol.ModuleSymbol moduleSymbol, String str) {
        try {
            return this.finder.loadClass(moduleSymbol, this.names.fromString(str.replace(PsuedoNames.PSEUDONAME_ROOT, Constants.ATTRVAL_THIS)));
        } catch (Symbol.CompletionFailure unused) {
            return resolveIdent(moduleSymbol, str);
        }
    }

    public boolean explicitAnnotationProcessingRequested() {
        return this.explicitAnnotationProcessingRequested || explicitAnnotationProcessingRequested(this.options, this.fileManager);
    }

    public Queue<Env<AttrContext>> warn(Env<AttrContext> env) {
        ListBuffer listBuffer = new ListBuffer();
        warn(env, listBuffer);
        return stopIfError(CompileStates.CompileState.WARN, listBuffer);
    }

    public Queue<Env<AttrContext>> warn(Queue<Env<AttrContext>> queue) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Env<AttrContext>> it = queue.iterator();
        while (it.hasNext()) {
            warn(it.next(), listBuffer);
        }
        return stopIfError(CompileStates.CompileState.WARN, listBuffer);
    }

    public Queue<Env<AttrContext>> attribute(Queue<Env<AttrContext>> queue) {
        ListBuffer listBuffer = new ListBuffer();
        while (!queue.isEmpty()) {
            listBuffer.append(attribute(queue.remove()));
        }
        return stopIfError(CompileStates.CompileState.ATTR, listBuffer);
    }

    public JCTree.JCCompilationUnit parse(JavaFileObject javaFileObject, CharSequence charSequence) {
        return parse(javaFileObject, charSequence, false);
    }

    @Deprecated
    public JCTree.JCCompilationUnit parse(String str) {
        return parse(((JavacFileManager) this.fileManager).getJavaFileObjectsFromStrings(List.of(str)).iterator().next());
    }

    public JCTree.JCCompilationUnit parse(JavaFileObject javaFileObject) {
        JavaFileObject javaFileObjectUseSource = this.log.useSource(javaFileObject);
        try {
            JCTree.JCCompilationUnit jCCompilationUnit = parse(javaFileObject, readSource(javaFileObject));
            EndPosTable endPosTable = jCCompilationUnit.endPositions;
            if (endPosTable != null) {
                this.log.setEndPosTable(javaFileObject, endPosTable);
            }
            return jCCompilationUnit;
        } finally {
            this.log.useSource(javaFileObjectUseSource);
        }
    }

    public Queue<Env<AttrContext>> flow(Env<AttrContext> env) {
        ListBuffer listBuffer = new ListBuffer();
        flow(env, listBuffer);
        return stopIfError(CompileStates.CompileState.FLOW, listBuffer);
    }

    public Queue<Env<AttrContext>> flow(Queue<Env<AttrContext>> queue) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Env<AttrContext>> it = queue.iterator();
        while (it.hasNext()) {
            flow(it.next(), listBuffer);
        }
        return stopIfError(CompileStates.CompileState.FLOW, listBuffer);
    }

    public void generate(Queue<Pair<Env<AttrContext>, JCTree.JCClassDecl>> queue) {
        generate(queue, null);
    }

    public void processAnnotations(List<JCTree.JCCompilationUnit> list) {
        processAnnotations(list, List.nil());
    }

    private void readSourceFile(Symbol.ClassSymbol classSymbol) throws Symbol.CompletionFailure {
        readSourceFile(null, classSymbol);
    }

    public Queue<Pair<Env<AttrContext>, JCTree.JCClassDecl>> desugar(Queue<Env<AttrContext>> queue) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Env<AttrContext>> it = queue.iterator();
        while (it.hasNext()) {
            desugar(it.next(), listBuffer);
        }
        return stopIfError(CompileStates.CompileState.FLOW, listBuffer);
    }

    public void compile(List<JavaFileObject> list) throws Throwable {
        compile(list, List.nil(), null, List.nil());
    }
}

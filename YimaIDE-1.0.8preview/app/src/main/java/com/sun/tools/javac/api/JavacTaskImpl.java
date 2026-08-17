package com.sun.tools.javac.api;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.tools.javac.api.JavacTaskImpl;
import com.sun.tools.javac.code.DeferredCompletionFailureHandler;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.comp.Attr;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.file.BaseFileManager;
import com.sun.tools.javac.main.Arguments;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.main.Main;
import com.sun.tools.javac.parser.ParserFactory;
import com.sun.tools.javac.processing.AnnotationProcessingError;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.ClientCodeException;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.FatalError;
import com.sun.tools.javac.util.JavacMessages;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.PropagatedException;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.processing.Processor;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacTaskImpl extends BasicJavacTask {
    private ListBuffer<String> addModules;
    private final Arguments args;
    private JavaCompiler compiler;
    private DeferredCompletionFailureHandler dcfh;
    private JavaFileManager fileManager;
    private ListBuffer<Env<AttrContext>> genList;
    private Locale locale;
    private Map<JavaFileObject, JCTree.JCCompilationUnit> notYetEntered;
    private boolean parsed;
    private Iterable<? extends Processor> processors;
    private final AtomicBoolean used;

    /* JADX INFO: renamed from: com.sun.tools.javac.api.JavacTaskImpl$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr;
            try {
                iArr[JCTree.Tag.CLASSDEF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MODULEDEF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PACKAGEDEF.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public abstract class Filter {
        public Filter() {
        }

        public abstract void process(Env<AttrContext> env);

        public void run(Queue<Env<AttrContext>> queue, Iterable<? extends Element> iterable) {
            Object objOutermostClass;
            HashSet hashSet = new HashSet();
            Iterator<? extends Element> it = iterable.iterator();
            while (it.hasNext()) {
                hashSet.add(it.next());
            }
            ListBuffer listBuffer = new ListBuffer();
            while (queue.peek() != null) {
                Env<AttrContext> envRemove = queue.remove();
                boolean zHasTag = envRemove.tree.hasTag(JCTree.Tag.MODULEDEF);
                JCTree jCTree = envRemove.tree;
                if (zHasTag) {
                    objOutermostClass = ((JCTree.JCModuleDecl) jCTree).sym;
                } else if (jCTree.hasTag(JCTree.Tag.PACKAGEDEF)) {
                    objOutermostClass = envRemove.toplevel.packge;
                } else {
                    Symbol.ClassSymbol classSymbol = envRemove.enclClass.sym;
                    objOutermostClass = classSymbol != null ? classSymbol.outermostClass() : null;
                }
                if (objOutermostClass == null || !hashSet.contains(objOutermostClass)) {
                    listBuffer = listBuffer.append(envRemove);
                } else {
                    process(envRemove);
                }
            }
            queue.addAll(listBuffer);
        }
    }

    public JavacTaskImpl(Context context) {
        super(context, true);
        this.used = new AtomicBoolean();
        this.addModules = new ListBuffer<>();
        this.parsed = false;
        this.args = Arguments.instance(context);
        this.fileManager = (JavaFileManager) context.get(JavaFileManager.class);
        DeferredCompletionFailureHandler deferredCompletionFailureHandlerInstance = DeferredCompletionFailureHandler.instance(context);
        this.dcfh = deferredCompletionFailureHandlerInstance;
        deferredCompletionFailureHandlerInstance.setHandler(deferredCompletionFailureHandlerInstance.userCodeHandler);
    }

    public static /* synthetic */ Main.Result b(JavacTaskImpl javacTaskImpl) {
        javacTaskImpl.prepareCompiler(false);
        if (javacTaskImpl.compiler.errorCount() > 0) {
            return Main.Result.ERROR;
        }
        javacTaskImpl.compiler.compile(javacTaskImpl.args.getFileObjects(), javacTaskImpl.args.getClassNames(), javacTaskImpl.processors, javacTaskImpl.addModules);
        return javacTaskImpl.compiler.errorCount() > 0 ? Main.Result.ERROR : Main.Result.OK;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFlowResults(Queue<Env<AttrContext>> queue, ListBuffer<Element> listBuffer) {
        Symbol.PackageSymbol packageSymbol;
        for (Env<AttrContext> env : queue) {
            int i = AnonymousClass3.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[env.tree.getTag().ordinal()];
            if (i == 1) {
                Symbol.ClassSymbol classSymbol = ((JCTree.JCClassDecl) env.tree).sym;
                if (classSymbol != null) {
                    listBuffer.append(classSymbol);
                }
            } else if (i == 2) {
                Symbol.ModuleSymbol moduleSymbol = ((JCTree.JCModuleDecl) env.tree).sym;
                if (moduleSymbol != null) {
                    listBuffer.append(moduleSymbol);
                }
            } else if (i == 3 && (packageSymbol = env.toplevel.packge) != null) {
                listBuffer.append(packageSymbol);
            }
        }
        this.genList.addAll(queue);
    }

    private <T> Pair<T, Throwable> invocationHelper(Callable<T> callable) {
        Pair<T, Throwable> pair;
        DeferredCompletionFailureHandler deferredCompletionFailureHandler = this.dcfh;
        DeferredCompletionFailureHandler.Handler handler = deferredCompletionFailureHandler.setHandler(deferredCompletionFailureHandler.javacCodeHandler);
        try {
            try {
                try {
                    try {
                        Pair<T, Throwable> pair2 = new Pair<>(callable.call(), null);
                        this.dcfh.setHandler(handler);
                        return pair2;
                    } catch (Error | Exception e) {
                        JavaCompiler javaCompiler = this.compiler;
                        if (javaCompiler == null || javaCompiler.errorCount() == 0 || Options.instance(this.context).isSet("dev")) {
                            Log logInstance = Log.instance(this.context);
                            logInstance.printLines(Log.PrefixKind.JAVAC, "msg.bug", JavaCompiler.version());
                            e.printStackTrace(logInstance.getWriter(Log.WriterKind.NOTICE));
                        }
                        pair = new Pair<>(null, e);
                        this.dcfh.setHandler(handler);
                        return pair;
                    }
                } catch (FatalError e2) {
                    Log logInstance2 = Log.instance(this.context);
                    Options optionsInstance = Options.instance(this.context);
                    logInstance2.printRawLines(e2.getMessage());
                    if (e2.getCause() != null && optionsInstance.isSet("dev")) {
                        e2.getCause().printStackTrace(logInstance2.getWriter(Log.WriterKind.NOTICE));
                    }
                    pair = new Pair<>(null, e2);
                    this.dcfh.setHandler(handler);
                    return pair;
                } catch (IllegalStateException e3) {
                    throw e3;
                }
            } catch (AnnotationProcessingError | ClientCodeException e4) {
                throw new RuntimeException(e4.getCause());
            } catch (PropagatedException e5) {
                throw e5.getCause();
            }
        } catch (Throwable th) {
            this.dcfh.setHandler(handler);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Iterable<? extends CompilationUnitTree> parseInternal() {
        Log log;
        Log log2;
        try {
            prepareCompiler(true);
            List<JCTree.JCCompilationUnit> files = this.compiler.parseFiles(this.args.getFileObjects());
            for (JCTree.JCCompilationUnit jCCompilationUnit : files) {
                JavaFileObject sourceFile = jCCompilationUnit.getSourceFile();
                if (this.notYetEntered.containsKey(sourceFile)) {
                    this.notYetEntered.put(sourceFile, jCCompilationUnit);
                }
            }
            this.parsed = true;
            JavaCompiler javaCompiler = this.compiler;
            if (javaCompiler != null && (log2 = javaCompiler.log) != null) {
                log2.flush();
            }
            return files;
        } catch (Throwable th) {
            this.parsed = true;
            JavaCompiler javaCompiler2 = this.compiler;
            if (javaCompiler2 != null && (log = javaCompiler2.log) != null) {
                log.flush();
            }
            throw th;
        }
    }

    private void prepareCompiler(boolean z) {
        if (this.used.getAndSet(true)) {
            if (this.compiler == null) {
                throw new PropagatedException(new IllegalStateException());
            }
            return;
        }
        this.args.validate();
        this.context.put((Class<Locale>) Locale.class, this.locale);
        JavacMessages javacMessages = (JavacMessages) this.context.get(JavacMessages.messagesKey);
        if (javacMessages != null && !javacMessages.getCurrentLocale().equals(this.locale)) {
            javacMessages.setCurrentLocale(this.locale);
        }
        initPlugins(this.args.getPluginOpts());
        initDocLint(this.args.getDocLintOpts());
        JavaCompiler javaCompilerInstance = JavaCompiler.instance(this.context);
        this.compiler = javaCompilerInstance;
        javaCompilerInstance.keepComments = true;
        javaCompilerInstance.genEndPos = true;
        this.notYetEntered = new HashMap();
        if (z) {
            this.compiler.initProcessAnnotations(this.processors, this.args.getFileObjects(), this.args.getClassNames());
            Iterator<JavaFileObject> it = this.args.getFileObjects().iterator();
            while (it.hasNext()) {
                this.notYetEntered.put(it.next(), null);
            }
            this.genList = new ListBuffer<>();
        }
    }

    @Override // com.sun.tools.javac.api.BasicJavacTask, javax.tools.JavaCompiler.CompilationTask
    public void addModules(Iterable<String> iterable) {
        Objects.requireNonNull(iterable);
        if (this.used.get()) {
            g33.a();
            return;
        }
        for (String str : iterable) {
            Objects.requireNonNull(str);
            this.addModules.add(str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v5, types: [com.sun.tools.javac.util.Log] */
    public Iterable<? extends Element> analyze(Iterable<? extends Element> iterable) {
        enter(null);
        final ListBuffer listBuffer = new ListBuffer();
        try {
            if (iterable == null) {
                JavaCompiler javaCompiler = this.compiler;
                handleFlowResults(javaCompiler.warn(javaCompiler.flow(javaCompiler.attribute(javaCompiler.todo))), listBuffer);
            } else {
                new Filter(this) { // from class: com.sun.tools.javac.api.JavacTaskImpl.1
                    final /* synthetic */ JavacTaskImpl this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super();
                        this.this$0 = this;
                    }

                    @Override // com.sun.tools.javac.api.JavacTaskImpl.Filter
                    public void process(Env<AttrContext> env) {
                        JavacTaskImpl javacTaskImpl = this.this$0;
                        javacTaskImpl.handleFlowResults(javacTaskImpl.compiler.warn(this.this$0.compiler.flow(this.this$0.compiler.attribute(env))), listBuffer);
                    }
                }.run(this.compiler.todo, iterable);
            }
            this.compiler.log.reportOutstandingWarnings();
            this = this.compiler.log;
            this.flush();
            return listBuffer;
        } catch (Throwable th) {
            this.compiler.log.reportOutstandingWarnings();
            this.compiler.log.flush();
            throw th;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.tools.javac.api.BasicJavacTask, javax.tools.JavaCompiler.CompilationTask, java.util.concurrent.Callable
    public Boolean call() {
        return Boolean.valueOf(doCall().isOK());
    }

    public void cleanup() {
        JavaCompiler javaCompiler = this.compiler;
        if (javaCompiler != null) {
            javaCompiler.close();
        }
        JavaFileManager javaFileManager = this.fileManager;
        if ((javaFileManager instanceof BaseFileManager) && ((BaseFileManager) javaFileManager).autoClose) {
            try {
                javaFileManager.close();
            } catch (IOException unused) {
            }
        }
        this.compiler = null;
        this.context = null;
        this.notYetEntered = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Main.Result doCall() {
        Main.Result result;
        try {
            try {
                Pair pairInvocationHelper = invocationHelper(new Callable() { // from class: kn7
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return JavacTaskImpl.b(this.a);
                    }
                });
                B b = pairInvocationHelper.snd;
                if (b == 0) {
                    result = (Main.Result) pairInvocationHelper.fst;
                } else {
                    result = b instanceof FatalError ? Main.Result.SYSERR : Main.Result.ABNORMAL;
                }
                return result;
            } finally {
                cleanup();
            }
        } catch (ClientCodeException e) {
            rc6.a(e.getCause());
            return null;
        }
    }

    public void ensureEntered() {
        this.args.allowEmpty();
        enter(null);
    }

    public Iterable<? extends Element> enter(Iterable<? extends CompilationUnitTree> iterable) {
        Symbol.ModuleSymbol moduleSymbol;
        Map<JavaFileObject, JCTree.JCCompilationUnit> map;
        if (iterable == null && (map = this.notYetEntered) != null && map.isEmpty()) {
            return List.nil();
        }
        boolean z = this.compiler != null;
        prepareCompiler(true);
        ListBuffer listBuffer = null;
        if (iterable != null) {
            ListBuffer listBuffer2 = null;
            for (CompilationUnitTree compilationUnitTree : iterable) {
                if (!(compilationUnitTree instanceof JCTree.JCCompilationUnit)) {
                    b6c.a(compilationUnitTree);
                    return null;
                }
                JCTree.JCCompilationUnit jCCompilationUnit = (JCTree.JCCompilationUnit) compilationUnitTree;
                if (listBuffer2 == null) {
                    listBuffer2 = new ListBuffer();
                }
                listBuffer2.append(jCCompilationUnit);
                this.notYetEntered.remove(compilationUnitTree.getSourceFile());
            }
            listBuffer = listBuffer2;
        } else if (this.notYetEntered.size() > 0) {
            if (!this.parsed) {
                parseInternal();
            }
            Iterator<JavaFileObject> it = this.args.getFileObjects().iterator();
            while (it.hasNext()) {
                JCTree.JCCompilationUnit jCCompilationUnitRemove = this.notYetEntered.remove(it.next());
                if (jCCompilationUnitRemove != null) {
                    if (listBuffer == null) {
                        listBuffer = new ListBuffer();
                    }
                    listBuffer.append(jCCompilationUnitRemove);
                }
            }
            this.notYetEntered.clear();
        }
        if (listBuffer == null) {
            if (iterable == null && !z) {
                this.compiler.initModules(List.nil());
            }
            return List.nil();
        }
        try {
            List<JCTree.JCCompilationUnit> listEnterTrees = this.compiler.enterTrees(this.compiler.initModules(listBuffer.toList()));
            if (this.notYetEntered.isEmpty()) {
                this.compiler.processAnnotations(listEnterTrees);
            }
            ListBuffer listBuffer3 = new ListBuffer();
            for (JCTree.JCCompilationUnit jCCompilationUnit2 : listEnterTrees) {
                if (jCCompilationUnit2.sourcefile.isNameCompatible("package-info", JavaFileObject.Kind.SOURCE)) {
                    listBuffer3.append(jCCompilationUnit2.packge);
                } else {
                    for (JCTree jCTree : jCCompilationUnit2.defs) {
                        if (jCTree.hasTag(JCTree.Tag.CLASSDEF)) {
                            Symbol.ClassSymbol classSymbol = ((JCTree.JCClassDecl) jCTree).sym;
                            if (classSymbol != null) {
                                listBuffer3.append(classSymbol);
                            }
                        } else if (jCTree.hasTag(JCTree.Tag.MODULEDEF) && (moduleSymbol = ((JCTree.JCModuleDecl) jCTree).sym) != null) {
                            listBuffer3.append(moduleSymbol);
                        }
                    }
                }
            }
            return listBuffer3.toList();
        } finally {
            this.compiler.log.flush();
        }
    }

    public Iterable<? extends JavaFileObject> generate(Iterable<? extends Element> iterable) {
        final ListBuffer listBuffer = new ListBuffer();
        try {
            analyze(null);
            if (iterable == null) {
                JavaCompiler javaCompiler = this.compiler;
                javaCompiler.generate(javaCompiler.desugar(this.genList), listBuffer);
                this.genList.clear();
            } else {
                new Filter(this) { // from class: com.sun.tools.javac.api.JavacTaskImpl.2
                    final /* synthetic */ JavacTaskImpl this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super();
                        this.this$0 = this;
                    }

                    @Override // com.sun.tools.javac.api.JavacTaskImpl.Filter
                    public void process(Env<AttrContext> env) {
                        this.this$0.compiler.generate(this.this$0.compiler.desugar(ListBuffer.of(env)), listBuffer);
                    }
                }.run(this.genList, iterable);
            }
            if (this.genList.isEmpty()) {
                this.compiler.reportDeferredDiagnostics();
                cleanup();
            }
            return listBuffer;
        } finally {
            JavaCompiler javaCompiler2 = this.compiler;
            if (javaCompiler2 != null) {
                javaCompiler2.log.reportOutstandingWarnings();
                this.compiler.log.flush();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.api.BasicJavacTask, com.sun.source.util.JavacTask
    public Iterable<? extends CompilationUnitTree> parse() {
        Pair pairInvocationHelper = invocationHelper(new Callable() { // from class: hn7
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.a.parseInternal();
            }
        });
        B b = pairInvocationHelper.snd;
        if (b == 0) {
            return (Iterable) pairInvocationHelper.fst;
        }
        e7f.a((Throwable) b);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Type parseType(String str, TypeElement typeElement) {
        if (str == null || str.equals("")) {
            j2d.a();
            return null;
        }
        JavaCompiler javaCompilerInstance = JavaCompiler.instance(this.context);
        this.compiler = javaCompilerInstance;
        JavaFileObject javaFileObjectUseSource = javaCompilerInstance.log.useSource(null);
        ParserFactory parserFactoryInstance = ParserFactory.instance(this.context);
        try {
            return Attr.instance(this.context).attribType(parserFactoryInstance.newParser(CharBuffer.wrap(str.concat("\u0000").toCharArray(), 0, str.length()), false, false, false).parseType(), (Symbol.TypeSymbol) typeElement);
        } finally {
            this.compiler.log.useSource(javaFileObjectUseSource);
        }
    }

    @Override // com.sun.tools.javac.api.BasicJavacTask, javax.tools.JavaCompiler.CompilationTask
    public void setLocale(Locale locale) {
        if (this.used.get()) {
            g33.a();
        } else {
            this.locale = locale;
        }
    }

    @Override // com.sun.tools.javac.api.BasicJavacTask, javax.tools.JavaCompiler.CompilationTask
    public void setProcessors(Iterable<? extends Processor> iterable) {
        Objects.requireNonNull(iterable);
        if (this.used.get()) {
            g33.a();
        } else {
            this.processors = iterable;
        }
    }

    public <T> String toString(Iterable<T> iterable, String str) {
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        for (T t : iterable) {
            sb.append(str2);
            sb.append(t.toString());
            str2 = str;
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.api.BasicJavacTask, com.sun.source.util.JavacTask
    public Iterable<? extends Element> analyze() {
        Pair pairInvocationHelper = invocationHelper(new Callable() { // from class: jn7
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.a.analyze(null);
            }
        });
        B b = pairInvocationHelper.snd;
        if (b == 0) {
            return (Iterable) pairInvocationHelper.fst;
        }
        e7f.a((Throwable) b);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.api.BasicJavacTask, com.sun.source.util.JavacTask
    public Iterable<? extends JavaFileObject> generate() {
        Pair pairInvocationHelper = invocationHelper(new Callable() { // from class: in7
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.a.generate(null);
            }
        });
        B b = pairInvocationHelper.snd;
        if (b == 0) {
            return (Iterable) pairInvocationHelper.fst;
        }
        e7f.a((Throwable) b);
        return null;
    }

    public Iterable<? extends Element> enter() {
        return enter(null);
    }
}

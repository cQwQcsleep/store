package com.sun.tools.javac.processing;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.source.util.TaskEvent;
import com.sun.tools.javac.api.MultiTaskListener;
import com.sun.tools.javac.code.ClassFinder;
import com.sun.tools.javac.code.DeferredCompletionFailureHandler;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Annotate;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Check;
import com.sun.tools.javac.comp.CompileStates;
import com.sun.tools.javac.comp.Enter;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.comp.Modules;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.platform.PlatformDescription;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Abort;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.ClientCodeException;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.Iterators;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.JavacMessages;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.MatchingUtils;
import com.sun.tools.javac.util.ModuleHelper;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementScanner14;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import nbjavac.ModuleWrapper;
import nbjavac.ServiceLoaderWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacProcessingEnvironment implements ProcessingEnvironment, Closeable {
    public static final Pattern noMatches = Pattern.compile("(\\P{all})+");
    private final Annotate annotate;
    private final Check chk;
    private final JavaCompiler compiler;
    private final Context context;
    private final DeferredCompletionFailureHandler dcfh;
    JCDiagnostic.Factory diags;
    private DiscoveredProcessors discoveredProcs;
    private final JavacElements elementUtils;
    private final Enter enter;
    private final boolean fatalErrors;
    private final JavaFileManager fileManager;
    private final JavacFiler filer;
    private final Symbol.Completer initialCompleter;
    final Log log;
    private final JavacMessager messager;
    private JavacMessages messages;
    private final Modules modules;
    private final Names names;
    private final Options options;
    private final Set<String> platformAnnotations;
    private final Preview preview;
    private final boolean printProcessorInfo;
    private final boolean printRounds;
    private ClassLoader processorClassLoader;
    private final Map<String, String> processorOptions;
    private ServiceLoader<Processor> serviceLoader;
    private final boolean showResolveErrors;
    Source source;
    private final Symtab symtab;
    private MultiTaskListener taskListener;
    private final JavacTypes typeUtils;
    private final Types types;
    private final Set<String> unmatchedProcessorOptions;
    private final boolean verbose;
    private final boolean werror;
    private Set<Symbol.PackageSymbol> specifiedPackages = Collections.EMPTY_SET;
    private final TreeScanner treeCleaner = new AnonymousClass1();

    /* JADX INFO: renamed from: com.sun.tools.javac.processing.JavacProcessingEnvironment$1, reason: invalid class name */
    public class AnonymousClass1 extends TreeScanner {
        JCTree.JCCompilationUnit topLevel;

        public AnonymousClass1() {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            super.scan(jCTree);
            if (jCTree != null) {
                jCTree.type = null;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
            jCAnnotation.attribute = null;
            super.visitAnnotation(jCAnnotation);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
            jCAssignOp.operator = null;
            super.visitAssignop(jCAssignOp);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBinary(JCTree.JCBinary jCBinary) {
            jCBinary.operator = null;
            super.visitBinary(jCBinary);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            super.visitClassDef(jCClassDecl);
            List listNil = List.nil();
            List list = jCClassDecl.defs;
            while (list.nonEmpty() && !((JCTree) list.head).hasTag(JCTree.Tag.METHODDEF)) {
                listNil = listNil.prepend((JCTree) list.head);
                list = list.tail;
            }
            if (list.nonEmpty() && (((JCTree.JCMethodDecl) list.head).mods.flags & Flags.GENERATEDCONSTR) != 0) {
                List list2 = list.tail;
                while (listNil.nonEmpty()) {
                    List listPrepend = list2.prepend((JCTree) listNil.head);
                    listNil = listNil.tail;
                    list2 = listPrepend;
                }
                jCClassDecl.defs = list2;
            }
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            if (classSymbol != null) {
                classSymbol.completer = JavacProcessingEnvironment.this.new ImplicitCompleter(this.topLevel);
                Iterator<? extends Symbol.RecordComponent> it = jCClassDecl.sym.getRecordComponents().iterator();
                while (it.hasNext()) {
                    it.next().getOriginalAnnos().forEach(new Consumer() { // from class: com.sun.tools.javac.processing.a
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            this.b.visitAnnotation((JCTree.JCAnnotation) obj);
                        }
                    });
                }
                jCClassDecl.sym.clearPermittedSubclasses();
            }
            jCClassDecl.sym = null;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIdent(JCTree.JCIdent jCIdent) {
            jCIdent.sym = null;
            super.visitIdent(jCIdent);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            Symbol.MethodSymbol methodSymbol;
            JCTree.JCBlock jCBlock;
            if (TreeInfo.isConstructor(jCMethodDecl) && (methodSymbol = jCMethodDecl.sym) != null && methodSymbol.owner.isEnum() && (jCBlock = jCMethodDecl.body) != null && jCBlock.stats.nonEmpty() && TreeInfo.isSuperCall(jCMethodDecl.body.stats.head)) {
                JCTree.JCBlock jCBlock2 = jCMethodDecl.body;
                List<JCTree.JCStatement> list = jCBlock2.stats;
                if (list.head.pos == jCBlock2.pos) {
                    jCBlock2.stats = list.tail;
                }
            }
            jCMethodDecl.sym = null;
            super.visitMethodDef(jCMethodDecl);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            jCNewClass.constructor = null;
            super.visitNewClass(jCNewClass);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
            jCFieldAccess.sym = null;
            super.visitSelect(jCFieldAccess);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTopLevel(final JCTree.JCCompilationUnit jCCompilationUnit) {
            if (jCCompilationUnit.packge != null) {
                if (JavacProcessingEnvironment.this.isPkgInfo(jCCompilationUnit.sourcefile, JavaFileObject.Kind.SOURCE)) {
                    jCCompilationUnit.packge.package_info.reset();
                }
                jCCompilationUnit.packge.reset();
            }
            if (JavacProcessingEnvironment.this.isModuleInfo(jCCompilationUnit.sourcefile, JavaFileObject.Kind.SOURCE)) {
                jCCompilationUnit.modle.reset();
                jCCompilationUnit.modle.completer = new Symbol.Completer() { // from class: com.sun.tools.javac.processing.b
                    @Override // com.sun.tools.javac.code.Symbol.Completer
                    public final void complete(Symbol symbol) {
                        JavacProcessingEnvironment.AnonymousClass1 anonymousClass1 = this.b;
                        JCTree.JCCompilationUnit jCCompilationUnit2 = jCCompilationUnit;
                        JavacProcessingEnvironment.this.modules.enter(List.of(jCCompilationUnit2), jCCompilationUnit2.modle.module_info);
                    }
                };
                jCCompilationUnit.modle.module_info.reset();
                Symbol.ClassSymbol classSymbol = jCCompilationUnit.modle.module_info;
                classSymbol.members_field = Scope.WriteableScope.create(classSymbol);
            }
            jCCompilationUnit.packge = null;
            this.topLevel = jCCompilationUnit;
            try {
                super.visitTopLevel(jCCompilationUnit);
            } finally {
                this.topLevel = null;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitUnary(JCTree.JCUnary jCUnary) {
            jCUnary.operator = null;
            super.visitUnary(jCUnary);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            jCVariableDecl.sym = null;
            super.visitVarDef(jCVariableDecl);
        }
    }

    public class DiscoveredProcessors implements Iterable<ProcessorState> {
        ArrayList<ProcessorState> procStateList = new ArrayList<>();
        Iterator<? extends Processor> processorIterator;

        public class ProcessorStateIterator implements Iterator<ProcessorState> {
            Iterator<ProcessorState> innerIter;
            boolean onProcIterator = false;
            DiscoveredProcessors psi;

            public ProcessorStateIterator(DiscoveredProcessors discoveredProcessors) {
                this.psi = discoveredProcessors;
                this.innerIter = discoveredProcessors.procStateList.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.onProcIterator) {
                    return this.psi.processorIterator.hasNext();
                }
                return this.innerIter.hasNext() || this.psi.processorIterator.hasNext();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public ProcessorState next() {
                if (!this.onProcIterator) {
                    if (this.innerIter.hasNext()) {
                        return this.innerIter.next();
                    }
                    this.onProcIterator = true;
                }
                if (!this.psi.processorIterator.hasNext()) {
                    z0e.a();
                    return null;
                }
                Processor next = this.psi.processorIterator.next();
                JavacProcessingEnvironment javacProcessingEnvironment = JavacProcessingEnvironment.this;
                ProcessorState processorState = new ProcessorState(next, javacProcessingEnvironment.log, javacProcessingEnvironment.source, javacProcessingEnvironment.dcfh, Source.Feature.MODULES.allowedInSource(JavacProcessingEnvironment.this.source), JavacProcessingEnvironment.this);
                this.psi.procStateList.add(processorState);
                return processorState;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            public void runContributingProcs(RoundEnvironment roundEnvironment) {
                if (this.onProcIterator) {
                    return;
                }
                Set set = Collections.EMPTY_SET;
                while (this.innerIter.hasNext()) {
                    ProcessorState next = this.innerIter.next();
                    if (next.contributed) {
                        JavacProcessingEnvironment.this.callProcessor(next.processor, set, roundEnvironment);
                    }
                }
            }
        }

        public DiscoveredProcessors(Iterator<? extends Processor> it) {
            this.processorIterator = it;
        }

        public void close() {
            Iterator<? extends Processor> it = this.processorIterator;
            if (it == null || !(it instanceof ServiceIterator)) {
                return;
            }
            ((ServiceIterator) it).close();
        }

        @Override // java.lang.Iterable
        /* JADX INFO: renamed from: iterator, reason: merged with bridge method [inline-methods] */
        public Iterator<ProcessorState> iterator2() {
            return new ProcessorStateIterator(this);
        }
    }

    public class ImplicitCompleter implements Symbol.Completer {
        private final JCTree.JCCompilationUnit topLevel;

        public ImplicitCompleter(JCTree.JCCompilationUnit jCCompilationUnit) {
            this.topLevel = jCCompilationUnit;
        }

        @Override // com.sun.tools.javac.code.Symbol.Completer
        public void complete(Symbol symbol) throws Symbol.CompletionFailure {
            JavacProcessingEnvironment.this.compiler.readSourceFile(this.topLevel, (Symbol.ClassSymbol) symbol);
        }
    }

    public static class NameProcessIterator implements Iterator<Processor> {
        Log log;
        Iterator<String> names;
        Processor nextProc = null;
        ClassLoader processorCL;

        public NameProcessIterator(String str, ClassLoader classLoader, Log log) {
            this.names = Arrays.asList(str.split(",")).iterator();
            this.processorCL = classLoader;
            this.log = log;
        }

        private void ensureReadable(Class<?> cls) {
            try {
                Method method = Class.class.getMethod(Constants.GET_MODULE, null);
                Object objInvoke = method.invoke(getClass(), null);
                Object objInvoke2 = method.invoke(cls, null);
                Class<?> returnType = method.getReturnType();
                returnType.getMethod(Constants.ADD_READS, returnType).invoke(objInvoke, objInvoke2);
            } catch (NoSuchMethodException unused) {
            } catch (Exception e) {
                throw new InternalError(e);
            }
        }

        private Processor getNextProcessor(String str) {
            try {
                try {
                    try {
                        Class<?> clsLoadClass = this.processorCL.loadClass(str);
                        ensureReadable(clsLoadClass);
                        return (Processor) clsLoadClass.getConstructor(null).newInstance(null);
                    } catch (Throwable th) {
                        throw new AnnotationProcessingError(th);
                    }
                } catch (ClassCastException unused) {
                    this.log.error(CompilerProperties.Errors.ProcProcessorWrongType(str));
                    return null;
                } catch (ClassNotFoundException unused2) {
                    this.log.error(CompilerProperties.Errors.ProcProcessorNotFound(str));
                    return null;
                } catch (Exception unused3) {
                    this.log.error(CompilerProperties.Errors.ProcProcessorCantInstantiate(str));
                    return null;
                }
            } catch (ClientCodeException e) {
                throw e;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Processor nextProcessor;
            if (this.nextProc != null) {
                return true;
            }
            if (!this.names.hasNext() || (nextProcessor = getNextProcessor(this.names.next())) == null) {
                return false;
            }
            this.nextProc = nextProcessor;
            return true;
        }

        @Override // java.util.Iterator
        public Processor next() {
            if (!hasNext()) {
                z0e.a();
                return null;
            }
            Processor processor = this.nextProc;
            this.nextProc = null;
            return processor;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public class NameServiceIterator extends ServiceIterator {
        private Map<String, Processor> namedProcessorsMap;
        private Processor nextProc;
        private Iterator<String> processorNames;

        public NameServiceIterator(ServiceLoader<Processor> serviceLoader, Log log, String str) {
            super(serviceLoader, log);
            this.namedProcessorsMap = new HashMap();
            this.processorNames = null;
            this.nextProc = null;
            this.processorNames = Arrays.asList(str.split(",")).iterator();
        }

        @Override // com.sun.tools.javac.processing.JavacProcessingEnvironment.ServiceIterator
        public boolean internalHasNext() {
            if (this.nextProc != null) {
                return true;
            }
            if (!this.processorNames.hasNext()) {
                this.namedProcessorsMap = null;
                return false;
            }
            String next = this.processorNames.next();
            Processor processor = this.namedProcessorsMap.get(next);
            if (processor != null) {
                this.namedProcessorsMap.remove(next);
                this.nextProc = processor;
                return true;
            }
            while (this.iterator.hasNext()) {
                Processor next2 = this.iterator.next();
                String name = next2.getClass().getName();
                if (name.equals(next)) {
                    this.nextProc = next2;
                    return true;
                }
                this.namedProcessorsMap.put(name, next2);
            }
            this.log.error(CompilerProperties.Errors.ProcProcessorNotFound(next));
            return false;
        }

        @Override // com.sun.tools.javac.processing.JavacProcessingEnvironment.ServiceIterator
        public Processor internalNext() {
            if (!hasNext()) {
                z0e.a();
                return null;
            }
            Processor processor = this.nextProc;
            this.nextProc = null;
            return processor;
        }
    }

    public static class ProcessorState {
        public boolean contributed = false;
        public Processor processor;
        private Set<Pattern> supportedAnnotationPatterns;
        private Set<String> supportedAnnotationStrings;
        private Set<String> supportedOptionNames;

        public ProcessorState(Processor processor, Log log, Source source, DeferredCompletionFailureHandler deferredCompletionFailureHandler, boolean z, ProcessingEnvironment processingEnvironment) {
            this.processor = processor;
            DeferredCompletionFailureHandler.Handler handler = deferredCompletionFailureHandler.setHandler(deferredCompletionFailureHandler.userCodeHandler);
            try {
                try {
                    this.processor.init(processingEnvironment);
                    checkSourceVersionCompatibility(source, log);
                    this.supportedAnnotationStrings = new LinkedHashSet();
                    this.supportedAnnotationPatterns = new LinkedHashSet();
                    for (String str : this.processor.getSupportedAnnotationTypes()) {
                        boolean zAdd = this.supportedAnnotationStrings.add(str);
                        this.supportedAnnotationPatterns.add(JavacProcessingEnvironment.importStringToPattern(z, str, this.processor, log));
                        if (!zAdd) {
                            log.warning(CompilerProperties.LintWarnings.ProcDuplicateSupportedAnnotation(str, processor.getClass().getName()));
                        }
                    }
                    if (this.supportedAnnotationPatterns.contains(MatchingUtils.validImportStringToPattern("*")) && this.supportedAnnotationPatterns.size() > 1) {
                        log.warning(CompilerProperties.LintWarnings.ProcRedundantTypesWithWildcard(processor.getClass().getName()));
                    }
                    this.supportedOptionNames = new LinkedHashSet();
                    for (String str2 : this.processor.getSupportedOptions()) {
                        if (checkOptionName(str2, log) && !this.supportedOptionNames.add(str2)) {
                            log.warning(CompilerProperties.LintWarnings.ProcDuplicateOptionName(str2, processor.getClass().getName()));
                        }
                    }
                    deferredCompletionFailureHandler.setHandler(handler);
                } catch (Throwable th) {
                    deferredCompletionFailureHandler.setHandler(handler);
                    throw th;
                }
            } catch (ClientCodeException e) {
                throw e;
            } catch (Throwable th2) {
                throw new AnnotationProcessingError(th2);
            }
        }

        private boolean checkOptionName(String str, Log log) {
            boolean zIsValidOptionName = JavacProcessingEnvironment.isValidOptionName(str);
            if (!zIsValidOptionName) {
                log.error(CompilerProperties.Errors.ProcProcessorBadOptionName(str, this.processor.getClass().getName()));
            }
            return zIsValidOptionName;
        }

        private void checkSourceVersionCompatibility(Source source, Log log) {
            SourceVersion supportedSourceVersion = this.processor.getSupportedSourceVersion();
            if (supportedSourceVersion.compareTo(Source.toSourceVersion(source)) < 0) {
                log.warning(CompilerProperties.Warnings.ProcProcessorIncompatibleSourceVersion(supportedSourceVersion, this.processor.getClass().getName(), source.name));
            }
        }

        public boolean annotationSupported(String str) {
            Iterator<Pattern> it = this.supportedAnnotationPatterns.iterator();
            while (it.hasNext()) {
                if (it.next().matcher(str).matches()) {
                    return true;
                }
            }
            return false;
        }

        public void removeSupportedOptions(Set<String> set) {
            set.removeAll(this.supportedOptionNames);
        }
    }

    public JavacProcessingEnvironment(Context context) {
        this.context = context;
        context.put((Class<JavacProcessingEnvironment>) JavacProcessingEnvironment.class, this);
        this.log = Log.instance(context);
        this.source = Source.instance(context);
        this.diags = JCDiagnostic.Factory.instance(context);
        Options optionsInstance = Options.instance(context);
        this.options = optionsInstance;
        this.printProcessorInfo = optionsInstance.isSet(Option.XPRINTPROCESSORINFO);
        this.printRounds = optionsInstance.isSet(Option.XPRINTROUNDS);
        this.verbose = optionsInstance.isSet(Option.VERBOSE);
        JavaCompiler javaCompilerInstance = JavaCompiler.instance(context);
        this.compiler = javaCompilerInstance;
        if (optionsInstance.isSet(Option.PROC, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_ONLY) || optionsInstance.isSet(Option.XPRINT)) {
            javaCompilerInstance.shouldStopPolicyIfNoError = CompileStates.CompileState.PROCESS;
        }
        this.fatalErrors = optionsInstance.isSet("fatalEnterError");
        this.showResolveErrors = optionsInstance.isSet("showResolveErrors");
        this.werror = javaCompilerInstance.isWerror(Lint.LintCategory.PROCESSING);
        this.fileManager = (JavaFileManager) context.get(JavaFileManager.class);
        this.platformAnnotations = initPlatformAnnotations();
        this.filer = new JavacFiler(context);
        this.messager = new JavacMessager(context, this);
        this.elementUtils = JavacElements.instance(context);
        this.typeUtils = JavacTypes.instance(context);
        this.modules = Modules.instance(context);
        this.types = Types.instance(context);
        this.annotate = Annotate.instance(context);
        this.processorOptions = initProcessorOptions();
        this.unmatchedProcessorOptions = initUnmatchedProcessorOptions();
        this.messages = JavacMessages.instance(context);
        this.taskListener = MultiTaskListener.instance(context);
        this.symtab = Symtab.instance(context);
        this.dcfh = DeferredCompletionFailureHandler.instance(context);
        this.names = Names.instance(context);
        this.enter = Enter.instance(context);
        this.initialCompleter = ClassFinder.instance(context).getCompleter();
        this.chk = Check.instance(context);
        this.preview = Preview.instance(context);
        initProcessorLoader();
    }

    public static /* synthetic */ Iterator a(Iterator it) {
        return it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean callProcessor(Processor processor, Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        DeferredCompletionFailureHandler deferredCompletionFailureHandler = this.dcfh;
        DeferredCompletionFailureHandler.Handler handler = deferredCompletionFailureHandler.setHandler(deferredCompletionFailureHandler.userCodeHandler);
        try {
            try {
                try {
                    try {
                        boolean zProcess = processor.process(set, roundEnvironment);
                        this.dcfh.setHandler(handler);
                        return zProcess;
                    } catch (Symbol.CompletionFailure e) {
                        StringWriter stringWriter = new StringWriter();
                        e.printStackTrace(new PrintWriter(stringWriter));
                        this.log.error(CompilerProperties.Errors.ProcCantAccess(e.sym, e.getDetailValue(), stringWriter.toString()));
                        this.dcfh.setHandler(handler);
                        return false;
                    }
                } catch (ClassFinder.BadClassFile e2) {
                    this.log.error(CompilerProperties.Errors.ProcCantAccess1(e2.sym, e2.getDetailValue()));
                    this.dcfh.setHandler(handler);
                    return false;
                }
            } catch (ClientCodeException e3) {
                throw e3;
            } catch (Throwable th) {
                throw new AnnotationProcessingError(th);
            }
        } catch (Throwable th2) {
            this.dcfh.setHandler(handler);
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r8v4, types: [com.sun.tools.javac.processing.JavacProcessingEnvironment$DiscoveredProcessors$ProcessorStateIterator] */
    public void discoverAndRunProcs(Set<TypeElement> set, List<Symbol.ClassSymbol> list, List<Symbol.PackageSymbol> list2, List<Symbol.ModuleSymbol> list3) {
        HashMap map = new HashMap(set.size());
        Iterator<TypeElement> it = set.iterator();
        while (true) {
            String str = "";
            if (!it.hasNext()) {
                break;
            }
            TypeElement next = it.next();
            ModuleElement moduleOf = this.elementUtils.getModuleOf(next);
            if (Source.Feature.MODULES.allowedInSource(this.source) && moduleOf != null) {
                str = moduleOf.getQualifiedName() + PsuedoNames.PSEUDONAME_ROOT;
            }
            map.put(str + next.getQualifiedName().toString(), next);
        }
        if (map.size() == 0) {
            map.put("", null);
        }
        ?? Iterator2 = this.discoveredProcs.iterator2();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(list);
        linkedHashSet.addAll(list2);
        linkedHashSet.addAll(list3);
        JavacRoundEnvironment javacRoundEnvironment = new JavacRoundEnvironment(false, false, Collections.unmodifiableSet(linkedHashSet), this);
        while (map.size() > 0 && Iterator2.hasNext()) {
            ProcessorState next2 = Iterator2.next();
            HashSet hashSet = new HashSet();
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            for (Map.Entry entry : map.entrySet()) {
                String str2 = (String) entry.getKey();
                if (next2.annotationSupported(str2)) {
                    hashSet.add(str2);
                    TypeElement typeElement = (TypeElement) entry.getValue();
                    if (typeElement != null) {
                        linkedHashSet2.add(typeElement);
                    }
                }
            }
            if (hashSet.size() > 0 || next2.contributed) {
                boolean zCallProcessor = callProcessor(next2.processor, linkedHashSet2, javacRoundEnvironment);
                next2.contributed = true;
                next2.removeSupportedOptions(this.unmatchedProcessorOptions);
                if (this.printProcessorInfo || this.verbose) {
                    this.log.printLines("x.print.processor.info", next2.processor.getClass().getName(), hashSet.toString(), Boolean.valueOf(zCallProcessor));
                }
                if (zCallProcessor) {
                    map.keySet().removeAll(hashSet);
                }
            }
        }
        map.remove("");
        if (map.size() > 0) {
            map.keySet().removeAll(this.platformAnnotations);
            if (map.size() > 0) {
                this.log.warning(CompilerProperties.LintWarnings.ProcAnnotationsWithoutProcessors(map.keySet()));
            }
        }
        Iterator2.runContributingProcs(javacRoundEnvironment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Symbol.ModuleSymbol> getModuleInfoFiles(List<? extends JCTree.JCCompilationUnit> list) {
        List listNil = List.nil();
        for (JCTree.JCCompilationUnit jCCompilationUnit : list) {
            if (isModuleInfo(jCCompilationUnit.sourcefile, JavaFileObject.Kind.SOURCE) && jCCompilationUnit.defs.nonEmpty()) {
                for (JCTree jCTree : jCCompilationUnit.defs) {
                    if (!jCTree.hasTag(JCTree.Tag.IMPORT)) {
                        if (!jCTree.hasTag(JCTree.Tag.MODULEDEF)) {
                            break;
                        }
                        listNil = listNil.prepend(jCCompilationUnit.modle);
                        break;
                    }
                }
            }
        }
        return listNil.reverse();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Symbol.PackageSymbol> getPackageInfoFiles(List<? extends JCTree.JCCompilationUnit> list) {
        List listNil = List.nil();
        for (JCTree.JCCompilationUnit jCCompilationUnit : list) {
            if (isPkgInfo(jCCompilationUnit.sourcefile, JavaFileObject.Kind.SOURCE)) {
                listNil = listNil.prepend(jCCompilationUnit.packge);
            }
        }
        return listNil.reverse();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Symbol.PackageSymbol> getPackageInfoFilesFromClasses(List<? extends Symbol.ClassSymbol> list) {
        List listNil = List.nil();
        for (Symbol.ClassSymbol classSymbol : list) {
            if (isPkgInfo(classSymbol)) {
                listNil = listNil.prepend((Symbol.PackageSymbol) classSymbol.owner);
            }
        }
        return listNil.reverse();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Symbol.ClassSymbol> getTopLevelClasses(List<? extends JCTree.JCCompilationUnit> list) {
        List listNil = List.nil();
        Iterator<? extends JCTree.JCCompilationUnit> it = list.iterator();
        while (it.hasNext()) {
            for (JCTree jCTree : it.next().defs) {
                if (jCTree.hasTag(JCTree.Tag.CLASSDEF)) {
                    Symbol.ClassSymbol classSymbol = ((JCTree.JCClassDecl) jCTree).sym;
                    Assert.checkNonNull(classSymbol);
                    listNil = listNil.prepend(classSymbol);
                }
            }
        }
        return listNil.reverse();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Symbol.ClassSymbol> getTopLevelClassesFromClasses(List<? extends Symbol.ClassSymbol> list) {
        List listNil = List.nil();
        for (Symbol.ClassSymbol classSymbol : list) {
            if (!isPkgInfo(classSymbol)) {
                listNil = listNil.prepend(classSymbol);
            }
        }
        return listNil.reverse();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Pattern importStringToPattern(boolean z, String str, Processor processor, Log log) {
        String strQuote;
        String strSubstring;
        int iIndexOf = str.indexOf(47);
        if (iIndexOf != -1) {
            String strSubstring2 = str.substring(0, iIndexOf);
            if (!SourceVersion.isName(strSubstring2)) {
                return warnAndNoMatches(str, processor, log);
            }
            strQuote = Pattern.quote(strSubstring2.concat(PsuedoNames.PSEUDONAME_ROOT));
            strSubstring = str.substring(iIndexOf + 1);
        } else {
            if (str.equals("*")) {
                return MatchingUtils.validImportStringToPattern(str);
            }
            strQuote = z ? ".*/" : "";
            strSubstring = str;
        }
        if (!MatchingUtils.isValidImportString(strSubstring)) {
            return warnAndNoMatches(str, processor, log);
        }
        return Pattern.compile(strQuote + MatchingUtils.validImportStringToPatternString(strSubstring));
    }

    private Set<String> initPlatformAnnotations() {
        String str = Source.Feature.MODULES.allowedInSource(this.source) ? "java.base/" : "";
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(str.concat("java.lang.Deprecated"), str.concat("java.lang.FunctionalInterface"), str.concat("java.lang.Override"), str.concat("java.lang.SafeVarargs"), str.concat("java.lang.SuppressWarnings"), str.concat("java.lang.annotation.Documented"), str.concat("java.lang.annotation.Inherited"), str.concat("java.lang.annotation.Native"), str.concat("java.lang.annotation.Repeatable"), str.concat("java.lang.annotation.Retention"), str.concat("java.lang.annotation.Target"), str.concat("java.io.Serial"))));
    }

    private void initProcessorIterator(Iterable<? extends Processor> iterable) {
        Iterator<? extends Processor> it;
        Iterator<? extends Processor> nameProcessIterator;
        if (this.options.isSet(Option.XPRINT)) {
            try {
                it = List.of(new PrintingProcessor()).iterator();
            } catch (Throwable th) {
                throw new AssertionError("Problem instantiating PrintingProcessor.", th);
            }
        } else if (iterable != null) {
            it = iterable.iterator();
        } else {
            String str = this.options.get(Option.PROCESSOR);
            if (this.fileManager.hasLocation(StandardLocation.ANNOTATION_PROCESSOR_MODULE_PATH)) {
                ServiceLoader<Processor> serviceLoader = this.serviceLoader;
                if (str == null) {
                    it = new ServiceIterator(serviceLoader, this.log);
                } else {
                    nameProcessIterator = new NameServiceIterator(serviceLoader, this.log, str);
                    it = nameProcessIterator;
                }
            } else {
                ClassLoader classLoader = this.processorClassLoader;
                if (str != null) {
                    nameProcessIterator = new NameProcessIterator(str, classLoader, this.log);
                    it = nameProcessIterator;
                } else {
                    it = new ServiceIterator(classLoader, this.log);
                }
            }
        }
        PlatformDescription platformDescription = (PlatformDescription) this.context.get(PlatformDescription.class);
        java.util.List list = Collections.EMPTY_LIST;
        if (platformDescription != null) {
            list = (java.util.List) platformDescription.getAnnotationProcessors().stream().map(new Function() { // from class: en7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return (Processor) ((PlatformDescription.PluginInfo) obj).getPlugin();
                }
            }).collect(Collectors.toList());
        }
        this.discoveredProcs = new DiscoveredProcessors(Iterators.createCompoundIterator(List.of((Iterator) it, list.iterator()), new Function() { // from class: fn7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return JavacProcessingEnvironment.a((Iterator) obj);
            }
        }));
    }

    private void initProcessorLoader() {
        JavaFileManager javaFileManager = this.fileManager;
        StandardLocation standardLocation = StandardLocation.ANNOTATION_PROCESSOR_MODULE_PATH;
        boolean zHasLocation = javaFileManager.hasLocation(standardLocation);
        JavaFileManager javaFileManager2 = this.fileManager;
        if (zHasLocation) {
            try {
                this.serviceLoader = javaFileManager2.getServiceLoader(standardLocation, Processor.class);
                return;
            } catch (IOException e) {
                throw new Abort(e);
            }
        }
        StandardLocation standardLocation2 = StandardLocation.ANNOTATION_PROCESSOR_PATH;
        boolean zHasLocation2 = javaFileManager2.hasLocation(standardLocation2);
        JavaFileManager javaFileManager3 = this.fileManager;
        this.processorClassLoader = zHasLocation2 ? javaFileManager3.getClassLoader(standardLocation2) : javaFileManager3.getClassLoader(StandardLocation.CLASS_PATH);
        if (this.options.isSet("accessInternalAPI")) {
            ModuleHelper.addExports(ModuleWrapper.getModule(getClass()), ModuleWrapper.getUnnamedModule(this.processorClassLoader));
        }
        Object obj = this.processorClassLoader;
        if (obj == null || !(obj instanceof Closeable)) {
            return;
        }
        JavaCompiler javaCompiler = this.compiler;
        javaCompiler.closeables = javaCompiler.closeables.prepend((Closeable) obj);
    }

    private Map<String, String> initProcessorOptions() {
        String strSubstring;
        Set<String> setKeySet = this.options.keySet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : setKeySet) {
            if (str.startsWith("-A") && str.length() > 2) {
                int iIndexOf = str.indexOf(61);
                String strSubstring2 = null;
                if (iIndexOf == -1) {
                    strSubstring2 = str.substring(2);
                    strSubstring = null;
                } else if (iIndexOf >= 3) {
                    String strSubstring3 = str.substring(2, iIndexOf);
                    strSubstring = iIndexOf < str.length() + (-1) ? str.substring(iIndexOf + 1) : null;
                    strSubstring2 = strSubstring3;
                } else {
                    strSubstring = null;
                }
                linkedHashMap.put(strSubstring2, strSubstring);
            }
        }
        PlatformDescription platformDescription = (PlatformDescription) this.context.get(PlatformDescription.class);
        if (platformDescription != null) {
            Iterator<PlatformDescription.PluginInfo<Processor>> it = platformDescription.getAnnotationProcessors().iterator();
            while (it.hasNext()) {
                linkedHashMap.putAll(it.next().getOptions());
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private Set<String> initUnmatchedProcessorOptions() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.processorOptions.keySet());
        return hashSet;
    }

    public static JavacProcessingEnvironment instance(Context context) {
        JavacProcessingEnvironment javacProcessingEnvironment = (JavacProcessingEnvironment) context.get(JavacProcessingEnvironment.class);
        return javacProcessingEnvironment == null ? new JavacProcessingEnvironment(context) : javacProcessingEnvironment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isModuleInfo(JavaFileObject javaFileObject, JavaFileObject.Kind kind) {
        return javaFileObject.isNameCompatible("module-info", kind);
    }

    private boolean isPkgInfo(Symbol.ClassSymbol classSymbol) {
        return isPkgInfo(classSymbol.classfile, JavaFileObject.Kind.CLASS) && classSymbol.packge().package_info == classSymbol;
    }

    public static boolean isValidOptionName(String str) {
        for (String str2 : str.split("\\.", -1)) {
            if (!SourceVersion.isIdentifier(str2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> join(List<T> list, List<T> list2) {
        return list.appendList(list2);
    }

    private boolean moreToDo() {
        return this.filer.newFiles();
    }

    private static Pattern warnAndNoMatches(String str, Processor processor, Log log) {
        log.warning(CompilerProperties.LintWarnings.ProcMalformedSupportedString(str, processor.getClass().getName()));
        return noMatches;
    }

    private void warnIfUnmatchedOptions() {
        if (this.unmatchedProcessorOptions.isEmpty()) {
            return;
        }
        this.log.warning(CompilerProperties.Warnings.ProcUnmatchedProcessorOptions(this.unmatchedProcessorOptions.toString()));
    }

    public boolean atLeastOneProcessor() {
        return this.discoveredProcs.iterator2().hasNext();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.filer.close();
        DiscoveredProcessors discoveredProcessors = this.discoveredProcs;
        if (discoveredProcessors != null) {
            discoveredProcessors.close();
        }
        this.discoveredProcs = null;
    }

    public boolean doProcessing(List<JCTree.JCCompilationUnit> list, List<Symbol.ClassSymbol> list2, Iterable<? extends Symbol.PackageSymbol> iterable, Log.DeferredDiagnosticHandler deferredDiagnosticHandler) {
        boolean zUnrecoverableError;
        Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        Iterator<Env<AttrContext>> it = this.enter.getEnvs().iterator();
        while (it.hasNext()) {
            setNewSetFromMap.add(it.next().toplevel);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<? extends Symbol.PackageSymbol> it2 = iterable.iterator();
        while (it2.hasNext()) {
            linkedHashSet.add(it2.next());
        }
        this.specifiedPackages = Collections.unmodifiableSet(linkedHashSet);
        Round round = new Round(this, list, list2, setNewSetFromMap, deferredDiagnosticHandler);
        do {
            round.run(false, false);
            zUnrecoverableError = round.unrecoverableError();
            boolean zMoreToDo = moreToDo();
            round.showDiagnostics(this.showResolveErrors);
            round = round.next(new LinkedHashSet(this.filer.getGeneratedSourceFileObjects()), new LinkedHashMap(this.filer.getGeneratedClasses()));
            if (round.unrecoverableError()) {
                zUnrecoverableError = true;
            }
            if (!zMoreToDo) {
                break;
            }
        } while (!zUnrecoverableError);
        round.run(true, zUnrecoverableError);
        round.showDiagnostics(true);
        this.filer.warnIfUnclosedFiles();
        warnIfUnmatchedOptions();
        if (this.messager.errorRaised() || (this.werror && round.warningCount() > 0 && round.errorCount() > 0)) {
            zUnrecoverableError = true;
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(this.filer.getGeneratedSourceFileObjects());
        List<JCTree.JCCompilationUnit> listAppendList = round.roots;
        boolean z = zUnrecoverableError || this.compiler.errorCount() > 0;
        if (linkedHashSet2.size() > 0) {
            listAppendList = listAppendList.appendList(this.compiler.parseFiles(linkedHashSet2));
        }
        if ((z || this.compiler.errorCount() > 0) && this.compiler.errorCount() == 0) {
            this.compiler.log.nerrors++;
        }
        if (this.compiler.continueAfterProcessAnnotations()) {
            round.finalCompiler();
            JavaCompiler javaCompiler = this.compiler;
            javaCompiler.enterTrees(javaCompiler.initModules(listAppendList));
        } else {
            this.compiler.todo.clear();
        }
        close();
        if (!this.taskListener.isEmpty()) {
            this.taskListener.finished(new TaskEvent(TaskEvent.Kind.ANNOTATION_PROCESSING));
        }
        return true;
    }

    public Context getContext() {
        return this.context;
    }

    @Override // javax.annotation.processing.ProcessingEnvironment
    public Locale getLocale() {
        return this.messages.getCurrentLocale();
    }

    @Override // javax.annotation.processing.ProcessingEnvironment
    public Messager getMessager() {
        return this.messager;
    }

    @Override // javax.annotation.processing.ProcessingEnvironment
    public Map<String, String> getOptions() {
        return this.processorOptions;
    }

    public ClassLoader getProcessorClassLoader() {
        return this.processorClassLoader;
    }

    public <S> ServiceLoader<S> getServiceLoader(Class<S> cls) {
        JavaFileManager javaFileManager = this.fileManager;
        StandardLocation standardLocation = StandardLocation.ANNOTATION_PROCESSOR_MODULE_PATH;
        if (!javaFileManager.hasLocation(standardLocation)) {
            return ServiceLoaderWrapper.loadWithClassLoader(cls, getProcessorClassLoader());
        }
        try {
            return this.fileManager.getServiceLoader(standardLocation, cls);
        } catch (IOException e) {
            throw new Abort(e);
        }
    }

    @Override // javax.annotation.processing.ProcessingEnvironment
    public SourceVersion getSourceVersion() {
        return Source.toSourceVersion(this.source);
    }

    public Set<Symbol.PackageSymbol> getSpecifiedPackages() {
        return this.specifiedPackages;
    }

    @Override // javax.annotation.processing.ProcessingEnvironment
    public boolean isPreviewEnabled() {
        return this.preview.isEnabled();
    }

    public void setProcessors(Iterable<? extends Processor> iterable) {
        Assert.checkNull(this.discoveredProcs);
        initProcessorIterator(iterable);
    }

    public String toString() {
        return "javac ProcessingEnvironment";
    }

    @Override // javax.annotation.processing.ProcessingEnvironment
    public JavacElements getElementUtils() {
        return this.elementUtils;
    }

    @Override // javax.annotation.processing.ProcessingEnvironment
    public JavacFiler getFiler() {
        return this.filer;
    }

    @Override // javax.annotation.processing.ProcessingEnvironment
    public JavacTypes getTypeUtils() {
        return this.typeUtils;
    }

    public static class ComputeAnnotationSet extends ElementScanner14<Set<TypeElement>, Set<TypeElement>> {
        final Elements elements;

        public ComputeAnnotationSet(Elements elements) {
            this.elements = elements;
        }

        public void addAnnotations(Element element, Set<TypeElement> set) {
            Iterator<? extends AnnotationMirror> it = this.elements.getAllAnnotationMirrors(element).iterator();
            while (it.hasNext()) {
                set.add((TypeElement) it.next().getAnnotationType().asElement());
            }
        }

        @Override // javax.lang.model.util.ElementScanner6
        public Set<TypeElement> scan(Element element, Set<TypeElement> set) {
            addAnnotations(element, set);
            return (Set) super.scan(element, set);
        }

        @Override // javax.lang.model.util.ElementScanner14, javax.lang.model.util.ElementScanner6, javax.lang.model.element.ElementVisitor
        public Set<TypeElement> visitExecutable(ExecutableElement executableElement, Set<TypeElement> set) {
            scan(executableElement.getTypeParameters(), set);
            return (Set) super.visitExecutable(executableElement, set);
        }

        @Override // javax.lang.model.util.ElementScanner14, javax.lang.model.util.ElementScanner6, javax.lang.model.element.ElementVisitor
        public Set<TypeElement> visitType(TypeElement typeElement, Set<TypeElement> set) {
            scan(typeElement.getTypeParameters(), set);
            return (Set) super.visitType(typeElement, set);
        }

        @Override // javax.lang.model.util.ElementScanner6, javax.lang.model.element.ElementVisitor
        public Set<TypeElement> visitPackage(PackageElement packageElement, Set<TypeElement> set) {
            return set;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPkgInfo(JavaFileObject javaFileObject, JavaFileObject.Kind kind) {
        return javaFileObject.isNameCompatible("package-info", kind);
    }

    public class ServiceIterator implements Iterator<Processor> {
        Iterator<Processor> iterator;
        ServiceLoader<Processor> loader;
        Log log;

        public ServiceIterator(ClassLoader classLoader, Log log) {
            this.log = log;
            try {
                ServiceLoader<Processor> serviceLoaderLoadWithClassLoader = ServiceLoaderWrapper.loadWithClassLoader(Processor.class, classLoader);
                this.loader = serviceLoaderLoadWithClassLoader;
                this.iterator = serviceLoaderLoadWithClassLoader.iterator();
            } catch (Throwable th) {
                log.error(CompilerProperties.Errors.ProcServiceProblem);
                throw new Abort(th);
            }
        }

        public void close() {
            ServiceLoader<Processor> serviceLoader = this.loader;
            if (serviceLoader != null) {
                try {
                    serviceLoader.reload();
                } catch (Exception unused) {
                }
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            try {
                return internalHasNext();
            } catch (UnsupportedClassVersionError e) {
                this.log.error(CompilerProperties.Errors.ProcCantLoadClass(e.getLocalizedMessage()));
                throw new Abort(e);
            } catch (ClassFormatError e2) {
                this.log.error(CompilerProperties.Errors.ProcCantLoadClass(e2.getLocalizedMessage()));
                throw new Abort(e2);
            } catch (ServiceConfigurationError e3) {
                this.log.error(CompilerProperties.Errors.ProcBadConfigFile(e3.getLocalizedMessage()));
                throw new Abort(e3);
            } catch (Throwable th) {
                this.log.error(CompilerProperties.Errors.ProcBadConfigFile(th.getLocalizedMessage()));
                throw new Abort(th);
            }
        }

        public boolean internalHasNext() {
            return this.iterator.hasNext();
        }

        public Processor internalNext() {
            return this.iterator.next();
        }

        @Override // java.util.Iterator
        public Processor next() {
            try {
                return internalNext();
            } catch (ServiceConfigurationError e) {
                this.log.error(CompilerProperties.Errors.ProcBadConfigFile(e.getLocalizedMessage()));
                throw new Abort(e);
            } catch (Throwable th) {
                this.log.error(CompilerProperties.Errors.ProcBadConfigFile(th.getLocalizedMessage()));
                throw new Abort(th);
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public ServiceIterator(ServiceLoader<Processor> serviceLoader, Log log) {
            this.log = log;
            this.loader = serviceLoader;
            this.iterator = serviceLoader.iterator();
        }
    }

    public class Round {
        private final Predicate<JCDiagnostic> ACCEPT_ALL;
        private final Predicate<JCDiagnostic> ACCEPT_NON_RECOVERABLE;
        Set<TypeElement> annotationsPresent;
        final Log.DeferredDiagnosticHandler deferredDiagnosticHandler;
        Map<Symbol.ModuleSymbol, Map<String, JavaFileObject>> genClassFiles;
        List<Symbol.ModuleSymbol> moduleInfoFiles;
        final int number;
        List<Symbol.PackageSymbol> packageInfoFiles;
        List<JCTree.JCCompilationUnit> roots;
        List<Symbol.ClassSymbol> topLevelClasses;
        Set<JCTree.JCCompilationUnit> treesToClean;

        private Round(JavacProcessingEnvironment javacProcessingEnvironment, Round round, Set<JavaFileObject> set, Map<Symbol.ModuleSymbol, Map<String, JavaFileObject>> map) {
            this(round.number + 1, round.treesToClean, (Log.DeferredDiagnosticHandler) null);
            round.newRound();
            this.genClassFiles = round.genClassFiles;
            List<JCTree.JCCompilationUnit> files = javacProcessingEnvironment.compiler.parseFiles(set, true);
            this.roots = round.roots.appendList(files);
            if (unrecoverableError()) {
                javacProcessingEnvironment.compiler.initModules(List.nil());
                return;
            }
            this.roots = javacProcessingEnvironment.compiler.initModules(this.roots);
            enterClassFiles(this.genClassFiles);
            List<Symbol.ClassSymbol> listEnterClassFiles = enterClassFiles(map);
            for (Map.Entry<Symbol.ModuleSymbol, Map<String, JavaFileObject>> entry : map.entrySet()) {
                this.genClassFiles.computeIfAbsent(entry.getKey(), new Function() { // from class: com.sun.tools.javac.processing.d
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return JavacProcessingEnvironment.Round.c((Symbol.ModuleSymbol) obj);
                    }
                }).putAll(entry.getValue());
            }
            enterTrees(this.roots);
            if (unrecoverableError()) {
                return;
            }
            this.topLevelClasses = JavacProcessingEnvironment.join(javacProcessingEnvironment.getTopLevelClasses(files), javacProcessingEnvironment.getTopLevelClassesFromClasses(listEnterClassFiles));
            this.packageInfoFiles = JavacProcessingEnvironment.join(javacProcessingEnvironment.getPackageInfoFiles(files), javacProcessingEnvironment.getPackageInfoFilesFromClasses(listEnterClassFiles));
            this.moduleInfoFiles = List.nil();
            findAnnotationsPresent();
        }

        public static /* synthetic */ boolean a(JCDiagnostic jCDiagnostic) {
            return (jCDiagnostic.getKind() == Diagnostic.Kind.ERROR && jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.RECOVERABLE) && !jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.API)) ? false : true;
        }

        public static /* synthetic */ boolean b(JCDiagnostic jCDiagnostic) {
            return true;
        }

        public static /* synthetic */ Map c(Symbol.ModuleSymbol moduleSymbol) {
            return new LinkedHashMap();
        }

        public static /* synthetic */ boolean d(Round round, JCDiagnostic jCDiagnostic) {
            round.getClass();
            if (jCDiagnostic.getKind() == Diagnostic.Kind.WARNING && JavacProcessingEnvironment.this.werror) {
                return true;
            }
            if (jCDiagnostic.getKind() == Diagnostic.Kind.ERROR) {
                return JavacProcessingEnvironment.this.fatalErrors || !jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.RECOVERABLE);
            }
            return false;
        }

        private List<Symbol.ClassSymbol> enterClassFiles(Map<Symbol.ModuleSymbol, Map<String, JavaFileObject>> map) {
            Symbol.ClassSymbol classSymbolEnterClass;
            List listNil = List.nil();
            for (Map.Entry<Symbol.ModuleSymbol, Map<String, JavaFileObject>> entry : map.entrySet()) {
                for (Map.Entry<String, JavaFileObject> entry2 : entry.getValue().entrySet()) {
                    Name nameFromString = JavacProcessingEnvironment.this.names.fromString(entry2.getKey());
                    JavaFileObject value = entry2.getValue();
                    JavaFileObject.Kind kind = value.getKind();
                    JavaFileObject.Kind kind2 = JavaFileObject.Kind.CLASS;
                    if (kind != kind2) {
                        x01.a(value);
                        return null;
                    }
                    if (JavacProcessingEnvironment.this.isPkgInfo(value, kind2)) {
                        Symbol.PackageSymbol packageSymbolEnterPackage = JavacProcessingEnvironment.this.symtab.enterPackage(entry.getKey(), Convert.packagePart(nameFromString));
                        if (packageSymbolEnterPackage.package_info == null) {
                            packageSymbolEnterPackage.package_info = JavacProcessingEnvironment.this.symtab.enterClass(entry.getKey(), Convert.shortName(nameFromString), packageSymbolEnterPackage);
                        }
                        classSymbolEnterClass = packageSymbolEnterPackage.package_info;
                        classSymbolEnterClass.reset();
                        if (classSymbolEnterClass.classfile == null) {
                            classSymbolEnterClass.classfile = value;
                        }
                        classSymbolEnterClass.completer = JavacProcessingEnvironment.this.initialCompleter;
                    } else {
                        classSymbolEnterClass = JavacProcessingEnvironment.this.symtab.enterClass(entry.getKey(), nameFromString);
                        classSymbolEnterClass.reset();
                        classSymbolEnterClass.classfile = value;
                        classSymbolEnterClass.completer = JavacProcessingEnvironment.this.initialCompleter;
                        Symbol symbol = classSymbolEnterClass.owner;
                        if (symbol.kind == Kinds.Kind.PCK) {
                            symbol.members().enter(classSymbolEnterClass);
                        }
                    }
                    listNil = listNil.prepend(classSymbolEnterClass);
                }
            }
            return listNil.reverse();
        }

        private void enterTrees(List<JCTree.JCCompilationUnit> list) {
            JavacProcessingEnvironment.this.compiler.enterTrees(list);
        }

        private void newRound() {
            Iterator<Env<AttrContext>> it = JavacProcessingEnvironment.this.enter.getEnvs().iterator();
            while (it.hasNext()) {
                this.treesToClean.add(it.next().toplevel);
            }
            Iterator<JCTree.JCCompilationUnit> it2 = this.treesToClean.iterator();
            while (it2.hasNext()) {
                JavacProcessingEnvironment.this.treeCleaner.scan(it2.next());
            }
            JavacProcessingEnvironment.this.chk.newRound();
            JavacProcessingEnvironment.this.enter.newRound();
            JavacProcessingEnvironment.this.filer.newRound();
            JavacProcessingEnvironment.this.messager.newRound();
            JavacProcessingEnvironment.this.compiler.newRound();
            JavacProcessingEnvironment.this.modules.newRound();
            JavacProcessingEnvironment.this.types.newRound();
            JavacProcessingEnvironment.this.annotate.newRound();
            JavacProcessingEnvironment.this.elementUtils.newRound();
            Iterator<Symbol.ClassSymbol> it3 = JavacProcessingEnvironment.this.symtab.getAllClasses().iterator();
            while (it3.hasNext()) {
                if (it3.next().kind == Kinds.Kind.ERR) {
                    for (Symbol.ClassSymbol classSymbol : JavacProcessingEnvironment.this.symtab.getAllClasses()) {
                        if (classSymbol.classfile != null || classSymbol.kind == Kinds.Kind.ERR) {
                            Kinds.Kind kind = classSymbol.kind;
                            classSymbol.reset();
                            if (kind == Kinds.Kind.ERR) {
                                classSymbol.type = new Type.ClassType(classSymbol.type.getEnclosingType(), null, classSymbol);
                            }
                            if (classSymbol.isCompleted()) {
                                classSymbol.completer = JavacProcessingEnvironment.this.initialCompleter;
                            }
                        }
                    }
                    return;
                }
            }
        }

        private void printRoundInfo(boolean z) {
            if (JavacProcessingEnvironment.this.printRounds || JavacProcessingEnvironment.this.verbose) {
                List<Symbol.ClassSymbol> listNil = z ? List.nil() : this.topLevelClasses;
                Set<TypeElement> set = z ? Collections.EMPTY_SET : this.annotationsPresent;
                JavacProcessingEnvironment.this.log.printLines("x.print.rounds", Integer.valueOf(this.number), "{" + listNil.toString(", ") + "}", set, Boolean.valueOf(z));
            }
        }

        public int errorCount() {
            return JavacProcessingEnvironment.this.compiler.errorCount();
        }

        public void finalCompiler() {
            newRound();
        }

        public void findAnnotationsPresent() {
            ComputeAnnotationSet computeAnnotationSet = new ComputeAnnotationSet(JavacProcessingEnvironment.this.elementUtils);
            this.annotationsPresent = new LinkedHashSet();
            Iterator<Symbol.ClassSymbol> it = this.topLevelClasses.iterator();
            while (it.hasNext()) {
                computeAnnotationSet.scan((Element) it.next(), this.annotationsPresent);
            }
            Iterator<Symbol.PackageSymbol> it2 = this.packageInfoFiles.iterator();
            while (it2.hasNext()) {
                computeAnnotationSet.scan((Element) it2.next(), this.annotationsPresent);
            }
            Iterator<Symbol.ModuleSymbol> it3 = this.moduleInfoFiles.iterator();
            while (it3.hasNext()) {
                computeAnnotationSet.scan((Element) it3.next(), this.annotationsPresent);
            }
        }

        public Round next(Set<JavaFileObject> set, Map<Symbol.ModuleSymbol, Map<String, JavaFileObject>> map) {
            return new Round(JavacProcessingEnvironment.this, this, set, map);
        }

        /* JADX WARN: Type inference failed for: r4v12, types: [com.sun.tools.javac.processing.JavacProcessingEnvironment$DiscoveredProcessors$ProcessorStateIterator] */
        public void run(boolean z, boolean z2) {
            printRoundInfo(z);
            if (!JavacProcessingEnvironment.this.taskListener.isEmpty()) {
                JavacProcessingEnvironment.this.taskListener.started(new TaskEvent(TaskEvent.Kind.ANNOTATION_PROCESSING_ROUND));
            }
            JavacProcessingEnvironment javacProcessingEnvironment = JavacProcessingEnvironment.this;
            try {
                if (z) {
                    javacProcessingEnvironment.filer.setLastRound(true);
                    JavacProcessingEnvironment.this.discoveredProcs.iterator2().runContributingProcs(new JavacRoundEnvironment(true, z2, Collections.EMPTY_SET, JavacProcessingEnvironment.this));
                } else {
                    javacProcessingEnvironment.discoverAndRunProcs(this.annotationsPresent, this.topLevelClasses, this.packageInfoFiles, this.moduleInfoFiles);
                }
                if (JavacProcessingEnvironment.this.taskListener.isEmpty()) {
                    return;
                }
                JavacProcessingEnvironment.this.taskListener.finished(new TaskEvent(TaskEvent.Kind.ANNOTATION_PROCESSING_ROUND));
            } catch (Throwable th) {
                try {
                    JavacProcessingEnvironment.this.compiler.reportDeferredDiagnosticAndClearHandler();
                    throw th;
                } catch (Throwable th2) {
                    if (!JavacProcessingEnvironment.this.taskListener.isEmpty()) {
                        JavacProcessingEnvironment.this.taskListener.finished(new TaskEvent(TaskEvent.Kind.ANNOTATION_PROCESSING_ROUND));
                    }
                    throw th2;
                }
            }
        }

        public void showDiagnostics(boolean z) {
            this.deferredDiagnosticHandler.reportDeferredDiagnostics(z ? this.ACCEPT_ALL : this.ACCEPT_NON_RECOVERABLE);
            JavacProcessingEnvironment.this.log.popDiagnosticHandler(this.deferredDiagnosticHandler);
            JavacProcessingEnvironment.this.compiler.setDeferredDiagnosticHandler(null);
        }

        public boolean unrecoverableError() {
            if (JavacProcessingEnvironment.this.messager.errorRaised()) {
                return true;
            }
            return this.deferredDiagnosticHandler.getDiagnostics().stream().anyMatch(new Predicate() { // from class: com.sun.tools.javac.processing.c
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return JavacProcessingEnvironment.Round.d(this.b, (JCDiagnostic) obj);
                }
            });
        }

        public int warningCount() {
            return JavacProcessingEnvironment.this.compiler.warningCount();
        }

        public Round(JavacProcessingEnvironment javacProcessingEnvironment, List<JCTree.JCCompilationUnit> list, List<Symbol.ClassSymbol> list2, Set<JCTree.JCCompilationUnit> set, Log.DeferredDiagnosticHandler deferredDiagnosticHandler) {
            this(1, set, deferredDiagnosticHandler);
            this.roots = list;
            this.genClassFiles = new HashMap();
            this.topLevelClasses = javacProcessingEnvironment.getTopLevelClasses(list).prependList(list2.reverse());
            this.packageInfoFiles = javacProcessingEnvironment.getPackageInfoFiles(list);
            this.moduleInfoFiles = javacProcessingEnvironment.getModuleInfoFiles(list);
            findAnnotationsPresent();
        }

        private Round(int i, Set<JCTree.JCCompilationUnit> set, Log.DeferredDiagnosticHandler deferredDiagnosticHandler) {
            this.ACCEPT_NON_RECOVERABLE = new Predicate() { // from class: com.sun.tools.javac.processing.e
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return JavacProcessingEnvironment.Round.a((JCDiagnostic) obj);
                }
            };
            this.ACCEPT_ALL = new Predicate() { // from class: com.sun.tools.javac.processing.f
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return JavacProcessingEnvironment.Round.b((JCDiagnostic) obj);
                }
            };
            this.number = i;
            if (i == 1) {
                Assert.checkNonNull(deferredDiagnosticHandler);
                this.deferredDiagnosticHandler = deferredDiagnosticHandler;
            } else {
                Log log = JavacProcessingEnvironment.this.log;
                Objects.requireNonNull(log);
                Log.DeferredDiagnosticHandler deferredDiagnosticHandler2 = new Log.DeferredDiagnosticHandler(log);
                this.deferredDiagnosticHandler = deferredDiagnosticHandler2;
                JavacProcessingEnvironment.this.compiler.setDeferredDiagnosticHandler(deferredDiagnosticHandler2);
            }
            this.topLevelClasses = List.nil();
            this.packageInfoFiles = List.nil();
            this.moduleInfoFiles = List.nil();
            this.treesToClean = set;
        }
    }
}

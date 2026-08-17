package com.sun.tools.javac.util;

import com.sun.jna.platform.win32.COM.tlb.imp.TlbBase;
import com.sun.tools.javac.api.DiagnosticFormatter;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.LintMapper;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.main.Main;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Options;
import defpackage.xa9;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Log extends AbstractLog {
    protected int MaxErrors;
    protected int MaxWarnings;
    private final EnumMap<Lint.LintCategory, WarningAggregator> aggregators;
    public boolean compressedOutput;
    private final Context context;
    private DiagnosticFormatter<JCDiagnostic> diagFormatter;
    protected DiagnosticListener<? super JavaFileObject> diagListener;
    private DiagnosticHandler diagnosticHandler;
    public boolean dumpOnError;
    public boolean emitWarnings;
    public Set<String> expectDiagKeys;
    private final LintMapper lintMapper;
    public final EnumSet<Lint.LintCategory> lintWarnings;
    private JavacMessages messages;
    public int nerrors;
    public int nsuppressederrors;
    public int nsuppressedwarns;
    public int nwarnings;
    private final Options options;
    public boolean promptOnError;
    protected Set<Pair<JavaFileObject, Integer>> recorded;
    protected Set<Pair<JavaFileObject, java.util.List<String>>> recordedSourceLevelErrors;
    private Lint rootLint;
    public boolean suppressNotes;
    private final EnumSet<Lint.LintCategory> suppressedDeferredMandatory;
    private final Map<WriterKind, PrintWriter> writers;
    public static final Context.Key<Log> logKey = new Context.Key<>();
    public static final Context.Key<PrintWriter> outKey = new Context.Key<>();
    public static final Context.Key<PrintWriter> errKey = new Context.Key<>();
    private static boolean useRawMessages = false;

    /* JADX INFO: renamed from: com.sun.tools.javac.util.Log$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Lint$LintCategory;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType;

        static {
            int[] iArr = new int[JCDiagnostic.DiagnosticType.values().length];
            $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType = iArr;
            try {
                iArr[JCDiagnostic.DiagnosticType.FRAGMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType[JCDiagnostic.DiagnosticType.NOTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType[JCDiagnostic.DiagnosticType.WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType[JCDiagnostic.DiagnosticType.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[Lint.LintCategory.values().length];
            $SwitchMap$com$sun$tools$javac$code$Lint$LintCategory = iArr2;
            try {
                iArr2[Lint.LintCategory.PREVIEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Lint$LintCategory[Lint.LintCategory.DEPRECATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public abstract class DiagnosticHandler {
        protected Map<JavaFileObject, java.util.List<JCDiagnostic>> lintWaitersMap = new LinkedHashMap();
        protected final DiagnosticHandler prev;

        public DiagnosticHandler() {
            this.prev = Log.this.diagnosticHandler;
            Log.this.diagnosticHandler = this;
        }

        public static /* synthetic */ java.util.List a(JavaFileObject javaFileObject) {
            return new LinkedList();
        }

        public static /* synthetic */ boolean b(final DiagnosticHandler diagnosticHandler, Map.Entry entry) {
            diagnosticHandler.getClass();
            JavaFileObject javaFileObject = (JavaFileObject) entry.getKey();
            if (!Log.this.lintMapper.isKnown(javaFileObject)) {
                return true;
            }
            java.util.List list = (java.util.List) entry.getValue();
            JavaFileObject javaFileObjectUseSource = Log.this.useSource(javaFileObject);
            try {
                list.removeIf(new Predicate() { // from class: ig9
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Log.DiagnosticHandler.c(this.b, (JCDiagnostic) obj);
                    }
                });
                return list.isEmpty();
            } finally {
                Log.this.useSource(javaFileObjectUseSource);
            }
        }

        public static /* synthetic */ boolean c(DiagnosticHandler diagnosticHandler, JCDiagnostic jCDiagnostic) {
            Lint lintLintFor = Log.this.lintFor(jCDiagnostic);
            if (lintLintFor == null) {
                return false;
            }
            diagnosticHandler.reportWithLint(jCDiagnostic, lintLintFor);
            return true;
        }

        public void addLintWaiter(JavaFileObject javaFileObject, JCDiagnostic jCDiagnostic) {
            this.lintWaitersMap.computeIfAbsent(javaFileObject, new Function() { // from class: jg9
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Log.DiagnosticHandler.a((JavaFileObject) obj);
                }
            }).add(jCDiagnostic);
        }

        public void flushLintWaiters() {
            this.lintWaitersMap.entrySet().removeIf(new Predicate() { // from class: hg9
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Log.DiagnosticHandler.b(this.b, (Map.Entry) obj);
                }
            });
        }

        public final void report(JCDiagnostic jCDiagnostic) {
            Lint lintRootLint;
            Lint.LintCategory lintCategory = jCDiagnostic.getLintCategory();
            if (lintCategory == null) {
                lintRootLint = null;
            } else if (jCDiagnostic.getDiagnosticPosition() == null || !lintCategory.annotationSuppression) {
                lintRootLint = Log.this.rootLint();
            } else {
                if (!Log.this.rootLint().isEnabled(lintCategory) && !jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED) && !jCDiagnostic.getCode().equals(CompilerProperties.LintWarnings.RequiresTransitiveAutomatic.key())) {
                    return;
                }
                lintRootLint = Log.this.lintFor(jCDiagnostic);
                if (lintRootLint == null) {
                    addLintWaiter(Log.this.currentSourceFile(), jCDiagnostic);
                    return;
                }
            }
            reportWithLint(jCDiagnostic, lintRootLint);
        }

        public abstract void reportReady(JCDiagnostic jCDiagnostic);

        public final void reportWithLint(JCDiagnostic jCDiagnostic, Lint lint) {
            boolean zIsEnabled;
            if (jCDiagnostic.getCode().equals(CompilerProperties.LintWarnings.RequiresTransitiveAutomatic.key()) && !lint.isEnabled(Lint.LintCategory.REQUIRES_TRANSITIVE_AUTOMATIC)) {
                reportWithLint(Log.this.diags.warning(null, jCDiagnostic.getDiagnosticSource(), jCDiagnostic.getDiagnosticPosition(), CompilerProperties.LintWarnings.RequiresAutomatic), lint);
                return;
            }
            if (lint != null) {
                Lint.LintCategory lintCategory = jCDiagnostic.getLintCategory();
                if (jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED)) {
                    zIsEnabled = !lintCategory.annotationSuppression ? Log.this.options.isDisabled(Option.XLINT, lintCategory) : lint.isSuppressed(lintCategory);
                } else {
                    zIsEnabled = lint.isEnabled(lintCategory);
                }
                if (!zIsEnabled) {
                    return;
                }
            }
            reportReady(jCDiagnostic);
        }
    }

    public class DiscardDiagnosticHandler extends DiagnosticHandler {
        public DiscardDiagnosticHandler() {
            super();
        }

        @Override // com.sun.tools.javac.util.Log.DiagnosticHandler
        public void addLintWaiter(JavaFileObject javaFileObject, JCDiagnostic jCDiagnostic) {
        }

        @Override // com.sun.tools.javac.util.Log.DiagnosticHandler
        public void reportReady(JCDiagnostic jCDiagnostic) {
        }
    }

    public enum PrefixKind {
        JAVAC("javac."),
        COMPILER_MISC("compiler.misc.");

        final String value;

        PrefixKind(String str) {
            this.value = str;
        }

        public String key(String str) {
            return this.value + str;
        }
    }

    public enum WriterKind {
        NOTICE,
        WARNING,
        ERROR,
        STDOUT,
        STDERR
    }

    private Log(Context context, Map<WriterKind, PrintWriter> map) {
        super(JCDiagnostic.Factory.instance(context));
        this.nerrors = 0;
        this.nwarnings = 0;
        this.lintWarnings = Lint.LintCategory.newEmptySet();
        this.nsuppressederrors = 0;
        this.nsuppressedwarns = 0;
        this.recorded = new HashSet();
        this.recordedSourceLevelErrors = new HashSet();
        this.aggregators = new EnumMap<>(Lint.LintCategory.class);
        this.suppressedDeferredMandatory = EnumSet.noneOf(Lint.LintCategory.class);
        context.put(logKey, this);
        this.context = context;
        Options optionsInstance = Options.instance(context);
        this.options = optionsInstance;
        this.lintMapper = LintMapper.instance(context);
        this.writers = map;
        this.diagListener = (DiagnosticListener) context.get(DiagnosticListener.class);
        this.diagnosticHandler = new DefaultDiagnosticHandler(this, null);
        JavacMessages javacMessagesInstance = JavacMessages.instance(context);
        this.messages = javacMessagesInstance;
        javacMessagesInstance.add(Main.javacBundleName);
        this.emitWarnings = true;
        this.MaxErrors = getDefaultMaxErrors();
        this.MaxWarnings = getDefaultMaxWarnings();
        this.diagFormatter = new BasicDiagnosticFormatter(this.messages);
        optionsInstance.whenReady(new Consumer() { // from class: bg9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.initOptions((Options) obj);
            }
        });
    }

    public static /* synthetic */ Log a(PrintWriter printWriter, Context context) {
        return new Log(context, printWriter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WarningAggregator aggregatorFor(Lint.LintCategory lintCategory) {
        int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$Lint$LintCategory[lintCategory.ordinal()];
        if (i == 1) {
            return this.aggregators.computeIfAbsent(lintCategory, new Function() { // from class: vf9
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Log.c(this.b, (Lint.LintCategory) obj);
                }
            });
        }
        EnumMap<Lint.LintCategory, WarningAggregator> enumMap = this.aggregators;
        return i != 2 ? enumMap.computeIfAbsent(lintCategory, new Function() { // from class: xf9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Log.d(this.b, (Lint.LintCategory) obj);
            }
        }) : enumMap.computeIfAbsent(lintCategory, new Function() { // from class: wf9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Log.b(this.b, (Lint.LintCategory) obj);
            }
        });
    }

    public static /* synthetic */ WarningAggregator b(Log log, Lint.LintCategory lintCategory) {
        log.getClass();
        return new WarningAggregator(log, null, lintCategory, "deprecated");
    }

    public static /* synthetic */ WarningAggregator c(Log log, Lint.LintCategory lintCategory) {
        return new WarningAggregator(log, Source.instance(log.context), lintCategory);
    }

    public static /* synthetic */ WarningAggregator d(Log log, Lint.LintCategory lintCategory) {
        log.getClass();
        return new WarningAggregator(log, null, lintCategory);
    }

    public static /* synthetic */ boolean e(Log log, Map.Entry entry) {
        return !log.suppressedDeferredMandatory.contains(entry.getKey());
    }

    public static String format(String str, Object... objArr) {
        return String.format(null, str, objArr);
    }

    private java.util.List<String> getCode(JCDiagnostic jCDiagnostic) {
        ListBuffer<String> listBuffer = new ListBuffer<>();
        getCodeRecursive(listBuffer, jCDiagnostic);
        return listBuffer.toList();
    }

    private void getCodeRecursive(ListBuffer<String> listBuffer, JCDiagnostic jCDiagnostic) {
        listBuffer.add(jCDiagnostic.getCode());
        for (Object obj : jCDiagnostic.getArgs()) {
            if (obj instanceof JCDiagnostic) {
                getCodeRecursive(listBuffer, (JCDiagnostic) obj);
            }
        }
    }

    private int getIntOption(Options options, Option option, int i) {
        String str = options.get(option);
        if (str != null) {
            try {
                int i2 = Integer.parseInt(str);
                if (i2 <= 0) {
                    return Integer.MAX_VALUE;
                }
                return i2;
            } catch (NumberFormatException unused) {
            }
        }
        return i;
    }

    public static String getLocalizedString(String str, Object... objArr) {
        return JavacMessages.getDefaultLocalizedString(PrefixKind.COMPILER_MISC.key(str), objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOptions(Options options) {
        this.dumpOnError = options.isSet(Option.DOE);
        this.promptOnError = options.isSet(Option.PROMPT);
        this.emitWarnings = options.isUnset(Option.NOWARN);
        this.suppressNotes = options.isSet("suppressNotes");
        this.MaxErrors = getIntOption(options, Option.XMAXERRS, getDefaultMaxErrors());
        this.MaxWarnings = getIntOption(options, Option.XMAXWARNS, getDefaultMaxWarnings());
        this.diagFormatter = options.isSet("rawDiagnostics") ? new RawDiagnosticFormatter(options) : new BasicDiagnosticFormatter(options, this.messages);
        String str = options.get("expectKeys");
        if (str != null) {
            this.expectDiagKeys = new HashSet(Arrays.asList(str.split(", *")));
        }
    }

    private static Map<WriterKind, PrintWriter> initWriters(Context context) {
        PrintWriter printWriter = (PrintWriter) context.get(outKey);
        PrintWriter printWriter2 = (PrintWriter) context.get(errKey);
        if (printWriter == null && printWriter2 == null) {
            return initWriters(new PrintWriter((OutputStream) System.out, true), new PrintWriter((OutputStream) System.err, true));
        }
        if (printWriter != null && printWriter2 != null) {
            return initWriters(printWriter, printWriter2);
        }
        if (printWriter == null) {
            printWriter = printWriter2;
        }
        return initWriters(printWriter, printWriter);
    }

    public static Log instance(Context context) {
        Log log = (Log) context.get(logKey);
        return log == null ? new Log(context) : log;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Lint lintFor(JCDiagnostic jCDiagnostic) {
        Assert.check(jCDiagnostic.getLintCategory() != null);
        return this.lintMapper.lintAt(jCDiagnostic.getSource(), jCDiagnostic.getDiagnosticPosition()).orElse(null);
    }

    public static void preRegister(Context context, final PrintWriter printWriter) {
        context.put(Log.class, new Context.Factory() { // from class: sf9
            @Override // com.sun.tools.javac.util.Context.Factory
            public final Object make(Context context2) {
                return Log.a(printWriter, context2);
            }
        });
    }

    private void printErrLine(int i, PrintWriter printWriter) {
        DiagnosticSource diagnosticSource = this.source;
        String line = diagnosticSource == null ? null : diagnosticSource.getLine(i);
        if (line == null) {
            return;
        }
        int columnNumber = this.source.getColumnNumber(i, false);
        printRawLines(printWriter, line);
        for (int i2 = 0; i2 < columnNumber - 1; i2++) {
            printWriter.print(line.charAt(i2) == '\t' ? TlbBase.TAB : " ");
        }
        printWriter.println("^");
        printWriter.flush();
    }

    private void printRawDiag(PrintWriter printWriter, String str, int i, String str2) {
        DiagnosticSource diagnosticSource = this.source;
        if (diagnosticSource == null || i == -1) {
            printRawLines(printWriter, str + str2);
        } else {
            int lineNumber = diagnosticSource.getLineNumber(i);
            JavaFileObject file = this.source.getFile();
            if (file != null) {
                printRawLines(printWriter, file.getName() + ":" + lineNumber + ": " + str2);
            }
            printErrLine(i, printWriter);
        }
        printWriter.flush();
    }

    public static void printRawLines(PrintWriter printWriter, String str) {
        while (true) {
            int iIndexOf = str.indexOf(10);
            if (iIndexOf == -1) {
                break;
            }
            printWriter.println(str.substring(0, iIndexOf));
            str = str.substring(iIndexOf + 1);
        }
        if (str.length() != 0) {
            printWriter.println(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Lint rootLint() {
        if (this.rootLint == null) {
            this.rootLint = Lint.instance(this.context);
        }
        return this.rootLint;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldReport(JCDiagnostic jCDiagnostic) {
        JavaFileObject source = jCDiagnostic.getSource();
        if (source == null) {
            return true;
        }
        if (!shouldReport(source, jCDiagnostic.getIntPosition())) {
            return false;
        }
        if (!jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.SOURCE_LEVEL)) {
            return true;
        }
        Pair<JavaFileObject, java.util.List<String>> pair = new Pair<>(source, getCode(jCDiagnostic));
        boolean zContains = this.recordedSourceLevelErrors.contains(pair);
        boolean z = !zContains;
        if (!zContains) {
            this.recordedSourceLevelErrors.add(pair);
        }
        return z;
    }

    public void clear() {
        this.recorded.clear();
        this.sourceMap.clear();
        this.lintWarnings.clear();
        this.nerrors = 0;
        this.nwarnings = 0;
        this.nsuppressederrors = 0;
        this.nsuppressedwarns = 0;
        while (true) {
            DiagnosticHandler diagnosticHandler = this.diagnosticHandler;
            if (diagnosticHandler.prev == null) {
                this.aggregators.clear();
                this.suppressedDeferredMandatory.clear();
                return;
            }
            popDiagnosticHandler(diagnosticHandler);
        }
    }

    public JavaFileObject currentSourceFile() {
        DiagnosticSource diagnosticSource = this.source;
        if (diagnosticSource == null) {
            return null;
        }
        return diagnosticSource.getFile();
    }

    @Override // com.sun.tools.javac.util.AbstractLog
    public void directError(String str, Object... objArr) {
        PrintWriter printWriter = this.writers.get(WriterKind.ERROR);
        printRawLines(printWriter, localize(str, objArr));
        printWriter.flush();
    }

    public void flush() {
        Iterator<PrintWriter> it = this.writers.values().iterator();
        while (it.hasNext()) {
            it.next().flush();
        }
    }

    public int getDefaultMaxErrors() {
        return 100;
    }

    public int getDefaultMaxWarnings() {
        return 100;
    }

    public DiagnosticFormatter<JCDiagnostic> getDiagnosticFormatter() {
        return this.diagFormatter;
    }

    public PrintWriter getWriter(WriterKind writerKind) {
        return this.writers.get(writerKind);
    }

    public PrintWriter getWriterForDiagnosticType(JCDiagnostic.DiagnosticType diagnosticType) {
        int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType[diagnosticType.ordinal()];
        if (i == 1) {
            j2d.a();
            return null;
        }
        if (i == 2) {
            return this.writers.get(WriterKind.NOTICE);
        }
        if (i == 3) {
            return this.writers.get(WriterKind.WARNING);
        }
        if (i == 4) {
            return this.writers.get(WriterKind.ERROR);
        }
        throw new Error();
    }

    public boolean hasDiagnosticListener() {
        return this.diagListener != null;
    }

    public boolean hasErrorOn(JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        DiagnosticSource diagnosticSource = this.source;
        JavaFileObject javaFileObject = diagnosticSource != null ? diagnosticSource.fileObject : null;
        return javaFileObject != null && this.recorded.contains(new Pair(javaFileObject, Integer.valueOf(diagnosticPosition.getPreferredPosition())));
    }

    public String localize(PrefixKind prefixKind, String str, Object... objArr) {
        return useRawMessages ? prefixKind.key(str) : this.messages.getLocalizedString(prefixKind.key(str), objArr);
    }

    public void popDiagnosticHandler(DiagnosticHandler diagnosticHandler) {
        Assert.check(this.diagnosticHandler == diagnosticHandler);
        Assert.check(diagnosticHandler.prev != null);
        this.diagnosticHandler = diagnosticHandler.prev;
    }

    public void printLines(String str, Object... objArr) {
        printRawLines(this.writers.get(WriterKind.NOTICE), localize(str, objArr));
    }

    public void printNewline() {
        this.writers.get(WriterKind.NOTICE).println();
    }

    public void printVerbose(String str, Object... objArr) {
        printRawLines(this.writers.get(WriterKind.NOTICE), localize("verbose." + str, objArr));
    }

    public void prompt() {
        int i;
        if (this.promptOnError) {
            System.err.println(localize("resume.abort", new Object[0]));
            do {
                try {
                    i = System.in.read();
                    if (i != 65) {
                        if (i == 82) {
                            return;
                        }
                        if (i == 88) {
                            break;
                        } else if (i != 97) {
                            if (i == 114) {
                                return;
                            }
                        }
                    }
                    System.exit(-1);
                    return;
                } catch (IOException unused) {
                    return;
                }
            } while (i != 120);
            throw new AssertionError("user abort");
        }
    }

    public void rawError(int i, String str) {
        PrintWriter printWriter = this.writers.get(WriterKind.ERROR);
        if (this.nerrors >= this.MaxErrors || !shouldReport(currentSourceFile(), i)) {
            this.nsuppressederrors++;
        } else {
            printRawDiag(printWriter, "error: ", i, str);
            prompt();
            this.nerrors++;
        }
        printWriter.flush();
    }

    public void rawWarning(int i, String str) {
        PrintWriter printWriter = this.writers.get(WriterKind.ERROR);
        if (this.emitWarnings) {
            if (this.nwarnings < this.MaxWarnings) {
                printRawDiag(printWriter, "warning: ", i, str);
            } else {
                this.nsuppressedwarns++;
            }
        }
        prompt();
        this.nwarnings++;
        printWriter.flush();
    }

    @Override // com.sun.tools.javac.util.AbstractLog
    public void report(JCDiagnostic jCDiagnostic) {
        this.diagnosticHandler.report(jCDiagnostic);
    }

    public void reportOutstandingNotes() {
        this.aggregators.entrySet().stream().filter(new Predicate() { // from class: yf9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Log.e(this.b, (Map.Entry) obj);
            }
        }).map(new Function() { // from class: com.sun.tools.javac.util.a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (WarningAggregator) ((Map.Entry) obj).getValue();
            }
        }).map(new Function() { // from class: com.sun.tools.javac.util.b
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((WarningAggregator) obj).aggregationNotes();
            }
        }).flatMap(new Function() { // from class: zf9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((List) obj).stream();
            }
        }).forEach(new Consumer() { // from class: ag9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.report((JCDiagnostic) obj);
            }
        });
        this.aggregators.clear();
    }

    public void reportOutstandingWarnings() {
        this.diagnosticHandler.flushLintWaiters();
    }

    public void setDiagnosticFormatter(DiagnosticFormatter<JCDiagnostic> diagnosticFormatter) {
        this.diagFormatter = diagnosticFormatter;
    }

    public void setEndPosTable(JavaFileObject javaFileObject, EndPosTable endPosTable) {
        Assert.checkNonNull(javaFileObject);
        getSource(javaFileObject).setEndPosTable(endPosTable);
    }

    public void setWriter(WriterKind writerKind, PrintWriter printWriter) {
        Assert.checkNonNull(printWriter);
        this.writers.put(writerKind, printWriter);
    }

    public void setWriters(PrintWriter printWriter) {
        Assert.checkNonNull(printWriter);
        for (WriterKind writerKind : WriterKind.values()) {
            this.writers.put(writerKind, printWriter);
        }
    }

    public void suppressAggregatedWarningNotes(Lint.LintCategory lintCategory) {
        this.suppressedDeferredMandatory.add(lintCategory);
    }

    public void writeDiagnostic(JCDiagnostic jCDiagnostic) {
        int i;
        int[] iArr = AnonymousClass1.$SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType;
        int i2 = iArr[jCDiagnostic.getType().ordinal()];
        if (i2 == 3) {
            this.nwarnings++;
            Optional map = Optional.of(jCDiagnostic).map(new Function() { // from class: tf9
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JCDiagnostic) obj).getLintCategory();
                }
            });
            EnumSet<Lint.LintCategory> enumSet = this.lintWarnings;
            Objects.requireNonNull(enumSet);
            map.ifPresent(new xa9(enumSet));
        } else if (i2 == 4) {
            this.nerrors++;
        }
        DiagnosticListener<? super JavaFileObject> diagnosticListener = this.diagListener;
        if (diagnosticListener != null) {
            diagnosticListener.report(jCDiagnostic);
            return;
        }
        PrintWriter writerForDiagnosticType = getWriterForDiagnosticType(jCDiagnostic.getType());
        printRawLines(writerForDiagnosticType, this.diagFormatter.format(jCDiagnostic, this.messages.getCurrentLocale()));
        if (this.promptOnError && ((i = iArr[jCDiagnostic.getType().ordinal()]) == 3 || i == 4)) {
            prompt();
        }
        if (this.dumpOnError) {
            new RuntimeException().printStackTrace(writerForDiagnosticType);
        }
        writerForDiagnosticType.flush();
    }

    public class DefaultDiagnosticHandler extends DiagnosticHandler {
        private DefaultDiagnosticHandler() {
            super();
        }

        @Override // com.sun.tools.javac.util.Log.DiagnosticHandler
        public void reportReady(JCDiagnostic jCDiagnostic) {
            JCDiagnostic jCDiagnosticRewrite;
            Set<String> set = Log.this.expectDiagKeys;
            if (set != null) {
                set.remove(jCDiagnostic.getCode());
            }
            if (jCDiagnostic.hasRewriter() && (jCDiagnosticRewrite = jCDiagnostic.rewrite()) != null) {
                jCDiagnostic = jCDiagnosticRewrite;
            }
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$util$JCDiagnostic$DiagnosticType[jCDiagnostic.getType().ordinal()];
            if (i == 1) {
                j2d.a();
                return;
            }
            if (i != 2) {
                if (i == 3) {
                    if (jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.AGGREGATE)) {
                        Lint.LintCategory lintCategory = jCDiagnostic.getLintCategory();
                        if (!Log.this.aggregatorFor(lintCategory).aggregate(jCDiagnostic, Log.this.lintFor(jCDiagnostic).isEnabled(lintCategory))) {
                            return;
                        }
                    }
                    boolean zIsFlagSet = jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.STRICT);
                    Log log = Log.this;
                    if (zIsFlagSet) {
                        log.writeDiagnostic(jCDiagnostic);
                        return;
                    } else if (log.emitWarnings || jCDiagnostic.isMandatory()) {
                        Log log2 = Log.this;
                        if (log2.nwarnings < log2.MaxWarnings) {
                            log2.writeDiagnostic(jCDiagnostic);
                        } else {
                            log2.nsuppressedwarns++;
                        }
                    }
                } else if (i == 4 && (jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.API) || Log.this.shouldReport(jCDiagnostic))) {
                    Log log3 = Log.this;
                    if (log3.nerrors < log3.MaxErrors) {
                        log3.writeDiagnostic(jCDiagnostic);
                    } else {
                        log3.nsuppressederrors++;
                    }
                }
            } else if (Log.this.emitWarnings || jCDiagnostic.isMandatory()) {
                Log log4 = Log.this;
                if (!log4.suppressNotes) {
                    log4.writeDiagnostic(jCDiagnostic);
                }
            }
            if (jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.COMPRESSED)) {
                Log.this.compressedOutput = true;
            }
        }

        public /* synthetic */ DefaultDiagnosticHandler(Log log, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public void printNewline(WriterKind writerKind) {
        getWriter(writerKind).println();
    }

    public void printLines(JCDiagnostic.DiagnosticInfo diagnosticInfo) {
        printRawLines(this.writers.get(WriterKind.NOTICE), localize(diagnosticInfo));
    }

    public String localize(JCDiagnostic.DiagnosticInfo diagnosticInfo) {
        if (useRawMessages) {
            return diagnosticInfo.key();
        }
        return this.messages.getLocalizedString(diagnosticInfo);
    }

    public void printLines(PrefixKind prefixKind, String str, Object... objArr) {
        printRawLines(this.writers.get(WriterKind.NOTICE), localize(prefixKind, str, objArr));
    }

    public void printLines(WriterKind writerKind, String str, Object... objArr) {
        printRawLines(getWriter(writerKind), localize(str, objArr));
    }

    public String localize(String str, Object... objArr) {
        return localize(PrefixKind.COMPILER_MISC, str, objArr);
    }

    public void printLines(WriterKind writerKind, PrefixKind prefixKind, String str, Object... objArr) {
        printRawLines(getWriter(writerKind), localize(prefixKind, str, objArr));
    }

    public void flush(WriterKind writerKind) {
        getWriter(writerKind).flush();
    }

    public class DeferredDiagnosticHandler extends DiagnosticHandler {
        private java.util.List<JCDiagnostic> deferred;
        private final Predicate<JCDiagnostic> filter;
        private final boolean passOnNonDeferrable;

        public DeferredDiagnosticHandler(Predicate<JCDiagnostic> predicate, boolean z) {
            super();
            this.deferred = new ArrayList();
            this.filter = (Predicate) Optional.ofNullable(predicate).orElse(new Predicate() { // from class: cg9
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Log.DeferredDiagnosticHandler.e((JCDiagnostic) obj);
                }
            });
            this.passOnNonDeferrable = z;
        }

        public static /* synthetic */ boolean d(JCDiagnostic jCDiagnostic) {
            return true;
        }

        private boolean deferrable(JCDiagnostic jCDiagnostic) {
            return !(jCDiagnostic.isFlagSet(JCDiagnostic.DiagnosticFlag.NON_DEFERRABLE) && this.passOnNonDeferrable) && this.filter.test(jCDiagnostic);
        }

        public static /* synthetic */ boolean e(JCDiagnostic jCDiagnostic) {
            return true;
        }

        public static /* synthetic */ void g(final DeferredDiagnosticHandler deferredDiagnosticHandler, Predicate predicate, final JavaFileObject javaFileObject, java.util.List list) {
            deferredDiagnosticHandler.getClass();
            list.stream().filter(predicate).forEach(new Consumer() { // from class: gg9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.prev.addLintWaiter(javaFileObject, (JCDiagnostic) obj);
                }
            });
        }

        @Override // com.sun.tools.javac.util.Log.DiagnosticHandler
        public void addLintWaiter(JavaFileObject javaFileObject, JCDiagnostic jCDiagnostic) {
            if (deferrable(jCDiagnostic)) {
                super.addLintWaiter(javaFileObject, jCDiagnostic);
            } else {
                this.prev.addLintWaiter(javaFileObject, jCDiagnostic);
            }
        }

        public java.util.List<JCDiagnostic> getDiagnostics() {
            return this.deferred;
        }

        public void reportDeferredDiagnostics(final Predicate<JCDiagnostic> predicate) {
            Stream<JCDiagnostic> streamFilter = this.deferred.stream().filter(predicate);
            final DiagnosticHandler diagnosticHandler = this.prev;
            Objects.requireNonNull(diagnosticHandler);
            streamFilter.forEach(new Consumer() { // from class: dg9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    diagnosticHandler.report((JCDiagnostic) obj);
                }
            });
            this.deferred = null;
            this.lintWaitersMap.forEach(new BiConsumer() { // from class: eg9
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    Log.DeferredDiagnosticHandler.g(this.a, predicate, (JavaFileObject) obj, (List) obj2);
                }
            });
            this.lintWaitersMap = null;
        }

        @Override // com.sun.tools.javac.util.Log.DiagnosticHandler
        public void reportReady(JCDiagnostic jCDiagnostic) {
            if (deferrable(jCDiagnostic)) {
                this.deferred.add(jCDiagnostic);
            } else {
                this.prev.reportReady(jCDiagnostic);
            }
        }

        public DeferredDiagnosticHandler(Log log, Predicate<JCDiagnostic> predicate) {
            this(predicate, true);
        }

        public DeferredDiagnosticHandler(Log log) {
            this(log, null);
        }

        public void reportDeferredDiagnostics() {
            reportDeferredDiagnostics(new Predicate() { // from class: fg9
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Log.DeferredDiagnosticHandler.d((JCDiagnostic) obj);
                }
            });
        }

        public void reportDeferredDiagnostics(Comparator<JCDiagnostic> comparator) {
            this.deferred.sort(comparator);
            reportDeferredDiagnostics();
        }
    }

    public void printRawLines(WriterKind writerKind, String str) {
        printRawLines(getWriter(writerKind), str);
    }

    public void printRawLines(String str) {
        printRawLines(this.writers.get(WriterKind.NOTICE), str);
    }

    public boolean shouldReport(JavaFileObject javaFileObject, int i) {
        if (javaFileObject == null) {
            return true;
        }
        Pair<JavaFileObject, Integer> pair = new Pair<>(javaFileObject, Integer.valueOf(i));
        boolean zContains = this.recorded.contains(pair);
        boolean z = !zContains;
        if (!zContains) {
            this.recorded.add(pair);
        }
        return z;
    }

    private static Map<WriterKind, PrintWriter> initWriters(PrintWriter printWriter, PrintWriter printWriter2) {
        EnumMap enumMap = new EnumMap(WriterKind.class);
        enumMap.put(WriterKind.ERROR, printWriter2);
        enumMap.put(WriterKind.WARNING, printWriter2);
        enumMap.put(WriterKind.NOTICE, printWriter2);
        enumMap.put(WriterKind.STDOUT, printWriter);
        enumMap.put(WriterKind.STDERR, printWriter2);
        return enumMap;
    }

    public Log(Context context, PrintWriter printWriter) {
        this(context, initWriters(printWriter, printWriter));
    }

    public Log(Context context, PrintWriter printWriter, PrintWriter printWriter2) {
        this(context, initWriters(printWriter, printWriter2));
    }

    public Log(Context context) {
        this(context, initWriters(context));
    }
}

package com.sun.tools.javap;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.classfile.Attribute;
import com.sun.tools.classfile.Attributes;
import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.FatalError;
import com.sun.tools.classfile.Field;
import com.sun.tools.classfile.InnerClasses_attribute;
import com.sun.tools.classfile.Method;
import com.sun.tools.javap.JavapTask;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.NoSuchFileException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavapTask implements DisassemblerTool.DisassemblerTask, Messages {
    static final int EXIT_ABNORMAL = 4;
    static final int EXIT_CMDERR = 2;
    static final int EXIT_ERROR = 1;
    static final int EXIT_OK = 0;
    static final int EXIT_SYSERR = 3;
    private static final String nl = System.getProperty("line.separator");
    private static final String progname = "javap";
    static final Option[] recognizedOptions;
    private static ResourceBundle versionRB = null;
    private static final String versionRBName = "com.sun.tools.javap.resources.version";
    protected Attribute.Factory attributeFactory;
    Map<Locale, ResourceBundle> bundles;
    List<String> classes;
    protected Context context;
    JavaFileManager defaultFileManager;
    DiagnosticListener<? super JavaFileObject> diagnosticListener;
    JavaFileManager fileManager;
    PrintWriter log;
    JavaFileManager.Location moduleLocation;
    Options options;
    Locale task_locale;

    /* JADX INFO: renamed from: com.sun.tools.javap.JavapTask$23, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass23 {
        static final /* synthetic */ int[] $SwitchMap$javax$tools$Diagnostic$Kind;

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
                $SwitchMap$javax$tools$Diagnostic$Kind[Diagnostic.Kind.NOTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public class BadArgs extends Exception {
        static final long serialVersionUID = 8765093759964640721L;
        final Object[] args;
        final String key;
        boolean showUsage;

        public BadArgs(String str, Object... objArr) {
            super(JavapTask.this.getMessage(str, objArr));
            this.key = str;
            this.args = objArr;
        }

        public BadArgs showUsage(boolean z) {
            this.showUsage = z;
            return this;
        }
    }

    public static class ClassFileInfo {
        public final ClassFile cf;
        public final byte[] digest;
        public final JavaFileObject fo;
        public final int size;

        public ClassFileInfo(JavaFileObject javaFileObject, ClassFile classFile, byte[] bArr, int i) {
            this.fo = javaFileObject;
            this.cf = classFile;
            this.digest = bArr;
            this.size = i;
        }
    }

    public static abstract class Option {
        final String[] aliases;
        final boolean hasArg;

        public Option(boolean z, String... strArr) {
            this.hasArg = z;
            this.aliases = strArr;
        }

        public boolean ignoreRest() {
            return false;
        }

        public boolean matches(String str) {
            for (String str2 : this.aliases) {
                if (str2.equals(str)) {
                    return true;
                }
            }
            return false;
        }

        public abstract void process(JavapTask javapTask, String str, String str2) throws BadArgs;
    }

    static {
        boolean z = false;
        recognizedOptions = new Option[]{new Option(z, "-help", "--help", "-?", "-h") { // from class: com.sun.tools.javap.JavapTask.1
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.help = true;
            }
        }, new Option(z, "-version") { // from class: com.sun.tools.javap.JavapTask.2
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.version = true;
            }
        }, new Option(z, "-fullversion") { // from class: com.sun.tools.javap.JavapTask.3
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.fullVersion = true;
            }
        }, new Option(z, "-v", "-verbose", "-all") { // from class: com.sun.tools.javap.JavapTask.4
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                Options options = javapTask.options;
                options.verbose = true;
                options.showDescriptors = true;
                options.showFlags = true;
                options.showAllAttrs = true;
            }
        }, new Option(z, "-l") { // from class: com.sun.tools.javap.JavapTask.5
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.showLineAndLocalVariableTables = true;
            }
        }, new Option(z, "-public") { // from class: com.sun.tools.javap.JavapTask.6
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.accessOptions.add(str);
                javapTask.options.showAccess = 1;
            }
        }, new Option(z, "-protected") { // from class: com.sun.tools.javap.JavapTask.7
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.accessOptions.add(str);
                javapTask.options.showAccess = 4;
            }
        }, new Option(z, "-package") { // from class: com.sun.tools.javap.JavapTask.8
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.accessOptions.add(str);
                javapTask.options.showAccess = 0;
            }
        }, new Option(z, "-p", "-private") { // from class: com.sun.tools.javap.JavapTask.9
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                if (!javapTask.options.accessOptions.contains("-p") && !javapTask.options.accessOptions.contains("-private")) {
                    javapTask.options.accessOptions.add(str);
                }
                javapTask.options.showAccess = 2;
            }
        }, new Option(z, "-c") { // from class: com.sun.tools.javap.JavapTask.10
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.showDisassembled = true;
            }
        }, new Option(z, "-s") { // from class: com.sun.tools.javap.JavapTask.11
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.showDescriptors = true;
            }
        }, new Option(z, "-sysinfo") { // from class: com.sun.tools.javap.JavapTask.12
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.sysInfo = true;
            }
        }, new Option(z, "-XDdetails") { // from class: com.sun.tools.javap.JavapTask.13
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.details = EnumSet.allOf(InstructionDetailWriter.Kind.class);
            }
        }, new Option(z, "-XDdetails:") { // from class: com.sun.tools.javap.JavapTask.14
            public boolean handleArg(JavapTask javapTask, String str) {
                boolean z2;
                if (str.length() == 0) {
                    return true;
                }
                if (str.equals("all")) {
                    javapTask.options.details = EnumSet.allOf(InstructionDetailWriter.Kind.class);
                    return true;
                }
                if (str.startsWith("-")) {
                    str = str.substring(1);
                    z2 = false;
                } else {
                    z2 = true;
                }
                for (InstructionDetailWriter.Kind kind : InstructionDetailWriter.Kind.values()) {
                    if (str.equalsIgnoreCase(kind.option)) {
                        if (z2) {
                            javapTask.options.details.add(kind);
                        } else {
                            javapTask.options.details.remove(kind);
                        }
                        return true;
                    }
                }
                return false;
            }

            @Override // com.sun.tools.javap.JavapTask.Option
            public boolean matches(String str) {
                int iIndexOf = str.indexOf(":");
                return iIndexOf != -1 && super.matches(str.substring(0, iIndexOf + 1));
            }

            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) throws BadArgs {
                for (String str3 : str.substring(str.indexOf(":") + 1).split("[,: ]+")) {
                    if (!handleArg(javapTask, str3)) {
                        Objects.requireNonNull(javapTask);
                        throw javapTask.new BadArgs("err.invalid.arg.for.option", str3);
                    }
                }
            }
        }, new Option(z, "-constants") { // from class: com.sun.tools.javap.JavapTask.15
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.showConstants = true;
            }
        }, new Option(z, "-XDinner") { // from class: com.sun.tools.javap.JavapTask.16
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) {
                javapTask.options.showInnerClasses = true;
            }
        }, new Option(z, "-XDindent:") { // from class: com.sun.tools.javap.JavapTask.17
            @Override // com.sun.tools.javap.JavapTask.Option
            public boolean matches(String str) {
                int iIndexOf = str.indexOf(":");
                return iIndexOf != -1 && super.matches(str.substring(0, iIndexOf + 1));
            }

            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) throws BadArgs {
                try {
                    int iIntValue = Integer.valueOf(str.substring(str.indexOf(":") + 1)).intValue();
                    if (iIntValue > 0) {
                        javapTask.options.indentWidth = iIntValue;
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }, new Option(z, "-XDtab:") { // from class: com.sun.tools.javap.JavapTask.18
            @Override // com.sun.tools.javap.JavapTask.Option
            public boolean matches(String str) {
                int iIndexOf = str.indexOf(":");
                return iIndexOf != -1 && super.matches(str.substring(0, iIndexOf + 1));
            }

            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) throws BadArgs {
                try {
                    int iIntValue = Integer.valueOf(str.substring(str.indexOf(":") + 1)).intValue();
                    if (iIntValue > 0) {
                        javapTask.options.tabColumn = iIntValue;
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }, new Option(true, "--module", "-m") { // from class: com.sun.tools.javap.JavapTask.19
            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) throws BadArgs {
                javapTask.options.moduleName = str2;
            }
        }, new Option(false, "-J") { // from class: com.sun.tools.javap.JavapTask.20
            @Override // com.sun.tools.javap.JavapTask.Option
            public boolean matches(String str) {
                return str.startsWith("-J");
            }

            @Override // com.sun.tools.javap.JavapTask.Option
            public void process(JavapTask javapTask, String str, String str2) throws BadArgs {
                Objects.requireNonNull(javapTask);
                throw javapTask.new BadArgs("err.only.for.launcher", new Object[0]);
            }
        }};
    }

    public JavapTask(Writer writer, JavaFileManager javaFileManager, DiagnosticListener<? super JavaFileObject> diagnosticListener, Iterable<String> iterable, Iterable<String> iterable2) {
        this(writer, javaFileManager, diagnosticListener);
        this.classes = new ArrayList();
        for (String str : iterable2) {
            Objects.requireNonNull(str);
            this.classes.add(str);
        }
        if (iterable != null) {
            try {
                handleOptions(iterable, false);
            } catch (BadArgs e) {
                w01.a(e.getMessage());
                throw null;
            }
        }
    }

    public static /* synthetic */ void a(JavapTask javapTask, PrintWriter printWriter, Diagnostic diagnostic) {
        javapTask.getClass();
        int i = AnonymousClass23.$SwitchMap$javax$tools$Diagnostic$Kind[diagnostic.getKind().ordinal()];
        if (i == 1) {
            printWriter.print(javapTask.getMessage("err.prefix", new Object[0]));
        } else if (i == 2) {
            printWriter.print(javapTask.getMessage("warn.prefix", new Object[0]));
        } else if (i == 3) {
            printWriter.print(javapTask.getMessage("note.prefix", new Object[0]));
        }
        printWriter.print(" ");
        printWriter.println(diagnostic.getMessage(null));
    }

    private Diagnostic<JavaFileObject> createDiagnostic(final Diagnostic.Kind kind, final String str, final Object... objArr) {
        return new Diagnostic<JavaFileObject>() { // from class: com.sun.tools.javap.JavapTask.22
            @Override // javax.tools.Diagnostic
            public String getCode() {
                return str;
            }

            @Override // javax.tools.Diagnostic
            public long getColumnNumber() {
                return -1L;
            }

            @Override // javax.tools.Diagnostic
            public long getEndPosition() {
                return -1L;
            }

            @Override // javax.tools.Diagnostic
            public Diagnostic.Kind getKind() {
                return kind;
            }

            @Override // javax.tools.Diagnostic
            public long getLineNumber() {
                return -1L;
            }

            @Override // javax.tools.Diagnostic
            public String getMessage(Locale locale) {
                return JavapTask.this.getMessage(locale, str, objArr);
            }

            @Override // javax.tools.Diagnostic
            public long getPosition() {
                return -1L;
            }

            @Override // javax.tools.Diagnostic
            public long getStartPosition() {
                return -1L;
            }

            public String toString() {
                return getClass().getName() + "[key=" + str + ",args=" + Arrays.asList(objArr) + "]";
            }

            @Override // javax.tools.Diagnostic
            public JavaFileObject getSource() {
                return null;
            }
        };
    }

    private JavaFileManager.Location findModule(String str) throws IOException {
        JavaFileManager.Location[] locationArr = {StandardLocation.UPGRADE_MODULE_PATH, StandardLocation.SYSTEM_MODULES, StandardLocation.MODULE_PATH};
        for (int i = 0; i < 3; i++) {
            Iterator<Set<JavaFileManager.Location>> it = this.fileManager.listLocationsForModules(locationArr[i]).iterator();
            while (it.hasNext()) {
                JavaFileManager.Location location = null;
                for (JavaFileManager.Location location2 : it.next()) {
                    if (this.fileManager.inferModuleName(location2).equals(str)) {
                        if (location != null) {
                            r8g.a("multiple definitions found for ", str);
                            return null;
                        }
                        location = location2;
                    }
                }
                if (location != null) {
                    return location;
                }
            }
        }
        return null;
    }

    private JavaFileObject getClassFileObject(String str) throws IOException {
        try {
            JavaFileManager.Location location = this.moduleLocation;
            JavaFileManager javaFileManager = this.fileManager;
            if (location != null) {
                return javaFileManager.getJavaFileForInput(location, str, JavaFileObject.Kind.CLASS);
            }
            StandardLocation standardLocation = StandardLocation.PLATFORM_CLASS_PATH;
            JavaFileObject.Kind kind = JavaFileObject.Kind.CLASS;
            JavaFileObject javaFileForInput = javaFileManager.getJavaFileForInput(standardLocation, str, kind);
            return javaFileForInput == null ? this.fileManager.getJavaFileForInput(StandardLocation.CLASS_PATH, str, kind) : javaFileForInput;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private JavaFileManager getDefaultFileManager(DiagnosticListener<? super JavaFileObject> diagnosticListener, PrintWriter printWriter) {
        if (this.defaultFileManager == null) {
            this.defaultFileManager = JavapFileManager.create(diagnosticListener, printWriter);
        }
        return this.defaultFileManager;
    }

    private DiagnosticListener<JavaFileObject> getDiagnosticListenerForStream(OutputStream outputStream) {
        return getDiagnosticListenerForWriter(getPrintWriterForStream(outputStream));
    }

    private DiagnosticListener<JavaFileObject> getDiagnosticListenerForWriter(Writer writer) {
        final PrintWriter printWriterForWriter = getPrintWriterForWriter(writer);
        return new DiagnosticListener() { // from class: zn7
            public final void report(Diagnostic diagnostic) {
                JavapTask.a(this.a, printWriterForWriter, diagnostic);
            }
        };
    }

    private static PrintWriter getPrintWriterForStream(OutputStream outputStream) {
        if (outputStream == null) {
            outputStream = System.err;
        }
        return new PrintWriter(outputStream, true);
    }

    private static PrintWriter getPrintWriterForWriter(Writer writer) {
        if (writer == null) {
            return getPrintWriterForStream(null);
        }
        return writer instanceof PrintWriter ? (PrintWriter) writer : new PrintWriter(writer, true);
    }

    private void handleOption(String str, Iterator<String> it) throws BadArgs {
        for (Option option : recognizedOptions) {
            if (option.matches(str)) {
                if (!option.hasArg) {
                    option.process(this, str, null);
                } else {
                    if (!it.hasNext()) {
                        throw new BadArgs("err.missing.arg", str).showUsage(true);
                    }
                    option.process(this, str, it.next());
                }
                if (option.ignoreRest()) {
                    while (it.hasNext()) {
                        it.next();
                    }
                    return;
                }
                return;
            }
        }
        try {
            if (!this.fileManager.handleOption(str, it)) {
                throw new BadArgs("err.unknown.option", str).showUsage(true);
            }
        } catch (IllegalArgumentException unused) {
            throw new BadArgs("err.invalid.use.of.option", str).showUsage(true);
        }
    }

    private void handleOptions(Iterable<String> iterable, boolean z) throws BadArgs {
        PrintWriter printWriter = this.log;
        if (printWriter == null) {
            this.log = getPrintWriterForStream(System.out);
            if (this.diagnosticListener == null) {
                this.diagnosticListener = getDiagnosticListenerForStream(System.err);
            }
        } else if (this.diagnosticListener == null) {
            this.diagnosticListener = getDiagnosticListenerForWriter(printWriter);
        }
        if (this.fileManager == null) {
            this.fileManager = getDefaultFileManager(this.diagnosticListener, this.log);
        }
        Iterator<String> it = iterable.iterator();
        boolean zHasNext = it.hasNext();
        while (it.hasNext()) {
            String next = it.next();
            if (next.startsWith("-")) {
                handleOption(next, it);
            } else {
                if (!z) {
                    throw new BadArgs("err.unknown.option", next).showUsage(true);
                }
                if (this.classes == null) {
                    this.classes = new ArrayList();
                }
                this.classes.add(next);
                while (it.hasNext()) {
                    this.classes.add(it.next());
                }
            }
        }
        if (this.options.accessOptions.size() > 1) {
            StringBuilder sb = new StringBuilder();
            for (String str : this.options.accessOptions) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(str);
            }
            throw new BadArgs("err.incompatible.options", sb);
        }
        List<String> list = this.classes;
        if ((list == null || list.size() == 0) && zHasNext) {
            Options options = this.options;
            if (!options.help && !options.version && !options.fullVersion) {
                throw new BadArgs("err.no.classes.specified", new Object[0]);
            }
        }
        if (!zHasNext || this.options.help) {
            showHelp();
        }
        Options options2 = this.options;
        if (options2.version || options2.fullVersion) {
            showVersion(options2.fullVersion);
        }
    }

    private void printLines(String str) {
        this.log.println(str.replace("\n", nl));
    }

    private void reportError(String str, Object... objArr) {
        this.diagnosticListener.report(createDiagnostic(Diagnostic.Kind.ERROR, str, objArr));
    }

    private void reportNote(String str, Object... objArr) {
        this.diagnosticListener.report(createDiagnostic(Diagnostic.Kind.NOTE, str, objArr));
    }

    private void reportWarning(String str, Object... objArr) {
        this.diagnosticListener.report(createDiagnostic(Diagnostic.Kind.WARNING, str, objArr));
    }

    private void showHelp() {
        printLines(getMessage("main.usage", progname));
        for (Option option : recognizedOptions) {
            String strReplaceAll = option.aliases[0].replaceAll("^-+", "").replaceAll("-+", "_");
            if (!strReplaceAll.startsWith("X") && !strReplaceAll.equals("fullversion")) {
                printLines(getMessage("main.opt.".concat(strReplaceAll), new Object[0]));
            }
        }
        String[] strArr = {"--module-path", "--system", "--class-path", "-classpath", "-cp", "-bootclasspath", "--multi-release"};
        for (int i = 0; i < 7; i++) {
            String str = strArr[i];
            if (this.fileManager.isSupportedOption(str) != -1) {
                printLines(getMessage("main.opt." + str.replaceAll("^-+", "").replaceAll("-+", "_"), new Object[0]));
            }
        }
        printLines(getMessage("main.usage.foot", new Object[0]));
    }

    private void showVersion(boolean z) {
        printLines(version(z ? "full" : "release"));
    }

    private String version(String str) {
        if (versionRB == null) {
            try {
                versionRB = ResourceBundle.getBundle(versionRBName);
            } catch (MissingResourceException unused) {
                return getMessage("version.resource.missing", System.getProperty("java.version"));
            }
        }
        try {
            return versionRB.getString(str);
        } catch (MissingResourceException unused2) {
            return getMessage("version.unknown", System.getProperty("java.version"));
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.tools.javap.DisassemblerTool.DisassemblerTask, java.util.concurrent.Callable
    public Boolean call() {
        return Boolean.valueOf(run() == 0);
    }

    @Override // com.sun.tools.javap.Messages
    public String getMessage(Locale locale, String str, Object... objArr) {
        if (this.bundles == null) {
            this.bundles = new HashMap();
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        ResourceBundle bundle = this.bundles.get(locale);
        if (bundle == null) {
            try {
                bundle = ResourceBundle.getBundle("com.sun.tools.javap.resources.javap", locale);
                this.bundles.put(locale, bundle);
            } catch (MissingResourceException unused) {
                throw new InternalError("Cannot find javap resource bundle for locale " + locale);
            }
        }
        try {
            return MessageFormat.format(bundle.getString(str), objArr);
        } catch (MissingResourceException e) {
            throw new InternalError(e, str);
        }
    }

    public JavaFileObject open(String str) throws IOException {
        JavaFileObject classFileObject;
        JavaFileObject classFileObject2 = getClassFileObject(str);
        if (classFileObject2 != null) {
            return classFileObject2;
        }
        String str2 = str;
        do {
            int iLastIndexOf = str2.lastIndexOf(Constants.ATTRVAL_THIS);
            if (iLastIndexOf == -1) {
                if (!str.endsWith(JavaClass.EXTENSION)) {
                    return null;
                }
                JavaFileManager javaFileManager = this.fileManager;
                if (javaFileManager instanceof StandardJavaFileManager) {
                    try {
                        JavaFileObject next = ((StandardJavaFileManager) javaFileManager).getJavaFileObjects(str).iterator().next();
                        if (next != null && next.getLastModified() != 0) {
                            return next;
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                }
                if (str.matches("^[A-Za-z]+:.*")) {
                    try {
                        final URI uri = new URI(str);
                        final URLConnection uRLConnectionOpenConnection = uri.toURL().openConnection();
                        uRLConnectionOpenConnection.setUseCaches(false);
                        return new JavaFileObject() { // from class: com.sun.tools.javap.JavapTask.21
                            @Override // javax.tools.FileObject
                            public boolean delete() {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.tools.JavaFileObject
                            public Modifier getAccessLevel() {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.tools.FileObject
                            public CharSequence getCharContent(boolean z) throws IOException {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.tools.JavaFileObject
                            public JavaFileObject.Kind getKind() {
                                return JavaFileObject.Kind.CLASS;
                            }

                            @Override // javax.tools.FileObject
                            public long getLastModified() {
                                return uRLConnectionOpenConnection.getLastModified();
                            }

                            @Override // javax.tools.FileObject
                            public String getName() {
                                return uri.toString();
                            }

                            @Override // javax.tools.JavaFileObject
                            public NestingKind getNestingKind() {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.tools.JavaFileObject
                            public boolean isNameCompatible(String str3, JavaFileObject.Kind kind) {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.tools.FileObject
                            public InputStream openInputStream() throws IOException {
                                return uRLConnectionOpenConnection.getInputStream();
                            }

                            @Override // javax.tools.FileObject
                            public OutputStream openOutputStream() throws IOException {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.tools.FileObject
                            public Reader openReader(boolean z) throws IOException {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.tools.FileObject
                            public Writer openWriter() throws IOException {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.tools.FileObject
                            public URI toUri() {
                                return uri;
                            }
                        };
                    } catch (IOException | URISyntaxException unused2) {
                    }
                }
                return null;
            }
            str2 = str2.substring(0, iLastIndexOf) + "$" + str2.substring(iLastIndexOf + 1);
            classFileObject = getClassFileObject(str2);
        } while (classFileObject == null);
        return classFileObject;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ClassFileInfo read(JavaFileObject javaFileObject) throws Throwable {
        MessageDigest messageDigest;
        MessageDigest messageDigest2;
        SizeInputStream sizeInputStream;
        InputStream inputStreamOpenInputStream = javaFileObject.openInputStream();
        try {
            Options options = this.options;
            byte[] bArrDigest = null;
            if (options.sysInfo || options.verbose) {
                try {
                    messageDigest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException unused) {
                    messageDigest = null;
                }
                DigestInputStream digestInputStream = new DigestInputStream(inputStreamOpenInputStream, messageDigest);
                try {
                    inputStreamOpenInputStream = new SizeInputStream(digestInputStream);
                    messageDigest2 = messageDigest;
                    sizeInputStream = inputStreamOpenInputStream;
                } catch (Throwable th) {
                    th = th;
                    inputStreamOpenInputStream = digestInputStream;
                    inputStreamOpenInputStream.close();
                    throw th;
                }
            } else {
                sizeInputStream = 0;
                messageDigest2 = null;
            }
            ClassFile classFile = ClassFile.read(inputStreamOpenInputStream, this.attributeFactory);
            if (messageDigest2 != null) {
                bArrDigest = messageDigest2.digest();
            }
            ClassFileInfo classFileInfo = new ClassFileInfo(javaFileObject, classFile, bArrDigest, sizeInputStream == 0 ? -1 : sizeInputStream.size());
            inputStreamOpenInputStream.close();
            return classFileInfo;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int run() {
        List<String> list = this.classes;
        if (list == null || list.isEmpty()) {
            return 1;
        }
        this.context.put(PrintWriter.class, this.log);
        ClassWriter classWriterInstance = ClassWriter.instance(this.context);
        SourceWriter.instance(this.context).setFileManager(this.fileManager);
        String str = this.options.moduleName;
        if (str != null) {
            try {
                JavaFileManager.Location locationFindModule = findModule(str);
                this.moduleLocation = locationFindModule;
                if (locationFindModule == null) {
                    reportError("err.cant.find.module", this.options.moduleName);
                    return 1;
                }
            } catch (IOException e) {
                reportError("err.cant.find.module.ex", this.options.moduleName, e);
                return 1;
            }
        }
        int iWriteClass = 0;
        for (String str2 : this.classes) {
            try {
                iWriteClass = writeClass(classWriterInstance, str2);
            } catch (ConstantPoolException e2) {
                reportError("err.bad.constant.pool", str2, e2.getLocalizedMessage());
                iWriteClass = 1;
            } catch (FatalError e3) {
                e = e3;
                String localizedMessage = e.getLocalizedMessage();
                if (localizedMessage != null) {
                    e = localizedMessage;
                }
                reportError("err.fatal.err", e);
                iWriteClass = 1;
            } catch (EOFException unused) {
                reportError("err.end.of.file", str2);
                iWriteClass = 1;
            } catch (FileNotFoundException | NoSuchFileException e4) {
                reportError("err.file.not.found", e4.getLocalizedMessage());
                iWriteClass = 1;
            } catch (IOException e5) {
                e = e5;
                String localizedMessage2 = e.getLocalizedMessage();
                if (localizedMessage2 != null) {
                    e = localizedMessage2;
                }
                reportError("err.ioerror", str2, e);
                iWriteClass = 1;
            } catch (OutOfMemoryError unused2) {
                reportError("err.nomem", new Object[0]);
                iWriteClass = 1;
            } catch (Throwable th) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                th.printStackTrace(printWriter);
                printWriter.close();
                reportError("err.crash", th.toString(), stringWriter.toString());
                iWriteClass = 4;
            }
        }
        return iWriteClass;
    }

    public void setClassFile(ClassFile classFile) {
        ClassWriter.instance(this.context).setClassFile(classFile);
    }

    public void setDiagnosticListener(OutputStream outputStream) {
        setDiagnosticListener(getDiagnosticListenerForStream(outputStream));
    }

    @Override // com.sun.tools.javap.DisassemblerTool.DisassemblerTask
    public void setLocale(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        this.task_locale = locale;
    }

    public void setLog(OutputStream outputStream) {
        setLog(getPrintWriterForStream(outputStream));
    }

    public void setMethod(Method method) {
        ClassWriter.instance(this.context).setMethod(method);
    }

    public void write(ClassFileInfo classFileInfo) {
        ClassWriter classWriterInstance = ClassWriter.instance(this.context);
        Options options = this.options;
        if (options.sysInfo || options.verbose) {
            classWriterInstance.setFile(classFileInfo.fo.toUri());
            classWriterInstance.setLastModified(classFileInfo.fo.getLastModified());
            classWriterInstance.setDigest("SHA-256", classFileInfo.digest);
            classWriterInstance.setFileSize(classFileInfo.size);
        }
        classWriterInstance.write(classFileInfo.cf);
    }

    public int writeClass(ClassWriter classWriter, String str) throws Throwable {
        JavaFileObject javaFileObjectOpen = open(str);
        if (javaFileObjectOpen == null) {
            reportError("err.class.not.found", str);
            return 1;
        }
        ClassFileInfo classFileInfo = read(javaFileObjectOpen);
        if (!str.endsWith(JavaClass.EXTENSION)) {
            ClassFile classFile = classFileInfo.cf;
            if (classFile.this_class == 0) {
                if (!str.equals("module-info")) {
                    reportWarning("warn.unexpected.class", javaFileObjectOpen.getName(), str);
                }
            } else if (!classFile.getName().replaceAll("[/$]", Constants.ATTRVAL_THIS).equals(str.replaceAll("[/$]", Constants.ATTRVAL_THIS))) {
                reportWarning("warn.unexpected.class", javaFileObjectOpen.getName(), str);
            }
        }
        write(classFileInfo);
        int i = 0;
        if (this.options.showInnerClasses) {
            ClassFile classFile2 = classFileInfo.cf;
            Attribute attribute = classFile2.getAttribute(Attribute.InnerClasses);
            if (attribute instanceof InnerClasses_attribute) {
                InnerClasses_attribute innerClasses_attribute = (InnerClasses_attribute) attribute;
                int iWriteClass = 0;
                while (true) {
                    try {
                        InnerClasses_attribute.Info[] infoArr = innerClasses_attribute.classes;
                        if (i >= infoArr.length) {
                            return iWriteClass;
                        }
                        if (classFile2.constant_pool.getClassInfo(infoArr[i].outer_class_info_index).getName().equals(classFile2.getName())) {
                            String name = classFile2.constant_pool.getClassInfo(innerClasses_attribute.classes[i].inner_class_info_index).getName();
                            classWriter.println("// inner class " + name.replaceAll("[/$]", Constants.ATTRVAL_THIS));
                            classWriter.println();
                            iWriteClass = writeClass(classWriter, name);
                            if (iWriteClass != 0) {
                                return iWriteClass;
                            }
                        }
                        i++;
                    } catch (ConstantPoolException unused) {
                        reportError("err.bad.innerclasses.attribute", str);
                        return 1;
                    }
                }
            } else if (attribute != null) {
                reportError("err.bad.innerclasses.attribute", str);
                return 1;
            }
        }
        return 0;
    }

    public void setDiagnosticListener(DiagnosticListener<? super JavaFileObject> diagnosticListener) {
        this.diagnosticListener = diagnosticListener;
    }

    public void setLog(Writer writer) {
        this.log = getPrintWriterForWriter(writer);
    }

    public static class SizeInputStream extends FilterInputStream {
        private int size;

        public SizeInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = super.read(bArr, i, i2);
            if (i3 > 0) {
                this.size += i3;
            }
            return i3;
        }

        public int size() {
            return this.size;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int i = super.read();
            this.size++;
            return i;
        }
    }

    public void write(Attribute attribute) {
        AttributeWriter attributeWriterInstance = AttributeWriter.instance(this.context);
        ClassFile classFile = ClassWriter.instance(this.context).getClassFile();
        attributeWriterInstance.write(classFile, attribute, classFile.constant_pool);
    }

    public JavapTask(Writer writer, JavaFileManager javaFileManager, DiagnosticListener<? super JavaFileObject> diagnosticListener) {
        this();
        this.log = getPrintWriterForWriter(writer);
        this.fileManager = javaFileManager;
        this.diagnosticListener = diagnosticListener;
    }

    public void write(Attributes attributes) {
        AttributeWriter attributeWriterInstance = AttributeWriter.instance(this.context);
        ClassFile classFile = ClassWriter.instance(this.context).getClassFile();
        attributeWriterInstance.write(classFile, attributes, classFile.constant_pool);
    }

    public JavapTask() {
        Context context = new Context();
        this.context = context;
        context.put(Messages.class, this);
        this.options = Options.instance(this.context);
        this.attributeFactory = new Attribute.Factory();
    }

    public void write(ConstantPool constantPool) {
        ConstantWriter.instance(this.context).writeConstantPool(constantPool);
    }

    public void write(ConstantPool constantPool, int i) {
        ConstantWriter.instance(this.context).write(i);
    }

    public void write(ConstantPool.CPInfo cPInfo) {
        ConstantWriter.instance(this.context).println(cPInfo);
    }

    public void write(Field field) {
        ClassWriter.instance(this.context).writeField(field);
    }

    public void write(Method method) {
        ClassWriter.instance(this.context).writeMethod(method);
    }

    @Override // com.sun.tools.javap.Messages
    public String getMessage(String str, Object... objArr) {
        return getMessage(this.task_locale, str, objArr);
    }

    public int run(String[] strArr) {
        try {
            try {
                handleOptions(strArr);
                List<String> list = this.classes;
                if (list != null && list.size() != 0) {
                    int iRun = run();
                    JavaFileManager javaFileManager = this.defaultFileManager;
                    if (javaFileManager != null) {
                        try {
                            javaFileManager.close();
                            this.defaultFileManager = null;
                        } catch (IOException e) {
                            throw new InternalError(e, new Object[0]);
                        }
                    }
                    this.log.flush();
                    return iRun;
                }
                Options options = this.options;
                if (!options.help && !options.version && !options.fullVersion) {
                    JavaFileManager javaFileManager2 = this.defaultFileManager;
                    if (javaFileManager2 != null) {
                        try {
                            javaFileManager2.close();
                            this.defaultFileManager = null;
                        } catch (IOException e2) {
                            throw new InternalError(e2, new Object[0]);
                        }
                    }
                    return 2;
                }
                JavaFileManager javaFileManager3 = this.defaultFileManager;
                if (javaFileManager3 != null) {
                    try {
                        javaFileManager3.close();
                        this.defaultFileManager = null;
                    } catch (IOException e3) {
                        throw new InternalError(e3, new Object[0]);
                    }
                }
                this.log.flush();
                return 0;
            } catch (Throwable th) {
                JavaFileManager javaFileManager4 = this.defaultFileManager;
                if (javaFileManager4 != null) {
                    try {
                        javaFileManager4.close();
                        this.defaultFileManager = null;
                    } catch (IOException e4) {
                        throw new InternalError(e4, new Object[0]);
                    }
                }
                throw th;
            }
        } catch (InternalError e5) {
            Throwable cause = e5.getCause();
            Object[] objArr = e5.args;
            if (cause != null) {
                Object[] objArr2 = new Object[objArr.length + 1];
                objArr2[0] = e5.getCause();
                Object[] objArr3 = e5.args;
                System.arraycopy(objArr3, 0, objArr2, 1, objArr3.length);
                objArr = objArr2;
            }
            reportError("err.internal.error", objArr);
            return 4;
        } catch (BadArgs e6) {
            reportError(e6.key, e6.args);
            if (e6.showUsage) {
                printLines(getMessage("main.usage.summary", progname));
            }
        } finally {
            this.log.flush();
        }
    }

    public void handleOptions(String[] strArr) throws BadArgs {
        handleOptions(Arrays.asList(strArr), true);
    }
}

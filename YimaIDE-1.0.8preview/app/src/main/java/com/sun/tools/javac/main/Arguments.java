package com.sun.tools.javac.main;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.source.util.Plugin;
import com.sun.tools.doclint.DocLint;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.file.BaseFileManager;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.jvm.Profile;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.main.Arguments;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.platform.PlatformDescription;
import com.sun.tools.javac.platform.PlatformUtils;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.PropagatedException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javax.annotation.processing.Processor;
import javax.lang.model.SourceVersion;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Arguments {
    public static final Context.Key<Arguments> argsKey = new Context.Key<>();
    private Set<String> classNames;
    private final Context context;
    private Map<Option, String> deferredFileManagerOptions;
    private boolean emptyAllowed;
    private ErrorMode errorMode;
    private boolean errors;
    private JavaFileManager fileManager;
    private Set<JavaFileObject> fileObjects;
    private Set<Path> files;
    private final Log log;
    private final Options options;
    private String ownName;
    private final OptionHelper cmdLineHelper = new OptionHelper() { // from class: com.sun.tools.javac.main.Arguments.1
        @Override // com.sun.tools.javac.main.OptionHelper
        public void addClassName(String str) {
            Arguments.this.classNames.add(str);
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public void addFile(Path path) {
            Arguments.this.files.add(path);
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public String get(Option option) {
            return Arguments.this.options.get(option);
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public Log getLog() {
            return Arguments.this.log;
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public String getOwnName() {
            return Arguments.this.ownName;
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public boolean handleFileManagerOption(Option option, String str) {
            Arguments.this.options.put(option, str);
            Arguments.this.deferredFileManagerOptions.put(option, str);
            return true;
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public void initialize() {
            Arguments.this.options.initialize();
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public void put(String str, String str2) {
            Arguments.this.options.put(str, str2);
        }

        @Override // com.sun.tools.javac.main.OptionHelper
        public void remove(String str) {
            Arguments.this.options.remove(str);
        }
    };
    private final OptionHelper apiHelper = new OptionHelper.GrumpyHelper(null) { // from class: com.sun.tools.javac.main.Arguments.2
        @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
        public String get(Option option) {
            return Arguments.this.options.get(option);
        }

        @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
        public Log getLog() {
            return Arguments.this.log;
        }

        @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
        public void initialize() {
            Arguments.this.options.initialize();
        }

        @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
        public void put(String str, String str2) {
            Arguments.this.options.put(str, str2);
        }

        @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
        public void remove(String str) {
            Arguments.this.options.remove(str);
        }
    };

    public enum ErrorMode {
        ILLEGAL_ARGUMENT,
        ILLEGAL_STATE,
        LOG
    }

    public interface ErrorReporter {
        void report(Option option);
    }

    public Arguments(Context context) {
        context.put(argsKey, this);
        this.options = Options.instance(context);
        this.log = Log.instance(context);
        this.context = context;
    }

    public static /* synthetic */ void b(Arguments arguments, Target target, Option option) {
        arguments.getClass();
        arguments.reportDiag(CompilerProperties.Errors.OptionNotAllowedWithTarget(option, target));
    }

    public static /* synthetic */ PlatformDescription c(Arguments arguments, final String str) {
        arguments.getClass();
        PlatformDescription platformDescriptionLookupPlatformDescription = PlatformUtils.lookupPlatformDescription(str);
        if (platformDescriptionLookupPlatformDescription != null) {
            return platformDescriptionLookupPlatformDescription;
        }
        int iIntValue = Integer.valueOf(str).intValue();
        final PlatformDescription platformDescriptionLookupPlatformDescription2 = null;
        while (platformDescriptionLookupPlatformDescription2 == null) {
            platformDescriptionLookupPlatformDescription2 = PlatformUtils.lookupPlatformDescription("" + iIntValue);
            iIntValue += -1;
        }
        return new PlatformDescription(arguments) { // from class: com.sun.tools.javac.main.Arguments.3
            final /* synthetic */ Arguments this$0;

            {
                this.this$0 = arguments;
            }

            @Override // com.sun.tools.javac.platform.PlatformDescription, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                platformDescriptionLookupPlatformDescription2.close();
            }

            @Override // com.sun.tools.javac.platform.PlatformDescription
            public List<String> getAdditionalOptions() {
                return platformDescriptionLookupPlatformDescription2.getAdditionalOptions();
            }

            @Override // com.sun.tools.javac.platform.PlatformDescription
            public List<PlatformDescription.PluginInfo<Processor>> getAnnotationProcessors() {
                return platformDescriptionLookupPlatformDescription2.getAnnotationProcessors();
            }

            @Override // com.sun.tools.javac.platform.PlatformDescription
            public JavaFileManager getFileManager() {
                return platformDescriptionLookupPlatformDescription2.getFileManager();
            }

            @Override // com.sun.tools.javac.platform.PlatformDescription
            public List<PlatformDescription.PluginInfo<Plugin>> getPlugins() {
                return platformDescriptionLookupPlatformDescription2.getPlugins();
            }

            @Override // com.sun.tools.javac.platform.PlatformDescription
            public String getSourceVersion() {
                return str;
            }

            @Override // com.sun.tools.javac.platform.PlatformDescription
            public String getTargetVersion() {
                return str;
            }
        };
    }

    private boolean checkDirectory(Option option) {
        String str = this.options.get(option);
        if (str == null) {
            return true;
        }
        Path path = Paths.get(str, new String[0]);
        if (!Files.exists(path, new LinkOption[0]) || Files.isDirectory(path, new LinkOption[0])) {
            return true;
        }
        reportDiag(CompilerProperties.Errors.FileNotDirectory(str));
        return false;
    }

    public static /* synthetic */ void d(Arguments arguments, Target target, Option option) {
        arguments.getClass();
        arguments.reportDiag(CompilerProperties.Errors.OptionNotAllowedWithTarget(option, target));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:20:0x0040  */
    public boolean doProcessArgs(Iterable<String> iterable, Set<Option> set, OptionHelper optionHelper, boolean z, boolean z2) {
        Option optionLookup;
        JavaFileManager fileManager = z2 ? getFileManager() : null;
        Iterator<String> it = iterable.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.length() == 0) {
                reportDiag(CompilerProperties.Errors.InvalidFlag(next));
                return false;
            }
            if (next.startsWith("-")) {
                optionLookup = Option.lookup(next, set);
            } else if (z) {
                optionLookup = Option.SOURCEFILE;
                if (!optionLookup.matches(next)) {
                    optionLookup = null;
                }
            } else {
                optionLookup = null;
            }
            if (optionLookup != null) {
                try {
                    optionLookup.handleOption(optionHelper, next, it);
                } catch (Option.InvalidValueException e) {
                    error(e);
                    return false;
                }
            } else if (fileManager == null || !fileManager.handleOption(next, it)) {
                reportDiag(CompilerProperties.Errors.InvalidFlag(next));
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ void e(Arguments arguments, Option option) {
        arguments.getClass();
        arguments.reportDiag(CompilerProperties.Errors.ReleaseBootclasspathConflict(option));
    }

    private JavaFileManager getFileManager() {
        if (this.fileManager == null) {
            this.fileManager = (JavaFileManager) this.context.get(JavaFileManager.class);
        }
        return this.fileManager;
    }

    public static Arguments instance(Context context) {
        Arguments arguments = (Arguments) context.get(argsKey);
        return arguments == null ? new Arguments(context) : arguments;
    }

    private boolean processArgs(Iterable<String> iterable, final Set<Option> set, final OptionHelper optionHelper, final boolean z, final boolean z2) {
        if (!doProcessArgs(iterable, set, optionHelper, z, z2) || !handleReleaseOptions(new Predicate() { // from class: ve0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.doProcessArgs((Iterable) obj, set, optionHelper, z, z2);
            }
        })) {
            return false;
        }
        this.options.notifyListeners();
        return true;
    }

    private JCDiagnostic.Fragment releaseNote(Source source, String str) {
        if (source.compareTo(Source.JDK8) <= 0) {
            String str2 = source.name;
            return str != null ? CompilerProperties.Fragments.SourceNoBootclasspathWithTarget(str2, str) : CompilerProperties.Fragments.SourceNoBootclasspath(str2);
        }
        String str3 = source.name;
        return str != null ? CompilerProperties.Fragments.SourceNoSystemModulesPathWithTarget(str3, str) : CompilerProperties.Fragments.SourceNoSystemModulesPath(str3);
    }

    private void report(JCDiagnostic.DiagnosticInfo diagnosticInfo) {
        if (diagnosticInfo instanceof JCDiagnostic.Error) {
            this.log.error((JCDiagnostic.Error) diagnosticInfo);
        } else if (diagnosticInfo instanceof JCDiagnostic.Warning) {
            this.log.warning((JCDiagnostic.Warning) diagnosticInfo);
        }
    }

    private void validateAddExports(SourceVersion sourceVersion) {
        Options options = this.options;
        Option option = Option.ADD_EXPORTS;
        String str = options.get(option);
        if (str != null) {
            Pattern pattern = option.getPattern();
            for (String str2 : str.split("\u0000")) {
                Matcher matcher = pattern.matcher(str2);
                if (matcher.matches()) {
                    String strGroup = matcher.group(1);
                    if (!SourceVersion.isName(strGroup, sourceVersion)) {
                        this.log.warning(CompilerProperties.Warnings.BadNameForOption(Option.ADD_EXPORTS, strGroup));
                    }
                    String strGroup2 = matcher.group(2);
                    if (!SourceVersion.isName(strGroup2, sourceVersion)) {
                        this.log.warning(CompilerProperties.Warnings.BadNameForOption(Option.ADD_EXPORTS, strGroup2));
                    }
                    for (String str3 : matcher.group(3).split(",")) {
                        str3.getClass();
                        if (!str3.equals("ALL-UNNAMED") && !str3.equals("") && !SourceVersion.isName(str3, sourceVersion)) {
                            this.log.warning(CompilerProperties.Warnings.BadNameForOption(Option.ADD_EXPORTS, str3));
                        }
                    }
                }
            }
        }
    }

    private void validateAddModules(SourceVersion sourceVersion) {
        String str = this.options.get(Option.ADD_MODULES);
        if (str != null) {
            for (String str2 : str.split(",")) {
                str2.getClass();
                switch (str2) {
                    case "ALL-SYSTEM":
                    case "":
                    case "ALL-MODULE-PATH":
                        break;
                    default:
                        if (SourceVersion.isName(str2, sourceVersion)) {
                            break;
                        } else {
                            this.log.error(CompilerProperties.Errors.BadNameForOption(Option.ADD_MODULES, str2));
                            break;
                        }
                        break;
                }
            }
        }
    }

    private void validateAddReads(SourceVersion sourceVersion) {
        Options options = this.options;
        Option option = Option.ADD_READS;
        String str = options.get(option);
        if (str != null) {
            Pattern pattern = option.getPattern();
            for (String str2 : str.split("\u0000")) {
                Matcher matcher = pattern.matcher(str2);
                if (matcher.matches()) {
                    String strGroup = matcher.group(1);
                    if (!SourceVersion.isName(strGroup, sourceVersion)) {
                        this.log.warning(CompilerProperties.Warnings.BadNameForOption(Option.ADD_READS, strGroup));
                    }
                    for (String str3 : matcher.group(2).split(",", -1)) {
                        str3.getClass();
                        if (!str3.equals("ALL-UNNAMED") && !str3.equals("") && !SourceVersion.isName(str3, sourceVersion)) {
                            this.log.warning(CompilerProperties.Warnings.BadNameForOption(Option.ADD_READS, str3));
                        }
                    }
                }
            }
        }
    }

    private void validateDefaultModuleForCreatedFiles(SourceVersion sourceVersion) {
        Options options = this.options;
        Option option = Option.DEFAULT_MODULE_FOR_CREATED_FILES;
        String str = options.get(option);
        if (str == null || SourceVersion.isName(str, sourceVersion)) {
            return;
        }
        this.log.error(CompilerProperties.Errors.BadNameForOption(option, str));
    }

    private void validateLimitModules(SourceVersion sourceVersion) {
        String str = this.options.get(Option.LIMIT_MODULES);
        if (str != null) {
            for (String str2 : str.split(",")) {
                str2.getClass();
                if (!str2.equals("") && !SourceVersion.isName(str2, sourceVersion)) {
                    this.log.error(CompilerProperties.Errors.BadNameForOption(Option.LIMIT_MODULES, str2));
                }
            }
        }
    }

    public void allowEmpty() {
        this.emptyAllowed = true;
    }

    public void checkOptionAllowed(boolean z, final ErrorReporter errorReporter, Option... optionArr) {
        if (z) {
            return;
        }
        Stream streamOf = Stream.of((Object[]) optionArr);
        final Options options = this.options;
        Objects.requireNonNull(options);
        Stream streamFilter = streamOf.filter(new Predicate() { // from class: ue0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return options.isSet((Option) obj);
            }
        });
        Objects.requireNonNull(errorReporter);
        streamFilter.forEach(new Consumer() { // from class: com.sun.tools.javac.main.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                errorReporter.report((Option) obj);
            }
        });
    }

    public void error(Option.InvalidValueException invalidValueException) {
        String message = invalidValueException.getMessage();
        this.errors = true;
        int iOrdinal = this.errorMode.ordinal();
        if (iOrdinal == 0) {
            throw new PropagatedException(new IllegalArgumentException(message, invalidValueException.getCause()));
        }
        if (iOrdinal == 1) {
            throw new PropagatedException(new IllegalStateException(message, invalidValueException.getCause()));
        }
        if (iOrdinal != 2) {
            return;
        }
        this.log.printRawLines(message);
    }

    public Set<String> getClassNames() {
        return this.classNames;
    }

    public Map<Option, String> getDeferredFileManagerOptions() {
        return this.deferredFileManagerOptions;
    }

    public com.sun.tools.javac.util.List<String> getDocLintOpts() {
        String str = this.options.get(Option.XDOCLINT);
        String str2 = this.options.get(Option.XDOCLINT_CUSTOM);
        if (str == null && str2 == null) {
            return com.sun.tools.javac.util.List.nil();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (str != null) {
            linkedHashSet.add(DocLint.XMSGS_OPTION);
        }
        if (str2 != null) {
            for (String str3 : str2.split("\\s+")) {
                if (str3.length() != 0) {
                    linkedHashSet.add(DocLint.XMSGS_CUSTOM_PREFIX.concat(str3));
                }
            }
        }
        if (linkedHashSet.equals(Collections.singleton("-Xmsgs:none"))) {
            return com.sun.tools.javac.util.List.nil();
        }
        String str4 = this.options.get(Option.XDOCLINT_PACKAGE);
        if (str4 != null) {
            linkedHashSet.add(DocLint.XCHECK_PACKAGE.concat(str4));
        }
        return com.sun.tools.javac.util.List.from((String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]));
    }

    public Set<JavaFileObject> getFileObjects() {
        if (this.fileObjects == null) {
            this.fileObjects = new LinkedHashSet();
        }
        if (this.files != null) {
            Iterator<? extends JavaFileObject> it = ((JavacFileManager) getFileManager()).getJavaFileObjectsFromPaths((Collection<? extends Path>) this.files).iterator();
            while (it.hasNext()) {
                this.fileObjects.add(it.next());
            }
        }
        return this.fileObjects;
    }

    public Set<com.sun.tools.javac.util.List<String>> getPluginOpts() {
        String str = this.options.get(Option.PLUGIN);
        if (str == null) {
            return Collections.EMPTY_SET;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str2 : str.split("\\x00")) {
            linkedHashSet.add(com.sun.tools.javac.util.List.from(str2.split("\\s+")));
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public boolean handleReleaseOptions(Predicate<Iterable<String>> predicate) {
        final String str = this.options.get(Option.RELEASE);
        boolean z = str == null;
        ErrorReporter errorReporter = new ErrorReporter() { // from class: com.sun.tools.javac.main.a
            @Override // com.sun.tools.javac.main.Arguments.ErrorReporter
            public final void report(Option option) {
                Arguments.e(this.a, option);
            }
        };
        Option option = Option.BOOT_CLASS_PATH;
        Option option2 = Option.XBOOTCLASSPATH;
        Option option3 = Option.XBOOTCLASSPATH_APPEND;
        Option option4 = Option.XBOOTCLASSPATH_PREPEND;
        Option option5 = Option.ENDORSEDDIRS;
        Option option6 = Option.DJAVA_ENDORSED_DIRS;
        Option option7 = Option.EXTDIRS;
        Option option8 = Option.DJAVA_EXT_DIRS;
        Option option9 = Option.SOURCE;
        Option option10 = Option.TARGET;
        checkOptionAllowed(z, errorReporter, option, option2, option3, option4, option5, option6, option7, option8, option9, option10, Option.SYSTEM, Option.UPGRADE_MODULE_PATH);
        if (str != null) {
            PlatformDescription platformDescription = (PlatformDescription) new Supplier() { // from class: te0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Arguments.c(this.b, str);
                }
            }.get();
            if (platformDescription == null) {
                reportDiag(CompilerProperties.Errors.UnsupportedReleaseVersion(str));
                return false;
            }
            this.options.put(option9, platformDescription.getSourceVersion());
            this.options.put(option10, platformDescription.getTargetVersion());
            this.context.put((Class<PlatformDescription>) PlatformDescription.class, platformDescription);
            if (!predicate.test(platformDescription.getAdditionalOptions())) {
                return false;
            }
            DelegatingJavaFileManager.installReleaseFileManager(this.context, platformDescription.getFileManager(), getFileManager());
        }
        return true;
    }

    public void init(String str, Iterable<String> iterable) {
        this.ownName = str;
        this.errorMode = ErrorMode.LOG;
        this.files = new LinkedHashSet();
        this.deferredFileManagerOptions = new LinkedHashMap();
        this.fileObjects = null;
        this.classNames = new LinkedHashSet();
        processArgs(iterable, Option.getJavaCompilerOptions(), this.cmdLineHelper, true, false);
        if (this.errors) {
            this.log.printLines(Log.PrefixKind.JAVAC, "msg.usage", str);
        }
    }

    public boolean isEmpty() {
        Set<Path> set = this.files;
        if (set != null && !set.isEmpty()) {
            return false;
        }
        Set<JavaFileObject> set2 = this.fileObjects;
        if (set2 != null && !set2.isEmpty()) {
            return false;
        }
        Set<String> set3 = this.classNames;
        return set3 == null || set3.isEmpty();
    }

    public void reportDiag(JCDiagnostic.DiagnosticInfo diagnosticInfo) {
        this.errors = true;
        int iOrdinal = this.errorMode.ordinal();
        if (iOrdinal == 0) {
            throw new PropagatedException(new IllegalArgumentException(this.log.localize(diagnosticInfo)));
        }
        if (iOrdinal == 1) {
            throw new PropagatedException(new IllegalStateException(this.log.localize(diagnosticInfo)));
        }
        if (iOrdinal != 2) {
            return;
        }
        report(diagnosticInfo);
    }

    public <T> ListBuffer<T> toList(Iterable<? extends T> iterable) {
        ListBuffer<T> listBuffer = new ListBuffer<>();
        if (iterable != null) {
            Iterator<? extends T> it = iterable.iterator();
            while (it.hasNext()) {
                listBuffer.add(it.next());
            }
        }
        return listBuffer;
    }

    public <T> Set<T> toSet(Iterable<? extends T> iterable) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (iterable != null) {
            Iterator<? extends T> it = iterable.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(it.next());
            }
        }
        return linkedHashSet;
    }

    /* JADX WARN: Code duplicated, block: B:145:0x02dd  */
    /* JADX WARN: Code duplicated, block: B:146:0x02e7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:147:0x02e9  */
    /* JADX WARN: Code duplicated, block: B:150:0x02fb  */
    /* JADX WARN: Code duplicated, block: B:151:0x02fd  */
    /* JADX WARN: Code duplicated, block: B:154:0x0330  */
    /* JADX WARN: Code duplicated, block: B:155:0x0333  */
    /* JADX WARN: Code duplicated, block: B:169:0x0390  */
    /* JADX WARN: Code duplicated, block: B:172:0x03b2  */
    public boolean validate() {
        boolean z;
        Target target;
        boolean z2;
        boolean z3;
        Option option;
        JavaFileManager fileManager = getFileManager();
        Options options = this.options;
        Option option2 = Option.MODULE;
        if (options.isSet(option2)) {
            if (!fileManager.hasLocation(StandardLocation.CLASS_OUTPUT)) {
                this.log.error(CompilerProperties.Errors.OutputDirMustBeSpecifiedWithDashMOption);
            } else if (fileManager.hasLocation(StandardLocation.MODULE_SOURCE_PATH)) {
                try {
                    for (String str : Arrays.asList(this.options.get(option2).split(","))) {
                        JavaFileManager.Location locationForModule = fileManager.getLocationForModule(StandardLocation.MODULE_SOURCE_PATH, str);
                        if (locationForModule == null) {
                            this.log.error(CompilerProperties.Errors.ModuleNotFoundInModuleSourcePath(str));
                        } else {
                            JavaFileManager.Location locationForModule2 = fileManager.getLocationForModule(StandardLocation.CLASS_OUTPUT, str);
                            for (JavaFileObject javaFileObject : fileManager.list(locationForModule, "", EnumSet.of(JavaFileObject.Kind.SOURCE), true)) {
                                JavaFileObject javaFileForInput = fileManager.getJavaFileForInput(locationForModule2, fileManager.inferBinaryName(locationForModule, javaFileObject), JavaFileObject.Kind.CLASS);
                                if (javaFileForInput == null || javaFileForInput.getLastModified() < javaFileObject.getLastModified()) {
                                    if (this.fileObjects == null) {
                                        this.fileObjects = new HashSet();
                                    }
                                    this.fileObjects.add(javaFileObject);
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    this.log.printLines(Log.PrefixKind.JAVAC, "msg.io", new Object[0]);
                    e.printStackTrace(this.log.getWriter(Log.WriterKind.NOTICE));
                    return false;
                }
            } else {
                this.log.error(CompilerProperties.Errors.ModulesourcepathMustBeSpecifiedWithDashMOption);
            }
        }
        if (isEmpty()) {
            if (this.options.isSet(Option.HELP) || this.options.isSet(Option.X) || this.options.isSet(Option.HELP_LINT) || this.options.isSet(Option.VERSION) || this.options.isSet(Option.FULLVERSION) || this.options.isSet(Option.MODULE)) {
                return true;
            }
            if (!this.emptyAllowed) {
                if (!this.errors) {
                    if (JavaCompiler.explicitAnnotationProcessingRequested(this.options, this.fileManager)) {
                        reportDiag(CompilerProperties.Errors.NoSourceFilesClasses);
                    } else {
                        reportDiag(CompilerProperties.Errors.NoSourceFiles);
                    }
                }
                return false;
            }
        }
        if (!checkDirectory(Option.D) || !checkDirectory(Option.S) || !checkDirectory(Option.H)) {
            return false;
        }
        if (fileManager instanceof StandardJavaFileManager) {
            StandardJavaFileManager standardJavaFileManager = (StandardJavaFileManager) fileManager;
            StandardLocation standardLocation = StandardLocation.CLASS_OUTPUT;
            if (standardJavaFileManager.hasLocation(standardLocation)) {
                Path next = standardJavaFileManager.getLocationAsPaths(standardLocation).iterator().next();
                if (!standardJavaFileManager.hasLocation(StandardLocation.MODULE_SOURCE_PATH)) {
                    Path parent = next.getParent();
                    if (parent != null && Files.exists(parent.resolve("module-info.class"), new LinkOption[0])) {
                        this.log.warning(CompilerProperties.LintWarnings.OutdirIsInExplodedModule(next));
                    }
                } else if (Files.exists(next.resolve("module-info.class"), new LinkOption[0])) {
                    this.log.error(CompilerProperties.Errors.MultiModuleOutdirCannotBeExplodedModule(next));
                }
            }
        }
        String str2 = this.options.get(Option.SOURCE);
        Source sourceLookup = str2 != null ? Source.lookup(str2) : Source.DEFAULT;
        String str3 = this.options.get(Option.TARGET);
        final Target targetLookup = str3 != null ? Target.lookup(str3) : Target.DEFAULT;
        if (Character.isDigit(targetLookup.name.charAt(0)) && targetLookup.compareTo(sourceLookup.requiredTarget()) < 0) {
            if (str3 != null) {
                if (str2 == null) {
                    reportDiag(CompilerProperties.Errors.TargetDefaultSourceConflict(sourceLookup.name, str3));
                } else {
                    reportDiag(CompilerProperties.Errors.SourceTargetConflict(str2, str3));
                }
                return false;
            }
            targetLookup = sourceLookup.requiredTarget();
            this.options.put("-target", targetLookup.name);
        }
        if (this.options.isSet(Option.PREVIEW)) {
            if (str2 == null) {
                report(CompilerProperties.Errors.PreviewWithoutSourceOrRelease);
                return false;
            }
            Source source = Source.DEFAULT;
            if (sourceLookup != source) {
                report(CompilerProperties.Errors.PreviewNotLatest(str2, source));
                return false;
            }
        }
        Options options2 = this.options;
        Option option3 = Option.PROFILE;
        String str4 = options2.get(option3);
        if (str4 != null) {
            Profile profileLookup = Profile.lookup(str4);
            if (targetLookup.compareTo(Target.JDK1_8) <= 0 && !profileLookup.isValid(targetLookup)) {
                reportDiag(CompilerProperties.Warnings.ProfileTargetConflict(profileLookup, targetLookup));
            }
            if (this.options.get(Option.BOOT_CLASS_PATH) != null) {
                reportDiag(CompilerProperties.Errors.ProfileBootclasspathConflict);
            }
        }
        if (this.options.isSet(Option.SOURCE_PATH) && this.options.isSet(Option.MODULE_SOURCE_PATH)) {
            reportDiag(CompilerProperties.Errors.SourcepathModulesourcepathConflict);
        }
        if (sourceLookup.compareTo(Source.DEFAULT) < 0 && !this.options.isSet(Option.RELEASE) && (fileManager instanceof BaseFileManager)) {
            BaseFileManager baseFileManager = (BaseFileManager) fileManager;
            if (sourceLookup.compareTo(Source.JDK8) <= 0) {
                if (baseFileManager.isDefaultBootClassPath()) {
                    this.log.warning(CompilerProperties.LintWarnings.SourceNoBootclasspath(sourceLookup.name, releaseNote(sourceLookup, str3)));
                }
            } else if (baseFileManager.isDefaultSystemModulesPath()) {
                this.log.warning(CompilerProperties.LintWarnings.SourceNoSystemModulesPath(sourceLookup.name, releaseNote(sourceLookup, str3)));
            }
        }
        Source source2 = Source.MIN;
        if (sourceLookup.compareTo(source2) >= 0) {
            if (sourceLookup == source2) {
                this.log.warning(CompilerProperties.LintWarnings.OptionObsoleteSource(sourceLookup.name));
                z = true;
            }
            target = Target.MIN;
            if (targetLookup.compareTo(target) < 0) {
                this.log.error(CompilerProperties.Errors.OptionRemovedTarget(targetLookup, target));
            } else if (targetLookup == target) {
                this.log.warning(CompilerProperties.LintWarnings.OptionObsoleteTarget(targetLookup));
                z = true;
            }
            if (targetLookup.compareTo(Target.JDK1_8) <= 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            checkOptionAllowed(z2, new ErrorReporter() { // from class: com.sun.tools.javac.main.c
                @Override // com.sun.tools.javac.main.Arguments.ErrorReporter
                public final void report(Option option4) {
                    Arguments.d(this.a, targetLookup, option4);
                }
            }, Option.BOOT_CLASS_PATH, Option.XBOOTCLASSPATH_PREPEND, Option.XBOOTCLASSPATH, Option.XBOOTCLASSPATH_APPEND, Option.ENDORSEDDIRS, Option.DJAVA_ENDORSED_DIRS, Option.EXTDIRS, Option.DJAVA_EXT_DIRS, option3);
            if (targetLookup.compareTo(Target.JDK1_9) >= 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            ErrorReporter errorReporter = new ErrorReporter() { // from class: com.sun.tools.javac.main.d
                @Override // com.sun.tools.javac.main.Arguments.ErrorReporter
                public final void report(Option option4) {
                    Arguments.b(this.a, targetLookup, option4);
                }
            };
            Option option4 = Option.MODULE_SOURCE_PATH;
            Option option5 = Option.UPGRADE_MODULE_PATH;
            Option option6 = Option.SYSTEM;
            Option option7 = Option.MODULE_PATH;
            Option option8 = Option.ADD_MODULES;
            Option option9 = Option.ADD_EXPORTS;
            option = Option.ADD_OPENS;
            checkOptionAllowed(z3, errorReporter, option4, option5, option6, option7, option8, option9, option, Option.ADD_READS, Option.LIMIT_MODULES, Option.PATCH_MODULE);
            if (fileManager.hasLocation(StandardLocation.MODULE_SOURCE_PATH) && !this.options.isSet(Option.PROC, Constants.ATTRNAME_ONLY) && !fileManager.hasLocation(StandardLocation.CLASS_OUTPUT)) {
                this.log.error(CompilerProperties.Errors.NoOutputDir);
            }
            if (fileManager.hasLocation(StandardLocation.ANNOTATION_PROCESSOR_MODULE_PATH) && fileManager.hasLocation(StandardLocation.ANNOTATION_PROCESSOR_PATH)) {
                this.log.error(CompilerProperties.Errors.ProcessorpathNoProcessormodulepath);
            }
            if (z) {
                this.log.warning(CompilerProperties.LintWarnings.OptionObsoleteSuppression);
            }
            SourceVersion sourceVersion = Source.toSourceVersion(sourceLookup);
            validateAddExports(sourceVersion);
            validateAddModules(sourceVersion);
            validateAddReads(sourceVersion);
            validateLimitModules(sourceVersion);
            validateDefaultModuleForCreatedFiles(sourceVersion);
            if (this.options.isSet(option)) {
                this.log.warning(CompilerProperties.LintWarnings.AddopensIgnored);
            }
            return this.errors && this.log.nerrors == 0;
        }
        this.log.error(CompilerProperties.Errors.OptionRemovedSource(sourceLookup.name, source2.name));
        z = false;
        target = Target.MIN;
        if (targetLookup.compareTo(target) < 0) {
            this.log.error(CompilerProperties.Errors.OptionRemovedTarget(targetLookup, target));
        } else if (targetLookup == target) {
            this.log.warning(CompilerProperties.LintWarnings.OptionObsoleteTarget(targetLookup));
            z = true;
        }
        if (targetLookup.compareTo(Target.JDK1_8) <= 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        checkOptionAllowed(z2, new ErrorReporter() { // from class: com.sun.tools.javac.main.c
            @Override // com.sun.tools.javac.main.Arguments.ErrorReporter
            public final void report(Option option10) {
                Arguments.d(this.a, targetLookup, option10);
            }
        }, Option.BOOT_CLASS_PATH, Option.XBOOTCLASSPATH_PREPEND, Option.XBOOTCLASSPATH, Option.XBOOTCLASSPATH_APPEND, Option.ENDORSEDDIRS, Option.DJAVA_ENDORSED_DIRS, Option.EXTDIRS, Option.DJAVA_EXT_DIRS, option3);
        if (targetLookup.compareTo(Target.JDK1_9) >= 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        ErrorReporter errorReporter2 = new ErrorReporter() { // from class: com.sun.tools.javac.main.d
            @Override // com.sun.tools.javac.main.Arguments.ErrorReporter
            public final void report(Option option10) {
                Arguments.b(this.a, targetLookup, option10);
            }
        };
        Option option10 = Option.MODULE_SOURCE_PATH;
        Option option11 = Option.UPGRADE_MODULE_PATH;
        Option option12 = Option.SYSTEM;
        Option option13 = Option.MODULE_PATH;
        Option option14 = Option.ADD_MODULES;
        Option option15 = Option.ADD_EXPORTS;
        option = Option.ADD_OPENS;
        checkOptionAllowed(z3, errorReporter2, option10, option11, option12, option13, option14, option15, option, Option.ADD_READS, Option.LIMIT_MODULES, Option.PATCH_MODULE);
        if (fileManager.hasLocation(StandardLocation.MODULE_SOURCE_PATH)) {
            this.log.error(CompilerProperties.Errors.NoOutputDir);
        }
        if (fileManager.hasLocation(StandardLocation.ANNOTATION_PROCESSOR_MODULE_PATH)) {
            this.log.error(CompilerProperties.Errors.ProcessorpathNoProcessormodulepath);
        }
        if (z) {
            this.log.warning(CompilerProperties.LintWarnings.OptionObsoleteSuppression);
        }
        SourceVersion sourceVersion2 = Source.toSourceVersion(sourceLookup);
        validateAddExports(sourceVersion2);
        validateAddModules(sourceVersion2);
        validateAddReads(sourceVersion2);
        validateLimitModules(sourceVersion2);
        validateDefaultModuleForCreatedFiles(sourceVersion2);
        if (this.options.isSet(option)) {
            this.log.warning(CompilerProperties.LintWarnings.AddopensIgnored);
        }
        if (this.errors) {
        }
    }

    public void init(String str, Iterable<String> iterable, Iterable<String> iterable2, Iterable<? extends JavaFileObject> iterable3) {
        Arguments arguments;
        this.ownName = str;
        this.classNames = toSet(iterable2);
        this.fileObjects = toSet(iterable3);
        this.files = null;
        this.errorMode = ErrorMode.ILLEGAL_ARGUMENT;
        if (iterable != null) {
            arguments = this;
            arguments.processArgs(toList(iterable), Option.getJavacToolOptions(), this.apiHelper, false, true);
        } else {
            arguments = this;
        }
        arguments.errorMode = ErrorMode.ILLEGAL_STATE;
    }

    public void init(String str) {
        this.ownName = str;
        this.errorMode = ErrorMode.LOG;
    }
}

package com.sun.tools.javac.main;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.sun.tools.doclint.DocLint;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.jvm.Profile;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.platform.PlatformProvider;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.StringUtils;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.lang.model.SourceVersion;
import nbjavac.ModuleWrapper;
import nbjavac.ServiceLoaderWrapper;
import nbjavac.VMWrapper;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'G' uses external variables
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
/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Option {
    private static final /* synthetic */ Option[] $VALUES;
    public static final Option A;
    public static final Option ADD_EXPORTS;
    public static final Option ADD_MODULES;
    public static final Option ADD_OPENS;
    public static final Option ADD_READS;
    public static final Option AT;
    public static final Option BOOT_CLASS_PATH;
    public static final Option CLASS_PATH;
    private static final String COMPACT_FORMAT = "  %-28s %s";
    public static final Option D;
    public static final Option DEBUG;
    private static final int DEFAULT_MAX_LINE_LENGTH = 80;
    public static final Option DEFAULT_MODULE_FOR_CREATED_FILES;
    private static final int DEFAULT_SYNOPSIS_WIDTH = 28;
    public static final Option DEPRECATION;
    public static final Option DIAGS;
    public static final Option DISABLE_LINE_DOC_COMMENTS;
    public static final Option DJAVA_ENDORSED_DIRS;
    public static final Option DJAVA_EXT_DIRS;
    public static final Option DOE;
    public static final Option ENCODING;
    public static final Option ENDORSEDDIRS;
    public static final Option EXTDIRS;
    public static final Option FULLVERSION;
    public static final Option G;
    public static final Option G_CUSTOM;
    public static final Option G_NONE;
    public static final Option H;
    public static final Option HELP;
    public static final Option HELP_LINT;
    public static final Option IMPLICIT;
    public static final Option INHERIT_RUNTIME_ENVIRONMENT;
    public static final Option J;
    private static final String LARGE_INDENT = "        ";
    public static final Option LIMIT_MODULES;
    public static final String LINT_CUSTOM_ALL = "all";
    public static final String LINT_CUSTOM_NONE = "none";
    public static final Option MODULE;
    public static final Option MODULE_PATH;
    public static final Option MODULE_SOURCE_PATH;
    public static final Option MODULE_VERSION;
    public static final Option MOREINFO;
    public static final Option MULTIRELEASE;
    public static final Option NOWARN;
    public static final Option O;
    public static final Option PARAMETERS;
    public static final Option PATCH_MODULE;
    public static final Option PLUGIN;
    public static final Option PREVIEW;
    public static final Option PRINTSOURCE;
    public static final Option PROC;
    public static final Option PROCESSOR;
    public static final Option PROCESSOR_MODULE_PATH;
    public static final Option PROCESSOR_PATH;
    public static final Option PROFILE;
    public static final Option PROMPT;
    public static final Option RELEASE;
    public static final Option S;
    public static final Option SHOULDSTOP;
    private static final String SMALL_INDENT = "  ";
    public static final Option SOURCE;
    public static final Option SOURCEFILE;
    public static final Option SOURCE_PATH;
    public static final Option SYSTEM;
    public static final Option TARGET;
    public static final Option UPGRADE_MODULE_PATH;
    public static final Option VERBOSE;
    public static final Option VERSION;
    public static final Option WARNUNCHECKED;
    public static final Option WERROR;
    public static final Option WERROR_CUSTOM;
    public static final Option X;
    public static final Option XBOOTCLASSPATH;
    public static final Option XBOOTCLASSPATH_APPEND;
    public static final Option XBOOTCLASSPATH_PREPEND;
    public static final Option XD;
    public static final Option XDIAGS;
    public static final Option XDOCLINT;
    public static final Option XDOCLINT_CUSTOM;
    public static final Option XDOCLINT_PACKAGE;
    public static final Option XJCOV;
    public static final Option XLINT;
    public static final Option XLINT_CUSTOM;
    public static final Option XMAXERRS;
    public static final Option XMAXWARNS;
    public static final Option XPKGINFO;
    public static final Option XPREFER;
    public static final Option XPRINT;
    public static final Option XPRINTPROCESSORINFO;
    public static final Option XPRINTROUNDS;
    public static final Option XSTDOUT;
    public static final Option XXUSERPATHSFIRST;
    private final ArgKind argKind;
    protected final String argsNameKey;
    private final ChoiceKind choiceKind;
    private final Set<String> choices;
    protected final String descrKey;
    private final OptionGroup group;
    private final OptionKind kind;
    public final String[] names;
    public final String primaryName;

    /* JADX INFO: renamed from: com.sun.tools.javac.main.Option$13, reason: invalid class name */
    public final enum AnonymousClass13 extends Option {
        public AnonymousClass13(String str, int i, String str2, String str3, String str4, OptionKind optionKind, OptionGroup optionGroup) {
            super(str, i, str2, str3, str4, optionKind, optionGroup);
        }

        @Override // com.sun.tools.javac.main.Option
        public void help(Log log) {
            super.help(log, log.localize(Log.PrefixKind.JAVAC, this.descrKey, Option.formatAbbreviatedList((Set) StreamSupport.stream(ServiceLoaderWrapper.loadTool(PlatformProvider.class).spliterator(), false).flatMap(new Function() { // from class: com.sun.tools.javac.main.f
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return StreamSupport.stream(((PlatformProvider) obj).getSupportedPlatformNames().spliterator(), false);
                }
            }).collect(Collectors.toCollection(new emc())))));
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.main.Option$21, reason: invalid class name */
    public final enum AnonymousClass21 extends Option {
        private final String HELP_INDENT;
        private final String LINT_KEY_FORMAT;

        public AnonymousClass21(String str, int i, String str2, String str3, OptionKind optionKind, OptionGroup optionGroup) {
            super(str, i, str2, str3, optionKind, optionGroup);
            this.HELP_INDENT = "    ";
            this.LINT_KEY_FORMAT = "    %-20s %s";
        }

        public static /* synthetic */ void r(final AnonymousClass21 anonymousClass21, final TreeMap treeMap, final Log log, final Lint.LintCategory lintCategory) {
            anonymousClass21.getClass();
            lintCategory.optionList.stream().forEach(new Consumer() { // from class: com.sun.tools.javac.main.k
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Option.AnonymousClass21 anonymousClass22 = this.b;
                    TreeMap treeMap2 = treeMap;
                    Lint.LintCategory lintCategory2 = lintCategory;
                    Log log2 = log;
                    String str = (String) obj;
                    treeMap2.put(str, String.format(anonymousClass22.LINT_KEY_FORMAT, str, str.equals(lintCategory2.option) ? log2.localize(Log.PrefixKind.JAVAC, "opt.Xlint.desc.".concat(str), new Object[0]) : log2.localize(Log.PrefixKind.JAVAC, "opt.Xlint.alias.of", lintCategory2.option, str)));
                }
            });
        }

        @Override // com.sun.tools.javac.main.Option
        public void process(OptionHelper optionHelper, String str) throws InvalidValueException {
            final Log log = optionHelper.getLog();
            Log.WriterKind writerKind = Log.WriterKind.STDOUT;
            Log.PrefixKind prefixKind = Log.PrefixKind.JAVAC;
            log.printRawLines(writerKind, log.localize(prefixKind, "opt.help.lint.header", new Object[0]));
            log.printRawLines(writerKind, String.format(this.LINT_KEY_FORMAT, "all", log.localize(prefixKind, "opt.Xlint.all", new Object[0])));
            final TreeMap treeMap = new TreeMap();
            Stream.of((Object[]) Lint.LintCategory.values()).forEach(new Consumer() { // from class: com.sun.tools.javac.main.g
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    Option.AnonymousClass21.r(this.b, treeMap, log, (Lint.LintCategory) obj);
                }
            });
            treeMap.values().forEach(new Consumer() { // from class: com.sun.tools.javac.main.h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    log.printRawLines(Log.WriterKind.STDOUT, (String) obj);
                }
            });
            log.printRawLines(writerKind, String.format(this.LINT_KEY_FORMAT, Option.LINT_CUSTOM_NONE, log.localize(prefixKind, "opt.Xlint.none", new Object[0])));
            log.printRawLines(writerKind, log.localize(prefixKind, "opt.help.lint.enabled.by.default", new Object[0]));
            log.printRawLines(writerKind, String.format("%s%s.", "    ", (String) Stream.of((Object[]) Lint.LintCategory.values()).filter(new Predicate() { // from class: com.sun.tools.javac.main.i
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Lint.LintCategory) obj).enabledByDefault;
                }
            }).map(new Function() { // from class: com.sun.tools.javac.main.j
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Lint.LintCategory) obj).option;
                }
            }).sorted().collect(Collectors.joining(", "))));
            List<String> list = Lint.LintCategory.IDENTITY.optionList;
            log.printRawLines(writerKind, log.localize(prefixKind, "opt.help.lint.footer", list.get(0), list.get(1)));
            super.process(optionHelper, str);
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.main.Option$38, reason: invalid class name */
    public final enum AnonymousClass38 extends Option {
        public AnonymousClass38(String str, int i, String str2, String str3, OptionKind optionKind, OptionGroup optionGroup) {
            super(str, i, str2, str3, optionKind, optionGroup);
        }

        private Option[] getSupportedRuntimeOptions() {
            return new Option[]{Option.ADD_EXPORTS, Option.ADD_MODULES, Option.LIMIT_MODULES, Option.MODULE_PATH, Option.UPGRADE_MODULE_PATH, Option.PATCH_MODULE};
        }

        public static /* synthetic */ boolean r(String str) {
            return (str.length() == 0 || str.equals("ALL-DEFAULT")) ? false : true;
        }

        public static /* synthetic */ String t(String str) {
            return "invalid runtime option:" + str;
        }

        @Override // com.sun.tools.javac.main.Option
        public void process(OptionHelper optionHelper, String str) throws InvalidValueException {
            for (final String str2 : VMWrapper.getRuntimeArguments()) {
                for (Option option : getSupportedRuntimeOptions()) {
                    if (option.matches(str2)) {
                        if (AnonymousClass40.$SwitchMap$com$sun$tools$javac$main$Option[option.ordinal()] == 1) {
                            int iIndexOf = str2.indexOf(61);
                            Assert.check(iIndexOf > 0, (Supplier<String>) new Supplier() { // from class: com.sun.tools.javac.main.l
                                @Override // java.util.function.Supplier
                                public final Object get() {
                                    return Option.AnonymousClass38.t(str2);
                                }
                            });
                            int i = iIndexOf + 1;
                            String str3 = (String) Arrays.stream(str2.substring(i).split(",")).filter(new Predicate() { // from class: com.sun.tools.javac.main.m
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    return Option.AnonymousClass38.r((String) obj);
                                }
                            }).collect(Collectors.joining(","));
                            if (str3.length() == 0) {
                                break;
                            }
                            option.handleOption(optionHelper, str2.substring(0, i).concat(str3), Collections.emptyIterator());
                            break;
                        }
                        option.handleOption(optionHelper, str2, Collections.emptyIterator());
                        break;
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.main.Option$40, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass40 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$main$Option;

        static {
            int[] iArr = new int[Option.values().length];
            $SwitchMap$com$sun$tools$javac$main$Option = iArr;
            try {
                iArr[Option.ADD_MODULES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.main.Option$5, reason: invalid class name */
    public final enum AnonymousClass5 extends Option {
        public AnonymousClass5(String str, int i, String str2, String str3, String str4, OptionKind optionKind, OptionGroup optionGroup) {
            super(str, i, str2, str3, str4, optionKind, optionGroup);
        }

        public static /* synthetic */ boolean D(Pattern pattern, String str) {
            return !pattern.matcher(str).matches();
        }

        @Override // com.sun.tools.javac.main.Option
        public Pattern getPattern() {
            return Pattern.compile("([\\p{Alnum}$_.]+)=(.*)");
        }

        @Override // com.sun.tools.javac.main.Option
        public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
            if (str2.length() == 0) {
                throw optionHelper.newInvalidValueException(CompilerProperties.Errors.NoValueForOption(str));
            }
            final Pattern pattern = getPattern();
            String str3 = optionHelper.get(Option.MODULE_SOURCE_PATH);
            if (str3 == null) {
                super.process(optionHelper, str, str2);
                return;
            }
            if (!pattern.matcher(str2).matches()) {
                if (Arrays.stream(str3.split("\u0000")).anyMatch(new Predicate() { // from class: com.sun.tools.javac.main.q
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Option.AnonymousClass5.D(pattern, (String) obj);
                    }
                })) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.MultipleValuesForModuleSourcePath);
                }
                super.process(optionHelper, str, str3 + (char) 0 + str2);
                return;
            }
            final String strSubstring = str2.substring(0, str2.indexOf(61));
            if (Arrays.stream(str3.split("\u0000")).filter(new Predicate() { // from class: com.sun.tools.javac.main.n
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return pattern.matcher((String) obj).matches();
                }
            }).map(new Function() { // from class: com.sun.tools.javac.main.o
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String str4 = (String) obj;
                    return str4.substring(0, str4.indexOf(61));
                }
            }).anyMatch(new Predicate() { // from class: com.sun.tools.javac.main.p
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((String) obj).equals(strSubstring);
                }
            })) {
                throw optionHelper.newInvalidValueException(CompilerProperties.Errors.RepeatedValueForModuleSourcePath(strSubstring));
            }
            super.process(optionHelper, str, str3 + (char) 0 + str2);
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.main.Option$6, reason: invalid class name */
    public final enum AnonymousClass6 extends Option {
        public AnonymousClass6(String str, int i, String str2, String str3, String str4, OptionKind optionKind, OptionGroup optionGroup) {
            super(str, i, str2, str3, str4, optionKind, optionGroup);
        }

        @Override // com.sun.tools.javac.main.Option
        public Pattern getPattern() {
            return Pattern.compile("([^/]+)=(,*[^,].*)");
        }

        @Override // com.sun.tools.javac.main.Option
        public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
            if (str2.length() == 0) {
                throw optionHelper.newInvalidValueException(CompilerProperties.Errors.NoValueForOption(str));
            }
            if (!getPattern().matcher(str2).matches()) {
                throw optionHelper.newInvalidValueException(CompilerProperties.Errors.BadValueForOption(str, str2));
            }
            String str3 = optionHelper.get(Option.PATCH_MODULE);
            if (str3 == null) {
                super.process(optionHelper, str, str2);
                return;
            }
            String strSubstring = str2.substring(0, str2.indexOf(61));
            if (((Set) Arrays.stream(str3.split("\u0000")).map(new Function() { // from class: com.sun.tools.javac.main.r
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String str4 = (String) obj;
                    return str4.substring(0, str4.indexOf(61));
                }
            }).collect(Collectors.toSet())).contains(strSubstring)) {
                throw optionHelper.newInvalidValueException(CompilerProperties.Errors.RepeatedValueForPatchModule(strSubstring));
            }
            super.process(optionHelper, str, str3 + (char) 0 + str2);
        }
    }

    public enum ArgKind {
        NONE,
        REQUIRED,
        ADJACENT
    }

    public enum ChoiceKind {
        ONEOF,
        ANYOF
    }

    public enum HiddenGroup {
        DIAGS("diags"),
        DEBUG(TransformerFactoryImpl.DEBUG),
        SHOULDSTOP("should-stop");

        final String text;

        HiddenGroup(String str) {
            this.text = str;
        }

        public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
            for (String str3 : str2.split(";")) {
                String str4 = this.text + Constants.ATTRVAL_THIS + str3.trim();
                Option.XD.process(optionHelper, str4, str4);
            }
        }
    }

    public enum OptionGroup {
        BASIC,
        FILEMANAGER,
        INFO,
        OPERAND
    }

    public enum OptionKind {
        STANDARD,
        EXTENDED,
        HIDDEN
    }

    public enum PkgInfo {
        ALWAYS,
        LEGACY,
        NONEMPTY;

        public static PkgInfo get(Options options) {
            String str = options.get(Option.XPKGINFO);
            return str == null ? LEGACY : valueOf(StringUtils.toUpperCase(str));
        }
    }

    private static /* synthetic */ Option[] $values() {
        return new Option[]{G, G_NONE, G_CUSTOM, XLINT, XLINT_CUSTOM, XDOCLINT, XDOCLINT_CUSTOM, XDOCLINT_PACKAGE, NOWARN, VERBOSE, DEPRECATION, CLASS_PATH, SOURCE_PATH, MODULE_SOURCE_PATH, MODULE_PATH, UPGRADE_MODULE_PATH, SYSTEM, PATCH_MODULE, BOOT_CLASS_PATH, XBOOTCLASSPATH_PREPEND, XBOOTCLASSPATH_APPEND, XBOOTCLASSPATH, EXTDIRS, DJAVA_EXT_DIRS, ENDORSEDDIRS, DJAVA_ENDORSED_DIRS, PROC, PROCESSOR, PROCESSOR_PATH, PROCESSOR_MODULE_PATH, PARAMETERS, D, S, H, IMPLICIT, ENCODING, SOURCE, TARGET, RELEASE, PREVIEW, DISABLE_LINE_DOC_COMMENTS, PROFILE, VERSION, FULLVERSION, HELP, A, DEFAULT_MODULE_FOR_CREATED_FILES, X, HELP_LINT, J, MOREINFO, WERROR, WERROR_CUSTOM, PROMPT, DOE, PRINTSOURCE, WARNUNCHECKED, XMAXERRS, XMAXWARNS, XSTDOUT, XPRINT, XPRINTROUNDS, XPRINTPROCESSORINFO, XPREFER, XXUSERPATHSFIRST, XPKGINFO, O, XJCOV, PLUGIN, XDIAGS, DEBUG, SHOULDSTOP, DIAGS, XD, ADD_EXPORTS, ADD_OPENS, ADD_READS, MODULE, ADD_MODULES, LIMIT_MODULES, MODULE_VERSION, AT, SOURCEFILE, MULTIRELEASE, INHERIT_RUNTIME_ENVIRONMENT};
    }

    static {
        OptionKind optionKind = OptionKind.STANDARD;
        OptionGroup optionGroup = OptionGroup.BASIC;
        G = new Option("G", 0, "-g", "opt.g", optionKind, optionGroup);
        G_NONE = new Option("G_NONE", 1, "-g:none", "opt.g.none", optionKind, optionGroup) { // from class: com.sun.tools.javac.main.Option.1
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str) {
                optionHelper.put("-g:", Option.LINT_CUSTOM_NONE);
            }
        };
        ChoiceKind choiceKind = ChoiceKind.ANYOF;
        G_CUSTOM = new Option("G_CUSTOM", 2, "-g:", "opt.g.lines.vars.source", optionKind, optionGroup, choiceKind, "lines", "vars", "source");
        OptionKind optionKind2 = OptionKind.EXTENDED;
        XLINT = new Option("XLINT", 3, "-Xlint", "opt.Xlint", optionKind2, optionGroup);
        XLINT_CUSTOM = new Option("XLINT_CUSTOM", 4, "-Xlint:", "opt.arg.Xlint", "opt.Xlint.custom", optionKind2, optionGroup, choiceKind, getXLintChoices());
        XDOCLINT = new Option("XDOCLINT", 5, "-Xdoclint", "opt.Xdoclint", optionKind2, optionGroup);
        XDOCLINT_CUSTOM = new Option("XDOCLINT_CUSTOM", 6, "-Xdoclint:", "opt.Xdoclint.subopts", "opt.Xdoclint.custom", optionKind2, optionGroup) { // from class: com.sun.tools.javac.main.Option.2
            @Override // com.sun.tools.javac.main.Option
            public boolean matches(String str) {
                return DocLint.newDocLint().isValidOption(str.replace(Option.XDOCLINT_CUSTOM.primaryName, DocLint.XMSGS_CUSTOM_PREFIX));
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str, String str2) {
                Option option = Option.XDOCLINT_CUSTOM;
                String str3 = optionHelper.get(option);
                if (str3 != null) {
                    str2 = str3 + " " + str2;
                }
                optionHelper.put(option.primaryName, str2);
            }
        };
        XDOCLINT_PACKAGE = new Option("XDOCLINT_PACKAGE", 7, "-Xdoclint/package:", "opt.Xdoclint.package.args", "opt.Xdoclint.package.desc", optionKind2, optionGroup) { // from class: com.sun.tools.javac.main.Option.3
            @Override // com.sun.tools.javac.main.Option
            public boolean matches(String str) {
                return DocLint.newDocLint().isValidOption(str.replace(Option.XDOCLINT_PACKAGE.primaryName, DocLint.XCHECK_PACKAGE));
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str, String str2) {
                Option option = Option.XDOCLINT_PACKAGE;
                String str3 = optionHelper.get(option);
                if (str3 != null) {
                    str2 = str3 + "," + str2;
                }
                optionHelper.put(option.primaryName, str2);
            }
        };
        NOWARN = new Option("NOWARN", 8, "-nowarn", "opt.nowarn", optionKind, optionGroup);
        VERBOSE = new Option("VERBOSE", 9, "-verbose", "opt.verbose", optionKind, optionGroup);
        DEPRECATION = new Option("DEPRECATION", 10, "-deprecation", "opt.deprecation", optionKind, optionGroup) { // from class: com.sun.tools.javac.main.Option.4
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str) {
                optionHelper.put("-Xlint:deprecation", str);
            }
        };
        OptionGroup optionGroup2 = OptionGroup.FILEMANAGER;
        CLASS_PATH = new Option("CLASS_PATH", 11, "--class-path -classpath -cp", "opt.arg.path", "opt.classpath", optionKind, optionGroup2);
        SOURCE_PATH = new Option("SOURCE_PATH", 12, "--source-path -sourcepath", "opt.arg.path", "opt.sourcepath", optionKind, optionGroup2);
        MODULE_SOURCE_PATH = new AnonymousClass5("MODULE_SOURCE_PATH", 13, "--module-source-path", "opt.arg.mspath", "opt.modulesourcepath", optionKind, optionGroup2);
        MODULE_PATH = new Option("MODULE_PATH", 14, "--module-path -p", "opt.arg.path", "opt.modulepath", optionKind, optionGroup2);
        UPGRADE_MODULE_PATH = new Option("UPGRADE_MODULE_PATH", 15, "--upgrade-module-path", "opt.arg.path", "opt.upgrademodulepath", optionKind, optionGroup2);
        SYSTEM = new Option("SYSTEM", 16, "--system", "opt.arg.jdk", "opt.system", optionKind, optionGroup2);
        PATCH_MODULE = new AnonymousClass6("PATCH_MODULE", 17, "--patch-module", "opt.arg.patch", "opt.patch", optionKind2, optionGroup2);
        BOOT_CLASS_PATH = new Option("BOOT_CLASS_PATH", 18, "--boot-class-path -bootclasspath", "opt.arg.path", "opt.bootclasspath", optionKind, optionGroup2) { // from class: com.sun.tools.javac.main.Option.7
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
                optionHelper.remove("-Xbootclasspath/p:");
                optionHelper.remove("-Xbootclasspath/a:");
                super.process(optionHelper, str, str2);
            }
        };
        XBOOTCLASSPATH_PREPEND = new Option("XBOOTCLASSPATH_PREPEND", 19, "-Xbootclasspath/p:", "opt.arg.path", "opt.Xbootclasspath.p", optionKind2, optionGroup2);
        XBOOTCLASSPATH_APPEND = new Option("XBOOTCLASSPATH_APPEND", 20, "-Xbootclasspath/a:", "opt.arg.path", "opt.Xbootclasspath.a", optionKind2, optionGroup2);
        XBOOTCLASSPATH = new Option("XBOOTCLASSPATH", 21, "-Xbootclasspath:", "opt.arg.path", "opt.bootclasspath", optionKind2, optionGroup2) { // from class: com.sun.tools.javac.main.Option.8
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
                optionHelper.remove("-Xbootclasspath/p:");
                optionHelper.remove("-Xbootclasspath/a:");
                super.process(optionHelper, "-bootclasspath", str2);
            }
        };
        EXTDIRS = new Option("EXTDIRS", 22, "-extdirs", "opt.arg.dirs", "opt.extdirs", optionKind, optionGroup2);
        DJAVA_EXT_DIRS = new Option("DJAVA_EXT_DIRS", 23, "-Djava.ext.dirs=", "opt.arg.dirs", "opt.extdirs", optionKind2, optionGroup2) { // from class: com.sun.tools.javac.main.Option.9
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
                Option.EXTDIRS.process(optionHelper, "-extdirs", str2);
            }
        };
        ENDORSEDDIRS = new Option("ENDORSEDDIRS", 24, "-endorseddirs", "opt.arg.dirs", "opt.endorseddirs", optionKind, optionGroup2);
        DJAVA_ENDORSED_DIRS = new Option("DJAVA_ENDORSED_DIRS", 25, "-Djava.endorsed.dirs=", "opt.arg.dirs", "opt.endorseddirs", optionKind2, optionGroup2) { // from class: com.sun.tools.javac.main.Option.10
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
                Option.ENDORSEDDIRS.process(optionHelper, "-endorseddirs", str2);
            }
        };
        ChoiceKind choiceKind2 = ChoiceKind.ONEOF;
        PROC = new Option("PROC", 26, "-proc:", "opt.proc.none.only", optionKind, optionGroup, choiceKind2, LINT_CUSTOM_NONE, Constants.ATTRNAME_ONLY, "full");
        PROCESSOR = new Option("PROCESSOR", 27, "-processor", "opt.arg.class.list", "opt.processor", optionKind, optionGroup);
        PROCESSOR_PATH = new Option("PROCESSOR_PATH", 28, "--processor-path -processorpath", "opt.arg.path", "opt.processorpath", optionKind, optionGroup2);
        PROCESSOR_MODULE_PATH = new Option("PROCESSOR_MODULE_PATH", 29, "--processor-module-path", "opt.arg.path", "opt.processormodulepath", optionKind, optionGroup2);
        PARAMETERS = new Option("PARAMETERS", 30, "-parameters", "opt.parameters", optionKind, optionGroup);
        D = new Option("D", 31, "-d", "opt.arg.directory", "opt.d", optionKind, optionGroup2);
        S = new Option("S", 32, "-s", "opt.arg.directory", "opt.sourceDest", optionKind, optionGroup2);
        H = new Option("H", 33, "-h", "opt.arg.directory", "opt.headerDest", optionKind, optionGroup2);
        IMPLICIT = new Option("IMPLICIT", 34, "-implicit:", "opt.implicit", optionKind, optionGroup, choiceKind2, LINT_CUSTOM_NONE, "class");
        ENCODING = new Option("ENCODING", 35, "-encoding", "opt.arg.encoding", "opt.encoding", optionKind, optionGroup2);
        SOURCE = new Option("SOURCE", 36, "--source -source", "opt.arg.release", "opt.source", optionKind, optionGroup) { // from class: com.sun.tools.javac.main.Option.11
            @Override // com.sun.tools.javac.main.Option
            public void help(Log log) {
                ArrayList arrayList = new ArrayList();
                for (Source source : Source.values()) {
                    if (source.isSupported()) {
                        arrayList.add(source.name);
                    }
                }
                super.help(log, log.localize(Log.PrefixKind.JAVAC, this.descrKey, Option.formatAbbreviatedList(arrayList)));
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
                if (Source.lookup(str2) == null) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.InvalidSource(str2));
                }
                super.process(optionHelper, str, str2);
            }
        };
        TARGET = new Option("TARGET", 37, "--target -target", "opt.arg.release", "opt.target", optionKind, optionGroup) { // from class: com.sun.tools.javac.main.Option.12
            @Override // com.sun.tools.javac.main.Option
            public void help(Log log) {
                ArrayList arrayList = new ArrayList();
                for (Target target : Target.values()) {
                    if (target.isSupported()) {
                        arrayList.add(target.name);
                    }
                }
                super.help(log, log.localize(Log.PrefixKind.JAVAC, this.descrKey, Option.formatAbbreviatedList(arrayList)));
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
                if (Target.lookup(str2) == null) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.InvalidTarget(str2));
                }
                super.process(optionHelper, str, str2);
            }
        };
        RELEASE = new AnonymousClass13("RELEASE", 38, "--release", "opt.arg.release", "opt.release", optionKind, optionGroup);
        PREVIEW = new Option("PREVIEW", 39, "--enable-preview", "opt.preview", optionKind, optionGroup);
        DISABLE_LINE_DOC_COMMENTS = new Option("DISABLE_LINE_DOC_COMMENTS", 40, "--disable-line-doc-comments", "opt.lineDocComments", optionKind2, optionGroup);
        PROFILE = new Option("PROFILE", 41, "-profile", "opt.arg.profile", "opt.profile", optionKind, optionGroup) { // from class: com.sun.tools.javac.main.Option.14
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
                if (Profile.lookup(str2) == null) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.InvalidProfile(str2));
                }
                super.process(optionHelper, str, str2);
            }
        };
        OptionGroup optionGroup3 = OptionGroup.INFO;
        VERSION = new Option("VERSION", 42, "--version -version", "opt.version", optionKind, optionGroup3) { // from class: com.sun.tools.javac.main.Option.15
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str) throws InvalidValueException {
                optionHelper.getLog().printLines(Log.WriterKind.STDOUT, Log.PrefixKind.JAVAC, "version", optionHelper.getOwnName(), JavaCompiler.version());
                super.process(optionHelper, str);
            }
        };
        OptionKind optionKind3 = OptionKind.HIDDEN;
        FULLVERSION = new Option("FULLVERSION", 43, "--full-version -fullversion", null, optionKind3, optionGroup3) { // from class: com.sun.tools.javac.main.Option.16
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str) throws InvalidValueException {
                optionHelper.getLog().printLines(Log.WriterKind.STDOUT, Log.PrefixKind.JAVAC, "fullVersion", optionHelper.getOwnName(), JavaCompiler.fullVersion());
                super.process(optionHelper, str);
            }
        };
        HELP = new Option("HELP", 44, "--help -help -?", "opt.help", optionKind, optionGroup3) { // from class: com.sun.tools.javac.main.Option.17
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str) throws InvalidValueException {
                Log log = optionHelper.getLog();
                String ownName = optionHelper.getOwnName();
                Log.WriterKind writerKind = Log.WriterKind.STDOUT;
                log.printLines(writerKind, Log.PrefixKind.JAVAC, "msg.usage.header", ownName);
                Option.showHelp(log, OptionKind.STANDARD);
                log.printNewline(writerKind);
                super.process(optionHelper, str);
            }
        };
        ArgKind argKind = ArgKind.ADJACENT;
        A = new Option("A", 45, "-A", "opt.arg.key.equals.value", "opt.A", optionKind, optionGroup, argKind) { // from class: com.sun.tools.javac.main.Option.18
            @Override // com.sun.tools.javac.main.Option
            public boolean hasArg() {
                return false;
            }

            @Override // com.sun.tools.javac.main.Option
            public boolean matches(String str) {
                return str.startsWith("-A");
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str) throws InvalidValueException {
                int length = str.length();
                if (length == 2) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.EmptyAArgument);
                }
                int iIndexOf = str.indexOf(61);
                if (iIndexOf != -1) {
                    length = iIndexOf;
                }
                if (!JavacProcessingEnvironment.isValidOptionName(str.substring(2, length))) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.InvalidAKey(str));
                }
                optionHelper.put(str, str);
            }
        };
        DEFAULT_MODULE_FOR_CREATED_FILES = new Option("DEFAULT_MODULE_FOR_CREATED_FILES", 46, "--default-module-for-created-files", "opt.arg.default.module.for.created.files", "opt.default.module.for.created.files", optionKind2, optionGroup) { // from class: com.sun.tools.javac.main.Option.19
            @Override // com.sun.tools.javac.main.Option
            public Pattern getPattern() {
                return Pattern.compile("[^,].*");
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
                Option option = Option.DEFAULT_MODULE_FOR_CREATED_FILES;
                if (optionHelper.get(option) != null) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.OptionTooMany(option.primaryName));
                }
                if (str2.length() == 0) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.NoValueForOption(str));
                }
                if (!getPattern().matcher(str2).matches()) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.BadValueForOption(str, str2));
                }
                optionHelper.put(option.primaryName, str2);
            }
        };
        X = new Option("X", 47, "--help-extra -X", "opt.X", optionKind, optionGroup3) { // from class: com.sun.tools.javac.main.Option.20
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str) throws InvalidValueException {
                Log log = optionHelper.getLog();
                Option.showHelp(log, OptionKind.EXTENDED);
                Log.WriterKind writerKind = Log.WriterKind.STDOUT;
                log.printNewline(writerKind);
                log.printLines(writerKind, Log.PrefixKind.JAVAC, "msg.usage.nonstandard.footer", new Object[0]);
                super.process(optionHelper, str);
            }
        };
        HELP_LINT = new AnonymousClass21("HELP_LINT", 48, "--help-lint", "opt.help.lint", optionKind2, optionGroup3);
        J = new Option("J", 49, "-J", "opt.arg.flag", "opt.J", optionKind, optionGroup3, argKind) { // from class: com.sun.tools.javac.main.Option.22
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
                throw optionHelper.newInvalidValueException(CompilerProperties.Errors.InvalidFlag(str + str2));
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str) {
                throw new AssertionError("the -J flag should be caught by the launcher.");
            }
        };
        String str = null;
        MOREINFO = new Option("MOREINFO", 50, "-moreinfo", str, optionKind3, optionGroup) { // from class: com.sun.tools.javac.main.Option.23
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str2) throws InvalidValueException {
                Type.moreInfo = true;
                super.process(optionHelper, str2);
            }
        };
        WERROR = new Option("WERROR", 51, "-Werror", "opt.Werror", optionKind, optionGroup);
        WERROR_CUSTOM = new Option("WERROR_CUSTOM", 52, "-Werror:", "opt.arg.Werror", "opt.Werror.custom", optionKind, optionGroup, choiceKind, getXLintChoices());
        PROMPT = new Option("PROMPT", 53, "-prompt", null, optionKind3, optionGroup);
        DOE = new Option("DOE", 54, "-doe", null, optionKind3, optionGroup);
        PRINTSOURCE = new Option("PRINTSOURCE", 55, "-printsource", null, optionKind3, optionGroup);
        WARNUNCHECKED = new Option("WARNUNCHECKED", 56, "-warnunchecked", str, optionKind3, optionGroup) { // from class: com.sun.tools.javac.main.Option.24
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str2) {
                optionHelper.put("-Xlint:unchecked", str2);
            }
        };
        XMAXERRS = new Option("XMAXERRS", 57, "-Xmaxerrs", "opt.arg.number", "opt.maxerrs", optionKind2, optionGroup);
        XMAXWARNS = new Option("XMAXWARNS", 58, "-Xmaxwarns", "opt.arg.number", "opt.maxwarns", optionKind2, optionGroup);
        XSTDOUT = new Option("XSTDOUT", 59, "-Xstdout", "opt.arg.file", "opt.Xstdout", optionKind2, optionGroup3) { // from class: com.sun.tools.javac.main.Option.25
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str2, String str3) throws InvalidValueException {
                try {
                    optionHelper.getLog().setWriters(new PrintWriter((Writer) new FileWriter(str3), true));
                    super.process(optionHelper, str2, str3);
                } catch (IOException e) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.ErrorWritingFile(str3, e.getMessage()));
                }
            }
        };
        XPRINT = new Option("XPRINT", 60, "-Xprint", "opt.print", optionKind2, optionGroup);
        XPRINTROUNDS = new Option("XPRINTROUNDS", 61, "-XprintRounds", "opt.printRounds", optionKind2, optionGroup);
        XPRINTPROCESSORINFO = new Option("XPRINTPROCESSORINFO", 62, "-XprintProcessorInfo", "opt.printProcessorInfo", optionKind2, optionGroup);
        XPREFER = new Option("XPREFER", 63, "-Xprefer:", "opt.prefer", optionKind2, optionGroup, choiceKind2, "source", "newer");
        XXUSERPATHSFIRST = new Option("XXUSERPATHSFIRST", 64, "-XXuserPathsFirst", "opt.userpathsfirst", optionKind3, optionGroup);
        XPKGINFO = new Option("XPKGINFO", 65, "-Xpkginfo:", "opt.pkginfo", optionKind2, optionGroup, choiceKind2, "always", "legacy", "nonempty");
        O = new Option("O", 66, "-O", null, optionKind3, optionGroup);
        XJCOV = new Option("XJCOV", 67, "-Xjcov", null, optionKind3, optionGroup);
        PLUGIN = new Option("PLUGIN", 68, "-Xplugin:", "opt.arg.plugin", "opt.plugin", optionKind2, optionGroup) { // from class: com.sun.tools.javac.main.Option.26
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str2, String str3) {
                Option option = Option.PLUGIN;
                String str4 = optionHelper.get(option);
                String str5 = option.primaryName;
                if (str4 != null) {
                    str3 = str4 + (char) 0 + str3;
                }
                optionHelper.put(str5, str3);
            }
        };
        XDIAGS = new Option("XDIAGS", 69, "-Xdiags:", "opt.diags", optionKind2, optionGroup, choiceKind2, "compact", "verbose");
        ArgKind argKind2 = ArgKind.REQUIRED;
        String str2 = null;
        DEBUG = new Option("DEBUG", 70, "--debug", str2, optionKind3, optionGroup, argKind2) { // from class: com.sun.tools.javac.main.Option.27
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3, String str4) throws InvalidValueException {
                HiddenGroup.DEBUG.process(optionHelper, str3, str4);
            }
        };
        SHOULDSTOP = new Option("SHOULDSTOP", 71, "--should-stop", str2, optionKind3, optionGroup, argKind2) { // from class: com.sun.tools.javac.main.Option.28
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3, String str4) throws InvalidValueException {
                HiddenGroup.SHOULDSTOP.process(optionHelper, str3, str4);
            }
        };
        DIAGS = new Option("DIAGS", 72, "--diags", str2, optionKind3, optionGroup, argKind2) { // from class: com.sun.tools.javac.main.Option.29
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3, String str4) throws InvalidValueException {
                HiddenGroup.DIAGS.process(optionHelper, str3, str4);
            }
        };
        XD = new Option("XD", 73, "-XD", str2, optionKind3, optionGroup) { // from class: com.sun.tools.javac.main.Option.30
            @Override // com.sun.tools.javac.main.Option
            public boolean matches(String str3) {
                return str3.startsWith(this.primaryName);
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3, String str4) {
                int iIndexOf = str4.indexOf(61);
                String strSubstring = iIndexOf < 0 ? str4 : str4.substring(0, iIndexOf);
                if (iIndexOf >= 0) {
                    str4 = str4.substring(iIndexOf + 1);
                }
                optionHelper.put(strSubstring, str4);
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3) {
                process(optionHelper, str3, str3.substring(this.primaryName.length()));
            }
        };
        ADD_EXPORTS = new Option("ADD_EXPORTS", 74, "--add-exports", "opt.arg.addExports", "opt.addExports", optionKind2, optionGroup) { // from class: com.sun.tools.javac.main.Option.31
            @Override // com.sun.tools.javac.main.Option
            public Pattern getPattern() {
                return Pattern.compile("([^/]+)/([^=]+)=(,*[^,].*)");
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3, String str4) throws InvalidValueException {
                if (str4.length() == 0) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.NoValueForOption(str3));
                }
                if (!getPattern().matcher(str4).matches()) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.BadValueForOption(str3, str4));
                }
                Option option = Option.ADD_EXPORTS;
                String str5 = optionHelper.get(option);
                String str6 = option.primaryName;
                if (str5 != null) {
                    str4 = str5 + (char) 0 + str4;
                }
                optionHelper.put(str6, str4);
            }
        };
        ADD_OPENS = new Option("ADD_OPENS", 75, "--add-opens", (String) null, (String) null, optionKind3, optionGroup);
        ADD_READS = new Option("ADD_READS", 76, "--add-reads", "opt.arg.addReads", "opt.addReads", optionKind2, optionGroup) { // from class: com.sun.tools.javac.main.Option.32
            @Override // com.sun.tools.javac.main.Option
            public Pattern getPattern() {
                return Pattern.compile("([^=]+)=(,*[^,].*)");
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3, String str4) throws InvalidValueException {
                if (str4.length() == 0) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.NoValueForOption(str3));
                }
                if (!getPattern().matcher(str4).matches()) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.BadValueForOption(str3, str4));
                }
                Option option = Option.ADD_READS;
                String str5 = optionHelper.get(option);
                String str6 = option.primaryName;
                if (str5 != null) {
                    str4 = str5 + (char) 0 + str4;
                }
                optionHelper.put(str6, str4);
            }
        };
        MODULE = new Option("MODULE", 77, "--module -m", "opt.arg.m", "opt.m", optionKind, optionGroup);
        ADD_MODULES = new Option("ADD_MODULES", 78, "--add-modules", "opt.arg.addmods", "opt.addmods", optionKind, optionGroup) { // from class: com.sun.tools.javac.main.Option.33
            @Override // com.sun.tools.javac.main.Option
            public Pattern getPattern() {
                return Pattern.compile(",*[^,].*");
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3, String str4) throws InvalidValueException {
                if (str4.length() == 0) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.NoValueForOption(str3));
                }
                if (!getPattern().matcher(str4).matches()) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.BadValueForOption(str3, str4));
                }
                Option option = Option.ADD_MODULES;
                String str5 = optionHelper.get(option);
                String str6 = option.primaryName;
                if (str5 != null) {
                    str4 = str5 + ',' + str4;
                }
                optionHelper.put(str6, str4);
            }
        };
        LIMIT_MODULES = new Option("LIMIT_MODULES", 79, "--limit-modules", "opt.arg.limitmods", "opt.limitmods", optionKind, optionGroup) { // from class: com.sun.tools.javac.main.Option.34
            @Override // com.sun.tools.javac.main.Option
            public Pattern getPattern() {
                return Pattern.compile(",*[^,].*");
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3, String str4) throws InvalidValueException {
                if (str4.length() == 0) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.NoValueForOption(str3));
                }
                if (!getPattern().matcher(str4).matches()) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.BadValueForOption(str3, str4));
                }
                optionHelper.put(Option.LIMIT_MODULES.primaryName, str4);
            }
        };
        MODULE_VERSION = new Option("MODULE_VERSION", 80, "--module-version", "opt.arg.module.version", "opt.module.version", optionKind, optionGroup) { // from class: com.sun.tools.javac.main.Option.35
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3, String str4) throws InvalidValueException {
                if (str4.length() == 0) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.NoValueForOption(str3));
                }
                try {
                    ModuleWrapper.ModuleDescriptor.Version.parse(str4);
                    super.process(optionHelper, str3, str4);
                } catch (IllegalArgumentException unused) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.BadValueForOption(str3, str4));
                }
            }
        };
        AT = new Option("AT", 81, "@", "opt.arg.file", "opt.AT", optionKind, optionGroup3, argKind) { // from class: com.sun.tools.javac.main.Option.36
            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3) {
                throw new AssertionError("the @ flag should be caught by CommandLine.");
            }
        };
        SOURCEFILE = new Option("SOURCEFILE", 82, "sourcefile", null, optionKind3, optionGroup3) { // from class: com.sun.tools.javac.main.Option.37
            @Override // com.sun.tools.javac.main.Option
            public boolean matches(String str3) {
                if (str3.endsWith(".java")) {
                    return true;
                }
                int iIndexOf = str3.indexOf(47);
                if (iIndexOf != -1) {
                    return SourceVersion.isName(str3.substring(0, iIndexOf)) && SourceVersion.isName(str3.substring(iIndexOf + 1));
                }
                return SourceVersion.isName(str3);
            }

            @Override // com.sun.tools.javac.main.Option
            public void process(OptionHelper optionHelper, String str3) throws InvalidValueException {
                if (!str3.endsWith(".java")) {
                    optionHelper.addClassName(str3);
                    return;
                }
                try {
                    Path path = Paths.get(str3, new String[0]);
                    if (!Files.exists(path, new LinkOption[0])) {
                        throw optionHelper.newInvalidValueException(CompilerProperties.Errors.FileNotFound(path.toString()));
                    }
                    if (!Files.isRegularFile(path, new LinkOption[0])) {
                        throw optionHelper.newInvalidValueException(CompilerProperties.Errors.FileNotFile(path));
                    }
                    optionHelper.addFile(path);
                } catch (InvalidPathException unused) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.InvalidPath(str3));
                }
            }
        };
        MULTIRELEASE = new Option("MULTIRELEASE", 83, "--multi-release", "opt.arg.multi-release", "opt.multi-release", optionKind3, optionGroup2);
        INHERIT_RUNTIME_ENVIRONMENT = new AnonymousClass38("INHERIT_RUNTIME_ENVIRONMENT", 84, "--inherit-runtime-environment", "opt.inherit_runtime_environment", optionKind3, optionGroup);
        $VALUES = $values();
    }

    private Option(String str, int i, String str2, String str3, String str4, OptionKind optionKind, OptionGroup optionGroup, ChoiceKind choiceKind, Set set, ArgKind argKind) {
        super(str, i);
        String[] strArrSplit = str2.trim().split("\\s+");
        this.names = strArrSplit;
        Assert.check(strArrSplit.length >= 1);
        this.primaryName = strArrSplit[0];
        this.argsNameKey = str3;
        this.descrKey = str4;
        this.kind = optionKind;
        this.group = optionGroup;
        this.choiceKind = choiceKind;
        this.choices = set;
        this.argKind = argKind;
    }

    private static int findSeparator(String str) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == ':' || cCharAt == '=') {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String formatAbbreviatedList(Collection<String> collection) {
        List arrayList = collection instanceof List ? (List) collection : new ArrayList(collection);
        int size = arrayList.size();
        if (size == 0) {
            return "";
        }
        if (size <= 6) {
            return String.join(", ", arrayList);
        }
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (int i = 0; i < 3; i++) {
            stringJoiner.add((CharSequence) arrayList.get(i));
        }
        stringJoiner.add("...");
        for (int i2 = size - 3; i2 < size; i2++) {
            stringJoiner.add((CharSequence) arrayList.get(i2));
        }
        return stringJoiner.toString();
    }

    public static Set<Option> getJavaCompilerOptions() {
        return EnumSet.allOf(Option.class);
    }

    public static Set<Option> getJavacFileManagerOptions() {
        return getOptions(OptionGroup.FILEMANAGER);
    }

    public static Set<Option> getJavacToolOptions() {
        return getOptions(OptionGroup.BASIC);
    }

    private static Set<Option> getOptions(final OptionGroup optionGroup) {
        return (Set) Arrays.stream(values()).filter(new Predicate() { // from class: com.sun.tools.javac.main.e
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Option.n(optionGroup, (Option) obj);
            }
        }).collect(Collectors.toCollection(new Supplier() { // from class: tqa
            @Override // java.util.function.Supplier
            public final Object get() {
                return EnumSet.noneOf(Option.class);
            }
        }));
    }

    private static Set<String> getXLintChoices() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("all");
        Lint.LintCategory.options().stream().flatMap(new Function() { // from class: rqa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String str = (String) obj;
                return Stream.of((Object[]) new String[]{str, "-" + str});
            }
        }).forEach(new vlf(linkedHashSet));
        linkedHashSet.add(LINT_CUSTOM_NONE);
        return linkedHashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String helpSynopsis(String str, Log log) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (this.argsNameKey != null) {
            if (!str.matches(".*[=:]$") && this.argKind != ArgKind.ADJACENT) {
                sb.append(" ");
            }
            sb.append(log.localize(Log.PrefixKind.JAVAC, this.argsNameKey, new Object[0]));
        } else if (this.choices != null) {
            if (!str.endsWith(":")) {
                sb.append(" ");
            }
            String str2 = "{";
            for (String str3 : this.choices) {
                sb.append(str2);
                sb.append(str3);
                str2 = ",";
            }
            sb.append("}");
        }
        return sb.toString();
    }

    public static /* synthetic */ boolean i(OptionKind optionKind, Option option) {
        return option.kind == optionKind;
    }

    public static Option lookup(String str, Set<Option> set) {
        for (Option option : set) {
            if (option.matches(str)) {
                return option;
            }
        }
        return null;
    }

    private boolean matches(String str, String str2) {
        if (str2.startsWith("--")) {
            return str.equals(str2) || (hasArg() && str.startsWith(str2.concat("=")));
        }
        if (this.argKind != ArgKind.ADJACENT && !str2.endsWith(":") && !str2.endsWith("=")) {
            return str.equals(str2);
        }
        if (!str.startsWith(str2)) {
            return false;
        }
        if (this.choices != null) {
            String strSubstring = str.substring(str2.length());
            if (this.choiceKind == ChoiceKind.ONEOF) {
                return this.choices.contains(strSubstring);
            }
            for (String str3 : strSubstring.split(",+")) {
                if (!this.choices.contains(str3)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static /* synthetic */ boolean n(OptionGroup optionGroup, Option option) {
        return option.group == optionGroup;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showHelp(final Log log, final OptionKind optionKind) {
        getJavaCompilerOptions().stream().filter(new Predicate() { // from class: nqa
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Option.i(optionKind, (Option) obj);
            }
        }).sorted(new Comparator<Option>() { // from class: com.sun.tools.javac.main.Option.39
            final Collator collator;

            {
                Collator collator = Collator.getInstance(Locale.US);
                this.collator = collator;
                collator.setStrength(0);
            }

            @Override // java.util.Comparator
            public int compare(Option option, Option option2) {
                return this.collator.compare(option.primaryName, option2.primaryName);
            }
        }).forEach(new Consumer() { // from class: pqa
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Option) obj).help(log);
            }
        });
    }

    public static Option valueOf(String str) {
        return (Option) Enum.valueOf(Option.class, str);
    }

    public static Option[] values() {
        return (Option[]) $VALUES.clone();
    }

    public ArgKind getArgKind() {
        return this.argKind;
    }

    public Option getCustom() {
        return valueOf(name() + "_CUSTOM");
    }

    public OptionKind getKind() {
        return this.kind;
    }

    public Option getLintCustom() {
        if (this == XLINT || this == WERROR) {
            return getCustom();
        }
        j2d.a();
        return null;
    }

    public Pattern getPattern() {
        throw new UnsupportedOperationException();
    }

    public String getPrimaryName() {
        return this.primaryName;
    }

    public void handleOption(OptionHelper optionHelper, String str, Iterator<String> it) throws InvalidValueException {
        String next;
        String strSubstring;
        String strSubstring2;
        optionHelper.initialize();
        if (!hasArg()) {
            if ((this == HELP || this == X || this == HELP_LINT || this == VERSION || this == FULLVERSION) && optionHelper.get(this) != null) {
                return;
            }
            process(optionHelper, str);
            return;
        }
        int iFindSeparator = findSeparator(str);
        if (getArgKind() != ArgKind.ADJACENT) {
            if (iFindSeparator > 0) {
                strSubstring = str.substring(0, iFindSeparator);
                strSubstring2 = str.substring(iFindSeparator + 1);
            } else {
                if (!it.hasNext()) {
                    throw optionHelper.newInvalidValueException(CompilerProperties.Errors.ReqArg(this.primaryName));
                }
                next = it.next();
            }
            process(optionHelper, str, next);
        }
        strSubstring = this.primaryName;
        strSubstring2 = str.substring(strSubstring.length());
        String str2 = strSubstring;
        next = strSubstring2;
        str = str2;
        process(optionHelper, str, next);
    }

    public boolean hasArg() {
        return this.argKind != ArgKind.NONE;
    }

    public boolean hasSeparateArg() {
        return (getArgKind() != ArgKind.REQUIRED || this.primaryName.endsWith(":") || this.primaryName.endsWith("=")) ? false : true;
    }

    public void help(final Log log, String str) {
        String str2 = (String) Arrays.stream(this.names).map(new Function() { // from class: lqa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.helpSynopsis((String) obj, log);
            }
        }).collect(Collectors.joining(", "));
        if (str2.length() < 28 && !str.contains("\n") && 31 + str.length() <= 80) {
            log.printRawLines(Log.WriterKind.STDOUT, String.format(COMPACT_FORMAT, str2, str));
            return;
        }
        if (str2.length() <= 80) {
            log.printRawLines(Log.WriterKind.STDOUT, SMALL_INDENT.concat(str2));
        } else {
            for (String str3 : this.names) {
                log.printRawLines(Log.WriterKind.STDOUT, SMALL_INDENT + helpSynopsis(str3, log));
            }
        }
        log.printRawLines(Log.WriterKind.STDOUT, LARGE_INDENT + str.replace("\n", "\n        "));
    }

    public boolean isInBasicOptionGroup() {
        return this.group == OptionGroup.BASIC;
    }

    public void process(OptionHelper optionHelper, String str, String str2) throws InvalidValueException {
        optionHelper.initialize();
        Set<String> set = this.choices;
        if (set != null) {
            if (this.choiceKind == ChoiceKind.ONEOF) {
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    optionHelper.remove(this.primaryName + it.next());
                }
                String str3 = this.primaryName + str2;
                optionHelper.put(str3, str3);
                String str4 = this.primaryName;
                optionHelper.put(str4.substring(0, str4.length() - 1), str2);
            } else {
                for (String str5 : str2.split(",+")) {
                    String str6 = this.primaryName + str5;
                    optionHelper.put(str6, str6);
                }
            }
        }
        optionHelper.put(this.primaryName, str2);
        if (this.group == OptionGroup.FILEMANAGER) {
            optionHelper.handleFileManagerOption(this, str2);
        }
    }

    public static class InvalidValueException extends Exception {
        private static final long serialVersionUID = -1;

        public InvalidValueException(String str) {
            super(str);
        }

        public InvalidValueException(String str, Throwable th) {
            super(str, th);
        }
    }

    public static Option lookup(String str) {
        return lookup(str, EnumSet.allOf(Option.class));
    }

    private Option(String str, int i, String str2, String str3, OptionKind optionKind, OptionGroup optionGroup) {
        this(str, i, str2, null, str3, optionKind, optionGroup, null, null, ArgKind.NONE);
    }

    private Option(String str, int i, String str2, String str3, OptionKind optionKind, OptionGroup optionGroup, ArgKind argKind) {
        this(str, i, str2, null, str3, optionKind, optionGroup, null, null, argKind);
    }

    private Option(String str, int i, String str2, String str3, String str4, OptionKind optionKind, OptionGroup optionGroup) {
        this(str, i, str2, str3, str4, optionKind, optionGroup, null, null, ArgKind.REQUIRED);
    }

    private Option(String str, int i, String str2, String str3, String str4, OptionKind optionKind, OptionGroup optionGroup, ArgKind argKind) {
        this(str, i, str2, str3, str4, optionKind, optionGroup, null, null, argKind);
    }

    private Option(String str, int i, String str2, String str3, String str4, OptionKind optionKind, OptionGroup optionGroup, ChoiceKind choiceKind, Set set) {
        this(str, i, str2, str3, str4, optionKind, optionGroup, choiceKind, set, ArgKind.REQUIRED);
    }

    private Option(String str, int i, String str2, String str3, OptionKind optionKind, OptionGroup optionGroup, ChoiceKind choiceKind, String... strArr) {
        this(str, i, str2, null, str3, optionKind, optionGroup, choiceKind, new LinkedHashSet(Arrays.asList(strArr)), ArgKind.REQUIRED);
    }

    public boolean matches(String str) {
        for (String str2 : this.names) {
            if (matches(str, str2)) {
                return true;
            }
        }
        return false;
    }

    public void process(OptionHelper optionHelper, String str) throws InvalidValueException {
        if (this.argKind == ArgKind.NONE) {
            process(optionHelper, this.primaryName, str);
        } else {
            process(optionHelper, this.primaryName, str.substring(findSeparator(str) + 1));
        }
    }

    public void help(Log log) {
        help(log, log.localize(Log.PrefixKind.JAVAC, this.descrKey, new Object[0]));
    }
}

package org.jetbrains.kotlin.cli.common;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'COMPILE_DAEMON_VERBOSE_REPORT_PROPERTY' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:160)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b.\b\u0086\u0081\u0002\u0018\u0000 22\b\u0012\u0004\u0012\u00020\u00000\u0001:\u00012B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J%\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010'2\b\u0010(\u001a\u0004\u0018\u0001H'2\u0006\u0010)\u001a\u0002H'H\u0002¢\u0006\u0002\u0010*J\b\u00101\u001a\u0004\u0018\u00010\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR(\u0010+\u001a\u0004\u0018\u00010\u00032\b\u0010+\u001a\u0004\u0018\u00010\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b,\u0010\t\"\u0004\b-\u0010.R\u0011\u0010/\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b0\u0010\tj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/CompilerSystemProperties;", Argument.Delimiters.none, "property", Argument.Delimiters.none, "alwaysDirectAccess", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;ILjava/lang/String;Z)V", "getProperty", "()Ljava/lang/String;", "getAlwaysDirectAccess", "()Z", "COMPILE_DAEMON_ENABLED_PROPERTY", "COMPILE_DAEMON_JVM_OPTIONS_PROPERTY", "COMPILE_DAEMON_OPTIONS_PROPERTY", "COMPILE_DAEMON_CLIENT_OPTIONS_PROPERTY", "COMPILE_DAEMON_CLIENT_ALIVE_PATH_PROPERTY", "COMPILE_DAEMON_LOG_PATH_PROPERTY", "COMPILE_DAEMON_REPORT_PERF_PROPERTY", "COMPILE_DAEMON_VERBOSE_REPORT_PROPERTY", "COMPILE_DAEMON_STARTUP_TIMEOUT_PROPERTY", "JAVA_RMI_SERVER_HOSTNAME", "DAEMON_RMI_SOCKET_BACKLOG_SIZE_PROPERTY", "DAEMON_RMI_SOCKET_CONNECT_ATTEMPTS_PROPERTY", "DAEMON_RMI_SOCKET_CONNECT_INTERVAL_PROPERTY", "KOTLIN_COMPILER_ENVIRONMENT_KEEPALIVE_PROPERTY", "COMPILE_DAEMON_CUSTOM_RUN_FILES_PATH_FOR_TESTS", "COMPILE_WAIT_BEFORE_COMPILATION_FOR_TESTS", "COMPILE_DAEMON_ENVIRONMENT_VARIABLES_FOR_TESTS", "KOTLIN_COLORS_ENABLED_PROPERTY", "LANGUAGE_VERSION_SETTINGS", "COMPILE_DAEMON_INITIATOR_MARKER_FILE", "OS_NAME", "TMP_DIR", "USER_HOME", "JAVA_VERSION", "JAVA_HOME", "JAVA_CLASS_PATH", "getProperFunction", "T", "custom", "default", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "value", "getValue", "setValue", "(Ljava/lang/String;)V", "safeValue", "getSafeValue", "clear", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompilerSystemProperties {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CompilerSystemProperties[] $VALUES;
    public static final CompilerSystemProperties COMPILE_DAEMON_VERBOSE_REPORT_PROPERTY;
    public static final CompilerSystemProperties COMPILE_WAIT_BEFORE_COMPILATION_FOR_TESTS;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static Function1<? super String, String> systemPropertyCleaner;
    private static Function1<? super String, String> systemPropertyGetter;
    private static Function2<? super String, ? super String, String> systemPropertySetter;
    private final boolean alwaysDirectAccess;
    private final String property;
    public static final CompilerSystemProperties COMPILE_DAEMON_ENABLED_PROPERTY = new CompilerSystemProperties("COMPILE_DAEMON_ENABLED_PROPERTY", 0, "kotlin.daemon.enabled", false, 2, null);
    public static final CompilerSystemProperties COMPILE_DAEMON_JVM_OPTIONS_PROPERTY = new CompilerSystemProperties("COMPILE_DAEMON_JVM_OPTIONS_PROPERTY", 1, "kotlin.daemon.jvm.options", false, 2, null);
    public static final CompilerSystemProperties COMPILE_DAEMON_OPTIONS_PROPERTY = new CompilerSystemProperties("COMPILE_DAEMON_OPTIONS_PROPERTY", 2, "kotlin.daemon.options", false, 2, null);
    public static final CompilerSystemProperties COMPILE_DAEMON_CLIENT_OPTIONS_PROPERTY = new CompilerSystemProperties("COMPILE_DAEMON_CLIENT_OPTIONS_PROPERTY", 3, "kotlin.daemon.client.options", false, 2, null);
    public static final CompilerSystemProperties COMPILE_DAEMON_CLIENT_ALIVE_PATH_PROPERTY = new CompilerSystemProperties("COMPILE_DAEMON_CLIENT_ALIVE_PATH_PROPERTY", 4, "kotlin.daemon.client.alive.path", false, 2, null);
    public static final CompilerSystemProperties COMPILE_DAEMON_LOG_PATH_PROPERTY = new CompilerSystemProperties("COMPILE_DAEMON_LOG_PATH_PROPERTY", 5, "kotlin.daemon.log.path", false, 2, null);
    public static final CompilerSystemProperties COMPILE_DAEMON_REPORT_PERF_PROPERTY = new CompilerSystemProperties("COMPILE_DAEMON_REPORT_PERF_PROPERTY", 6, "kotlin.daemon.perf", false, 2, null);
    public static final CompilerSystemProperties COMPILE_DAEMON_STARTUP_TIMEOUT_PROPERTY = new CompilerSystemProperties("COMPILE_DAEMON_STARTUP_TIMEOUT_PROPERTY", 8, "kotlin.daemon.startup.timeout", false, 2, null);
    public static final CompilerSystemProperties JAVA_RMI_SERVER_HOSTNAME = new CompilerSystemProperties("JAVA_RMI_SERVER_HOSTNAME", 9, "java.rmi.server.hostname", false, 2, null);
    public static final CompilerSystemProperties DAEMON_RMI_SOCKET_BACKLOG_SIZE_PROPERTY = new CompilerSystemProperties("DAEMON_RMI_SOCKET_BACKLOG_SIZE_PROPERTY", 10, "kotlin.daemon.socket.backlog.size", false, 2, null);
    public static final CompilerSystemProperties DAEMON_RMI_SOCKET_CONNECT_ATTEMPTS_PROPERTY = new CompilerSystemProperties("DAEMON_RMI_SOCKET_CONNECT_ATTEMPTS_PROPERTY", 11, "kotlin.daemon.socket.connect.attempts", false, 2, null);
    public static final CompilerSystemProperties DAEMON_RMI_SOCKET_CONNECT_INTERVAL_PROPERTY = new CompilerSystemProperties("DAEMON_RMI_SOCKET_CONNECT_INTERVAL_PROPERTY", 12, "kotlin.daemon.socket.connect.interval", false, 2, null);
    public static final CompilerSystemProperties KOTLIN_COMPILER_ENVIRONMENT_KEEPALIVE_PROPERTY = new CompilerSystemProperties("KOTLIN_COMPILER_ENVIRONMENT_KEEPALIVE_PROPERTY", 13, "kotlin.environment.keepalive", false, 2, null);
    public static final CompilerSystemProperties COMPILE_DAEMON_CUSTOM_RUN_FILES_PATH_FOR_TESTS = new CompilerSystemProperties("COMPILE_DAEMON_CUSTOM_RUN_FILES_PATH_FOR_TESTS", 14, "kotlin.daemon.custom.run.files.path.for.tests", false, 2, null);
    public static final CompilerSystemProperties COMPILE_DAEMON_ENVIRONMENT_VARIABLES_FOR_TESTS = new CompilerSystemProperties("COMPILE_DAEMON_ENVIRONMENT_VARIABLES_FOR_TESTS", 16, "kotlin.daemon.environment.variables.for.tests", false, 2, null);
    public static final CompilerSystemProperties KOTLIN_COLORS_ENABLED_PROPERTY = new CompilerSystemProperties("KOTLIN_COLORS_ENABLED_PROPERTY", 17, "kotlin.colors.enabled", false, 2, null);
    public static final CompilerSystemProperties LANGUAGE_VERSION_SETTINGS = new CompilerSystemProperties("LANGUAGE_VERSION_SETTINGS", 18, "kotlin.language.settings", false, 2, null);
    public static final CompilerSystemProperties COMPILE_DAEMON_INITIATOR_MARKER_FILE = new CompilerSystemProperties("COMPILE_DAEMON_INITIATOR_MARKER_FILE", 19, "kotlin.daemon.initiator.marker.file", false, 2, null);
    public static final CompilerSystemProperties OS_NAME = new CompilerSystemProperties("OS_NAME", 20, "os.name", true);
    public static final CompilerSystemProperties TMP_DIR = new CompilerSystemProperties("TMP_DIR", 21, "java.io.tmpdir", false, 2, null);
    public static final CompilerSystemProperties USER_HOME = new CompilerSystemProperties("USER_HOME", 22, "user.home", true);
    public static final CompilerSystemProperties JAVA_VERSION = new CompilerSystemProperties("JAVA_VERSION", 23, "java.specification.version", true);
    public static final CompilerSystemProperties JAVA_HOME = new CompilerSystemProperties("JAVA_HOME", 24, "java.home", true);
    public static final CompilerSystemProperties JAVA_CLASS_PATH = new CompilerSystemProperties("JAVA_CLASS_PATH", 25, "java.class.path", true);

    private static final /* synthetic */ CompilerSystemProperties[] $values() {
        return new CompilerSystemProperties[]{COMPILE_DAEMON_ENABLED_PROPERTY, COMPILE_DAEMON_JVM_OPTIONS_PROPERTY, COMPILE_DAEMON_OPTIONS_PROPERTY, COMPILE_DAEMON_CLIENT_OPTIONS_PROPERTY, COMPILE_DAEMON_CLIENT_ALIVE_PATH_PROPERTY, COMPILE_DAEMON_LOG_PATH_PROPERTY, COMPILE_DAEMON_REPORT_PERF_PROPERTY, COMPILE_DAEMON_VERBOSE_REPORT_PROPERTY, COMPILE_DAEMON_STARTUP_TIMEOUT_PROPERTY, JAVA_RMI_SERVER_HOSTNAME, DAEMON_RMI_SOCKET_BACKLOG_SIZE_PROPERTY, DAEMON_RMI_SOCKET_CONNECT_ATTEMPTS_PROPERTY, DAEMON_RMI_SOCKET_CONNECT_INTERVAL_PROPERTY, KOTLIN_COMPILER_ENVIRONMENT_KEEPALIVE_PROPERTY, COMPILE_DAEMON_CUSTOM_RUN_FILES_PATH_FOR_TESTS, COMPILE_WAIT_BEFORE_COMPILATION_FOR_TESTS, COMPILE_DAEMON_ENVIRONMENT_VARIABLES_FOR_TESTS, KOTLIN_COLORS_ENABLED_PROPERTY, LANGUAGE_VERSION_SETTINGS, COMPILE_DAEMON_INITIATOR_MARKER_FILE, OS_NAME, TMP_DIR, USER_HOME, JAVA_VERSION, JAVA_HOME, JAVA_CLASS_PATH};
    }

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        COMPILE_DAEMON_VERBOSE_REPORT_PROPERTY = new CompilerSystemProperties("COMPILE_DAEMON_VERBOSE_REPORT_PROPERTY", 7, "kotlin.daemon.verbose", false, 2, defaultConstructorMarker);
        COMPILE_WAIT_BEFORE_COMPILATION_FOR_TESTS = new CompilerSystemProperties("COMPILE_WAIT_BEFORE_COMPILATION_FOR_TESTS", 15, "kotlin.daemon.wait.before.compilation.for.tests", false, 2, defaultConstructorMarker);
        CompilerSystemProperties[] compilerSystemPropertiesArr$values = $values();
        $VALUES = compilerSystemPropertiesArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(compilerSystemPropertiesArr$values);
        INSTANCE = new Companion(null);
    }

    public /* synthetic */ CompilerSystemProperties(String str, int i, String str2, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, (i2 & 2) != 0 ? false : z);
    }

    public static EnumEntries<CompilerSystemProperties> getEntries() {
        return $ENTRIES;
    }

    private final <T> T getProperFunction(T custom, T t) {
        return (this.alwaysDirectAccess || custom == null) ? t : custom;
    }

    public static CompilerSystemProperties valueOf(String str) {
        return (CompilerSystemProperties) Enum.valueOf(CompilerSystemProperties.class, str);
    }

    public static CompilerSystemProperties[] values() {
        return (CompilerSystemProperties[]) $VALUES.clone();
    }

    public final String clear() {
        return (String) ((Function1) getProperFunction(systemPropertyCleaner, AnonymousClass1.INSTANCE)).invoke(this.property);
    }

    public final boolean getAlwaysDirectAccess() {
        return this.alwaysDirectAccess;
    }

    public final String getProperty() {
        return this.property;
    }

    public final String getSafeValue() {
        String value = getValue();
        if (value != null) {
            return value;
        }
        b88.a("No value for ", this.property, " system property");
        return null;
    }

    public final String getValue() {
        return (String) ((Function1) getProperFunction(systemPropertyGetter, CompilerSystemProperties$value$1.INSTANCE)).invoke(this.property);
    }

    public final void setValue(String str) {
        Function2 function2 = (Function2) getProperFunction(systemPropertySetter, CompilerSystemProperties$value$2.INSTANCE);
        String str2 = this.property;
        str.getClass();
        function2.invoke(str2, str);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R*\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR0\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R*\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\n¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/CompilerSystemProperties$Companion;", Argument.Delimiters.none, "<init>", "()V", "systemPropertyGetter", "Lkotlin/Function1;", Argument.Delimiters.none, "getSystemPropertyGetter", "()Lkotlin/jvm/functions/Function1;", "setSystemPropertyGetter", "(Lkotlin/jvm/functions/Function1;)V", "systemPropertySetter", "Lkotlin/Function2;", "getSystemPropertySetter", "()Lkotlin/jvm/functions/Function2;", "setSystemPropertySetter", "(Lkotlin/jvm/functions/Function2;)V", "systemPropertyCleaner", "getSystemPropertyCleaner", "setSystemPropertyCleaner", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Function1<String, String> getSystemPropertyCleaner() {
            return CompilerSystemProperties.systemPropertyCleaner;
        }

        public final Function1<String, String> getSystemPropertyGetter() {
            return CompilerSystemProperties.systemPropertyGetter;
        }

        public final Function2<String, String, String> getSystemPropertySetter() {
            return CompilerSystemProperties.systemPropertySetter;
        }

        public final void setSystemPropertyCleaner(Function1<? super String, String> function1) {
            CompilerSystemProperties.systemPropertyCleaner = function1;
        }

        public final void setSystemPropertyGetter(Function1<? super String, String> function1) {
            CompilerSystemProperties.systemPropertyGetter = function1;
        }

        public final void setSystemPropertySetter(Function2<? super String, ? super String, String> function2) {
            CompilerSystemProperties.systemPropertySetter = function2;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.cli.common.CompilerSystemProperties$clear$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<String, String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1, System.class, "clearProperty", "clearProperty(Ljava/lang/String;)Ljava/lang/String;", 0);
        }

        public final String invoke(String str) {
            return System.clearProperty(str);
        }
    }

    private CompilerSystemProperties(String str, int i, String str2, boolean z) {
        super(str, i);
        this.property = str2;
        this.alwaysDirectAccess = z;
    }
}

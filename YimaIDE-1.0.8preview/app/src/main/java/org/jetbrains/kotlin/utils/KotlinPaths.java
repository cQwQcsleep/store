package org.jetbrains.kotlin.utils;

import com.intellij.util.lang.JavaVersion;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.utils.KotlinPaths;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u000256J\u0010\u0010(\u001a\u00020\u00032\u0006\u0010(\u001a\u00020)H&J\u0010\u0010*\u001a\u00020\u00032\u0006\u0010(\u001a\u00020)H&J\u0012\u0010+\u001a\u0004\u0018\u00010\u00032\u0006\u0010(\u001a\u00020)H&J\u001c\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030#2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020)0.H\u0016J/\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030#2\u0006\u0010/\u001a\u0002002\u0012\u00101\u001a\n\u0012\u0006\b\u0001\u0012\u00020)02\"\u00020)H\u0016¢\u0006\u0002\u00103J'\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00030#2\u0012\u0010-\u001a\n\u0012\u0006\b\u0001\u0012\u00020)02\"\u00020)H\u0016¢\u0006\u0002\u00104R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0014\u0010\f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0005R\u0014\u0010\u000e\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0014\u0010\u0010\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0014\u0010\u0012\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0014\u0010\u0014\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0014\u0010\u0016\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0014\u0010\u0018\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0014\u0010\u001a\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u0014\u0010\u001c\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0005R\u0014\u0010\u001e\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0005R\u0014\u0010 \u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0005R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00067À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/utils/KotlinPaths;", "", "homePath", "Ljava/io/File;", "getHomePath", "()Ljava/io/File;", "libPath", "getLibPath", "stdlibPath", "getStdlibPath", "reflectPath", "getReflectPath", "scriptRuntimePath", "getScriptRuntimePath", "kotlinTestPath", "getKotlinTestPath", "stdlibSourcesPath", "getStdlibSourcesPath", "jsStdLibKlibPath", "getJsStdLibKlibPath", "wasmJsStdLibKlibPath", "getWasmJsStdLibKlibPath", "jsStdLibSrcJarPath", "getJsStdLibSrcJarPath", "jsKotlinTestKlibPath", "getJsKotlinTestKlibPath", "allOpenPluginJarPath", "getAllOpenPluginJarPath", "noArgPluginJarPath", "getNoArgPluginJarPath", "lombokPluginJarPath", "getLombokPluginJarPath", "samWithReceiverJarPath", "getSamWithReceiverJarPath", "compilerClasspath", "", "getCompilerClasspath", "()Ljava/util/List;", "compilerPath", "getCompilerPath", "jar", "Lorg/jetbrains/kotlin/utils/KotlinPaths$Jar;", "klib", "sourcesJar", "classPath", "jars", "Lkotlin/sequences/Sequence;", "base", "Lorg/jetbrains/kotlin/utils/KotlinPaths$ClassPaths;", "additionalJars", "", "(Lorg/jetbrains/kotlin/utils/KotlinPaths$ClassPaths;[Lorg/jetbrains/kotlin/utils/KotlinPaths$Jar;)Ljava/util/List;", "([Lorg/jetbrains/kotlin/utils/KotlinPaths$Jar;)Ljava/util/List;", "Jar", "ClassPaths", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public interface KotlinPaths {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001d¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/utils/KotlinPaths$Jar;", "", "baseName", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getBaseName", "()Ljava/lang/String;", "StdLib", "StdLibJdk7", "StdLibJdk8", "Reflect", "ScriptRuntime", "KotlinTest", "JsStdLib", "WasmStdLib", "JsKotlinTest", "AllOpenPlugin", "NoArgPlugin", "LombokPlugin", "SamWithReceiver", "SerializationPlugin", "Compiler", "ScriptingPlugin", "ScriptingImpl", "ScriptingLib", "ScriptingJvmLib", "CoroutinesCore", "KotlinDaemon", "MainKts", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public enum Jar {
        StdLib(PathUtil.KOTLIN_JAVA_STDLIB_NAME),
        StdLibJdk7(PathUtil.KOTLIN_JAVA_RUNTIME_JDK7_NAME),
        StdLibJdk8(PathUtil.KOTLIN_JAVA_RUNTIME_JDK8_NAME),
        Reflect(PathUtil.KOTLIN_JAVA_REFLECT_NAME),
        ScriptRuntime(PathUtil.KOTLIN_JAVA_SCRIPT_RUNTIME_NAME),
        KotlinTest(PathUtil.KOTLIN_TEST_NAME),
        JsStdLib(PathUtil.JS_LIB_NAME),
        WasmStdLib(PathUtil.WASM_JS_LIB_NAME),
        JsKotlinTest(PathUtil.KOTLIN_TEST_JS_NAME),
        AllOpenPlugin(PathUtil.ALLOPEN_PLUGIN_NAME),
        NoArgPlugin(PathUtil.NOARG_PLUGIN_NAME),
        LombokPlugin(PathUtil.LOMBOK_PLUGIN_NAME),
        SamWithReceiver(PathUtil.SAM_WITH_RECEIVER_PLUGIN_NAME),
        SerializationPlugin(PathUtil.SERIALIZATION_PLUGIN_NAME),
        Compiler(PathUtil.KOTLIN_COMPILER_NAME),
        ScriptingPlugin(PathUtil.KOTLIN_SCRIPTING_COMPILER_PLUGIN_NAME),
        ScriptingImpl(PathUtil.KOTLIN_SCRIPTING_COMPILER_IMPL_NAME),
        ScriptingLib(PathUtil.KOTLIN_SCRIPTING_COMMON_NAME),
        ScriptingJvmLib(PathUtil.KOTLIN_SCRIPTING_JVM_NAME),
        CoroutinesCore(PathUtil.KOTLINX_COROUTINES_CORE_NAME),
        KotlinDaemon(PathUtil.KOTLIN_DAEMON_NAME),
        MainKts(PathUtil.MAIN_KTS_NAME);

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final String baseName;

        Jar(String str) {
            this.baseName = str;
        }

        public static EnumEntries<Jar> getEntries() {
            return $ENTRIES;
        }

        public final String getBaseName() {
            return this.baseName;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.utils.KotlinPaths$classPath$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Jar, File> {
        public AnonymousClass1(Object obj) {
            super(1, obj, KotlinPaths.class, "jar", "jar(Lorg/jetbrains/kotlin/utils/KotlinPaths$Jar;)Ljava/io/File;", 0);
        }

        public final File invoke(Jar jar) {
            jar.getClass();
            return ((KotlinPaths) ((CallableReference) this).receiver).jar(jar);
        }
    }

    default List<File> classPath(ClassPaths base, Jar... additionalJars) {
        base.getClass();
        additionalJars.getClass();
        return classPath(SequencesKt.plus(kotlin.collections.CollectionsKt.asSequence(base.getContents()), additionalJars));
    }

    default File getAllOpenPluginJarPath() {
        return jar(Jar.AllOpenPlugin);
    }

    default List<File> getCompilerClasspath() {
        return classPath(ClassPaths.Compiler, new Jar[0]);
    }

    default File getCompilerPath() {
        return jar(Jar.Compiler);
    }

    File getHomePath();

    default File getJsKotlinTestKlibPath() {
        return klib(Jar.JsKotlinTest);
    }

    default File getJsStdLibKlibPath() {
        return klib(Jar.JsStdLib);
    }

    default File getJsStdLibSrcJarPath() {
        File fileSourcesJar = sourcesJar(Jar.JsStdLib);
        fileSourcesJar.getClass();
        return fileSourcesJar;
    }

    default File getKotlinTestPath() {
        return jar(Jar.KotlinTest);
    }

    File getLibPath();

    default File getLombokPluginJarPath() {
        return jar(Jar.LombokPlugin);
    }

    default File getNoArgPluginJarPath() {
        return jar(Jar.NoArgPlugin);
    }

    default File getReflectPath() {
        return jar(Jar.Reflect);
    }

    default File getSamWithReceiverJarPath() {
        return jar(Jar.SamWithReceiver);
    }

    default File getScriptRuntimePath() {
        return jar(Jar.ScriptRuntime);
    }

    default File getStdlibPath() {
        return jar(Jar.StdLib);
    }

    default File getStdlibSourcesPath() {
        File fileSourcesJar = sourcesJar(Jar.StdLib);
        fileSourcesJar.getClass();
        return fileSourcesJar;
    }

    default File getWasmJsStdLibKlibPath() {
        return klib(Jar.WasmStdLib);
    }

    File jar(Jar jar);

    File klib(Jar jar);

    File sourcesJar(Jar jar);

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'Compiler' uses external variables
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
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u001d\b\u0012\u0012\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\b\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\tB%\b\u0012\u0012\u0006\u0010\n\u001a\u00020\u0000\u0012\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\b\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u000bB1\b\u0012\u0012\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\b\"\u00020\u0004\u0012\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\r¢\u0006\u0004\b\u0005\u0010\u000eR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/utils/KotlinPaths$ClassPaths;", "", "contents", "", "Lorg/jetbrains/kotlin/utils/KotlinPaths$Jar;", "<init>", "(Ljava/lang/String;ILjava/util/List;)V", "jars", "", "(Ljava/lang/String;I[Lorg/jetbrains/kotlin/utils/KotlinPaths$Jar;)V", "baseClassPath", "(Ljava/lang/String;ILorg/jetbrains/kotlin/utils/KotlinPaths$ClassPaths;[Lorg/jetbrains/kotlin/utils/KotlinPaths$Jar;)V", "gen", "Lkotlin/Function0;", "(Ljava/lang/String;I[Lorg/jetbrains/kotlin/utils/KotlinPaths$Jar;Lkotlin/jvm/functions/Function0;)V", "getContents", "()Ljava/util/List;", "Empty", "StdLib", "Compiler", "CompilerWithScripting", "MainKts", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class ClassPaths {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ClassPaths[] $VALUES;
        public static final ClassPaths Compiler;
        public static final ClassPaths CompilerWithScripting;
        public static final ClassPaths Empty = new ClassPaths("Empty", 0, null, 1, null);
        public static final ClassPaths MainKts;
        public static final ClassPaths StdLib;
        private final List<Jar> contents;

        private static final /* synthetic */ ClassPaths[] $values() {
            return new ClassPaths[]{Empty, StdLib, Compiler, CompilerWithScripting, MainKts};
        }

        static {
            ClassPaths classPaths = new ClassPaths("StdLib", 1, new Jar[]{Jar.StdLib}, new Function0() { // from class: bd8
                public final Object invoke() {
                    return KotlinPaths.ClassPaths.b();
                }
            });
            StdLib = classPaths;
            Jar jar = Jar.Compiler;
            Jar jar2 = Jar.Reflect;
            Jar jar3 = Jar.ScriptRuntime;
            ClassPaths classPaths2 = new ClassPaths("Compiler", 2, classPaths, jar, jar2, jar3, Jar.KotlinDaemon, Jar.CoroutinesCore);
            Compiler = classPaths2;
            CompilerWithScripting = new ClassPaths("CompilerWithScripting", 3, classPaths2, Jar.ScriptingPlugin, Jar.ScriptingImpl, Jar.ScriptingLib, Jar.ScriptingJvmLib);
            MainKts = new ClassPaths("MainKts", 4, classPaths, Jar.MainKts, jar3, jar2);
            ClassPaths[] classPathsArr$values = $values();
            $VALUES = classPathsArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(classPathsArr$values);
        }

        private ClassPaths(String str, int i, Jar[] jarArr, Function0 function0) {
            this(str, i, kotlin.collections.CollectionsKt.plus(ArraysKt.asList(jarArr), (Iterable) function0.invoke()));
        }

        public static List b() {
            if (JavaVersion.current().compareTo(JavaVersion.compose(8)) >= 0) {
                return kotlin.collections.CollectionsKt.listOf(new Jar[]{Jar.StdLibJdk7, Jar.StdLibJdk8});
            }
            return JavaVersion.current().compareTo(JavaVersion.compose(7)) >= 0 ? kotlin.collections.CollectionsKt.listOf(Jar.StdLibJdk7) : kotlin.collections.CollectionsKt.emptyList();
        }

        public static EnumEntries<ClassPaths> getEntries() {
            return $ENTRIES;
        }

        public static ClassPaths valueOf(String str) {
            return (ClassPaths) Enum.valueOf(ClassPaths.class, str);
        }

        public static ClassPaths[] values() {
            return (ClassPaths[]) $VALUES.clone();
        }

        public final List<Jar> getContents() {
            return this.contents;
        }

        public /* synthetic */ ClassPaths(String str, int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, i, (i2 & 1) != 0 ? kotlin.collections.CollectionsKt.emptyList() : list);
        }

        private ClassPaths(String str, int i, ClassPaths classPaths, Jar... jarArr) {
            this(str, i, kotlin.collections.CollectionsKt.plus(classPaths.contents, jarArr));
        }

        private ClassPaths(String str, int i, List list) {
            super(str, i);
            this.contents = list;
        }
    }

    default List<File> classPath(Sequence<? extends Jar> jars) {
        jars.getClass();
        return SequencesKt.toList(SequencesKt.map(jars, new AnonymousClass1(this)));
    }

    default List<File> classPath(Jar... jars) {
        jars.getClass();
        return classPath(ArraysKt.asSequence(jars));
    }
}

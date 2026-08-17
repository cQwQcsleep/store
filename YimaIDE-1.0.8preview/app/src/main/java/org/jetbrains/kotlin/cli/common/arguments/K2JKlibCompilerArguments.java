package org.jetbrains.kotlin.cli.common.arguments;

import com.intellij.util.xmlb.annotations.Transient;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b6\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 W2\u00020\u0001:\u0001WB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010Q\u001a\u00020RH\u0014R*\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR*\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR*\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR8\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00112\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00118\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R*\u0010\u0018\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\b\"\u0004\b\u001a\u0010\nR&\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u001b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R&\u0010!\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u001b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R&\u0010$\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u001b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001e\"\u0004\b&\u0010 R&\u0010'\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u001b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001e\"\u0004\b)\u0010 R&\u0010*\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u001b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001e\"\u0004\b,\u0010 R&\u0010-\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u001b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001e\"\u0004\b/\u0010 R*\u00100\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\b\"\u0004\b2\u0010\nR&\u00103\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u001b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001e\"\u0004\b5\u0010 R8\u00106\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00112\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00118\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b7\u0010\u0014\"\u0004\b8\u0010\u0016R*\u00109\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\b\"\u0004\b;\u0010\nR*\u0010<\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\b\"\u0004\b>\u0010\nR&\u0010?\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u001b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u001e\"\u0004\bA\u0010 R&\u0010B\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u001b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u001e\"\u0004\bD\u0010 R8\u0010E\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00112\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00118\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\bF\u0010\u0014\"\u0004\bG\u0010\u0016R*\u0010H\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\b\"\u0004\bJ\u0010\nR*\u0010K\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\b\"\u0004\bM\u0010\nR&\u0010N\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u001b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u001e\"\u0004\bP\u0010 R\u0016\u0010S\u001a\u00020T8\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bU\u0010V¨\u0006X"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/K2JKlibCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "<init>", "()V", "value", Argument.Delimiters.none, "destination", "getDestination", "()Ljava/lang/String;", "setDestination", "(Ljava/lang/String;)V", ModuleXmlParser.CLASSPATH, "getClasspath", "setClasspath", "moduleName", "getModuleName", "setModuleName", Argument.Delimiters.none, "friendPaths", "getFriendPaths", "()[Ljava/lang/String;", "setFriendPaths", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "klibLibraries", "getKlibLibraries", "setKlibLibraries", Argument.Delimiters.none, "noStdlib", "getNoStdlib", "()Z", "setNoStdlib", "(Z)V", "expectBuiltinsAsPartOfStdlib", "getExpectBuiltinsAsPartOfStdlib", "setExpectBuiltinsAsPartOfStdlib", "noJdk", "getNoJdk", "setNoJdk", "noReflect", "getNoReflect", "setNoReflect", "typeEnhancementImprovementsInStrictMode", "getTypeEnhancementImprovementsInStrictMode", "setTypeEnhancementImprovementsInStrictMode", "enhanceTypeParameterTypesToDefNotNull", "getEnhanceTypeParameterTypesToDefNotNull", "setEnhanceTypeParameterTypesToDefNotNull", "jvmDefault", "getJvmDefault", "setJvmDefault", "valueClasses", "getValueClasses", "setValueClasses", "jsr305", "getJsr305", "setJsr305", "supportCompatqualCheckerFrameworkAnnotations", "getSupportCompatqualCheckerFrameworkAnnotations", "setSupportCompatqualCheckerFrameworkAnnotations", "jspecifyAnnotations", "getJspecifyAnnotations", "setJspecifyAnnotations", "inheritMultifileParts", "getInheritMultifileParts", "setInheritMultifileParts", "outputBuiltinsMetadata", "getOutputBuiltinsMetadata", "setOutputBuiltinsMetadata", "nullabilityAnnotations", "getNullabilityAnnotations", "setNullabilityAnnotations", "friendModules", "getFriendModules", "setFriendModules", "samConversions", "getSamConversions", "setSamConversions", "compileIr", "getCompileIr", "setCompileIr", "copyOf", "Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;", "configurator", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "getConfigurator", "()Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JKlibCompilerArguments extends CommonCompilerArguments {
    private static final long serialVersionUID = 0;

    @Argument(description = "List of directories and JAR/ZIP archives to search for user .kotlin_metadata files.", shortName = "-cp", value = "-classpath", valueDescription = "<path>")
    private String classpath;

    @Argument(description = "Enable the IR compilation phase.", value = "-Xcompile-ir")
    private boolean compileIr;
    private final transient CommonCompilerArgumentsConfigurator configurator = new K2JKlibCompilerArgumentsConfigurator();

    @Argument(description = "Destination for generated files.", value = "-d", valueDescription = "<klib>")
    private String destination;

    @Argument(description = "Enhance not-null-annotated type parameter types to definitely-non-nullable types ('@NotNull T' => 'T & Any').", value = "-Xenhance-type-parameter-types-to-def-not-null")
    private boolean enhanceTypeParameterTypesToDefNotNull;

    @Argument(description = "Enable behaviour needed to compile builtins as part of JVM stdlib", value = "-Xcompile-builtins-as-part-of-stdlib")
    private boolean expectBuiltinsAsPartOfStdlib;

    @Argument(description = "Paths to friend modules.", value = "-friend-modules", valueDescription = "<path>")
    private String friendModules;

    @Argument(description = "Paths to output directories for friend modules (modules whose internals should be visible).", value = "-Xfriend-paths", valueDescription = "<path>")
    private String[] friendPaths;

    @Argument(description = "Compile multifile classes as a hierarchy of parts and a facade.", value = "-Xmultifile-parts-inherit")
    private boolean inheritMultifileParts;

    @Argument(description = "Specify the behavior of 'jspecify' annotations.\nThe default value is 'warn'.", value = "-Xjspecify-annotations", valueDescription = "ignore|strict|warn")
    private String jspecifyAnnotations;

    @Argument(deprecatedName = "-Xjsr305-annotations", description = "Specify the behavior of 'JSR-305' nullability annotations:\n-Xjsr305={ignore/strict/warn}                   global (all non-@UnderMigration annotations)\n-Xjsr305=under-migration:{ignore/strict/warn}   all @UnderMigration annotations\n-Xjsr305=@<fq.name>:{ignore/strict/warn}        annotation with the given fully qualified class name\nModes:\n* ignore\n* strict (experimental; treat like other supported nullability annotations)\n* warn (report a warning)", value = "-Xjsr305", valueDescription = "{ignore/strict/warn}|under-migration:{ignore/strict/warn}|@<fq.name>:{ignore/strict/warn}")
    private String[] jsr305;

    @Argument(description = "Emit JVM default methods for interface declarations with bodies. The default is 'enable'.\n-jvm-default=enable              Generate default methods for non-abstract interface declarations, as well as 'DefaultImpls' classes with\n                                 static methods for compatibility with code compiled in the 'disable' mode.\n                                 This is the default behavior since language version 2.2.\n-jvm-default=no-compatibility    Generate default methods for non-abstract interface declarations. Do not generate 'DefaultImpls' classes.\n-jvm-default=disable             Do not generate JVM default methods. This is the default behavior up to language version 2.1.", value = "-jvm-default", valueDescription = "{enable|no-compatibility|disable}")
    private String jvmDefault;

    @Argument(description = "Paths to cross-platform libraries in the .klib format.", value = "-Xklib", valueDescription = "<path>")
    private String klibLibraries;

    @Argument(description = "Name of the generated .kotlin_module file.", value = "-module-name", valueDescription = "<name>")
    private String moduleName;

    @Argument(description = "Don't automatically include the Java runtime in the classpath.", value = "-no-jdk")
    private boolean noJdk;

    @Argument(description = "Don't automatically include the Kotlin reflection dependency in the classpath.", value = "-no-reflect")
    private boolean noReflect;

    @Argument(description = "Don't automatically include the Kotlin/JVM stdlib and Kotlin reflection dependencies in the classpath.", value = "-no-stdlib")
    private boolean noStdlib;

    @Argument(description = "Specify the behavior for specific Java nullability annotations (provided with fully qualified package name).\nModes:\n* ignore\n* strict\n* warn (report a warning)", value = "-Xnullability-annotations", valueDescription = "@<fq.name>:{ignore/strict/warn}")
    private String[] nullabilityAnnotations;

    @Argument(description = "Output builtins metadata as .kotlin_builtins files", value = "-Xoutput-builtins-metadata")
    private boolean outputBuiltinsMetadata;

    @Argument(description = "Select the code generation scheme for SAM conversions.\n-Xsam-conversions=indy          Generate SAM conversions using 'invokedynamic' with 'LambdaMetafactory.metafactory'.\n-Xsam-conversions=class         Generate SAM conversions as explicit classes.\nThe default value is 'indy'.", value = "-Xsam-conversions", valueDescription = "{class|indy}")
    private String samConversions;

    @Argument(description = "Specify the behavior for Checker Framework 'compatqual' annotations ('NullableDecl'/'NonNullDecl').\nThe default value is 'enable'.", value = "-Xsupport-compatqual-checker-framework-annotations", valueDescription = "enable|disable")
    private String supportCompatqualCheckerFrameworkAnnotations;

    @Argument(description = "Enable strict mode for improvements to type enhancement for loaded Java types based on nullability annotations,\nincluding the ability to read type-use annotations from class files.\nSee KT-45671 for more details.", value = "-Xtype-enhancement-improvements-strict-mode")
    private boolean typeEnhancementImprovementsInStrictMode;

    @Argument(description = "Enable experimental value classes.", value = "-Xvalue-classes")
    private boolean valueClasses;

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    @Override // org.jetbrains.kotlin.cli.common.arguments.Freezable
    public Freezable copyOf() throws NotImplementedError {
        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
    }

    public final String getClasspath() {
        return this.classpath;
    }

    public final boolean getCompileIr() {
        return this.compileIr;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
    @Transient
    public CommonCompilerArgumentsConfigurator getConfigurator() {
        return this.configurator;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final boolean getEnhanceTypeParameterTypesToDefNotNull() {
        return this.enhanceTypeParameterTypesToDefNotNull;
    }

    public final boolean getExpectBuiltinsAsPartOfStdlib() {
        return this.expectBuiltinsAsPartOfStdlib;
    }

    public final String getFriendModules() {
        return this.friendModules;
    }

    public final String[] getFriendPaths() {
        return this.friendPaths;
    }

    public final boolean getInheritMultifileParts() {
        return this.inheritMultifileParts;
    }

    public final String getJspecifyAnnotations() {
        return this.jspecifyAnnotations;
    }

    public final String[] getJsr305() {
        return this.jsr305;
    }

    public final String getJvmDefault() {
        return this.jvmDefault;
    }

    public final String getKlibLibraries() {
        return this.klibLibraries;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final boolean getNoJdk() {
        return this.noJdk;
    }

    public final boolean getNoReflect() {
        return this.noReflect;
    }

    public final boolean getNoStdlib() {
        return this.noStdlib;
    }

    public final String[] getNullabilityAnnotations() {
        return this.nullabilityAnnotations;
    }

    public final boolean getOutputBuiltinsMetadata() {
        return this.outputBuiltinsMetadata;
    }

    public final String getSamConversions() {
        return this.samConversions;
    }

    public final String getSupportCompatqualCheckerFrameworkAnnotations() {
        return this.supportCompatqualCheckerFrameworkAnnotations;
    }

    public final boolean getTypeEnhancementImprovementsInStrictMode() {
        return this.typeEnhancementImprovementsInStrictMode;
    }

    public final boolean getValueClasses() {
        return this.valueClasses;
    }

    public final void setClasspath(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.classpath = str;
    }

    public final void setCompileIr(boolean z) {
        checkFrozen();
        this.compileIr = z;
    }

    public final void setDestination(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.destination = str;
    }

    public final void setEnhanceTypeParameterTypesToDefNotNull(boolean z) {
        checkFrozen();
        this.enhanceTypeParameterTypesToDefNotNull = z;
    }

    public final void setExpectBuiltinsAsPartOfStdlib(boolean z) {
        checkFrozen();
        this.expectBuiltinsAsPartOfStdlib = z;
    }

    public final void setFriendModules(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.friendModules = str;
    }

    public final void setFriendPaths(String[] strArr) {
        checkFrozen();
        this.friendPaths = strArr;
    }

    public final void setInheritMultifileParts(boolean z) {
        checkFrozen();
        this.inheritMultifileParts = z;
    }

    public final void setJspecifyAnnotations(String str) {
        checkFrozen();
        this.jspecifyAnnotations = str;
    }

    public final void setJsr305(String[] strArr) {
        checkFrozen();
        this.jsr305 = strArr;
    }

    public final void setJvmDefault(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.jvmDefault = str;
    }

    public final void setKlibLibraries(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.klibLibraries = str;
    }

    public final void setModuleName(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.moduleName = str;
    }

    public final void setNoJdk(boolean z) {
        checkFrozen();
        this.noJdk = z;
    }

    public final void setNoReflect(boolean z) {
        checkFrozen();
        this.noReflect = z;
    }

    public final void setNoStdlib(boolean z) {
        checkFrozen();
        this.noStdlib = z;
    }

    public final void setNullabilityAnnotations(String[] strArr) {
        checkFrozen();
        this.nullabilityAnnotations = strArr;
    }

    public final void setOutputBuiltinsMetadata(boolean z) {
        checkFrozen();
        this.outputBuiltinsMetadata = z;
    }

    public final void setSamConversions(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.samConversions = str;
    }

    public final void setSupportCompatqualCheckerFrameworkAnnotations(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.supportCompatqualCheckerFrameworkAnnotations = str;
    }

    public final void setTypeEnhancementImprovementsInStrictMode(boolean z) {
        checkFrozen();
        this.typeEnhancementImprovementsInStrictMode = z;
    }

    public final void setValueClasses(boolean z) {
        checkFrozen();
        this.valueClasses = z;
    }
}

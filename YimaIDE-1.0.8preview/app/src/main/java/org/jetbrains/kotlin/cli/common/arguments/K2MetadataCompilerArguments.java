package org.jetbrains.kotlin.cli.common.arguments;

import com.intellij.util.xmlb.annotations.Transient;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010+\u001a\u00020,H\u0014R4\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR&\u0010\r\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R&\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00128\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R4\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\u0019\u0010\t\"\u0004\b\u001a\u0010\u000bR4\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\u001c\u0010\t\"\u0004\b\u001d\u0010\u000bR*\u0010\u001e\u001a\u0004\u0018\u00010\u00062\b\u0010\u0004\u001a\u0004\u0018\u00010\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000f\"\u0004\b \u0010\u0011R*\u0010!\u001a\u0004\u0018\u00010\u00062\b\u0010\u0004\u001a\u0004\u0018\u00010\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011R*\u0010$\u001a\u0004\u0018\u00010\u00062\b\u0010\u0004\u001a\u0004\u0018\u00010\u00068\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010\u0011R\u0016\u0010'\u001a\u00020(8\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/K2MetadataCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "<init>", "()V", "value", Argument.Delimiters.none, Argument.Delimiters.none, "friendPaths", "getFriendPaths", "()[Ljava/lang/String;", "setFriendPaths", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "klibZipFileAccessorCacheLimit", "getKlibZipFileAccessorCacheLimit", "()Ljava/lang/String;", "setKlibZipFileAccessorCacheLimit", "(Ljava/lang/String;)V", Argument.Delimiters.none, "legacyMetadataJar", "getLegacyMetadataJar", "()Z", "setLegacyMetadataJar", "(Z)V", "refinesPaths", "getRefinesPaths", "setRefinesPaths", "targetPlatform", "getTargetPlatform", "setTargetPlatform", ModuleXmlParser.CLASSPATH, "getClasspath", "setClasspath", "destination", "getDestination", "setDestination", "moduleName", "getModuleName", "setModuleName", "configurator", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "getConfigurator", "()Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "copyOf", "Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2MetadataCompilerArguments extends CommonCompilerArguments {

    @Argument(description = "List of directories and JAR/ZIP archives to search for user .kotlin_metadata files.", shortName = "-cp", value = "-classpath", valueDescription = "<path>")
    private String classpath;

    @Argument(description = "Destination for generated .kotlin_metadata files.", value = "-d", valueDescription = "<directory|jar>")
    private String destination;

    @Argument(description = "Produce a legacy metadata jar instead of metadata klib. Suitable only for K2 compilation", value = "-Xlegacy-metadata-jar-k2")
    private boolean legacyMetadataJar;

    @Argument(description = "Name of the generated .kotlin_module file.", value = "-module-name", valueDescription = "<name>")
    private String moduleName;

    @Argument(description = "Paths to output directories for friend modules (modules whose internals should be visible).", value = "-Xfriend-paths", valueDescription = "<path>")
    private String[] friendPaths = new String[0];

    @Argument(description = "Maximum number of klibs that can be cached during compilation. Default is 64.", value = "-Xklib-zip-file-accessor-cache-limit")
    private String klibZipFileAccessorCacheLimit = "64";

    @Argument(description = "Paths to output directories for refined modules (modules whose expects this module can actualize).", value = "-Xrefines-paths", valueDescription = "<path>")
    private String[] refinesPaths = new String[0];

    @Argument(description = "Target platform for metadata generation. Possible values: JVM, JS, WasmJs, WasmWasi, Native", value = "-Xtarget-platform")
    private String[] targetPlatform = new String[0];
    private final transient CommonCompilerArgumentsConfigurator configurator = new K2MetadataCompilerArgumentsConfigurator();

    @Override // org.jetbrains.kotlin.cli.common.arguments.Freezable
    public Freezable copyOf() {
        return K2MetadataCompilerArgumentsCopyGeneratedKt.copyK2MetadataCompilerArguments(this, new K2MetadataCompilerArguments());
    }

    public final String getClasspath() {
        return this.classpath;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
    @Transient
    public CommonCompilerArgumentsConfigurator getConfigurator() {
        return this.configurator;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final String[] getFriendPaths() {
        return this.friendPaths;
    }

    public final String getKlibZipFileAccessorCacheLimit() {
        return this.klibZipFileAccessorCacheLimit;
    }

    public final boolean getLegacyMetadataJar() {
        return this.legacyMetadataJar;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final String[] getRefinesPaths() {
        return this.refinesPaths;
    }

    public final String[] getTargetPlatform() {
        return this.targetPlatform;
    }

    public final void setClasspath(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.classpath = str;
    }

    public final void setDestination(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.destination = str;
    }

    public final void setFriendPaths(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.friendPaths = strArr;
    }

    public final void setKlibZipFileAccessorCacheLimit(String str) {
        str.getClass();
        checkFrozen();
        this.klibZipFileAccessorCacheLimit = str;
    }

    public final void setLegacyMetadataJar(boolean z) {
        checkFrozen();
        this.legacyMetadataJar = z;
    }

    public final void setModuleName(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.moduleName = str;
    }

    public final void setRefinesPaths(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.refinesPaths = strArr;
    }

    public final void setTargetPlatform(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.targetPlatform = strArr;
    }
}

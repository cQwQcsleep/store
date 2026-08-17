package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Deprecated;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0014\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R*\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR*\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR&\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R&\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\nR&\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R4\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u001a2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u001a8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR&\u0010!\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\b\"\u0004\b#\u0010\nR0\u0010$\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b%\u0010\u0003\u001a\u0004\b&\u0010\b\"\u0004\b'\u0010\nR*\u0010(\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\b\"\u0004\b*\u0010\nR&\u0010+\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0011\"\u0004\b-\u0010\u0013¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/CommonKlibBasedCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "<init>", "()V", "value", Argument.Delimiters.none, "customKlibAbiVersion", "getCustomKlibAbiVersion", "()Ljava/lang/String;", "setCustomKlibAbiVersion", "(Ljava/lang/String;)V", "duplicatedUniqueNameStrategy", "getDuplicatedUniqueNameStrategy", "setDuplicatedUniqueNameStrategy", Argument.Delimiters.none, "enableSignatureClashChecks", "getEnableSignatureClashChecks", "()Z", "setEnableSignatureClashChecks", "(Z)V", "irInlinerBeforeKlibSerialization", "getIrInlinerBeforeKlibSerialization", "setIrInlinerBeforeKlibSerialization", "normalizeAbsolutePath", "getNormalizeAbsolutePath", "setNormalizeAbsolutePath", Argument.Delimiters.none, "relativePathBases", "getRelativePathBases", "()[Ljava/lang/String;", "setRelativePathBases", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "klibZipFileAccessorCacheLimit", "getKlibZipFileAccessorCacheLimit", "setKlibZipFileAccessorCacheLimit", "partialLinkageMode", "getPartialLinkageMode$annotations", "getPartialLinkageMode", "setPartialLinkageMode", "partialLinkageLogLevel", "getPartialLinkageLogLevel", "setPartialLinkageLogLevel", "skipLibrarySpecialCompatibilityChecks", "getSkipLibrarySpecialCompatibilityChecks", "setSkipLibrarySpecialCompatibilityChecks", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CommonKlibBasedCompilerArguments extends CommonCompilerArguments {

    @Argument(description = "Specify the custom ABI version to be written in KLIB. This option is intended only for tests.\nWarning: This option does not affect KLIB ABI. Neither allows it making a KLIB backward-compatible with older ABI versions.\nThe only observable effect is that a custom ABI version is written to KLIB manifest file.", value = "-Xklib-abi-version", valueDescription = "<version>")
    private String customKlibAbiVersion;

    @Argument(description = "Klib dependencies usage strategy when multiple KLIBs has same `unique_name` property value.", value = "-Xklib-duplicated-unique-name-strategy", valueDescription = "{deny|allow-all-with-warning|allow-first-with-warning}")
    private String duplicatedUniqueNameStrategy;

    @Argument(description = "Normalize absolute paths in klibs.", value = "-Xklib-normalize-absolute-path")
    private boolean normalizeAbsolutePath;

    @Argument(description = "Define the compile-time log level for partial linkage.", value = "-Xpartial-linkage-loglevel", valueDescription = "{silent|info|warning|error}")
    private String partialLinkageLogLevel;

    @Argument(description = "This option is deprecated and will be deleted in future versions.\nThe partial linkage engine is always turned on.\nIf you would like to adjust the compile-time log level for partial linkage, use -Xpartial-linkage-loglevel.", value = "-Xpartial-linkage", valueDescription = "{enable|disable}")
    private String partialLinkageMode;

    @Argument(description = "Skip library compatibility checks for stdlib and kotlin.test library.", value = "-Xskip-library-special-compatibility-checks")
    private boolean skipLibrarySpecialCompatibilityChecks;

    @Argument(description = "Enable signature uniqueness checks.", value = "-Xklib-enable-signature-clash-checks")
    private boolean enableSignatureClashChecks = true;

    @Argument(description = "Set the mode of the experimental IR inliner on the first compilation stage.\n- `intra-module` mode enforces inlining of the functions only from the compiled module\n- `full` mode enforces inlining of all functions (from the compiled module and from all dependencies)\n   Warning: This mode will trigger setting the `pre-release` flag for the compiled library.\n- `disabled` mode completely disables the IR inliner\n- `default` mode lets the IR inliner run in `intra-module`, `full` or `disabled` mode based on the current language version\n        ", value = "-Xklib-ir-inliner", valueDescription = "{intra-module|full|disabled|default}")
    private String irInlinerBeforeKlibSerialization = "default";

    @Argument(description = "Relativize all the paths stored in a klib using the given path prefixes.\nThe supplied prefixes should be absolute paths to the directories containing the source code files.\nNote: The prefixes are applied in the same order as they are passed in this CLI argument.", value = "-Xklib-relative-path-base")
    private String[] relativePathBases = new String[0];

    @Argument(description = "Maximum number of klibs that can be cached during compilation. Default is 64.", value = "-Xklib-zip-file-accessor-cache-limit")
    private String klibZipFileAccessorCacheLimit = "64";

    @Deprecated(message = "This flag is deprecated")
    public static /* synthetic */ void getPartialLinkageMode$annotations() {
    }

    public final String getCustomKlibAbiVersion() {
        return this.customKlibAbiVersion;
    }

    public final String getDuplicatedUniqueNameStrategy() {
        return this.duplicatedUniqueNameStrategy;
    }

    public final boolean getEnableSignatureClashChecks() {
        return this.enableSignatureClashChecks;
    }

    public final String getIrInlinerBeforeKlibSerialization() {
        return this.irInlinerBeforeKlibSerialization;
    }

    public final String getKlibZipFileAccessorCacheLimit() {
        return this.klibZipFileAccessorCacheLimit;
    }

    public final boolean getNormalizeAbsolutePath() {
        return this.normalizeAbsolutePath;
    }

    public final String getPartialLinkageLogLevel() {
        return this.partialLinkageLogLevel;
    }

    public final String getPartialLinkageMode() {
        return this.partialLinkageMode;
    }

    public final String[] getRelativePathBases() {
        return this.relativePathBases;
    }

    public final boolean getSkipLibrarySpecialCompatibilityChecks() {
        return this.skipLibrarySpecialCompatibilityChecks;
    }

    public final void setCustomKlibAbiVersion(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.customKlibAbiVersion = str;
    }

    public final void setDuplicatedUniqueNameStrategy(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.duplicatedUniqueNameStrategy = str;
    }

    public final void setEnableSignatureClashChecks(boolean z) {
        checkFrozen();
        this.enableSignatureClashChecks = z;
    }

    public final void setIrInlinerBeforeKlibSerialization(String str) {
        str.getClass();
        checkFrozen();
        this.irInlinerBeforeKlibSerialization = str;
    }

    public final void setKlibZipFileAccessorCacheLimit(String str) {
        str.getClass();
        checkFrozen();
        this.klibZipFileAccessorCacheLimit = str;
    }

    public final void setNormalizeAbsolutePath(boolean z) {
        checkFrozen();
        this.normalizeAbsolutePath = z;
    }

    public final void setPartialLinkageLogLevel(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.partialLinkageLogLevel = str;
    }

    public final void setPartialLinkageMode(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.partialLinkageMode = str;
    }

    public final void setRelativePathBases(String[] strArr) {
        strArr.getClass();
        checkFrozen();
        this.relativePathBases = strArr;
    }

    public final void setSkipLibrarySpecialCompatibilityChecks(boolean z) {
        checkFrozen();
        this.skipLibrarySpecialCompatibilityChecks = z;
    }
}

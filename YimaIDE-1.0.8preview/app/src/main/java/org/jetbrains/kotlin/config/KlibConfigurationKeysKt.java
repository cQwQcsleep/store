package org.jetbrains.kotlin.config;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.konan.file.ZipFileSystemAccessor;
import org.jetbrains.kotlin.library.KotlinAbiVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\"4\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00042\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\"(\u0010\n\u001a\u00020\t*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\"(\u0010\u000f\u001a\u00020\t*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000e\",\u0010\u0013\u001a\u0004\u0018\u00010\u0012*\u00020\u00042\b\u0010\u0000\u001a\u0004\u0018\u00010\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017\",\u0010\u0019\u001a\u0004\u0018\u00010\u0018*\u00020\u00042\b\u0010\u0000\u001a\u0004\u0018\u00010\u00188F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\"(\u0010\u001f\u001a\u00020\u001e*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\u001e8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\",\u0010%\u001a\u0004\u0018\u00010$*\u00020\u00042\b\u0010\u0000\u001a\u0004\u0018\u00010$8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)\"(\u0010*\u001a\u00020\t*\u00020\u00042\u0006\u0010\u0000\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b+\u0010\f\"\u0004\b,\u0010\u000e¨\u0006-"}, d2 = {"value", Argument.Delimiters.none, Argument.Delimiters.none, "klibRelativePathBases", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getKlibRelativePathBases", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/util/List;", "setKlibRelativePathBases", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;)V", Argument.Delimiters.none, "klibNormalizeAbsolutePath", "getKlibNormalizeAbsolutePath", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Z", "setKlibNormalizeAbsolutePath", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Z)V", "produceKlibSignaturesClashChecks", "getProduceKlibSignaturesClashChecks", "setProduceKlibSignaturesClashChecks", "Lorg/jetbrains/kotlin/config/DuplicatedUniqueNameStrategy;", "duplicatedUniqueNameStrategy", "getDuplicatedUniqueNameStrategy", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/DuplicatedUniqueNameStrategy;", "setDuplicatedUniqueNameStrategy", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/DuplicatedUniqueNameStrategy;)V", "Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "customKlibAbiVersion", "getCustomKlibAbiVersion", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "setCustomKlibAbiVersion", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/library/KotlinAbiVersion;)V", "Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;", "klibAbiCompatibilityLevel", "getKlibAbiCompatibilityLevel", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;", "setKlibAbiCompatibilityLevel", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;)V", "Lorg/jetbrains/kotlin/konan/file/ZipFileSystemAccessor;", "zipFileSystemAccessor", "getZipFileSystemAccessor", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/konan/file/ZipFileSystemAccessor;", "setZipFileSystemAccessor", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/konan/file/ZipFileSystemAccessor;)V", "skipLibrarySpecialCompatibilityChecks", "getSkipLibrarySpecialCompatibilityChecks", "setSkipLibrarySpecialCompatibilityChecks", "org.jetbrains.kotlin:config"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KlibConfigurationKeysKt {
    public static final KotlinAbiVersion getCustomKlibAbiVersion(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (KotlinAbiVersion) compilerConfiguration.get(KlibConfigurationKeys.CUSTOM_KLIB_ABI_VERSION);
    }

    public static final DuplicatedUniqueNameStrategy getDuplicatedUniqueNameStrategy(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (DuplicatedUniqueNameStrategy) compilerConfiguration.get(KlibConfigurationKeys.DUPLICATED_UNIQUE_NAME_STRATEGY);
    }

    public static final KlibAbiCompatibilityLevel getKlibAbiCompatibilityLevel(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (KlibAbiCompatibilityLevel) compilerConfiguration.get(KlibConfigurationKeys.KLIB_ABI_COMPATIBILITY_LEVEL, KlibAbiCompatibilityLevel.INSTANCE.getLATEST_STABLE());
    }

    public static final boolean getKlibNormalizeAbsolutePath(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(KlibConfigurationKeys.KLIB_NORMALIZE_ABSOLUTE_PATH);
    }

    public static final List<String> getKlibRelativePathBases(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getList(KlibConfigurationKeys.KLIB_RELATIVE_PATH_BASES);
    }

    public static final boolean getProduceKlibSignaturesClashChecks(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(KlibConfigurationKeys.PRODUCE_KLIB_SIGNATURES_CLASH_CHECKS);
    }

    public static final boolean getSkipLibrarySpecialCompatibilityChecks(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return compilerConfiguration.getBoolean(KlibConfigurationKeys.SKIP_LIBRARY_SPECIAL_COMPATIBILITY_CHECKS);
    }

    public static final ZipFileSystemAccessor getZipFileSystemAccessor(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return (ZipFileSystemAccessor) compilerConfiguration.get(KlibConfigurationKeys.ZIP_FILE_SYSTEM_ACCESSOR);
    }

    public static final void setCustomKlibAbiVersion(CompilerConfiguration compilerConfiguration, KotlinAbiVersion kotlinAbiVersion) {
        compilerConfiguration.getClass();
        compilerConfiguration.putIfNotNull(KlibConfigurationKeys.CUSTOM_KLIB_ABI_VERSION, kotlinAbiVersion);
    }

    public static final void setDuplicatedUniqueNameStrategy(CompilerConfiguration compilerConfiguration, DuplicatedUniqueNameStrategy duplicatedUniqueNameStrategy) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<DuplicatedUniqueNameStrategy> compilerConfigurationKey = KlibConfigurationKeys.DUPLICATED_UNIQUE_NAME_STRATEGY;
        if (duplicatedUniqueNameStrategy != null) {
            compilerConfiguration.put(compilerConfigurationKey, duplicatedUniqueNameStrategy);
        } else {
            w01.a("nullable values are not allowed");
        }
    }

    public static final void setKlibAbiCompatibilityLevel(CompilerConfiguration compilerConfiguration, KlibAbiCompatibilityLevel klibAbiCompatibilityLevel) {
        compilerConfiguration.getClass();
        klibAbiCompatibilityLevel.getClass();
        compilerConfiguration.put(KlibConfigurationKeys.KLIB_ABI_COMPATIBILITY_LEVEL, klibAbiCompatibilityLevel);
    }

    public static final void setKlibNormalizeAbsolutePath(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(KlibConfigurationKeys.KLIB_NORMALIZE_ABSOLUTE_PATH, Boolean.valueOf(z));
    }

    public static final void setKlibRelativePathBases(CompilerConfiguration compilerConfiguration, List<String> list) {
        compilerConfiguration.getClass();
        list.getClass();
        compilerConfiguration.put(KlibConfigurationKeys.KLIB_RELATIVE_PATH_BASES, list);
    }

    public static final void setProduceKlibSignaturesClashChecks(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(KlibConfigurationKeys.PRODUCE_KLIB_SIGNATURES_CLASH_CHECKS, Boolean.valueOf(z));
    }

    public static final void setSkipLibrarySpecialCompatibilityChecks(CompilerConfiguration compilerConfiguration, boolean z) {
        compilerConfiguration.getClass();
        compilerConfiguration.put(KlibConfigurationKeys.SKIP_LIBRARY_SPECIAL_COMPATIBILITY_CHECKS, Boolean.valueOf(z));
    }

    public static final void setZipFileSystemAccessor(CompilerConfiguration compilerConfiguration, ZipFileSystemAccessor zipFileSystemAccessor) {
        compilerConfiguration.getClass();
        CompilerConfigurationKey<ZipFileSystemAccessor> compilerConfigurationKey = KlibConfigurationKeys.ZIP_FILE_SYSTEM_ACCESSOR;
        if (zipFileSystemAccessor != null) {
            compilerConfiguration.put(compilerConfigurationKey, zipFileSystemAccessor);
        } else {
            w01.a("nullable values are not allowed");
        }
    }
}

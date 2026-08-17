package org.jetbrains.kotlin.config;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.konan.file.ZipFileSystemAccessor;
import org.jetbrains.kotlin.library.KotlinAbiVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/config/KlibConfigurationKeys;", Argument.Delimiters.none, "<init>", "()V", "KLIB_RELATIVE_PATH_BASES", "Lorg/jetbrains/kotlin/config/CompilerConfigurationKey;", Argument.Delimiters.none, Argument.Delimiters.none, "KLIB_NORMALIZE_ABSOLUTE_PATH", Argument.Delimiters.none, "PRODUCE_KLIB_SIGNATURES_CLASH_CHECKS", "DUPLICATED_UNIQUE_NAME_STRATEGY", "Lorg/jetbrains/kotlin/config/DuplicatedUniqueNameStrategy;", "CUSTOM_KLIB_ABI_VERSION", "Lorg/jetbrains/kotlin/library/KotlinAbiVersion;", "KLIB_ABI_COMPATIBILITY_LEVEL", "Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;", "ZIP_FILE_SYSTEM_ACCESSOR", "Lorg/jetbrains/kotlin/konan/file/ZipFileSystemAccessor;", "SKIP_LIBRARY_SPECIAL_COMPATIBILITY_CHECKS", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KlibConfigurationKeys {
    public static final CompilerConfigurationKey<KotlinAbiVersion> CUSTOM_KLIB_ABI_VERSION;
    public static final CompilerConfigurationKey<DuplicatedUniqueNameStrategy> DUPLICATED_UNIQUE_NAME_STRATEGY;
    public static final KlibConfigurationKeys INSTANCE = new KlibConfigurationKeys();
    public static final CompilerConfigurationKey<KlibAbiCompatibilityLevel> KLIB_ABI_COMPATIBILITY_LEVEL;
    public static final CompilerConfigurationKey<Boolean> KLIB_NORMALIZE_ABSOLUTE_PATH;
    public static final CompilerConfigurationKey<List<String>> KLIB_RELATIVE_PATH_BASES;
    public static final CompilerConfigurationKey<Boolean> PRODUCE_KLIB_SIGNATURES_CLASH_CHECKS;
    public static final CompilerConfigurationKey<Boolean> SKIP_LIBRARY_SPECIAL_COMPATIBILITY_CHECKS;
    public static final CompilerConfigurationKey<ZipFileSystemAccessor> ZIP_FILE_SYSTEM_ACCESSOR;

    static {
        CompilerConfigurationKey.Companion companion = CompilerConfigurationKey.INSTANCE;
        KLIB_RELATIVE_PATH_BASES = companion.create("KLIB_RELATIVE_PATH_BASES");
        KLIB_NORMALIZE_ABSOLUTE_PATH = companion.create("KLIB_NORMALIZE_ABSOLUTE_PATH");
        PRODUCE_KLIB_SIGNATURES_CLASH_CHECKS = companion.create("PRODUCE_KLIB_SIGNATURES_CLASH_CHECKS");
        DUPLICATED_UNIQUE_NAME_STRATEGY = companion.create("DUPLICATED_UNIQUE_NAME_STRATEGY");
        CUSTOM_KLIB_ABI_VERSION = companion.create("CUSTOM_KLIB_ABI_VERSION");
        KLIB_ABI_COMPATIBILITY_LEVEL = companion.create("KLIB_ABI_COMPATIBILITY_LEVEL");
        ZIP_FILE_SYSTEM_ACCESSOR = companion.create("ZIP_FILE_SYSTEM_ACCESSOR");
        SKIP_LIBRARY_SPECIAL_COMPATIBILITY_CHECKS = companion.create("SKIP_LIBRARY_SPECIAL_COMPATIBILITY_CHECKS");
    }

    private KlibConfigurationKeys() {
    }
}

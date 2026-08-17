package org.jetbrains.kotlin.konan.library;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0001\u001a\u0016\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"KLIB_INTEROP_IR_PROVIDER_IDENTIFIER", "", "KONAN_DISTRIBUTION_COMMONIZED_LIBS_DIR", "KONAN_DISTRIBUTION_COMMON_LIBS_DIR", "KONAN_DISTRIBUTION_KLIB_DIR", "KONAN_DISTRIBUTION_PLATFORM_LIBS_DIR", "KONAN_DISTRIBUTION_SOURCES_DIR", "KONAN_DISTRIBUTION_TOOLS_DIR", "KONAN_PLATFORM_LIBS_NAME_PREFIX", "KONAN_STDLIB_NAME", "konanCommonLibraryPath", "Ljava/io/File;", "libraryName", "konanPlatformLibraryPath", NativeLibraryConstantsKt.KONAN_DISTRIBUTION_PLATFORM_LIBS_DIR, "kotlin-native-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NativeLibraryConstantsKt {
    public static final String KLIB_INTEROP_IR_PROVIDER_IDENTIFIER = "kotlin.native.cinterop";
    public static final String KONAN_DISTRIBUTION_COMMONIZED_LIBS_DIR = "commonized";
    public static final String KONAN_DISTRIBUTION_COMMON_LIBS_DIR = "common";
    public static final String KONAN_DISTRIBUTION_KLIB_DIR = "klib";
    public static final String KONAN_DISTRIBUTION_PLATFORM_LIBS_DIR = "platform";
    public static final String KONAN_DISTRIBUTION_SOURCES_DIR = "sources";
    public static final String KONAN_DISTRIBUTION_TOOLS_DIR = "tools";
    public static final String KONAN_PLATFORM_LIBS_NAME_PREFIX = "org.jetbrains.kotlin.native.platform.";
    public static final String KONAN_STDLIB_NAME = "stdlib";

    public static final File konanCommonLibraryPath(String str) {
        str.getClass();
        return FilesKt.resolve(new File("klib", KONAN_DISTRIBUTION_COMMON_LIBS_DIR), str);
    }

    public static final File konanPlatformLibraryPath(String str, String str2) {
        str.getClass();
        str2.getClass();
        return FilesKt.resolve(FilesKt.resolve(new File("klib", KONAN_DISTRIBUTION_PLATFORM_LIBS_DIR), str2), str);
    }
}

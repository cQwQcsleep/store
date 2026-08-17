package org.jetbrains.kotlin.library.components;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/library/components/KlibMetadataConstants;", "", "()V", "KLIB_METADATA_FILE_EXTENSION", "", "KLIB_METADATA_FILE_EXTENSION_WITH_DOT", "KLIB_METADATA_FOLDER_NAME", "KLIB_MODULE_METADATA_FILE_NAME", "KLIB_NONROOT_PACKAGE_FRAGMENT_FOLDER_PREFIX", "KLIB_ROOT_PACKAGE_FRAGMENT_FOLDER_NAME", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KlibMetadataConstants {
    public static final KlibMetadataConstants INSTANCE = new KlibMetadataConstants();
    public static final String KLIB_METADATA_FILE_EXTENSION = "knm";
    public static final String KLIB_METADATA_FILE_EXTENSION_WITH_DOT = ".knm";
    public static final String KLIB_METADATA_FOLDER_NAME = "linkdata";
    public static final String KLIB_MODULE_METADATA_FILE_NAME = "module";
    public static final String KLIB_NONROOT_PACKAGE_FRAGMENT_FOLDER_PREFIX = "package_";
    public static final String KLIB_ROOT_PACKAGE_FRAGMENT_FOLDER_NAME = "root_package";

    private KlibMetadataConstants() {
    }
}

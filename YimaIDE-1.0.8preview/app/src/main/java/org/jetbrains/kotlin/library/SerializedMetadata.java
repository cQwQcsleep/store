package org.jetbrains.kotlin.library;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.library.components.KlibMetadataConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\t\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00050\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00050\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/library/SerializedMetadata;", "", KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME, "", "fragments", "", "fragmentNames", "", "metadataVersion", "", "([BLjava/util/List;Ljava/util/List;[I)V", "getFragmentNames", "()Ljava/util/List;", "getFragments", "getMetadataVersion", "()[I", "getModule", "()[B", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SerializedMetadata {
    private final List<String> fragmentNames;
    private final List<List<byte[]>> fragments;
    private final int[] metadataVersion;
    private final byte[] module;

    /* JADX WARN: Multi-variable type inference failed */
    public SerializedMetadata(byte[] bArr, List<? extends List<byte[]>> list, List<String> list2, int[] iArr) {
        bArr.getClass();
        list.getClass();
        list2.getClass();
        iArr.getClass();
        this.module = bArr;
        this.fragments = list;
        this.fragmentNames = list2;
        this.metadataVersion = iArr;
    }

    public final List<String> getFragmentNames() {
        return this.fragmentNames;
    }

    public final List<List<byte[]>> getFragments() {
        return this.fragments;
    }

    public final int[] getMetadataVersion() {
        return this.metadataVersion;
    }

    public final byte[] getModule() {
        return this.module;
    }
}

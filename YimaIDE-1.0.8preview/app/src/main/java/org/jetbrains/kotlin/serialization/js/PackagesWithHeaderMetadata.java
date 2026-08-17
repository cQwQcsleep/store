package org.jetbrains.kotlin.serialization.js;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.library.KotlinLibraryKt;
import org.jetbrains.kotlin.utils.JsMetadataVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/serialization/js/PackagesWithHeaderMetadata;", "", KotlinLibraryKt.KLIB_PROPERTY_HEADER, "", "packages", "", "metadataVersion", "Lorg/jetbrains/kotlin/utils/JsMetadataVersion;", "<init>", "([BLjava/util/List;Lorg/jetbrains/kotlin/utils/JsMetadataVersion;)V", "getHeader", "()[B", "getPackages", "()Ljava/util/List;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/utils/JsMetadataVersion;", "org.jetbrains.kotlin:js.serializer"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PackagesWithHeaderMetadata {
    private final byte[] header;
    private final JsMetadataVersion metadataVersion;
    private final List<byte[]> packages;

    public PackagesWithHeaderMetadata(byte[] bArr, List<byte[]> list, JsMetadataVersion jsMetadataVersion) {
        bArr.getClass();
        list.getClass();
        jsMetadataVersion.getClass();
        this.header = bArr;
        this.packages = list;
        this.metadataVersion = jsMetadataVersion;
    }

    public final byte[] getHeader() {
        return this.header;
    }

    public final JsMetadataVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public final List<byte[]> getPackages() {
        return this.packages;
    }
}

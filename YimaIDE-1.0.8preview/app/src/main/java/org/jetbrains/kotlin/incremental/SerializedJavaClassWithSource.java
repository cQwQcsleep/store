package org.jetbrains.kotlin.incremental;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/incremental/SerializedJavaClassWithSource;", "", "source", "Ljava/io/File;", "proto", "Lorg/jetbrains/kotlin/incremental/SerializedJavaClass;", "<init>", "(Ljava/io/File;Lorg/jetbrains/kotlin/incremental/SerializedJavaClass;)V", "getSource", "()Ljava/io/File;", "getProto", "()Lorg/jetbrains/kotlin/incremental/SerializedJavaClass;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SerializedJavaClassWithSource {
    private final SerializedJavaClass proto;
    private final File source;

    public SerializedJavaClassWithSource(File file, SerializedJavaClass serializedJavaClass) {
        file.getClass();
        serializedJavaClass.getClass();
        this.source = file;
        this.proto = serializedJavaClass;
    }

    public static /* synthetic */ SerializedJavaClassWithSource copy$default(SerializedJavaClassWithSource serializedJavaClassWithSource, File file, SerializedJavaClass serializedJavaClass, int i, Object obj) {
        if ((i & 1) != 0) {
            file = serializedJavaClassWithSource.source;
        }
        if ((i & 2) != 0) {
            serializedJavaClass = serializedJavaClassWithSource.proto;
        }
        return serializedJavaClassWithSource.copy(file, serializedJavaClass);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final File getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SerializedJavaClass getProto() {
        return this.proto;
    }

    public final SerializedJavaClassWithSource copy(File source, SerializedJavaClass proto) {
        source.getClass();
        proto.getClass();
        return new SerializedJavaClassWithSource(source, proto);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SerializedJavaClassWithSource)) {
            return false;
        }
        SerializedJavaClassWithSource serializedJavaClassWithSource = (SerializedJavaClassWithSource) other;
        return Intrinsics.areEqual(this.source, serializedJavaClassWithSource.source) && Intrinsics.areEqual(this.proto, serializedJavaClassWithSource.proto);
    }

    public final SerializedJavaClass getProto() {
        return this.proto;
    }

    public final File getSource() {
        return this.source;
    }

    public int hashCode() {
        return (this.source.hashCode() * 31) + this.proto.hashCode();
    }

    public String toString() {
        return "SerializedJavaClassWithSource(source=" + this.source + ", proto=" + this.proto + ')';
    }
}

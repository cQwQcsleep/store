package org.jetbrains.kotlin.incremental;

import kotlin.Metadata;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolverImpl;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.serialization.deserialization.NameResolverUtilKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/incremental/SerializedJavaClass;", "", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "stringTable", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$StringTable;", "qualifiedNameTable", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$QualifiedNameTable;", "<init>", "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;Lorg/jetbrains/kotlin/metadata/ProtoBuf$StringTable;Lorg/jetbrains/kotlin/metadata/ProtoBuf$QualifiedNameTable;)V", "getProto", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "getStringTable", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$StringTable;", "getQualifiedNameTable", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$QualifiedNameTable;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SerializedJavaClass {
    private final ProtoBuf.Class proto;
    private final ProtoBuf.QualifiedNameTable qualifiedNameTable;
    private final ProtoBuf.StringTable stringTable;

    public SerializedJavaClass(ProtoBuf.Class r1, ProtoBuf.StringTable stringTable, ProtoBuf.QualifiedNameTable qualifiedNameTable) {
        r1.getClass();
        stringTable.getClass();
        qualifiedNameTable.getClass();
        this.proto = r1;
        this.stringTable = stringTable;
        this.qualifiedNameTable = qualifiedNameTable;
    }

    public final ClassId getClassId() {
        return NameResolverUtilKt.getClassId(new NameResolverImpl(this.stringTable, this.qualifiedNameTable), this.proto.getFqName());
    }

    public final ProtoBuf.Class getProto() {
        return this.proto;
    }

    public final ProtoBuf.QualifiedNameTable getQualifiedNameTable() {
        return this.qualifiedNameTable;
    }

    public final ProtoBuf.StringTable getStringTable() {
        return this.stringTable;
    }
}

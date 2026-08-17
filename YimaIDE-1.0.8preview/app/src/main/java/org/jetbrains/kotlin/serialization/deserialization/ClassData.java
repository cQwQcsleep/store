package org.jetbrains.kotlin.serialization.deserialization;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/ClassData;", "", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "sourceElement", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "<init>", "(Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lorg/jetbrains/kotlin/descriptors/SourceElement;)V", "getNameResolver", "()Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "getClassProto", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "getSourceElement", "()Lorg/jetbrains/kotlin/descriptors/SourceElement;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:deserialization.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final /* data */ class ClassData {
    private final ProtoBuf.Class classProto;
    private final BinaryVersion metadataVersion;
    private final NameResolver nameResolver;
    private final SourceElement sourceElement;

    public ClassData(NameResolver nameResolver, ProtoBuf.Class r2, BinaryVersion binaryVersion, SourceElement sourceElement) {
        nameResolver.getClass();
        r2.getClass();
        binaryVersion.getClass();
        sourceElement.getClass();
        this.nameResolver = nameResolver;
        this.classProto = r2;
        this.metadataVersion = binaryVersion;
        this.sourceElement = sourceElement;
    }

    public static /* synthetic */ ClassData copy$default(ClassData classData, NameResolver nameResolver, ProtoBuf.Class r2, BinaryVersion binaryVersion, SourceElement sourceElement, int i, Object obj) {
        if ((i & 1) != 0) {
            nameResolver = classData.nameResolver;
        }
        if ((i & 2) != 0) {
            r2 = classData.classProto;
        }
        if ((i & 4) != 0) {
            binaryVersion = classData.metadataVersion;
        }
        if ((i & 8) != 0) {
            sourceElement = classData.sourceElement;
        }
        return classData.copy(nameResolver, r2, binaryVersion, sourceElement);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final NameResolver getNameResolver() {
        return this.nameResolver;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ProtoBuf.Class getClassProto() {
        return this.classProto;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SourceElement getSourceElement() {
        return this.sourceElement;
    }

    public final ClassData copy(NameResolver nameResolver, ProtoBuf.Class classProto, BinaryVersion metadataVersion, SourceElement sourceElement) {
        nameResolver.getClass();
        classProto.getClass();
        metadataVersion.getClass();
        sourceElement.getClass();
        return new ClassData(nameResolver, classProto, metadataVersion, sourceElement);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClassData)) {
            return false;
        }
        ClassData classData = (ClassData) other;
        return Intrinsics.areEqual(this.nameResolver, classData.nameResolver) && Intrinsics.areEqual(this.classProto, classData.classProto) && Intrinsics.areEqual(this.metadataVersion, classData.metadataVersion) && Intrinsics.areEqual(this.sourceElement, classData.sourceElement);
    }

    public final ProtoBuf.Class getClassProto() {
        return this.classProto;
    }

    public final BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public final NameResolver getNameResolver() {
        return this.nameResolver;
    }

    public final SourceElement getSourceElement() {
        return this.sourceElement;
    }

    public int hashCode() {
        return (((((this.nameResolver.hashCode() * 31) + this.classProto.hashCode()) * 31) + this.metadataVersion.hashCode()) * 31) + this.sourceElement.hashCode();
    }

    public String toString() {
        return "ClassData(nameResolver=" + this.nameResolver + ", classProto=" + this.classProto + ", metadataVersion=" + this.metadataVersion + ", sourceElement=" + this.sourceElement + ')';
    }
}

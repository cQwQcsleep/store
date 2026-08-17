package org.jetbrains.kotlin.psi.stubs.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/psi/stubs/impl/EnumData;", "", "enumClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "enumEntryName", "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/name/Name;)V", "getEnumClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getEnumEntryName", "()Lorg/jetbrains/kotlin/name/Name;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:psi-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class EnumData {
    private final ClassId enumClassId;
    private final Name enumEntryName;

    public EnumData(ClassId classId, Name name) {
        classId.getClass();
        name.getClass();
        this.enumClassId = classId;
        this.enumEntryName = name;
    }

    public static /* synthetic */ EnumData copy$default(EnumData enumData, ClassId classId, Name name, int i, Object obj) {
        if ((i & 1) != 0) {
            classId = enumData.enumClassId;
        }
        if ((i & 2) != 0) {
            name = enumData.enumEntryName;
        }
        return enumData.copy(classId, name);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ClassId getEnumClassId() {
        return this.enumClassId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Name getEnumEntryName() {
        return this.enumEntryName;
    }

    public final EnumData copy(ClassId enumClassId, Name enumEntryName) {
        enumClassId.getClass();
        enumEntryName.getClass();
        return new EnumData(enumClassId, enumEntryName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EnumData)) {
            return false;
        }
        EnumData enumData = (EnumData) other;
        return Intrinsics.areEqual(this.enumClassId, enumData.enumClassId) && Intrinsics.areEqual(this.enumEntryName, enumData.enumEntryName);
    }

    public final ClassId getEnumClassId() {
        return this.enumClassId;
    }

    public final Name getEnumEntryName() {
        return this.enumEntryName;
    }

    public int hashCode() {
        return (this.enumClassId.hashCode() * 31) + this.enumEntryName.hashCode();
    }

    public String toString() {
        return "EnumData(enumClassId=" + this.enumClassId + ", enumEntryName=" + this.enumEntryName + ')';
    }
}

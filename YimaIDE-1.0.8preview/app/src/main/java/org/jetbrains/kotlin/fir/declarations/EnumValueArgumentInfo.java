package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/EnumValueArgumentInfo;", Argument.Delimiters.none, "enumClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "enumEntryName", "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/name/Name;)V", "getEnumClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getEnumEntryName", "()Lorg/jetbrains/kotlin/name/Name;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class EnumValueArgumentInfo {
    private final ClassId enumClassId;
    private final Name enumEntryName;

    public EnumValueArgumentInfo(ClassId classId, Name name) {
        name.getClass();
        this.enumClassId = classId;
        this.enumEntryName = name;
    }

    public static /* synthetic */ EnumValueArgumentInfo copy$default(EnumValueArgumentInfo enumValueArgumentInfo, ClassId classId, Name name, int i, Object obj) {
        if ((i & 1) != 0) {
            classId = enumValueArgumentInfo.enumClassId;
        }
        if ((i & 2) != 0) {
            name = enumValueArgumentInfo.enumEntryName;
        }
        return enumValueArgumentInfo.copy(classId, name);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ClassId getEnumClassId() {
        return this.enumClassId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Name getEnumEntryName() {
        return this.enumEntryName;
    }

    public final EnumValueArgumentInfo copy(ClassId enumClassId, Name enumEntryName) {
        enumEntryName.getClass();
        return new EnumValueArgumentInfo(enumClassId, enumEntryName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EnumValueArgumentInfo)) {
            return false;
        }
        EnumValueArgumentInfo enumValueArgumentInfo = (EnumValueArgumentInfo) other;
        return Intrinsics.areEqual(this.enumClassId, enumValueArgumentInfo.enumClassId) && Intrinsics.areEqual(this.enumEntryName, enumValueArgumentInfo.enumEntryName);
    }

    public final ClassId getEnumClassId() {
        return this.enumClassId;
    }

    public final Name getEnumEntryName() {
        return this.enumEntryName;
    }

    public int hashCode() {
        ClassId classId = this.enumClassId;
        return ((classId == null ? 0 : classId.hashCode()) * 31) + this.enumEntryName.hashCode();
    }

    public String toString() {
        return "EnumValueArgumentInfo(enumClassId=" + this.enumClassId + ", enumEntryName=" + this.enumEntryName + ')';
    }
}

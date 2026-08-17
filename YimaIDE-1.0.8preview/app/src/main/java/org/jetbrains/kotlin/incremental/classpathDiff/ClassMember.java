package org.jetbrains.kotlin.incremental.classpathDiff;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ClassMember;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ProgramSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "memberName", "", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Ljava/lang/String;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getMemberName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class ClassMember extends ProgramSymbol {
    private final ClassId classId;
    private final String memberName;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClassMember(ClassId classId, String str) {
        super(null);
        classId.getClass();
        str.getClass();
        this.classId = classId;
        this.memberName = str;
    }

    public static /* synthetic */ ClassMember copy$default(ClassMember classMember, ClassId classId, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            classId = classMember.classId;
        }
        if ((i & 2) != 0) {
            str = classMember.memberName;
        }
        return classMember.copy(classId, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ClassId getClassId() {
        return this.classId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMemberName() {
        return this.memberName;
    }

    public final ClassMember copy(ClassId classId, String memberName) {
        classId.getClass();
        memberName.getClass();
        return new ClassMember(classId, memberName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClassMember)) {
            return false;
        }
        ClassMember classMember = (ClassMember) other;
        return Intrinsics.areEqual(this.classId, classMember.classId) && Intrinsics.areEqual(this.memberName, classMember.memberName);
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public final String getMemberName() {
        return this.memberName;
    }

    public int hashCode() {
        return (this.classId.hashCode() * 31) + this.memberName.hashCode();
    }

    public String toString() {
        return "ClassMember(classId=" + this.classId + ", memberName=" + this.memberName + ')';
    }
}

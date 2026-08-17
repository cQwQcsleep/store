package org.jetbrains.kotlin.incremental.classpathDiff;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ClassMembers;", "", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "memberNames", "", "", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Ljava/util/Set;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getMemberNames", "()Ljava/util/Set;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class ClassMembers {
    private final ClassId classId;
    private final Set<String> memberNames;

    public ClassMembers(ClassId classId, Set<String> set) {
        classId.getClass();
        set.getClass();
        this.classId = classId;
        this.memberNames = set;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ClassMembers copy$default(ClassMembers classMembers, ClassId classId, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            classId = classMembers.classId;
        }
        if ((i & 2) != 0) {
            set = classMembers.memberNames;
        }
        return classMembers.copy(classId, set);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ClassId getClassId() {
        return this.classId;
    }

    public final Set<String> component2() {
        return this.memberNames;
    }

    public final ClassMembers copy(ClassId classId, Set<String> memberNames) {
        classId.getClass();
        memberNames.getClass();
        return new ClassMembers(classId, memberNames);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClassMembers)) {
            return false;
        }
        ClassMembers classMembers = (ClassMembers) other;
        return Intrinsics.areEqual(this.classId, classMembers.classId) && Intrinsics.areEqual(this.memberNames, classMembers.memberNames);
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public final Set<String> getMemberNames() {
        return this.memberNames;
    }

    public int hashCode() {
        return (this.classId.hashCode() * 31) + this.memberNames.hashCode();
    }

    public String toString() {
        return "ClassMembers(classId=" + this.classId + ", memberNames=" + this.memberNames + ')';
    }
}

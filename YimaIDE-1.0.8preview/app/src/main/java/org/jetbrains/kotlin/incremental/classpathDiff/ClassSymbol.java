package org.jetbrains.kotlin.incremental.classpathDiff;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/ClassSymbol;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/ProgramSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class ClassSymbol extends ProgramSymbol {
    private final ClassId classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClassSymbol(ClassId classId) {
        super(null);
        classId.getClass();
        this.classId = classId;
    }

    public static /* synthetic */ ClassSymbol copy$default(ClassSymbol classSymbol, ClassId classId, int i, Object obj) {
        if ((i & 1) != 0) {
            classId = classSymbol.classId;
        }
        return classSymbol.copy(classId);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ClassId getClassId() {
        return this.classId;
    }

    public final ClassSymbol copy(ClassId classId) {
        classId.getClass();
        return new ClassSymbol(classId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ClassSymbol) && Intrinsics.areEqual(this.classId, ((ClassSymbol) other).classId);
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public int hashCode() {
        return this.classId.hashCode();
    }

    public String toString() {
        return "ClassSymbol(classId=" + this.classId + ')';
    }
}

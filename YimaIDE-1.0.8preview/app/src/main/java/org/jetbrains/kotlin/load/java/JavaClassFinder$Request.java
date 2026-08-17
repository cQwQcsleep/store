package org.jetbrains.kotlin.load.java;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/load/java/JavaClassFinder$Request;", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "previouslyFoundClassFileContent", Argument.Delimiters.none, "outerClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;[BLorg/jetbrains/kotlin/load/java/structure/JavaClass;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getPreviouslyFoundClassFileContent", "()[B", "getOuterClass", "()Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class JavaClassFinder$Request {
    private final ClassId classId;
    private final JavaClass outerClass;
    private final byte[] previouslyFoundClassFileContent;

    public /* synthetic */ JavaClassFinder$Request(ClassId classId, byte[] bArr, JavaClass javaClass, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(classId, (i & 2) != 0 ? null : bArr, (i & 4) != 0 ? null : javaClass);
    }

    public static /* synthetic */ JavaClassFinder$Request copy$default(JavaClassFinder$Request javaClassFinder$Request, ClassId classId, byte[] bArr, JavaClass javaClass, int i, Object obj) {
        if ((i & 1) != 0) {
            classId = javaClassFinder$Request.classId;
        }
        if ((i & 2) != 0) {
            bArr = javaClassFinder$Request.previouslyFoundClassFileContent;
        }
        if ((i & 4) != 0) {
            javaClass = javaClassFinder$Request.outerClass;
        }
        return javaClassFinder$Request.copy(classId, bArr, javaClass);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ClassId getClassId() {
        return this.classId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final byte[] getPreviouslyFoundClassFileContent() {
        return this.previouslyFoundClassFileContent;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final JavaClass getOuterClass() {
        return this.outerClass;
    }

    public final JavaClassFinder$Request copy(ClassId classId, byte[] previouslyFoundClassFileContent, JavaClass outerClass) {
        classId.getClass();
        return new JavaClassFinder$Request(classId, previouslyFoundClassFileContent, outerClass);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JavaClassFinder$Request)) {
            return false;
        }
        JavaClassFinder$Request javaClassFinder$Request = (JavaClassFinder$Request) other;
        return Intrinsics.areEqual(this.classId, javaClassFinder$Request.classId) && Intrinsics.areEqual(this.previouslyFoundClassFileContent, javaClassFinder$Request.previouslyFoundClassFileContent) && Intrinsics.areEqual(this.outerClass, javaClassFinder$Request.outerClass);
    }

    public final ClassId getClassId() {
        return this.classId;
    }

    public final JavaClass getOuterClass() {
        return this.outerClass;
    }

    public final byte[] getPreviouslyFoundClassFileContent() {
        return this.previouslyFoundClassFileContent;
    }

    public int hashCode() {
        int iHashCode = this.classId.hashCode() * 31;
        byte[] bArr = this.previouslyFoundClassFileContent;
        int iHashCode2 = (iHashCode + (bArr == null ? 0 : Arrays.hashCode(bArr))) * 31;
        JavaClass javaClass = this.outerClass;
        return iHashCode2 + (javaClass != null ? javaClass.hashCode() : 0);
    }

    public String toString() {
        return "Request(classId=" + this.classId + ", previouslyFoundClassFileContent=" + Arrays.toString(this.previouslyFoundClassFileContent) + ", outerClass=" + this.outerClass + ')';
    }

    public JavaClassFinder$Request(ClassId classId, byte[] bArr, JavaClass javaClass) {
        classId.getClass();
        this.classId = classId;
        this.previouslyFoundClassFileContent = bArr;
        this.outerClass = javaClass;
    }
}

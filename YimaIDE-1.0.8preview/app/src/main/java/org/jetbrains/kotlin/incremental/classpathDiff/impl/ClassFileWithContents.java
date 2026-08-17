package org.jetbrains.kotlin.incremental.classpathDiff.impl;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.incremental.classpathDiff.impl.ClassFileWithContents;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0002\u001a\u00020\u0003¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassFileWithContents;", "", "classFile", "Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassFile;", "contents", "", "<init>", "(Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassFile;[B)V", "getClassFile$annotations", "()V", "getClassFile", "()Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassFile;", "getContents", "()[B", "classInfo", "Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/BasicClassInfo;", "getClassInfo", "()Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/BasicClassInfo;", "classInfo$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ClassFileWithContents {
    private final ClassFile classFile;

    /* JADX INFO: renamed from: classInfo$delegate, reason: from kotlin metadata */
    private final Lazy classInfo;
    private final byte[] contents;

    public ClassFileWithContents(ClassFile classFile, byte[] bArr) {
        classFile.getClass();
        bArr.getClass();
        this.classFile = classFile;
        this.contents = bArr;
        this.classInfo = LazyKt.lazy(new Function0() { // from class: xu1
            public final Object invoke() {
                return ClassFileWithContents.a(this.b);
            }
        });
    }

    public static BasicClassInfo a(ClassFileWithContents classFileWithContents) {
        return BasicClassInfo.INSTANCE.compute(classFileWithContents.contents);
    }

    public static /* synthetic */ void getClassFile$annotations() {
    }

    public final ClassFile getClassFile() {
        return this.classFile;
    }

    public final BasicClassInfo getClassInfo() {
        return (BasicClassInfo) this.classInfo.getValue();
    }

    public final byte[] getContents() {
        return this.contents;
    }
}

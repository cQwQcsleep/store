package org.jetbrains.kotlin.incremental.classpathDiff.impl;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\r\u001a\u00020\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassFileWithContentsProvider;", "", "classFile", "Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassFile;", "contentsProvider", "Lkotlin/Function0;", "", "<init>", "(Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassFile;Lkotlin/jvm/functions/Function0;)V", "getClassFile", "()Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassFile;", "getContentsProvider", "()Lkotlin/jvm/functions/Function0;", "loadContents", "Lorg/jetbrains/kotlin/incremental/classpathDiff/impl/ClassFileWithContents;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ClassFileWithContentsProvider {
    private final ClassFile classFile;
    private final Function0<byte[]> contentsProvider;

    public ClassFileWithContentsProvider(ClassFile classFile, Function0<byte[]> function0) {
        classFile.getClass();
        function0.getClass();
        this.classFile = classFile;
        this.contentsProvider = function0;
    }

    public final ClassFile getClassFile() {
        return this.classFile;
    }

    public final Function0<byte[]> getContentsProvider() {
        return this.contentsProvider;
    }

    public final ClassFileWithContents loadContents() {
        return new ClassFileWithContents(this.classFile, (byte[]) this.contentsProvider.invoke());
    }
}

package org.jetbrains.kotlin.javac.components;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.SourceFile;
import org.jetbrains.kotlin.load.java.sources.JavaSourceElement;
import org.jetbrains.kotlin.load.java.structure.JavaElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/javac/components/JavacBasedSourceElement;", "Lorg/jetbrains/kotlin/load/java/sources/JavaSourceElement;", "javaElement", "Lorg/jetbrains/kotlin/load/java/structure/JavaElement;", "<init>", "(Lorg/jetbrains/kotlin/load/java/structure/JavaElement;)V", "getJavaElement", "()Lorg/jetbrains/kotlin/load/java/structure/JavaElement;", "getContainingFile", "Lorg/jetbrains/kotlin/descriptors/SourceFile;", "org.jetbrains.kotlin:javac-wrapper"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JavacBasedSourceElement implements JavaSourceElement {
    private final JavaElement javaElement;

    public JavacBasedSourceElement(JavaElement javaElement) {
        javaElement.getClass();
        this.javaElement = javaElement;
    }

    public SourceFile getContainingFile() {
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        sourceFile.getClass();
        return sourceFile;
    }

    @Override // org.jetbrains.kotlin.load.java.sources.JavaSourceElement
    public JavaElement getJavaElement() {
        return this.javaElement;
    }
}

package org.jetbrains.kotlin.descriptors.runtime.components;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.SourceFile;
import org.jetbrains.kotlin.descriptors.runtime.structure.ReflectJavaElement;
import org.jetbrains.kotlin.load.java.sources.JavaSourceElement;
import org.jetbrains.kotlin.load.java.sources.JavaSourceElementFactory;
import org.jetbrains.kotlin.load.java.structure.JavaElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/components/RuntimeSourceElementFactory;", "Lorg/jetbrains/kotlin/load/java/sources/JavaSourceElementFactory;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/load/java/sources/JavaSourceElement;", "javaElement", "Lorg/jetbrains/kotlin/load/java/structure/JavaElement;", "RuntimeSourceElement", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RuntimeSourceElementFactory implements JavaSourceElementFactory {
    public static final RuntimeSourceElementFactory INSTANCE = new RuntimeSourceElementFactory();

    private RuntimeSourceElementFactory() {
    }

    public JavaSourceElement source(JavaElement javaElement) {
        javaElement.getClass();
        return new RuntimeSourceElement((ReflectJavaElement) javaElement);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/runtime/components/RuntimeSourceElementFactory$RuntimeSourceElement;", "Lorg/jetbrains/kotlin/load/java/sources/JavaSourceElement;", "javaElement", "Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaElement;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaElement;)V", "getJavaElement", "()Lorg/jetbrains/kotlin/descriptors/runtime/structure/ReflectJavaElement;", "toString", Argument.Delimiters.none, "getContainingFile", "Lorg/jetbrains/kotlin/descriptors/SourceFile;", "org.jetbrains.kotlin:descriptors.runtime"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class RuntimeSourceElement implements JavaSourceElement {
        private final ReflectJavaElement javaElement;

        public RuntimeSourceElement(ReflectJavaElement reflectJavaElement) {
            reflectJavaElement.getClass();
            this.javaElement = reflectJavaElement;
        }

        public SourceFile getContainingFile() {
            SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
            sourceFile.getClass();
            return sourceFile;
        }

        public String toString() {
            return RuntimeSourceElement.class.getName() + ": " + m172getJavaElement();
        }

        /* JADX INFO: renamed from: getJavaElement, reason: from getter and merged with bridge method [inline-methods] */
        public ReflectJavaElement m172getJavaElement() {
            return this.javaElement;
        }
    }
}

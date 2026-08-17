package org.jetbrains.kotlin.load.java.structure.impl.source;

import com.intellij.psi.PsiType;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004R\u0016\u0010\u0004\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementTypeSourceWithFixedType;", "TYPE", "Lcom/intellij/psi/PsiType;", "Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementTypeSource;", "type", "factory", "Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementSourceFactory;", "<init>", "(Lcom/intellij/psi/PsiType;Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementSourceFactory;)V", "getType", "()Lcom/intellij/psi/PsiType;", "Lcom/intellij/psi/PsiType;", "getFactory", "()Lorg/jetbrains/kotlin/load/java/structure/impl/source/JavaElementSourceFactory;", "toString", "", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JavaElementTypeSourceWithFixedType<TYPE extends PsiType> extends JavaElementTypeSource<TYPE> {
    private final JavaElementSourceFactory factory;
    private final TYPE type;

    public JavaElementTypeSourceWithFixedType(TYPE type, JavaElementSourceFactory javaElementSourceFactory) {
        type.getClass();
        javaElementSourceFactory.getClass();
        this.type = type;
        this.factory = javaElementSourceFactory;
    }

    @Override // org.jetbrains.kotlin.load.java.structure.impl.source.JavaElementTypeSource
    public JavaElementSourceFactory getFactory() {
        return this.factory;
    }

    @Override // org.jetbrains.kotlin.load.java.structure.impl.source.JavaElementTypeSource
    public TYPE getType() {
        return this.type;
    }

    public String toString() {
        String string = getType().toString();
        string.getClass();
        return string;
    }
}

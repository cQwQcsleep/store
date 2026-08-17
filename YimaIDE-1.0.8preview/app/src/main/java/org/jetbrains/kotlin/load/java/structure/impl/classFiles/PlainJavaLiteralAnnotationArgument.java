package org.jetbrains.kotlin.load.java.structure.impl.classFiles;

import kotlin.Metadata;
import org.jetbrains.kotlin.load.java.structure.JavaLiteralAnnotationArgument;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/load/java/structure/impl/classFiles/PlainJavaLiteralAnnotationArgument;", "Lorg/jetbrains/kotlin/load/java/structure/impl/classFiles/PlainJavaAnnotationArgument;", "Lorg/jetbrains/kotlin/load/java/structure/JavaLiteralAnnotationArgument;", "name", "", "value", "", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PlainJavaLiteralAnnotationArgument extends PlainJavaAnnotationArgument implements JavaLiteralAnnotationArgument {
    private final Object value;

    public PlainJavaLiteralAnnotationArgument(String str, Object obj) {
        super(str, null);
        this.value = obj;
    }

    @Override // org.jetbrains.kotlin.load.java.structure.JavaLiteralAnnotationArgument
    public Object getValue() {
        return this.value;
    }
}

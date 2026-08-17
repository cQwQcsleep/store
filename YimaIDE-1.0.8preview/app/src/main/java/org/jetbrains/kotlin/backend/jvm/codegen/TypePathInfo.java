package org.jetbrains.kotlin.backend.jvm.codegen;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.org.objectweb.asm.TypePath;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/TypePathInfo;", "T", "", "path", "Lorg/jetbrains/org/objectweb/asm/TypePath;", "annotations", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/org/objectweb/asm/TypePath;Ljava/util/List;)V", "getPath", "()Lorg/jetbrains/org/objectweb/asm/TypePath;", "getAnnotations", "()Ljava/util/List;", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class TypePathInfo<T> {
    private final List<T> annotations;
    private final TypePath path;

    /* JADX WARN: Multi-variable type inference failed */
    public TypePathInfo(TypePath typePath, List<? extends T> list) {
        list.getClass();
        this.path = typePath;
        this.annotations = list;
    }

    public final List<T> getAnnotations() {
        return this.annotations;
    }

    public final TypePath getPath() {
        return this.path;
    }
}

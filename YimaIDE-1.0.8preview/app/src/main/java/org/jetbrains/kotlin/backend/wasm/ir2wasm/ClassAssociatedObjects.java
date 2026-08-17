package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/ClassAssociatedObjects;", "", "klass", "", "objects", "", "Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/AssociatedObject;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(JLjava/util/List;)V", "getKlass", "()J", "getObjects", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class ClassAssociatedObjects {
    private final long klass;
    private final List<AssociatedObject> objects;

    public ClassAssociatedObjects(long j, List<AssociatedObject> list) {
        list.getClass();
        this.klass = j;
        this.objects = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ClassAssociatedObjects copy$default(ClassAssociatedObjects classAssociatedObjects, long j, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            j = classAssociatedObjects.klass;
        }
        if ((i & 2) != 0) {
            list = classAssociatedObjects.objects;
        }
        return classAssociatedObjects.copy(j, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getKlass() {
        return this.klass;
    }

    public final List<AssociatedObject> component2() {
        return this.objects;
    }

    public final ClassAssociatedObjects copy(long klass, List<AssociatedObject> objects) {
        objects.getClass();
        return new ClassAssociatedObjects(klass, objects);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClassAssociatedObjects)) {
            return false;
        }
        ClassAssociatedObjects classAssociatedObjects = (ClassAssociatedObjects) other;
        return this.klass == classAssociatedObjects.klass && Intrinsics.areEqual(this.objects, classAssociatedObjects.objects);
    }

    public final long getKlass() {
        return this.klass;
    }

    public final List<AssociatedObject> getObjects() {
        return this.objects;
    }

    public int hashCode() {
        return (Long.hashCode(this.klass) * 31) + this.objects.hashCode();
    }

    public String toString() {
        return "ClassAssociatedObjects(klass=" + this.klass + ", objects=" + this.objects + Util.C_PARAM_END;
    }
}

package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.util.IdSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/AssociatedObject;", "", "obj", "", "getterFunc", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "isExternal", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(JLorg/jetbrains/kotlin/ir/util/IdSignature;Z)V", "getObj", "()J", "getGetterFunc", "()Lorg/jetbrains/kotlin/ir/util/IdSignature;", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class AssociatedObject {
    private final IdSignature getterFunc;
    private final boolean isExternal;
    private final long obj;

    public AssociatedObject(long j, IdSignature idSignature, boolean z) {
        idSignature.getClass();
        this.obj = j;
        this.getterFunc = idSignature;
        this.isExternal = z;
    }

    public static /* synthetic */ AssociatedObject copy$default(AssociatedObject associatedObject, long j, IdSignature idSignature, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            j = associatedObject.obj;
        }
        if ((i & 2) != 0) {
            idSignature = associatedObject.getterFunc;
        }
        if ((i & 4) != 0) {
            z = associatedObject.isExternal;
        }
        return associatedObject.copy(j, idSignature, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getObj() {
        return this.obj;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final IdSignature getGetterFunc() {
        return this.getterFunc;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsExternal() {
        return this.isExternal;
    }

    public final AssociatedObject copy(long obj, IdSignature getterFunc, boolean isExternal) {
        getterFunc.getClass();
        return new AssociatedObject(obj, getterFunc, isExternal);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssociatedObject)) {
            return false;
        }
        AssociatedObject associatedObject = (AssociatedObject) other;
        return this.obj == associatedObject.obj && Intrinsics.areEqual(this.getterFunc, associatedObject.getterFunc) && this.isExternal == associatedObject.isExternal;
    }

    public final IdSignature getGetterFunc() {
        return this.getterFunc;
    }

    public final long getObj() {
        return this.obj;
    }

    public int hashCode() {
        return (((Long.hashCode(this.obj) * 31) + this.getterFunc.hashCode()) * 31) + Boolean.hashCode(this.isExternal);
    }

    public final boolean isExternal() {
        return this.isExternal;
    }

    public String toString() {
        return "AssociatedObject(obj=" + this.obj + ", getterFunc=" + this.getterFunc + ", isExternal=" + this.isExternal + Util.C_PARAM_END;
    }
}

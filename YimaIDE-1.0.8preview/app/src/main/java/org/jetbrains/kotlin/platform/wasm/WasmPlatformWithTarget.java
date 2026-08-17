package org.jetbrains.kotlin.platform.wasm;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u000f\u001a\u00020\tH\u0096\u0080\u0004J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/platform/wasm/WasmPlatformWithTarget;", "Lorg/jetbrains/kotlin/platform/wasm/WasmPlatform;", "target", "Lorg/jetbrains/kotlin/platform/wasm/WasmTarget;", "<init>", "(Lorg/jetbrains/kotlin/platform/wasm/WasmTarget;)V", "getTarget", "()Lorg/jetbrains/kotlin/platform/wasm/WasmTarget;", "targetName", "", "getTargetName", "()Ljava/lang/String;", "isWeb", "", "()Z", "toString", "component1", "copy", "equals", "other", "", "hashCode", "", "org.jetbrains.kotlin:wasm.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class WasmPlatformWithTarget extends WasmPlatform {
    private final WasmTarget target;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WasmPlatformWithTarget(WasmTarget wasmTarget) {
        super(wasmTarget.getAlias());
        wasmTarget.getClass();
        this.target = wasmTarget;
    }

    public static /* synthetic */ WasmPlatformWithTarget copy$default(WasmPlatformWithTarget wasmPlatformWithTarget, WasmTarget wasmTarget, int i, Object obj) {
        if ((i & 1) != 0) {
            wasmTarget = wasmPlatformWithTarget.target;
        }
        return wasmPlatformWithTarget.copy(wasmTarget);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final WasmTarget getTarget() {
        return this.target;
    }

    public final WasmPlatformWithTarget copy(WasmTarget target) {
        target.getClass();
        return new WasmPlatformWithTarget(target);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof WasmPlatformWithTarget) && this.target == ((WasmPlatformWithTarget) other).target;
    }

    public final WasmTarget getTarget() {
        return this.target;
    }

    @Override // org.jetbrains.kotlin.platform.SimplePlatform
    public String getTargetName() {
        return this.target.name();
    }

    public int hashCode() {
        return this.target.hashCode();
    }

    @Override // org.jetbrains.kotlin.platform.WasmPlatform, org.jetbrains.kotlin.platform.PotentiallyWebPlatform
    public boolean isWeb() {
        return this.target == WasmTarget.JS;
    }

    @Override // org.jetbrains.kotlin.platform.SimplePlatform
    public String toString() {
        return super.toString();
    }
}

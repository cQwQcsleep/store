package org.jetbrains.kotlin.platform.wasm;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/platform/wasm/WasmPlatform;", "Lorg/jetbrains/kotlin/platform/WasmPlatform;", "platformName", "", "<init>", "(Ljava/lang/String;)V", "oldFashionedDescription", "getOldFashionedDescription", "()Ljava/lang/String;", "org.jetbrains.kotlin:wasm.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class WasmPlatform extends org.jetbrains.kotlin.platform.WasmPlatform {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WasmPlatform(String str) {
        super(str);
        str.getClass();
    }

    @Override // org.jetbrains.kotlin.platform.WasmPlatform, org.jetbrains.kotlin.platform.SimplePlatform
    public String getOldFashionedDescription() {
        return "Wasm";
    }
}

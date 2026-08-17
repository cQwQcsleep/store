package org.jetbrains.kotlin.platform;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/platform/WasmPlatform;", "Lorg/jetbrains/kotlin/platform/SimplePlatform;", "Lorg/jetbrains/kotlin/platform/PotentiallyWebPlatform;", "platformName", "", "<init>", "(Ljava/lang/String;)V", "oldFashionedDescription", "getOldFashionedDescription", "()Ljava/lang/String;", "isWeb", "", "()Z", "org.jetbrains.kotlin:language.targets"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class WasmPlatform extends SimplePlatform implements PotentiallyWebPlatform {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WasmPlatform(String str) {
        super(str);
        str.getClass();
    }

    @Override // org.jetbrains.kotlin.platform.SimplePlatform
    public String getOldFashionedDescription() {
        return "Wasm ";
    }

    @Override // org.jetbrains.kotlin.platform.PotentiallyWebPlatform
    public boolean isWeb() {
        return true;
    }
}

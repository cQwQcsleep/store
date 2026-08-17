package org.jetbrains.kotlin.ir.backend.js.wasm;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\n\u001a\u00020\u0003H&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/wasm/WasmKlibExport;", "", "containingFile", "", "<init>", "(Ljava/lang/String;)V", "getContainingFile", "()Ljava/lang/String;", "fqName", "getFqName", "render", "org.jetbrains.kotlin:ir.serialization.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class WasmKlibExport {
    private final String containingFile;

    public WasmKlibExport(String str) {
        str.getClass();
        this.containingFile = str;
    }

    public final String getContainingFile() {
        return this.containingFile;
    }

    public abstract String getFqName();

    public abstract String render();
}

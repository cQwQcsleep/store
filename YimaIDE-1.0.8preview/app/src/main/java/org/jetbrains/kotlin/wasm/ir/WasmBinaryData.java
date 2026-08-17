package org.jetbrains.kotlin.wasm.ir;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmBinaryData;", "", "data", "", "size", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "([BI)V", "Companion", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmBinaryData {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final byte[] data;
    private final int size;

    public WasmBinaryData(byte[] bArr, int i) {
        bArr.getClass();
        this.data = bArr;
        this.size = i;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\n\u0010\t\u001a\u00020\n*\u00020\u0006¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmBinaryData$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "writeTo", "", "Lorg/jetbrains/kotlin/wasm/ir/WasmBinaryData;", "file", "Ljava/io/File;", "toByteArray", "", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final byte[] toByteArray(WasmBinaryData wasmBinaryData) {
            wasmBinaryData.getClass();
            return Arrays.copyOf(wasmBinaryData.data, wasmBinaryData.size);
        }

        public final void writeTo(WasmBinaryData wasmBinaryData, File file) {
            wasmBinaryData.getClass();
            file.getClass();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(wasmBinaryData.data, 0, wasmBinaryData.size);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileOutputStream, th);
                    throw th2;
                }
            }
        }

        private Companion() {
        }
    }
}

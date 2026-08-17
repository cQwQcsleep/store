package org.jetbrains.kotlin.wasm.ir;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/WasmStructDeclaration;", "Lorg/jetbrains/kotlin/wasm/ir/WasmTypeDeclaration;", "name", "", "fields", "", "Lorg/jetbrains/kotlin/wasm/ir/WasmStructFieldDeclaration;", "superType", "Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type;", "isFinal", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Ljava/util/List;Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type;Z)V", "getFields", "()Ljava/util/List;", "getSuperType", "()Lorg/jetbrains/kotlin/wasm/ir/WasmHeapType$Type;", "()Z", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WasmStructDeclaration extends WasmTypeDeclaration {
    private final List<WasmStructFieldDeclaration> fields;
    private final boolean isFinal;
    private final WasmHeapType.Type superType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WasmStructDeclaration(String str, List<WasmStructFieldDeclaration> list, WasmHeapType.Type type, boolean z) {
        super(str, (DefaultConstructorMarker) null);
        str.getClass();
        list.getClass();
        this.fields = list;
        this.superType = type;
        this.isFinal = z;
    }

    public final List<WasmStructFieldDeclaration> getFields() {
        return this.fields;
    }

    public final WasmHeapType.Type getSuperType() {
        return this.superType;
    }

    /* JADX INFO: renamed from: isFinal, reason: from getter */
    public final boolean getIsFinal() {
        return this.isFinal;
    }
}

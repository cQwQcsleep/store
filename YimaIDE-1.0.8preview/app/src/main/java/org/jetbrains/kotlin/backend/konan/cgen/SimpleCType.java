package org.jetbrains.kotlin.backend.konan.cgen;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/cgen/SimpleCType;", "Lorg/jetbrains/kotlin/backend/konan/cgen/CType;", "type", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;)V", "render", "name", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class SimpleCType implements CType {
    private final String type;

    public SimpleCType(String str) {
        str.getClass();
        this.type = str;
    }

    @Override // org.jetbrains.kotlin.backend.konan.cgen.CType
    public String render(String name) {
        name.getClass();
        int length = name.length();
        String str = this.type;
        if (length == 0) {
            return str;
        }
        return str + ' ' + name;
    }
}

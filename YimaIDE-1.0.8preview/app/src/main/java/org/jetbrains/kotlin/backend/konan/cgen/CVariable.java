package org.jetbrains.kotlin.backend.konan.cgen;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\u0005H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/cgen/CVariable;", "", "type", "Lorg/jetbrains/kotlin/backend/konan/cgen/CType;", "name", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/backend/konan/cgen/CType;Ljava/lang/String;)V", "getType", "()Lorg/jetbrains/kotlin/backend/konan/cgen/CType;", "getName", "()Ljava/lang/String;", "toString", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CVariable {
    private final String name;
    private final CType type;

    public CVariable(CType cType, String str) {
        cType.getClass();
        str.getClass();
        this.type = cType;
        this.name = str;
    }

    public final String getName() {
        return this.name;
    }

    public final CType getType() {
        return this.type;
    }

    public String toString() {
        return this.type.render(this.name);
    }
}

package org.jetbrains.kotlin.backend.konan.cgen;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/cgen/PointerCType;", "Lorg/jetbrains/kotlin/backend/konan/cgen/CType;", "pointee", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/backend/konan/cgen/CType;)V", "render", "", "name", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class PointerCType implements CType {
    private final CType pointee;

    public PointerCType(CType cType) {
        cType.getClass();
        this.pointee = cType;
    }

    @Override // org.jetbrains.kotlin.backend.konan.cgen.CType
    public String render(String name) {
        name.getClass();
        return this.pointee.render("*" + name);
    }
}

package org.jetbrains.kotlin.backend.common.overrides;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/overrides/DefaultFakeOverrideClassFilter;", "Lorg/jetbrains/kotlin/backend/common/overrides/FakeOverrideClassFilter;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "needToConstructFakeOverrides", "", "clazz", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultFakeOverrideClassFilter implements FakeOverrideClassFilter {
    public static final DefaultFakeOverrideClassFilter INSTANCE = new DefaultFakeOverrideClassFilter();

    private DefaultFakeOverrideClassFilter() {
    }

    @Override // org.jetbrains.kotlin.backend.common.overrides.FakeOverrideClassFilter
    public boolean needToConstructFakeOverrides(IrClass clazz) {
        clazz.getClass();
        return true;
    }
}

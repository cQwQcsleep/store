package org.jetbrains.kotlin.codegen.state;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.name.NameUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/state/KotlinTypeMapper$InternalNameMapper;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "mangleInternalName", "", "name", "moduleName", "org.jetbrains.kotlin:backend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KotlinTypeMapper$InternalNameMapper {
    public static final KotlinTypeMapper$InternalNameMapper INSTANCE = new KotlinTypeMapper$InternalNameMapper();

    private KotlinTypeMapper$InternalNameMapper() {
    }

    public final String mangleInternalName(String name, String moduleName) {
        name.getClass();
        moduleName.getClass();
        return name + '$' + NameUtils.sanitizeAsJavaIdentifier(moduleName);
    }
}

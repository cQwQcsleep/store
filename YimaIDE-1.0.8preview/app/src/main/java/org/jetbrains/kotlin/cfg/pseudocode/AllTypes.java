package org.jetbrains.kotlin.cfg.pseudocode;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0096\u0082\u0004¢\u0006\u0002\u0010\bJ\n\u0010\t\u001a\u00020\nH\u0096\u0080\u0004¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/cfg/pseudocode/AllTypes;", "Lorg/jetbrains/kotlin/cfg/pseudocode/TypePredicate;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "invoke", "", "typeToCheck", "Lorg/jetbrains/kotlin/types/KotlinType;", "(Lorg/jetbrains/kotlin/types/KotlinType;)Ljava/lang/Boolean;", "toString", "", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class AllTypes implements TypePredicate {
    public static final AllTypes INSTANCE = new AllTypes();

    private AllTypes() {
    }

    public String toString() {
        return "*";
    }

    @Override // org.jetbrains.kotlin.cfg.pseudocode.TypePredicate
    public Boolean invoke(KotlinType typeToCheck) {
        typeToCheck.getClass();
        return Boolean.TRUE;
    }
}

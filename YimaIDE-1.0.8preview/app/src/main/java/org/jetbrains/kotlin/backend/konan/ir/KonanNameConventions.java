package org.jetbrains.kotlin.backend.konan.ir;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/ir/KonanNameConventions;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "setWithoutBoundCheck", "Lorg/jetbrains/kotlin/name/Name;", "getSetWithoutBoundCheck", "()Lorg/jetbrains/kotlin/name/Name;", "getWithoutBoundCheck", "getGetWithoutBoundCheck", "org.jetbrains.kotlin:ir.backend.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KonanNameConventions {
    public static final KonanNameConventions INSTANCE = new KonanNameConventions();
    private static final Name getWithoutBoundCheck;
    private static final Name setWithoutBoundCheck;

    static {
        Name nameSpecial = Name.special("<setWithoutBoundCheck>");
        nameSpecial.getClass();
        setWithoutBoundCheck = nameSpecial;
        Name nameSpecial2 = Name.special("<getWithoutBoundCheck>");
        nameSpecial2.getClass();
        getWithoutBoundCheck = nameSpecial2;
    }

    private KonanNameConventions() {
    }

    public final Name getGetWithoutBoundCheck() {
        return getWithoutBoundCheck;
    }

    public final Name getSetWithoutBoundCheck() {
        return setWithoutBoundCheck;
    }
}

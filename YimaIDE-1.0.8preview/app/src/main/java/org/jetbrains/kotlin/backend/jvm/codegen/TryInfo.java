package org.jetbrains.kotlin.backend.jvm.codegen;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.org.objectweb.asm.Label;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R#\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/TryInfo;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/ExpressionInfo;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "gaps", "", "Lkotlin/Pair;", "Lorg/jetbrains/org/objectweb/asm/Label;", "getGaps", "()Ljava/util/List;", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class TryInfo extends ExpressionInfo {
    private final List<Pair<Label, Label>> gaps;

    public TryInfo() {
        super(null);
        this.gaps = new ArrayList();
    }

    public final List<Pair<Label, Label>> getGaps() {
        return this.gaps;
    }
}

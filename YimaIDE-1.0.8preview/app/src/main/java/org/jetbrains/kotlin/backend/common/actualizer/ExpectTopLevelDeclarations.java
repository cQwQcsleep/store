package org.jetbrains.kotlin.backend.common.actualizer;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B5\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0003¢\u0006\u0004\b\n\u0010\u000bR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR#\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/actualizer/ExpectTopLevelDeclarations;", "", "classes", "", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "callables", "Lorg/jetbrains/kotlin/name/CallableId;", "", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/Map;Ljava/util/Map;)V", "getClasses", "()Ljava/util/Map;", "getCallables", "org.jetbrains.kotlin:ir.actualization"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class ExpectTopLevelDeclarations {
    private final Map<CallableId, List<IrSymbol>> callables;
    private final Map<ClassId, IrClassSymbol> classes;

    /* JADX WARN: Multi-variable type inference failed */
    public ExpectTopLevelDeclarations(Map<ClassId, ? extends IrClassSymbol> map, Map<CallableId, ? extends List<? extends IrSymbol>> map2) {
        map.getClass();
        map2.getClass();
        this.classes = map;
        this.callables = map2;
    }

    public final Map<CallableId, List<IrSymbol>> getCallables() {
        return this.callables;
    }

    public final Map<ClassId, IrClassSymbol> getClasses() {
        return this.classes;
    }
}

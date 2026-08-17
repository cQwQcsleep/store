package org.jetbrains.kotlin.backend.common.lower;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0014R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/VariableRemapper;", "Lorg/jetbrains/kotlin/backend/common/lower/AbstractVariableRemapper;", "mapping", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/Map;)V", "getMapping", "()Ljava/util/Map;", "remapVariable", "value", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class VariableRemapper extends AbstractVariableRemapper {
    private final Map<IrValueParameter, IrValueDeclaration> mapping;

    public VariableRemapper(Map<IrValueParameter, ? extends IrValueDeclaration> map) {
        map.getClass();
        this.mapping = map;
    }

    public final Map<IrValueParameter, IrValueDeclaration> getMapping() {
        return this.mapping;
    }

    @Override // org.jetbrains.kotlin.backend.common.lower.AbstractVariableRemapper
    public IrValueDeclaration remapVariable(IrValueDeclaration value) {
        value.getClass();
        return this.mapping.get(value);
    }
}

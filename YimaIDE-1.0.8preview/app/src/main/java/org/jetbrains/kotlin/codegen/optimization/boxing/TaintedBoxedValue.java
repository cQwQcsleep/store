package org.jetbrains.kotlin.codegen.optimization.boxing;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\n\u001a\u00020\u0001H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/TaintedBoxedValue;", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxedBasicValue;", "boxedBasicValue", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/CleanBoxedValue;", "<init>", "(Lorg/jetbrains/kotlin/codegen/optimization/boxing/CleanBoxedValue;)V", "descriptor", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxedValueDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxedValueDescriptor;", "taint", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TaintedBoxedValue extends BoxedBasicValue {
    private final CleanBoxedValue boxedBasicValue;

    /* JADX WARN: Illegal instructions before constructor call */
    public TaintedBoxedValue(CleanBoxedValue cleanBoxedValue) {
        cleanBoxedValue.getClass();
        Type type = cleanBoxedValue.getType();
        type.getClass();
        super(type);
        this.boxedBasicValue = cleanBoxedValue;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxedBasicValue
    public BoxedValueDescriptor getDescriptor() {
        return this.boxedBasicValue.getDescriptor();
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxedBasicValue
    public BoxedBasicValue taint() {
        return this;
    }
}

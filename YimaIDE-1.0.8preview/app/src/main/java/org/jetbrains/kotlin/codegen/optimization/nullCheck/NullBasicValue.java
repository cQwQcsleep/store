package org.jetbrains.kotlin.codegen.optimization.nullCheck;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/NullBasicValue;", "Lorg/jetbrains/kotlin/codegen/optimization/common/StrictBasicValue;", "<init>", "()V", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NullBasicValue extends StrictBasicValue {
    public static final NullBasicValue INSTANCE = new NullBasicValue();

    private NullBasicValue() {
        super(AsmTypes.OBJECT_TYPE);
    }
}

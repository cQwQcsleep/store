package org.jetbrains.kotlin.backend.konan.lower;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class BackendChecker$visitCall$integerTypePredicates$1 extends FunctionReferenceImpl implements Function1<IrType, Boolean> {
    public static final BackendChecker$visitCall$integerTypePredicates$1 INSTANCE = new BackendChecker$visitCall$integerTypePredicates$1();

    public BackendChecker$visitCall$integerTypePredicates$1() {
        super(1, IrTypePredicatesKt.class, "isByte", "isByte(Lorg/jetbrains/kotlin/ir/types/IrType;)Z", 1);
    }

    public final Boolean invoke(IrType irType) {
        irType.getClass();
        return Boolean.valueOf(IrTypePredicatesKt.isByte(irType));
    }
}

package org.jetbrains.kotlin.resolve.calls.inference;

import kotlin.Metadata;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/inference/ConstraintSystemTransaction;", "", "<init>", "()V", "closeTransaction", "", "rollbackTransaction", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public abstract class ConstraintSystemTransaction {
    public abstract void closeTransaction();

    public abstract void rollbackTransaction();
}

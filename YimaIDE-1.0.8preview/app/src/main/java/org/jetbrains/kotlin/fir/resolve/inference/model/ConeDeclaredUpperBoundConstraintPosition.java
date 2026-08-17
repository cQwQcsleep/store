package org.jetbrains.kotlin.fir.resolve.inference.model;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.calls.inference.model.DeclaredUpperBoundConstraintPosition;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\n\u0010\u0005\u001a\u00020\u0006H\u0096\u0080\u0004¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeDeclaredUpperBoundConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/DeclaredUpperBoundConstraintPosition;", Argument.Delimiters.none, "<init>", "()V", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeDeclaredUpperBoundConstraintPosition extends DeclaredUpperBoundConstraintPosition {
    public ConeDeclaredUpperBoundConstraintPosition() {
        super((Object) null);
    }

    public String toString() {
        return "DeclaredUpperBound";
    }
}

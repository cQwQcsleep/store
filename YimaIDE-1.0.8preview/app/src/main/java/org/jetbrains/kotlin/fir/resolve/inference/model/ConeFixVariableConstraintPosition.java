package org.jetbrains.kotlin.fir.resolve.inference.model;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.resolve.calls.inference.model.FixVariableConstraintPosition;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\u0007\u001a\u00020\bH\u0096\u0080\u0004¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeFixVariableConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/FixVariableConstraintPosition;", Argument.Delimiters.none, "variable", "Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", "<init>", "(Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;)V", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeFixVariableConstraintPosition extends FixVariableConstraintPosition {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeFixVariableConstraintPosition(TypeVariableMarker typeVariableMarker) {
        super(typeVariableMarker, (Object) null);
        typeVariableMarker.getClass();
    }

    public String toString() {
        return "Fix variable " + ((ConeTypeVariable) getVariable()).getTypeConstructor().getName();
    }
}

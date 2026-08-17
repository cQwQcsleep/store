package org.jetbrains.kotlin.fir.resolve.inference.model;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.resolve.calls.inference.model.SemiFixVariableConstraintPosition;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0006\u001a\u00020\u0007H\u0096\u0080\u0004¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/model/ConeSemiFixVariableConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/SemiFixVariableConstraintPosition;", "variable", "Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", "<init>", "(Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;)V", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeSemiFixVariableConstraintPosition extends SemiFixVariableConstraintPosition {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeSemiFixVariableConstraintPosition(TypeVariableMarker typeVariableMarker) {
        super(typeVariableMarker);
        typeVariableMarker.getClass();
    }

    public String toString() {
        return "Fix variable " + ((ConeTypeVariable) getVariable()).getTypeConstructor().getName();
    }
}

package org.jetbrains.kotlin.resolve.calls.inference.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0010\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintPosition;", "", "<init>", "()V", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ArgumentConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/BuilderInferencePosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/BuilderInferenceSubstitutionConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/CallableReferenceConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/DeclaredUpperBoundConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/DelegatedPropertyConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ExpectedTypeConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ExplicitTypeParameterConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/FixVariableConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/IncorporationConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/InjectedAnotherStubTypeConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/KnownTypeParameterConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ProvideDelegateFixationPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ReceiverConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/SemiFixVariableConstraintPosition;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/SimpleConstraintSystemConstraintPosition;", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public abstract class ConstraintPosition {
    public /* synthetic */ ConstraintPosition(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ConstraintPosition() {
    }
}

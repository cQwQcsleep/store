package org.jetbrains.kotlin.resolve.calls.inference.components;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0014B+\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0013\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0006\b\u0011\u0012\u0002\b\u0012¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;", "", "allPostponedAtomsShouldBeAnalyzed", "", "allLambdasShouldBeAnalyzed", "shouldForkPointConstraintsBeResolved", "fixNotInferredTypeVariablesToErrorType", "<init>", "(Ljava/lang/String;IZZZZ)V", "getAllPostponedAtomsShouldBeAnalyzed", "()Z", "getAllLambdasShouldBeAnalyzed", "getShouldForkPointConstraintsBeResolved", "getFixNotInferredTypeVariablesToErrorType", "FULL", "PCLA_POSTPONED_CALL", "PARTIAL", "UNTIL_FIRST_LAMBDA", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode$ExclusiveForOverloadResolutionByLambdaReturnType;", "isUntilFirstLambda", "ExclusiveForOverloadResolutionByLambdaReturnType", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum ConstraintSystemCompletionMode {
    FULL(true, false, true, true, 2, null),
    PCLA_POSTPONED_CALL(false, true, false, false),
    PARTIAL(false, false, false, false),
    UNTIL_FIRST_LAMBDA(false, false, true, false);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final boolean allLambdasShouldBeAnalyzed;
    private final boolean allPostponedAtomsShouldBeAnalyzed;
    private final boolean fixNotInferredTypeVariablesToErrorType;
    private final boolean shouldForkPointConstraintsBeResolved;

    @Retention(RetentionPolicy.RUNTIME)
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000Ê\u0001\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode$ExclusiveForOverloadResolutionByLambdaReturnType;", "", "org.jetbrains.kotlin:resolution.common", "Lkotlin/RequiresOptIn;", "message", "This mode should be used only for OverloadResolutionByLambdaReturnTypeResolver. Consider using isUntilFirstLambda() if you need just to check this mode."}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public @interface ExclusiveForOverloadResolutionByLambdaReturnType {
    }

    ConstraintSystemCompletionMode(boolean z, boolean z2, boolean z3, boolean z4) {
        this.allPostponedAtomsShouldBeAnalyzed = z;
        this.allLambdasShouldBeAnalyzed = z2;
        this.shouldForkPointConstraintsBeResolved = z3;
        this.fixNotInferredTypeVariablesToErrorType = z4;
    }

    public static EnumEntries<ConstraintSystemCompletionMode> getEntries() {
        return $ENTRIES;
    }

    public final boolean getAllLambdasShouldBeAnalyzed() {
        return this.allLambdasShouldBeAnalyzed;
    }

    public final boolean getAllPostponedAtomsShouldBeAnalyzed() {
        return this.allPostponedAtomsShouldBeAnalyzed;
    }

    public final boolean getFixNotInferredTypeVariablesToErrorType() {
        return this.fixNotInferredTypeVariablesToErrorType;
    }

    public final boolean getShouldForkPointConstraintsBeResolved() {
        return this.shouldForkPointConstraintsBeResolved;
    }

    public final boolean isUntilFirstLambda() {
        return this == UNTIL_FIRST_LAMBDA;
    }

    /* synthetic */ ConstraintSystemCompletionMode(boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? z : z2, z3, z4);
    }
}

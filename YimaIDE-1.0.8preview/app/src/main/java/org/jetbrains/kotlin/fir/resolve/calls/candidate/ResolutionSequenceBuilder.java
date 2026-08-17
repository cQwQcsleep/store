package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckArguments;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckCallableReferenceExpectedType;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckContextArguments;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckDispatchReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckExtensionReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckLowPriorityInOverloadResolution;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckVisibility;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CreateFreshTypeVariableSubstitutorStage;
import org.jetbrains.kotlin.fir.resolve.calls.stages.DiscriminateSyntheticAndForbiddenProperties;
import org.jetbrains.kotlin.fir.resolve.calls.stages.EagerResolveOfCallableReferences;
import org.jetbrains.kotlin.fir.resolve.calls.stages.InitializeEmptyArgumentMap;
import org.jetbrains.kotlin.fir.resolve.calls.stages.MapArguments;
import org.jetbrains.kotlin.fir.resolve.calls.stages.MapTypeArguments;
import org.jetbrains.kotlin.fir.resolve.calls.stages.NoTypeArguments;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0006\u0010$\u001a\u00020%R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0010¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/ResolutionSequenceBuilder;", Argument.Delimiters.none, "checkVisibility", Argument.Delimiters.none, "discriminateSynthetics", "checkDispatchReceiver", "checkExtensionReceiver", "checkArguments", "checkLowPriorityInOverloadResolution", "mapTypeArguments", "resolveCallableReferenceArguments", "checkCallableReferenceExpectedType", "checkContextParameters", "<init>", "(ZZZZZZZZZZ)V", "getCheckVisibility", "()Z", "setCheckVisibility", "(Z)V", "getDiscriminateSynthetics", "setDiscriminateSynthetics", "getCheckDispatchReceiver", "setCheckDispatchReceiver", "getCheckExtensionReceiver", "setCheckExtensionReceiver", "getCheckArguments", "setCheckArguments", "getCheckLowPriorityInOverloadResolution", "setCheckLowPriorityInOverloadResolution", "getMapTypeArguments", "setMapTypeArguments", "getResolveCallableReferenceArguments", "setResolveCallableReferenceArguments", "getCheckCallableReferenceExpectedType", "setCheckCallableReferenceExpectedType", "getCheckContextParameters", "build", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ResolutionSequenceBuilder {
    private boolean checkArguments;
    private boolean checkCallableReferenceExpectedType;
    private final boolean checkContextParameters;
    private boolean checkDispatchReceiver;
    private boolean checkExtensionReceiver;
    private boolean checkLowPriorityInOverloadResolution;
    private boolean checkVisibility;
    private boolean discriminateSynthetics;
    private boolean mapTypeArguments;
    private boolean resolveCallableReferenceArguments;

    public /* synthetic */ ResolutionSequenceBuilder(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4, (i & 16) != 0 ? false : z5, (i & 32) != 0 ? false : z6, (i & 64) != 0 ? false : z7, (i & 128) != 0 ? false : z8, (i & 256) != 0 ? false : z9, (i & 512) != 0 ? false : z10);
    }

    public final CallKind build() {
        ArrayList arrayList = new ArrayList();
        if (this.checkVisibility) {
            arrayList.add(CheckVisibility.INSTANCE);
        }
        if (this.discriminateSynthetics) {
            arrayList.add(DiscriminateSyntheticAndForbiddenProperties.INSTANCE);
        }
        arrayList.add(this.checkArguments ? MapArguments.INSTANCE : InitializeEmptyArgumentMap.INSTANCE);
        arrayList.add(this.mapTypeArguments ? MapTypeArguments.INSTANCE : NoTypeArguments.INSTANCE);
        if (this.checkArguments || this.checkDispatchReceiver || this.checkExtensionReceiver) {
            arrayList.add(CreateFreshTypeVariableSubstitutorStage.INSTANCE);
        }
        if (this.checkDispatchReceiver) {
            arrayList.add(CheckDispatchReceiver.INSTANCE);
        }
        if (this.checkExtensionReceiver) {
            arrayList.add(CheckExtensionReceiver.INSTANCE);
        }
        if (this.checkArguments) {
            arrayList.add(CheckArguments.INSTANCE);
        }
        if (this.checkContextParameters) {
            arrayList.add(CheckContextArguments.INSTANCE);
        }
        if (this.resolveCallableReferenceArguments) {
            arrayList.add(EagerResolveOfCallableReferences.INSTANCE);
        }
        if (this.checkLowPriorityInOverloadResolution) {
            arrayList.add(CheckLowPriorityInOverloadResolution.INSTANCE);
        }
        if (this.checkCallableReferenceExpectedType) {
            arrayList.add(CheckCallableReferenceExpectedType.INSTANCE);
        }
        ResolutionStage[] resolutionStageArr = (ResolutionStage[]) arrayList.toArray(new ResolutionStage[0]);
        return new CallKind.CustomForIde((ResolutionStage[]) Arrays.copyOf(resolutionStageArr, resolutionStageArr.length));
    }

    public final boolean getCheckArguments() {
        return this.checkArguments;
    }

    public final boolean getCheckCallableReferenceExpectedType() {
        return this.checkCallableReferenceExpectedType;
    }

    public final boolean getCheckContextParameters() {
        return this.checkContextParameters;
    }

    public final boolean getCheckDispatchReceiver() {
        return this.checkDispatchReceiver;
    }

    public final boolean getCheckExtensionReceiver() {
        return this.checkExtensionReceiver;
    }

    public final boolean getCheckLowPriorityInOverloadResolution() {
        return this.checkLowPriorityInOverloadResolution;
    }

    public final boolean getCheckVisibility() {
        return this.checkVisibility;
    }

    public final boolean getDiscriminateSynthetics() {
        return this.discriminateSynthetics;
    }

    public final boolean getMapTypeArguments() {
        return this.mapTypeArguments;
    }

    public final boolean getResolveCallableReferenceArguments() {
        return this.resolveCallableReferenceArguments;
    }

    public final void setCheckArguments(boolean z) {
        this.checkArguments = z;
    }

    public final void setCheckCallableReferenceExpectedType(boolean z) {
        this.checkCallableReferenceExpectedType = z;
    }

    public final void setCheckDispatchReceiver(boolean z) {
        this.checkDispatchReceiver = z;
    }

    public final void setCheckExtensionReceiver(boolean z) {
        this.checkExtensionReceiver = z;
    }

    public final void setCheckLowPriorityInOverloadResolution(boolean z) {
        this.checkLowPriorityInOverloadResolution = z;
    }

    public final void setCheckVisibility(boolean z) {
        this.checkVisibility = z;
    }

    public final void setDiscriminateSynthetics(boolean z) {
        this.discriminateSynthetics = z;
    }

    public final void setMapTypeArguments(boolean z) {
        this.mapTypeArguments = z;
    }

    public final void setResolveCallableReferenceArguments(boolean z) {
        this.resolveCallableReferenceArguments = z;
    }

    public ResolutionSequenceBuilder(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10) {
        this.checkVisibility = z;
        this.discriminateSynthetics = z2;
        this.checkDispatchReceiver = z3;
        this.checkExtensionReceiver = z4;
        this.checkArguments = z5;
        this.checkLowPriorityInOverloadResolution = z6;
        this.mapTypeArguments = z7;
        this.resolveCallableReferenceArguments = z8;
        this.checkCallableReferenceExpectedType = z9;
        this.checkContextParameters = z10;
    }

    public ResolutionSequenceBuilder() {
        this(false, false, false, false, false, false, false, false, false, false, 1023, null);
    }
}

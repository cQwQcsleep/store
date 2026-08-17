package org.jetbrains.kotlin.fir.backend.generators;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazyFakeOverrideGenerator$computeFakeOverrideKeysImpl$1$compatibleType$1 implements Function1<RigidTypeMarker, Boolean> {
    final /* synthetic */ ConeClassLikeLookupTag $symbolDispatchReceiver;
    final /* synthetic */ ConeInferenceContext $this_with;

    public Fir2IrLazyFakeOverrideGenerator$computeFakeOverrideKeysImpl$1$compatibleType$1(ConeInferenceContext coneInferenceContext, ConeClassLikeLookupTag coneClassLikeLookupTag) {
        this.$this_with = coneInferenceContext;
        this.$symbolDispatchReceiver = coneClassLikeLookupTag;
    }

    public final Boolean invoke(RigidTypeMarker rigidTypeMarker) {
        rigidTypeMarker.getClass();
        return Boolean.valueOf(Intrinsics.areEqual(this.$this_with.m691typeConstructor(rigidTypeMarker), this.$symbolDispatchReceiver));
    }
}

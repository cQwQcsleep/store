package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"relationForExposedVisibility", "Lorg/jetbrains/kotlin/fir/analysis/checkers/PermissivenessForExposedVisibility;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "sessionHolder", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "other", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;)Lorg/jetbrains/kotlin/fir/analysis/checkers/PermissivenessForExposedVisibility;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirVisibilityHelpersKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EffectiveVisibility.Permissiveness.values().length];
            try {
                iArr[EffectiveVisibility.Permissiveness.LESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EffectiveVisibility.Permissiveness.SAME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EffectiveVisibility.Permissiveness.MORE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[EffectiveVisibility.Permissiveness.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final PermissivenessForExposedVisibility relationForExposedVisibility(SessionHolder sessionHolder, EffectiveVisibility effectiveVisibility, EffectiveVisibility effectiveVisibility2) {
        sessionHolder.getClass();
        effectiveVisibility.getClass();
        effectiveVisibility2.getClass();
        if ((effectiveVisibility instanceof EffectiveVisibility.InternalOrPackage) && (effectiveVisibility2 instanceof EffectiveVisibility.InternalOrPackage) && !Intrinsics.areEqual(effectiveVisibility, effectiveVisibility2)) {
            return PermissivenessForExposedVisibility.PACKAGE_PRIVATE_FROM_INTERNAL;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[effectiveVisibility.relation(effectiveVisibility2, TypeComponentsKt.getTypeContext(sessionHolder.get$session())).ordinal()];
        if (i == 1) {
            return PermissivenessForExposedVisibility.LESS;
        }
        if (i == 2) {
            return PermissivenessForExposedVisibility.SAME;
        }
        if (i == 3) {
            return PermissivenessForExposedVisibility.MORE;
        }
        if (i == 4) {
            return PermissivenessForExposedVisibility.UNKNOWN;
        }
        bu8.a();
        return null;
    }
}

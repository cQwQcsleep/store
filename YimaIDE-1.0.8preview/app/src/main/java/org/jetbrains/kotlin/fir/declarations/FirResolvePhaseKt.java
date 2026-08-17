package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0012\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0003¨\u0006\u0007"}, d2 = {"isBodyResolve", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)Z", "isItAllowedToCallLazyResolveTo", "requestedPhase", "isItAllowedToCallLazyResolveToTheSamePhase", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvePhaseKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FirResolvePhase.values().length];
            try {
                iArr[FirResolvePhase.BODY_RESOLVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirResolvePhase.COMPILER_REQUIRED_ANNOTATIONS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final boolean isBodyResolve(FirResolvePhase firResolvePhase) {
        firResolvePhase.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[firResolvePhase.ordinal()];
        return i == 1 || i == 2;
    }

    public static final boolean isItAllowedToCallLazyResolveTo(FirResolvePhase firResolvePhase, FirResolvePhase firResolvePhase2) {
        firResolvePhase.getClass();
        firResolvePhase2.getClass();
        if (firResolvePhase.compareTo(firResolvePhase2) > 0) {
            return true;
        }
        if (firResolvePhase == firResolvePhase2) {
            return isItAllowedToCallLazyResolveToTheSamePhase(firResolvePhase);
        }
        return false;
    }

    public static final boolean isItAllowedToCallLazyResolveToTheSamePhase(FirResolvePhase firResolvePhase) {
        firResolvePhase.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[firResolvePhase.ordinal()];
        return i == 2 || i == 3;
    }
}

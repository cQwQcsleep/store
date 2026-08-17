package org.jetbrains.kotlin.fir.deserialization;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.EffectiveVisibilityUtilsKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.deserialization.FirMemberDeserializerKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"toLazyEffectiveVisibility", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "forClass", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-deserialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMemberDeserializerKt {
    public static EffectiveVisibility a(Visibility visibility, FirClassLikeSymbol firClassLikeSymbol, boolean z, FirSession firSession) {
        EffectiveVisibility effectiveVisibility;
        EffectiveVisibility effectiveVisibility$default = EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(visibility, firClassLikeSymbol, z, false, 4, (Object) null);
        if (firClassLikeSymbol == null || (effectiveVisibility = firClassLikeSymbol.getResolvedStatus().getEffectiveVisibility()) == null) {
            effectiveVisibility = EffectiveVisibility.Public.INSTANCE;
        }
        return effectiveVisibility.lowerBound(effectiveVisibility$default, TypeComponentsKt.getTypeContext(firSession));
    }

    public static final Lazy<EffectiveVisibility> toLazyEffectiveVisibility(final Visibility visibility, final FirClassLikeSymbol<?> firClassLikeSymbol, final FirSession firSession, final boolean z) {
        visibility.getClass();
        firSession.getClass();
        return LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: pa5
            public final Object invoke() {
                return FirMemberDeserializerKt.a(visibility, firClassLikeSymbol, z, firSession);
            }
        });
    }
}

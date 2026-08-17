package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B=\b\u0002\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eB/\b\u0016\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000fJ\u001a\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00012\u0006\u0010\u0011\u001a\u00020\fH\u0016J\u001c\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\nH\u0017b\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitDispatchReceiverValue;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "boundSymbol", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "originalType", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "mutable", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Z)V", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "createSnapshot", "keepMutable", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImplicitDispatchReceiverValue extends ImplicitReceiverValue<FirClassSymbol<?>> {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ImplicitDispatchReceiverValue(FirClassSymbol<?> firClassSymbol, ConeKotlinType coneKotlinType, FirSession firSession, ScopeSession scopeSession) {
        this(firClassSymbol, coneKotlinType, coneKotlinType, firSession, scopeSession, true);
        firClassSymbol.getClass();
        coneKotlinType.getClass();
        firSession.getClass();
        scopeSession.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue, org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue
    public ImplicitReceiverValue<FirClassSymbol<?>> createSnapshot(boolean keepMutable) {
        return new ImplicitDispatchReceiverValue(getBoundSymbol(), getType(), getOriginalType(), getSession(), getScopeSession(), keepMutable);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue
    @DelicateScopeAPI
    public ImplicitDispatchReceiverValue withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new ImplicitDispatchReceiverValue(getBoundSymbol(), getType(), getOriginalType(), newSession, newScopeSession, getMutable());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ImplicitDispatchReceiverValue(FirClassSymbol firClassSymbol, ConeKotlinType coneKotlinType, FirSession firSession, ScopeSession scopeSession, int i, DefaultConstructorMarker defaultConstructorMarker) {
        FirClassSymbol firClassSymbol2;
        if ((i & 2) != 0) {
            firClassSymbol2 = firClassSymbol;
            coneKotlinType = TypeConstructionUtilsKt.constructType$default((FirClassLikeSymbol) firClassSymbol2, (ConeTypeProjection[]) null, false, (ConeAttributes) null, 7, (Object) null);
        } else {
            firClassSymbol2 = firClassSymbol;
        }
        this(firClassSymbol2, coneKotlinType, firSession, scopeSession);
    }

    private ImplicitDispatchReceiverValue(FirClassSymbol<?> firClassSymbol, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession, ScopeSession scopeSession, boolean z) {
        super(firClassSymbol, coneKotlinType, coneKotlinType2, firSession, scopeSession, z, null, 64, null);
    }
}

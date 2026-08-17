package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.InaccessibleReceiverKind;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u0018\u00002\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001BE\b\u0002\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010B5\b\u0016\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0011J\u001a\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u00012\u0006\u0010\u0015\u001a\u00020\fH\u0016J\u001c\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\nH\u0017b\u0002\b\u0019R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/InaccessibleImplicitReceiverValue;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "boundSymbol", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "originalType", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "mutable", Argument.Delimiters.none, "kind", "Lorg/jetbrains/kotlin/fir/expressions/InaccessibleReceiverKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;ZLorg/jetbrains/kotlin/fir/expressions/InaccessibleReceiverKind;)V", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/expressions/InaccessibleReceiverKind;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "getKind", "()Lorg/jetbrains/kotlin/fir/expressions/InaccessibleReceiverKind;", "createSnapshot", "keepMutable", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InaccessibleImplicitReceiverValue extends ImplicitReceiverValue<FirClassSymbol<?>> {
    private final InaccessibleReceiverKind kind;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InaccessibleImplicitReceiverValue(FirClassSymbol<?> firClassSymbol, ConeKotlinType coneKotlinType, InaccessibleReceiverKind inaccessibleReceiverKind, FirSession firSession, ScopeSession scopeSession) {
        this(firClassSymbol, coneKotlinType, coneKotlinType, firSession, scopeSession, true, inaccessibleReceiverKind);
        firClassSymbol.getClass();
        coneKotlinType.getClass();
        inaccessibleReceiverKind.getClass();
        firSession.getClass();
        scopeSession.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue, org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue
    public ImplicitReceiverValue<FirClassSymbol<?>> createSnapshot(boolean keepMutable) {
        return new InaccessibleImplicitReceiverValue(getBoundSymbol(), getType(), getOriginalType(), getSession(), getScopeSession(), keepMutable, this.kind);
    }

    public final InaccessibleReceiverKind getKind() {
        return this.kind;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue
    @DelicateScopeAPI
    public InaccessibleImplicitReceiverValue withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new InaccessibleImplicitReceiverValue(getBoundSymbol(), getType(), getOriginalType(), newSession, newScopeSession, getMutable(), this.kind);
    }

    private InaccessibleImplicitReceiverValue(FirClassSymbol<?> firClassSymbol, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession, ScopeSession scopeSession, boolean z, InaccessibleReceiverKind inaccessibleReceiverKind) {
        super(firClassSymbol, coneKotlinType, coneKotlinType2, firSession, scopeSession, z, inaccessibleReceiverKind, null);
        this.kind = inaccessibleReceiverKind;
    }
}

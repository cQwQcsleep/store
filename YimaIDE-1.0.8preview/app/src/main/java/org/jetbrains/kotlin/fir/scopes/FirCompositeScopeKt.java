package org.jetbrains.kotlin.fir.scopes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a;\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0087\bb\u0002\b\t¨\u0006\n"}, d2 = {"withReplacedSessionOrNull", Argument.Delimiters.none, "S", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", Argument.Delimiters.none, "newSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCompositeScopeKt {
    @DelicateScopeAPI
    public static final /* synthetic */ <S extends FirScope> List<S> withReplacedSessionOrNull(Iterable<? extends S> iterable, FirSession firSession, ScopeSession scopeSession) {
        iterable.getClass();
        firSession.getClass();
        scopeSession.getClass();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator<? extends S> it = iterable.iterator();
        boolean z = false;
        while (true) {
            FirScope firScope = null;
            if (!it.hasNext()) {
                break;
            }
            S next = it.next();
            FirScope firScopeWithReplacedSessionOrNull = next.withReplacedSessionOrNull(firSession, scopeSession);
            if (firScopeWithReplacedSessionOrNull != null) {
                firScope = firScopeWithReplacedSessionOrNull;
                z = true;
            }
            Intrinsics.reifiedOperationMarker(1, "S?");
            if (firScope != null) {
                next = firScope;
            }
            arrayList.add(next);
        }
        if (z) {
            return arrayList;
        }
        return null;
    }
}

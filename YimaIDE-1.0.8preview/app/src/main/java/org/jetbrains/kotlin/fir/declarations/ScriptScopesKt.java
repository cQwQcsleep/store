package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValueForScriptOrSnippet;
import org.jetbrains.kotlin.fir.scopes.impl.FirScriptDeclarationsScope;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"collectTowerDataElementsForScript", "Lorg/jetbrains/kotlin/fir/declarations/TowerElementsForScript;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "owner", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ScriptScopesKt {
    public static final TowerElementsForScript collectTowerDataElementsForScript(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirScript firScript) {
        sessionAndScopeSessionHolder.getClass();
        firScript.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firScript, FirResolvePhase.TYPES);
        List<FirScriptReceiverParameter> receivers = firScript.getReceivers();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(receivers, 10));
        int i = 0;
        for (Object obj : receivers) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirScriptReceiverParameter firScriptReceiverParameter = (FirScriptReceiverParameter) obj;
            arrayList.add(new ImplicitReceiverValueForScriptOrSnippet(firScriptReceiverParameter.getSymbol(), FirTypeUtilsKt.getConeType(firScriptReceiverParameter.getTypeRef()), sessionAndScopeSessionHolder.getSession(), sessionAndScopeSessionHolder.getScopeSession()));
            i = i2;
        }
        return new TowerElementsForScript(CollectionsKt.asReversed(arrayList), new FirScriptDeclarationsScope(sessionAndScopeSessionHolder.getSession(), firScript));
    }
}

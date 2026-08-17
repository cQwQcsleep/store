package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataElement;
import org.jetbrains.kotlin.fir.scopes.FirScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003¨\u0006\u0004"}, d2 = {"createCurrentScopeList", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BodyResolveComponentsKt {
    public static final List<FirScope> createCurrentScopeList(BodyResolveComponents bodyResolveComponents) {
        bodyResolveComponents.getClass();
        List listAsReversed = CollectionsKt.asReversed(bodyResolveComponents.getTowerDataElements());
        ArrayList arrayList = new ArrayList();
        Iterator it = listAsReversed.iterator();
        while (it.hasNext()) {
            FirScope scope = ((FirTowerDataElement) it.next()).getScope();
            if (scope != null) {
                arrayList.add(scope);
            }
        }
        return arrayList;
    }
}

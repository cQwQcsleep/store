package org.jetbrains.kotlin.fir.resolve.calls.tower;

import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataContext;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataElement;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.tower.TowerDataElementsForName;
import org.jetbrains.kotlin.fir.scopes.impl.FirLocalScope;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR'\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0010\u0010\fR+\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u000e0\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0012\u001a\u0004\b\u0015\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "towerDataContext", "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataContext;)V", "nonLocalTowerDataElements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataElement;", "getNonLocalTowerDataElements", "()Ljava/util/List;", "reversedFilteredLocalScopes", "Lkotlin/collections/IndexedValue;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirLocalScope;", "getReversedFilteredLocalScopes", "reversedFilteredLocalScopes$delegate", "Lkotlin/Lazy;", "implicitReceivers", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "getImplicitReceivers", "implicitReceivers$delegate", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TowerDataElementsForName {

    /* JADX INFO: renamed from: implicitReceivers$delegate, reason: from kotlin metadata */
    private final Lazy implicitReceivers;
    private final List<FirTowerDataElement> nonLocalTowerDataElements;

    /* JADX INFO: renamed from: reversedFilteredLocalScopes$delegate, reason: from kotlin metadata */
    private final Lazy reversedFilteredLocalScopes;

    public TowerDataElementsForName(final Name name, final FirTowerDataContext firTowerDataContext) {
        name.getClass();
        firTowerDataContext.getClass();
        this.nonLocalTowerDataElements = CollectionsKt.asReversed(firTowerDataContext.getNonLocalTowerDataElements());
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        this.reversedFilteredLocalScopes = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: rhe
            public final Object invoke() {
                return TowerDataElementsForName.b(firTowerDataContext, name);
            }
        });
        this.implicitReceivers = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: she
            public final Object invoke() {
                return TowerDataElementsForName.a(this.b);
            }
        });
    }

    public static List a(TowerDataElementsForName towerDataElementsForName) {
        List<FirTowerDataElement> list = towerDataElementsForName.nonLocalTowerDataElements;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ImplicitReceiverValue<?> implicitReceiver = ((FirTowerDataElement) obj).getImplicitReceiver();
            IndexedValue indexedValue = implicitReceiver != null ? new IndexedValue(i, implicitReceiver) : null;
            if (indexedValue != null) {
                arrayList.add(indexedValue);
            }
            i = i2;
        }
        return arrayList;
    }

    public static List b(FirTowerDataContext firTowerDataContext, Name name) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        PersistentList<FirLocalScope> localScopes = firTowerDataContext.getLocalScopes();
        int lastIndex = CollectionsKt.getLastIndex(localScopes);
        for (int i = lastIndex; -1 < i; i--) {
            FirLocalScope firLocalScope = (FirLocalScope) localScopes.get(i);
            if (firLocalScope.mayContainName(name)) {
                listCreateListBuilder.add(new IndexedValue(lastIndex - i, firLocalScope));
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    public final List<IndexedValue<ImplicitReceiverValue<?>>> getImplicitReceivers() {
        return (List) this.implicitReceivers.getValue();
    }

    public final List<FirTowerDataElement> getNonLocalTowerDataElements() {
        return this.nonLocalTowerDataElements;
    }

    public final List<IndexedValue<FirLocalScope>> getReversedFilteredLocalScopes() {
        return (List) this.reversedFilteredLocalScopes.getValue();
    }
}

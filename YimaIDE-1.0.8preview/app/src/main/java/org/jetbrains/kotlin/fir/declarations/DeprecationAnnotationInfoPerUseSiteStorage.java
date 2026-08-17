package org.jetbrains.kotlin.fir.declarations;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR%\u0010\u0002\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/DeprecationAnnotationInfoPerUseSiteStorage;", Argument.Delimiters.none, "storage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/DeprecationInfoProvider;", "<init>", "(Ljava/util/Map;)V", "getStorage", "()Ljava/util/Map;", "toDeprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "firCachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeprecationAnnotationInfoPerUseSiteStorage {
    private final Map<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> storage;

    /* JADX WARN: Multi-variable type inference failed */
    public DeprecationAnnotationInfoPerUseSiteStorage(Map<AnnotationUseSiteTarget, ? extends List<? extends DeprecationInfoProvider>> map) {
        map.getClass();
        this.storage = map;
    }

    public final Map<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> getStorage() {
        return this.storage;
    }

    public final DeprecationsProvider toDeprecationsProvider(FirCachesFactory firCachesFactory) {
        firCachesFactory.getClass();
        if (this.storage.isEmpty()) {
            return EmptyDeprecationsProvider.INSTANCE;
        }
        Map<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> map = this.storage;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> entry : map.entrySet()) {
            if (entry.getKey() != null) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        List<DeprecationInfoProvider> listPlus = this.storage.get(null);
        List<DeprecationInfoProvider> list = this.storage.get(AnnotationUseSiteTarget.ALL);
        if (listPlus == null) {
            listPlus = list;
        } else if (list != null) {
            listPlus = CollectionsKt.plus(listPlus, list);
        }
        if (linkedHashMap.isEmpty()) {
            linkedHashMap = null;
        }
        return new DeprecationsProviderImpl(firCachesFactory, listPlus, linkedHashMap);
    }
}

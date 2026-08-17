package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0012\u001a\u00020\u000eH\u0002R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProviderImpl;", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "firCachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "all", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/DeprecationInfoProvider;", "bySpecificSite", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "<init>", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;Ljava/util/List;Ljava/util/Map;)V", "cache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsPerUseSite;", Argument.Delimiters.none, "getDeprecationsInfo", "languageVersionSettings", "computeDeprecationInfoOrNull", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeprecationsProviderImpl extends DeprecationsProvider {
    private final List<DeprecationInfoProvider> all;
    private final Map<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> bySpecificSite;
    private final FirCache cache;

    /* JADX WARN: Multi-variable type inference failed */
    public DeprecationsProviderImpl(FirCachesFactory firCachesFactory, List<? extends DeprecationInfoProvider> list, Map<AnnotationUseSiteTarget, ? extends List<? extends DeprecationInfoProvider>> map) {
        firCachesFactory.getClass();
        this.all = list;
        this.bySpecificSite = map;
        this.cache = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.DeprecationsProviderImpl$special$$inlined$createCache$1
            public final DeprecationsPerUseSite invoke(LanguageVersionSettings languageVersionSettings, Void r7) {
                languageVersionSettings.getClass();
                LanguageVersionSettings languageVersionSettings2 = languageVersionSettings;
                List list2 = this.this$0.all;
                LinkedHashMap linkedHashMap = null;
                FirDeprecationInfo firDeprecationInfoComputeDeprecationInfoOrNull = list2 != null ? this.this$0.computeDeprecationInfoOrNull(list2, languageVersionSettings2) : null;
                Map map2 = this.this$0.bySpecificSite;
                if (map2 != null) {
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(map2.size()));
                    for (Map.Entry entry : map2.entrySet()) {
                        linkedHashMap2.put(entry.getKey(), this.this$0.computeDeprecationInfoOrNull((List) entry.getValue(), languageVersionSettings2));
                    }
                    LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                    for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
                        if (((FirDeprecationInfo) entry2.getValue()) != null) {
                            linkedHashMap3.put(entry2.getKey(), entry2.getValue());
                        }
                    }
                    linkedHashMap = linkedHashMap3;
                }
                return new DeprecationsPerUseSite(firDeprecationInfoComputeDeprecationInfoOrNull, linkedHashMap);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((LanguageVersionSettings) obj, (Void) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirDeprecationInfo computeDeprecationInfoOrNull(List<? extends DeprecationInfoProvider> list, LanguageVersionSettings languageVersionSettings) {
        Object obj;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            FirDeprecationInfo firDeprecationInfoComputeDeprecationInfo = ((DeprecationInfoProvider) it.next()).computeDeprecationInfo(languageVersionSettings);
            if (firDeprecationInfoComputeDeprecationInfo != null) {
                arrayList.add(firDeprecationInfoComputeDeprecationInfo);
            }
        }
        Iterator it2 = arrayList.iterator();
        if (it2.hasNext()) {
            Object next = it2.next();
            if (it2.hasNext()) {
                Comparable deprecationLevel = ((FirDeprecationInfo) next).getDeprecationLevel();
                do {
                    Object next2 = it2.next();
                    Comparable deprecationLevel2 = ((FirDeprecationInfo) next2).getDeprecationLevel();
                    if (deprecationLevel.compareTo(deprecationLevel2) < 0) {
                        next = next2;
                        deprecationLevel = deprecationLevel2;
                    }
                } while (it2.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        return (FirDeprecationInfo) obj;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.DeprecationsProvider
    public DeprecationsPerUseSite getDeprecationsInfo(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        return (DeprecationsPerUseSite) this.cache.getValue(languageVersionSettings, null);
    }
}

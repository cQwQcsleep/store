package org.jetbrains.kotlin.load.java.typeEnhancement;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007B5\b\u0016\u0012*\u0010\b\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\n0\t\"\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\n¢\u0006\u0004\b\u0006\u0010\u000bJ\u0006\u0010\u000e\u001a\u00020\u0000R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/load/java/typeEnhancement/TypeEnhancementInfo;", "", "map", "", "", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/JavaTypeQualifiers;", "<init>", "(Ljava/util/Map;)V", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)V", "getMap", "()Ljava/util/Map;", "copyForWarnings", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TypeEnhancementInfo {
    private final Map<Integer, JavaTypeQualifiers> map;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TypeEnhancementInfo(Pair<Integer, JavaTypeQualifiers>... pairArr) {
        this((Map<Integer, JavaTypeQualifiers>) MapsKt.mapOf((Pair[]) Arrays.copyOf(pairArr, pairArr.length)));
        pairArr.getClass();
    }

    public final TypeEnhancementInfo copyForWarnings() {
        Map<Integer, JavaTypeQualifiers> map = this.map;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        Iterator<T> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            linkedHashMap.put(entry.getKey(), JavaTypeQualifiers.copy$default((JavaTypeQualifiers) entry.getValue(), null, null, false, true, true, 7, null));
        }
        return new TypeEnhancementInfo(linkedHashMap);
    }

    public final Map<Integer, JavaTypeQualifiers> getMap() {
        return this.map;
    }

    public TypeEnhancementInfo(Map<Integer, JavaTypeQualifiers> map) {
        map.getClass();
        this.map = map;
    }
}

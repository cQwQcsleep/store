package org.jetbrains.kotlin.analysis.utils.caches;

import com.intellij.util.containers.ContainerUtil;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J#\u0010\u0011\u001a\u00028\u00012\u0006\u0010\u0012\u001a\u00028\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015Rt\u0010\t\u001ah\u0012\u0016\u0012\u0014 \f*\t\u0018\u00018\u0000¢\u0006\u0002\b\u000b8\u0000¢\u0006\u0002\b\u000b\u0012\u0016\u0012\u0014 \f*\t\u0018\u00018\u0001¢\u0006\u0002\b\u000b8\u0001¢\u0006\u0002\b\u000b \f*2\u0012\u0016\u0012\u0014 \f*\t\u0018\u00018\u0000¢\u0006\u0002\b\u000b8\u0000¢\u0006\u0002\b\u000b\u0012\u0016\u0012\u0014 \f*\t\u0018\u00018\u0001¢\u0006\u0002\b\u000b8\u0001¢\u0006\u0002\b\u000b0\r0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMapWithoutTrackers;", "K", "", "V", "Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMap;", "kind", "Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMap$Kind;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMap$Kind;)V", "cache", "", "Lorg/jetbrains/annotations/NotNull;", "kotlin.jvm.PlatformType", "", "clear", "", "clearCachedValues", "getOrPut", "key", "create", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class SoftCachedMapWithoutTrackers<K, V> extends SoftCachedMap<K, V> {
    private final Map<K, V> cache;

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SoftCachedMap.Kind.values().length];
            try {
                iArr[SoftCachedMap.Kind.SOFT_KEYS_SOFT_VALUES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SoftCachedMap.Kind.STRONG_KEYS_SOFT_VALUES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SoftCachedMapWithoutTrackers(SoftCachedMap.Kind kind) {
        Map<K, V> mapCreateConcurrentSoftKeySoftValueMap;
        kind.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i == 1) {
            mapCreateConcurrentSoftKeySoftValueMap = ContainerUtil.createConcurrentSoftKeySoftValueMap();
            mapCreateConcurrentSoftKeySoftValueMap.getClass();
        } else {
            if (i != 2) {
                bu8.a();
                throw null;
            }
            mapCreateConcurrentSoftKeySoftValueMap = ContainerUtil.createSoftValueMap();
            mapCreateConcurrentSoftKeySoftValueMap.getClass();
        }
        this.cache = mapCreateConcurrentSoftKeySoftValueMap;
    }

    @Override // org.jetbrains.kotlin.analysis.utils.caches.SoftCachedMap
    public void clear() {
        this.cache.clear();
    }

    @Override // org.jetbrains.kotlin.analysis.utils.caches.SoftCachedMap
    public void clearCachedValues() {
    }

    @Override // org.jetbrains.kotlin.analysis.utils.caches.SoftCachedMap
    public V getOrPut(K key, Function0<? extends V> create) {
        key.getClass();
        create.getClass();
        Map<K, V> map = this.cache;
        V v = map.get(key);
        if (v == null) {
            v = (V) create.invoke();
            map.put(key, v);
        }
        v.getClass();
        return v;
    }
}

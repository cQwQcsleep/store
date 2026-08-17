package org.jetbrains.kotlin.analysis.utils.caches;

import com.intellij.openapi.project.Project;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.util.CachedValueBase;
import com.intellij.util.containers.ContainerUtil;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B%\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J#\u0010\u0016\u001a\u00028\u00012\u0006\u0010\u0017\u001a\u00028\u00002\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\u0019H\u0016¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR4\u0010\u000e\u001a(\u0012\t\u0012\u00078\u0000¢\u0006\u0002\b\u0010\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u00010\u0011¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00120\u000f¢\u0006\u0002\b\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMapWithTrackers;", "K", "", "V", "Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMap;", "project", "Lcom/intellij/openapi/project/Project;", "kind", "Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMap$Kind;", "trackers", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/openapi/project/Project;Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMap$Kind;[Ljava/lang/Object;)V", "[Ljava/lang/Object;", "cache", "Ljava/util/concurrent/ConcurrentMap;", "Lorg/jetbrains/annotations/NotNull;", "Lcom/intellij/psi/util/CachedValue;", "Lkotlin/jvm/internal/EnhancedNullability;", "clear", "", "clearCachedValues", "getOrPut", "key", "create", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class SoftCachedMapWithTrackers<K, V> extends SoftCachedMap<K, V> {
    private final ConcurrentMap<K, com.intellij.psi.util.CachedValue<V>> cache;
    private final Project project;
    private final Object[] trackers;

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

    public SoftCachedMapWithTrackers(Project project, SoftCachedMap.Kind kind, Object[] objArr) {
        ConcurrentMap<K, com.intellij.psi.util.CachedValue<V>> concurrentMapCreateConcurrentSoftMap;
        project.getClass();
        kind.getClass();
        objArr.getClass();
        this.project = project;
        this.trackers = objArr;
        int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i == 1) {
            concurrentMapCreateConcurrentSoftMap = ContainerUtil.createConcurrentSoftMap();
        } else {
            if (i != 2) {
                bu8.a();
                throw null;
            }
            concurrentMapCreateConcurrentSoftMap = new ConcurrentHashMap<>();
        }
        concurrentMapCreateConcurrentSoftMap.getClass();
        this.cache = concurrentMapCreateConcurrentSoftMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CachedValueProvider.Result getOrPut$lambda$0$0(Function0 function0, SoftCachedMapWithTrackers softCachedMapWithTrackers) {
        Object objInvoke = function0.invoke();
        Object[] objArr = softCachedMapWithTrackers.trackers;
        return new CachedValueProvider.Result(objInvoke, Arrays.copyOf(objArr, objArr.length));
    }

    @Override // org.jetbrains.kotlin.analysis.utils.caches.SoftCachedMap
    public void clear() {
        this.cache.clear();
    }

    @Override // org.jetbrains.kotlin.analysis.utils.caches.SoftCachedMap
    public void clearCachedValues() {
        Iterator<T> it = this.cache.values().iterator();
        while (it.hasNext()) {
            CachedValueBase cachedValueBase = (com.intellij.psi.util.CachedValue) it.next();
            CachedValueBase cachedValueBase2 = cachedValueBase instanceof CachedValueBase ? cachedValueBase : null;
            if (cachedValueBase2 != null) {
                cachedValueBase2.clear();
            }
        }
    }

    @Override // org.jetbrains.kotlin.analysis.utils.caches.SoftCachedMap
    public V getOrPut(K key, final Function0<? extends V> create) {
        key.getClass();
        create.getClass();
        ConcurrentMap<K, com.intellij.psi.util.CachedValue<V>> concurrentMap = this.cache;
        com.intellij.psi.util.CachedValue<V> cachedValue = concurrentMap.get(key);
        if (cachedValue == null) {
            com.intellij.psi.util.CachedValue<V> cachedValueCreateCachedValue = CachedValuesManager.getManager(this.project).createCachedValue(new CachedValueProvider() { // from class: org.jetbrains.kotlin.analysis.utils.caches.a
                public final CachedValueProvider.Result compute() {
                    return SoftCachedMapWithTrackers.getOrPut$lambda$0$0(create, this);
                }
            });
            com.intellij.psi.util.CachedValue<V> cachedValuePutIfAbsent = concurrentMap.putIfAbsent(key, cachedValueCreateCachedValue);
            cachedValue = cachedValuePutIfAbsent == null ? cachedValueCreateCachedValue : cachedValuePutIfAbsent;
        }
        V v = (V) cachedValue.getValue();
        v.getClass();
        return v;
    }
}

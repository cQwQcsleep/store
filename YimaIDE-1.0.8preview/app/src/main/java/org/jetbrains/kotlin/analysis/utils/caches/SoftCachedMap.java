package org.jetbrains.kotlin.analysis.utils.caches;

import com.intellij.openapi.project.Project;
import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u0000 \u000e*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0002\u000e\u000fB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\u0006\u001a\u00028\u00012\u0006\u0010\u0007\u001a\u00028\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\tH&¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\fH&¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMap;", "K", "", "V", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getOrPut", "key", "create", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "clear", "", "clearCachedValues", "Companion", "Kind", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class SoftCachedMap<K, V> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMap$Kind;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;I)V", "SOFT_KEYS_SOFT_VALUES", "STRONG_KEYS_SOFT_VALUES", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public enum Kind {
        SOFT_KEYS_SOFT_VALUES,
        STRONG_KEYS_SOFT_VALUES;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }
    }

    public abstract void clear();

    public abstract void clearCachedValues();

    public abstract V getOrPut(K key, Function0<? extends V> create);

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JD\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\u0005\"\b\b\u0002\u0010\u0006*\u00020\u0001\"\b\b\u0003\u0010\u0007*\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMap$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "create", "Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMap;", "K", "V", "project", "Lcom/intellij/openapi/project/Project;", "kind", "Lorg/jetbrains/kotlin/analysis/utils/caches/SoftCachedMap$Kind;", "trackers", "", "org.jetbrains.kotlin:analysis-internal-utils"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <K, V> SoftCachedMap<K, V> create(Project project, Kind kind, List<? extends Object> trackers) {
            project.getClass();
            kind.getClass();
            trackers.getClass();
            return trackers.isEmpty() ? new SoftCachedMapWithoutTrackers(kind) : new SoftCachedMapWithTrackers(project, kind, trackers.toArray(new Object[0]));
        }

        private Companion() {
        }
    }
}

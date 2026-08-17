package org.jetbrains.kotlin.incremental;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import org.jetbrains.kotlin.incremental.components.EnumWhenTracker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0016R%\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/EnumWhenTrackerImpl;", "Lorg/jetbrains/kotlin/incremental/components/EnumWhenTracker;", "<init>", "()V", "whenExpressionFilePathToEnumClassMap", "", "", "", "getWhenExpressionFilePathToEnumClassMap", "()Ljava/util/Map;", "Ljava/util/concurrent/ConcurrentHashMap;", "report", "", "whenExpressionFilePath", "enumClassFqName", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EnumWhenTrackerImpl implements EnumWhenTracker {
    private final ConcurrentHashMap<String, Set<String>> whenExpressionFilePathToEnumClassMap = new ConcurrentHashMap<>();

    public final Map<String, Collection<String>> getWhenExpressionFilePathToEnumClassMap() {
        return this.whenExpressionFilePathToEnumClassMap;
    }

    @Override // org.jetbrains.kotlin.incremental.components.EnumWhenTracker
    public void report(String whenExpressionFilePath, String enumClassFqName) {
        Set<String> setPutIfAbsent;
        whenExpressionFilePath.getClass();
        enumClassFqName.getClass();
        ConcurrentHashMap<String, Set<String>> concurrentHashMap = this.whenExpressionFilePathToEnumClassMap;
        Set<String> hashSet = concurrentHashMap.get(whenExpressionFilePath);
        if (hashSet == null && (setPutIfAbsent = concurrentHashMap.putIfAbsent(whenExpressionFilePath, (hashSet = new HashSet<>()))) != null) {
            hashSet = setPutIfAbsent;
        }
        hashSet.add(enumClassFqName);
    }
}

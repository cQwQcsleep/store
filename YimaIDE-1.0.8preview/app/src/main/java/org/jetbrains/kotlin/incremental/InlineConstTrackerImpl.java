package org.jetbrains.kotlin.incremental;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.incremental.components.InlineConstTracker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0016R%\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/incremental/InlineConstTrackerImpl;", "Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "<init>", "()V", "inlineConstMap", "", "", "", "Lorg/jetbrains/kotlin/incremental/ConstantRef;", "getInlineConstMap", "()Ljava/util/Map;", "Ljava/util/HashMap;", "report", "", "filePath", "owner", "name", "constType", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InlineConstTrackerImpl implements InlineConstTracker {
    private final HashMap<String, Set<ConstantRef>> inlineConstMap = new HashMap<>();

    public final Map<String, Collection<ConstantRef>> getInlineConstMap() {
        return this.inlineConstMap;
    }

    @Override // org.jetbrains.kotlin.incremental.components.InlineConstTracker
    public void report(String filePath, String owner, String name, String constType) {
        filePath.getClass();
        owner.getClass();
        name.getClass();
        constType.getClass();
        HashMap<String, Set<ConstantRef>> map = this.inlineConstMap;
        Set<ConstantRef> hashSet = map.get(filePath);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            map.put(filePath, hashSet);
        }
        hashSet.add(new ConstantRef(owner, name, constType));
    }
}

package org.jetbrains.kotlin.incremental;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.incremental.components.ExpectActualTracker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0016R%\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0019\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0007¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ExpectActualTrackerImpl;", "Lorg/jetbrains/kotlin/incremental/components/ExpectActualTracker;", "<init>", "()V", "expectToActualMap", "", "Ljava/io/File;", "", "getExpectToActualMap", "()Ljava/util/Map;", "Ljava/util/HashMap;", "expectsOfLenientStubsSet", "getExpectsOfLenientStubsSet", "()Ljava/util/Set;", "Ljava/util/HashSet;", "report", "", "expectedFile", "actualFile", "reportExpectOfLenientStub", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ExpectActualTrackerImpl implements ExpectActualTracker {
    private final HashMap<File, Set<File>> expectToActualMap = new HashMap<>();
    private final HashSet<File> expectsOfLenientStubsSet = new HashSet<>();

    public final Map<File, Set<File>> getExpectToActualMap() {
        return this.expectToActualMap;
    }

    public final Set<File> getExpectsOfLenientStubsSet() {
        return this.expectsOfLenientStubsSet;
    }

    @Override // org.jetbrains.kotlin.incremental.components.ExpectActualTracker
    public void report(File expectedFile, File actualFile) {
        expectedFile.getClass();
        actualFile.getClass();
        HashMap<File, Set<File>> map = this.expectToActualMap;
        Set<File> hashSet = map.get(expectedFile);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            map.put(expectedFile, hashSet);
        }
        hashSet.add(actualFile);
    }

    @Override // org.jetbrains.kotlin.incremental.components.ExpectActualTracker
    public void reportExpectOfLenientStub(File expectedFile) {
        expectedFile.getClass();
        this.expectsOfLenientStubsSet.add(expectedFile);
    }
}

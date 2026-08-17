package org.jetbrains.kotlin.konan.properties;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\u0003H&J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\u0003H&J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\u0003H&J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\u0003H&J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\u0003H&J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/konan/properties/TargetableExternalStorage;", "", "absolute", "", "value", "downloadDependencies", "", "hostList", "", "key", "hostString", "hostTargetList", "hostTargetString", "targetList", "targetString", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface TargetableExternalStorage {
    String absolute(String value);

    void downloadDependencies();

    List<String> hostList(String key);

    String hostString(String key);

    List<String> hostTargetList(String key);

    String hostTargetString(String key);

    List<String> targetList(String key);

    String targetString(String key);
}

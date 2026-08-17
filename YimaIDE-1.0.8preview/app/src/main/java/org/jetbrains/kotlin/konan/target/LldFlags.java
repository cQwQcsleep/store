package org.jetbrains.kotlin.konan.target;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.konan.properties.TargetableExternalStorage;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/LldFlags;", "Lorg/jetbrains/kotlin/konan/properties/TargetableExternalStorage;", "lldFlags", "", "", "getLldFlags", "()Ljava/util/List;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface LldFlags extends TargetableExternalStorage {
    default List<String> getLldFlags() {
        return targetList("lld");
    }
}

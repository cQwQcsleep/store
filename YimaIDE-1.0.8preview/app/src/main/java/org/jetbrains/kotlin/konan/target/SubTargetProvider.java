package org.jetbrains.kotlin.konan.target;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Deprecated(level = DeprecationLevel.WARNING, message = "K/N subtargets are removed. You can freely remove the usages of SubTargetProvider from your code")
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001:\u0001\u0006J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0004H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/SubTargetProvider;", "", "availableSubTarget", "", "", "genericName", "NoSubTargets", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface SubTargetProvider {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/SubTargetProvider$NoSubTargets;", "Lorg/jetbrains/kotlin/konan/target/SubTargetProvider;", "()V", "availableSubTarget", "", "", "genericName", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class NoSubTargets implements SubTargetProvider {
        public static final NoSubTargets INSTANCE = new NoSubTargets();

        private NoSubTargets() {
        }

        @Override // org.jetbrains.kotlin.konan.target.SubTargetProvider
        public List<String> availableSubTarget(String genericName) {
            genericName.getClass();
            return CollectionsKt.emptyList();
        }
    }

    List<String> availableSubTarget(String genericName);
}

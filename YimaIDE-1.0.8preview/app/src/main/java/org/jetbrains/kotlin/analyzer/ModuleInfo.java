package org.jetbrains.kotlin.analyzer;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.ModuleCapability;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.resolve.PlatformDependentAnalyzerServices;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 #2\u00020\u0001:\u0002\"#J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\u000bH&J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00000\u0018H\u0016J\b\u0010 \u001a\u00020!H\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R&\u0010\u0019\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006$À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "", "name", "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "displayedName", "", "getDisplayedName", "()Ljava/lang/String;", "dependencies", "", "expectedBy", "getExpectedBy", "()Ljava/util/List;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "analyzerServices", "Lorg/jetbrains/kotlin/resolve/PlatformDependentAnalyzerServices;", "getAnalyzerServices", "()Lorg/jetbrains/kotlin/resolve/PlatformDependentAnalyzerServices;", "modulesWhoseInternalsAreVisible", "", "capabilities", "", "Lorg/jetbrains/kotlin/descriptors/ModuleCapability;", "getCapabilities", "()Ljava/util/Map;", "stableName", "getStableName", "dependencyOnBuiltIns", "Lorg/jetbrains/kotlin/analyzer/ModuleInfo$DependencyOnBuiltIns;", "DependencyOnBuiltIns", "Companion", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface ModuleInfo {
    public static final Companion Companion = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/analyzer/ModuleInfo$DependencyOnBuiltIns;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;I)V", "NONE", "AFTER_SDK", "LAST", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public enum DependencyOnBuiltIns {
        NONE,
        AFTER_SDK,
        LAST;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<DependencyOnBuiltIns> getEntries() {
            return $ENTRIES;
        }
    }

    List<ModuleInfo> dependencies();

    default DependencyOnBuiltIns dependencyOnBuiltIns() {
        return getAnalyzerServices().dependencyOnBuiltIns();
    }

    PlatformDependentAnalyzerServices getAnalyzerServices();

    default Map<ModuleCapability<?>, Object> getCapabilities() {
        return MapsKt.mapOf(TuplesKt.to(Companion.getCapability(), this));
    }

    default String getDisplayedName() {
        String strAsString = getName().asString();
        strAsString.getClass();
        return strAsString;
    }

    default List<ModuleInfo> getExpectedBy() {
        return CollectionsKt.emptyList();
    }

    Name getName();

    TargetPlatform getPlatform();

    default Name getStableName() {
        return null;
    }

    default Collection<ModuleInfo> modulesWhoseInternalsAreVisible() {
        return CollectionsKt.emptyList();
    }
}

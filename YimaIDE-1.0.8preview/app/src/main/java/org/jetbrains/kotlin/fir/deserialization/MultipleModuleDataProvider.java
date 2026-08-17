package org.jetbrains.kotlin.fir.deserialization;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/MultipleModuleDataProvider;", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "moduleDataWithFilters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirModuleData;", "Lorg/jetbrains/kotlin/fir/deserialization/LibraryPathFilter;", "regularDependenciesModuleData", "<init>", "(Ljava/util/Map;Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "getRegularDependenciesModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "allModuleData", Argument.Delimiters.none, "getAllModuleData", "()Ljava/util/Collection;", "getModuleData", ModuleXmlParser.PATH, "Ljava/nio/file/Path;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MultipleModuleDataProvider extends ModuleDataProvider {
    private final Map<FirModuleData, LibraryPathFilter> moduleDataWithFilters;
    private final FirModuleData regularDependenciesModuleData;

    /* JADX WARN: Multi-variable type inference failed */
    public MultipleModuleDataProvider(Map<FirModuleData, ? extends LibraryPathFilter> map, FirModuleData firModuleData) {
        map.getClass();
        firModuleData.getClass();
        this.moduleDataWithFilters = map;
        this.regularDependenciesModuleData = firModuleData;
        if (map.isEmpty()) {
            w01.a("ModuleDataProvider must contain at least one module data");
            throw null;
        }
        if (map.containsKey(getRegularDependenciesModuleData())) {
            return;
        }
        w01.a("moduleDataWithFilters should contin regularDependenciesModuleData");
        throw null;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider
    public Collection<FirModuleData> getAllModuleData() {
        return this.moduleDataWithFilters.keySet();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider
    public FirModuleData getModuleData(Path path) {
        Path pathNormalize = path != null ? path.normalize() : null;
        for (Map.Entry<FirModuleData, LibraryPathFilter> entry : this.moduleDataWithFilters.entrySet()) {
            FirModuleData key = entry.getKey();
            if (entry.getValue().accepts(pathNormalize)) {
                return key;
            }
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider
    public FirModuleData getRegularDependenciesModuleData() {
        return this.regularDependenciesModuleData;
    }
}

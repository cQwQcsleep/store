package org.jetbrains.kotlin.fir.deserialization;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000eH&J\u0018\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0016R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", Argument.Delimiters.none, "<init>", "()V", "allModuleData", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getAllModuleData", "()Ljava/util/Collection;", "regularDependenciesModuleData", "getRegularDependenciesModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", ModuleXmlParser.PATH, "Ljava/nio/file/Path;", "getModuleDataPaths", Argument.Delimiters.none, "moduleData", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ModuleDataProvider {
    public abstract Collection<FirModuleData> getAllModuleData();

    public abstract FirModuleData getModuleData(Path path);

    public Set<Path> getModuleDataPaths(FirModuleData moduleData) {
        moduleData.getClass();
        return null;
    }

    public abstract FirModuleData getRegularDependenciesModuleData();
}

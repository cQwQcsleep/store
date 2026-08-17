package org.jetbrains.kotlin.fir.deserialization;

import java.nio.file.Path;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/SingleModuleDataProvider;", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "allModuleData", Argument.Delimiters.none, "getAllModuleData", "()Ljava/util/Collection;", "regularDependenciesModuleData", "getRegularDependenciesModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", ModuleXmlParser.PATH, "Ljava/nio/file/Path;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SingleModuleDataProvider extends ModuleDataProvider {
    private final FirModuleData moduleData;

    public SingleModuleDataProvider(FirModuleData firModuleData) {
        firModuleData.getClass();
        this.moduleData = firModuleData;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider
    public Collection<FirModuleData> getAllModuleData() {
        return CollectionsKt.listOf(this.moduleData);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider
    public FirModuleData getModuleData(Path path) {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider
    /* JADX INFO: renamed from: getRegularDependenciesModuleData, reason: from getter */
    public FirModuleData getModuleData() {
        return this.moduleData;
    }
}

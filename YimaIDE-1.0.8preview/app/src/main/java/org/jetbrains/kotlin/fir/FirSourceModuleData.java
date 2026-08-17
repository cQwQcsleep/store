package org.jetbrains.kotlin.fir;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.container.DataStructuresKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSourceModuleData;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.platform.TargetPlatformKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u0004\u0018\u00010 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0011¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirSourceModuleData;", "Lorg/jetbrains/kotlin/fir/FirModuleData;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "dependencies", Argument.Delimiters.none, "dependsOnDependencies", "friendDependencies", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "isCommon", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/name/Name;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/platform/TargetPlatform;Z)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getDependencies", "()Ljava/util/List;", "getDependsOnDependencies", "getFriendDependencies", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "()Z", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "capabilities", "Lorg/jetbrains/kotlin/fir/FirModuleCapabilities;", "getCapabilities", "()Lorg/jetbrains/kotlin/fir/FirModuleCapabilities;", "stableModuleName", Argument.Delimiters.none, "getStableModuleName", "()Ljava/lang/String;", "allDependsOnDependencies", "getAllDependsOnDependencies", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSourceModuleData extends FirModuleData {
    private final List<FirModuleData> allDependsOnDependencies;
    private final List<FirModuleData> dependencies;
    private final List<FirModuleData> dependsOnDependencies;
    private final List<FirModuleData> friendDependencies;
    private final boolean isCommon;
    private final Name name;
    private final TargetPlatform platform;

    /* JADX WARN: Multi-variable type inference failed */
    public FirSourceModuleData(Name name, List<? extends FirModuleData> list, List<? extends FirModuleData> list2, List<? extends FirModuleData> list3, TargetPlatform targetPlatform, boolean z) {
        name.getClass();
        list.getClass();
        list2.getClass();
        list3.getClass();
        targetPlatform.getClass();
        this.name = name;
        this.dependencies = list;
        this.dependsOnDependencies = list2;
        this.friendDependencies = list3;
        this.platform = targetPlatform;
        this.isCommon = z;
        this.allDependsOnDependencies = DataStructuresKt.topologicalSort$default(getDependsOnDependencies(), false, new Function1() { // from class: de5
            public final Object invoke(Object obj) {
                return FirSourceModuleData.a((FirModuleData) obj);
            }
        }, 2, null);
    }

    public static Iterable a(FirModuleData firModuleData) {
        firModuleData.getClass();
        return firModuleData.getDependsOnDependencies();
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public List<FirModuleData> getAllDependsOnDependencies() {
        return this.allDependsOnDependencies;
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public FirModuleCapabilities getCapabilities() {
        return FirModuleCapabilities.INSTANCE.getEmpty();
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public List<FirModuleData> getDependencies() {
        return this.dependencies;
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public List<FirModuleData> getDependsOnDependencies() {
        return this.dependsOnDependencies;
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public List<FirModuleData> getFriendDependencies() {
        return this.friendDependencies;
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public TargetPlatform getPlatform() {
        return this.platform;
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public FirSession getSession() {
        FirSession boundSession = getBoundSession();
        if (boundSession != null) {
            return boundSession;
        }
        FirModuleDataKt.sessionNotBoundError(this);
        wq6.a();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public String getStableModuleName() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    /* JADX INFO: renamed from: isCommon, reason: from getter */
    public boolean getIsCommon() {
        return this.isCommon;
    }

    public /* synthetic */ FirSourceModuleData(Name name, List list, List list2, List list3, TargetPlatform targetPlatform, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(name, list, list2, list3, targetPlatform, (i & 32) != 0 ? TargetPlatformKt.isCommon(targetPlatform) : z);
    }
}

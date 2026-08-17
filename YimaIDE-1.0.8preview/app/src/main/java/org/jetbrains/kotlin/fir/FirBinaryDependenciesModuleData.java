package org.jetbrains.kotlin.fir;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\u0004\u0018\u00010\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirBinaryDependenciesModuleData;", "Lorg/jetbrains/kotlin/fir/FirModuleData;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "capabilities", "Lorg/jetbrains/kotlin/fir/FirModuleCapabilities;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/FirModuleCapabilities;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getCapabilities", "()Lorg/jetbrains/kotlin/fir/FirModuleCapabilities;", "dependencies", Argument.Delimiters.none, "getDependencies", "()Ljava/util/List;", "dependsOnDependencies", "getDependsOnDependencies", "allDependsOnDependencies", "getAllDependsOnDependencies", "friendDependencies", "getFriendDependencies", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "isCommon", Argument.Delimiters.none, "()Z", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "stableModuleName", Argument.Delimiters.none, "getStableModuleName", "()Ljava/lang/String;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirBinaryDependenciesModuleData extends FirModuleData {
    private final FirModuleCapabilities capabilities;
    private final Name name;

    public FirBinaryDependenciesModuleData(Name name, FirModuleCapabilities firModuleCapabilities) {
        name.getClass();
        firModuleCapabilities.getClass();
        this.name = name;
        this.capabilities = firModuleCapabilities;
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public List<FirModuleData> getAllDependsOnDependencies() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public FirModuleCapabilities getCapabilities() {
        return this.capabilities;
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public List<FirModuleData> getDependencies() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public List<FirModuleData> getDependsOnDependencies() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public List<FirModuleData> getFriendDependencies() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public Name getName() {
        return this.name;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.FirModuleData
    public TargetPlatform getPlatform() throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
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
    public boolean isCommon() {
        return false;
    }

    public /* synthetic */ FirBinaryDependenciesModuleData(Name name, FirModuleCapabilities firModuleCapabilities, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(name, (i & 2) != 0 ? FirModuleCapabilities.INSTANCE.getEmpty() : firModuleCapabilities);
    }
}

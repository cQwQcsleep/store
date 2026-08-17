package org.jetbrains.kotlin.fir;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.TargetPlatform;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010$\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u001eJ\n\u0010&\u001a\u00020'H\u0096\u0080\u0004R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00000\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00000\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000bR\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\"\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0012\u0010\"\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010!R\u0014\u0010(\u001a\u0004\u0018\u00010'X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u0018¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirModuleData;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "dependencies", Argument.Delimiters.none, "getDependencies", "()Ljava/util/List;", "dependsOnDependencies", "getDependsOnDependencies", "allDependsOnDependencies", "getAllDependsOnDependencies", "friendDependencies", "getFriendDependencies", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "isCommon", Argument.Delimiters.none, "()Z", "capabilities", "Lorg/jetbrains/kotlin/fir/FirModuleCapabilities;", "getCapabilities", "()Lorg/jetbrains/kotlin/fir/FirModuleCapabilities;", "value", "Lorg/jetbrains/kotlin/fir/FirSession;", "boundSession", "getBoundSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "session", "getSession", "bindSession", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "stableModuleName", "getStableModuleName", "()Ljava/lang/String;", "areRedeclarationsEquivalent", "getAreRedeclarationsEquivalent", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirModuleData implements FirSessionComponent {
    private FirSession boundSession;

    public final void bindSession(FirSession session) {
        session.getClass();
        if (this.boundSession == null) {
            this.boundSession = session;
        } else {
            w04.a("module data already bound to ", this);
        }
    }

    public abstract List<FirModuleData> getAllDependsOnDependencies();

    public boolean getAreRedeclarationsEquivalent() {
        return getSession().getKind() == FirSession.Kind.Library;
    }

    public final FirSession getBoundSession() {
        return this.boundSession;
    }

    public FirModuleCapabilities getCapabilities() {
        return FirModuleCapabilities.INSTANCE.getEmpty();
    }

    public abstract List<FirModuleData> getDependencies();

    public abstract List<FirModuleData> getDependsOnDependencies();

    public abstract List<FirModuleData> getFriendDependencies();

    public abstract Name getName();

    public abstract TargetPlatform getPlatform();

    public abstract FirSession getSession();

    public abstract String getStableModuleName();

    public abstract boolean isCommon();

    public String toString() {
        return "Module " + getName();
    }
}

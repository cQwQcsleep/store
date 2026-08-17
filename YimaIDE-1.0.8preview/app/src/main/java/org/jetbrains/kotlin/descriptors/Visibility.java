package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0019\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0005H&J\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0000H\u0016¢\u0006\u0002\u0010\u0013J\n\u0010\u0014\u001a\u00020\u0003H\u0086\u0080\u0004J\b\u0010\u0015\u001a\u00020\u0000H\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\t¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/Visibility;", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "isPublicAPI", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Z)V", "getName", "()Ljava/lang/String;", "()Z", "internalDisplayName", "getInternalDisplayName", "externalDisplayName", "getExternalDisplayName", "mustCheckInImports", "compareTo", Argument.Delimiters.none, "visibility", "(Lorg/jetbrains/kotlin/descriptors/Visibility;)Ljava/lang/Integer;", "toString", "normalize", "customEffectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "visibleFromPackage", "fromPackage", "Lorg/jetbrains/kotlin/name/FqName;", "myPackage", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class Visibility {
    private final boolean isPublicAPI;
    private final String name;

    public Visibility(String str, boolean z) {
        str.getClass();
        this.name = str;
        this.isPublicAPI = z;
    }

    public Integer compareTo(Visibility visibility) {
        visibility.getClass();
        return Visibilities.INSTANCE.compareLocal$org_jetbrains_kotlin_compiler_common(this, visibility);
    }

    public EffectiveVisibility customEffectiveVisibility() {
        return null;
    }

    public String getExternalDisplayName() {
        return getName();
    }

    /* JADX INFO: renamed from: getInternalDisplayName, reason: from getter */
    public String getName() {
        return this.name;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: isPublicAPI, reason: from getter */
    public final boolean getIsPublicAPI() {
        return this.isPublicAPI;
    }

    public abstract boolean mustCheckInImports();

    public Visibility normalize() {
        return this;
    }

    public final String toString() {
        return getName();
    }

    public boolean visibleFromPackage(FqName fromPackage, FqName myPackage) {
        fromPackage.getClass();
        myPackage.getClass();
        return true;
    }
}

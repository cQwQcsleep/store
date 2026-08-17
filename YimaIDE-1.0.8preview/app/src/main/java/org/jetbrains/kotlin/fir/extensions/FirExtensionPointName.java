package org.jetbrains.kotlin.fir.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\u0007J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", Argument.Delimiters.none, "(Ljava/lang/String;)V", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "component1", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class FirExtensionPointName {
    private final Name name;

    /* JADX WARN: Illegal instructions before constructor call */
    public FirExtensionPointName(String str) {
        str.getClass();
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        this(nameIdentifier);
    }

    public static /* synthetic */ FirExtensionPointName copy$default(FirExtensionPointName firExtensionPointName, Name name, int i, Object obj) {
        if ((i & 1) != 0) {
            name = firExtensionPointName.name;
        }
        return firExtensionPointName.copy(name);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Name getName() {
        return this.name;
    }

    public final FirExtensionPointName copy(Name name) {
        name.getClass();
        return new FirExtensionPointName(name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FirExtensionPointName) && Intrinsics.areEqual(this.name, ((FirExtensionPointName) other).name);
    }

    public final Name getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return "FirExtensionPointName(name=" + this.name + ')';
    }

    public FirExtensionPointName(Name name) {
        name.getClass();
        this.name = name;
    }
}

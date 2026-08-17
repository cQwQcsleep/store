package org.jetbrains.kotlin.cli.common.config;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/config/KotlinSourceRoot;", "Lorg/jetbrains/kotlin/cli/common/config/ContentRoot;", ModuleXmlParser.PATH, Argument.Delimiters.none, "isCommon", Argument.Delimiters.none, "hmppModuleName", "<init>", "(Ljava/lang/String;ZLjava/lang/String;)V", "getPath", "()Ljava/lang/String;", "()Z", "getHmppModuleName", "component1", "component2", "component3", "copy", "equals", "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class KotlinSourceRoot implements ContentRoot {
    private final String hmppModuleName;
    private final boolean isCommon;
    private final String path;

    public KotlinSourceRoot(String str, boolean z, String str2) {
        str.getClass();
        this.path = str;
        this.isCommon = z;
        this.hmppModuleName = str2;
    }

    public static /* synthetic */ KotlinSourceRoot copy$default(KotlinSourceRoot kotlinSourceRoot, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = kotlinSourceRoot.path;
        }
        if ((i & 2) != 0) {
            z = kotlinSourceRoot.isCommon;
        }
        if ((i & 4) != 0) {
            str2 = kotlinSourceRoot.hmppModuleName;
        }
        return kotlinSourceRoot.copy(str, z, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsCommon() {
        return this.isCommon;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getHmppModuleName() {
        return this.hmppModuleName;
    }

    public final KotlinSourceRoot copy(String path, boolean isCommon, String hmppModuleName) {
        path.getClass();
        return new KotlinSourceRoot(path, isCommon, hmppModuleName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KotlinSourceRoot)) {
            return false;
        }
        KotlinSourceRoot kotlinSourceRoot = (KotlinSourceRoot) other;
        return Intrinsics.areEqual(this.path, kotlinSourceRoot.path) && this.isCommon == kotlinSourceRoot.isCommon && Intrinsics.areEqual(this.hmppModuleName, kotlinSourceRoot.hmppModuleName);
    }

    public final String getHmppModuleName() {
        return this.hmppModuleName;
    }

    public final String getPath() {
        return this.path;
    }

    public int hashCode() {
        int iHashCode = ((this.path.hashCode() * 31) + Boolean.hashCode(this.isCommon)) * 31;
        String str = this.hmppModuleName;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isCommon() {
        return this.isCommon;
    }

    public String toString() {
        return "KotlinSourceRoot(path=" + this.path + ", isCommon=" + this.isCommon + ", hmppModuleName=" + this.hmppModuleName + ')';
    }
}

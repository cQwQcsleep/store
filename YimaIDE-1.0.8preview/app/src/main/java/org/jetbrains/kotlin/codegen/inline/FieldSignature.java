package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/FieldSignature;", Argument.Delimiters.none, "owner", Argument.Delimiters.none, ModuleXmlParser.NAME, "desc", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getOwner", "()Ljava/lang/String;", "getName", "getDesc", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class FieldSignature {
    private final String desc;
    private final String name;
    private final String owner;

    public FieldSignature(String str, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.owner = str;
        this.name = str2;
        this.desc = str3;
    }

    public static /* synthetic */ FieldSignature copy$default(FieldSignature fieldSignature, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = fieldSignature.owner;
        }
        if ((i & 2) != 0) {
            str2 = fieldSignature.name;
        }
        if ((i & 4) != 0) {
            str3 = fieldSignature.desc;
        }
        return fieldSignature.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOwner() {
        return this.owner;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    public final FieldSignature copy(String owner, String name, String desc) {
        owner.getClass();
        name.getClass();
        desc.getClass();
        return new FieldSignature(owner, name, desc);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FieldSignature)) {
            return false;
        }
        FieldSignature fieldSignature = (FieldSignature) other;
        return Intrinsics.areEqual(this.owner, fieldSignature.owner) && Intrinsics.areEqual(this.name, fieldSignature.name) && Intrinsics.areEqual(this.desc, fieldSignature.desc);
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getName() {
        return this.name;
    }

    public final String getOwner() {
        return this.owner;
    }

    public int hashCode() {
        return (((this.owner.hashCode() * 31) + this.name.hashCode()) * 31) + this.desc.hashCode();
    }

    public String toString() {
        return "FieldSignature(owner=" + this.owner + ", name=" + this.name + ", desc=" + this.desc + ')';
    }
}

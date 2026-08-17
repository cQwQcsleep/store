package org.jetbrains.kotlin.library.abi;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.ir.BuiltInOperatorNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\b\u0087\b\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001bB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0000H\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\u0010\u0010\u0010\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0011\u0010\bJ\u0010\u0010\u0012\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b\u0013\u0010\bJ$\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\r\u001a\u0004\u0018\u00010\u0019HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\fHÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\bÊ\u0001\u0002\b\u001d¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/AbiQualifiedName;", "", "packageName", "Lorg/jetbrains/kotlin/library/abi/AbiCompoundName;", "relativeName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getPackageName-l9-qHIQ", "()Ljava/lang/String;", "Ljava/lang/String;", "getRelativeName-l9-qHIQ", BuiltInOperatorNames.COMPARE_TO, "", "other", "toString", "", "component1", "component1-l9-qHIQ", "component2", "component2-l9-qHIQ", "copy", "copy-z9QQr6Q", "(Ljava/lang/String;Ljava/lang/String;)Lorg/jetbrains/kotlin/library/abi/AbiQualifiedName;", "equals", "", "", "hashCode", "Companion", "org.jetbrains.kotlin:kotlin-util-klib-abi", "Lorg/jetbrains/kotlin/library/abi/ExperimentalLibraryAbiReader;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@ExperimentalLibraryAbiReader
public final /* data */ class AbiQualifiedName implements Comparable<AbiQualifiedName> {
    public static final char SEPARATOR = '/';
    private final String packageName;
    private final String relativeName;

    private AbiQualifiedName(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.packageName = str;
        this.relativeName = str2;
        if (str2.length() > 0) {
            return;
        }
        w01.a("Empty relative name");
        throw null;
    }

    /* JADX INFO: renamed from: copy-z9QQr6Q$default, reason: not valid java name */
    public static /* synthetic */ AbiQualifiedName m606copyz9QQr6Q$default(AbiQualifiedName abiQualifiedName, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = abiQualifiedName.packageName;
        }
        if ((i & 2) != 0) {
            str2 = abiQualifiedName.relativeName;
        }
        return abiQualifiedName.m609copyz9QQr6Q(str, str2);
    }

    @Override // java.lang.Comparable
    public int compareTo(AbiQualifiedName other) {
        other.getClass();
        int iM594compareTo6OaPNJE = AbiCompoundName.m594compareTo6OaPNJE(this.packageName, other.packageName);
        return iM594compareTo6OaPNJE != 0 ? iM594compareTo6OaPNJE : AbiCompoundName.m594compareTo6OaPNJE(this.relativeName, other.relativeName);
    }

    /* JADX INFO: renamed from: component1-l9-qHIQ, reason: not valid java name and from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component2-l9-qHIQ, reason: not valid java name and from getter */
    public final String getRelativeName() {
        return this.relativeName;
    }

    /* JADX INFO: renamed from: copy-z9QQr6Q, reason: not valid java name */
    public final AbiQualifiedName m609copyz9QQr6Q(String packageName, String relativeName) {
        packageName.getClass();
        relativeName.getClass();
        return new AbiQualifiedName(packageName, relativeName, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AbiQualifiedName)) {
            return false;
        }
        AbiQualifiedName abiQualifiedName = (AbiQualifiedName) other;
        return AbiCompoundName.m597equalsimpl0(this.packageName, abiQualifiedName.packageName) && AbiCompoundName.m597equalsimpl0(this.relativeName, abiQualifiedName.relativeName);
    }

    /* JADX INFO: renamed from: getPackageName-l9-qHIQ, reason: not valid java name */
    public final String m610getPackageNamel9qHIQ() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: getRelativeName-l9-qHIQ, reason: not valid java name */
    public final String m611getRelativeNamel9qHIQ() {
        return this.relativeName;
    }

    public int hashCode() {
        return (AbiCompoundName.m601hashCodeimpl(this.packageName) * 31) + AbiCompoundName.m601hashCodeimpl(this.relativeName);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) AbiCompoundName.m603toStringimpl(this.packageName));
        sb.append(SEPARATOR);
        sb.append((Object) AbiCompoundName.m603toStringimpl(this.relativeName));
        return sb.toString();
    }

    public /* synthetic */ AbiQualifiedName(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }
}

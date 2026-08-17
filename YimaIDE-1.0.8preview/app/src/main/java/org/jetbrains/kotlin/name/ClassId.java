package org.jetbrains.kotlin.name;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;
import org.jetbrains.kotlin.library.abi.AbiQualifiedName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000 /2\u00020\u0001:\u0001/B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0007\u0010\u000bJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\nJ\u0006\u0010 \u001a\u00020\u0003J\u000e\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\nJ\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020$J\n\u0010&\u001a\u00020$H\u0096\u0080\u0004J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J'\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010+\u001a\u00020\u00062\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010-\u001a\u00020.HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0005\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u00008F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\u001b\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u0011\u0010\u001d\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0011¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/name/ClassId;", "", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "relativeClassName", "isLocal", "", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/name/FqName;Z)V", "topLevelName", "Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/name/Name;)V", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getRelativeClassName", "isLocal$annotations", "()V", "()Z", "parentClassId", "getParentClassId$annotations", "getParentClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "shortClassName", "getShortClassName", "()Lorg/jetbrains/kotlin/name/Name;", "outerClassId", "getOuterClassId", "outermostClassId", "getOutermostClassId", "isNestedClass", "createNestedClassId", "name", "asSingleFqName", "startsWith", "segment", "asString", "", "asFqNameString", "toString", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "Companion", "org.jetbrains.kotlin:names"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ClassId {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean isLocal;
    private final FqName packageFqName;
    private final FqName relativeClassName;

    public ClassId(FqName fqName, FqName fqName2, boolean z) {
        fqName.getClass();
        fqName2.getClass();
        this.packageFqName = fqName;
        this.relativeClassName = fqName2;
        this.isLocal = z;
        fqName2.isRoot();
    }

    private static final String asString$escapeSlashes(FqName fqName) {
        String strAsString = fqName.asString();
        if (!StringsKt.contains$default(strAsString, AbiQualifiedName.SEPARATOR, false, 2, (Object) null)) {
            return strAsString;
        }
        return "`" + strAsString + '`';
    }

    public static /* synthetic */ ClassId copy$default(ClassId classId, FqName fqName, FqName fqName2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            fqName = classId.packageFqName;
        }
        if ((i & 2) != 0) {
            fqName2 = classId.relativeClassName;
        }
        if ((i & 4) != 0) {
            z = classId.isLocal;
        }
        return classId.copy(fqName, fqName2, z);
    }

    @JvmStatic
    public static final ClassId fromString(String str) {
        return INSTANCE.fromString(str);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use `outerClassId` instead. It is semantically equivalent.", replaceWith = @ReplaceWith(expression = "outerClassId", imports = {}))
    public static /* synthetic */ void getParentClassId$annotations() {
    }

    @ClassIdBasedLocality
    public static /* synthetic */ void isLocal$annotations() {
    }

    @JvmStatic
    public static final ClassId topLevel(FqName fqName) {
        return INSTANCE.topLevel(fqName);
    }

    public final String asFqNameString() {
        if (this.packageFqName.isRoot()) {
            return this.relativeClassName.asString();
        }
        return this.packageFqName.asString() + "." + this.relativeClassName.asString();
    }

    public final FqName asSingleFqName() {
        if (this.packageFqName.isRoot()) {
            return this.relativeClassName;
        }
        return new FqName(this.packageFqName.asString() + AbiCompoundName.SEPARATOR + this.relativeClassName.asString());
    }

    public final String asString() {
        if (this.packageFqName.isRoot()) {
            return asString$escapeSlashes(this.relativeClassName);
        }
        return StringsKt.replace$default(this.packageFqName.asString(), AbiCompoundName.SEPARATOR, AbiQualifiedName.SEPARATOR, false, 4, (Object) null) + "/" + asString$escapeSlashes(this.relativeClassName);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FqName getRelativeClassName() {
        return this.relativeClassName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsLocal() {
        return this.isLocal;
    }

    public final ClassId copy(FqName packageFqName, FqName relativeClassName, boolean isLocal) {
        packageFqName.getClass();
        relativeClassName.getClass();
        return new ClassId(packageFqName, relativeClassName, isLocal);
    }

    public final ClassId createNestedClassId(Name name) {
        name.getClass();
        return new ClassId(this.packageFqName, this.relativeClassName.child(name), this.isLocal);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClassId)) {
            return false;
        }
        ClassId classId = (ClassId) other;
        return Intrinsics.areEqual(this.packageFqName, classId.packageFqName) && Intrinsics.areEqual(this.relativeClassName, classId.relativeClassName) && this.isLocal == classId.isLocal;
    }

    public final ClassId getOuterClassId() {
        FqName fqNameParent = this.relativeClassName.parent();
        if (fqNameParent.isRoot()) {
            return null;
        }
        return new ClassId(this.packageFqName, fqNameParent, this.isLocal);
    }

    public final ClassId getOutermostClassId() {
        FqName fqNameParent = this.relativeClassName;
        while (!fqNameParent.parent().isRoot()) {
            fqNameParent = fqNameParent.parent();
        }
        return new ClassId(this.packageFqName, fqNameParent, false);
    }

    public final FqName getPackageFqName() {
        return this.packageFqName;
    }

    public final ClassId getParentClassId() {
        if (isNestedClass()) {
            return new ClassId(this.packageFqName, this.relativeClassName.parent(), this.isLocal);
        }
        return null;
    }

    public final FqName getRelativeClassName() {
        return this.relativeClassName;
    }

    public final Name getShortClassName() {
        return this.relativeClassName.shortName();
    }

    public int hashCode() {
        return (((this.packageFqName.hashCode() * 31) + this.relativeClassName.hashCode()) * 31) + Boolean.hashCode(this.isLocal);
    }

    public final boolean isLocal() {
        return this.isLocal;
    }

    public final boolean isNestedClass() {
        return !this.relativeClassName.parent().isRoot();
    }

    public final boolean startsWith(Name segment) {
        segment.getClass();
        return this.packageFqName.startsWith(segment);
    }

    public String toString() {
        if (!this.packageFqName.isRoot()) {
            return asString();
        }
        return "/" + asString();
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u001a\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/name/ClassId$Companion;", "", "<init>", "()V", "topLevel", "Lorg/jetbrains/kotlin/name/ClassId;", "topLevelFqName", "Lorg/jetbrains/kotlin/name/FqName;", "fromString", "string", "", "isLocal", "", "org.jetbrains.kotlin:names"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ ClassId fromString$default(Companion companion, String str, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.fromString(str, z);
        }

        @JvmStatic
        public final ClassId fromString(String string, boolean isLocal) {
            String strReplace$default;
            String strReplace$default2;
            string.getClass();
            int iIndexOf$default = StringsKt.indexOf$default(string, '`', 0, false, 6, (Object) null);
            if (iIndexOf$default == -1) {
                iIndexOf$default = string.length();
            }
            int iLastIndexOf$default = StringsKt.lastIndexOf$default(string, "/", iIndexOf$default, false, 4, (Object) null);
            if (iLastIndexOf$default == -1) {
                strReplace$default2 = StringsKt.replace$default(string, "`", "", false, 4, (Object) null);
                strReplace$default = "";
            } else {
                strReplace$default = StringsKt.replace$default(string.substring(0, iLastIndexOf$default), AbiQualifiedName.SEPARATOR, AbiCompoundName.SEPARATOR, false, 4, (Object) null);
                strReplace$default2 = StringsKt.replace$default(string.substring(iLastIndexOf$default + 1), "`", "", false, 4, (Object) null);
            }
            return new ClassId(new FqName(strReplace$default), new FqName(strReplace$default2), isLocal);
        }

        @JvmStatic
        public final ClassId topLevel(FqName topLevelFqName) {
            topLevelFqName.getClass();
            return new ClassId(topLevelFqName.parent(), topLevelFqName.shortName());
        }

        private Companion() {
        }

        @JvmStatic
        public final ClassId fromString(String str) {
            str.getClass();
            return fromString$default(this, str, false, 2, null);
        }
    }

    @JvmStatic
    public static final ClassId fromString(String str, boolean z) {
        return INSTANCE.fromString(str, z);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ClassId(FqName fqName, Name name) {
        this(fqName, FqName.INSTANCE.topLevel(name), false);
        fqName.getClass();
        name.getClass();
    }
}

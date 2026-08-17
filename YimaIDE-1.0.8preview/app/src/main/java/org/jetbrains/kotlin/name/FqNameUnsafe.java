package org.jetbrains.kotlin.name;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;
import org.jetbrains.kotlin.library.abi.AbiQualifiedName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\b\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0019\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\bB!\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0000\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0006\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0006\u0010\u0011\u001a\u00020\u0003J\u0006\u0010\u0015\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\u0000J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u000bJ\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\u0019\u001a\u00020\u000bJ\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001bJ\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u000bJ\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0000J\n\u0010\u001f\u001a\u00020\u0003H\u0096\u0080\u0004J\u0014\u0010 \u001a\u00020\u00132\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010!\u001a\u00020\u0010H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014R\u0011\u0010\u0016\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "", "fqName", "", "safe", "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/name/FqName;)V", "(Ljava/lang/String;)V", "parent", "shortName", "Lorg/jetbrains/kotlin/name/Name;", "(Ljava/lang/String;Lorg/jetbrains/kotlin/name/FqNameUnsafe;Lorg/jetbrains/kotlin/name/Name;)V", "compute", "", "indexOfLastDotWithBackticksSupport", "", "asString", "isSafe", "", "()Z", "toSafe", "isRoot", "child", "name", "shortNameOrSpecial", "pathSegments", "", "startsWith", "segment", "other", "toString", "equals", "hashCode", "Companion", "org.jetbrains.kotlin:names"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FqNameUnsafe {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Name ROOT_NAME;
    private static final Pattern SPLIT_BY_DOTS;
    private final String fqName;
    private transient FqNameUnsafe parent;
    private transient FqName safe;
    private transient Name shortName;

    static {
        Name nameSpecial = Name.special("<root>");
        nameSpecial.getClass();
        ROOT_NAME = nameSpecial;
        Pattern patternCompile = Pattern.compile("\\.");
        patternCompile.getClass();
        SPLIT_BY_DOTS = patternCompile;
    }

    public FqNameUnsafe(String str, FqName fqName) {
        str.getClass();
        fqName.getClass();
        this.fqName = str;
        this.safe = fqName;
    }

    private final void compute() {
        int iIndexOfLastDotWithBackticksSupport = indexOfLastDotWithBackticksSupport(this.fqName);
        String str = this.fqName;
        if (iIndexOfLastDotWithBackticksSupport >= 0) {
            this.shortName = Name.guessByFirstCharacter(str.substring(iIndexOfLastDotWithBackticksSupport + 1));
            this.parent = new FqNameUnsafe(this.fqName.substring(0, iIndexOfLastDotWithBackticksSupport));
        } else {
            this.shortName = Name.guessByFirstCharacter(str);
            this.parent = FqName.ROOT.getFqName();
        }
    }

    private final int indexOfLastDotWithBackticksSupport(String fqName) {
        int length = fqName.length() - 1;
        boolean z = false;
        while (length >= 0) {
            char cCharAt = fqName.charAt(length);
            if (cCharAt == '.' && !z) {
                return length;
            }
            if (cCharAt == '`') {
                z = !z;
            } else if (cCharAt == '\\') {
                length--;
            }
            length--;
        }
        return -1;
    }

    @JvmStatic
    public static final boolean isValid(String str) {
        return INSTANCE.isValid(str);
    }

    private static final List<Name> pathSegments$collectSegmentsOf(FqNameUnsafe fqNameUnsafe) {
        if (fqNameUnsafe.isRoot()) {
            return new ArrayList();
        }
        List<Name> listPathSegments$collectSegmentsOf = pathSegments$collectSegmentsOf(fqNameUnsafe.parent());
        listPathSegments$collectSegmentsOf.add(fqNameUnsafe.shortName());
        return listPathSegments$collectSegmentsOf;
    }

    @JvmStatic
    public static final FqNameUnsafe topLevel(Name name) {
        return INSTANCE.topLevel(name);
    }

    /* JADX INFO: renamed from: asString, reason: from getter */
    public final String getFqName() {
        return this.fqName;
    }

    public final FqNameUnsafe child(Name name) {
        String strAsString;
        name.getClass();
        if (isRoot()) {
            strAsString = name.asString();
        } else {
            strAsString = this.fqName + AbiCompoundName.SEPARATOR + name.asString();
        }
        strAsString.getClass();
        return new FqNameUnsafe(strAsString, this, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FqNameUnsafe) && Intrinsics.areEqual(this.fqName, ((FqNameUnsafe) other).fqName);
    }

    public int hashCode() {
        return this.fqName.hashCode();
    }

    public final boolean isRoot() {
        return this.fqName.length() == 0;
    }

    public final boolean isSafe() {
        return this.safe != null || StringsKt.indexOf$default(getFqName(), '<', 0, false, 6, (Object) null) < 0;
    }

    public final FqNameUnsafe parent() {
        FqNameUnsafe fqNameUnsafe = this.parent;
        if (fqNameUnsafe != null) {
            return fqNameUnsafe;
        }
        if (isRoot()) {
            k2d.a("root");
            return null;
        }
        compute();
        FqNameUnsafe fqNameUnsafe2 = this.parent;
        fqNameUnsafe2.getClass();
        return fqNameUnsafe2;
    }

    public final List<Name> pathSegments() {
        return pathSegments$collectSegmentsOf(this);
    }

    public final Name shortName() {
        Name name = this.shortName;
        if (name != null) {
            return name;
        }
        if (isRoot()) {
            k2d.a("root");
            return null;
        }
        compute();
        Name name2 = this.shortName;
        name2.getClass();
        return name2;
    }

    public final Name shortNameOrSpecial() {
        return isRoot() ? ROOT_NAME : shortName();
    }

    public final boolean startsWith(Name segment) {
        segment.getClass();
        if (isRoot()) {
            return false;
        }
        int iIndexOf$default = StringsKt.indexOf$default(this.fqName, AbiCompoundName.SEPARATOR, 0, false, 6, (Object) null);
        if (iIndexOf$default == -1) {
            iIndexOf$default = this.fqName.length();
        }
        int i = iIndexOf$default;
        String strAsString = segment.asString();
        strAsString.getClass();
        return i == strAsString.length() && StringsKt.regionMatches$default(this.fqName, 0, strAsString, 0, i, false, 16, (Object) null);
    }

    public final FqName toSafe() {
        FqName fqName = this.safe;
        if (fqName != null) {
            return fqName;
        }
        FqName fqName2 = new FqName(this);
        this.safe = fqName2;
        return fqName2;
    }

    public String toString() {
        if (!isRoot()) {
            return this.fqName;
        }
        String strAsString = ROOT_NAME.asString();
        strAsString.getClass();
        return strAsString;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/name/FqNameUnsafe$Companion;", "", "<init>", "()V", "ROOT_NAME", "Lorg/jetbrains/kotlin/name/Name;", "SPLIT_BY_DOTS", "Ljava/util/regex/Pattern;", "isValid", "", "qualifiedName", "", "topLevel", "Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "shortName", "org.jetbrains.kotlin:names"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final boolean isValid(String qualifiedName) {
            return qualifiedName != null && StringsKt.indexOf$default(qualifiedName, AbiQualifiedName.SEPARATOR, 0, false, 6, (Object) null) < 0 && StringsKt.indexOf$default(qualifiedName, '*', 0, false, 6, (Object) null) < 0;
        }

        @JvmStatic
        public final FqNameUnsafe topLevel(Name shortName) {
            shortName.getClass();
            String strAsString = shortName.asString();
            strAsString.getClass();
            return new FqNameUnsafe(strAsString, FqName.ROOT.getFqName(), shortName, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ FqNameUnsafe(String str, FqNameUnsafe fqNameUnsafe, Name name, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, fqNameUnsafe, name);
    }

    public FqNameUnsafe(String str) {
        str.getClass();
        this.fqName = str;
    }

    private FqNameUnsafe(String str, FqNameUnsafe fqNameUnsafe, Name name) {
        this.fqName = str;
        this.parent = fqNameUnsafe;
        this.shortName = name;
    }

    public final boolean startsWith(FqNameUnsafe other) {
        int length;
        int length2;
        other.getClass();
        if (!isRoot() && (length = this.fqName.length()) >= (length2 = other.fqName.length())) {
            return (length == length2 || this.fqName.charAt(length2) == '.') && StringsKt.regionMatches$default(this.fqName, 0, other.fqName, 0, length2, false, 16, (Object) null);
        }
        return false;
    }
}

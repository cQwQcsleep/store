package org.jetbrains.kotlin.name;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\u0007B\u0019\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0000¢\u0006\u0004\b\u0004\u0010\tJ\u0006\u0010\n\u001a\u00020\u0003J\u0006\u0010\u000b\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0000J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0015J\u000e\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0011J\u000e\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0000J\n\u0010\u0019\u001a\u00020\u0003H\u0096\u0080\u0004J\u0014\u0010\u001a\u001a\u00020\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u001b\u001a\u00020\u001cH\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000e¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/name/FqName;", "", "fqName", "", "<init>", "(Ljava/lang/String;)V", "Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "(Lorg/jetbrains/kotlin/name/FqNameUnsafe;)V", "parent", "(Lorg/jetbrains/kotlin/name/FqNameUnsafe;Lorg/jetbrains/kotlin/name/FqName;)V", "asString", "toUnsafe", "isRoot", "", "()Z", "child", "name", "Lorg/jetbrains/kotlin/name/Name;", "shortName", "shortNameOrSpecial", "pathSegments", "", "startsWith", "segment", "other", "toString", "equals", "hashCode", "", "Companion", "org.jetbrains.kotlin:names"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FqName {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final FqName ROOT = new FqName("");
    private final FqNameUnsafe fqName;
    private transient FqName parent;

    public FqName(String str) {
        str.getClass();
        this.fqName = new FqNameUnsafe(str, this);
    }

    @JvmStatic
    public static final FqName fromSegments(List<String> list) {
        return INSTANCE.fromSegments(list);
    }

    @JvmStatic
    public static final FqName topLevel(Name name) {
        return INSTANCE.topLevel(name);
    }

    public final String asString() {
        return this.fqName.getFqName();
    }

    public final FqName child(Name name) {
        name.getClass();
        return new FqName(this.fqName.child(name), this);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FqName) && Intrinsics.areEqual(this.fqName, ((FqName) other).fqName);
    }

    public int hashCode() {
        return this.fqName.hashCode();
    }

    public final boolean isRoot() {
        return this.fqName.isRoot();
    }

    public final FqName parent() {
        FqName fqName = this.parent;
        if (fqName != null) {
            return fqName;
        }
        if (isRoot()) {
            k2d.a("root");
            return null;
        }
        FqName fqName2 = new FqName(this.fqName.parent());
        this.parent = fqName2;
        return fqName2;
    }

    public final List<Name> pathSegments() {
        return this.fqName.pathSegments();
    }

    public final Name shortName() {
        return this.fqName.shortName();
    }

    public final Name shortNameOrSpecial() {
        return this.fqName.shortNameOrSpecial();
    }

    public final boolean startsWith(FqName other) {
        other.getClass();
        return this.fqName.startsWith(other.fqName);
    }

    public String toString() {
        return this.fqName.toString();
    }

    /* JADX INFO: renamed from: toUnsafe, reason: from getter */
    public final FqNameUnsafe getFqName() {
        return this.fqName;
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0007J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u0010\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/name/FqName$Companion;", "", "<init>", "()V", "fromSegments", "Lorg/jetbrains/kotlin/name/FqName;", "names", "", "", "ROOT", "topLevel", "shortName", "Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:names"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final FqName fromSegments(List<String> names) {
            names.getClass();
            return new FqName(CollectionsKt.joinToString$default(names, ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        }

        @JvmStatic
        public final FqName topLevel(Name shortName) {
            shortName.getClass();
            return new FqName(FqNameUnsafe.INSTANCE.topLevel(shortName));
        }

        private Companion() {
        }
    }

    public final boolean startsWith(Name segment) {
        segment.getClass();
        return this.fqName.startsWith(segment);
    }

    public FqName(FqNameUnsafe fqNameUnsafe) {
        fqNameUnsafe.getClass();
        this.fqName = fqNameUnsafe;
    }

    private FqName(FqNameUnsafe fqNameUnsafe, FqName fqName) {
        this.fqName = fqNameUnsafe;
        this.parent = fqName;
    }
}

package org.jetbrains.kotlin.incremental;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u000b\f\r\u000eB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0014J\n\u0010\n\u001a\u00020\tH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0003\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangeInfo;", "", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;)V", "getFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "toStringProperties", "", "toString", "MembersChanged", "Removed", "SignatureChanged", "ParentsChanged", "Lorg/jetbrains/kotlin/incremental/ChangeInfo$MembersChanged;", "Lorg/jetbrains/kotlin/incremental/ChangeInfo$ParentsChanged;", "Lorg/jetbrains/kotlin/incremental/ChangeInfo$SignatureChanged;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class ChangeInfo {
    private final FqName fqName;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000b\u001a\u00020\u0006H\u0014R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangeInfo$MembersChanged;", "Lorg/jetbrains/kotlin/incremental/ChangeInfo;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "names", "", "", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Ljava/util/Collection;)V", "getNames", "()Ljava/util/Collection;", "toStringProperties", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class MembersChanged extends ChangeInfo {
        private final Collection<String> names;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MembersChanged(FqName fqName, Collection<String> collection) {
            super(fqName, null);
            fqName.getClass();
            collection.getClass();
            this.names = collection;
        }

        public final Collection<String> getNames() {
            return this.names;
        }

        @Override // org.jetbrains.kotlin.incremental.ChangeInfo
        public String toStringProperties() {
            return super.toStringProperties() + ", names = " + this.names;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangeInfo$ParentsChanged;", "Lorg/jetbrains/kotlin/incremental/ChangeInfo;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "parentsChanged", "", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Ljava/util/Collection;)V", "getParentsChanged", "()Ljava/util/Collection;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ParentsChanged extends ChangeInfo {
        private final Collection<FqName> parentsChanged;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ParentsChanged(FqName fqName, Collection<FqName> collection) {
            super(fqName, null);
            fqName.getClass();
            collection.getClass();
            this.parentsChanged = collection;
        }

        public final Collection<FqName> getParentsChanged() {
            return this.parentsChanged;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangeInfo$Removed;", "Lorg/jetbrains/kotlin/incremental/ChangeInfo$MembersChanged;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "names", "", "", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Ljava/util/Collection;)V", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Removed extends MembersChanged {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Removed(FqName fqName, Collection<String> collection) {
            super(fqName, collection);
            fqName.getClass();
            collection.getClass();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ChangeInfo$SignatureChanged;", "Lorg/jetbrains/kotlin/incremental/ChangeInfo;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "areSubclassesAffected", "", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Z)V", "getAreSubclassesAffected", "()Z", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SignatureChanged extends ChangeInfo {
        private final boolean areSubclassesAffected;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SignatureChanged(FqName fqName, boolean z) {
            super(fqName, null);
            fqName.getClass();
            this.areSubclassesAffected = z;
        }

        public final boolean getAreSubclassesAffected() {
            return this.areSubclassesAffected;
        }
    }

    private ChangeInfo(FqName fqName) {
        this.fqName = fqName;
    }

    public final FqName getFqName() {
        return this.fqName;
    }

    public String toString() {
        return getClass().getSimpleName() + '(' + toStringProperties() + ')';
    }

    public String toStringProperties() {
        return "fqName = " + this.fqName;
    }

    public /* synthetic */ ChangeInfo(FqName fqName, DefaultConstructorMarker defaultConstructorMarker) {
        this(fqName);
    }
}

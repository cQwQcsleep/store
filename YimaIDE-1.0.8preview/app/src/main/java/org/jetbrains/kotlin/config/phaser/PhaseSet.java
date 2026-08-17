package org.jetbrains.kotlin.config.phaser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u000b\f\rB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007j\u0002`\bH¦\u0002J\u0011\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H¦\u0002\u0082\u0001\u0003\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/config/phaser/PhaseSet;", Argument.Delimiters.none, "<init>", "()V", "contains", Argument.Delimiters.none, "phase", "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Lorg/jetbrains/kotlin/config/phaser/AnyNamedPhase;", "plus", "phaseSet", "Enum", "All", "Empty", "Lorg/jetbrains/kotlin/config/phaser/PhaseSet$All;", "Lorg/jetbrains/kotlin/config/phaser/PhaseSet$Empty;", "Lorg/jetbrains/kotlin/config/phaser/PhaseSet$Enum;", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class PhaseSet {

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007j\u0002`\bH\u0096\u0002J\u0011\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0096\u0002¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/config/phaser/PhaseSet$All;", "Lorg/jetbrains/kotlin/config/phaser/PhaseSet;", "<init>", "()V", "contains", Argument.Delimiters.none, "phase", "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Lorg/jetbrains/kotlin/config/phaser/AnyNamedPhase;", "plus", "phaseSet", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class All extends PhaseSet {
        public static final All INSTANCE = new All();

        private All() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.config.phaser.PhaseSet
        public boolean contains(NamedCompilerPhase<?, ?, ?> phase) {
            phase.getClass();
            return true;
        }

        @Override // org.jetbrains.kotlin.config.phaser.PhaseSet
        public PhaseSet plus(PhaseSet phaseSet) {
            phaseSet.getClass();
            return INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007j\u0002`\bH\u0096\u0002J\u0011\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0096\u0002¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/config/phaser/PhaseSet$Empty;", "Lorg/jetbrains/kotlin/config/phaser/PhaseSet;", "<init>", "()V", "contains", Argument.Delimiters.none, "phase", "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Lorg/jetbrains/kotlin/config/phaser/AnyNamedPhase;", "plus", "phaseSet", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Empty extends PhaseSet {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.config.phaser.PhaseSet
        public boolean contains(NamedCompilerPhase<?, ?, ?> phase) {
            phase.getClass();
            return false;
        }

        @Override // org.jetbrains.kotlin.config.phaser.PhaseSet
        public PhaseSet plus(PhaseSet phaseSet) {
            phaseSet.getClass();
            return phaseSet;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\u0007\u001a\u00020\b2\u0016\u0010\t\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\nj\u0002`\u000bH\u0096\u0002J\u0011\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0096\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/config/phaser/PhaseSet$Enum;", "Lorg/jetbrains/kotlin/config/phaser/PhaseSet;", "phases", Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "(Ljava/util/Set;)V", "contains", Argument.Delimiters.none, "phase", "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Lorg/jetbrains/kotlin/config/phaser/AnyNamedPhase;", "plus", "phaseSet", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Enum extends PhaseSet {
        private final Set<String> phases;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Enum(Set<String> set) {
            super(null);
            set.getClass();
            Set<String> set2 = set;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set2, 10));
            Iterator<T> it = set2.iterator();
            while (it.hasNext()) {
                arrayList.add(CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly((String) it.next()));
            }
            this.phases = CollectionsKt.toSet(arrayList);
        }

        @Override // org.jetbrains.kotlin.config.phaser.PhaseSet
        public boolean contains(NamedCompilerPhase<?, ?, ?> phase) {
            phase.getClass();
            return this.phases.contains(CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(phase.getName()));
        }

        @Override // org.jetbrains.kotlin.config.phaser.PhaseSet
        public PhaseSet plus(PhaseSet phaseSet) {
            phaseSet.getClass();
            All all = All.INSTANCE;
            if (Intrinsics.areEqual(phaseSet, all)) {
                return all;
            }
            if (Intrinsics.areEqual(phaseSet, Empty.INSTANCE)) {
                return this;
            }
            if (phaseSet instanceof Enum) {
                return new Enum(SetsKt.plus(this.phases, ((Enum) phaseSet).phases));
            }
            bu8.a();
            return null;
        }
    }

    public /* synthetic */ PhaseSet(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract boolean contains(NamedCompilerPhase<?, ?, ?> phase);

    public abstract PhaseSet plus(PhaseSet phaseSet);

    private PhaseSet() {
    }
}

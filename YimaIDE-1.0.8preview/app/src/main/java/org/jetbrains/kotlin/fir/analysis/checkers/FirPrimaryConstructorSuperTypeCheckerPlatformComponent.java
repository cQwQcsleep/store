package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u000e\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\fH\u0017b\u0002\b\rR\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPrimaryConstructorSuperTypeCheckerPlatformComponent;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "supertypesThatDontNeedInitializationInSubtypesConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "getSupertypesThatDontNeedInitializationInSubtypesConstructors", "()Ljava/util/Set;", "createComposed", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPrimaryConstructorSuperTypeCheckerPlatformComponent$Composed;", "components", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Default", "Composed", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirPrimaryConstructorSuperTypeCheckerPlatformComponent implements FirComposableSessionComponent<FirPrimaryConstructorSuperTypeCheckerPlatformComponent> {

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPrimaryConstructorSuperTypeCheckerPlatformComponent$Composed;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPrimaryConstructorSuperTypeCheckerPlatformComponent;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "supertypesThatDontNeedInitializationInSubtypesConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "getSupertypesThatDontNeedInitializationInSubtypesConstructors", "()Ljava/util/Set;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirPrimaryConstructorSuperTypeCheckerPlatformComponent implements FirComposableSessionComponent.Composed<FirPrimaryConstructorSuperTypeCheckerPlatformComponent> {
        private final List<FirPrimaryConstructorSuperTypeCheckerPlatformComponent> components;
        private final Set<ClassId> supertypesThatDontNeedInitializationInSubtypesConstructors;

        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends FirPrimaryConstructorSuperTypeCheckerPlatformComponent> list) {
            list.getClass();
            this.components = list;
            List<FirPrimaryConstructorSuperTypeCheckerPlatformComponent> components = getComponents();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(linkedHashSet, ((FirPrimaryConstructorSuperTypeCheckerPlatformComponent) it.next()).getSupertypesThatDontNeedInitializationInSubtypesConstructors());
            }
            this.supertypesThatDontNeedInitializationInSubtypesConstructors = linkedHashSet;
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirPrimaryConstructorSuperTypeCheckerPlatformComponent> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPrimaryConstructorSuperTypeCheckerPlatformComponent
        public Set<ClassId> getSupertypesThatDontNeedInitializationInSubtypesConstructors() {
            return this.supertypesThatDontNeedInitializationInSubtypesConstructors;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPrimaryConstructorSuperTypeCheckerPlatformComponent$Default;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPrimaryConstructorSuperTypeCheckerPlatformComponent;", "<init>", "()V", "supertypesThatDontNeedInitializationInSubtypesConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "getSupertypesThatDontNeedInitializationInSubtypesConstructors", "()Ljava/util/Set;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default extends FirPrimaryConstructorSuperTypeCheckerPlatformComponent {
        public static final Default INSTANCE = new Default();
        private static final Set<ClassId> supertypesThatDontNeedInitializationInSubtypesConstructors = SetsKt.setOf(StandardClassIds.INSTANCE.getEnum());

        private Default() {
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPrimaryConstructorSuperTypeCheckerPlatformComponent
        public Set<ClassId> getSupertypesThatDontNeedInitializationInSubtypesConstructors() {
            return supertypesThatDontNeedInitializationInSubtypesConstructors;
        }
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirPrimaryConstructorSuperTypeCheckerPlatformComponent> components) {
        components.getClass();
        return new Composed(components);
    }

    public abstract Set<ClassId> getSupertypesThatDontNeedInitializationInSubtypesConstructors();

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirPrimaryConstructorSuperTypeCheckerPlatformComponent>) list);
    }
}

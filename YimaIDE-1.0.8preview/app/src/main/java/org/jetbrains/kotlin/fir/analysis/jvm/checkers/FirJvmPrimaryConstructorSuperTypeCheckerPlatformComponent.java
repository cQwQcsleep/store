package org.jetbrains.kotlin.fir.analysis.jvm.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPrimaryConstructorSuperTypeCheckerPlatformComponent;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/FirJvmPrimaryConstructorSuperTypeCheckerPlatformComponent;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirPrimaryConstructorSuperTypeCheckerPlatformComponent;", "<init>", "()V", "supertypesThatDontNeedInitializationInSubtypesConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "getSupertypesThatDontNeedInitializationInSubtypesConstructors", "()Ljava/util/Set;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmPrimaryConstructorSuperTypeCheckerPlatformComponent extends FirPrimaryConstructorSuperTypeCheckerPlatformComponent {
    public static final FirJvmPrimaryConstructorSuperTypeCheckerPlatformComponent INSTANCE = new FirJvmPrimaryConstructorSuperTypeCheckerPlatformComponent();
    private static final Set<ClassId> supertypesThatDontNeedInitializationInSubtypesConstructors = SetsKt.setOf(new ClassId[]{StandardClassIds.INSTANCE.getEnum(), JvmStandardClassIds.Java.INSTANCE.getRecord()});

    private FirJvmPrimaryConstructorSuperTypeCheckerPlatformComponent() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirPrimaryConstructorSuperTypeCheckerPlatformComponent
    public Set<ClassId> getSupertypesThatDontNeedInitializationInSubtypesConstructors() {
        return supertypesThatDontNeedInitializationInSubtypesConstructors;
    }
}

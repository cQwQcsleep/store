package org.jetbrains.kotlin.descriptors.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.resolve.scopes.MemberScope;
import org.jetbrains.kotlin.types.TypeSubstitution;
import org.jetbrains.kotlin.types.checker.KotlinTypeRefiner;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\b"}, d2 = {"getRefinedUnsubstitutedMemberScopeIfPossible", "Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "kotlinTypeRefiner", "Lorg/jetbrains/kotlin/types/checker/KotlinTypeRefiner;", "getRefinedMemberScopeIfPossible", "typeSubstitution", "Lorg/jetbrains/kotlin/types/TypeSubstitution;", "org.jetbrains.kotlin:descriptors"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ModuleAwareClassDescriptorKt {
    public static final MemberScope getRefinedMemberScopeIfPossible(ClassDescriptor classDescriptor, TypeSubstitution typeSubstitution, KotlinTypeRefiner kotlinTypeRefiner) {
        classDescriptor.getClass();
        typeSubstitution.getClass();
        kotlinTypeRefiner.getClass();
        return ModuleAwareClassDescriptor.INSTANCE.getRefinedMemberScopeIfPossible$org_jetbrains_kotlin_descriptors(classDescriptor, typeSubstitution, kotlinTypeRefiner);
    }

    public static final MemberScope getRefinedUnsubstitutedMemberScopeIfPossible(ClassDescriptor classDescriptor, KotlinTypeRefiner kotlinTypeRefiner) {
        classDescriptor.getClass();
        kotlinTypeRefiner.getClass();
        return ModuleAwareClassDescriptor.INSTANCE.getRefinedUnsubstitutedMemberScopeIfPossible$org_jetbrains_kotlin_descriptors(classDescriptor, kotlinTypeRefiner);
    }
}

package org.jetbrains.kotlin.descriptors.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\u0000H&J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00002\u0006\u0010\r\u001a\u00020\u000eH&J0\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;", "Lorg/jetbrains/kotlin/descriptors/ConstructorDescriptor;", "Lorg/jetbrains/kotlin/descriptors/impl/DescriptorDerivedFromTypeAlias;", "underlyingConstructorDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassConstructorDescriptor;", "getUnderlyingConstructorDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassConstructorDescriptor;", "getContainingDeclaration", "Lorg/jetbrains/kotlin/descriptors/TypeAliasDescriptor;", "getReturnType", "Lorg/jetbrains/kotlin/types/KotlinType;", "getOriginal", "substitute", "substitutor", "Lorg/jetbrains/kotlin/types/TypeSubstitutor;", "withDispatchReceiver", "getWithDispatchReceiver", "()Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;", "copy", "newOwner", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "visibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "kind", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor$Kind;", "copyOverrides", Argument.Delimiters.none, "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface TypeAliasConstructorDescriptor extends ConstructorDescriptor, DescriptorDerivedFromTypeAlias {
    /* JADX INFO: renamed from: copy */
    TypeAliasConstructorDescriptor mo162copy(DeclarationDescriptor newOwner, Modality modality, DescriptorVisibility visibility, CallableMemberDescriptor.Kind kind, boolean copyOverrides);

    /* JADX INFO: renamed from: getContainingDeclaration */
    TypeAliasDescriptor mo163getContainingDeclaration();

    /* JADX INFO: renamed from: getOriginal */
    TypeAliasConstructorDescriptor mo165getOriginal();

    KotlinType getReturnType();

    ClassConstructorDescriptor getUnderlyingConstructorDescriptor();

    TypeAliasConstructorDescriptor getWithDispatchReceiver();

    /* JADX INFO: renamed from: substitute */
    TypeAliasConstructorDescriptor mo167substitute(TypeSubstitutor substitutor);
}

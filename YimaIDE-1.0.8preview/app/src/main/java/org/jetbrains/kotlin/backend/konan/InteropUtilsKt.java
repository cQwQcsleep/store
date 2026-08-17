package org.jetbrains.kotlin.backend.konan;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor;
import org.jetbrains.kotlin.incremental.components.NoLookupLocation;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.scopes.MemberScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u001f\u0010\u0004\u001a\r\u0012\t\u0012\u00070\u0006¢\u0006\u0002\b\u00070\u0005*\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u0000\u001a\u001f\u0010\f\u001a\r\u0012\t\u0012\u00070\r¢\u0006\u0002\b\u00070\u0005*\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u0002¨\u0006\u000e"}, d2 = {"child", "Lorg/jetbrains/kotlin/name/FqName;", "nameIdent", "", "getContributedVariables", "", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "Lkotlin/jvm/JvmWildcard;", "Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "name", "getContributedClass", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "getContributedFunctions", "Lorg/jetbrains/kotlin/descriptors/SimpleFunctionDescriptor;", "org.jetbrains.kotlin:base"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class InteropUtilsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName child(FqName fqName, String str) {
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        return fqName.child(nameIdentifier);
    }

    public static final ClassDescriptor getContributedClass(MemberScope memberScope, String str) {
        memberScope.getClass();
        str.getClass();
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        ClassDescriptor contributedClassifier = memberScope.getContributedClassifier(nameIdentifier, NoLookupLocation.FROM_BUILTINS);
        contributedClassifier.getClass();
        return contributedClassifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection<? extends SimpleFunctionDescriptor> getContributedFunctions(MemberScope memberScope, String str) {
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        return memberScope.getContributedFunctions(nameIdentifier, NoLookupLocation.FROM_BUILTINS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Collection<? extends PropertyDescriptor> getContributedVariables(MemberScope memberScope, String str) {
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        return memberScope.getContributedVariables(nameIdentifier, NoLookupLocation.FROM_BUILTINS);
    }
}

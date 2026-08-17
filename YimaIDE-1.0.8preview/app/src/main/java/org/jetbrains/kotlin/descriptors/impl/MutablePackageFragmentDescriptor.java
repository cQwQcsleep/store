package org.jetbrains.kotlin.descriptors.impl;

import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.scopes.MemberScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class MutablePackageFragmentDescriptor extends PackageFragmentDescriptorImpl {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 2 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "fqName";
        } else if (i != 2) {
            objArr[0] = "module";
        } else {
            objArr[0] = "org/jetbrains/kotlin/descriptors/impl/MutablePackageFragmentDescriptor";
        }
        if (i != 2) {
            objArr[1] = "org/jetbrains/kotlin/descriptors/impl/MutablePackageFragmentDescriptor";
        } else {
            objArr[1] = "getMemberScope";
        }
        if (i != 2) {
            objArr[2] = CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME;
        }
        String str2 = String.format(str, objArr);
        if (i == 2) {
            throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutablePackageFragmentDescriptor(ModuleDescriptor moduleDescriptor, FqName fqName) {
        super(moduleDescriptor, fqName);
        if (moduleDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        if (fqName == null) {
            $$$reportNull$$$0(1);
        }
    }

    public MemberScope getMemberScope() {
        MemberScope.Empty empty = MemberScope.Empty.INSTANCE;
        if (empty == null) {
            $$$reportNull$$$0(2);
        }
        return empty;
    }
}

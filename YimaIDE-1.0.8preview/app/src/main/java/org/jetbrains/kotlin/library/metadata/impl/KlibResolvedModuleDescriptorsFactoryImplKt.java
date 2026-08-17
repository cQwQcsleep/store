package org.jetbrains.kotlin.library.metadata.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u0004"}, d2 = {"isForwardDeclarationModule", "", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "(Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;)Z", "org.jetbrains.kotlin:kotlin-util-klib-metadata"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class KlibResolvedModuleDescriptorsFactoryImplKt {
    public static final boolean isForwardDeclarationModule(ModuleDescriptor moduleDescriptor) {
        moduleDescriptor.getClass();
        return Intrinsics.areEqual(moduleDescriptor.getName(), KlibResolvedModuleDescriptorsFactoryImpl.Companion.getFORWARD_DECLARATIONS_MODULE_NAME());
    }
}

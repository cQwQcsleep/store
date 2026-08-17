package org.jetbrains.kotlin.backend.common.serialization.metadata;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider;
import org.jetbrains.kotlin.descriptors.impl.ModuleDescriptorImpl;
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aM\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00030\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0018\u0010\u0007\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0004\u0012\u0002H\u00020\bH\u0000¢\u0006\u0002\u0010\t\u001a2\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001\u001a2\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00112\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001\u001a\u0011\u0010\u0014\u001a\u0004\u0018\u00010\u0006*\u00020\u0015¢\u0006\u0002\u0010\u0016\"\u0018\u0010\u0017\u001a\u00020\u0018*\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"maybeChunked", "", "R", "T", "", "size", "", "transform", "Lkotlin/Function1;", "(Ljava/lang/Iterable;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "serializeKlibHeader", "Lorg/jetbrains/kotlin/library/metadata/KlibMetadataProtoBuf$Header;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "fragmentNames", "", "emptyPackages", "moduleName", "extractFileId", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;)Ljava/lang/Integer;", "packageFragmentProviderForModuleContentWithoutDependencies", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "getPackageFragmentProviderForModuleContentWithoutDependencies", "(Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;)Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class KlibMetadataSerializerKt {
    public static final Integer extractFileId(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        if (declarationDescriptor instanceof DeserializedClassDescriptor) {
            return (Integer) ((DeserializedClassDescriptor) declarationDescriptor).getClassProto().getExtension(KlibMetadataProtoBuf.classFile);
        }
        if (declarationDescriptor instanceof DeserializedSimpleFunctionDescriptor) {
            return (Integer) ((DeserializedSimpleFunctionDescriptor) declarationDescriptor).getProto().getExtension(KlibMetadataProtoBuf.functionFile);
        }
        if (declarationDescriptor instanceof DeserializedPropertyDescriptor) {
            return (Integer) ((DeserializedPropertyDescriptor) declarationDescriptor).getProto().getExtension(KlibMetadataProtoBuf.propertyFile);
        }
        return null;
    }

    public static final PackageFragmentProvider getPackageFragmentProviderForModuleContentWithoutDependencies(ModuleDescriptor moduleDescriptor) {
        PackageFragmentProvider packageFragmentProviderForModuleContentWithoutDependencies;
        moduleDescriptor.getClass();
        ModuleDescriptorImpl moduleDescriptorImpl = moduleDescriptor instanceof ModuleDescriptorImpl ? (ModuleDescriptorImpl) moduleDescriptor : null;
        if (moduleDescriptorImpl != null && (packageFragmentProviderForModuleContentWithoutDependencies = moduleDescriptorImpl.getPackageFragmentProviderForModuleContentWithoutDependencies()) != null) {
            return packageFragmentProviderForModuleContentWithoutDependencies;
        }
        c2f.a("Can't get a module content package fragments, it's not a ", Reflection.getOrCreateKotlinClass(ModuleDescriptorImpl.class).getSimpleName(), 46);
        return null;
    }

    public static final <T, R> List<R> maybeChunked(Iterable<? extends T> iterable, Integer num, Function1<? super List<? extends T>, ? extends R> function1) {
        List<R> listChunked;
        iterable.getClass();
        function1.getClass();
        return (num == null || (listChunked = CollectionsKt.chunked(iterable, num.intValue(), function1)) == null) ? CollectionsKt.listOf(function1.invoke(CollectionsKt.toList(iterable))) : listChunked;
    }

    public static final KlibMetadataProtoBuf.Header serializeKlibHeader(LanguageVersionSettings languageVersionSettings, String str, List<String> list, List<String> list2) {
        languageVersionSettings.getClass();
        str.getClass();
        list.getClass();
        list2.getClass();
        KlibMetadataProtoBuf.Header.Builder builderNewBuilder = KlibMetadataProtoBuf.Header.newBuilder();
        builderNewBuilder.setModuleName(str);
        if (languageVersionSettings.isPreRelease()) {
            builderNewBuilder.setFlags(2);
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            builderNewBuilder.addPackageFragmentName((String) it.next());
        }
        Iterator<T> it2 = list2.iterator();
        while (it2.hasNext()) {
            builderNewBuilder.addEmptyPackage((String) it2.next());
        }
        KlibMetadataProtoBuf.Header headerBuild = builderNewBuilder.build();
        headerBuild.getClass();
        return headerBuild;
    }

    public static final KlibMetadataProtoBuf.Header serializeKlibHeader(LanguageVersionSettings languageVersionSettings, ModuleDescriptor moduleDescriptor, List<String> list, List<String> list2) {
        languageVersionSettings.getClass();
        moduleDescriptor.getClass();
        list.getClass();
        list2.getClass();
        String strAsString = moduleDescriptor.getName().asString();
        strAsString.getClass();
        return serializeKlibHeader(languageVersionSettings, strAsString, list, list2);
    }
}

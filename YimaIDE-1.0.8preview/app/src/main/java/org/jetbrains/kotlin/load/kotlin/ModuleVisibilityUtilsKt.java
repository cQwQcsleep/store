package org.jetbrains.kotlin.load.kotlin;

import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithSource;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.load.java.lazy.descriptors.LazyJavaPackageFragment;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedTypeAliasDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003\"\u0015\u0010\b\u001a\u00020\u0007*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"isContainedByCompiledPartOfOurModule", Argument.Delimiters.none, "descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "friendPath", "Ljava/io/File;", "getSourceElement", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "toSourceElement", "getToSourceElement", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;)Lorg/jetbrains/kotlin/descriptors/SourceElement;", "org.jetbrains.kotlin:frontend.java"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ModuleVisibilityUtilsKt {
    public static final SourceElement getSourceElement(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        if (declarationDescriptor instanceof CallableMemberDescriptor) {
            CallableMemberDescriptor callableMemberDescriptor = (CallableMemberDescriptor) declarationDescriptor;
            if (callableMemberDescriptor.getSource() == SourceElement.NO_SOURCE) {
                DeclarationDescriptor containingDeclaration = callableMemberDescriptor.getContainingDeclaration();
                containingDeclaration.getClass();
                return getSourceElement(containingDeclaration);
            }
        }
        if (!(declarationDescriptor instanceof DeserializedTypeAliasDescriptor)) {
            return getToSourceElement(declarationDescriptor);
        }
        DeclarationDescriptor containingDeclaration2 = ((DeserializedTypeAliasDescriptor) declarationDescriptor).getContainingDeclaration();
        containingDeclaration2.getClass();
        return getSourceElement(containingDeclaration2);
    }

    public static final SourceElement getToSourceElement(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        SourceElement source = declarationDescriptor instanceof DeclarationDescriptorWithSource ? ((DeclarationDescriptorWithSource) declarationDescriptor).getSource() : SourceElement.NO_SOURCE;
        source.getClass();
        return source;
    }

    public static final boolean isContainedByCompiledPartOfOurModule(DeclarationDescriptor declarationDescriptor, File file) {
        KotlinJvmBinaryClass representativeBinaryClass;
        declarationDescriptor.getClass();
        if (file == null || !(DescriptorUtils.getParentOfType(declarationDescriptor, PackageFragmentDescriptor.class, false) instanceof LazyJavaPackageFragment)) {
            return false;
        }
        KotlinJvmBinarySourceElement sourceElement = getSourceElement(declarationDescriptor);
        if (sourceElement instanceof KotlinJvmBinarySourceElement) {
            representativeBinaryClass = sourceElement.getBinaryClass();
        } else if (!(sourceElement instanceof KotlinJvmBinaryPackageSourceElement)) {
            representativeBinaryClass = null;
        } else if (declarationDescriptor instanceof DeserializedMemberDescriptor) {
            KotlinJvmBinaryPackageSourceElement kotlinJvmBinaryPackageSourceElement = (KotlinJvmBinaryPackageSourceElement) sourceElement;
            representativeBinaryClass = kotlinJvmBinaryPackageSourceElement.getContainingBinaryClass((DeserializedMemberDescriptor) declarationDescriptor);
            if (representativeBinaryClass == null) {
                representativeBinaryClass = kotlinJvmBinaryPackageSourceElement.getRepresentativeBinaryClass();
            }
        } else {
            representativeBinaryClass = ((KotlinJvmBinaryPackageSourceElement) sourceElement).getRepresentativeBinaryClass();
        }
        if (representativeBinaryClass instanceof VirtualFileKotlinClass) {
            VirtualFile file2 = ((VirtualFileKotlinClass) representativeBinaryClass).getFile();
            String protocol = file2.getFileSystem().getProtocol();
            int iHashCode = protocol.hashCode();
            if (iHashCode != 104987) {
                if (iHashCode == 3143036 && protocol.equals("file")) {
                    File fileVirtualToIoFile = VfsUtilCore.virtualToIoFile(file2);
                    fileVirtualToIoFile.getClass();
                    return fileVirtualToIoFile.toPath().startsWith(file.toPath());
                }
            } else if (protocol.equals("jar")) {
                VirtualFile virtualFileForJar = VfsUtilCore.getVirtualFileForJar(file2);
                File fileVirtualToIoFile2 = virtualFileForJar != null ? VfsUtilCore.virtualToIoFile(virtualFileForJar) : null;
                if (fileVirtualToIoFile2 != null && Intrinsics.areEqual(fileVirtualToIoFile2.toPath(), file.toPath())) {
                    return true;
                }
            }
        }
        return false;
    }
}

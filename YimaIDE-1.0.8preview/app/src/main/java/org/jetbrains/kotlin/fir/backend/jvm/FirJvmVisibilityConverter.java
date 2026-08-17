package org.jetbrains.kotlin.fir.backend.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.descriptors.java.JavaVisibilities;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.load.java.JavaDescriptorVisibilities;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0014¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmVisibilityConverter;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "<init>", "()V", "convertPlatformVisibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmVisibilityConverter extends Fir2IrVisibilityConverter {
    public static final FirJvmVisibilityConverter INSTANCE = new FirJvmVisibilityConverter();

    private FirJvmVisibilityConverter() {
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter
    public DescriptorVisibility convertPlatformVisibility(Visibility visibility) {
        visibility.getClass();
        if (Intrinsics.areEqual(visibility, JavaVisibilities.PackageVisibility.INSTANCE)) {
            DescriptorVisibility descriptorVisibility = JavaDescriptorVisibilities.PACKAGE_VISIBILITY;
            descriptorVisibility.getClass();
            return descriptorVisibility;
        }
        if (Intrinsics.areEqual(visibility, JavaVisibilities.ProtectedStaticVisibility.INSTANCE)) {
            DescriptorVisibility descriptorVisibility2 = JavaDescriptorVisibilities.PROTECTED_STATIC_VISIBILITY;
            descriptorVisibility2.getClass();
            return descriptorVisibility2;
        }
        if (!Intrinsics.areEqual(visibility, JavaVisibilities.ProtectedAndPackage.INSTANCE)) {
            w04.a("Unknown visibility: ", visibility);
            return null;
        }
        DescriptorVisibility descriptorVisibility3 = JavaDescriptorVisibilities.PROTECTED_AND_PACKAGE;
        descriptorVisibility3.getClass();
        return descriptorVisibility3;
    }
}

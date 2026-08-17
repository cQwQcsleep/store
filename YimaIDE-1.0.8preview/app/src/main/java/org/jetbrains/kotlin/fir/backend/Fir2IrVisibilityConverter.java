package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001:\u0001\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H$¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", Argument.Delimiters.none, "<init>", "()V", "convertToDescriptorVisibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "convertPlatformVisibility", "Default", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class Fir2IrVisibilityConverter {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0014¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter$Default;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "<init>", "()V", "convertPlatformVisibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default extends Fir2IrVisibilityConverter {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter
        public DescriptorVisibility convertPlatformVisibility(Visibility visibility) {
            visibility.getClass();
            throw new IllegalStateException(("Unknown visibility: " + visibility).toString());
        }
    }

    public abstract DescriptorVisibility convertPlatformVisibility(Visibility visibility);

    public final DescriptorVisibility convertToDescriptorVisibility(Visibility visibility) {
        visibility.getClass();
        if (Intrinsics.areEqual(visibility, Visibilities.Private.INSTANCE)) {
            DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PRIVATE;
            descriptorVisibility.getClass();
            return descriptorVisibility;
        }
        if (Intrinsics.areEqual(visibility, Visibilities.PrivateToThis.INSTANCE)) {
            DescriptorVisibility descriptorVisibility2 = DescriptorVisibilities.PRIVATE_TO_THIS;
            descriptorVisibility2.getClass();
            return descriptorVisibility2;
        }
        if (Intrinsics.areEqual(visibility, Visibilities.Protected.INSTANCE)) {
            DescriptorVisibility descriptorVisibility3 = DescriptorVisibilities.PROTECTED;
            descriptorVisibility3.getClass();
            return descriptorVisibility3;
        }
        if (Intrinsics.areEqual(visibility, Visibilities.Internal.INSTANCE)) {
            DescriptorVisibility descriptorVisibility4 = DescriptorVisibilities.INTERNAL;
            descriptorVisibility4.getClass();
            return descriptorVisibility4;
        }
        if (Intrinsics.areEqual(visibility, Visibilities.Public.INSTANCE)) {
            DescriptorVisibility descriptorVisibility5 = DescriptorVisibilities.PUBLIC;
            descriptorVisibility5.getClass();
            return descriptorVisibility5;
        }
        if (Intrinsics.areEqual(visibility, Visibilities.Local.INSTANCE)) {
            DescriptorVisibility descriptorVisibility6 = DescriptorVisibilities.LOCAL;
            descriptorVisibility6.getClass();
            return descriptorVisibility6;
        }
        if (Intrinsics.areEqual(visibility, Visibilities.InvisibleFake.INSTANCE)) {
            DescriptorVisibility descriptorVisibility7 = DescriptorVisibilities.INVISIBLE_FAKE;
            descriptorVisibility7.getClass();
            return descriptorVisibility7;
        }
        if (!Intrinsics.areEqual(visibility, Visibilities.Unknown.INSTANCE)) {
            return convertPlatformVisibility(visibility);
        }
        DescriptorVisibility descriptorVisibility8 = DescriptorVisibilities.UNKNOWN;
        descriptorVisibility8.getClass();
        return descriptorVisibility8;
    }
}

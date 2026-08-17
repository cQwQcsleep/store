package org.jetbrains.kotlin.descriptors.java;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/java/JavaVisibilities;", Argument.Delimiters.none, "<init>", "()V", "PackageVisibility", "ProtectedStaticVisibility", "ProtectedAndPackage", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaVisibilities {
    public static final JavaVisibilities INSTANCE = new JavaVisibilities();

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0001H\u0016¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u0001H\u0016J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0016R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/java/JavaVisibilities$PackageVisibility;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "mustCheckInImports", Argument.Delimiters.none, "compareTo", Argument.Delimiters.none, "visibility", "(Lorg/jetbrains/kotlin/descriptors/Visibility;)Ljava/lang/Integer;", "normalize", "internalDisplayName", Argument.Delimiters.none, "getInternalDisplayName", "()Ljava/lang/String;", "externalDisplayName", "getExternalDisplayName", "customEffectiveVisibility", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "visibleFromPackage", "fromPackage", "Lorg/jetbrains/kotlin/name/FqName;", "myPackage", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class PackageVisibility extends Visibility {
        public static final PackageVisibility INSTANCE = new PackageVisibility();

        private PackageVisibility() {
            super("package", false);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public Integer compareTo(Visibility visibility) {
            visibility.getClass();
            if (this == visibility) {
                return 0;
            }
            return Visibilities.INSTANCE.isPrivate(visibility) ? 1 : -1;
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public EffectiveVisibility customEffectiveVisibility() {
            return EffectiveVisibility.PackagePrivate.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public String getExternalDisplayName() {
            return "package-private";
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        /* JADX INFO: renamed from: getInternalDisplayName */
        public String getName() {
            return "public/*package*/";
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            return true;
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public Visibility normalize() {
            return Visibilities.Protected.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean visibleFromPackage(FqName fromPackage, FqName myPackage) {
            fromPackage.getClass();
            myPackage.getClass();
            return Intrinsics.areEqual(fromPackage, myPackage);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0016¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0001H\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/java/JavaVisibilities$ProtectedAndPackage;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "compareTo", Argument.Delimiters.none, "visibility", "(Lorg/jetbrains/kotlin/descriptors/Visibility;)Ljava/lang/Integer;", "normalize", "mustCheckInImports", Argument.Delimiters.none, "internalDisplayName", Argument.Delimiters.none, "getInternalDisplayName", "()Ljava/lang/String;", "externalDisplayName", "getExternalDisplayName", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ProtectedAndPackage extends Visibility {
        public static final ProtectedAndPackage INSTANCE = new ProtectedAndPackage();

        private ProtectedAndPackage() {
            super("protected_and_package", true);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public Integer compareTo(Visibility visibility) {
            visibility.getClass();
            if (Intrinsics.areEqual(this, visibility)) {
                return 0;
            }
            if (visibility == Visibilities.Internal.INSTANCE) {
                return null;
            }
            return Visibilities.INSTANCE.isPrivate(visibility) ? 1 : -1;
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public String getExternalDisplayName() {
            return "protected";
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        /* JADX INFO: renamed from: getInternalDisplayName */
        public String getName() {
            return "protected/*protected and package*/";
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            return false;
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public Visibility normalize() {
            return Visibilities.Protected.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0001H\u0016R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/java/JavaVisibilities$ProtectedStaticVisibility;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "<init>", "()V", "mustCheckInImports", Argument.Delimiters.none, "normalize", "internalDisplayName", Argument.Delimiters.none, "getInternalDisplayName", "()Ljava/lang/String;", "externalDisplayName", "getExternalDisplayName", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ProtectedStaticVisibility extends Visibility {
        public static final ProtectedStaticVisibility INSTANCE = new ProtectedStaticVisibility();

        private ProtectedStaticVisibility() {
            super("protected_static", true);
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public String getExternalDisplayName() {
            return "protected";
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        /* JADX INFO: renamed from: getInternalDisplayName */
        public String getName() {
            return "protected/*protected static*/";
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public boolean mustCheckInImports() {
            return false;
        }

        @Override // org.jetbrains.kotlin.descriptors.Visibility
        public Visibility normalize() {
            return Visibilities.Protected.INSTANCE;
        }
    }

    private JavaVisibilities() {
    }
}

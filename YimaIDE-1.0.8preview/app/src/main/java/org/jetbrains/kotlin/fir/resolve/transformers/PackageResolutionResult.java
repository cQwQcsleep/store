package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/PackageResolutionResult;", Argument.Delimiters.none, "<init>", "()V", "PackageOrClass", "Error", "Lorg/jetbrains/kotlin/fir/resolve/transformers/PackageResolutionResult$Error;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/PackageResolutionResult$PackageOrClass;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class PackageResolutionResult {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/PackageResolutionResult$Error;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/PackageResolutionResult;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "(Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;)V", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Error extends PackageResolutionResult {
        private final ConeDiagnostic diagnostic;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Error(ConeDiagnostic coneDiagnostic) {
            super(null);
            coneDiagnostic.getClass();
            this.diagnostic = coneDiagnostic;
        }

        public final ConeDiagnostic getDiagnostic() {
            return this.diagnostic;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006HÆ\u0003J/\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/PackageResolutionResult$PackageOrClass;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/PackageResolutionResult;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "relativeClassFqName", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)V", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getRelativeClassFqName", "getClassSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class PackageOrClass extends PackageResolutionResult {
        private final FirClassLikeSymbol<?> classSymbol;
        private final FqName packageFqName;
        private final FqName relativeClassFqName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PackageOrClass(FqName fqName, FqName fqName2, FirClassLikeSymbol<?> firClassLikeSymbol) {
            super(null);
            fqName.getClass();
            this.packageFqName = fqName;
            this.relativeClassFqName = fqName2;
            this.classSymbol = firClassLikeSymbol;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PackageOrClass copy$default(PackageOrClass packageOrClass, FqName fqName, FqName fqName2, FirClassLikeSymbol firClassLikeSymbol, int i, Object obj) {
            if ((i & 1) != 0) {
                fqName = packageOrClass.packageFqName;
            }
            if ((i & 2) != 0) {
                fqName2 = packageOrClass.relativeClassFqName;
            }
            if ((i & 4) != 0) {
                firClassLikeSymbol = packageOrClass.classSymbol;
            }
            return packageOrClass.copy(fqName, fqName2, firClassLikeSymbol);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FqName getPackageFqName() {
            return this.packageFqName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final FqName getRelativeClassFqName() {
            return this.relativeClassFqName;
        }

        public final FirClassLikeSymbol<?> component3() {
            return this.classSymbol;
        }

        public final PackageOrClass copy(FqName packageFqName, FqName relativeClassFqName, FirClassLikeSymbol<?> classSymbol) {
            packageFqName.getClass();
            return new PackageOrClass(packageFqName, relativeClassFqName, classSymbol);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PackageOrClass)) {
                return false;
            }
            PackageOrClass packageOrClass = (PackageOrClass) other;
            return Intrinsics.areEqual(this.packageFqName, packageOrClass.packageFqName) && Intrinsics.areEqual(this.relativeClassFqName, packageOrClass.relativeClassFqName) && Intrinsics.areEqual(this.classSymbol, packageOrClass.classSymbol);
        }

        public final FirClassLikeSymbol<?> getClassSymbol() {
            return this.classSymbol;
        }

        public final FqName getPackageFqName() {
            return this.packageFqName;
        }

        public final FqName getRelativeClassFqName() {
            return this.relativeClassFqName;
        }

        public int hashCode() {
            int iHashCode = this.packageFqName.hashCode() * 31;
            FqName fqName = this.relativeClassFqName;
            int iHashCode2 = (iHashCode + (fqName == null ? 0 : fqName.hashCode())) * 31;
            FirClassLikeSymbol<?> firClassLikeSymbol = this.classSymbol;
            return iHashCode2 + (firClassLikeSymbol != null ? firClassLikeSymbol.hashCode() : 0);
        }

        public String toString() {
            return "PackageOrClass(packageFqName=" + this.packageFqName + ", relativeClassFqName=" + this.relativeClassFqName + ", classSymbol=" + this.classSymbol + ')';
        }
    }

    public /* synthetic */ PackageResolutionResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private PackageResolutionResult() {
    }
}

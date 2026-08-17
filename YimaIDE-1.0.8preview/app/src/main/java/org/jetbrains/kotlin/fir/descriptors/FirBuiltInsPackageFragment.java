package org.jetbrains.kotlin.fir.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.builtins.BuiltInsPackageFragment;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/descriptors/FirBuiltInsPackageFragment;", "Lorg/jetbrains/kotlin/fir/descriptors/FirPackageFragmentDescriptor;", "Lorg/jetbrains/kotlin/builtins/BuiltInsPackageFragment;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;)V", "isFallback", Argument.Delimiters.none, "()Z", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirBuiltInsPackageFragment extends FirPackageFragmentDescriptor implements BuiltInsPackageFragment {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirBuiltInsPackageFragment(FqName fqName, ModuleDescriptor moduleDescriptor) {
        super(fqName, moduleDescriptor);
        fqName.getClass();
        moduleDescriptor.getClass();
    }

    public boolean isFallback() {
        return false;
    }
}

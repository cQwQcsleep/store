package org.jetbrains.kotlin.builtins;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/builtins/FunctionInterfacePackageFragment;", "Lorg/jetbrains/kotlin/builtins/BuiltInsPackageFragment;", "isFallback", "", "()Z", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface FunctionInterfacePackageFragment extends BuiltInsPackageFragment {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        public static boolean isFallback(FunctionInterfacePackageFragment functionInterfacePackageFragment) {
            return false;
        }
    }

    boolean isFallback();
}

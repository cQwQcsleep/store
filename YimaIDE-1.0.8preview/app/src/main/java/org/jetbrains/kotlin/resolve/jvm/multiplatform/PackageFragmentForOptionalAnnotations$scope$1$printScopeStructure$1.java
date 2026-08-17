package org.jetbrains.kotlin.resolve.jvm.multiplatform;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.name.Name;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final /* synthetic */ class PackageFragmentForOptionalAnnotations$scope$1$printScopeStructure$1 extends FunctionReferenceImpl implements Function1<Name, String> {
    public static final PackageFragmentForOptionalAnnotations$scope$1$printScopeStructure$1 INSTANCE = new PackageFragmentForOptionalAnnotations$scope$1$printScopeStructure$1();

    public PackageFragmentForOptionalAnnotations$scope$1$printScopeStructure$1() {
        super(1, Name.class, "asString", "asString()Ljava/lang/String;", 0);
    }

    public final String invoke(Name name) {
        name.getClass();
        return name.asString();
    }
}

package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/ImportPath$Companion;", "", "<init>", "()V", "fromString", "Lorg/jetbrains/kotlin/resolve/ImportPath;", "pathStr", "", "org.jetbrains.kotlin:names"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class ImportPath$Companion {
    public /* synthetic */ ImportPath$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    public final ImportPath fromString(String pathStr) {
        pathStr.getClass();
        return StringsKt.endsWith$default(pathStr, ".*", false, 2, (Object) null) ? new ImportPath(new FqName(pathStr.substring(0, pathStr.length() - 2)), true, (Name) null, 4, (DefaultConstructorMarker) null) : new ImportPath(new FqName(pathStr), false, (Name) null, 4, (DefaultConstructorMarker) null);
    }

    private ImportPath$Companion() {
    }
}

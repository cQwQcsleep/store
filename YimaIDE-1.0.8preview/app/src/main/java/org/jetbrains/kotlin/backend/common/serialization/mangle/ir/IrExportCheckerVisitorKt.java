package org.jetbrains.kotlin.backend.common.serialization.mangle.ir;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u0004"}, d2 = {"isAnonymous", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/name/Name;)Z", "org.jetbrains.kotlin:ir.serialization.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IrExportCheckerVisitorKt {
    public static final boolean isAnonymous(Name name) {
        name.getClass();
        if (name.isSpecial()) {
            return Intrinsics.areEqual(name, SpecialNames.ANONYMOUS) || Intrinsics.areEqual(name, SpecialNames.NO_NAME_PROVIDED);
        }
        return false;
    }
}

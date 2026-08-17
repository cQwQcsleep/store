package org.jetbrains.kotlin.codegen.coroutines;

import kotlin.Metadata;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0002"}, d2 = {"BOXING_CLASS_INTERNAL_NAME", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ChangeBoxingMethodTransformerKt {
    private static final String BOXING_CLASS_INTERNAL_NAME;

    static {
        FqName fqName = StandardNames.COROUTINES_JVM_INTERNAL_PACKAGE_FQ_NAME;
        Name nameIdentifier = Name.identifier("Boxing");
        nameIdentifier.getClass();
        BOXING_CLASS_INTERNAL_NAME = CodegenUtilKt.topLevelClassInternalName(fqName.child(nameIdentifier));
    }
}

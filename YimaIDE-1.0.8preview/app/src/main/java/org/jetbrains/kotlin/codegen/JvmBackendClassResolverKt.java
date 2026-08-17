package org.jetbrains.kotlin.codegen;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"classId", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/org/objectweb/asm/Type;", "getClassId", "(Lorg/jetbrains/org/objectweb/asm/Type;)Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmBackendClassResolverKt {
    public static final ClassId getClassId(Type type) {
        type.getClass();
        String className = type.getClassName();
        className.getClass();
        int iLastIndexOf$default = StringsKt.lastIndexOf$default(className, '.', 0, false, 6, (Object) null);
        FqName fqName = iLastIndexOf$default >= 0 ? new FqName(className.substring(0, iLastIndexOf$default)) : FqName.ROOT;
        if (iLastIndexOf$default >= 0) {
            className = className.substring(iLastIndexOf$default + 1);
        }
        String str = className;
        str.getClass();
        return new ClassId(fqName, new FqName(StringsKt.replace$default(str, '$', '.', false, 4, (Object) null)), false);
    }
}

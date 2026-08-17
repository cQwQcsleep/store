package org.jetbrains.kotlin.fir.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNamesUtilKt;
import org.jetbrains.kotlin.name.NameUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"scriptClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "org.jetbrains.kotlin:fir-serialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirElementSerializerKt {
    public static final ClassId scriptClassId(FirScript firScript) {
        firScript.getClass();
        FqName fqNameParentOrNull = FqNamesUtilKt.parentOrNull(firScript.getSymbol().getFqName());
        if (fqNameParentOrNull == null) {
            fqNameParentOrNull = FqName.ROOT;
        }
        return new ClassId(fqNameParentOrNull, NameUtils.getScriptTargetClassName(firScript.getName()));
    }
}

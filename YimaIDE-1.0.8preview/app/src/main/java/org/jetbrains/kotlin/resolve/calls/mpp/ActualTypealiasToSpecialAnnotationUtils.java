package org.jetbrains.kotlin.resolve.calls.mpp;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/mpp/ActualTypealiasToSpecialAnnotationUtils;", "", "<init>", "()V", "FORBIDDEN_PACKAGES", "", "Lorg/jetbrains/kotlin/name/FqName;", "isAnnotationProhibitedInActualTypeAlias", "", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class ActualTypealiasToSpecialAnnotationUtils {
    public static final ActualTypealiasToSpecialAnnotationUtils INSTANCE = new ActualTypealiasToSpecialAnnotationUtils();
    private static final Set<FqName> FORBIDDEN_PACKAGES = SetsKt.setOf(new FqName[]{StandardNames.ANNOTATION_PACKAGE_FQ_NAME, StandardNames.BUILT_INS_PACKAGE_FQ_NAME, StandardNames.KOTLIN_INTERNAL_FQ_NAME});

    private ActualTypealiasToSpecialAnnotationUtils() {
    }

    public final boolean isAnnotationProhibitedInActualTypeAlias(ClassId classId) {
        classId.getClass();
        return FORBIDDEN_PACKAGES.contains(classId.getPackageFqName());
    }
}

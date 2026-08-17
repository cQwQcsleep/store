package org.jetbrains.kotlin.resolve.calls.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.name.FqName;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"ALLOWED_MEMBER_NAMES", "", "", "ALLOWED_CLASSES", "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class AbstractReflectionApiCallCheckerKt {
    private static final Set<String> ALLOWED_MEMBER_NAMES = SetsKt.setOf(new String[]{"equals", "hashCode", "toString", "invoke", "name"});
    private static final Set<FqName> ALLOWED_CLASSES = SetsKt.setOf(new FqName[]{new FqName("kotlin.reflect.KType"), new FqName("kotlin.reflect.KTypeParameter"), new FqName("kotlin.reflect.KTypeProjection"), new FqName("kotlin.reflect.KTypeProjection.Companion"), new FqName("kotlin.reflect.KVariance")});
}

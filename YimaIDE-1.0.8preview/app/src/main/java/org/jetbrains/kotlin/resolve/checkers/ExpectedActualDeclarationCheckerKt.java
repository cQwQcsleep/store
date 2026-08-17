package org.jetbrains.kotlin.resolve.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000*<\b\u0002\u0010\u0002\"\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00032\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u0003¨\u0006\u0007"}, d2 = {"implicitlyActualizedAnnotationFqn", "Lorg/jetbrains/kotlin/name/FqName;", "ActualsMap", "", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility;", "Lorg/jetbrains/kotlin/descriptors/MemberDescriptor;", "", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class ExpectedActualDeclarationCheckerKt {
    private static final FqName implicitlyActualizedAnnotationFqn = StandardClassIds.Annotations.INSTANCE.getImplicitlyActualizedByJvmDeclaration().asSingleFqName();
}

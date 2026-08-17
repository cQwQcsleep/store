package org.jetbrains.kotlin.backend.common.serialization.mangle;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u0007H&¢\u0006\u0002\u0010\bJ\u0011\u0010\t\u001a\u00020\u0004*\u00028\u0000H&¢\u0006\u0002\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/mangle/KotlinExportChecker;", "D", "", "check", "", "declaration", "type", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/SpecialDeclarationType;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/backend/common/serialization/mangle/SpecialDeclarationType;)Z", "isPlatformSpecificExported", "(Ljava/lang/Object;)Z", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface KotlinExportChecker<D> {
    boolean check(D declaration, SpecialDeclarationType type);

    boolean isPlatformSpecificExported(D d);
}

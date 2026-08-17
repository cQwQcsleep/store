package org.jetbrains.kotlin.ir.backend.js.lower;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.backend.common.CommonBackendContext;
import org.jetbrains.kotlin.backend.common.lower.InitializersCleanupLowering;
import org.jetbrains.kotlin.backend.common.phaser.PhasePrerequisites;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@PhasePrerequisites({JsInitializersLowering.class})
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005Ê\u0001\u0010\b\u0007\u0012\f\b\b\u0012\b\b\fJ\u0004\b\t0\t¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/lower/JsInitializersCleanupLowering;", "Lorg/jetbrains/kotlin/backend/common/lower/InitializersCleanupLowering;", "context", "Lorg/jetbrains/kotlin/backend/common/CommonBackendContext;", "<init>", "(Lorg/jetbrains/kotlin/backend/common/CommonBackendContext;)V", "org.jetbrains.kotlin:backend.js", "Lorg/jetbrains/kotlin/backend/common/phaser/PhasePrerequisites;", "value", "Lorg/jetbrains/kotlin/ir/backend/js/lower/JsInitializersLowering;"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class JsInitializersCleanupLowering extends InitializersCleanupLowering {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsInitializersCleanupLowering(CommonBackendContext commonBackendContext) {
        super(commonBackendContext, (Function1) null, 2, (DefaultConstructorMarker) null);
        commonBackendContext.getClass();
    }
}

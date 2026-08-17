package org.jetbrains.kotlin.cfg;

import kotlin.Metadata;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final /* synthetic */ class ControlFlowProcessor$CFPVisitor$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static {
        int[] iArr = new int[ExplicitReceiverKind.values().length];
        try {
            iArr[ExplicitReceiverKind.DISPATCH_RECEIVER.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[ExplicitReceiverKind.EXTENSION_RECEIVER.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[ExplicitReceiverKind.BOTH_RECEIVERS.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[ExplicitReceiverKind.NO_EXPLICIT_RECEIVER.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        $EnumSwitchMapping$0 = iArr;
    }
}

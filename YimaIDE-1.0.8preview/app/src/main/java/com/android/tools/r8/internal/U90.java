package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetraceUnknownJsonMappingInformationResult;
import com.android.tools.r8.retrace.RetraceUnknownMappingInformationElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class U90 implements RetraceUnknownMappingInformationElement {
    public final RetraceUnknownJsonMappingInformationResult a;
    public final C2883vk0 b;

    public U90(RetraceUnknownJsonMappingInformationResult retraceUnknownJsonMappingInformationResult, C2883vk0 c2883vk0) {
        this.a = retraceUnknownJsonMappingInformationResult;
        this.b = c2883vk0;
    }

    @Override // com.android.tools.r8.retrace.RetraceUnknownMappingInformationElement
    public final String getIdentifier() {
        return this.b.a;
    }

    @Override // com.android.tools.r8.retrace.RetraceUnknownMappingInformationElement
    public final String getPayLoad() {
        return this.b.b;
    }

    @Override // com.android.tools.r8.retrace.RetraceUnknownMappingInformationElement
    public final RetraceUnknownJsonMappingInformationResult getRetraceResultContext() {
        return this.a;
    }
}

package org.codehaus.stax2;

import org.codehaus.stax2.validation.DTDValidationSchema;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface DTDInfo {
    String getDTDInternalSubset();

    String getDTDPublicId();

    String getDTDRootName();

    String getDTDSystemId();

    Object getProcessedDTD();

    DTDValidationSchema getProcessedDTDSchema();
}

package org.bouncycastle.tsp.ers;

import java.util.Date;
import org.bouncycastle.util.Selector;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ERSEvidenceRecordSelector implements Selector<ERSEvidenceRecord> {
    private final ERSData data;
    private final Date date;

    public ERSEvidenceRecordSelector(ERSData eRSData, Date date) {
        this.data = eRSData;
        this.date = new Date(date.getTime());
    }

    public Object clone() {
        return this;
    }

    public ERSData getData() {
        return this.data;
    }

    public boolean match(ERSEvidenceRecord eRSEvidenceRecord) {
        try {
            if (eRSEvidenceRecord.isContaining(this.data, this.date)) {
                eRSEvidenceRecord.validatePresent(this.data, this.date);
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public ERSEvidenceRecordSelector(ERSData eRSData) {
        this(eRSData, new Date());
    }
}

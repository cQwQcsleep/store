package org.bouncycastle.tsp.ers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.bouncycastle.asn1.tsp.ArchiveTimeStamp;
import org.bouncycastle.asn1.tsp.PartialHashtree;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ERSEvidenceRecordStore implements Store<ERSEvidenceRecord> {
    private DigestCalculator digCalc;
    private Map<HashNode, List<ERSEvidenceRecord>> recordMap = new HashMap();

    public static class HashNode {
        private final byte[] dataHash;
        private final int hashCode;

        public HashNode(byte[] bArr) {
            this.dataHash = bArr;
            this.hashCode = Arrays.hashCode(bArr);
        }

        public boolean equals(Object obj) {
            if (obj instanceof HashNode) {
                return Arrays.areEqual(this.dataHash, ((HashNode) obj).dataHash);
            }
            return false;
        }

        public int hashCode() {
            return this.hashCode;
        }
    }

    public ERSEvidenceRecordStore(Collection<ERSEvidenceRecord> collection) throws OperatorCreationException {
        this.digCalc = null;
        for (ERSEvidenceRecord eRSEvidenceRecord : collection) {
            ArchiveTimeStamp archiveTimeStamp = eRSEvidenceRecord.getArchiveTimeStamps()[0];
            if (this.digCalc == null) {
                this.digCalc = eRSEvidenceRecord.getDigestAlgorithmProvider().get(archiveTimeStamp.getDigestAlgorithmIdentifier());
            }
            PartialHashtree hashTreeLeaf = archiveTimeStamp.getHashTreeLeaf();
            if (hashTreeLeaf != null) {
                byte[][] values = hashTreeLeaf.getValues();
                if (values.length > 1) {
                    for (int i = 0; i != values.length; i++) {
                        addRecord(new HashNode(values[i]), eRSEvidenceRecord);
                    }
                    addRecord(new HashNode(ERSUtil.computeNodeHash(this.digCalc, hashTreeLeaf)), eRSEvidenceRecord);
                } else {
                    addRecord(new HashNode(values[0]), eRSEvidenceRecord);
                }
            } else {
                addRecord(new HashNode(archiveTimeStamp.getTimeStampDigestValue()), eRSEvidenceRecord);
            }
        }
    }

    private void addRecord(HashNode hashNode, ERSEvidenceRecord eRSEvidenceRecord) {
        List<ERSEvidenceRecord> list = this.recordMap.get(hashNode);
        if (list == null) {
            this.recordMap.put(hashNode, Collections.singletonList(eRSEvidenceRecord));
            return;
        }
        ArrayList arrayList = new ArrayList(list.size() + 1);
        arrayList.addAll(list);
        arrayList.add(eRSEvidenceRecord);
        this.recordMap.put(hashNode, arrayList);
    }

    @Override // org.bouncycastle.util.Store
    public Collection<ERSEvidenceRecord> getMatches(Selector<ERSEvidenceRecord> selector) throws StoreException {
        ArrayList arrayList;
        if (selector instanceof ERSEvidenceRecordSelector) {
            List<ERSEvidenceRecord> list = this.recordMap.get(new HashNode(((ERSEvidenceRecordSelector) selector).getData().getHash(this.digCalc, null)));
            if (list == null) {
                return Collections.EMPTY_LIST;
            }
            ArrayList arrayList2 = new ArrayList(list.size());
            for (int i = 0; i != list.size(); i++) {
                ERSEvidenceRecord eRSEvidenceRecord = list.get(i);
                if (selector.match(eRSEvidenceRecord)) {
                    arrayList2.add(eRSEvidenceRecord);
                }
            }
            return Collections.unmodifiableList(arrayList2);
        }
        if (selector == null) {
            HashSet hashSet = new HashSet(this.recordMap.size());
            Iterator<List<ERSEvidenceRecord>> it = this.recordMap.values().iterator();
            while (it.hasNext()) {
                hashSet.addAll(it.next());
            }
            arrayList = new ArrayList(hashSet);
        } else {
            HashSet hashSet2 = new HashSet();
            for (List<ERSEvidenceRecord> list2 : this.recordMap.values()) {
                for (int i2 = 0; i2 != list2.size(); i2++) {
                    if (selector.match(list2.get(i2))) {
                        hashSet2.add(list2.get(i2));
                    }
                }
            }
            arrayList = new ArrayList(hashSet2);
        }
        return Collections.unmodifiableList(arrayList);
    }
}

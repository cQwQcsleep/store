package org.bouncycastle.tsp.ers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SortedHashList {
    private static final Comparator<byte[]> hashComp = new ByteArrayComparator();
    private final LinkedList<byte[]> baseList = new LinkedList<>();

    public void add(byte[] bArr) {
        int size = this.baseList.size();
        LinkedList<byte[]> linkedList = this.baseList;
        if (size == 0) {
            linkedList.addFirst(bArr);
            return;
        }
        if (hashComp.compare(bArr, linkedList.get(0)) < 0) {
            this.baseList.addFirst(bArr);
            return;
        }
        int i = 1;
        while (i < this.baseList.size() && hashComp.compare(this.baseList.get(i), bArr) <= 0) {
            i++;
        }
        int size2 = this.baseList.size();
        LinkedList<byte[]> linkedList2 = this.baseList;
        if (i == size2) {
            linkedList2.add(bArr);
        } else {
            linkedList2.add(i, bArr);
        }
    }

    public byte[] getFirst() {
        return this.baseList.getFirst();
    }

    public int size() {
        return this.baseList.size();
    }

    public List<byte[]> toList() {
        return new ArrayList(this.baseList);
    }
}

package org.jcodings.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class ObjHash<K, V> extends Hash<V> {
    public V delete(K k) {
        Hash.HashEntry<V> hashEntry;
        K k2;
        K k3;
        int iHashValue = Hash.hashValue(k.hashCode());
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        ObjHashEntry objHashEntry = (ObjHashEntry) this.table[iBucketIndex];
        if (objHashEntry == null) {
            return null;
        }
        if (objHashEntry.hash == iHashValue && ((k3 = objHashEntry.key) == k || k.equals(k3))) {
            this.table[iBucketIndex] = objHashEntry.next;
            this.size--;
            objHashEntry.remove();
            return objHashEntry.value;
        }
        while (true) {
            hashEntry = objHashEntry.next;
            if (hashEntry == null) {
                return null;
            }
            if (hashEntry.hash == iHashValue && ((k2 = objHashEntry.key) == k || k.equals(k2))) {
                break;
            }
            objHashEntry = (ObjHashEntry) objHashEntry.next;
        }
        objHashEntry.next = objHashEntry.next.next;
        this.size--;
        hashEntry.remove();
        return hashEntry.value;
    }

    public V get(K k) {
        K k2;
        int iHashValue = Hash.hashValue(k.hashCode());
        Hash.HashEntry<V>[] hashEntryArr = this.table;
        Hash.HashEntry<V> hashEntry = hashEntryArr[Hash.bucketIndex(iHashValue, hashEntryArr.length)];
        while (true) {
            ObjHashEntry objHashEntry = (ObjHashEntry) hashEntry;
            if (objHashEntry == null) {
                return null;
            }
            if (objHashEntry.hash == iHashValue && ((k2 = objHashEntry.key) == k || k.equals(k2))) {
                return objHashEntry.value;
            }
            hashEntry = objHashEntry.next;
        }
    }

    @Override // org.jcodings.util.Hash
    public void init() {
        this.head = new ObjHashEntry();
    }

    public V put(K k, V v) {
        K k2;
        checkResize();
        int iHashValue = Hash.hashValue(k.hashCode());
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        Hash.HashEntry<V> hashEntry = this.table[iBucketIndex];
        while (true) {
            ObjHashEntry objHashEntry = (ObjHashEntry) hashEntry;
            if (objHashEntry == null) {
                Hash.HashEntry<V>[] hashEntryArr = this.table;
                hashEntryArr[iBucketIndex] = new ObjHashEntry(iHashValue, hashEntryArr[iBucketIndex], v, k, this.head);
                this.size++;
                return null;
            }
            if (objHashEntry.hash == iHashValue && ((k2 = objHashEntry.key) == k || k.equals(k2))) {
                objHashEntry.value = v;
                return v;
            }
            hashEntry = objHashEntry.next;
        }
    }

    public void putDirect(K k, V v) {
        checkResize();
        int iHashValue = Hash.hashValue(k.hashCode());
        int iBucketIndex = Hash.bucketIndex(iHashValue, this.table.length);
        Hash.HashEntry<V>[] hashEntryArr = this.table;
        hashEntryArr[iBucketIndex] = new ObjHashEntry(iHashValue, hashEntryArr[iBucketIndex], v, k, this.head);
        this.size++;
    }

    public static final class ObjHashEntry<K, V> extends Hash.HashEntry<V> {
        public final K key;

        public ObjHashEntry() {
            this.key = null;
        }

        public boolean equals(Object obj) {
            K k = this.key;
            if (k == obj) {
                return true;
            }
            return k.equals(obj);
        }

        public ObjHashEntry(int i, Hash.HashEntry<V> hashEntry, V v, K k, Hash.HashEntry<V> hashEntry2) {
            super(i, hashEntry, v, hashEntry2);
            this.key = k;
        }
    }
}

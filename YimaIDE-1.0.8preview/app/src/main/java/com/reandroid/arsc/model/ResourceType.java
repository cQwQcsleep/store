package com.reandroid.arsc.model;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.item.TypeString;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.pool.TypeStringPool;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.FilterIterator;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResourceType implements Comparable<ResourceType>, Iterable<ResourceEntry> {
    private final int id;
    private final PackageBlock packageBlock;

    public ResourceType(SpecTypePair specTypePair) {
        this(specTypePair.getPackageBlock(), specTypePair.getId());
    }

    private Iterator<ResourceEntry> getResources() {
        SpecTypePair specTypePair = getSpecTypePair();
        return specTypePair != null ? specTypePair.getResources() : EmptyIterator.of();
    }

    private SpecTypePair getSpecTypePair() {
        return getPackageBlock().getSpecTypePair(getId());
    }

    @Override // java.lang.Comparable
    public int compareTo(ResourceType resourceType) {
        if (resourceType == null) {
            return -1;
        }
        if (resourceType == this) {
            return 0;
        }
        int iCompare = CompareUtil.compare(getPackageId(), resourceType.getPackageId());
        return iCompare == 0 ? CompareUtil.compare(getId(), resourceType.getId()) : iCompare;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ResourceType) && getPackageBlock() == ((ResourceType) obj).getPackageBlock();
    }

    public ResourceEntry get(int i) {
        int id;
        if (i == 0 || (i >>> 24) != getPackageId() || (id = getId()) == 0 || ((i >> 16) & 255) != id || (65535 & i) > size()) {
            return null;
        }
        return new ResourceEntry(getPackageBlock(), i);
    }

    public int getId() {
        return this.id;
    }

    public int getIdentifier(String str) {
        return getPackageBlock().mo35getSpecStringPool().resolveResourceId(getId(), str);
    }

    public String getName() {
        return getPackageBlock().typeNameOf(getId());
    }

    public PackageBlock getPackageBlock() {
        return this.packageBlock;
    }

    public int getPackageId() {
        return getPackageBlock().getId();
    }

    public int hashCode() {
        return (getId() << 16) | (getPackageId() << 24);
    }

    public boolean isEmpty() {
        return size() == 0 || !iterator().hasNext();
    }

    @Override // java.lang.Iterable
    public Iterator<ResourceEntry> iterator() {
        return FilterIterator.of(getResources(), new Predicate() { // from class: hic
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ResourceEntry) obj).isDefined();
            }
        });
    }

    public void setName(String str) {
        TypeStringPool typeStringPool = getPackageBlock().getTypeStringPool();
        int id = getId();
        TypeString byId = typeStringPool.getById(id);
        if (byId == null) {
            typeStringPool.getOrCreate(id, str);
        } else {
            byId.set(str);
        }
    }

    public int size() {
        SpecTypePair specTypePair = getSpecTypePair();
        if (specTypePair != null) {
            return specTypePair.getHighestEntryCount();
        }
        return 0;
    }

    public String toString() {
        return getName();
    }

    public ResourceType(PackageBlock packageBlock, int i) {
        this.packageBlock = packageBlock;
        this.id = i;
    }
}

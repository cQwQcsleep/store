package com.reandroid.arsc.model;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.item.SpecString;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.arsc.value.ResValue;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.arsc.value.attribute.AttributeBag;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ResourceEntry implements Iterable<Entry> {
    private final PackageBlock packageBlock;
    private final int resourceId;

    public ResourceEntry(PackageBlock packageBlock, int i) {
        this.resourceId = i;
        this.packageBlock = packageBlock;
    }

    private ResourceEntry getResourceEntry(int i) {
        PackageBlock packageBlock = getPackageBlock();
        TableBlock tableBlock = packageBlock.getTableBlock();
        if (tableBlock == null) {
            return null;
        }
        return tableBlock.getResource(packageBlock, i);
    }

    private ResourceEntry resolveReference(Set<Integer> set) {
        ResValue resValue;
        ValueType valueType;
        ResourceEntry resourceEntryResolveReference;
        Entry entry = get();
        if (entry == null || (resValue = entry.getResValue()) == null || (valueType = resValue.getValueType()) == null || !valueType.isReference()) {
            return this;
        }
        int data = resValue.getData();
        if (data == 0 || set.contains(Integer.valueOf(data))) {
            return null;
        }
        set.add(Integer.valueOf(data));
        ResourceEntry resourceEntry = getResourceEntry(data);
        return (resourceEntry == null || (resourceEntryResolveReference = resourceEntry.resolveReference(set)) == null) ? resourceEntry : resourceEntryResolveReference;
    }

    public Entry any() {
        Iterator<Entry> it = iterator(true);
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public String buildReference(PackageBlock packageBlock, ValueType valueType) {
        String packageName;
        StringBuilder sb = new StringBuilder();
        if (valueType != null) {
            if (valueType == ValueType.REFERENCE) {
                sb.append('@');
            } else {
                sb.append('?');
            }
        }
        PackageBlock packageBlock2 = getPackageBlock();
        if (packageBlock != packageBlock2 && !packageBlock2.isEmpty() && (packageName = getPackageName()) != null) {
            sb.append(packageName);
            sb.append(':');
        }
        sb.append(getType());
        sb.append('/');
        sb.append(getName());
        return sb.toString();
    }

    public String decodeAttributeData(int i) {
        AttributeBag attributeBagCreate;
        Entry entry = get();
        if (entry == null || (attributeBagCreate = AttributeBag.create(entry.getResValueMapArray())) == null) {
            return null;
        }
        return attributeBagCreate.decodeAttributeValue(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ResourceEntry) && getResourceId() == ((ResourceEntry) obj).getResourceId();
    }

    public Entry get() {
        Entry entry = null;
        for (Entry entry2 : this) {
            if (entry2.isDefault()) {
                return entry2;
            }
            if (entry == null) {
                entry = entry2;
            }
        }
        return entry;
    }

    public Iterator<ResConfig> getConfigs() {
        return new ComputeIterator(iterator(false), new yhc());
    }

    public int getConfigsCount() {
        return CollectionUtil.count(iterator(true));
    }

    public Entry getEqualsOrMoreSpecific(ResConfig resConfig) {
        Entry entry = null;
        for (Entry entry2 : this) {
            if (resConfig.equals(entry2.getResConfig())) {
                return entry2;
            }
            if (entry == null && entry2.getResConfig().isEqualOrMoreSpecificThan(resConfig)) {
                entry = entry2;
            }
        }
        return entry;
    }

    public String getHexId() {
        return HexUtil.toHex8(getResourceId());
    }

    public ResourceEntry getLast() {
        SpecTypePair specTypePair = getPackageBlock().getSpecTypePair((getResourceId() >> 16) & 255);
        if (specTypePair != null) {
            return specTypePair.getResource(specTypePair.getHighestEntryId());
        }
        return null;
    }

    public String getName() {
        Iterator<Entry> it = iterator(false);
        while (it.hasNext()) {
            String name = it.next().getName();
            if (name != null) {
                return name;
            }
        }
        return null;
    }

    public Entry getOrCreate(ResConfig resConfig) {
        int resourceId = getResourceId();
        Entry orCreateEntry = getPackageBlock().getOrCreateEntry((byte) ((resourceId >> 16) & 255), (short) (resourceId & 65535), resConfig);
        String name = getName();
        if (name != null && orCreateEntry.getName() == null) {
            orCreateEntry.setName(name, true);
        }
        return orCreateEntry;
    }

    public PackageBlock getPackageBlock() {
        return this.packageBlock;
    }

    public String getPackageName() {
        return getPackageBlock().getName();
    }

    public int getResourceId() {
        return this.resourceId;
    }

    public Iterator<String> getStringValues(Entry entry) {
        TableBlock tableBlock;
        ResValue resValue = entry.getResValue();
        if (resValue == null) {
            return EmptyIterator.of();
        }
        ValueType valueType = resValue.getValueType();
        if (valueType == ValueType.STRING) {
            return SingleIterator.of(resValue.getValueAsString());
        }
        if (valueType.isReference() && (tableBlock = getPackageBlock().getTableBlock()) != null) {
            return getStringValues(tableBlock.resolveReference(resValue.getData()).iterator());
        }
        return EmptyIterator.of();
    }

    public String getType() {
        return getPackageBlock().typeNameOf((getResourceId() >> 16) & 255);
    }

    public int hashCode() {
        return getResourceId();
    }

    public boolean isContext(Block block) {
        if (block == null) {
            return false;
        }
        if (!(block instanceof TableBlock)) {
            return block instanceof PackageBlock ? isContext((PackageBlock) block) : isContext((PackageBlock) block.getParentInstance(PackageBlock.class));
        }
        PackageBlock packageBlock = getPackageBlock();
        return packageBlock != null && block == packageBlock.getTableBlock();
    }

    public boolean isDeclared() {
        return getName() != null;
    }

    public boolean isDefined() {
        return iterator(true).hasNext();
    }

    public boolean isEmpty() {
        return CollectionUtil.isEmpty(iterator(true));
    }

    public Iterator<Entry> iterator(Predicate<? super Entry> predicate) {
        return new FilterIterator(getPackageBlock().getEntries(getResourceId()), predicate);
    }

    public ResourceEntry next() {
        SpecTypePair specTypePair;
        int resourceId = getResourceId();
        int i = resourceId & 65535;
        if (i == 65535 || (specTypePair = getPackageBlock().getSpecTypePair((resourceId >> 16) & 255)) == null) {
            return null;
        }
        return specTypePair.getResource(i + 1);
    }

    public ResourceEntry previous() {
        int resourceId = getResourceId();
        int i = 65535 & resourceId;
        if (i == 0) {
            return null;
        }
        return new ResourceEntry(getPackageBlock(), (resourceId & (-65536)) | (i - 1));
    }

    public boolean serializePublicXml(XmlSerializer xmlSerializer) throws IOException {
        String name = getName();
        if (name == null) {
            return false;
        }
        xmlSerializer.text("\n  ");
        String str = PackageBlock.TAG_public;
        xmlSerializer.startTag(null, str);
        xmlSerializer.attribute(null, "id", getHexId());
        xmlSerializer.attribute(null, "type", getType());
        xmlSerializer.attribute(null, "name", name);
        xmlSerializer.endTag(null, str);
        return true;
    }

    public void setName(String str) {
        SpecString name = null;
        boolean z = false;
        for (Entry entry : this) {
            if (name != null) {
                entry.setSpecReference(name);
            } else {
                name = entry.setName(str);
                z = true;
            }
        }
        if (z) {
            return;
        }
        Iterator<Entry> it = iterator(false);
        if (it.hasNext()) {
            it.next().setName(str, true);
        }
    }

    public ResourceName toResourceName() {
        String name = getName();
        if (name == null) {
            return null;
        }
        return new ResourceName(getPackageName(), getType(), name);
    }

    public String toString() {
        String packageName = getPackageName();
        if (packageName == null) {
            return getHexId() + " @" + getType() + PsuedoNames.PSEUDONAME_ROOT + getName();
        }
        return getHexId() + " @" + packageName + ":" + getType() + PsuedoNames.PSEUDONAME_ROOT + getName();
    }

    public Iterator<Entry> iterator(boolean z) {
        return getPackageBlock().getEntries(getResourceId(), z);
    }

    @Override // java.lang.Iterable
    public Iterator<Entry> iterator() {
        return iterator(true);
    }

    public Entry get(ResConfig resConfig) {
        int resourceId = getResourceId();
        return getPackageBlock().getEntry(resConfig, (resourceId >> 16) & 255, resourceId & 65535);
    }

    public Entry get(String str) {
        return get(ResConfig.parse(str));
    }

    public Entry getOrCreate(String str) {
        return getOrCreate(ResConfig.parse(str));
    }

    public boolean isContext(PackageBlock packageBlock) {
        PackageBlock packageBlock2;
        if (packageBlock == null || (packageBlock2 = getPackageBlock()) == null) {
            return false;
        }
        return packageBlock2 == packageBlock || packageBlock2.getTableBlock() == packageBlock.getTableBlock();
    }

    public ResourceEntry resolveReference() {
        HashSet hashSet = new HashSet();
        hashSet.add(0);
        hashSet.add(Integer.valueOf(getResourceId()));
        ResourceEntry resourceEntryResolveReference = resolveReference(hashSet);
        return resourceEntryResolveReference != null ? resourceEntryResolveReference : this;
    }

    public Iterator<String> getStringValues(Iterator<Entry> it) {
        return new 1(this, it);
    }

    public String buildReference(PackageBlock packageBlock) {
        return buildReference(packageBlock, null);
    }

    public Iterator<String> getStringValues() {
        return getStringValues(iterator());
    }

    public String buildReference() {
        return buildReference(getPackageBlock(), null);
    }
}

package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.item.ResXmlID;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsStore;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlID extends IntegerItem implements Comparable<ResXmlID> {
    public static final Creator<ResXmlID> CREATOR = new Creator() { // from class: jfc
        public final Block newInstance() {
            return new ResXmlID();
        }
    };
    private Object mReferencedList;
    private ResXmlString mResXmlString;

    public void addReference(ReferenceItem referenceItem) {
        this.mReferencedList = ObjectsStore.add(this.mReferencedList, referenceItem);
    }

    @Override // java.lang.Comparable
    public int compareTo(ResXmlID resXmlID) {
        if (resXmlID == null) {
            return -1;
        }
        if (resXmlID == this) {
            return 0;
        }
        ResXmlString resXmlString = getResXmlString();
        ResXmlString resXmlString2 = resXmlID.getResXmlString();
        int iCompare = CompareUtil.compare(resXmlString == null, resXmlString2 == null);
        if (iCompare != 0) {
            return iCompare;
        }
        if (resXmlString == null || resXmlString2 == null) {
            return 0;
        }
        return CompareUtil.compare(resXmlString.getIndex(), resXmlString2.getIndex());
    }

    public String getName() {
        ResXmlString resXmlString = getResXmlString();
        if (resXmlString == null) {
            return null;
        }
        return resXmlString.getHtml();
    }

    public int getReferenceCount() {
        return ObjectsStore.size(this.mReferencedList);
    }

    public ResXmlString getResXmlString() {
        ResXmlString resXmlString = this.mResXmlString;
        if (resXmlString == null || resXmlString.getParent() == null) {
            return null;
        }
        return resXmlString;
    }

    public boolean hasReference(Block block) {
        if (block == null) {
            return false;
        }
        Iterator it = ObjectsStore.iterator(this.mReferencedList);
        while (it.hasNext()) {
            if (((ReferenceItem) it.next()).getReferredParent(block.getClass()) == block) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return getResXmlString() == null || get() == 0;
    }

    public void removeReference(ReferenceItem referenceItem) {
        this.mReferencedList = ObjectsStore.remove(this.mReferencedList, referenceItem);
    }

    public void setResXmlStringInternal(ResXmlString resXmlString) {
        this.mResXmlString = resXmlString;
    }

    @Override // com.reandroid.arsc.item.IntegerItem
    public String toString() {
        StringBuilder sb = new StringBuilder("USED-BY=");
        sb.append(getReferenceCount());
        sb.append('{');
        String name = getName();
        if (name != null) {
            sb.append(name);
        } else {
            sb.append(getIndex());
        }
        sb.append(':');
        sb.append(HexUtil.toHex8(get()));
        sb.append('}');
        return sb.toString();
    }

    public boolean hasReference() {
        return !ObjectsStore.isEmpty(this.mReferencedList);
    }
}

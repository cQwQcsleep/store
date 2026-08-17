package com.reandroid.dex.model;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.TypeString;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.value.Entry;
import com.reandroid.dex.data.FieldDef;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.collection.EmptyIterator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RField extends DexField implements Comparable<RField> {
    public RField(RClass rClass, FieldDef fieldDef) {
        super(rClass, fieldDef);
    }

    public static boolean isResourceIdValue(Key key) {
        if (!(key instanceof PrimitiveKey)) {
            return false;
        }
        PrimitiveKey primitiveKey = (PrimitiveKey) key;
        if (primitiveKey.isInteger()) {
            return PackageBlock.isResourceId(((PrimitiveKey.IntegerKey) primitiveKey).value());
        }
        return false;
    }

    public static Map<Integer, RField> mapRFields(Iterator<RField> it) {
        HashMap map = new HashMap();
        while (it.hasNext()) {
            RField next = it.next();
            map.put(Integer.valueOf(next.getResourceId()), next);
        }
        return map;
    }

    public static String sanitizeResourceName(String str) {
        if (str.charAt(0) == '$') {
            str = str.substring(1);
        }
        return str.replace('.', '_');
    }

    public static String toStyleResourceName(String str) {
        char cCharAt;
        int length = str.length();
        if (length < 3 || str.indexOf(95) < 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(length);
        int i = length - 1;
        for (int i2 = 0; i2 < i; i2++) {
            char cCharAt2 = str.charAt(i2);
            if (cCharAt2 == '_' && (cCharAt = str.charAt(i2 + 1)) <= 'Z' && cCharAt >= 'A') {
                cCharAt2 = '.';
            }
            sb.append(cCharAt2);
        }
        sb.append(str.charAt(i));
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(RField rField) {
        if (rField == null) {
            return -1;
        }
        return Integer.compare(getResourceId(), rField.getResourceId());
    }

    @Override // com.reandroid.dex.model.DexField
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && getResourceId() == ((RField) obj).getResourceId();
    }

    @Override // com.reandroid.dex.model.DexField, com.reandroid.dex.model.DexDeclaration
    public RClass getDexClass() {
        return (RClass) super.getDexClass();
    }

    public int getResourceId() {
        IntegerReference staticIntegerValue = getStaticIntegerValue();
        if (staticIntegerValue != null) {
            return staticIntegerValue.get();
        }
        return 0;
    }

    public String getResourceName() {
        String name = getName();
        return TypeString.isTypeStyle(getResourceType()) ? toStyleResourceName(name) : name;
    }

    public String getResourceType() {
        return getDexClass().getResourceType();
    }

    @Override // com.reandroid.dex.model.DexDeclaration
    public int hashCode() {
        return getResourceId();
    }

    public void serializePublicXml(XmlSerializer xmlSerializer) throws IOException {
        toResourceEntry().serializePublicXml(xmlSerializer);
    }

    public void setResourceId(int i) {
        setStaticValue(PrimitiveKey.of(i));
    }

    public String toJavaDeclare(boolean z) {
        StringBuilder sb = new StringBuilder(getName().length() + 38);
        sb.append("public static ");
        if (z) {
            sb.append("int ");
        }
        sb.append(getName());
        sb.append(" = ");
        sb.append(HexUtil.toHex8(getResourceId()));
        sb.append(';');
        return sb.toString();
    }

    public ResourceEntry toResourceEntry() {
        return new DexResourceEntry(this);
    }

    @Override // com.reandroid.dex.model.DexDeclaration, com.reandroid.dex.model.Dex
    public String toString() {
        return toJavaDeclare();
    }

    public static class DexResourceEntry extends ResourceEntry {
        private final RField rField;

        public DexResourceEntry(RField rField) {
            super(RClass.EMPTY_TABLE.pickOrEmptyPackage(), rField.getResourceId());
            this.rField = rField;
        }

        @Override // com.reandroid.arsc.model.ResourceEntry
        public String getName() {
            return getRField().getResourceName();
        }

        @Override // com.reandroid.arsc.model.ResourceEntry
        public PackageBlock getPackageBlock() {
            return RClass.EMPTY_TABLE.pickOrEmptyPackage();
        }

        @Override // com.reandroid.arsc.model.ResourceEntry
        public String getPackageName() {
            return null;
        }

        public RField getRField() {
            return this.rField;
        }

        @Override // com.reandroid.arsc.model.ResourceEntry
        public int getResourceId() {
            return getRField().getResourceId();
        }

        @Override // com.reandroid.arsc.model.ResourceEntry
        public String getType() {
            return this.rField.getResourceType();
        }

        @Override // com.reandroid.arsc.model.ResourceEntry
        public boolean isEmpty() {
            return true;
        }

        @Override // com.reandroid.arsc.model.ResourceEntry
        public Iterator<Entry> iterator(boolean z) {
            return EmptyIterator.of();
        }

        @Override // com.reandroid.arsc.model.ResourceEntry
        public Iterator<Entry> iterator(Predicate<? super Entry> predicate) {
            return EmptyIterator.of();
        }
    }

    public String toJavaDeclare() {
        return toJavaDeclare(true);
    }
}

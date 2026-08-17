package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.xml.internal.stream.XMLBufferListener;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLAttributesImpl implements XMLAttributes, XMLBufferListener {
    protected static final int MAX_HASH_COLLISIONS = 40;
    protected static final int MULTIPLIERS_MASK = 31;
    protected static final int MULTIPLIERS_SIZE = 32;
    protected static final int SIZE_LIMIT = 20;
    protected static final int TABLE_SIZE = 101;
    protected Attribute[] fAttributeTableView;
    protected int[] fAttributeTableViewChainState;
    protected Attribute[] fAttributes;
    protected int[] fHashMultipliers;
    protected boolean fIsTableViewConsistent;
    protected int fLargeCount;
    protected int fLength;
    protected boolean fNamespaces;
    protected int fTableViewBuckets;

    public static class Attribute {
        public Attribute next;
        public String nonNormalizedValue;
        public boolean specified;
        public String type;
        public String value;
        public XMLString xmlValue;
        public final QName name = new QName();
        public Augmentations augs = new AugmentationsImpl();
    }

    public XMLAttributesImpl(int i) {
        this.fNamespaces = true;
        this.fLargeCount = 1;
        this.fAttributes = new Attribute[4];
        this.fTableViewBuckets = i;
        int i2 = 0;
        while (true) {
            Attribute[] attributeArr = this.fAttributes;
            if (i2 >= attributeArr.length) {
                return;
            }
            attributeArr[i2] = new Attribute();
            i2++;
        }
    }

    private QName checkManyDuplicatesNS() {
        this.fIsTableViewConsistent = false;
        prepareTableView();
        int i = this.fLength;
        Attribute[] attributeArr = this.fAttributes;
        Attribute[] attributeArr2 = this.fAttributeTableView;
        int[] iArr = this.fAttributeTableViewChainState;
        int i2 = this.fLargeCount;
        for (int i3 = 0; i3 < i; i3++) {
            Attribute attribute = attributeArr[i3];
            QName qName = attribute.name;
            int tableViewBucket = getTableViewBucket(qName.localpart, qName.uri);
            if (iArr[tableViewBucket] != i2) {
                iArr[tableViewBucket] = i2;
                attribute.next = null;
                attributeArr2[tableViewBucket] = attribute;
            } else {
                Attribute attribute2 = attributeArr2[tableViewBucket];
                int i4 = 0;
                while (attribute2 != null) {
                    QName qName2 = attribute2.name;
                    String str = qName2.localpart;
                    QName qName3 = attribute.name;
                    if (str == qName3.localpart && qName2.uri == qName3.uri) {
                        return qName3;
                    }
                    attribute2 = attribute2.next;
                    i4++;
                }
                if (i4 >= 40) {
                    rebalanceTableViewNS(i3 + 1);
                    i2 = this.fLargeCount;
                } else {
                    attribute.next = attributeArr2[tableViewBucket];
                    attributeArr2[tableViewBucket] = attribute;
                }
            }
        }
        return null;
    }

    private String getReportableType(String str) {
        return str.charAt(0) == '(' ? SchemaSymbols.ATTVAL_NMTOKEN : str;
    }

    private void growTableView() {
        int i = this.fLength;
        int i2 = this.fTableViewBuckets;
        do {
            i2 = (i2 << 1) + 1;
            if (i2 < 0) {
                i2 = Integer.MAX_VALUE;
                break;
            }
        } while (i > i2);
        this.fTableViewBuckets = i2;
        this.fAttributeTableView = null;
        this.fLargeCount = 1;
    }

    private int hash(String str, String str2) {
        return this.fHashMultipliers == null ? str.hashCode() + (str2.hashCode() * 31) : hash0(str) + (hash0(str2) * this.fHashMultipliers[32]);
    }

    private int hash0(String str) {
        int length = str.length();
        int[] iArr = this.fHashMultipliers;
        int iCharAt = 0;
        for (int i = 0; i < length; i++) {
            iCharAt = (iCharAt * iArr[i & 31]) + str.charAt(i);
        }
        return iCharAt;
    }

    private void prepareAndPopulateTableView(int i) {
        prepareTableView();
        for (int i2 = 0; i2 < i; i2++) {
            Attribute attribute = this.fAttributes[i2];
            int tableViewBucket = getTableViewBucket(attribute.name.rawname);
            int[] iArr = this.fAttributeTableViewChainState;
            int i3 = iArr[tableViewBucket];
            int i4 = this.fLargeCount;
            if (i3 != i4) {
                iArr[tableViewBucket] = i4;
                attribute.next = null;
                this.fAttributeTableView[tableViewBucket] = attribute;
            } else {
                Attribute[] attributeArr = this.fAttributeTableView;
                attribute.next = attributeArr[tableViewBucket];
                attributeArr[tableViewBucket] = attribute;
            }
        }
    }

    private void prepareAndPopulateTableViewNS(int i) {
        prepareTableView();
        for (int i2 = 0; i2 < i; i2++) {
            Attribute attribute = this.fAttributes[i2];
            QName qName = attribute.name;
            int tableViewBucket = getTableViewBucket(qName.localpart, qName.uri);
            int[] iArr = this.fAttributeTableViewChainState;
            int i3 = iArr[tableViewBucket];
            int i4 = this.fLargeCount;
            if (i3 != i4) {
                iArr[tableViewBucket] = i4;
                attribute.next = null;
                this.fAttributeTableView[tableViewBucket] = attribute;
            } else {
                Attribute[] attributeArr = this.fAttributeTableView;
                attribute.next = attributeArr[tableViewBucket];
                attributeArr[tableViewBucket] = attribute;
            }
        }
    }

    private void rebalanceTableView(int i) {
        if (this.fHashMultipliers == null) {
            this.fHashMultipliers = new int[33];
        }
        PrimeNumberSequenceGenerator.generateSequence(this.fHashMultipliers);
        prepareAndPopulateTableView(i);
    }

    private void rebalanceTableViewNS(int i) {
        if (this.fHashMultipliers == null) {
            this.fHashMultipliers = new int[33];
        }
        PrimeNumberSequenceGenerator.generateSequence(this.fHashMultipliers);
        prepareAndPopulateTableViewNS(i);
    }

    public int addAttribute(QName qName, String str, String str2, XMLString xMLString) {
        int indexFast;
        int i;
        int i2;
        if (this.fLength < 20) {
            String str3 = qName.uri;
            indexFast = (str3 == null || str3.length() == 0) ? getIndexFast(qName.rawname) : getIndexFast(qName.uri, qName.localpart);
            if (indexFast == -1) {
                indexFast = this.fLength;
                this.fLength = indexFast + 1;
                Attribute[] attributeArr = this.fAttributes;
                if (indexFast == attributeArr.length) {
                    int length = attributeArr.length + 4;
                    Attribute[] attributeArr2 = new Attribute[length];
                    System.arraycopy(attributeArr, 0, attributeArr2, 0, attributeArr.length);
                    for (int length2 = this.fAttributes.length; length2 < length; length2++) {
                        attributeArr2[length2] = new Attribute();
                    }
                    this.fAttributes = attributeArr2;
                }
            }
        } else {
            String str4 = qName.uri;
            if (str4 == null || str4.length() == 0 || (indexFast = getIndexFast(qName.uri, qName.localpart)) == -1) {
                if (!this.fIsTableViewConsistent || (i2 = this.fLength) == 20 || (i2 > 20 && i2 > this.fTableViewBuckets)) {
                    prepareAndPopulateTableView();
                    this.fIsTableViewConsistent = true;
                }
                int tableViewBucket = getTableViewBucket(qName.rawname);
                if (this.fAttributeTableViewChainState[tableViewBucket] != this.fLargeCount) {
                    i = this.fLength;
                    this.fLength = i + 1;
                    Attribute[] attributeArr3 = this.fAttributes;
                    if (i == attributeArr3.length) {
                        int length3 = attributeArr3.length << 1;
                        Attribute[] attributeArr4 = new Attribute[length3];
                        System.arraycopy(attributeArr3, 0, attributeArr4, 0, attributeArr3.length);
                        for (int length4 = this.fAttributes.length; length4 < length3; length4++) {
                            attributeArr4[length4] = new Attribute();
                        }
                        this.fAttributes = attributeArr4;
                    }
                    this.fAttributeTableViewChainState[tableViewBucket] = this.fLargeCount;
                    Attribute attribute = this.fAttributes[i];
                    attribute.next = null;
                    this.fAttributeTableView[tableViewBucket] = attribute;
                } else {
                    Attribute attribute2 = this.fAttributeTableView[tableViewBucket];
                    int i3 = 0;
                    while (attribute2 != null && attribute2.name.rawname != qName.rawname) {
                        attribute2 = attribute2.next;
                        i3++;
                    }
                    if (attribute2 == null) {
                        i = this.fLength;
                        this.fLength = i + 1;
                        Attribute[] attributeArr5 = this.fAttributes;
                        if (i == attributeArr5.length) {
                            int length5 = attributeArr5.length << 1;
                            Attribute[] attributeArr6 = new Attribute[length5];
                            System.arraycopy(attributeArr5, 0, attributeArr6, 0, attributeArr5.length);
                            for (int length6 = this.fAttributes.length; length6 < length5; length6++) {
                                attributeArr6[length6] = new Attribute();
                            }
                            this.fAttributes = attributeArr6;
                        }
                        Attribute[] attributeArr7 = this.fAttributes;
                        if (i3 >= 40) {
                            attributeArr7[i].name.setValues(qName);
                            rebalanceTableView(this.fLength);
                        } else {
                            Attribute attribute3 = attributeArr7[i];
                            Attribute[] attributeArr8 = this.fAttributeTableView;
                            attribute3.next = attributeArr8[tableViewBucket];
                            attributeArr8[tableViewBucket] = attribute3;
                        }
                    } else {
                        indexFast = getIndexFast(qName.rawname);
                    }
                }
                indexFast = i;
            }
        }
        Attribute attribute4 = this.fAttributes[indexFast];
        attribute4.name.setValues(qName);
        attribute4.type = str;
        attribute4.value = str2;
        attribute4.xmlValue = xMLString;
        attribute4.nonNormalizedValue = str2;
        attribute4.specified = false;
        Augmentations augmentations = attribute4.augs;
        if (augmentations != null) {
            augmentations.removeAllItems();
        }
        return indexFast;
    }

    public void addAttributeNS(QName qName, String str, String str2) {
        int i = this.fLength;
        int i2 = i + 1;
        this.fLength = i2;
        Attribute[] attributeArr = this.fAttributes;
        if (i == attributeArr.length) {
            Attribute[] attributeArr2 = i2 < 20 ? new Attribute[attributeArr.length + 4] : new Attribute[attributeArr.length << 1];
            System.arraycopy(attributeArr, 0, attributeArr2, 0, attributeArr.length);
            for (int length = this.fAttributes.length; length < attributeArr2.length; length++) {
                attributeArr2[length] = new Attribute();
            }
            this.fAttributes = attributeArr2;
        }
        Attribute attribute = this.fAttributes[i];
        attribute.name.setValues(qName);
        attribute.type = str;
        attribute.value = str2;
        attribute.nonNormalizedValue = str2;
        attribute.specified = false;
        attribute.augs.removeAllItems();
    }

    public QName checkDuplicatesNS() {
        int i = this.fLength;
        if (i > 20) {
            return checkManyDuplicatesNS();
        }
        Attribute[] attributeArr = this.fAttributes;
        int i2 = 0;
        while (i2 < i - 1) {
            Attribute attribute = attributeArr[i2];
            i2++;
            for (int i3 = i2; i3 < i; i3++) {
                Attribute attribute2 = attributeArr[i3];
                QName qName = attribute.name;
                String str = qName.localpart;
                QName qName2 = attribute2.name;
                if (str == qName2.localpart && qName.uri == qName2.uri) {
                    return qName2;
                }
            }
        }
        return null;
    }

    public void cleanTableView() {
        int i = this.fLargeCount + 1;
        this.fLargeCount = i;
        if (i < 0) {
            if (this.fAttributeTableViewChainState != null) {
                for (int i2 = this.fTableViewBuckets - 1; i2 >= 0; i2--) {
                    this.fAttributeTableViewChainState[i2] = 0;
                }
            }
            this.fLargeCount = 1;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public Augmentations getAugmentations(String str, String str2) {
        int index = getIndex(str, str2);
        if (index != -1) {
            return this.fAttributes[index].augs;
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public int getIndex(String str, String str2) {
        String str3;
        for (int i = 0; i < this.fLength; i++) {
            Attribute attribute = this.fAttributes[i];
            String str4 = attribute.name.localpart;
            if (str4 != null && str4.equals(str2) && (str == (str3 = attribute.name.uri) || (str != null && str3 != null && str3.equals(str)))) {
                return i;
            }
        }
        return -1;
    }

    public int getIndexByLocalName(String str) {
        for (int i = 0; i < this.fLength; i++) {
            String str2 = this.fAttributes[i].name.localpart;
            if (str2 != null && str2.equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public int getIndexFast(String str, String str2) {
        for (int i = 0; i < this.fLength; i++) {
            QName qName = this.fAttributes[i].name;
            if (qName.localpart == str2 && qName.uri == str) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public int getLength() {
        return this.fLength;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public String getLocalName(int i) {
        if (!this.fNamespaces) {
            return "";
        }
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return this.fAttributes[i].name.localpart;
    }

    public String getName(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return this.fAttributes[i].name.rawname;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public String getNonNormalizedValue(int i) {
        return this.fAttributes[i].nonNormalizedValue;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public String getPrefix(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        String str = this.fAttributes[i].name.prefix;
        return str != null ? str : "";
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public String getQName(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        String str = this.fAttributes[i].name.rawname;
        return str != null ? str : "";
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public QName getQualifiedName(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return this.fAttributes[i].name;
    }

    public int getTableViewBucket(String str, String str2) {
        return str2 == null ? (hash(str) & Integer.MAX_VALUE) % this.fTableViewBuckets : (hash(str, str2) & Integer.MAX_VALUE) % this.fTableViewBuckets;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public String getType(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return getReportableType(this.fAttributes[i].type);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public String getURI(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return this.fAttributes[i].name.uri;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public String getValue(int i) {
        XMLString xMLString;
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        Attribute attribute = this.fAttributes[i];
        if (attribute.value == null && (xMLString = attribute.xmlValue) != null) {
            attribute.value = xMLString.toString();
        }
        return this.fAttributes[i].value;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public boolean isSpecified(int i) {
        return this.fAttributes[i].specified;
    }

    public void prepareTableView() {
        if (this.fLength > this.fTableViewBuckets) {
            growTableView();
        }
        if (this.fAttributeTableView != null) {
            cleanTableView();
            return;
        }
        int i = this.fTableViewBuckets;
        this.fAttributeTableView = new Attribute[i];
        this.fAttributeTableViewChainState = new int[i];
    }

    @Override // com.sun.xml.internal.stream.XMLBufferListener
    public void refresh() {
        if (this.fLength > 0) {
            for (int i = 0; i < this.fLength; i++) {
                getValue(i);
            }
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public void removeAllAttributes() {
        this.fLength = 0;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public void removeAttributeAt(int i) {
        this.fIsTableViewConsistent = false;
        int i2 = this.fLength;
        if (i < i2 - 1) {
            Attribute[] attributeArr = this.fAttributes;
            Attribute attribute = attributeArr[i];
            System.arraycopy(attributeArr, i + 1, attributeArr, i, (i2 - i) - 1);
            this.fAttributes[this.fLength - 1] = attribute;
        }
        this.fLength--;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public void setAugmentations(int i, Augmentations augmentations) {
        this.fAttributes[i].augs = augmentations;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public void setName(int i, QName qName) {
        this.fAttributes[i].name.setValues(qName);
    }

    public void setNamespaces(boolean z) {
        this.fNamespaces = z;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public void setNonNormalizedValue(int i, String str) {
        if (str == null) {
            str = this.fAttributes[i].value;
        }
        this.fAttributes[i].nonNormalizedValue = str;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public void setSpecified(int i, boolean z) {
        this.fAttributes[i].specified = z;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public void setType(int i, String str) {
        this.fAttributes[i].type = str;
    }

    public void setURI(int i, String str) {
        this.fAttributes[i].name.uri = str;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public void setValue(int i, String str, XMLString xMLString) {
        Attribute attribute = this.fAttributes[i];
        attribute.value = str;
        attribute.nonNormalizedValue = str;
        attribute.xmlValue = xMLString;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public void setValue(int i, String str) {
        setValue(i, str, null);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public Augmentations getAugmentations(String str) {
        int index = getIndex(str);
        if (index != -1) {
            return this.fAttributes[index].augs;
        }
        return null;
    }

    @Override // com.sun.xml.internal.stream.XMLBufferListener
    public void refresh(int i) {
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public Augmentations getAugmentations(int i) {
        if (i < 0 || i >= this.fLength) {
            return null;
        }
        return this.fAttributes[i].augs;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public void getName(int i, QName qName) {
        qName.setValues(this.fAttributes[i].name);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public String getType(String str) {
        int index = getIndex(str);
        if (index != -1) {
            return getReportableType(this.fAttributes[index].type);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public String getType(String str, String str2) {
        int index;
        if (this.fNamespaces && (index = getIndex(str, str2)) != -1) {
            return getType(index);
        }
        return null;
    }

    public int getTableViewBucket(String str) {
        return (hash(str) & Integer.MAX_VALUE) % this.fTableViewBuckets;
    }

    public int getIndexFast(String str) {
        for (int i = 0; i < this.fLength; i++) {
            if (this.fAttributes[i].name.rawname == str) {
                return i;
            }
        }
        return -1;
    }

    public XMLAttributesImpl() {
        this(101);
    }

    private int hash(String str) {
        if (this.fHashMultipliers == null) {
            return str.hashCode();
        }
        return hash0(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public String getValue(String str) {
        int index = getIndex(str);
        if (index == -1) {
            return null;
        }
        Attribute attribute = this.fAttributes[index];
        if (attribute.value == null) {
            attribute.value = attribute.xmlValue.toString();
        }
        return this.fAttributes[index].value;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public String getValue(String str, String str2) {
        int index = getIndex(str, str2);
        if (index != -1) {
            return getValue(index);
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public int getIndex(String str) {
        for (int i = 0; i < this.fLength; i++) {
            String str2 = this.fAttributes[i].name.rawname;
            if (str2 != null && str2.equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public void prepareAndPopulateTableView() {
        prepareAndPopulateTableView(this.fLength);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLAttributes
    public int addAttribute(QName qName, String str, String str2) {
        return addAttribute(qName, str, str2, null);
    }
}

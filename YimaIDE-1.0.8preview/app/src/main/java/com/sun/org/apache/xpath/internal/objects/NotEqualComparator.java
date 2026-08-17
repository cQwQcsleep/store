package com.sun.org.apache.xpath.internal.objects;

import com.sun.org.apache.xml.internal.utils.XMLString;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class NotEqualComparator extends Comparator {
    @Override // com.sun.org.apache.xpath.internal.objects.Comparator
    public boolean compareNumbers(double d, double d2) {
        return d != d2;
    }

    @Override // com.sun.org.apache.xpath.internal.objects.Comparator
    public boolean compareStrings(XMLString xMLString, XMLString xMLString2) {
        return !xMLString.equals(xMLString2);
    }
}

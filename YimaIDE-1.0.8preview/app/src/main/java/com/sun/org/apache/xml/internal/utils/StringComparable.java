package com.sun.org.apache.xml.internal.utils;

import java.text.CollationElementIterator;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringComparable implements Comparable<StringComparable> {
    public static final int LOWER_CASE = 2;
    public static final int UNKNOWN_CASE = -1;
    public static final int UPPER_CASE = 1;
    private String m_caseOrder;
    private RuleBasedCollator m_collator;
    private Locale m_locale;
    private int m_mask;
    private String m_text;

    public StringComparable(String str, Locale locale, Collator collator, String str2) {
        this.m_mask = -1;
        this.m_text = str;
        this.m_locale = locale;
        RuleBasedCollator ruleBasedCollator = (RuleBasedCollator) collator;
        this.m_collator = ruleBasedCollator;
        this.m_caseOrder = str2;
        this.m_mask = getMask(ruleBasedCollator.getStrength());
    }

    private final int getCaseDiff(String str, String str2) {
        int strength = this.m_collator.getStrength();
        int decomposition = this.m_collator.getDecomposition();
        this.m_collator.setStrength(2);
        this.m_collator.setDecomposition(1);
        int[] firstCaseDiff = getFirstCaseDiff(str, str2, this.m_locale);
        this.m_collator.setStrength(strength);
        this.m_collator.setDecomposition(decomposition);
        if (firstCaseDiff == null) {
            return 0;
        }
        if (this.m_caseOrder.equals(com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_CASEORDER_UPPER)) {
            return firstCaseDiff[0] == 1 ? -1 : 1;
        }
        return firstCaseDiff[0] == 2 ? -1 : 1;
    }

    public static final Comparable getComparator(String str, Locale locale, Collator collator, String str2) {
        return (str2 == null || str2.length() == 0) ? ((RuleBasedCollator) collator).getCollationKey(str) : new StringComparable(str, locale, collator, str2);
    }

    private final int getElement(int i) {
        return this.m_mask & i;
    }

    private final int[] getFirstCaseDiff(String str, String str2, Locale locale) {
        CollationElementIterator collationElementIterator;
        int i;
        boolean z;
        int i2;
        String str3 = str;
        String str4 = str2;
        CollationElementIterator collationElementIterator2 = this.m_collator.getCollationElementIterator(str3);
        CollationElementIterator collationElementIterator3 = this.m_collator.getCollationElementIterator(str4);
        int i3 = -1;
        int element = getElement(-1);
        int offset = -1;
        int offset2 = -1;
        int offset3 = -1;
        int offset4 = -1;
        boolean z2 = true;
        boolean z3 = true;
        int element2 = 0;
        int element3 = 0;
        while (true) {
            if (z2) {
                offset = collationElementIterator3.getOffset();
                element2 = getElement(collationElementIterator3.next());
                offset2 = collationElementIterator3.getOffset();
            }
            if (z3) {
                offset3 = collationElementIterator2.getOffset();
                element3 = getElement(collationElementIterator2.next());
                offset4 = collationElementIterator2.getOffset();
            }
            offset3 = offset3;
            offset4 = offset4;
            if (element2 == element || element3 == element) {
                return null;
            }
            if (element3 == 0) {
                z2 = false;
                z3 = true;
            } else if (element2 == 0) {
                z2 = true;
                z3 = false;
            } else {
                if (element3 == element2 || offset >= offset2 || offset3 >= offset4) {
                    collationElementIterator = collationElementIterator2;
                    i = i3;
                    z = true;
                } else {
                    i = i3;
                    String strSubstring = str3.substring(offset3, offset4);
                    String strSubstring2 = str4.substring(offset, offset2);
                    String upperCase = strSubstring.toUpperCase(locale);
                    String upperCase2 = strSubstring2.toUpperCase(locale);
                    if (this.m_collator.compare(upperCase, upperCase2) != 0) {
                        collationElementIterator = collationElementIterator2;
                        z = true;
                    } else {
                        collationElementIterator = collationElementIterator2;
                        int[] iArr = {i, i};
                        if (this.m_collator.compare(strSubstring, upperCase) == 0) {
                            iArr[0] = 1;
                        } else if (this.m_collator.compare(strSubstring, strSubstring.toLowerCase(locale)) == 0) {
                            iArr[0] = 2;
                        }
                        if (this.m_collator.compare(strSubstring2, upperCase2) == 0) {
                            iArr[1] = 1;
                        } else if (this.m_collator.compare(strSubstring2, strSubstring2.toLowerCase(locale)) == 0) {
                            iArr[1] = 2;
                        }
                        int i4 = iArr[0];
                        z = true;
                        if (i4 == 1) {
                            i2 = 2;
                            if (iArr[1] != 2) {
                            }
                            return iArr;
                        }
                        i2 = 2;
                        if (i4 == i2 && iArr[1] == 1) {
                            return iArr;
                        }
                    }
                }
                str3 = str;
                i3 = i;
                collationElementIterator2 = collationElementIterator;
                z3 = z;
                str4 = str2;
                z2 = z3;
            }
        }
    }

    private static final int getMask(int i) {
        if (i != 0) {
            return i != 1 ? -1 : -256;
        }
        return -65536;
    }

    @Override // java.lang.Comparable
    public int compareTo(StringComparable stringComparable) {
        int iCompare;
        String string = stringComparable.toString();
        if (this.m_text.equals(string)) {
            return 0;
        }
        int strength = this.m_collator.getStrength();
        if (strength == 0 || strength == 1) {
            iCompare = this.m_collator.compare(this.m_text, string);
        } else {
            this.m_collator.setStrength(1);
            iCompare = this.m_collator.compare(this.m_text, string);
            this.m_collator.setStrength(strength);
        }
        if (iCompare != 0) {
            return iCompare;
        }
        int caseDiff = getCaseDiff(this.m_text, string);
        return caseDiff != 0 ? caseDiff : this.m_collator.compare(this.m_text, string);
    }

    public final String toString() {
        return this.m_text;
    }
}

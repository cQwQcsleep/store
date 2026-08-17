package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.utils.IntVector;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTMStringPool {
    static final int HASHPRIME = 101;
    public static final int NULL = -1;
    IntVector m_hashChain;
    int[] m_hashStart;
    List<String> m_intToString;

    public DTMStringPool(int i) {
        this.m_hashStart = new int[101];
        this.m_intToString = new ArrayList();
        this.m_hashChain = new IntVector(i);
        removeAllElements();
        stringToIndex("");
    }

    public static void _main(String[] strArr) {
        String[] strArr2 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty", "Twenty-One", "Twenty-Two", "Twenty-Three", "Twenty-Four", "Twenty-Five", "Twenty-Six", "Twenty-Seven", "Twenty-Eight", "Twenty-Nine", "Thirty", "Thirty-One", "Thirty-Two", "Thirty-Three", "Thirty-Four", "Thirty-Five", "Thirty-Six", "Thirty-Seven", "Thirty-Eight", "Thirty-Nine"};
        DTMStringPool dTMStringPool = new DTMStringPool();
        System.out.println("If no complaints are printed below, we passed initial test.");
        for (int i = 0; i <= 1; i++) {
            for (int i2 = 0; i2 < 40; i2++) {
                int iStringToIndex = dTMStringPool.stringToIndex(strArr2[i2]);
                if (iStringToIndex != i2) {
                    System.out.println("\tMismatch populating pool: assigned " + iStringToIndex + " for create " + i2);
                }
            }
            for (int i3 = 0; i3 < 40; i3++) {
                int iStringToIndex2 = dTMStringPool.stringToIndex(strArr2[i3]);
                if (iStringToIndex2 != i3) {
                    System.out.println("\tMismatch in stringToIndex: returned " + iStringToIndex2 + " for lookup " + i3);
                }
            }
            for (int i4 = 0; i4 < 40; i4++) {
                String strIndexToString = dTMStringPool.indexToString(i4);
                if (!strArr2[i4].equals(strIndexToString)) {
                    System.out.println("\tMismatch in indexToString: returned" + strIndexToString + " for lookup " + i4);
                }
            }
            dTMStringPool.removeAllElements();
            System.out.println("\nPass " + i + " complete\n");
        }
    }

    public String indexToString(int i) throws IndexOutOfBoundsException {
        if (i == -1) {
            return null;
        }
        return this.m_intToString.get(i);
    }

    public void removeAllElements() {
        this.m_intToString.clear();
        for (int i = 0; i < 101; i++) {
            this.m_hashStart[i] = -1;
        }
        this.m_hashChain.removeAllElements();
    }

    public int stringToIndex(String str) {
        if (str == null) {
            return -1;
        }
        int iHashCode = str.hashCode() % 101;
        if (iHashCode < 0) {
            iHashCode = -iHashCode;
        }
        int iElementAt = this.m_hashStart[iHashCode];
        int i = iElementAt;
        while (true) {
            List<String> list = this.m_intToString;
            if (iElementAt == -1) {
                int size = list.size();
                this.m_intToString.add(str);
                this.m_hashChain.addElement(-1);
                if (i == -1) {
                    this.m_hashStart[iHashCode] = size;
                    return size;
                }
                this.m_hashChain.setElementAt(size, i);
                return size;
            }
            if (list.get(iElementAt).equals(str)) {
                return iElementAt;
            }
            i = iElementAt;
            iElementAt = this.m_hashChain.elementAt(iElementAt);
        }
    }

    public DTMStringPool() {
        this(512);
    }
}

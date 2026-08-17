package com.sun.org.apache.xml.internal.dtm.ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTMSafeStringPool extends DTMStringPool {
    public static void _main(String[] strArr) {
        String[] strArr2 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty", "Twenty-One", "Twenty-Two", "Twenty-Three", "Twenty-Four", "Twenty-Five", "Twenty-Six", "Twenty-Seven", "Twenty-Eight", "Twenty-Nine", "Thirty", "Thirty-One", "Thirty-Two", "Thirty-Three", "Thirty-Four", "Thirty-Five", "Thirty-Six", "Thirty-Seven", "Thirty-Eight", "Thirty-Nine"};
        DTMSafeStringPool dTMSafeStringPool = new DTMSafeStringPool();
        System.out.println("If no complaints are printed below, we passed initial test.");
        for (int i = 0; i <= 1; i++) {
            for (int i2 = 0; i2 < 40; i2++) {
                int iStringToIndex = dTMSafeStringPool.stringToIndex(strArr2[i2]);
                if (iStringToIndex != i2) {
                    System.out.println("\tMismatch populating pool: assigned " + iStringToIndex + " for create " + i2);
                }
            }
            for (int i3 = 0; i3 < 40; i3++) {
                int iStringToIndex2 = dTMSafeStringPool.stringToIndex(strArr2[i3]);
                if (iStringToIndex2 != i3) {
                    System.out.println("\tMismatch in stringToIndex: returned " + iStringToIndex2 + " for lookup " + i3);
                }
            }
            for (int i4 = 0; i4 < 40; i4++) {
                String strIndexToString = dTMSafeStringPool.indexToString(i4);
                if (!strArr2[i4].equals(strIndexToString)) {
                    System.out.println("\tMismatch in indexToString: returned" + strIndexToString + " for lookup " + i4);
                }
            }
            dTMSafeStringPool.removeAllElements();
            System.out.println("\nPass " + i + " complete\n");
        }
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMStringPool
    public synchronized String indexToString(int i) throws ArrayIndexOutOfBoundsException {
        return super.indexToString(i);
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMStringPool
    public synchronized void removeAllElements() {
        super.removeAllElements();
    }

    @Override // com.sun.org.apache.xml.internal.dtm.ref.DTMStringPool
    public synchronized int stringToIndex(String str) {
        return super.stringToIndex(str);
    }
}

package com.reandroid.arsc.chunk;

import com.reandroid.apk.xmlencoder.EncodeException;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class PolicyFlag {
    public static final PolicyFlag ACTOR_SIGNATURE;
    public static final String ATTR_type = ObjectsUtil.of("type");
    public static final PolicyFlag CONFIG_SIGNATURE;
    public static final PolicyFlag ODM_PARTITION;
    public static final PolicyFlag OEM_PARTITION;
    public static final PolicyFlag PRODUCT_PARTITION;
    public static final PolicyFlag PUBLIC;
    public static final PolicyFlag SIGNATURE;
    public static final PolicyFlag SYSTEM_PARTITION;
    private static final PolicyFlag[] VALUES;
    public static final PolicyFlag VENDOR_PARTITION;
    private static final Map<String, PolicyFlag> nameMap;
    private final int flag;
    private final String name;

    static {
        PolicyFlag policyFlag = new PolicyFlag(1, "public");
        PUBLIC = policyFlag;
        PolicyFlag policyFlag2 = new PolicyFlag(2, "system");
        SYSTEM_PARTITION = policyFlag2;
        PolicyFlag policyFlag3 = new PolicyFlag(4, "vendor");
        VENDOR_PARTITION = policyFlag3;
        PolicyFlag policyFlag4 = new PolicyFlag(8, "product");
        PRODUCT_PARTITION = policyFlag4;
        PolicyFlag policyFlag5 = new PolicyFlag(16, "signature");
        SIGNATURE = policyFlag5;
        PolicyFlag policyFlag6 = new PolicyFlag(32, "odm");
        ODM_PARTITION = policyFlag6;
        PolicyFlag policyFlag7 = new PolicyFlag(64, "oem");
        OEM_PARTITION = policyFlag7;
        PolicyFlag policyFlag8 = new PolicyFlag(128, "actor");
        ACTOR_SIGNATURE = policyFlag8;
        PolicyFlag policyFlag9 = new PolicyFlag(Fcntl.S_IRUSR, TypeBlock.NAME_config);
        CONFIG_SIGNATURE = policyFlag9;
        PolicyFlag[] policyFlagArr = {policyFlag, policyFlag2, policyFlag3, policyFlag4, policyFlag5, policyFlag6, policyFlag7, policyFlag8, policyFlag9};
        VALUES = policyFlagArr;
        HashMap map = new HashMap();
        nameMap = map;
        for (int i = 0; i < 9; i++) {
            PolicyFlag policyFlag10 = policyFlagArr[i];
            map.put(policyFlag10.name(), policyFlag10);
        }
    }

    private PolicyFlag(int i, String str) {
        this.flag = i;
        this.name = str;
    }

    public static boolean contains(PolicyFlag[] policyFlagArr, PolicyFlag policyFlag) {
        if (policyFlagArr == null || policyFlagArr.length == 0) {
            return policyFlag == null;
        }
        if (policyFlag == null) {
            return false;
        }
        for (PolicyFlag policyFlag2 : policyFlagArr) {
            if (policyFlag.equals(policyFlag2)) {
                return true;
            }
        }
        return false;
    }

    public static PolicyFlag nameOf(String str) {
        return nameMap.get(str);
    }

    public static int parse(XmlPullParser xmlPullParser) throws IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, ATTR_type);
        if (StringsUtil.isEmpty(attributeValue)) {
            return 0;
        }
        int iFlag = 0;
        for (String str : StringsUtil.split(attributeValue, '|')) {
            String strTrim = str.trim();
            if (strTrim.length() != 0) {
                PolicyFlag policyFlagNameOf = nameOf(strTrim);
                if (policyFlagNameOf == null) {
                    throw new EncodeException(xmlPullParser, "Unknown policy flag '" + strTrim + "'");
                }
                iFlag |= policyFlagNameOf.flag();
            }
        }
        return iFlag;
    }

    public static void serialize(XmlSerializer xmlSerializer, int i) throws IOException {
        String string = toString(i);
        if (StringsUtil.isEmpty(string)) {
            return;
        }
        xmlSerializer.attribute(null, ATTR_type, string);
    }

    public static int sum(PolicyFlag[] policyFlagArr) {
        if (policyFlagArr == null || policyFlagArr.length == 0) {
            return 0;
        }
        int iFlag = 0;
        for (PolicyFlag policyFlag : policyFlagArr) {
            if (policyFlag != null) {
                iFlag |= policyFlag.flag();
            }
        }
        return iFlag;
    }

    public static String toString(int i) {
        if (i == 0) {
            return StringsUtil.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (PolicyFlag policyFlag : VALUES) {
            if (policyFlag.isSet(i)) {
                if (z) {
                    sb.append('|');
                }
                sb.append(policyFlag.name());
                z = true;
            }
        }
        return sb.toString();
    }

    public static PolicyFlag[] valuesOf(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.trim().split("\\s*\\|\\s*");
        int length = strArrSplit.length;
        PolicyFlag[] policyFlagArr = new PolicyFlag[length];
        int i = 0;
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            PolicyFlag policyFlagNameOf = nameOf(strArrSplit[i2]);
            if (policyFlagNameOf != null) {
                policyFlagArr[i2] = policyFlagNameOf;
                i++;
            }
        }
        if (i == 0) {
            return null;
        }
        if (i == length) {
            return policyFlagArr;
        }
        PolicyFlag[] policyFlagArr2 = new PolicyFlag[i];
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            PolicyFlag policyFlag = policyFlagArr[i4];
            if (policyFlag != null) {
                policyFlagArr2[i3] = policyFlag;
                i3++;
            }
        }
        return policyFlagArr2;
    }

    public int flag() {
        return this.flag;
    }

    public boolean isSet(int i) {
        int iFlag = flag();
        return (i & iFlag) == iFlag;
    }

    public String name() {
        return this.name;
    }

    public static String toString(PolicyFlag[] policyFlagArr) {
        if (policyFlagArr != null && policyFlagArr.length != 0) {
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            for (PolicyFlag policyFlag : policyFlagArr) {
                if (policyFlag != null) {
                    if (z) {
                        sb.append('|');
                    }
                    sb.append(policyFlag.name());
                    z = true;
                }
            }
            return sb.toString();
        }
        return StringsUtil.EMPTY;
    }

    public static PolicyFlag[] valuesOf(int i) {
        if (i == 0) {
            return null;
        }
        PolicyFlag[] policyFlagArr = VALUES;
        int length = policyFlagArr.length;
        PolicyFlag[] policyFlagArr2 = new PolicyFlag[length];
        int i2 = 0;
        for (int i3 = 0; i3 < policyFlagArr.length; i3++) {
            PolicyFlag policyFlag = policyFlagArr[i3];
            if (policyFlag.isSet(i)) {
                policyFlagArr2[i3] = policyFlag;
                i2++;
            }
        }
        if (i2 == 0) {
            return null;
        }
        if (i2 == length) {
            return policyFlagArr2;
        }
        PolicyFlag[] policyFlagArr3 = new PolicyFlag[i2];
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            PolicyFlag policyFlag2 = policyFlagArr2[i5];
            if (policyFlag2 != null) {
                policyFlagArr3[i4] = policyFlag2;
                i4++;
            }
        }
        return policyFlagArr3;
    }
}

package com.reandroid.dex.common;

import com.reandroid.dex.common.HiddenApiFlag;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.utils.ObjectsStore;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HiddenApiFlag extends Modifier implements SmaliFormat {
    public static final HiddenApiFlag BLACKLIST;
    public static final HiddenApiFlag CORE_PLATFORM_API;
    private static final HiddenApiFlag[] DOMAIN_VALUES;
    public static final HiddenApiFlag GREYLIST;
    public static final HiddenApiFlag GREYLIST_MAX_O;
    public static final HiddenApiFlag GREYLIST_MAX_P;
    public static final HiddenApiFlag GREYLIST_MAX_Q;
    public static final HiddenApiFlag GREYLIST_MAX_R;
    private static final Map<String, HiddenApiFlag> NAME_MAP;
    public static final int NO_RESTRICTION;
    private static final HiddenApiFlag[] RESTRICTION_VALUES;
    public static final HiddenApiFlag TEST_API;
    private static final HiddenApiFlag[] VALUES;
    public static final HiddenApiFlag WHITELIST;
    private final boolean domainFlag;

    static {
        int i = 0;
        HiddenApiFlag hiddenApiFlag = new HiddenApiFlag(0, "whitelist");
        WHITELIST = hiddenApiFlag;
        HiddenApiFlag hiddenApiFlag2 = new HiddenApiFlag(1, "greylist");
        GREYLIST = hiddenApiFlag2;
        HiddenApiFlag hiddenApiFlag3 = new HiddenApiFlag(2, "blacklist");
        BLACKLIST = hiddenApiFlag3;
        HiddenApiFlag hiddenApiFlag4 = new HiddenApiFlag(3, "greylist-max-o");
        GREYLIST_MAX_O = hiddenApiFlag4;
        HiddenApiFlag hiddenApiFlag5 = new HiddenApiFlag(4, "greylist-max-p");
        GREYLIST_MAX_P = hiddenApiFlag5;
        HiddenApiFlag hiddenApiFlag6 = new HiddenApiFlag(5, "greylist-max-q");
        GREYLIST_MAX_Q = hiddenApiFlag6;
        HiddenApiFlag hiddenApiFlag7 = new HiddenApiFlag(6, "greylist-max-r");
        GREYLIST_MAX_R = hiddenApiFlag7;
        HiddenApiFlag hiddenApiFlag8 = new HiddenApiFlag(8, "core-platform-api", true);
        CORE_PLATFORM_API = hiddenApiFlag8;
        HiddenApiFlag hiddenApiFlag9 = new HiddenApiFlag(16, "test-api", true);
        TEST_API = hiddenApiFlag9;
        NO_RESTRICTION = ObjectsUtil.of(7);
        HiddenApiFlag[] hiddenApiFlagArr = {hiddenApiFlag, hiddenApiFlag2, hiddenApiFlag3, hiddenApiFlag4, hiddenApiFlag5, hiddenApiFlag6, hiddenApiFlag7};
        RESTRICTION_VALUES = hiddenApiFlagArr;
        HiddenApiFlag[] hiddenApiFlagArr2 = {hiddenApiFlag8, hiddenApiFlag9};
        DOMAIN_VALUES = hiddenApiFlagArr2;
        VALUES = new HiddenApiFlag[hiddenApiFlagArr.length + hiddenApiFlagArr2.length];
        HashMap map = new HashMap();
        int length = hiddenApiFlagArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            HiddenApiFlag hiddenApiFlag10 = hiddenApiFlagArr[i2];
            VALUES[i3] = hiddenApiFlag10;
            map.put(hiddenApiFlag10.getName(), hiddenApiFlag10);
            i2++;
            i3++;
        }
        HiddenApiFlag[] hiddenApiFlagArr3 = DOMAIN_VALUES;
        int length2 = hiddenApiFlagArr3.length;
        while (i < length2) {
            HiddenApiFlag hiddenApiFlag11 = hiddenApiFlagArr3[i];
            VALUES[i3] = hiddenApiFlag11;
            map.put(hiddenApiFlag11.getName(), hiddenApiFlag11);
            i++;
            i3++;
        }
        NAME_MAP = map;
    }

    private HiddenApiFlag(int i, String str, boolean z) {
        super(i, str);
        this.domainFlag = z;
    }

    public static int combineHiddenApiFlag(Iterator<? extends Modifier> it) {
        int i = NO_RESTRICTION;
        while (it.hasNext()) {
            Modifier next = it.next();
            if (next instanceof AccessFlag) {
                int value = next.getValue();
                i = i == NO_RESTRICTION ? value : i | value;
            }
        }
        return i;
    }

    public static HiddenApiFlag domainOf(int i) {
        for (HiddenApiFlag hiddenApiFlag : DOMAIN_VALUES) {
            if (hiddenApiFlag.isSet(i)) {
                return hiddenApiFlag;
            }
        }
        return null;
    }

    public static Iterator<HiddenApiFlag> getValues(Predicate<HiddenApiFlag> predicate) {
        return new ArrayIterator(VALUES, predicate);
    }

    public static HiddenApiFlag[] parse(SmaliReader smaliReader) {
        Object objAdd = null;
        while (true) {
            HiddenApiFlag next = parseNext(smaliReader);
            if (next == null) {
                break;
            }
            objAdd = ObjectsStore.add(objAdd, next);
        }
        if (objAdd == null) {
            return null;
        }
        int size = ObjectsStore.size(objAdd);
        smaliReader.skipWhitespaces();
        HiddenApiFlag[] hiddenApiFlagArr = new HiddenApiFlag[size];
        ObjectsStore.collect(objAdd, hiddenApiFlagArr);
        return hiddenApiFlagArr;
    }

    private static HiddenApiFlag parseNext(SmaliReader smaliReader) {
        smaliReader.skipWhitespaces();
        int iIndexOf = smaliReader.indexOf(' ');
        if (iIndexOf < 0) {
            return null;
        }
        int iPosition = smaliReader.position();
        HiddenApiFlag hiddenApiFlagValueOf = valueOf(smaliReader.readString(iIndexOf - smaliReader.position()));
        if (hiddenApiFlagValueOf == null) {
            smaliReader.position(iPosition);
        }
        return hiddenApiFlagValueOf;
    }

    public static HiddenApiFlag restrictionOf(int i) {
        for (HiddenApiFlag hiddenApiFlag : RESTRICTION_VALUES) {
            if (hiddenApiFlag.isSet(i)) {
                return hiddenApiFlag;
            }
        }
        return null;
    }

    public static HiddenApiFlag valueOf(String str) {
        return NAME_MAP.get(str);
    }

    public static Iterator<HiddenApiFlag> valuesOf(final int i) {
        int i2 = NO_RESTRICTION;
        if (i == i2) {
            return EmptyIterator.of();
        }
        return (i < 0 || i >= i2) ? getValues(new Predicate() { // from class: x76
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((HiddenApiFlag) obj).isSet(i);
            }
        }) : SingleIterator.of(RESTRICTION_VALUES[i]);
    }

    public boolean isDomainFlag() {
        return this.domainFlag;
    }

    @Override // com.reandroid.dex.common.Modifier
    public boolean isSet(int i) {
        int value = getValue();
        if (this.domainFlag) {
            return (i & value) == value;
        }
        return (NO_RESTRICTION & i) == value;
    }

    private HiddenApiFlag(int i, String str) {
        this(i, str, false);
    }

    public static Iterator<HiddenApiFlag> getValues() {
        return getValues(null);
    }

    public static int combineHiddenApiFlag(HiddenApiFlag[] hiddenApiFlagArr) {
        int i = NO_RESTRICTION;
        if (hiddenApiFlagArr != null) {
            for (HiddenApiFlag hiddenApiFlag : hiddenApiFlagArr) {
                int value = hiddenApiFlag.getValue();
                i = i == NO_RESTRICTION ? value : i | value;
            }
        }
        return i;
    }
}

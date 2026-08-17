package com.reandroid.arsc.value;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockLoad;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.utils.CompareUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class ResConfigBase extends FixedBlockContainer implements BlockLoad {
    static final int LEN_localeScript = 4;
    static final int LEN_localeVariant = 8;
    public static final String NAME_color_mode_hdr = "color_mode_hdr";
    public static final String NAME_color_mode_wide = "color_mode_wide";
    public static final String NAME_config_size = "config_size";
    public static final String NAME_density = "density";
    public static final String NAME_gender = "gender";
    public static final String NAME_input_flags_keys_hidden = "input_flags_keys_hidden";
    public static final String NAME_input_flags_nav_hidden = "input_flags_nav_hidden";
    public static final String NAME_keyboard = "keyboard";
    public static final String NAME_language = "language";
    public static final String NAME_localeScript = "localeScript";
    public static final String NAME_localeVariant = "localeVariant";
    public static final String NAME_mcc = "mcc";
    public static final String NAME_minorVersion = "minorVersion";
    public static final String NAME_mnc = "mnc";
    public static final String NAME_navigation = "navigation";
    public static final String NAME_orientation = "orientation";
    public static final String NAME_region = "region";
    public static final String NAME_screenHeight = "screenHeight";
    public static final String NAME_screenHeightDp = "screenHeightDp";
    public static final String NAME_screenWidth = "screenWidth";
    public static final String NAME_screenWidthDp = "screenWidthDp";
    public static final String NAME_screen_layout_dir = "screen_layout_dir";
    public static final String NAME_screen_layout_long = "screen_layout_long";
    public static final String NAME_screen_layout_round = "screen_layout_round";
    public static final String NAME_screen_layout_size = "screen_layout_size";
    public static final String NAME_sdkVersion = "sdkVersion";
    public static final String NAME_smallestScreenWidthDp = "smallestScreenWidthDp";
    public static final String NAME_touchscreen = "touchscreen";
    public static final String NAME_ui_mode_night = "ui_mode_night";
    public static final String NAME_ui_mode_type = "ui_mode_type";
    public static final String NAME_unknownBytes = "unknown_bytes";
    private static final int OFFSET_colorMode = 45;
    private static final int OFFSET_density = 10;
    private static final int OFFSET_gender = 15;
    private static final int OFFSET_inputFlags = 14;
    private static final int OFFSET_keyboard = 12;
    private static final int OFFSET_language = 4;
    private static final int OFFSET_localeScript = 32;
    private static final int OFFSET_localeVariant = 36;
    private static final int OFFSET_mcc = 0;
    private static final int OFFSET_minorVersion = 22;
    private static final int OFFSET_mnc = 2;
    private static final int OFFSET_navigation = 13;
    private static final int OFFSET_orientation = 8;
    private static final int OFFSET_region = 6;
    private static final int OFFSET_reservedColorModePadding = 46;
    private static final int OFFSET_screenHeight = 18;
    private static final int OFFSET_screenHeightDp = 30;
    private static final int OFFSET_screenLayout = 24;
    private static final int OFFSET_screenLayout2 = 44;
    private static final int OFFSET_screenWidth = 16;
    private static final int OFFSET_screenWidthDp = 28;
    private static final int OFFSET_sdkVersion = 20;
    private static final int OFFSET_smallestScreenWidthDp = 26;
    private static final int OFFSET_touchscreen = 9;
    private static final int OFFSET_uiMode = 25;
    private static final int OFFSET_unknown = 48;
    public static final int SIZE_16 = 16;
    public static final int SIZE_28 = 28;
    public static final int SIZE_32 = 32;
    public static final int SIZE_36 = 36;
    public static final int SIZE_48 = 48;
    public static final int SIZE_52 = 52;
    public static final int SIZE_56 = 56;
    public static final int SIZE_64 = 64;
    public static final String UNKNOWN_BYTES = "unknown_bytes";
    private final IntegerItem configSize;
    private final ResConfigValueContainer mValuesContainer;

    public ResConfigBase(int i) {
        super(2);
        IntegerItem integerItem = new IntegerItem(i);
        this.configSize = integerItem;
        ResConfigValueContainer resConfigValueContainer = new ResConfigValueContainer(i - 4, integerItem);
        this.mValuesContainer = resConfigValueContainer;
        addChild(0, integerItem);
        addChild(1, resConfigValueContainer);
        integerItem.setBlockLoad(this);
    }

    public static String ensureLength(String str, int i, char c) {
        int length = str.length();
        if (length >= i) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        int i2 = i - length;
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static boolean isNullBytes(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        for (byte b : bArr) {
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullChars(char[] cArr) {
        if (cArr == null) {
            return true;
        }
        for (char c : cArr) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidSize(int i) {
        return i == 16 || i == 28 || i == 32 || i == 36 || i == 48 || i == 52 || i == 56 || i == 64 || i > 64;
    }

    public static int nearestSize(int i) {
        if (i <= 16) {
            return 16;
        }
        if (i <= 28) {
            return 28;
        }
        if (i <= 32) {
            return 32;
        }
        if (i <= 36) {
            return 36;
        }
        if (i <= 48) {
            return 48;
        }
        if (i <= 52) {
            return 52;
        }
        if (i <= 56) {
            return 56;
        }
        if (i <= 64) {
            return 64;
        }
        return i + ((4 - (i % 4)) % i);
    }

    public static byte[] toByteArray(char[] cArr, int i) {
        byte[] bArr = new byte[i];
        if (cArr != null) {
            int length = cArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                bArr[i2] = (byte) cArr[i2];
            }
        }
        return bArr;
    }

    public static char[] toCharArray(byte[] bArr) {
        if (isNullBytes(bArr)) {
            return null;
        }
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        return cArr;
    }

    public static char[] trimEndingZero(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        int i = -1;
        for (int i2 = 0; i2 < cArr.length; i2++) {
            if (cArr[i2] != 0) {
                i = i2;
            }
        }
        if (i == -1) {
            return null;
        }
        int i3 = i + 1;
        if (i3 == cArr.length) {
            return cArr;
        }
        char[] cArr2 = new char[i3];
        System.arraycopy(cArr, 0, cArr2, 0, i3);
        return cArr2;
    }

    public static String trimPostfix(String str, char c) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        while (true) {
            int i = length - 1;
            if (length <= 0 || str.charAt(i) != c) {
                break;
            }
            str = str.substring(0, i);
            length = str.length();
        }
        return str;
    }

    public int compareLocale(ResConfigBase resConfigBase) {
        int iCompare = CompareUtil.compare(this.mValuesContainer.getShortValue(6), resConfigBase.mValuesContainer.getShortValue(6));
        if (iCompare == 0) {
            iCompare = CompareUtil.compare(this.mValuesContainer.getShortValue(4), resConfigBase.mValuesContainer.getShortValue(4));
        }
        if (iCompare == 0) {
            iCompare = CompareUtil.compare(this.mValuesContainer.getIntValue(32), resConfigBase.mValuesContainer.getIntValue(32));
        }
        return iCompare == 0 ? CompareUtil.compare(Long.valueOf(this.mValuesContainer.getLongValue(36)), Long.valueOf(resConfigBase.mValuesContainer.getLongValue(36))) : iCompare;
    }

    public int getColorMode() {
        return this.mValuesContainer.getByteValue(45);
    }

    public int getConfigSize() {
        return this.configSize.get();
    }

    public int getDensityValue() {
        return this.mValuesContainer.getShortValue(10);
    }

    public int getGenderValue() {
        return this.mValuesContainer.getByteValue(15);
    }

    public int getInputFlagsValue() {
        return this.mValuesContainer.getByteValue(14);
    }

    public int getKeyboardValue() {
        return this.mValuesContainer.getByteValue(12);
    }

    public byte[] getLanguageBytes() {
        return getConfigSize() < 16 ? new byte[2] : this.mValuesContainer.getByteArrayValue(4, 2);
    }

    public byte[] getLocaleScriptBytes() {
        return this.mValuesContainer.getByteArrayValue(32, 4);
    }

    public byte[] getLocaleVariantBytes() {
        return this.mValuesContainer.getByteArrayValue(36, 8);
    }

    public int getMcc() {
        return this.mValuesContainer.getShortValue(0);
    }

    public int getMinorVersion() {
        return this.mValuesContainer.getShortValue(22);
    }

    public int getMnc() {
        return this.mValuesContainer.getShortValue(2);
    }

    public int getNavigationValue() {
        return this.mValuesContainer.getByteValue(13);
    }

    public int getOrientationValue() {
        return this.mValuesContainer.getByteValue(8);
    }

    public byte[] getRegionBytes() {
        return this.mValuesContainer.getByteArrayValue(6, 2);
    }

    public int getReservedColorModePadding() {
        return this.mValuesContainer.getByteValue(46);
    }

    public int getScreenHeight() {
        return this.mValuesContainer.getShortValue(18);
    }

    public int getScreenHeightDp() {
        return this.mValuesContainer.getShortValue(30);
    }

    public int getScreenLayout() {
        return this.mValuesContainer.getByteValue(24);
    }

    public int getScreenLayout2() {
        return this.mValuesContainer.getByteValue(44);
    }

    public int getScreenWidth() {
        return this.mValuesContainer.getShortValue(16);
    }

    public int getScreenWidthDp() {
        return this.mValuesContainer.getShortValue(28);
    }

    public int getSdkVersion() {
        return this.mValuesContainer.getShortValue(20);
    }

    public int getSmallestScreenWidthDp() {
        return this.mValuesContainer.getShortValue(26);
    }

    public int getTouchscreenValue() {
        return this.mValuesContainer.getByteValue(9);
    }

    public int getUiMode() {
        return this.mValuesContainer.getByteValue(25);
    }

    public byte[] getUnknownBytes() {
        return this.mValuesContainer.getByteArrayValue(48, getConfigSize() - 52);
    }

    public byte[] getValueBytes() {
        return this.mValuesContainer.getBytes();
    }

    public void onBlockLoaded(BlockReader blockReader, Block block) throws IOException {
        IntegerItem integerItem = this.configSize;
        if (block == integerItem) {
            this.mValuesContainer.setSize(integerItem.get() - 4);
        }
    }

    public void onPreRefresh() {
        this.configSize.set(countBytes());
    }

    public void onRefreshed() {
    }

    public void resetValueBytes() {
        this.mValuesContainer.fill((byte) 0);
    }

    public void setColorMode(int i) {
        this.mValuesContainer.setByteValue(45, i);
    }

    public void setConfigSize(int i) {
        if (!isValidSize(i)) {
            qf1.a("Invalid config size = ", i);
            return;
        }
        int i2 = i % 4;
        if (i2 != 0) {
            i += 4 - i2;
        }
        this.configSize.set(i);
        this.mValuesContainer.setSize(i - 4);
    }

    public void setDensity(int i) {
        this.mValuesContainer.setShortValue(10, i);
    }

    public void setGenderValue(int i) {
        this.mValuesContainer.setByteValue(15, i);
    }

    public void setInputFlags(int i) {
        this.mValuesContainer.setByteValue(14, i);
    }

    public void setKeyboard(int i) {
        this.mValuesContainer.setByteValue(12, i);
    }

    public void setLanguageBytes(byte[] bArr) {
        this.mValuesContainer.setByteArrayValue(4, bArr, 2);
    }

    public void setLocaleScript(byte[] bArr) {
        this.mValuesContainer.setByteArrayValue(32, bArr, 4);
    }

    public void setLocaleVariant(byte[] bArr) {
        this.mValuesContainer.setByteArrayValue(36, bArr, 8);
    }

    public void setMcc(int i) {
        this.mValuesContainer.setShortValue(0, i);
    }

    public void setMinorVersion(int i) {
        this.mValuesContainer.setShortValue(22, i);
    }

    public void setMnc(int i) {
        this.mValuesContainer.setShortValue(2, i);
    }

    public void setNavigation(int i) {
        this.mValuesContainer.setByteValue(13, i);
    }

    public void setOrientation(int i) {
        this.mValuesContainer.setByteValue(8, i);
    }

    public void setRegionBytes(byte[] bArr) {
        this.mValuesContainer.setByteArrayValue(6, bArr, 2);
    }

    public void setReservedColorModePadding(int i) {
        this.mValuesContainer.setByteValue(46, i);
    }

    public void setScreenHeight(int i) {
        this.mValuesContainer.setShortValue(18, i);
    }

    public void setScreenHeightDp(int i) {
        this.mValuesContainer.setShortValue(30, i);
    }

    public void setScreenLayout(int i) {
        this.mValuesContainer.setByteValue(24, i);
    }

    public void setScreenLayout2(int i) {
        this.mValuesContainer.setByteValue(44, i);
    }

    public void setScreenWidth(int i) {
        this.mValuesContainer.setShortValue(16, i);
    }

    public void setScreenWidthDp(int i) {
        this.mValuesContainer.setShortValue(28, i);
    }

    public void setSdkVersion(int i) {
        this.mValuesContainer.setShortValue(20, i);
    }

    public void setSmallestScreenWidthDp(int i) {
        this.mValuesContainer.setShortValue(26, i);
    }

    public void setTouchscreen(int i) {
        this.mValuesContainer.setByteValue(9, i);
    }

    public void setUiMode(int i) {
        this.mValuesContainer.setByteValue(25, i);
    }

    public void setUnknownBytes(byte[] bArr) {
        int configSize = getConfigSize() - 52;
        if (bArr.length > configSize) {
            configSize = bArr.length;
        }
        this.mValuesContainer.setByteArrayValue(48, bArr, configSize);
    }

    public void setValueBytes(byte[] bArr) {
        setConfigSize(bArr.length + 4);
        this.mValuesContainer.putByteArray(0, bArr);
    }

    public void trimToMinimumSize() {
        trimToSize(nearestSize(ByteArray.trimTrailZeros(this.mValuesContainer.getBytes()).length + 4));
    }

    public boolean trimToSize(int i) {
        int configSize = getConfigSize();
        if (configSize == i) {
            return true;
        }
        if (!isValidSize(i)) {
            return false;
        }
        if (configSize < i) {
            setConfigSize(i);
            return true;
        }
        int i2 = i - 4;
        if (!isNullBytes(this.mValuesContainer.getByteArray(i2, (configSize - 4) - i2))) {
            return false;
        }
        setConfigSize(i);
        return true;
    }

    public static byte[] trimEndingZero(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int i = -1;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (bArr[i2] != 0) {
                i = i2;
            }
        }
        if (i == -1) {
            return null;
        }
        int i3 = i + 1;
        if (i3 == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, 0, bArr2, 0, i3);
        return bArr2;
    }
}

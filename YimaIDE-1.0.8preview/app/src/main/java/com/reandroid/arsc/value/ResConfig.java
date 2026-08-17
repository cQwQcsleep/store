package com.reandroid.arsc.value;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.HexUtil;
import com.sun.jna.platform.win32.WinError;
import com.sun.jna.platform.win32.WinNT;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ResConfig extends ResConfigBase implements JSONConvert<JSONObject>, Comparable<ResConfig> {
    private static final ResConfig DEFAULT_INSTANCE = new ResConfig(16);
    private static final char POSTFIX_locale = '#';
    private String mQualifiers;
    private int mQualifiersStamp;

    public static class QualifierBuilder {
        private StringBuilder mBuilder;
        private final ResConfig mConfig;
        private String mNumberingSystem;

        public QualifierBuilder(ResConfig resConfig) {
            this.mConfig = resConfig;
        }

        private void appendDp(String str, int i) {
            if (i == 0) {
                return;
            }
            StringBuilder sb = this.mBuilder;
            sb.append(LocaleUtility.IETF_SEPARATOR);
            if (str != null) {
                sb.append(str);
            }
            sb.append(i);
            sb.append("dp");
        }

        private void appendFlag(Flag flag) {
            if (flag == null) {
                return;
            }
            StringBuilder sb = this.mBuilder;
            sb.append(LocaleUtility.IETF_SEPARATOR);
            sb.append(flag.toString());
        }

        private void appendLanguageAndRegion() {
            ResConfig resConfig = this.mConfig;
            String language = resConfig.getLanguage();
            String region = resConfig.getRegion();
            String localeScript = resConfig.getLocaleScript();
            String localeVariant = resConfig.getLocaleVariant();
            if (language == null && region == null) {
                return;
            }
            StringBuilder sb = this.mBuilder;
            char c = LocaleUtility.IETF_SEPARATOR;
            if (localeScript != null || localeVariant != null || (region != null && region.length() == 3)) {
                sb.append(LocaleUtility.IETF_SEPARATOR);
                sb.append('b');
                c = '+';
            }
            if (language != null) {
                sb.append(c);
                sb.append(language);
            }
            if (region != null) {
                sb.append(c);
                if (region.length() == 2) {
                    sb.append('r');
                }
                sb.append(region);
            }
            if (localeScript != null) {
                sb.append(c);
                sb.append(localeScript);
            }
            if (localeVariant != null) {
                sb.append(c);
                sb.append(localeVariant);
            }
        }

        private void appendLocaleNumberingSystem() {
            String str = this.mNumberingSystem;
            if (str == null) {
                return;
            }
            StringBuilder sb = this.mBuilder;
            sb.append("-u+nu+");
            sb.append(str);
        }

        private void appendPrefixedNumber(String str, int i) {
            if (i == 0) {
                return;
            }
            StringBuilder sb = this.mBuilder;
            sb.append(LocaleUtility.IETF_SEPARATOR);
            sb.append(str);
            sb.append(i);
        }

        private void appendScreenWidthHeight() {
            ResConfig resConfig = this.mConfig;
            int screenWidth = resConfig.getScreenWidth();
            int screenHeight = resConfig.getScreenHeight();
            if (screenWidth == 0 && screenHeight == 0) {
                return;
            }
            StringBuilder sb = this.mBuilder;
            sb.append(LocaleUtility.IETF_SEPARATOR);
            sb.append(screenWidth);
            sb.append('x');
            sb.append(screenHeight);
        }

        private void appendUnknownBytes() {
            String unknownHexBytes = this.mConfig.getUnknownHexBytes();
            if (unknownHexBytes == null) {
                return;
            }
            StringBuilder sb = this.mBuilder;
            sb.append(LocaleUtility.IETF_SEPARATOR);
            sb.append("unknown_bytes");
            sb.append(unknownHexBytes);
        }

        public String build() {
            ResConfig resConfig = this.mConfig;
            if (resConfig.isDefault()) {
                return "";
            }
            this.mBuilder = new StringBuilder();
            appendPrefixedNumber(ResConfigBase.NAME_mcc, resConfig.getMcc());
            appendPrefixedNumber(ResConfigBase.NAME_mnc, resConfig.getMnc());
            appendLanguageAndRegion();
            appendFlag(resConfig.getGender());
            appendFlag(resConfig.getScreenLayoutDir());
            appendDp("sw", resConfig.getSmallestScreenWidthDp());
            appendDp("w", resConfig.getScreenWidthDp());
            appendDp("h", resConfig.getScreenHeightDp());
            appendFlag(resConfig.getScreenLayoutSize());
            appendFlag(resConfig.getScreenLayoutLong());
            appendFlag(resConfig.getScreenLayoutRound());
            appendFlag(resConfig.getColorModeWide());
            appendFlag(resConfig.getColorModeHdr());
            appendFlag(resConfig.getOrientation());
            appendFlag(resConfig.getUiModeType());
            appendFlag(resConfig.getUiModeNight());
            appendFlag(resConfig.getDensity());
            appendFlag(resConfig.getTouchscreen());
            appendFlag(resConfig.getInputFlagsKeysHidden());
            appendFlag(resConfig.getKeyboard());
            appendFlag(resConfig.getInputFlagsNavHidden());
            appendFlag(resConfig.getNavigation());
            appendScreenWidthHeight();
            appendPrefixedNumber("v", resConfig.getSdkVersion());
            appendUnknownBytes();
            return this.mBuilder.toString();
        }
    }

    private ResConfig(int i) {
        super(i);
        this.mQualifiersStamp = 0;
    }

    public static ResConfig getDefault() {
        ResConfig resConfig = DEFAULT_INSTANCE;
        if (resConfig.isDefault()) {
            return resConfig;
        }
        resConfig.resetValueBytes();
        resConfig.setConfigSize(16);
        return resConfig;
    }

    private String getLocaleScriptInternal() {
        char[] localeScriptChars = getLocaleScriptChars();
        if (localeScriptChars == null) {
            return null;
        }
        return new String(localeScriptChars);
    }

    private String getLocaleVariantInternal() {
        char[] localeVariantChars = getLocaleVariantChars();
        if (localeVariantChars == null) {
            return null;
        }
        return new String(localeVariantChars);
    }

    private static byte[] packLanguage(char[] cArr) {
        return packLanguageOrRegion(cArr, 'a');
    }

    private static byte[] packLanguageOrRegion(char[] cArr, char c) {
        char c2;
        byte[] bArr = new byte[2];
        if (cArr != null && cArr.length >= 2) {
            if (cArr.length != 2 && (c2 = cArr[2]) != 0 && c2 != '-') {
                byte b = (byte) ((cArr[0] - c) & 127);
                byte b2 = (byte) ((cArr[1] - c) & 127);
                bArr[0] = (byte) ((((byte) ((c2 - c) & 127)) << 2) | 128 | (b2 >> 3));
                bArr[1] = (byte) ((b2 << 5) | b);
                return bArr;
            }
            bArr[0] = (byte) cArr[0];
            bArr[1] = (byte) cArr[1];
        }
        return bArr;
    }

    private static byte[] packRegion(char[] cArr) {
        return packLanguageOrRegion(cArr, '0');
    }

    public static ResConfig parse(String str) {
        ResConfig resConfig = new ResConfig();
        resConfig.parseQualifiers(str);
        return resConfig;
    }

    private void setLocaleVariantInternal(String str) {
        String strTrimPostfix = ResConfigBase.trimPostfix(str, POSTFIX_locale);
        setLocaleVariant(strTrimPostfix != null ? strTrimPostfix.toCharArray() : null);
    }

    private static char[] unPackLanguage(byte b, byte b2) {
        return unpackLanguageOrRegion(b, b2, 'a');
    }

    private static char[] unPackRegion(byte b, byte b2) {
        return unpackLanguageOrRegion(b, b2, '0');
    }

    private static char[] unpackLanguageOrRegion(byte b, byte b2, char c) {
        if ((b & 128) != 0) {
            return new char[]{(char) (((byte) (b2 & WinNT.VALID_INHERIT_FLAGS)) + c), (char) (((byte) (((b2 & 224) >> 5) + ((b & 3) << 3))) + c), (char) (((byte) ((b & 124) >> 2)) + c)};
        }
        return (b == 0 || b2 == 0) ? new char[2] : new char[]{(char) b, (char) b2};
    }

    @Override // java.lang.Comparable
    public int compareTo(ResConfig resConfig) {
        int iCompare = CompareUtil.compare(getMnc(), resConfig.getMnc());
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = CompareUtil.compare(getMcc(), resConfig.getMcc());
        if (iCompare2 != 0) {
            return iCompare2;
        }
        int iCompareLocale = compareLocale(resConfig);
        if (iCompareLocale != 0) {
            return iCompareLocale;
        }
        int iCompare3 = CompareUtil.compare(getGenderValue(), resConfig.getGenderValue());
        if (iCompare3 != 0) {
            return iCompare3;
        }
        int iCompare4 = CompareUtil.compare(getDensityValue(), resConfig.getDensityValue());
        if (iCompare4 != 0) {
            return iCompare4;
        }
        int iCompare5 = CompareUtil.compare(getTouchscreenValue(), resConfig.getTouchscreenValue());
        if (iCompare5 != 0) {
            return iCompare5;
        }
        int iCompare6 = CompareUtil.compare(getOrientationValue(), resConfig.getOrientationValue());
        if (iCompare6 != 0) {
            return iCompare6;
        }
        int iCompare7 = CompareUtil.compare(getNavigationValue(), resConfig.getNavigationValue());
        if (iCompare7 != 0) {
            return iCompare7;
        }
        int iCompare8 = CompareUtil.compare(getKeyboardValue(), resConfig.getKeyboardValue());
        if (iCompare8 != 0) {
            return iCompare8;
        }
        int iCompare9 = CompareUtil.compare(getInputFlagsValue(), resConfig.getInputFlagsValue());
        if (iCompare9 != 0) {
            return iCompare9;
        }
        int iCompare10 = CompareUtil.compare(getScreenWidth(), resConfig.getScreenWidth());
        if (iCompare10 != 0) {
            return iCompare10;
        }
        int iCompare11 = CompareUtil.compare(getScreenHeight(), resConfig.getScreenHeight());
        if (iCompare11 != 0) {
            return iCompare11;
        }
        int iCompare12 = CompareUtil.compare(getSdkVersion(), resConfig.getSdkVersion());
        if (iCompare12 != 0) {
            return iCompare12;
        }
        int iCompare13 = CompareUtil.compare(getScreenLayout(), resConfig.getScreenLayout());
        if (iCompare13 != 0) {
            return iCompare13;
        }
        int iCompare14 = CompareUtil.compare(getScreenLayout2(), resConfig.getScreenLayout2());
        if (iCompare14 != 0) {
            return iCompare14;
        }
        int iCompare15 = CompareUtil.compare(getColorMode(), resConfig.getColorMode());
        if (iCompare15 != 0) {
            return iCompare15;
        }
        int iCompare16 = CompareUtil.compare(getUiMode(), resConfig.getUiMode());
        if (iCompare16 != 0) {
            return iCompare16;
        }
        int iCompare17 = CompareUtil.compare(getSmallestScreenWidthDp(), resConfig.getSmallestScreenWidthDp());
        if (iCompare17 != 0) {
            return iCompare17;
        }
        int iCompare18 = CompareUtil.compare(getScreenHeightDp(), resConfig.getScreenHeightDp());
        if (iCompare18 != 0) {
            return iCompare18;
        }
        int iCompare19 = CompareUtil.compare(getScreenWidthDp(), resConfig.getScreenWidthDp());
        if (iCompare19 != 0) {
            return iCompare19;
        }
        return 0;
    }

    public void copyFrom(ResConfig resConfig) {
        if (resConfig == this || resConfig == null) {
            return;
        }
        setValueBytes(resConfig.getValueBytes());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof ResConfig)) {
            return ByteArray.equalsIgnoreTrailZero(getValueBytes(), ((ResConfig) obj).getValueBytes());
        }
        return false;
    }

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        if (jSONObject.isEmpty()) {
            resetValueBytes();
            return;
        }
        int iOptInt = jSONObject.optInt(ResConfigBase.NAME_config_size, 0);
        if (iOptInt == 0) {
            iOptInt = 64;
        }
        trimToSize(iOptInt);
        setMcc(jSONObject.optInt(ResConfigBase.NAME_mcc));
        setMnc(jSONObject.optInt(ResConfigBase.NAME_mnc));
        setLanguage(jSONObject.optString("language"));
        setRegion(jSONObject.optString(ResConfigBase.NAME_region));
        setOrientation(Orientation.valueOf(jSONObject.optString("orientation")));
        setTouchscreen(Touchscreen.valueOf(jSONObject.optString(ResConfigBase.NAME_touchscreen)));
        setDensity(Density.valueOf(jSONObject.optString(ResConfigBase.NAME_density)));
        setKeyboard(Keyboard.valueOf(jSONObject.optString(ResConfigBase.NAME_keyboard)));
        setNavigation(Navigation.valueOf(jSONObject.optString(ResConfigBase.NAME_navigation)));
        setInputFlagsKeysHidden(InputFlagsKeysHidden.valueOf(jSONObject.optString(ResConfigBase.NAME_input_flags_keys_hidden)));
        setInputFlagsNavHidden(InputFlagsNavHidden.valueOf(jSONObject.optString(ResConfigBase.NAME_input_flags_nav_hidden)));
        setGender(Gender.valueOf(jSONObject.optString(ResConfigBase.NAME_gender)));
        setScreenWidth(jSONObject.optInt(ResConfigBase.NAME_screenWidth));
        setScreenHeight(jSONObject.optInt(ResConfigBase.NAME_screenHeight));
        setSdkVersion(jSONObject.optInt(ResConfigBase.NAME_sdkVersion));
        setMinorVersion(jSONObject.optInt(ResConfigBase.NAME_minorVersion));
        setScreenLayoutSize(ScreenLayoutSize.valueOf(jSONObject.optString(ResConfigBase.NAME_screen_layout_size)));
        setScreenLayoutLong(ScreenLayoutLong.valueOf(jSONObject.optString(ResConfigBase.NAME_screen_layout_long)));
        setScreenLayoutDir(ScreenLayoutDir.valueOf(jSONObject.optString(ResConfigBase.NAME_screen_layout_dir)));
        setUiModeType(UiModeType.valueOf(jSONObject.optString(ResConfigBase.NAME_ui_mode_type)));
        setUiModeNight(UiModeNight.valueOf(jSONObject.optString(ResConfigBase.NAME_ui_mode_night)));
        setSmallestScreenWidthDp(jSONObject.optInt(ResConfigBase.NAME_smallestScreenWidthDp));
        setScreenWidthDp(jSONObject.optInt(ResConfigBase.NAME_screenWidthDp));
        setScreenHeightDp(jSONObject.optInt(ResConfigBase.NAME_screenHeightDp));
        setLocaleScript(jSONObject.optString(ResConfigBase.NAME_localeScript));
        setLocaleVariantInternal(jSONObject.optString(ResConfigBase.NAME_localeVariant));
        setScreenLayoutRound(ScreenLayoutRound.valueOf(jSONObject.optString(ResConfigBase.NAME_screen_layout_round)));
        setColorModeWide(ColorModeWide.valueOf(jSONObject.optString(ResConfigBase.NAME_color_mode_wide)));
        setColorModeHdr(ColorModeHdr.valueOf(jSONObject.optString(ResConfigBase.NAME_color_mode_hdr)));
        setUnknownBytes(jSONObject.optString("unknown_bytes"));
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getColorMode() {
        return super.getColorMode();
    }

    public ColorModeHdr getColorModeHdr() {
        return ColorModeHdr.valueOf(getColorMode());
    }

    public ColorModeWide getColorModeWide() {
        return ColorModeWide.valueOf(getColorMode());
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getConfigSize() {
        return super.getConfigSize();
    }

    public Density getDensity() {
        return Density.valueOf(getDensityValue());
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getDensityValue() {
        return super.getDensityValue();
    }

    public Gender getGender() {
        return Gender.valueOf(getGenderValue());
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getGenderValue() {
        return super.getGenderValue();
    }

    public InputFlagsKeysHidden getInputFlagsKeysHidden() {
        return InputFlagsKeysHidden.valueOf(getInputFlagsValue());
    }

    public InputFlagsNavHidden getInputFlagsNavHidden() {
        return InputFlagsNavHidden.valueOf(getInputFlagsValue());
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getInputFlagsValue() {
        return super.getInputFlagsValue();
    }

    public Keyboard getKeyboard() {
        return Keyboard.valueOf(getKeyboardValue());
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getKeyboardValue() {
        return super.getKeyboardValue();
    }

    public String getLanguage() {
        char[] languageChars = getLanguageChars();
        if (ResConfigBase.isNullChars(languageChars)) {
            return null;
        }
        return new String(languageChars);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ byte[] getLanguageBytes() {
        return super.getLanguageBytes();
    }

    public char[] getLanguageChars() {
        byte[] languageBytes = getLanguageBytes();
        return unPackLanguage(languageBytes[0], languageBytes[1]);
    }

    public String getLocale() {
        StringBuilder sb = new StringBuilder();
        String language = getLanguage();
        if (language != null) {
            sb.append(language);
        }
        String region = getRegion();
        if (region != null) {
            if (sb.length() != 0) {
                sb.append(LocaleUtility.IETF_SEPARATOR);
            }
            sb.append(region);
        }
        String localeScriptInternal = getLocaleScriptInternal();
        if (localeScriptInternal != null) {
            if (sb.length() != 0) {
                sb.append(LocaleUtility.IETF_SEPARATOR);
            }
            sb.append(localeScriptInternal);
        }
        return sb.toString();
    }

    public String getLocaleScript() {
        String localeScriptInternal = getLocaleScriptInternal();
        if (localeScriptInternal == null) {
            return null;
        }
        return ResConfigBase.ensureLength(localeScriptInternal, 3, POSTFIX_locale);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ byte[] getLocaleScriptBytes() {
        return super.getLocaleScriptBytes();
    }

    public char[] getLocaleScriptChars() {
        return ResConfigBase.trimEndingZero(ResConfigBase.toCharArray(getLocaleScriptBytes()));
    }

    public String getLocaleVariant() {
        String localeVariantInternal = getLocaleVariantInternal();
        if (localeVariantInternal == null) {
            return null;
        }
        return ResConfigBase.ensureLength(localeVariantInternal, 5, POSTFIX_locale).toUpperCase();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ byte[] getLocaleVariantBytes() {
        return super.getLocaleVariantBytes();
    }

    public char[] getLocaleVariantChars() {
        return ResConfigBase.trimEndingZero(ResConfigBase.toCharArray(getLocaleVariantBytes()));
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getMcc() {
        return super.getMcc();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getMinorVersion() {
        return super.getMinorVersion();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getMnc() {
        return super.getMnc();
    }

    public Navigation getNavigation() {
        return Navigation.valueOf(getNavigationValue());
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getNavigationValue() {
        return super.getNavigationValue();
    }

    public Orientation getOrientation() {
        return Orientation.valueOf(getOrientationValue());
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getOrientationValue() {
        return super.getOrientationValue();
    }

    public String getQualifiers() {
        int iHashCode = hashCode();
        if (this.mQualifiers == null || this.mQualifiersStamp != iHashCode) {
            this.mQualifiers = new QualifierBuilder(this).build();
            this.mQualifiersStamp = iHashCode;
        }
        return this.mQualifiers;
    }

    public String getRegion() {
        char[] regionChars = getRegionChars();
        if (ResConfigBase.isNullChars(regionChars)) {
            return null;
        }
        return new String(regionChars);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ byte[] getRegionBytes() {
        return super.getRegionBytes();
    }

    public char[] getRegionChars() {
        byte[] regionBytes = getRegionBytes();
        return unPackRegion(regionBytes[0], regionBytes[1]);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getReservedColorModePadding() {
        return super.getReservedColorModePadding();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getScreenHeight() {
        return super.getScreenHeight();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getScreenHeightDp() {
        return super.getScreenHeightDp();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getScreenLayout() {
        return super.getScreenLayout();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getScreenLayout2() {
        return super.getScreenLayout2();
    }

    public ScreenLayoutDir getScreenLayoutDir() {
        return ScreenLayoutDir.valueOf(getScreenLayout());
    }

    public ScreenLayoutLong getScreenLayoutLong() {
        return ScreenLayoutLong.valueOf(getScreenLayout());
    }

    public ScreenLayoutRound getScreenLayoutRound() {
        return ScreenLayoutRound.valueOf(getScreenLayout2());
    }

    public ScreenLayoutSize getScreenLayoutSize() {
        return ScreenLayoutSize.valueOf(getScreenLayout());
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getScreenWidth() {
        return super.getScreenWidth();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getScreenWidthDp() {
        return super.getScreenWidthDp();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getSdkVersion() {
        return super.getSdkVersion();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getSmallestScreenWidthDp() {
        return super.getSmallestScreenWidthDp();
    }

    public Touchscreen getTouchscreen() {
        return Touchscreen.valueOf(getTouchscreenValue());
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getTouchscreenValue() {
        return super.getTouchscreenValue();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ int getUiMode() {
        return super.getUiMode();
    }

    public UiModeNight getUiModeNight() {
        return UiModeNight.valueOf(getUiMode());
    }

    public UiModeType getUiModeType() {
        return UiModeType.valueOf(getUiMode());
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ byte[] getUnknownBytes() {
        return super.getUnknownBytes();
    }

    public String getUnknownHexBytes(int i) {
        byte[] bArrTrimEndingZero = ResConfigBase.trimEndingZero(getUnknownBytes());
        String hex2 = null;
        if (ResConfigBase.isNullBytes(bArrTrimEndingZero)) {
            return null;
        }
        if (i < 0) {
            i = bArrTrimEndingZero.length;
        }
        if (bArrTrimEndingZero.length < i) {
            i = bArrTrimEndingZero.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            hex2 = HexUtil.toHex2(hex2, bArrTrimEndingZero[i2]);
        }
        return hex2;
    }

    public int hashCode() {
        return Arrays.hashCode(ByteArray.trimTrailZeros(getValueBytes()));
    }

    public boolean isDefault() {
        return ResConfigBase.isNullBytes(getValueBytes());
    }

    public boolean isEqualOrMoreSpecificThan(ResConfig resConfig) {
        if (resConfig == null) {
            return false;
        }
        if (resConfig != this && !resConfig.isDefault()) {
            byte[] bArrTrimTrailZeros = ByteArray.trimTrailZeros(getValueBytes());
            byte[] bArrTrimTrailZeros2 = ByteArray.trimTrailZeros(resConfig.getValueBytes());
            int length = bArrTrimTrailZeros2.length;
            if (length > bArrTrimTrailZeros.length) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                byte b = bArrTrimTrailZeros2[i];
                if (b != 0 && bArrTrimTrailZeros[i] != b) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEqualQualifiers(String str) {
        return equals(parse(str));
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void onBlockLoaded(BlockReader blockReader, Block block) throws IOException {
        super.onBlockLoaded(blockReader, block);
    }

    public String[] parseLocale(String str) {
        QualifierParser qualifierParser = new QualifierParser(this, str);
        qualifierParser.parseLocale();
        return qualifierParser.getErrors();
    }

    public String[] parseQualifiers(String str) {
        QualifierParser qualifierParser = new QualifierParser(this, str);
        qualifierParser.parse();
        return qualifierParser.getErrors();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setColorMode(int i) {
        super.setColorMode(i);
    }

    public void setColorModeHdr(ColorModeHdr colorModeHdr) {
        setColorMode(ColorModeHdr.update(colorModeHdr, getColorMode()));
    }

    public void setColorModeWide(ColorModeWide colorModeWide) {
        setColorMode(ColorModeWide.update(colorModeWide, getColorMode()));
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setConfigSize(int i) {
        super.setConfigSize(i);
    }

    public void setDensity(Density density) {
        setDensity(Density.update(density, getDensityValue()));
    }

    public void setGender(Gender gender) {
        setGenderValue(Gender.update(gender, getGenderValue()));
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setGenderValue(int i) {
        super.setGenderValue(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setInputFlags(int i) {
        super.setInputFlags(i);
    }

    public void setInputFlagsKeysHidden(InputFlagsKeysHidden inputFlagsKeysHidden) {
        setInputFlags(InputFlagsKeysHidden.update(inputFlagsKeysHidden, getInputFlagsValue()));
    }

    public void setInputFlagsNavHidden(InputFlagsNavHidden inputFlagsNavHidden) {
        setInputFlags(InputFlagsNavHidden.update(inputFlagsNavHidden, getInputFlagsValue()));
    }

    public void setKeyboard(Keyboard keyboard) {
        setKeyboard(Keyboard.update(keyboard, getKeyboardValue()));
    }

    public void setLanguage(String str) {
        setLanguage(str != null ? str.toCharArray() : null);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setLanguageBytes(byte[] bArr) {
        super.setLanguageBytes(bArr);
    }

    public void setLocaleScript(String str) {
        String strTrimPostfix = ResConfigBase.trimPostfix(str, POSTFIX_locale);
        setLocaleScript(strTrimPostfix != null ? strTrimPostfix.toCharArray() : null);
    }

    public void setLocaleVariant(char[] cArr) {
        setLocaleVariant(ResConfigBase.toByteArray(cArr, 8));
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setMcc(int i) {
        super.setMcc(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setMinorVersion(int i) {
        super.setMinorVersion(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setMnc(int i) {
        super.setMnc(i);
    }

    public void setNavigation(Navigation navigation) {
        setNavigation(Navigation.update(navigation, getNavigationValue()));
    }

    public void setOrientation(Orientation orientation) {
        setOrientation(Orientation.update(orientation, getOrientationValue()));
    }

    public void setRegion(String str) {
        char[] charArray;
        if (str != null) {
            if (str.length() == 3 && str.charAt(0) == 'r') {
                str = str.substring(1);
            }
            charArray = str.toCharArray();
        } else {
            charArray = null;
        }
        setRegion(charArray);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setRegionBytes(byte[] bArr) {
        super.setRegionBytes(bArr);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setReservedColorModePadding(int i) {
        super.setReservedColorModePadding(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setScreenHeight(int i) {
        super.setScreenHeight(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setScreenHeightDp(int i) {
        super.setScreenHeightDp(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setScreenLayout(int i) {
        super.setScreenLayout(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setScreenLayout2(int i) {
        super.setScreenLayout2(i);
    }

    public void setScreenLayoutDir(ScreenLayoutDir screenLayoutDir) {
        setScreenLayout(ScreenLayoutDir.update(screenLayoutDir, getScreenLayout()));
    }

    public void setScreenLayoutLong(ScreenLayoutLong screenLayoutLong) {
        setScreenLayout(ScreenLayoutLong.update(screenLayoutLong, getScreenLayout()));
    }

    public void setScreenLayoutRound(ScreenLayoutRound screenLayoutRound) {
        setScreenLayout2(ScreenLayoutRound.update(screenLayoutRound, getScreenLayout2()));
    }

    public void setScreenLayoutSize(ScreenLayoutSize screenLayoutSize) {
        setScreenLayout(ScreenLayoutSize.update(screenLayoutSize, getScreenLayout()));
    }

    public void setScreenSize(int i, int i2) {
        setScreenWidth(i);
        setScreenHeight(i2);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setScreenWidth(int i) {
        super.setScreenWidth(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setScreenWidthDp(int i) {
        super.setScreenWidthDp(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setSdkVersion(int i) {
        super.setSdkVersion(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setSmallestScreenWidthDp(int i) {
        super.setSmallestScreenWidthDp(i);
    }

    public void setTouchscreen(Touchscreen touchscreen) {
        setTouchscreen(Touchscreen.update(touchscreen, getTouchscreenValue()));
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setUiMode(int i) {
        super.setUiMode(i);
    }

    public void setUiModeNight(UiModeNight uiModeNight) {
        setUiMode(UiModeNight.update(uiModeNight, getUiMode()));
    }

    public void setUiModeType(UiModeType uiModeType) {
        setUiMode(UiModeType.update(uiModeType, getUiMode()));
    }

    public void setUnknownBytes(String str) {
        if (str == null || str.length() == 0 || str.length() % 2 != 0) {
            return;
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            try {
                bArr[i / 2] = (byte) HexUtil.parseHex(new String(charArray, i, 2));
            } catch (NumberFormatException unused) {
                return;
            }
        }
        setUnknownBytes(bArr);
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        if (!isDefault()) {
            int mcc = getMcc();
            if (mcc != 0) {
                jSONObject.put(ResConfigBase.NAME_mcc, mcc);
            }
            int mnc = getMnc();
            if (mnc != 0) {
                jSONObject.put(ResConfigBase.NAME_mnc, mnc);
            }
            String language = getLanguage();
            if (language != null) {
                jSONObject.put("language", language);
            }
            String region = getRegion();
            if (region != null) {
                jSONObject.put(ResConfigBase.NAME_region, region);
            }
            jSONObject.put("orientation", Flag.toString(getOrientation()));
            jSONObject.put(ResConfigBase.NAME_touchscreen, Flag.toString(getTouchscreen()));
            jSONObject.put(ResConfigBase.NAME_density, Flag.toString(getDensity()));
            jSONObject.put(ResConfigBase.NAME_keyboard, Flag.toString(getKeyboard()));
            jSONObject.put(ResConfigBase.NAME_navigation, Flag.toString(getNavigation()));
            jSONObject.put(ResConfigBase.NAME_input_flags_keys_hidden, Flag.toString(getInputFlagsKeysHidden()));
            jSONObject.put(ResConfigBase.NAME_input_flags_nav_hidden, Flag.toString(getInputFlagsNavHidden()));
            jSONObject.put(ResConfigBase.NAME_gender, Flag.toString(getGender()));
            int screenWidth = getScreenWidth();
            if (screenWidth != 0) {
                jSONObject.put(ResConfigBase.NAME_screenWidth, screenWidth);
            }
            int screenHeight = getScreenHeight();
            if (screenHeight != 0) {
                jSONObject.put(ResConfigBase.NAME_screenHeight, screenHeight);
            }
            int sdkVersion = getSdkVersion();
            if (sdkVersion != 0) {
                jSONObject.put(ResConfigBase.NAME_sdkVersion, sdkVersion);
            }
            int minorVersion = getMinorVersion();
            if (minorVersion != 0) {
                jSONObject.put(ResConfigBase.NAME_minorVersion, minorVersion);
            }
            jSONObject.put(ResConfigBase.NAME_screen_layout_size, Flag.toString(getScreenLayoutSize()));
            jSONObject.put(ResConfigBase.NAME_screen_layout_long, Flag.toString(getScreenLayoutLong()));
            jSONObject.put(ResConfigBase.NAME_screen_layout_dir, Flag.toString(getScreenLayoutDir()));
            jSONObject.put(ResConfigBase.NAME_ui_mode_type, Flag.toString(getUiModeType()));
            jSONObject.put(ResConfigBase.NAME_ui_mode_night, Flag.toString(getUiModeNight()));
            int smallestScreenWidthDp = getSmallestScreenWidthDp();
            if (smallestScreenWidthDp != 0) {
                jSONObject.put(ResConfigBase.NAME_smallestScreenWidthDp, smallestScreenWidthDp);
            }
            int screenWidthDp = getScreenWidthDp();
            if (screenWidthDp != 0) {
                jSONObject.put(ResConfigBase.NAME_screenWidthDp, screenWidthDp);
            }
            int screenHeightDp = getScreenHeightDp();
            if (screenHeightDp != 0) {
                jSONObject.put(ResConfigBase.NAME_screenHeightDp, screenHeightDp);
            }
            String localeScriptInternal = getLocaleScriptInternal();
            if (localeScriptInternal != null) {
                jSONObject.put(ResConfigBase.NAME_localeScript, localeScriptInternal);
            }
            String localeVariantInternal = getLocaleVariantInternal();
            if (localeVariantInternal != null) {
                jSONObject.put(ResConfigBase.NAME_localeVariant, localeVariantInternal);
            }
            jSONObject.put(ResConfigBase.NAME_screen_layout_round, Flag.toString(getScreenLayoutRound()));
            jSONObject.put(ResConfigBase.NAME_color_mode_wide, Flag.toString(getColorModeWide()));
            jSONObject.put(ResConfigBase.NAME_color_mode_hdr, Flag.toString(getColorModeHdr()));
            String unknownHexBytes = getUnknownHexBytes(-1);
            if (unknownHexBytes != null) {
                jSONObject.put("unknown_bytes", unknownHexBytes);
                jSONObject.put(ResConfigBase.NAME_config_size, getConfigSize());
            }
        }
        return jSONObject;
    }

    public String toString() {
        String qualifiers = getQualifiers();
        if (qualifiers.length() == 0) {
            return "[DEFAULT]";
        }
        return "[" + qualifiers + "]";
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void trimToMinimumSize() {
        super.trimToMinimumSize();
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ boolean trimToSize(int i) {
        return super.trimToSize(i);
    }

    public ResConfig() {
        this(64);
    }

    public static final class ColorModeHdr extends Flag {
        public static final ColorModeHdr HIGHDR;
        public static final ColorModeHdr LOWDR;
        public static final int MASK = 12;
        public static final ColorModeHdr[] VALUES;

        static {
            ColorModeHdr colorModeHdr = new ColorModeHdr("lowdr", 4);
            LOWDR = colorModeHdr;
            ColorModeHdr colorModeHdr2 = new ColorModeHdr("highdr", 8);
            HIGHDR = colorModeHdr2;
            VALUES = new ColorModeHdr[]{colorModeHdr, colorModeHdr2};
        }

        private ColorModeHdr(String str, int i) {
            super(str, i);
        }

        public static ColorModeHdr fromQualifiers(String str) {
            return (ColorModeHdr) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(ColorModeHdr colorModeHdr, int i) {
            return Flag.update(12, colorModeHdr, i);
        }

        public static ColorModeHdr valueOf(int i) {
            return (ColorModeHdr) Flag.valueOf(VALUES, 12, i);
        }

        public static ColorModeHdr fromQualifiers(String[] strArr) {
            return (ColorModeHdr) Flag.fromQualifiers(VALUES, strArr);
        }

        public static ColorModeHdr valueOf(String str) {
            return (ColorModeHdr) Flag.valueOf(VALUES, str);
        }
    }

    public static final class ColorModeWide extends Flag {
        public static final int MASK = 3;
        public static final ColorModeWide NOWIDECG;
        public static final ColorModeWide[] VALUES;
        public static final ColorModeWide WIDECG;

        static {
            ColorModeWide colorModeWide = new ColorModeWide("nowidecg", 1);
            NOWIDECG = colorModeWide;
            ColorModeWide colorModeWide2 = new ColorModeWide("widecg", 2);
            WIDECG = colorModeWide2;
            VALUES = new ColorModeWide[]{colorModeWide, colorModeWide2};
        }

        private ColorModeWide(String str, int i) {
            super(str, i);
        }

        public static ColorModeWide fromQualifiers(String str) {
            return (ColorModeWide) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(ColorModeWide colorModeWide, int i) {
            return Flag.update(3, colorModeWide, i);
        }

        public static ColorModeWide valueOf(int i) {
            return (ColorModeWide) Flag.valueOf(VALUES, 3, i);
        }

        public static ColorModeWide fromQualifiers(String[] strArr) {
            return (ColorModeWide) Flag.fromQualifiers(VALUES, strArr);
        }

        public static ColorModeWide valueOf(String str) {
            return (ColorModeWide) Flag.valueOf(VALUES, str);
        }
    }

    public static class Flag implements Comparable<Flag> {
        private final int flag;
        private final String name;

        public Flag(String str, int i) {
            this.name = str;
            this.flag = i;
        }

        public static <T extends Flag> T fromQualifiers(T[] tArr, String[] strArr) {
            if (strArr == null) {
                return null;
            }
            for (int i = 0; i < strArr.length; i++) {
                T t = (T) valueOf(tArr, strArr[i]);
                if (t != null) {
                    strArr[i] = null;
                    return t;
                }
            }
            return null;
        }

        public static String toString(Flag flag) {
            if (flag != null) {
                return flag.toString();
            }
            return null;
        }

        public static int update(int i, Flag flag, int i2) {
            int i3 = (~i) & 255 & i2;
            return flag != null ? i3 | flag.getFlag() : i3;
        }

        public static <T extends Flag> T valueOf(T[] tArr, String str) {
            if (str != null && str.length() != 0) {
                if (str.charAt(0) == '-') {
                    str = str.substring(1);
                }
                String lowerCase = str.toLowerCase();
                for (T t : tArr) {
                    if (lowerCase.equals(t.toString())) {
                        return t;
                    }
                }
            }
            return null;
        }

        @Override // java.lang.Comparable
        public int compareTo(Flag flag) {
            if (flag == null) {
                return 1;
            }
            return CompareUtil.compare(getFlag(), flag.getFlag());
        }

        public boolean equals(Object obj) {
            return obj == this;
        }

        public int getFlag() {
            return this.flag;
        }

        public int hashCode() {
            return super.hashCode();
        }

        public String toString() {
            return this.name;
        }

        public static <T extends Flag> T fromQualifiers(T[] tArr, String str) {
            if (str == null) {
                return null;
            }
            return (T) fromQualifiers(tArr, str.split("\\s*-\\s*"));
        }

        public static <T extends Flag> T valueOf(T[] tArr, int i, int i2) {
            int i3 = i & i2;
            for (T t : tArr) {
                if (i3 == t.getFlag()) {
                    return t;
                }
            }
            return null;
        }
    }

    public static final class Gender extends Flag {
        public static final Gender FEMININE;
        public static final Gender MASCULINE;
        public static final int MASK = 3;
        public static final Gender NEUTER;
        public static final Gender[] VALUES;

        static {
            Gender gender = new Gender("neuter", 1);
            NEUTER = gender;
            Gender gender2 = new Gender("feminine", 2);
            FEMININE = gender2;
            Gender gender3 = new Gender("masculine", 3);
            MASCULINE = gender3;
            VALUES = new Gender[]{gender, gender2, gender3};
        }

        private Gender(String str, int i) {
            super(str, i);
        }

        public static Gender fromQualifiers(String str) {
            return (Gender) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(Gender gender, int i) {
            return Flag.update(3, gender, i);
        }

        public static Gender valueOf(int i) {
            return (Gender) Flag.valueOf(VALUES, 3, i);
        }

        public static Gender fromQualifiers(String[] strArr) {
            return (Gender) Flag.fromQualifiers(VALUES, strArr);
        }

        public static Gender valueOf(String str) {
            return (Gender) Flag.valueOf(VALUES, str);
        }
    }

    public static final class InputFlagsKeysHidden extends Flag {
        public static final InputFlagsKeysHidden KEYSEXPOSED;
        public static final InputFlagsKeysHidden KEYSHIDDEN;
        public static final InputFlagsKeysHidden KEYSSOFT;
        public static final int MASK = 3;
        public static final InputFlagsKeysHidden[] VALUES;

        static {
            InputFlagsKeysHidden inputFlagsKeysHidden = new InputFlagsKeysHidden("keysexposed", 1);
            KEYSEXPOSED = inputFlagsKeysHidden;
            InputFlagsKeysHidden inputFlagsKeysHidden2 = new InputFlagsKeysHidden("keyshidden", 2);
            KEYSHIDDEN = inputFlagsKeysHidden2;
            InputFlagsKeysHidden inputFlagsKeysHidden3 = new InputFlagsKeysHidden("keyssoft", 3);
            KEYSSOFT = inputFlagsKeysHidden3;
            VALUES = new InputFlagsKeysHidden[]{inputFlagsKeysHidden, inputFlagsKeysHidden2, inputFlagsKeysHidden3};
        }

        private InputFlagsKeysHidden(String str, int i) {
            super(str, i);
        }

        public static InputFlagsKeysHidden fromQualifiers(String str) {
            return (InputFlagsKeysHidden) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(InputFlagsKeysHidden inputFlagsKeysHidden, int i) {
            return Flag.update(3, inputFlagsKeysHidden, i);
        }

        public static InputFlagsKeysHidden valueOf(int i) {
            return (InputFlagsKeysHidden) Flag.valueOf(VALUES, 3, i);
        }

        public static InputFlagsKeysHidden fromQualifiers(String[] strArr) {
            return (InputFlagsKeysHidden) Flag.fromQualifiers(VALUES, strArr);
        }

        public static InputFlagsKeysHidden valueOf(String str) {
            return (InputFlagsKeysHidden) Flag.valueOf(VALUES, str);
        }
    }

    public static final class InputFlagsNavHidden extends Flag {
        public static final int MASK = 12;
        public static final InputFlagsNavHidden NAVEXPOSED;
        public static final InputFlagsNavHidden NAVHIDDEN;
        public static final InputFlagsNavHidden[] VALUES;

        static {
            InputFlagsNavHidden inputFlagsNavHidden = new InputFlagsNavHidden("navexposed", 4);
            NAVEXPOSED = inputFlagsNavHidden;
            InputFlagsNavHidden inputFlagsNavHidden2 = new InputFlagsNavHidden("navhidden", 8);
            NAVHIDDEN = inputFlagsNavHidden2;
            VALUES = new InputFlagsNavHidden[]{inputFlagsNavHidden, inputFlagsNavHidden2};
        }

        private InputFlagsNavHidden(String str, int i) {
            super(str, i);
        }

        public static InputFlagsNavHidden fromQualifiers(String str) {
            return (InputFlagsNavHidden) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(InputFlagsNavHidden inputFlagsNavHidden, int i) {
            return Flag.update(12, inputFlagsNavHidden, i);
        }

        public static InputFlagsNavHidden valueOf(int i) {
            return (InputFlagsNavHidden) Flag.valueOf(VALUES, 12, i);
        }

        public static InputFlagsNavHidden fromQualifiers(String[] strArr) {
            return (InputFlagsNavHidden) Flag.fromQualifiers(VALUES, strArr);
        }

        public static InputFlagsNavHidden valueOf(String str) {
            return (InputFlagsNavHidden) Flag.valueOf(VALUES, str);
        }
    }

    public static final class Keyboard extends Flag {
        public static final Keyboard KEY12;
        public static final int MASK = 15;
        public static final Keyboard NOKEYS;
        public static final Keyboard QWERTY;
        public static final Keyboard[] VALUES;

        static {
            Keyboard keyboard = new Keyboard("nokeys", 1);
            NOKEYS = keyboard;
            Keyboard keyboard2 = new Keyboard("qwerty", 2);
            QWERTY = keyboard2;
            Keyboard keyboard3 = new Keyboard("12key", 3);
            KEY12 = keyboard3;
            VALUES = new Keyboard[]{keyboard, keyboard2, keyboard3};
        }

        private Keyboard(String str, int i) {
            super(str, i);
        }

        public static Keyboard fromQualifiers(String str) {
            return (Keyboard) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(Keyboard keyboard, int i) {
            return Flag.update(15, keyboard, i);
        }

        public static Keyboard valueOf(int i) {
            return (Keyboard) Flag.valueOf(VALUES, 15, i);
        }

        public static Keyboard fromQualifiers(String[] strArr) {
            return (Keyboard) Flag.fromQualifiers(VALUES, strArr);
        }

        public static Keyboard valueOf(String str) {
            return (Keyboard) Flag.valueOf(VALUES, str);
        }
    }

    public static final class Navigation extends Flag {
        public static final Navigation DPAD;
        public static final int MASK = 15;
        public static final Navigation NONAV;
        public static final Navigation TRACKBALL;
        public static final Navigation[] VALUES;
        public static final Navigation WHEEL;

        static {
            Navigation navigation = new Navigation("nonav", 1);
            NONAV = navigation;
            Navigation navigation2 = new Navigation("dpad", 2);
            DPAD = navigation2;
            Navigation navigation3 = new Navigation("trackball", 3);
            TRACKBALL = navigation3;
            Navigation navigation4 = new Navigation("wheel", 4);
            WHEEL = navigation4;
            VALUES = new Navigation[]{navigation, navigation2, navigation3, navigation4};
        }

        private Navigation(String str, int i) {
            super(str, i);
        }

        public static Navigation fromQualifiers(String str) {
            return (Navigation) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(Navigation navigation, int i) {
            return Flag.update(15, navigation, i);
        }

        public static Navigation valueOf(int i) {
            return (Navigation) Flag.valueOf(VALUES, 15, i);
        }

        public static Navigation fromQualifiers(String[] strArr) {
            return (Navigation) Flag.fromQualifiers(VALUES, strArr);
        }

        public static Navigation valueOf(String str) {
            return (Navigation) Flag.valueOf(VALUES, str);
        }
    }

    public static final class Orientation extends Flag {
        public static final Orientation LAND;
        public static final int MASK = 15;
        public static final Orientation PORT;
        public static final Orientation SQUARE;
        public static final Orientation[] VALUES;

        static {
            Orientation orientation = new Orientation("port", 1);
            PORT = orientation;
            Orientation orientation2 = new Orientation("land", 2);
            LAND = orientation2;
            Orientation orientation3 = new Orientation("square", 3);
            SQUARE = orientation3;
            VALUES = new Orientation[]{orientation, orientation2, orientation3};
        }

        private Orientation(String str, int i) {
            super(str, i);
        }

        public static Orientation fromQualifiers(String str) {
            return (Orientation) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(Orientation orientation, int i) {
            return Flag.update(15, orientation, i);
        }

        public static Orientation valueOf(int i) {
            return (Orientation) Flag.valueOf(VALUES, 15, i);
        }

        public static Orientation fromQualifiers(String[] strArr) {
            return (Orientation) Flag.fromQualifiers(VALUES, strArr);
        }

        public static Orientation valueOf(String str) {
            return (Orientation) Flag.valueOf(VALUES, str);
        }
    }

    public static final class ScreenLayoutDir extends Flag {
        public static final ScreenLayoutDir LDLTR;
        public static final ScreenLayoutDir LDRTL;
        public static final int MASK = 192;
        public static final ScreenLayoutDir[] VALUES;

        static {
            ScreenLayoutDir screenLayoutDir = new ScreenLayoutDir("ldltr", 64);
            LDLTR = screenLayoutDir;
            ScreenLayoutDir screenLayoutDir2 = new ScreenLayoutDir("ldrtl", 128);
            LDRTL = screenLayoutDir2;
            VALUES = new ScreenLayoutDir[]{screenLayoutDir, screenLayoutDir2};
        }

        private ScreenLayoutDir(String str, int i) {
            super(str, i);
        }

        public static ScreenLayoutDir fromQualifiers(String str) {
            return (ScreenLayoutDir) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(ScreenLayoutDir screenLayoutDir, int i) {
            return Flag.update(192, screenLayoutDir, i);
        }

        public static ScreenLayoutDir valueOf(int i) {
            return (ScreenLayoutDir) Flag.valueOf(VALUES, 192, i);
        }

        public static ScreenLayoutDir fromQualifiers(String[] strArr) {
            return (ScreenLayoutDir) Flag.fromQualifiers(VALUES, strArr);
        }

        public static ScreenLayoutDir valueOf(String str) {
            return (ScreenLayoutDir) Flag.valueOf(VALUES, str);
        }
    }

    public static final class ScreenLayoutLong extends Flag {
        public static final ScreenLayoutLong LONG;
        public static final int MASK = 48;
        public static final ScreenLayoutLong NOTLONG;
        public static final ScreenLayoutLong[] VALUES;

        static {
            ScreenLayoutLong screenLayoutLong = new ScreenLayoutLong("notlong", 16);
            NOTLONG = screenLayoutLong;
            ScreenLayoutLong screenLayoutLong2 = new ScreenLayoutLong("long", 32);
            LONG = screenLayoutLong2;
            VALUES = new ScreenLayoutLong[]{screenLayoutLong, screenLayoutLong2};
        }

        private ScreenLayoutLong(String str, int i) {
            super(str, i);
        }

        public static ScreenLayoutLong fromQualifiers(String str) {
            return (ScreenLayoutLong) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(ScreenLayoutLong screenLayoutLong, int i) {
            return Flag.update(48, screenLayoutLong, i);
        }

        public static ScreenLayoutLong valueOf(int i) {
            return (ScreenLayoutLong) Flag.valueOf(VALUES, 48, i);
        }

        public static ScreenLayoutLong fromQualifiers(String[] strArr) {
            return (ScreenLayoutLong) Flag.fromQualifiers(VALUES, strArr);
        }

        public static ScreenLayoutLong valueOf(String str) {
            return (ScreenLayoutLong) Flag.valueOf(VALUES, str);
        }
    }

    public static final class ScreenLayoutRound extends Flag {
        public static final int MASK = 3;
        public static final ScreenLayoutRound NOTROUND;
        public static final ScreenLayoutRound ROUND;
        public static final ScreenLayoutRound[] VALUES;

        static {
            ScreenLayoutRound screenLayoutRound = new ScreenLayoutRound("notround", 1);
            NOTROUND = screenLayoutRound;
            ScreenLayoutRound screenLayoutRound2 = new ScreenLayoutRound(Keywords.FUNC_ROUND_STRING, 2);
            ROUND = screenLayoutRound2;
            VALUES = new ScreenLayoutRound[]{screenLayoutRound, screenLayoutRound2};
        }

        private ScreenLayoutRound(String str, int i) {
            super(str, i);
        }

        public static ScreenLayoutRound fromQualifiers(String str) {
            return (ScreenLayoutRound) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(ScreenLayoutRound screenLayoutRound, int i) {
            return Flag.update(3, screenLayoutRound, i);
        }

        public static ScreenLayoutRound valueOf(int i) {
            return (ScreenLayoutRound) Flag.valueOf(VALUES, 3, i);
        }

        public static ScreenLayoutRound fromQualifiers(String[] strArr) {
            return (ScreenLayoutRound) Flag.fromQualifiers(VALUES, strArr);
        }

        public static ScreenLayoutRound valueOf(String str) {
            return (ScreenLayoutRound) Flag.valueOf(VALUES, str);
        }
    }

    public static final class ScreenLayoutSize extends Flag {
        public static final ScreenLayoutSize LARGE;
        public static final int MASK = 15;
        public static final ScreenLayoutSize NORMAL;
        public static final ScreenLayoutSize SMALL;
        public static final ScreenLayoutSize[] VALUES;
        public static final ScreenLayoutSize XLARGE;

        static {
            ScreenLayoutSize screenLayoutSize = new ScreenLayoutSize("small", 1);
            SMALL = screenLayoutSize;
            ScreenLayoutSize screenLayoutSize2 = new ScreenLayoutSize("normal", 2);
            NORMAL = screenLayoutSize2;
            ScreenLayoutSize screenLayoutSize3 = new ScreenLayoutSize("large", 3);
            LARGE = screenLayoutSize3;
            ScreenLayoutSize screenLayoutSize4 = new ScreenLayoutSize("xlarge", 4);
            XLARGE = screenLayoutSize4;
            VALUES = new ScreenLayoutSize[]{screenLayoutSize, screenLayoutSize2, screenLayoutSize3, screenLayoutSize4};
        }

        private ScreenLayoutSize(String str, int i) {
            super(str, i);
        }

        public static ScreenLayoutSize fromQualifiers(String str) {
            return (ScreenLayoutSize) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(ScreenLayoutSize screenLayoutSize, int i) {
            return Flag.update(15, screenLayoutSize, i);
        }

        public static ScreenLayoutSize valueOf(int i) {
            return (ScreenLayoutSize) Flag.valueOf(VALUES, 15, i);
        }

        public static ScreenLayoutSize fromQualifiers(String[] strArr) {
            return (ScreenLayoutSize) Flag.fromQualifiers(VALUES, strArr);
        }

        public static ScreenLayoutSize valueOf(String str) {
            return (ScreenLayoutSize) Flag.valueOf(VALUES, str);
        }
    }

    public static final class Touchscreen extends Flag {
        public static final Touchscreen FINGER;
        public static final int MASK = 15;
        public static final Touchscreen NOTOUCH;
        public static final Touchscreen STYLUS;
        public static final Touchscreen[] VALUES;

        static {
            Touchscreen touchscreen = new Touchscreen("notouch", 1);
            NOTOUCH = touchscreen;
            Touchscreen touchscreen2 = new Touchscreen("stylus", 2);
            STYLUS = touchscreen2;
            Touchscreen touchscreen3 = new Touchscreen("finger", 3);
            FINGER = touchscreen3;
            VALUES = new Touchscreen[]{touchscreen, touchscreen2, touchscreen3};
        }

        private Touchscreen(String str, int i) {
            super(str, i);
        }

        public static Touchscreen fromQualifiers(String str) {
            return (Touchscreen) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(Touchscreen touchscreen, int i) {
            return Flag.update(15, touchscreen, i);
        }

        public static Touchscreen valueOf(int i) {
            return (Touchscreen) Flag.valueOf(VALUES, 15, i);
        }

        public static Touchscreen fromQualifiers(String[] strArr) {
            return (Touchscreen) Flag.fromQualifiers(VALUES, strArr);
        }

        public static Touchscreen valueOf(String str) {
            return (Touchscreen) Flag.valueOf(VALUES, str);
        }
    }

    public static final class UiModeNight extends Flag {
        public static final int MASK = 48;
        public static final UiModeNight NIGHT;
        public static final UiModeNight NOTNIGHT;
        private static final UiModeNight[] VALUES;

        static {
            UiModeNight uiModeNight = new UiModeNight("notnight", 16);
            NOTNIGHT = uiModeNight;
            UiModeNight uiModeNight2 = new UiModeNight("night", 32);
            NIGHT = uiModeNight2;
            VALUES = new UiModeNight[]{uiModeNight, uiModeNight2};
        }

        private UiModeNight(String str, int i) {
            super(str, i);
        }

        public static UiModeNight fromQualifiers(String str) {
            return (UiModeNight) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(UiModeNight uiModeNight, int i) {
            return Flag.update(48, uiModeNight, i);
        }

        public static UiModeNight valueOf(int i) {
            return (UiModeNight) Flag.valueOf(VALUES, 48, i);
        }

        public static UiModeNight fromQualifiers(String[] strArr) {
            return (UiModeNight) Flag.fromQualifiers(VALUES, strArr);
        }

        public static UiModeNight valueOf(String str) {
            return (UiModeNight) Flag.valueOf(VALUES, str);
        }
    }

    public static final class UiModeType extends Flag {
        public static final UiModeType APPLIANCE;
        public static final UiModeType CAR;
        public static final UiModeType DESK;
        public static final UiModeType GODZILLAUI;
        public static final UiModeType HUGEUI;
        public static final UiModeType LARGEUI;
        public static final int MASK = 15;
        public static final UiModeType MEDIUMUI;
        public static final UiModeType NORMAL;
        public static final UiModeType SMALLUI;
        public static final UiModeType TELEVISION;
        private static final UiModeType[] VALUES;
        public static final UiModeType VRHEADSET;
        public static final UiModeType WATCH;

        static {
            UiModeType uiModeType = new UiModeType("normal", 1);
            NORMAL = uiModeType;
            UiModeType uiModeType2 = new UiModeType("desk", 2);
            DESK = uiModeType2;
            UiModeType uiModeType3 = new UiModeType("car", 3);
            CAR = uiModeType3;
            UiModeType uiModeType4 = new UiModeType("television", 4);
            TELEVISION = uiModeType4;
            UiModeType uiModeType5 = new UiModeType("appliance", 5);
            APPLIANCE = uiModeType5;
            UiModeType uiModeType6 = new UiModeType("watch", 6);
            WATCH = uiModeType6;
            UiModeType uiModeType7 = new UiModeType("vrheadset", 7);
            VRHEADSET = uiModeType7;
            UiModeType uiModeType8 = new UiModeType("godzillaui", 11);
            GODZILLAUI = uiModeType8;
            UiModeType uiModeType9 = new UiModeType("smallui", 12);
            SMALLUI = uiModeType9;
            UiModeType uiModeType10 = new UiModeType("mediumui", 13);
            MEDIUMUI = uiModeType10;
            UiModeType uiModeType11 = new UiModeType("largeui", 14);
            LARGEUI = uiModeType11;
            UiModeType uiModeType12 = new UiModeType("hugeui", 15);
            HUGEUI = uiModeType12;
            VALUES = new UiModeType[]{uiModeType, uiModeType2, uiModeType3, uiModeType4, uiModeType5, uiModeType6, uiModeType7, uiModeType8, uiModeType9, uiModeType10, uiModeType11, uiModeType12};
        }

        private UiModeType(String str, int i) {
            super(str, i);
        }

        public static UiModeType fromQualifiers(String str) {
            return (UiModeType) Flag.fromQualifiers(VALUES, str);
        }

        public static int update(UiModeType uiModeType, int i) {
            return Flag.update(15, uiModeType, i);
        }

        public static UiModeType valueOf(int i) {
            return (UiModeType) Flag.valueOf(VALUES, 15, i);
        }

        public static UiModeType fromQualifiers(String[] strArr) {
            return (UiModeType) Flag.fromQualifiers(VALUES, strArr);
        }

        public static UiModeType valueOf(String str) {
            return (UiModeType) Flag.valueOf(VALUES, str);
        }
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setLocaleVariant(byte[] bArr) {
        super.setLocaleVariant(bArr);
    }

    public void setLocaleVariant(String str) {
        if (str != null) {
            str = str.toLowerCase();
        }
        setLocaleVariantInternal(str);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setDensity(int i) {
        super.setDensity(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setKeyboard(int i) {
        super.setKeyboard(i);
    }

    public void setLanguage(char[] cArr) {
        setLanguageBytes(packLanguage(cArr));
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setNavigation(int i) {
        super.setNavigation(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setOrientation(int i) {
        super.setOrientation(i);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setTouchscreen(int i) {
        super.setTouchscreen(i);
    }

    public static class QualifierParser {
        private final ResConfig mConfig;
        private boolean mEmpty;
        private boolean mLanguageRegionParsed;
        private boolean mParseComplete;
        private final int mPreferredSize;
        private final String[] mQualifiers;
        private static final Pattern PATTERN_PREFIX_NUMBER = Pattern.compile("^([mcnv]+)([0-9]+)$");
        private static final Pattern PATTERN_DP = Pattern.compile("^([swh]+)([0-9]+)dp$");
        private static final Pattern PATTERN_WIDTH_HEIGHT = Pattern.compile("^([0-9]+)[xX]([0-9]+)$");
        private static final Pattern PATTERN_LOCALE_NUMBERING_SYSTEM = Pattern.compile("^u\\+nu\\+(.{1,8})$");
        private static final Pattern PATTERN_LOCALE_SCRIPT_VARIANT = Pattern.compile("^b(\\+[a-z]{2})?(\\+r?[A-Z0-9]{2,3})?(\\+[A-Z][a-z]{3})?(\\+[A-Z]{2,8})?$");

        public QualifierParser(ResConfig resConfig, String[] strArr) {
            this.mConfig = resConfig;
            this.mQualifiers = strArr;
            this.mPreferredSize = resConfig.getConfigSize();
        }

        private static boolean isAtoZLower(char c) {
            return c <= 'z' && c >= 'a';
        }

        private static boolean isAtoZUpper(char c) {
            return c <= 'Z' && c >= 'A';
        }

        private static boolean isDigit(char c) {
            return c <= '9' && c >= '0';
        }

        private static boolean isEmpty(String[] strArr) {
            if (strArr == null) {
                return true;
            }
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                if (str != null) {
                    if (str.length() != 0) {
                        return false;
                    }
                    strArr[i] = null;
                }
            }
            return true;
        }

        private static boolean isLanguage(String str) {
            if (str == null) {
                return false;
            }
            char[] charArray = str.toCharArray();
            int length = charArray.length;
            if (length != 2 && length != 3) {
                return false;
            }
            for (char c : charArray) {
                if (!isAtoZLower(c)) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isLocaleRegion(String str) {
            if (str == null) {
                return false;
            }
            int length = str.length();
            if (length != 2 && length != 3) {
                return false;
            }
            char[] charArray = str.toCharArray();
            if (length == 2) {
                for (char c : charArray) {
                    if (!isAtoZUpper(c)) {
                        return false;
                    }
                }
                return true;
            }
            for (char c2 : charArray) {
                if (!isDigit(c2)) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isRegion(String str) {
            if (str == null || str.length() != 3) {
                return false;
            }
            char[] charArray = str.toCharArray();
            boolean zIsDigit = false;
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (i == 0) {
                    if (c != 'r' && !(zIsDigit = isDigit(c))) {
                        return false;
                    }
                } else if (zIsDigit) {
                    if (!isDigit(c)) {
                        return false;
                    }
                } else if (!isAtoZUpper(c)) {
                    return false;
                }
            }
            return true;
        }

        private void onParseComplete() {
            this.mConfig.trimToSize(this.mPreferredSize);
            this.mParseComplete = true;
        }

        private boolean parseDp(String str) {
            if (str == null) {
                return false;
            }
            Matcher matcher = PATTERN_DP.matcher(str);
            if (!matcher.find()) {
                return false;
            }
            String strGroup = matcher.group(1);
            int i = Integer.parseInt(matcher.group(2));
            ResConfig resConfig = this.mConfig;
            if ("sw".equals(strGroup)) {
                resConfig.setSmallestScreenWidthDp(i);
            } else if ("w".equals(strGroup)) {
                resConfig.setScreenWidthDp(i);
            } else {
                if (!"h".equals(strGroup)) {
                    return false;
                }
                resConfig.setScreenHeightDp(i);
            }
            return true;
        }

        private void parseLanguage() {
            if (this.mLanguageRegionParsed || isEmpty()) {
                return;
            }
            String[] strArr = this.mQualifiers;
            for (int i = 0; i < strArr.length; i++) {
                if (parseLanguage(strArr[i])) {
                    strArr[i] = null;
                    return;
                }
            }
        }

        private void parseLocaleNumberingSystem() {
            if (isEmpty()) {
                return;
            }
            String[] strArr = this.mQualifiers;
            for (int i = 0; i < strArr.length; i++) {
                if (parseLocaleNumberingSystem(strArr[i])) {
                    strArr[i] = null;
                    return;
                }
            }
        }

        private void parseLocaleRegion() {
            if (this.mLanguageRegionParsed || isEmpty()) {
                return;
            }
            String[] strArr = this.mQualifiers;
            for (int i = 0; i < strArr.length; i++) {
                if (parseLocaleRegion(strArr[i])) {
                    strArr[i] = null;
                    return;
                }
            }
        }

        private boolean parseLocaleScriptVariant(String str) {
            if (str != null && str.length() >= 4) {
                char[] charArray = str.toCharArray();
                if (charArray[0] == 'b' && charArray[1] == '+') {
                    Matcher matcher = PATTERN_LOCALE_SCRIPT_VARIANT.matcher(str);
                    if (matcher.find()) {
                        ResConfig resConfig = this.mConfig;
                        resConfig.setLanguage(trimPlus(matcher.group(1)));
                        resConfig.setRegion(trimPlus(matcher.group(2)));
                        resConfig.setLocaleScript(trimPlus(matcher.group(3)));
                        resConfig.setLocaleVariant(trimPlus(matcher.group(4)));
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean parsePrefixedNumber(String str) {
            if (str == null) {
                return false;
            }
            Matcher matcher = PATTERN_PREFIX_NUMBER.matcher(str);
            if (!matcher.find()) {
                return false;
            }
            String strGroup = matcher.group(1);
            int i = Integer.parseInt(matcher.group(2));
            ResConfig resConfig = this.mConfig;
            if (ResConfigBase.NAME_mcc.equals(strGroup)) {
                resConfig.setMcc(i);
            } else if (ResConfigBase.NAME_mnc.equals(strGroup)) {
                resConfig.setMnc(i);
            } else {
                if (!"v".equals(strGroup)) {
                    return false;
                }
                resConfig.setSdkVersion(i);
            }
            return true;
        }

        private void parseRegion() {
            if (this.mLanguageRegionParsed || isEmpty()) {
                return;
            }
            String[] strArr = this.mQualifiers;
            for (int i = 0; i < strArr.length; i++) {
                if (parseRegion(strArr[i])) {
                    strArr[i] = null;
                    return;
                }
            }
        }

        private void parseUnknownBytes() {
            if (isEmpty()) {
                return;
            }
            String[] strArr = this.mQualifiers;
            for (int i = 0; i < strArr.length; i++) {
                if (parseUnknownBytes(strArr[i])) {
                    strArr[i] = null;
                    return;
                }
            }
        }

        private boolean parseWidthHeight(String str) {
            if (str == null) {
                return false;
            }
            Matcher matcher = PATTERN_WIDTH_HEIGHT.matcher(str);
            if (!matcher.find()) {
                return false;
            }
            int i = Integer.parseInt(matcher.group(1));
            int i2 = Integer.parseInt(matcher.group(2));
            ResConfig resConfig = this.mConfig;
            resConfig.setScreenWidth(i);
            resConfig.setScreenHeight(i2);
            return true;
        }

        private static String[] splitQualifiers(String str) {
            if (str == null || str.length() == 0) {
                return null;
            }
            return str.split("-");
        }

        private static String trimPlus(String str) {
            if (str == null || str.length() == 0) {
                return null;
            }
            return str.charAt(0) == '+' ? str.substring(1) : str;
        }

        public String[] getErrors() {
            if (!this.mParseComplete) {
                return null;
            }
            String[] strArr = this.mQualifiers;
            if (isEmpty(strArr)) {
                return null;
            }
            int length = strArr.length;
            String[] strArr2 = new String[length];
            int i = 0;
            for (String str : strArr) {
                if (str != null && str.length() != 0) {
                    strArr2[i] = str;
                    i++;
                }
            }
            if (i == 0) {
                return null;
            }
            if (i == length) {
                return strArr2;
            }
            String[] strArr3 = new String[i];
            System.arraycopy(strArr2, 0, strArr3, 0, i);
            return strArr3;
        }

        public void parse() {
            if (this.mParseComplete) {
                return;
            }
            if (isEmpty()) {
                onParseComplete();
                return;
            }
            ResConfig resConfig = this.mConfig;
            resConfig.setConfigSize(64);
            parsePrefixedNumber();
            parseDp();
            parseWidthHeight();
            parseLocaleNumberingSystem();
            parseUnknownBytes();
            if (isEmpty()) {
                onParseComplete();
                return;
            }
            String[] strArr = this.mQualifiers;
            resConfig.setOrientation(Orientation.fromQualifiers(strArr));
            resConfig.setTouchscreen(Touchscreen.fromQualifiers(strArr));
            resConfig.setDensity(Density.fromQualifiers(strArr));
            resConfig.setKeyboard(Keyboard.fromQualifiers(strArr));
            resConfig.setNavigation(Navigation.fromQualifiers(strArr));
            if (isEmpty()) {
                onParseComplete();
                return;
            }
            resConfig.setInputFlagsKeysHidden(InputFlagsKeysHidden.fromQualifiers(strArr));
            resConfig.setInputFlagsNavHidden(InputFlagsNavHidden.fromQualifiers(strArr));
            resConfig.setGender(Gender.fromQualifiers(strArr));
            resConfig.setScreenLayoutSize(ScreenLayoutSize.fromQualifiers(strArr));
            resConfig.setScreenLayoutLong(ScreenLayoutLong.fromQualifiers(strArr));
            resConfig.setScreenLayoutDir(ScreenLayoutDir.fromQualifiers(strArr));
            if (isEmpty()) {
                onParseComplete();
                return;
            }
            resConfig.setUiModeType(UiModeType.fromQualifiers(strArr));
            resConfig.setUiModeNight(UiModeNight.fromQualifiers(strArr));
            resConfig.setScreenLayoutRound(ScreenLayoutRound.fromQualifiers(strArr));
            resConfig.setColorModeWide(ColorModeWide.fromQualifiers(strArr));
            resConfig.setColorModeHdr(ColorModeHdr.fromQualifiers(strArr));
            if (isEmpty()) {
                onParseComplete();
                return;
            }
            parseLocaleScriptVariant();
            parseLanguage();
            parseRegion();
            onParseComplete();
        }

        public void parseLocale() {
            if (this.mParseComplete) {
                return;
            }
            String str = null;
            if (isEmpty()) {
                ResConfig resConfig = this.mConfig;
                resConfig.setLanguage((String) null);
                resConfig.setRegion((String) null);
                resConfig.setLocaleScript((String) null);
                this.mParseComplete = true;
                return;
            }
            parseLanguage();
            parseLocaleRegion();
            String[] strArr = this.mQualifiers;
            if (strArr != null) {
                for (int i = 0; i < strArr.length; i++) {
                    String str2 = strArr[i];
                    if (str2 != null && str2.length() >= 2) {
                        strArr[i] = null;
                        str = str2;
                        break;
                    }
                }
            }
            this.mConfig.setLocaleScript(str);
            this.mParseComplete = true;
        }

        public QualifierParser(ResConfig resConfig, String str) {
            this(resConfig, splitQualifiers(str));
        }

        private boolean isEmpty() {
            if (!this.mEmpty) {
                this.mEmpty = isEmpty(this.mQualifiers);
            }
            return this.mEmpty;
        }

        private boolean parseLocaleNumberingSystem(String str) {
            return str != null && PATTERN_LOCALE_NUMBERING_SYSTEM.matcher(str).find();
        }

        private boolean parseUnknownBytes(String str) {
            if (str == null || !str.startsWith("unknown_bytes")) {
                return false;
            }
            this.mConfig.setUnknownBytes(str.substring(13));
            return true;
        }

        private boolean parseLanguage(String str) {
            if (!isLanguage(str)) {
                return false;
            }
            this.mConfig.setLanguage(str);
            return true;
        }

        private boolean parseLocaleRegion(String str) {
            if (!isLocaleRegion(str)) {
                return false;
            }
            this.mConfig.setRegion(str);
            return true;
        }

        private boolean parseRegion(String str) {
            if (!isRegion(str)) {
                return false;
            }
            this.mConfig.setRegion(str);
            return true;
        }

        private void parseWidthHeight() {
            if (isEmpty()) {
                return;
            }
            String[] strArr = this.mQualifiers;
            for (int i = 0; i < strArr.length; i++) {
                if (parseWidthHeight(strArr[i])) {
                    strArr[i] = null;
                    return;
                }
            }
        }

        private void parsePrefixedNumber() {
            if (isEmpty()) {
                return;
            }
            String[] strArr = this.mQualifiers;
            for (int i = 0; i < strArr.length; i++) {
                if (parsePrefixedNumber(strArr[i])) {
                    strArr[i] = null;
                }
            }
        }

        private void parseDp() {
            if (isEmpty()) {
                return;
            }
            String[] strArr = this.mQualifiers;
            for (int i = 0; i < strArr.length; i++) {
                if (parseDp(strArr[i])) {
                    strArr[i] = null;
                }
            }
        }

        private void parseLocaleScriptVariant() {
            if (this.mLanguageRegionParsed || isEmpty()) {
                return;
            }
            String[] strArr = this.mQualifiers;
            for (int i = 0; i < strArr.length; i++) {
                if (parseLocaleScriptVariant(strArr[i])) {
                    strArr[i] = null;
                    this.mLanguageRegionParsed = true;
                    return;
                }
            }
        }
    }

    public void setLocaleScript(char[] cArr) {
        setLocaleScript(ResConfigBase.toByteArray(cArr, 4));
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setLocaleScript(byte[] bArr) {
        super.setLocaleScript(bArr);
    }

    public static final class Density extends Flag {
        public static final Density ANYDPI;
        public static final Density HDPI;
        public static final Density LDPI;
        public static final int MASK = 65535;
        public static final Density MDPI;
        public static final Density NODPI;
        public static final Density TVDPI;
        public static final Density[] VALUES;
        public static final Density XHDPI;
        public static final Density XXHDPI;
        public static final Density XXXHDPI;

        static {
            Density density = new Density("ldpi", 120);
            LDPI = density;
            Density density2 = new Density("mdpi", 160);
            MDPI = density2;
            Density density3 = new Density("tvdpi", 213);
            TVDPI = density3;
            Density density4 = new Density("hdpi", 240);
            HDPI = density4;
            Density density5 = new Density("xhdpi", 320);
            XHDPI = density5;
            Density density6 = new Density("xxhdpi", 480);
            XXHDPI = density6;
            Density density7 = new Density("xxxhdpi", WinError.ERROR_MULTIPLE_FAULT_VIOLATION);
            XXXHDPI = density7;
            Density density8 = new Density("anydpi", 65534);
            ANYDPI = density8;
            Density density9 = new Density("nodpi", 65535);
            NODPI = density9;
            VALUES = new Density[]{density, density2, density3, density4, density5, density6, density7, density8, density9};
        }

        private Density(String str, int i) {
            super(str, i);
        }

        public static Density fromQualifiers(String[] strArr) {
            if (strArr == null) {
                return null;
            }
            for (int i = 0; i < strArr.length; i++) {
                Density densityValueOf = valueOf(strArr[i]);
                if (densityValueOf != null) {
                    strArr[i] = null;
                    return densityValueOf;
                }
            }
            return null;
        }

        public static int update(Density density, int i) {
            return Flag.update(65535, density, i);
        }

        public static Density valueOf(String str) {
            if (str == null || str.length() < 4) {
                return null;
            }
            String lowerCase = str.toLowerCase();
            if (lowerCase.charAt(0) == '-') {
                lowerCase = lowerCase.substring(1);
            }
            Density density = (Density) Flag.valueOf(VALUES, lowerCase);
            if (density == null && lowerCase.endsWith("dpi")) {
                try {
                    int i = Integer.parseInt(lowerCase.substring(0, lowerCase.length() - 3));
                    return new Density(i + "dpi", i);
                } catch (NumberFormatException unused) {
                }
            }
            return density;
        }

        public static Density fromQualifiers(String str) {
            return fromQualifiers(str.split("\\s*-\\s*"));
        }

        public static Density valueOf(int i) {
            if (i == 0) {
                return null;
            }
            Density density = (Density) Flag.valueOf(VALUES, 65535, i);
            if (density != null) {
                return density;
            }
            int i2 = i & 65535;
            return new Density(i2 + "dpi", i2);
        }
    }

    public void setRegion(char[] cArr) {
        setRegionBytes(packRegion(cArr));
    }

    public String getUnknownHexBytes() {
        return getUnknownHexBytes(8);
    }

    @Override // com.reandroid.arsc.value.ResConfigBase
    public /* bridge */ /* synthetic */ void setUnknownBytes(byte[] bArr) {
        super.setUnknownBytes(bArr);
    }
}

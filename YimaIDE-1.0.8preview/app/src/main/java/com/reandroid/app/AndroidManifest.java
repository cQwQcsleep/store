package com.reandroid.app;

import android.R;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.value.ValueItem;
import com.reandroid.utils.ObjectsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface AndroidManifest {
    public static final int ID_authorities = ObjectsUtil.of(R.attr.authorities);
    public static final int ID_compileSdkVersionCodename = ObjectsUtil.of(R.attr.compileSdkVersionCodename);
    public static final int ID_compileSdkVersion = ObjectsUtil.of(R.attr.compileSdkVersion);
    public static final int ID_configChanges = ObjectsUtil.of(R.attr.configChanges);
    public static final int ID_debuggable = ObjectsUtil.of(R.attr.debuggable);
    public static final int ID_exported = ObjectsUtil.of(R.attr.exported);
    public static final int ID_extractNativeLibs = ObjectsUtil.of(R.attr.extractNativeLibs);
    public static final int ID_host = ObjectsUtil.of(R.attr.host);
    public static final int ID_icon = ObjectsUtil.of(R.attr.icon);
    public static final int ID_id = ObjectsUtil.of(R.attr.id);
    public static final int ID_isFeatureSplit = ObjectsUtil.of(R.attr.isFeatureSplit);
    public static final int ID_isSplitRequired = ObjectsUtil.of(R.attr.isSplitRequired);
    public static final int ID_label = ObjectsUtil.of(R.attr.label);
    public static final int ID_maxSdkVersion = ObjectsUtil.of(R.attr.maxSdkVersion);
    public static final int ID_minSdkVersion = ObjectsUtil.of(R.attr.minSdkVersion);
    public static final int ID_name = ObjectsUtil.of(R.attr.name);
    public static final int ID_requiredSplitTypes = ObjectsUtil.of(R.attr.requiredSplitTypes);
    public static final int ID_resource = ObjectsUtil.of(R.attr.resource);
    public static final int ID_roundIcon = ObjectsUtil.of(R.attr.roundIcon);
    public static final int ID_screenOrientation = ObjectsUtil.of(R.attr.screenOrientation);
    public static final int ID_splitTypes = ObjectsUtil.of(R.attr.splitTypes);
    public static final int ID_targetActivity = ObjectsUtil.of(R.attr.targetActivity);
    public static final int ID_targetSdkVersion = ObjectsUtil.of(R.attr.targetSdkVersion);
    public static final int ID_theme = ObjectsUtil.of(R.attr.theme);
    public static final int ID_value = ObjectsUtil.of(R.attr.value);
    public static final int ID_versionCode = ObjectsUtil.of(R.attr.versionCode);
    public static final int ID_versionName = ObjectsUtil.of(R.attr.versionName);
    public static final String NAME_authorities = ObjectsUtil.of("authorities");
    public static final String NAME_compileSdkVersionCodename = ObjectsUtil.of("compileSdkVersionCodename");
    public static final String NAME_compileSdkVersion = ObjectsUtil.of("compileSdkVersion");
    public static final String NAME_configChanges = ObjectsUtil.of("configChanges");
    public static final String NAME_coreApp = ObjectsUtil.of("coreApp");
    public static final String NAME_debuggable = ObjectsUtil.of("debuggable");
    public static final String NAME_exported = ObjectsUtil.of("exported");
    public static final String NAME_extractNativeLibs = ObjectsUtil.of("extractNativeLibs");
    public static final String NAME_host = ObjectsUtil.of("host");
    public static final String NAME_icon = ObjectsUtil.of("icon");
    public static final String NAME_id = ObjectsUtil.of(TypeBlock.NAME_id);
    public static final String NAME_installLocation = ObjectsUtil.of("installLocation");
    public static final String NAME_isFeatureSplit = ObjectsUtil.of("isFeatureSplit");
    public static final String NAME_isSplitRequired = ObjectsUtil.of("isSplitRequired");
    public static final String NAME_label = ObjectsUtil.of("label");
    public static final String NAME_maxSdkVersion = ObjectsUtil.of("maxSdkVersion");
    public static final String NAME_minSdkVersion = ObjectsUtil.of("minSdkVersion");
    public static final String NAME_name = ObjectsUtil.of(TypeBlock.NAME_name);
    public static final String NAME_PACKAGE = ObjectsUtil.of("package");
    public static final String NAME_platformBuildVersionCode = ObjectsUtil.of("platformBuildVersionCode");
    public static final String NAME_platformBuildVersionName = ObjectsUtil.of("platformBuildVersionName");
    public static final String NAME_requiredSplitTypes = ObjectsUtil.of("requiredSplitTypes");
    public static final String NAME_resource = ObjectsUtil.of("resource");
    public static final String NAME_roundIcon = ObjectsUtil.of("roundIcon");
    public static final String NAME_screenOrientation = ObjectsUtil.of("screenOrientation");
    public static final String NAME_split = ObjectsUtil.of("split");
    public static final String NAME_splitTypes = ObjectsUtil.of("splitTypes");
    public static final String NAME_targetActivity = ObjectsUtil.of("targetActivity");
    public static final String NAME_targetSdkVersion = ObjectsUtil.of("targetSdkVersion");
    public static final String NAME_theme = ObjectsUtil.of("theme");
    public static final String NAME_value = ObjectsUtil.of("value");
    public static final String NAME_versionCode = ObjectsUtil.of("versionCode");
    public static final String NAME_versionName = ObjectsUtil.of("versionName");
    public static final String TAG_action = ObjectsUtil.of("action");
    public static final String TAG_activity_alias = ObjectsUtil.of("activity-alias");
    public static final String TAG_activity = ObjectsUtil.of("activity");
    public static final String TAG_application = ObjectsUtil.of("application");
    public static final String TAG_category = ObjectsUtil.of("category");
    public static final String TAG_data = ObjectsUtil.of(ValueItem.NAME_data);
    public static final String TAG_intent_filter = ObjectsUtil.of("intent-filter");
    public static final String TAG_manifest = ObjectsUtil.of("manifest");
    public static final String TAG_meta_data = ObjectsUtil.of("meta-data");
    public static final String TAG_package = ObjectsUtil.of("package");
    public static final String TAG_permission = ObjectsUtil.of("permission");
    public static final String TAG_provider = ObjectsUtil.of("provider");
    public static final String TAG_receiver = ObjectsUtil.of("receiver");
    public static final String TAG_service = ObjectsUtil.of("service");
    public static final String TAG_uses_feature = ObjectsUtil.of("uses-feature");
    public static final String TAG_uses_library = ObjectsUtil.of("uses-library");
    public static final String TAG_uses_permission = ObjectsUtil.of("uses-permission");
    public static final String TAG_uses_sdk = ObjectsUtil.of("uses-sdk");
    public static final String VALUE_android_intent_action_MAIN = ObjectsUtil.of("android.intent.action.MAIN");
    public static final String VALUE_com_android_dynamic_apk_fused_modules = ObjectsUtil.of("com.android.dynamic.apk.fused.modules");
    public static final String FILE_NAME = ObjectsUtil.of("AndroidManifest.xml");
    public static final String FILE_NAME_BIN = ObjectsUtil.of("AndroidManifest.xml.bin");
    public static final String FILE_NAME_JSON = ObjectsUtil.of("AndroidManifest.xml.json");
    public static final String EMPTY_MANIFEST_TAG = ObjectsUtil.of("x");

    String getApplicationClassName();

    default AndroidApiLevel getCompileSdk() {
        Integer compileSdkVersion = getCompileSdkVersion();
        if (compileSdkVersion != null) {
            return AndroidApiLevel.forApi(compileSdkVersion.intValue());
        }
        return null;
    }

    Integer getCompileSdkVersion();

    String getCompileSdkVersionCodename();

    String getMainActivityClassName();

    Integer getMinSdkVersion();

    String getPackageName();

    default AndroidApiLevel getPlatformBuild() {
        Integer platformBuildVersionCode = getPlatformBuildVersionCode();
        if (platformBuildVersionCode != null) {
            return AndroidApiLevel.forApi(platformBuildVersionCode.intValue());
        }
        return null;
    }

    Integer getPlatformBuildVersionCode();

    Object getPlatformBuildVersionName();

    Integer getTargetSdkVersion();

    Integer getVersionCode();

    String getVersionName();

    void setApplicationClassName(String str);

    default void setCompileSdk(AndroidApiLevel androidApiLevel) {
        setCompileSdkVersion(androidApiLevel.getApi());
        setCompileSdkVersionCodename(androidApiLevel.getVersion());
    }

    void setCompileSdkVersion(int i);

    void setCompileSdkVersionCodename(String str);

    void setMainActivityClassName(String str);

    void setMinSdkVersion(int i);

    void setPackageName(String str);

    default void setPlatformBuild(AndroidApiLevel androidApiLevel) {
        setPlatformBuildVersionCode(androidApiLevel.getApi());
        setPlatformBuildVersionName(androidApiLevel.getVersion());
    }

    void setPlatformBuildVersionCode(int i);

    void setPlatformBuildVersionName(Object obj);

    void setTargetSdkVersion(int i);

    void setVersionCode(int i);

    void setVersionName(String str);
}

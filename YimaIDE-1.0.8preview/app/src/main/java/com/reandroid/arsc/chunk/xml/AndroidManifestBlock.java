package com.reandroid.arsc.chunk.xml;

import com.reandroid.app.AndroidManifest;
import com.reandroid.arsc.ApkFile$ApkType;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.xml.AndroidManifestBlock;
import com.reandroid.arsc.chunk.xml.ResXmlElement;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.ComputeIterator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class AndroidManifestBlock extends ResXmlDocument implements AndroidManifest {
    public static final Predicate<ResXmlElement> PREDICATE_FUSED_MODULES = new Predicate() { // from class: e60
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return AndroidManifestBlock.p((ResXmlElement) obj);
        }
    };
    private int mGuessedPackageId;

    public AndroidManifestBlock() {
        super.getStringPool().setUtf8(false);
    }

    public static AndroidManifestBlock empty() {
        AndroidManifestBlock androidManifestBlock = new AndroidManifestBlock();
        androidManifestBlock.getOrCreateElement(AndroidManifest.EMPTY_MANIFEST_TAG);
        return androidManifestBlock;
    }

    public static String getAndroidNameValue(ResXmlElement resXmlElement) {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId = resXmlElement.searchAttributeByResourceId(AndroidManifest.ID_name);
        if (resXmlAttributeSearchAttributeByResourceId != null) {
            return resXmlAttributeSearchAttributeByResourceId.getValueAsString();
        }
        return null;
    }

    private Integer getManifestAttributeInt(int i) {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null || (resXmlAttributeSearchAttributeByResourceId = manifestElement.searchAttributeByResourceId(i)) == null || resXmlAttributeSearchAttributeByResourceId.getValueType() != ValueType.DEC) {
            return null;
        }
        return Integer.valueOf(resXmlAttributeSearchAttributeByResourceId.getData());
    }

    private String getManifestAttributeString(int i) {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null || (resXmlAttributeSearchAttributeByResourceId = manifestElement.searchAttributeByResourceId(i)) == null || resXmlAttributeSearchAttributeByResourceId.getValueType() != ValueType.STRING) {
            return null;
        }
        return resXmlAttributeSearchAttributeByResourceId.getValueAsString();
    }

    private ResXmlElement getOrCreateManifestElement() {
        return getOrCreateElement(AndroidManifest.TAG_manifest);
    }

    public static boolean isAndroidManifestBlock(ResXmlDocument resXmlDocument) {
        return (resXmlDocument == null || resXmlDocument.getElement(AndroidManifest.TAG_manifest) == null) ? false : true;
    }

    public static AndroidManifestBlock load(File file) throws IOException {
        AndroidManifestBlock androidManifestBlock = new AndroidManifestBlock();
        androidManifestBlock.readBytes(new BlockReader(file));
        return androidManifestBlock;
    }

    public static /* synthetic */ boolean o(String str, String str2, ResXmlElement resXmlElement) {
        return resXmlElement.equalsName(str) && str2.equals(getAndroidNameValue(resXmlElement));
    }

    public static /* synthetic */ boolean p(ResXmlElement resXmlElement) {
        return resXmlElement.equalsName(AndroidManifest.TAG_meta_data) && AndroidManifest.VALUE_com_android_dynamic_apk_fused_modules.equals(getAndroidNameValue(resXmlElement));
    }

    private void setManifestAttributeInt(String str, int i, int i2) {
        getOrCreateManifestElement().getOrCreateAndroidAttribute(str, i).setTypeAndData(ValueType.DEC, i2);
    }

    private void setManifestAttributeString(String str, int i, String str2) {
        getOrCreateManifestElement().getOrCreateAndroidAttribute(str, i).setValueAsString(str2);
    }

    public void addFusedModuleNames(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        ResXmlElement orCreateApplicationElement = getOrCreateApplicationElement();
        ResXmlElement resXmlElementNewElement = (ResXmlElement) CollectionUtil.getFirst(orCreateApplicationElement.getElements(PREDICATE_FUSED_MODULES));
        if (resXmlElementNewElement == null) {
            resXmlElementNewElement = orCreateApplicationElement.newElement(AndroidManifest.TAG_meta_data);
            resXmlElementNewElement.getOrCreateAndroidAttribute(AndroidManifest.NAME_name, AndroidManifest.ID_name).setValueAsString(AndroidManifest.VALUE_com_android_dynamic_apk_fused_modules);
        }
        ResXmlAttribute orCreateAndroidAttribute = resXmlElementNewElement.getOrCreateAndroidAttribute(AndroidManifest.NAME_value, AndroidManifest.ID_value);
        ArrayCollection arrayCollection = new ArrayCollection();
        String valueAsString = orCreateAndroidAttribute.getValueAsString();
        if (valueAsString != null) {
            arrayCollection.addAll(StringsUtil.split(valueAsString, ','));
        }
        for (String str : strArr) {
            if (!StringsUtil.isEmpty(str) && !arrayCollection.contains(str)) {
                arrayCollection.add(str);
            }
        }
        orCreateAndroidAttribute.setValueAsString(StringsUtil.join(arrayCollection, ','));
    }

    public ResXmlElement addUsesPermission(String str) {
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null) {
            return null;
        }
        ResXmlElement usesPermission = getUsesPermission(str);
        if (usesPermission != null) {
            return usesPermission;
        }
        String str2 = AndroidManifest.TAG_uses_permission;
        int iLastIndexOf = manifestElement.lastIndexOf(str2) + 1;
        ResXmlElement resXmlElementNewElement = manifestElement.newElement(str2);
        resXmlElementNewElement.getOrCreateAndroidAttribute(AndroidManifest.NAME_name, AndroidManifest.ID_name).setValueAsString(str);
        manifestElement.moveTo(resXmlElementNewElement, iLastIndexOf);
        return resXmlElementNewElement;
    }

    public boolean clearFusedModuleNames() {
        ResXmlElement applicationElement = getApplicationElement();
        if (applicationElement != null) {
            return applicationElement.removeElementsIf(PREDICATE_FUSED_MODULES);
        }
        return false;
    }

    public void ensureFullClassNames() {
        ResXmlElement applicationElement = getApplicationElement();
        if (applicationElement == null) {
            return;
        }
        Iterator itRecursiveAttributes = applicationElement.recursiveAttributes();
        while (itRecursiveAttributes.hasNext()) {
            ResXmlAttribute resXmlAttribute = (ResXmlAttribute) itRecursiveAttributes.next();
            if (resXmlAttribute.getNameId() == AndroidManifest.ID_name && resXmlAttribute.getValueType() == ValueType.STRING) {
                resXmlAttribute.setValueAsString(fullClassName(resXmlAttribute.getValueAsString()));
            }
        }
        applicationElement.refresh();
    }

    public String fullClassName(String str) {
        String packageName;
        return (str == null || str.length() == 0 || str.charAt(0) != '.' || (packageName = getPackageName()) == null) ? str : packageName.concat(str);
    }

    public Iterator<ResXmlElement> getActivities(boolean z) {
        String str = AndroidManifest.TAG_manifest;
        String str2 = AndroidManifest.TAG_application;
        Iterator<ResXmlElement> elementsWithChild = getElementsWithChild(new String[]{str, str2, AndroidManifest.TAG_activity});
        return !z ? elementsWithChild : CombiningIterator.two(elementsWithChild, getElementsWithChild(new String[]{str, str2, AndroidManifest.TAG_activity_alias}));
    }

    public ResXmlElement getActivity(String str, boolean z) {
        String strFullClassName = fullClassName(str);
        Iterator<ResXmlElement> activities = getActivities(true);
        while (activities.hasNext()) {
            ResXmlElement next = activities.next();
            if (ObjectsUtil.equals(strFullClassName, getAndroidNameValue(next))) {
                return next;
            }
        }
        return null;
    }

    public Iterator<ResXmlElement> getAndroidNameElements(final String str, final String str2) {
        return recursiveElements(new Predicate() { // from class: f60
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AndroidManifestBlock.o(str, str2, (ResXmlElement) obj);
            }
        });
    }

    @Override // com.reandroid.app.AndroidManifest
    public String getApplicationClassName() {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement applicationElement = getApplicationElement();
        if (applicationElement == null || (resXmlAttributeSearchAttributeByResourceId = applicationElement.searchAttributeByResourceId(AndroidManifest.ID_name)) == null) {
            return null;
        }
        return fullClassName(resXmlAttributeSearchAttributeByResourceId.getValueAsString());
    }

    public ResXmlElement getApplicationElement() {
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null) {
            return null;
        }
        return manifestElement.getElement(AndroidManifest.TAG_application);
    }

    public Iterator<ResXmlElement> getApplicationElementsByTag(String str) {
        return getElementsWithChild(new String[]{AndroidManifest.TAG_manifest, AndroidManifest.TAG_application, str});
    }

    public Integer getApplicationLabelReference() {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement applicationElement = getApplicationElement();
        if (applicationElement == null || (resXmlAttributeSearchAttributeByResourceId = applicationElement.searchAttributeByResourceId(AndroidManifest.ID_label)) == null || resXmlAttributeSearchAttributeByResourceId.getValueType() != ValueType.REFERENCE) {
            return null;
        }
        return Integer.valueOf(resXmlAttributeSearchAttributeByResourceId.getData());
    }

    public String getApplicationLabelString() {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement applicationElement = getApplicationElement();
        if (applicationElement == null || (resXmlAttributeSearchAttributeByResourceId = applicationElement.searchAttributeByResourceId(AndroidManifest.ID_label)) == null || resXmlAttributeSearchAttributeByResourceId.getValueType() != ValueType.STRING) {
            return null;
        }
        return resXmlAttributeSearchAttributeByResourceId.getValueAsString();
    }

    @Override // com.reandroid.app.AndroidManifest
    public Integer getCompileSdkVersion() {
        return getManifestAttributeInt(AndroidManifest.ID_compileSdkVersion);
    }

    @Override // com.reandroid.app.AndroidManifest
    public String getCompileSdkVersionCodename() {
        return getManifestAttributeString(AndroidManifest.ID_compileSdkVersionCodename);
    }

    public String[] getFusedModuleNames() {
        ResXmlElement resXmlElement;
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement applicationElement = getApplicationElement();
        if (applicationElement == null || (resXmlElement = (ResXmlElement) CollectionUtil.getFirst(applicationElement.getElements(PREDICATE_FUSED_MODULES))) == null || (resXmlAttributeSearchAttributeByResourceId = resXmlElement.searchAttributeByResourceId(AndroidManifest.ID_value)) == null || resXmlAttributeSearchAttributeByResourceId.getValueType() != ValueType.STRING) {
            return null;
        }
        return StringsUtil.split(resXmlAttributeSearchAttributeByResourceId.getValueAsString(), ',');
    }

    public int getIconResourceId() {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement applicationElement = getApplicationElement();
        if (applicationElement == null || (resXmlAttributeSearchAttributeByResourceId = applicationElement.searchAttributeByResourceId(AndroidManifest.ID_icon)) == null || resXmlAttributeSearchAttributeByResourceId.getValueType() != ValueType.REFERENCE) {
            return 0;
        }
        return resXmlAttributeSearchAttributeByResourceId.getData();
    }

    public ResXmlElement getMainActivity() {
        Iterator<ResXmlElement> activities = getActivities(true);
        while (activities.hasNext()) {
            ResXmlElement next = activities.next();
            Iterator elementsWithChild = next.getElementsWithChild(new String[]{AndroidManifest.TAG_intent_filter, AndroidManifest.TAG_action});
            while (elementsWithChild.hasNext()) {
                ResXmlAttribute resXmlAttributeSearchAttributeByResourceId = ((ResXmlElement) elementsWithChild.next()).searchAttributeByResourceId(AndroidManifest.ID_name);
                if (resXmlAttributeSearchAttributeByResourceId != null && AndroidManifest.VALUE_android_intent_action_MAIN.equals(resXmlAttributeSearchAttributeByResourceId.getValueAsString())) {
                    return next;
                }
            }
        }
        return null;
    }

    @Override // com.reandroid.app.AndroidManifest
    public String getMainActivityClassName() {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement mainActivity = getMainActivity();
        if (mainActivity == null || (resXmlAttributeSearchAttributeByResourceId = mainActivity.searchAttributeByResourceId(AndroidManifest.ID_name)) == null) {
            return null;
        }
        return fullClassName(resXmlAttributeSearchAttributeByResourceId.getValueAsString());
    }

    public ResXmlElement getManifestElement() {
        return getElement(AndroidManifest.TAG_manifest);
    }

    @Override // com.reandroid.app.AndroidManifest
    public Integer getMinSdkVersion() {
        ResXmlElement element;
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null || (element = manifestElement.getElement(AndroidManifest.TAG_uses_sdk)) == null || (resXmlAttributeSearchAttributeByResourceId = element.searchAttributeByResourceId(AndroidManifest.ID_minSdkVersion)) == null || resXmlAttributeSearchAttributeByResourceId.getValueType() != ValueType.DEC) {
            return null;
        }
        return Integer.valueOf(resXmlAttributeSearchAttributeByResourceId.getData());
    }

    public ResXmlElement getOrCreateActivity(String str, boolean z) {
        ResXmlElement activity = getActivity(str, z);
        if (activity != null) {
            return activity;
        }
        ResXmlElement resXmlElementNewElement = getOrCreateApplicationElement().newElement(z ? AndroidManifest.TAG_activity_alias : AndroidManifest.TAG_activity);
        resXmlElementNewElement.createAndroidAttribute(AndroidManifest.NAME_name, AndroidManifest.ID_name).setValueAsString(str);
        return resXmlElementNewElement;
    }

    public ResXmlElement getOrCreateApplicationElement() {
        ResXmlElement orCreateManifestElement = getOrCreateManifestElement();
        String str = AndroidManifest.TAG_application;
        ResXmlElement element = orCreateManifestElement.getElement(str);
        return element == null ? orCreateManifestElement.newElement(str) : element;
    }

    public ResXmlElement getOrCreateMainActivity(String str) {
        ResXmlElement mainActivity = getMainActivity();
        if (mainActivity == null) {
            mainActivity = getOrCreateApplicationElement().newElementAt(0, AndroidManifest.TAG_activity);
            ResXmlElement resXmlElementNewElement = mainActivity.newElement(AndroidManifest.TAG_intent_filter);
            ResXmlElement resXmlElementNewElement2 = resXmlElementNewElement.newElement(AndroidManifest.TAG_action);
            String str2 = AndroidManifest.NAME_name;
            int i = AndroidManifest.ID_name;
            resXmlElementNewElement2.getOrCreateAndroidAttribute(str2, i).setValueAsString(AndroidManifest.VALUE_android_intent_action_MAIN);
            String str3 = AndroidManifest.TAG_category;
            resXmlElementNewElement.newElement(str3).getOrCreateAndroidAttribute(str2, i).setValueAsString("android.intent.category.DEFAULT");
            resXmlElementNewElement.newElement(str3).getOrCreateAndroidAttribute(str2, i).setValueAsString("android.intent.category.LAUNCHER");
        }
        mainActivity.getOrCreateAndroidAttribute(AndroidManifest.NAME_name, AndroidManifest.ID_name).setValueAsString(str);
        return mainActivity;
    }

    @Override // com.reandroid.app.AndroidManifest
    public String getPackageName() {
        ResXmlAttribute resXmlAttributeSearchAttributeByName;
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null || (resXmlAttributeSearchAttributeByName = manifestElement.searchAttributeByName(AndroidManifest.NAME_PACKAGE)) == null || resXmlAttributeSearchAttributeByName.getValueType() != ValueType.STRING) {
            return null;
        }
        return resXmlAttributeSearchAttributeByName.getValueAsString();
    }

    @Override // com.reandroid.app.AndroidManifest
    public Integer getPlatformBuildVersionCode() {
        ResXmlAttribute resXmlAttributeSearchAttributeByName;
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null || (resXmlAttributeSearchAttributeByName = manifestElement.searchAttributeByName(AndroidManifest.NAME_platformBuildVersionCode)) == null || resXmlAttributeSearchAttributeByName.getValueType() != ValueType.DEC) {
            return null;
        }
        return Integer.valueOf(resXmlAttributeSearchAttributeByName.getData());
    }

    @Override // com.reandroid.app.AndroidManifest
    public Object getPlatformBuildVersionName() {
        ResXmlAttribute resXmlAttributeSearchAttributeByName;
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null || (resXmlAttributeSearchAttributeByName = manifestElement.searchAttributeByName(AndroidManifest.NAME_platformBuildVersionName)) == null) {
            return null;
        }
        return resXmlAttributeSearchAttributeByName.getValueType() == ValueType.STRING ? resXmlAttributeSearchAttributeByName.getValueAsString() : Integer.valueOf(resXmlAttributeSearchAttributeByName.getData());
    }

    public int getRoundIconResourceId() {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement applicationElement = getApplicationElement();
        if (applicationElement == null || (resXmlAttributeSearchAttributeByResourceId = applicationElement.searchAttributeByResourceId(AndroidManifest.ID_roundIcon)) == null || resXmlAttributeSearchAttributeByResourceId.getValueType() != ValueType.REFERENCE) {
            return 0;
        }
        return resXmlAttributeSearchAttributeByResourceId.getData();
    }

    public String getSplit() {
        ResXmlAttribute resXmlAttributeSearchAttributeByName;
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null || (resXmlAttributeSearchAttributeByName = manifestElement.searchAttributeByName(AndroidManifest.NAME_split)) == null) {
            return null;
        }
        return resXmlAttributeSearchAttributeByName.getValueAsString();
    }

    @Override // com.reandroid.app.AndroidManifest
    public Integer getTargetSdkVersion() {
        ResXmlElement element;
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null || (element = manifestElement.getElement(AndroidManifest.TAG_uses_sdk)) == null || (resXmlAttributeSearchAttributeByResourceId = element.searchAttributeByResourceId(AndroidManifest.ID_targetSdkVersion)) == null || resXmlAttributeSearchAttributeByResourceId.getValueType() != ValueType.DEC) {
            return null;
        }
        return Integer.valueOf(resXmlAttributeSearchAttributeByResourceId.getData());
    }

    public ResXmlElement getUsesPermission(String str) {
        Iterator elementsWithChild = getElementsWithChild(new String[]{AndroidManifest.TAG_manifest, AndroidManifest.TAG_uses_permission});
        while (elementsWithChild.hasNext()) {
            ResXmlElement resXmlElement = (ResXmlElement) elementsWithChild.next();
            if (ObjectsUtil.equals(str, getAndroidNameValue(resXmlElement))) {
                return resXmlElement;
            }
        }
        return null;
    }

    public List<String> getUsesPermissions() {
        return CollectionUtil.toList(ComputeIterator.of(getElementsWithChild(new String[]{AndroidManifest.TAG_manifest, AndroidManifest.TAG_uses_permission}), new Function() { // from class: d60
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AndroidManifestBlock.getAndroidNameValue((ResXmlElement) obj);
            }
        }));
    }

    @Override // com.reandroid.app.AndroidManifest
    public Integer getVersionCode() {
        return getManifestAttributeInt(AndroidManifest.ID_versionCode);
    }

    @Override // com.reandroid.app.AndroidManifest
    public String getVersionName() {
        return getManifestAttributeString(AndroidManifest.ID_versionName);
    }

    public ApkFile$ApkType guessApkType() {
        if (isSplit()) {
            return ApkFile$ApkType.SPLIT;
        }
        Boolean boolIsCoreApp = isCoreApp();
        if (boolIsCoreApp != null && boolIsCoreApp.booleanValue()) {
            return ApkFile$ApkType.CORE;
        }
        if (getMainActivity() != null) {
            return ApkFile$ApkType.BASE;
        }
        return null;
    }

    public int guessCurrentPackageId() {
        if (this.mGuessedPackageId == 0) {
            this.mGuessedPackageId = (getIconResourceId() >> 24) & 255;
        }
        return this.mGuessedPackageId;
    }

    public Boolean isCoreApp() {
        ResXmlAttribute resXmlAttributeSearchAttributeByName;
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null || (resXmlAttributeSearchAttributeByName = manifestElement.searchAttributeByName(AndroidManifest.NAME_coreApp)) == null || resXmlAttributeSearchAttributeByName.getValueType() != ValueType.BOOLEAN) {
            return null;
        }
        return Boolean.valueOf(resXmlAttributeSearchAttributeByName.getValueAsBoolean());
    }

    public boolean isDebuggable() {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement applicationElement = getApplicationElement();
        if (applicationElement == null || (resXmlAttributeSearchAttributeByResourceId = applicationElement.searchAttributeByResourceId(AndroidManifest.ID_debuggable)) == null) {
            return false;
        }
        return resXmlAttributeSearchAttributeByResourceId.getValueAsBoolean();
    }

    public Boolean isExtractNativeLibs() {
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId;
        ResXmlElement applicationElement = getApplicationElement();
        if (applicationElement == null || (resXmlAttributeSearchAttributeByResourceId = applicationElement.searchAttributeByResourceId(AndroidManifest.ID_extractNativeLibs)) == null || resXmlAttributeSearchAttributeByResourceId.getValueType() != ValueType.BOOLEAN) {
            return null;
        }
        return Boolean.valueOf(resXmlAttributeSearchAttributeByResourceId.getValueAsBoolean());
    }

    public boolean isFusingInclude() {
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null) {
            return false;
        }
        Iterator elements = manifestElement.getElements("module");
        while (elements.hasNext()) {
            Iterator elements2 = ((ResXmlElement) elements.next()).getElements("fusing");
            while (elements2.hasNext()) {
                ResXmlAttribute resXmlAttributeSearchAttributeByName = ((ResXmlElement) elements2.next()).searchAttributeByName("include");
                if (resXmlAttributeSearchAttributeByName != null && resXmlAttributeSearchAttributeByName.getValueType() == ValueType.BOOLEAN) {
                    return resXmlAttributeSearchAttributeByName.getValueAsBoolean();
                }
            }
        }
        return false;
    }

    public boolean isSplit() {
        ResXmlElement manifestElement = getManifestElement();
        return (manifestElement == null || manifestElement.searchAttributeByName(AndroidManifest.NAME_split) == null) ? false : true;
    }

    @Deprecated
    public List<ResXmlElement> listActivities() {
        ArrayCollection arrayCollection = new ArrayCollection();
        arrayCollection.addAll(getActivities(true));
        return arrayCollection;
    }

    public List<ResXmlElement> listApplicationElementsByTag(String str) {
        return CollectionUtil.toList(getApplicationElementsByTag(str));
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocument
    public PackageBlock selectPackageBlock(TableBlock tableBlock) {
        ResourceEntry resource = tableBlock.getResource(getIconResourceId());
        if (resource == null) {
            return super.selectPackageBlock(tableBlock);
        }
        PackageBlock packageBlock = resource.getPackageBlock();
        return packageBlock.getTableBlock() != tableBlock ? super.selectPackageBlock(tableBlock) : packageBlock;
    }

    @Override // com.reandroid.app.AndroidManifest
    public void setApplicationClassName(String str) {
        getOrCreateApplicationElement().getOrCreateAndroidAttribute(AndroidManifest.NAME_name, AndroidManifest.ID_name).setValueAsString(str);
    }

    public void setApplicationLabel(int i) {
        getOrCreateApplicationElement().getOrCreateAndroidAttribute(AndroidManifest.NAME_label, AndroidManifest.ID_label).setTypeAndData(ValueType.REFERENCE, i);
    }

    @Override // com.reandroid.app.AndroidManifest
    public void setCompileSdkVersion(int i) {
        setManifestAttributeInt(AndroidManifest.NAME_compileSdkVersion, AndroidManifest.ID_compileSdkVersion, i);
    }

    @Override // com.reandroid.app.AndroidManifest
    public void setCompileSdkVersionCodename(String str) {
        setManifestAttributeString(AndroidManifest.NAME_compileSdkVersionCodename, AndroidManifest.ID_compileSdkVersionCodename, str);
    }

    public void setDebuggable(boolean z) {
        ResXmlElement applicationElement = getApplicationElement();
        if (applicationElement == null) {
            return;
        }
        int i = AndroidManifest.ID_debuggable;
        ResXmlAttribute resXmlAttributeSearchAttributeByResourceId = applicationElement.searchAttributeByResourceId(i);
        if (z) {
            if (resXmlAttributeSearchAttributeByResourceId == null) {
                resXmlAttributeSearchAttributeByResourceId = applicationElement.createAndroidAttribute(AndroidManifest.NAME_debuggable, i);
            }
            resXmlAttributeSearchAttributeByResourceId.setValueAsBoolean(true);
        } else if (resXmlAttributeSearchAttributeByResourceId != null) {
            applicationElement.removeAttribute(resXmlAttributeSearchAttributeByResourceId);
        }
    }

    public void setExtractNativeLibs(Boolean bool) {
        ResXmlElement applicationElement = bool == null ? getApplicationElement() : getOrCreateApplicationElement();
        if (applicationElement != null) {
            if (bool == null) {
                applicationElement.removeAttributesWithId(AndroidManifest.ID_extractNativeLibs);
            } else {
                applicationElement.getOrCreateAndroidAttribute(AndroidManifest.NAME_extractNativeLibs, AndroidManifest.ID_extractNativeLibs).setValueAsBoolean(bool.booleanValue());
            }
        }
    }

    public void setIconResourceId(int i) {
        getOrCreateApplicationElement().getOrCreateAndroidAttribute(AndroidManifest.NAME_icon, AndroidManifest.ID_icon).setTypeAndData(ValueType.REFERENCE, i);
    }

    @Override // com.reandroid.app.AndroidManifest
    public void setMainActivityClassName(String str) {
        getOrCreateMainActivity(str);
    }

    @Override // com.reandroid.app.AndroidManifest
    public void setMinSdkVersion(int i) {
        ResXmlElement orCreateManifestElement = getOrCreateManifestElement();
        String str = AndroidManifest.TAG_uses_sdk;
        ResXmlElement element = orCreateManifestElement.getElement(str);
        if (element == null) {
            element = orCreateManifestElement.newElement(str);
        }
        element.getOrCreateAndroidAttribute(AndroidManifest.NAME_minSdkVersion, AndroidManifest.ID_minSdkVersion).setTypeAndData(ValueType.DEC, i);
    }

    @Override // com.reandroid.app.AndroidManifest
    public void setPackageName(String str) {
        getOrCreateManifestElement().getOrCreateAttribute(AndroidManifest.NAME_PACKAGE, 0).setValueAsString(str);
    }

    @Override // com.reandroid.app.AndroidManifest
    public void setPlatformBuildVersionCode(int i) {
        setManifestAttributeInt(AndroidManifest.NAME_platformBuildVersionCode, 0, i);
    }

    @Override // com.reandroid.app.AndroidManifest
    public void setPlatformBuildVersionName(Object obj) {
        Integer numValueOf;
        if (obj instanceof Integer) {
            numValueOf = (Integer) obj;
        } else {
            try {
                numValueOf = Integer.valueOf(Integer.parseInt((String) obj));
            } catch (NumberFormatException unused) {
                numValueOf = null;
            }
        }
        if (numValueOf != null) {
            setManifestAttributeInt(AndroidManifest.NAME_platformBuildVersionName, 0, numValueOf.intValue());
        } else {
            setManifestAttributeString(AndroidManifest.NAME_platformBuildVersionName, 0, (String) obj);
        }
    }

    public void setRoundIconResourceId(int i) {
        getOrCreateApplicationElement().getOrCreateAndroidAttribute(AndroidManifest.NAME_icon, AndroidManifest.ID_roundIcon).setTypeAndData(ValueType.REFERENCE, i);
    }

    public void setSplit(String str, boolean z) {
        ResXmlAttribute resXmlAttributeSearchAttributeByName;
        ResXmlElement manifestElement = getManifestElement();
        if (manifestElement == null) {
            return;
        }
        if (z) {
            resXmlAttributeSearchAttributeByName = manifestElement.getOrCreateAttribute(AndroidManifest.NAME_split, 0);
        } else {
            resXmlAttributeSearchAttributeByName = manifestElement.searchAttributeByName(AndroidManifest.NAME_split);
            if (resXmlAttributeSearchAttributeByName == null) {
                return;
            }
        }
        resXmlAttributeSearchAttributeByName.setValueAsString(str);
    }

    @Override // com.reandroid.app.AndroidManifest
    public void setTargetSdkVersion(int i) {
        ResXmlElement orCreateManifestElement = getOrCreateManifestElement();
        String str = AndroidManifest.TAG_uses_sdk;
        ResXmlElement element = orCreateManifestElement.getElement(str);
        if (element == null) {
            element = orCreateManifestElement.newElement(str);
        }
        element.getOrCreateAndroidAttribute(AndroidManifest.NAME_targetSdkVersion, AndroidManifest.ID_targetSdkVersion).setTypeAndData(ValueType.DEC, i);
    }

    @Override // com.reandroid.app.AndroidManifest
    public void setVersionCode(int i) {
        setManifestAttributeInt(AndroidManifest.NAME_versionCode, AndroidManifest.ID_versionCode, i);
    }

    @Override // com.reandroid.app.AndroidManifest
    public void setVersionName(String str) {
        setManifestAttributeString(AndroidManifest.NAME_versionName, AndroidManifest.ID_versionName, str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree, com.reandroid.arsc.container.WrappedBlock
    public String toString() {
        touchChildNodesForDebug();
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(AndroidManifest.NAME_PACKAGE);
        sb.append("=");
        sb.append(getPackageName());
        sb.append(", ");
        sb.append(AndroidManifest.NAME_versionCode);
        sb.append("=");
        sb.append(getVersionCode());
        sb.append(", ");
        sb.append(AndroidManifest.NAME_versionName);
        sb.append("=");
        sb.append(getVersionName());
        sb.append(", ");
        sb.append(AndroidManifest.NAME_compileSdkVersion);
        sb.append("=");
        sb.append(getCompileSdkVersion());
        sb.append(", ");
        sb.append(AndroidManifest.NAME_compileSdkVersionCodename);
        sb.append("=");
        sb.append(getCompileSdkVersionCodename());
        List<String> usesPermissions = getUsesPermissions();
        sb.append(", PERMISSIONS[");
        boolean z = false;
        for (String str : usesPermissions) {
            if (z) {
                sb.append(", ");
            }
            sb.append(str);
            z = true;
        }
        sb.append("]}");
        return sb.toString();
    }

    public static AndroidManifestBlock load(InputStream inputStream) throws IOException {
        AndroidManifestBlock androidManifestBlock = new AndroidManifestBlock();
        androidManifestBlock.readBytes(inputStream);
        return androidManifestBlock;
    }

    @Deprecated
    public List<ResXmlElement> listActivities(boolean z) {
        ArrayCollection arrayCollection = new ArrayCollection();
        arrayCollection.addAll(getActivities(z));
        return arrayCollection;
    }

    public void setApplicationLabel(String str) {
        getOrCreateApplicationElement().getOrCreateAndroidAttribute(AndroidManifest.NAME_label, AndroidManifest.ID_label).setValueAsString(str);
    }
}

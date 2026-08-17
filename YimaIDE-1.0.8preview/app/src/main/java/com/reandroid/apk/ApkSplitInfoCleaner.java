package com.reandroid.apk;

import com.reandroid.apk.ApkSplitInfoCleaner;
import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.arsc.chunk.xml.AndroidManifestBlock;
import com.reandroid.arsc.chunk.xml.ResXmlAttribute;
import com.reandroid.arsc.chunk.xml.ResXmlElement;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResValue;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.FilterIterator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ApkSplitInfoCleaner {
    public static /* synthetic */ boolean a(ResXmlAttribute resXmlAttribute) {
        int nameId = resXmlAttribute.getNameId();
        if (nameId != 0) {
            return nameId == AndroidManifest.ID_isSplitRequired || nameId == AndroidManifest.ID_isFeatureSplit || nameId == AndroidManifest.ID_extractNativeLibs;
        }
        return resXmlAttribute.equalsName(AndroidManifest.NAME_requiredSplitTypes) || resXmlAttribute.equalsName(AndroidManifest.NAME_splitTypes);
    }

    private static void cleanActivities(ApkModule apkModule) {
        Iterator it = CollectionUtil.toList(FilterIterator.of(apkModule.getAndroidManifest().getManifestElement().recursiveElements(), new Predicate() { // from class: ob0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ApkSplitInfoCleaner.isSplitElement((ResXmlElement) obj);
            }
        })).iterator();
        while (it.hasNext()) {
            cleanElement(apkModule, (ResXmlElement) it.next());
        }
    }

    private static void cleanElement(ApkModule apkModule, int i) {
        if (i == 0) {
            return;
        }
        List<Entry> listResolveReference = apkModule.getTableBlock().resolveReference(i);
        ZipEntryMap zipEntryMap = apkModule.getZipEntryMap();
        for (Entry entry : listResolveReference) {
            ResValue resValue = entry.getResValue();
            if (resValue != null) {
                zipEntryMap.remove(resValue.getValueAsString());
                resValue.setValueAsBoolean(false);
            }
            entry.setNull(true);
            entry.getTypeBlock().getParentSpecTypePair().removeNullEntries(entry.getId());
        }
    }

    public static void cleanSplitInfo(ApkModule apkModule) {
        cleanSplitInfoAttributes(apkModule.getAndroidManifest().getManifestElement());
        cleanSplitInfoMeta(apkModule);
        cleanActivities(apkModule);
    }

    private static void cleanSplitInfoAttributes(ResXmlElement resXmlElement) {
        Iterator it = CollectionUtil.toList(FilterIterator.of(resXmlElement.recursiveAttributes(), new Predicate() { // from class: mb0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ApkSplitInfoCleaner.a((ResXmlAttribute) obj);
            }
        })).iterator();
        while (it.hasNext()) {
            ((ResXmlAttribute) it.next()).removeSelf();
        }
    }

    private static void cleanSplitInfoMeta(ApkModule apkModule) {
        Iterator it = CollectionUtil.toList(FilterIterator.of(apkModule.getAndroidManifest().getManifestElement().recursiveElements(), new Predicate() { // from class: nb0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ApkSplitInfoCleaner.isSplitMetaElement((ResXmlElement) obj);
            }
        })).iterator();
        while (it.hasNext()) {
            cleanElement(apkModule, (ResXmlElement) it.next());
        }
    }

    public static boolean isSplitElement(ResXmlElement resXmlElement) {
        if (resXmlElement != null) {
            return (resXmlElement.equalsName(AndroidManifest.TAG_activity) || resXmlElement.equalsName(AndroidManifest.TAG_service)) && isSplitElement(AndroidManifestBlock.getAndroidNameValue(resXmlElement));
        }
        return false;
    }

    public static boolean isSplitMetaElement(ResXmlElement resXmlElement) {
        return resXmlElement != null && resXmlElement.equalsName(AndroidManifest.TAG_meta_data) && isSplitMetaNamePrefix(AndroidManifestBlock.getAndroidNameValue(resXmlElement));
    }

    private static boolean isSplitMetaNamePrefix(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("com.android.vending.") || str.startsWith("com.android.stamp.") || str.startsWith("com.android.dynamic.apk.");
    }

    private static boolean isSplitElement(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("com.google.android.play.core.missingsplits.") || str.startsWith("com.google.android.play.core.assetpacks.");
    }

    private static void cleanElement(ApkModule apkModule, ResXmlElement resXmlElement) {
        if (resXmlElement.getAttributeCount() < 2) {
            resXmlElement.removeSelf();
            return;
        }
        Iterator<ResXmlAttribute> attributes = resXmlElement.getAttributes();
        while (attributes.hasNext()) {
            ResXmlAttribute next = attributes.next();
            if (next.getValueType() == ValueType.REFERENCE) {
                cleanElement(apkModule, next.getData());
            }
        }
        resXmlElement.removeSelf();
    }
}

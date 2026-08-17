package com.reandroid.graph;

import com.reandroid.apk.ApkModule;
import com.reandroid.apk.ResFile;
import com.reandroid.archive.InputSource;
import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import com.reandroid.arsc.chunk.xml.ResXmlElement;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.graph.VitalClassesSet;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayIterator;
import com.reandroid.utils.io.IOUtil;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class VitalClassesSet extends BaseApkModuleProcessor implements Predicate<TypeKey> {
    private final ApkBuildOption buildOption;
    private final Set<String> elementNameSuffix;
    private final Set<TypeKey> mainClasses;
    private boolean scanned;
    private final Set<TypeKey> sourceStringClasses;

    public VitalClassesSet(ApkBuildOption apkBuildOption, ApkModule apkModule, DexClassRepository dexClassRepository) {
        super(apkModule, dexClassRepository);
        this.buildOption = apkBuildOption;
        this.mainClasses = new HashSet();
        this.sourceStringClasses = new HashSet();
        this.elementNameSuffix = new HashSet();
    }

    public static /* synthetic */ boolean a(Set set, TypeKey typeKey) {
        return !set.contains(typeKey);
    }

    private void addType(TypeKey typeKey) {
        if (typeKey == null) {
            return;
        }
        TypeKey declaring = typeKey.getDeclaring();
        Set<TypeKey> set = this.mainClasses;
        if (set.contains(declaring) || !getClassRepository().containsClass(declaring)) {
            return;
        }
        set.add(declaring);
    }

    private ApkBuildOption getBuildOption() {
        return this.buildOption;
    }

    private boolean isValidSimpleName(char c) {
        if (c == '\t' || c == '\n' || c == '\r' || c == '/' || c == '|') {
            return false;
        }
        switch (c) {
            case ' ':
            case '!':
            case '\"':
            case '#':
                return false;
            default:
                switch (c) {
                    case '%':
                    case '&':
                    case '\'':
                    case '(':
                    case ')':
                    case '*':
                    case '+':
                    case ',':
                        return false;
                    default:
                        switch (c) {
                            case ':':
                            case ';':
                            case '<':
                            case '=':
                            case '>':
                            case '?':
                            case '@':
                                return false;
                            default:
                                switch (c) {
                                    case '[':
                                    case '\\':
                                    case ']':
                                    case '^':
                                        return false;
                                    default:
                                        return true;
                                }
                        }
                }
        }
    }

    private void loadElementNames(ResXmlDocument resXmlDocument) {
        if (resXmlDocument != null) {
            Set<String> set = this.elementNameSuffix;
            Iterator itRecursiveElements = resXmlDocument.recursiveElements();
            while (itRecursiveElements.hasNext()) {
                set.add(((ResXmlElement) itRecursiveElements.next()).getName(false));
            }
        }
    }

    private boolean maybeValidSourceType(String str) {
        int length;
        if (StringsUtil.isEmpty(str) || (length = str.length()) < 3 || str.indexOf(46) < 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!isValidSimpleName(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private void scanElementSuffix() {
        final Set<String> set = this.elementNameSuffix;
        Iterator<DexClass> dexClasses = getClassRepository().getDexClasses(new Predicate() { // from class: gdf
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return set.contains(((TypeKey) obj).getSimpleName());
            }
        });
        while (dexClasses.hasNext()) {
            addType(dexClasses.next().getKey());
        }
        set.clear();
    }

    private void scanImplSuffix() {
        keepClasses(new Predicate() { // from class: edf
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((TypeKey) obj).getTypeName().endsWith("_Impl;");
            }
        });
    }

    private void scanOnDexStrings() {
        if (getBuildOption().isProcessClassNamesOnStrings()) {
            debug("Searching on dex strings ...");
            Set<TypeKey> set = this.sourceStringClasses;
            DexClassRepository classRepository = getClassRepository();
            Iterator items = classRepository.getItems(SectionType.STRING_ID);
            while (items.hasNext()) {
                StringId stringId = (StringId) items.next();
                if (stringId.containsUsage(UsageMarker.USAGE_INSTRUCTION) || stringId.containsUsage(UsageMarker.USAGE_STATIC_VALUES)) {
                    String string = stringId.getString();
                    if (maybeValidSourceType(string)) {
                        TypeKey typeKey = TypeKey.parse(string);
                        if (classRepository.containsClass(typeKey)) {
                            set.add(typeKey);
                        }
                    }
                }
            }
        }
    }

    private void scanOnResourceStrings() {
        debug("Searching on resource strings ...");
        Iterator localResources = getApkModule().getTableBlock().getLocalResources("string");
        ResConfig resConfig = ResConfig.getDefault();
        while (localResources.hasNext()) {
            Entry entry = ((ResourceEntry) localResources.next()).get(resConfig);
            if (entry != null) {
                String valueAsString = entry.getValueAsString();
                if (maybeValidSourceType(valueAsString)) {
                    addType(TypeKey.parse(valueAsString));
                }
            }
        }
    }

    private void scanOnResourceXmlFiles() {
        List listListResFiles = getApkModule().listResFiles();
        debug("Searching required classes on res files: " + listListResFiles.size());
        Iterator it = listListResFiles.iterator();
        while (it.hasNext()) {
            scanOnXml(((ResFile) it.next()).getResXmlDocument());
        }
    }

    private void scanOnXml(ResXmlDocument resXmlDocument) {
        if (resXmlDocument == null) {
            return;
        }
        Iterator strings = resXmlDocument.getStringPool().getStrings();
        while (strings.hasNext()) {
            addType(TypeKey.parse((String) strings.next()));
        }
        loadElementNames(resXmlDocument);
    }

    private void scanOthers() {
        scanImplSuffix();
    }

    private void scanRequiredByUser() {
        keepClasses(getBuildOption().getKeepClasses());
    }

    private void scanUsedByMetaInfServices(InputSource inputSource) {
        addType(TypeKey.parse(inputSource.getSimpleName()));
        try {
            for (String str : StringsUtil.split(IOUtil.readUtf8(inputSource.openStream()), '\n', true)) {
                addType(TypeKey.parse(str.trim()));
            }
        } catch (IOException e) {
            warn("Failed to process '" + inputSource.getAlias() + "', error = " + e.getMessage());
        }
    }

    private void scanUsedByNative() {
        debug("Searching used by native ...");
        final Set<TypeKey> set = this.mainClasses;
        Iterator<DexClass> dexClasses = getClassRepository().getDexClasses(new Predicate() { // from class: fdf
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return VitalClassesSet.a(set, (TypeKey) obj);
            }
        });
        while (dexClasses.hasNext()) {
            DexClass next = dexClasses.next();
            if (next.usesNative()) {
                set.add(next.getKey());
            }
        }
    }

    @Override // com.reandroid.graph.GraphTask
    public void apply() {
        if (this.scanned) {
            return;
        }
        this.scanned = true;
        debug("Scanning ...");
        scanOnXml();
        scanUsedByNative();
        scanUsedByMetaInfServices();
        scanRequiredByUser();
        scanOthers();
        scanOnResourceStrings();
        scanOnDexStrings();
        verbose("Classes: " + this.mainClasses.size());
    }

    public boolean containsSourceString(TypeKey typeKey) {
        return this.sourceStringClasses.contains(typeKey);
    }

    public Iterator<TypeKey> getDexSourceStringClasses() {
        return this.sourceStringClasses.iterator();
    }

    public Iterator<TypeKey> getMainClasses() {
        return this.mainClasses.iterator();
    }

    public void keepClasses(Predicate<? super TypeKey> predicate) {
        if (predicate == null) {
            return;
        }
        Iterator<DexClass> dexClasses = getClassRepository().getDexClasses(predicate);
        while (dexClasses.hasNext()) {
            addType(dexClasses.next().getKey());
        }
    }

    public void updateSourceStrings() {
        Set<TypeKey> set = this.sourceStringClasses;
        if (set.isEmpty()) {
            return;
        }
        DexClassRepository classRepository = getClassRepository();
        Iterator itOf = ArrayIterator.of(set.toArray());
        SectionType<StringId> sectionType = SectionType.STRING_ID;
        while (itOf.hasNext()) {
            TypeKey typeKey = (TypeKey) itOf.next();
            if (!classRepository.contains(sectionType, new StringKey(typeKey.getSourceName()))) {
                set.remove(typeKey);
            }
        }
    }

    @Override // java.util.function.Predicate
    public boolean test(TypeKey typeKey) {
        return this.mainClasses.contains(typeKey);
    }

    private void scanOnXml() {
        debug("Scanning xml ...");
        scanOnXml(getApkModule().getAndroidManifest());
        scanOnResourceXmlFiles();
        scanElementSuffix();
    }

    private void scanUsedByMetaInfServices() {
        debug("Searching classes on META-INF/services/ ...");
        Iterator itWithinDirectory = getZipEntryMap().withinDirectory("META-INF/services/");
        while (itWithinDirectory.hasNext()) {
            scanUsedByMetaInfServices((InputSource) itWithinDirectory.next());
        }
    }
}

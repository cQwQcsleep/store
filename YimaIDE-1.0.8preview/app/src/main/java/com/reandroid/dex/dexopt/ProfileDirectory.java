package com.reandroid.dex.dexopt;

import com.reandroid.archive.ByteInputSource;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.dex.dexopt.ProfileDirectory;
import com.reandroid.dex.model.DexFile;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.Crc32;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.io.FileUtil;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileDirectory implements LinkableProfileItem, Closeable {
    private ProfileMetadataFile mMetadataFile;
    private ProfileDataFile mProfileFile;

    public static /* synthetic */ boolean c(File file, List list, String str) {
        return !list.contains(new File(file, str));
    }

    public static /* synthetic */ boolean d(String str) {
        return DexFile.getDexFileNumber(str) >= 0;
    }

    public static /* synthetic */ boolean e(File file, List list, String str) {
        if (str.endsWith(".dex")) {
            return !list.contains(new File(file, str.substring(0, str.length() - 4)));
        }
        return true;
    }

    public static /* synthetic */ boolean f(ZipEntryMap zipEntryMap, String str) {
        return !zipEntryMap.contains(str);
    }

    private boolean isUninitialized(String str) {
        DexProfileData profile = getProfile(str);
        ProfileMetadata metadata = getMetadata(str);
        if (profile == null && metadata == null) {
            return false;
        }
        if (profile != null && profile.isInitialized() && (metadata == null || metadata.isInitialized())) {
            return false;
        }
        if (metadata == null || !metadata.isInitialized()) {
            return true;
        }
        return (profile == null || profile.isInitialized()) ? false : true;
    }

    private void linkUninitialized(InputSource inputSource) throws IOException {
        String alias = inputSource.getAlias();
        if (isUninitialized(alias)) {
            DexProfileData profile = getProfile(alias);
            ProfileMetadata metadata = getMetadata(alias);
            DexFile dexFile = DexFile.read(inputSource.openStream(), SectionType.minimal());
            dexFile.setSimpleName(alias);
            if (profile != null && !profile.isInitialized()) {
                profile.update(dexFile);
            }
            if (metadata == null || metadata.isInitialized()) {
                return;
            }
            metadata.update(dexFile);
        }
    }

    private static List<InputSource> listDexFiles(ZipEntryMap zipEntryMap) {
        List<InputSource> list = CollectionUtil.toList(zipEntryMap.iteratorWithPath(new Predicate() { // from class: gab
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ProfileDirectory.d((String) obj);
            }
        }));
        list.sort(new Comparator() { // from class: hab
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return CompareUtil.compare(DexFile.getDexFileNumber(((InputSource) obj).getAlias()), DexFile.getDexFileNumber(((InputSource) obj2).getAlias()));
            }
        });
        return list;
    }

    private static List<File> listSmaliDirectories(File file) {
        File[] fileArrListFiles;
        ArrayCollection arrayCollection = new ArrayCollection();
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    if (DexFile.getDexFileNumber(file2.getName() + ".dex") >= 0) {
                        arrayCollection.add(file2);
                    }
                }
            }
        }
        return arrayCollection;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        setProfileFile(null);
        setMetadataFile(null);
    }

    public void decodeToJsonDir(File file) throws IOException {
        File file2 = new File(file, ProfileFile.JSON_NAME_PROF);
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            profileFile.toJson().write(file2);
        }
        File file3 = new File(file, ProfileFile.JSON_NAME_PROFM);
        ProfileMetadataFile metadataFile = getMetadataFile();
        if (metadataFile != null) {
            metadataFile.toJson().write(file3);
        }
    }

    public void encodeJsonDir(File file) throws IOException {
        File file2 = new File(file, ProfileFile.JSON_NAME_PROF);
        if (file2.isFile()) {
            ProfileDataFile profileFile = getProfileFile();
            if (profileFile == null) {
                setProfileFile(new ProfileDataFile());
                profileFile = getProfileFile();
            }
            profileFile.fromJson(new JSONObject(file2));
        }
        File file3 = new File(file, ProfileFile.JSON_NAME_PROFM);
        if (file3.isFile()) {
            ProfileMetadataFile metadataFile = getMetadataFile();
            if (metadataFile == null) {
                setMetadataFile(new ProfileMetadataFile());
                metadataFile = getMetadataFile();
            }
            metadataFile.fromJson(new JSONObject(file3));
        }
    }

    public void ensureDexFile(String str) {
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            profileFile.getOrCreate(str);
        }
        ProfileMetadataFile metadataFile = getMetadataFile();
        if (metadataFile != null) {
            metadataFile.getOrCreate(str);
        }
    }

    public ProfileMetadata getMetadata(String str) {
        ProfileMetadataFile metadataFile = getMetadataFile();
        if (metadataFile != null) {
            return metadataFile.get(str);
        }
        return null;
    }

    public ProfileMetadataFile getMetadataFile() {
        return this.mMetadataFile;
    }

    public DexProfileData getProfile(String str) {
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            return profileFile.get(str);
        }
        return null;
    }

    public ProfileDataFile getProfileFile() {
        return this.mProfileFile;
    }

    public boolean hasProfile() {
        return getProfileFile() != null;
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            profileFile.link(dexFile);
        }
        ProfileMetadataFile metadataFile = getMetadataFile();
        if (metadataFile != null) {
            metadataFile.link(dexFile);
        }
    }

    public void linkApk(ZipEntryMap zipEntryMap) throws IOException {
        Iterator<InputSource> it = listDexFiles(zipEntryMap).iterator();
        while (it.hasNext()) {
            linkDex(it.next());
        }
    }

    public void linkDex(InputSource inputSource) throws IOException {
        String alias = inputSource.getAlias();
        DexProfileData profile = getProfile(alias);
        ProfileMetadata metadata = getMetadata(alias);
        if (profile == null && metadata == null) {
            return;
        }
        DexFile dexFile = DexFile.read(inputSource.openStream(), SectionType.minimal());
        dexFile.setSimpleName(alias);
        if (profile != null) {
            profile.link(dexFile);
        }
        if (metadata != null) {
            metadata.link(dexFile);
        }
        dexFile.close();
    }

    public void readApk(ZipEntryMap zipEntryMap) throws IOException {
        InputSource inputSource;
        InputSource inputSource2;
        if (getProfileFile() == null && (inputSource2 = zipEntryMap.getInputSource(ProfileFile.PATH_PROF)) != null) {
            setProfileFile(ProfileDataFile.read(inputSource2.openStream()));
        }
        if (getMetadataFile() != null || (inputSource = zipEntryMap.getInputSource(ProfileFile.PATH_PROFM)) == null) {
            return;
        }
        setMetadataFile(ProfileMetadataFile.read(inputSource.openStream()));
    }

    public void refresh() {
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            profileFile.refresh();
        }
        ProfileMetadataFile metadataFile = getMetadataFile();
        if (metadataFile != null) {
            metadataFile.refresh();
        }
    }

    public void removeIfName(Predicate<String> predicate) {
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            profileFile.removeIfName(predicate);
        }
        ProfileMetadataFile metadataFile = getMetadataFile();
        if (metadataFile != null) {
            metadataFile.removeIfName(predicate);
        }
    }

    public void setMetadataFile(ProfileMetadataFile profileMetadataFile) {
        this.mMetadataFile = profileMetadataFile;
    }

    public void setProfileFile(ProfileDataFile profileDataFile) {
        this.mProfileFile = profileDataFile;
    }

    public void syncApk(final ZipEntryMap zipEntryMap) throws IOException {
        List<InputSource> listListDexFiles = listDexFiles(zipEntryMap);
        if (listListDexFiles.isEmpty()) {
            return;
        }
        removeIfName(new Predicate() { // from class: kab
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ProfileDirectory.f(zipEntryMap, (String) obj);
            }
        });
        for (InputSource inputSource : listListDexFiles) {
            ensureDexFile(inputSource.getAlias());
            linkUninitialized(inputSource);
            updateFileChecksum(inputSource);
        }
    }

    public void syncDexDirectory(final File file) throws IOException {
        final List<File> listListClassesDex = FileUtil.listClassesDex(file);
        if (listListClassesDex.isEmpty()) {
            return;
        }
        removeIfName(new Predicate() { // from class: jab
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ProfileDirectory.c(file, listListClassesDex, (String) obj);
            }
        });
        for (File file2 : listListClassesDex) {
            ensureDexFile(file2.getName());
            updateFileChecksum(file2);
            linkUninitialized(file2);
        }
    }

    public void syncSmaliDirectory(final File file) {
        final List<File> listListSmaliDirectories = listSmaliDirectories(file);
        if (listListSmaliDirectories.isEmpty()) {
            return;
        }
        removeIfName(new Predicate() { // from class: iab
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ProfileDirectory.e(file, listListSmaliDirectories, (String) obj);
            }
        });
        Iterator<File> it = listListSmaliDirectories.iterator();
        while (it.hasNext()) {
            ensureDexFile(it.next().getName() + ".dex");
        }
    }

    public String toString() {
        return "DexOpt{profile=" + this.mProfileFile + ", metadata=" + this.mMetadataFile + '}';
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            profileFile.update(dexFile);
        }
        ProfileMetadataFile metadataFile = getMetadataFile();
        if (metadataFile != null) {
            metadataFile.update(dexFile);
        }
    }

    public void updateChecksum(ZipEntryMap zipEntryMap) throws IOException {
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            profileFile.updateChecksum(zipEntryMap);
        }
    }

    public void updateChecksumInDexDirectory(File file) throws IOException {
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            for (DexProfileData dexProfileData : profileFile) {
                File file2 = new File(file, dexProfileData.getName());
                if (file2.isFile()) {
                    dexProfileData.setChecksum(Crc32.of(file2));
                }
            }
        }
    }

    public void updateDexDirectory(File file) throws IOException {
        Iterator<File> it = FileUtil.listClassesDex(file).iterator();
        while (it.hasNext()) {
            updateDexFile(it.next());
        }
    }

    public void updateDexFile(File file) throws IOException {
        String name = file.getName();
        DexProfileData profile = getProfile(name);
        ProfileMetadata metadata = getMetadata(name);
        if (profile == null && metadata == null) {
            return;
        }
        DexFile dexFile = DexFile.read(file, SectionType.minimal());
        dexFile.setSimpleName(name);
        if (profile != null) {
            profile.update(dexFile);
        }
        if (metadata != null) {
            metadata.update(dexFile);
        }
        dexFile.close();
    }

    public void updateFileChecksum(InputSource inputSource) throws IOException {
        DexProfileData profile = getProfile(inputSource.getAlias());
        if (profile != null) {
            profile.setChecksum(inputSource.getCrc());
        }
    }

    public void writeTo(ZipEntryMap zipEntryMap) {
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            zipEntryMap.add(new ByteInputSource(profileFile.getBytes(), ProfileFile.PATH_PROF));
        }
        ProfileMetadataFile metadataFile = getMetadataFile();
        if (metadataFile != null) {
            zipEntryMap.add(new ByteInputSource(metadataFile.getBytes(), ProfileFile.PATH_PROFM));
        }
    }

    public void updateFileChecksum(File file) throws IOException {
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            profileFile.updateFileChecksum(file);
        }
    }

    public void writeTo(File file) throws IOException {
        ProfileDataFile profileFile = getProfileFile();
        if (profileFile != null) {
            profileFile.write(new File(file, ProfileFile.NAME_PROF));
        }
        ProfileMetadataFile metadataFile = getMetadataFile();
        if (metadataFile != null) {
            metadataFile.write(new File(file, ProfileFile.NAME_PROFM));
        }
    }

    private void linkUninitialized(File file) throws IOException {
        String name = file.getName();
        if (isUninitialized(name)) {
            DexProfileData profile = getProfile(name);
            ProfileMetadata metadata = getMetadata(name);
            DexFile dexFile = DexFile.read(file, SectionType.minimal());
            dexFile.setSimpleName(name);
            if (profile != null && !profile.isInitialized()) {
                profile.update(dexFile);
            }
            if (metadata == null || metadata.isInitialized()) {
                return;
            }
            metadata.update(dexFile);
        }
    }
}

package com.reandroid.archive;

import com.reandroid.archive.block.CentralEntryHeader;
import com.reandroid.archive.block.DataDescriptor;
import com.reandroid.archive.block.LocalFileHeader;
import com.reandroid.archive.io.ArchiveFileEntrySource;
import com.reandroid.archive.writer.HeaderInterceptor;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.ObjectsUtil;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ArchiveInfo implements HeaderInterceptor, JSONConvert<JSONObject> {
    private int cehVersionMadeBy;
    private long dosTime;
    private int lfhVersionMadeBy;
    private int versionExtract;
    public static final String JSON_FILE = ObjectsUtil.of("archive-info.json");
    public static final String NAME_dosTime = ObjectsUtil.of("dos_time");
    public static final String NAME_cehVersionMadeBy = ObjectsUtil.of("ceh_version_made_by");
    public static final String NAME_lfhVersionMadeBy = ObjectsUtil.of("lfh_version_made_by");
    public static final String NAME_versionExtract = ObjectsUtil.of("version_extract");
    public static final String NAME_platform = ObjectsUtil.of("platform");

    public static ArchiveInfo apk() {
        ArchiveInfo archiveInfo = new ArchiveInfo();
        archiveInfo.setDosTime(35719201L);
        archiveInfo.setCehVersionMadeBy(768);
        archiveInfo.setLfhVersionMadeBy(0);
        archiveInfo.setVersionExtract(0);
        return archiveInfo;
    }

    public static ArchiveInfo build(ArchiveFileEntrySource archiveFileEntrySource) {
        if (archiveFileEntrySource == null) {
            return null;
        }
        ArchiveEntry archiveEntry = archiveFileEntrySource.getArchiveEntry();
        ArchiveInfo archiveInfo = new ArchiveInfo();
        archiveInfo.setDosTime(archiveEntry.getDosTime());
        archiveInfo.setCehVersionMadeBy(archiveEntry.getCentralEntryHeader().getVersionMadeBy());
        archiveInfo.setLfhVersionMadeBy(archiveEntry.getLocalFileHeader().getVersionMadeBy());
        archiveInfo.setVersionExtract(archiveEntry.getCentralEntryHeader().getVersionExtract());
        return archiveInfo;
    }

    private static ArchiveFileEntrySource pick(Iterator<InputSource> it) {
        ArchiveFileEntrySource archiveFileEntrySource = null;
        while (it.hasNext()) {
            InputSource next = it.next();
            if (next instanceof ArchiveFileEntrySource) {
                archiveFileEntrySource = (ArchiveFileEntrySource) next;
                if (!next.getName().startsWith("META-INF/")) {
                    break;
                }
            }
        }
        return archiveFileEntrySource;
    }

    public static ArchiveInfo readJson(File file) throws IOException {
        if (!file.isFile()) {
            file = new File(file, JSON_FILE);
        }
        if (!file.isFile()) {
            return null;
        }
        ArchiveInfo archiveInfo = new ArchiveInfo();
        archiveInfo.fromJson(new JSONObject(file));
        return archiveInfo;
    }

    public static ArchiveInfo zip() {
        ArchiveInfo archiveInfo = new ArchiveInfo();
        archiveInfo.setDosTime(2162688L);
        archiveInfo.setCehVersionMadeBy(20);
        archiveInfo.setLfhVersionMadeBy(20);
        archiveInfo.setVersionExtract(20);
        return archiveInfo;
    }

    public void fromJson(JSONObject jSONObject) {
        setDosTime(jSONObject.optLong(NAME_dosTime, getDosTime()));
        setCehVersionMadeBy(jSONObject.optInt(NAME_cehVersionMadeBy, getCehVersionMadeBy()));
        setLfhVersionMadeBy(jSONObject.optInt(NAME_lfhVersionMadeBy, getLfhVersionMadeBy()));
        setVersionExtract(jSONObject.optInt(NAME_versionExtract, getVersionExtract()));
    }

    public int getCehVersionMadeBy() {
        return this.cehVersionMadeBy;
    }

    public long getDosTime() {
        return this.dosTime;
    }

    public int getLfhVersionMadeBy() {
        return this.lfhVersionMadeBy;
    }

    public int getVersionExtract() {
        return this.versionExtract;
    }

    @Override // com.reandroid.archive.writer.HeaderInterceptor
    public void onWriteCeh(CentralEntryHeader centralEntryHeader) {
        long dosTime = getDosTime();
        if (dosTime != -1) {
            centralEntryHeader.setDosTime(dosTime);
        }
        int cehVersionMadeBy = getCehVersionMadeBy();
        if (cehVersionMadeBy != -1) {
            centralEntryHeader.setVersionMadeBy(cehVersionMadeBy);
        }
        int versionExtract = getVersionExtract();
        if (versionExtract != -1) {
            centralEntryHeader.setVersionExtract(versionExtract);
        }
    }

    @Override // com.reandroid.archive.writer.HeaderInterceptor
    public void onWriteDD(DataDescriptor dataDescriptor) {
    }

    @Override // com.reandroid.archive.writer.HeaderInterceptor
    public void onWriteLfh(LocalFileHeader localFileHeader) {
        long dosTime = getDosTime();
        if (dosTime != -1) {
            localFileHeader.setDosTime(dosTime);
        }
        int lfhVersionMadeBy = getLfhVersionMadeBy();
        if (lfhVersionMadeBy != -1) {
            localFileHeader.setVersionMadeBy(lfhVersionMadeBy);
        }
    }

    public void setCehVersionMadeBy(int i) {
        this.cehVersionMadeBy = i;
    }

    public void setDosTime(long j) {
        this.dosTime = j;
    }

    public void setLfhVersionMadeBy(int i) {
        this.lfhVersionMadeBy = i;
    }

    public void setVersionExtract(int i) {
        this.versionExtract = i;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NAME_dosTime, getDosTime());
        jSONObject.put(NAME_cehVersionMadeBy, getCehVersionMadeBy());
        jSONObject.put(NAME_lfhVersionMadeBy, getLfhVersionMadeBy());
        jSONObject.put(NAME_versionExtract, getVersionExtract());
        return jSONObject;
    }

    public String toString() {
        return toJson().toString();
    }

    public void write(File file) throws IOException {
        toJson().write(file);
    }

    public void writeToDirectory(File file) throws IOException {
        write(new File(file, JSON_FILE));
    }

    private static ArchiveFileEntrySource pick(InputSource[] inputSourceArr) {
        ArchiveFileEntrySource archiveFileEntrySource = null;
        for (InputSource inputSource : inputSourceArr) {
            if (inputSource instanceof ArchiveFileEntrySource) {
                archiveFileEntrySource = (ArchiveFileEntrySource) inputSource;
                if (!inputSource.getName().startsWith("META-INF/")) {
                    return archiveFileEntrySource;
                }
            }
        }
        return archiveFileEntrySource;
    }

    public static ArchiveInfo build(Iterator<InputSource> it) {
        return build(pick(it));
    }

    public static ArchiveInfo build(InputSource[] inputSourceArr) {
        return build(pick(inputSourceArr));
    }
}

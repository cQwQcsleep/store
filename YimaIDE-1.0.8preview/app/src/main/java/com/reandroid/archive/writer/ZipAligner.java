package com.reandroid.archive.writer;

import com.reandroid.archive.Archive;
import com.reandroid.archive.block.LocalFileHeader;
import com.reandroid.archive.block.ZipHeader;
import com.reandroid.archive.writer.ZipAligner;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ZipAligner {
    private static final int ALIGNMENT_4 = 4;
    private static final int ALIGNMENT_PAGE_4KB = 4096;
    private static final int NO_ALIGNMENT = 1;
    public static final Predicate<String> PREDICATE_NATIVE_LIBS = new Predicate() { // from class: m8g
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return ZipAligner.a((String) obj);
        }
    };
    private int defaultAlignment;
    private final Map<Pattern, Integer> alignmentPatternMapDepreciated = new HashMap();
    private final Map<Predicate<String>, Integer> alignmentMap = new HashMap();

    public static /* synthetic */ boolean a(String str) {
        return str.startsWith("lib/") && str.endsWith(".so");
    }

    public static ZipAligner apkAligner() {
        ZipAligner zipAligner = new ZipAligner();
        zipAligner.setDefaultAlignment(4);
        zipAligner.setFileAlignment(PREDICATE_NATIVE_LIBS, 4096);
        return zipAligner;
    }

    private int getAlignment(String str) {
        if (!this.alignmentMap.isEmpty()) {
            for (Map.Entry<Predicate<String>, Integer> entry : this.alignmentMap.entrySet()) {
                if (entry.getKey().test(str)) {
                    return entry.getValue().intValue();
                }
            }
        }
        int alignmentDepreciated = getAlignmentDepreciated(str);
        return alignmentDepreciated != 0 ? alignmentDepreciated : this.defaultAlignment;
    }

    private int getAlignmentDepreciated(String str) {
        if (this.alignmentPatternMapDepreciated.isEmpty()) {
            return 0;
        }
        for (Map.Entry<Pattern, Integer> entry : this.alignmentPatternMapDepreciated.entrySet()) {
            if (entry.getKey().matcher(str).matches()) {
                return entry.getValue().intValue();
            }
        }
        return 0;
    }

    public static ZipAligner noAlignment() {
        ZipAligner zipAligner = new ZipAligner();
        zipAligner.setDefaultAlignment(1);
        return zipAligner;
    }

    public void align(long j, LocalFileHeader localFileHeader) {
        int alignment;
        if (ZipHeader.isZip64Length(localFileHeader.getSize() + j)) {
            return;
        }
        int iCountBytes = 0;
        localFileHeader.setZipAlign(0);
        localFileHeader.updateDataDescriptor();
        if (localFileHeader.getMethod() != Archive.DEFLATED && (alignment = getAlignment(localFileHeader.getFileName())) != 1) {
            long j2 = alignment;
            iCountBytes = (int) ((j2 - ((j + ((long) localFileHeader.countBytes())) % j2)) % j2);
        }
        localFileHeader.setZipAlign(iCountBytes);
    }

    public void clearFileAlignment() {
        this.alignmentMap.clear();
        this.alignmentPatternMapDepreciated.clear();
    }

    public void setDefaultAlignment(int i) {
        if (i <= 0) {
            i = 1;
        }
        this.defaultAlignment = i;
    }

    public void setFileAlignment(Predicate<String> predicate, int i) {
        if (predicate == null) {
            return;
        }
        this.alignmentMap.remove(predicate);
        if (i > 1) {
            this.alignmentMap.put(predicate, Integer.valueOf(i));
        }
    }

    @Deprecated
    public void setFileAlignment(Pattern pattern, int i) {
        if (pattern == null) {
            return;
        }
        this.alignmentPatternMapDepreciated.remove(pattern);
        if (i > 1) {
            this.alignmentPatternMapDepreciated.put(pattern, Integer.valueOf(i));
        }
    }
}

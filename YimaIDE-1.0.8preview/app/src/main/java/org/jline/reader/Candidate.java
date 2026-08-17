package org.jline.reader;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class Candidate implements Comparable<Candidate> {
    private final boolean complete;
    private final String descr;
    private final String displ;
    private final String group;
    private final String key;
    private final int sort;
    private final String suffix;
    private final String value;

    public Candidate(String str, String str2, String str3, String str4, String str5, String str6, boolean z, int i) {
        Objects.requireNonNull(str);
        this.value = str;
        Objects.requireNonNull(str2);
        this.displ = str2;
        this.group = str3;
        this.descr = str4;
        this.suffix = str5;
        this.key = str6;
        this.complete = z;
        this.sort = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(Candidate candidate) {
        return this.sort == candidate.sort() ? this.value.compareTo(candidate.value) : Integer.compare(this.sort, candidate.sort());
    }

    public boolean complete() {
        return this.complete;
    }

    public String descr() {
        return this.descr;
    }

    public String displ() {
        return this.displ;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.value, ((Candidate) obj).value);
    }

    public String group() {
        return this.group;
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    public String key() {
        return this.key;
    }

    public int sort() {
        return this.sort;
    }

    public String suffix() {
        return this.suffix;
    }

    public String toString() {
        return "Candidate{" + this.value + "}";
    }

    public String value() {
        return this.value;
    }

    public Candidate(String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        this(str, str2, str3, str4, str5, str6, z, 0);
    }
}

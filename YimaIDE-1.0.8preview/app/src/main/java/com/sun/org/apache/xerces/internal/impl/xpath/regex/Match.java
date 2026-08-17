package com.sun.org.apache.xerces.internal.impl.xpath.regex;

import java.text.CharacterIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Match implements Cloneable {
    int[] beginpos = null;
    int[] endpos = null;
    int nofgroups = 0;
    CharacterIterator ciSource = null;
    String strSource = null;
    char[] charSource = null;

    public synchronized Object clone() {
        Match match;
        try {
            match = new Match();
            int i = this.nofgroups;
            if (i > 0) {
                match.setNumberOfGroups(i);
                CharacterIterator characterIterator = this.ciSource;
                if (characterIterator != null) {
                    match.setSource(characterIterator);
                }
                String str = this.strSource;
                if (str != null) {
                    match.setSource(str);
                }
                for (int i2 = 0; i2 < this.nofgroups; i2++) {
                    match.setBeginning(i2, getBeginning(i2));
                    match.setEnd(i2, getEnd(i2));
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return match;
    }

    public int getBeginning(int i) {
        int[] iArr = this.beginpos;
        if (iArr == null) {
            k2d.a("A result is not set.");
            return 0;
        }
        if (i >= 0 && this.nofgroups > i) {
            return iArr[i];
        }
        eq7.a("The parameter must be less than ", this.nofgroups, ": ", i);
        return 0;
    }

    public String getCapturedText(int i) {
        int[] iArr = this.beginpos;
        if (iArr == null) {
            k2d.a("match() has never been called.");
            return null;
        }
        if (i < 0 || this.nofgroups <= i) {
            eq7.a("The parameter must be less than ", this.nofgroups, ": ", i);
            return null;
        }
        int i2 = iArr[i];
        int i3 = this.endpos[i];
        if (i2 < 0 || i3 < 0) {
            return null;
        }
        CharacterIterator characterIterator = this.ciSource;
        if (characterIterator != null) {
            return REUtil.substring(characterIterator, i2, i3);
        }
        String str = this.strSource;
        return str != null ? str.substring(i2, i3) : new String(this.charSource, i2, i3 - i2);
    }

    public int getEnd(int i) {
        int[] iArr = this.endpos;
        if (iArr == null) {
            k2d.a("A result is not set.");
            return 0;
        }
        if (i >= 0 && this.nofgroups > i) {
            return iArr[i];
        }
        eq7.a("The parameter must be less than ", this.nofgroups, ": ", i);
        return 0;
    }

    public int getNumberOfGroups() {
        int i = this.nofgroups;
        if (i > 0) {
            return i;
        }
        k2d.a("A result is not set.");
        return 0;
    }

    public void setBeginning(int i, int i2) {
        this.beginpos[i] = i2;
    }

    public void setEnd(int i, int i2) {
        this.endpos[i] = i2;
    }

    public void setNumberOfGroups(int i) {
        int i2 = this.nofgroups;
        this.nofgroups = i;
        if (i2 <= 0 || i2 < i || i * 2 < i2) {
            this.beginpos = new int[i];
            this.endpos = new int[i];
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.beginpos[i3] = -1;
            this.endpos[i3] = -1;
        }
    }

    public void setSource(CharacterIterator characterIterator) {
        this.ciSource = characterIterator;
        this.strSource = null;
        this.charSource = null;
    }

    public void setSource(String str) {
        this.ciSource = null;
        this.strSource = str;
        this.charSource = null;
    }

    public void setSource(char[] cArr) {
        this.ciSource = null;
        this.strSource = null;
        this.charSource = cArr;
    }
}

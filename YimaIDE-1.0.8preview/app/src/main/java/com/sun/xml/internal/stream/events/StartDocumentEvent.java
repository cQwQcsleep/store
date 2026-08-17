package com.sun.xml.internal.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.Location;
import javax.xml.stream.events.StartDocument;
import jdk.xml.internal.JdkConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StartDocumentEvent extends DummyEvent implements StartDocument {
    protected String fEncodingScheam;
    private boolean fEncodingSchemeSet;
    protected boolean fStandalone;
    private boolean fStandaloneSet;
    protected String fSystemId;
    protected String fVersion;
    private boolean nestedCall;

    public StartDocumentEvent() {
        this.fEncodingSchemeSet = false;
        this.fStandaloneSet = false;
        this.nestedCall = false;
        init("UTF-8", "1.0", true, null);
    }

    public void clear() {
        this.fEncodingScheam = "UTF-8";
        this.fStandalone = true;
        this.fVersion = "1.0";
        this.fEncodingSchemeSet = false;
        this.fStandaloneSet = false;
    }

    public boolean encodingSet() {
        return this.fEncodingSchemeSet;
    }

    public String getCharacterEncodingScheme() {
        return this.fEncodingScheam;
    }

    public String getSystemId() {
        Location location = this.fLocation;
        return location == null ? "" : location.getSystemId();
    }

    public String getVersion() {
        return this.fVersion;
    }

    public void init(String str, String str2, boolean z, Location location) {
        setEventType(7);
        this.fEncodingScheam = str;
        this.fVersion = str2;
        this.fStandalone = z;
        if (str == null || str.isEmpty()) {
            this.fEncodingSchemeSet = false;
            this.fEncodingScheam = "UTF-8";
        } else {
            this.fEncodingSchemeSet = true;
        }
        this.fLocation = location;
    }

    public boolean isStandalone() {
        return this.fStandalone;
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public boolean isStartDocument() {
        return true;
    }

    public void setDeclaredEncoding(boolean z) {
        this.fEncodingSchemeSet = z;
    }

    public void setEncoding(String str) {
        this.fEncodingScheam = str;
    }

    public void setStandalone(String str) {
        this.fStandaloneSet = true;
        if (str == null) {
            this.fStandalone = true;
        } else if (str.equals(JdkConstants.JDK_YES)) {
            this.fStandalone = true;
        } else {
            this.fStandalone = false;
        }
    }

    public void setVersion(String str) {
        this.fVersion = str;
    }

    public boolean standaloneSet() {
        return this.fStandaloneSet;
    }

    public String toString() {
        String str = ("<?xml version=\"" + this.fVersion + "\"") + " encoding='" + this.fEncodingScheam + "'";
        if (this.fStandaloneSet) {
            return this.fStandalone ? str.concat(" standalone='yes'?>") : str.concat(" standalone='no'?>");
        }
        return str.concat("?>");
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(toString());
    }

    public StartDocumentEvent(String str) {
        this.fEncodingSchemeSet = false;
        this.fStandaloneSet = false;
        this.nestedCall = false;
        init(str, "1.0", true, null);
    }

    public void setStandalone(boolean z, boolean z2) {
        this.fStandaloneSet = z2;
        this.fStandalone = z;
    }

    public StartDocumentEvent(String str, String str2) {
        this.fEncodingSchemeSet = false;
        this.fStandaloneSet = false;
        this.nestedCall = false;
        init(str, str2, true, null);
    }

    public StartDocumentEvent(String str, String str2, boolean z) {
        this.fEncodingSchemeSet = false;
        this.nestedCall = false;
        this.fStandaloneSet = true;
        init(str, str2, z, null);
    }

    public StartDocumentEvent(String str, String str2, boolean z, Location location) {
        this.fEncodingSchemeSet = false;
        this.nestedCall = false;
        this.fStandaloneSet = true;
        init(str, str2, z, location);
    }
}

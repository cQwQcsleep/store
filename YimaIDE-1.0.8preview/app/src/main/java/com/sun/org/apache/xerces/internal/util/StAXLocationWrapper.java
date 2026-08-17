package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import javax.xml.stream.Location;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class StAXLocationWrapper implements XMLLocator {
    private Location fLocation = null;

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getBaseSystemId() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getCharacterOffset() {
        Location location = this.fLocation;
        if (location != null) {
            return location.getCharacterOffset();
        }
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getColumnNumber() {
        Location location = this.fLocation;
        if (location != null) {
            return location.getColumnNumber();
        }
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getEncoding() {
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getExpandedSystemId() {
        return getLiteralSystemId();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public int getLineNumber() {
        Location location = this.fLocation;
        if (location != null) {
            return location.getLineNumber();
        }
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getLiteralSystemId() {
        Location location = this.fLocation;
        if (location != null) {
            return location.getSystemId();
        }
        return null;
    }

    public Location getLocation() {
        return this.fLocation;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getPublicId() {
        Location location = this.fLocation;
        if (location != null) {
            return location.getPublicId();
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.XMLLocator
    public String getXMLVersion() {
        return null;
    }

    public void setLocation(Location location) {
        this.fLocation = location;
    }
}

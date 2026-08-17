package org.codehaus.stax2.ri;

import javax.xml.stream.Location;
import org.codehaus.stax2.XMLStreamLocation2;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class Stax2LocationAdapter implements XMLStreamLocation2 {
    protected final Location mParentLocation;
    protected final Location mWrappedLocation;

    public Stax2LocationAdapter(Location location, Location location2) {
        this.mWrappedLocation = location;
        this.mParentLocation = location2;
    }

    public int getCharacterOffset() {
        return this.mWrappedLocation.getCharacterOffset();
    }

    public int getColumnNumber() {
        return this.mWrappedLocation.getColumnNumber();
    }

    @Override // org.codehaus.stax2.XMLStreamLocation2
    public XMLStreamLocation2 getContext() {
        Location location = this.mParentLocation;
        if (location == null) {
            return null;
        }
        return location instanceof XMLStreamLocation2 ? (XMLStreamLocation2) location : new Stax2LocationAdapter(location);
    }

    public int getLineNumber() {
        return this.mWrappedLocation.getLineNumber();
    }

    public String getPublicId() {
        return this.mWrappedLocation.getPublicId();
    }

    public String getSystemId() {
        return this.mWrappedLocation.getSystemId();
    }

    public Stax2LocationAdapter(Location location) {
        this(location, null);
    }
}

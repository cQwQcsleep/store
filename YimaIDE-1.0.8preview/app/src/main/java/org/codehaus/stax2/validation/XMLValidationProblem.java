package org.codehaus.stax2.validation;

import javax.xml.stream.Location;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class XMLValidationProblem {
    public static final int SEVERITY_ERROR = 2;
    public static final int SEVERITY_FATAL = 3;
    public static final int SEVERITY_WARNING = 1;
    protected Location mLocation;
    protected final String mMessage;
    protected XMLValidator mReporter;
    protected final int mSeverity;
    protected String mType;

    public XMLValidationProblem(Location location, String str, int i, String str2) {
        this.mLocation = location;
        this.mMessage = str;
        this.mSeverity = i;
        this.mType = str2;
    }

    public Location getLocation() {
        return this.mLocation;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public XMLValidator getReporter() {
        return this.mReporter;
    }

    public int getSeverity() {
        return this.mSeverity;
    }

    public String getType() {
        return this.mType;
    }

    public void setLocation(Location location) {
        this.mLocation = location;
    }

    public void setReporter(XMLValidator xMLValidator) {
        this.mReporter = xMLValidator;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public XMLValidationException toException() {
        return XMLValidationException.createException(this);
    }

    public XMLValidationProblem(Location location, String str, int i) {
        this(location, str, i, null);
    }

    public XMLValidationProblem(Location location, String str) {
        this(location, str, 2);
    }
}

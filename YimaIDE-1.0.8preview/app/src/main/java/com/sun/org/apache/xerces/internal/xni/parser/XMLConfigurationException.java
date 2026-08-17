package com.sun.org.apache.xerces.internal.xni.parser;

import com.sun.org.apache.xerces.internal.util.Status;
import com.sun.org.apache.xerces.internal.xni.XNIException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLConfigurationException extends XNIException {
    static final long serialVersionUID = -5437427404547669188L;
    protected String fIdentifier;
    protected Status fType;

    public XMLConfigurationException(Status status, String str) {
        super(str);
        this.fType = status;
        this.fIdentifier = str;
    }

    public String getIdentifier() {
        return this.fIdentifier;
    }

    public Status getType() {
        return this.fType;
    }

    public XMLConfigurationException(Status status, String str, String str2) {
        super(str2);
        this.fType = status;
        this.fIdentifier = str;
    }
}

package org.codehaus.stax2.validation;

import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class XMLValidationException extends XMLStreamException {
    private static final long serialVersionUID = 1;
    protected XMLValidationProblem mCause;

    public XMLValidationException(XMLValidationProblem xMLValidationProblem) {
        if (xMLValidationProblem == null) {
            throwMissing();
        }
        this.mCause = xMLValidationProblem;
    }

    public static XMLValidationException createException(XMLValidationProblem xMLValidationProblem) {
        String message = xMLValidationProblem.getMessage();
        if (message == null) {
            return new XMLValidationException(xMLValidationProblem);
        }
        Location location = xMLValidationProblem.getLocation();
        return location == null ? new XMLValidationException(xMLValidationProblem, message) : new XMLValidationException(xMLValidationProblem, message, location);
    }

    public static void throwMissing() throws RuntimeException {
        throw new IllegalArgumentException("Validation problem argument can not be null");
    }

    public XMLValidationProblem getValidationProblem() {
        return this.mCause;
    }

    public XMLValidationException(XMLValidationProblem xMLValidationProblem, String str) {
        super(str);
        if (xMLValidationProblem == null) {
            throwMissing();
        }
        this.mCause = xMLValidationProblem;
    }

    public XMLValidationException(XMLValidationProblem xMLValidationProblem, String str, Location location) {
        super(str, location);
        if (xMLValidationProblem == null) {
            throwMissing();
        }
        this.mCause = xMLValidationProblem;
    }
}

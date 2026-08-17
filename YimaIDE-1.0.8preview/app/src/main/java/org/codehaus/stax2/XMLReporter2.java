package org.codehaus.stax2;

import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.validation.XMLValidationProblem;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface XMLReporter2 extends XMLReporter {
    void report(XMLValidationProblem xMLValidationProblem) throws XMLStreamException;
}

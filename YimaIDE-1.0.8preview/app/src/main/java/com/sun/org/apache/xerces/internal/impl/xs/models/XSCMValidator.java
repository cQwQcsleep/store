package com.sun.org.apache.xerces.internal.impl.xs.models;

import com.sun.org.apache.xerces.internal.impl.xs.SubstitutionGroupHandler;
import com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaException;
import com.sun.org.apache.xerces.internal.xni.QName;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSCMValidator {
    public static final short FIRST_ERROR = -1;
    public static final short SUBSEQUENT_ERROR = -2;

    List<String> checkMinMaxBounds();

    boolean checkUniqueParticleAttribution(SubstitutionGroupHandler substitutionGroupHandler) throws XMLSchemaException;

    boolean endContentModel(int[] iArr);

    String getTermName(int i);

    boolean isCompactedForUPA();

    int[] occurenceInfo(int[] iArr);

    Object oneTransition(QName qName, int[] iArr, SubstitutionGroupHandler substitutionGroupHandler);

    int[] startContentModel();

    List<Object> whatCanGoHere(int[] iArr);
}

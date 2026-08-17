package com.sun.org.apache.xerces.internal.xs;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSElementDeclaration extends XSTerm {
    boolean getAbstract();

    @Deprecated
    Object getActualVC() throws XSException;

    @Deprecated
    short getActualVCType() throws XSException;

    XSAnnotation getAnnotation();

    XSObjectList getAnnotations();

    short getConstraintType();

    @Deprecated
    String getConstraintValue();

    short getDisallowedSubstitutions();

    XSComplexTypeDefinition getEnclosingCTDefinition();

    XSNamedMap getIdentityConstraints();

    @Deprecated
    ShortList getItemValueTypes() throws XSException;

    boolean getNillable();

    short getScope();

    XSElementDeclaration getSubstitutionGroupAffiliation();

    short getSubstitutionGroupExclusions();

    XSTypeDefinition getTypeDefinition();

    XSValue getValueConstraintValue();

    boolean isDisallowedSubstitution(short s);

    boolean isSubstitutionGroupExclusion(short s);
}

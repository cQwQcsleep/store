package com.sun.org.apache.xerces.internal.xs.datatypes;

import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface XSDecimal {
    BigDecimal getBigDecimal();

    BigInteger getBigInteger() throws NumberFormatException;

    byte getByte() throws NumberFormatException;

    int getInt() throws NumberFormatException;

    long getLong() throws NumberFormatException;

    short getShort() throws NumberFormatException;
}

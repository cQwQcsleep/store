package org.eclipse.jdt.internal.compiler.classfmt;

import java.io.PrintStream;
import java.io.PrintWriter;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ClassFormatException extends Exception {
    public static final int ErrBadClassname = 16;
    public static final int ErrBadComponentInfo = 29;
    public static final int ErrBadConstantClass = 4;
    public static final int ErrBadConstantFieldRef = 7;
    public static final int ErrBadConstantInterfaceMethodRef = 9;
    public static final int ErrBadConstantMethodRef = 8;
    public static final int ErrBadConstantNameAndType = 6;
    public static final int ErrBadConstantPoolIndex = 10;
    public static final int ErrBadConstantString = 5;
    public static final int ErrBadFieldInfo = 17;
    public static final int ErrBadInterface = 24;
    public static final int ErrBadMagic = 1;
    public static final int ErrBadMajorVersion = 3;
    public static final int ErrBadMethodInfo = 17;
    public static final int ErrBadMinorVersion = 2;
    public static final int ErrBadModifiers = 14;
    public static final int ErrBadSuperclassName = 11;
    public static final int ErrClassCannotBeAbstractFinal = 15;
    public static final int ErrEmptyConstantPool = 18;
    public static final int ErrIncorrectInterfaceMethods = 26;
    public static final int ErrInterfaceCannotBeFinal = 12;
    public static final int ErrInterfaceMustBeAbstract = 13;
    public static final int ErrInterfaceMustSubclassObject = 25;
    public static final int ErrInvalidMethodName = 27;
    public static final int ErrInvalidMethodSignature = 28;
    public static final int ErrMalformedAttribute = 23;
    public static final int ErrMalformedUtf8 = 19;
    public static final int ErrMethodMustBeAbstract = 22;
    public static final int ErrTruncatedInput = 21;
    public static final int ErrUnknownConstantTag = 20;
    private static final long serialVersionUID = 6667458511042774540L;
    private int bufferPosition;
    private int errorCode;
    private char[] fileName;
    private Exception nestedException;

    public ClassFormatException(Exception exc, char[] cArr, int i, int i2) {
        this.nestedException = exc;
        this.fileName = cArr;
        this.errorCode = i;
        this.bufferPosition = i2;
    }

    public int getBufferPosition() {
        return this.bufferPosition;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public Throwable getException() {
        return this.nestedException;
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        synchronized (printStream) {
            try {
                super.printStackTrace(printStream);
                Throwable exception = getException();
                if (exception != null) {
                    if (this.fileName != null) {
                        printStream.print("Caused in ");
                        printStream.print(this.fileName);
                        printStream.print(" by: ");
                    } else {
                        printStream.print("Caused by: ");
                    }
                    exception.printStackTrace(printStream);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public ClassFormatException(int i) {
        this.errorCode = i;
    }

    public ClassFormatException(RuntimeException runtimeException, char[] cArr) {
        this.nestedException = runtimeException;
        this.fileName = cArr;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        synchronized (printWriter) {
            try {
                super.printStackTrace(printWriter);
                Throwable exception = getException();
                if (exception != null) {
                    if (this.fileName != null) {
                        printWriter.print("Caused in ");
                        printWriter.print(this.fileName);
                        printWriter.print(" by: ");
                    } else {
                        printWriter.print("Caused by: ");
                    }
                    exception.printStackTrace(printWriter);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

package org.jcodings.transcode;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum EConvResult {
    InvalidByteSequence,
    UndefinedConversion,
    DestinationBufferFull,
    SourceBufferEmpty,
    Finished,
    AfterOutput,
    IncompleteInput;

    private final String symbolicName;

    EConvResult() {
        String strName = name();
        StringBuilder sb = new StringBuilder(strName.length() + 3);
        for (int i = 0; i < strName.length(); i++) {
            char cCharAt = strName.charAt(i);
            if (Character.isLowerCase(cCharAt)) {
                sb.append(cCharAt);
            } else if (Character.isUpperCase(cCharAt)) {
                if (i > 0) {
                    sb.append('_');
                }
                sb.append(Character.toLowerCase(cCharAt));
            }
        }
        this.symbolicName = sb.toString().intern();
    }

    public boolean isAfterOutput() {
        return this == AfterOutput;
    }

    public boolean isDestinationBufferFull() {
        return this == DestinationBufferFull;
    }

    public boolean isFinished() {
        return this == Finished;
    }

    public boolean isIncompleteInput() {
        return this == IncompleteInput;
    }

    public boolean isInvalidByteSequence() {
        return this == InvalidByteSequence;
    }

    public boolean isSourceBufferEmpty() {
        return this == SourceBufferEmpty;
    }

    public boolean isUndefinedConversion() {
        return this == UndefinedConversion;
    }

    public String symbolicName() {
        return this.symbolicName;
    }
}

package com.mygdx.util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    private static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException ignored) {}
    }

    public static long hash(long number) {
        byte[] bytes = ByteBuffer.allocate(Long.SIZE).putLong(number).array();
        md.update(bytes);
        byte[] digest = md.digest();
        ByteBuffer bb = ByteBuffer.wrap(digest);
        return bb.getLong();
    }
}

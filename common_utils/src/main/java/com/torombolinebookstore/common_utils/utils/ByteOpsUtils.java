package com.torombolinebookstore.common_utils.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

public class ByteOpsUtils {


    public static UUID getUUIDFromByteArray(byte[] uuidBytes){
        ByteBuffer buffer = ByteBuffer.wrap(uuidBytes);
        buffer.order(ByteOrder.BIG_ENDIAN);
        long mostSignificantBits = buffer.getLong();
        long leastSignificantBits = buffer.getLong();
        return new UUID(mostSignificantBits, leastSignificantBits);
    }

    public static byte[] getByteArrayFromUUID(UUID uuid){
        byte[] uuidBytes = new byte[16];
        ByteBuffer.wrap(uuidBytes)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits());
        return  uuidBytes;
    }

    public static String generateRandomSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[32]; // 16 bytes for a 128-bit salt
        random.nextBytes(salt);
        String result = Base64.getEncoder().encodeToString(salt);
        result = result.length()>24? result.substring(result.length()-24):result;
        return result;
    }


}

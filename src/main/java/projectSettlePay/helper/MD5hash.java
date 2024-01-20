package projectSettlePay.helper;

import org.apache.commons.codec.digest.DigestUtils;

public interface MD5hash {

    static String getHash(String text){
        return DigestUtils.md5Hex(text);
    }
}

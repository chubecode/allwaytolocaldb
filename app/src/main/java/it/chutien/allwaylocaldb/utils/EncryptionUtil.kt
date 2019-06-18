package it.chutien.allwaylocaldb.utils

import ru.bullyboo.encoder.Encoder
import ru.bullyboo.encoder.methods.AES

/**
 * Created by ChuTien on ${1/25/2017}.
 */


object EncryptionUtil {

    // Encrypts a string
    fun encrypt(message: String) = Encoder.BuilderAES()
        .method(AES.Method.AES_CBC_PKCS5PADDING)
        .message(message)
        .key("test key") // not necessary
        .keySize(AES.Key.SIZE_128) // not necessary
        .iVector("test vector") // not necessary
        .encrypt()

    // Decrypts a message
    fun decrypt(encrypt: String) = Encoder.BuilderAES()
        .message(encrypt)
        .method(AES.Method.AES_CBC_PKCS5PADDING)
        .key("test key", AES.Key.SIZE_128)
        .iVector("test vector")
        .decrypt();
}





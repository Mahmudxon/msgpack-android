package uz.mahmudxon.msgpack

class Msgpack {
    init {
        System.loadLibrary("messagepack")
    }
    external fun convertToJson(msgpackData: ByteArray): String
    external fun convertFromJson(jsonData: String): ByteArray
}
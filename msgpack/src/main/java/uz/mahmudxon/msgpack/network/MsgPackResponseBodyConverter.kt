package uz.mahmudxon.msgpack.network

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonToken
import okhttp3.ResponseBody
import retrofit2.Converter
import uz.mahmudxon.msgpack.Msgpack
import java.io.IOException

internal class MsgPackResponseBodyConverter<T>(private val gson: Gson?, private val adapter: TypeAdapter<T>) : Converter<ResponseBody?, T> {

    private val msgpack by lazy { Msgpack() }

    @Throws(IOException::class)
    override fun convert(value: ResponseBody?): T {
        val jsonReader = gson!!.newJsonReader(value?.bytes()?.let { msgpack.convertToJson(it).reader() })
        return value.use { _ ->
            val result = adapter.read(jsonReader)
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw JsonIOException("JSON document was not fully consumed.")
            }
            result
        }
    }

}
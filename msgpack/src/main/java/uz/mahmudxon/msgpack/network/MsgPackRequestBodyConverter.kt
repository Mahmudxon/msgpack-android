package uz.mahmudxon.msgpack.network

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter
import uz.mahmudxon.msgpack.Msgpack
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.nio.charset.Charset

internal class MsgPackRequestBodyConverter<T>(private val gson: Gson?, val adapter: TypeAdapter<T>) :
    Converter<T, RequestBody?> {
    private val mediaType: MediaType? by lazy { MediaType.parse("application/octet-stream; charset=UTF-8") }
    private val charset by lazy { Charset.forName("UTF-8") }
    private val msgpack by lazy { Msgpack() }

    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer: Writer = OutputStreamWriter(buffer.outputStream(), charset)
        val jsonWriter = gson!!.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        val jsonData = buffer.readByteString().string(charset)
        val msgData = msgpack.convertFromJson(jsonData)
        return RequestBody.create(mediaType, msgData)
    }
}
package uz.mahmudxon.example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import uz.mahmudxon.msgpack.Msgpack
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private val textJson =
        "{ \"glossary\": { \"title\": \"example glossary\", \"GlossDiv\": { \"title\": \"S\", \"GlossList\": { \"GlossEntry\": { \"ID\": \"SGML\", \"SortAs\": \"SGML\", \"GlossTerm\": \"Standard Generalized Markup Language\", \"Acronym\": \"SGML\", \"Abbrev\": \"ISO 8879:1986\", \"GlossDef\": { \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\", \"GlossSeeAlso\": [\"GML\", \"XML\"] }, \"GlossSee\": \"markup\" } } } }\n }"

    private val msgpack by lazy { Msgpack() }
    private val TAG = "TEST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val msgData = msgpack.convertFromJson(textJson)
        Log.d(TAG, "Msgpack Data: ${msgData.toString(Charset.forName("UTF-8"))}")
        val jsonData = msgpack.convertToJson(msgData)
        Log.d(TAG, "Decoded json Data: $jsonData")
    }
}
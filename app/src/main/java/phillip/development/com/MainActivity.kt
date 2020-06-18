package phillip.development.com

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Matrix
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

import phillip.development.com.Data.ApiCurrencyService

import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() , SensorEventListener {
    //Sensors
    lateinit var mSensorManager : SensorManager

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        setContentView(R.layout.activity_main)
        // get reference of the service
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // focus in accelerometer
        mSensorManager.registerListener(this,
            mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
        

        val usdradio : RadioButton = findViewById(R.id.usdradio)
        val eurradio : RadioButton = findViewById(R.id.eurradio)
        //Text
        val caText : TextView = findViewById(R.id.CanadaTxt)
        val ukText : TextView = findViewById(R.id.UkText)
        val mxText : TextView = findViewById(R.id.MxText)
        //Image
        val arrow : ImageView = findViewById(R.id.arrow)


        fun getData(method: String){

            val apiService = Retrofit.Builder().baseUrl("https://api.exchangeratesapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiCurrencyService::class.java)

            GlobalScope.launch(Dispatchers.IO) {//This one launches the operation on another thread
                  try {
                      val rates = apiService.getCurrency(method).await()
                      GlobalScope.launch( Dispatchers.Main ) {//This one will launch the results to the main thread
                          //caText.text = "%.2f".format(rates.ratesData.cAD) //maybe if i want to format the value
                          caText.text = rates.ratesData.cAD.toString()
                          ukText.text = rates.ratesData.gBP.toString()
                          mxText.text = rates.ratesData.mXN.toString()

                          Log.d("Rates data", rates.toString())
                      }
                  }catch (e:Exception){e.printStackTrace()}
            }
        }

       radiosel.setOnCheckedChangeListener(){_,checkedId ->
           val radio: RadioButton = findViewById(checkedId)
            // This will check which radio button is clicked and then call the class for updating data
            when (radio){
                usdradio -> {
                    getData("USD")
                }
                eurradio ->{
                    getData("EUR")
                }

            }
       }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        val xposition = event.values[0]
        if(xposition>=1)
        {
            arrow.scaleX = (-1).toFloat()
        }else {
            arrow.scaleX = (1).toFloat()
        }
    }

    override fun onResume() {
        super.onResume()
//        Register the sensor on resume of the activity
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
//        unregister the sensor onPause else it will be active even if the activity is closed
        mSensorManager.unregisterListener(this)
    }
}





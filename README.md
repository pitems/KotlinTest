---


---

<h1 id="welcome-to-kotlin-test">Welcome to Kotlin Test</h1>
<p>This is a Test Project for Kotlin Using multiple libraries those being retrofit2, Coroutines, Sensors</p>
<h1 id="retrofit-2">Retrofit 2</h1>
<p>This Library allows us to make a call to an api rest service to recover some data from it, we build it using an interface for it and creating later an apiService val that will receive a method selected on the radio buttons(either USD or EUR) and calling it through a Coroutines using a different thread that the main thread to not stop the application</p>
<h1 id="coroutines">Coroutines</h1>
<p>Similar to using Threads on java or Async and await on dart in flutter this allows us to make asynchronous calls without stopping the application if the method being called takes too long to finish.<br>
In here i used 2 Coroutines called GlobalScope those being</p>
<h2 id="globalscope.launchdispatchers.io">GlobalScope.launch(<a href="http://Dispatchers.IO">Dispatchers.IO</a>)</h2>
<p>This takes all the IO data from the api call and doesn’t stop the main thread</p>
<h2 id="globalscope.launchdispatchers.main">GlobalScope.launch(Dispatchers.Main)</h2>
<p>This will make a call or execute a method in the main thread i used it for updating data on the ui when i received the data from the api service</p>
<h1 id="sensors">Sensors</h1>
<p>This was kinda hard at first to utilize because i never played with the sensors but it works like this first we call the system to detect a sensor, we have several of them and we can list them to know which sensors our device has at the moment. I used the accelerometer to check it’s x axis position.</p>
<p>We used a Sensor Manager with a listener to wait for any changes made in the sensor, in this case any movement made on the device</p>
<p>We added an override to the onSensorChanged method so when its xposition changed it flipped a picture on horizontally</p>
<p>We also added a onResume and onPause overrides so the sensor stops and resume if the application its on the background</p>
<h1 id="section"></h1>

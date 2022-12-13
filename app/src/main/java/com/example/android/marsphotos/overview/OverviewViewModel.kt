/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    // private val _status = MutableLiveData<String>()
    private val _status = MutableLiveData<MarsApiStatus>()

    val status: LiveData<MarsApiStatus> = _status

    // 3.1 Open overview/OverviewViewModel.kt. Just below the _status property declaration, add a new
    // mutable property called _photos, of the type MutableLiveData that can store a single MarsPhoto object.

    // 6.1 Change the _photos type to be a list of MarsPhoto objects.

    private val _photos = MutableLiveData<List<MarsPhoto>>()
    // 3.2 Just below the _photos declaration, add a public backing field called photos of the type, LiveData<MarsPhoto>.

    // 6.1 Replace the backing property photos type to the List<MarsPhoto> type as well:

    val photos: LiveData<List<MarsPhoto>> = _photos

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {
        //*15 Inside getMarsPhotos(), launch the coroutine using viewModelScope.launch.
        // Exception Handling 1. Open overview/OverviewViewModel.kt. Scroll down to the getMarsPhotos()
        // method. Inside the launch block, add a try block around MarsApi call to handle exceptions.
        // Add catch block after the try block:
        viewModelScope.launch{
            //*16 Inside viewModelScope, use the singleton object MarsApi, to call the getPhotos()
        // method from the RETROFITSERVICE interface. Save the returned response in a val called listResult.
            _status.value = MarsApiStatus.LOADING
            try {
                // 3.3 In the getMarsPhotos() method, inside the try{} block, find the line that
                // sets the data retrieved from the web service to listResult. Assign the first Mars
                // photo retrieved to the new variable _photos. Change the listResult to _photos.value.
                // Assign the first photos url at the index 0. This will throw an error, you will fix it later.
                // _photos.value = MarsApi.retrofitService.getPhotos()[0]
                _photos.value = MarsApi.retrofitService.getPhotos()


                // val listResult = MarsApi.retrofitService.getPhotos()
                //*17 Assign the result we just received from the backend server to the _status.value.

                // In the method getMarsPhotos(), listResult is a List<MarsPhoto> not a String
                // anymore. The size of that list is the number of photos that were received and
                // parsed. To print the number of photos retrieved update _status.value as follows.

                // 3.5 In the next line, update the status.value to the following. Use the data from
                // the new property instead of listResult. Display the first image URL from the photos List.

                // _status.value = "   First Mars image URL : ${_photos.value!!.imgSrcUrl}"
                // _status.value = "Success: Mars properties retrieved"
                _status.value = MarsApiStatus.DONE

                // _status.value = "Success: ${listResult.size} Mars photos retrieved"
            } catch (e: Exception){
                // Inside the catch {} block, handle the failure response. Display the error message
            // to the user by setting the e.message to the _status.value.
                // _status.value = "Failure: ${e.message}"
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}

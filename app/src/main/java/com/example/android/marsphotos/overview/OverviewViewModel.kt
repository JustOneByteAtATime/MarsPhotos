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
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

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
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                //*17 Assign the result we just received from the backend server to the _status.value.

                // In the method getMarsPhotos(), listResult is a List<MarsPhoto> not a String
                // anymore. The size of that list is the number of photos that were received and
                // parsed. To print the number of photos retrieved update _status.value as follows.
                _status.value = "Success: ${listResult.size} Mars photos retrieved"
            } catch (e: Exception){
                // Inside the catch {} block, handle the failure response. Display the error message
            // to the user by setting the e.message to the _status.value.
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}

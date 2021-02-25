package com.enigma.dio.tictactoe.utils

class ResourceState(val status: ResourceStatus, val data: Any?, val message: String?) {

    companion object {
        fun success(data: Any?): ResourceState =
            ResourceState(status = ResourceStatus.SUCCESS, data = data, message = null)

        fun failure(message: String?) : ResourceState =
            ResourceState(status = ResourceStatus.FAILURE, data = null, message = message)

        fun loading() =
            ResourceState(status = ResourceStatus.LOADING, data = null, message = null)
    }
}
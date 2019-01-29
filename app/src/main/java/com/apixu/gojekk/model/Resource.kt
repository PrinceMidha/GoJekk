package com.apixu.gojekk.model



data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {

    // check this method logic
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val resource = other as Resource<*>?

        return if (status != resource?.status) {
            false
        } else (if (error != null)
            error == resource.error
        else resource.error == null)
                && if (data != null) data == resource.data else resource.data == null
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        result = 31 * result + (error?.hashCode() ?: 0)
        return result
    }

    companion object {

        fun <T> create(data: T?, status: Status, error: Throwable?): Resource<T> {
            return Resource(status, data, error)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, error)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

package com.reprezen.kaizen.oasparser.validate

class ValidationContext : AutoCloseable {

    private val validationResults: ValidationResults
    private val visitedObjectRegistry: VisitedObjectRegistry

    init {
        val context = threadInstance.get()
        check(context == null)
        validationResults = ValidationResults()
        visitedObjectRegistry = VisitedObjectRegistry()
        threadInstance.set(this)
    }

    override fun close() {
        threadInstance.remove()
        check(threadInstance.get() == null)
    }

    companion object {

        var threadInstance = ThreadLocal<ValidationContext?>()

        @JvmStatic
        fun open(): ValidationContext {
            return ValidationContext()
        }

        fun get(): ValidationContext? {
            return threadInstance.get()
        }

        @JvmStatic
        fun getNullableResults() : ValidationResults? {
            val context = get()
            return context?.validationResults
        }

        @JvmStatic
        fun getValidationResults(): ValidationResults {
            return getNullableResults() ?: throw IllegalStateException()
        }

        fun visitIfUnvisited(o: Any): Boolean {
            val context = get()
            return context?.visitedObjectRegistry?.visitIfUnvisited(o) ?: throw IllegalStateException()
        }
    }
}
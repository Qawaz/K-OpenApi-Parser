package com.reprezen.jsonoverlay.parser


@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
@Target(
    AnnotationTarget.FILE,
    AnnotationTarget.CLASS,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.FIELD,
    AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.VALUE_PARAMETER
)
annotation class Generated(
    /**
     * The value element must have the name of the code generator.
     * The recommended convention is to use the fully qualified name of the
     * code generator. For example: `com.acme.generator.CodeGen`.
     */
    vararg val value: String,
    /**
     * Date when the source was generated.
     */
    val date: String = "",
    /**
     * A place holder for any comments that the code generator may want to
     * include in the generated code.
     */
    val comments: String = ""
)
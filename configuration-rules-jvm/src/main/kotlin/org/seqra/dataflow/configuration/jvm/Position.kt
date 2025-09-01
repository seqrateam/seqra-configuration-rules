package org.seqra.dataflow.configuration.jvm

interface PositionResolver<T> {
    fun resolve(position: Position): T
}

sealed interface Position

data class Argument(val index: Int) : Position

data object This : Position {
    override fun toString(): String = javaClass.simpleName
}

data object Result : Position {
    override fun toString(): String = javaClass.simpleName
}

data class ClassStatic(val className: String) : Position

sealed interface PositionAccessor {
    data object ElementAccessor : PositionAccessor {
        override fun toString(): String = javaClass.simpleName
    }

    data object AnyFieldAccessor : PositionAccessor {
        override fun toString(): String = javaClass.simpleName
    }

    data class FieldAccessor(
        val className: String,
        val fieldName: String,
        val fieldType: String
    ) : PositionAccessor
}

data class PositionWithAccess(
    val base: Position,
    val access: PositionAccessor
) : Position

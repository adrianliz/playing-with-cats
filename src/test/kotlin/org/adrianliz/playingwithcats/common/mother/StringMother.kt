package org.adrianliz.playingwithcats.common.mother

class StringMother {
    companion object {
        fun random(): String {
            val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
            return List(10) { charPool.random() }.joinToString("")
        }
    }
}

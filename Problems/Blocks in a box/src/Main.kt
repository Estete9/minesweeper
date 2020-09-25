data class Block(val color: String) {
    object DimProperties {
        var length = 0
        var width = 0
        fun blocksInBox(boxLength: Int, boxWidth: Int): Int {
            return if (length > boxLength || width > boxWidth) 0 else {
                boxLength / length * (boxWidth / width)
            }
        }
    }
}
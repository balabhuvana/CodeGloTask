package com.bala.codeglotask.model

class HeaderItem(name: String) : RecyclerViewItem() {
    override var name: String = ""

    init {
        this.name = name
    }
}
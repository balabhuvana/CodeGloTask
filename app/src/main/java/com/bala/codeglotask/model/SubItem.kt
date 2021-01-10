package com.bala.codeglotask.model

class SubItem(name: String) : RecyclerViewItem() {
    override var name: String = ""

    init {
        this.name = name
    }
}
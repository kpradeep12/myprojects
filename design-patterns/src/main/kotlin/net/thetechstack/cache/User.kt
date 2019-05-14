package net.thetechstack.cache

data class User(val id:Int){
    constructor(id:Int, firstName:String, secondName:String,
                email:String, address:String): this(id)
}
